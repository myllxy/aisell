
// v:当前格子对应的数据(路径)
function permsFormat(v) {
    var ps = "";
    for(var p of v){
        ps += p.name+" ";
    }
    return ps;
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
    //两个grid
    var rolePermsGrid = $("#rolePermsGrid");
    var allPermsGrid = $("#allPermsGrid");

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
            //清空表单中的数据 【只能清空表单元素:文本框,多选,单选,下拉...】
            editForm.form("clear");
            //清空datagrid中的数据
            rolePermsGrid.datagrid("loadData",[]);
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
            //进行数据回显 【只能回显表单元素:文本框,多选,单选,下拉...】
            editForm.form("load",row);
            //回显权限数据
            //复制一个数组
            var perms = [...row.permissions];
            rolePermsGrid.datagrid("loadData",perms);
        },
        //保存数据
        save(){
            //1.准备相应的路径
            var url = "/role/save";
            //2.获取隐藏域的id(如果id有值,就修改路径)
            var roleId = $("#roleId").val();
            if(roleId){
                url = "/role/update?_cmd=update";
            }
            //提交表单中的数据 【只能提交表单元素:文本框,多选,单选,下拉...】
            editForm.form('submit', {
                //路径
                url:url,
                //提交前执行的代码
                //param:空的(我给它加啥，它就会给后台传啥)
                onSubmit: function(param){
                    //1.拿到当前用户的权限
                    var rows = rolePermsGrid.datagrid("getRows");
                    //2.遍历它，拼接格式  permissions[0].id = 1
                    for(var i=0;i<rows.length;i++){
                        var p = rows[i];
                        // param.permissions is undefined
                        param[`permissions[${i}].id`] = p.id;
                    }
                    // 验证
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
                    $.get("/role/delete",{id:row.id},function (result) {
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
        },
        //添加权限
        // index:当前点的第几行 row:点击的行数据
        addPerms(index, row){
            //1.拿到当前角色的所有行数据
            var rows = rolePermsGrid.datagrid("getRows");
            //2.遍历所有行数据(出现重复则给出提示,后面不执行)
            for(var r of rows){
                if(r.id == row.id){
                    $.messager.show({
                        title:'错误',
                        msg:'<h2 style="color: red;">数据已经存在,你聪明一点</h2>',
                        showType:'slide',
                        timeout:2000,
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }
                    });
                    return;
                }
            }

            //把相应的数据追加进去
            rolePermsGrid.datagrid("appendRow",row);
        },
        //删除权限
        removePerms(index,row){
            rolePermsGrid.datagrid("deleteRow",index);
        }
    };


    //创建两个grid
    //1.当前角色拥有的权限Grid
    rolePermsGrid.datagrid({
        fitColumns:true,
        singleSelect:true,
        fit:true,
        onDblClickRow:itsource.removePerms
    })
    //2.所有权限的Grid
    allPermsGrid.datagrid({
        url:'/permission/list',
        fitColumns:true,
        singleSelect:true,
        fit:true,
        onDblClickRow:itsource.addPerms
    })
})