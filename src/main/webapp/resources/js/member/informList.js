/*
* 会员中心 -通知列表
* */

$(function(){
    $(".warehouse-list-title>span:contains('个人中心')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('培训通知')").css({"background":"#7a8ce3","color":"#fff"});
});


app.controller('newController', function($scope, $http,httpService,$filter) {
    //初始化
    $scope.isShow = false;
    $scope.curr=1;
    $scope.tab=1;
    var data={
        modules:'1',
        pageIndex:1,
        pageSize:10
    }
    $scope.showT = function(index){
        $scope.messageTitle = $scope.notifications[index].MessageTitle;
        $scope.messageContent = $scope.notifications[index].MessageContent;
        console.log(index+''+$scope.messageTitle);
        $scope.isShow = true;
    }
    httpService.getList(data,"/messageManage/queryMessage").then(function successCallback(response) {
        $scope.notifications = response.data.result;
        console.log(response.data.result);
        for(var i =0;length=$scope.notifications.length,i<length;i++){
            $scope.notifications[i].CreateTime = $filter("date")($scope.notifications[i].CreateTime, "yyyy-MM-dd");
        }
        console.log(response.data.result);
    },function errorCallback(response) {
        console.log("err");
    });
    function submit(page){
        //初始化分页页数
        httpService.getData("/messageManage/queryMessageCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
            //返回页数范围（用来遍历）
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //初始化
    submit(1);
    $scope.select=function(num){
        $scope.tab=num;
        if(num==1){
            var data={
                modules:'1',
                pageIndex:1,
                pageSize:10
            }
        }else{
            var data={
                modules:'3,4,5',
                pageIndex:1,
                pageSize:10
            }
        }
        console.log(data);
        httpService.getList(data,"/messageManage/queryMessage").then(function successCallback(response) {
            if (response.data.code==1){
                 $scope.notifications = response.data.result;
                for(var i =0;length=$scope.notifications.length,i<length;i++){
                    $scope.notifications[i].CreateTime = $filter("date")($scope.notifications[i].CreateTime, "yyyy-MM-dd");
                }
            }else{
                console.log("暂无消息!");
                $scope.notifications=null;
            }
        },function errorCallback(response) {
            console.log("err");
        });
        submit(1);
    }
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
        var data={
            modules:'1',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/messageManage/queryMessage").then(function successCallback(response) {
            $scope.notifications = response.data.result;
            for(var i =0;length=$scope.notifications.length,i<length;i++){
                $scope.notifications[i].CreateTime = $filter("date")($scope.notifications[i].CreateTime, "yyyy-MM-dd");
            }
        },function errorCallback(response) {
            console.log("err");
        });
        submit(page);
    };

	
});			