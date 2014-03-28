<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-2-20
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
    String title = request.getParameter("title");
    String subTitle = request.getParameter("subTitle");
    if(StringUtils.isEmpty(subTitle)){
        subTitle = "Code For Fun &amp; Life";
    }
%>
<div class="header">

    <div class="a_title">
        <h2><%=title%></h2>
        <%--<h3><%=subTitle%></h3>--%>
    </div><!-- end title -->

    <div class="contact">

<%--        <table>
            <tr>
                <td>Twitter</td>
                <td> | </td>
                <td><a href="http://twitter.com/jandougherty">@jandougherty</a></td>
            </tr>
            <tr>
                <td>Website</td>
                <td> | </td>
                <td><a href="http://yoursite.com">My Portfolio</a></td>
            </tr>
            <tr>
                <td>Email</td>
                <td> | </td>
                <td><a href="mailto:jan@janswebdev.com">jan@janswebdev.com</a></td>
            </tr>
            <tr>
                <td>Phone</td>
                <td> | </td>
                <td>(555)555-5555</td>
            </tr>
        </table>--%>

    </div><!-- end contact -->
    <div style="clear: both"></div>

</div><!-- end header -->
