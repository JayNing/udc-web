/*
* 图书借阅js
* */
//页面初始化选中左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('专家管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('专家提问管理')").css({"background":"#7a8ce3","color":"#fff"});
});
//右边图书控制器,初始化显示图书信息
app.controller('questionController', function($scope,$http,httpService) {
    $scope.questionBool=false;
    $scope.search1="";
    $scope.search2="";
    $scope.startTime="";
    $scope.endTime="";
    $scope.curr=1;
    //初始化表格数据获取值
    var data = {
        title:'',
        realName:'',
        start1Time:'',
        end1Time:'',
        pageIndex:1,
        pageSize:10
    }
    //发送请求
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }
    httpService.getList(data,"/specialistManager/querySpeConsult").then(function successCallback(response) {
        $scope.questions = response.data.result;
        for(var i=0;i<$scope.questions.length;i++){
            if($scope.questions[i].Createtime>0){
                $scope.questions[i].Createtime = timestampToTime($scope.questions[i].Createtime);
            }
        }

        console.log($scope.questions);
    },function errorCallback(response) {
        console.log("err");
    });

//初始化分页页数
    var data1 = {
        title:'',
        realName:'',
        start1Time:'',
        end1Time:''
    }
    httpService.getList(data1,"/specialistManager/querySpeConsultCount").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
        //返回页数范围（用来遍历）
    },function errorCallback(response) {
        console.log("err");
    });

    //绑定分页点击事件
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
        //获取图书数据
        var data = {
            title:$scope.search1?($scope.search1):'',
            realName:$scope.search2?($scope.search2):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/specialistManager/querySpeConsult").then(function successCallback(response) {

            $scope.questions = response.data.result;
            for(var i=0;i<$scope.questions.length;i++){
                if($scope.questions[i].Createtime>0){
                    $scope.questions[i].Createtime = timestampToTime($scope.questions[i].Createtime);
                }
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1 = {
            title:$scope.search1?($scope.search1):'',
            realName:$scope.search2?($scope.search2):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/specialistManager/querySpeConsultCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //显示弹框
    $scope.see = function(id) {
        $scope.questionBool=true;
        var data={
            consultId:id
        }
        $scope.id=id;
        httpService.getList(data,"/specialistManager/querySpeConsultDetail").then(function successCallback(response) {
            console.log(response.data.result);
            $scope.RealName = response.data.result.RealName;
            $scope.DepartmentName = response.data.result.DepartmentName;
            $scope.Content = response.data.result.Content;
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //关闭弹框
    $scope.close= function() {
        $scope.questionBool=false;
    };
    //根据输入信息搜索图书信息
    $scope.submit = function() {
        //获取值
        var data = {
            title:$scope.search1?($scope.search1):'',
            realName:$scope.search2?($scope.search2):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        //发送请求
        httpService.getList(data,"/specialistManager/querySpeConsult").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $scope.questions = response.data.result;
            for(var i=0;i<$scope.questions.length;i++){
                if($scope.questions[i].Createtime>0){
                    $scope.questions[i].Createtime = timestampToTime($scope.questions[i].Createtime);
                }
            }
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确搜索内容!")
        });
        //搜索获取页数
        var data1 = {
            title:$scope.search1?($scope.search1):'',
            realName:$scope.search2?($scope.search2):'',
            start1Time:$scope.startTime,
            end1Time:$scope.endTime
        }
        httpService.getList(data1,"/specialistManager/querySpeConsultCount").then(function successCallback(response) {
            //返回页数范围（用来遍历）
            $scope.curr = 1;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(1, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.submit();
        }
    }


});