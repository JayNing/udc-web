<#--上部考试培训二级模板-->
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<#macro modelHead title charset="utf-8" lang="zh-CN">

    <@defaultLayout.htmlHead title="${title}">
    <#--<link rel="stylesheet" href="${basePath}/resources/css/test/test.css">-->
        <#nested>
    </@defaultLayout.htmlHead>


</#macro>


<#macro modelBody>
    <@defaultLayout.htmlBody>

    <div class="main member-center clearfix" >
        <div class="member-modular-left manage-left"  ng-controller="navController">
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix" ng-click="leftJumps('/examTrain/1')">
                    <span>发起考试</span>
                    <i class="iconfont icon-youjiantou" ></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix" ng-click="leftJumps('/examTrain/2')">
                    <span>题库管理</span>
                    <i class="iconfont icon-youjiantou" ></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix" ng-click="leftJumps('/examTrain/3')">
                    <span>考卷管理</span>
                    <i class="iconfont icon-youjiantou" ></i>
                </div>
            </div>
            <div class="warehouse-list">
                <div class="warehouse-list-title clearfix" ng-click="leftJumps('/examTrain/5')">
                    <span>考试管理</span>
                    <i class="iconfont icon-youjiantou" ></i>
                </div>
            </div>
        <#--    <div class="warehouse-list">
                <div class="warehouse-list-title clearfix" ng-click="leftJumps('/examTrain/9')">
                    <span>培训管理</span>
                    <i class="iconfont icon-youjiantou" ></i>
                </div>
            </div>-->


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

