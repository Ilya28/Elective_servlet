<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login in system</title>
</head>
<body>
        <h1>Вход в систему</h1><br/>
        <form method="post" action="/login">

            Email <input type="text" name="email"><br/>
            Pass <input type="password" name="pass"><br/><br/>
            <input class="button" type="submit" value="Войти">

        </form>
        <br/>

        <a href="/register">Register</a>
</body>
</html>