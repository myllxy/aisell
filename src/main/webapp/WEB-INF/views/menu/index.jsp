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
    <%@include file="/WEB-INF/views/head.jsp"%>
    <%--引入当前模块对应的js--%>
    <script src="${pageContext.request.contextPath}/js/model/menu.js"></script>
</head>
<body>

<%--头部的工具条--%>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <%--高级查询搜索框--%>
    <form id="searchForm" method="post">
        名称: <input name="name" class="easyui-textbox" style="width:80px">
        <a href="#"  data-method="search"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--数据表格(展示当前模块数据)--%>
<table id="datagrid" class="easyui-datagrid"
       data-options="url:'/menu/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">编码</th>
        <th data-options="field:'name',width:100">名称</th>
    </tr>
    </thead>
</table>


<%--添加与修改的弹出窗口--%>
<div id="editDialog" class="easyui-dialog" title="数据管理"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:'#editButtons'">
    <form id="editForm" method="post">
        <input id="menuId" type="hidden" name="id" />
        <table cellpadding="5">
            <tr>
                <td>名称:</td>
                <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
            </tr>
        </table>
    </form>
    <div id="editButtons">
        <a href="#" data-method="save" class="easyui-linkbutton c1">保存</a>
        <a href="#" data-method="close" class="easyui-linkbutton c5">关闭</a>
    </div>
</div>

</body>
</html>
