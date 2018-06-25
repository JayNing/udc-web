
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考卷管理-添加考卷">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/addPaper.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

    <div class="member-modular-right message-notification" ng-controller="addPapersController">
        <div class="bg edit-problem " ng-cloak class="ng-cloak" ng-show="selectTemplatesBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <span></span>
                        选择模块
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="problem-add">
                    <form>
                        <div class="clearfix">
                            <span>模块：</span>
                            <select>
                                <option>选择题</option>
                            </select>
                        </div>
                        <div class="clearfix">
                            <span>每题分数：</span>
                            <input type="">
                        </div>
                        <a href="javascript:;" class="problem-submit" ng-click="templateConfirm()">确定</a>
                    </form>

                </div>
            </div>

        </div>
        <div class="bg edit-problem" ng-cloak class="ng-cloak" ng-show="selectPapersBool">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <span></span>
                        试卷选择
                    </div>
                    <span ng-click="close()">x</span>
                </div>
                <div class="paper-selection">
                    <form>
                        <div class="clearfix paper-input">
                            <input type="text" placeholder="题目名称">
                            <div>
                                <input type="text" id="start-time" placeholder="开始时间 ">
                                <span>至 </span>
                                <input type="text" id="end-time" class="end-time" placeholder="结束时间">
                            </div>
                            <a href="">搜索</a>
                        </div>
                        <div class="paper-data">
                            <div class="clearfix paper-classification">
                                <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==1]" ng-click="selectSubject(1)">选择题</a>
                                <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==2]" ng-click="selectSubject(2)">多选题</a>
                                <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==3]" ng-click="selectSubject(3)">判断题</a>
                                <a href="javascript:;" ng-class="{true:'hover',false:''}[subjectId==4]" ng-click="selectSubject(4)">问答题</a>
                            </div>
                            <div class="question-edit" ng-if="subjectId==1">
                                <div class="test-paper">
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">您的性别是( )</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">2018年初财务部新晋员工培训测试</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="page test-page">
                                        <a href="" class="page-text">首页</a>
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
                            <div class="question-edit" ng-if="subjectId==2">
                                <div class="test-paper">
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">sdahfjsdfjh</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">2018年初财务部新晋员工培训测试</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="page test-page">
                                        <a href="" class="page-text">首页</a>
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
                            <div class="question-edit" ng-if="subjectId==3">
                                <div class="test-paper">
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">您的性别地方烦烦烦烦烦烦是( )</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">2018年初财务部新晋员工培训测试</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="page test-page">
                                        <a href="" class="page-text">首页</a>
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
                            <div class="question-edit" ng-if="subjectId==4">
                                <div class="test-paper">
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">您的性别是444( )</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="test-paper-list">
                                        <label class="clearfix">
                                            <div>
                                                <input type="checkbox">
                                                <i class="iconfont"></i>
                                            </div>
                                            <span class="test-paper-name">2018年初财务部新晋员工培训测试</span>
                                            <span class="test-time">2018-02-22</span>
                                        </label>
                                    </div>
                                    <div class="page test-page">
                                        <a href="" class="page-text">首页</a>
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
                            <div class="test-paper-submit"><a href="" ng-click="testQuestionConfirm()">确定</a></div>
                        </div>
                    </form>

                </div>
            </div>

        </div>

        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>考卷管理</strong></span>
        </div>
        <div class="test-paper-examination">
            <div class="test-paper-title">试卷名称：<span>2017年末培训考试</span></div>
            <div class="test-paper-modular">
                <h3>一、选择题（每题10分）<a href="" ng-click="deleteTemplates()">删除模板</a></h3>
                <div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题１</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>
                            <i ng-click="del()">x</i>
                        </div>
                        <div class="problem-option">
                            <div>
                                <span><em>A.</em> 立场型谈判 </span>
                                <span><em>B.</em> 立场型谈判 </span>
                            </div>
                            <div>
                                <span><em>C.</em> 立场型谈判 </span>
                                <span><em>D.</em> 立场型谈判 </span>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题2</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>
                            <i ng-click="del()">x</i>
                        </div>
                        <div class="problem-option">
                            <div>
                                <span><em>A.</em> 立场型谈判 </span>
                                <span><em>B.</em> 立场型谈判 </span>
                            </div>
                            <div>
                                <span><em>C.</em> 立场型谈判 </span>
                                <span><em>D.</em> 立场型谈判 </span>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题3</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>
                            <i ng-click="del()">x</i>
                        </div>
                        <div class="problem-option">
                            <div>
                                <span><em>A.</em> 立场型谈判 </span>
                                <span><em>B.</em> 立场型谈判 </span>
                            </div>
                            <div>
                                <span><em>C.</em> 立场型谈判 </span>
                                <span><em>D.</em> 立场型谈判 </span>
                            </div>
                        </div>
                    </div>
                    <a href="javascript:;" class="add-problem" ng-click="addTestQuestions()">添加</a>
                </div>
            </div>
            <div class="test-paper-modular">
                <h3>二、判断题（每题10分）<a href="" ng-click="deleteTemplates()">删除模板</a></h3>
                <div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题１</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>
                            <i ng-click="del()">x</i>
                        </div>
                        <div class="problem-option problem-judge">
                            <label>
                                <input type="radio" name="judge" />
                                <i class="iconfont"> </i>
                                对
                            </label>
                            <label>
                                <input type="radio" name="judge"/>
                                <i class="iconfont"> </i>
                                错
                            </label>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题2</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>
                            <i ng-click="del()">x</i>
                        </div>
                        <div class="problem-option problem-judge">
                            <label>
                                <input type="radio" name="judge" />
                                <i class="iconfont"> </i>
                                对
                            </label>
                            <label>
                                <input type="radio" name="judge"/>
                                <i class="iconfont"> </i>
                                错
                            </label>
                        </div>
                    </div>
                    <a href="" class="add-problem" ng-click="addTestQuestions()">添加</a>
                </div>
            </div>
            <div class="test-paper-modular">
                <h3>三、问答题（每题20分）<a href="" ng-click="deleteTemplates()">删除模板</a></h3>
                <div>
                    <div class="problem-list problem-qa">
                        <div class="problem-subject">
                            <em>题１</em>
                            请简述在与客户交流过程应当注意哪些事项？
                            <i ng-click="del()">x</i>
                        </div>
                    </div>
                    <div class="problem-list problem-qa">
                        <div class="problem-subject">
                            <em>题2</em>
                            请简述在与客户交流过程应当注意哪些事项？
                            <i ng-click="del()">x</i>
                        </div>
                    </div>
                    <a href="" class="add-problem" ng-click="addTestQuestions()">添加</a>
                </div>
            </div>
            <div class="test-paper-footer">
                <a href="" ng-click="selectTemplates()">选择模块</a>
            </div>
        </div>
        <a href="" class="test-submit">保存</a>
    </div>
</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/exam/addPaper.js" type="text/javascript"></script>
</@examTrain.js_scripts>
