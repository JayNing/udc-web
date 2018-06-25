
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="社区-提问 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/community/communityQuestion.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main search-result clearfix" ng-controller="communityQuestionController">
    <div class="search-show-left question-left">
        <img src="${basePath}/resources/images/community/browse.png" class="browse" ng-click="goBack()"/>
        <h3><img src="${basePath}/resources/images/community/question-icon.png" />我要提问</h3>
        <form>
            <input type="text" placeholder="请输入你的问题..." ng-model="share.question">
            <textarea placeholder="请输入你的问题描述..." ng-model="share.arrticle"></textarea>
            <div class="key-words clearfix">
                <span>关键字：</span>
                <input type="text" placeholder="请输入问题的关键词，多个关键词请用逗号隔开" ng-model="share.key">
            </div>
            <div class="key-words clearfix">
                <span>问题类型选择:</span>
                <select ng-model="share.type">
                    <option value="0">选择问题类型</option>
                    <option ng-repeat="x in cases" value="{{x.essayTypeId}}">{{x.essayTypeName}}</option>
                </select>
            </div>
            <div class="key-words clearfix">
                <span>知识库分类:</span>
				<select ng-model="share.knlwarehouseType" ng-change="selectChange()">
					<option value="0">选择知识库分类</option>
                    <option ng-repeat="x in classifications1" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                </select>
                <select class="key-left-space" ng-model="share.gmaType" >
                    <option value="0">选择二级分类</option>
                    <option ng-repeat="x in classifications2" value="{{x.repCatId}}">{{x.repCatName}}</option>
                </select>
            </div>
            <div class="key-words clearfix">
                <span>业务流程:</span>
                <select ng-model="share.businessType">
                    <option value="0">选择业务流程</option>
                    <option ng-repeat="x in business" value="{{x.flowId}}">{{x.flowName}}</option>
                </select>
            </div>
			<a href="javascript:;" class="submit case-share" ng-click="submit()">提问</a>
        </form>
    </div>
    <#include "/default/rightLayout.ftl">
</div>

</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/community/communityQuestion.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/default/rightLayout.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
