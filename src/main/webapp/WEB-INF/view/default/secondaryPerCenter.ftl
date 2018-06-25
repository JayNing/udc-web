<#--个人中心二级模板-->
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<#macro modelHead title charset="utf-8" lang="zh-CN">

    <@defaultLayout.htmlHead title="${title}">
    <#--<link rel="stylesheet" href="${basePath}/resources/css/test/test.css">-->
        <#nested>
    </@defaultLayout.htmlHead>


</#macro>


<#macro modelBody>
    <@defaultLayout.htmlBody>

    <div class="main member-center clearfix">
        <div class="member-modular-left" ng-controller="navController">
            <div class="bg edit-user" ng-cloak ng-show="editBool">
                <div class="data-edit">
                    <div class="data-edit-head clearfix">
                        <div>
                            <img src="${basePath}/resources/images/app/rounded.png" />
                            编辑个人资料
                        </div>
                        <span ng-click="close()">x</span>
                    </div>
                    <div class="data-edit-main">
                        <form>
                            <div class="data-edit-list data-edit-img">
                                <span>头像：</span>
                                <div ng-style="headImg" >
                                    <input type="file"  id='upfile'  onchange="angular.element(this).scope().fileChange()" ng-model="head"/>
                                </div>
                            </div>
                            <div class="data-edit-list">
                                <span>姓名：</span>
                                <input id="leftname" type="text" ng-model="leftname" ng-keyup="xz($event,5)"/>
                            </div>
                            <div class="data-edit-list">
                                <span>部门：</span>
                                <select ng-model="selectDeparment" id="selectDeparment" ng-change="selectChange()">
                                    <option value="0">请选择部门</option>
                                    <option ng-repeat="x in deparments" ng-bind="x.departmentName" value="{{x.departmentId}}"></option>
                                </select>
                            </div>
                            <a href="javascript:;" ng-click="edit_submit()">保存</a>
                        </form>
                    </div>
                </div>
            </div>
            <div class="member-info">
                <div class="member-photo" ng-style="headImg" ></div>
                <h3 ng-cloak class="ng-cloak">{{depa}} <span id="navleftname">{{name}}</span></h3>
                <div class="member-tab clearfix">
                    <div>
                        <div ng-cloak class="ng-cloak">{{share}}</div>
                        <a href="">我的分享</a>
                    </div>
                    <div>
                        <div ng-cloak class="ng-cloak">{{question}}</div>
                        <a href="">我的提问</a>
                    </div>
                    <div>
                        <div ng-cloak class="ng-cloak">{{core}}</div>
                        <a href="">积分值</a>
                    </div>
                </div>
                <a href="javascript:;" ng-click="user_edit()">编辑</a>
            </div>
            <div class="member-nav warehouse-list-wrap">
                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix">
                        <span>个人中心</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou none"></i>
                    </div>
                    <div class="none">
                        <a href="javascript:;" ng-click="leftJumps('/persoalCenter/1')">个人主页</a>
                        <a href="javascript:;" ng-click="leftJumps('/persoalCenter/2')" >消息通知-培训通知</a>
                        <a href="javascript:;" ng-click="leftJumps('/persoalCenter/3')" >消息通知-培训发起审核</a>
                        <div class="child-nav">
                            <a href="javascript:;" class="clearfix" >
                                <span>我的考试</span>
                                <i class="iconfont icon-youjiantou"></i>
                                <i class="iconfont icon-xiajiantou  none"></i>
                            </a>
                            <div class="child-list none" >
                                <a href="javascript:;" ng-click="leftJumps('/ownexam')" >考试列表</a>
                            </div>
                        </div>
                       <#-- <div class="child-nav">
                            <a href="javascript:;" class="clearfix" >
                                <span>我的培训</span>
                                <i class="iconfont icon-youjiantou"></i>
                                <i class="iconfont icon-xiajiantou  none"></i>
                            </a>
                            <div class="child-list none" >
                                <a href="javascript:;" ng-click="leftJumps('/examTrain/9')" >培训列表</a>
                            </div>
                        </div>-->
                    </div>
                </div>
                <div class="warehouse-list" >
                    <div class="warehouse-list-title clearfix" ng-click="leftJumps('/bookList')">
                        <span>图书管理</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou  none"></i>
                    </div>
                </div>
                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix">
                        <span>知识仓库</span>
                        <i class="iconfont icon-youjiantou" ></i>
                        <i class="iconfont icon-xiajiantou none"></i>
                    </div>
                    <div class="none">
                        <a href="javascript:;" ng-click="leftJumps('/knlgBank/1')">知识仓库管理</a>
                        <a href="javascript:;" ng-click="leftJumps('/knlgBank/2')">录入知识</a>
                    </div>
                </div>
                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix"  ng-click="leftJumps('/communifyInfo')">
                        <span>社区管理</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou  none"></i>
                    </div>
                </div>
                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix">
                        <span>专家管理</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou  none"></i>
                    </div>
                    <div class="none">
                        <a href="javascript:;"  ng-click="leftJumps('/knlgBank/3')">专家列表</a>
                        <a href="javascript:;"  ng-click="leftJumps('/knlgBank/4')">专家提问管理</a>
                    </div>
                </div>
                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix">
                        <span>分类管理</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou  none"></i>
                    </div>
                    <div class="none">
                        <a href="javascript:;" ng-click="leftJumps('/classifyManager/1')">知识仓库文章分类</a>
                        <a href="javascript:;" ng-click="leftJumps('/classifyManager/5')">知识仓库节点分类</a>
                        <a href="javascript:;" ng-click="leftJumps('/classifyManager/2')">社区文章分类</a>
                        <a href="javascript:;" ng-click="leftJumps('/classifyManager/3')">部门分类</a>
                        <a href="javascript:;" ng-click="leftJumps('/classifyManager/6')">考试培训分类</a>
                    </div>
                </div>

                <div class="warehouse-list">
                    <div class="warehouse-list-title clearfix">
                        <span>账号管理</span>
                        <i class="iconfont icon-youjiantou"></i>
                        <i class="iconfont icon-xiajiantou none"></i>
                    </div>
                    <div class="none">
                        <a href="javascript:;" ng-click="leftJumps('/accountManager/1')">员工账号</a>
                        <a href="javascript:;" ng-click="leftJumps('/accountManager/2')">权限设置</a>
                        <a href="javascript:;" ng-click="leftJumps('/accountManager/3')">模块管理</a>
                    </div>
                </div>
            </div>
        </div>
        <#nested>

    </div>

    </@defaultLayout.htmlBody>
</#macro>


<#macro js_scripts>
    <@defaultLayout.js_scripts>
    </@defaultLayout.js_scripts>
    <#nested>
<script src="${basePath}/resources/js/memberCenter/memberNavLeft.js" type="text/javascript"></script>
</#macro>

