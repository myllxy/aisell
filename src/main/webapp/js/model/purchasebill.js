// 对象的格式化
function objFormat(v) {
    return v ? v.name || v.username : "";
}

// 状态的格式化
function statusFormat(v) {
    if (v === 0) {
        return "<span style='color: red;'>待审核</span>";
    } else if (v === 1) {
        return "<span style='color: green;'>审核通过</span>";
    } else {
        return "<s style='color: grey;'>作废</s>";
    }
}

// 图片的格式化
function imgFormat(v) {
    return `<img src='${v}' style="width: 50px;height: 50px;">`;
}

$(function () {
    // 提取公共属性
    let datagrid = $("#datagrid");
    let searchForm = $("#searchForm");
    let formDialog = $("#formDialog");
    let purchasebillForm = $("#purchasebillForm");
    // $("*[data-method]")是将所有有data-method属性的方法绑定事件
    $("*[data-method]").on("click", function () {
        // 1.拿到当前对应的方法名
        let methodName = $(this).data("method");
        // 2.调用方法：itsource[methodName]拿到对应的方法，加个()就代表执行这个方法
        itsource[methodName]();
    });
    itsource = {
        /* 因为新政和修改打开的是同一个dialog,所以在新增的时候
         * 需要clear整个dialog中的form,而修改不需要额外的clear,
         * 因为修改表单中的数据会被鼠标所选行的数据自动填充(即回显)
         * */
        add() {
            /*let formDialog = $("#formDialog");*/
            formDialog.dialog('open');
            purchasebillForm.form('clear');
            /* 因为修改操作隐藏了部分组件,所以在添加时需要重新显示 */
        },
        /* 无论是修改界面还是新增界面都是使用这个save保存数据
         * 所以我们需要判断当前操作是新增还是修改，然后映射到
         * controller的不同的方法中去
         * */
        save() {
            // 1.准备相应的路径
            let url = "/purchasebill/save";
            // 2.获取隐藏域的id(如果id有值,就修改路径)
            let purchasebillId = $("#purchasebillId").val();
            if (purchasebillId) {
                url = "/purchasebill/update?_cmd=update";
            }
            // call 'submit' method of form plugin to submit the form
            purchasebillForm.form('submit', {
                url: url,
                onSubmit: function () {
                    // do some check
                    // return false to prevent submit;
                    return $(this).form('validate');
                },
                success: function (data) {
                    let result = JSON.parse(data);
                    if (result.success) {
                        //5.刷新页面
                        datagrid.datagrid("reload");
                        formDialog.window('close');
                    } else {
                        //给出错误提示
                        $.messager.alert('提示', `添加失败，原因是:${result.msg}`, "error");
                    }
                }
            });
        },
        update() {
            //1.获取选中的行
            let row = datagrid.datagrid("getSelected");
            //2.如果这一行不存在(给出提示，后台代码不再执行)
            if (!row) {
                $.messager.alert('提示', '请选中一行再来修改，好嘛！', "warning");
                return;
            }
            // 弹出对话框,固定居中
            formDialog.dialog("open").dialog("center");
            purchasebillForm.form('load', row);
            // 完成部门的回显
            purchasebillForm.form('reload');
        },
        delete() {
            //1.获取选中的行
            let row = datagrid.datagrid("getSelected");
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
                    $.get("/purchaseBillItem/delete", {id: row.id}, function (result) {
                        // 前台传来{success:true/false,msg:xxx}
                        /*1.可以这么写*/
                        // for (let i in result) {
                        //     let data = result[i];
                        //     if (i === "success" && data) {
                        //         datagrid.datagrid("reload");
                        //         itsource.close()
                        //     } else {
                        //         //给出错误提示
                        //         $.messager.alert('提示', `删除失败，原因是:${result.msg}`, "error");
                        //     }
                        // }
                        /*2.还可以这么写*/
                        if (result.success) {
                            //5.刷新页面
                            datagrid.datagrid("reload");
                        } else {
                            //给出错误提示
                            $.messager.alert('提示', `删除失败，原因是:${result.msg}`, "error");
                        }
                    })
                }
            });
        },
        //高级查询功能
        search() {
            //1.拿到查询的值
            //获取表单的值序列化成 JSON 对象并返回
            let params = searchForm.serializeObject();
            //2.进行查询
            datagrid.datagrid("load", params);
        },
        //窗口关闭方法
        close() {
            /*方式1.因为dialog是继承自window的所以能谁用它的方法*/
            formDialog.dialog('close');
            /*方式2*/
            // formDialog.window("close");
        }
    };

    /*=================编辑明细====================*/
    var dg = $("#itemGrid"),
        defaultRow = {ID: "", Code: "", Name: "", StandardModel: "", ApplyCount: "", Remark: "", Stocks: ""},
        insertPosition = "bottom";
    var dgInit = function () {
        var getColumns = function () {
            var result = [];
            var normal = [
                {
                    field: 'Code', title: '物资编码', width: 180,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'Name', title: '名称', width: 180,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: false,
                            readonly: false
                        }
                    }
                },
                {
                    field: 'StandardModel', title: '规格型号(只读)', width: 100,
                    editor: {
                        type: "validatebox",
                        options: {
                            required: false,
                            readonly: true
                        }
                    }
                },
                {
                    field: 'ApplyCount', title: '申请数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true
                        }
                    }
                },
                {
                    field: 'Remark', title: '备注', width: 100,
                    editor: {
                        type: "text"
                    }
                },
                {
                    field: 'Stocks', title: '库存数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            readonly: false
                        }
                    }
                }
            ];
            result.push(normal);

            return result;
        };
        var options = {
            idField: "ID",
            rownumbers: true,
            fitColumns: true,
            fit: true,
            border: true,
            singleSelect: true,
            columns: getColumns(),
            toolbar: "#itemButtons",
            //表示开启单元格编辑功能
            enableCellEdit: true
        };

        dg.datagrid(options);

    };

    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }

    var buttonBindEvent = function () {

        $("#btnInsert").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, {ID: $.util.guid()});
            dg.datagrid("insertRow", {index: targetIndex, row: targetRow});
            dg.datagrid("editCell", {index: 0, field: "Code"});
        });

        $("#btnSave").click(function () {
            var rows = dg.datagrid("getRows"), len = rows.length;
            for (var i = 0; i < len; i++) {
                dg.datagrid("endEdit", i);
            }
        });
    };


    dgInit();
    buttonBindEvent();


});