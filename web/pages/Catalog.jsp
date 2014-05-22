<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.vint.iblog.common.constant.GlobalVerbs" %>
<%@ page import="com.vint.iblog.common.cache.ArticleCatalogCacheLoader" %>
<%@ page import="java.util.List" %>
<%@ page import="org.vint.iblog.common.util.TimeUtil" %>
<%@ page import="com.vint.iblog.service.interfaces.ArticleSV" %>
<%@ page import="org.vintsie.jcobweb.proxy.ServiceFactory" %>
<%@ page import="org.vint.iblog.common.util.CommonUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-5-20
  Time: 下午3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String catalog = request.getParameter("catalog");
    if (StringUtils.isEmpty(catalog)) {
        catalog = GlobalVerbs.DEFAULT_CATALOG;
    }
    ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
    List<ArticleCatalogCacheLoader.ArticleSummary> ass = articleSV.getCachedArticleList(catalog);
%>
<html>
<head>
    <%@ include file="/common/CommonHead.jsp" %>
    <title><%=catalog.toUpperCase()%></title>

</head>
<body>

<div class="wrapper">
    <%@ include file="Header.jsp" %>
    <ul class="posts">
        <%
            if (!ass.isEmpty()) {
                for (ArticleCatalogCacheLoader.ArticleSummary as : ass) {
        %>
        <li>
            <a href="/posts/<%=catalog + '/' + as.getHexCode()%>"><%=CommonUtil.getNoMdTitle(as.getTitle())%></a>
            <span class="date"><%=TimeUtil.getDate(as.getDate())%></span>
        </li>
        <%
                }
            }
        %>
    </ul>
    <%@include file="Footer.jsp"%>
</div>
</body>
</html>
