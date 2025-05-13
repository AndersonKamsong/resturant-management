<%-- 
    Document   : register
    Created on : May 13, 2025, 7:20:10 AM
    Author     : anderson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #1e1e1e; /* light black */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .register-container {
            background-color: #2c2c2c;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.4);
            width: 100%;
            max-width: 450px;
            color: #FFD700; /* golden yellow */
        }

        .register-container h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #FFD700;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #FFD700;
            background-color: #1e1e1e;
            color: #FFD700;
            box-sizing: border-box;
        }

        input::placeholder {
            color: #cccccc;
        }

        input:focus {
            border-color: #ffc107;
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #FFD700;
            color: #1e1e1e;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
        }

        button:hover {
            background-color: #e6c200;
        }

        .error {
            color: #ff6b6b;
            margin-bottom: 10px;
            text-align: center;
        }

        .login-link {
            text-align: center;
            margin-top: 20px;
        }

        .login-link a {
            color: #FFD700;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h1>Register</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="register">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required placeholder="Choose a username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required placeholder="Create a password">
        </div>
        <div class="form-group">
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name="fullName" required placeholder="Your full name">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required placeholder="you@example.com">
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" placeholder="Optional">
        </div>
        <button type="submit">Register</button>
    </form>

    <div class="login-link">
        <p>Already have an account? <a href="login">Login here</a></p>
    </div>
</div>
</body>
</html>
