/*
* 考试培训-培训管理-发起线下js
* */
$(function(){
    $(".warehouse-list-title>span:contains('培训管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
$(function(){

    //初始化选中左边目录栏
    $(".warehouse-list-title>span:contains('考试培训')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children(".child-nav").find("span:contains('培训管理')").parent().next().show().children("a:contains('发起培训-线下')").css({"background":"#7a8ce3","color":"#fff"});




    $(".examination-examine").click(function(){
        var title = $("#title").val();
        var start_time = $("#start-time").val();
        var end_time = $("#end-time").val();
        var name = $("#name").val();
        var summary = $("#summary").val();
        var train = $("#train").val();
        if (title == '') {
            layer.msg("请输入培训标题");
            return false;
        }
        if (start_time == '') {
            layer.msg("请输入开始时间");
            return false;
        }
        if(end_time == ''){
            layer.msg("请输入结束时间");
            return false;
        }
        if(name == ''){
            layer.msg("请输入讲师姓名");
            return false;
        }
        if(summary == ''){
            layer.msg("请输入讲师简介");
            return false;
        }
        if(train == ''){
            layer.msg("请输入培训内容");
            return false;
        }
    });

});


app.controller('beLineController', function($scope) {
    //跳转线下页面
    $scope.onLine = function () {
        window.location.href=basePath + "/index/onLine";
    }

});