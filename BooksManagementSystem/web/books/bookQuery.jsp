<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/28
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书查询</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>

    <script type="text/javascript">
        function myQueryBooks() {
            var location = "${pageContext.request.contextPath}/page.action?bookName=" + $('.Bquery input').val();
            window.location = location;
        }
    </script>

    <%
        session.setAttribute("currentPage","queryBooks");
        String s1 = (String) session.getAttribute("previous");
        String s2 = (String) session.getAttribute("currentPage");
        if((s1 == null) || (!s1.equals(s2))){
            session.removeAttribute("books");
            session.setAttribute("previous","queryBooks");
            session.setAttribute("currentPage","queryBooks");
        }else{
            session.setAttribute("previous","queryBooks");
            session.setAttribute("currentPage","queryBooks");
        }
    %>

    <script>
        $(document).ready(function () {
            $('#inputBookName').focus();
        });
    </script>

</head>
<body>

<div class="Bquery">
    <span>检索图书</span>
    <input type="text" name="bookName" placeholder="请输入书名" id="inputBookName" />
    <a><img src="${pageContext.request.contextPath}/images/search.gif" onclick="myQueryBooks()"></a>
</div>
<s:debug/>
<div style="width: 100%; height: 200px;">
<table class="default" width="100%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <col width="10%">
    <tr class="title">
        <td>ISBN</td>
        <td>书名</td>
        <td>出版社</td>
        <td>作译者</td>
        <td>库存量</td>
    </tr>

    <!-- 遍历开始 -->
    <s:iterator value="#session.books" var="book">
        <tr>
            <td><s:property value="#book.ISBN"/></td>
            <td><s:property value="#book.bookName"/></td>
            <td><s:property value="#book.press"/></td>
            <td><s:property value="#book.writer"/></td>
            <td><s:property value="#book.bookStock"/></td>
        </tr>
    </s:iterator>
    <!-- 遍历结束 -->
</table>
</div>

<div class="page">
    <ul>
        第<s:property value="pageNum" default="0"/>页 | 共<s:property value="totalpage" default="0"/>页
        <s:url action="page" id="fristPage">
            <s:param name="pageNum">1</s:param>
        </s:url>
        <s:a href="%{fristPage}">首页</s:a>

        <s:url action="page" id="prePage">
            <s:param name="pageNum">
                <s:property value="pageNum-1"/>
            </s:param>
        </s:url>
        <s:a href="%{prePage}">上一页</s:a>

        <s:url action="page" id="nextPage">
            <s:param name="pageNum">
                <s:property value="pageNum+1"/>
            </s:param>
        </s:url>
        <s:a href="%{nextPage}">下一页</s:a>

        <s:url action="page" id="lastPage">
            <s:param name="pageNum">
                <s:property value="totalpage"/>
            </s:param>
        </s:url>
        <s:a href="%{lastPage}">末页</s:a>
    </ul>
</div>

</body>
</html>
