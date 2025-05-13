<%-- 
    Document   : manage-users
    Created on : May 13, 2025, 7:20:33 AM
    Author     : anderson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Manage Users</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-container { margin-bottom: 20px; padding: 15px; border: 1px solid #ddd; }
        .form-group { margin-bottom: 10px; }
        label { display: inline-block; width: 100px; }
        input, select { padding: 5px; }
        button { padding: 5px 10px; margin-right: 5px; }
        .error { color: red; }
        .success { color: green; }
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
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.username}</td>
                <td>${user.role}</td>
                <td>${user.fullName}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>
                    <form method="post" action="manage-users" style="display: inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <button type="submit">Delete</button>
                    </form>
                    <a href="manage-users?action=edit&userId=${user.userId}"><button>Edit</button></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <p><a href="logout">Logout</a></p>
</body>
</html>