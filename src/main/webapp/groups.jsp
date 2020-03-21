<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mlistek
  Date: 21.03.2020
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>groups</title>
</head>
<body>
    <h3>List of all groups:</h3>

    <table>
        <c:forEach items="${groups}" var="group">
            <tr>
                <td>Group ID: ${group.group_id}</td>
                <td><a href="http://localhost:8080/group/users?group_id=${group.group_id}">Check list of users</a></td>
            </tr>
            <tr>
                <td>Name: ${group.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
