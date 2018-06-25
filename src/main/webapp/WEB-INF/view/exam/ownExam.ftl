
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人中心 -我的考试" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/exam/ownExam.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

<div class="member-modular-right message-notification"  ng-controller="ownExamController">
    <form>
        <div class="ownExam-form">
            <div class="ownExam-text warehouse-author clearfix">
                <span class="ownExamPaperName">试卷名称</span>
                <input type="text" id="start-time" placeholder="开始时间 " ng-model="startTime">
                <span>至 </span>
                <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="endTime">
                <a href="javascript:void(0);" ng-click="submitManager()">提交</a>
            </div>
            <div class="ownExam-list">
                <table ng-cloak class="ng-cloak" ng-show="qusBank.questionType==1">
                    <thead>
                    <tr>
                        <td width="8%">序号</td>
                        <td width="30%">试卷名称</td>
                        <td width="14%">开始时间</td>
                        <td width="14%">结束时间</td>
                        <td width="8%">成绩</td>
                        <td width="10%">状态</td>
                        <td width="10%">操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="e in es">
                        <td ng-bind="(curr-1)*10+$index+1">1</td>
                        <td><div class="" ng-bind="e.ExcsTitle">2017年末培训考试</div></td>
                        <td ng-bind="e.StratTime">2017-12-15<br/> 18:00:00</td>
                        <td ng-bind="e.EndTime">2017-12-15<br/>18:00:00</td>
                        <td ng-bind="e.Result">100</td>
                        <td >{{ e.flag==1?'进行中':'以结束'}}</td>
                        <td><div ng-class="{true:'ownExam-start-btn',false:'ownExam-details-btn'}[e.y==1]">
                            <a href="javascript:;" class="hover" ng-click="startExam(e.ExercisesId)" ng-if="e.y==1">开始考试</a>
                            <a href="javascript:;" class="hover" ng-click="examDetail(e.ExercisesId)" ng-if="e.y==2">考试详情</a>
                        </div></td>
                    </tr>
                    <#--<tr>-->
                        <#--<td ng-bind="(curr-1)*10+$index+1">1</td>-->
                        <#--<td><div class="" ng-bind="e.ExcsTitle">2017年末培训考试</div></td>-->
                        <#--<td ng-bind="e.RepCatId1Name">2017-12-15<br/>18:00:00</td>-->
                        <#--<td ng-bind="e.RepCatId2Name">2017-12-15<br/>18:00:00</td>-->
                        <#--<td ng-bind="e.CreateTime">85</td>-->
                        <#--<td ng-bind="e.CreateTime">已参加</td>-->
                        <#--<td><div class="ownExam-details-btn"><a href="javascript:;" class="hover" ng-click="examDetail(e.ExercisesId)">考试详情</a></div></td>-->
                    <#--</tr>-->
                    <#--<tr ng-repeat="e in exercisesList">-->
                        <#--<td ng-bind="(curr-1)*10+$index+1"></td>-->
                        <#--<td><div class="books-author" ng-bind="e.ExcsTitle"></div></td>-->
                        <#--<td ng-bind="e.RepCatId1Name"></td>-->
                        <#--<td ng-bind="e.RepCatId2Name"></td>-->
                        <#--<td ng-bind="e.CreateTime"></td>-->
                        <#--<td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(e.ExercisesId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(e.ExercisesId,$index)">删除</a></div></td>-->
                    <#--</tr>-->
                    </tbody>
                </table>
            </div>
            <div class="books-page" >
                <div class="page test-page">
                    <a href="" class="page-text">首页</a>
                    <a href="" class="page-num hover">1</a>
                    <a href="" class="page-num">2</a>
                    <a href="" class="page-num">3</a>
                    <a href="" class="page-num">4</a>
                    <a href="" class="page-num">5</a>
                    <a href="" class="page-more">...</a>
                    <a href="" class="page-text">末页</a>
                </div>
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p">首页</a>
            </div>
        </div>
    </form>
</div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/exam/ownExam.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
