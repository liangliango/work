layui.use(['layer', 'form', 'element', 'jquery'], function () {

    let element = layui.element,
        $ = layui.jquery,
        layer = layui.layer,
        form = layui.form;

    form.on('submit(change)', function () {
        console.log($("#loginId1").val());

        $.ajax({
            type: 'put',
            url: nginx_url + '/manger/changePwd',
            async: false,
            data: {
                'loginId1': $("#loginId1").val(),
                'password': $("#newPassword").val()
            },
            dataType: 'json',
            success: function (result) {
                console.log(result);
                if (result === 'SUCCESS') {
                    layer.msg('密码修改成功', {
                        time: 800,
                        icon: 1
                    }, function () {
                        $('#passForm')[0].reset()
                    });
                } else {
                    layer.msg('密码修改失败', {
                        time: 800,
                        icon: 5
                    });
                }
            }
        });

        return false;
    })

});