<input type="submit" name="sortBy" value="
<c:choose>
<c:when test="${requestScope.usersSortBy eq 'ADDRESS_tU'}">Address+</c:when>
<c:when test="${requestScope.usersSortBy eq 'ADDRESS_tL'}">Address-</c:when>
<c:otherwise>Address</c:otherwise>
</c:choose>
" style="width: 100%;" />