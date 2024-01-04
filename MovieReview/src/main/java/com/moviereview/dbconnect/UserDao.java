package com.moviereview.dbconnect;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDao implements AutoCloseable {

    Connection con;
    String sql;
    PreparedStatement signIn;
    PreparedStatement signUp;
    PreparedStatement editUser;
    PreparedStatement updatePass;
    PreparedStatement allUsers;
    public UserDao() throws SQLException {
        con = DbUtil.getConnect();
        sql = "select * from users where email = ? and password = ?";
        signIn = con.prepareStatement(sql);
        sql = "INSERT INTO users values (default, ?, ?, ?, ?, ?, ?)";
        signUp = con.prepareStatement(sql);
        sql = "UPDATE users set first_name = ?, last_name = ?, mobile = ?, birth = ? where id = ?";
        editUser = con.prepareStatement(sql);
        sql = "update users set password = ? where id = ?";
        updatePass = con.prepareStatement(sql);
        sql = "select * from users";
        allUsers = con.prepareStatement(sql);

    }

    public List<Users> getAllUsers() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        ResultSet rs = allUsers.executeQuery();
        while(rs.next())
        {
            Users u = new Users();
            u.id = rs.getInt(1);
            u.f_name = rs.getString(2);
            u.l_name = rs.getString(3);
            u.email = rs.getString(4);
            u.pass = rs.getString(5);
            u.mobile = rs.getString(6);
            usersList.add(u);
        }
        return usersList;

    }
    public Users signInUser(Users u) throws SQLException {

        signIn.setString(1, u.getEmail());
        signIn.setString(2, u.getPass());
        try(ResultSet rs = signIn.executeQuery())
        {
            if(rs.next())
                return new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7));
            else
                return null;
        }

    }

    public int signUpUser(Users u) throws SQLException {

        signUp.setString(1, u.getF_name());
        signUp.setString(2, u.getL_name());
        signUp.setString(3, u.getEmail());
        signUp.setString(4, u.getPass());
        signUp.setString(5, u.getMobile());
        signUp.setDate(6, new Date (u.getBirth().getTime()));
        return signUp.executeUpdate();

    }

    public int editUser(Users u) throws SQLException, ParseException {

        u.editUser();

        editUser.setString(1, u.getF_name());
        editUser.setString(2, u.getL_name());
        editUser.setString(3, u.getMobile());
        editUser.setDate(4, new Date(u.getBirth().getTime()));
        editUser.setInt(5, u.getId());

        return editUser.executeUpdate();
    }

    public int updatePass(Users u) throws SQLException {
        u.changePassword();
        updatePass.setString(1, u.getPass());
        updatePass.setInt(2, u.getId());
        return updatePass.executeUpdate();
    }

    @Override
    public void close() throws Exception {
        allUsers.close();
        updatePass.close();
        editUser.close();
        signUp.close();
        signIn.close();
        con.close();
    }
}
