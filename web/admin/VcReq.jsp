<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.vint.iblog.common.bean.config.StaticData" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.vint.iblog.common.StaticDataManager" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.vint.iblog.common.constant.StaticTypeConst" %>
<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-4-27
  Time: 下午11:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/CommonHead.jsp"%>

<%
    String token = request.getParameter("token");
    if (StringUtils.isEmpty(token)) {
        throw new JspException("鉴权失败");
    }
    Set data = StaticDataManager.getStaticData(StaticTypeConst.TOKEN);
    if(null == data || data.isEmpty()){
        throw new JspException("鉴权失败");
    }
    Iterator iterator = data.iterator();
    StaticData sd = (StaticData)iterator.next();
    if(!sd.getDataValue().equals(token)){
        throw new JspException("鉴权失败");
    }
%>
<html>
<head>
    <title></title>
    <script src="<%=request.getContextPath()%>/statics/js/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/statics/js/admin/vc_req.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/css/admin.css">
</head>
<body>
    <div class="wrapper">
        <table>
            <tr>
                <td class="L">
                    <div class="f_n">加载GitHub内容</div>
                </td>
                <td class="R">
                    <table>
                        <tr>
                            <td class="td_input_title">
                                <div class="input_title">owner</div>
                            </td>
                            <td class="td_input">
                                <label><input id ="owner" type="text" placeholder="Type owner here."></label>
                            </td>
                        </tr>

                        <tr>
                            <td class="td_input_title">
                                <div class="input_title">Repo</div>
                            </td>
                            <td class="td_input">
                                <label><input id="repo" type="text" placeholder="type repo here."></label>
                            </td>
                        </tr>

                        <tr>
                            <td class="td_input_title">
                                <div class="input_title">Path</div>
                            </td>
                            <td class="td_input">
                                <label><input id="path" type="text" placeholder="type file path here."></label>
                            </td>
                        </tr>
                    </table>
                    <div class="btn btn-middle" id="submit_gh"><span>提&nbsp;&nbsp;交</span></div>
                </td>
            </tr>

        </table>
    </div>
</body>
<script language="JavaScript">
</script>
</html>
