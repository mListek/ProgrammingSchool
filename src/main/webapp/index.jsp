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
    <p>
        <h1>Lastest ${number} solutions added:</h1>
    </p>
    <table>
        <c:forEach items="${solutions}" var="solution">
            <tr>
                <td>ID: ${solution.solution_id}</td>
                <td>|</td>
                <td>Solution: ${solution.description}</td>
                <td>|</td>
                <td><a href="http://localhost:8080/ProgrammingSchool_war_exploded/solution?solution_id=${solution.solution_id}">Details</a></td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <h2>This is text after forEach loop</h2>
    </p>

    <%@ include file="footer.jsp" %>
</body>
</html>
