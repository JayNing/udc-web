<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="外部专家 " keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@defaultLayout.htmlHead>
<link href="${basePath}/resources/css/specialist/externalSpecialist.css" rel="stylesheet" type="text/css">
<@defaultLayout.htmlBody>
<div class="main expert" ng-controller="externalController">
    <div class="bg" ng-cloak class="ng-cloak" ng-show="consultationBool">
        <div class="consultation">
            <div class="consultation-top clearfix">
                <div><img src="${basePath}/resources/images/app/consultation.png" />专家咨询</div>
                <span class="close" ng-click="close()">x</span>
            </div>
            <div class="consultation-main">
                <textarea placeholder="请输入你要咨询的问题…" ng-model="consultations" ng-keyup="tolCount()"></textarea>　
                <div class="books-btn"><span>{{num}}/800</span><a href="javascript:;" class="hover" ng-click="consultationSubmit()">提交 </a></div>
            </div>
        </div>
    </div>
    <div class="expert-list clearfix" ng-repeat="x in experts" >
        <img src="${basePath}/resources/images/app/expert-icon.png" />
        <div class="expert-photo" ng-style="{'background-image': 'url('+x.headUrl+')'}"></div>
        <div class="expert-info" >
            <div>
                <span ng-bind="x.speName"></span>
                <div>
                    <img src="${basePath}/resources/images/app/zan1.png" ng-click="addCount(x.speId)" ng-show="x.likeFlag==2"/>
                    <img src="${basePath}/resources/images/app/zan.png" ng-click="addCount(x.speId)" ng-show="x.likeFlag==1" />
                    <em ng-model="count" ng-bind="x.speLikeCount"></em>
                </div>
            </div>
            <h5 ng-bind="x.jobTitle" ></h5>
            <p class="brief" ng-bind="x.speProfile"></p>
            <h5 class="user-honor">个人荣誉</h5>
            <p class="honor" ng-repeat="y in x.honors" ng-cloak class="ng-cloak"><span></span>{{y}}</p>
            <a href="javascript:;" ng-click="consultation(x.speId)" name="consultation" >立即咨询</a>
        </div>
    </div>
    <div class="page books-page">
        <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
    </div>
</div>


</@defaultLayout.htmlBody>

<@defaultLayout.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/specialist/externalSpecialist.js" type="text/javascript"></script>
</@defaultLayout.js_scripts>
