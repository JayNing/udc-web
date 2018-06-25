
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="知识仓库-分类 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/knlwarehouse/knseClassify.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main warehouse clearfix" >
    <div class="warehouse-nav" ng-controller="nodeController">
        <h3><span>+</span>分类检索</h3>
        <div class="warehouse-list-wrap">
            <div class="warehouse-list" ng-repeat="x in knlwarehouseNode" ng-class="{'hover':x.isActive}">
                <div class="warehouse-classification-title clearfix" ng-click="hoverCat1($index)">
                    <span ng-bind="x.RepositoryCategory.repCatName"></span>
                    <i class="iconfont icon-youjiantou" ng-if="!x.isHover"></i>
                    <i class="iconfont icon-xiajiantou" ng-if="x.isHover"></i>
                </div>
                <div class="childNode child147 ng-scope" ng-if="x.IsHaveChild==1&&x.isHover">
                    <a href="" ng-class="{'hover':child.isHover}" ng-click="hoverCat2($parent.$index,$index)" ng-repeat="child in x.childrenNode">{{child.repCatName}}</a>
                </div>
            </div>
        </div>
    </div>
    <div class="warehouse-content" ng-controller="classifyController">
        <div class="search-head clearfix">
            <div class="warehouse-search clearfix">
                <i class="iconfont icon-sousuo"></i>
                <input type="text" placeholder="输入关键词" ng-model="warehouseSearch.search"/>
            </div>
            <div class="warehouse-time">
                <#include "/default/timeLayout.ftl">
            </div>
        </div>
        <div class="clearfix process">
            <div class="classifyNode" ng-repeat="x in nodes" ng-style="setWidth">
                <a href="javascript:;" ng-click="myNode(x.flowId,$event)">
                    <p ng-bind="x.flowName" class="nodeShow"></p>
                </a>
                <div><i class="iconfont icon-youjiantou"></i></div>
            </div>
        </div>
        <div class="news">
            <a href="" class="clearfix" ng-repeat="x in knlwarehouses" ng-click="searchDetail(x.KId)">
                <div class="news-title">
                    <span></span>
                    {{x.Title}}
                    <#--<img src="${basePath}/resources/images/knlwarehouse/news-word.png" />-->
                    <div ng-if="x.BbsFlag==2" class="hover">社区沉淀</div>
                  <#--  <div ng-if="x.BbsFlag==2">社区沉淀</div>-->
                </div>
                <div class="news-time clearfix">
                    <div><span ng-bind="x.ViewCount"></span> 浏览</div>
                    <span ng-bind="x.CreateTime"></span>
                </div>
            </a>
        </div>
        <div class="page books-page">
            <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
        </div>
    </div>
</div>

</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/knlwarehouse/knseClassify.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
