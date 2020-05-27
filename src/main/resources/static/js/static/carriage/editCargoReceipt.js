layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;
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
    $.ajax({
        type: "get",
        url: nginx_url + "/vehicle/selectLeftCodes",
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
        url: nginx_url + '/route/findAllRegions',
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
        url: nginx_url + '/driverInfo/selectAllId',
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
            url: nginx_url + '/vehicle/findGoodsBill/' + data.value,
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
            url: nginx_url + "/vehicle/add",
            data: $("#carriageForm").serialize(),
            dataType: "json",
            async: false,
            success: function (result) {
                if (result === "SUCCESS") {
                    layer.msg('货运回执单添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('货运回执单添加失败', {
                        time: 800,
                        icon: 2
                    });
                }
            }
        });
        return false;
    });

});