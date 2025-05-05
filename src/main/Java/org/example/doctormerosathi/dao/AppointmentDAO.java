package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.model.Appointment;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT appointment_id, customer_id, doctor_id, appointment_datetime, " +
                "status, reason, rescheduled_from, cancelled_by, cancelled_at, " +
                "cancellation_reason, created_at FROM appointments";

        try (Connection conn = DbConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("appointment_id"));
                appointment.setCustomerId(rs.getInt("customer_id"));
                appointment.setDoctorId(rs.getInt("doctor_id"));
                appointment.setAppointmentDatetime(rs.getTimestamp("appointment_datetime"));
                appointment.setStatus(rs.getString("status"));
                appointment.setReason(rs.getString("reason"));
                appointment.setRescheduledFrom(rs.getInt("rescheduled_from"));
                if (rs.wasNull()) appointment.setRescheduledFrom(null);
                appointment.setCancelledBy(rs.getInt("cancelled_by"));
                appointment.setCancelledAt(rs.getTimestamp("cancelled_at"));
                appointment.setCancellationReason(rs.getString("cancellation_reason"));
                appointment.setCreatedAt(rs.getTimestamp("created_at"));

                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch appointments", e);
        }
        return appointments;
    }

    public void deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete appointment", e);
        }
    }

    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        List<Appointment> appointments = new ArrayList<>();

        String sql = "SELECT * FROM appointments WHERE doctor_id = ?";

        try (Connection conn = DbConnectionUtil.getConnection(); // Adjust to your DB connection utility
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Appointment appt = new Appointment();
                appt.setId(rs.getInt("appointment_id"));
                appt.setCustomerId(rs.getInt("customer_id"));
                appt.setDoctorId(rs.getInt("doctor_id"));
                appt.setAppointmentDatetime(rs.getTimestamp("appointment_datetime"));
                appt.setStatus(rs.getString("status"));
                appt.setReason(rs.getString("reason"));
                appt.setRescheduledFrom((Integer) rs.getObject("rescheduled_from"));
                appt.setCancelledBy((Integer) rs.getObject("cancelled_by"));
                appt.setCancelledAt(rs.getTimestamp("cancelled_at"));
                appt.setCancellationReason(rs.getString("cancellation_reason"));
                appt.setCreatedAt(rs.getTimestamp("created_at"));
                appointments.add(appt);
            }

        } catch (Exception e) {
        System.err.println("DB Error while fetching appointments: " + e.getMessage());
        e.printStackTrace();
    }


        return appointments;
    }
    public boolean updateAppointmentStatus(int appointmentId, String status,
                                           int userId, String cancellationReason) {
        String sql;

        if ("cancelled".equalsIgnoreCase(status)) {
            sql = "UPDATE appointments SET status = ?, " +
                    "cancelled_by = ?, cancelled_at = CURRENT_TIMESTAMP, " +
                    "cancellation_reason = ? " +
                    "WHERE appointment_id = ? AND (doctor_id = ? OR ? IN (SELECT user_id FROM users WHERE role = 'admin'))";
        } else if ("completed".equalsIgnoreCase(status)) {
            sql = "UPDATE appointments SET status = ? " +
                    "WHERE appointment_id = ? AND doctor_id = ?";
        } else {
            return false; // Invalid status
        }

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            stmt.setString(paramIndex++, status);

            if ("cancelled".equalsIgnoreCase(status)) {
                stmt.setInt(paramIndex++, userId);
                stmt.setString(paramIndex++,
                        cancellationReason != null ? cancellationReason : "No reason provided");
            }

            stmt.setInt(paramIndex++, appointmentId);
            stmt.setInt(paramIndex++, userId);

            // For cancellation, allow admin to cancel any appointment
            if ("cancelled".equalsIgnoreCase(status)) {
                stmt.setInt(paramIndex++, userId);
            }

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void bookAppointment(int customerId, int doctorId, Timestamp appointmentDateTime, String reason) {
        String sql = "INSERT INTO appointments (customer_id, doctor_id, appointment_datetime, status, reason) " +
                "VALUES (?, ?, ?, 'pending', ?)";

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            stmt.setInt(2, doctorId);
            stmt.setTimestamp(3, appointmentDateTime);
            stmt.setString(4, reason);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to book appointment", e);
        }
    }




}