<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Disks2</title>
</head>
<tr>

    <td background="pics/Pic4Back.jpg" valign="top">
        <p>
            <span style="color: #ffffff;"><%=request.getParameter("system_message")%></span>
        </p>
    </td>
</tr>
</head>
<body>
<h2>Список свободных дисков</h2>


<c:if  test="${!empty listFreeDisks}">

    <table border="1px" cellpadding="8px" class="data">
        <tr>
            <th>ID</th>
            <th>disk</th>
        </tr>
        <c:forEach items="${listFreeDisks}" var="free">
            <tr>
                <td>${free.id}</td>
                <td>${free.name}</td>
                <td><a href="${free.id}/takeFreeDisk">взять</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<h2>Список свободных дисков всех пользователей</h2>

<c:if  test="${!empty listOwnDisksFromAllUsers}">

    <table border="1px" cellpadding="8px" class="data">
        <tr>
            <th>diskID</th>
            <th>disk</th>
            <th>user</th>
            <th>take</th>
        </tr>
        <c:forEach items="${listOwnDisksFromAllUsers}" var="allOwn">
            <tr>
                <td>${allOwn.disk.id}</td>
                <td>${allOwn.disk.name}</td>
                <td>${allOwn.user.login}</td>
                <td><a href="${allOwn.disk.id}/${allOwn.user.id}/takeFreeDiskFromUser">взять</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<h3><a href="/login/listTakenDisksByUser">Взятые диски</a></h3>
</body>
</html>