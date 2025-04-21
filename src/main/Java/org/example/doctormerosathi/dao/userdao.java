package org.example.doctormerosathi.dao;

import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.Util.DbConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userdao {

    public static final String INSERT_USER = "INSERT INTO users(name,email,password,role, profile_picture) VALUES(?,?,?,?, ?)";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public static int registerUser(UsersModel user) {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);) {
            // Set parameters for the prepared statement
            ps.setString(1, user.getFullName()); // Assuming you have 'fullName' in UsersModel
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPasswordHash()); // Use the hashed password field
            ps.setString(4, user.getRole());
            ps.setBytes(5, user.getProfilePicture()); // Assuming you have 'profilePicture' in UsersModel

            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static UsersModel getUserByEmail(String email) {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            // Set the email parameter
            ps.setString(1, email);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // If the user is found, create and return a UsersModel object
            if (rs.next()) {
                UsersModel userFromDB = new UsersModel();
                userFromDB.setId(rs.getInt("id"));
                userFromDB.setFullName(rs.getString("name"));
                userFromDB.setEmail(rs.getString("email"));
                userFromDB.setPasswordHash(rs.getString("password")); // Assuming you store password hash in DB
                userFromDB.setRole(rs.getString("role"));
                userFromDB.setProfilePicture(rs.getBytes("profile_picture"));
                return userFromDB;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user by email: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null; // Return null if user not found
    }

    public static UsersModel getUserById(int id) {
        try (Connection connection = DbConnectionUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");) {
            // Set the user ID parameter
            ps.setInt(1, id);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // If the user is found, create and return a UsersModel object
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
}
