<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <title>源码智销系统</title>
    <script>
        // 检查自己是否是顶级页面
        if (top !== window) {// 如果不是顶级
            //把子页面的地址，赋值给顶级页面显示
            window.top.location.href = window.location.href;
        }

        function submitForm() {
            //直接提交
            // 对咱们来说，这是一个Ajax请求
            $('#loginForm').form('submit', {
                url: "/login",
                onSubmit: function () {
                    return $(this).form('validate');
                },
                //访问成功后的功能
                success: function (data) {
                    //{"success":false,"msg":"用户名错误！"}
                    //Object { success: false, msg: "用户名或者密码错误！" }
                    var result = JSON.parse(data);
                    if (result.success) {
                        //访问成功 -> 跳到主页面(Js怎么跳转)
                        // location:有很多当前页面的位置信息
                        // JS的BOM【浏览器对象模型】部分
                        window.location.href = "/main";
                    } else {
                        //访问失败 -> 给出提示
                        $.messager.alert('错误', result.msg);
                    }
                }
            });
        }

        $(document.documentElement).on("keyup", function (event) {
            //console.debug(event.keyCode);
            var keyCode = event.keyCode;
            console.debug(keyCode);
            if (keyCode === 13) { // 捕获回车
                submitForm(); // 提交表单
            }
        });
    </script>
</head>
<body>


<div align="center" style="margin-top: 100px;">
    <div class="easyui-panel" title="智销系统用户登陆" style="width: 350px; height: 240px;">
        <form id="loginForm" class="easyui-form" method="post">
            <table align="center" style="margin-top: 15px;">
                <tr height="20">
                    <td>用户名:</td>
                </tr>
                <tr height="10">
                    <td><input name="username" class="easyui-validatebox" required="true" value="admin"/></td>
                </tr>
                <tr height="20">
                    <td>密&emsp;码:</td>
                </tr>
                <tr height="10">
                    <td><input name="password" type="password" class="easyui-validatebox" required="true"
                               value="admin"/></td>
                </tr>
                <tr height="40">
                    <td align="center"><a href="javascript:;" class="easyui-linkbutton" onclick="submitForm();">登陆</a>
                        <a
                                href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>