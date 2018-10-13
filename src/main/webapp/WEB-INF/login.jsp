<%--
  Created by IntelliJ IDEA.
  User: slimakanzer
  Date: 12.10.18
  Time: 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
    <form action="/login" method="post">
        <label for="username"></label>
        <input type="text" id="username" name="ssoId" placeholder="Enter Username" required>

        <label for="password"></label>
        <input type="password" id="password" name="password" placeholder="Enter Password" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Log in">
    </form>
</body>
</html>
