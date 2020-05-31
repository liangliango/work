layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;
    let array = ['未出合同', '未到', '已到','已结算'];

    refresh(0);

    // 监听工具条
    table.on('tool(cargoReceiptTool)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        let data = obj.data; //获得当前行数据
        let layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        let tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'del') { //删除
            layer.confirm('是否删除', function (index) {
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
                $.ajax({
                    type: "delete",
                    url: nginx_url + "/carriage/deleteCarriageByCarriageId/" + data.carriageId,
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
                title: '运输合同修改',
                content: ['modifyDetail.html?carriageId=' + data.carriageId],
                area: ['85%', '85%'],
                shadeClose: true,
                move: false,
                end: function () {
                    table.reload('cargoReceiptTable1', {
                        url: nginx_url + '/carriage/findAllCarriageByState/' + array[0]
                    })
                }
            });
        } else if (layEvent === 'detail') {
            layer.open({
                type: 2,
                title: '运输合同详细信息',
                content: ['showDetail.html?carriageId=' + data.carriageId],
                area: ['85%', '85%'],
                shadeClose: true,
                move: false,
            });
        }else if (layEvent === 'clear') {
            layer.open({
                type: 2,
                title: '运输合同详细信息',
                content: ['showDetail.html?carriageId=' + data.carriageId],
                area: ['85%', '85%'],
                shadeClose: true,
                move: false,
            });
        }

    });

    element.on('tab(demo)', function (data) {

        refresh(data.index);

        // 监听工具条
    });

    function refresh(id) {
        if (id!==4){
            table.render({
                elem: '#cargoReceiptTable' + (id + 1),
                height: 'full-170',
                url: nginx_url + '/carriage/findAllCarriageByState/' + array[id],
                datatype:"json",
                limit: 10,
                limits: [10],
                request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'limit' //每页数据量的参数名，默认：limit
                },
                response: {
                    statusName: 'code', //数据状态的字段名称，默认：code
                    statusCode: 200, //成功的状态码，默认：0
                    msgName: 'msg', //状态信息的字段名称，默认：msgz
                    countName: 'count', //数据总数的字段名称，默认：count
                    dataName: 'data' //数据列表的字段名称，默认：data
                },
                page: true //开启分页
                , cellMinWidth: 60
                , cols: [[
                    {title: 'ID', fixed: 'left', type: 'numbers', align: 'center'},
                    {field: 'carriageId', title: '运输合同编号', align: 'center'},
                    {field: 'state', title: '状态', align: "center"},
                    {field: 'reciverPhone', title: '收货联系人', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
                ]]
            });
        }else {
            table.render({
                elem: '#cargoReceiptTable' + (id + 1),
                height: 'full-170',

                url: nginx_url + '/carriage/findAllCarriage',

                limit: 10,
                limits: [10],
                request: {
                    pageName: 'pageNum' //页码的参数名称，默认：page
                    , limitName: 'limit' //每页数据量的参数名，默认：limit
                },
                response: {
                    statusName: 'code', //数据状态的字段名称，默认：code
                    statusCode: 200, //成功的状态码，默认：0
                    msgName: 'msg', //状态信息的字段名称，默认：msgz
                    countName: 'count', //数据总数的字段名称，默认：count
                    dataName: 'data' //数据列表的字段名称，默认：data
                },
                page: true //开启分页
                , cellMinWidth: 60
                , cols: [[
                    {title: 'ID', fixed: 'left', type: 'numbers', align: 'center'},
                    {field: 'carriageId', title: '运输合同编号', align: 'center'},
                    {field: 'state', title: '状态', align: "center"},
                    {field: 'receivePhone', title: '收货联系人', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
                ]]
            });
        }

    }

});
