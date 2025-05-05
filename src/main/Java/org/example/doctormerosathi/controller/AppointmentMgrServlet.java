package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.Appointment;
import org.example.doctormerosathi.services.AppointmentService;

import java.io.IOException;
import java.util.List;

// to display and manage appointment data
@WebServlet("/admin/appointmentmgr")
public class AppointmentMgrServlet extends HttpServlet {
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
            // Fetch all appointments
            List<Appointment> appointments = appointmentService.getAllAppointments();

            // Set appointments as an attribute for the JSP page
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/WEB-INF/view/appointmentmgr.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error (maybe redirect to an error page)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to load appointments.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Check for the action parameter to handle deletion
            String action = request.getParameter("action");

            if ("delete".equals(action)) {
                String appointmentIdStr = request.getParameter("id");

                if (appointmentIdStr != null && !appointmentIdStr.isEmpty()) {
                    // Parse the ID
                    int appointmentId = Integer.parseInt(appointmentIdStr);

                    // Call the AppointmentService to delete the appointment
                    boolean success = appointmentService.deleteAppointment(appointmentId);

                    if (success) {
                        request.setAttribute("successMessage", "Appointment deleted successfully.");
                    } else {
                        request.setAttribute("errorMessage", "Appointment not found or couldn't be deleted.");
                    }
                } else {
                    request.setAttribute("errorMessage", "Invalid appointment ID.");
                }
            }

            // Redirect back to the appointment management page
            response.sendRedirect(request.getContextPath() + "/admin/appointmentmgr");

        } catch (Exception e) {
            e.printStackTrace();
            // Handle any other errors
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to process the request.");
        }
    }

}
