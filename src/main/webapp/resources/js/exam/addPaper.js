
$(function(){
    $(".warehouse-list-title>span:contains('考卷管理')").parents(".warehouse-list").addClass("hover");
    $(".header li a:contains('考试培训')").addClass("hover");
});
$(function(){

    $(".warehouse-list-title>span:contains('考试培训')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children(".child-nav").find("span:contains('考卷管理')").parent().next().show().children("a:contains('添加考卷')").css({"background":"#7a8ce3","color":"#fff"});
    $(".paper-data .paper-classification a:first-child").click();
    $(".member-info>a").click(function(e){
        $(".edit-user").fadeIn();
    });
    $(".assessment").click(function(e){
        $(".edit-test").fadeIn();
    });

    $(".test-paper-modular .add-problem").click(function(){
        $(".edit-problem").fadeIn();

    });
});
app.controller('addPapersController', function($scope, $http, httpService) {
    $scope.selectTemplatesBool = false;
    $scope.selectPapersBool = false;
    $scope.deleteTemplates = function(){

    }
    $scope.selectTemplates = function(){
      $scope.selectTemplatesBool = true;
    }
    $scope.addTestQuestions = function(){
      $scope.selectPapersBool = true;
    }
    $scope.close = function(){
      $scope.selectPapersBool = false;
      $scope.selectTemplatesBool = false;
    }
    $scope.templateConfirm = function(){
      $scope.selectTemplatesBool = false;
    }
    $scope.testQuestionConfirm = function(){
      $scope.selectPapersBool = false;
    }
    //选择题目
    $scope.selectSubject=function(num){
        $scope.subjectId=num;
    }
});
