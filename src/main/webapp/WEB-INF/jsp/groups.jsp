<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>


<div class="content">
    <h2>Управление группами</h2>
    <form id="groups">
        <div id="rez"></div>

        <table>
            <thead>
            <tr>
                <th>Название группы</th>
                <th>Редактировать</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td><input type="text" name="group_name" value="${group.groupName}"/>
                    </td>
                    <td><a data-group-id="${group.id}" href="#" class="btn update">Изменить</a>
                    </td>
                    <td><a data-group-id="${group.id}" href="#" class="btn delete">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <hr>
    <h3>Добавить группу</h3>

    ${resultMsg.equals("done")? "<div class='msg success'>Операция выполнена успешно!</div>":""}
    ${resultMsg.equals("error")? "<div class='msg error'>Возникла проблема! Повторите действие</div>":""}

    <form action="${pageContext.request.contextPath}/hteacher/groups" method="post">
        <table>
            <tr>
                <td><input type="text" value="" name="group_name" placeholder="Название группы"></td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>


</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>