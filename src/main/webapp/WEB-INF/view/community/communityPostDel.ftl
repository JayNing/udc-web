
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="社区-贴文详情 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/community/communityPostDel.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main train clearfix"  ng-controller="communityposdController">
    <input type="hidden" value="${RequestParameters.disId}" id="dis"/>
    <div class="train-left ">
        <div class="q-a">
            <div class="q-title clearfix">
                <h3 ng-cloak class="ng-cloak">{{title}}</h3>
                <a href="javascript:;" ng-click="add()" ng-class="{'btn-disable':KnlgFlag==2}" >{{KnlgFlag==2?'已加入知识仓库':'加入知识仓库'}}</a>
            </div>
            <div class="q-author" ng-cloak class="ng-cloak">作者    {{department}}—{{name}}<span ng-click="addFollow($event)" ng-if="!FollowButton"><em ng-if="!IsFollow">关注</em><em ng-if="IsFollow">已关注</em></span></div>
            <div class="q-article-main">
                <div ng-cloak class="ng-cloak arrticle-content" ng-bind-html="content | showAsHtml" ></div>
                <div class="q-label">
                    <span ng-repeat="x in labels" ng-bind="x" ng-click="labelSearch(x)"></span>
                </div>
            </div>
            <div class="clearfix q-foot" >
                <div class="likes">
                    <div class="q-like" ng-click="addUse()">
                        <i ng-class="{true:'hover',false:''}[IsUseful]">有用</i>
                        <span ng-bind="use"></span>
                    </div>
                    <div class="q-no-like" ng-click="addUseless()">
                        <i ng-class="{true:'hover',false:''}[IsUnUseful]">无用</i>
                        <span ng-bind="useless"></span>
                    </div>
                </div>
                <div class="operation">
                    <div class="q-collection" ng-click="collection($event)">
                        <img class="rating" ng-if="IsCollect" src="${basePath}/resources/images/app/rating.png" />
                        <img class="no-rating" ng-if="!IsCollect" src="${basePath}/resources/images/community/q-collection-icon.png" />
                        收藏
                    </div>
                <#--<div class="q-share" ng-click="share()">
                    <img src="${basePath}/resources/images/community/q-share-icon.png" />
                    分享
                </div>
                <div class="q-report" ng-click="report()">
                    <img src="${basePath}/resources/images/community/q-report-icon.png" />
                    举报
                </div>-->
                </div>
            </div>
            <div class="message-board">
                <h3 class="clearfix">评论区<span>{{message.length}}/150</span></h3>
                <textarea placeholder="写下你的评论…"  ng-model="message" ng-keyup="xz($event,150)"></textarea>
                <div class="clearfix expression">
                    <a href="javascript:;" ng-click="submit()">回答</a>
                </div>
            </div>
            <div class="message-content" ng-if="interlocution">
                <h3 class="clearfix">全部回答<span>（{{allNum}}）</span></h3>
                <div class="message-wrap">
                    <div class="message-list" ng-repeat="x in interlocution">
                        <div class="message-list-main">
                            <div class="message-img" ng-if="x.HeadUrl==null" style="background: url('${basePath}/resources/images/app/message.png') no-repeat center;background-size:cover;"></div>
                            <div class="message-img" ng-if="x.HeadUrl!=null" ng-style="{'background-image': 'url('+x.HeadUrl+')'}"></div>
                            <div class="message-detail" >
                                <div ng-cloak class="ng-cloak">{{x.DepartmentName}}：{{x.RealName}}<span>{{x.CreateTime}}</span></div>
                                <p ng-bind="x.ComContent"></p>
                                <div class="set-message">
                                    <div><i class="iconfont icon-zan1" ng-click="likes(x.CommentId,$event)" ng-class="{true:'hover',false:''}[x.IsComLike]" "></i>{{x.ComLike}}</div>
                                <#--<span class="reply" ng-click="reply($event)">回复</span>
                                <span class="report" ng-click="report(x.AnsId)">举报</span>-->
                                    <a href="javascript:;" ng-show="x.ButtonFlag==1" ng-click="setAnswer(x.AnsId,x.QueId)">设为答案</a>
                                </div>
                            </div>
                        </div>
                    <#--<div class="reply-main none" >
                        <div class="reply-text">
                            <div></div>
                            <textarea ng-model="editContent"></textarea>
                        </div>
                        <div class="reply-btn clearfix">
                            <a href="javascript:;" class="q-publish" ng-click="publish(x.QueId,$event)">发表</a>
                            <a href="javascript:;" class="q-cancel" ng-click="cancel($event)">取消</a>
                        </div>
                    </div>-->
                    </div>

                </div>
                <div class="page books-page">
                    <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
                </div>
            </div>
        </div>

    </div>
    <div class="train-right">
        <div class="online-personnel" ng-controller="recommendController">
            <h3>相关推荐</h3>
            <div class="training-case" ng-repeat="x in recommends">
                <a href="javascript:;" ng-bind="x.DisTitle" ng-click="detail(x.DisId,x.DisType)"></a>
                <p>作者   {{x.DepartmentName}}—{{x.AnswerName}}</p>
            </div>
        </div>
        <div class="online-personnel" ng-controller="hotController">
            <h3>社区热搜</h3>
            <div class="hot-case">
                <a href="javascript:;" ng-repeat="x in hots" ng-bind="x.tagName" ng-click="hotSearch(x.tagName)"></a>
            </div>
        </div>
    </div>
</div>


</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/community/communityPostDel.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
