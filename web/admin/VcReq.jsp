<%--
  Created by IntelliJ IDEA.
  User: Vin
  Date: 14-4-27
  Time: 下午11:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="<%=request.getContextPath()%>/statics/js/jquery-1.11.1.min.js"></script>
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
                                <label><input type="text" placeholder="Type owner here."></label>
                            </td>
                        </tr>

                        <tr>
                            <td class="td_input_title">
                                <div class="input_title">Repo</div>
                            </td>
                            <td class="td_input">
                                <label><input type="text" placeholder="type repo here."></label>
                            </td>
                        </tr>

                        <tr>
                            <td class="td_input_title">
                                <div class="input_title">Path</div>
                            </td>
                            <td class="td_input">
                                <label><input type="text" placeholder="type file path here."></label>
                            </td>
                        </tr>
                    </table>
                    <div class="btn btn-middle"><span>Do it!</span></div>
                </td>
            </tr>

        </table>
    </div>
</body>
</html>
