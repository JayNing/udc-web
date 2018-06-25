/*
* 考试培训-题库管理js
* */
$(function(){
    $(".warehouse-list-title>span:contains('题库管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});

app.controller('extrQusBankManagerController', function($scope, $http, httpService) {
    $scope.curr=1;
    $scope.delBool=false;
    $scope.paperBool=false;
    $scope.adds=false;
    $scope.edits=false;
    $scope.paper={
        paperQuestion:'',
        paperAnswer:'',
        oneClassify:'0',
        twoClassify:'0'
    }
    $scope.qusBank={
        questionName:'',
        authorName:'',
        firstLevelCategory:'0',
        secondLevelCategory:'0',
        startTime:'',
        endTime:'',
        questionType:1,

    }
    //搜索
    function submit(page) {
        var data = {
            'exeType':$scope.qusBank.questionType,
            'title':$scope.qusBank.questionName,
            'repCatId1':parseInt($scope.qusBank.firstLevelCategory)>0?parseInt($scope.qusBank.firstLevelCategory):'',
            'repCatId2':parseInt($scope.qusBank.secondLevelCategory)>0?parseInt($scope.qusBank.secondLevelCategory):'',
            'createName':$scope.qusBank.authorName,
            'start1Time':$scope.qusBank.startTime,
            'end1Time':$scope.qusBank.endTime,
            'pageIndex':page,
            'pageSize':10
        };
        httpService.getList(data ,"/examModule/queryExerciseList").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                $scope.exercisesList=response.data.result;
                for(var i=0;length=$scope.exercisesList.length,i<length;i++){
                    $scope.exercisesList[i].CreateTime= $scope.exercisesList[i].CreateTime.substr(0,10);
                }
                console.log(response.data.result);
            }else {
                layer.msg(response.data.msg);
                $scope.exercisesList=[];
            }
        },function errorCallback(response) {
            console.log("err");
        });

        //获取分页
        var pageData = {
            'exeType':$scope.qusBank.questionType,
            'title':$scope.qusBank.questionName,
            'repCatId1':parseInt($scope.qusBank.firstLevelCategory,10)>0?parseInt($scope.qusBank.firstLevelCategory,10):'',
            'repCatId2':parseInt($scope.qusBank.secondLevelCategory,10)>0?parseInt($scope.qusBank.secondLevelCategory,10):'',
            'createName':$scope.qusBank.authorName,
            'start1Time':$scope.qusBank.startTime,
            'end1Time':$scope.qusBank.endTime
        };
        httpService.getList(pageData,"/examModule/queryExerciseListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    submit(1);
    $scope.submitManager=function(){
        submit(1);
    }
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
        console.log($scope.delBool);
    };
    //取消删除
    $scope.cancel=function(){
        $scope.delBool=false;
    }
    //确认删除
    $scope.confirm=function(){
        var data = {
            exercisesId:$scope.id
        }
        $scope.exercisesList.splice($scope.index, 1);
        httpService.postList(data,"/examModule/deleteExercise").then(function successCallback(response) {
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


    //一级下拉框
    function initFirstLevel(){
        var data = {
            "repCatParentId" : 0
        };
        httpService.getList(data ,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                $scope.firstLevelCategorys = response.data.result;
            }else {
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");

        });
    }
    //一级下拉框
    initFirstLevel();
    //二级下拉框
    $scope.getSecondLevel1 = function () {
        var data = {
            "repCatId" : parseInt($scope.qusBank.firstLevelCategory,10)>0 ? parseInt($scope.qusBank.firstLevelCategory,10):''
        };
        console.log($scope.qusBank.firstLevelCategory);
        $scope.qusBank.secondLevelCategory = '0';
        $scope.secondLevelCategorys=[];
        httpService.getList(data ,"/repositoryClassify/getRepository").then(function successCallback(response) {
            var code = response.data.code;
            if(code == 1){
                $scope.secondLevelCategorys = response.data.result;
                console.log($scope.secondLevelCategorys);
            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    $scope.getSecondLevel = function () {
        var data = {
            "repCatId" : parseInt($scope.paper.oneClassify,10)>0 ? parseInt($scope.paper.oneClassify,10):''
        };
        $scope.paper.twoClassify = '0';
        $scope.secondLevelCategorys=[];
        console.log($scope.paper.oneClassify);
        httpService.getList(data ,"/repositoryClassify/getRepository").then(function successCallback(response) {
            var code = response.data.code;
            console.log(response.data.result)
            if(code == 1){
                $scope.secondLevelCategorys = response.data.result;
                console.log($scope.secondLevelCategorys);
            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    //选择题目类别
    $scope.selectQuestionType = function(questionType){
        $scope.qusBank.questionType = questionType;
        submit(1);
    }
    //问题选择
    $scope.selectSubject=function(num){
        $scope.subjectId=num;
    }
    //添加问答
    $scope.addPaper = function(){
        $("body").css("overflow","hidden");
        $scope.paperBool=true;
        $scope.adds=true;
        $scope.edits=false;
        if($scope.qusBank.questionType==2){
            $scope.subjectId=2;
            $scope.lists=[{CrrctAns:1,OptionCode:'',OptionDesc:''}]
        }else if($scope.qusBank.questionType==3){
            $scope.subjectId=3;
            $scope.lists=[{CrrctAns:1,OptionCode:'',OptionDesc:''}]
        }else if($scope.qusBank.questionType==4){
            $scope.subjectId=4;
        }else{
            $scope.subjectId=1;
            $scope.lists=[{CrrctAns:1,OptionCode:'',OptionDesc:''}]
        }
    }
    //设为答案
    $scope.setAnswer=function(index,type){
        if(type==1||type==3){
            for(var i=0;length=$scope.lists.length,i<length;i++){
                $scope.lists[i].CrrctAns = 1;
            }
            $scope.lists[index].CrrctAns = 2;
        }else if(type==2){
            $scope.lists[index].CrrctAns==1 ? $scope.lists[index].CrrctAns=2:$scope.lists[index].CrrctAns=1;
        }
        console.log($scope.lists);
    }
    //添加选项
    $scope.paperAdd=function(){

      if( $scope.subjectId==1){
        $scope.lists.push({CrrctAns:1,OptionCode:'',OptionDesc:''})
       }
       if( $scope.subjectId==2){
         $scope.lists.push({CrrctAns:4,OptionCode:'',OptionDesc:''})
        }
       if( $scope.subjectId==3){
           if($scope.lists.length>=2) return;
        }

    }
    //编辑
    $scope.edit=function(id){
        $scope.paperBool=true;
        if($scope.qusBank.questionType==2){
            //多选题
            $scope.subjectId=2;
        }else if($scope.qusBank.questionType==3){
            //判断题
            $scope.subjectId=3;
        }else if($scope.qusBank.questionType==4){
            //问答题
            $scope.subjectId=4;
        }else{
            //单选题
            $scope.subjectId=1;
        }
        //判断添加或编辑
        $scope.adds=false;
        $scope.edits=true;
        //编辑id
        $scope.setId=id;
        var data={
            exercisesId:id
        }
        httpService.getList(data ,"/examModule/queryExerciseDetail").then(function successCallback(response) {
            console.log(response.data.result);
            //编辑回调信息
            var data = response.data.result;
            if(response.data.code==1){
                $scope.paper.paperQuestion=data.ExcsTitle;
                $scope.paper.oneClassify=data.RepCatId1+'';
                $scope.paper.twoClassify=data.RepCatId2+'';
                if($scope.qusBank.questionType==1||$scope.qusBank.questionType==2||$scope.qusBank.questionType==3){
                    $scope.lists=data.OptionList;
                    console.log($scope.lists);
                }else{
                    $scope.paper.paperAnswer=data.CurrAnswerContent;
                }
            }

        },function errorCallback(response) {
            console.log("err");
        });

    }
    //提交信息
    $scope.paperSubmit=function(){
        //单选题提交
        if($scope.subjectId==1||$scope.subjectId==2||$scope.subjectId==3){
            console.log($scope.lists);
            var str='';
            for(var i=0;length=$scope.lists.length,i<length;i++){
                str=str+($scope.lists[i].OptionCode+','+$scope.lists[i].OptionDesc+','+$scope.lists[i].CrrctAns)+'_';
            }
            str = str.substring(0,str.length - 1);

            console.log(str);
            //判断添加提交或编辑提交
            if($scope.adds){
                //添加提交
                var data={
                    type:$scope.subjectId,
                    excsTitle:$scope.paper.paperQuestion,
                    repCatId1:parseInt($scope.paper.oneClassify,10),
                    repCatId2:parseInt($scope.paper.twoClassify,10),
                    list:str,
                    currAnswerContent:$scope.paper.paperAnswer
                }
            }else{
                //编辑提交
                var data={
                    exercisesId:$scope.setId,
                    type:$scope.subjectId,
                    excsTitle:$scope.paper.paperQuestion,
                    repCatId1:parseInt($scope.paper.oneClassify,10),
                    repCatId2:parseInt($scope.paper.twoClassify,10),
                    list:str,
                }
            }
            $scope.lists=[];
        }else if($scope.subjectId==4){
            //问答提交
            if($scope.adds){
                //添加提交
                var data={
                    type:$scope.subjectId,
                    excsTitle:$scope.paper.paperQuestion,
                    repCatId1:parseInt($scope.paper.oneClassify,10),
                    repCatId2:parseInt($scope.paper.twoClassify,10),
                    list:'',
                    currAnswerContent:$scope.paper.paperAnswer
                }
            }else{
                //编辑提交
                var data={
                    exercisesId:$scope.setId,
                    type:$scope.subjectId,
                    excsTitle:$scope.paper.paperQuestion,
                    repCatId1:parseInt($scope.paper.oneClassify,10),
                    repCatId2:parseInt($scope.paper.twoClassify,10),
                    list:'',
                    currAnswerContent:$scope.paper.paperAnswer
                }
            }
        }
        console.log(data);
        //判断添加提交或编辑提交
        if($scope.adds){
            //添加提交
            httpService.postList(data ,"/examModule/addTopic").then(function successCallback(response) {
                layer.msg(response.data.msg);
            },function errorCallback(response) {
                console.log("err");
            });
        }else{
            //编辑提交
            httpService.postList(data ,"/examModule/updateExercise").then(function successCallback(response) {
                layer.msg(response.data.msg);
            },function errorCallback(response) {
                console.log("err");
            });

        }
        submit(1);
        $scope.paperBool=false;
        $scope.paper.paperQuestion='';
        $scope.paper.oneClassify='0';
        $scope.firstLevelCategorys=[];
        $scope.paper.twoClassify='0';
        $scope.secondLevelCategorys=[];
        $("body").css("overflow","auto");
    }
    $scope.close=function(){
        $("body").css("overflow","auto");
        $scope.delBool=false;
        $scope.paperBool=false;
        scope.paper.paperQuestion='';
        $scope.paper.oneClassify='0';
        $scope.firstLevelCategorys=[];
        $scope.paper.twoClassify='0';
        $scope.secondLevelCategorys=[];
    }
});
