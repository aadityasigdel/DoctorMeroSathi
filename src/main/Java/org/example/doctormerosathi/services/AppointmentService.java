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

    public void deleteAppointment(int id) {
        appointmentDao.deleteAppointment(id);
    }
}