/*
* 权限管理js
* */

//页面初始化选中左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('账号管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('权限设置')").css({"background":"#7a8ce3","color":"#fff"});
});

//控制器,初始化显示所有角色信息
app.controller('permissionController', function($scope, $http,httpService,$timeout) {
    $scope.addBool=false;
    $scope.editBool=false;
    $scope.delBool=false;
    $scope.roleName="";
    $scope.curr=1;

    function submit(page) {
        //获取数据
        var data = {
            roleName:$scope.search?($scope.search):'',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/roleManager/getRoleList").then(function successCallback(response) {
            $scope.roles = '';
            if (response.data.code==2){
                layer.msg("暂无相关搜索内容!")
            }

            $scope.roles = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        //获取分页页数
        var data1 = {
            roleName:$scope.search?($scope.search):''
        }
        httpService.getList(data1,"/roleManager/getRoleListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //初始化
    submit(1);

     //添加角色弹框
    $scope.addRole= function() {
        $scope.addBool=!$scope.addBool;
        httpService.getData("/moduleManager/getAllModuleInfo").then(function successCallback(response) {
            $scope.roleLabel = response.data.result;
            console.log( response.data.result);
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //添加提交角色信息
    $scope.selected = [];
    var updateSelected = function (action, id) {
        if (action == 'add' && $scope.selected.indexOf(id.moduleId) == -1) $scope.selected.push(id.moduleId);
        if (action == 'remove' && $scope.selected.indexOf(id.moduleId) != -1) $scope.selected.splice($scope.selected.indexOf(id.moduleId), 1);
    };
    //更新数据的选择
    $scope.updateSelection = function ($event, id) {
        var checkbox = $event.target;
        var action = (checkbox.checked ? 'add' : 'remove');
        console.log(action);
        id.checked = checkbox.checked;
        if(id.subModelList.length !== 0){
            for (var i =0;i<id.subModelList.length;i++){

                $scope.updateSelection($event,id.subModelList[i])
            }
        }
        updateSelected(action, id);
    };
    $scope.isSelected = function (id) {
        return $scope.selected.indexOf(id) >= 0;
    };
    $scope.addInfo=function(){
        var str =$scope.selected.join(',');
        var data = {
            roleName:$scope.roleName,
            modules:str
        }
        if (data.roleName == "") {
            layer.msg("请输入角色名"); return;
        }
        if(!data.modules || data.modules == ""){
            return layer.msg('请选择权限');
        }
        httpService.postList(data,"/roleManager/saveRoleInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新角色
        submit($scope.curr);

        $scope.selected = [];
        $scope.addBool=false;
        $scope.roleName="";
    };
    //编辑角色弹框
    $scope.roleEdit= function(id) {
        var data={
            roleId:id
        }
        $scope.editId=id;
        var data1={
            roleIdList:id+''
        }

        $scope.editBool=!$scope.editBool;
        //获取角色名称
        httpService.getList(data,"/roleManager/getRoleInfoDetailByRoleId").then(function successCallback(response) {
            $scope.editRoleName = response.data.result.RoleName;
            console.log( response.data.result);
        },function errorCallback(response) {
            console.log("err");
        });
        //获取权限列表
        httpService.getData("/moduleManager/getAllModuleInfo").then(function successCallback(response) {
            $scope.roleLabel = response.data.result;
            console.log( response.data.result);
        },function errorCallback(response) {
            console.log("err");
        });
        //获取权限id
        httpService.getList(data1,"/roleManager/getModuleInfoByRoleId").then(function successCallback(response) {
           if(response.data.result==null) return;
            $scope.selected = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
    };
    // 编辑角色修改
    $scope.editInfo= function() {
        var str =$scope.selected.join(',');
        var data = {
            roleName:$scope.editRoleName,
            newModules:str,
            roleId:$scope.editId+''
        }
        if (data.editRoleName == "") {
            layer.msg("请输入角色名"); return;
        }
        if(!data.newModules || data.newModules == ""){
            return layer.msg('请选择权限');
        }
        httpService.postList(data,"/roleManager/updateRoleInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $scope.editBool=false;
            $scope.selected=[];
        },function errorCallback(response) {
            console.log("err");
        });
        //更新数据
        submit($scope.curr);
    };

    //关闭弹框
    $scope.close= function() {
         $scope.selected = [];
         $scope.roleName="";
         $scope.addBool=false;
         $scope.delBool=false;
         $scope.editBool=false;
    };

    //根据角色名搜索
    $scope.submit = function() {
        submit(1);
    };
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            submit(1);
        }
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
            roleId:$scope.id
        }
        $scope.roles.splice($scope.index, 1);
        httpService.postList(data,"/roleManager/deleteRoleInfo").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        submit($scope.curr);
        $scope.delBool=false;
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
        //更新数据
        submit(page);

    };
});