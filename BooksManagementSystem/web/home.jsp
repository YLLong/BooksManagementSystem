<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/6/1
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>选择登录权限</title>
    <link href="${pageContext.request.contextPath}/css/Choose.css" rel="stylesheet" type="text/css" />
    <%--<script type="text/javascript" src="#{pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>--%>
</head>
<body>
<div id="Choose">
    <p><img src="${pageContext.request.contextPath}/images/logo.jpg"/></p>
    <p class="oneTitle">欢迎使用图书管理系统</p>
    <p><input type="button" value="学生登录"  onclick="javascript:location.href='${pageContext.request.contextPath}/students/student-login.jsp'" class="studentButton"/></p>
    <p><input type="button" value="管理员登录" onclick="javascript:location.href='${pageContext.request.contextPath}/admin/admin-login.jsp'" class="admButton"/></p>
    <p style="color: #808080;">(c) Copyright 2016 Andy. All Rights Reserved. </p>
</div>
</body>
</html>
