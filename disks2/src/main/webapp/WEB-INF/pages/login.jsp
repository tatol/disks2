<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Disks2</title>
<body>
<h2>Войти</h2>
<form:form action="login"  commandName="loginForm">
  <table>
    <tr>
      <td>User Name:<FONT color="red"><form:errors
              path="login" /></FONT></td>
    </tr>
    <tr>
      <td><form:input path="login" /></td>
    </tr>
    <tr>
      <td>Password:<FONT color="red"><form:errors
              path="password" /></FONT></td>
    </tr>
    <tr>
      <td><form:password path="password" /></td>
    </tr>
    <tr>
      <td><input type="submit" value="Submit" /></td>
    </tr>
  </table>
</form:form>

</body>
</html>