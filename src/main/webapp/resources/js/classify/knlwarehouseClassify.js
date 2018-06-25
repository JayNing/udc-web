/*
* 知识仓库文章类型js
* */

$(function(){
        //页面的初始化选中左侧,目录栏
    $(".warehouse-list-title>span:contains('分类管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('知识仓库文章分类')").css({"background":"#7a8ce3","color":"#fff"});
});

//右边图书控制器,初始化显示信息
app.controller('commArticleController', function($scope, $http,httpService,$compile) {
    $scope.addBool=false;
    $scope.editBool=false;
    $scope.delBool=false;
    $scope.fatherBool=false;
    //初始化表格数据获取值
    var data = {
        repCatParentId:0
    }
    httpService.getList(data,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
        $scope.classification= response.data.result;
        console.log( $scope.classification);
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
            repCatId: $scope.id
        }
        console.log(data.repCatId);
        $scope.classification.splice($scope.index, 1);
        httpService.postList(data,"/repositoryClassify/deleteRepositoryCategory").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据获取值
        var data1 = {
            repCatParentId:0
        }
        httpService.getList(data1,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            $scope.classification= response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.delBool=false;
    }

    //根据id编辑分类
    $scope.edit = function(id) {
        $scope.editBool= true;
        var data = {
            repCatId:id
        }
        $scope.id=id;
        httpService.getList(data,"/repositoryClassify/getRepositoryCategoryDetail").then(function successCallback(response) {
            $scope.typeName = response.data.result[0].repCatName;

        },function errorCallback(response) {
            console.log("err");
        });
    };
    //根据id提交分类
    $scope.editSubmit = function() {
        var data = {
            repCatId:$scope.id,
            repCatName:$scope.typeName
        }
        httpService.postList(data,"/repositoryClassify/updateRepositoryCategory").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据获取值
        var data1 = {
            repCatParentId:0
        }
        httpService.getList(data1,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            $scope.classification= response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.editBool=false;
    };
    //添加分类
    $scope.add = function() {
        $scope.addBool= true;
    }
    //点击加载二级分类
    $scope.dropDown = function(id,event) {
        var data = {
            repCatId:id
        }
        httpService.getList(data,"/repositoryClassify/getRepository").then(function successCallback(response) {
            $scope.twoClassification= response.data.result;
            console.log($scope.twoClassification);
            var html='';
            for(var i = 0;length=$scope.twoClassification.length,i<length;i++){
                html+='<tr class="child'+id+'">';
                html+='<td class=\"two-level\" style=\"border-right-width:0;\"><span class=\"dropDown\"></span>'+$scope.twoClassification[i].repCatName+'</td>';
                html+='<td style="border-left-width:0;border-right-width:0;"></td>';
                html+='<td>';
                html+='<div class="books-btn">';
                html+='<a href="javascript:;"   ng-click="edit('+$scope.twoClassification[i].repCatId+')">编辑</a>';
                html+='<a href="javascript:;"  ng-click="del('+$scope.twoClassification[i].repCatId+')">删除</a>';
                html+='</div>';
                html+=' </td>';
                html+='</tr>';
            }
            var $html = $compile(html)($scope); // 编译
            if($(event.target).text()=="+"){
                $(event.target).parents("tr").after($html);
                $(event.target).text("-");
            }else{
                $(event.target).text("+");
                $(".child"+id).remove();

            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    //添加二级分类
    $scope.append = function(id,name) {
        $scope.fatherBool= true;
        $scope.ParentId=id;
        $scope.ParentName=name;
    }
    //添加二级分类提交
    $scope.addFatherSubmit = function() {

        var data = {
            repCatName:$scope.typesName,
            repCatParentId:$scope.ParentId
        }
        if($scope.typesName=""){
            layer.msg("请输入分类名称!");
        }
        httpService.postList(data,"/repositoryClassify/addRepositoryCategory1").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $scope.fatherBool= false;
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据获取值
        var data1 = {
            repCatParentId:0
        }
        httpService.getList(data1,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            $scope.classification= response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.addBool=false;
    };
    //添加分类提交
    $scope.addSubmit = function() {
        var data = {
            repCatName:$scope.catname,
            repCatParentId:0
        }
        console.log($scope.IsChild);
        console.log(data.repCatParentId);
        if($scope.catname=""){
            layer.msg("请输入分类名称!");
        }
        httpService.postList(data,"/repositoryClassify/addRepositoryCategory1").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据获取值
        var data1 = {
            repCatParentId:0
        }
        httpService.getList(data1,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            $scope.classification= response.data.result;
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
        $scope.fatherBool= false;
        $scope.typesName = '';
        $scope.catname = '';
    };



});

