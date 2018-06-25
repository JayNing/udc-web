/*
* 考试培训-发起考试js
* */


$(function() {
    $(".warehouse-list-title>span:contains('发起考试')").parents(".warehouse-list").addClass("hover");
    $(".header  a:contains('考试培训')").addClass("hover");

    $(".additions-top").click(function(){
        if($(this).children("img").attr("src")==basePath+"/resources/images/app/drop-down.png"){
            $(this).next().show().end().children("img").attr("src",basePath+"/resources/images/app/minus.png");
        }else{
            $(this).next().hide().end().children("img").attr("src",basePath+"/resources/images/app/drop-down.png").end().next().children("a").removeClass("hover");
        }
      //  $(".additions-right").outerHeight($(".additions-left").outerHeight());
    });

    $("#addExaminee .additions-user a").click(function(){
        if($(this).hasClass("hover")){
            $(this).removeClass("hover");
            $("#addExaminee .additions-right a[data-content='"+ $(this).parent().prev().children(1).text() + $(this).text() + $(this).index() +"']").remove();
        }else{
            $(this).addClass("hover");
            $("#addExaminee .additions-right").append('<a href="javascript:;" data-department="'+ $(this).parent().prev().children(1).text() + '-' + $(this).text()+'" data-content="'+  $(this).parent().prev().children(1).text() + $(this).text() + $(this).index() +'" data-department="'+ $(this).parent().prev().children(1).text() +'">'+$(this).text()+'</a>');
        }
    });
    $("#addReader .additions-user a").click(function(){
        if($(this).hasClass("hover")){
            $(this).removeClass("hover");
            $("#addReader .additions-right a[data-content='"+ $(this).parent().prev().children(1).text() + $(this).text() + $(this).index() +"']").remove();
        }else{
            $(this).addClass("hover");
            $("#addReader .additions-right").append('<a href="javascript:;" data-department="'+ $(this).parent().prev().children(1).text() + '-' + $(this).text()+'" data-content="'+  $(this).parent().prev().children(1).text() + $(this).text() + $(this).index() +'" data-department="'+ $(this).parent().prev().children(1).text() +'">'+$(this).text()+'</a>');
        }
    });
    var readerName,examineeName;

    $("#addExaminee .additions-btn").click(function(){
         examineeName = [];
        for(var i=0;i<$("#addExaminee .additions-right a").length;i++){
          examineeName.push($("#addExaminee .additions-right a").eq(i).attr("data-department"))
        }
        alert(examineeName)
        return false;
    });
    $("#addReader .additions-btn").click(function(){
        readerName=[];
        for(var i=0;i<$("#addReader .additions-right a").length;i++){
          readerName.push($("#addReader .additions-right a").eq(i).attr("data-department"))
        }
        alert(readerName)
        return false;
    });

    //日期
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), nowTemp.getHours(),nowTemp.getMinutes(), nowTemp.getSeconds(), 0);
    var checkin = $('#start-time1').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? '' : '';
        },
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    }).data('datepicker');
    var checkout = $('#end-time1').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < checkin.date.valueOf()-1 ? 'disabled' : '';
        },
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    }).data('datepicker');
});
//angular
app.controller('initiateExamController', function($scope, $http, httpService,$filter) {
    $scope.exam={
        startTime:'',
        endTime:'',
        examTime:'',
        examName:'',
        testers:'',
        examination:''
    }
    $scope.pageBool=false;
    $scope.userBool=false;
    $scope.readerBool=false;
   $scope.examinationBool=false;
   $scope.examinationBool2=false;
   $scope.curr=1;
   $scope.change=function(){
   }

   $scope.pages={
       questionName:'',
       firstLevelCategory:'0',
       secondLevelCategory:'0',
       startTime1:'',
       endTime1:''
   }
   //获取下级目录
    $scope.getSecondLevel = function () {
        var data = {
            "categoryId": parseInt($scope.pages.firstLevelCategory,10)>0?parseInt($scope.pages.firstLevelCategory):''
        };
        httpService.getList(data, "/exCategory/getExCategoryListByParentId").then(function successCallback(response) {
            var code = response.data.code;
            if (code == 1) {
                $scope.secondLevelCategorys = response.data.result;
            } else {
                $scope.secondLevelCategorys = [];
                $scope.pages.secondLevelCategory = '0';
            }
        }, function errorCallback(response) {
            console.log("err");
        });
    }

    //弹框初始化
    $scope.pages={
        questionName : '',
        startTime1:'',
        endTime1:'',
        firstLevelCategory : '0',
        secondLevelCategory : '0'
    }
    function submit(page) {
        var data = {
            'title':$scope.pages.questionName,
            'repCatId1':parseInt($scope.pages.firstLevelCategory,10)>0?parseInt($scope.pages.firstLevelCategory,10):'',
            'repCatId2':parseInt($scope.pages.secondLevelCategory,10)?parseInt($scope.pages.secondLevelCategory,10):'',
            'start1Time':$scope.pages.startTime1,
            'end1Time':$scope.pages.endTime1,
            'pageIndex':page,
            'pageSize':10
        };
        httpService.getList(data ,"/examModule/queryPaperListc").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                $scope.paperList = response.data.result;
                console.log($scope.paperList);
                for(var i =0;length=$scope.paperList.length,i<length;i++){
                    $scope.paperList[i].CreateTime = $filter("date")($scope.paperList[i].CreateTime, "yyyy-MM-dd");
                }
            }else {
                $scope.paperList=[];
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //获取分页
        httpService.getList(data,"/examModule/queryPaperListCountc").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    //选择试卷弹框，初始化
    $scope.selectPage=function(){
        $scope.pageBool=true;
        //初始化部门下拉框数据
        initFirstLevel();
        function initFirstLevel() {
            var data = {
                "parentId": 0
            };
            httpService.getList(data, "/exCategory/getExCategoryList").then(function successCallback(response) {
                var code = response.data.code;
                console.log(response.data.result);
                if (code == 1) {
                    $scope.firstLevelCategorys = response.data.result;
                } else {
                    layer.msg(response.data.msg);
                }
            }, function errorCallback(response) {
                console.log("err");

            });
        }
        submit(1);
    }
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            submit(1);
        }
    }
    //选择试卷
    $scope.submitManager=function(){
        submit(1);
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

    $scope.examinationAdd=function(){
        $scope.examinationBool=true;
        httpService.getList(data ,"/examModule/queryScoreAnalysis").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                layer.msg(response.data.msg);
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }

    $scope.readerAdd=function(){
        $scope.examinationBool2=true;
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



    $scope.close=function(){
        $scope.assessmentBool=false;
        $scope.pageBool=false;
        $scope.userBool=false;
        $scope.readerBool=false;
        $scope.examinationBool=false;
        $scope.examinationBool2=false;
        $scope.pages.firstLevelCategory='0';
        $scope.pages.secondLevelCategory='0';
        $scope.pages.questionName='';
        $scope.pages.startTime1='';
        $scope.pages.endTime1='';
        $scope.secondLevelCategorys=[];
        $scope.secondLevelCategorys=[];
    }

    $scope.generate=function(){
        var data = {
            start1Time:$scope.exam.startTime,
            end1Time:$scope.exam.endTime,
            time:$scope.exam.examTime,
            examTitle:$scope.exam.examName,
            userIdList1:$scope.exam.examination,
            readerIdList1:$scope.exam.testers
        };
       if (data.start1Time == '') {
           layer.msg("请输入开始时间");
           return false;
       }
       if(data.end1Time == ''){
           layer.msg("请输入考试时长");
           return false;
       }
        if(data.time == ''){
            layer.msg("请输入考试时间");
            return false;
        }
       if(data.examTitle == ''){
           layer.msg("请选择试卷名称");
           return false;
       }
        if(data.userIdList1 == ''){
            layer.msg("请选择考试人员");
            return false;
        }
        if(data.readerIdList1 == ''){
            layer.msg("请选择阅卷人员");
            return false;
        }
        httpService.getList(data ,"/examModule/queryScoreAnalysis").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                layer.msg(response.data.msg);
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }


});
