<#import "/default/resourceManage.ftl" as resourceManage/>

<@resourceManage.modelHead title="会议室预约主页 " >

</@resourceManage.modelHead>
<link href="${basePath}/resources/css/resourceManager/orderHomepage.css" rel="stylesheet" type="text/css">
<@resourceManage.modelBody>

    <div class="member-modular-right meeting-modular">
        <div class="bg edit-user none">
            <div class="data-edit">
                <div class="data-edit-head clearfix">
                    <div>
                        <img src="${basePath}/resources/images/book/borrow.png" />
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
                <input type="text" placeholder="查询员工参会情况" />
            </div>
            <i class="iconfont icon-youjiantou2"></i>
        </div>
        <div class="meeting-reserve">
            <h3>会议室预定情况</h3>
            <div class="clearfix reserve">
                <div class="clearfix reserve-time">
                    <a href="">月</a>
                    <a href="">日</a>
                </div>
                <div class="reserve-date">
                    <i class="iconfont"><</i>
                    <span>2018</span>
                    <span>二月</span>
                    <i class="iconfont">></i>
                </div>
            </div>
            <div class="reserve-table">
                <div>星期五</div>
                <div class="reserve-table-main">
                    <div class="meeting-time">
                        <div>
                            <div>9:30 - 11:00</div>
                            <p>行政部门会议</p>
                        </div>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <td width="24%" >全天</td>
                            <td width="76%" ></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td >6:00以前</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>6:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>7:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>8:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>9:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>10:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>11:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>12:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>13:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>14:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>15:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>16:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>17:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>18:00</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        <tr>
                            <td>18:00以后</td>
                            <td ><div></div><div></div></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@resourceManage.modelBody>

<@resourceManage.js_scripts>
<script src="${basePath}/resources/js/resourceManager/orderHomepage.js" type="text/javascript"></script>
</@resourceManage.js_scripts>
