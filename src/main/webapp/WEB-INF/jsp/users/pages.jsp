<c:forEach var="user" items="${users}"  step="10">
    <c:set var="countB" value="${countB+1}"/>
    <button onclick="document.getElementById('page').value = '${countB}';">${countB}</button>
</c:forEach>
