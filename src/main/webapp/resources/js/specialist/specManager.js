/*
* 专家管理js
* */

//页面初始化选中左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('专家管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('专家列表')").css({"background":"#7a8ce3","color":"#fff"});
});

app.controller('expertController', function($scope, $http,httpService) {
    $scope.delBool=false;
    $scope.curr=1;
    //初始化专家列表
    var data = {
        pageIndex: 1,
        pageSize: 10
    }
    httpService.getList(data,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
        $scope.specialists = response.data.result;
        console.log($scope.specialists);
    },function errorCallback(response) {
        console.log("err");
    });
    //初始化分页页数
    httpService.getData("/specialistManager/getSpecialistCount").then(function successCallback(response) {
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
    $scope.curr = page;
    //更新专家列表
    var data = {
        pageIndex: page,
        pageSize: 10
    }
    httpService.getList(data,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
        $scope.specialists = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });

};
    //编辑专家,需传id
    $scope.expertEdit = function (id) {
        window.location.href=basePath + "/specEdit?speeditid="+id;
    }
    //添加专家
    $scope.expertAdd = function () {
        window.location.href=basePath + "/specAdd";
    }
   //删除账号信息
    $scope.del = function(id,index) {
        $scope.id=id;
        $scope.index = index;
        $scope.delBool=true;
    };
    //取消删除
    $scope.cancel=function(){
        $scope.delBool=false;
    }
    //确认删除
    $scope.confirm=function(){
        var data = {
            speId: $scope.id
        }
        var data1 = {
            pageIndex: $scope.curr,
            pageSize: 10
        }
        $scope.specialists.splice($scope.index, 1);
        httpService.postList(data,"/specialistManager/deleteSpecialistInfo").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        httpService.getList(data1,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
            $scope.specialists = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });

        $scope.delBool=false;
    }

    

});