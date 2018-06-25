
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="分类管理-知识仓库文章类型 " >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/classify/knlwarehouseClassify.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right message-notification"  ng-controller="commArticleController">
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="addBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/rectangle_fff.png" />
                        添加分类:
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="rectangle-main">
                    <form>
                        <div class="rectangle-list">
                            <span>分类名称：</span>
                            <input type="text" ng-model="catname" ng-keyup="xz($event,15)">
                        </div>
                        <a href="javascript:;" ng-click="addSubmit()">提交</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="fatherBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/rectangle_fff.png" />
                        添加二级分类:
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="rectangle-main">
                    <form>
                        <div class="rectangle-list">
                            <span>父类名称：</span>
                            <span ng-bind="ParentName"></span>
                        </div>
                        <div class="rectangle-list">
                            <span>分类名称：</span>
                            <input type="text" ng-model="typesName" ng-keyup="xz($event,15)">
                        </div>
                        <a href="javascript:;" ng-click="addFatherSubmit()">提交</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="editBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/app/rectangle_fff.png" />
                        编辑分类:
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="rectangle-main">
                    <form>
                        <div class="rectangle-list">
                            <span>分类名称：</span>
                            <input type="text" ng-model="typeName" ng-keyup="xz($event,15)">
                        </div>
                        <a href="javascript:;" ng-click="editSubmit()">提交</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="bg rectangle-user" ng-cloak class="ng-cloak" ng-show="delBool">
            <div class="data-edit del-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        删除分类:
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="del-main">
                    <p>是否删除该分类?</p>
                    <a href="javascript:;" ng-click="cancel()">取消</a>
                    <a href="javascript:;" ng-click="confirm()">确认</a>
                </div>
            </div>
        </div>
        <div class="books-title">
            <img src="${basePath}/resources/images/app/rectangle.png">
            <span>分类管理</span>
        </div>
        <div class="books-table community-table">
            <div class="clearfix classification" ng-controller="commArticleNavController">
                <a href="" class="hover">知识仓库文章类型 </a>
                <a href="" ng-click="knlgNode()"> 知识库节点分类</a>
                <a href="" ng-click="communityArit()">社区文章类型</a>
                <a href="" ng-click="departClassify()">部门分类 </a>
                <a href="" ng-click="examClassify()">考试分类</a>
            </div>
            <a href="javascript:;" class="add-classification" ng-click="add()"><span></span>添加一级分类</a>

            <table class="classification-table">
                <thead>
                <tr>
                    <td width='62%'>类目名称</td>
                    <td width="19%">子类</td>
                    <td width='19%'>操作</td>
                </tr>
                </thead>
                <tbody>
                        <tr ng-repeat="x in classification">
                            <td ng-cloak class="ng-cloak"><span class="dropDown"><i ng-click="dropDown(x.RepositoryCategory.repCatId,$event)"  ng-show="x.IsHaveChild==1">+</i></span> <em>{{x.RepositoryCategory.repCatName}}</em></td>
                            <td > <div class="books-btn"><a href="javascript:;"  ng-click="append(x.RepositoryCategory.repCatId,x.RepositoryCategory.repCatName)">添加二级分类</a></div></td>
                            <td>
                                <div class="books-btn">
                                    <a href="javascript:;"  ng-click="edit(x.RepositoryCategory.repCatId)">编辑</a>
                                    <a href="javascript:;"  ng-click="del(x.RepositoryCategory.repCatId,$index)">删除</a>
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
<script src="${basePath}/resources/js/classify/classifySkip.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/classify/knlwarehouseClassify.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
