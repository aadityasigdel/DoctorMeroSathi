package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


// To book appointment with doctor according to doctor id
@WebServlet("/BookApp")
public class BookAppServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BookAppServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String doctorId = request.getParameter("doctorId");

        if (doctorId == null || doctorId.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing doctorId parameter in request");
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=missing_doctor_id");
            return;
        }

        try {

            int doctorIdInt = Integer.parseInt(doctorId);
            request.setAttribute("doctorId", doctorId);
            request.getRequestDispatcher("/WEB-INF/view/BookApp.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid doctorId format: " + doctorId);
            response.sendRedirect(request.getContextPath() + "/appointmentsCli?error=invalid_doctor_id");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing booking request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "An error occurred while processing your request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}