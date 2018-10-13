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
    <script>
        var request = new XMLHttpRequest();
        var chats;
        request.open("GET", "/api/chats?id_user=1")
        request.send()

        function clicks() {
            chats = JSON.parse(request.responseText)
            alert(chats.length)
            
            for () 
        }
    </script>

    <div id="test" onclick="clicks();">
        Text
    </div>
</body>
</html>
