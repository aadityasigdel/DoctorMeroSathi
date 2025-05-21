package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.Util.DbConnectionUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/appointmentsCli")
public class DoctorListServlet extends HttpServlet {

    private static final String SELECT_DOCTORS_SQL = "SELECT user_id, full_name, specialization FROM users WHERE role = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UsersModel> doctors = fetchDoctorsFromDatabase();

        if (doctors == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve doctor list");
            return;
        }

        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/view/doctorList.jsp").forward(request, response);
    }

    private List<UsersModel> fetchDoctorsFromDatabase() {
        List<UsersModel> doctors = new ArrayList<>();

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_DOCTORS_SQL)) {

            stmt.setString(1, "doctor"); 

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UsersModel doctor = new UsersModel();
                    doctor.setId(rs.getInt("user_id"));
                    doctor.setFullName(rs.getString("full_name"));
                    doctor.setSpecialization(rs.getString("specialization"));
                    doctors.add(doctor);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return doctors;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Potential future implementation for filtered searches
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}