<input type="submit" name="sortBy" value="
<c:choose>
<c:when test="${requestScope.usersSortBy eq 'SURNAME_tU'}">Name+</c:when>
<c:when test="${requestScope.usersSortBy eq 'SURNAME_tL'}">Name-</c:when>
<c:otherwise>Name</c:otherwise>
</c:choose>
" style="width: 100%;" />
