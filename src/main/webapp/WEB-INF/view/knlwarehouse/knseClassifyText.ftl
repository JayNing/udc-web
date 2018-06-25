
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="知识仓库-文本 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/knlwarehouse/knseClassifyText.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main warehouse-article-detail clearfix">
    <div class="warehouse-nav">
        <h3><span>+</span>分类检索</h3>
        <div class="warehouse-list-wrap">
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>经营融资</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>精品投行</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>平台运营</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>基金机构行政后台</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
                <div class="none">
                    <a href="" class="hover">境外投资服务平台（产业联盟）</a>
                    <a href="">GMA线上服务</a>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>创新类业务</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>物业代理</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>总部经济</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>供应链</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>平台经济</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>服务业开放</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>企业行政后台</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix">
                    <span>物业咨询</span>
                    <i class="iconfont icon-youjiantou"></i>
                    <i class="iconfont icon-xiajiantou none"></i>
                </div>
            </div>
        </div>
    </div>
    <div class="warehouse-content warehouse-article" ng-controller="textController">
        <div class="clearfix article-top">
            <h3 ng-bind="title"></h3>
            <div>
                <span><i ng-bind="num"></i>浏览</span>
                <a href=""><img src="${basePath}/resources/images/community/browse.png" /></a>
            </div>
        </div>
        <div class="article-label clearfix">
            <span ng-repeat="x in labs" ng-bind="x"></span>
        </div>
        <div class="article-author">
            作者<div ng-style="ys"></div>市场部：<i ng-bind="name"></i>
        </div>
        <div class="communicate" ng-bind="content">
        </div>
        <a href="{{url}}" class="download">
            <span>PDF</span>
            文件下载
        </a>
        <div class="article-about">
            <h4>相关文章</h4>
            <div ng-repeat="x in arrticels">
                <a href="x.url" class="article-about-list" >
                    <div class="clearfix">
                        <i></i>
                        <div ng-bind="x.title"></div>
                        <span ng-bind="x.time"></span>
                    </div>
                    <p ng-cloak="">作者  {{x.department}}：{{x.name}}</p>
                </a>
            </div>
        </div>
    </div>
</div>

</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>


<script src="${basePath}/resources/js/knlwarehouse/knseClassifyText.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
