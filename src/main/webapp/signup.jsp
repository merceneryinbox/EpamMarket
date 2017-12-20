<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Registration Form</title>
</head>
<style>
    div {
        padding: 10px;
        border: 5px solid grey;
        margin: 0px
    }
</style>
<body>
<h1> Registration Form</h1>
<form action="${pageContext.servletContext.contextPath}/servlets/SignUpServlet" method="post">
    <table style="with: 50%">
            <td>Login</td>
            <td><input type="text" name="login" value="${user.login}"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" value="${user.phone}"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${user.email}"/></td>
        </tr></table>
    <input type="submit" value="Sign Up"/>
</form>
</body>
</html>

