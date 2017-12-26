<%--
  Created by IntelliJ IDEA.
  User: norri
  Date: 25.12.2017
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Payment page</title>
    <style>
        .fig {
            text-align: center;
        }
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
<%@include file="header.jspf" %>
<%---------------------------content--%>
<div>
    <h2 class="text-center">Payment page <b><c:out value="${sessionScope.user.login}"/></b></h2>
</div>
<p class="fig"><img src="https://magento-forum.ru/uploads/monthly_10_2013/post-1-0-91432800-1382385248_thumb.png" alt=""></p>
<form action="cart" method="get">
    <input class="btn btn-primary btn-block" type="submit" value="Go back to cart"/>
</form>

<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
