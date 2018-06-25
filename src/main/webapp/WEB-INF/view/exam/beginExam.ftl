
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人中心 -开始考试" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/exam/beginExam.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>

 <input type="hidden" value="${RequestParameters.examId}" id="examId"/>
<div class="member-modular-right message-notification">
    <div class="test-paper-examination">
        <div class="test-paper-content">
            <h3>2017年末培训考试</h3>
            <P>考试时间：30分钟</P>
        </div>
        <div class="test-paper-modular">
            <h3>一、选择题（每题10分）</h3>
            <div>
                <div class="problem-list">
                    <div class="problem-subject">
                        <em>题１</em>
                        <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> (  )</span></span>

                    </div>
                    <div class="problem-option">
                        <div>
                          <label>
                            <input type="radio" name="judge" />
                            <i class="iconfont"> </i>
                            <span><em>A.</em> 立场型谈判 </span>
                          </label>
                          <label>
                            <input type="radio" name="judge" />
                            <i class="iconfont"> </i>
                            <span><em>B.</em> 立场型谈判 </span>
                          </label>
                        </div>
                        <div>
                        <label>
                          <input type="radio" name="judge" />
                          <i class="iconfont"> </i>
                          <span><em>C.</em> 立场型谈判 </span>
                        </label>
                        <label>
                          <input type="radio"  />
                          <i class="iconfont"> </i>
                          <span><em>D.</em> 立场型谈判 </span>
                        </label>
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
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> (  )</span></span>

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
                    <div class="reference-answer clearfix">
                        <textarea ng-model="paper.paperAnswer" class="ng-pristine ng-valid ng-empty ng-touched"></textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="begin-submit">
            <a href="javascript:void(0);" ng-click="beginsubmitManager()">提交</a>
        </div>
    </div>

</div>
</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/exam/beginExam.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
