<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/29
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借书</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/borrowBook.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar3.js"></script>

    <%
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String curdate=f.format(new Date());
        request.setAttribute("today", curdate);
    %>

    <%
        session.setAttribute("currentPage","borrowBook");
        String s1= (String) session.getAttribute("previous");
        String s2= (String) session.getAttribute("currentPage");
        if((s1==null)||(!s1.equals(s2))){
            session.removeAttribute("books");
            session.setAttribute("previous","borrowBook");
            session.setAttribute("currentPage","borrowBook");
        }else{
            session.setAttribute("previous","borrowBook");
            session.setAttribute("currentPage","borrowBook");
        }
    %>

    <script type="text/javascript">

        $(function () {
            $('#inputStuID').focus();

            /**
             * 输入学号值改变的 ajax 处理
             */
            $('#inputStuID').change(function () {

                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/admin-queryStudentByStuID.action",
                    data:{
                        stuID:$('#inputStuID').val().trim()
                    },
                    dataType:"json",
                    success:function (data) {
                        var d = eval("(" + data  + ")");
                        $('#showStuName').val(d.stuName)
                    },
                    error:function () {
                        alert("该学号不存在！请重新输入.....")
                    }
                });
            });

            /**
             * 图书条形码输入框值改变的 ajax 处理
             */
            $('#inputBookID').change(function () {

                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/admin-queryBookByID.action",
                    data:{
                        bookID:$('#inputBookID').val().trim()
                    },
                    dataType:"json",
                    success:function (data) {
                        var d = eval("(" + data  + ")");
                        $('#showBookName').val(d.bookName)
                    },
                    error:function () {
                        alert("该图书条形码不存在！请重新输入.....")
                        $('#inputStuID').focus();
                    }
                });
            });

        });

    </script>

</head>
<body>

<div id="borrowBook">
    <form action="borrowBook" name="borrowBook">
        <div class="borrowBook1">
            <p><span>学号</span><input type="text" name="borrowBooks.stuID" id="inputStuID"/></p>
            <p><span>图书条形码</span><input type="text" name="borrowBooks.bookID" id="inputBookID" /></p>
            <p><span>借阅日期</span><input type="text" name="borrowBooks.checkoutDate" onclick="new Calendar().show(this);" value="${requestScope.today}"/></p>
            <p><input type="submit" value="确认"/></p>
        </div>
        <div class="borrowBook2">
            <p><span>姓名</span><input type="text" name="borrowBooks.stuName" id="showStuName"/></p>
            <p><span>图书名</span><input type="text" name="borrowBooks.bookName" id="showBookName"/></p>
            <p><span>应还日期</span><input type="text" name="borrowBooks.sReturnDate" onclick="new Calendar().show(this);"/></p>
            <p><input type="reset" value="重置"/></p>
        </div>
    </form>
</div>

${sessionScope.borrow_success}
<s:debug/>
</body>
</html>
