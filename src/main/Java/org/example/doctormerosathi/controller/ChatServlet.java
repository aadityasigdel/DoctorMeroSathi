package org.example.doctormerosathi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.doctormerosathi.Util.DbConnectionUtil;
import org.example.doctormerosathi.model.Message;
import org.example.doctormerosathi.model.UsersModel;
import org.example.doctormerosathi.services.Authservice;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel currentUser = Authservice.getCurrentUser(request);
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int currentUserId = currentUser.getId();
        List<UsersModel> contactList = new ArrayList<>();
        List<Message> messageList = new ArrayList<>();
        UsersModel selectedUser = null;

        try (Connection conn = DbConnectionUtil.getConnection()) {

            // Load all users for messaging
            String contactQuery = "SELECT u.user_id, u.full_name, " +
                    "(SELECT m.message FROM messages m " +
                    "WHERE (m.sender_id = u.user_id OR m.receiver_id = u.user_id) " +
                    "AND (m.sender_id = ? OR m.receiver_id = ?) " +
                    "ORDER BY m.created_at DESC LIMIT 1) as last_message " +
                    "FROM users u WHERE u.user_id != ?";

            try (PreparedStatement stmt = conn.prepareStatement(contactQuery)) {
                stmt.setInt(1, currentUserId);
                stmt.setInt(2, currentUserId);
                stmt.setInt(3, currentUserId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    UsersModel contact = new UsersModel();
                    contact.setId(rs.getInt("user_id"));
                    contact.setFullName(rs.getString("full_name"));
                    contactList.add(contact);
                }
            }

            // to load chat messages
            String receiverParam = request.getParameter("receiver_id");

            if (receiverParam != null && !receiverParam.trim().isEmpty()) {
                try {
                    int receiverId = Integer.parseInt(receiverParam);

                    // to Load doctor info
                    String userSql = "SELECT user_id, full_name FROM users WHERE user_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(userSql)) {
                        stmt.setInt(1, receiverId);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            selectedUser = new UsersModel();
                            selectedUser.setId(rs.getInt("user_id"));
                            selectedUser.setFullName(rs.getString("full_name"));
                        }
                    }

                    // to Load messages
                    String messageSql = "SELECT sender_id, receiver_id, message, created_at FROM messages " +
                            "WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) " +
                            "ORDER BY created_at DESC";
                    try (PreparedStatement stmt = conn.prepareStatement(messageSql)) {
                        stmt.setInt(1, currentUserId);
                        stmt.setInt(2, Integer.parseInt(receiverParam));
                        stmt.setInt(3, Integer.parseInt(receiverParam));
                        stmt.setInt(4, currentUserId);
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            Message message = new Message();
                            message.setSenderId(rs.getInt("sender_id"));
                            message.setReceiverId(rs.getInt("receiver_id"));
                            message.setMessage(rs.getString("message"));
                            message.setCreatedAt(rs.getTimestamp("created_at"));
                            messageList.add(message);
                        }
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid receiver ID");
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            return;
        }

        request.setAttribute("contacts", contactList);
        request.setAttribute("messages", messageList);
        request.setAttribute("selectedUser", selectedUser);
        request.setAttribute("user", currentUser);

        request.getRequestDispatcher("/WEB-INF/view/Chat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!Authservice.isAuthenticated(request)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        UsersModel currentUser = Authservice.getCurrentUser(request);
        if (currentUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int senderId = currentUser.getId();
        String receiverParam = request.getParameter("receiver_id");
        String messageText = request.getParameter("message");

        if (receiverParam == null || receiverParam.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Receiver ID is required");
            return;
        }

        int receiverId;
        try {
            receiverId = Integer.parseInt(receiverParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid receiver ID");
            return;
        }

        if (messageText != null && !messageText.trim().isEmpty()) {
            try (Connection conn = DbConnectionUtil.getConnection()) {
                String insertSql = "INSERT INTO messages (sender_id, receiver_id, message) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                    stmt.setInt(1, senderId);
                    stmt.setInt(2, receiverId);
                    stmt.setString(3, messageText.trim());
                    stmt.executeUpdate();
                    System.out.println("Message sent successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error sending message");
                return;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Message cannot be empty");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/chat?receiver_id=" + receiverId);
    }
}
