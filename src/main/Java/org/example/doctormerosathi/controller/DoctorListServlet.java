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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<UsersModel> doctors = new ArrayList<>();

        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE role = 'doctor'";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    UsersModel doc = new UsersModel();
                    doc.setId(rs.getInt("user_id"));
                    doc.setFullName(rs.getString("full_name"));
                    doc.setSpecialization(rs.getString("specialization"));
                    // add other fields as needed
                    doctors.add(doc);
                }
            }

            request.setAttribute("doctors", doctors);
            request.getRequestDispatcher("/WEB-INF/view/doctorList.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
