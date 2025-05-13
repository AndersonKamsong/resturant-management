<%-- 
    Document   : manage-users
    Created on : May 13, 2025, 7:20:33 AM
    Author     : anderson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://xmlns.jcp.org/jsp/jstl/core" %>--%>

<html>
    <head>
        <title>Manage Users</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                margin: 20px;
                background-color: #1e1e1e; /* Light black background */
                color: #f4c542; /* Golden yellow text */
            }

            h1, h2 {
                text-align: center;
                color: #f4c542;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                background-color: #2a2a2a;
                color: #f4c542;
                box-shadow: 0 2px 8px rgba(255, 215, 0, 0.1);
            }

            th, td {
                border: 1px solid #f4c542;
                padding: 12px;
                text-align: left;
            }

            th {
                background-color: #333;
                color: #f4c542;
            }

            tr:nth-child(even) {
                background-color: #252525;
            }

            tr:hover {
                background-color: #3a3a3a;
            }

            .form-container {
                background-color: #2a2a2a;
                padding: 20px;
                border-radius: 8px;
                border: 1px solid #f4c542;
                max-width: 700px;
                margin: 0 auto 20px auto;
                box-shadow: 0 2px 8px rgba(255, 215, 0, 0.1);
            }

            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: inline-block;
                width: 120px;
                font-weight: bold;
                color: #f4c542;
            }

            input, select {
                width: calc(100% - 130px);
                padding: 8px;
                margin-left: 5px;
                background-color: #333;
                color: #f4c542;
                border: 1px solid #f4c542;
                border-radius: 4px;
            }

            button {
                background-color: #f4c542;
                color: #1e1e1e;
                border: none;
                padding: 10px 15px;
                border-radius: 4px;
                cursor: pointer;
            }

            button:hover {
                background-color: #ffd700;
            }

            .error {
                color: #ff4d4d;
                text-align: center;
                margin-bottom: 15px;
            }

            .success {
                color: #4dff88;
                text-align: center;
                margin-bottom: 15px;
            }

            a button {
                background-color: #888;
                color: #1e1e1e;
            }

            a button:hover {
                background-color: #aaa;
            }

            .actions form,
            .actions a {
                display: inline;
            }

            .actions button {
                margin-right: 5px;
            }

            .logout {
                text-align: center;
                margin-top: 30px;
            }
        </style>


    </head>
    <body>
        <h1>Manage Users</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <!-- Add/Edit User Form -->
    <div class="form-container">
        <h2>${empty editUser ? 'Add New User' : 'Edit User'}</h2>
        <form method="post" action="manage-users">
            <input type="hidden" name="action" value="${empty editUser ? 'create' : 'update'}">
            <c:if test="${not empty editUser}">
                <input type="hidden" name="userId" value="${editUser.userId}">
            </c:if>

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${editUser.username}" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${editUser.password}" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role">
                    <option value="admin" ${editUser.role == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="manager" ${editUser.role == 'manager' ? 'selected' : ''}>Manager</option>
                    <option value="staff" ${editUser.role == 'staff' ? 'selected' : ''}>Staff</option>
                    <option value="client" ${editUser.role == 'client' ? 'selected' : ''}>Client</option>
                </select>
            </div>
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="${editUser.fullName}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${editUser.email}" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" id="phone" name="phone" value="${editUser.phone}">
            </div>
            <button type="submit">${empty editUser ? 'Create' : 'Update'}</button>
            <c:if test="${not empty editUser}">
                <a href="manage-users"><button type="button">Cancel</button></a>
            </c:if>
        </form>
    </div>

    <!-- Users Table -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.role}</td>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td class="actions">
                    <form method="post" action="manage-users">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <button type="submit">Delete</button>
                    </form>
                    <a href="manage-users?action=edit&userId=${user.userId}">
                        <button>Edit</button>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div class="logout">
    <p><a href="logout"><button>Logout</button></a></p>
</div>
</body>
</html>
