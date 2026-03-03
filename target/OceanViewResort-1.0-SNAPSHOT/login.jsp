<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ocean View Resort - Login</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .login-box {
            background: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.3);
            width: 380px;
        }
        .login-box h2 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
            font-size: 24px;
        }
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
            font-size: 14px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
            font-size: 14px;
        }
        .form-group input {
            width: 100%;
            padding: 12px;
            border: 2px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
            transition: border-color 0.3s;
        }
        .form-group input:focus {
            outline: none;
            border-color: #667eea;
        }
        .btn-login {
            width: 100%;
            padding: 12px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            font-weight: bold;
        }
        .btn-login:hover {
            opacity: 0.9;
        }
        .error {
            background: #fee;
            color: #c00;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
        }
        .hotel-name {
            text-align: center;
            color: #667eea;
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <div class="hotel-name">🌊 Ocean View</div>
    <h2>Resort Management</h2>
    <p class="subtitle">Sign in to your account</p>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error">${error}</div>
    <% } %>

    <form action="${pageContext.request.contextPath}/LoginServlet"
          method="post">
        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username"
                   placeholder="Enter username" required/>
        </div>
        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password"
                   placeholder="Enter password" required/>
        </div>
        <button type="submit" class="btn-login">Login</button>
    </form>
</div>
</body>
</html>