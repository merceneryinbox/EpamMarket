<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница входа в систему.</title>
</head>
<body>
<h1>
    Здесь будет форма для входа в систему (логин/пароль)
</h1>
<form action="price_list_ru" method="post">
    <input type="submit" value="Войти в систему"/>
</form>
<br><br>
<h1>
    Если вы еще не зарегистрированы:
</h1>
<form action="sign_up_ru" method="post">
    <input type="submit" value="Регистрация"/>
</form>
<br><br>
<h1>
    На данный момент нет возможности определить роль пользователя, так что кнопка перехода в панель админа здесь.
</h1>
<form action="admin_page_ru" method="post">
    <input type="submit" value="Если вы администратор, то вы перейдете сюда"/>
</form>
<br><br>
<form action="/sign_in" method="post">
    <input type="submit" value="Перейти на английскую версию магазина."/>
</form>
</body>
</html>
