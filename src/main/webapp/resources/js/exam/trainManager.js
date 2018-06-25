/*
* 考试培训-培训管理js
* */
$(function(){
    $(".warehouse-list-title>span:contains('培训管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
$(function(){

    $(".manage-list>a:contains('培训管理')").addClass("hover");


    $(".statistic a:nth-child(2)").click(function(){
        $(".edit-export").fadeIn();
    });
    $(".statistic a:nth-child(3)").click(function(){
        $(this).parents("tr").remove();
    });
    //参与人统计tab
    $(".export-tab a").click(function(){
        $(this).addClass("hover").siblings().removeClass("hover");
        var index = $(this).index();
        if(index==0){
            $(".sign1").show();
            $(".sign2").hide();
        }else{
            $(".sign1").hide();
            $(".sign2").show();
        }

    });


});
