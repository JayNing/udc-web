/*
* 会员中心 -通知审核js
* */

$(function(){
    $(".warehouse-list-title>span:contains('个人中心')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('培训发起审核')").css({"background":"#7a8ce3","color":"#fff"});


});

app.controller('noticeController', function($scope, $http,httpService) {
       $scope.train={};
		httpService.getData(data,"").then(function successCallback(response) {
            $scope.train.title=response.data.result.title;
            $scope.train.startTime=response.data.result.startTime;
            $scope.train.endTime=response.data.result.endTime;
            $scope.train.address=response.data.result.address;
            $scope.train.species=response.data.result.species;
            $scope.train.content=response.data.result.content;
            $scope.train.teacher=response.data.result.teacher;
            $scope.train.introduce=response.data.result.introduce;
            $scope.train.id=response.data.result.id;
		},function errorCallback(response) {
			console.log("err");
		});
		//审核通过
		$scope.passThrough=function(id){
			var data = {
				id:id
			}
            httpService.getList(data,"").then(function successCallback(response) {
                layer.msg(response.data.msg)
            },function errorCallback(response) {
                console.log("err");
            });
		}
		//驳回
		$scope.passThrough=function(id){
			var data = {
				id:id
			}
            httpService.getList(data,"").then(function successCallback(response) {
                layer.msg(response.data.msg)
            },function errorCallback(response) {
                console.log("err");
            });

		}
		
});
