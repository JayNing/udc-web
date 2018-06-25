

app.controller('followController',function($scope,$http,httpService){
    var data={
        pageIndex:1,
        pageSize:10000
    }
    httpService.getList(data,"/bbsManager/queryFollowUser").then(function successCallback(response) {
        $scope.follows = response.data.result;
        console.log("我的关注");
        console.log($scope.follows);
    },function errorCallback(response) {
        console.log("err");
    });
})