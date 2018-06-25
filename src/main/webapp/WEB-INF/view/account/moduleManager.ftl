
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="模块管理" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/account/moduleManager.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

<div class="member-modular-right message-notification"  ng-controller="moduleController">
    <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="addBool">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <img src="${basePath}/resources/images/app/rectangle.png" />
                    添加模块
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="add-account">
                <form class="ng-pristine ng-valid">
                    <div class="clearfix" ng-show="parentName!=0">
                        <span>父节点：</span>
                        <div ng-bind="parentName"></div>
                    </div>
                    <div class="clearfix">
                        <span>模块名称：</span>
                        <input type="text" placeholder="请输入模块名称" ng-model="moduleName" ng-keyup="xz($event,10)">
                    </div>
                    <div class="clearfix">
                        <span>编码：</span>
                        <input type="text" placeholder="请输入模块编码" ng-model="code"  ng-keyup="check($event,10)">
                    </div>
                    <a href="javascript:;" ng-click="addSubmit()">保存</a>
                 </form>
            </div>
        </div>
    </div>
    <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="editBool">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <img src="${basePath}/resources/images/app/rectangle.png" />
                    编辑模块
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="add-account">
                <form class="ng-pristine ng-valid">
                    <div class="clearfix">
                        <span>模块名称：</span>
                        <input type="text" placeholder="请输入模块名称" ng-model="editModuleName" ng-keyup="xz($event,10)">
                    </div>
                    <div class="clearfix">
                        <span>编码：</span>
                        <input type="text" placeholder="请输入模块编码" ng-model="editCode" ng-keyup="check($event,10)">
                    </div>
                    <a href="javascript:;" ng-click="editSubmit()">保存</a>
                </form>
            </div>
        </div>
    </div>
    <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
        <div class="data-edit del-edit">
            <div class="data-edit-head clearfix">
                <div>
                    删除分类
                </div>
                <span ng-click="close()">x</span>
            </div>
            <div class="del-main">
                <p>是否删除分类</p>
                <a href="javascript:;" ng-click="cancel()">取消</a>
                <a href="javascript:;" ng-click="confirm()">确认</a>
            </div>
        </div>
    </div>
    <div class="notification-title">
        <img src="${basePath}/resources/images/app/account.png">
        <span>账号管理-<a href="javascript:;">模块管理</a></span>
    </div>
    <div class="clearfix account-tab">
        <a href="javascript:;"><img src="${basePath}/resources/images/app/add.png" /><span ng-click="addModule()">新增模块</span></a>
    </div>
    <div class="books-table community-table">
        <table class="classification-table">
            <thead>
            <tr>
                <td width='62%'>模块名称</td>
                <td width="19%">编码</td>
                <td width='19%'>操作</td>
            </tr>
            </thead>
            <tbody>
                <tr ng-repeat="x in modules">
                    <td ng-cloak class="ng-cloak"  ng-click="append(x.ModuleId,x.ModuleName,$event)"><span class="dropDown"><i ng-click="dropDown(x.ModuleId,$event)"  ng-show="x.IsPlus==1">+</i></span> {{x.ModuleName}}</td>
                    <td ng-cloak class="ng-cloak"  ng-click="append(x.ModuleId,x.ModuleName,$event)">{{x.ModuleCode}}</td>
                    <td>
                        <div class="books-btn">
                            <a href="javascript:;"  ng-click="edit(x.ModuleId,x.ParentId)">编辑</a>
                            <a href="javascript:;"  ng-click="del(x.ModuleId,$index)">删除</a>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/account/moduleManager.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
