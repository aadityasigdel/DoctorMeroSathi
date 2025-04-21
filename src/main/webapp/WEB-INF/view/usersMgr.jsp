<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management - Doctor Mero Sathi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/adminDash.css">
</head>
<body>
<jsp:include page="AdminNav.jsp" />

<div class="dashboard-container">
    <div class="header">
        <h1>Doctor Mero Sathi</h1>
        <h2>User Management</h2>
    </div>

    <div class="filter-section">
        <form method="get" action="${pageContext.request.contextPath}/admin/users">
            <label>Filter by Role:</label>
            <select name="role">
                <option value="all">All</option>
                <option value="doctor">Doctor</option>
                <option value="patient">Patient</option>
                <option value="admin">Admin</option>
            </select>

            <label>Search:</label>
            <input type="text" name="search" placeholder="Search users...">

            <button type="submit" class="filter-btn">Apply</button>
            <a href="${pageContext.request.contextPath}/admin/users/add" class="add-btn">
                <i class="fas fa-plus"></i> Add User
            </a>
        </form>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>User ID</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <td>1</td>
                    <td>Aaditya Sigdel</td>
                    <td>aaditya.sigdel@example.com</td>
                    <td>Admin</td>
                    <td>98XXXXXXXX</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Dr. Smriti Sharma</td>
                    <td>smriti.sharma@healthcare.com</td>
                    <td>Doctor</td>
                    <td>98YYYYYYYY</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Ramesh Karki</td>
                    <td>ramesh.karki@email.net</td>
                    <td>Patient</td>
                    <td>98ZZZZZZZZ</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Priya Thapa</td>
                    <td>priya.thapa@domain.org</td>
                    <td>Nurse</td>
                    <td>98AAAAAAA</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Bikash Gurung</td>
                    <td>bikash.gurung@provider.co</td>
                    <td>Admin</td>
                    <td>98BBBBBBBB</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Dr. Anjali Rai</td>
                    <td>anjali.rai@hospital.info</td>
                    <td>Doctor</td>
                    <td>98CCCCCCCC</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Sita Magar</td>
                    <td>sita.magar@service.com</td>
                    <td>Patient</td>
                    <td>98DDDDDDDD</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>Kiran Limbu</td>
                    <td>kiran.limbu@network.net</td>
                    <td>Receptionist</td>
                    <td>98EEEEEEEE</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>Dr. Mahesh Bhattarai</td>
                    <td>mahesh.b@clinic.org</td>
                    <td>Doctor</td>
                    <td>98FFFFFFFF</td>
                </tr>

            </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/admin/users?page=${currentPage-1}&role=${param.role}&search=${param.search}">
                    &laquo; Previous
                </a>
            </c:if>

            <span>Page ${currentPage} of ${totalPages}</span>

            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/admin/users?page=${currentPage+1}&role=${param.role}&search=${param.search}">
                    Next &raquo;
                </a>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>