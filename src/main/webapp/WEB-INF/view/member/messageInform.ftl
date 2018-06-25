
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.htmlHead title="会员中心 -消息通知" keywords="UDC知识库管理平台" description ="UDC知识库管理平台">

</@secondaryPerCenter.htmlHead>
<link href="${basePath}/resources/css/member/messageInform.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.htmlBody>
<div class="bg none">
    <div class="data-edit">
        <div class="data-edit-head clearfix">
            <div>
                <img src="${basePath}/resources/images/app/rounded.png" />
                编辑个人资料
            </div>
            <span>x</span>
        </div>
        <div class="data-edit-main">
            <form>
                <div class="data-edit-list data-edit-img">
                    <span>头像：</span>
                    <div style="background:url('${basePath}/resources/images/app/select-img.png') no-repeat center;background-size:cover;"> <input type="file" /></div>
                </div>
                <div class="data-edit-list">
                    <span>姓名：</span>
                    <input type="text" />
                </div>
                <div class="data-edit-list">
                    <span>部门：</span>
                    <select>
                        <option>市场部</option>
                    </select>
                </div>
                <a href="">保存</a>
            </form>
        </div>
    </div>
</div>


    <div class="member-modular-right message-notification" ng-controller="noticeController">
        <div class="notification-title">
            <img src="${basePath}/resources/images/app/comment.png">
            <span>消息通知</span>
        </div>
        <div class="notification-attend">
            <h3>通知：线下培训《{{train.title}}》<a href="javascript:;">邀请你参加</a></h3>
           <table>
                <tr>
                    <td>培训标题：</td>
                    <td ng-bind="train.title"></td>
                </tr>
                <tr>
                    <td>时间：</td>
                    <td>{{train.startTime}}至{{train.endTime}}</td>
                </tr>
                <tr>
                    <td>地点：</td>
                    <td ng-bind="train.address"></td>
                </tr>
                <tr>
                    <td>种类：</td>
                    <td ng-bind="train.species"></td>
                </tr>
                <tr>
                    <td>培训内容：</td>
                    <td ng-bind="train.content"></td>
                </tr>
                <tr>
                    <td>导师介绍：</td>
                    <td>
                        <div>
                            <h4 ng-bind="train.teacher">讲师：刘茂生</h4>
                            <div ng-bind="train.introduce">刘茂生，文学博士、博士后，江西师范大学英语语言文学首席教授、博士生导师，江西师范大学校学术委员会委员、社会科学组副组长。兼任江西省社联第八届理事会理事、江西省外国文学学会会长、江西省哲学社会科学重点研究基地“江西师范大学叙事学研究中心”主任、江西省高校“外国语言文学”学科联盟学</div>
                        </div>
                    </td>
                </tr>
            </table>
            <div class="clearfix notification-btn">
				<a href="javascript:;" ng-click="join(train.id)">确认参加</a>
            </div>

        </div>
    </div>
</@secondaryPerCenter.htmlBody>

<@secondaryPerCenter.js_scripts>

<script src="${basePath}/resources/js/member/messageInform.js" type="text/javascript"></script>

</@secondaryPerCenter.js_scripts>
