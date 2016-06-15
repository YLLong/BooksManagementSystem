<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/29
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>还书</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/returnBook.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar3.js"></script>

    <%
        session.setAttribute("currentPage","returnBook");
        String s1= (String) session.getAttribute("previous");
        String s2= (String) session.getAttribute("currentPage");
        if((s1==null)||(!s1.equals(s2))){
            session.removeAttribute("book");
            session.setAttribute("previous","returnBook");
            session.setAttribute("currentPage","returnBook");
        }else{
            session.setAttribute("previous","returnBook");
            session.setAttribute("currentPage","returnBook");
        }
    %>

    <script>
        $(document).ready(function () {
            $('#inputbookID').focus();
        });
    </script>

    <%
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String curdate=f.format(new Date());
        request.setAttribute("today", curdate);
    %>

</head>
<body>

<div id="bookid">
    <form action="books-queryBybooKID" method="post">
    <p>图书条形码<input type="text" name="bookID" placeholder="请输入图书ID" id="inputbookID"/></p>
        <input type="submit" value="查询"/>
    </form>
</div>

<div>
    <table border="1" cellpadding="10" cellspacing="0" align="center">
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <col width="10%" />
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>书名</td>
            <td>借阅日期</td>
            <td>应还日期</td>
            <td>归还日期</td>
            <td>是否超期</td>
        </tr>
        <tr>
            <%--显示借书信息--%>
            <s:iterator value="#session.book" var="book">
            <td><s:property value="#book.stuID"/></td>
            <td><s:property value="#book.stuName"/> </td>
            <td><s:property value="#book.bookName"/></td>
            <td><s:date name="#session.book.checkoutDate" format="yyyy-MM-dd"/></td>
            <td><s:date name="#session.book.sReturnDate" format="yyyy-MM-dd"/></td>
            <td>${requestScope.today}</td>
            <td>
                <%--判断是否超期--%>
                <s:if test="#book.sReturnDate.compareTo(#request.today) < 0">
                    已超期
                    <script>
                        $(document).ready(function () {
                            $('#xujieBT').attr("disable", "disable");
                        });
                    </script>
                </s:if>
                <s:else>
                    未超期
                </s:else>
            </td>
            </s:iterator>
        </tr>
    </table>
</div>

<div style="float: right; margin-right: 200px; margin-top: 150px;">
    <input type="button" value="归还" onclick="javascript:location.href='${pageContext.request.contextPath}/books-returnBook.action'" style="border: 1px solid yellow; border-radius: 5px; font-size: 20px; font-weight: bold;"/>
    <input type="button" value="续借" id="xujieBT" style="border: 1px solid yellow; border-radius: 5px; font-size: 20px; font-weight: bold;"/>
</div>

<s:fielderror/>

</body>
</html>
