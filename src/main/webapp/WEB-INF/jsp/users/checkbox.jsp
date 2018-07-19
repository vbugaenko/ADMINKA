<button type="submit" style="border: 0; ${loopStatus.index % 2 == 0 ? 'background-color: #F7F8E0;/>' : 'background-color: #D8D8D8;/>'}" onclick="document.getElementById('activateID').value = '${user.getID()}';">
<c:if test="${user.getEnabled()}">    <img src="https://d30y9cdsu7xlg0.cloudfront.net/png/9014-200.png"                width="12" height="12"> </c:if>
<c:if test="${!user.getEnabled()}">   <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9FHwUL0aVSEqDHB62nAniEUPx08jAQ6iEc7r8wjrThYz7Ufhf" width="11" height="11"> </c:if>
</button>

