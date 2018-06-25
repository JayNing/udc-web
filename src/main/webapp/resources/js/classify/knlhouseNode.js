/*
*  知识库节点分类分类js
* */

$(function(){
    $(".warehouse-list-title>span:contains('分类管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('知识仓库节点分类')").css({"background":"#7a8ce3","color":"#fff"});
});

//右边图书控制器,初始化显示信息
app.controller('commArticleController', function($scope, $http,httpService) {
    $scope.addBool=false;
    $scope.editBool=false;
    $scope.delBool=false;
    //初始化表格数据获取值
    httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
        $scope.departments = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
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
            flowId:$scope.id
        }
        $scope.departments.splice($scope.index, 1);
        httpService.postList(data,"/repositoryCategoryFlow/deleteRepositoryCategoryFlow").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
            $scope.departments = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.delBool=false;
    }

    //根据id编辑分类
    $scope.edit = function(id) {
        $scope.editBool= true;
        var data = {
            flowId:id
        }
        $scope.id=id;
        httpService.getList(data,"/repositoryCategoryFlow/getRepositoryCategoryFlowDetail").then(function successCallback(response) {
          $scope.typeName = response.data.result[0].flowName;
            console.log(response.data.result[0]);
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //根据id提交分类
    $scope.editSubmit = function() {
        var data = {
            flowId:$scope.id,
            flowName:$scope.typeName
        }
        httpService.postList(data,"/repositoryCategoryFlow/updateRepositoryCategoryFlow").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分类列表
        httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
            $scope.departments = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.editBool=false;
    };
    //添加分类
    $scope.add = function() {
        $scope.addBool= true;
    }
    //添加分类提交
    $scope.addSubmit = function() {

        var data = {
            flowName:$scope.catname
        }
        if($scope.catname=""){
            layer.msg("请输入分类名称!");
        }
        httpService.postList(data,"/repositoryCategoryFlow/addRepositoryCategoryFlow").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分类列表
        httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
            $scope.departments = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.addBool=false;
    };
    //关闭弹框
    $scope.close= function() {
        $scope.addBool=false;
        $scope.editBool=false;
        $scope.delBool=false;
        $scope.catname = '';
    };



});


