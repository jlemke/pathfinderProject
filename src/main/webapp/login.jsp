<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 12/3/2016
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="j_security_check" method="POST">
    <table>
        <tr><td>User name: <input type="text" name="j_username"></td></tr>
        <tr><td>Password: <input type="password" name="j_password"></td></tr>
        <tr><td><input type="SUBMIT" value="Log In"></td></tr>
    </table>
    <a href="${pageContext.request.contextPath}/signup.jsp">Create Account</a>
</form>
</body>
</html>
