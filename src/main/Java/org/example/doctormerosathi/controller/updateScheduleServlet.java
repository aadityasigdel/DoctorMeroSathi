package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.doctormerosathi.dao.ScheduleDAO;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;

@WebServlet("/updateSchedule")
public class updateScheduleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is authenticated
        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Retrieve the currently logged-in user
        UsersModel user = Authservice.getCurrentUser(request);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get doctor ID from user object
        int doctorId = user.getId(); // Make sure UsersModel has getId()

        // Get session and form parameters
        HttpSession session = request.getSession();
        String[] daysArray = request.getParameterValues("days");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        ScheduleDAO dao = new ScheduleDAO();
        boolean duplicateFound = false;
        for (String day : daysArray) {
            boolean success = dao.insertSchedule(doctorId, day, startTime, endTime);
            if (!success) {
                duplicateFound = true; // If the schedule for that day already exists
            }
        }

        if (duplicateFound) {
            session.setAttribute("message", "Some days were already scheduled and skipped.");
        } else {
            session.setAttribute("message", "Schedule updated successfully.");
        }

        // Redirect back to profile
        response.sendRedirect(request.getContextPath() + "/profile");
    }
}
