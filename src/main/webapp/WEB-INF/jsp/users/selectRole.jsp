<select name="role" onchange="this.form.submit()">
<option <c:if test="${requestScope.usersRole eq 'ALL'}"         > selected    </c:if>>ALL</option>
<option <c:if test="${requestScope.usersRole eq 'ADMIN'}"       > selected    </c:if>>ADMIN</option>
<option <c:if test="${requestScope.usersRole eq 'TEACHER'}"     > selected    </c:if>>TEACHER</option>
<option <c:if test="${requestScope.usersRole eq 'STUDENT'}"     > selected    </c:if>>STUDENT</option>
<option <c:if test="${requestScope.usersRole eq 'HEADTEACHER'}" > selected    </c:if>>H.TEACHER</option>
</select>
