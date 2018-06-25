
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-培训管理">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/trainManager.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

<div class="bg edit-export none">
    <div class="data-edit">
        <div class="data-edit-head clearfix">
            <div>
                <span></span>
                参与人统计
            </div>
            <span>x</span>
            <div class="export"><img src="${basePath}/resources/images/app/export.png" />导出参与人</div>
        </div>
        <div class="clearfix export-tab">
            <a href="javascript:;" class="hover">确认情况</a>
            <a href="javascript:;">签到人员</a>
        </div>
        <div class="data-export">
            <p class="sign1">培训确认人数：<span>56人</span></p>
            <p class="sign2 none">满签人数：<span>56人</span></p>
            <div class="books-table sign1">
                <table>
                    <thead>
                    <tr>
                        <td width="16%">序号</td>
                        <td width="50%">姓名</td>
                        <td width="17%">消息阅读</td>
                        <td width="17%">培训确认</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>已读</td>
                        <td>是</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>已读</td>
                        <td>是</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>已读</td>
                        <td>是</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>已读</td>
                        <td>是</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="books-table sign2 none">
                <table>
                    <thead>
                    <tr>
                        <td width="16%">序号</td>
                        <td width="50%">姓名</td>
                        <td width="17%">开始二维码</td>
                        <td width="17%">结束二维码</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>是</td>
                        <td>是</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>是</td>
                        <td>是</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>王志鹏</td>
                        <td>是</td>
                        <td>是</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="page books-page">
                <a href="" class="page-text none">首页</a>
                <a href="" class="page-num hover">1</a>
                <a href="" class="page-num">2</a>
                <a href="" class="page-num">3</a>
                <a href="" class="page-num">4</a>
                <a href="" class="page-num">5</a>
                <a href="" class="page-more">...</a>
                <a href="" class="page-text">末页</a>
            </div>
        </div>
    </div>
</div>

    <div class="member-modular-right manage-right message-notification" ng-controller="navController">
        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>培训管理</strong></span>
        </div>
        <div class="books-table">
            <div class="search-model test-model clearfix">
                <div class="books-search clearfix">
                    <i class="iconfont icon-sousuo"></i>
                    <input type="text" placeholder="考试名称">
                </div>
                <div class="books-search-time">
                    <select>
                        <option>分类</option>
                    </select>
                    <select>
                        <option>场合</option>
                    </select>
                    <select>
                        <option>种类</option>
                    </select>
                    <input type="text" id="start-time" placeholder="开始时间 ">
                    <span>至 </span>
                    <input type="text" id="end-time" class="end-time" placeholder="结束时间">
                    <a href="">提交</a>
                </div>
            </div>
            <a href="javascript:;" class="add-classification" ng-click="leftJumps('/examTrain/10')"><span></span>发起培训</a>
            <table>
                <thead>
                <tr>
                    <td width="5%">序号</td>
                    <td width="21%">培训标题</td>
                    <td width="8%">分类</td>
                    <td width="6%">场合</td>
                    <td width="6%">种类</td>
                    <td width="12%">创建时间</td>
                    <td width="8%">状态</td>
                    <td width="10%">二维码</td>
                    <td width="24%">操作</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>演讲与财务汇报技巧</td>
                    <td>财务分析</td>
                    <td>线上</td>
                    <td>内训</td>
                    <td>2017-12-15</td>
                    <td>已通过</td>
                    <td><div class="ewm"><a href="javascript:;">开始</a><a href="javascript:;">结束</a></div></td>
                    <td><div class="statistic"><a href="javascript:;" class="hover">编辑</a><a href="javascript:;" class="hover">参与人</a><a href="javascript:;" class="hover">删除</a></div></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>演讲与财务汇报技巧</td>
                    <td>财务分析</td>
                    <td>线上</td>
                    <td>内训</td>
                    <td>2017-12-15</td>
                    <td>已通过</td>
                    <td><div class="ewm"><a href="javascript:;">开始</a><a href="javascript:;">结束</a></div></td>
                    <td><div class="statistic"><a href="javascript:;" >编辑</a><a href="javascript:;" >参与人</a><a href="javascript:;" >删除</a></div></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>演讲与财务汇报技巧</td>
                    <td>财务分析</td>
                    <td>线上</td>
                    <td>内训</td>
                    <td>2017-12-15</td>
                    <td>已通过</td>
                    <td><div class="ewm"><a href="javascript:;">开始</a><a href="javascript:;">结束</a></div></td>
                    <td><div class="statistic"><a href="javascript:;" >编辑</a><a href="javascript:;" >参与人</a><a href="javascript:;" >删除</a></div></td>
                </tr>
                </tbody>
            </table>
            <div class="page books-page">
                <a href="" class="page-text none">首页</a>
                <a href="" class="page-num hover">1</a>
                <a href="" class="page-num">2</a>
                <a href="" class="page-num">3</a>
                <a href="" class="page-num">4</a>
                <a href="" class="page-num">5</a>
                <a href="" class="page-more">...</a>
                <a href="" class="page-text">末页</a>
            </div>
        </div>

    </div>
</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/trainManager.js" type="text/javascript"></script>
</@examTrain.js_scripts>
