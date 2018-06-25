/*
* 外部专家js
* */
$(function(){
    $(".header li a:contains('外部专家')").addClass("hover");
});
//控制器
    app.controller('externalController', function ($scope,$rootScope,$timeout, $http,httpService) {
        $rootScope.isLoading = true;
        $scope.consultationBool=false;
        $scope.curr = 1;
        $scope.num = 0;
        var data = {
            pageIndex:1,
            pageSize:2
        }
        httpService.getList(data,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
            if(response.data.code == 1){
                $scope.experts = response.data.result;
                console.log( $scope.experts);
                for(var i=0;length=$scope.experts.length,i<length;i++){
                    $scope.experts[i].honors=$scope.experts[i].honors.split(",");
                }
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //初始化分页页数
        httpService.getData("/specialistManager/getSpecialistCount").then(function successCallback(response) {
            if(response.data.code==1)
            {
                $scope.allPage=Math.ceil(response.data.result/2);
                $scope.page = getRange(1, $scope.allPage, 5);
            }
            $timeout(function(){
                $rootScope.isLoading = false;
            },1000)
            //返回页数范围（用来遍历）
        },function errorCallback(response) {
            console.log("err");
            $timeout(function(){
                $rootScope.isLoading = false;
            },1000)
        });
        //弹框
        $scope.consultation=function(id){
            $scope.consultationBool=true;
            $scope.id=id;
        }
        $scope.close=function(){
            $scope.consultationBool=false; 
        }
        //校验字数
        $scope.tolCount = function () {
            $scope.num =  $scope.consultations.length;
            console.log($scope.consultations.length);
            if($scope.consultations.length>800){
                layer.msg("最多800个字");
                $scope.consultations=$scope.consultations.substr(0,800);
            }
        };
        //点赞
        $scope.addCount=function(id){
            var data={
                count:$scope.count,
                speId:id
            }
            httpService.getList(data,"/specialistManager/likeSpecialist").then(function successCallback(response) {
                layer.msg(response.data.msg);
            },function errorCallback(response) {
                console.log("err");
            });
            var data = {
                pageIndex:$scope.curr,
                pageSize:2
            }
            httpService.getList(data,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
                $scope.experts = response.data.result;
                for(var i =0;length=$scope.experts.length,i<length;i++){
                    if($scope.experts[i].honors.indexOf(",")>0){
                        $scope.experts[i].honors=$scope.experts[i].honors.split(",");
                    }else{
                        $scope.experts[i].honors=$scope.experts[i].honors.split(" ");
                    }
                }

            },function errorCallback(response) {
                console.log("err");
            });

        }
        //咨询问题
        $scope.consultationSubmit=function(){
            $scope.consultationBool=false;
            var data={
                speId:$scope.id,
                content:$scope.consultations
            }
            //对输入框中的专家咨询内容进行判断
            if (!$scope.consultations||typeof($scope.consultations) == "undefined") {
                layer.msg("请输入咨询内容!");
                return;
            }
            httpService.postList(data,"/specialistManager/saveSpeConsult").then(function successCallback(response) {
                layer.msg(response.data.msg);
                $scope.consultations="";
            },function errorCallback(response) {
                console.log("err");
            });
        }
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
            //更新数据
            var data = {
                pageIndex:page,
                pageSize:2
            }
            httpService.getList(data,"/specialistManager/getSpecialistInfo").then(function successCallback(response) {
                $scope.experts = response.data.result;
                for(var i=0;length=$scope.experts.length,i<length;i++){
                    $scope.experts[i].honors=$scope.experts[i].honors.split(",");
                }
            },function errorCallback(response) {
                console.log("err");
            });
            $scope.curr=page;

        };
    });


