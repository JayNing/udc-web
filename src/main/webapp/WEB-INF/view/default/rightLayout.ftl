<div class="search-bar-right">
    <div class="community-bulletin">
        <div class="bulletin-head clearfix" ng-controller="dataController">
            <div ng-click="leftJumps('/postList')">
                <div ng-bind="postNum"></div>
                <h4>我的帖子</h4>
            </div>
            <div ng-click="leftJumps('/backIndex')">
                <div ng-bind="attentionNum"></div>
                <h4>我的关注</h4>
            </div>
            <div ng-click="leftJumps('/collectList')">
                <div ng-bind="collectionNum"></div>
                <h4>我的收藏</h4>
            </div>
        </div>
        <h3>社区公告</h3>
        <div class="bulletin-main" ng-controller="noticeController">
            <p ng-cloak class="ng-cloak" ng-bind-html="MessageContent | showAsHtml" ><span></span></p>
        </div>
    </div>
    <div class="integral" ng-controller="integralController">
        <div>
            <h3>积分排行榜</h3>
            <table>
                <thead>
                <tr>
                    <td width="17%">排名</td><td width="21%">姓名</td><td width="31%">所属部门</td><td width="31%">积分值</td>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="x in integralRecord">
                    <td ng-if="$index+1==1"><span ng-bind="$index+1"  ng-class="{true:'color1',false:''}[$index+1==1]"></span></td>
                    <td ng-if="$index+1==2"><span ng-bind="$index+1"  ng-class="{true:'color2',false:''}[$index+1==2]"></span></td>
                    <td ng-if="$index+1==3"><span ng-bind="$index+1"  ng-class="{true:'color3',false:''}[$index+1==3]"></span></td>
                    <td ng-if="$index+1>3"><span ng-bind="$index+1"></span></td>
                    <td ng-bind="x.RealName"></td>
                    <td ng-bind="x.DepartmentName"></td>
                    <td ng-bind="x.UserScore"></td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>
</div>

