<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
    <link rel="icon" href="https://www.atypon.com/wp-content/themes/atypon-2018/assets/favicon/favicon.ico">
</head>
<body>
<form action="/signup" method="POST">
    <h3>Sign Up</h3>

    <label for="regUsername">Username</label>
    <input type="text" name="regUsername" placeholder="Username" id="regUsername" required="">

    <label for="regPassword">Password</label>
    <input type="password" name="regPassword" placeholder="Password" id="regPassword" required="">

    <button>Sign Up</button>
    <br>
    <p style="color: red">${regErrorMessage}</p>

</body>
</html>