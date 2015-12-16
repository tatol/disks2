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
<h2>Список своих дисков</h2>
<c:if test="${pageContext.request.userPrincipal.name != null}">
 <c:if  test="${!empty listOwnDisks}">

  <table border="1px" cellpadding="8px" class="data">
    <tr>
      <th>diskID</th>
      <th>disk</th>
    </tr>
    <c:forEach items="${listOwnDisks}" var="own">
      <tr>
        <td>${own.id}</td>
        <td>${own.name}</td>
        <td><a href="${own.id}/returnOwnDisk">вернуть</a></td>
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
