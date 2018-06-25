
//左边导航控制器
app.controller('navController', function($scope,$rootScope,$http,httpService) {
    //编辑弹框
    $scope.editBool=false;
    $scope.user_edit= function() {
        $scope.editBool=!$scope.editBool;
        $scope.selectDeparment=$scope.id+'';
    };
    $scope.close= function() {
        $scope.editBool=false;
    };

    $scope.changeName = function(){
        console.log($scope.name)
        if($scope.leftname.length>5){
            layer.msg('请输入5个字以内');
            $scope.leftname = $scope.leftname.substr(0,5);
        }
    }
    //左侧个人主页跳转
    $scope.leftJumps = function (url) {
        window.location.href=basePath + url;
    }
    //获取头像姓名
    httpService.getData("/communal/initUserInfo").then(function successCallback(response) {
        if(response.data.result==null) return;
        if(response.data.result[0].avatar==null){

            $scope.headImg = {
                "background":"url("+basePath+"/resources/images/top/user.png) no-repeat center",
                "background-size":"cover"
            }
        }else{
            var urls= response.data.result[0].avatar;
            $scope.headImg = {
                "background":"url("+urls+") no-repeat center",
                "background-size":"cover"
            }
        }
        $scope.url= response.data.result[0].avatar;
        $scope.depa = response.data.result[0].departmentName;
        $scope.name = response.data.result[0].userName;
        $scope.leftname = $scope.name;
        $scope.id=response.data.result[0].departmentId;

    },function errorCallback(response) {
        console.log("err");
    });

    //获取分享数据
    httpService.getData("/communal/getShareQusCount").then(function successCallback(response) {
        $scope.share = response.data.result.myPost;
        $scope.question = response.data.result.myQuestion;
        $scope.core= response.data.result.myIntegral;
    },function errorCallback(response) {
        console.log("err");
    });

    //初始化部门下拉框
    $http({
        method: 'GET',
        url:basePath+ '/departmentManager/getDepartmentList'
    }).then(function successCallback(response) {
        $scope.deparments = response.data.result;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    //文件上传
    $scope.fileChange=function(){
        var files = document.getElementById("upfile").files[0];
        var file = new FormData();
        file.append('file', files);
        $http({
            method: 'POST',
            url: basePath + '/communal/saveHeadImage',
            data:file,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).then(function successCallback(response) {
            var src=response.data.result;
            $scope.url= response.data.result;
           var urlDemo = $('#headImageInfo');
            urlDemo.attr("style","background:url("+src+") no-repeat center;background-size:cover");
            console.log(src);

            if(src!=null){
                $scope.headImg={
                    "background":"url("+src+") no-repeat center",
                    "background-size":"cover",
                    "border": "1px solid #ddd"
                }
            }else{
                $scope.headImg={
                    "background":"url('${basePath}/resources/images/app/select-img.png') no-repeat center",
                    "background-size":"contain"
                }

            }
           // layer.msg(response.data.msg)
        }, function errorCallback(response) {

        });

    }
    //下拉框选项value同步
    $scope.selectChange=function(){
        $rootScope.depa=$('#selectDeparment option:selected').text();
        $scope.depa=$rootScope.depa;
        console.log($rootScope.depa);
    }
    //提交编辑
    $scope.edit_submit=function(){
        var data={
            realName:$scope.leftname,
            departmentId:parseInt($scope.selectDeparment,10),
            fileUrl:$scope.url
        }
        if($scope.leftname==''){
            layer.msg("请填写姓名!");
            return;
        }
        if(parseInt($scope.selectDeparment,10)==0){
            layer.msg("请选择部门!");
            return;
        }
        httpService.postList(data,"/communal/saveHeadChange").then(function successCallback(response) {

            $('#isName').text($scope.leftname);
            $('#navleftname').text($scope.leftname);
            $('#idDepa').html($scope.depa);
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.editBool=false;
    }
});