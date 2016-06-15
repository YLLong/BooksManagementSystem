<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/26
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入成功</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>

    <script>

        <%--图书查询--%>
        function bookquery() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/bookQuery.jsp");
        }

        <%--图书管理--%>
        function showInfo() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/bookInfo.jsp");
        }

        <%--借书--%>
        function borrowBook() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/borrowBook.jsp");
        }

        <%--还书--%>
        function returnBook() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/returnBook.jsp");
        }

        <%--根据ISBN查询图书借出情况--%>
        function queryISBN() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/queryISBN.jsp");
        }

        <%--根据学号查询学生借书情况--%>
        function queryStuID() {
            $('iframe').attr("src", "${pageContext.request.contextPath}/books/queryStuID.jsp");
        }
    </script>

    <%--<%--%>
        <%--if (("queryBooks").equals(session.getAttribute("previous"))) {--%>
            <%--session.removeAttribute("books");--%>
        <%--}--%>
    <%--%>--%>

</head>
<body background="${pageContext.request.contextPath}/images/all_bg.jpg" style="background-size: cover">

<div id="TOP">
    <div id="uiLogo">
    <img src="${pageContext.request.contextPath}/images/admin.png">
    </div>

    <div id="uiTop">
    <img border="1" src="${pageContext.request.contextPath}/images/books.png"/>
    </div>

    <div id="user_info">
        <div id="welcome">欢迎 <font color="blue">${admin.admininfo.adminName}</font> 使用本系统</div>
        <div id="logout"><a href="${pageContext.request.contextPath}/admin-logout.action">安全退出</a></div>
    </div>

    <div id="menu">
        <div id="menu_container">
            <ul id="menu_items">
                <li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>个人信息</a></li>
                <li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a onclick="bookquery()">图书查询</a></li>
                <li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a>借书查询</a>
                    <ul>
                        <li><a onclick="queryISBN()">根据ISBN查询图书借出情况</a></li>
                        <li><a onclick="queryStuID()">根据学号查询学生借书情况</a></li>
                    </ul>
                </li>
                <li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a onclick="borrowBook()">借书</a></li>
                <li class="menu_item" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'"><a onclick="returnBook()">还书</a></li>
                <li class="menu_item" style="border-radius:8px 0 0 8px;border:0px;" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'" onmouseover="this.style.backgroundColor='#77D1F6';this.style.borderRadius='0 8px 8px 0';this.style.fontWeight='bold'"><a onclick="showInfo()">图书管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div>
<div class="showInfo">
    <p>个人信息:</p>
    <p>ID：${admin.admininfo.adminID}</p>
    <p>姓名:${admin.admininfo.adminName}<br/></p>
    <p>性别:${admin.admininfo.gender}<br/></p>
    <p>年龄:${admin.admininfo.age}<br/></p>
    <p>身份证:${admin.admininfo.IDcard}<br/></p>
    <p>电话号码:${admin.admininfo.tele}<br/></p>
    <p>出生日期:<s:date name="admin.admininfo.birth" format="yyyy-MM-dd" nice="false"/><br/></p>
    <p>学历:${admin.admininfo.educated}<br/></p>
    <p>地址:${admin.admininfo.address}<br/></p>
    <p>入职时间:<s:date name="admin.admininfo.hiredate" format="yyyy-MM-dd" nice="false"/><br/></p>
</div>

<div class="UIiframe">
    <iframe frameborder="0" src=""></iframe>
</div>
</div>
<s:debug/>
</body>
</html>
