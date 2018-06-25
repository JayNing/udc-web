/*
* 考试培训-考卷管理js
* */
$(function(){
    $(".warehouse-list-title>span:contains('考卷管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});

app.controller('paperManagerController', function($scope, $http, httpService) {
    $scope.firstLevelCategorys = [];
    $scope.firstLevelCategory = '0';
    $scope.secondLevelCategorys = [];
    $scope.secondLevelCategory = '0';
    $scope.questionName = '';
    $scope.startTime='';
    $scope.endTime='';
    initFirstLevel();
    function initFirstLevel() {
        var data = {
            "parentId": 0
        };
        httpService.getList(data, "/exCategory/getExCategoryList").then(function successCallback(response) {
            var code = response.data.code;
            console.log(response.data.result);
            if (code == 1) {
                $scope.firstLevelCategorys = response.data.result;
            } else {
                layer.msg(response.data.msg);
            }
        }, function errorCallback(response) {
            console.log("err");

        });
    }
    //获取下级目录
    $scope.getSecondLevel = function () {
        var data = {
            "categoryId":parseInt($scope.firstLevelCategory,10)>0?parseInt($scope.firstLevelCategory):''
        };
        httpService.getList(data, "/exCategory/getExCategoryListByParentId").then(function successCallback(response) {
            var code = response.data.code;
            if (code == 1) {
                $scope.secondLevelCategorys = response.data.result;
            } else {
                $scope.secondLevelCategorys = [];
                $scope.secondLevelCategory = '0';
            }
        }, function errorCallback(response) {
            console.log("err");
        });
    }
    function submit(page) {
        var data = {
            'title':$scope.questionName,
            'repCatId1':parseInt($scope.firstLevelCategory,10)>0?parseInt($scope.firstLevelCategory,10):'',
            'repCatId2':parseInt($scope.secondLevelCategory,10)?parseInt($scope.secondLevelCategory,10):'',
            'start1Time':$scope.startTime,
            'end1Time':$scope.endTime,
            'pageIndex':page,
            'pageSize':10
        };
       console.log(data);
       httpService.getList(data ,"/examModule/queryPaperListc").then(function successCallback(response) {
           var code = response.data.code;
           if(code == 1){
               $scope.paperList = response.data.result;
           }else {
               $scope.paperList=[];
               layer.msg(response.data.msg);
           }
       },function errorCallback(response) {
           console.log("err");
       });

        //获取分页
        httpService.getList(data,"/examModule/queryPaperListCountc").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    submit(1);
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            submit(1);
        }
    }
    //初始化
    $scope.submitManager = function(){
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

        //获取数据
        submit(page);
    };
    //左侧个人主页跳转
    $scope.leftJumps = function (url) {
        window.location.href=basePath + url;
    }



});


