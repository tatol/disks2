<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>Администрирование</h1>

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
<c:if  test="${!empty listUser}">

    <table border="1px" cellpadding="8px" class="data">
        <tr>
            <th>userID</th>
            <th>login</th>
            <th>roles</th>
        </tr>
        <c:forEach items="${listUser}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
            <c:forEach items="${user.roles}" var="roles">
                        <td>${roles.role}</td>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>
<h2>Добавить Нового пользователя</h2>
<form:form method="post" action="addUser" commandName="newUser">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <table width="400px" height="150px">
        <tr>
            <td><form:label path="login">Логин</form:label></td>
            <td><form:input path="login" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Пароль</form:label></td>
            <td><form:input path="password" type="password" /></td>
        </tr>
        <tr>
            <td><form:label path="roles">Роль</form:label></td>
            <td><input type="text" name="role"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Добавить" />
            </td>
        </tr>
    </table>
</form:form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
  <h2>
    Администратор : ${pageContext.request.userPrincipal.name} | <a
          href="javascript:formSubmit()"> Выйти</a>
  </h2>
</c:if>
<h3><a href="/">Назад</a></h3>
</body>
</html>
