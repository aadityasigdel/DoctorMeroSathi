package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.Appointment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDashDAO implements AutoCloseable {
    private Connection connection;

    public AdminDashDAO() throws SQLException {
        this.connection = DbConnectionUtil.getConnection();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Get total users count (all roles)
    public int getTotalUserCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM users";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // Get active doctors count (using role='doctor')
    public int getActiveDoctorCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE role = 'doctor'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // Get appointments count for specific date
    public int getAppointmentCountForDate(LocalDate date) throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointments WHERE appointment_date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    // Get pending appointments count
    public int getPendingAppointmentsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = 'PENDING'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // Get recent appointments with basic information
    public List<Appointment> getRecentAppointments(int limit) throws SQLException {
        String sql = """
            SELECT 
                a.appointment_id,
                a.appointment_date,
                a.appointment_time,
                a.status,
                a.patient_id,
                a.doctor_id
            FROM appointments a
            ORDER BY a.appointment_date DESC, a.appointment_time DESC 
            LIMIT ?""";

        List<Appointment> appointments = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    appointments.add(mapToAppointment(rs));
                }
            }
        }
        return appointments;
    }

    private Appointment mapToAppointment(ResultSet rs) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setId(rs.getInt("appointment_id"));
        appointment.setAppointmentDatetime(rs.getTimestamp("appointmentDatetime"));

        Time time = rs.getTime("appointment_time");

        appointment.setStatus(rs.getString("status"));
        appointment.setCustomerId(rs.getInt("customer_id"));
        appointment.setDoctorId(rs.getInt("doctor_id"));

        return appointment;
    }
}