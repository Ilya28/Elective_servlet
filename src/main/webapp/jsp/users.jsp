<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/paginationTag.tld" prefix="elective" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<a href="/home">Home</a>
<a href="/logout">Log out</a>
<a href="/courses">Courses</a>
<br/><br/>
<h3>Users</h3>
<br/>
Users list
<br/>
<table>
    <c:forEach items="${users}" var="item">
        <tr>
            <td>
                <a href="/user?id=${item.id}">
                    User ID: <c:out value="${item.id}"/>
                </a>
            </td>
            <td>
                <a href="/user?id=${item.id}">
                    User name: <c:out value="${item.name}"/>
                </a>
            </td>
            <td>
                <a href="/user/delete?id=${item.id}">
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<elective:pager page="${page}" count="${pages}"/>

</body>
</html>
