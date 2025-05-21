package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {

    private static final String SELECT_DOCTORS_SQL = "SELECT user_id, full_name, specialization FROM users WHERE role = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel user = Authservice.getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            List<UsersModel> doctors = fetchDoctorsFromDatabase();
            request.setAttribute("doctors", doctors);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/view/Home.jsp").forward(request, response);
    }

    private List<UsersModel> fetchDoctorsFromDatabase() throws SQLException {
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
        }
        return doctors;

    }
}
