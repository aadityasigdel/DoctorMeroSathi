package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.Util.PasswordHashUtil;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String dbURL = "jdbc:mysql://localhost:3306/DoctorMeroSathiDB";
        String dbUser = "root";
        String dbPass = "your_password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");

                if (PasswordHashUtil.verifyPassword(password, storedHash)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user_id", rs.getInt("user_id"));
                    session.setAttribute("full_name", rs.getString("full_name"));
                    session.setAttribute("role", rs.getString("role"));

                    String role = rs.getString("role");
                    if ("doctor".equals(role)) {
                        response.sendRedirect("doctor_dashboard.jsp");
                    } else if ("customer".equals(role)) {
                        response.sendRedirect("customer_dashboard.jsp");
                    } else if ("admin".equals(role)) {
                        response.sendRedirect("admin_dashboard.jsp");
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                } else {
                    // Wrong password
                    request.setAttribute("error", "Invalid email or password.");
                    request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request, response);
                }
            } else {
                // Email not found
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request, response);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Server error: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(request, response);
        }
    }
}
