<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mlistek
  Date: 21.03.2020
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
    <%@ include file="header.jsp" %>

    <table>
        <c:forEach items="${solutions}" var="solution">
            <tr>
                <td>Solution : ${solution.description}, Created: ${solution.created}</td>
            </tr>
        </c:forEach>
    </table>

    <%@ include file="footer.jsp" %>
</body>
</html>
