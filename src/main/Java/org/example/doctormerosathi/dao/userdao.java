package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userdao {
    private static final String SELECT_ALL_USERS = "SELECT user_id, full_name, email, role FROM users";

    public List<UsersModel> getAllUsers() {
        List<UsersModel> users = new ArrayList<>();

        try (Connection conn = DbConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS)) {

            while (rs.next()) {
                UsersModel user = new UsersModel();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to load users", e);
        }

        return users;
    }
}