package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/submitReview")
public class SubmitReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UsersModel currentUser = (UsersModel) session.getAttribute("user");

        int userId = currentUser.getId();
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String reviewText = request.getParameter("review");

        try (Connection conn = DbConnectionUtil.getConnection()) {
            String sql = "INSERT INTO reviews (user_id, doctor_id, rating, review) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, doctorId);
                stmt.setInt(3, rating);
                stmt.setString(4, reviewText);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("userprofile?id=" + doctorId);
    }
}
