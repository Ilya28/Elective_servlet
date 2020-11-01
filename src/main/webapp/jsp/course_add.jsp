<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add course</title>
</head>
<body>
<h3>Add course</h3>
<br/>
<form method="post" action="${pageContext.request.contextPath}/course/add">
    Name (en) <input type="text" name="name_en"><br/>
    Name (ua) <input type="text" name="name_ua"><br/>
    Description (en) <input type="text" name="description_en"><br/>
    Description (ua) <input type="text" name="description_ua"><br/>
    Subject id <input type="text" name="subject"><br/>
    Date start <input type="text" name="date_start"><br/>
    Date end <input type="text" name="date_end"><br/>
    Seats <input type="text" name="seats"><br/>
    <input class="button" type="submit" value="Войти">
</form>
</body>
</html>
