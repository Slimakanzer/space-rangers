<%--
  Created by IntelliJ IDEA.
  User: slimakanzer
  Date: 12.10.18
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <div id="test" onclick="clicks();">
        It's users page :0
        <c:if test="${pageContext.request.userPrincipal.name != null}">${pageContext.request.userPrincipal.name} ${pageContext.request.userPrincipal.toString()}</c:if>
    </div>
</body>
</html>
