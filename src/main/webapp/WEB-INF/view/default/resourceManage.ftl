<#--资源管理二级模板-->
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<#macro modelHead title charset="utf-8" lang="zh-CN">

<@defaultLayout.htmlHead title="${title}">
<#--<link rel="stylesheet" href="${basePath}/resources/css/test/test.css">-->
    <#nested>
</@defaultLayout.htmlHead>


</#macro>


<#macro modelBody>
<@defaultLayout.htmlBody>

<div class="main member-center clearfix" >

    <div class="member-modular-left boardroom-left" ng-controller="navController">
        <div class="boardroom-main">
            <div class="boardroom-head"><img src="${basePath}/resources/images/app/boardroom-icon.png" />会议室预定</div>
            <div class="boardroom-list">
                <a href="javascript:;">
                    <h4><i></i><span>1号会议室</span></h4>
                    <p>外高桥30</p>
                    <img src="${basePath}/resources/images/app/meeting.png" />
                </a>
                <a href="javascript:;">
                    <h4><i></i><span>2号会议室</span></h4>
                    <p>外高桥30</p>
                </a>
                <a href="javascript:;">
                    <h4><i></i><span>3号会议室</span></h4>
                    <p>外高桥30</p>
                </a>
                <a href="javascript:;">
                    <h4><i></i><span>4号会议室</span></h4>
                    <p>外高桥30</p>
                </a>
            </div>
        </div>
        <div class="boardroom-other">
            <h3>其他</h3>
            <a href="javascript:;" ng-click="leftJumps('/bookBorrow')"><img src="${basePath}/resources/images/book/books.png" />图书借阅</a>
        </div>

    </div>

    <#nested>
</div>

</@defaultLayout.htmlBody>
</#macro>


<#macro js_scripts>
    <@defaultLayout.js_scripts>
    </@defaultLayout.js_scripts>
    <#nested>
<script src="${basePath}/resources/js/memberCenter/memberNavLeft.js" type="text/javascript"></script>
</#macro>

