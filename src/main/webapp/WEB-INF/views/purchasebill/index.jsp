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
    <script src="${pageContext.request.contextPath}/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
    <script src="${pageContext.request.contextPath}/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.editors.js"></script>
    <script src="${pageContext.request.contextPath}/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script src="${pageContext.request.contextPath}/js/model/purchasebill.js"></script>
</head>
<body>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <form action="/purchasebill/export" id="searchForm" method="post">
        交易时间：<input class="easyui-datetimebox" name="begindate"
                    data-options="required:true,showSeconds:false" style="width:150px">-
        <input class="easyui-datetimebox" name="enddate"
               data-options="required:true,showSeconds:false" style="width:150px">
        状态：<select id="cc" class="easyui-combobox" name="status" style="width:200px;" data-options="panelHeight:'auto'">
        <option value="">-所有-</option>
        <option value="0">待审核</option>
        <option value="1">通过审核</option>
        <option value="-1">作废</option>
    </select>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <button type="submit" data-method="export" class="easyui-linkbutton" plain="true">导出</button>
        <a href="/import/index" data-method="export" class="easyui-linkbutton" plain="true">导入</a>
    </form>
    <div id="formDialog" class="easyui-dialog" title="My Dialog" style="width:800px"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed: true">
        <form id="purchasebillForm" method="post">
            <table cellpadding="5">
                <tr>
                    <td>交易时间:</td>
                    <td><input id="purchasebillId" type="hidden" name="id"/>
                        <input type="text" class="easyui-datebox" required="required" name="vdate"/></td>
                </tr>
                <tr>
                    <td>供货商:</td>
                    <td><input class="easyui-combobox" name="supplier.id"
                               data-options="valueField:'id',textField:'name',url:'/supplier/list',panelHeight:'auto'"/>
                    </td>
                </tr>
                <tr>
                    <td>采购员:</td>
                    <td><input class="easyui-combobox" name="buyer.id"
                               data-options="valueField:'id',textField:'username',url:'/employee/getSuppers',panelHeight:'auto'"/>
                    </td>
                </tr>
            </table>
            <table id="itemGrid"></table>
            <div id="itemButtons">
                <a id="btnInsert" href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
            </div>
        </form>
        <div id="editButtons">
            <a href="#" class="easyui-linkbutton" data-method="save" data-options="iconAlign:'right'">保存</a>
            <a href="#" class="easyui-linkbutton" data-method="close" data-options="iconAlign:'right'">关闭</a>
        </div>
    </div>
</div>
<table id="datagrid" class="easyui-datagrid"
       data-options="url:'/purchasebill/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">编码</th>
        <%--如果不加formatter:imgFormat显示的就是图片的路径--%>
        <th data-options="field:'vdate',width:100">交易时间</th>
        <th data-options="field:'totalAmount',width:100">总金额</th>
        <th data-options="field:'totalNum',width:100">总数量</th>
        <th data-options="field:'status',width:100,formatter:statusFormat">审核状态</th>
        <th data-options="field:'supplier',width:100,formatter:objFormat">供货商</th>
        <th data-options="field:'inputUser',width:100,formatter:objFormat">录入员</th>
        <th data-options="field:'buyer',width:100,formatter:objFormat">采购员</th>
    </tr>
    </thead>
</table>
</body>
</html>
