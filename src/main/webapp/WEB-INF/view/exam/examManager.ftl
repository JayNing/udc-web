
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考试管理 ">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/examManager.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>
    <div class="member-modular-right manage-right message-notification" ng-controller="examManagerController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除问题
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除该问题?</p>
                    <a href="javascript:;" ng-click="close()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="bg edit-test" ng-cloak class="ng-cloak" ng-show="assessmentBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <span></span>
                        评估
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="test-edit-main">
                    <form>
                        <textarea placeholder="请填写本次考试结果评估…" ng-model="resultsEvaluation"></textarea>
                        <a href="javascript:;" ng-click="evaluationSubmit()">保存</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>考试管理</strong></span>
        </div>
        <div class="books-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="考试名称" ng-keyup="enterEvent($event)" ng-model="exam.examName">
                </div>
                <div class="books-search-time examinationt-time">
                    <input type="text" id="start-time" placeholder="开始时间 " ng-model="exam.startTime">
                    <span>至 </span>
                    <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="exam.endTime">
                    <select ng-model="exam.showStatus" >
                        <option value="0">状态</option>
                        <option value="2">评估</option>
                        <option value="1">未评估</option>
                    </select>
                    <a href="javascript:void(0);" ng-click="submitManager()">提交</a>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width="6%">序号</td>
                    <td width="25%">试卷名称</td>
                    <td width="12%">创建时间</td>
                    <td width="10%">状态</td>
                    <td width="12%">批卷</td>
                    <td width="35%">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr  ng-repeat="p in examList">
                    <td ng-bind="(curr-1)*10+$index+1"></td>
                    <td ng-bind="p.ExamTitle"></td>
                    <td ng-bind="p.CreateTime"></td>
                    <td>
                        <div class="assessment hover" ng-cloak  ng-if="p.AssmStatus==1" ng-click="assessment(p.ExamId)">评估</div>
                        <div class="assessment" ng-cloak ng-if="p.AssmStatus==2">已评估</div>
                    </td>
                    <td><div class="check hover" href="javascript:;" ng-click="expertEdit(p.ExamId)" >批卷</div></td>
                    <td><div class="statistic"><a href="javascript:;" ng-click="examDetail(p.ExamId)" class="hover">考试分析</a><a href="" class="hover" href="javascript:;" ng-click="examCount(x.ExamId)">考试统计 </a><a href="javascript:;" class="hover" ng-click="del(p.ExamId,$index)">删除</a></div></td>
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
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/examManager.js" type="text/javascript"></script>
</@examTrain.js_scripts>
