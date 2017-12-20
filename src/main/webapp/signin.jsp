<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in form</title>
</head>
<body>
<form action="/signin.jsp" method="post">
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
<form action="/signup.jsp" method="post">
    <input type="submit" value="Registration"/>
</form>
<br><br>
<h1>
Now we cant get role of user, so here two buttons yet.
</h1>
<form action="/adminpage.jsp" method="post">
    <input type="submit" value="if you are admin, then you will go this page!"/>
</form>
</body>
</html>



