<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dolphin shops!</title>
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
<%---------------------------content--%>
<div class="container">
    <%--<p><img src="https://i.mycdn.me/image?id=838978447422&t=37&plc=WEB&tkn=*INSPiOXNMUkXB9voQnYKtsVMAKw" alt=""></p>--%>
    <div>
        <c:if test="${sessionScope.user==null}">
            <div class="page-header">
                <h1>
                    Glad to see you in our dolphin-shop!
                </h1>
            </div>
            <form action="sign_in" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Sign In"/>
            </form>
            <form action="sign_up" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Registration"/>
            </form>
            <form action="price_list" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Watch our goods!"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.user!=null}">
            <div class="page-header">
                <h1>
                    Welcome Back!
                </h1>
            </div>
            <form action="price_list" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Watch our goods!"/>
            </form>
            <form action="cart" method="get">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Look at your cart!"/>
            </form>
        </c:if>

    </div>
</div>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
