<%--
  Created by IntelliJ IDEA.
  User: LXY
  Date: 19/12/20
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/head.jsp" %>
</head>
<body>
<!-- 上传必需是:post，enctype="multipart/form-data"-->
<form action="${pageContext.request.contextPath}/import/employeeXlsx_v" method="post" enctype="multipart/form-data">
    <input class="easyui-filebox" name="empFile" style="width:80%"
           data-options="prompt:'选择一个文件...',buttonText: '选择文件'"/>
    <button class="easyui-linkbutton">导入</button>
</form>
</body>
</html>
