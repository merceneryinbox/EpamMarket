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
<%@include file="header.jspf" %>
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
                <input class="btn btn-success btn-lg btn-block" type="submit" value="Registration"/>
            </form>
            <form action="price_list" method="get">
                <input class="btn btn-warning btn-lg btn-block" type="submit" value="Watch our goods!"/>
            </form>
        </c:if>
        <c:if test="${sessionScope.user!=null}">
            <div class="page-header">
                <h1>
                    Welcome Back,
                    <c:out value="${sessionScope.user.login}"/>
                    !
                </h1>
            </div>
            <form action="price_list" method="get">
                <input class="btn btn-warning btn-lg btn-block" type="submit" value="Watch our goods!"/>
            </form>
            <form action="cart" method="get">
                <input class="btn btn-success btn-lg btn-block" type="submit" value="Look at your cart!"/>
            </form>
        </c:if>

    </div>
</div>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
