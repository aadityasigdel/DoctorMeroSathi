package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.dao.AppointmentDAO;
import org.example.doctormerosathi.model.UsersModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/addApointment")
public class AdminAppointmentbook extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AdminAppointmentbook.class.getName());
    private static final String SELECT_DOCTORS_SQL = "SELECT user_id, full_name, specialization FROM users WHERE role = ?";

    // Method to fetch doctors from database
    private List<UsersModel> fetchDoctorsFromDatabase() {
        List<UsersModel> doctors = new ArrayList<>();

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_DOCTORS_SQL)) {

            stmt.setString(1, "doctor");
            System.out.println("Executing query...");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UsersModel doctor = new UsersModel();
                    doctor.setId(rs.getInt("user_id"));
                    doctor.setFullName(rs.getString("full_name"));
                    doctor.setSpecialization(rs.getString("specialization"));
                    doctors.add(doctor);
                }
            }

            System.out.println("Doctors fetched: " + doctors.size());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching doctors: " + e.getMessage());
            return null;
        }

        return doctors;
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Fetch the list of doctors from the database
        List<UsersModel> doctors = fetchDoctorsFromDatabase();

        // Check if doctors are available, then set it as a request attribute
        if (doctors != null && !doctors.isEmpty()) {
            request.setAttribute("doctors", doctors);
        } else {
            request.setAttribute("error", "No doctors available.");
        }

        // Forward to the admin appointment booking page
        request.getRequestDispatcher("/WEB-INF/view/AdminAppointmnetBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            String dateStr = request.getParameter("appointment_date");
            String timeStr = request.getParameter("appointment_time");
            String reason = request.getParameter("symptoms");

            // Retrieve the logged-in admin
            UsersModel admin = (UsersModel) request.getSession().getAttribute("user");
            if (admin == null) {
                response.sendRedirect(request.getContextPath() + "/login?error=not_logged_in");
                return;
            }

            Integer adminId = admin.getId();

            String appointmentDateTime = dateStr + " " + timeStr + ":00";
            Timestamp appointmentTimestamp = Timestamp.valueOf(appointmentDateTime);

            logger.log(Level.INFO, "Admin: {0} booking appointment for doctor: {1} on {2} with reason: {3}",
                    new Object[]{adminId, doctorId, appointmentDateTime, reason});

            AppointmentDAO dao = new AppointmentDAO();
            dao.bookAppointment(adminId, doctorId, appointmentTimestamp, reason);

            logger.log(Level.INFO, "Appointment booked successfully by admin: {0} with doctor: {1}", new Object[]{adminId, doctorId});
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?success=booked");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to book appointment", e);
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=booking_failed");
        }
    }
}
