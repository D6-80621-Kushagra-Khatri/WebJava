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
import java.util.List;

@WebServlet("/edit")
public class EditReview extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sesh = req.getSession();
        Users user = (Users) sesh.getAttribute("user");
        int RId = Integer.parseInt(req.getParameter("reviewId"));
        resp.setContentType("text/html");
        try(ReviewsDao rd= new ReviewsDao()) {
            List<Reviews> list = rd.displayMyReviews(user.getId());

            for( Reviews r : list)
            {
                if(r.getId()==RId)
                {
                    PrintWriter out = resp.getWriter();
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<div align ='center'>");
                    out.println("</br></br>");
                    out.println("<h1>Edit your review Mr." + user.getF_name() + "</h1>");
                    out.println("</br></br>");

                    out.println("<form action = 'edit' method ='post'>");
                    out.printf("<h4> Review ID : <input type='text' name='reviewId' value = %d readonly/> </h4>\n", RId);
                    out.printf("<h4> User ID : <input type='text' name='userId' value = %d  readonly/> </h4>", user.getId());
                    out.printf("<h4> Review : <input type='text' name='review' value='%s'/> </h4>", r.getReview());
                    out.printf("<h4> Rating : <input type='text' name='rating' value = %d /> </h4>", r.getRating());
                    out.println("<input type='submit' value='Edit it!'/>");
                    out.println("</form>");

                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        String review = req.getParameter("review");
        int rating = Integer.parseInt(req.getParameter("rating"));

        try(ReviewsDao rd = new ReviewsDao())
        {
            if(rd.updateExistingReview(new Reviews(review,reviewId, userId, rating))>0)
                resp.sendRedirect("reviews?type=my");
            else {
                resp.setContentType("text/html");
                resp.getWriter().println("something is wrong with you!!");
                resp.getWriter().println("<a href='reviews?type=my'> Reviews </a>");
            }

        }catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
