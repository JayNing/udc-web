/*
* 图书借阅js
* */

$(function(){
    $(".header li a:contains('资源管理')").addClass("hover");
});

//右边图书控制器,初始化显示图书信息
app.controller('booksController', function($scope,$http,httpService,$timeout) {
    $scope.borrowBool=false;
    $scope.borrowTime="";
    $scope.search="";
    $scope.startTime="";
    $scope.endTime="";
    $scope.curr=1;
    //初始化表格数据获取值
    var data = {
        param:'',
        start1Time:'',
        end1Time:'',
        pageIndex:1,
        pageSize:10
    }
    //发送请求
    httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
        $scope.books = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });

//初始化分页页数
    var data1 = {
        param:'',
        start1Time:'',
        end1Time:''
    }
    httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
        //返回页数范围（用来遍历）
    },function errorCallback(response) {
        console.log("err");
    });

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
        //获取图书数据
        var data = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1 = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //显示弹框
    $scope.borrows = function(pkId,flag) {
        if(flag!=1){
            layer.msg("书籍已被借阅，请等待归还后继续借阅！");
            return;
        }
        httpService.getData("/communal/initUserInfo").then(function successCallback(response) {
            $scope.userDepartment = response.data.result[0].departmentName;
            $scope.name = response.data.result[0].userName;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.borrowBool=true;
        $scope.id=pkId;
        var date = new Date();
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var newdate = date.getFullYear() + "-" + month + "-" + currentDate;
        $scope.endTime=newdate;
    };
    //关闭弹框
    $scope.close= function() {
        $scope.borrowBool=false;
    };
    //根据输入信息搜索图书信息
    $scope.submit = function() {
        //获取值
        var data = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        //发送请求
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确搜索内容!")
        });
        //搜索获取页数
        var data1 = {
            param:$scope.search,
            start1Time:$scope.startTime,
            end1Time:$scope.endTime
        }
        httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
            //返回页数范围（用来遍历）
            $scope.curr = 1;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(1, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.submit();
        }
    }
    //借阅时间检测
    $scope.setTime=function(){
           $scope.borrowTime=$scope.borrowTime.replace(/\D/g,'');
            if($scope.borrowTime>1000){
                $scope.borrowTime=1000;
                layer.msg("借阅天数不能大于1000天!");
            }
            var date = new Date();
            date.setDate(date.getDate()+Math.ceil($scope.borrowTime));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var newdate = date.getFullYear() + "-" + month + "-" + currentDate;
            $scope.endTime=newdate;
    }
    //提交借阅记录
    $scope.borrowSubmit=function(){
        //根据登录用户信息获取userId,以及借阅时长
        var data = {
            bkPkId:$scope.id,
            borrowDays:$scope.borrowTime,
            remark:''

        }
        if ($scope.borrowTime =="") {
            layer.msg("请输入借阅天数!"); return;
        }
        httpService.postList(data,"/bookManager/saveBorrowInfo").then(function successCallback(response) {
            if(response.data.code==1){
                $scope.borrowBool=false;
                $scope.borrowTime="";
                layer.msg(response.data.msg);
                $timeout(function () {
                    window.location.replace(location.href);
                },500)
            }
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确信息!")
        });

        //提交记录动态刷新
        var data = {
            param:$scope.search,
            start1Time:$scope.startTime,
            end1Time:$scope.endTime,
            pageIndex:$scope.curr,
            pageSize:10
        }
        //发送请求
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确搜索内容!")
        });

    };

});