package org.example.doctormerosathi.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdmindashServlet", value = "/admin/dashboard")
public class AdmindashServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set statistics
        request.setAttribute("totalAppointments", 12);
        request.setAttribute("totalPatients", 8);
        request.setAttribute("availableSlots", 14);

        // Mock appointment data with Nepali names
        List<Appointment> recentAppointments = new ArrayList<>();
        recentAppointments.add(new Appointment("Ramesh Thapa", "2024-04-23", "10:00 AM", "Completed"));
        recentAppointments.add(new Appointment("Sunita Sharma", "2024-04-22", "02:00 PM", "Pending"));
        recentAppointments.add(new Appointment("Bijay Gurung", "2024-04-22", "09:00 AM", "Pending"));
        recentAppointments.add(new Appointment("Anjali Karki", "2024-04-21", "11:30 AM", "Cancelled"));
        recentAppointments.add(new Appointment("Dipesh Bhandari", "2024-04-20", "03:00 PM", "Cancelled"));

        // Add the appointment list to the request
        request.setAttribute("recentAppointments", recentAppointments);

        // Forward to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Admindash.jsp");
        dispatcher.forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // Simple inner class to represent appointment data
    public static class Appointment {
        private final String patientName;
        private final String date;
        private final String time;
        private final String status;

        public Appointment(String patientName, String date, String time, String status) {
            this.patientName = patientName;
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public String getPatientName() { return patientName; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public String getStatus() { return status; }
    }
}
