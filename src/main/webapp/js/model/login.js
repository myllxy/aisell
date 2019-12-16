//登录 输入信息后向后台发送ajax请求
function submitForm() {
    $('#loginForm').form('submit', {
        url:"/login",//点击登录跳转后台post请求login页面
        onSubmit: function(){
            var isValid = $(this).form('validate');
            if (!isValid){
                $.messager.progress('close');// 如果表单是无效的则隐藏进度条
            }
            return isValid;	// 返回false终止表单提交
        },
        success:function(data){
            $.messager.progress('close');// 如果提交成功则隐藏进度条
            var result = JSON.parse(data);
            console.debug(result)
            if (result.success){
                //成功跳转首页
                window.location.href="/index";
            }else{
                $.messager.alert("错误提示",result.msg, "error");
            }

        }
    });
}
//清除表单
function resetForm() {
    $("#loginForm").form("clear");
}