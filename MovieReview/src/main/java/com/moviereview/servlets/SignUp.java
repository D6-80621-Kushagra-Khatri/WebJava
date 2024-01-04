package com.moviereview.servlets;

import com.moviereview.dbconnect.MovieDao;
import com.moviereview.dbconnect.Movies;
import com.moviereview.dbconnect.UserDao;
import com.moviereview.dbconnect.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/signup")
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String f_name = req.getParameter("f_name");
        String l_name = req.getParameter("l_name");
        String email =req.getParameter("email");
        String pass = req.getParameter("pass");
        String mob = req.getParameter("mob");
        String dob = req.getParameter("dob");
        try(UserDao ud =new UserDao())
        {
            Users u = new Users(f_name, l_name, email, pass, mob, dob );
            if(ud.signUpUser(u)>0)
            {
                resp.setContentType("text/html");
                resp.getWriter().println("Sign up successful please sign in ");
                resp.getWriter().println("<br>");
                resp.getWriter().println("<a href='index.html'>Sign In </a>");
            }
            else
            {
                resp.setContentType("text/html");
                resp.getWriter().println("Sign up UNsuccessful please sign up properly ");
                resp.getWriter().println("<br>");
                resp.getWriter().println("<a href='signup'>Sign UP again :< </a>");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<div align ='center'>");
        out.println("</br></br>");
        out.println("<h1>Enter Details </h1>");
        out.println("</br></br>");
        out.println("<form action = 'signup' method ='post'>");
        out.printf("<h4> First Name : <input type='text' name='f_name' /> </h4>");
        out.printf("<h4> Last Name : <input type='text' name='l_name' /> </h4>");
        out.printf("<h4> Email : <input type='text' name='email' /> </h4>");
        out.printf("<h4> Password : <input type='text' name='pass' /> </h4>");
        out.printf("<h4> Mobile : <input type='text' name='mob' /> </h4>");
        out.printf("<h4> Date of Birth (dd-mm-yyyy) : <input type='text' name='dob' /> </h4>");

        out.println("<input type='submit' value='Sign Up!'/>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

    }
}
