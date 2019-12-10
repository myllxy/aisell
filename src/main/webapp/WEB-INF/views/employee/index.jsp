<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/10
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%--引入头部文件--%>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <%--引入当前模块对应的js--%>
    <script src="${pageContext.request.contextPath}/js/model/employee.js"></script>
</head>
<body>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <form id="searchForm" method="post">
        用户名: <input name="username" class="easyui-textbox" style="width:80px">
        邮件: <input name="email" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<table id="datagrid" class="easyui-datagrid"
       data-options="url:'/employee/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">编码</th>
        <th data-options="field:'username',width:100">名称</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'password',width:100">密码</th>
        <th data-options="field:'email',width:100">邮件</th>
    </tr>
    </thead>
</table>
</body>
</html>
