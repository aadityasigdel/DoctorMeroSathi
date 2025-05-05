package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.Review;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.model.ScheduleModel;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userprofile")
public class ViewProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String doctorIdParam = request.getParameter("id");

        if (doctorIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Doctor ID is required.");
            return;
        }

        try {
            int doctorId = Integer.parseInt(doctorIdParam);

            UsersModel doctor = getDoctorById(doctorId);
            List<ScheduleModel> schedule = getDoctorSchedule(doctorId);
            List<Review> reviews = getDoctorReviews(doctorId);

            if (doctor != null) {
                request.setAttribute("doctor", doctor);
                request.setAttribute("schedule", schedule);
                request.setAttribute("reviews", reviews);
                request.getRequestDispatcher("/WEB-INF/view/UserProfile.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Doctor ID.");
        }
    }

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
                        doctor.setExperienceYears(rs.getInt("experience_years"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

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

    private List<Review> getDoctorReviews(int doctorId) {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = """
                SELECT r.review_id, r.user_id, r.doctor_id, r.rating, r.review, r.created_at, u.full_name
                FROM reviews r
                JOIN users u ON r.user_id = u.user_id
                WHERE r.doctor_id = ?
                ORDER BY r.created_at DESC
            """;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, doctorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Review review = new Review();
                        review.setReviewId(rs.getInt("review_id"));
                        review.setUserId(rs.getInt("user_id"));
                        review.setDoctorId(rs.getInt("doctor_id"));
                        review.setRating(rs.getInt("rating"));
                        review.setReview(rs.getString("review"));
                        review.setCreatedAt(rs.getTimestamp("created_at"));
                        review.setUserName(rs.getString("full_name"));
                        reviews.add(review);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
