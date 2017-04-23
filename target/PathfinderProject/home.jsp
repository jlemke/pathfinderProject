<%@include file="head.jsp"%>
<html>
<%@include file="navbar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<c:if test="${loggedIn}">
    <div>Currently logged in as ${username}</div>
    <div><a href="${pageContext.request.contextPath}/sheets">View Sheets</a></div>
    <div><a href="${pageContext.request.contextPath}/logout">Log Out</a></div>
</c:if>

<c:if test="${!loggedIn}">
    <div><a href="${pageContext.request.contextPath}/login">Log In</a></div>
    <div><a href="${pageContext.request.contextPath}/signup.jsp">Sign Up</a></div>
</c:if>
</body>
</html>