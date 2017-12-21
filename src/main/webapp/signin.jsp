<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in form</title>
</head>
<body>
<form action="/sign_in" method="post">
    <p><strong>Login:</strong></p>
    <input type="text" name="login" value=""/>
    <br/><p><strong>Password:</strong></p>
    <input type="password" name="password" value=""/><br/><br>
    <input type="submit" value="Sign In"/> <br/>
</form>
<br><br>
<h1>
    if you are not registered yet
</h1>
<form action="sign_up" method="get">
    <input type="submit" value="Registration"/>
</form>
<br><br>
<h1>
Now we cant get role of user, so here two buttons yet.
</h1>
<form action="admin" method="get">
    <input type="submit" value="if you are admin, then you will go this page!"/>
</form>
</body>
</html>