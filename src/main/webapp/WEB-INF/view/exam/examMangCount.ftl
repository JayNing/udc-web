
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考试管理-统计">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/examMangCount.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

<input type="hidden" value="${RequestParameters.ExamId}" id="examId"/>
    <div class="member-modular-right message-notification" ng-controller="examManagerController">
        <div class="books-title check-title">
            <i></i>
            <span>考试培训-<strong>考试管理</strong></span>
        </div>
        <div class="books-table check-table">
            <h3>2017年末培训考试</h3>
            <p>人力资源部</p>
            <div id="chart">
            </div>
            <div class="fraction-detail">分数详情</div>
            <table>
                <thead>
                <tr>
                    <td width="20%">序号</td>
                    <td width="51%">姓名</td>
                    <td width="29%">分数</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in examList">
                    <td ng-bind="(curr-1)*10+$index+1">1</td>
                    <td ng-bind="x.RealName"></td>
                    <td ng-bind="x.RealName"></td>
                </tr>
                </tbody>
            </table>
            <div class="page books-page" ng-if="allPage>1">
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
            </div>
        </div>

    </div>
</@examTrain.modelBody>

<@examTrain.js_scripts>

<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/examMangCount.js" type="text/javascript"></script>
</@examTrain.js_scripts>
