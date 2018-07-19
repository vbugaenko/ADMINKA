<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="sidebar">
    <div class="open_close"></div>
    <sec:authorize access="hasRole('ROLE_STUDENT')">
    <a class="dashboard" href="/student/dashboard">Расписание </a>
    <a class="personal" href="/student/personal">Личная информация</a>
    <a class="marks" href="/student/marks">Успеваемость</a>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_TEACHER')">
        <a href="/teacher/timetable">Расписание</a>
        <a href="/teacher/statistic">Проверка ДЗ</a>
        <a href="/">Выйти в меню</a>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_HEADTEACHER')">
        <a class="personal" href="/hteacher/hteacher_info">Личная информация</a>
        <a class="groups" href="/hteacher/groups">Редактировать группы</a>
        <a class="dashboard" href="/hteacher/timetable_crud">Редактировать расписание</a>
        <a class="subjects" href="/hteacher/subjects_crud">Редактировать предметы</a>
    </sec:authorize>

</div>