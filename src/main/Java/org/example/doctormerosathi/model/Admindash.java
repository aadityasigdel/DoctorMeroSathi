package org.example.doctormerosathi.model;

import java.util.List;

public class Admindash {
    private int totalUsers;
    private int activeDoctors;
    private int todaysAppointments;
    private int pendingAppointments;
    private List<Appointment> recentAppointments;

    // Getters and Setters
    public int getTotalUsers() { return totalUsers; }
    public void setTotalUsers(int totalUsers) { this.totalUsers = totalUsers; }

    public int getActiveDoctors() { return activeDoctors; }
    public void setActiveDoctors(int activeDoctors) { this.activeDoctors = activeDoctors; }

    public int getTodaysAppointments() { return todaysAppointments; }
    public void setTodaysAppointments(int getTodaysAppointments) {
    }
    public List<Appointment> getRecentAppointments() { return recentAppointments; }
    public void setRecentAppointments(List<Appointment> recentAppointments) { this.recentAppointments = recentAppointments; }
}