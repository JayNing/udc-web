
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="知识仓库-知识详情 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/knlwarehouse/knseClassifyPPT.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main warehouse-article-detail clearfix" >
    <input type="hidden" value="${RequestParameters.id}" id="knlgitid"/>
    <div class="warehouse-nav" ng-controller="nodeController">
        <h3><span>+</span>分类检索</h3>
        <div class="warehouse-list-wrap">
            <div class="warehouse-list" ng-repeat="x in knlwarehouseNode" ng-class="{'hover':x.isActive}">
                <div class="warehouse-classification-title clearfix" ng-click="hoverCat1($index,$event)">
                    <span ng-bind="x.RepositoryCategory.repCatName"></span>
                    <i class="iconfont icon-youjiantou" ng-if="!x.isHover"></i>
                    <i class="iconfont icon-xiajiantou" ng-if="x.isHover"></i>
                </div>
                <div class="childNode child147 ng-scope" ng-if="x.IsHaveChild==1&&x.isHover">
                    <a href="" ng-class="{'hover':child.isHover}" ng-click="hoverCat2($parent.$index,$index,$event)" ng-repeat="child in x.childrenNode">{{child.repCatName}}</a>
                </div>
            </div>

        </div>
    </div>
    <div class="warehouse-content warehouse-article" ng-controller="fileController">
        <div class="clearfix article-top">
            <h3 ng-bind="title"></h3>
            <div ng-init="imgType=true">
                <span><i ng-bind="num"></i> 浏览</span>
                <a href="javascript:;" ng-click="goBack()"><img ng-mouseover="imgType=false" ng-mouseleave="imgType=true" ng-src="{{imgType?'${basePath}/resources/images/community/browse.png':'${basePath}/resources/images/community/browse1.png'}}" /></a>
            </div>
        </div>
        <div class="article-label clearfix">
            <span ng-repeat="x in labs" ng-bind="x" ng-click="tagSearch(x)"></span>
        </div>
        <div class="article-author" >
            作者: <#--<div ng-style="ys"></div>-->{{name}}
        </div>
        <p class="pdf-brief" ng-cloak class="ng-cloak">简介：{{brief}}</p>
        <div class="communicate" ng-cloak class="ng-cloak" ng-if="content!=null" >
            内容: <div ng-bind-html="content | showAsHtml"></div>
        </div>
        <div ng-if="urls.length>0 && urls[0]">
            <p class="file-explain">该文件不支持在线观看，请下载后查看。</p>
            <div class="fileDown">
                <a href="{{x}}" class="download" ng-repeat="x in urls">
                    <span>F</span>
                    文件下载
                </a>
            </div>
        </div>
        <div class="article-about">
            <h4>相关文章</h4>
            <a href="" class="article-about-list" ng-repeat="x in arrticles">
                <div class="clearfix">
                    <i></i>
                    <div ng-bind="x.Title"></div>
                    <span ng-bind="x.CreateTime"></span>
                </div>
                <p ng-cloak class="ng-cloak">作者：{{x.Author}}</p>
            </a>
        </div>
    </div>
</div>

</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>


<script src="${basePath}/resources/js/knlwarehouse/knseClassifyPPT.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
