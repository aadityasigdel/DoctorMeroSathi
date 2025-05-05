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
import java.util.List;

@WebServlet("/appointmentsDoc")
public class AppointmentsDocServlet extends HttpServlet {

    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the current session
        UsersModel user = Authservice.getCurrentUser(request);

        //  redirect to log in if no user session
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(user.getId());
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/WEB-INF/view/AppointmentDoc.jsp").forward(request, response);
    }
}