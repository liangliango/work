let payCustomer = $.cookie("loginId");
layui.use(['element', 'form', 'laydate', 'layer', 'table'],function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer;

    console.log(payCustomer);

    laydate.render({
        elem: '#clearDate',
        value: new Date()
    });

    $.ajax({
        type: 'get',
        url:nginx_url+'/customer/findAllCanClearByPayCustomer/'+payCustomer,
        success:function (result) {
            $.each(result,function (i,item) {

                let option = "<option value='" + item.billId + "'>";
                option += item.billId;
                option += "</option>";
                console.log(option);
                $("#billId").append(option);
                form.render('select');
            });

        }
    });

    form.on('select(change)',function (data) {
        console.log(data.value);
        $.ajax({
            type: 'get',
            url: nginx_url+'/customer/findWayBillByBillId/'+data.value,
            success:function (result) {
                let temp = "#";
                $.each(result,function (i,item) {
                    $(temp+i).val(item);
                });

                laydate.render({
                    elem: '#startDate',
                    value: new Date(result.startDate)
                });
                laydate.render({
                    elem: '#exceptDate',
                    value: new Date(result.exceptDate)
                });
                laydate.render({
                    elem: '#writeDate',
                    value: new Date(result.writeDate)
                });
                form.render();
            }
        })
    });
    form.on('submit(clear)',function () {

        $.ajax({
            type:'put',
            url:nginx_url+'/customer/wayBillClearByBillId/'+$('#billId').val(),
            data:{"state":$('#state').val(),"clearDate":$('#clearDate').val()},
            success:function (result) {
                if (result==="ERROR"){
                    layer.msg('更新失败', {
                        time: 800,
                        icon: 1
                    })
                };
                if (result === "SUCCESS") {
                    layer.msg('更新成功', {
                        time: 800,
                        icon: 1
                    }, function () {
                        $('#goodsBillForm').empty();
                    });}
            }
        })

    })



})