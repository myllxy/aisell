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
    <script src="${pageContext.request.contextPath}/js/model/product.js"></script>
</head>
<body>
<div id="toolbar" style="padding:5px;height:auto">
    <div style="margin-bottom:5px">
        <a href="#" data-method="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="#" data-method="update" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" data-method="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <form action="/product/export" id="searchForm" method="post">
        用户名: <input name="username" class="easyui-textbox" style="width:80px">
        颜色: <input name="email" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <button type="submit" data-method="export" class="easyui-linkbutton" plain="true">导出</button>
        <a href="/import/index" data-method="export" class="easyui-linkbutton" plain="true">导入</a>
    </form>
    <div id="formDialog" class="easyui-dialog" title="My Dialog" hidden="hidden"
         style="width:100%;max-width:400px;padding:30px 60px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed: true">
        <form id="productForm" method="post">
            <%--隐藏域id,用于在后面判断操作是新增还是修改--%>
            <input id="productId" type="hidden" name="id"/>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="name" style="width:100%"
                       data-options="label:'Name:',required:true">
            </div>
            <div style="margin-bottom:10px">
                <%--validType:'email'为什么不是想象中的样子--%>
                <input class="easyui-textbox" name="color" style="width:100%"
                       data-options="label:'Color:',required:true">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="pic" style="width:100%"
                       data-options="label:'Pic:',required:true">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="smallPic" style="width:100%"
                       data-options="label:'SmallPic:',required:true">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="costPrice" style="width:100%"
                       data-options="label:'CostPrice:',required:true">
            </div>
            <div style="margin-bottom:10px">
                <input class="easyui-textbox" name="salePrice" style="width:100%"
                       data-options="label:'SmallPic:',required:true">
            </div>
        </form>
        <div id="bb">
            <a href="#" class="easyui-linkbutton" data-method="save" data-options="iconAlign:'right'">保存</a>
            <a href="#" class="easyui-linkbutton" data-method="close" data-options="iconAlign:'right'">关闭</a>
        </div>
    </div>
</div>
<table id="datagrid" class="easyui-datagrid"
       data-options="url:'/product/page',fitColumns:true,singleSelect:true,fit:true,pagination:true,toolbar:'#toolbar'">
    <thead>
    <tr>
        <th data-options="field:'id',width:100">编码</th>
        <%--如果不加formatter:imgFormat显示的就是图片的路径--%>
        <th data-options="field:'name',width:100">名字</th>
        <th data-options="field:'color',width:100">颜色</th>
        <th data-options="field:'pic',width:100,formatter:imgFormat">图片</th>
        <th data-options="field:'smallPic',width:100,formatter:imgFormat">缩略图</th>
        <th data-options="field:'costPrice',width:100">成本</th>
        <th data-options="field:'salePrice',width:100">出售价</th>
        <th data-options="field:'producttype',width:100,formatter:objFormat">producttype</th>
        <th data-options="field:'sdd1',width:100,formatter:objFormat">sdd1</th>
        <th data-options="field:'sdd2',width:100,formatter:objFormat">sdd2</th>
    </tr>
    </thead>
</table>
</body>
</html>
