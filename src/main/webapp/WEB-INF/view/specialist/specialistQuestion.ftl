<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="专家提问管理 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/specialist/specialistQuestion.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

<div class="main member-center clearfix">
    <div class="member-modular-right boardroom-right message-notification" ng-controller="questionController">
        <div class="bg edit-user" ng-cloak class="ng-cloak" ng-show="questionBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/book/borrow.png" />
                        专家提问
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="question-main">
                    <div class="clearfix">
                        <span>问题 : </span>
                        <div ng-bind="Content"></div>
                    </div>
                    <div class="clearfix">
                        <span>提问人 : </span>
                        <div ng-bind="RealName"></div>
                    </div>
                    <div class="clearfix">
                        <span>所属部门 : </span>
                        <div ng-bind="DepartmentName"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="/resources/images/app/expert.png">
            <span>专家提问管理</span>
        </div>
        <div class="books-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" ng-model="search1" class="firstQuestion" ng-keyup="enterEvent($event)"  placeholder="问题" >
                </div>
                <div class="books-search clearfix">
                    <input type="text" ng-model="search2" ng-keyup="enterEvent($event)"  placeholder="提问人">
                </div>
                <div class="books-search-time">
                    <#include "/default/timeLayout.ftl">
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width=12%>序号</td>
                    <td width=33%>问题</td>
                    <td width=13%>提问人</td>
                    <td width=13%>所属部门</td>
                    <td width=13%>提问时间</td>
                    <td width=16%>操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in questions">
                    <td ng-cloak class="ng-cloak">{{((curr-1)*10)+$index+1}}</td>
                    <td><div class="books-name" ng-cloak class="ng-cloak">{{x.Content}}</div></td>
                    <td><div class="books-author" ng-cloak class="ng-cloak">{{x.RealName}}</div></td>
                    <td><div class="books-author" ng-cloak class="ng-cloak">{{x.DepartmentName}}</div></td>
                    <td ng-cloak class="ng-cloak">{{x.Createtime}}</td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;" class="hover" ng-click="see(x.ConsultId)" >查看详情</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="page books-page">
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
            </div>

        </div>
    </div>
</div>


</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/specialist/specialistQuestion.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
