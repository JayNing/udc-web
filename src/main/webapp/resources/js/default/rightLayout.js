//帖子、关注、收藏
app.controller('dataController', function ($scope, $http,httpService) {
    //个人主页跳转
    $scope.leftJumps = function (url) {
        window.location.href=basePath + url;
    }
    httpService.getData("/bbsManager/queryMyselfCount").then(function successCallback(response) {
        $scope.postNum = response.data.result.DiscussionCount;
        $scope.attentionNum = response.data.result.FollowUserCount;
        $scope.collectionNum = response.data.result.CollectCount;
    },function errorCallback(response) {
        console.log("err");
    });

});
//社区公告
app.controller('noticeController', function ($scope, $http,httpService) {
    var data={
        messageType:2
    }
    console.log(data);
    httpService.getList(data,"/messageManage/queryNewestMessage").then(function successCallback(response) {
        var tempContent = response.data.result.MessageContent.replace(/\n|\r\n/g,",");
        var tempArr = tempContent.split(',');
        console.log(tempArr);
        $scope.MessageContent = '';
        for (var i=0;i<tempArr.length;i++){
            $scope.MessageContent += '<span></span>'+tempArr[i]+'<br>';
        }
        // $scope.MessageContent =
    },function errorCallback(response) {
        console.log("err");
    });
});
//积分排行榜
app.controller('integralController', function ($scope,$rootScope, $http,httpService,$timeout) {
    var data={
        pageIndex:1,
        pageSize:10
    }
    httpService.getList(data,"/bbsManager/queryIntegralRanking").then(function successCallback(response) {
        if(response.data.code == 1) {
            $scope.integralRecord = response.data.result;
            console.log($scope.integralRecord);
        }
        $timeout(function () {
            $rootScope.isLoading = false;
        },1000)
    },function errorCallback(response) {
        console.log("err");
        $timeout(function () {
            $rootScope.isLoading = false;
        },1000)
    });
});


