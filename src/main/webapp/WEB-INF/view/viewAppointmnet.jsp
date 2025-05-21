<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.doctormerosathi.model.Appointment" %>
<%@ page import="org.example.doctormerosathi.model.UsersModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Appointments</title>
    <style>

        body {
            font-family: 'Roboto', sans-serif;
            background: #F0F4F8;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .full-container {
            width: 80%;
            max-width: 1200px;
            height: auto;
            background: #FFFFFF;
            border-radius: 20px;
            padding: 30px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1); /* Add depth to make it feel elevated */
            margin-top: 50px;
            transition: all 0.3s ease;
            background: linear-gradient(135deg, #F0F4F8, #ffffff); /* Smooth gradient background */
            display: flex;
            flex-direction: column;
            align-items: center;
        }



        h3 {
            font-size: 2.4rem;
            color: #2A7F9D;
            font-weight: 700;
            margin-bottom: 20px;
            text-align: center;
            background: linear-gradient(135deg, #2A7F9D, #3B9E9F);
            -webkit-background-clip: text;
        }

        .appointments-container{
            width: 80%;
            margin-top: 50px;

        }

        .appointment-card {
            background: #FFFFFF;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
            transition: all 0.3s ease;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transform: translateY(0);
        }


        .appointment-info {
            margin-bottom: 20px;
        }

        .doctor-name {
            font-size: 1.5rem;
            font-weight: 600;
            color: #2A7F9D;
            margin-bottom: 5px;
            transition: color 0.3s ease;
        }

        .appointment-date,
        .appointment-status{
            font-size: 1rem;
            color: #666;
            margin-bottom: 8px;
        }

        .appointment-status {
            font-weight: bold;
            color: #4CAF50;
        }



        .no-appointments {
            font-size: 1.5rem;
            color: #888;
            text-align: center;
            margin-top: 50px;
            font-weight: 500;
        }



        @media (max-width: 768px) {
            .appointments-container {
                padding: 15px;
            }

            .appointment-card {
                padding: 15px;
            }

            h2 {
                font-size: 1.5rem;
            }

            .doctor-name {
                font-size: 1.2rem;
            }

            .appointment-status,
            .appointment-reason {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<div class = "full-container">
<h2>Your Appointments List</h2>
<div class="appointments-container">
    <c:if test="${not empty appointments}">
        <c:forEach var="appointment" items="${appointments}">
            <div class="appointment-card">
                <div class="appointment-info">
                    <div class="doctor-name">Dr. <c:out value="${appointment.doctorName}"/></div>
                    <div class="appointment-date">Date and Time: <c:out value="${appointment.appointmentDatetime}"/></div>
                    <div class="appointment-status">Status:
                        <c:choose>
                            <c:when test="${appointment.status == 'cancelled'}">
                                <span class="cancelled-status">Cancelled</span>
                            </c:when>
                            <c:otherwise>
                                <span class="appointment-status">Confirmed</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${empty appointments}">
        <p class="no-appointments">You have no appointments scheduled.</p>
    </c:if>
</div>
</div>

</body>
</html>