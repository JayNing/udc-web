<!DOCTYPE html>
<html lang="en">
<#assign basePath = request.contextPath />

<#macro htmlHead title="" keywords="" description="" charset="utf-8" lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=${charset}"/>
    <meta http-equiv="Content-Language" content="${lang}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">

    <meta name="keywords" content="${keywords}">
    <meta name="description" content="${description}">
    <link rel="icon" href="${basePath}/resources/images/facicon/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${basePath}/resources/images/facicon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="//at.alicdn.com/t/font_705065_rvzuao5htog.css">
    <link href="${basePath}/resources/css/foundation-datepicker/foundation-datepicker.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/css/iconfont/iconfont.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${basePath}/resources/css/app.css">
    <link rel="stylesheet" href="${basePath}/resources/css/idangerous.swiper/idangerous.swiper.css" type="text/css">
    <script type="text/javascript" src="${basePath}/resources/3rd_party/echarts.4.0.4/echarts-all.js"></script>
    <script type="text/javascript" src="${basePath}/resources/js/date/schedule.js"></script>
    <script src="${basePath}/resources/3rd_party/jquery-1.12.4/jquery-1.12.4.min.js" type="text/javascript"></script>
    <script src="${basePath}/resources/3rd_party/foundation-datepicker3.0.0/foundation-datepicker.js"></script>
    <script src="${basePath}/resources/3rd_party/foundation-datepicker3.0.0/foundation-datepicker.zh-CN.js"></script>
    <script src="${basePath}/resources/3rd_party/echarts.4.0.4/echarts.common.min.js"></script>
    <script src="${basePath}/resources/3rd_party/layer/layer.js" type="text/javascript"></script>
    <script src="${basePath}/resources/3rd_party/idangerous.swiper.2.0.0/idangerous.swiper.min.js" ></script>
    <script src="${basePath}/resources/3rd_party/angular.1.6.9/angular.min.js"></script>
    <title>${title}</title>
    <script>
        // 全局变量
        var basePath = "${basePath}";
    </script>
    <#nested>
</head>
</#macro>

<#macro htmlBody>
<body ng-app="app">
<#--loading-->
<div ng-cloak class="sk-loading ng-cloak" ng-show="isLoading">
    <div class='sk-circle-bounce'>
        <div class='sk-child sk-circle-1'></div>
        <div class='sk-child sk-circle-2'></div>
        <div class='sk-child sk-circle-3'></div>
        <div class='sk-child sk-circle-4'></div>
        <div class='sk-child sk-circle-5'></div>
        <div class='sk-child sk-circle-6'></div>
        <div class='sk-child sk-circle-7'></div>
        <div class='sk-child sk-circle-8'></div>
        <div class='sk-child sk-circle-9'></div>
        <div class='sk-child sk-circle-10'></div>
        <div class='sk-child sk-circle-11'></div>
        <div class='sk-child sk-circle-12'></div>
    </div>
    <div class="sk-mask"></div>
</div>
<div class="header"  ng-controller="userController">
    <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
        <div class="data-edit del-edit">
            <div class="data-edit-head clearfix">
                <div>
                来自UDC：
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="del-main">
                <p>是否退出登录?</p>
                <a href="javascript:;" ng-click="close()">取消</a>
                <a href="javascript:;" ng-click="confirm()">确认</a>
            </div>
        </div>
    </div>
    <div class="main clearfix">
        <img src="${basePath}/resources/images/top/logo.png"  class="logo" />
        <ul>
            <li><a href="javascript:;" ng-click="topJumps('/')">首页</a></li>
            <li><a href="javascript:;" ng-click="topJumps('/topSkip/1')">资源管理</a></li>
            <li><a href="javascript:;"  ng-click="topJumps('/topSkip/2')">知识仓库</a></li>
            <li><a href="javascript:;" ng-click="topJumps('/topSkip/3')">考试培训</a></li>
            <li><a href="javascript:;" ng-click="topJumps('/topSkip/4')" >社区</a></li>
            <li><a href="javascript:;" ng-click="topJumps('/topSkip/5')">外部专家</a></li>
        </ul>
        <div class="user-info">
            <div class="user-main" ng-click="backIndex()">
                <div class="user-photo"  id="headImageInfo" ng-style="headImg"></div>
                <div class="user-name">
                    <h4 ng-bind="depa" id="idDepa"></h4>
                    <p  ng-bind="name" id="isName"></p>
                </div>
            </div>
            <a href="javascript:;" class="exit" ng-click="logout()">退出</a>
        </div>
    </div>
</div>
<div >
    <#nested>
</div>

<div class="footer">Copyright © 2017 掌心科技（苏州）有限公司版权所有</div>
</body>
</#macro>


<#macro js_scripts>
<script src="${basePath}/resources/js/app.js" type="text/javascript"></script>


    <#nested>
</#macro>

</html>
