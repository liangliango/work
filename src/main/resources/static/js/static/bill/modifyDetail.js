layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    let billId = window.location.href.split("=")[1];
    $.ajax({
        type: "get",
        url: nginx_url + "/goodsBill/selectByCode/" + billId,
        success: function (result) {
            console.log(result);
            $.each(result, function (i, item) {
                let temp_id = '#' + i;
                $(temp_id).val(item);
            });

            // // 审核
            // if (result.ifAudit === '审核') {
            //     $("#ifAudit").attr('checked', 'checked');
            //     form.render('checkbox');
            // }
            //
            // // 有效
            // if (result.validity === '有效') {
            //     $("#validity").attr('checked', 'checked');
            //     form.render('checkbox');
            // }
            //
            // // 结账
            // if (result.ifSettleAccounts === '结账') {
            //     $("#ifSettleAccounts").attr('checked', 'checked');
            //     form.render('checkbox');
            // }

            // 日期
            laydate.render({
                elem: '#startDate',
                type: 'date',
                value: new Date(result.startDate)
                // theme: 'grid'
            });
            // if (result.factDealDate != null && result.factDealDate !== '') {
            //     laydate.render({
            //         elem: '#factDealDate',
            //         type: 'date',
            //         value: new Date(result.factDealDate)
            //         // theme: 'grid'
            //     });
            // }
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
            url: nginx_url + '/goodsBill/updateByCode/' + billId,
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

    lay('.test-item').each(function () {
        laydate.render({
            elem: this,
            trigger: 'click'
        })
    });

    $.ajax({
        type: "get",
        url: nginx_url + "/selectAllCusCode",
        success: function (result) {
            $.each(result, function (i, item) {
                let option = "<option value='" + item + "'>";
                option += item;
                option += "</option>";
                $("#sendId").append(option);
                $("#receiveId").append(option);
                $("#payCusomer").append(option);
                form.render();
            });
        }

    });

    form.on('select(changeSend)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/selectCusByCode/' + data.value,
            success: function (result) {
                $("#send").val(result.customer);
                $("#sendPhone").val(result.phone);
                // $("#sendGoodsCustomerAddr").val(result.address);
            }
        });
    });

    form.on('select(changeSend2)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/selectCusByCode/' + data.value,
            success: function (result) {
                $("#receive").val(result.customer);
                $("#receivePhone").val(result.phone);
                // $("#receiveGoodsCustomerAddr").val(result.address);
            }
        });
    });
    // form.on('select(changeSend3)', function (data) {
    //     // ajax
    //     $.ajax({
    //         type: 'get',
    //         url: nginx_url + '/selectCusByCode/' + data.value,
    //         success: function (result) {
    //             $("#receive").val(result.customer);
    //             $("#receivePhone").val(result.phone);
    //             // $("#receiveGoodsCustomerAddr").val(result.address);
    //         }
    //     });
    // });

});