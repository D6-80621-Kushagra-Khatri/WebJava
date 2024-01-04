package com.moviereview.servlets;

import com.moviereview.dbconnect.*;
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

@WebServlet("/addReview")
public class AddReview extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = Integer.parseInt(req.getParameter("userId"));
        int movieId = Integer.parseInt(req.getParameter("movieId"));
        String review = req.getParameter("review");
        int rating = Integer.parseInt(req.getParameter("rating"));
        try(ReviewsDao rd = new ReviewsDao())
        {
            if(rd.createNewReview(new Reviews(movieId, userId, review,rating))>0)
                resp.sendRedirect("reviews?type=my");
            else
                throw new RuntimeException("cannot insert");

        }catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession sesh = req.getSession();
        Users user = (Users) sesh.getAttribute("user");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<div align ='center'>");
        out.println("</br></br>");
        out.println("<h1>Add a review Mr." + user.getF_name() + "</h1>");
        out.println("</br></br>");
        out.println("<form action = 'addReview' method ='post'>");
        out.printf("<h4> User ID : <input type='number' name='userId' value = %d readonly/> </h4>\n", user.getId());

        try {
            List<Movies> moviesList = new MovieDao().displayAllMovies();
            out.println(" <h4> Movie : <select name='movieId'>");
            for(Movies m : moviesList) {
                out.printf("  <option value=%d> %s </option> \n", m.getId() ,m.getTitle());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</select></br>");
        out.println("<h4> Review : <input type='text' name='review' /> </h4>");
        out.println("<h4> Rating : <input type='number' name='rating' /> </h4>");
        out.println("<input type='submit' value='Add it!'/>");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
