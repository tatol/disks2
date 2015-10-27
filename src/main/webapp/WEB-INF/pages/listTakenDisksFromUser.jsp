<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Disks</title>
</head>
<body>
<h2>Список дисков взятых у пользователя</h2>


<c:if  test="${!empty listTakenDisksFromUser}">

  <table border="1px" cellpadding="8px" class="data">
    <tr>
      <th>diskID</th>
      <th>disk</th>
      <th>who</th>
    </tr>
    <c:forEach items="${listTakenDisksFromUser}" var="takenFromUser">
      <tr>
        <td>${takenFromUser.disk.id}</td>
        <td>${takenFromUser.disk.name}</td>
        <td>${takenFromUser.user.login}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>