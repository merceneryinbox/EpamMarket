<%--
  Created by IntelliJ IDEA.
  User: norri
  Date: 22.12.2017
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
    <%@ attribute name="cell1" required="true" type="java.lang.String" description="This login is present yet" %>
    <%@ attribute name="cell2" required="false" type="java.lang.String" description="Please, type another one" %>

    <table>
        <tr>
            <td id = "cell1">${cell1}</td>
            <td id = "cell2">${cell2}</td>
        </tr>
    </table>

