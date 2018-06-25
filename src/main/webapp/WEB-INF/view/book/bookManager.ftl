<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="图书管理 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/book/bookManager.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
        <div class="member-modular-right message-notification" ng-controller="booksController">
            <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
                <div class="data-edit del-edit">
                    <div class="data-edit-head clearfix">
                        <div>
                            删除图书
                        </div>
                        <span ng-click="close()">x</span>
                    </div>
                    <div class="del-main">
                        <p>是否删除该图书?</p>
                        <a href="javascript:;" ng-click="close()">取消</a>
                        <a href="javascript:;" ng-click="confirm()">确认</a>
                    </div>
                </div>
            </div>
            <div class="bg new-record" ng-cloak ng-show="recordBool">
                <div class="data-edit " >
                    <div class="data-edit-head clearfix">
                        <div>
                            <img src="${basePath}/resources/images/book/book-add.png">
                            借阅记录
                        </div>
                        <span ng-click="close()">x</span>
                    </div>
                    <div class="loan-record">
                        <table>
                            <thead>
                            <tr>
                                <td>序号</td>
                                <td>借阅人</td>
                                <td>借阅日期 </td>
                                <td> 所属部门</td>
                                <td> 归还日期</td>
                                <td> 操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="x in record">
                                <td ng-bind="((curr1-1)*10+$index+1)"></td>
                                <td ng-bind="x.UserName"></td>
                                <td ng-bind="x.BorrowTime"></td>
                                <td ng-bind="x.DepartmentName"></td>
                                <td class="books" ng-bind="x.GivebackTime"></td>
                                <td>
                                    <div class="books-btn books-btn-no" ng-class="{true: 'hover', false: ''}[x.flag==1]">
                                        <a href="javascript:;"    ng-show="((curr1-1)*10+$index)==0&&x.Flag==1">已归还</a>
                                        <a href="javascript:;" class="hover"  ng-click="returnShow(x.UserId)" ng-show="((curr1-1)*10+$index)==0&&x.Flag==2">归还</a>
                                        <a href="javascript:;" ng-show="((curr1-1)*10+$index)!=0">已归还</a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="page books-page">
                            <a href="javascript:;" ng-click="pageClick1(p)" ng-repeat="p in page1" class="{{curr1==p?'hover':''}}" ng-bind="p"></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bg new-books" ng-cloak ng-show="booksBool">
                <div class="data-edit">
                    <div class="data-edit-head clearfix">
                        <div>
                            <img src="${basePath}/resources/images/book/book-add.png">
                            添加新图书入库
                        </div>
                        <span ng-click="close()">x</span>
                    </div>
                    <div class="books-edit-main">
                        <form>
                            <div class="books-edit-list">
                                <span>编号：</span>
                                <input type="text" ng-model="number" placeholder="请输入编号" ng-keyup="setNumber()"/>
                            </div>
                            <div class="books-edit-list books-edit-name">
                                <span>图书名称：</span>
                                <input type="text" ng-model="bookName" placeholder="请输入图书名称" ng-keyup="xz($event,50)"/>
                            </div>
                            <div class="books-edit-list">
                                <span>作者：</span>
                                <input type="text" ng-model="author" placeholder="请输入作者名" ng-keyup="xz($event,20)"/>
                            </div>
                            <a href="javascript:;" ng-click="book_submit()">确定</a>
                        </form>
                    </div>
                </div>
            </div>
            <div class="bg new-books" ng-cloak ng-show="returnBool">
                <div class="data-edit del-edit">
                    <div class="data-edit-head clearfix">
                        <div>
                            <img src="/resources/images/book/book-add.png">
                            归还图书
                        </div>
                        <span ng-click="close1()">x</span>
                    </div>
                    <div class="del-main">
                        <p>归还图书</p>
                        <a href="javascript:;" ng-click="cancel()">取消</a>
                        <a href="javascript:;" ng-click="returnBook()">确认归还</a>
                    </div>
                </div>
            </div>
            <div class="books-title">
                <img src="${basePath}/resources/images/book/books.png">
                <span>图书管理</span>
            </div>
            <div class="books-table" >
                <div class="search-model clearfix">
                    <div class="books-search clearfix">
                        <i class="iconfont icon-sousuo"></i>
                        <input type="text" ng-keyup="enterEvent($event)" placeholder="书名、编码、作者" ng-model="search">
                    </div>
                    <div class="books-search-time">
                        <#include "/default/timeLayout.ftl">
                    </div>
                </div>
                <table>
                    <thead>
                    <tr>
                        <td width=15%>图书编码</td>
                        <td width="25%">书名</td>
                        <td width=15%>作者</td>
                        <td width=15%>添加时间</td>
                        <td width=10%>状态</td>
                        <td width=20%>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="x in books">
                        <#--<td>{{(x.pageIndex-1)*10+$index+1}}</td>-->
                        <td ng-bind="x.bookNo"></td>
                        <td><div class="books-name" ng-bind="x.bookName"></div></td>
                        <td><div class="books-author" ng-bind="x.author"></div></td>
                        <td ng-bind="x.createTime"></td>
                        <td ng-bind="(x.flag-1)?'已借':'可借'" ng-class="{true : 'active',false : '' }[x.flag-1==0]" ></td>
                        <td>
                            <div class="books-btn">
                                <a href="javascript:;" ng-click="booksRecord(x.pkId)" class="jieyue-btn">借阅记录</a>

                                <a href="javascript:;"  ng-click="del(x.pkId,$index)" ng-if="x.flag==1" class="hover">删除</a>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="page books-page">
                    <a href="javascript:;" ng-click="pageClick(p)" ng-repeat="p in page" class="{{curr==p?'hover':''}}" ng-bind="p"></a>
                </div>
            </div>
            <a href="javascript:;" class="add-books" ng-click="add_book()"><span>+</span>添加新图书入库</a>
        </div>


</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/book/bookManager.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>

</@secondaryPerCenter.js_scripts>
