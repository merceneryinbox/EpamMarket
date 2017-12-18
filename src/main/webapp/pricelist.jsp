<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<html>
<head>
    <title>Price list</title>
</head>
<body>
<h1>
    Price list will be here!
</h1>
<br><br>

<form action="/" method="post">
    <input type="submit" value="Go to welcome-page"/>
</form>

<br><br>
<h1>
    This buttons will appear only if you are not logged.
</h1>
<form action="sign_in" method="post">
    <input type="submit" value="Sign In"/>
</form>
<form action="sign_up" method="post">
    <input type="submit" value="Registration"/>
</form>

<br><br>

<h1>
    This button will apear only if you are logged.
</h1>
<form action="cart" method="post">
    <input type="submit" value="Go to cart"/>
</form>
<br><br>
<form action="/price_list_ru" method="post">
    <input type="submit" value="Go to russian version of market."/>
</form>

</body>
</html>
