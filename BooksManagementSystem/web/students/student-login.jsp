<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/6/1
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>学生登录</title>
    <link href="${pageContext.request.contextPath}/css/Slogin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="Slogin">
    <p><img src="${pageContext.request.contextPath}/images/logo.jpg"/></p>
    <p class="oneTitle">欢迎使用图书管理系统</p>
    <p class="grayFont">V 1.0</p>
    <form action="student-login" method="post">
    <p><input type="text" name="stuID" class="textBox" placeholder="请输入用户名"/></p>
    <p><input type="password" name="student.password" class="textBox" placeholder="请输入密码"/></p>
    <p>
        <input type="submit" value="登录" class="submitButton"/>
        <input type="reset" value="重置" class="resetButton"/>
    </p>
    </form>
    <div style="margin: 0 auto; text-align: center">
        <s:fielderror/>
    </div>
    <p style="width: 270px;margin:0 auto;text-align: right;color: #808080;">忘记密码</p>
    <p style="color: #808080;">(c) Copyright 2016 Andy. All Rights Reserved. </p>
</div>
</body>
</html>

