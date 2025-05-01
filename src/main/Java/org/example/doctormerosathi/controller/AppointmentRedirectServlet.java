package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import static org.example.doctormerosathi.services.Authservice.isAuthenticated;

import java.io.IOException;


//to redirect doctor and user to diff appointment page
@WebServlet("/view-appointments")
public class AppointmentRedirectServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            // Redirect to login page if not authenticated
            if (!Authservice.isAuthenticated(request)) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Get the current authenticated user from the session
            UsersModel user = Authservice.getCurrentUser(request);

            //redirect to login page is user isn't log in
            if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Check if  user is doctor or  not and redirect correct
        if ("doctor".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/appointmentsDoc");
        } else {
            response.sendRedirect(request.getContextPath() + "/appointmentsCli");
        }
    }
}
