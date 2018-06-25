/*
* 知识仓库-分类-视频js
* */

//初始化
app.controller('videoController', function ($scope, $http,httpService) {
    //初始化相关文章
    httpService.getData("").then(function successCallback(response) {
        $scope.arrticels = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    //初始化video资料
    httpService.getList(data,"").then(function successCallback(response) {
        $scope.title = response.data.result.title;
        $scope.num = response.data.result.num;
        $scope.labs = response.data.result.labs;
        $scope.name= response.data.result.name;
        $scope.brief= response.data.result.brief;
        $scope.url= response.data.result.url;
        $scope.ys ={
            "background":"url("+response.data.result.src+") no-repeat center;background-size: cover;"
        }
    },function errorCallback(response) {
        console.log("err");
    });

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