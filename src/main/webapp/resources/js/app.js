$(function(){
    //下拉
    $(".warehouse-list-title").click(function(){
        if($(this).next().is(":visible")){
            $(this).children(".icon-xiajiantou").hide().css("color","#d4dbef").siblings("i").show().siblings("span").css("color","#333").end().end().end().next().hide();
        }else{
            $(this).children(".icon-xiajiantou").show().css("color","#7a8ce3").siblings("i").hide().siblings("span").css("color","#7a8ce3").end().end().end().next().show();
        }

    });
    $(".child-nav>a").click(function(){
        if($(this).next().is(":visible")){
            $(this).children(".icon-xiajiantou").hide().css("color","#d4dbef").siblings("i").show().siblings("span").css("color","#333").end().end().end().next().hide();
        }else{
            $(this).children(".icon-xiajiantou").show().css("color","#7a8ce3").siblings("i").hide().siblings("span").css("color","#333").end().end().end().next().show();
        }
    });


});
//获取url路径
function GetUrlRelativePath()
{
    var url = document.location.toString();
    var arrUrl = url.split("//");

    var start = arrUrl[1].indexOf("/");
    var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符

    if(relUrl.indexOf("?") != -1){
        relUrl = relUrl.split("?")[0];
    }
    return relUrl;
}
//获取urlqueryString
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}

var app = angular.module('app', []);
//自定义http服务
app.service('httpService', function($http,$q) {
    this.getData = function(url){
        var d = $q.defer();
        $http({
            method: 'GET',
            url: basePath + url
        }).then(function successCallback(response) {
            d.resolve(response);
        }, function errorCallback(response) {
            d.reject("error");
        });
        return d.promise;
    };
    this.getList = function(data,url){
        var d = $q.defer();
        $http({
            method: 'GET',
            url: basePath + url,
            params:data
        }).then(function successCallback(response) {
            d.resolve(response);
        }, function errorCallback(response) {
            d.reject("error");
        });
        return d.promise;
    };
    this.postList = function(data,url){
        var d = $q.defer();
        $http({
            method: 'POST',
            url: basePath + url,
            data:data ,
            headers:{'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj){
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).then(function successCallback(response) {
            d.resolve(response);
        }, function errorCallback(response) {
            d.reject("error");
        });
        return d.promise;
    };
});

app.controller('userController', function($scope,$rootScope, $http,httpService) {
    $rootScope.GetLength = function(str) {
        return str.replace(/[\u0391-\uFFE5]/g,"aa").length;  //先把中文替换成两个字节的英文，在计算长度
    };
    //上面栏目跳转
    $scope.topJumps = function (url) {
        window.location.href=basePath + url;
    }
    //发送请求
    $http({
        method: 'GET',
        url:basePath+ '/communal/initUserInfo'
    }).then(function successCallback(response) {
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
        $scope.depa = response.data.result[0].departmentName;
        $rootScope.name = response.data.result[0].userName;
    }, function errorCallback(response) {
        // 请求失败执行代码
    });
    $rootScope.xz =function(e,num){
        num= num*2;
        var len = GetLength(e.target.value)
        if(len>num){
            layer.msg('请输入'+num+'个字以内!');
            e.target.value = e.target.value.substr(0,num);
        }
        // if()
    }
    //点击知识库管理平台img跳转回主页
    $scope.backIndex = function () {
        window.location.href=basePath + "/backIndex";
    }
    $scope.delBool=false;
    //退出登录
    $scope.cancel=function(){
        $scope.delBool=false;
    }
    $scope.confirm=function(){
        $scope.delBool=false;
        window.location.href="/logout";
    }
    $scope.logout = function () {
        $scope.delBool=true;
    }
    $scope.close=function(){
        $scope.delBool=false;
    }

    });
app.filter("showAsHtml",function($sce){
    return function(input){
        return $sce.trustAsHtml(input);
    }
});


var GetLength = function(str) {
    return str.replace(/[\u0391-\uFFE5]/g,"aa").length;  //先把中文替换成两个字节的英文，在计算长度
};
var filterHTMLTag = function (msg) {
    var msg = msg.replace(/<\/?[^>]*>/g, ''); //去除HTML Tag
    msg = msg.replace(/[|]*\n/, '') //去除行尾空格
    msg = msg.replace(/&npsp;/ig, ''); //去掉npsp
    return msg;
}
function check(str){
    var temp=""
    for(var i=0;i<str.length;i++)
        if(str.charCodeAt(i)>0&&str.charCodeAt(i)<255)
            temp+=str.charAt(i)
    return temp
}
var filterTs = function(msg,cb){
    var pattern = new RegExp("[ `~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]+");
    var result = msg.replace(pattern,'');
    if(cb){
        cb(result);
    }else{
        return result;
    }
}