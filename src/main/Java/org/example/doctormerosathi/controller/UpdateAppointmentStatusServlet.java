package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.AppointmentService;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;

@WebServlet("/updateAppointmentStatus")
public class UpdateAppointmentStatusServlet extends HttpServlet {
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Authentication check
        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel user = Authservice.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String status = request.getParameter("status");
        String appointmentIdStr = request.getParameter("appointmentId");

        try {
            // Validate appointment ID
            if (appointmentIdStr == null || appointmentIdStr.trim().isEmpty()) {
                request.getSession().setAttribute("errorMessage", "Appointment ID cannot be empty");
                response.sendRedirect(request.getContextPath() + "/appointmentsDoc");
                return;
            }

            int appointmentId = Integer.parseInt(appointmentIdStr);
            if (appointmentId <= 0) {
                request.getSession().setAttribute("errorMessage", "Invalid Appointment ID");
                response.sendRedirect(request.getContextPath() + "/appointmentsDoc");
                return;
            }

            boolean success = false;
            String resultMessage = "";

            if ("completed".equalsIgnoreCase(status)) {
                // Complete appointment
                success = appointmentService.completeAppointment(appointmentId, user.getId());
                resultMessage = success ? "Appointment completed successfully"
                        : "Failed to complete appointment";
            }
            else if ("cancelled".equalsIgnoreCase(status)) {
                // Cancel appointment with reason
                String cancellationReason = request.getParameter("cancellationReason");
                if (cancellationReason == null || cancellationReason.trim().isEmpty()) {
                    cancellationReason = "Cancelled by doctor";
                }

                success = appointmentService.cancelAppointment(
                        appointmentId,
                        user.getId(),
                        cancellationReason
                );
                resultMessage = success ? "Appointment cancelled successfully"
                        : "Failed to cancel appointment";
            }
            else {
                request.getSession().setAttribute("errorMessage", "Invalid status action");
                response.sendRedirect(request.getContextPath() + "/appointmentsDoc");
                return;
            }

            // Set appropriate flash message
            if (success) {
                request.getSession().setAttribute("successMessage", resultMessage);
            } else {
                request.getSession().setAttribute("errorMessage", resultMessage);
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Invalid Appointment ID format");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage",
                    "An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/appointmentsDoc");
    }
}