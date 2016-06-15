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
    <meta charset="UTF-8">
    <title>主界面</title>
    <link href="${pageContext.request.contextPath}/css/welcome.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="welcome">
    <p><img src="${pageContext.request.contextPath}/images/logo.jpg"/></p>
    <p class="head">欢迎同学使用图书管理系统</p>
    <p>
        <input type="button" value="个人信息" class="StuButton" onclick="javascript:location.href='${pageContext.request.contextPath}/'"/>
        <input type="button" value="图书查询" onclick="javascript:location.href='${pageContext.request.contextPath}/books/bookQuery.jsp'" class="StuButton"/>
        <input type="button" value="当前借阅" onclick="javascript:location.href='${pageContext.request.contextPath}/student-currentBorrow.action'" class="StuButton"/>
    </p>
</div>
</body>
</html>

