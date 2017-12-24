<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sorry</title>
    <style>
        <%@include file="/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body class="p-3 mb-2 bg-info text-white">
<%--------------------------HEADER--%>
<%@include file="header.jspf" %>
<%---------------------------content--%>
<h1>YOU ARE BANNED!</h1>
<form action="/" method="post">
    <input class="btn btn-success btn-lg btn-block" type="submit" value="Return to welcome page"/>
</form>
<%-------------FOOTER----------------%>
<%@include file="footer.jspf" %>
</body>
</html>
