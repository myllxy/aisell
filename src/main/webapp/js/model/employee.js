
$(function () {

    //常用的元素都先在这里获取到
    var datagrid = $("#datagrid");
    var searchForm = $("#searchForm")

    //事件
    $("*[data-method]").on("click",function () {
        //1.拿到当前对应的方法名
        var methodName = $(this).data("method");
        //2.调用方法
        itsource[methodName]();
    })

    itsource ={
        add(){
            alert("add");
        },
        update(){alert("update");},
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
                    $.get("/employee/delete",{id:row.id},function (result) {
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
        }
    };


})