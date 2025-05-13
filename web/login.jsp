<%-- 
    Document   : login
    Created on : May 13, 2025, 7:19:37 AM
    Author     : anderson
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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

        .login-container {
            background-color: #2c2c2c;
            padding: 35px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.4);
            width: 100%;
            max-width: 420px;
            color: #FFD700; /* golden yellow text */
        }

        .login-container h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #FFD700;
        }

        .form-group {
            margin-bottom: 20px;
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
            background: #1e1e1e;
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
            font-weight: bold;
            font-size: 16px;
        }

        button:hover {
            background-color: #e6c200;
        }

        .error {
            color: #ff6b6b;
            margin-bottom: 10px;
            text-align: center;
        }

        .success {
            color: #32cd32;
            margin-bottom: 10px;
            text-align: center;
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
        }

        .register-link a {
            color: #FFD700;
            text-decoration: none;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>Login</h1>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="success">${success}</div>
    </c:if>

    <form method="post" action="login">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required placeholder="Enter your username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required placeholder="Enter your password">
        </div>
        <button type="submit">Login</button>
    </form>

    <div class="register-link">
        <p>Don't have an account? <a href="register">Register here</a></p>
    </div>
</div>
</body>
</html>


