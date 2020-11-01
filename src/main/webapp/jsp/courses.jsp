<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
    <a href="/home">Home</a>
    <a href="/logout">Log out</a>
    <br/><br/>
    <h3>Courses</h3>
    <br/>
    <table>
    <c:forEach items="${courses}" var="item">
        <tr>
            <td>
                <a href="/course?id=${item.id}">
                    <c:out value="${item.nameEN}"/>
                </a>
            </td>
            <td>
                <c:out value="${item.dateStart}"/>
            </td>
            <td>
                <c:out value="${item.dateEnd}"/>
            </td>
            <td>
                <a href="/course/register?id=${item.id}">
                    Register
                </a>
            </td>
            <td>
                <a href="/course/delete?id=${item.id}">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
    </table>
    <br/>
    <a href="/course/add">
        Add course
    </a>
</body>
</html>
