package org.example;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(value = "/time")
public class TimezoneValidateFilter extends HttpFilter {
    private String timezone;

    @Override
    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse resp,
                            FilterChain chain) throws IOException, ServletException {
        timezone = req.getParameter("timezone");

        if (Pattern.matches("UTC [0-9]", timezone)) {
            chain.doFilter(req, resp);
        } else {
            resp.setStatus(400);
            resp.setContentType("text/html");
            resp.getWriter().write("Invalid timezone");
            resp.getWriter().close();
        }
    }
}
