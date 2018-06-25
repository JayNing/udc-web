/*
* 知识仓库文章类型js
* */

$(function(){
    $(".warehouse-list-title>span:contains('账号管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('模块管理')").css({"background":"#7a8ce3","color":"#fff"});
});

//右边控制器,初始化显示信息
app.controller('moduleController', function($scope, $rootScope,$http,httpService,$compile) {
    $scope.addBool=false;
    $scope.editBool=false;
    $scope.delBool=false;
    $scope.parentId=0;
    $scope.parentName=0;
    $scope.editModuleName='';
    $scope.editCode1='';
    $scope.moduleName='';
    $scope.code='';
    $scope.IsChild=1;

    function submit(){
        var data={
            moduleId:0
        }
        httpService.getList(data,"/moduleManager/getNextModuleInfoByParentId").then(function successCallback(response) {
            $scope.modules= response.data.result;
            console.log(response.data.result);
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //初始化表格数据获取值
    submit();

    $scope.check = function(e,num){
        filterTs(check(e.target.value),function(result){
            e.target.value = result;
            $rootScope.xz(e,num);
        });



    }
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
            moduleId: $scope.id
        }
        $scope.modules.splice($scope.index, 1);
        httpService.postList(data,"/moduleManager/deleteModuleInfo").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据
        submit();
        $scope.delBool=false;
    }

    //根据id编辑分类
    $scope.edit = function(id,parentId) {
        $scope.editBool= true;
        var data = {
            moduleId:id
        }
        $scope.id=id;
        $scope.parentId=parentId;
        httpService.getList(data,"/moduleManager/getModuleInfoByModuleId").then(function successCallback(response) {
          $scope.editModuleName = response.data.result.moduleName;
          var str = response.data.result.moduleCode.substring(response.data.result.moduleCode.length-3);
          $scope.editCode = str;
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //根据id编辑提交分类
    $scope.editSubmit = function() {
        var data = {
            moduleId:$scope.id,
            parentId:$scope.parentId,
            ModuleName:$scope.editModuleName,
            moduleCode:$scope.editCode
        }
        if($scope.editModuleName=="" && !$scope.editModuleName){
            layer.msg("请输入模块名称");return
        }
        if($scope.editCode=="" && !$scope.editCode){
            layer.msg("请输入模块编码");return
        }
        httpService.postList(data,"/moduleManager/updateModuleInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据
        submit();
        $scope.editBool=false;
    };

    //点击加载二级分类
    $scope.dropDown = function(id,event) {
        var data = {
            moduleId:id
        }
        var left = event.target.offsetLeft + 26;
        httpService.getList(data,"/moduleManager/getNextModuleInfoByParentId").then(function successCallback(response) {
            $scope.twoClassification= response.data.result;
            console.log($scope.twoClassification);
            var html='';
            for(var i = 0;length=$scope.twoClassification.length,i<length;i++){
                html+='<tr class="child'+id+'">';
                html+='<td ng-click="append('+$scope.twoClassification[i].ModuleId+',\''+$scope.twoClassification[i].ModuleName+'\','+'$event)" class="two-level" style="border-right-width:0;padding-left:'+left+'px;"><span class="dropDown"><i ng-click="dropDown('+$scope.twoClassification[i].ModuleId+',$event)"  ng-show="'+$scope.twoClassification[i].IsPlus+'==1">+</i></span>'+$scope.twoClassification[i].ModuleName+'</td>';
                html+='<td ng-click="append('+$scope.twoClassification[i].ModuleId+',\''+$scope.twoClassification[i].ModuleName+'\','+'$event)" style="border-left-width:0;border-right-width:0;">'+ $scope.twoClassification[i].ModuleCode +'</td>';
                html+='<td>';
                html+='<div class="books-btn">';
                html+='<a href="javascript:;"   ng-click="edit('+$scope.twoClassification[i].ModuleId+','+id+')">编辑</a>';
                html+='<a href="javascript:;"  ng-click="del('+$scope.twoClassification[i].ModuleId+')">删除</a>';
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
    //添加分类
    $scope.addModule = function() {
        $scope.addBool= true;
    }
    //添加二级分类
    $scope.append = function(id,name,event) {
        if($scope.parentId!=id){
            $(event.target).parents("tr").css("background-color","rgb(187, 194, 227)").siblings("tr").css("background-color","rgb(255, 255, 255)");
            $scope.IsChild=2;
            $scope.parentName=name;
        }else{0
            if($(event.target).parents("tr").css("background-color")!="rgb(255, 255, 255)"){
                $(event.target).parents("tr").css("background-color","rgb(255, 255, 255)").siblings("tr").css("background-color","rgb(255, 255, 255)");
                $scope.IsChild=1;
                $scope.parentName="";
            }else{
                $(event.target).parents("tr").css("background-color","rgb(187, 194, 227)").siblings("tr").css("background-color","rgb(255, 255, 255)");
                $scope.IsChild=2;
                $scope.parentName=name;
            }

        }
        $scope.parentId=id;

    }
    //添加分类提交
    $scope.addSubmit = function() {
        if( $scope.IsChild==1){
            var data = {
                ModuleName:$scope.moduleName,
                moduleCode:$scope.code,
                parentId:0
            }
        }else if($scope.IsChild==2){
            var data = {
                ModuleName:$scope.moduleName,
                moduleCode:$scope.code,
                parentId:$scope.parentId
            }
        }

        if($scope.moduleName=="" && !$scope.moduleName){
            layer.msg("请输入模块名称");return
        }
        if($scope.code=="" && !$scope.code){
            layer.msg("请输入模块编码");return
        }
        httpService.postList(data,"/moduleManager/addModuleInfo").then(function successCallback(response) {
            $scope.moduleName="";
            $scope.code="";
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据
        submit();
        $scope.addBool=false;
    };
    //关闭弹框
    $scope.close= function() {
        $scope.addBool=false;
        $scope.editBool=false;
        $scope.delBool=false;
    };



});

