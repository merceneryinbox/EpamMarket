<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Price list</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
<%@include file="header.jspf" %>
<%---------------------------content--%>
<div>
    <h2 class="text-center">List of our Product</h2>
    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <th>In stock</th>
            <th>Description</th>
            <th>Price,$</th>
            <th>Add this goods!</th>
        </tr>
        <c:forEach var="product" items="${priceList}">
            <tr>
                <td><c:out value='${product.name}'/></td>
                <td><c:out value='${product.amount}'/></td>
                <td><c:out value='${product.description}'/></td>
                <td><c:out value='${product.price}'/></td>
                <td>
                    <form action="price_list" method="post">
                        <p> Amount: </p>
                        <div class="row">
                            <div class="col-sm-4">
                                <input class="form-control" type="number" name="amount" value="1" min="1"
                                       max="${product.amount}" required>
                                <input type="hidden" name="goodsId" value="${product.id}">
                            </div>
                            <div class="col-sm-8">
                                <c:choose>
                                    <c:when test="${product.amount<1||sessionScope.user==null}">
                                        <input class="btn-info btn" type="submit" value="Add in cart" disabled>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="btn-info btn" type="submit" value="Add in cart">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <c:if test="${sessionScope.user==null}">
                <div>Please log in or log up for use your own cart</div>
                <form action="sign_in" method="get">
                    <input class=" btn btn-primary" type="submit" value="Sign In"/>
                </form>
                <form action="sign_up" method="get">
                    <input class="btn btn-success" type="submit" value="Registration"/>
                </form>
            </c:if>
            <c:if test="${sessionScope.user!=null}">
                <div>See your cart</div>
                <form action="cart" method="get">
                    <input class="btn btn-success" type="submit" value="Go to cart"/>
                </form>
            </c:if>
        </div>
        <div class="col-sm-4">
            <c:if test="${sessionScope.user==null}">
                <div>If you want to buy something without log in:</div>
                <div>Call: +7-999-9999</div>
            </c:if>
        </div>
    </div>
</div>

<form action="price_list" method="get">
    <input class="btn btn-primary btn-block" type="submit" value="Back to top"/>
</form>
<br>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
