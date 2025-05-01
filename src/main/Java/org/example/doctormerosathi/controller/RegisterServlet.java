package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.Util.PasswordHashUtil;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import java.io.IOException;
import java.sql.*;

//To receive valid user information and  store the information in the database
@WebServlet(name = "signup", value = "/signup")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form parameters
        String name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String specialization = request.getParameter("specialization");
        String experience = request.getParameter("experience");

        // Validate input (email, password, etc.)
        if (name == null || email == null || password == null || role == null) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
            return;
        }

        // Hash the password before storing it
        String hashedPassword = PasswordHashUtil.hashPassword(password);

        // Database connection and SQL query details
        String sql = "INSERT INTO users (email, password, role, full_name, phone, gender, dob, specialization, experience_years, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, role);
            stmt.setString(4, name);
            stmt.setString(5, phone);
            stmt.setString(6, gender);


            if (dob != null && !dob.isEmpty()) {
                try {
                    java.sql.Date sqlDob = java.sql.Date.valueOf(dob);
                    stmt.setDate(7, sqlDob);
                } catch (IllegalArgumentException e) {
                    request.setAttribute("error", "Invalid date format for Date of Birth.");
                    request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
                    return;
                }
            } else {
                stmt.setDate(7, null);
            }

            stmt.setString(8, specialization);
            stmt.setInt(9, (experience != null && !experience.isEmpty()) ? Integer.parseInt(experience) : 0); // Default to 0 if empty or invalid


            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {

                // Redirect to login page
                response.sendRedirect(request.getContextPath()+"/login");
            } else {
                // Handle failure in registration
                request.setAttribute("error", "An error occurred during registration. Please try again.");
                request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle unexpected errors
            request.setAttribute("error", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/SignUp.jsp").forward(request, response);
        }
    }
}
