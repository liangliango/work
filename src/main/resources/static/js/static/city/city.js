let cityArray = [];
let pass_station = [];
let cityId;
let number1 = 0;
let range_dot = ['', 'layui-bg-orange', 'layui-bg-green', 'layui-bg-cyan', 'layui-bg-blue', 'layui-bg-black', 'layui-bg-gray'];
//获取所有城市信息（id + cityName）

layui.use(['element', 'form', 'laydate', 'layer', 'table', 'jquery'], function () {
    let element = layui.element,
        form = layui.form,
        layer = layui.layer,
        table = layui.table;
    form.on('submit(add)', function () {
        $.ajax({
            type: "post",
            url: nginx_url + "/city/addCity",
            data: {'city':$("#city").val()},
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
        let cityId = $("#cityId").val();
        let linkCity = $("#linkCity").val();
        $.ajax({
            type: 'post',
            url: nginx_url + '/city/addCityLink',
            data: {
                'cityId': cityId,
                'linkCity': linkCity,
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
        console.log("passStation: " + pass_station.join(","));
        console.log("endStation: " + $("#endStation").val());
        $.ajax({
            type: 'post',
            url: nginx_url + '/city/addCityRoute/'+$("#fetchTime").val(),
            data: {
                'startStation': startStation,
                'passStation': pass_station.join(","),
                'endStation': $("#endStation").val(),
                'distance': $("#distance").val()
            },
            dataType: 'json',
            async: false,
            success: function (result) {
                if (result === 'SUCCESS') {
                    layer.msg('添加成功', {
                        time: 800,
                        icon: 1
                    });
                    // $("#resetForm3").click();
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

    element.on('tab(demo)',function (data) {
        pass_station=[];
    if (data.index===1){
        $.ajax({
            type: 'get',
            url: nginx_url + '/city/findAllCity',
            dataType: 'json',
            async: false,
            success: function (result) {
                $.each(result, function (i, item) {
                    let option = '<option value="' + item.id + '">';
                    option += item.city;
                    option += '</option>';
                    $("#cityId").append(option);
                    $("#linkCity").append(option);
                });
                form.render('select');
            }
        });
    }
        if (data.index===2){
            $.ajax({
                type: 'get',
                url: nginx_url + '/city/findAllCity',
                dataType: 'json',
                async: false,
                success: function (result) {
                    $.each(result, function (i, item) {
                        let option = '<option value="' + item.city + '">';
                        option += item.city;
                        option += '</option>';
                        $("#startStation").append(option);
                        $("#passStation").append(option);
                        $("#endStation").append(option);
                    });
                    form.render('select');
                }
            });

        }


    })
    form.on('select(changeRange)', function (data) {
        console.log(data);
        console.log(data.value);
        addPass(data.value);
        // number1=number1+1;
        let content = "<button type='button' class='layui-btn layui-btn-sm' id='city-" +  + "' onclick='removeSpan(" + number1 + ")'>";
        content += data.value;
        content += "<span class='layui-badge layui-bg-gray' style='font-size: 4px; line-height: 16px; height: 16px'>X</span></button>";

        $("#selectedCity2").append(content);
        form.render('select');

    })
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

    function addPass(passStation1) {
        pass_station.push(passStation1);
    }