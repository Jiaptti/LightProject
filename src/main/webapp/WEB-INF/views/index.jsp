<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<% String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
 <head>
  <title>路灯项目</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="<%=contextPath%>/static/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="<%=contextPath%>/static/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="<%=contextPath%>/static/assets/css/main.css" rel="stylesheet" type="text/css" />
 </head>
 <body>
	<div class="header">
		<div class="dl-title">
			<span class="lp-title-port">路灯</span><span class="dl-title-text">系统</span>
		</div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user"><shiro:principal property="nickname"/>  </span> <span class="admin"></span>
			<a href="logout" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-storage">首页</div></li>
			</ul>
		</div>
		<%--<input type="button", id="cors" value="CORS跨域请求"/>--%>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	
  <script type="text/javascript" src="<%=contextPath%>/static/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="<%=contextPath%>/static/lib/layer/1.9.3/layer.js"></script>
  <script type="text/javascript" src="<%=contextPath%>/static/assets/js/bui-min.js"></script>
  <script type="text/javascript" src="<%=contextPath%>/static/assets/js/config-min.js"></script>
  <script type="text/javascript" src="<%=contextPath%>/static/js/barrage.js"></script>
  <script>	
	BUI.use('common/main',function(){
      var config = [{
          id:'system',
          menu:[{
              text:'用户管理',
              items:[
                {id:'yhgl',text:'用户列表',href:'/user/userPage'},
                {id:'qxgl',text:'权限列表',href:'/permission/permissionPage' },
				{id:'jsgl',text:'角色列表',href:'/role/rolePage' },
				{id:'csqxgl',text:'初始权限列表',href:'/permissionInit/permissionInitPage'},
				{id:'zxyhgl',text:'在线用户列表',href:'/user/onlineUserPage'},
				{id:'qxcsym',text:'权限测试页面',href:'/add'},
				{id:'rjk',text:'Redis监控',href:'/redis/redisMonitor'},
				{id:'djk',text:'Druid监控',href:'/druid'}
              ]
          	 },{
              text:'街道管理',
              items:[
                  {id:'jdxx',text:'街道列表',href:'/location/streetPage'},
                  {id:'jdxx',text:'街道信息',href:'/location/streetPage'}
              ]
          	}]
	  	}];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
//	$(function () {
//		$("#cors").click(function () {
//			$.ajax({
//				url:"http://localhost:8080/user/cors",
//				success:function (data) {
//					alert(data);
//                }
//			})
//		});
//    });
  </script>
 </body>
</html>