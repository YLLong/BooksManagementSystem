<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/29
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>根据借阅人ID查询借阅情况</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/returnBook.css">


    <%
        session.setAttribute("currentPage","queryStuID");
        String s1= (String) session.getAttribute("previous");
        String s2= (String) session.getAttribute("currentPage");
        if((s1==null)||(!s1.equals(s2))){
            session.removeAttribute("books");
            session.setAttribute("previous","queryStuID");
            session.setAttribute("currentPage","queryStuID");
        }else{
            session.setAttribute("previous","queryStuID");
            session.setAttribute("currentPage","queryStuID");
        }
    %>

</head>
<body>

<div id="bookid">
    <p>借阅人ID<input type="text" name="bookID" placeholder="请输入借阅人ID"/></p>
</div>

<div>
    <table border="1" cellpadding="10" cellspacing="0" align="center">
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <tr>
            <td>图书条形码</td>
            <td>书名</td>
            <td>借阅日期</td>
            <td>应还日期</td>
            <td>是否超期</td>
        </tr>
        <tr>

        </tr>
    </table>
</div>

</body>
</html>
