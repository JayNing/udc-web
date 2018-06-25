
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-题库管理">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/extrQusBankManager.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>
<div class="member-modular-right manage-right message-notification" ng-controller="extrQusBankManagerController">
    <div class="bg edit-test" ng-cloak class="ng-cloak" ng-show="paperBool">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div ng-if="adds">
                    <span></span>
                    添加题目
                </div>
                <div  ng-if="edits">
                    <span></span>
                    编辑题目
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="question-wrap">
                <div class="question-tab clearfix">
                    <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==1]" ng-click="selectSubject(1)">选择题</a>
                    <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==2]" ng-click="selectSubject(2)">多选题</a>
                    <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==3]" ng-click="selectSubject(3)">判断题</a>
                    <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==4]" ng-click="selectSubject(4)">问答题</a>
                </div>
                <div class="question-edit" ng-if="subjectId==1">
                    <div class="question-input clearfix">
                        <span>问题:</span>
                        <input type="text" placeholder="请设置问题" ng-model="paper.paperQuestion"/>
                    </div>
                    <div class="question-input clearfix">
                        <span>类目:</span>
                        <select ng-model="paper.oneClassify" ng-change="getSecondLevel()">
                            <option value="0">选择一级类目</option>
                            <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                        </select>
                        <select ng-model="paper.twoClassify">
                            <option value="0">选择二级类目</option>
                            <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
                        </select>
                    </div>
                    <div ng-repeat="x in lists track by $index">
                        <div class="question-input question-option clearfix">
                            <span>选项名:</span>
                            <input type="text" placeholder="选项名" ng-model="x.OptionCode"/>
                            <span ng-class="{true:'hover',false:''}[x.CrrctAns==2]" ng-click="setAnswer($index,1)">设为答案</span>
                        </div>
                        <div class="question-input question-option1 clearfix">
                            <span>选项值:</span>
                            <input type="text" placeholder="选项值"  ng-model="x.OptionDesc "/>
                        </div>
                    </div>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperAdd()">增加选项</a>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperSubmit()">确定</a>
                </div>
                <div class="question-edit" ng-if="subjectId==2">
                    <div class="question-input clearfix">
                        <span>问题:</span>
                        <input type="text" placeholder="请设置问题" ng-model="paper.paperQuestion"/>
                    </div>
                    <div class="question-input clearfix">
                        <span>类目:</span>
                        <select ng-model="paper.oneClassify" ng-change="getSecondLevel()">
                            <option value="0">选择一级类目</option>
                            <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                        </select>
                        <select ng-model="paper.twoClassify">
                            <option value="0">选择二级类目</option>
                            <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
                        </select>
                    </div>
                    <div ng-repeat="x in lists track by $index">
                        <div class="question-input question-option clearfix">
                            <span>选项名:</span>
                            <input type="text" placeholder="选项名" ng-model="x.OptionCode"/>
                            <span ng-class="{true:'hover',false:''}[x.CrrctAns==2]" ng-click="setAnswer($index,2)">设为答案</span>
                        </div>
                        <div class="question-input question-option1 clearfix">
                            <span>选项值:</span>
                            <input type="text" placeholder="选项值"  ng-model="x.OptionDesc "/>
                        </div>
                    </div>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperAdd()">增加选项</a>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperSubmit()">确定</a>
                </div>
                <div class="question-edit" ng-if="subjectId==3">
                    <div class="question-input clearfix">
                        <span>问题:</span>
                        <input type="text" placeholder="请设置问题" ng-model="paper.paperQuestion"/>
                    </div>
                    <div class="question-input clearfix">
                        <span>类目:</span>
                        <select ng-model="paper.oneClassify" ng-change="getSecondLevel()">
                            <option value="0">选择一级类目</option>
                            <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                        </select>
                        <select ng-model="paper.twoClassify">
                            <option value="0">选择二级类目</option>
                            <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
                        </select>
                    </div>
                    <div class="question-edit" ng-repeat="x in lists track by $index">
                        <div class="question-input question-option clearfix">
                            <span>选项名:</span>
                            <input type="text" placeholder="选项名" ng-model="x.OptionCode"/>
                            <span ng-class="{true:'hover',false:''}[x.CrrctAns==2]" ng-click="setAnswer($index,1)">设为答案</span>
                        </div>
                        <div class="question-input question-option1 clearfix">
                            <span>选项值:</span>
                            <input type="text" placeholder="选项值"  ng-model="x.OptionDesc "/>
                        </div>
                    </div>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperAdd()">增加选项</a>
                    <a href="javascript:;" class="question-btn question-btn1" ng-click="paperSubmit()">确定</a>
                </div>
                <div class="question-edit" ng-show="subjectId==4">
                    <div class="question-input clearfix">
                        <span>问题:</span>
                        <input type="text" placeholder="请简述在与客户交流过程应当注意哪些事项？" ng-model="paper.paperQuestion"/>
                    </div>
                    <div class="question-input clearfix">
                        <span>类目:</span>
                        <select ng-model="paper.oneClassify" ng-change="getSecondLevel()">
                            <option value="0">选择一级类目</option>
                            <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                        </select>
                        <select ng-model="paper.twoClassify">
                            <option value="0">选择二级类目</option>
                            <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
                        </select>
                    </div>
                    <div class="question-answer clearfix">
                        <span>参考答案</span>
                        <textarea ng-model="paper.paperAnswer"></textarea>
                    </div>
                    <a href="javascript:;" ng-if="adds" class="question-btn" ng-click="paperSubmit()">确定</a>
                </div>
            </div>
        </div>
    </div>
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
    <div class="books-title">
        <i></i>
        <span>考试培训-<strong>题库管理</strong></span>
    </div>
    <div class="books-table">
        <div class="search-model test-model clearfix">
            <div class="books-search clearfix">
                <i class="iconfont icon-sousuo"></i>
                <input type="text" placeholder="题目" ng-keyup="enterEvent($event)" ng-model="qusBank.questionName" >
            </div>
            <div class="books-search-time">
                <input type="text" placeholder="创建人" ng-model="qusBank.authorName">
                <select ng-model="qusBank.firstLevelCategory" ng-change="getSecondLevel1()">
                    <option value="0">一级类目</option>
                    <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                </select>
                <select ng-model="qusBank.secondLevelCategory" >
                    <option value="0">二级类目</option>
                    <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
                </select>
                <input type="text" id="start-time" placeholder="开始时间 " ng-model="qusBank.startTime">
                <span>至 </span>
                <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="qusBank.endTime">
                <a href="javascript:void(0);" ng-click="submitManager()">提交</a>

            </div>
        </div>
        <div class="clearfix subject">
            <div class="clearfix">
                <input type="hidden" ng-model="qusBank.questionType" />
                <a href="javascript:;" ng-class="{true:'hover',false:''}[qusBank.questionType==1]" ng-click="selectQuestionType(1)">选择题</a>
                <a href="javascript:;" ng-class="{true:'hover',false:''}[qusBank.questionType==2]" ng-click="selectQuestionType(2)">多选题</a>
                <a href="javascript:;" ng-class="{true:'hover',false:''}[qusBank.questionType==3]" ng-click="selectQuestionType(3)">判断题</a>
                <a href="javascript:;" ng-class="{true:'hover',false:''}[qusBank.questionType==4]" ng-click="selectQuestionType(4)">问答题</a>
            </div>
            <a href="javascript:;" class="add-classification" ng-click="addPaper()"><span></span>添加题目</a>
        </div>
        <table ng-cloak class="ng-cloak" ng-show="qusBank.questionType==1">
            <thead>
            <tr>
                <td width="13%">序号</td>
                <td width="30%">题目</td>
                <td width="14%">一级类目</td>
                <td width="14%">二级类目</td>
                <td width="14%">创建人</td>
                <td width="14%">添加时间</td>
                <td width="15%">操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="e in exercisesList">
                <td ng-bind="(curr-1)*10+$index+1"></td>
                <td><div class="books-author" ng-bind="e.ExcsTitle"></div></td>
                <td ng-bind="e.RepCatId1Name"></td>
                <td ng-bind="e.RepCatId2Name"></td>
                <td ng-bind="e.CreaterName"></td>
                <td ng-bind="e.CreateTime"></td>
                <td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(e.ExercisesId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(e.ExercisesId,$index)">删除</a></div></td>
            </tr>
            </tbody>
        </table>
        <table ng-cloak class="ng-cloak" ng-show="qusBank.questionType==2">
            <thead>
            <tr>
                <td width="13%">序号</td>
                <td width="30%">题目</td>
                <td width="14%">一级类目</td>
                <td width="14%">二级类目</td>
                <td width="14%">添加时间</td>
                <td width="15%">操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="e in exercisesList">
                <td ng-bind="(curr-1)*10+$index+1">{{}}</td>
                <td><div class="books-author" ng-bind="e.ExcsTitle"></div></td>
                <td ng-bind="e.RepCatId1Name"></td>
                <td ng-bind="e.RepCatId2Name"></td>
                <td ng-bind="e.CreateTime"></td>
                <td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(e.ExercisesId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(e.ExercisesId,$index)">删除</a></div></td>
            </tr>
            </tbody>
        </table>
        <table ng-cloak class="ng-cloak" ng-show="qusBank.questionType==3">
            <thead>
            <tr>
                <td width="13%">序号</td>
                <td width="30%">题目</td>
                <td width="14%">一级类目</td>
                <td width="14%">二级类目</td>
                <td width="14%">添加时间</td>
                <td width="15%">操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="e in exercisesList">
                <td ng-bind="(curr-1)*10+$index+1">{{}}</td>
                <td><div class="books-author" ng-bind="e.ExcsTitle"></div></td>
                <td ng-bind="e.RepCatId1Name"></td>
                <td ng-bind="e.RepCatId2Name"></td>
                <td ng-bind="e.CreateTime"></td>
                <td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(e.ExercisesId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(e.ExercisesId,$index)">删除</a></div></td>
            </tr>
            </tbody>
        </table>
        <table ng-cloak class="ng-cloak" ng-show="qusBank.questionType==4">
            <thead>
            <tr>
                <td width="13%">序号</td>
                <td width="30%">题目</td>
                <td width="14%">一级类目</td>
                <td width="14%">二级类目</td>
                <td width="14%">添加时间</td>
                <td width="15%">操作</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="e in exercisesList">
                <td ng-bind="(curr-1)*10+$index+1"></td>
                <td><div class="books-author" ng-bind="e.ExcsTitle"></div></td>
                <td ng-bind="e.RepCatId1Name"></td>
                <td ng-bind="e.RepCatId2Name"></td>
                <td ng-bind="e.RepCatId2Name"></td>
                <td ng-bind="e.CreateTime"></td>
                <td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(e.ExercisesId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(e.ExercisesId,$index)">删除</a></div></td>
            </tr>
            </tbody>
        </table>
        <div class="page books-page" >
            <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
        </div>
    </div>

</div>
</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/extrQusBankManager.js" type="text/javascript"></script>
</@examTrain.js_scripts>
