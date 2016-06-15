<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: uilong
  Date: 2016/5/28
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.4.min.js"></script>

    <%
        session.setAttribute("currentPage","bookInfo");
        String s1= (String) session.getAttribute("previous");
        String s2= (String) session.getAttribute("currentPage");
        if((s1==null)||(!s1.equals(s2))){
            session.removeAttribute("books");
            session.setAttribute("previous","bookInfo");
            session.setAttribute("currentPage","bookInfo");
        }else{
            session.setAttribute("previous","bookInfo");
            session.setAttribute("currentPage","bookInfo");
        }
    %>

    <script>
        $(document).ready(function () {
            $('#inputISBN').focus();

            /**
             * 输入ISBN通过 ajax 请求查找图书信息
             */
            $('#inputISBN').change(function () {

                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/books-queryBookInfoByISBN.action",
                    data:{
                        ISBN:$('#inputISBN').val().trim()
                    },
                    dataType:"json",
                    success:function (data) {
                        var d = eval("(" + data  + ")");
                        alert(d.photo);
//                        alert(data);
//                        attr("readonly","readonly") 设置为只读
                        $('#bookName').val(d.bookName).attr("readonly","readonly");
                        $('#press').val(d.press).attr("readonly","readonly");
                        $('#writer').val(d.writer).attr("readonly","readonly");
                        $('#content').val(d.content);
                        $('#price').val(d.price).attr("readonly","readonly");
                        $('#volumeOfConpies').val(d.volumeOfConpies);
                        $('#bookStock').val(d.bookStock);
                        $('#image').attr("src", "${pageContext.request.contextPath}/uploadphoto/" + d.photo);
                        $('#bookType').val(d.bookType).attr("readonly","readonly");

                    },
                    error:function () {
                        alert("该ISBN不存在！请新书入库.....");
                        $('#bookName').val("").removeAttr("readonly");
                        $('#press').val("").removeAttr("readonly");
                        $('#writer').val("").removeAttr("readonly");
                        $('#content').val("");
                        $('#price').val("").removeAttr("readonly");
                        $('#volumeOfConpies').val("");
                        $('#bookStock').val("");
                        $('#photo').val("");
                        $('#bookType').val("1").removeAttr("readonly");
                    }
                });
            });

        });
    </script>

</head>

<body>

<%--<s:debug/>--%>

<div style="text-align: center; margin-top: 10px">
    <p style="font-family: 微软雅黑; font-size: 18px; color: #FF5500; font-weight: bold">图书信息</p>
</div>

<s:form theme="simple" method="POST" enctype="multipart/form-data" name="form">
<div style="width: 100%; margin: 0 auto">
    <div id="showInfo1">
            <p><span>ISBN</span><s:textfield name="bookInfo.ISBN" id="inputISBN"/></p>
            <p><span>书名</span><s:textfield name="bookInfo.bookName" id="bookName"/></p>
            <p><span>出版社</span><s:textfield name="bookInfo.press" id="press"/></p>
            <p><span>作译者</span><s:textfield name="bookInfo.writer" id="writer"/></p>
            <p><span>内容摘要</span><s:textarea name="bookInfo.content" id="content"/></p>
    </div>

    <div id="showInfo2">
        <p><span>价格</span><s:textfield name="bookInfo.price" id="price"/></p>
        <p><span>复本量</span><s:textfield name="bookInfo.volumeOfConpies" id="volumeOfConpies"/></p>
        <p><span>库存量</span><s:textfield name="bookInfo.bookStock" id="bookStock"/></p>
        <p><span>图书封面</span><s:file name="photo" id="photo"/></p>
        <p><span>图书类型</span>
            <s:select list="# {1:'小说类', 2:'计算机类', 3:'美术类', 4:'科技类', 5:'文学类'}" name="bookInfo.bookType" listKey="key"
            listValue="value" id="bookType"></s:select>
        </p>
    </div>
    <div style="background-color: #1C75BC; width: 200px; height: 200px; float: left">
        <img src="${pageContext.request.contextPath}/images/admin.png" id="image" style="width: 100%; height: 100%">
    </div>
</div>

<div id="showInfo3" style="text-align: center; margin-top: 50px; float: left">
    <s:submit value="图书追加" onclick="form.action='books-addBook.action'; form.submit(); "/>
    <br/><br/>
    <s:submit value="图书删除" onclick="form.action='books-deleteBook.action'; form.submit(); "/>
    <br/><br/>
    <s:submit value="图书修改" onclick="form.action='books-updateBook.action'; form.submit(); "/>
    <br/><br/>
</div>
</s:form>

${requestScope.errortype}
<s:fielderror/>

</body>
</html>
