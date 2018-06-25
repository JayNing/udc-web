/*
* 社区js
* */
$(function(){
    $(".header li a:contains('社区')").addClass("hover");
});

//社区搜索
app.controller('keyController', function ($scope,$rootScope, $http,httpService) {
    $scope.keySearch=$("#name").val();
    var lname = window.localStorage['lname'] || '';
    window.localStorage.clear();
    $scope.curr=1;
    $rootScope.isLoading = true;
    var page = getQueryString('page') || 1;
    function submit(page){
        var listUrlPath = "/bbsManager/queryDiscussionList",
            countUrlPath = "/bbsManager/queryDiscussionListCount";
        // if()
        //我的帖子展示
        var data = {
            param: $scope.keySearch?$scope.keySearch:'',
            pageIndex:page,
            pageSize:10
        }
        if(lname){
            listUrlPath = "/bbsManager/showBbsByTags";
            countUrlPath = "/bbsManager/queryBbsByTagsCount";
            data.param = lname;
        }
        console.log(data);
        //发送请求
        httpService.getList(data,listUrlPath).then(function successCallback(response) {
            $scope.searchRecord = response.data.result;
            if(response.data.code ==1){

                for ( var index in response.data.result){
                    if(response.data.result[index].DisType == 1){
                        console.log(response.data.result[index].EssayContent);
                        $scope.searchRecord[index].EssayContent = response.data.result[index].EssayContent.replace(/(&nbsp;)|(<[^>]+>)/g,"");
                    }
                }
                console.log( response.data);
                //  console.log( response.data.result[3].EssayContent.replace(/<[^>]+>/g,""));
                if(response.data.code==1){
                    var length=$scope.searchRecord.length;
                }
                for(var i =0;i<length;i++){
                    if($scope.searchRecord[i].TagStrings.indexOf(",")>0){
                        $scope.searchRecord[i].TagStrings=$scope.searchRecord[i].TagStrings.split(",");
                    }else{
                        $scope.searchRecord[i].TagStrings=$scope.searchRecord[i].TagStrings.split(" ");
                    }
                }
            }else if(response.data.code == 2){
                layer.msg('暂无相关的搜索内容!');
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //初始化分页页数
        var data1 = {
            param: $scope.keySearch?$scope.keySearch:''
        }
        if(lname){
            data1.param = lname;
        }
        httpService.getList(data1,countUrlPath).then(function successCallback(response) {
            $scope.curr=page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
            //返回页数范围（用来遍历）
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //初始化列表，分页
    submit(page);

    //左侧个人主页跳转
    $scope.leftJumps = function (url) {
        window.location.href=basePath + url;
    }
    //标签初始化展示
    var data1 = {
        parentId:0
    }
    httpService.getList(data1,"/essayType/getEssayTypeList").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.hotLabel = response.data.result;
            console.log($scope.hotLabel);
        }
    },function errorCallback(response) {
        console.log("err");
    });
    //阅读全文
    $scope.communityLink=function(disId,disType){
        if(disType==1){
            window.location.href=basePath + "/communifyDisDetail?disId="+disId+"&&disType="+disType;
        }else if (disType==2){
            window.location.href=basePath + "/communifyQusDetail?disId="+disId+"&&disType="+disType;
        }
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
        var tempurl = "http://"+window.location.host + GetUrlRelativePath(window.location.href)+'?page='+page;
        console.log(tempurl);
        window.location.replace(tempurl);


    };
    //标签搜索记录
    $scope.tagSearch=function(name){
        lname = '';
        // window.localStorage.clear();
        var data = {
            pageIndex:1,
            pageSize:10,
            param: name
        }
        httpService.getList(data,"/bbsManager/queryDiscussionList").then(function successCallback(response) {
            if(response.data.code == 1){
                $scope.searchRecord = response.data.result;
                var length=$scope.searchRecord.length;
                for ( var index in response.data.result){
                    if(response.data.result[index].DisType == 1){
                        console.log(response.data.result[index].EssayContent);
                        $scope.searchRecord[index].EssayContent = response.data.result[index].EssayContent.replace(/(&nbsp;)|(<[^>]+>)/g,"");
                    }
                }
                for(var i =0;i<length;i++){
                    if($scope.searchRecord[i].TagStrings.indexOf(",")>0){
                        $scope.searchRecord[i].TagStrings=$scope.searchRecord[i].TagStrings.split(",");
                    }else{
                        $scope.searchRecord[i].TagStrings=$scope.searchRecord[i].TagStrings.split(" ");
                    }
                }
            }else if(response.data.code == 2){
                layer.msg(response.data.msg);

            }
        },function errorCallback(response) {
            console.log("err");
        });

    }

    $scope.searchEnter = function (e){
        console.log(e);
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.search();
        }
    }
    //搜索记录
    $scope.search=function(){
        // window.localStorage.clear();
        lname = '';
        submit(1);
    }
});









