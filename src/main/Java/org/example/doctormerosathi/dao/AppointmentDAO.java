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
}