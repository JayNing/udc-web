
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-发起考试 ">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/initiateExam.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

<div class="member-modular-right manage-right message-notification"  ng-controller="initiateExamController">
    <div class="bg  similarity" ng-cloak class="ng-cloak" ng-show="examinationBool" id="addExaminee">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <img src="${basePath}/resources/images/app/expert-1.png">
                    人员选择
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="clearfix additions">
                <div class="clearfix addtions-content">
                    <div class="additions-left">
                        <div class="additions-list">
                            <div class="additions-top"><img src="${basePath}/resources/images/app/drop-down.png"><a href="javascript:;">总务处</a></div>
                            <div class="additions-user none">
                                <a href="javascript:;">方正余</a>
                                <a href="javascript:;">方正余</a>
                                <a href="javascript:;">方正余</a>
                            </div>
                        </div>
                        <div class="additions-list">
                            <div class="additions-top"><img src="${basePath}/resources/images/app/drop-down.png"><a href="javascript:;">教务处</a></div>
                            <div class="additions-user none">
                                <a href="javascript:;">正余</a>
                                <a href="javascript:;">正余</a>
                                <a href="javascript:;">正余</a>
                            </div>
                        </div>
                    </div>
                    <div class="additions-right">

                    </div>
                </div>
                <a href="javascript:;" class="additions-btn">确定</a>
            </div>

        </div>
    </div>
    <div class="bg  similarity" ng-cloak class="ng-cloak" ng-show="examinationBool2" id="addReader">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <img src="${basePath}/resources/images/app/expert-1.png">
                    人员选择
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="clearfix additions">
                <div class="clearfix addtions-content">
                    <div class="additions-left">
                        <div class="additions-list">
                            <div class="additions-top"><img src="${basePath}/resources/images/app/drop-down.png"><a href="javascript:;">总务处</a></div>
                            <div class="additions-user none">
                                <a href="javascript:;">方正余2</a>
                                <a href="javascript:;">方正余2</a>
                                <a href="javascript:;">方正余2</a>
                            </div>
                        </div>
                        <div class="additions-list">
                            <div class="additions-top"><img src="${basePath}/resources/images/app/drop-down.png"><a href="javascript:;">教务处</a></div>
                            <div class="additions-user none">
                                <a href="javascript:;">正余2</a>
                                <a href="javascript:;">正余2</a>
                                <a href="javascript:;">正余2</a>
                            </div>
                        </div>
                    </div>
                    <div class="additions-right">

                    </div>
                </div>
                <a href="javascript:;" class="additions-btn">确定</a>
            </div>

        </div>
    </div>
    <div class="bg edit-test" ng-cloak class="ng-cloak"  ng-show="pageBool">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <span></span>
                    试卷选择符
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="test-selection clearfix">
                <div class="clearfix test-data">
                    <input type="text" ng-model="pages.questionName" ng-keyup="enterEvent($event)" placeholder="试卷名称" />
                    <select ng-model="pages.firstLevelCategory" ng-change="getSecondLevel()">
                        <option value="0">一级类目</option>
                        <option ng-repeat="x in firstLevelCategorys" value="{{x.ExCategory.categoryId}}">{{x.ExCategory.categoryName}}</option>
                    </select>
                    <select ng-model="pages.secondLevelCategory" >
                        <option value="0">二级类目</option>
                        <option ng-repeat="x in secondLevelCategorys" value="{{x.categoryId}}">{{x.categoryName}}</option>
                    </select>
                    <input type="text" id="start-time" placeholder="开始时间 " ng-model="pages.startTime1">
                    <span>至 </span>
                    <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="pages.endTime1">
                    <a href="javascripe:;" ng-click="submitManager()">搜索</a>
                </div>
                <div class="test-paper">
                    <div>
                        <div class="test-paper-list" ng-repeat="x in paperList">
                            <label class="clearfix" >
                                <div>
                                    <input type="radio" ng-modle="myVar" name="test-paper-name"/>
                                </div>
                                <span class="test-paper-name" ng-bind="x.PaperTitle"></span>
                                <span ng-bind="x.ExCatId1Name" class="dep1"></span>
                                <span ng-bind="x.ExCatId2Name" class="dep1"></span>
                                <span ng-bind="x.ExCatId2Name"></span>
                                <span class="test-time" ng-bind="x.CreateTime"></span>
                            </label>
                        </div>
                    </div>
                    <div class="page books-page" ng-if="allPage>1">
                        <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}">{{p}}</a>
                    </div>
                </div>
                <div class="test-paper-submit"><a href="javascript:;" ng-click="pageSubmit()">确定</a></div>
            </div>
        </div>
    </div>
    <div class="books-title check-title">
        <i></i>
        <span>考试培训-<strong>发起考试</strong></span>
    </div>
    <form>
        <div class="examination-form">
            <div class="examination-text warehouse-author clearfix">
                <span>开始时间：</span>
                <input type="text" id="start-time1" ng-model="exam.startTime">
                <img src="${basePath}/resources/images/app/date.png" />
            </div>
            <div class="examination-text warehouse-author clearfix">
                <span>结束时间：</span>
                <input type="text" id="end-time1" ng-model="exam.endTime">
                <img src="${basePath}/resources/images/app/date.png" />
            </div>
            <div class="examination-text test-time clearfix">
                <span>考试时长：</span>
                <input type="text" ng-model="exam.examTime">
                <i>分钟</i>
            </div>
            <div class="examination-mark clearfix">
                <span>添加考试人员： </span>
                <span>财务部-<em>方正余</em></span>
                <span>财务部-<em>张岩</em></span>
                <a href="javascript:;" ng-click="examinationAdd()">添加</a>
            </div>
            <div class="examination-text test-name clearfix">
                <span>试卷名称：</span>
                <input type="text" ng-mode="exam.examName">
                <a href="javascript:;" ng-click="selectPage()">选择</a>
            </div>
            <div class="examination-mark clearfix">
                <span>添加阅卷人： </span>
                <span>财务部-<em>方正余</em></span>
                <span>财务部-<em>张岩</em></span>
                <a href="javascript:;" ng-click="readerAdd()">添加</a>
            </div>
            <a href="javascript:;" class="generate" ng-click="generate()">保存</a>
        </div>
    </form>
</div>

</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/initiateExam.js" type="text/javascript"></script>
</@examTrain.js_scripts>
