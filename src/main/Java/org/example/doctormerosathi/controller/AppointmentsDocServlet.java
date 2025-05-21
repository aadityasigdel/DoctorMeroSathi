package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.example.doctormerosathi.model.Appointment;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.AppointmentService;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/appointmentsDoc")
public class AppointmentsDocServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AppointmentsDocServlet.class.getName());
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel user = Authservice.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(user.getId());
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/WEB-INF/view/AppointmentDoc.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel user = Authservice.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        String appointmentIdStr = request.getParameter("appointmentId");
        int appointmentId = Integer.parseInt(appointmentIdStr);

        boolean success = false;

        // Handle the complete action
        if ("complete".equals(action)) {
            success = appointmentService.completeAppointment(appointmentId, user.getId());
        }
        // Handle the cancel action
        else if ("cancel".equals(action)) {
            String cancellationReason = request.getParameter("cancellationReason");
            success = appointmentService.cancelAppointment(appointmentId, user.getId(), cancellationReason);
        } else {
            // If the action is invalid, set error message
            request.setAttribute("errorMessage", "Invalid action.");
            request.getRequestDispatcher("/WEB-INF/view/AppointmentDoc.jsp").forward(request, response);
            return;
        }

        // After completing or canceling, fetch updated appointments list
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(user.getId());
        request.setAttribute("appointments", appointments);


        request.getRequestDispatcher("/WEB-INF/view/AppointmentDoc.jsp").forward(request, response);

        if (success) {
            logger.severe("successMessage");
        } else {
            logger.severe("errorMessage");
        }

        request.getRequestDispatcher("/WEB-INF/view/AppointmentDoc.jsp").forward(request, response);
    }
}


