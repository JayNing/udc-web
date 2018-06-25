/*
* 专家管理-添加专家
* */

//提交专家信息
app.controller('expertController', function ($scope, $http,httpService,$timeout) {
    $scope.src=null;
    $scope.specialist={
        name:'',
        academic:'',
        summary:'',
        honor:''
    }
    $scope.headImg={
        "background":"url("+basePath+"/resources/images/app/select-img.png) no-repeat center",
        'background-size':'contain'
    }
    //添加提交
    $scope.expertAdd = function () {
        var data = {
            headUrl:$scope.url,
            speName: $scope.specialist.name,
            jobTitle: $scope.specialist.academic,
            speProfile: $scope.specialist.summary,
            honors: $scope.specialist.honor
        };
        //对输入值进行检验,非空判定,字符的合法性
        if (data.speName == "") {
            layer.msg("请输入姓名!");
            return;
        }
        if (data.jobTitle == "") {
            layer.msg("请输入职称!");
            return;
        }
        if (data.speProfile == "") {
            layer.msg("请输入简介!");
            return;
        }
        if (data.honors == "") {
            layer.msg("请填写个人荣誉!");
            return;
        }

        httpService.postList(data,"/specialistManager/saveSpecialistInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $timeout(function(){
                window.location.href=basePath + "/knlgBank/3"
            },1000);


        },function errorCallback(response) {
            console.log("err");
        });


    };
    //编辑提交
    $scope.expertEdit = function () {
        var data = {
            speId:parseInt($("#speeditid").val(),10),
            headUrl:$scope.url,
            speName: $scope.specialist.name,
            jobTitle: $scope.specialist.academic,
            speProfile: $scope.specialist.summary,
            honors: $scope.specialist.honor
        };
        //对输入值进行检验,非空判定,字符的合法性
        console.log(data);
        if (data.speName == "") {
            layer.msg("请输入姓名!");
            return;
        }
        if (data.jobTitle == "") {
            layer.msg("请输入职称!");
            return;
        }
        if (data.speProfile == "") {
            layer.msg("请输入简介!");
            return;
        }
        if (data.honors == "") {
            layer.msg("请填写个人荣誉!");
            return;
        }
        httpService.postList(data,"/specialistManager/updateSpecialistInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $timeout(function(){
                window.location.href=basePath + "/knlgBank/3"
            },1000);
        },function errorCallback(response) {
            console.log("err");
        });


    };
    //判断添加或编辑
    if($("#speeditid").val()==""){
        $scope.editBool=false;
        $scope.addBool=true;
    }else{
        $scope.editBool=true;
        $scope.addBool=false;
        var data = {
            speId:parseInt($("#speeditid").val(),10)
        };
        httpService.getList(data,"/specialistManager/getSpecialistInfoDetail").then(function successCallback(response) {
            console.log(response.data.result);
            $scope.headImg={
                "background":"url("+response.data.result.headUrl+") no-repeat center",
                "background-size":"cover"
            }
            $scope.url= response.data.result.headUrl;
            $scope.specialist.name = response.data.result.speName;
            $scope.specialist.academic = response.data.result.jobTitle;
            $scope.specialist.summary = response.data.result.speProfile;
            $scope.specialist.honor = response.data.result.honors;
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //文件上传
    $scope.fileChange=function(){
        var files = document.getElementById("file").files[0];
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
            $scope.url=response.data.result;
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
            //layer.msg(response.data.msg)
        }, function errorCallback(response) {

        });

    }

});

        