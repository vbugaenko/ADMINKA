<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>


<div class="content">
    <h2>Личная информация</h2>
    <c:choose>
        <c:when test="${not empty user.photo}">
            <img class="personal_photo" src="${user.photo}">
        </c:when>
    </c:choose>

    <div class="personal_info">
        <table>
            <tr>
                <td>ФИО</td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td>Дата рождения</td>
                <td>${user.age}</td>
            </tr>
            <tr>
                <td>Адрес</td>
                <td>${user.fulladdress}</td>
            </tr>
            <tr>
                <td>Населенный пункт</td>
                <td>${user.city}</td>
            </tr>
            <tr>
                <td>Телефон</td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td>${user.email}</td>
            </tr>

            <tr>
                <td>Группа</td>
                <td>${student.group}</td>
            </tr>
            <tr>
                <td>Логин</td>
                <td>${user.login}</td>
            </tr>
            <tr>
                <td>Инфо</td>
                <td>${user.info} </td>
            </tr>
        </table>
    </div>
    <div class="clear"></div>
    <hr/>
    ${resultMsg.equals("done")? "<div class='msg success'>Операция выполнена успешно!</div>":""}
    ${resultMsg.equals("emptyFields")? "<div class='msg error'>Пароли не совпадпют!</div>":""}

    <form action="${pageContext.request.contextPath}/student/personal" method="post">
        <h2>Сменить пароль</h2>
        <input type="password" value="" name="oldPassword" placeholder="Новый пароль">
        <input type="password" value="" name="newPassword" placeholder="Повторите пароль">
        <input type="submit" value="Изменить">
    </form>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>