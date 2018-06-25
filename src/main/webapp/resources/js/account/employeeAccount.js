/*
* 账号管理-员工账号js
* */
//分页指令

//页面初始化选中左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('账号管理')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('员工账号')").css({"background":"#7a8ce3","color":"#fff"});
});
//控制器    
    app.controller('accountController', function ($scope,$rootScope, $http,httpService) {
        $scope.addBool = false;
        $scope.editBool = false;
        $scope.bgBool=false;
        $scope.delBool=false;
        $scope.editAccountBool = false;
        $scope.roleBool=false;
        $scope.selectDepartment="0";
        $scope.curr=1;
        $scope.search = "";
        $scope.accountDepartment="0";
        $scope.check = function(e,num){
            e.target.value = check(e.target.value);
            $rootScope.xz(e,num);
        }
        $scope.addAccount={
            userName:'',
            password:'',
            names:'',
            roleIdList:'',
            moduleIdList:''
        }
        function submit(page){
            if (parseInt($scope.selectDepartment,10) > 0) {
                data = {
                    name: $scope.search ? ($scope.search) : '',
                    departmentId: parseInt($scope.selectDepartment,10),
                    pageIndex: page,
                    pageSize: 10
                }
            } else if (parseInt($scope.selectDepartment,10)==0) {
                data = {
                    name: $scope.search ? ($scope.search) : '',
                    pageIndex: page,
                    pageSize: 10
                }
            }
            //更新账户列表
            httpService.getList(data,"/accountManger/getUserInfo").then(function successCallback(response) {
                if(response.data.code == 2) layer.msg(response.data.msg);
                $scope.accounts = response.data.result;

            },function errorCallback(response) {
                console.log("err");
            });
            //更新分页页数
            if (parseInt($scope.selectDepartment,10) > 0) {
                data1 = {
                    name: $scope.search ? ($scope.search) : '',
                    departmentId: parseInt($scope.selectDepartment,10)
                }
            } else if (parseInt($scope.selectDepartment,10)==0) {
                data1= {
                    name: $scope.search ? ($scope.search) : ''
                }
            }
            httpService.getList(data1,"/accountManger/getUserInfoCount").then(function successCallback(response) {
                $scope.curr = page;
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange(page, $scope.allPage, 5);
            },function errorCallback(response) {
                console.log("err");
            });

        }
        //初始化账号列表
        submit(1);
        //初始化获取所有部门名称
        httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
            $scope.sites = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });

        //关闭弹框
        $scope.close = function () {
            $scope.selected = [];
            $scope.selected1 = [];
            $scope.selectedRole=[];
            $scope.roleName='';
            $scope.addBool = false;
            $scope.editBool = false;
            $scope.editAccountBool = false;
            $scope.delBool=false;
        };

        //根据用户名部门搜索账号
        $scope.submit = function () {
            submit(1);
        };
        //回车键搜索
        $scope.enterEvent = function(e) {
            var keycode = window.event?e.keyCode:e.which;
            if(keycode==13){
                submit(1);
            }
        }
        //编辑账号信息
        $scope.accountsEdit = function (id) {
            $scope.editAccountBool = !$scope.editAccountBool;
            var data = {
                userId: id
            }
            $scope.editId=id;
            httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
                $scope.departments1 = response.data.result;
            },function errorCallback(response) {
                console.log("err");
            });
            var data = {
                userId: id
            }
            //获取账户信息
            httpService.getList(data,"/accountManger/getUserInfosDetailByUserId").then(function successCallback(response) {
                $scope.editAccount = response.data.result;
                console.log(response.data.result);
                $scope.userName = $scope.editAccount[0].LoginName;
                $scope.password = $scope.editAccount[0].PassWd;
                $scope.accountDepartment1 = $scope.editAccount[0].DepartmentId.toString();
                $scope.selected1 = response.data.result[0].RoleIdList;
                $scope.selected = response.data.result[0].ModuleIdList;
                $scope.selectedRole=response.data.result[0].RoleNameList;
                $scope.roleName=$scope.selectedRole.join(',');
                $scope.name = response.data.result[0].RealName;
                console.log( response.data.result);
            },function errorCallback(response) {
                console.log("err");
            });
            //获取角色模块
            var data = {
                roleName:'',
                pageIndex:1,
                pageSize:1000
            }
            httpService.getList(data,"/roleManager/getRoleList").then(function successCallback(response) {
                $scope.roles = response.data.result;
                console.log($scope.roles);
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


        };
        //编辑提交账号信息
        $scope.accountSubmit = function () {
            $scope.moduleIdList=$scope.selected.join(',');
            $scope.roleIdList=$scope.selected1.join(',');
            var data = {
                userId: $scope.editId,
                realName: $scope.name,
                avatar:"",
                passWd: $scope.password,
                departmentId: $scope.accountDepartment1,
                roleIdList:$scope.roleIdList,
                moduleIdList:$scope.moduleIdList

            };

            //对输入值进行检验,非空判定,字符的合法性
            if (data.loginName == "") {
                layer.msg("请输入用户名!");
                return;
            }
            var regEx = /^[a-zA-Z0-9_]{2,20}$/;
            if (!regEx.test(data.loginName)) {
                layer.msg("请输入2-20位英文字符、数字或下划线的组合!");
                return;
            }
            if (data.name == "") {
                layer.msg("请输入姓名!");
                return;
            }
            if (data.password == "") {
                layer.msg("请输入密码!");
                return;
            }
            if(data.roleIdList == "" || !data.roleIdList){
                console.log(data.roleIdList);
                return layer.msg('请选择角色');
            }
            if (data.departmentId == 0) {
                layer.msg("请选择所属部门!");
                return;
            }
            httpService.postList(data,"/accountManger/updateUserInfo").then(function successCallback(response) {
                layer.msg("账号信息修改成功!");
                $scope.selected = [];
                $scope.selected1 = [];
            },function errorCallback(response) {
                console.log("err");
                layer.msg("请输入正确的用户信息!")
            });
            submit($scope.curr);
            $scope.editAccountBool = false;
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
                userId:$scope.id
            }
            $scope.accounts.splice($scope.index, 1);
            httpService.postList(data,"/accountManger/deleteUserInfo").then(function successCallback(response) {
                layer.msg(response.data.msg)
            },function errorCallback(response) {
                console.log("err");
                layer.msg("请输入正确的用户信息!")
            });
            submit($scope.curr);
            $scope.delBool=false;
        }


        $scope.roleHide=function(){
            $scope.roleBool=false;
            $scope.bgBool=false;
        }
        $scope.roleShow=function(){
            $scope.roleBool=!$scope.roleBool;
            $scope.bgBool=true;
            console.log($scope.bgBool);
        }
        //发送公告弹框
        $scope.release = function () {
            $scope.noticeBool = !$scope.noticeBool;
        };
        $scope.addNotice = function(){
            var data = {
                title : $scope.noticeTitle,
                messageContent: $scope.noticeContent
            }
            console.log(data);
            if (!data.title) {
                layer.msg("请输入公告标题!");
                return;
            }
            if (!data.messageContent) {
                layer.msg("请输入公告内容!");
                return;
            }
            httpService.postList(data,"/messageManage/addPlatformNotice").then(function successCallback(response) {
                if (response.data.code==1){
                    layer.msg(response.data.msg);
                }
            },function errorCallback(response) {
                console.log("err");
            });
            $scope.noticeBool = false;
        }
        //添加账户弹框
        $scope.add = function () {
            $scope.addBool = !$scope.addBool;
           var data = {
                 roleName:'',
                 pageIndex:1,
                 pageSize:1000
             }
             //获取角色模块
             httpService.getList(data,"/roleManager/getRoleList").then(function successCallback(response) {
                 $scope.roles = response.data.result;
                 console.log($scope.roles);
             },function errorCallback(response) {
                 console.log("err");
             });
             //获取部门
             httpService.getData("/departmentManager/getDepartmentList").then(function successCallback(response) {
                 $scope.departments = response.data.result;
             },function errorCallback(response) {
                 console.log("err");
             });
             //获取权限模块
             httpService.getData("/moduleManager/getAllModuleInfo").then(function successCallback(response) {
                 $scope.roleLabel = response.data.result;
                 console.log( response.data.result);
             },function errorCallback(response) {
                 console.log("err");
             });
             $scope.accountDepartment = "0";
        };
        //角色选择
        $scope.selected1 = [];
        $scope.selectedRole = [];
        //更新数据的选择
        $scope.updateSelection1 = function ($event, id,name) {
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add' : 'remove');
            updateSelected1(action, id,name);
        };
        $scope.isSelected1 = function (id) {
            return $scope.selected1.indexOf(id) >= 0;
        };
        var updateSelected1 = function (action, id,name) {
            console.log(action);
            if (action == 'add' && $scope.selected1.indexOf(id) == -1){
                $scope.selected1.push(id);
                $scope.selectedRole.push(name);
            }
            if (action == 'remove' && $scope.selected1.indexOf(id) != -1){
                $scope.selected1.splice($scope.selected1.indexOf(id), 1);
                $scope.selectedRole.splice($scope.selectedRole.indexOf(name), 1);
            }
            console.log("角色");
            console.log($scope.selectedRole);
            console.log($scope.selected1);
            console.log($scope.selected);
            $scope.roleName=$scope.selectedRole.join(',');

            var data = {
                roleIdList:$scope.selected1.join(',')
            }

            httpService.getList(data,"/roleManager/getModuleInfoByRoleId").then(function successCallback(response) {

                if(response.data.code==1){
                    $scope.selected = response.data.result;
                }else{
                    $scope.selected=[];
                }
                console.log($scope.selected);
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
        //添加提交账户信息
        $scope.addAccount = function () {
            $scope.addAccount.moduleIdList=$scope.selected.join(',');
            $scope.addAccount.roleIdList=$scope.selected1.join(',');
            //对输入值进行检验,非空判定,字符的合法性


            if(!$scope.addAccount.userName){
                layer.msg("请输入用户名！");
                return;
            }
            if(!$scope.addAccount.password){
                layer.msg("请输入密码！");
                return;
            }
            //正则判断输入的值,2-20位字符,下划线或数字
            var regEx = /^[a-zA-Z0-9_]{2,20}$/;
            if (!regEx.test($scope.addAccount.password)) {
                layer.msg("请输入2-20位英文字符、数字或下划线的组合!");
                return;
            }
            if(!$scope.addAccount.names){
                layer.msg("请输入姓名！");
                return;
            }
            if($scope.addAccount.roleIdList.length=='0'){
                layer.msg("请选择角色!");
                return;
            }
            if($scope.accountDepartment=='0'){
                layer.msg("请选择所属部门!");
                return;
            }
            if($scope.addAccount.moduleIdList.length=='0'){
                layer.msg("请选择权限!");
                return;
            }
            var data = {
                loginName:  $scope.addAccount.userName,
                passWd:  $scope.addAccount.password,
                realName:  $scope.addAccount.names,
                departmentId: $scope.accountDepartment,
                roleIdList:$scope.addAccount.roleIdList,
                moduleIdList:$scope.addAccount.moduleIdList
            };



            //提交添加
            httpService.postList(data,"/accountManger/saveUserInfo").then(function successCallback(response) {
                layer.msg(response.data.msg);
                $scope.selected = [];
                $scope.selected1 = [];
                $scope.selectedRole=[];
                $scope.addAccount={};
                $scope.accountDepartment="0";
                $scope.roleName="";
                $scope.addBool = false;
                // window.location.href=basePath+"/accountManager/1";
            },function errorCallback(response) {
                console.log("err");
                layer.msg("请输入正确的用户信息!");
            });
            //更新账户
            submit($scope.curr);

        };

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
                    if(page==$scope.allPage1){
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
            //更新账户
            submit(page);


        };
    });
