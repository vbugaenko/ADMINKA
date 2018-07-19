<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="content">
    <h2>Прошедшие занятия</h2>
    <table>
        <thead>
        <tr>
            <th>Предмет</th>
            <th>Дата</th>
            <th>Начало</th>
            <th>Окончание</th>
            <th>Домашняя работа</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="teacherSubjectsPassed" items="${teacherSubjectsPassed}">
            <tr>
                <td>${teacherSubjectsPassed.sname}
                </td>
                <td>${teacherSubjectsPassed.date}
                </td>
                <td>${teacherSubjectsPassed.timeFrom}
                </td>
                <td>${teacherSubjectsPassed.timeTo}
                </td>
                <td><a href="#">Добавить/Изменить домашнее задание</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <h2>Предстоящие занятия</h2>
    <table>
        <thead>
        <tr>
            <th>Предмет</th>
            <th>Дата</th>
            <th>Начало</th>
            <th>Окончание</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="teacherSubjectsFuture" items="${teacherSubjectsFuture}">
            <tr>
                <td>${teacherSubjectsFuture.sname}
                </td>
                <td>${teacherSubjectsFuture.date}
                </td>
                <td>${teacherSubjectsFuture.timeFrom}
                </td>
                <td>${teacherSubjectsFuture.timeTo}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>