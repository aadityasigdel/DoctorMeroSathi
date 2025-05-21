<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>My Appointments</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/DocApp.css">
</head>
<body>

<%@ include file="/WEB-INF/view/navbar.jsp" %>

<!-- Success/Error Messages -->
<c:if test="${not empty successMessage}">
    <div class="alert alert-success">
            ${successMessage}
    </div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-error">
            ${errorMessage}
    </div>
</c:if>

<h2>Appointments for You</h2>

<div class="user-management-container">
    <c:if test="${empty appointments}">
        <p style="text-align:center;">No appointments found.</p>
    </c:if>

    <c:if test="${not empty appointments}">
        <table>
            <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Date & Time</th>
                <th>Status</th>
                <th>Reason</th>
                <th>Created At</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="appt" items="${appointments}">
                <tr>
                    <td>${appt.id}</td>
                    <td>${appt.customerId}</td>
                    <td>
                        <fmt:formatDate value="${appt.appointmentDatetime}" pattern="MMM dd, yyyy hh:mm a" />
                    </td>
                    <td class="status-${appt.status}">${appt.status}</td>
                    <td>${appt.reason}</td>
                    <td>
                        <fmt:formatDate value="${appt.createdAt}" pattern="MMM dd, yyyy" />
                    </td>
                    <td class="action-buttons">
                        <!-- Only show actions for pending/confirmed appointments -->
                        <c:if test="${appt.status == 'pending' or appt.status == 'confirmed'}">
                            <!-- Complete Appointment Form -->
                            <form action="${pageContext.request.contextPath}/appointmentsDoc" method="post"
                                  onsubmit="return confirm('Mark this appointment as completed?')">
                                <input type="hidden" name="action" value="complete" />
                                <input type="hidden" name="appointmentId" value="${appt.id}" />
                                <button type="submit" class="btn-complete">Complete</button>
                            </form>

                            <!-- Cancel Appointment Form with Reason -->
                            <form action="${pageContext.request.contextPath}/appointmentsDoc" method="post"
                                  onsubmit="return confirmCancel()">
                                <input type="hidden" name="action" value="cancel" />
                                <input type="hidden" name="appointmentId" value="${appt.id}" />
                                <input type="hidden" id="reason-${appt.id}" name="cancellationReason" value="Doctor cancelled" />
                                <button type="submit" class="btn-cancel">Cancel</button>
                            </form>
                        </c:if>
                        <c:if test="${appt.status == 'completed' or appt.status == 'cancelled'}">
                            <span>No actions available</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

<script>
    function confirmCancel() {
        const reason = prompt("Please enter cancellation reason (optional):");
        if (reason === null) return false;

        document.querySelector('input[name="cancellationReason"]').value =
            reason || "Doctor cancelled";
        return confirm("Are you sure you want to cancel this appointment?");
    }
</script>

</body>
</html>
