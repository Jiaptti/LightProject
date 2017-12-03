<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/3
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript"
            src="<%=basePath%>/static/js/jquery-1.11.3.js"></script>
    <title>Index</title>
</head>
<body>
helloJsp
<input type="button" id="logout" value="退出登录" />
</body>
<script type="text/javascript">
    $("#logout").click(function(){
        location.href="/logout";
    });
</script>
</html>
