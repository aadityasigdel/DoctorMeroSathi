package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.model.ScheduleModel;  // Assuming you have a ScheduleModel class
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userprofile")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the doctorId from the query parameter
        String doctorIdParam = request.getParameter("id");

        if (doctorIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Doctor ID is required.");
            return;
        }

        try {
            int doctorId = Integer.parseInt(doctorIdParam);

            UsersModel doctor = getDoctorById(doctorId);
            List<ScheduleModel> schedule = getDoctorSchedule(doctorId); // Get the schedule for the doctor

            if (doctor != null) {
                request.setAttribute("doctor", doctor);
                request.setAttribute("schedule", schedule);  // Add the schedule to the request
                request.getRequestDispatcher("/WEB-INF/view/UserProfile.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found.");
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID.");
        }
    }

    // Method to fetch doctor data from DB
    private UsersModel getDoctorById(int doctorId) {
        UsersModel doctor = null;
        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ? AND role = 'doctor'";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, doctorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        doctor = new UsersModel();
                        doctor.setId(rs.getInt("user_id"));
                        doctor.setFullName(rs.getString("full_name"));
                        doctor.setSpecialization(rs.getString("specialization"));
                        doctor.setProfilePicture(rs.getBytes("profile_picture"));
                        doctor.setExperienceYears(rs.getObject("experience_years") != null ? rs.getInt("experience_years") : null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    // Method to fetch doctor's schedule from DB
    private List<ScheduleModel> getDoctorSchedule(int doctorId) {
        List<ScheduleModel> scheduleList = new ArrayList<>();
        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = "SELECT day, start_time, end_time FROM doctor_schedule WHERE doctor_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, doctorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ScheduleModel schedule = new ScheduleModel();
                        schedule.setDay(rs.getString("day"));
                        schedule.setStartTime(rs.getString("start_time"));
                        schedule.setEndTime(rs.getString("end_time"));
                        scheduleList.add(schedule);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheduleList;
    }
}
