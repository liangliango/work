let driverId = $.cookie("loginId");
let array = ['未结算', '已结算'];
layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;

    refresh(0);

    console.log(driverId);
    // 监听工具条
    table.on('tool(cargoReceiptTool)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        let data = obj.data; //获得当前行数据
        let layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        let tr = obj.tr; //获得当前行 tr 的DOM对象

        if (layEvent === 'detail') {
            layer.open({
                type: 2,
                title: '详细结算信息',
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
        console.log(driverId,array[id]);
        table.render({
            elem: '#cargoReceiptTable' + (id + 1),
            height: 'full-170',
            url: nginx_url + '/driver/findDriverClearByDriverIdAndState/'+driverId+'/'+array[id],
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
                {field: 'carriageId', title: '货运合同编号', align: 'center'},
                {field: 'money', title: '收入', align: "center"},
                {fixed: 'right', title: "操作", align: "center", toolbar: '#barDemo' + (id + 1), width: 200}
            ]]
        });
    }

});
