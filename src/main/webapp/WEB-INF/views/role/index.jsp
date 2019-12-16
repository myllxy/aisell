<%--
  Created by IntelliJ IDEA.
  User: LXY
  Date: 19/12/12
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <%--引入头部文件--%>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <%--引入当前模块对应的js--%>
    <script src="${pageContext.request.contextPath}/js/model/role.js"></script>
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
        sn: <input name="sn" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>

<%--数据表格(展示当前模块数据)--%>
<table id="datagrid" class="easyui-datagrid"
       data-options="url:'/role/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'sn',width:20">编码</th>
        <th data-options="field:'name',width:20">名称</th>
        <th data-options="field:'permissions',width:100,formatter:permsFormat">权限</th>
    </tr>
    </thead>
</table>


<%--添加与修改的弹出窗口--%>
<div id="formDialog" class="easyui-dialog" title="My Dialog" hidden="hidden"
     style="width:100%;max-width:400px;padding:30px 60px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed: true">
    <form id="roleForm" method="post">
        <%--隐藏域id,用于在后面判断操作是新增还是修改--%>
        <input id="roleId" type="hidden" name="id"/>
        <div style="margin-bottom:10px">
            <input class="easyui-textbox" name="name" style="width:100%"
                   data-options="label:'name:',required:true">
        </div>
        <div style="margin-bottom:10px">
            <%--validType:'email'为什么不是想象中的样子--%>
            <input class="easyui-textbox" name="sn" style="width:100%"
                   data-options="label:'sn:',required:true">
        </div>
    </form>
    <div id="editButtons">
        <a href="#" data-method="save" class="easyui-linkbutton">保存</a>
        <a href="#" data-method="close" class="easyui-linkbutton">关闭</a>
    </div>
</div>
</body>
</html>
