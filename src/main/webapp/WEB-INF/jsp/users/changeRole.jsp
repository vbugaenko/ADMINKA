<select name="editRole">
<option <c:if test="${user.getRole().getShortname() eq 'ADMIN'}"    >  selected    </c:if> >ADMIN</option>
<option <c:if test="${user.getRole().getShortname() eq 'TEACHER'}"  >  selected    </c:if> >TEACHER</option>
<option <c:if test="${user.getRole().getShortname() eq 'STUDENT'}"  >  selected    </c:if> >STUDENT</option>
<option <c:if test="${user.getRole().getShortname() eq 'H.TEACHER'}">  selected    </c:if> >H.TEACHER</option>
</select>
