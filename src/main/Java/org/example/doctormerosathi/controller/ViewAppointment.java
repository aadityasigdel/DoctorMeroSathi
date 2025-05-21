package org.example.doctormerosathi.controller;

import org.example.doctormerosathi.dao.AppointmentDAO;
import org.example.doctormerosathi.model.Appointment;
import org.example.doctormerosathi.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewAppointments")
public class ViewAppointment extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        appointmentDAO = new AppointmentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsersModel currentUser = (UsersModel) request.getSession().getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int customerId = currentUser.getId();

        try {
            List<Appointment> appointments = appointmentDAO.getAppointmentsByCustomerId(customerId);
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("/WEB-INF/view/viewAppointmnet.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
