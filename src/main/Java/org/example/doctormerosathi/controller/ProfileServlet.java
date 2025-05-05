package org.example.doctormerosathi.controller;

import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;
import java.sql.*;


// to display user profile
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            // Redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the current session
        UsersModel user = Authservice.getCurrentUser(request);

        if (user == null) {
            //  redirect to login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // for editing profile
        String editParam = request.getParameter("edit");
        boolean isEdit = "true".equalsIgnoreCase(editParam);

        // If not already populated, fetch additional data (like profile picture) from the DB
        if (user.getProfilePicture() == null || user.getFullName() == null) {
            try (Connection conn = DbConnectionUtil.getConnection()) {
                String sql = "SELECT * FROM users WHERE user_id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, user.getId());
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            user.setEmail(rs.getString("email"));
                            user.setPasswordHash(rs.getString("password"));
                            user.setRole(rs.getString("role"));
                            user.setFullName(rs.getString("full_name"));
                            user.setPhoneNumber(rs.getString("phone"));
                            user.setGender(rs.getString("gender"));
                            user.setProfilePicture(rs.getBytes("profile_picture"));
                            user.setAddress(rs.getString("address"));
                            user.setDob(rs.getDate("dob"));
                            user.setSpecialization(rs.getString("specialization"));
                            user.setExperienceYears(rs.getObject("experience_years") != null ? rs.getInt("experience_years") : null);
                            user.setCreatedAt(rs.getTimestamp("created_at"));
                        } else {
                            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                            return;
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
                return;
            }
        }

        // user data  to display
        request.setAttribute("user", user);

        // Forward to the appropriate page based on the "edit" parameter
        if (isEdit) {
            request.getRequestDispatcher("/WEB-INF/view/Editprofile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/Profile.jsp").forward(request, response);
        }


    }
}


