
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="账号管理-员工账号">

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/account/employeeAccount.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
    <div class="member-modular-right message-notification" ng-controller="accountController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除账号
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除账号?</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="bg add-account-bg" ng-cloak class="ng-cloak"  ng-show="addBool">
            <div class="data-edit">
                <div ng-show="bgBool" class="bg1" ng-click="roleHide()"></div>
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/add1.png" class="add1" />
                        添加账号
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="add-account">
                    <form>
                        <div class="clearfix">
                            <span>用户名：</span>
                            <input type="text"  ng-model="addAccount.userName" ng-keyup="check($event,20)"/>
                        </div>
                        <div class="clearfix">
                            <span>密码：</span>
                            <input type="password"  ng-model="addAccount.password" ng-keyup="xz($event,16)"/>
                        </div>
                        <div class="clearfix">
                            <span>姓名：</span>
                            <input type="text" ng-model="addAccount.names" ng-keyup="xz($event,5)"/>
                        </div>
                        <div class="clearfix">
                            <span>角色：</span>
                            <div class="role-main">
                                <div class="role-name" ng-click="roleShow()" ng-bind="roleName"></div>

                                <div ng-show="roleBool" class="roleSelect">
                                    <label ng-repeat="x in roles">
                                        <input type="checkbox" name="" ng-click="updateSelection1($event,x.roleId,x.roleName)" ng-checked="isSelected1(x.roleId)">
                                        <i class="iconfont icon"></i>
                                        <span ng-bind="x.roleName"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix">
                            <span>所属部门：</span>
                            <select ng-model="accountDepartment" >
                                <option value="0">请选择部门</option>
                                <option ng-repeat="x in departments" value="{{x.departmentId}}">{{x.departmentName}}</option>
                            </select>
                        </div>
                        <div class="rule-jurisdiction">
                            <h3>权限选择:</h3>
                            <ul class="rule-list1">
                                <li ng-class="{'checkedi':selected.indexOf(a.moduleId) >= 0}" ng-repeat="a in roleLabel" ng-init="a.more = false">
                                    <span class="btn-more" ng-class="{'morehide':!a.subModelList.length}" ng-click="a.more=!a.more">{{a.more?'-':'+'}}</span>
                                    <label for="add_{{a.moduleId}}">
                                        <i class="iconfont"></i>
                                        <input id="add_{{a.moduleId}}" type="checkbox" ng-checked="selected.indexOf(a.moduleId) >= 0" ng-click="updateSelection($event,a)">
                                        <span>{{a.moduleName}}</span>
                                    </label>
                                    <ul class="rule-list2" ng-show="a.more">
                                        <li ng-repeat="b in a.subModelList" ng-class="{'checkedi':selected.indexOf(b.moduleId) >= 0}" ng-init="b.more = false">
                                            <span class="btn-more" ng-class="{'morehide':!b.subModelList.length}" ng-click="b.more = !b.more">{{b.more?'-':'+'}}</span>
                                            <label for="add_{{b.moduleId}}">
                                                <i class="iconfont"></i>
                                                <input id="add_{{b.moduleId}}" type="checkbox" ng-checked="a.checked || selected.indexOf(b.moduleId) >= 0" ng-click="updateSelection($event,b)">
                                                <span>{{b.moduleName}}</span>
                                            </label>
                                            <ul class="rule-list3" ng-show="b.more">
                                                <li ng-repeat="c in b.subModelList" ng-class="{'checkedi':selected.indexOf(c.moduleId) >= 0}" ng-init="c.more = false">
                                                    <span class="btn-more" ng-class="{'morehide':!c.subModelList.length}" ng-click="c.more=!c.more">{{c.more?'-':'+'}}</span>
                                                    <label for="add_{{c.moduleId}}">
                                                        <i class="iconfont"></i>
                                                        <input id="add_{{c.moduleId}}" type="checkbox" ng-checked="b.checked || selected.indexOf(c.moduleId) >= 0" ng-click="updateSelection($event,c)">
                                                        <span>{{b.moduleName}}</span>
                                                    </label>
                                                    <ul class="rule-list4" ng-show="c.more">
                                                        <li ng-repeat="d in c.subModelList" ng-class="{'checkedi':selected.indexOf(d.moduleId) >= 0}" ng-init="d.more = false">
                                                            <span class="btn-more" ng-class="{'morehide':!d.subModelList.length}" ng-click="d.more=!d.more">{{d.more?'-':'+'}}</span>
                                                            <label for="add_{{d.moduleId}}">
                                                                <i class="iconfont"></i>
                                                                <input id="add_{{d.moduleId}}" type="checkbox" ng-checked="c.checked || selected.indexOf(d.moduleId) >= 0" ng-click="updateSelection($event,d)">
                                                                <span>{{d.moduleName}}</span>
                                                            </label>
                                                        </li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <a href="javascript:;" ng-click="addAccount()">保存</a>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <div class="bg add-account-bg" ng-cloak class="ng-cloak" ng-show="editAccountBool">
            <div class="data-edit ">
                <div ng-show="bgBool" class="bg1" ng-click="roleHide()"></div>
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/add1.png" class="add1" />
                        编辑账号
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="add-account">
                    <form >
                        <div class="clearfix">
                            <span>用户名：</span>
                            <input type="text"  disabled ng-model="userName" />
                        </div>
                        <div class="clearfix">
                            <span>别名：</span>
                            <input type="text" ng-model="name" />
                        </div>
                        <div class="clearfix">
                            <span>密码：</span>
                            <input type="password" ng-model="password" />
                        </div>
                        <div class="clearfix">
                            <span>角色：</span>
                            <div class="role-main">
                                <div class="role-name" ng-click="roleShow()" ng-bind="roleName"></div>
                                <div ng-show="roleBool" class="roleSelect">
                                    <label ng-repeat="x in roles" >
                                        <input type="checkbox"  name="" ng-click="updateSelection1($event,x.roleId,x.roleName)" ng-checked="isSelected1(x.roleId)">
                                        <i class="iconfont icon"></i>
                                        <span ng-bind="x.roleName"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix">
                            <span>所属部门：</span>
                            <select ng-model="accountDepartment1">
                                <option value="0">请选择部门</option>
                                <!--<option value="5">tao</option>-->
                                <option ng-repeat="x in departments1" value="{{x.departmentId}}">{{x.departmentName}}</option>
                            </select>
                        </div>
                        <div class="rule-jurisdiction">
                            <h3>权限选择:</h3>
                            <ul class="rule-list1">
                                <li ng-class="{'checkedi':selected.indexOf(a.moduleId) >= 0}" ng-repeat="a in roleLabel" ng-init="a.more = false">
                                    <span class="btn-more" ng-class="{'morehide':!a.subModelList.length}" ng-click="a.more=!a.more">{{a.more?'-':'+'}}</span>
                                    <label for="edit_{{a.moduleId}}">
                                        <i class="iconfont"></i>
                                        <input id="edit_{{a.moduleId}}" type="checkbox" ng-checked="selected.indexOf(a.moduleId) >= 0" ng-click="updateSelection($event,a)">
                                        <span>{{a.moduleName}}</span>
                                    </label>
                                    <ul class="rule-list2" ng-show="a.more">
                                        <li ng-repeat="b in a.subModelList" ng-class="{'checkedi':selected.indexOf(b.moduleId) >= 0}" ng-init="b.more = false">
                                            <span class="btn-more" ng-class="{'morehide':!b.subModelList.length}" ng-click="b.more = !b.more">{{b.more?'-':'+'}}</span>
                                            <label for="edit_{{b.moduleId}}">
                                                <i class="iconfont"></i>
                                                <input id="edit_{{b.moduleId}}" type="checkbox" ng-checked="a.checked || selected.indexOf(b.moduleId) >= 0" ng-click="updateSelection($event,b)">
                                                <span>{{b.moduleName}}</span>
                                            </label>
                                            <ul class="rule-list3" ng-show="b.more">
                                                <li ng-repeat="c in b.subModelList" ng-class="{'checkedi':selected.indexOf(c.moduleId) >= 0}" ng-init="c.more = false">
                                                    <span class="btn-more" ng-class="{'morehide':!c.subModelList.length}" ng-click="c.more=!c.more">{{c.more?'-':'+'}}</span>
                                                    <label for="edit_{{c.moduleId}}">
                                                        <i class="iconfont"></i>
                                                        <input id="edit_{{c.moduleId}}" type="checkbox" ng-checked="b.checked || selected.indexOf(c.moduleId) >= 0" ng-click="updateSelection($event,c)">
                                                        <span>{{b.moduleName}}</span>
                                                    </label>
                                                    <ul class="rule-list4" ng-show="c.more">
                                                        <li ng-repeat="d in c.subModelList" ng-class="{'checkedi':selected.indexOf(d.moduleId) >= 0}" ng-init="d.more = false">
                                                            <span class="btn-more" ng-class="{'morehide':!d.subModelList.length}" ng-click="d.more=!d.more">{{d.more?'-':'+'}}</span>
                                                            <label for="edit_{{d.moduleId}}">
                                                                <i class="iconfont"></i>
                                                                <input id="edit_{{d.moduleId}}" type="checkbox" ng-checked="c.checked || selected.indexOf(d.moduleId) >= 0" ng-click="updateSelection($event,d)">
                                                                <span>{{d.moduleName}}</span>
                                                            </label>
                                                        </li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                            <a href="javascript:;" ng-click="accountSubmit()">编辑</a>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        <div class="notification-title">
            <img src="${basePath}/resources/images/app/account.png">
            <span>账号管理-<a href="javascript:;">员工账号</a></span>
        </div>
        <div class="clearfix account-tab">
            <#--<a href="javascript:;" ng-click="edit()"><img src="${basePath}/resources/images/app/comment.png" /><span>发公告</span></a>-->
            <a href="javascript:;" ng-click="add()"><img src="${basePath}/resources/images/app/add.png" /><span>添加账号</span></a>
                <a href="javascript:;" ng-click="release()" class="notice"><img src="/resources/images/app/comment.png"><span>发公告</span></a>
        </div>
        <div class="bg notice-bg" ng-cloak class="ng-cloak" ng-show="noticeBool">
            <div class="data-edit ">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/comment1.png" />
                        发公告
                    </div>
                    <span ng-click="noticeBool = !noticeBool">x</span>
                </div>
                <div class="account-notice">
                    <form>
                        <div class="account-notice-title">
                            <label for="notice-title">标题:</label>
                            <input type="text" ng-model="noticeTitle" placeholder="请输入平台公告标题" ng-keyup="xz($event,30)">
                        </div>
                        <div class="account-notice-content">
                            <label for="notice-content" >内容:</label>
                            <textarea placeholder="请输入平台公告的内容" ng-model="noticeContent" ng-keyup="xz($event,500)"></textarea>
                        </div>
                        <a href="javascript:;" ng-click="addNotice()">确定发送</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="books-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="姓名" ng-model="search" ng-keyup="enterEvent($event)">
                </div>
                <div class="books-search-time account-search-time">
                    <select ng-model="selectDepartment">
                        <option value="0">请选择部门</option>
                        <option ng-repeat="x in sites" value="{{x.departmentId}}">{{x.departmentName}}</option>
                    </select>
                    <a href="javascript:;" ng-click="submit()">提交</a>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width="6%">序号</td>
                    <td width="27%">用户名</td>
                    <td width="23%">姓名</td>
                    <td width="22%">所属部门</td>
                    <td width="22%">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in accounts" >
                    <td ng-bind="((curr-1)*10+$index+1)"></td>
                    <td ng-bind="x.LoginName"></td>
                    <td ng-bind="x.RealName"></td>
                    <td ng-bind="x.DepartmentName"></td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;" ng-click="accountsEdit(x.UserId)" >编辑</a>
                            <a href="javascript:;" ng-click="del(x.UserId,$index)" >删除</a>
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
<script src="${basePath}/resources/js/account/employeeAccount.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
