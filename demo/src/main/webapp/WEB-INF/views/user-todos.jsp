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
</head>
<body style="alignment: center">
<div>
    <h1 style="text-align: center">Welcome ${username}</h1>

    <table border="1">
        <tr>
            <td>No</td>
            <td>Task Description</td>
            <td>Task Status</td>

        <tr>
            <c:forEach items="${userTodos}" var="show">
        <tr>
            <td>${show.id}</td>
            <td>${show.desc}</td>
            <td>${show.status}</td>
            <td><a href="delete?id=${show.id}" style="text-decoration-line: none"><button>Delete</button></a></td>

        </tr>
        </c:forEach>
    </table>
    <a href="/add-todo" style="text-decoration-line: none">
        <button> Add</button>
    </a>

</div>

</body>


</html>
