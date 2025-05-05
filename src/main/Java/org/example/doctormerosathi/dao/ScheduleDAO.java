package org.example.doctormerosathi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.doctormerosathi.Util.DbConnectionUtil;

public class ScheduleDAO {

    // Checks if a schedule already exists for the given doctor and day
    public boolean scheduleExists(int doctorId, String day) {
        String sql = "SELECT COUNT(*) FROM doctor_schedule WHERE doctor_id = ? AND day = ?";
        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            stmt.setString(2, day);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // True if count > 0 (schedule exists)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Insert schedule only if it doesn't exist
    public boolean insertSchedule(int doctorId, String day, String startTime, String endTime) {
        if (scheduleExists(doctorId, day)) {
            return false; // Schedule already exists for the given day
        }

        String sql = "INSERT INTO doctor_schedule (doctor_id, day, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, doctorId);
            stmt.setString(2, day);
            stmt.setString(3, startTime);
            stmt.setString(4, endTime);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
