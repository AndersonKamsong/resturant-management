<%-- 
    Document   : tablesList
    Created on : May 13, 2025, 9:08:43 AM
    Author     : anderson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Tables</title>
    <style>
        body {
            background-color: #1c1c1c;
            color: #ffd700;
            font-family: Arial, sans-serif;
        }

        h1, h2 {
            text-align: center;
            color: #ffd700;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #2a2a2a;
        }

        th, td {
            padding: 10px;
            border: 1px solid #444;
            text-align: center;
            color: #fff;
        }

        th {
            background-color: #333;
            color: #ffd700;
        }

        form {
            background-color: #2a2a2a;
            padding: 20px;
            margin: 20px auto;
            width: 60%;
            border-radius: 10px;
        }

        label {
            color: #ffd700;
        }

        input, select {
            padding: 5px;
            margin: 10px 0;
            width: 100%;
            background-color: #444;
            color: #fff;
            border: 1px solid #666;
        }

        button {
            background-color: #ffd700;
            color: #000;
            padding: 10px 15px;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }

        button:hover {
            background-color: #e6c200;
        }

        .actions form {
            display: inline;
        }
    </style>
</head>
<body>

<h1>Restaurant Table Management</h1>

<h2>Add / Update Table</h2>
<form method="post" action="tables">
    <input type="hidden" name="action" value="add" id="formAction"/>
    <input type="hidden" name="table_id" id="tableId"/>

    <label for="tableNumber">Table Number:</label>
    <input type="text" name="table_number" id="tableNumber" required/>

    <label for="capacity">Capacity:</label>
    <input type="number" name="capacity" id="capacity" required/>

    <label for="status">Status:</label>
    <select name="status" id="status">
        <option value="available">Available</option>
        <option value="occupied">Occupied</option>
        <option value="reserved">Reserved</option>
    </select>

    <label for="locationDescription">Location Description:</label>
    <input type="text" name="location_description" id="locationDescription"/>

    <button type="submit">Submit</button>
</form>

<h2>Existing Tables</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Table Number ${tables[0]}</th>
        <th>Capacity</th>
        <th>Status</th>
        <th>Location</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="table" items="${tables}">
        <tr>
            <td>${table.id}</td>
            <td>${table.tableNumber}</td>
            <td>${table.capacity}</td>
            <td>${table.status}</td>
            <td>${table.locationDescription}</td>
            <td class="actions">
                <!-- Update form trigger -->
                <button onclick="fillForm('${table.id}', '${table.tableNumber}', '${table.capacity}', '${table.status}', '${table.locationDescription}')">Edit</button>

                <!-- Delete -->
                <form method="post" action="tables">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="table_id" value="${table.id}"/>
                    <button type="submit">Delete</button>
                </form>

                <!-- Status Toggle -->
                <form method="post" action="tables">
                    <input type="hidden" name="action" value="updateStatus"/>
                    <input type="hidden" name="table_id" value="${table.id}"/>
                    <input type="hidden" name="new_status" value="${table.status eq 'available' ? 'occupied' : 'available'}"/>
                    <button type="submit">${table.status eq 'available' ? 'Mark Occupied' : 'Mark Available'}</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    function fillForm(id, number, capacity, status, location) {
        document.getElementById("formAction").value = "update";
        document.getElementById("tableId").value = id;
        document.getElementById("tableNumber").value = number;
        document.getElementById("capacity").value = capacity;
        document.getElementById("status").value = status;
        document.getElementById("locationDescription").value = location;
    }
</script>

</body>
</html>


