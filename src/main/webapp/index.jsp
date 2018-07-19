<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="WEB-INF/jsp/header.jsp" %>

<div class="main_info small_block">
    <h1>SATURN</h1>
    <h4>Система ведения успеваемости студентов</h4>
    <div class="login">
        <p>Добро пожаловать!</p>
        <sec:authorize access="hasRole('ROLE_STUDENT')">
            <a class="btn" href="/student/personal">Личная информация</a></br>
            <a class="btn" href="/student/dashboard">Расписание</a></br>
            <a class="btn" href="/student/marks">Успеваемость</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_TEACHER')">
            <a class="btn" href="/teacher/timetable">Расписание занятий</a></br>
            <a class="btn" href="/teacher/statistic">Проверка ДЗ</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_HEADTEACHER')">
            <a class="btn" href="/hteacher/hteacher_info">Личная информация</a>
            <a class="btn" href="/hteacher/groups">Редактировать группы</a>
            <a class="btn" href="/hteacher/timetable_crud">Редактировать расписание</a>
            <a class="btn" href="/hteacher/subjects_crud">Редактировать предметы</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a class="btn" href="/admin/usersList">Список пользователей</a></br>
        </sec:authorize>
    </div>
</div>

<div class="clear"></div>
<%@ include file="WEB-INF/jsp/footer.jsp" %>
