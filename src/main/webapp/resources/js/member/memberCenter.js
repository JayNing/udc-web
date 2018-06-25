/*
* 个人主页
* */
//消息通知

$(function(){
    $(".warehouse-list-title>span:contains('个人中心')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('个人主页')").css({"background":"#7a8ce3","color":"#fff"});
});

//消息通知
app.controller('noticeController', function($scope,$rootScope, $http,httpService) {
    var data={
        modules:"1,2,3,4,5",
        pageIndex:1,
        pageSize:10
    }
    $rootScope.isLoading = true;
    httpService.getList(data,"/messageManage/queryMessage").then(function successCallback(response) {
        $scope.notifications = response.data.result;
        console.log(response.data.result);
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.getMore=function(){
         window.location.href=basePath + "/informList"
    }

});
//我的帖子
app.controller('postController', function($scope, $http,httpService) {
    var data={
        disType:'',
        pageIndex:1,
        pageSize:10
    }
    httpService.getList(data,"/bbsManager/queryMyDiscussion").then(function successCallback(response) {
        $scope.posts = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.detail=function(disId,disType){
        if(disType==1){
            window.location.href=basePath + "/communifyDisDetail?disId="+disId
                +"&&disType="+disType;
        }else if (disType==2){
            window.location.href=basePath + "/communifyQusDetail?disId="+disId
                +"&&disType="+disType;
        }
    }
    //个人主页跳转
    $scope.leftJumps = function (url) {
        window.location.href=basePath + url;
    }
    $scope.getMore=function(){
        window.location.href=basePath + "/postList"
    }

});
//我的关注
app.controller('followController', function($scope, $http,httpService) {
    $scope.moreBool=true;
    var data={
        pageIndex:1,
        pageSize:10
    }
    httpService.getList(data,"/bbsManager/queryFollowUser").then(function successCallback(response) {
        $scope.follows = response.data.result;
        console.log("我的关注");
        console.log($scope.follows);
    },function errorCallback(response) {
        console.log("err");
    });
  /*  $scope.getMore=function(){
        $scope.moreBool=false;
        var data={
            pageIndex:1,
            pageSize:1000
        }
        httpService.getList(data,"/bbsManager/queryFollowUser").then(function successCallback(response) {
            $scope.follows = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
    }

    $scope.getMore=function(){
        $scope.moreBool=false;

    }*/

    $scope.getMore=function(){
        window.location.href=basePath + "/followList"
    }

});
//我的收藏
app.controller('collectionController', function($scope,$rootScope, $http,httpService) {
    var data={
        disType:'',
        pageIndex:1,
        pageSize:10
    }
    httpService.getList(data,"/bbsManager/queryCollectDiscussion").then(function successCallback(response) {
        $scope.collects = response.data.result;
        console.log("我的收藏");
        console.log($scope.collects);
        $rootScope.isLoading = false;
        $rootScope.isLoading = false;
    },function errorCallback(response) {
        console.log("err");
        $rootScope.isLoading = false;
    });
    $scope.detail=function(disId,disType){
        if(disType==1){
            window.location.href=basePath + "/communifyDisDetail?disId="+disId
                +"&&disType="+disType;
        }else if (disType==2){
            window.location.href=basePath + "/communifyQusDetail?disId="+disId
                +"&&disType="+disType;
        }
    }
    $scope.getMoreCollect=function(){
        window.location.href=basePath + "/collectList"
    }

});





