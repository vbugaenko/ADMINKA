<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<div class="small_block">
    <h2>Регистрация нового пользователя</h2>
    <c:choose>
        <c:when test="${paramsChecking.equals('')}"> </c:when>
        <c:otherwise><div class='msg error'> <c:out value="${paramsChecking}"/></div></c:otherwise>
    </c:choose>


    <form id="registration" action="${pageContext.request.contextPath}/registration" method="post">
        <label>Выберите тип пользователя</label>
        <div class="styledsel">
            <select name="userRole">
                <option value="1" ${(userRole == 1)?"selected":""}>Админ
                </option>
                <option value="3" ${(userRole == 3)?"selected":""}>Студент
                </option>
                <option value="2" ${(userRole == 2)?"selected":""}>
                    Преподаватель
                </option>
                <option value="4" ${(userRole == 4)?"selected":""}>Завуч
                </option>
            </select>
        </div>
        <div id="userLogin">
            <label>Логин</label>
            <input type="text" value="${userLogin}" name="userLogin" required>
        </div>
        <div id="userName">
            <label>ФИО</label>
            <input type="text" value="${userName}" name="userName" required>
        </div>
        <div id="userAge">
            <label>Дата рождения</label>
            <input type="date" value="${userAge}" name="userAge" style="margin-left: 50px;">
        </div>
        <div id="userCity">
            <label>Город</label>
            <input type="text" value="${userCity}" name="userCity" requqqired>
        </div>
        <div id="userAdress">
            <label>Адрес</label>
            <input type="text" value="${userAdress}" name="userAdress" requqqired>
        </div>

        <div id="userPhone">
            <label>Телефон</label>
            <input type="text" value="${userPhone}" class="phone" name="userPhone" required>
        </div>
        <div id="userEmail">
            <label>Email</label>
            <input type="email" value="${userEmail}" name="userEmail" required>
        </div>
        <div id="userPhoto">
            <label>Ссылка на фото</label>
            <input type="text" value="${userPhoto}" name="userPhoto" required>
        </div>
        <div id="userPassword">
            <label>Пароль</label>
            <input type="password" value="${userPassword}" name="userPassword" required>
        </div>
        <div id="userPasswordRepeat">
            <label>Повторите Пароль</label>
            <input type="password" value="${userPasswordRepeat}" name="userPasswordRepeat"
                   required>
        </div>
        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>
