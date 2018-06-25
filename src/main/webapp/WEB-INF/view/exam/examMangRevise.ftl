
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考试管理-批卷" >

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/examMangRevise.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

<input type="hidden" value="${RequestParameters.ExamId}" id="examId"/>
<div class="member-modular-right message-notification" ng-controller="batchController">
    <div class="bg edit-test" ng-show="pageBool">
        <div class="data-test-paper">
            <div class="data-edit-head clearfix">
                <div>
                    <span></span>
                    批卷
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="test-paper-pop">
                <h2>2017年末培训考试</h2>
                <div class="test-paper-modular">
                    <h3>一、选择题（每题10分）</h3>
                    <div ng-repeat="x in exerList0" ng-if="exerList0.length>0">
                        <div class="problem-list">
                            <div class="problem-subject">
                                <em>题{{$index+1}}</em>
                                <span>{{x.ExcsTitle}}<span> ( {{x.UserAnswer}} )</span></span>
                                <img ng-if="x.IsCorrect==2" src="${basePath}/resources/images/app/correct.png">
                                <img ng-if="x.IsCorrect==1" src="${basePath}/resources/images/app/error.png">
                            </div>
                            <div class="problem-option" >
                                <div>
                                    <span ng-repeat="y in x.OptionList"><em>{{y.OptionCode}}.</em> {{y.OptionDesc}} </span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="test-paper-modular">
                    <h3>一、多选题（每题10分）</h3>
                    <div ng-repeat="x in exerList1" ng-if="exerList1.length>0">
                        <div class="problem-list">
                            <div class="problem-subject">
                                <em>题{{$index+1}}</em>
                                <span>{{x.ExcsTitle}}<span> ( {{x.UserAnswer}} )</span></span>
                                <img ng-if="x.IsCorrect==2" src="${basePath}/resources/images/app/correct.png">
                                <img ng-if="x.IsCorrect==1" src="${basePath}/resources/images/app/error.png">
                            </div>
                            <div class="problem-option" >
                                <div>
                                    <span ng-repeat="y in x.OptionList"><em>{{y.OptionCode}}.</em> {{y.OptionDesc}} </span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="test-paper-modular">
                    <h3>二、判断题（每题10分）</h3>
                    <div>
                        <div class="problem-list" ng-repeat="x in exerList2" ng-if="exerList2.length>0">
                            <div class="problem-subject">
                                <em>题{{$index+1}}</em>
                                <span>{{x.ExcsTitle}}</span>
                            </div>
                            <div class="problem-option problem-judge">
                                <label ng-repeat="y in x.OptionList">
                                    <input type="radio" name="judge" >
                                    <i class="iconfont"> </i>
                                    {{y.OptionDesc}}
                                </label>

                                <img ng-if="x.IsCorrect==2" src="${basePath}/resources/images/app/correct.png">
                                <img ng-if="x.IsCorrect==1" src="${basePath}/resources/images/app/error.png">
                            </div>
                        </div>

                    </div>
                </div>
                <div class="test-paper-modular">
                    <h3>三、问答题（每题20分）</h3>
                    <div>
                        <div class="problem-list problem-qa" ng-repeat="x in exerList3" ng-if="exerList3.length>0">
                            <div class="problem-subject">
                                <em>题{{$index+1}}</em>
                                {{x.ExcsTitle}}

                            </div>
                            <div class="reference-answer clearfix">
                                <div>
                                    {{x.UserAnswer}}
                                </div>
                            </div>
                            <div class="score">
                                <span>打分</span>
                                <input type="text"  class="core{{x.ExercisesId}}"  >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pop-paper-footer">
                    <a href="javascript:;" ng-click="submit()">提交</a>
                </div>
            </div>

        </div>
    </div>
    <div class="books-title check-title">
        <i></i>
        <span>考试培训-<strong>考试管理</strong></span>
    </div>
    <div class="books-table check-table">
        <h3>2017年末培训考试</h3>
        <h4>《 批卷 》</h4>
        <table>
            <thead>
            <tr>
                <td width="20%">序号</td>
                <td width="40%">姓名</td>
                <td width="20%">消息阅读</td>
                <td width="20%">分数</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="x in examList">
                <td ng-bind="(curr-1)*10+$index+1"></td>
                <td ng-bind="x.RealName"></td>
                <td>
                    <div class="check hover" ng-if="x.ReaderStatus==1" ng-click="batch(x.UserId)">批卷</div>
                    <div class="check" ng-if="x.ReaderStatus==2">已批卷</div>
                </td>
                <td ng-bind="x.ExScore"></td>
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
<script src="${basePath}/resources/js/exam/examMangRevise.js" type="text/javascript"></script>
</@examTrain.js_scripts>
