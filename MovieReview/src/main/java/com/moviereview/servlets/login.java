package com.moviereview.servlets;

import com.moviereview.dbconnect.UserDao;
import com.moviereview.dbconnect.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username = req.getParameter("username");
       String password = req.getParameter("password");
       HttpSession sesh = req.getSession();
       Users user = new Users(username, password);
       try(UserDao userDao = new UserDao())
       {

           user = userDao.signInUser(user);
           if(user!=null) {
               resp.sendRedirect("reviews?type=all");
               sesh.setAttribute("user", user);
           }
           resp.setContentType("text/html");
           resp.getWriter().println("<h1 align='center'> Invalid Login </h1>");
           resp.getWriter().println("<h4 align='center'> <a href ='index.html'> Login Again </a></h4>");

       }catch (Exception e)
       {
           e.printStackTrace();
           throw new ServletException(e);
       }


    }
}
