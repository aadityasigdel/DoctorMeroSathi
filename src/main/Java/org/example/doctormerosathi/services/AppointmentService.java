package org.example.doctormerosathi.services;

import org.example.doctormerosathi.dao.AppointmentDAO;
import org.example.doctormerosathi.model.Appointment;

import java.util.List;

public class AppointmentService {
    private final AppointmentDAO appointmentDao;

    public AppointmentService() {
        this.appointmentDao = new AppointmentDAO();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    public boolean deleteAppointment(int appointmentId) {
        try {
            appointmentDao.deleteAppointment(appointmentId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
        return appointmentDao.getAppointmentsByDoctorId(doctorId);
    }

    public boolean completeAppointment(int appointmentId, int doctorId) {
        return updateAppointmentStatus(
                appointmentId,
                "completed",
                doctorId,
                null
        );
    }

    public boolean cancelAppointment(int appointmentId, int doctorId, String cancellationReason) {
        return updateAppointmentStatus(
                appointmentId,
                "cancelled",
                doctorId,
                cancellationReason != null ? cancellationReason : "Cancelled by doctor"
        );
    }

    public boolean updateAppointmentStatus(int appointmentId, String status,
                                           int doctorId, String cancellationReason) {
        // Validate input parameters
        if (appointmentId <= 0 || doctorId <= 0 || status == null) {
            return false;
        }

        // Only allow valid status transitions
        if (!isValidStatus(status)) {
            return false;
        }

        return appointmentDao.updateAppointmentStatus(
                appointmentId,
                status.toLowerCase(),
                doctorId,
                cancellationReason
        );
    }

    private boolean isValidStatus(String status) {
        return "pending".equalsIgnoreCase(status) ||
                "confirmed".equalsIgnoreCase(status) ||
                "completed".equalsIgnoreCase(status) ||
                "cancelled".equalsIgnoreCase(status);
    }

    // Additional business logic methods can be added here
    public boolean canCancelAppointment(Appointment appointment) {
        return appointment != null &&
                ("pending".equalsIgnoreCase(appointment.getStatus()) ||
                        "confirmed".equalsIgnoreCase(appointment.getStatus()));
    }

    public boolean canCompleteAppointment(Appointment appointment) {
        return appointment != null &&
                "confirmed".equalsIgnoreCase(appointment.getStatus());
    }
}