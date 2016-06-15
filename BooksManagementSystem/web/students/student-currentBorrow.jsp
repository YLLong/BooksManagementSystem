<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/6/1
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>当前借阅</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>

    <%
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String curdate=f.format(new Date());
        request.setAttribute("today", curdate);
    %>

</head>

<body>

<s:debug/>
<div style="width: 100%; height: 200px;">
    <table class="default">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <col width="10%">
        <tr class="title">
            <td>学号</td>
            <td>姓名</td>
            <td>图书条形码</td>
            <td>图书名</td>
            <td>借阅日期</td>
            <td>应还日期</td>
            <td>是否过期</td>
        </tr>

        <s:if test="#session.borrowInfo == null">
            你当前没有借阅任何书籍！
        </s:if>
        <s:else>
            <!-- 遍历开始 -->
            <s:iterator value="#session.borrowInfo" var="info">
                <tr>
                    <td><s:property value="#info.stuID"/></td>
                    <td><s:property value="#info.stuName"/></td>
                    <td><s:property value="#info.bookID"/></td>
                    <td><s:property value="#info.bookName"/></td>
                    <td><s:date name="#info.checkoutDate" format="yyyy-MM-dd"/></td>
                    <td><s:date name="#info.sReturnDate" format="yyyy-MM-dd"/></td>
                    <td>
                        <%--判断是否超期--%>
                        <s:if test="#info.sReturnDate.compareTo(#request.today) < 0">
                            已超期
                        </s:if>
                        <s:else>
                            未超期
                        </s:else>
                    </td>
                </tr>
            </s:iterator>
            <!-- 遍历结束 -->
        </s:else>
    </table>
</div>

</body>
</html>
