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
            <select name="uRole">
                <option value="3" ${(uRole == 3)?"selected":""}>Студент  </option>
                <option value="1" ${(uRole == 1)?"selected":""}>Админ    </option>
                <option value="2" ${(uRole == 2)?"selected":""}>Учитель  </option>
                <option value="4" ${(uRole == 4)?"selected":""}>Завуч    </option>
            </select>
        </div>
        <div id="uLogin"     ><label>Логин            </label><input type="text"     value="${uLogin}"      name="uLogin"     required></div>
        <div id="uName"      ><label>ФИО              </label><input type="text"     value="${uName}"       name="uName"      required></div>
        <div id="uAge"       ><label>Дата рождения    </label><input type="date"     value="${uAge}"        name="uAge"       style="margin-left: 50px;"></div>
        <div id="uCity"      ><label>Город            </label><input type="text"     value="${uCity}"       name="uCity"      requqqired></div>
        <div id="uAdress"    ><label>Адрес            </label><input type="text"     value="${uAdress}"     name="uAdress"    requqqired></div>
        <div id="uPhone"     ><label>Телефон          </label><input type="text"     value="${uPhone}"      name="uPhone"     class="phone" required></div>
        <div id="uEmail"     ><label>Email            </label><input type="email"    value="${uEmail}"      name="uEmail"     required></div>
        <div id="uPhoto"     ><label>Ссылка на фото   </label><input type="text"     value="${uPhoto}"      name="uPhoto"     required></div>
        <div id="uPassword"  ><label>Пароль           </label><input type="password" value="${uPassword}"   name="uPassword"  required></div>
        <div id="uPassRepeat"><label>Повторите Пароль </label><input type="password" value="${uPassRepeat}" name="uPassRepeat"required></div>
        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>