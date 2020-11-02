<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="locale" />

<html>
<head>
    <title>Home</title>
</head>
<body>
<a href="/home"><fmt:message key="home.title" /></a>
<a href="/logout"><fmt:message key="nav.logout" /></a>
<a href="/courses"><fmt:message key="nav.courses" /></a>
<a href="/users"><fmt:message key="nav.users" /></a>
|
<a href="/locale?locale=${sessionScope.locale=='en'?'ua':'en'}">${sessionScope.locale=='en'?'ua':'en'}</a>

<br/><br/>
<h3><fmt:message key="home.title" /></h3>
<br/>
<fmt:message key="registrations" />
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
                <a href="/course/register/cancel?id=${item.course}">
                    <fmt:message key="btn.cancel" />
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
