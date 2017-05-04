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
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="login.css" type="text/css">
</head>
<body>
    <div class="container">
        <div class="login-form" style="margin-top: 15%">
            <form class="form-horizontal col-sm-6 col-sm-offset-3 well-lg" action="j_security_check" method="POST">
                <div class="form-group">
                    <label for="username" class="col-sm-4 control-label">Username:</label>
                    <div class="col-sm-6">
                        <input id="username" class="form-control" type="text" name="j_username" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-4 control-label">Password:</label>
                    <div class="col-sm-6">
                        <input id="password" class="form-control" type="password" name="j_password" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-6">
                        <button type="submit" class="btn btn-default">Log In</button>
                    </div>
                </div>
                <div class="col-sm-offset-4 col-sm-8">
                    Don't have an account? <a href="${pageContext.request.contextPath}/signup.jsp">Sign up!</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
