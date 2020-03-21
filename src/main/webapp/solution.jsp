<%--
  Created by IntelliJ IDEA.
  User: mlistek
  Date: 21.03.2020
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Solution</title>
</head>
<body>
    <h1>Exercise: ${exercise.title}</h1>
    <h4>${exercise.description}</h4>



    <h1>Solution ${solution.solution_id}</h1>
    <table>
        <tr>
            <td> Description: </td>
        </tr>
        <tr>
            <td>${solution.description} </td>
        </tr>
        <tr>
            <td> Created: ${solution.created} </td>
            <td> Updated: ${solution.updated} </td>
        </tr>
        <tr>
            <td> Author: ${username}</td>
        </tr>
    </table>
</body>
</html>
