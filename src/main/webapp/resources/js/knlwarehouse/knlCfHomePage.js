/*
* 知识仓库首页
* */
$(function(){
    $(".header li a:contains('知识仓库')").addClass("hover");
});
app.controller('keyController', function ($scope, $http,httpService,$filter) {
    $scope.keySearch="";
    $scope.searchRecord = [];

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
    $scope.enterEvent = function(e,name) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }
    $scope.searchDetail = function(id,disId,disType){
        if(disId && disType) {
            if(disType==1){
                window.location.href=basePath + "/communifyDisDetail?disId="+disId+"&&disType="+disType;
            }else if (disType==2){
                window.location.href=basePath + "/communifyQusDetail?disId="+disId+"&&disType="+disType;
            }
        }else{
            window.location.href=basePath + "/searchDetail?id="+id;
        }

    }
    //标签搜索记录
    $scope.tagSearch = function(name){
        console.log("name : " + name);
        window.localStorage['tname'] = name;
        window.location.href="/intelligentSearchResult?tagSearch="+name;
        // $scope.keySearch = name;
        // var data = {
        //     pageIndex:$scope.curr,
        //     pageSize:10,
        //     param: name
        // }
        // httpService.getList(data,"/knowledgeManage/queryKnowledgeInfoIntelligent").then(function successCallback(response) {
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
        // },function errorCallback(response) {
        //     console.log("err");
        // });
    }

    //搜索记录
    $scope.search=function(){
        console.log("$scope.keySearch : " + $scope.keySearch);
        if($scope.keySearch != ''){
            window.location.href = basePath + '/intelligentSearchResult?keySearch=' + $scope.keySearch ;
        }else {
            layer.msg("搜索条件不能为空")
        }
    }

    $scope.knownledgeDisplay = function (type) {
        if ("1" == type){
            //智能推荐
            intelligentRecommend();
        }else if ("2" == type){
            //最近浏览
            recentlySearch();
        }else if ("3" == type){
            //公司热点
            companyHot();
        }
    }
    $scope.selectType = 1;

    function companyHot() {
        console.log("companyHot is called.");
        var companyHotData = {
            num:6
        }
        httpService.getList(companyHotData," /knowledgeManage/queryKnowledgeInfoHot").then(function successCallback(response) {
            if(response.data.code == 1){
                $scope.searchHotRecord = response.data.result;
                if($scope.searchHotRecord.length > 0){
                    for(var i =0;i<$scope.searchHotRecord.length;i++){
                        console.log($scope.searchHotRecord[i].DisTitle);
                        $scope.searchHotRecord[i].Title =  $scope.searchHotRecord[i].DisTitle
                    }
                }
            }else {
                $scope.searchHotRecord = [];
                // layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }

    function recentlySearch() {
        //最近浏览
        var recentlyData = {
            num:6
        }
        httpService.getList(recentlyData," /knowledgeManage/queryKnowledgeInfoRecently").then(function successCallback(response) {
            if(response.data.code == 1){
                $scope.searchRecRecord = response.data.result;
                if($scope.searchRecRecord.length > 0){
                    for(var i =0;i<$scope.searchRecRecord.length;i++){
                        console.log($scope.searchRecRecord[i].ViewTime);
                        $scope.searchRecRecord[i].time =  $filter("date")($scope.searchRecRecord[i].ViewTime, "yyyy-MM-dd");
                    }
                }
            }else {
                $scope.searchRecRecord = [];
                // layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }

    function intelligentRecommend() {
        //智能推荐
        var recommendData = {
            num:6
        }
        httpService.getList(recommendData," /knowledgeManage/queryKnowledgeInfoIntel").then(function successCallback(response) {

            if(response.data.code == 1){
                console.log(response.data)
                $scope.searchRecord = response.data.result;
                if($scope.searchRecord.length){
                    var length=$scope.searchRecord.length;
                    for(var i =0;i<length;i++){
                        console.log($scope.searchRecord[i].CreateTime);
                        $scope.searchRecord[i].time =  $filter("date")($scope.searchRecord[i].CreateTime, "yyyy-MM-dd");
                    }
                }
            }else {
                $scope.searchRecord = [];
                // layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }

    //知识库初始化展示
    $scope.knownledgeDisplay(1);
    $scope.knownledgeDisplay(2);
    $scope.knownledgeDisplay(3);

    $scope.knsClassifySearch = function () {
        window.location.href = "/knseClassify";
    }

});

$(function(){
    $(".repository-nav a").click(function(){
        $(this).addClass("hover").parent().siblings().children("a").removeClass("hover");
    });
});
