<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h1>${title}</h1>
<c:if test="${pageContext.request.userPrincipal.name == null}">
<h1>${message}</h1>
</c:if>

    <!-- For login user -->
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
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <h3><a href="/login">Войти</a></h3>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Пользователь : ${pageContext.request.userPrincipal.name} | <a
                href="javascript:formSubmit()"> Выйти</a>
        </h2>
        <sec:authorize access="hasRole('role_admin')">
            <h3><a href="/admin">Администрирование</a></h3>
        </sec:authorize>
        <h3><a href="/listOwnDisks">Список своих дисков</a></h3>
        <h3><a href="/listFreeDisks">Свободные диски</a></h3>
        <h3><a href="/listTakenDisksByUser">Диски взятые пользователем</a></h3>
        <h3><a href="/listTakenDisksFromUser">Диски взятые у пользователя</a></h3>
    </c:if>


</body>
</html>