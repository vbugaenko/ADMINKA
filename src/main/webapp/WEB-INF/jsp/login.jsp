<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%@ include file="header.jsp" %>
<div class="small_block">
    <h2 id="welcome_label">Здравствуйте! Авторизуйтесь, пожалуйста.</h2>
    ${msg.equals("authErr")? " <div class='msg error'>Неверное имя пользователя/пароль</div>" :""}
    ${msg.equals("logout")? " <div class='msg success'>Вы успешно вышли из сиситемы</div>" :""}

    <form action="/j_spring_security_check" method="post">
        <input type="text" value="" name="j_username">
        <input type="password" value="" name="j_password">
        <input type="submit" value="Войти"></br>
        <a class="btn" href="/registration">Зарегистрироваться</a>
    </form>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>
