<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form action="/signin.jsp" method="post">
    <input type="submit" value="Sign In"/>
</form>
<form action="/signup.jsp" method="post">
    <input type="submit" value="Registration"/>
</form>

<br><br>

<h1>
    This button will apear only if you are logged.
</h1>
<form action="/cart.jsp" method="post">
    <input type="submit" value="Go to cart"/>
</form>


</body>
</html>
