<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>error</title>
</head>
<body>
<body>
<h1>Ошибка входа</h1>

<c:choose>
  <c:when test="${empty login}">
    <h2>Нет прав на доступ к этой странице</h2>
  </c:when>
  <c:otherwise>
    <h2>Пользователь : ${login} <br/>Нет прав на доступ к этой странице</h2>
  </c:otherwise>
</c:choose>

</body>
</body>
</html>
