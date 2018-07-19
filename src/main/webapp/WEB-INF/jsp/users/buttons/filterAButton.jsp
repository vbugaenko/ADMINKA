<input type="submit" name="filter" value="
<c:choose>
<c:when test="${requestScope.filter eq 'NONE'}">N</c:when>
<c:when test="${requestScope.filter eq 'ACTIVE'}">A</c:when>
<c:when test="${requestScope.filter eq 'DISABLED'}">D</c:when>
</c:choose>
" style="width: 100%;" />