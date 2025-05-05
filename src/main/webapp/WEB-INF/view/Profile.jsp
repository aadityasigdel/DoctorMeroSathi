<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 7:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.doctormerosathi.model.UsersModel" %>
<%@ page import="java.util.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsersModel user = (UsersModel) request.getAttribute("user");
    String base64Image = "";
    if (user != null && user.getProfilePicture() != null) {
        base64Image = Base64.getEncoder().encodeToString(user.getProfilePicture());
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile - Doctor Mero Saathi</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/Profile.css">
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>

<   <script type="text/javascript">
    <%
        String message = (String) session.getAttribute("message");
        String error = (String) session.getAttribute("error");
        if (message != null) {
    %>
    alert('<%= message %>');
    <%
            session.removeAttribute("message");
        }
        if (error != null) {
    %>
    alert('<%= error %>');
    <%
            session.removeAttribute("error");
        }
    %>
</script>

<div class="profile-container">
    <div class="profile-header">
        <h2>My Profile</h2>
    </div>

    <div class="profile-content">
        <div class="profile-picture-section">
            <% if (!base64Image.isEmpty()) { %>
            <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Profile Picture" class="profile-picture" />
            <% } else { %>
            <div class="no-picture">
                <i class="fas fa-user"></i>
            </div>
            <% } %>
        </div>

        <div class="profile-details">
            <div class="detail-card">
                <h3><i class="fas fa-user"></i> Personal Information</h3>
                <div class="detail-row">
                    <div class="detail-label">Full Name:</div>
                    <div class="detail-value"><%= user.getFullName() %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Email:</div>
                    <div class="detail-value"><%= user.getEmail() %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Role:</div>
                    <div class="detail-value"><%= user.getRole() %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Phone:</div>
                    <div class="detail-value"><%= user.getPhoneNumber() != null ? user.getPhoneNumber() : "N/A" %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Gender:</div>
                    <div class="detail-value"><%= user.getGender() != null ? user.getGender() : "N/A" %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Date of Birth:</div>
                    <div class="detail-value"><%= user.getDob() != null ? user.getDob() : "N/A" %></div>
                </div>
            </div>

            <div class="detail-card">
                <h3><i class="fas fa-map-marker-alt"></i> Address</h3>
                <div class="detail-row">
                    <div class="detail-value"><%= user.getAddress() != null ? user.getAddress() : "No address provided" %></div>
                </div>
            </div>

            <% if ("doctor".equalsIgnoreCase(user.getRole())) { %>
            <!-- Professional Information Section for Doctors -->
            <div class="detail-card">
                <h3><i class="fas fa-stethoscope"></i> Professional Information</h3>
                <div class="detail-row">
                    <div class="detail-label">Specialization:</div>
                    <div class="detail-value"><%= user.getSpecialization() != null ? user.getSpecialization() : "N/A" %></div>
                </div>
                <div class="detail-row">
                    <div class="detail-label">Experience:</div>
                    <div class="detail-value"><%= user.getExperienceYears() != null ? user.getExperienceYears() + " years" : "N/A" %></div>
                </div>
            </div>

            <!-- Schedule Update Form for Doctors -->
            <details class="schedule-details">
                <summary class="toggle-schedule-btn">
                    <i class="fas fa-calendar-plus"></i> Add Schedule
                </summary>

                <!-- Schedule Update Form for Doctors -->
                <div class="detail-card">
                    <h3><i class="fas fa-calendar-day"></i> Update Schedule</h3>
                    <form action="<%= request.getContextPath() %>/updateSchedule" method="post">
                        <div class="schedule-input">
                            <label for="days">Select Days:</label>
                            <select name="days" id="days" multiple size="7">
                                <option value="Monday">Monday</option>
                                <option value="Tuesday">Tuesday</option>
                                <option value="Wednesday">Wednesday</option>
                                <option value="Thursday">Thursday</option>
                                <option value="Friday">Friday</option>
                                <option value="Saturday">Saturday</option>
                                <option value="Sunday">Sunday</option>
                            </select>
                        </div>
                        <div class="schedule-input">
                            <label for="startTime">Start Time:</label>
                            <input type="time" id="startTime" name="startTime" required>
                        </div>
                        <div class="schedule-input">
                            <label for="endTime">End Time:</label>
                            <input type="time" id="endTime" name="endTime" required>
                        </div>
                        <button type="submit" class="update-schedule-btn">
                            <i class="fas fa-check"></i> Update Schedule
                        </button>
                    </form>
                </div>
            </details>

            <% } %>

            <div class="detail-card">
                <h3><i class="fas fa-calendar-alt"></i> Account Information</h3>
                <div class="detail-row">
                    <div class="detail-label">Account Created:</div>
                    <div class="detail-value"><%= user.getCreatedAt() %></div>
                </div>
            </div>

            <form action="<%= request.getContextPath() %>/profile" method="get">
                <input type="hidden" name="edit" value="true" />
                <button type="submit" class="edit-btn">
                    <i class="fas fa-edit"></i> Edit Profile
                </button>
            </form>

        </div>
    </div>
</div>
</body>
</html>