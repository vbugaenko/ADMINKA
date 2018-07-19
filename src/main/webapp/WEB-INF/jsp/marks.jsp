<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>


<div class="content">
    <h2>Успеваемость</h2>
    <table>
        <thead>
        <tr>
            <th>Предмет</th>
            <th>Количество занятий</th>
            <th>Средний балл</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subject" items="${subjectsWithMarks}">
            <tr>
                <td>${subject.sname} </td>
                <td>${subject.subj_counter}</td>
                <td>${subject.mark_summ/subject.subj_counter} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="clear"></div>
<%@ include file="footer.jsp" %>