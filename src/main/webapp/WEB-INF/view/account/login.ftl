<!DOCTYPE html>
<html ng-app="app">
<#assign basePath = request.contextPath />
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="UDC知识库管理平台">
    <meta name="description" content="UDC知识库管理平台">
    <meta name="author" content=""/>
    <meta name="copyright" content=""/>
    <title>登录</title>
    <link rel="icon" href="${basePath}/resources/images/facicon/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${basePath}/resources/images/facicon/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/resources/css/idangerous.swiper/idangerous.swiper.css" type="text/css">
    <link href="${basePath}/resources/css/foundation-datepicker/foundation-datepicker.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/css/iconfont/iconfont.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/css/app.css" rel="stylesheet" type="text/css">
    <link href="${basePath}/resources/css/account/login.css" rel="stylesheet" type="text/css">
    <script src="${basePath}/resources/3rd_party/jquery-1.12.4/jquery-1.12.4.min.js" type="text/javascript"></script>
    <script src="${basePath}/resources/3rd_party/layer/layer.js" type="text/javascript"></script>
    <script src="${basePath}/resources/3rd_party/angular.1.6.9/angular.min.js"></script>
    <script src="${basePath}/resources/js/app.js" type="text/javascript"></script>
    <script src="${basePath}/resources/js/account/login.js" type="text/javascript"></script>
</head>
<body>
<script>
    // 全局变量
    var basePath = "${basePath}";
</script>
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
<div class="load" ng-controller="loadController">
    <div class="load-main clearfix">
        <div class="load-bar">
            <img src='${basePath}/resources/images/app/load-logo.png' />
            <p>Welcome to the UDC knowledge base management platform</p>
            <p class="load-welcome">欢迎登录UDC知识库管理平台</p>
        </div>
        <div class="load-form">
            <div>Sign in <span>UDC</span></div>
            <form ng-keyup="enterEvent($event)">
                <div class="load-module">
                    <h4>账号：</h4>
                    <input type="text" ng-model="account" placeholder="输入你的登录账号"/>
                </div>
                <div class="load-module">
                    <h4>密码：</h4>
                    <input type="password" ng-model="password" placeholder="输入 登录密码"/>
                </div>
                <a href="javascript:;"   ng-click="login()">登录</a>
            </form>
        </div>
    </div>
    <div class="load-bg1"></div>
    <div class="load-bJS
    g2"></div>
</div>

</body>
</html>


