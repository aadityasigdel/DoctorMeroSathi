<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.sql.*, java.util.Base64" %>
<%@ page import="org.example.doctormerosathi.model.UsersModel" %>
<%@ page import="org.example.doctormerosathi.model.Message" %>
<%@ page session="true" %>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    List<UsersModel> contacts = (List<UsersModel>) request.getAttribute("contacts");
    List<Message> messages = (List<Message>) request.getAttribute("messages");
    UsersModel selectedUser = (UsersModel) request.getAttribute("selectedUser");
    UsersModel currentUser = (UsersModel) request.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat System</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/Chat.css">
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<div class="chat-container">


    <!-- Contacts Sidebar -->
    <div class="contacts-sidebar">
        <div class="chat-header">Chats</div>
        <% if (contacts != null) {
            for (UsersModel contact : contacts) {
                byte[] pic = contact.getProfilePicture();
                String encodedImage = null;
                if (pic != null) {
                    encodedImage = Base64.getEncoder().encodeToString(pic);
                }
        %>
        <form method="get" action="<%= request.getContextPath() %>/chat">
            <input type="hidden" name="receiver_id" value="<%= contact.getId() %>">
            <button type="submit" class="contact">
                <div class="contact-avatar">
                    <img src="<%= (encodedImage != null)
                                ? "data:image/jpeg;base64," + encodedImage
                                : (request.getContextPath() + "/assets/avatar.jpg") %>"
                    />
                </div>
                <div class="contact-info">
                    <div class="contact-name"><%= contact.getFullName() %></div>

                </div>
            </button>
        </form>
        <% } } %>
    </div>

    <!-- Chat Area -->
    <div class="chat-area">
        <% if (selectedUser != null) { %>
        <div class="chat-header">Chat with <%= selectedUser.getFullName() %></div>
        <div class="messages-container" id="messagesContainer">
            <% if (messages != null) {
                for (Message message : messages) {
                    boolean isSent = message.getSenderId() == currentUser.getId();
            %>
            <div class="message <%= isSent ? "sent" : "received" %>">
                <div class="message-content"><%= message.getMessage() %></div>
                <div class="message-time"><%= message.getCreatedAt() %></div>
            </div>
            <% }} %>
        </div>

        <!-- Message Input -->
        <div class="message-input">
            <form method="post" id="messageForm">
                <input type="hidden" name="receiver_id" value="<%= selectedUser.getId() %>">
                <textarea name="message" id="messageInput" placeholder="Type a message..." required></textarea>
                <button type="submit">Send</button>
            </form>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
