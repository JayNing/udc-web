<#import "/default/resourceManage.ftl" as resourceManage/>

<@resourceManage.modelHead title="员工参会搜索结果 " >

</@resourceManage.modelHead>
<link href="${basePath}/resources/css/resourceManager/orderDate.css" rel="stylesheet" type="text/css">
<@resourceManage.modelBody>

<div class="member-modular-right meeting-modular">
    <div class="bg edit-user none">
        <div class="data-edit">
            <div class="data-edit-head clearfix">
                <div>
                    <img src="i${basePath}/resources/images/book/borrow.png" />
                    借阅
                </div>
                <span>x</span>
            </div>
            <div class="borrow-edit-main">
                <form>
                    <div class="borrow-edit-list">
                        <span>借阅时长：</span>
                        <input type="text" />
                        <span class="borrow-class">天</span>
                    </div>
                    <div class="borrow-edit-list">
                        <span>归还时间：</span>
                        <span class="borrow-class">2018-03-11</span>
                    </div>
                    <div class="borrow-edit-list">
                        <span>借阅人：</span>
                        <span><em class="borrow-class">总务处</em>—王志明</span>
                    </div>
                    <a href="">提交 </a>
                </form>
            </div>
        </div>
    </div>
    <div class="clearfix meeting-search">
        <div>
            <i class="iconfont icon-sousuo"></i>
            <input type="text" placeholder="搜索页" />
        </div>
        <i class="iconfont icon-youjiantou2"></i>
    </div>
    <div class="meeting-reservation">
        <div class="meeting-search-year">
            <i class=""><</i>
            <span>2018</span>
            <span>二月</span>
            <i class="">></i>
        </div>
        <div class="meeting-search-tit">
            <div class="meeting-line"></div>
            <div class="meeting-search-list clearfix">
                <div class="meeting-search-time">
                    <span>2号</span>
                    <i></i>
                </div>
                <div class="meeting-search-content">
                    <div class="meeting-search-title clearfix">
                        <div>
                            <h4>1号会议室外高桥30</h4>
                            <p>外高桥30</p>
                        </div>
                        <img src="${basePath}/resources/images/app/meeting.png" />
                    </div>
                    <div class="meeting-search-details">
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>
                    </div>
                    <div class="meeting-search-title clearfix">
                        <div>
                            <h4>1号会议室外高桥30</h4>
                            <p>外高桥30</p>
                        </div>
                        <img src="${basePath}/resources/images/app/meeting.png" />
                    </div>
                    <div class="meeting-search-details">
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>

                    </div>
                </div>
            </div>
            <div class="meeting-search-list clearfix">
                <div class="meeting-search-time">
                    <span>7号</span>
                    <i></i>
                </div>
                <div class="meeting-search-content">
                    <div class="meeting-search-title clearfix">
                        <div>
                            <h4>1号会议室外高桥30</h4>
                            <p>外高桥30</p>
                        </div>
                        <img src="${basePath}/resources/images/app/meeting.png" />
                    </div>
                    <div class="meeting-search-details">
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>

                    </div>

                </div>
            </div>
            <div class="meeting-search-list clearfix">
                <div class="meeting-search-time">
                    <span>10号</span>
                    <i></i>
                </div>
                <div class="meeting-search-content">
                    <div class="meeting-search-title clearfix">
                        <div>
                            <h4>1号会议室外高桥30</h4>
                            <p>外高桥30</p>
                        </div>
                        <img src="${basePath}/resources/images/app/meeting.png" />
                    </div>
                    <div class="meeting-search-details">
                        <div>
                            <div>9:50~11:00</div>
                            <p>行政部门会议</p>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

</@resourceManage.modelBody>

<@resourceManage.js_scripts>
<script src="${basePath}/resources/js/resourceManager/orderDate.js" type="text/javascript"></script>
</@resourceManage.js_scripts>
