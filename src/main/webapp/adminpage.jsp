<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin page</title>
</head>
<body>
<div style="text-align: center">
    <div style="font-size:25pt">All Users</div>
    <div>
        <table style="table-layout: auto" border="2" align="center" width="600">
            <tr>
                <th>Login</th>
                <th>Status</th>
                <th>Here will be button</th>
            </tr>
            <c:forEach var="user" items="${Users}">
                <tr>
                    <td><c:out value='${user.login}'/></td>
                    <td><c:out value='${user.status}'/></td>
                    <td><c:out value='button will be her'/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<form action="/" method="post">
    <input type="submit" value="Return to welcome page"/>
</form>
<form action="price_list" method="get">
    <input type="submit" value="Go to the market"/>
</form>

</body>
</html>
