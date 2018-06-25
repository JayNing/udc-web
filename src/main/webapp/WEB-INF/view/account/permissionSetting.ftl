<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="账号管理-权限设置">

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/account/permissionSetting.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right message-notification" ng-controller="permissionController">
        <div class="bg edit-user" ng-cloak class="ng-cloak" ng-show="addBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/add1.png" class="add1"/>
                        添加角色
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="rule-edit-main">
                    <form>
                        <div class="rule-name clearfix">
                            <span>角色名：</span>
                            <input type="text" ng-model="roleName" ng-keyup="xz($event,10)"/>
                        </div>
                        <div class="rule-jurisdiction">
                            <h3>权限选择</h3>
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
                            <a href="javascript:;" ng-click="addInfo()">保存</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="bg edit-user" ng-cloak class="ng-cloak" ng-show="editBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/add1.png" class="add1"/>
                        编辑角色
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="rule-edit-main">
                    <form>
                        <div class="rule-name clearfix">
                            <span>角色名：</span>
                            <input type="text" ng-model="editRoleName" ng-keyup="xz($event,10)"/>
                        </div>
                        <div class="rule-jurisdiction">
                            <h3>权限选择</h3>
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
                            <a href="javascript:;" ng-click="editInfo()">保存</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除角色
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除角色?</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="notification-title">
            <img src="${basePath}/resources/images/app/account.png">
            <span>账号管理-<a href="javascript:;">权限设置</a></span>
        </div>
        <div class="clearfix account-tab">
            <a href="javascript:;"><img src="${basePath}/resources/images/app/add.png" /><span ng-click="addRole()">添加角色</span></a>
        </div>
        <div class="books-table">
            <div class="search-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="角色名" ng-model="search" ng-keyup="enterEvent($event)" ng-keyup="xz($event,10)">
                </div>
                <div class="books-search-time account-search-time">
                    <a href="javascript:;" ng-click="submit()">提交</a>
                </div>
            </div>
            <table>
                <thead>
                <tr>
                    <td width="6%">序号</td>
                    <td width="72%"><div class="use-name">角色名</div></td>
                    <td width="22%">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in roles">
                    <td ng-cloak class="ng-cloak">{{(curr-1)*10+$index+1}}</td>
                    <td><div class="use-name">{{x.roleName}}</div></td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;" ng-click="roleEdit(x.roleId)">编辑</a>
                            <a href="javascript:;"  ng-click="del(x.roleId,$index)">删除</a>
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
<script src="${basePath}/resources/js/account/permissionSetting.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
