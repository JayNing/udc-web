/*
* 会员中心 -帖子列表
* */

app.controller('postController', function($scope, $http,httpService) {
	//初始化
    $scope.curr=1;
    function submit(page){
        var data={
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/bbsManager/queryMyDiscussion").then(function successCallback(response) {
            $scope.posts = response.data.result;
            for(var i =0;length=$scope.posts.length,i<length;i++){
                if($scope.posts[i].EssayTypeName.indexOf(",")>0){
                    $scope.posts[i].EssayTypeName=$scope.posts[i].EssayTypeName.split(",");
                }else{
                    $scope.posts[i].EssayTypeName=$scope.posts[i].EssayTypeName.split(" ");
                }
            }
            console.log($scope.posts);
        },function errorCallback(response) {
            console.log("err");
        });
        //初始化分页页数
        httpService.getData("/bbsManager/queryMyselfCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result.DiscussionCount/10);
            $scope.page = getRange(page, $scope.allPage, 5);
            //返回页数范围（用来遍历）
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //初始化
    submit(1);
    $scope.detail=function(disId,disType){
        if(disType==1){
            window.location.href=basePath + "/communifyDisDetail?disId="+disId
                +"&&disType="+disType;
        }else if (disType==2){
            window.location.href=basePath + "/communifyQusDetail?disId="+disId
                +"&&disType="+disType;
        }
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

});			