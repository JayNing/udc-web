/*
* 考试培训-考试管理js
* */
$(function(){
    $(".warehouse-list-title>span:contains('考试管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
app.controller('examManagerController', function($scope, $http, httpService) {
    $scope.assessmentBool=false;
    $scope.examName = '';
    $scope.showStatus = 1;
    $scope.curr=1;
    $scope.exam={
        examName:'',
        startTime:'',
        endTime:'',
        showStatus:'0'
    }
    function submit(page) {
        var data = {
            'title':$scope.exam.examName,
            'start1Time':$scope.exam.startTime,
            'end1Time':$scope.exam.endTime,
            'assmStatus':parseInt($scope.exam.showStatus,10)>0?parseInt($scope.exam.showStatus,10):'',
            'pageIndex':page,
            'pageSize':10
        };
        console.log(data);
        httpService.getList(data ,"/examModule/queryExamManageList").then(function successCallback(response) {
            var code = response.data.code;
            console.log(response.data.result);
            if(code == 1){
                $scope.examList = response.data.result;
                for(var i=0;length=$scope.examList.length,i<length;i++){
                    $scope.examList[i].CreateTime=$scope.examList[i].CreateTime.substr(0,10);
                }
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });

        //获取分页
        httpService.getList(data,"/examModule/queryExamManageListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    submit(1);
    $scope.submitManager=function(){
        submit($scope.curr);
    }
    $scope.close=function(){
        $scope.assessmentBool=false;
        $scope.delBool=false;
    }
    //评估弹框
    $scope.assessment=function(id){
        $scope.assessmentBool=true;
        $scope.id=id;
    }

    //批卷跳转
    $scope.expertEdit = function (id) {
        window.location.href=basePath + "/examRevise?ExamId="+id;
    }

    //统计跳转
    $scope.examCount = function (id) {
        window.location.href=basePath + "/examCount?ExamId="+id;
    }

    //详情跳转
    $scope.examDetail = function (id) {
        window.location.href=basePath + "/detailPage?ExamId="+id;
    }

    //评估提交
    $scope.evaluationSubmit=function(){
        var data={
            assmContent:$scope.resultsEvaluation,
            examId:$scope.id
        }
        console.log(data);
        httpService.postList(data,"/examModule/addAssessment").then(function successCallback(response) {
            console.log(response.data);
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.assessmentBool=false;
    }
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            submit($scope.curr);
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
            examId:$scope.id
        }
        console.log(1111);
        $scope.examList.splice($scope.index, 1);
        httpService.postList(data,"/examModule/deleteExam").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        submit($scope.curr);
        $scope.delBool=false;
    }
//绑定点击事件
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

        //获取数据
        submit(page);
    };




});