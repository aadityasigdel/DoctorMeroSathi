package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;
import org.example.doctormerosathi.Util.PasswordHashUtil;
import org.example.doctormerosathi.Util.DbConnectionUtil;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the SQL query
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    String storedHash = rs.getString("password");

                    if (PasswordHashUtil.verifyPassword(password, storedHash)) {
                        // If login is successful, create a UsersModel object and use Authservice to create session
                        UsersModel user = new UsersModel();
                        user.setId(rs.getInt("user_id"));
                        user.setFullName(rs.getString("full_name"));
                        user.setRole(rs.getString("role"));


                        Authservice.createUserSession(request, user, 30 * 60); // 30 minutes timeout


                        String role = user.getRole();

                        if ("admin".equals(role)) {
                            response.sendRedirect(request.getContextPath()+"/admin/dashboard");

                        }
                        else {
                            response.sendRedirect(request.getContextPath() + "/home");
                        }

                    } else {
                        // Invalid password
                        request.setAttribute("error", "Invalid email or password.");
                        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                    }
                } else {
                    // Email not found
                    request.setAttribute("error", "Invalid email or password.");
                    request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Database error: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any other exception that may occur
            request.setAttribute("error", "Server error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}
