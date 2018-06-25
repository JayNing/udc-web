
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="知识仓库管理-录入知识 ">

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/knlwarehouse/kClMagInputKnl.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right warehouse-form" ng-controller="knowledgeController">

           <input type="hidden" value="${RequestParameters.id}" id="knlgitid"/>
        <div class="notification-title">
            <img src="${basePath}/resources/images/book/book.png">
            <span>知识仓库管理</span>
        </div>
        <form>
            <div class="warehouse-info">
                <div class="warehouse-text clearfix">
                    <span>标题：</span>
                    <input type="text" ng-model="warehouse.title" ng-keyup="xz($event,60)"/>
                </div>
                <div class="warehouse-text warehouse-author clearfix">
                    <span>作者：</span>
                    <input type="text" ng-model="warehouse.author"  ng-keyup="xz($event,20)"/>
                    <i>（选填）</i>
                </div>
                <div class="warehouse-text clearfix por">
                    <span>简介：</span>
                    <textarea placeholder="" ng-model="warehouse.summary" ng-keyup="xz($event,100)"></textarea>
                    <div class="countText"><span ng-bind="warehouse.summary.length"></span> / 100</div>
                </div>
                <div class="warehouse-classification clearfix">
                    <span>知识仓库分类:</span>
                    <select ng-model="warehouse.articleType" ng-change="selectChange()">
                        <option  value="0">请选择分类</option>
                        <option ng-repeat="x in articleTypes" value="{{x.RepositoryCategory.repCatId}}">{{x.RepositoryCategory.repCatName}}</option>
                    </select>
                    <select ng-model="warehouse.processType" class="key-left-space">
                        <option value="0">请选择分类</option>
                        <option ng-repeat="x in processTypes" value="{{x.repCatId}}">{{x.repCatName}}</option>
                    </select>
                </div>
                <div class="warehouse-classification clearfix">
                    <span>节点分类：</span>
                    <select ng-model="warehouse.nodeType" >
                        <option value="0">请选择分类</option>
                        <option ng-repeat="x in nodeTypes" value="{{x.flowId}}">{{x.flowName}}</option>
                    </select>
                </div>

                <div class="warehouse-text warehouse-add clearfix">
                    <span>增加文章：</span>
                    <div class="warehouse-radio clearfix">
                        <label>
                            <input type="radio" name="selected"  value="1" ng-model="warehouse.articleBool" >
                            <i class="iconfont"></i>
                            <span>是</span>
                        </label>
                        <label>
                            <input type="radio"   value="2" name="selected" ng-model="warehouse.articleBool" >
                            <i class="iconfont"></i>
                            <span>否</span>
                        </label>
                    </div>
                </div>
                <div class="warehouse-text clearfix" ng-show="warehouse.articleBool==1">
                    <span>文章内容：</span>
                    <div class="key-textarea" id="edit">

                    </div>
                    <#--<textarea placeholder="" ng-model="warehouse.article"></textarea>-->
                </div>
                <div class="warehouse-text warehouse-key clearfix">
                    <span>关键字：</span>
                    <input type="text" placeholder="请输入知识的关键词，多个关键词请用逗号隔开" ng-model="warehouse.key"/>
                </div>
                <#--<div class="warehouse-text warehouse-key clearfix" ng-repeat="file in filesArr">-->
                    <#--<span>附件上传：</span>-->
                    <#--<input type="text" ng-model="fileName" placeholder="可多文件上传"/>-->
                    <#--<div>-->
                        <#--<input type="file" id='file' onchange="angular.element(this).scope().fileChange()" ng-model="head"/>-->
                        <#--本地上传-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="warehouse-text warehouse-key clearfix" >-->
                    <#--<span>附件上传：</span>-->
                    <#--<input type="text" ng-model="filesArr[0]" placeholder="可多文件上传"/>-->
                    <#--<div>-->
                        <#--<input type="file" id='file' onchange="angular.element(this).scope().fileChange(0)" ng-model="head"/>-->
                        <#--本地上传-->
                    <#--</div>-->
                    <#--<div >-->
                        <#--<input type="button" ng-click="fileAdd()" ng-model="head"/>-->
                        <#--添加-->
                    <#--</div>-->
                <#--</div>-->
                <div class="warehouse-text warehouse-key clearfix" ng-repeat="file in filesArr track by $index" ng-if="filesArr.length > 0" >
                    <span>附件上传：{{$index+1}}</span>
                    <input type="text" ng-model="filesNameArr[$index]" placeholder="可多文件上传"/>
                    <div>
                        <input type="file" id='file' onchange="angular.element(this).scope().fileChange(this)" data-index="{{$index}}" ng-model="filesNameArr[$index]"/>
                        本地上传
                    </div>
                    <div ng-if="$index !== filesArr.length-1">
                        <input type="button" ng-click="fileRemove($index)" ng-model="head1"/>
                        删除
                    </div>
                    <div ng-if="$index == filesArr.length-1">
                        <input type="button" ng-click="fileAdd()" ng-model="head2"/>
                        添加
                    </div>
                </div>

                <a href="javascript:;" class="warehouse-submit" ng-click="warehouseSubmit()" ng-if="addBool">添加</a>
                <a href="javascript:;" class="warehouse-submit" ng-click="warehouseSubmit()" ng-if="editBool">编辑</a>
            </div>
        </form>

    </div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="//unpkg.com/wangeditor/release/wangEditor.min.js"></script>
<script src="${basePath}/resources/js/knlwarehouse/kClMagInputKnl.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/memberCenter/memberNavLeft.js" type="text/javascript"></script>

</@secondaryPerCenter.js_scripts>
