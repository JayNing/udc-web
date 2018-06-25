
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="知识仓库管理 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/knlwarehouse/knseClfManager.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right message-notification" ng-controller="recordController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除文章
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除该文章</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="${basePath}/resources/images/book/zsckgl.png">
            <span>知识仓库管理</span>
        </div>
        <div class="clearfix warehouse-select">
            <select ng-model="firstLevelCategory" ng-change="getSecondLevel()">
                <#--<option ng-show="firstLevelCategory.length == 0" value="">一级类目</option>-->
                <option value="">一级类目</option>
                <option ng-repeat="x in firstLevelCategorys" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
            </select>
            <select ng-model="secondLevelCategory" ng-change="submit(1)">
                <#--<option ng-show="secondLevelCategorys.length == 0" value="">二级类目</option>-->
                <option value="">二级类目</option>
                <option ng-repeat="x in secondLevelCategorys" value="{{x.repCatId}}">{{x.repCatName}}</option>
            </select>
            <select ng-model="kldNodeCategory" ng-change="submit(1)">
                <option value="">知识库节点分类</option>
                <option ng-repeat="x in kldNodeCategorys" value="{{x.flowId}}">{{x.flowName}}</option>
            </select>
            <div> <a href="javascript:;" ng-click="inputKnlg()"><span>+</span>录入知识</a></div>
        </div>
        <div class="books-table warehouse-table" >
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo" ></i>
                    <input type="text" placeholder="标题" ng-model="search" ng-keyup="enterEvent($event)">
                </div>
                <div class="books-search-time">
                    <#include "/default/timeLayout.ftl">
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width=60%>序号/标题</td>
                    <td width="20%">添加时间</td>
                    <td width=20%>操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in records">
                    <td style="border-right-width:0;">
                        <a  href="" class="books-name" ng-cloak class="ng-cloak">{{(curr-1)*10+$index+1}}、{{x.Title}}<div ng-if="x.BbsFlag==2" class="hover sqcd">社区沉淀</div>   <#--<img src="${basePath}/resources/images/knlwarehouse/news-word.png" />-->

                        </a>
                    </td>
                    <td style="border-right-width:0;border-left-width: 0;" ng-bind="x.time"></td>
                    <td style="border-left-width: 0;">
                        <div class="books-btn">
                            <a href="javascript:;" ng-click="edit(x.KId)">编辑</a>
                            <a href="javascript:;" ng-click="del(x.KId)">删除</a>
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
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/memberCenter/memberNavLeft.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/knlwarehouse/knseClfManager.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
