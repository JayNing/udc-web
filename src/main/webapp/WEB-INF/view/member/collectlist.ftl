
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="会员中心 -收藏列表" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/member/collectlist.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

<div class="member-modular-right message-notification">
    <div class="notification-title">
        <img src="${basePath}/resources/images/app/documentscopy.png">
        <span>我的收藏</span>
    </div>
    <div class="post-wrap" ng-controller="postController">
        <div class="post-main">
            <div class="post-list clearfix" ng-repeat="x in posts">
                <span class="post-dian"></span>
                <a href="javascript:;" ng-cloak class="ng-cloak" ng-click="detail(x.DisId,x.DisType)"><i ng-show="x.DisType==2">问：</i>{{x.DisTitle}}</a>
                <span class="post-label" ng-repeat="y in x.EssayTypeName">{{y}}</span>
                <div><a href="" ng-cloak class="ng-cloak">{{x.ComCount}}人</a>已回复</div>
            </div>

        </div>
        <div class="page message-page" ng-if="allPage>1">
            <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}">{{p}}</a>
        </div>
    </div>
</div>


</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/member/collectlist.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
