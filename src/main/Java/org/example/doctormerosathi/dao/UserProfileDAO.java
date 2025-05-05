package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.UsersModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDAO {

    public static final String INSERT_USER = "INSERT INTO users(name,email,password,role, profile_picture) VALUES(?,?,?,?, ?)";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";


    // Get a user by email
    public static UsersModel getUserByEmail(String email) {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsersModel userFromDB = new UsersModel();
                userFromDB.setId(rs.getInt("id"));
                userFromDB.setFullName(rs.getString("name"));
                userFromDB.setEmail(rs.getString("email"));
                userFromDB.setPasswordHash(rs.getString("password"));
                userFromDB.setRole(rs.getString("role"));
                userFromDB.setProfilePicture(rs.getBytes("profile_picture"));
                return userFromDB;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by email: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    // Get a user by ID
    public static UsersModel getUserById(int id) {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID);) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UsersModel userFromDB = new UsersModel();
                userFromDB.setId(rs.getInt("id"));
                userFromDB.setFullName(rs.getString("name"));
                userFromDB.setEmail(rs.getString("email"));
                userFromDB.setPasswordHash(rs.getString("password"));
                userFromDB.setRole(rs.getString("role"));
                userFromDB.setProfilePicture(rs.getBytes("profile_picture"));
                return userFromDB;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    // Get all users
    public static List<UsersModel> getAllUsers() {
        List<UsersModel> users = new ArrayList<>();
        String sql = "SELECT user_id, full_name, email, role FROM users";

        try (Connection conn = DbConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UsersModel user = new UsersModel();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Error loading users: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return users;
    }
}

