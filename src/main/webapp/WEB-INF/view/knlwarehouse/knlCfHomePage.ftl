
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="知识仓库首页 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/knlwarehouse/knlCfHomePage.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>

<div class="knowledge-repository" ng-controller="keyController">
    <div class="repository-head">
        <h3>UDC知识仓库智能搜索</h3>
        <div class="intelligent-search-wrap">
            <div class="intelligent-search clearfix">
                <i class="iconfont icon-sousuo" ng-click="search()"></i>
                <input type="text" placeholder="输入关键词快速搜索" class="searchInput" ng-model="keySearch" ng-keyup="enterEvent($event,keySearch)"/>
                <i class="iconfont icon-youjiantou1" ng-click="search()"></i>
            </div>
        </div>
        <div class="repository-label-wrap">
            <div class="repository-label clearfix">
                <div>
                    <div>热门标签</div>
                    <span ng-repeat="x in hotLabel" ng-bind="x.tagName" ng-click="tagSearch(x.tagName)"></span>
                </div>
                <a href="javascript:;" ng-click="knsClassifySearch()">分类检索></a>
            </div>
        </div>
        <ul class="repository-nav clearfix">
            <li><a href="javascript:;" class="hover" ng-click="selectType=1">智能推荐</a></li>
            <li><a href="javascript:;" ng-click="selectType=2">历史搜索</a></li>
            <li><a href="javascript:;" ng-click="selectType=3">公司热点</a></li>
        </ul>
    </div>
    <div class="repository-content">
        <div class="repository-main">
            <div class="news repository-news">
                <#--推荐-->
                <a href="" class="news-list clearfix untrans" ng-repeat="x in searchRecord" ng-show="selectType==1" ng-click="searchDetail(x.KId,x.DisId,x.DisType)">
                    <div class="news-title">
                        <span></span>
                        {{x.Title}}
                    </div>
                    <div class="news-time clearfix">
                        <span ng-bind="x.time"></span>
                    </div>
                </a>
                <#--最近-->
                <a href="" class="news-list clearfix untrans" ng-repeat="x in searchRecRecord" ng-show="selectType==2" ng-click="searchDetail(x.KId,x.DisId,x.DisType)">
                    <div class="news-title">
                        <span></span>
                        {{x.Title}}
                    </div>
                    <div class="news-time clearfix">
                        <span ng-bind="x.time"></span>
                    </div>
                </a>
                <#--热门-->
                <a href="" class="news-list clearfix untrans" ng-repeat="x in searchHotRecord" ng-show="selectType==3" ng-click="searchDetail(x.KId,x.DisId,x.DisType)">
                    <div class="news-title">
                        <span></span>
                        {{x.Title}}
                    </div>
                    <div class="news-time clearfix">
                        <span ng-bind="x.time"></span>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>

<script src="${basePath}/resources/js/knlwarehouse/knlCfHomePage.js" type="text/javascript"></script>

</@defaultLayout.js_scripts>
