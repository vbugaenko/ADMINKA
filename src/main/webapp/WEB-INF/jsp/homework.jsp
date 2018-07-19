
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="content">

    <h2>Домашнее задание от ${homeWorkInfo.date} </h2>
    <h3>Предмет: ${subjectName}</h3>
    ${resultMsg.equals("done")? "<div class='msg success'>Операция выполнена успешно!</div>": ""}

    <h4>Формулировка:</h4>
    <p>${homeWorkInfo.homework}</p>

    <h4>Ответ:</h4>

    <p>
        <c:choose>
        <c:when test="${answer==null}">
            Нет загруженных ответов.
        </c:when>
        <c:otherwise>
            ${answer}

        </c:otherwise>
    </c:choose>
    </p>

<c:choose>
    <c:when test="${fileUrl!=null}">
    <span>Ссылка на github: ${fileUrl}</span>
    </c:when>
</c:choose>


<br><hr><br>
    ${resultMsg.equals("emptyFields")? "<div class='msg error'>Заполните поля формы!</div>": ""}
    <h4>Загрузить/изменить дз:</h4>
    <form action="${pageContext.request.contextPath}/student/homework?timetableId=${timetableId}&subjectId=${subjectId}"
          method="post">
        <textarea name="answer" placeholder="Мой Ответ"></textarea>
        <input type="text" name="fileUrl" placeholder="Ссылка на github" value="">
        <input type="hidden" name="subjectId" value="${subjectId}">
        <input type="hidden" name="timetableId" value="${timetableId}">
        <input type="submit" value="Сохранить">
    </form>
    <div class="clear"></div>
<%@ include file="footer.jsp" %>