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
        密码：<input type="password" name="password" id="password"/>
    </p>
    <P><input type="checkbox" name="rememberMe"  id="rememberMe" />记住我</P>
    <p>
        验证码：<input type="text" name="vcode" id="vcode"/>
    </p>
    <p>
        <img src="/getGifCode">
    </p>
    <p>
        <input type="button" id="ajaxLogin" onclick="login();"  value="登录" />
    </p>
</form>
</body>
<script>
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        var vcode = $("#vcode").val();
        var rememberMe =$('#rememberMe').is(':checked');
        $.ajax({
            type : "POST",
            data : {
                "username" : username,
                "password" : password,
                "vcode" : vcode,
                "rememberMe" : rememberMe
            },
            dataType : "json",
            url : "<%=basePath%>/ajaxLogin",
            success : function(result) {
                if (result.status != 200) {
                    $("#error").html(result.message);
                } else {
                    location.href = "/index";
                }
            }
        });
    }
</script>
</html>
