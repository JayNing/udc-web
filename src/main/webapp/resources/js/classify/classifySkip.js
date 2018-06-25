app.controller('commArticleNavController', function ($scope) {

    //跳转知识仓库文章类型页面
    $scope.knlgArticle = function () {
        window.location.href=basePath + "/knlgArticle";
    }

    //跳转部门分类页面
    $scope.departClassify = function () {
        window.location.href=basePath + "/departClassify";
    }

    //跳转考试分类页面
    $scope.examClassify = function () {
        window.location.href=basePath + "/examClassify";
    }


    //跳转社区文章分类页面
    $scope.communityArit = function () {
        window.location.href=basePath + "/communityArit";
    }

    //跳转知识仓库流程节点分类页面
    $scope.knlgNode = function () {
        window.location.href=basePath + "/knlgNode";
    }

});
