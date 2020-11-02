<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1><br/>
<form method="post" action="/register">

    Name <input type="text" name="name"><br/>
    Email <input type="text" name="email"><br/>
    Pass <input type="password" name="pass"><br/><br/>
    <input class="button" type="submit" value="Register">

</form>
<br/>

<a href="/login">Log in</a>
</body>
</html>
