<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="userCard">
    <div align="left" style="display: inline-block;">
        <img src="${user.getPhoto()}" width="160"/>
        <br/>
            <br/>
            <%@ include file="changeRole.jsp" %>
        </p>
    </div>
    <div align="left" style="display: inline-block; width: 650px; vertical-align: top;">
        <label>Full Address: <input type="text" value="${user.getFullAddress()}" disabled/></label>
        <br/>
        <label>Info: <input type="text" name="editInfo" value="${user.getInfo()}"/></label>
        <br/>
        <label>History: <textarea height="200" style="font-size:10px" disabled>
            <%@ include file="history.jsp" %></textarea></label>
    </div>
    <div style="display: inline-block; vertical-align: bottom; float:right;">
        <button type="submit" onclick="document.getElementById('page').value = '${page}';">Ok</button>
        &nbsp;
        <button onclick="document.getElementById('page').value = '${page}';">Cancel</button>
    </div>
</div>
