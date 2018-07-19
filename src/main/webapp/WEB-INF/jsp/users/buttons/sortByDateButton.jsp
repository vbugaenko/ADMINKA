<input type="submit" name="sortBy" value="
<c:choose>
<c:when test="${requestScope.usersSortBy eq 'REGDATE_tU'}">Date+</c:when>
<c:when test="${requestScope.usersSortBy eq 'REGDATE_tL'}">Date-</c:when>
<c:otherwise>Date</c:otherwise>
</c:choose>
" style="width: 100%;" />