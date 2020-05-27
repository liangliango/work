layui.use(['layer', 'form', 'element', 'laydate', 'jquery', 'table'], function () {
    let element = layui.element,
        $ = layui.jquery,
        form = layui.form,
        layer = layui.layer,
        laydate = layui.laydate,
        table = layui.table;

    form.render();

    let driverId = window.location.href.split("=")[1];

    $.ajax({
        type: 'get',
        url: nginx_url + '/emp/selectById/' + driverId,
        dataType: 'json',
        success: function (result) {
            $.each(result, function (i, item) {
                let temp_id = '#' + i;
                $(temp_id).val(item);
            });
        }
    });

    form.on('submit(modifyDriver)', function () {
        $("#employeeForm :input").each(function () {
            $(this).removeAttr("disabled");
        });
        $.ajax({
            type: 'put',
            url: nginx_url + '/emp/update/' + driverId,
            data: $("#employeeForm").serialize(),
            dataType: 'json',
            success: function (result) {
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