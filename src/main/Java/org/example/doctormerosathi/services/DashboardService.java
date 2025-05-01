package org.example.doctormerosathi.services;

import org.example.doctormerosathi.dao.AdminDashDAO;
import org.example.doctormerosathi.model.Admindash;
import java.sql.SQLException;
import java.time.LocalDate;

public class DashboardService {
    public Admindash getDashboardData() {
        try (AdminDashDAO adminDashDAO = new AdminDashDAO()) {
            Admindash dashboard = new Admindash();

            try {
                dashboard.setTotalUsers(adminDashDAO.getTotalUserCount());
            } catch (SQLException e) {
                System.err.println("Error loading total users: " + e.getMessage());
                dashboard.setTotalUsers(-1);
            }

            try {
                dashboard.setActiveDoctors(adminDashDAO.getActiveDoctorCount());
            } catch (SQLException e) {
                System.err.println("Error loading active doctors: " + e.getMessage());
                dashboard.setActiveDoctors(-1);
            }

            try {
                dashboard.setTodaysAppointments(adminDashDAO.getAppointmentCountForDate(LocalDate.now()));
            } catch (SQLException e) {
                System.err.println("Error loading today's appointments: " + e.getMessage());
                dashboard.setTodaysAppointments(-1);
            }

            try {
                dashboard.setPendingAppointments(adminDashDAO.getPendingAppointmentsCount());
            } catch (SQLException e) {
                System.err.println("Error loading pending appointments: " + e.getMessage());
                dashboard.setPendingAppointments(-1);
            }

            try {
                dashboard.setRecentAppointments(adminDashDAO.getRecentAppointments(5));
            } catch (SQLException e) {
                System.err.println("Error loading recent appointments: " + e.getMessage());
                // Leave recent appointments as null or empty list
            }

            return dashboard;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }
}