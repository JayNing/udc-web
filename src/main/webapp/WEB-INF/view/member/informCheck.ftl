
<#import "/default/secondaryPerCenter.ftl" as secondaryPerCenter/>

<@secondaryPerCenter.modelHead title="个人中心 -通知审核" >

</@secondaryPerCenter.modelHead>
<link href="${basePath}/resources/css/member/informCheck.css" rel="stylesheet" type="text/css">
<@secondaryPerCenter.modelBody>


    <div class="member-modular-right message-notification" ng-controller="noticeController">
        <div class="notification-title">
            <img src="${basePath}/resources/images/app/comment.png">
            <span>消息通知</span>
        </div>
        <div class="notification-attend">
            <h3>通知：线下培训《{{train.title}}》<a href="javascript:;" class="examine">请求审核</a></h3>
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
                <a href="javascript:;" ng-click="passThrough(train.id)">审核通过</a>
                <a href="javascript:;" ng-click="reject()">驳回</a>
            </div>
        </div>
    </div>

</@secondaryPerCenter.modelBody>

<@secondaryPerCenter.js_scripts>
<script src="${basePath}/resources/js/member/informCheck.js" type="text/javascript"></script>
</@secondaryPerCenter.js_scripts>
