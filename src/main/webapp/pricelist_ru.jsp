<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог товаров.</title>
</head>
<body>
<h1>
    Здесь будет располагаться каталог товаров.
</h1>
<br><br>

<form action="/ru" method="post">
    <input type="submit" value="Вернуться на стартовую страницу"/>
</form>

<br><br>
<h1>
    Кнопки ниже будут видны для пользователей, не зашедших в систему.
</h1>
<form action="sign_in_ru" method="post">
    <input type="submit" value="Войти в систему."/>
</form>
<form action="sign_up_ru" method="post">
    <input type="submit" value="Регистрация"/>
</form>

<br><br>

<h1>
    This button will apear only if you are logged.
</h1>
<form action="cart_ru" method="post">
    <input type="submit" value="Перейти в корзину покупок"/>
</form>
<br><br>
<form action="/price_list" method="post">
    <input type="submit" value="Перейти на английскую версию магазина."/>
</form>

</body>
</html>
