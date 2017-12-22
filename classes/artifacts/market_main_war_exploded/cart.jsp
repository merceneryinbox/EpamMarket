<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User's cart</title>
</head>
<body>
<div>
    <table style="table-layout: auto" border="2" align="center" width="600">
        <tr>
            <th>Goods ID</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="cart" items="${userCart}">
            <tr>
                <td><c:out value='${cart.goodId}'/></td>
                <td><c:out value='${cart.amount}'/></td>
            </tr>
        </c:forEach>
    </table>
    <form action="price_list" method="post">
        <p> Delete </p>
        <input type="hidden" name="goodsName" value="${product.name}">
        <input type="submit" value="Delete">
    </form>
</div>

<form action="price_list" method="get">
    <input type="submit" value="go back to price list"/>
</form>
</body>
</html>
