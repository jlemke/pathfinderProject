<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 11/23/2016
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@include file="navbar.jsp"%>
<body>

<table class="table">
    <c:forEach items="${sheets}" var="sheet">
        <tr>
            <td>${sheet.characterName}</td>
            <td>${sheet.characterRace}</td>
            <td>${sheet.campaign}</td>
            <td></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
