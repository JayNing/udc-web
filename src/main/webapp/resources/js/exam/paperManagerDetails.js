/*
* 试卷详情
* */
$(function(){
    $(".warehouse-list-title>span:contains('考试管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});

$(function(){

    //页面初始化默认选定左侧导航栏
    $(".warehouse-list-title>span:contains('考试培训')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children(".child-nav").find("span:contains('考试管理')").parent().next().show().children("a:contains('试卷详情')").css({"background":"#7a8ce3","color":"#fff"});


});
