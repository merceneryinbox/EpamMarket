<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in form</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
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
<div class="container">
    <form class="form-signin" action="/sign_in" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p><strong>Login:</strong></p>
        <input class="form-control" type="text" name="login" value="" required autofocus/>
        <br/>
        <p><strong>Password:</strong></p>
        <input type="password" name="password" value="" class="form-control" placeholder="Password" required/><br/><br>
        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign In"/> <br/>
    </form>
    <form action="sign_up" method="get">
        <input class="btn btn-lg btn-success btn-block" type="submit" value="Registration"/>
    </form>
</div>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>