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
<head>
    <link href="css/sheet_list.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/sheetList.js"></script>
</head>
<body>

<table class="table" id="sheetList">
    <tr>
        <th>Name</th>
        <th>Race</th>
        <th>Classes</th>
        <th>Campaign</th>
        <th>Date Created</th>
        <th>Last Accessed</th>
    </tr>
    <c:forEach items="${sheets}" var="sheet">
        <tr class="sheet-row" sheet-id="${sheet.sheetId}">
            <td>${sheet.characterName}</td>
            <td>${sheet.characterRace}</td>
            <td>${sheet.characterClassString}</td>
            <td>${sheet.campaign}</td>
            <td>${sheet.dateCreated}</td>
            <td>${sheet.lastAccessed}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
