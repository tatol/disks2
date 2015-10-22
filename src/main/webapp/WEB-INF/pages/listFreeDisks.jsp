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
        <c:forEach items="${listFreeDisks}" var="listFreeDisks">
            <tr>
                <td>${listFreeDisks.id}</td>
                <td>${listFreeDisks.name}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>