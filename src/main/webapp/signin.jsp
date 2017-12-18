<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in form</title>
</head>
<body>
<h1>
    There will be form for sign in!
</h1>
<form action="price_list" method="post">
    <input type="submit" value="Sign In"/>
</form>
<br><br>
<h1>
    if you are not registered yet
</h1>
<form action="sign_up" method="post">
    <input type="submit" value="Registration"/>
</form>
<br><br>
<h1>
Now we cant get role of user, so here two buttons yet.
</h1>
<form action="admin_page" method="post">
    <input type="submit" value="if you are admin, then you will go this page!"/>
</form>
<br><br>
<form action="/sign_in_ru" method="post">
    <input type="submit" value="Go to russian version of market."/>
</form>
</body>
</html>
