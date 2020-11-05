<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 05/11/2020
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/login.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/all.css" type="text/css">
</head>
<body>
<div class="login">
    <div class="login-left"></div>
    <div class="login-right">
        <div class="login-right-form-wrapper">
            <h1>HELLO</h1>
            <form>
                <label for="login">Login</label>
                <div>
                    <span></span>
                    <input type="text" id="login" name="login" /><br>
                </div>
                <label for="pwd">Password</label>
                <input type="password" id="pwd" name="pwd" /><br>
                <input type="submit" name="buttonSend" value="connect">
            </form>
        </div>
    </div>
</div>
</body>
</html>