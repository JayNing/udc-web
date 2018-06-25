
<#import "/default/resourceManage.ftl" as resourceManage/>

<@resourceManage.modelHead title="图书借阅 " >

</@resourceManage.modelHead>
<link href="${basePath}/resources/css/book/bookBorrow.css" rel="stylesheet" type="text/css">
<@resourceManage.modelBody>
<div class="main member-center clearfix">
    <div class="member-modular-right boardroom-right message-notification" ng-controller="booksController">
        <div class="bg edit-user" ng-cloak class="ng-cloak" ng-show="borrowBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/book/borrow.png" />
                        借阅
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="borrow-edit-main">
                    <form>
                        <div class="borrow-edit-list">
                            <span>借阅时长：</span>
                            <input type="text" placeholder="请输入天数" ng-model="borrowTime" ng-keyup="setTime()"/>
                            <span class="borrow-class">天</span>
                        </div>
                        <div class="borrow-edit-list">
                            <span>归还时间：</span>
                            <span class="borrow-class" ng-bind="endTime"></span>
                        </div>
                        <div class="borrow-edit-list">
                            <span>借阅人：</span>
                            <span ng-cloak class="ng-cloak"><em class="borrow-class">{{userDepartment}}</em>—{{name}}</span>
                        </div>
                        <a href="javascript:;" ng-click="borrowSubmit()">提交 </a>
                    </form>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="${basePath}/resources/images/book/books.png">
            <span>图书库</span>
        </div>
        <div class="books-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" ng-model="search" ng-keyup="enterEvent($event)"  placeholder="书名、编码、作者">
                </div>
                <div class="books-search-time">
                    <#include "/default/timeLayout.ftl">
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width=14.5%>图书编码</td>
                    <td width=42%>书名</td>
                    <td width=14.5%>作者</td>
                    <td width=14.5%>添加时间</td>
                    <td width=14.5%>操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in books">
                    <td ng-cloak class="ng-cloak">{{x.bookNo}}</td>
                    <td><div class="books-name" ng-cloak class="ng-cloak">{{x.bookName}}</div></td>
                    <td><div class="books-author" ng-cloak class="ng-cloak">{{x.author}}</div></td>
                    <td ng-cloak class="ng-cloak">{{x.createTime}}</td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;" ng-class="{true: 'hover', false: ''}[x.flag==1]"  ng-click="borrows(x.pkId,x.flag)" >
                                <span ng-if="x.flag!=1">已借阅</span>
                                <span ng-if="x.flag==1">借阅</span>
                            </a>

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
</div>

</@resourceManage.modelBody>

<@resourceManage.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/book/bookBorrow.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>

</@resourceManage.js_scripts>
