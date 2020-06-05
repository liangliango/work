layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    let billId = window.location.href.split("=")[1];
    console.log(billId);
    $.ajax({
        type: "get",
        url: nginx_url + "/bill/findWayBillByBillId/" + billId,
        success: function (result) {
            console.log(result);
            $.each(result, function (i, item) {
                let temp_id = '#' + i;
                $(temp_id).val(item);
            });

            // 日期
            laydate.render({
                elem: '#startDate',
                type: 'date',
                value: new Date(result.startDate)
                // theme: 'grid'
            });
            // 日期
            laydate.render({
                elem: '#clearDate',
                type: 'date',
                value: new Date()
                // theme: 'grid'
            });
            laydate.render({
                elem: '#exceptDate',
                type: 'date',
                value: new Date(result.exceptDate)
                // theme: 'grid'
            });
            laydate.render({
                elem: '#writeDate',
                type: 'date',
                value: new Date(result.writeDate)
                // theme: 'grid'
            });
        }
    });

    form.on('submit(modifyGoodsBill)', function () {

        $("#goodsBillForm :input").each(function () {
            $(this).removeAttr("disabled");
        });

        $.ajax({
            type: 'put',
            url: nginx_url + '/bill/updateWayBillByBillId1/' + billId,
            data: $("#goodsBillForm").serialize(),
            dataType: "json",
            success: function (result) {
                console.log(result);
                if (result === "SUCCESS") {
                    layer.msg('更新成功', {
                        time: 800,
                        icon: 1
                    }, function () {
                        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    });
                } else {
                    layer.msg('更新失败', {
                        time: 800,
                        icon: 2
                    });
                }
            }

        });
        return false;
    });


});