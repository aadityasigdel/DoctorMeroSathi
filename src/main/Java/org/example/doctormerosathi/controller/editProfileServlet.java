package org.example.doctormerosathi.controller;

import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

//to edit user profile
@WebServlet("/edit-profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class editProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsersModel currentUser = Authservice.getCurrentUser(request);
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        //To get  user data from th dataabase
        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, currentUser.getId());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        UsersModel user = new UsersModel();
                        user.setId(rs.getInt("user_id"));
                        user.setEmail(rs.getString("email"));
                        user.setFullName(rs.getString("full_name"));
                        user.setPhoneNumber(rs.getString("phone"));
                        user.setGender(rs.getString("gender"));
                        user.setProfilePicture(rs.getBytes("profile_picture"));
                        user.setAddress(rs.getString("address"));
                        user.setDob(rs.getDate("dob"));
                        user.setSpecialization(rs.getString("specialization"));
                        user.setExperienceYears(rs.getObject("experience_years", Integer.class));
                        user.setCreatedAt(rs.getTimestamp("created_at"));

                        request.setAttribute("user", user);
                        request.getRequestDispatcher("/WEB-INF/view/editProfile.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "User not found in database");
                        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        UsersModel currentUser = (UsersModel) session.getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = currentUser.getId();

        String fullName = request.getParameter("full_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String specialization = request.getParameter("specialization");
        Integer experienceYears = null;

        try {
            experienceYears = Integer.parseInt(request.getParameter("experience_years"));
        } catch (NumberFormatException ignored) {}

        // file upload
        byte[] profilePicture = null;
        Part filePart = request.getPart("profile_picture");

        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream fileContent = filePart.getInputStream()) {
                profilePicture = fileContent.readAllBytes();
            } catch (IOException e) {
                request.setAttribute("error", "Error reading profile picture: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/Editprofile.jsp").forward(request, response);
                return;
            }
        }

        // Update user in Database
        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql;
            PreparedStatement stmt;

            if (profilePicture != null) {
                sql = "UPDATE users SET full_name=?, email=?, phone=?, gender=?, address=?, "
                        + "profile_picture=?, specialization=?, experience_years=? WHERE user_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, fullName);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, gender);
                stmt.setString(5, address);
                stmt.setBytes(6, profilePicture);
                stmt.setString(7, specialization);
                stmt.setObject(8, experienceYears);
                stmt.setInt(9, userId);
            } else {
                sql = "UPDATE users SET full_name=?, email=?, phone=?, gender=?, address=?, "
                        + "specialization=?, experience_years=? WHERE user_id=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, fullName);
                stmt.setString(2, email);
                stmt.setString(3, phone);
                stmt.setString(4, gender);
                stmt.setString(5, address);
                stmt.setString(6, specialization);
                stmt.setObject(7, experienceYears);
                stmt.setInt(8, userId);
            }

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Refresh session user
                String fetchSql = "SELECT * FROM users WHERE user_id = ?";
                try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSql)) {
                    fetchStmt.setInt(1, userId);
                    try (ResultSet rs = fetchStmt.executeQuery()) {
                        if (rs.next()) {
                            UsersModel updatedUser = new UsersModel();
                            updatedUser.setId(rs.getInt("user_id"));
                            updatedUser.setEmail(rs.getString("email"));
                            updatedUser.setFullName(rs.getString("full_name"));
                            updatedUser.setPhoneNumber(rs.getString("phone"));
                            updatedUser.setGender(rs.getString("gender"));
                            updatedUser.setProfilePicture(rs.getBytes("profile_picture"));
                            updatedUser.setAddress(rs.getString("address"));
                            updatedUser.setDob(rs.getDate("dob"));
                            updatedUser.setSpecialization(rs.getString("specialization"));
                            updatedUser.setExperienceYears(rs.getObject("experience_years", Integer.class));
                            updatedUser.setCreatedAt(rs.getTimestamp("created_at"));

                            session.setAttribute("user", updatedUser);
                        }
                    }
                }

                session.setAttribute("successMessage", "Profile updated successfully!");
                response.sendRedirect(request.getContextPath() + "/profile");

            } else {
                request.setAttribute("error", "No changes made to profile.");
                request.getRequestDispatcher("/WEB-INF/view/Editprofile.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/Editprofile.jsp").forward(request, response);
        }
    }
}
