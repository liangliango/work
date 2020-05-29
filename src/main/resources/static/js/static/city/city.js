let cityArray = [];
let pass_station = [];
let cityId;
let number1;
let range_dot = ['', 'layui-bg-orange', 'layui-bg-green', 'layui-bg-cyan', 'layui-bg-blue', 'layui-bg-black', 'layui-bg-gray'];
//获取所有城市信息（id + cityName）
$.ajax({
    type: 'get',
    url: nginx_url + '/city/findAllCity',
    dataType: 'json',
    async: false,
    success: function (result) {
        let index = 1;
        $.each(result, function (i, item) {
                let option = '<option value="' + item.city + '">';
                option += item.city;
                option += '</option>';
                $("startStation").append(option);
                $("passStation").append(option);
                $("endStation").append(option);
                cityArray.push(item.city);
            });
            form.render('select');
        }
});
layui.use(['element', 'form', 'laydate', 'layer', 'table', 'jquery'], function () {
    let element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery;
    form.on('submit(add)', function () {
        $.ajax({
            type: "get",
            url: nginx_url + "/city/addCity",
            data: $("#addCity").serialize(),
            dataType: "json",
            async: false,
            success: function (result) {
                if (result === "SUCCESS") {
                    lay.msg('城市添加成功', {time: 800, icon: 1});
                    $("#resetForm").click();
                } else {
                    lay.msg('城市添加失败', {time: 800, icon: 2});
                }
            }
        });
        return false;
    })
    form.on('submit(addLink)', function () {
        let cityId = $("#city").val();
        let linkCity = $("#linkCity").val();
        $.ajax({
            type: 'post',
            url: nginx_url + '/route/addCityLink',
            data: {
                'cityId': cityId,
                'linkCity': linkCity
            },
            dataType: 'json',
            success: function (result) {
                if (result === 'SUCCESS') {
                    layer.msg('添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('添加失败', {
                        time: 800,
                        icon: 5
                    })
                }
            }
        });
        return false;
    });

    form.on('submit(addRoute)', function () {
        let startStation = $("#startStation").val();
        console.log("startStation: " + startStation);
        console.log("passStation: " + pass_station.toString());
        $.ajax({
            type: 'post',
            url: nginx_url + '/route/addCityRoute',
            data: {
                'startStation': startStation,
                'passStation': pass_station.toString(),
                'endStation': $("#endStation").val,
                'distance': $("#distance").val(),
                'fetchTime': $("#fetchTime").val()
            },
            dataType: 'json',
            success: function (result) {
                if (result === 'SUCCESS') {
                    layer.msg('添加成功', {
                        time: 800,
                        icon: 1
                    });
                    $("#resetForm").click();
                } else {
                    layer.msg('添加失败', {
                        time: 800,
                        icon: 5
                    })
                }
            }
        });
        return false;
    });

    element.on('tab(demo)')

    form.on('select(changeRange)', function (data) {
        addPass(data);
        number1++;
        let content = "<button type='button' class='layui-btn layui-btn-sm' id='city-" + number1 + "' onclick='removeSpan(" + number1 + ")'>";
        content += pass_station[number1 - 1];
        content += "<span class='layui-badge layui-bg-gray' style='font-size: 4px; line-height: 16px; height: 16px'>X</span></button>";

        $("#selectedCity2").append(content);
        form.render('select');

    });



    $("#resetForm").click(function () {
        $("#selectedCity").empty();
    });

})
    function format(id) {
        let array = ('' + id).split(',');
        let result = '';
        $.each(array, function (i, item) {
            let index = item - 1;
            result += (i === 0 ? '' : ', ');
            result += cityArray[index];
        });
        return result;
    }

    function removeSpan(id) {
        let buttonId = '#city-' + id;
        pass_station.splice($.inArray(id, pass_station), 1);
        console.log(pass_station);
        number1--;
        $(buttonId).remove();
    }

    function addPass(passStation) {
        pass_station.push(passStation);
    }