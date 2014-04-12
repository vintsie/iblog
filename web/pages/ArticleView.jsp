<%@ page import="org.vint.iblog.common.bean.nor.CBNArticle" %>
<%@ page import="com.vint.iblog.service.interfaces.ArticleSV" %>
<%@ page import="org.vintsie.jcobweb.proxy.ServiceFactory" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-3-10
  Time: 下午4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <%@include file="/common/CommonHead.jsp" %>
    <%

        String hCode = request.getParameter("hCode");
        ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
        CBNArticle article = articleSV.getArticle(hCode);
    %>
</head>
<body>
<div class="wrapper">

    <jsp:include page="/pages/aHeader.jsp">
        <jsp:param name="title" value="<%=article.getTitle()%>" />
    </jsp:include>

    <div class="content">
        <%=article.getContent()%>
    </div>
</div>
</body>
</html>
