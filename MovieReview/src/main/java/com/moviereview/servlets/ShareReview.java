package com.moviereview.servlets;

import com.moviereview.dbconnect.ReviewsDao;
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

@WebServlet("/share")
public class ShareReview extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        try(ReviewsDao rd = new ReviewsDao())
        {
            if(rd.shareReviewWithOthers(reviewId, userId)>0)
                resp.sendRedirect("reviews?type=all");
            else {
                resp.setContentType("text/html");
                resp.getWriter().println("something is wrong with you!!");
                resp.getWriter().println("<a href='reviews?type=my'> Reviews </a>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession sesh = req.getSession();
        Users user = (Users) sesh.getAttribute("user");
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<div align ='center'>");
        out.println("</br></br>");
        out.println("<h1>Share your reviews, Mr. " + user.getF_name() + "</h1>");
        out.println("</br></br>");
        out.println("<form action = 'share' method ='post'>");

        out.printf("<h4> Review ID : <input type='text' name='reviewId' value=%d readonly/> </h4>", reviewId);
        try {
            List<Users> usersList = new UserDao().getAllUsers();
            out.println(" <h4> Users : <select name='userId'>");
            for(Users u : usersList) {
                if(u.getId()==user.getId())
                    continue;
                String name = u.getF_name() + " " + u.getL_name();
                out.printf("  <option value=%d>%s</option> ", u.getId(), name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</select></h4></br>");


        out.println("<input type='submit' value='Share it!'/>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
