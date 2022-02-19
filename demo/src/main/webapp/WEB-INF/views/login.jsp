<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
    <link rel="icon" href="https://www.atypon.com/wp-content/themes/atypon-2018/assets/favicon/favicon.ico">
</head>
<body>
<form action="/login" method="POST">
    <h3>Login</h3>

    <label for="username">Username</label>
    <input name="username" type="text" placeholder="Username" id="username">

    <label for="password">Password</label>
    <input name="password" type="password" placeholder="Password" id="password">

    <button>Log In</button>
    <a href="/signup"> register? </a>
    <br>
    <p style="color: red">${errorMessage}</p>

</body>
</html>