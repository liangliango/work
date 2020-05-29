layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    let carriageId = window.location.href.split("=")[1];
    $.ajax({
        type: "get",
        url: nginx_url + "/driver/findDriverClearByCarriageId/",
        data:{"carriageId":carriageId},
        datatype:"json",
        async: false,
        success: function (result) {
            $.each(result, function (i, item) {
                let temp_id = '#' + i;
                $(temp_id).val(item);
            });
            // 日期
            laydate.render({
                elem: '#writeDate',
                type: 'date',
                value: new Date(result.writeDate)
                // theme: 'grid'
            });
            laydate.render({
                elem: '#clearDate',
                type: 'date',
                value: new Date(result.clearDate)
                // theme: 'grid'
            });
        }

    });

});