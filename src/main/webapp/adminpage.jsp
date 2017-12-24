<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin page</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
<%@include file="header.jspf" %>
<%---------------------------content--%>

<div style="font-size:25pt">All Users</div>
<div>
    <table class="table table-striped">
        <tr>
            <th>Login</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value='${user.login}'/></td>
                <td><c:out value='${user.status}'/></td>
                <td>
                    <form action="adminpage" method="post">
                        <input type="hidden" name="userId" value="${user.id}">
                        <input class="btn btn-warning" type="submit" value="Change status">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<form action="/" method="post">
    <input class="btn btn-primary btn-lg btn-block" type="submit" value="Return to welcome page"/>
</form>
<form action="price_list" method="get">
    <input class="btn btn-primary btn-lg btn-block" type="submit" value="Go to the market"/>
</form>
<br>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
