<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Appointments - Doctor Mero Sathi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/adminDash.css">
</head>
<body>
<jsp:include page="AdminNav.jsp" />

<div class="dashboard-container">
    <div class="header">
        <h1>Doctor Mero Sathi</h1>
        <h2>Appointments Management</h2>
    </div>

    <div class="filter-section">
        <form method="get" action="${pageContext.request.contextPath}/admin/appointments">
            <label>Filter by Status:</label>
            <select name="status">
                <option value="all">All</option>
                <option value="pending">Pending</option>
                <option value="confirmed">Confirmed</option>
                <option value="completed">Completed</option>
                <option value="cancelled">Cancelled</option>
            </select>

            <label>Date Range:</label>
            <input type="date" name="fromDate">
            <span>to</span>
            <input type="date" name="toDate">

            <button type="submit" class="filter-btn">Apply</button>
        </form>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Appt ID</th>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Date</th>
                <th>Time</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <td>APT001</td>
            <td>Ramesh Karki</td>
            <td>Dr. Smriti Sharma</td>
            <td>2025-04-25</td>
            <td>10:00 AM</td>
            <td>Scheduled</td>
            <td class="actions">
                <a href="#" class="edit">Edit</a>
                <a href="#" class="cancel">Cancel</a>
            </td>
            </tr>
            <tr>
                <td>APT002</td>
                <td>Sita Magar</td>
                <td>Dr. Anjali Rai</td>
                <td>2025-04-26</td>
                <td>11:30 AM</td>
                <td>Confirmed</td>
                <td class="actions">
                    <a href="#" class="edit">Edit</a>
                    <a href="#" class="cancel">Cancel</a>
                </td>
            </tr>
            <tr>
                <td>APT003</td>
                <td>John Doe</td>
                <td>Dr. Mahesh Bhattarai</td>
                <td>2025-04-27</td>
                <td>09:00 AM</td>
                <td>Scheduled</td>
                <td class="actions">
                    <a href="#" class="edit">Edit</a>
                    <a href="#" class="cancel">Cancel</a>
                </td>
            </tr>
            <tr>
                <td>APT004</td>
                <td>Priya Thapa</td>
                <td>Dr. Smriti Sharma</td>
                <td>2025-04-27</td>
                <td>02:00 PM</td>
                <td>Completed</td>
                <td class="actions">
                    <a href="#" class="edit">View</a>
                    <span>-</span>
                </td>
            </tr>
            <tr>
                <td>APT005</td>
                <td>Bikash Gurung</td>
                <td>Dr. Anjali Rai</td>
                <td>2025-04-28</td>
                <td>03:45 PM</td>
                <td>Scheduled</td>
                <td class="actions">
                    <a href="#" class="edit">Edit</a>
                    <a href="#" class="cancel">Cancel</a>
                </td>
            </tr>
            <tr>
                <td>APT006</td>
                <td>Sunita Yadav</td>
                <td>Dr. Mahesh Bhattarai</td>
                <td>2025-04-29</td>
                <td>10:30 AM</td>
                <td>Confirmed</td>
                <td class="actions">
                    <a href="#" class="edit">Edit</a>
                    <a href="#" class="cancel">Cancel</a>
                </td>
            </tr>
            <tr>
                <td>APT007</td>
                <td>Ramesh Karki</td>
                <td>Dr. Anjali Rai</td>
                <td>2025-04-29</td>
                <td>01:00 PM</td>
                <td>Scheduled</td>
                <td class="actions">
                    <a href="#" class="edit">Edit</a>
                    <a href="#" class="cancel">Cancel</a>
                </td>
            </tr>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/admin/appointments?page=${currentPage-1}&status=${param.status}&fromDate=${param.fromDate}&toDate=${param.toDate}">
                    &laquo; Previous
                </a>
            </c:if>

            <span>Page ${currentPage} of ${totalPages}</span>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/admin/appointments?page=${currentPage+1}&status=${param.status}&fromDate=${param.fromDate}&toDate=${param.toDate}">
                    Next &raquo;
                </a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>