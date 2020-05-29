layui.use(['element', 'form', 'laydate', 'layer', 'table', 'jquery'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    lay('.test-item').each(function () {
        laydate.render({
            elem: this,
            trigger: 'click'
        })
    });

    laydate.render({
        elem: '#writeDate',
        value: new Date()
    });

    laydate.render({
        elem: '#sendGoodsDate',
        value: new Date()
    });

    let employeeId = $.cookie('loginId');
    $("#writeBillPerson").val(employeeId);
    $("#employeeCode").val(employeeId);

    $.ajax({
        type: "get",
        url: nginx_url + "/bill/findAllCustomerId",
        async: false,
        success: function (result) {
            $.each(result, function (i, item) {
                let option = "<option value='" + item + "'>";
                option += item;
                option += "</option>";
                $("#sendId").append(option);
                $("#receiveId").append(option);
                $("#payCustomer").append(option);
                form.render();
            });
        }

    });

    form.on('select(changeSend)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/bill/findCustomerByCustomerId/' + data.value,
            success: function (result) {
                $("#send").val(result.customerName);
                $("#sendPhone").val(result.linkmanMobile);
            }
        });
    });

    form.on('select(changeSend2)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/bill/findCustomerByCustomerId/' + data.value,
            success: function (result) {
                $("#receive").val(result.customerName);
                $("#receivePhone").val(result.linkmanMobile);
            }
        });
    });


    // 货运单信息添加
    // $("#addGoodsBill").click(function () {
    form.on('submit(addGoodsBill)', function () {

        $("#goodsBillForm :input").each(function () {
            $(this).removeAttr("disabled");
        });

        $.ajax({
            type: "post",
            url: nginx_url + "/bill/addWayBill",
            data: $("#goodsBillForm").serialize(),
            dataType: "json",
            async: false,
            success: function (result) {
                if (result.status === "SUCCESS") {
                    layer.msg('货运单添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('货运单添加失败', {
                        time: 800,
                        icon: 2
                    });
                }
                console.log(result);
            }
        });
        return false;
    });

});