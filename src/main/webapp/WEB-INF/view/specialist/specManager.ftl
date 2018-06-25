<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="专家管理 " >
<link href="${basePath}/resources/css/specialist/specManager.css" rel="stylesheet" type="text/css">
</@secondaryPerCenter.modelHead>
<#--<@module_01.modelHead title="FreeMarker测试 ">
<link rel="stylesheet" href="${basePath}/resources/css/specialist/specManager.css">
</@module_01.modelHead>-->
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right message-notification" ng-controller="expertController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除专家
                    </div>
                    <span ng-click="cancel()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除专家</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="${basePath}/resources/images/app/expert.png">
            <span>专家管理</span>
        </div>
        <div class="books-table community-table">
            <div class="add-expert">
                <img src="${basePath}/resources/images/app/add.png"/>
                <a href="javascript:;" class="hover" ng-click="expertAdd()">添加专家</a>
            </div>
            <table>
                <thead>
                <tr>
                    <td width='38%'>序号/头像/专家姓名</td>
                    <td width="41%">职称</td>
                    <td width='21%'>操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in specialists">
                    <td class="expert-message" ng-cloak class="ng-cloak"><span>{{((curr-1)*10)+$index+1}}、</span><div ng-style="{'background-image': 'url('+x.headUrl+')'}" ></div><span ng-bind="x.speName"></span></td>
                    <td ng-bind="x.jobTitle"></td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;" class="hover" ng-click="expertEdit(x.speId)">编辑</a>
                            <a href="javascript:;" class="hover" ng-click="del(x.speId,$index)">删除</a>
                        </div>
                    </td>
                </tr>

                </tbody>
            </table>
            <div class="page books-page">
                <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
            </div>
        </div>

    </div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/specialist/specManager.js" type="text/javascript"></script>

</@secondaryPerCenter.js_scripts>
