
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="会员中心 -关注列表" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/member/followList.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

<div class="member-modular-right message-notification">
    <div class="notification-title">
        <img src="${basePath}/resources/images/app/favorite.png">
        <span>我的关注</span>
    </div>
    <div class="post-wrap" ng-controller="followController">
        <div class="my-follow-main clearfix">
            <div class="my-follow-list" ng-repeat="x in follows">
                <div class="follow-photo" ng-if="x.avatar==null" style="background: url('${basePath}/resources/images/app/message.png') no-repeat center;background-size: cover;"></div>
                <div class="follow-photo" ng-if="x.avatar!=null" ng-style="{'background-image': 'url('+x.avatar+')'}" ></div>
                <div class="follow-user">
                    <h4 ng-cloak class="ng-cloak">{{x.departMentName}}</h4>
                    <span ng-cloak class="ng-cloak">{{x.realName}}</span>
                </div>
            </div>
            <a href="javascript:;" class="follow-more" ng-click="getMore()" ng-if="follows.length>5" ng-show="moreBool">更多</a>
        </div>
    </div>
</div>


</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/member/followList.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
