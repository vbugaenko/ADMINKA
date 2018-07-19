<input type="submit" name="sortBy" value="
<c:choose>
<c:when test="${requestScope.usersSortBy eq 'AGE_tU'}">Age+</c:when>
<c:when test="${requestScope.usersSortBy eq 'AGE_tL'}">Age-</c:when>
<c:otherwise>Age</c:otherwise>
</c:choose>
" style="width: 100%;" />