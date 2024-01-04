package com.moviereview.servlets;

import com.moviereview.dbconnect.ReviewsDao;
import com.moviereview.dbconnect.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete")
public class deleteReview extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesh = req.getSession();
        Users user = (Users) sesh.getAttribute("user");
        int reviewID = Integer.parseInt(req.getParameter("reviewId"));
        try(ReviewsDao rd =new ReviewsDao())
        {
            if(rd.deleteExistingReview(reviewID, user.getId())>0)
                resp.sendRedirect("reviews?type=my");
            else {
                resp.setContentType("text/html");
                resp.getWriter().println("you can only delete your review!!");
                resp.getWriter().println("<a href='reviews?type=my'> Your Reviews </a>");
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
