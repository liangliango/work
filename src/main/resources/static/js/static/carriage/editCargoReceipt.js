layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;
        $ = layui.jquery;

    laydate.render({
        elem: '#writeDate',
        value: new Date()
    });
    $.ajax({
        type: "get",
        url: nginx_url + "/carriage/findWayBillByState1",
        async: false,
        success: function (result) {
            $.each(result, function (i, item) {
                let option = "<option value='" + item + "'>";
                option += item;
                option += "</option>";
                $("#billId").append(option);
                form.render('select');
            });
        }
    });

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

    $.ajax({
        type: 'get',
        url: nginx_url + '/carriage/findAllDriver',
        dataType: 'json',
        async: false,
        success: function (result) {
            console.log(result);
            $.each(result, function (i, item) {
                let option = '<option value="' + item + '">';
                option += item;
                option += '</option>';
                $("#driverId").append(option);
            });
            form.render('select');
        }
    });

    form.on('select(changeSend)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/carriage/findWayBillByBillId/' + data.value,
            success: function (result) {
                $("#billName").val(result.billName);
                $("#send").val(result.send);
                $("#sendPhone").val(result.sendPhone);
                $("#receive").val(result.receive);
                $("#receivePhone").val(result.receivePhone);
                $("#exceptDate").val(result.exceptDate);
            }
        });
    });
    form.on('select(changeSend2)', function (data) {
        // ajax
        $.ajax({
            type: 'get',
            url: nginx_url + '/carriage/findDriverByDriverId/' + data.value,
            success: function (result) {
                $("#driverName").val(result.driverName);
                $("#phone").val(result.phone);
                if(result.isCompany===0){
                    $("#isCompany").val("否");
                }
                if(result.isCompany===1){
                    $("#isCompany").val("是");
                }
            }
        });
    });
    // laydate.render({
    //     elem: '#signTime',
    //     value: new Date()
    // });

    // 货运单回执信息添加
    form.on('submit(addCarriage)', function () {

        $("#carriageForm :input").each(function () {
            $(this).removeAttr("disabled");
        });

        $("#state").removeAttr("disabled");

        $.ajax({
            type: "post",
            url: nginx_url + "/carriage/addCarriage",
            data: $("#carriageForm").serialize(),
            dataType: "json",
            async: false,
            success: function (result) {
                if (result === "SUCCESS") {
                    layer.msg('货运合同添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('货运合同添加失败', {
                        time: 800,
                        icon: 2
                    });
                }
            }
        });
        return false;
    });

});