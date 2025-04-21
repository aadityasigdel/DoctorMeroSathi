package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import static org.example.doctormerosathi.services.Authservice.isAuthenticated;

import java.io.IOException;

@WebServlet("/view-appointments")
public class AppointmentRedirectServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            if (!Authservice.isAuthenticated(request)) {
                // Redirect to login page if not authenticated
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Get the current authenticated user from the session
            UsersModel user = Authservice.getCurrentUser(request);


            if (user == null) {
            // If user is not in the session, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Check user role and redirect accordingly
        if ("doctor".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/appointmentsDash.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/appointmentsCli");
        }
    }
}
