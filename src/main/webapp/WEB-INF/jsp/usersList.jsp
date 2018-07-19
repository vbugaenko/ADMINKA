<%@ page    contentType="text/html;charset=UTF-8" language="java"           %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"              %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn"    %>
<%@ include file="header.jsp"                                               %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"             %>

<form action="${pageContext.request.contextPath}/admin/usersList" method="get">
    <input type="hidden" id="idForDelete" name="idForDelete"/>
    <input type="hidden" id="idForUpdate" name="idForUpdate"/>
    <input type="hidden" id="page"        name="page"/>
    <input type="hidden" id="editID"      name="editID" value="${editID}"/>
    <input type="hidden" id="activateID"  name="activateID"/>

        <div class="usersBlocks" >
            <table>
                <tr class="tableHeader">
                <td width="30 " >del                                                          </td>
                <td width="45 " >ID                                                           </td>
                <td width="60 " ><%@ include file="users/selectRole.jsp"                  %>  </td>
                <td width="200" ><%@ include file="users/buttons/sortByNameButton.jsp"    %>  </td>
                <td width="200" ><%@ include file="users/buttons/sortByAddressButton.jsp" %>  </td>
                <td width="110" ><%@ include file="users/buttons/sortByDateButton.jsp"    %>  </td>
                <td width="30 " ><%@ include file="users/buttons/filterAButton.jsp"       %>  </td>
                <td width="190" >e-mail                                                       </td>
                <td width="40 " ><%@ include file="users/buttons/sortByAgeButton.jsp"     %>  </td>
                <td width="90 " >Phone                                                        </td>
                <td width="30 " class="whiteBG">                                              </td>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="loopStatus" begin="${beginInt}" end="${endInt}">
                     <tr style="
                            <c:if test="${!user.getEnabled()}">color: silver;</c:if>
                            background-color: ${loopStatus.index % 2 == 0 ? '#F7F8E0;/>' : '#D8D8D8;/>'}">
                        <td align="left"  class="whiteBG">   <%@ include file="users/buttons/deleteButton.jsp" %>  </td>
                        <td align="right"                >   ${user.getID()}                                       </td>
                        <td align="left"                 >   ${user.getRole().getShortname()}                      </td>
                        <td align="left"                 >   ${fn:substring(user.getName(),0, 26)}                 </td>
                        <td align="left"                 >   ${fn:substring(user.getCity(), 0, 26)}                </td>
                        <td align="right"                >   <%@ include file="users/registrationDate.jsp" %>      </td>
                        <td align="center"               >   <%@ include file="users/checkbox.jsp" %>              </td>
                        <td align="left"                 >   ${fn:substring(user.getEmail(),0, 25)}                </td>
                        <td align="center"               >   ${user.getAgeYears()}                                 </td>
                        <td align="left"                 >   ${user.getPhone()}                                    </td>
                        <td align="right" class="whiteBG">   <%@ include file="users/buttons/updateButton.jsp" %>  </td>
                    </tr>
                    <tr>
                        <td colspan="11">
                            <c:if test="${user.getID() eq editID}">
                            <%@ include file="users/userCard.jsp" %>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="usersBlocks" >
            Total: <b>${total}</b> users shown as ${usersRole}, sorted by ${sortBy}, with filter ${filter}, page ${page}
        </div>

        <div class="usersBlocks" >
            <%@ include file="users/pages.jsp" %>
        </div>

</form>

<br/>&nbsp;

<%@ include file="footer.jsp" %>


