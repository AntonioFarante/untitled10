package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/time")
public class MainServlet extends HttpServlet {
    private String timezone;
    private Calendar cal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        timezone = req.getParameter("timezone");
        cal = Calendar.getInstance();
        cal.setTime(new Date());

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        if (timezone != (null)) {
            String[] s = timezone.split(" ");
            int hours = Integer.parseInt(s[1]);
            cal.add(Calendar.HOUR_OF_DAY, hours - 2);
            Date calTime = cal.getTime();
            String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calTime);
            printWriter.write(formattedTime + " " + timezone);
        } else {
            cal.add(Calendar.HOUR_OF_DAY, -2);
            Date calTime = cal.getTime();
            String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calTime);
            printWriter.write(formattedTime + " UTC");
        }

        printWriter.close();
    }

}