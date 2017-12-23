<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title> Registration Form</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<div class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp" methods="post">Dolphin shop</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <div><img src="https://icon-icons.com/icons2/29/PNG/256/animal_dolphin_2740.png" height="50"></div>
                </li>
                <li><a method="get" href="price_list">PriceList</a></li>
            </ul>
        </div>
    </div>
</div>
<%---------------------CONTENT--%>
<div>
    <form action="sign_up" method="post">
        <h2 class="form-signin-heading">Registration Form</h2>
        <p><strong>Login:</strong></p>
        <input class="form-control" type="text" name="login" value="" placeholder="Login" required autofocus/><br>
        <p><strong>Password:</strong></p>
        <input class="form-control" type="password" name="password" value="" placeholder="Password" required/><br>
        <p><strong>Phone:</strong></p>
        <input class="form-control" type="text" name="phone" value="" placeholder="+19992345678" required/><br>
        <p><strong>Email:</strong></p>
        <input class="form-control" type="text" name="email" value="" placeholder="email@smth.com" required/>
        <br>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign Un"/> <br/>
    </form>
</div>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>

