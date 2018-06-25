/*
* 我的考试js
* */
$(function() {
    //日期
    var nowTemp = new Date();
    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), nowTemp.getHours(),nowTemp.getMinutes(), nowTemp.getSeconds(), 0);
    var checkin = $('#start-time').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < now.valueOf() ? '' : '';
        },
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    }).data('datepicker');
    var checkout = $('#end-time').fdatepicker({
        onRender: function (date) {
            return date.valueOf() < checkin.date.valueOf()-1 ? 'disabled' : '';
        },
        format: 'yyyy-mm-dd hh:ii',
        pickTime: true
    }).data('datepicker');
});




//ownExamController控制器
app.controller('ownExamController', function($scope, $http,httpService) {
    $scope.qusBank = {
        questionType:1
    }
    $scope.es = [{
            ExcsTitle:"2017年末培训考试",
            StratTime:"2017-12-15 18:00:00",
            EndTime:"2017-12-18 18:00:00",
            Result:0,
            flag:1,
            ExercisesId:1,
            y:1
        },{
        ExcsTitle:"2017年末培训考试",
        StratTime:"2017-12-15 18:00:00",
        EndTime:"2017-12-18 18:00:00",
        Result:30,
        flag:1,
        ExercisesId:1,
        y:2
    }];
    //开始考试,需传考试examId
    $scope.startExam = function (id) {
        window.location.href=basePath + "/beginExam?examId="+id;
    }

    //考试详情,需传考试examId
    $scope.examDetail = function (id) {
        window.location.href=basePath + "/checkDetail?examId="+id;
    }

});
