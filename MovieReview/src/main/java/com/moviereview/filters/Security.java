package com.moviereview.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebFilter({"/addReview", "/delete", "/edit", "/reviews", "/logout", "/share"})
public class Security implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if(req.getSession().getAttribute("user")!=null) {
            chain.doFilter(request, response);
            return;
        }
        res.sendRedirect("index.html");

    }
}
