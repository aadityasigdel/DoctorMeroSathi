package org.example.doctormerosathi.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.doctormerosathi.dao.userdao;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.Util.PasswordHashUtil;

public class Authservice {

    public static int register(String fullName, String email, String password, String role, byte[] image) {
        UsersModel user = new UsersModel();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPasswordHash(password);  // Password will be hashed before storing
        user.setRole(role);  // No need to change 'role' if it's a String
        user.setProfilePicture(image);

        try {
            return userdao.registerUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static UsersModel login(String email, String password) {

        UsersModel user = userdao.getUserByEmail(email);


        if (user != null && PasswordHashUtil.verifyPassword(password, user.getPasswordHash())) {
            return user;
        }


        return null;
    }


    public static boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        UsersModel user = (UsersModel) session.getAttribute("user");
        return user != null;
    }

    public static boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        UsersModel user = (UsersModel) session.getAttribute("user");
        return user != null && "admin".equals(user.getRole());
    }

    public static void createUserSession(HttpServletRequest request, UsersModel user, int timeoutSeconds) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(timeoutSeconds);
    }

    public static UsersModel getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        return (UsersModel) session.getAttribute("user");
    }

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
