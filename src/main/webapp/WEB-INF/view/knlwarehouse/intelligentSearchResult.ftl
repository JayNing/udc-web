
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="智能搜索检索结果" keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/knlwarehouse/intelligentSearchResult.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main search-result clearfix" ng-controller="keyController">
    <div class="search-show-left" >
        <div class="intelligent-search clearfix" >
        <#if (RequestParameters["keySearch"])?? >
            <input type="hidden" id="searchKeyValue" value="${RequestParameters["keySearch"]}" />
            <input type="hidden" id="isModel" value="key" />
        <#else>
            <input type="hidden" id="searchTagValue" value="${RequestParameters["tagSearch"]}" />
            <input type="hidden" id="isModel" value="tag" />
        </#if>
            <i class="iconfont icon-sousuo" ng-click="search(1)"></i>
            <input type="text" placeholder="输入关键词快速搜索" ng-model="keySearch" ng-keyup="searchEnter($event)" >
            <i class="iconfont icon-youjiantou1" ng-click="keySearchA(1)"></i>
        </div>
        <div class="repository-type" >
            <div class="repository-label clearfix">
                <div>
                    <div>热门标签</div>
                    <span ng-repeat="x in hotLabel" ng-bind="x.tagName" ng-click="tagSearch(x.tagName)"></span>
                </div>
                <a href="javascript:;" ng-click="knsClassifySearch()"><span>+</span>分类检索</a>
            </div>
        </div>
        <div class="search-list-main">
            <div class="search-list" ng-repeat="x in searchRecord">
                <a href="javascript:;" ng-click="searchDetail(x.KId)"  ng-bind="x.Title"></a>
                <p ng-bind="x.Profile"></p>
                <div class="clearfix">
                    <span ng-repeat="y in x.tagStrings" ng-click="tagSearch(y)">{{y}}</span>

                </div>
            </div>
        </div>
        <div class="page books-page">
            <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
        </div>
    </div>
    <div class="search-bar-right">
        <div class="browse-record" >
            <h3>最近浏览</h3>
            <div class="browse-list" ng-repeat="x in browseRecord">
                <a href="javascript:;" class="two-line" ng-bind="x.Title" ng-click="searchDetail(x.KId)"></a>
                <div class="clearfix">
                    <span ng-repeat="y in x.tagStrings" ng-bind="y" ng-click="tagSearch(y)"></span>
                </div>
            </div>
        </div>
        <div class="browse-record browse-recommend">
            <h3>智能推荐</h3>
            <div class="recommend-list" ng-repeat="re in recommend">
                <div><span></span><a href="javascript:;" ng-click="searchDetail(re.KId)">{{re.Title}}</a></div>
                <p>{{re.showCreateTime}}</p>
            </div>
        </div>-
    </div>
</div>
</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/knlwarehouse/intelligentSearchResult.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
