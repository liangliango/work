layui.use(['element', 'form', 'laydate', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;

    $.ajax({
        type: "get",
        url: nginx_url + "/selectAllEmpCode",
        async: false,
        success: function (result) {
            $.each(result, function (i, item) {
                let option = "<option value='" + item + "'>";
                option += item;
                option += "</option>";
                $("#employeeId").append(option);
                form.render();
            });
        }

    });

    laydate.render({
        elem: '#date',
        type: 'date',
        value: new Date()
        // theme: 'grid'
    });

    // 员工工资添加
    form.on('submit(addEmployeeWage)', function (data) {
        $.ajax({
            type: "post",
            url: nginx_url + "/Emp/addWage",
            data: $("#employeeWageForm").serialize(),
            dataType: "json",
            success: function (result) {
                if (result === "SUCCESS") {
                    layer.msg('员工工资添加成功', {
                        time: 800
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('员工工资添加失败', {
                        time: 800
                    });
                }
            }
        });
        return false;
    });

});