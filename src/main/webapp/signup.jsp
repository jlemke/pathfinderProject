<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 12/3/2016
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Sign Up</title>

    <!-- JQuery -->
    <script src="js/jquery-3.1.1.min.js"></script>

    <!-- Bootstrap -->
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- error handling etc. -->
    <script src="js/signup.js"></script>
</head>
<body>

<form>
    <label>Username</label><input type="text" id="username" /><div id="errors0"></div>
    <label>Password</label><input type="password" id="password1" /><div id="errors1"></div>
    <label>Password</label><input type="password" id="password2" /><div id="errors2"></div>
    <label>Email</label><input type="text" id="email" /><div id="errors3"></div>
    <input type="button" value="Submit" onclick="signUp()" />
</form>
</body>
</html>
