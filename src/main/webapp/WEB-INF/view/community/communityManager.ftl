
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="社区管理 ">

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/community/communityManager.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
    <div class="member-modular-right message-notification"  ng-controller="commumitymangController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除贴文:
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除该贴文?</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="bg notice-bg" ng-cloak class="ng-cloak" ng-show="noticeBool">
            <div class="data-edit ">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/comment1.png" />
                        社区公告
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="account-notice">
                    <form>
                        <textarea placeholder="" ng-model="notice"></textarea>
                        <a href="javascript:;" ng-click="editNotice()">确定</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="${basePath}/resources/images/community/home.png">
            <span>社区管理</span>
        </div>
        <div class="community-tab clearfix">
            <a href="javascripe:;" ng-click="selectId(2)" ng-class="{true:'hover',false:''}[typeId==2]">问题</a>
            <a href="javascripe:;" ng-click="selectId(1)" ng-class="{true:'hover',false:''}[typeId==1]">分享</a>
            <a href="javascript:;" ng-click="release()" class="notice"><img src="/resources/images/app/comment.png"><span>社区公告</span></a>
        </div>

        <div class="books-table community-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="标题" ng-model="search" ng-keyup="enterEvent($event)">
                </div>
                <div class="books-search-time">
                    <select ng-model="selectDepartment">
						<option value="0">所属分类</option>
                        <option ng-repeat="x in sites" value="{{x.essayTypeId}}">{{x.essayTypeName}}</option>
                    </select>
                    <#include "/default/timeLayout.ftl">
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width=47%>序号/标题</td>
                    <td width="15%">所属分类</td>
                    <td width=15%>添加时间</td>
                    <td width=23%>操作</td>
                </tr>
                </thead>
                <tbody>
					<tr ng-repeat="x in community" >
						<td style="border-right-width:0;">{{(curr-1)*10+$index+1}}、{{x.DisTitle}}</td>
						<td style="border-right-width:0;border-left-width: 0;" ng-bind="x.EssayTypeName"></td>
						<td style="border-right-width:0;border-left-width: 0;" ng-bind="x.CreateTime"></td>
						<td style="border-left-width: 0;">
							<div class="books-btn">
								<a href="javascript:;" ng-click="setTop(x.DisId,$index)" ng-class={true:"hover",false:""}[x.IsTop==2]><span ng-if="x.IsTop==2">取消置顶</span><span ng-if="x.IsTop==1">置顶</span></a>

                                <a href="javascript:;" ng-click="del(x.DisId,$index)">删除</a>
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
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/community/communityManager.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
