<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<a href="/logout">Log out</a>
<br/><br/>
<a href="/courses">Courses</a>
<br/>
Registrations
<br/>
<table>
    <c:forEach items="${registrations}" var="item">
        <tr>
            <td>
                <a href="/course?id=${item.course}">
                    Course ID: <c:out value="${item.course}"/>
                </a>
            </td>
            <td>
                <a href="/course/register/cancel?id=${item.id}">
                    Cancel
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
