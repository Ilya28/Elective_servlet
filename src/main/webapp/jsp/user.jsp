<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/home">Home</a>
<a href="/logout">Log out</a>
<a href="/courses">Courses</a>
<br/><br/>
<h3>User details</h3>
<br/>
${user.name}<br/>
${user.id}<br/>
${user.role}<br/>
<br/>
<a href="/user/delete?id=${item.id}">Delete</a>
</body>
</html>
