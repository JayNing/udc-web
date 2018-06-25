
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="考试培训-培训管理-发起线上">

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/exam/trainMangOnline.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

    <div class="member-modular-right message-notification" ng-controller="onLineController">
        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>培训管理</strong></span>
        </div>
        <div class="books-table">
            <div class="clearfix classification">
                <a href="" class="hover">线上</a>
                <a href="" ng-click="beLine()">线下</a>
            </div>
            <form>
                <div class="on-line">
                    <div class="on-line-text on-line-tit clearfix">
                        <span>培训标题：</span>
                        <input type="text" id="title">
                    </div>
                    <div class="on-line-text clearfix">
                        <span>开始时间：</span>
                        <input type="text" id="start-time">
                        <img src="${basePath}/resources/images/app/date.png">
                    </div>
                    <div class="on-line-text clearfix">
                        <span>结束时间：</span>
                        <input type="text" id="end-time">
                        <img src="${basePath}/resources/images/app/date.png">
                    </div>
                    <div class="line"></div>
                    <div class="on-line-text clearfix">
                        <span>讲师姓名：</span>
                        <input type="text" id="name">
                    </div>
                    <div class="on-line-text clearfix">
                        <span>讲师头像：</span>
                        <div class="teacher-photo" style="background:url('${basePath}/resources/images/app/select-img.png') no-repeat center;background-size:contain;">
                            <input type="file">
                        </div>
                    </div>
                    <div class="on-line-text clearfix">
                        <span>讲师简介：</span>
                        <textarea placeholder="" id="summary"></textarea>
                    </div>
                    <div class="line"></div>
                    <div class="on-line-text clearfix">
                        <span>培训内容：</span>
                        <textarea placeholder="" id="train"></textarea>
                    </div>
                    <div class="on-line-text test-name clearfix">
                        <span>视频上传：</span>
                        <input type="text" id="video">
                        <label >
                            <a href="javascript:;">本地上传</a>
                            <input type="file" />
                        </label>
                    </div>
                    <div class="on-line-text on-line-type clearfix">
                        <span>种类：</span>
                        <div class="warehouse-radio clearfix">
                            <label>
                                <input type="radio" checked="" name="selected">
                                <i class="iconfont"></i>
                                <span>外训</span>
                            </label>
                            <label>
                                <input type="radio" name="selected">
                                <i class="iconfont"></i>
                                <span>内训</span>
                            </label>
                        </div>
                    </div>
                    <div class="line"></div>
                    <div class="examination-mark clearfix">
                        <span>培训人员： </span>
                        <span>财务部-<em>方正余</em></span>
                        <span>财务部-<em>张岩</em></span>
                        <a href="">添加</a>
                    </div>
                </div>
                <a href="javascript:;" class="examination-examine">提交审核</a>
            </form>
        </div>

    </div>
</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>

<script src="${basePath}/resources/js/date/date.js" type="text/javascript"></script>
<script src="${basePath}/resources/js/exam/trainMangOnline.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
