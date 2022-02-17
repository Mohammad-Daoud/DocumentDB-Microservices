<%--
  Created by IntelliJ IDEA.
  User: mdss4
  Date: 2/16/2022
  Time: 7:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${username} Todo</title>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
    <link rel="icon" href="https://www.atypon.com/wp-content/themes/atypon-2018/assets/favicon/favicon.ico">
    <style>
        body{
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 50vh;
            font-family: 'Jost', sans-serif;
            background: linear-gradient(to bottom, #006400, #006400, #006400);
        }
        button{
            width: 60%;
            height: 40px;
            justify-content: center;
            display: block;
            color: #fff;
            background: #228B22;
            font-size: 1em;
            font-weight: bold;
            margin: 20px auto 30px;
            outline: none;
            border: none;
            border-radius: 5px;
            transition: .2s ease-in;
            cursor: pointer;

        }
        button:hover{
            background: #32CD32;
        }
    </style>
</head>
<body>
<div>
    <h1 style="text-align: center">Welcome ${username}</h1>

    <table class="table table-striped">
        <tr>
            <td>No</td>
            <td>Task Description</td>
            <td>Task Status</td>

        <tr class="active-row">
            <c:forEach items="${userTodos}" var="show">
        <tr>
            <td>${show.id}</td>
            <td>${show.desc}</td>
            <td>${show.status}</td>

        </tr>
        </c:forEach>
    </table>
    <a href="/add-todo" style="text-decoration-line: none"> <button> Add </button> </a>

</div>

</body>


</html>
