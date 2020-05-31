layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    $.ajax({
        type: 'get',
        url: nginx_url + '/carriage/findAllCity',
        dataType: 'json',
        async: false,
        success: function (result) {
            $.each(result, function (i, item) {
                let option = '<option value="' + item.city + '">';
                option += item.city;
                option += '</option>';
                $("#sendAdreass").append(option);
                $("#reciverAdreass").append(option);
            });
            form.render('select');
        }
    });
    let carriageId = window.location.href.split("=")[1];
    $.ajax({
        type: "get",
        url: nginx_url + "/carriage/findCarriageByCarriageId/" + carriageId,
        success: function (result) {
            console.log(result);
            $.each(result, function (i, item) {
                let temp_id = '#' + i;
                $(temp_id).val(item);
                // if(i==="payType"){
                //     let conntext = '<option value = "'+i+'" selected>'+item+'</option>';
                //     $(temp_id).append(conntext);
                // }
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
        }
    });

    form.on('submit(update)', function () {

        $("#cargoReceiptForm :input").each(function () {
            $(this).removeAttr("disabled");
        });


        $.ajax({
            type: 'put',
            url: nginx_url + '/carriage/updateCarriage',
            data: $("#cargoReceiptForm").serialize(),
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

    lay('.test-item').each(function () {
        laydate.render({
            elem: this,
            trigger: 'click'
        })
    });

});