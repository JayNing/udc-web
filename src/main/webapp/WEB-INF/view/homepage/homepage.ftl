
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="UDC-首页 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link rel="stylesheet" type="text/css" href="${basePath}/resources/css/homepage/homepage.css">
<@defaultLayout.htmlBody>

<div class="right-nav-container">
    <ul class="right-nav-list">
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="eas"></i><span>EAS</span></a></li>
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="nw"></i><span>外联发内网</span></a></li>
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="oa"></i><span>外联发OA</span></a></li>
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="bb"></i><span>报表中心</span></a></li>
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="yp"></i><span>我的云盘</span></a></li>
        <li class="right-nav-list-item"><a href="" class="fs15"><i class="qx"></i><span>权限管理</span></a></li>
    </ul>
</div>

<div class='body-container member-center'>
    <div class="container">
        <div class="top-container clearfix">
            <div class="left-container fl">
                <div class="msg-container" ng-controller="msgController">
                    <div class="msg-header page-header clearfix">
                        <h3 class="msg-header-title fs16">【通知】</h3>
                        <a class="msg-header-more fs14" href="/informList">更多>></a>
                        <i class="l"></i><i class="s"></i>
                    </div>
                    <div class="msg-content">
                        <div class="msg-list fs0">
                            <li ng-repeat="item in notifications" class="msg-list-item" ng-if="item.MessageType==1"><a href="javascript:;" ng-click="showT($index)" class="fs14"><span class="l" >[公告]</span>{{item.MessageTitle}}<span class="t fr">{{item.CreateTime}}</span></a></li>
                            <li ng-repeat="item in notifications" class="msg-list-item" ng-if="item.MessageType>2"><a href="${basePath}/informList"  class="fs14"><span class="l">[消息]</span>{{item.MessageTitle}}<span class="t fr">{{item.CreateTime}}</span></a></li>
                        </div>
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
                <div class="expired-container">
                    <div class="expired-header page-header clearfix">
                        <h3 class="expired-header-title fs16">已到期合同</h3>
                        <a class="expired-header-more fs14" href="">更多>></a>
                        <i class="l"></i><i class="s"></i>
                    </div>
                    <div class="expired-content">
                        <ul class="expired-list">
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                            <li class="expired-list-item"><a href="" class="fs14">22467896中信场地合同续租场地合同《WSKZIDMASKJJXSSLMMH…</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right-container fr">
                <div class="gr-container">
                    <div class="gr-header page-header">
                        <h3 class="fs16">个人及属下活动</h3>
                    </div>
                    <div class="gr-content">
                        <div class="gr-select">
                            <div class="gr-select-button">
                                <span class="fs18 fl" ng-bind="name">张山峰</span>
                                <a href="javascript:;" class="fl"></a>
                            </div>
                            <ul class="gr-select-list">
                                <li><a href="javascript:;">张山峰</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--日历模块-->
                <div class="rl-container">
                    <div id="schedule-box" class="boxshaw"></div>
                    <div>
                        <h3 id='h3Ele'></h3>
                    </div>

                </div>
                <div class="sj-container">
                    <div class="sj-header pr"><span class="fs16">当天总共事件：8</span></div>
                    <div class="sj-content">
                        <ul class="sj-list">
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                            <li class="sj-list-item"><a href="">参加《知识库操作说明》培训会</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="tb-container">
            <div class="tb-header fs16 clearfix">
                <h3 class="fs16 fl">四个业务部门指标能力</h3>
                <ul class="tb-info clearfix fr f0">
                    <li class="fl pr fs16">商务咨询部</li>
                    <li class="fl pr fs16">金融咨询部</li>
                    <li class="fl pr fs16">运营企划部</li>
                    <li class="fl pr fs16">研究室</li>
                </ul>
            </div>
            <!--图表内容-->
            <div class="tb-content clearfix" >
                <div class="tb-list fl fs16">
                    <span>商务咨询部</span>
                    <div class="tb-list-item" >

                        <div id="chartmain"></div>
                    </div>
                </div>
                <div class="tb-list fl fs16">
                    <span>金融咨询部</span>
                    <div class="tb-list-item" >

                        <div id="chartmain1"></div>
                    </div>
                </div>
                <div class="tb-list fl fs16">
                    <span>运营企划部</span>
                    <div class="tb-list-item" >

                        <div id="chartmain2"></div>
                    </div>
                </div>
                <div class="tb-list fl mr0 fs16">
                    <span>研究室</span>
                    <div class="tb-list-item" >

                        <div id="chartmain3"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/homepage/homepage.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
