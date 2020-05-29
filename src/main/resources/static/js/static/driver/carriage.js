layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;
    let array1 = ['未到', '已到'];

    refresh(0);
    let login = $.cookie("loginId");
    // 监听工具条
    table.on('tool(cargoReceiptTool)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        let data = obj.data; //获得当前行数据
        let layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        let tr = obj.tr; //获得当前行 tr 的DOM对象

         if (layEvent === 'detail') {
            layer.open({
                type: 2,
                title: '运输合同详细信息',
                content: ['ddetail.html?carriageId=' + data.carriageId],
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
        if (id===0 || id===1){
            table.render({
                elem: '#cargoReceiptTable' + (id + 1),
                height: 'full-170',
                url: nginx_url + '/driver/findCarriageByDriverIdAndState',
                data:{"driverId":login,"state":array1[id]},
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
                    {field: 'carriageId', title: '货运合同编号', align: 'center'},
                    {field: 'state', title: '状态', align: "center"},
                    {field: 'receivePhone', title: '收货联系人电话', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
                ]]
            });
        }
        if (id===2){
            table.render({
                elem: '#cargoReceiptTable' + (id + 1),
                height: 'full-170',
                url: nginx_url + '/driver/findCarriageByDriverIdAndIsClear',
                data:{"driverId":login,"state":1},
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
                    {field: 'carriageId', title: '货运合同编号', align: 'center'},
                    {field: 'state', title: '状态', align: "center"},
                    {field: 'receivePhone', title: '收货联系人电话', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
                ]]
            });
        }
        if (id===3){
            table.render({
                elem: '#cargoReceiptTable' + (id + 1),
                height: 'full-170',
                url: nginx_url + '/driver/findCarriageByDriverId',
                data:{"driverId":login},
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
                    {field: 'carriageId', title: '货运合同编号', align: 'center'},
                    {field: 'state', title: '状态', align: "center"},
                    {field: 'receivePhone', title: '收货联系人电话', align: 'center'},
                    {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
                ]]
            });
        }
    }

});
