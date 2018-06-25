<#--
搜索页面公共模板
-->

<div class="timeGroup">
    <input type="text" id="start-time" placeholder="开始时间" ng-model="startTime">
    <i class="retime iconfont icon-guanbi" href="javascript:;" ng-show="startTime" ng-click="startTime='';"></i>
</div>

<span>至 </span>
<div class="timeGroup endTimeGroup">
    <input type="text" id="end-time" class="end-time" placeholder="结束时间" ng-model="endTime">
    <i class="retime iconfont icon-guanbi" href="javascript:;" ng-show="endTime" ng-click="endTime='';"></i>
</div>
<a href="javascript:;" ng-click="submit()" class="time-submit">提交</a>