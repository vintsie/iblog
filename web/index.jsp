<%@ page import="com.vint.iblog.common.CacheManager" %>
<%@ page import="com.vint.iblog.common.cache.ArticleCatalogCacheLoader" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="org.vint.iblog.common.util.TimeUtil" %>
<%@ page import="org.vint.iblog.common.bean.nor.CBNGitHubCatalog" %>
<%@ page import="org.vint.iblog.common.util.CommonUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-2-10
  Time: 下午2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<String, Object> cachedCatalogs = CacheManager.getData(ArticleCatalogCacheLoader.class.getName());
    List programLists = (List) cachedCatalogs.get("vintsie^notebook^programming");
    CBNGitHubCatalog catalog = (CBNGitHubCatalog) cachedCatalogs.get("vintsie^notebook^programming^base");
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
                <a href="/posts/<%=catalog.getPath() + '/' + as.getHexCode()%>">
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
