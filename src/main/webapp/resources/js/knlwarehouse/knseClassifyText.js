/*
* 知识仓库-文本
* */

//初始化
app.controller('textController', function ($scope, $http) {
    //初始化相关文章
    httpService.getData("").then(function successCallback(response) {
        $scope.arrticels = response.data.result.arrticels;
    },function errorCallback(response) {
        console.log("err");
    });

    //初始化文本资料
    httpService.getList(data,"").then(function successCallback(response) {
        $scope.title = response.data.result.title;
        $scope.num = response.data.result.num;
        $scope.labs = response.data.result.labs;
        $scope.name= response.data.result.name;
        $scope.url= response.data.result.url;
        $scope.content= response.data.result.content;
        $scope.ys ={
            "background":"url("+response.data.result.src+") no-repeat center;background-size: cover;"
        }
    },function errorCallback(response) {
        console.log("err");
    });



});