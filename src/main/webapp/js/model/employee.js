$(function () {
    //提取公共属性
    const datagrid = $("#datagrid");
    const searchForm = $("#searchForm")
    //$("*[data-method]")是将所有有data-method属性的方法绑定事件
    $("*[data-method]").on("click", function () {
        //1.拿到当前对应的方法名
        const methodName = $(this).data("method");
        //2.调用方法：itsource[methodName]拿到对应的方法，加个()就代表执行这个方法
        itsource[methodName]();
    })
    itsource = {
        add() {
            alert("add");
        },
        update() {
            alert("update");
        },
        delete() {
            //1.获取选中的行
            const row = datagrid.datagrid("getSelected");
            //2.如果这一行不存在(给出提示，后台代码不再执行)
            if (!row) {
                $.messager.alert('提示', '请选中一行再来删除，好嘛！', "warning");
                return;
            }
            //3.让用户确定是否删除
            $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
                if (r) {
                    //4.通过Ajax请求进行删除
                    // result:是后端返回的结果
                    $.get("/employee/delete", {id: row.id}, function (result) {
                        // 前台传来{success:true/false,msg:xxx}
                        /*1.可以这么写*/
                        console.debug(result)
                        for (const i in result) {
                            const data = result[i];
                            if (i === "success" && data) {
                                datagrid.datagrid("reload");
                            } else {
                                //给出错误提示
                                $.messager.alert('提示', `删除失败，原因是:${result[msg]}`, "error");
                            }
                        }
                        /*2.还可以这么写*/
                        // if (result.success) {
                        //     //5.刷新页面
                        //     datagrid.datagrid("reload");
                        // } else {
                        //     //给出错误提示
                        //     $.messager.alert('提示', `删除失败，原因是:${result.msg}`, "error");
                        // }
                    })
                }
            });
        },
        //高级查询功能
        search() {
            //1.拿到查询的值
            //获取表单的值序列化成 JSON 对象并返回
            const params = searchForm.serializeObject();
            //2.进行查询
            datagrid.datagrid("load", params);
        }
    };
})