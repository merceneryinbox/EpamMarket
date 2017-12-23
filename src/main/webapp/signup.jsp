<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mystuff" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Registration Form</title>
</head>
<style>
    layer {
        background: ghostwhite;
        border: 2px solid black;
        padding: 20px;
    }
</style>
<body>
<h1> Registration Form</h1>
<form action="sign_up" method="post">
    <table style="with: 80%">
        <td>Login</td>
        <td><input type="text" name="login" value=""/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" value=""/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value=""/></td>
        </tr></table>

    <input type="submit" value="Sign Up"/>
</form>
</body>
</html>

