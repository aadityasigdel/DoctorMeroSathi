package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.doctormerosathi.dao.AppointmentDAO;
import org.example.doctormerosathi.model.UsersModel;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/BookApp")
public class BookAppServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BookAppServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String doctorId = request.getParameter("doctorId");

        if (doctorId == null || doctorId.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing doctorId parameter in request");
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=missing_doctor_id");
            return;
        }

        try {
            int doctorIdInt = Integer.parseInt(doctorId);
            request.setAttribute("doctorId", doctorIdInt);
            request.getRequestDispatcher("/WEB-INF/view/BookApp.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid doctorId format: " + doctorId);
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=invalid_doctor_id");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing booking request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "An error occurred while processing your request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            String dateStr = request.getParameter("appointment_date");
            String timeStr = request.getParameter("appointment_time");
            String reason = request.getParameter("symptoms");

            UsersModel user = (UsersModel) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login?error=not_logged_in");
                return;
            }

            Integer customerId = user.getId();

            // Log the appointment details for debugging
            logger.log(Level.INFO, "Booking appointment for user: {0}, Doctor: {1}, Date: {2}, Time: {3}, Reason: {4}",
                    new Object[]{customerId, doctorId, dateStr, timeStr, reason});

            String appointmentDateTime = dateStr + " " + timeStr + ":00";

            logger.log(Level.INFO, "Combined Appointment DateTime: " + appointmentDateTime);

            Timestamp appointmentTimestamp = Timestamp.valueOf(appointmentDateTime);

            AppointmentDAO dao = new AppointmentDAO();
            dao.bookAppointment(customerId, doctorId, appointmentTimestamp, reason);

            logger.log(Level.INFO, "Appointment booked successfully for user: {0} with doctor: {1}", new Object[]{customerId, doctorId});
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?success=booked");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to book appointment", e);
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=booking_failed");
        }
    }
}
