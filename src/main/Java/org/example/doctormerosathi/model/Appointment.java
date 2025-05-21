package org.example.doctormerosathi.model;

public class Appointment {
    private int id;
    private int customerId;
    private int doctorId;
    private java.sql.Timestamp appointmentDatetime;
    private String status;
    private String reason;
    private Integer rescheduledFrom;
    private Integer cancelledBy;
    private java.sql.Timestamp cancelledAt;
    private String cancellationReason;
    private java.sql.Timestamp createdAt;
    private String doctorName;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public java.sql.Timestamp getAppointmentDatetime() {
        return appointmentDatetime;
    }

    public void setAppointmentDatetime(java.sql.Timestamp appointmentDatetime) {
        this.appointmentDatetime = appointmentDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getRescheduledFrom() {
        return rescheduledFrom;
    }

    public void setRescheduledFrom(Integer rescheduledFrom) {
        this.rescheduledFrom = rescheduledFrom;
    }

    public Integer getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(Integer cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public java.sql.Timestamp getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(java.sql.Timestamp cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
