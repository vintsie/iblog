<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-3-10
  Time: 下午3:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true"%>
<html>
<head>
    <%@include file="/common/CommonHead.jsp" %>
</head>
<body>
    <div class="wrapper">
        <%--<%@include file="/pages/Header.jsp" %>--%>
            <%=request.getParameter("errmsg")%>
        <%

        %>

    </div>
</body>
</html>
