<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人主页 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/member/memberCenter.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
    <div class="member-modular-right" >

        <div class="post" ng-controller="postController">
            <div class="notice-title">
                <img src="${basePath}/resources/images/app/documentscopy.png" />
                <span>我的帖子</span>
            </div>
            <div class="post-main">
                <div class="post-list clearfix" ng-repeat="x in posts">
                    <span class="post-dian"></span>
                    <a href="javascript:;" ng-cloak class="ng-cloak" ng-click="detail(x.DisId,x.DisType)"><i ng-show="x.DisType==2">问：</i>{{x.DisTitle}}</a>
                    <span class="post-label" ng-bind="x.EssayTypeName"></span>
                    <div><a href="" ng-cloak class="ng-cloak">{{x.ComCount}}人</a>已回复</div>
                </div>
                <a href="javascript:;" ng-click="getMore()" ng-if="posts.length>9" >更多>></a>
                <div class="clearfix post-btn">
                    <a href="javascript:;" ng-click="leftJumps('/askShare/1')"><img src="${basePath}/resources/images/community/question-icon.png" />我要提问</a>
                    <a href="javascript:;" ng-click="leftJumps('/askShare/2')"><img src="${basePath}/resources/images/app/notice-share.png" />我要分享</a>
                </div>
            </div>
        </div>
        <div class="my-follow" ng-controller="followController">
            <div class="notice-title">
                <img src="${basePath}/resources/images/app/favorite.png" />
                <span>我的关注</span>
            </div>
            <div class="my-follow-main clearfix">
                <div class="my-follow-list" ng-repeat="x in follows">
                    <div class="follow-photo" ng-if="x.avatar==null" style="background: url('${basePath}/resources/images/app/message.png') no-repeat center;background-size: cover;"></div>
                    <div class="follow-photo" ng-if="x.avatar!=null" ng-style="{'background-image': 'url('+x.avatar+')'}" ></div>
                    <div class="follow-user">
                        <h4 ng-cloak class="ng-cloak">{{x.departMentName}}</h4>
                        <span ng-cloak class="ng-cloak">{{x.realName}}</span>
                    </div>
                </div>
                <a href="javascript:;" class="follow-more" ng-click="getMore()" ng-if="follows.length>9" ng-show="moreBool">更多</a>
            </div>
        </div>
        <div class="my-collection" ng-controller="collectionController">
            <div class="notice-title">
                <img src="${basePath}/resources/images/app/rating.png" />
                <span>我的收藏</span>
            </div>
            <div class="collection-main">
                <div class="collection-list" ng-repeat="x in collects">
                    <a href="javascript:;" ng-cloak class="ng-cloak" ng-click="detail(x.DisId,x.DisType)"><span></span>{{x.DisTitle}}</a>
                    <p ng-cloak class="ng-cloak">作者   {{x.DepartmentName}}—{{x.RealName}}</p>
                </div>
                <a class="collection-more" href="javascript:;" ng-click="getMoreCollect()" ng-if="collects.length>9" >更多>></a>
            </div>

        </div>
    </div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/member/memberCenter.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
