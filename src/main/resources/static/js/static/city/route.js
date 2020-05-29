let range_dot = ['', 'layui-bg-orange', 'layui-bg-green', 'layui-bg-cyan', 'layui-bg-blue', 'layui-bg-black', 'layui-bg-gray'];

layui.use(['element', 'form', 'laydate', 'layer', 'table', 'jquery'],function () {

    let element = layui.element,
        form = layui.form,
        laydate = layui.laydate,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;

    $("#routeInfo").empty();
    $.ajax({
        type: 'get',
        url: nginx_url + '/city/findAllCityRoute',
        dataType: 'json',
        success: function (result) {
            console.log(result);
            let rand = 0;
            $.each(result, function (i, item) {
                let content = '<div class="layui-row layui-col-space15">' +
                    '<div class="layui-col-md12">' +
                    '<div class="layui-card">' +
                    '<div class="layui-card-header">' +
                    '<div class="layui-col-md4">';
                content += item.startStation + ' - ' + item.endStation;
                content += '</div><div class="layui-col-md4">';
                content += '里程：' + item.distance + 'km';
                content += '</div><div class="layui-col-md4">';
                content += '耗时：' + item.fetchTime + '天';
                content += '</div></div>' +
                    '<div class="layui-card-body layui-col-space10">';
                if (item.passStation !== '') {
                    let passStation = item.passStation.split(',');
                    $.each(passStation, function (j, temp) {
                        content += j === 0 ? '' : ' - ';
                        content += '<span class="layui-badge-dot ' + range_dot[(rand++ % 7)] + '"></span> ';
                        content += '<span> ' + temp + '</span>'

                    });
                } else {
                    content += '<span class="layui-badge-dot ' + range_dot[(rand++ % 7)] + '"></span>';
                    content += '<span> 直达</span>'
                }
                $("#routeInfo").append(content);
            });
        }
    })
})