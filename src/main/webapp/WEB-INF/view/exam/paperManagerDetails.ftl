
<#import "/default/examTrain.ftl" as examTrain/>

<@examTrain.modelHead title="考试培训-考试管理-详情 ">

</@examTrain.modelHead>
<link href="${basePath}/resources/css/exam/paperManagerDetails.css" rel="stylesheet" type="text/css">
<@examTrain.modelBody>

<input type="hidden" value="${RequestParameters.ExamId}" id="examId"/>
    <div class="member-modular-right message-notification">
        <div class="books-title">
            <i></i>
            <span>考试培训-<strong>考试管理</strong></span>
        </div>
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
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>

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
                                <a href="">正确 90%  </a>
                                <a href=""> 错误5% </a>
                                <a href="">无效5%</a>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题2</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>

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
                                <a href="">正确 90%  </a>
                                <a href=""> 错误5% </a>
                                <a href="">无效5%</a>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题3</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>

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
                                <a href="">正确 90%  </a>
                                <a href=""> 错误5% </a>
                                <a href="">无效5%</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="test-paper-modular">
                <h3>二、判断题（每题10分）<a href="">删除模板</a></h3>
                <div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题１</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>

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
                                <a href="">正确 90%  </a>
                                <a href=""> 错误5% </a>
                                <a href="">无效5%</a>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list">
                        <div class="problem-subject">
                            <em>题2</em>
                            <span> 一般只限于合作关系非常友好,并有长期的业务往来的双方之间的谈判方式是<span> ( B )</span></span>

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
                                <a href="">正确 90%  </a>
                                <a href=""> 错误5% </a>
                                <a href="">无效5%</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="test-paper-modular">
                <h3>三、问答题（每题20分）<a href="">删除模板</a></h3>
                <div>
                    <div class="problem-list problem-qa">
                        <div class="problem-subject">
                            <em>题１</em>
                            请简述在与客户交流过程应当注意哪些事项？

                        </div>
                        <div class="reference-answer clearfix">
                            <span>参考答案</span>
                            <div>
                                <P>1、永远要对客户热情</P>
                                <P>2、充分关注客户需要，只有产生关心才能产生关系</P>
                                <P>3、发自内心的喜欢客户的想法，并产生共鸣</P>
                            </div>
                        </div>
                    </div>
                    <div class="problem-list problem-qa">
                        <div class="problem-subject">
                            <em>题2</em>
                            请简述在与客户交流过程应当注意哪些事项？

                        </div>
                        <div class="reference-answer clearfix">
                            <span>参考答案</span>
                            <div>
                                <P>1、永远要对客户热情</P>
                                <P>2、充分关注客户需要，只有产生关心才能产生关系</P>
                                <P>3、发自内心的喜欢客户的想法，并产生共鸣</P>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="evaluation">
                <h4>考试评估：</h4>
                <p>本次测验很有针对性，题目也出的相当好。虽然说测试结果没有达到预期，但恰恰说明了我们员工对于企业对于岗
                    位的职责性认识薄弱，能够反映出一定的问题。未来我们需要加大在基础认识上的培训，建立良好的企业规章。
                </p>
            </div>
        </div>

    </div>
</@examTrain.modelBody>

<@examTrain.js_scripts>
<script src="${basePath}/resources/js/exam/paperManagerDetails.js" type="text/javascript"></script>
</@examTrain.js_scripts>
