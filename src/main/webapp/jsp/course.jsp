<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course</title>
</head>
<body>

<a href="/home">Home</a>
<a href="/logout">Log out</a>
<br/><br/>
<h3>Course</h3>
<br/>
<h4>${course.nameEN}</h4><br/>
name: ${course.nameEN}<br/>
description: ${course.descriptionEN}<br/>
date start: ${course.dateStart}<br/>
date end: ${course.dateEnd}<br/>
<br/>
<a href="/course/register?id=${course.id}">
    Register
</a>
</body>
</html>
