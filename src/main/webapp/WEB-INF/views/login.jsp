<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path;
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
        src="<%=basePath%>/static/js/jquery-1.11.3.js"></script>
<head>
    <title>Title</title>
</head>
<body>
错误信息：
<h4 id="error"></h4>
<form>
    <p>
        账号：<input type="text" name="username" id="username" />
    </p>
    <p>
        密码：<input type="text" name="password" id="password"/>
    </p>
    <p>
        <input type="button" id="ajaxLogin" value="登录" />
    </p>
</form>
</body>
<script>
    var username = $("#username").val();
    var password = $("#password").val();
    $("#ajaxLogin").click(function () {
       $.post("/ajaxLogin",{
           "username" : username,
           "password" : password
       },function (result) {
           if(result.status){
               location.href = "/index";
           } else {
                $("#error").html(result.message);
           }
       });
    });
</script>
</html>
