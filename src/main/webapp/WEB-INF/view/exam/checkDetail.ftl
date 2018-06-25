
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人中心-我的考试-查看详情" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/exam/checkDetail.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

 <input type="hidden" value="${RequestParameters.examId}" id="examId"/>
<div class="member-modular-right message-notification">
    <div class="test-paper-examination">
        <div class="test-paper-content">
            <div class="test-paper-title">
              <h3>2017年末培训考试</h3>
              <P>考试时间：30分钟</P>
            </div>
            <div class="test-paper-scores">成绩：84分</span>
        </div>
        <div class="test-paper-modular">
            <h3>一、选择题（每题10分）</h3>
            <div>
                <div class="problem-list">
                    <div class="problem-subject">
                        <em>题１</em>
                        <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是</span>

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
                        <div class="clearfix correct-rate">
                            <span>答案：</span><span>B</span>
                        </div>
                    </div>
                </div>
                <div class="problem-list">
                    <div class="problem-subject">
                        <em>题2</em>
                        <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是</span>

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
                        <div class="clearfix correct-rate">
                            <span>答案：</span><span>B</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="test-paper-modular">
            <h3>二、判断题（每题10分）</h3>
            <div>
                <div class="problem-list">
                    <div class="problem-subject">
                        <em>题１</em>
                        <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是</span>

                    </div>
                    <div class="problem-option problem-judge">
                        <label>
                            <input type="radio" name="judge" />
                            <i class="iconfont"> </i>
                            对
                        </label>
                        <label>
                            <input type="radio" name="judge" checked="checked"/>
                            <i class="iconfont"> </i>
                            错
                        </label>
                        <div class="clearfix correct-rate">
                            <span>答案：</span>对
                        </div>
                    </div>
                </div>
                <div class="problem-list">
                    <div class="problem-subject">
                        <em>题2</em>
                        <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是</span>

                    </div>
                    <div class="problem-option problem-judge">
                        <label>
                            <input type="radio" name="judge1"  />
                            <i class="iconfont"> </i>
                            对
                        </label>
                        <label>
                            <input type="radio" name="judge1" checked="checked"/>
                            <i class="iconfont"> </i>
                            错
                        </label>
                        <div class="clearfix correct-rate">
                            <span>答案：</sapn></span>错</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="test-paper-modular">
            <h3>三、问答题（每题20分）</h3>
            <div>
                <div class="problem-list problem-qa">
                    <div class="problem-subject">
                        <em>题１</em>
                        请简述在与客户交流过程应当注意哪些事项？

                    </div>
                    <div class="question-answer clearfix">
                        <div>
                            <P>1、永远要对客户热情</P>
                            <P>2、充分关注客户需要，只有产生关心才能产生关系</P>
                            <P>3、发自内心的喜欢客户的想法，并产生共鸣</P>
                        </div>
                        <div class="reference-answer clearfix">
                            <span>答案：</span>
                            <div>
                                <P>1、永远要对客户热情</P>
                                <P>2、充分关注客户需要，只有产生关心才能产生关系</P>
                                <P>3、发自内心的喜欢客户的想法，并产生共鸣</P>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/exam/checkDetail.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
