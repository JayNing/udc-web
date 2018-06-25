/*
* 部门分类js
* */
$(function(){
    $(".warehouse-list-title>span:contains('分类管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('部门分类')").css({"background":"#7a8ce3","color":"#fff"});
});

//右边控制器,初始化显示信息
app.controller('commArticleController', function($scope, $http,httpService) {
    $scope.addBool=false;
    $scope.editBool=false;
    $scope.delBool=false;
    //初始化表格数据获取值
    httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
        $scope.departments = response.data.result;
        console.log($scope.departments);
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
            departmentId:$scope.id
        }
        $scope.departments.splice($scope.index, 1);
        httpService.postList(data,"/departmentManager/deleteDepartment").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
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
            departmentId:id
        }
        $scope.id=id;
        httpService.getList(data,"/departmentManager/getDepartmentDetail").then(function successCallback(response) {
            $scope.typeName = response.data.result.departmentName;
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //根据id提交分类
    $scope.editSubmit = function() {
        var data = {
            departmentId:$scope.id,
            departmentName:$scope.typeName
        }
        httpService.postList(data,"/departmentManager/updateDepartment").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分类列表
        httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
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
            departmentName:$scope.catname
        }
        console.log($scope.catname);
        if($scope.catname=""){
            layer.msg("请输入分类名称!");
        }
        httpService.postList(data,"/departmentManager/addDepartment").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分类列表
        httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
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

