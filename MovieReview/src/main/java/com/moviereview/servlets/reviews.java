package com.moviereview.servlets;

import com.moviereview.dbconnect.Reviews;
import com.moviereview.dbconnect.ReviewsDao;
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

@WebServlet("/reviews")
public class reviews extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession sesh = req.getSession();
        String type = req.getParameter("type");
        Users user = (Users) sesh.getAttribute("user");
        if(user==null)
            resp.sendRedirect("login");
        out.println("<html>");
        out.println("<body>");
        out.println("<h2 align='center'> Helloo "+ user.getF_name() + "</h2>");
        out.println(" <hr> ");
        out.println("<h4>" + "<a href='reviews?type=all'> All Reviews </a> </h4>" + "<h4>  " +
                "<a href='reviews?type=my'> Your Reviews </a>" + " </h4> " +
                " <h4><a href='reviews?type=shared'> Reviews Shared with you </a>" +
                "</h4>");
        out.println(" <hr> ");
        out.println(" <h3> "+type+" reviews </h3>");
        out.println("<table align='center'>");
        out.println("<thead> <th> Id </th> <th> Movie Id </th> <th> Review </th> <th> Rating </th> <th> User ID </th> <th> Date Modified </th> </tr> </thead>");
        try(ReviewsDao rd = new ReviewsDao()) {
            List<Reviews> list = null;
            if(type.equals("my")) {
                list = rd.displayMyReviews(user.getId());
                for(Reviews r : list)
                {
                    out.printf("<tr> <td> %d </td> <td> %d </td> <td> %s </td> <td> %d </td> <td> %d </td> <td> %s </td> <td> <a href='delete?reviewId=%d'>Delete</a> <a href='edit?reviewId=%d'>Edit</a> <a href='share?reviewId=%d'>Share</a></td> </tr>", r.getId(), r.getMovieId(), r.getReview(), r.getRating(), r.getUserId(), r.getModified().toString(), r.getId(), r.getId(), r.getId());
                }
            }
            else {
                if (type.equals("shared"))
                    list = rd.displaySharedWithMeReviews(user.getId());
                else if (type.equals("all"))
                    list = rd.displayAllReviews();
                for (Reviews r : list) {
                    out.printf("<tr> <td> %d </td> <td> %d </td> <td> %s </td> <td> %d </td> <td> %d </td> <td> %s </td></tr>", r.getId(), r.getMovieId(), r.getReview(), r.getRating(), r.getUserId(), r.getModified().toString());
                }
            }
                out.println("</table>");
                out.println("</br><hr>");
                out.println("<h4><a href='addReview'>Add Review </a></h4>");
                out.println("</br><hr>");
                out.println("<p><a href='logout'> Log Out </a></p>");
                out.println("</body>");
                out.println("</html>");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

