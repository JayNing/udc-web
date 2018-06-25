<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="专家管理-添加专家 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/specialist/specManagerAdd.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>
<div class="member-modular-right warehouse-form" ng-controller="expertController">
    <div class="notification-title">
        <img src="${basePath}/resources/images/app/expert.png">
        <span>专家管理</span>
        <input type="hidden" value="${RequestParameters.speeditid}" id="speeditid"/>
    </div>
    <form>
        <div class="warehouse-info">
            <form enctype="multipart/form-data" >
                <div class="warehouse-text expert-img clearfix">
                    <span>头像：</span>
                    <div ng-style="headImg" class="headerImg" >
                        <input type="file"  id="file"  onchange="angular.element(this).scope().fileChange()" ng-model="headImg"/>
                    </div>
                </div>
                <div class="warehouse-text warehouse-author clearfix">
                    <span>姓名：</span>
                    <input type="text" placeholder="请输入姓名" ng-model="specialist.name" ng-keyup="xz($event,5)"/>
                </div>
                <div class="warehouse-text warehouse-author clearfix">
                    <span>职称：</span>
                    <input type="text" placeholder="请输入职称" ng-model="specialist.academic" ng-keyup="xz($event,30)"/>
                </div>
                <div class="warehouse-text clearfix">
                    <span>简介：</span>
                    <textarea placeholder="请输入简介" ng-model="specialist.summary" ng-keyup="xz($event,500)"></textarea>
                </div>
                <div class="warehouse-text clearfix">
                    <span>个人荣誉：</span>
                    <textarea placeholder="请输入个人荣誉" ng-model="specialist.honor" ng-keyup="xz($event,300)"></textarea>
                </div>

                <input type="submit" class="expert-submit" ng-click="expertEdit()"  ng-show="editBool" value="编辑"/>
                <input type="submit" class="expert-submit" ng-click="expertAdd()" ng-show="addBool" value="添加"/>
            </form>
        </div>
    </form>

</div>



</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/paging/paging.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/specialist/specManagerAdd.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>

</@secondaryPerCenter.js_scripts>
