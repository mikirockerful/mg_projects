<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nueva solicitud</title>
</head>
<body>
<c:if test="${not empty user and empty msth and empty msths}">
Student: this is a MsTH request
    <form action="newMsTH" method="post" acceptcharset="utf-8">
    <input type="text" name="title" id="title" maxLength="255"
        size="20" required placeholder="Title" />
    <input type="text" name="advisor" id="advisor"
        maxLength="255" required size="20" placeholder="advisor" />
    <input type="submit" value="Send" />
</form>
</c:if>
</body>
</html>