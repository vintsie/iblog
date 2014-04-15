<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-2-10
  Time: 下午2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="common/CommonHead.jsp" %>

    <style>
        .left_banner li{
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="wrapper">
<%--
    <%@include file="pages/Header.jsp" %>
    <div class="content" style="height: 1200px">
        <div class="character">
            <h2>最新的文章</h2>
        </div>
        123
        <div class="character">
            <h2>大家都在看的文章</h2>
        </div>
    </div>
--%>

    <%--<div style="z-index: 99;background-color: gray;width: auto;height:auto;">--%>
        <div style="z-index: 99;width: 80px;height:100%;float: left">

        </div>
        <div style="z-index: 99;float: left;">
            <div style="position: absolute;top:50%; left: 50%">Loading...</div>
        </div>
    </div>
    <%--</div>--%>
</body>
</html>
