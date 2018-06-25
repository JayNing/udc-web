/*
* 会员中心 -消息通知
* */

app.controller('noticeController', function($scope, $http) {
       $scope.train={};
    //初始化分页页数
    var data1 = {

    }
    httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
        //返回页数范围（用来遍历）
    },function errorCallback(response) {
        console.log("err");
    });
    httpService.getData("").then(function successCallback(response) {
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

		//确认参加
		$scope.join=function(id){
			var data = {
				id:id
			}
            httpService.getList(data,"").then(function successCallback(response) {
                layer.msg(response.data.msg)
            },function errorCallback(response) {
                console.log("err");
            });

		}
    //绑定点击事件
    $scope.pageClick = function (page) {
        console.log(page);
        console.log($scope.curr);
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
        var data = {

        }
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        //获取分页
        var data1 = {

        }
        httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });
    };
		
});

