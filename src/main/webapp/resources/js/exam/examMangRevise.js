/*
* 考试培训-考试管理-批卷js
* */

$(function(){
    $(".warehouse-list-title>span:contains('考试管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
app.controller('batchController', function($scope, $http, httpService) {
    $scope.pageBool=false;
    function submit(page) {
        var data = {
            examId:parseInt($("#examId").val(),10),
            pageIndex:1,
            pageSize:10
        };
        httpService.getList(data ,"/examModule/queryCheckPaperList").then(function successCallback(response) {
            var code = response.data.code;
            console.log(response.data.result);
            if(code == 1){
                $scope.examList = response.data.result;
                console.log($scope.examList);
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });

        //获取分页
        var data1={
            examId:parseInt($("#examId").val(),10)
        }
        console.log(data1);
        httpService.getList(data1,"/examModule/queryCheckPaperListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            console.log(response.data.result);
            console.log($scope.allPage);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    submit(1);
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
        submit(page);

    };
    $scope.batch = function() {
      $("body").css("overflow","hidden");
        alert("fd")
    }
    $scope.batch=function(id){


        $scope.pageBool=true;
        var data = {
            examId:parseInt($("#examId").val(),10),
            userId:id
        };
        httpService.postList(data ,"/examModule/updateExamAnswer").then(function successCallback(response) {
            $scope.exerList0= response.data.result.ExerList[0].ExerInfoList;
            $scope.exerList1= response.data.result.ExerList[1].ExerInfoList;
            $scope.exerList2= response.data.result.ExerList[2].ExerInfoList;
            $scope.exerList3= response.data.result.ExerList[3].ExerInfoList;
        },function errorCallback(response) {
            console.log("err");
        });
    }
    $scope.submit=function(){
        var str='';
        console.log($scope.exerList3);
        arr= [];
        for(var i =0;length=$scope.exerList3.length,i<length;i++){
           arr.push($("."+$scope.exerList3[i].ExercisesId).val());
            console.log($(".core"+$scope.exerList3[i].ExercisesId).val());
            var num = $(".core"+$scope.exerList3[i].ExercisesId).val();
            arr.push(num);
            console.log(num);
            console.log(arr);
        }
        console.log(arr);
        for(var i =0;length=$scope.exerList3.length,i<length;i++){
            str+=$scope.exerList3[i].ExercisesId+","+arr[i]+"_";
        }
        str = str.substring(0,str.length - 1);
        console.log(str);return;

        var data = {
            examId:parseInt($("#examId").val(),10),
            userId:id

        };
        httpService.postList(data ,"/examModule/updateUserTotalScore").then(function successCallback(response) {

        },function errorCallback(response) {
            console.log("err");
        });
        $("body").css("overflow","auto");
    }
    $scope.close=function(){
        $("body").css("overflow","auto");
        $scope.pageBool=false;
    }
});
