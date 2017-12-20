<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Price list</title>
</head>
<body>
<div>
    <div style="text-align: center">
        <div style="font-size:25pt">List of our Product</div>
        <div>
            <table style="table-layout: auto" border="2" align="center" width="600">
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>In stock</th>
                </tr>
                <c:forEach var="product" items="${priceList}">
                    <tr>
                        <td><c:out value='${product.name}'/></td>
                        <td><c:out value='${product.price}'/></td>
                        <td><c:out value='${product.description}'/></td>
                        <td><c:out value='${product.amount}'/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <br>
        <div>If you want to buy something just call us!</div>
        <div>8800553535</div>
    </div>
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

</div>
</body>
</html>
