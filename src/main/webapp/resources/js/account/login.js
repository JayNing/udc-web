app.controller('loadController', function ($scope,$rootScope,$http) {

    var version='';
    var browser={
        versions:function(){
            var u = navigator.userAgent;
            return {
                window :u.indexOf('Windows Phone') > -1,
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器


            };
        }(),
        language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }

    if(!browser.versions.mobile){
        version=0;
    }else if(browser.versions.ios){
        version=2;
    }else if(browser.versions.window){
       version=3;
    }else if(browser.versions.android){
        version=1;
    }


    //回车键点击搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            console.log("这是测试!!")
            $scope.login();
        }
    }


    $scope.account="";
    $scope.password="";
    $scope.login=function(){
        $rootScope.isLoading = true;
       console.log(1);
       var data = {
            loginName: $scope.account,
            passwd: $scope.password,
            platformType:version
        };
       if (data.loginName == ""){
           layer.msg("请输入登录名!"); $rootScope.isLoading = false;return;
       }
        if (data.passwd == ""){
            layer.msg("请输入密码!"); $rootScope.isLoading = false;return;
        }
        $http({
            method: 'GET',
            url: basePath + '/userLogin/login',
            params:data
        }).then(function successCallback(response) {
            $rootScope.isLoading = false;
            if (response.data.code==1){
                window.location.href=basePath + "/";
            }else{
                layer.msg(response.data.msg);
                return;
            }

        }, function errorCallback(response) {
            $rootScope.isLoading = false;
            console.log("err");
        });
    }



})

