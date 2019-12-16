<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <%@include file="/WEB-INF/views/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $('#menuid').tree({
                method: 'get',
                url: 'menuTree.json',
                onClick: function (node) {
                    //添加选项卡
                    var text = node.text;
                    /*
                    * 1.exists标识表明指定的面板是否存在
                    * 2.select标识选中当前tab
                    * */
                    if (node.url && !$("#tabsId").tabs('exists', text)) {
                        //如果这个选项卡已经存在，就应该选中，没有才添加
                        var url = node.url;
                        $('#tabsId').tabs('add', {
                            title: text,
                            content: '<iframe frameborder="0" style="width: 100%;height: 100%" src="' + url + '"></iframe>',
                            closable: true
                        });
                    } else {
                        //选中当前存在的页签
                        $('#tabsId').tabs('select', text);
                    }
                }
            });
        });
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" style="height:80px;background:#ff6827;">
    <div><h1>AiSell智能销售系统</h1></div>
    欢迎<a href="#"><%--<shiro:principal property="username"/>--%></a>登录&emsp;<a href="/logout">注销</a>

</div>
<div id="menuid" data-options="region:'west',split:true" style="width:150px;padding:10px;">west content</div>
<div data-options="region:'east',split:true,collapsed:true" style="width:100px;padding:10px;">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
<div id="tabsId" class="easyui-tabs" data-options="region:'center'"></div>
</body>
</html>
