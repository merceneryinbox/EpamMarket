<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User's cart</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
<%@include file="header.jspf" %>
<%---------------------------content--%>
<div>
    <h2 class="text-center">Cart for <b><c:out value="${sessionScope.user.login}"/></b></h2>
    <table class="table table-striped">
        <tr>
            <th>Goods name</th>
            <th>Amount</th>
            <th>Price for each</th>
            <th>Price for all</th>
            <th>Remove from cart</th>
        </tr>
        <c:set var="total" value="${0}"/>
        <c:forEach var="cart" items="${userCart}">
            <tr>
                <td><c:out value='${cart.goodName}'/></td>
                <td><c:out value='${cart.amount}'/></td>
                <td><c:out value='${cart.price}'/></td>
                <td><c:out value='${cart.price * cart.amount}'/></td>
                <c:set var="total" value="${total + cart.price*cart.amount}" />
                <td>
                    <form action="cartwithdelete" method="post">
                    <input type="hidden" name="goodsId" value="${cart.goodId}">
                    <input class="btn-info btn" type="submit" value="Remove"></form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<p> Total:<c:out value='${total}'/> </p>
<form action="payment.jsp" method="get">
    <input class="btn-info btn" type="submit" value="Confirm payment"/>
</form>

<form action="price_list" method="get">
    <input class="btn btn-primary btn-block" type="submit" value="Go back to price list"/>
</form>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
