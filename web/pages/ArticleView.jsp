<%@ page import="org.vint.iblog.common.bean.nor.CBNArticle" %>
<%@ page import="com.vint.iblog.service.interfaces.ArticleSV" %>
<%@ page import="org.vintsie.jcobweb.proxy.ServiceFactory" %>
<%@ page import="org.vint.iblog.common.util.TimeUtil" %>
<%@ page import="org.vint.iblog.common.util.CommonUtil" %>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/css/markdown.css">
</head>
<body>


<div class="wrapper">
    <%@include file="Header.jsp"%>

    <h2 style="margin-bottom: 5px"><%=CommonUtil.getNoMdTitle(article.getTitle())%></h2>
    <span class="meta"><%=TimeUtil.getDate(article.getCreateDate())%></span>

    <div class="a_content">
        <%=article.getContent()%>
    </div>

    <%@include file="Footer.jsp"%>
</div>
</body>
</html>
