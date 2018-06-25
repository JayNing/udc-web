
<#import "/default/defaultLayout.ftl" as defaultLayout/>
<@defaultLayout.htmlHead title="社区 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/community/community.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>

<div class="main search-result clearfix">
    <input type="hidden" value="${RequestParameters.name}" id="name"/>
    <div class="search-show-left community-left" ng-controller="keyController">
        <div class="intelligent-search clearfix">
            <i class="iconfont icon-sousuo" ng-click="search()"></i>
            <input type="text" placeholder="输入关键词快速搜索" ng-model="keySearch" ng-keyup="searchEnter($event)">
            <i class="iconfont icon-youjiantou1"  ng-click="search()"></i>
        </div>
        <div class="community-type">
            <div class="community-label clearfix">
                <div>
                    <span ng-repeat="x in hotLabel" ng-bind="x.essayTypeName"  ng-click="tagSearch(x.essayTypeName)">案例</span>
                </div>
                <a href="javascript:;" ng-click="leftJumps('/askShare/2')"
                   class="share"><img src="${basePath}/resources/images/community/share.png" />我要分享</a>
                <a href="javascript:;" ng-click="leftJumps('/askShare/1')" class="question"><img src="${basePath}/resources/images/community/question.png" />我要提问</a>
            </div>
        </div>
        <div class="community-list-main">
            <div class="community-list" ng-repeat="x in searchRecord">
                <h3 ng-click="communityLink(x.DisId,x.DisType)">
                    <em ng-show="x.DisType==2">问: </em>{{x.DisTitle}}<span ng-bind="x.EssayTypeName" ng-if="x.EssayTypeName"></span>
                </h3>
                <p class="community-answer">
                    <span ng-show="x.DisType==2&&x.AnsContent!=null" ng-click="communityLink(x.DisId,x.DisType)">推荐答案</span><strong  ng-if="x.DisType==2&&x.AnsContent!=null" ng-click="communityLink(x.DisId,x.DisType)">{{x.AnsContentstr}}</strong><strong  ng-if="x.DisType==2&&x.AnsContent==null" ng-click="communityLink(x.DisId,x.DisType)">暂无推荐答案！</strong><strong  ng-if="x.DisType==1" ng-click="communityLink(x.DisId,x.DisType)">{{x.EssayContent}}</strong><a href="javascript:;" ng-click="communityLink(x.DisId,x.DisType)" >[ 阅读全文 ]</a>
                </p>
                <div class="clearfix community-data">
                    <div class="zan"><i class="icon-zan1 iconfont"></i><span ng-bind="x.UsefulCount"></span></div>
                    <div class="pinglun"><i class="icon--pinglun iconfont"></i><span ng-bind="x.ComOrAnswerTotalCount"></span>条评论</div>
                </div>
            </div>
        </div>
        <#--<div class="more">已经到底</div>-->
        <div class="page books-page">
            <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
        </div>
    </div>
    <#--引入公共模板文件-->
    <#include "/default/rightLayout.ftl">
</div>

</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/community/community.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/default/rightLayout.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
