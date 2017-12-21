<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>編輯</title>
    <link href="<%=contextPath%>/static/css/content-base.css"
          rel="stylesheet"/>
    <link href="<%=contextPath%>/static/lib/bootstrap-Switch/bootstrapSwitch.css"
          rel="stylesheet"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>编辑用户信息</h5>
            <div class="ibox-tools">
                <a class="collapse-link"> <i class="fa fa-chevron-up"></i>
                </a>
            </div>
        </div>
        <div class="ibox-content">
            <form action="${not empty user.id ? '/user/edit' : '/user/save'}" class="form-horizontal" method="post"
                  id="editForm">
                <input hidden="true" id="id" name="id" type="text"
                       value="${user.id}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="NickName">姓名</label>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="nickname" name="nickname"
                               placeholder="姓名" type="text" value="${user.nickname}"
                               data-val="true" data-val-regex="请输入正确的姓名"
                               data-val-regex-pattern="[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*"
                               data-val-required="姓名不能为空"/> <span
                            data-valmsg-for="nickname" data-valmsg-replace="true"
                            class="field-validation-valid"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="LoginName">用户名称</label>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="username" name="username"
                               placeholder="用户名称" type="text" value="${user.username}"
                               data-val-maxlength="账号长度不能超过20个字符"
                               data-val-maxlength-max="20" data-val-required="账号不能为空"/> <span
                            data-valmsg-for="username" data-valmsg-replace="true"
                            class="field-validation-valid"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="Password">密码</label>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="password" name="pswd"
                               placeholder="密码" type="text" value="${user.pswd}"
                               data-val="true" data-val-maxlength="密码长度不能超过20个字符"
                               data-val-minlength="密码长度不能少于5个字符" data-val-minlength-min="5"
                               data-val-maxlength-max="20" data-val-required="密码不能为空"/> <span
                            data-valmsg-for="pswd" data-valmsg-replace="true"
                            class="field-validation-valid"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="Phone">手机号码</label>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" id="phone" name="phone"
                               placeholder="手机号码" type="text" value="${user.phone}"
                               data-val="true" data-val-regex="请输入正确的手机号码"
                               data-val-regex-pattern="^1(3|4|5|7|8)\d{9}$"
                               data-val-required="手机号码不能为空"/> <span
                            data-valmsg-for="phone" data-valmsg-replace="true"
                            class="field-validation-valid"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="RealName">邮箱</label>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" data-val="true"
                               data-val-maxlength="邮箱长度不能超过36个字符" data-val-maxlength-max="36"
                               data-val-minlength="邮箱长度不能少于5个字符" data-val-minlength-min="5"
                               data-val-regex="请输入正确的邮箱地址"
                               data-val-regex-pattern="^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$"
                               data-val-required="邮箱不能为空" id="email" name="email"
                               placeholder="邮箱" type="text" value="${user.email}"/> <span
                            class="field-validation-valid" data-valmsg-for="email"
                            data-valmsg-replace="true"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="RoleId">角色</label>：</label>
                    <div class="col-sm-8">
                        <select id="roleId" name="roleId" data-val="true"
                                data-val-required="请选择">
                            <option value="0">请选择</option>
                            <option value="2" <c:if test="${user.roleId =='3'}">selected="selected"</c:if>>员工</option>
                            <option value="3" <c:if test="${user.roleId =='3'}">selected="selected"</c:if>>维修员</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"><label
                            for="RealName">是否有效</label>：</label>
                    <div class="col-sm-8">
                        <div class="switch switch-mini" checked="true" data-on-label="有效" data-off-label="无效">
                            <input type="checkbox"
                                   <c:if test="${user.status =='1'}">checked="true"</c:if>
                                   name="isEffective" id="isEffective"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-8">
                        <button class="btn btn-info" type="submit" id="btnSave">保存</button>
                        <button class="btn btn-white" type="button" id="back"
                                data-type="url">返回
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<%=contextPath%>/static/js/content/base.js"></script>
<script src="<%=contextPath%>/static/js/content/action.js"></script>
<script src="<%=contextPath%>/static/js/content/jqueryValidator.js"></script>
<script src="<%=contextPath%>/static/lib/bootstrap-Switch/bootstrapSwitch.js"></script>
<script type="text/javascript">
    //返回
    $("#back").bind("click", function () {
        window.location.href = "/user/userPage";
    });
</script>
</body>
</html>

