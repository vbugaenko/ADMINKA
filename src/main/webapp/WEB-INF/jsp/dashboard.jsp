<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="content">
    <h2>Расписание группы ${groupName}</h2>

    <table>
        <thead>
        <tr>
            <th>Предмет</th>
            <th>Дата</th>
            <th>Начало</th>
            <th>Окончание</th>
            <th>Формулировка ДЗ</th>
            <th>Переход</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="timetableLesson" items="${timetableLessons}">
            <tr>
                <td>${timetableLesson.sname}
                </td>
                <td>${timetableLesson.date}
                </td>
                <td>${timetableLesson.timeFrom}
                </td>
                <td>${timetableLesson.timeTo.hours}
                </td>
                <td>${timetableLesson.homework}
                </td>
                <td>
                    <a href="/student/homework?timetableId=${timetableLesson.id}&subjectId=${timetableLesson.subject_id}">
                    Перейти к ДЗ ></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>