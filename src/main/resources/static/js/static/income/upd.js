layui.use(['element', 'form', 'laydate', 'layer', 'table'], function (){
    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table;

    form.on('submit(add)',function () {
        $.ajax({
            type:post,
            url:nginx_url+"/income/upd",
            date:$("#extraForm").serialize(),
            datatype:"json",
            success: function (result) {
                if (result === "SUCCESS") {
                    layer.msg('手工调账成功', {
                        time: 800
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('手工调账失败', {
                        time: 800
                    });
                }
            }
        })
        return false;
    })
})