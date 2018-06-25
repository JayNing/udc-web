
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人中心 -通知列表" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/member/informList.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
    <div class="member-modular-right message-notification">
        <div class="notification-title">
            <img src="${basePath}/resources/images/app/comment.png">
            <span>消息通知</span>
        </div>
        <div class="notification-main" ng-controller="newController">
            <div class="notification-tab clearfix">
                <a href="javascript:;" ng-class="{true:'hover',false:''}[tab==1]" ng-click="select(1)" >公告</a>
                <a href="javascript:;" ng-class="{true:'hover',false:''}[tab==2]" ng-click="select(2)">消息</a>
            </div>
            <div class="notification-list">
                <a href="" class="clearfix" ng-repeat="x in notifications" ng-if="tab==1&&(x.MessageType==1)" ng-click="showT($index)">
                    <span class="notification-tit">[公告]</span>
                    <p ng-bind="x.MessageTitle"></p>
                    <span class="notification-time" ng-bind="x.CreateTime"></span>
                </a>
                <a href="" class="clearfix" ng-repeat="x in notifications" ng-if="tab==2&&(x.MessageType!=1&&x.MessageType!=2)">
                    <span class="notification-tit">[消息]</span>
                    <p ng-bind="x.MessageContent"></p>
                    <span class="notification-time" ng-bind="x.CreateTime"></span>
                </a>
               
            </div>
            <div class="page message-page" ng-if="allPage>1">
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}">{{p}}</a>
            </div>
            <div class="bg edit-user" ng-cloak class="ng-cloak" ng-show="isShow">
                <div class="data-edit">
                    <div class="data-edit-head clearfix">
                        <div>
                            <img src="${basePath}/resources/images/book/borrow.png" />
                            平台公告:
                        </div>
                        <span ng-click="isShow = false">x</span>
                    </div>
                    <div class="question-main">
                        <div class="clearfix">
                            <span>标题 : </span>
                            <div ng-bind="messageTitle"></div>
                        </div>
                        <div class="clearfix">
                            <span>内容 : </span>
                            <div ng-bind="messageContent"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>

<script src="${basePath}/resources/js/member/informList.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
