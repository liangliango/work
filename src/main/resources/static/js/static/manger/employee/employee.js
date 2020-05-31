layui.use(['layer', 'form', 'element', 'laydate', 'jquery', 'table'], function () {
    var form1 = layui.form;
    let element = layui.element,
        $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        laydate = layui.laydate,
        table = layui.table;

    laydate.render({
        elem: '#birthday',
        type: 'date',
        theme: '#393D49'
    });



    form.on('submit(addEmp)', function () {
        $.ajax({
            type: 'post',
            url: nginx_url + '/manger/addEmployee',
            data: $("#employeeForm").serialize(),
            dataType: 'json',
            success: function (result) {
                console.log(result);
                if (result === 'SUCCESS') {
                    layer.msg('员工信息添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('员工信息添加失败', {
                        time: 800,
                        icon: 1
                    });
                }
            }
        });
        return false;
    });

    element.on('tab(driverFilter)', function (data) {
        if (data.index === 1) {
            table.render({
                elem: '#driverTable',
                height: 'full-170',
                url: nginx_url + '/manger/findAllEmployeeByPage', //数据接口
                limit: 10,
                limits: [10],
                request: {
                    pageName: 'pageNum', //页码的参数名称，默认：page
                    limitName: 'limit' //每页数据量的参数名，默认：limit
                },
                response: {
                    statusName: 'code', //数据状态的字段名称，默认：code
                    statusCode: 200, //成功的状态码，默认：0
                    msgName: 'msg', //状态信息的字段名称，默认：msg
                    countName: 'count', //数据总数的字段名称，默认：count
                    dataName: 'data' //数据列表的字段名称，默认：data
                },
                page: true, //开启分页
                cellMinWidth: 80,
                cols: [[
                    {title: 'id', fixed: 'left', sort: true, type: 'numbers', align: 'center'},
                    {field: 'employeeId', title: '员工编号', align: 'center',sort: true},
                    {field: 'employeeName', title: '员工姓名', align: 'center'},
                    {field: 'phone', title: '电话', align: 'center'},
                    {field: 'gender', title: '性别', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo', width: 200}
                ]]
            });

            // 监听工具条
            table.on('tool(driverTool)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                let data = obj.data; //获得当前行数据
                let layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                let tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'del') { //删除
                    layer.confirm('是否删除', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        //向服务端发送删除指令
                        $.ajax({
                            type: "DELETE",
                            url: nginx_url + "/manger/deleteEmployeeByEmployeeId/" + data.employeeId,
                            success: function (result) {
                                console.log(result);
                            }
                        });
                        layer.msg('删除成功', {
                            time: 800
                        })
                    });

                } else if (layEvent === 'edit') { //编辑
                    layer.open({
                        type: 2,
                        title: '员工 - ' + data.employeeId + '信息修改',
                        content: ['employeeModify.html?employeeId=' + data.employeeId],
                        area: ['95%', '95%'],
                        shadeClose: true,
                        move: false,
                        end: function () {
                            table.reload('driverTable', {
                                url: nginx_url + '/manger/findAllEmployeeByPage'
                            })
                        }
                    });
                } else if (layEvent === 'detail') {
                    layer.open({
                        type: 2,
                        title: '员工 - ' + data.employeeId + ' 信息详情',
                        content: ['employeeDetail.html?employeeId=' + data.employeeId, 'no'],
                        area: ['95%', '95%'],
                        shadeClose: true,
                        move: false
                    });
                }
            });
        }
    });
});