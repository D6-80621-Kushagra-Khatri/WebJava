package com.moviereview.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    public static Connection getConnect()  {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackathon?useSSL=false", "D6_80621_KUSHAGRA", "sunbeam");
            if(conn != null){
                System.out.println("connection is NOT null");
                return conn;
            }else {
                System.out.println("connection is null");
            }
        }
        catch (Exception e)
        {
           throw new RuntimeException("!!!!");
        }
        return null;
    }
}
