
$(function(){
    $(".header li a:contains('首页')").addClass("hover");
});

var d = new Date()
var time = d.getFullYear()+'-'+(d.getMonth()+1)+'-'+d.getDate();
var mySchedule = new Schedule({
    el: '#schedule-box', //指定包裹元素（可选）
    date: time, //生成指定日期日历（可选）
    clickCb: function(y, m, d) {
        //点击日期回调（可选）
    },
    nextMonthCb: function(y, m, d) {
        //点击下个月回调（可选）
    },
    nextYeayCb: function(y, m, d) {
        //点击下一年回调（可选）
    },
    prevMonthCb: function(y, m, d) {
        //点击上个月回调（可选）
    },
    prevYearCb: function(y, m, d) {
        //点击上一年月回调（可选）
    }
});

//指定图标的配置和数据
var option = {
    // title:{
    //     text:'ECharts 数据统计'
    // },
    tooltip:{},
    // legend:{
    //     data:['用户来源']
    // },
    color : ['#7a8ce3'],
    xAxis:{
        data:["营收","收款","签约","拜访","客户"],
        axisLine:{
            lineStyle:{
                color:'#f1f1f1'
            }
        },
        axisLabel:{
            textStyle:{
                color:'#5d5d5d'
            }
        }
    },
    yAxis:{
        axisLine: {
            lineStyle: {
                color: '#fbfbfb'
            }
        },
        axisLabel: {
            textStyle: {
                color: '#898989'
            }
        }
    },
    series:[{
        name:'商务咨询部',
        type:'bar',
        barWidth : 20,
        itemStyle: {
            emphasis: {
                barBorderRadius: 30
            },
            normal: {
                barBorderRadius:[30, 30, 0, 0],
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#02c487'},
                        {offset: 0.5, color: '#3ebfae'},
                        {offset: 1, color: '#60bdc5'}
                    ]
                )
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#02c487'},
                        {offset: 0.5, color: '#3ebfae'},
                        {offset: 1, color: '#60bdc5'}
                    ]
                )
            }
        },
        data:[400,350,250,300,290]
    }]
};

var option1 = {
    // title:{
    //     text:'ECharts 数据统计'
    // },
    tooltip:{},
    // legend:{
    //     data:['用户来源']
    // },
    color : ['#7a8ce3'],
    xAxis:{
        data:["营收","收款","签约","拜访","客户"],
        axisLine:{
            lineStyle:{
                color:'#f1f1f1'
            }
        },
        axisLabel:{
            textStyle:{
                color:'#5d5d5d'
            }
        }
    },
    yAxis:{
        axisLine: {
            lineStyle: {
                color: '#fbfbfb'
            }
        },
        axisLabel: {
            textStyle: {
                color: '#898989'
            }
        }
    },
    series:[{
        name:'商务咨询部',
        type:'bar',
        barWidth : 20,
        itemStyle: {
            emphasis: {
                barBorderRadius: 30
            },
            normal: {
                barBorderRadius:[30, 30, 0, 0],
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#7aaced'},
                        {offset: 0.5, color: '#6ea0f1'},
                        {offset: 1, color: '#6494f6'}
                    ]
                )
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#7aaced'},
                        {offset: 0.5, color: '#6ea0f1'},
                        {offset: 1, color: '#6494f6'}
                    ]
                )
            }
        },
        data:[400,350,250,300,290]
    }]
};
var option2 = {
    // title:{
    //     text:'ECharts 数据统计'
    // },
    tooltip:{},
    // legend:{
    //     data:['用户来源']
    // },
    color : ['#7a8ce3'],
    xAxis:{
        data:["营收","收款","签约","拜访","客户"],
        axisLine:{
            lineStyle:{
                color:'#f1f1f1'
            }
        },
        axisLabel:{
            textStyle:{
                color:'#5d5d5d'
            }
        }
    },
    yAxis:{
        axisLine: {
            lineStyle: {
                color: '#fbfbfb'
            }
        },
        axisLabel: {
            textStyle: {
                color: '#898989'
            }
        }
    },
    series:[{
        name:'商务咨询部',
        type:'bar',
        barWidth : 20,
        itemStyle: {
            emphasis: {
                barBorderRadius: 30
            },
            normal: {
                barBorderRadius:[30, 30, 0, 0],
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#6febe9'},
                        {offset: 0.5, color: '#47c8cc'},
                        {offset: 1, color: '#35c8d1'}
                    ]
                )
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#6febe9'},
                        {offset: 0.5, color: '#47c8cc'},
                        {offset: 1, color: '#35c8d1'}
                    ]
                )
            }
        },
        data:[400,350,250,300,290]
    }]
};
var option3 = {
    // title:{
    //     text:'ECharts 数据统计'
    // },
    tooltip:{},
    // legend:{
    //     data:['用户来源']
    // },
    color : ['#7a8ce3'],
    xAxis:{
        data:["营收","收款","签约","拜访","客户"],
        axisLine:{
            lineStyle:{
                color:'#f1f1f1'
            }
        },
        axisLabel:{
            textStyle:{
                color:'#5d5d5d'
            }
        }
    },
    yAxis:{
        axisLine: {
            lineStyle: {
                color: '#fbfbfb'
            }
        },
        axisLabel: {
            textStyle: {
                color: '#898989'
            }
        }
    },
    series:[{
        name:'商务咨询部',
        type:'bar',
        barWidth : 20,
        itemStyle: {
            emphasis: {
                barBorderRadius: 30
            },
            normal: {
                barBorderRadius:[30, 30, 0, 0],
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#49cfed'},
                        {offset: 0.5, color: '#41bcf1'},
                        {offset: 1, color: '#38a9f6'}
                    ]
                )
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#49cfed'},
                        {offset: 0.5, color: '#41bcf1'},
                        {offset: 1, color: '#38a9f6'}
                    ]
                )
            }
        },
        data:[400,350,250,300,290]
    }]
};
//初始化echarts实例
var myChart = echarts.init(document.getElementById('chartmain'),'macarons');
var myChart1 = echarts.init(document.getElementById('chartmain1'),'macarons');
var myChart2 = echarts.init(document.getElementById('chartmain2'),'macarons');
var myChart3 = echarts.init(document.getElementById('chartmain3'),'macarons');

//使用制定的配置项和数据显示图表
myChart.setOption(option);
myChart1.setOption(option1);
myChart2.setOption(option2);
myChart3.setOption(option3);


app.controller('msgController',function($scope,$http,httpService,$filter){
    $scope.isShow = false;
    var data={
        modules:'1,3,4,5',
        pageIndex:1,
        pageSize:8
    }
    httpService.getList(data,'/messageManage/queryMessage').then(function successCallback(response) {
        $scope.notifications = response.data.result;
        console.log(response.data.result);
        for(var i =0;length=$scope.notifications.length,i<length;i++){
            $scope.notifications[i].CreateTime = $filter("date")($scope.notifications[i].CreateTime, "yyyy-MM-dd");
        }
        console.log(response.data.result);
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.showT = function(index){
        $scope.messageTitle = $scope.notifications[index].MessageTitle;
        $scope.messageContent = $scope.notifications[index].MessageContent;
        console.log(index+''+$scope.messageTitle);
        $scope.isShow = true;
    }
})
