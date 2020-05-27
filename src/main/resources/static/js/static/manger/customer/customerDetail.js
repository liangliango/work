layui.use(['element', 'form', 'laydate', 'jquery', 'layer', 'table'], function () {
    let element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    let customerId = window.location.href.split("=")[1];
    $.ajax({
        type: "get",
        url: nginx_url + "/selectCusByCode/" + customerId,
        success: function (result) {
            console.log(result);
            $("#customerName").val(result.customerName);
            $("#businessLicenSe").val(result.businessLicenSe);
            $("#address").val(result.address);
            $("#linkman").val(result.linkman);
            $("#linkmanMobile").val(result.linkmanMobile);
            $("#customerType").val(result.customerType);
            $("#email").val(result.email);
            $("#idCard").val(result.idCard);
        }
    });

    $("#goToMod").click(function () {
        let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭

    })

});