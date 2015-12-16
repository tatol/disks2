<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Disks2</title>
</head>
<body>
<h2>Список дисков, взятых пользователем</h2>

<c:if test="${pageContext.request.userPrincipal.name != null}">
 <c:if  test="${!empty listTakenDisksByUser}">

  <table border="1px" cellpadding="8px" class="data">
    <tr>
      <th>diskID</th>
      <th>disk</th>
      <th>from</th>
      <th>return</th>
    </tr>
    <c:forEach items="${listTakenDisksByUser}" var="takenByUser">
      <tr>
        <td>${takenByUser.id}</td>
        <td>${takenByUser.name}</td>
        <td>${takenByUser.fromUser.login}</td>
        <td><a href="${takenByUser.id}/${takenByUser.fromUser.id}/returnDiskToUser">отдать</a></td>
      </tr>
    </c:forEach>
  </table>
 </c:if>
    <c:url value="/logout" var="logoutUrl" />
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <h2>
        Пользователь : ${pageContext.request.userPrincipal.name} | <a
            href="javascript:formSubmit()"> Выйти</a>
    </h2>
    <h3><a href="/">Назад</a></h3>
</c:if>
</body>
</html>
