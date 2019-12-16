
//图片格式化
// v:当前格子对应的数据(路径)
function imgFormat(v) {
    return `<image src="${v}" style="width: 50px;height: 50px;" />`;
}

$(function () {

    //常用的元素都先在这里获取到
    //页数据表格
    var datagrid = $("#datagrid");
    //高级查询表单
    var searchForm = $("#searchForm");
    //编辑弹出框
    var editDialog = $("#editDialog");
    //编辑表单
    var editForm = $("#editForm");

    //事件
    $("*[data-method]").on("click",function () {
        //1.拿到当前对应的方法名
        var methodName = $(this).data("method");
        //2.调用方法
        itsource[methodName]();
    })

    itsource ={
        //添加按钮执行(弹出)
        add(){
            //展示数据
            $("*[shower]").show();
            //把修改时不需要操作的东西启用
            $("*[shower]>td>input").textbox("enable");
            //弹出对话框,固定居中
            editDialog.dialog("open").dialog("center");
            //清空表单中的数据
            editForm.form("clear");
        },
        update(){
            //获取到选中行，如果没有选中，给出提示，后面代码不在执行
            //1.获取选中的行
            var row = datagrid.datagrid("getSelected");
            //2.如果这一行不存在(给出提示，后台代码不再执行)
            if(!row){
                $.messager.alert('提示','哎，选都没选，傻啊！',"warning");
                return;
            }
            //把修改时不需要操作的东西隐藏起来
            $("*[shower]").hide();
            //把修改时不需要操作的东西禁用
            $("*[shower]>td>input").textbox("disable");

            //弹出对话框,固定居中
            editDialog.dialog("open").dialog("center");
            //清空表单中的数据
            editForm.form("clear");
            //进行数据回显
            editForm.form("load",row);


        },
        //保存数据
        save(){
            //1.准备相应的路径
            var url = "/permission/save";
            //2.获取隐藏域的id(如果id有值,就修改路径)
            var permissionId = $("#permissionId").val();
            if(permissionId){
                url = "/permission/update?_cmd=update";
            }
            editForm.form('submit', {
                //路径
                url:url,
                //提交前执行的代码
                onSubmit: function(){
                    // do some check
                    // return false to prevent submit;
                    return $(this).form('validate');
                },
                //成功后的处理
                //后台会返回一个结果:{success:true/false,msg:xxx}
                // Easyui这里没有直接帮你把json字符串转成json对象
                success:function(data){
                    // {"success":true,"msg":null}
                    var result = JSON.parse(data);
                    //如果成功,刷新数据
                    if(result.success){
                        //5.刷新页面
                        datagrid.datagrid("reload");
                    }else{
                        //给出错误提示
                        $.messager.alert('提示',`删除失败，原因是:${result.msg}`,"error");
                    }
                    itsource.close();
                }
            });
        },
        delete(){
            //1.获取选中的行
            var row = datagrid.datagrid("getSelected");
            //2.如果这一行不存在(给出提示，后台代码不再执行)
            if(!row){
                $.messager.alert('提示','请选中一行再来删除，好嘛！',"warning");
                return;
            }
            //3.让用户确定是否删除
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    //4.通过Ajax请求进行删除
                    // 参数一:请求路径   参数二:请求参数   参数三:回调
                    // result:是后端返回的结果
                    $.get("/permission/delete",{id:row.id},function (result) {
                        if(result.success){
                            //5.刷新页面
                            datagrid.datagrid("reload");
                        }else{
                            //给出错误提示
                            $.messager.alert('提示',`删除失败，原因是:${result.msg}`,"error");
                        }
                    })
                }
            });

        },
        //高级查询功能
        search(){
            //1.拿到查询的值
            var params = searchForm.serializeObject();
            //2.进行查询
            datagrid.datagrid("load",params);
        },
        //窗口关闭方法
        close(){
            editDialog.dialog("close");
        }
    };


})