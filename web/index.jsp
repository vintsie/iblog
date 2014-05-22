<%@ page import="com.vint.iblog.common.cache.ArticleCatalogCacheLoader" %>
<%@ page import="org.vint.iblog.common.util.TimeUtil" %>
<%@ page import="org.vint.iblog.common.util.CommonUtil" %>
<%@ page import="com.vint.iblog.service.interfaces.ArticleSV" %>
<%@ page import="org.vintsie.jcobweb.proxy.ServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.regex.Pattern" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-2-10
  Time: 下午2:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pageNum = request.getParameter("p");
    int pn = 0;
    try{
        pn = Integer.parseInt(pageNum);
    }catch (Exception e){

    }
    ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
    List<ArticleCatalogCacheLoader.ArticleSummary> programLists = articleSV.getCachedArticleList(pn, null);
%>
<html>
<head>
    <%@include file="common/CommonHead.jsp" %>
    <title>True and False means everything</title>
</head>
<body>
<div class="wrapper">
    <%@include file="pages/Header.jsp" %>
    <div class="home">
        <ul class="posts">
            <%
                if (null != programLists && programLists.size() > 0) {
                    for (Object obj : programLists) {
                        ArticleCatalogCacheLoader.ArticleSummary as = (ArticleCatalogCacheLoader.ArticleSummary) obj;
            %>
            <li>
                <a href="/posts/<%=as.getRepoInfo().split(Pattern.quote("^"))[2] + '/' + as.getHexCode()%>">
                    <%=CommonUtil.getNoMdTitle(as.getTitle())%>
                </a>
                <span class="date"><%=TimeUtil.getDate(as.getDate())%></span>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </div>
    <%@include file="pages/Footer.jsp"%>
</div>
</body>
</html>
