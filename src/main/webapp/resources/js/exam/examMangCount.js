/*
* 考试培训-考试管理-统计js
* */
$(function(){
    $(".warehouse-list-title>span:contains('考试管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('chart'));

// 指定图表的配置项和数据
var option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['60以下', '60~70 ', '70~80 ', '80~90', ' 90~100 '],
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'直接访问',
            type:'bar',
            barWidth: '60%',
            data:[10, 52, 200, 334, 390]
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

$(function(){
    $(".warehouse-list-title>span:contains('考试管理')").parents(".warehouse-list").addClass("hover");
});

app.controller('examManagerController', function($scope, $http, httpService) {
    function submit(page) {
        var data = {
            examId:parseInt($("#examId").val(),10),
            pageIndex:1,
            pageSize:10
        };
        console.log(data);
        httpService.getList(data ,"/examModule/queryScoreAnalysis").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                $scope.examList = response.data.result;
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });

        //获取分页
        var data1={
            examId:parseInt($("#examId").val(),10)
        }
        httpService.getList(data1,"/examModule/queryScoreAnalysisCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    submit(1);

    //绑定点击事件
    $scope.pageClick = function (page) {
        switch(page){
            case  '上一页':
                page = parseInt($scope.curr) - 1;
                if(page==1){
                    $(".page a:contains('上一页')").fadeOut(300);
                }
                $(".page a:contains('下一页')").fadeIn(300);
                break;
            case '首页':
                page = 1;
                $(".page a:contains('上一页')").fadeOut(300);
                $(".page a:contains('下一页')").fadeIn(300);
                break;
            case '下一页':
                page = parseInt($scope.curr) + 1;
                $(".page a:contains('上一页')").fadeIn(300);
                if(page==$scope.allPage){
                    $(".page a:contains('下一页')").fadeOut(300);
                }
                break;
            case '尾页':
                $(".page a:contains('上一页')").fadeIn(300);
                page =  $scope.allPage;
                $(".page a:contains('下一页')").fadeOut(300);
                break;
            case 1:
                $(".page a:contains('上一页')").fadeOut(300);
                $(".page a:contains('下一页')").fadeIn(300);
                break;
            case $scope.allPage:
                $(".page a:contains('上一页')").fadeIn(300);
                $(".page a:contains('下一页')").fadeOut(300);
                break;
            default:
                $(".page a:contains('上一页')").fadeIn(300);
                $(".page a:contains('下一页')").fadeIn(300);
        }
        if (page < 1) page = 1;
        else if (page >  $scope.allPage) page =  $scope.allPage;
        //点击相同的页数 不执行点击事件
        if (page == $scope.curr) return;
        //获取数据
        submit(page);
    };
});