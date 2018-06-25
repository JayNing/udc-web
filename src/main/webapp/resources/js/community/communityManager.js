/*
* 社区管理js
* */

//页面初始化选中左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('社区管理')").parent().children().css({"color":"#fff"}).parent().parent().css({"background":"#7a8ce3",});
});

//社区管理
app.controller('commumitymangController', function($scope,$http,httpService) {
    //页面初始化
    $scope.delBool=false;
    $scope.noticeBool=false;
    $scope.curr=1;
    $scope.search = "";
    $scope.startTime = "";
    $scope.endTime = "";
    $scope.typeId=2;
    $scope.selectDepartment="0";
    var data = {
        typId:'',
        title:'',
        disType:2,
        start1Time:'',
        end1Time:'',
        pageIndex: 1,
        pageSize: 10
    }
    httpService.getList(data,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.community = response.data.result;
            for(var i=0;length=$scope.community.length,i<length;i++){
                $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
            }
        }
    },function errorCallback(response) {
        console.log("err");
    });

    //初始化分页页数
    var data1 = {
        typId:'',
        title:'',
        disType:2,
        start1Time:'',
        end1Time:''
    }
    httpService.getList(data1,"/bbsManager/queryDiscussionInfoCounts").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
    },function errorCallback(response) {
        console.log("err");
    });
    //初始化获取所有分类
    var sitedData = {
        parentId:0
    }
    httpService.getList(sitedData,"/essayType/getEssayTypeList").then(function successCallback(response) {
        $scope.sites = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    //选择类别
    $scope.selectId=function(id){
        $scope.typeId=id;
        var data = {//选择类别时，不按照分类进行查找
            typId:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        httpService.getList(data,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
            if(response.data.code==1){
                $scope.community = response.data.result;
                for(var i=0;length=$scope.community.length,i<length;i++){
                    $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
                }
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1 = {
            typId:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/bbsManager/queryDiscussionInfoCounts").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange(1, $scope.allPage, 5);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }

    //发送公告弹框
    $scope.release = function () {
        httpService.getList(data,"/messageManage/queryNewestMessage").then(function (response) {
            $scope.MessageContent = response.data.result.MessageContent || '';
            $scope.notice = $scope.MessageContent;
        },function(){
            console.log("err")
        })
        $scope.noticeBool = !$scope.noticeBool;
    };
    //编辑公告
    $scope.editNotice = function () {
        var data = {
            messageContent: $scope.notice
        }
        if (data.messageContent == "") {
            layer.msg("请输入公告内容!");
            return;
        }
        httpService.postList(data,"/messageManage/addBbsNotice").then(function successCallback(response) {
            if (response.data.code==1){
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.noticeBool = false;
    };
    //关闭弹框
    $scope.close=function(){
        $scope.delBool=false;
        $scope.noticeBool = false;
        // $scope.notice = '';
    }
    //搜索社区
    $scope.submit = function () {
        var data = {
            typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        httpService.getList(data,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
            $scope.community = [];
            if (response.data.code==1){
                $scope.community = response.data.result;
                for(var i=0;length=$scope.community.length,i<length;i++){
                    $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
                }
            }else if(response.data.code == 2){
                layer.msg(response.data.msg);
            }

        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1 = {
            typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/bbsManager/queryDiscussionInfoCounts").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange(1, $scope.allPage, 5);
            }
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //回车键搜索
    $scope.enterEvent = function(e) {
        var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
            $scope.submit();
        }
    }
    //根据Id删除分类
    $scope.del = function(id,index) {
        $scope.id=id;
        $scope.index = index;
        $scope.delBool=true;
    };
    //取消删除
    $scope.cancel=function(){
        $scope.delBool=false;
    }
    //确认删除
    $scope.confirm=function(){
        var data = {
            disId:$scope.id
        }
        $scope.community.splice($scope.index, 1);
        //删除提交
        httpService.postList(data,"/bbsManager/deleteDiscussion").then(function successCallback(response) {
            if (response.data.code==1){
                layer.msg(response.data.msg)
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新社区
        var data1 = {
            typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        httpService.getList(data1,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.community = response.data.result;
                for(var i=0;length=$scope.community.length,i<length;i++){
                    $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
                }
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data2 = {
            typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data2,"/bbsManager/queryDiscussionInfoCounts").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange($scope.curr, $scope.allPage, 5);
            }
        },function errorCallback(response) {
            console.log("err");
        });

        $scope.delBool=false;
    }
    //置顶
    $scope.setTop = function (id,index) {
        var data = {
            disId: id
        }
        httpService.postList(data,"/bbsManager/addTopDiscussion").then(function successCallback(response) {
            if (response.data.code==1){
                layer.msg(response.data.msg)
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新社区
        var data1 = {
            typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
            title:$scope.search ? ($scope.search) : '',
            disType:$scope.typeId,
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        httpService.getList(data1,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.community = response.data.result;
                for(var i=0;length=$scope.community.length,i<length;i++){
                    $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
                }
            }

        },function errorCallback(response) {
            console.log("err");
        });
    }


   //绑定分页点击事件
   $scope.pageClick = function (page) {
       switch(page){
           case  '上一页':
               page = parseInt($scope.curr) - 1;
               if(page==1){
                   $(".page a:contains('上一页')").fadeOut(300);
               }
               $(".page a:contains('下一页')").fadeIn(300);
               break;
           case '首页':
               page = 1;
               $(".page a:contains('上一页')").fadeOut(300);
               $(".page a:contains('下一页')").fadeIn(300);
               break;
           case '下一页':
               page = parseInt($scope.curr) + 1;
               $(".page a:contains('上一页')").fadeIn(300);
               if(page==$scope.allPage){
                   $(".page a:contains('下一页')").fadeOut(300);
               }
               break;
           case '尾页':
               $(".page a:contains('上一页')").fadeIn(300);
               page =  $scope.allPage;
               $(".page a:contains('下一页')").fadeOut(300);
               break;
           case 1:
               $(".page a:contains('上一页')").fadeOut(300);
               $(".page a:contains('下一页')").fadeIn(300);
               break;
           case $scope.allPage:
               $(".page a:contains('上一页')").fadeIn(300);
               $(".page a:contains('下一页')").fadeOut(300);
               break;
           default:
               $(".page a:contains('上一页')").fadeIn(300);
               $(".page a:contains('下一页')").fadeIn(300);
       }
       if (page < 1) page = 1;
       else if (page >  $scope.allPage) page =  $scope.allPage;
       //点击相同的页数 不执行点击事件
       if (page == $scope.curr) return;
       //更新社区
       var data1 = {
           typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
           title:$scope.search ? ($scope.search) : '',
           disType:$scope.typeId,
           start1Time:$scope.startTime?$scope.startTime:'',
           end1Time:$scope.endTime?$scope.endTime:'',
           pageIndex:page,
           pageSize:10
       }
       httpService.getList(data1,"/bbsManager/queryDiscussionManageList").then(function successCallback(response) {
           if (response.data.code==1){
               $scope.community = response.data.result;
               for(var i=0;length=$scope.community.length,i<length;i++){
                   $scope.community[i].CreateTime=$scope.community[i].CreateTime.substr(0,10);
               }
           }
       },function errorCallback(response) {
           console.log("err");
       });
       //更新分页页数
       var data2 = {
           typId:$scope.selectDepartment>0?$scope.selectDepartment:'',
           title:$scope.search ? ($scope.search) : '',
           disType:$scope.typeId,
           start1Time:$scope.startTime?$scope.startTime:'',
           end1Time:$scope.endTime?$scope.endTime:''
       }
       httpService.getList(data2,"/bbsManager/queryDiscussionInfoCounts").then(function successCallback(response) {
           if (response.data.code==1){
               $scope.curr=page;
               $scope.allPage=Math.ceil(response.data.result/10);
               $scope.page = getRange(page, $scope.allPage, 5);
           }
       },function errorCallback(response) {
           console.log("err");
       });

   };

});




