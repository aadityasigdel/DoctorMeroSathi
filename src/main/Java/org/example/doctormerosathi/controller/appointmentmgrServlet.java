package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.Appointment;
import org.example.doctormerosathi.services.AppointmentService;
import java.io.IOException;
import java.util.List;


//to  display and manage  appointment data
@WebServlet("/admin/appointmentmgr")
public class appointmentmgrServlet extends HttpServlet {
    private AppointmentService appointmentService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.appointmentService = new AppointmentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();

            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/WEB-INF/view/appointmentmgr.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}