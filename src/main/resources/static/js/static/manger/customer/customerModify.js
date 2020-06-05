layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    let customerId = window.location.href.split("=")[1];//获得Id
    $.ajax({
        type: "get",
        url: nginx_url + "/manger/findCustomerByCustomerId/" + customerId,
        success: function (result) {
            console.log(result);
            $("#customerName").val(result.customerName);
            $("#address").val(result.address);
            $("#linkman").val(result.linkman);
            $("#linkmanMobile").val(result.linkmanMobile);
            $("#customerType").val(result.customerType);
            $("#email").val(result.email);
            $("#businessLicenSe").val(result.businessLicenSe);;
            $("#idcard").val(result.idcard);
        }
    });

    form.on('submit(modifyCus)', function () {
        $("#cusForm :input").each(function () {
            $(this).removeAttr("disabled");
        });
        $.ajax({
            type: 'put',
            url: nginx_url + '/manger/updateCustomerByCustomerId/' + customerId,
            data: $("#cusForm").serialize(),
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

    form.verify({
        postcode: function (value, item) {
            if (!new RegExp("^[0-9]{6}$").test(value)) {
                return '邮箱格式不正确';
            }
        }
    });

});