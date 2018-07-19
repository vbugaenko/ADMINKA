<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>


<div class="content">
    <h3>Фильтр</h3>
    <form action="${pageContext.request.contextPath}/hteacher/timetable_crud" method="post">
        <table>
            <tr>
                <td>
                    <select name="subject_name">
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.id}">${subject.subjectName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" value="" name="date" class="date" placeholder="дата"></td>
                <td>
                    <select name="time">
                        <c:forEach var="interval" items="${intervals}">
                            <option value="${interval.id}">${interval.time_from}-${interval.time_to}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="group">
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.group_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>

    <h3>Редактировать расписание</h3>
    <form id="timetable">
        <div id="del_rez"></div>

        <table>
            <thead>
            <tr>
                <th>Предмет</th>
                <th>Дата</th>
                <th>Время</th>
                <th>Группа</th>
                <th>удалить</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lesson" items="${lessons}">
                <tr>
                    <td>${lessons.subjectName}"</td>
                    <td>${lessons.date}"</td>
                    <td>${lessons.time}"</td>
                    <td>${lessons.time}"</td>
                    <td><a data-timetable-id="${lessons.id}" href="#" class="btn delete">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <hr>
    <h3>Добавить урок в расписание</h3>

    ${rezMsg.equals("done")? "<div class='msg success'>Операция выполнена успешно!</div>":""}
    ${rezMsg.equals("error")? "<div class='msg error'>Возникла проблема! Повторите действие</div>":""}

    <form id="add_lesson">
        <div id="add_rez"></div>
        <table>
            <tr>
                <td>
                    <select name="subject_name">
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.id}">${subject.subjectName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" value="" name="date" class="date" placeholder="дата"></td>
                <td>
                    <select name="time">
                        <c:forEach var="interval" items="${intervals}">
                            <option value="${interval.id}">${interval.time_from}-${interval.time_to}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="group">
                        <c:forEach var="group" items="${groups}">
                            <option value="${group.id}">${group.group_name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="submit" value="Создать"></td>
            </tr>
        </table>
    </form>


</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>