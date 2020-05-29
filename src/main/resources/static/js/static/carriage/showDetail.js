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
        url: nginx_url + "/carriage/findCarriageByCarriageId/" + carriageId,
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
            if (result.startDate != null && result.startDate !== '') {
                laydate.render({
                    elem: '#startDate',
                    type: 'date',
                    value: new Date(result.startDate)
                    // theme: 'grid'
                });
            }
            if (result.exceptDate != null && result.exceptDate !== '') {
                laydate.render({
                    elem: '#exceptDate',
                    type: 'date',
                    value: new Date(result.exceptDate)
                    // theme: 'grid'
                });
            }
            if (result.arriveDate != null && result.arriveDate !== '') {
                laydate.render({
                    elem: '#arriveDate',
                    type: 'date',
                    value: new Date(result.arriveDate)
                    // theme: 'grid'
                });
            }
        }

    });

});