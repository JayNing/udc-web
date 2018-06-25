
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考卷管理" >

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/paperManager.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

    <div class="member-modular-right manage-right message-notification" ng-controller="paperManagerController">
        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>考卷管理</strong></span>
        </div>
        <div class="books-table">
            <div class="search-model test-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="题目" ng-keyup="enterEvent($event)" ng-model="questionName">
                </div>
                <div class="books-search-time">
                    <select ng-model="firstLevelCategory" ng-change="getSecondLevel()">
                        <option value="0">一级类目</option>
                        <option ng-repeat="x in firstLevelCategorys" value="{{x.ExCategory.categoryId}}">{{x.ExCategory.categoryName}}</option>
                    </select>
                    <select ng-model="secondLevelCategory" >
                        <option value="0">二级类目</option>
                        <option ng-repeat="x in secondLevelCategorys" value="{{x.categoryId}}">{{x.categoryName}}</option>
                    </select>
                    <input type="text" id="start-time" placeholder="开始时间 " ng-model="startTime">
                    <span>至 </span>
                    <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="endTime">
                    <a href="javascript:void(0);" ng-click="submitManager()">提交</a>
                </div>
            </div>
            <a href="javascript:;" ng-click="leftJumps('/examTrain/4')" class="add-classification"><span></span>添加考卷</a>
            <table>
                <thead>
                <tr>
                    <td width="13%">序号</td>
                    <td width="30%">试卷名称</td>
                    <td width="14%">一级类目</td>
                    <td width="14%">二级类目</td>
                    <td width="14%">添加时间</td>
                    <td width="15%">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="p in paperList">
                    <td ng-bind="(curr-1)*10+$index+1"></td>
                    <td><div class="books-author" ng-bind="p.PaperTitle"></div></td>
                    <td ng-bind="p.ExCatId1Name"></td>
                    <td ng-bind="p.ExCatId2Name"></td>
                    <td ng-bind="p.CreateTime"></td>
                    <td><div class="books-btn"><a href="javascript:;" class="hover" ng-click="edit(x.PaperId)">编辑</a><a href="javascript:;" class="hover" ng-click="del(x.PaperId)">删除</a></div></td>
                </tr>
                </tbody>
            </table>
            <div class="page books-page">
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
            </div>
        </div>

    </div>
</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/paperManager.js" type="text/javascript"></script>
</@examTrain.js_scripts>
