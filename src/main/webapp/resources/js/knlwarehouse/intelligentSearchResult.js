/*
* 智能搜索检索结果js
* */


//搜索记录

    //初始化分页页数
/*    var data1 = {

    }
    httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
        //返回页数范围（用来遍历）
    },function errorCallback(response) {
        console.log("err");
    });*/

$(function(){
    $(".header li a:contains('知识仓库')").addClass("hover");
});
//最近浏览
app.controller('keyController', function ($scope,$rootScope, $http, httpService,$filter,$timeout) {
    var modelUrl = {
        key:{
            listUrlPath :'/knowledgeManage/queryKnowledgeInfoIntelligent',
            countUrlPath : '/knowledgeManage/queryKnowledgeInfoIntelligentCount'
        },
        tag:{
            listUrlPath :'/knowledgeManage/showKnowledgeByTags',
            countUrlPath :'/knowledgeManage/queryKnowledgeByTagsCount'
        }
    }
    var isModel = $('#isModel').val();
    $rootScope.isLoading = true;
    $scope.keySearch = $('#searchKeyValue').val() || $('#searchTagValue').val();
    console.log($scope.isModel)
    $scope.curr = 1;
    //标签初始化展示
    var tagData = {
        num:4
    }
    httpService.postList(tagData,"/knowledgeManage/updateTagInfoCounts").then(function successCallback(response) {
        if(response.data.code == 1){
            $scope.hotLabel = response.data.result;
        }else {
            layer.msg(response.data.msg);
        }
    },function errorCallback(response) {
        console.log("err");
    });

    $scope.searchDetail = function(id){

        window.location.href=basePath + "/searchDetail?id="+id;

    }
    $scope.searchEnter = function (e){
        isModel = 'key';
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }
    $scope.keySearchA = function(page){
        isModel = 'key';
        $scope.search(page);
    }
    //搜索记录
    $scope.search = function(page){

        console.log("$scope.keySearch : " + $scope.keySearch);
        console.log("page : " + page);
        var data = {
            pageIndex:page,
            pageSize:10,
            param: $scope.keySearch?$scope.keySearch:''
        }
        httpService.getList(data,modelUrl[isModel].listUrlPath).then(function successCallback(response) {
            $scope.searchRecord = response.data.result;
            if(response.data.code == 1){
                if($scope.searchRecord.length){
                    var length=$scope.searchRecord.length;
                    for(var i =0;i<length;i++){
                        $scope.searchRecord[i].tagStrings=$scope.searchRecord[i].TagStrings.split(",");
                        console.log( $scope.searchRecord[i].TagStrings);
                    }
                }
            }else {
                $rootScope.isLoading = false;
                layer.msg(response.data.msg);
                $scope.searchRecord = [];
            }

            //获取分页
            var data1 = {
                "param":$scope.keySearch
            }
            httpService.getList(data1,modelUrl[isModel].countUrlPath).then(function successCallback(response) {
                $scope.curr = page;
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange(page, $scope.allPage, 5);
                $timeout(function () {
                    console.log('isLoading')
                    $rootScope.isLoading = false;
                },1000)
            },function errorCallback(response) {
                console.log("err");
                $timeout(function () {
                    $rootScope.isLoading = false;
                },1000)
            });

        },function errorCallback(response) {
            console.log("err");
        });
    }

    $scope.search(1);

    //最近浏览
    var recentlyData = {
        num:6
    }
    httpService.getList(recentlyData," /knowledgeManage/queryKnowledgeInfoRecently").then(function successCallback(response) {
        if(response.data.code == 1){
            $scope.browseRecord = response.data.result;
            if($scope.browseRecord.length){
                var length=$scope.browseRecord.length;
                for(var i =0;i<length;i++){
                    $scope.browseRecord[i].tagStrings=$scope.browseRecord[i].TagStrings.split(",");
                }
            }
        }else {
            $scope.browseRecord = [];
            // layer.msg(response.data.msg);
        }
    },function errorCallback(response) {
        console.log("err");
    });

    //智能推荐
    var recommendData = {
        num:6
    }
    httpService.getList(recommendData," /knowledgeManage/queryKnowledgeInfoIntel").then(function successCallback(response) {
        if(response.data.code == 1){
            $scope.recommend = response.data.result;
            if($scope.recommend.length){
                var length=$scope.recommend.length;
                for(var i =0;i<length;i++){
                  console.log($scope.recommend[i].CreateTime);
                    $scope.recommend[i].showCreateTime = $filter("date")($scope.recommend[i].CreateTime, "yyyy-MM-dd");
                }
            }
        }else {
            $scope.recommend = [];
            // layer.msg(response.data.msg);
        }
    },function errorCallback(response) {
        console.log("err");
    });


    //标签搜索记录
    $scope.tagSearch=function(name){
        console.log("name : " + name)
        $scope.keySearch = name;
        window.location.href = basePath + '/intelligentSearchResult?tagSearch='+name;
        // var data = {
        //     pageIndex:$scope.curr,
        //     pageSize:10,
        //     param: name
        // }
        // httpService.getList(data,listUrlPath).then(function successCallback(response) {
        //     if(response.data.code == 1){
        //         $scope.searchRecord = response.data.result;
        //         if($scope.searchRecord.length) {
        //             var length=$scope.searchRecord.length;
        //             for(var i =0;i<length;i++){
        //                 $scope.searchRecord[i].tagStrings=$scope.searchRecord[i].TagStrings.split(",");
        //             }
        //         }
        //     }else {
        //         $scope.searchRecord = [];
        //         // layer.msg(response.data.msg);
        //     }
        //     //获取分页
        //     var data1 = {
        //         "param":$scope.keySearch
        //     }
        //     var page = 1;
        //     httpService.getList(data1,listUrlPath).then(function successCallback(response) {
        //         $scope.curr = page;
        //         $scope.allPage=Math.ceil(response.data.result/10);
        //         $scope.page = getRange(page, $scope.allPage, 5);
        //         $timeout(function () {
        //             console.log('isLoading')
        //             $rootScope.isLoading = false;
        //         },1000)
        //     },function errorCallback(response) {
        //         console.log("err");
        //         $timeout(function () {
        //             $rootScope.isLoading = false;
        //         },1000)
        //     });
        // },function errorCallback(response) {
        //     console.log("err");
        // });
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

        $scope.search(page);
    };

    $scope.knsClassifySearch = function () {
        window.location.href = "/knseClassify";
    }
});
