package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.Appointment;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // Get appointment count for a specific date
    public int getAppointmentCountForDate(LocalDate date) throws SQLException {
        // Convert LocalDate to LocalDateTime at the start of the day (00:00:00)
        LocalDateTime startOfDay = date.atStartOfDay();
        // Convert LocalDateTime to Timestamp
        Timestamp startOfDayTimestamp = Timestamp.valueOf(startOfDay);

        // Convert LocalDate to LocalDateTime at the end of the day (23:59:59)
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        // Convert to Timestamp
        Timestamp endOfDayTimestamp = Timestamp.valueOf(endOfDay);

        // SQL query to count appointments within the full date range
        String sql = "SELECT COUNT(*) FROM appointments WHERE appointment_datetime BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Set the start and end timestamps for the specific date range
            stmt.setTimestamp(1, startOfDayTimestamp);
            stmt.setTimestamp(2, endOfDayTimestamp);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    // Get pending appointments count
    public int getPendingAppointmentsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointments WHERE status = 'pending'";
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
                a.appointment_datetime,
                a.status,
                a.customer_id,
                a.doctor_id
            FROM appointments a
            ORDER BY a.appointment_datetime DESC
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
        appointment.setAppointmentDatetime(rs.getTimestamp("appointment_datetime"));
        appointment.setStatus(rs.getString("status"));
        appointment.setCustomerId(rs.getInt("customer_id"));
        appointment.setDoctorId(rs.getInt("doctor_id"));
        return appointment;
    }
}
