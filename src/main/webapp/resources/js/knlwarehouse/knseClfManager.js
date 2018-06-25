/*
* 知识仓库管理
* */
//分页指令

$(function(){
    $(".warehouse-list-title>span:contains('知识仓库')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('知识仓库管理')").css({"background":"#7a8ce3","color":"#fff"});

});

app.controller('recordController', function($scope, $http,httpService,$filter) {
    $scope.search="";
    $scope.startTime="";
    $scope.endTime="";
    $scope.search="";
    $scope.search="";
    $scope.curr=1;
    $scope.firstLevelCategory = '';
    $scope.secondLevelCategory = '';
    $scope.kldNodeCategory = '';
    $scope.firstLevelCategorys = [];
    $scope.secondLevelCategorys = [];
    $scope.kldNodeCategorys = [];

    //搜索
    function submit(page) {
        console.log($scope.startTime);
        var data = {
            'title':$scope.search?($scope.search):'',
            'start1Time':$scope.startTime?$scope.startTime:'',
            'end1Time':$scope.endTime?$scope.endTime:'',
            'repCatId1' : $scope.firstLevelCategory,
            'repCatId2' : $scope.secondLevelCategory,
            'flowId' : $scope.kldNodeCategory,
            pageIndex:page,
            pageSize:10
        };

        httpService.getList(data,"/knowledgeManage/queryKnowledgeInfo").then(function successCallback(response) {
            var data = response.data;
            if(data.code == 1){
                $scope.records = data.result;
                if($scope.records.length) {
                    var length=$scope.records.length;

                    for(var i =0;i<length;i++){
                        //截取时间格式,yyyy-MM-dd
                        $scope.records[i].time = $scope.records[i].CreateTime.substr(0, 10);
                      //  $scope.records[i].time = $filter("date")($scope.records[i].CreateTime, "yyyy-MM-dd");
                    }
                }
            }else {
                $scope.records = [];
                layer.msg(data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });

        //获取分页

        httpService.getList(data,"/knowledgeManage/queryKnowledgeInfoCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });
    };

    //获取一级下拉框
    function initFirstLevel(){
        var data = {
            "repCatParentId" : 0
        };
        httpService.getList(data ,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            var code = response.data.code;
            console.log("response.data.code : " + code);
            if(code == 1){
                $scope.firstLevelCategorys = response.data.result;
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");

        });
    }
    //获取知识库下拉框
    function initkldNodeCategorys(){
        httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
            var code = response.data.code;
            console.log("response.data.code : " + code);
            console.log("response.data.result : " + response.data.result);
            if(code == 1){
                $scope.kldNodeCategorys = response.data.result;
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");

        });
    }
    //初始化
    submit(1);
    initFirstLevel();
    initkldNodeCategorys();
    //获取二级下拉框
    $scope.getSecondLevel = function () {
        submit(1)
        console.log("$scope.firstLevelCategory : " + $scope.firstLevelCategory);
        var data = {
            "repCatId" : $scope.firstLevelCategory
        };
        httpService.getList(data ,"/repositoryClassify/getRepository").then(function successCallback(response) {
            var code = response.data.code;
            console.log("response.data.code : " + code);
            if(code == 1){
                $scope.secondLevelCategorys = response.data.result;
            }else {
                $scope.secondLevelCategorys = [];
                $scope.secondLevelCategory = '';
            }
        },function errorCallback(response) {
            console.log("err");
        });
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
        submit(page);

    };
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            submit($scope.curr);
        }
    }
    $scope.submit=function(){
        submit($scope.curr);
    }
    //录入知识
    $scope.inputKnlg = function () {
        window.location.href=basePath + "/inputKnlg";
    }
    //编辑
    $scope.edit = function(id) {
        window.location.href=basePath + "/knlgEdit?id="+id;
    };
    //根据Id删除分类
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
            'kId':$scope.id
        }
        $scope.records.splice($scope.index, 1);
        httpService.postList(data,"/knowledgeManage/deleteKnowledge").then(function successCallback(response) {
            if (response.data.code == 1){
                layer.msg(response.data.msg);
                submit($scope.curr);
            } else {
                layer.msg(response.data.msg)
            }
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.delBool=false;
    }

    //关闭弹框
    $scope.close=function(){
        $scope.delBool=false;
    }

});



