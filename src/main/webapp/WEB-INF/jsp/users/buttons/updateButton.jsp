<c:if test="${user.getID() ne editID}">
    <button onclick="
            document.getElementById('idForUpdate').value = '${user.getID()}';
            document.getElementById('page').value = '${page}';
            " style="padding: 0px;">
        <img src="http://icons.iconarchive.com/icons/custom-icon-design/office/256/edit-icon.png" width="15">
    </button>
</c:if>