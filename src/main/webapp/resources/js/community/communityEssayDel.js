/*
* 社区-提问详情js
* */
//社区
$(function(){
    $(".header li a:contains('社区')").addClass("hover");
});
app.controller('communityessdController', function ($scope, $http,httpService,$timeout) {
    $scope.listBool=false;
    var data = {
        disId:$("#dis").val()
    }
    //初始化页面内容
    httpService.getList(data,"/bbsManager/queryDisInfoByDisId").then(function successCallback(response) {

        if (response.data.code==1){

            console.log(response.data.result);
            $scope.title = response.data.result.DisTitle;
            $scope.department = response.data.result.DepartmentName;
            $scope.name = response.data.result.AuthorName;
            $scope.content = response.data.result.QueDesc;
            $scope.labels = response.data.result.TagStrings;
            $scope.use = response.data.result.UsefulCount;
            $scope.useless = response.data.result.UnUsefulCount;
            $scope.Type=response.data.result.DisType;
            $scope.DisId=response.data.result.DisId;
            $scope.DisType=response.data.result.DisType;
            $scope.DisRelationId=response.data.result.DisRelationId;
            $scope.IsCollect=response.data.result.IsCollect;
            $scope.IsUnUseful=response.data.result.IsUnUseful;
            $scope.IsUseful=response.data.result.IsUseful;
            $scope.IsFollow=response.data.result.IsFollow;
            $scope.UserId= response.data.result.UserId;
            $scope.FollowButton=response.data.result.FollowButton;
            $scope.KnlgFlag = response.data.result.KnlgFlag;
            $scope.AnsContent=response.data.result.AnsContent;
            if($scope.labels.indexOf(",")>0){
                $scope.labels=$scope.labels.split(",");
            }else{
                $scope.labels=$scope.labels.split(" ");
            }

        }

    },function errorCallback(response) {
        console.log("err");
    });
    //转换时间
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }
    //初始化问答列表
    var qData = {
        disId:parseInt($("#dis").val()),
        pageIndex:1,
        pageSize:10
    }
    httpService.getList(qData,"/bbsManager/queryCommentInfoList").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.interlocution = response.data.result;
            //如果没有回复隐藏模块
            if($scope.interlocution==null){
                $scope.listBool=false;
                return;
            }
            $scope.listBool=true;
            console.log($scope.interlocution);
            for(var i=0;i<$scope.interlocution.length;i++){
                if($scope.interlocution[i].CreateTime>0){
                    $scope.interlocution[i].CreateTime = timestampToTime($scope.interlocution[i].CreateTime);
                }
            }
        }

    },function errorCallback(response) {
        console.log("err");
    });
    //初始化分页页数
    $scope.curr=1;
    var pageData = {
        disId:parseInt($("#dis").val())
    }
    httpService.getList(pageData,"/bbsManager/queryCommentInfoListCount").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.allNum=response.data.result;
            $scope.page = getRange(1, $scope.allPage, 5);
            //返回页数范围（用来遍历）
        }
    },function errorCallback(response) {
        console.log("err");
    })
    //标签搜索
    $scope.labelSearch = function(lname){
        window.localStorage['lname'] = lname;
        window.location.href = basePath + '/topSkip/4';
    }
    //加入仓库
    $scope.add=function(){
        if($scope.KnlgFlag ==2) return;
        var data = {
            dis:parseInt($("#dis").val())
        }
        httpService.postList(data,"/bbsManager/addBbsToKnlg").then(function successCallback(response) {
            if(response.data.code == 1) $scope.KnlgFlag = 2;
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //关注
    $scope.addFollow=function(event){
        var data = {
            userId:$scope.UserId
        }
        httpService.postList(data,"/bbsManager/addFollow").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });

        $scope.IsFollow=!$scope.IsFollow;

    }
    //有用
    $scope.addUse=function(){
        //如果已经差评，点击好评则取消差评
        var useData = {
            UsefulTyp:$scope.Type,
            relationId:$scope.DisRelationId,
            disId:$scope.DisId
        }
        httpService.postList(useData,"/bbsManager/addUseful").then(function successCallback(response) {
            if(response.data.code == 1){
                    var useData1 = {
                        disId:$scope.DisId
                    }
                    httpService.getList(useData1,"/bbsManager/queryUsefulCountByDisId").then(function successCallback(response) {
                        if(response.data.code == 1){
                            // 点击后如果新的小于当前的，则取消 else 则增加
                            if($scope.use > response.data.result.UsefulCount){
                                layer.msg('取消好评');
                                $scope.IsUseful = false;
                            }else if($scope.use < response.data.result.UsefulCount){
                                layer.msg('好评成功');
                                $scope.IsUseful=true;
                            }else{
                                layer.msg('你已经差评过了');
                            }
                            $scope.use=response.data.result.UsefulCount;
                        }

                    },function errorCallback(response) {
                        console.log("err");
                    });
            }

        },function errorCallback(response) {
            console.log("err");
        });



    }
    //无用
    $scope.addUseless=function(){
        //如果已经好评，点击差评则取消好评
        var unuseData = {
            UsefulTyp:$scope.Type,
            relationId:$scope.DisRelationId,
            disId:$scope.DisId
        }
        httpService.postList(unuseData,"/bbsManager/addUnUseful").then(function successCallback(response) {
            if(response.data.code ==1){

                var useData = {
                    disId:$scope.DisId
                }
                httpService.getList(useData,"/bbsManager/queryUsefulCountByDisId").then(function successCallback(response) {
                    if($scope.useless > response.data.result.UnUsefulCount){
                        layer.msg('取消差评');
                        $scope.IsUnUseful = false;
                    }else if($scope.useless < response.data.result.UnUsefulCount){
                        layer.msg('差评成功');
                        $scope.IsUnUseful=true;
                    }else{
                        layer.msg('你已经好评过了');
                    }
                    $scope.useless=response.data.result.UnUsefulCount
                },function errorCallback(response) {
                    console.log("err");
                });
            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    //收藏
    $scope.collection=function(){
        var collecdData = {
            targetType:$scope.Type,
            disId:$scope.DisId
        }
        httpService.postList(collecdData,"/bbsManager/addCollect").then(function successCallback(response) {
            layer.msg(response.data.msg);
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.IsCollect=!$scope.IsCollect;

    }

    //评论提交
    $scope.message = "";
    $scope.changemessage=function(){
        if($scope.message.length>150){
            layer.msg("请输入150个字以内的回答！");
            $scope.message=$scope.message.substr(0,150);
        }
    }
    $scope.submit=function(){
        var data = {
            ansContent:$scope.message,
            queId:$scope.DisRelationId,
           // disId:$scope.DisId
        }
        console.log("77777777777777777");
        console.log(data);
        console.log($scope.message);
        if($scope.message==""){
            layer.msg("请填写答案!");
            return;
        }
        httpService.postList(data,"/bbsManager/addAnswerInfo").then(function successCallback(response) {
            layer.msg(response.data.msg);
            $timeout(function(){
                window.location.replace(location.href);
            },1000);
        },function errorCallback(response) {
            console.log("err");
        });

    }

    $scope.likes=function(id,event){
        var data = {
            UsefulTyp:2,
            relationId:id
        }

        httpService.postList(data,"/bbsManager/addDisctLike").then(function successCallback(response) {
            layer.msg(response.data.msg);
            if(response.data.code == 1){
                $(event.target).addClass("hover");
            }else if(response.data.code == 2){
                $(event.target).removeClass("hover");
            }
            $scope.updateQ();
        },function errorCallback(response) {
            console.log("err");
        });


    }
//更新问答列表
    $scope.updateQ = function(){

        var qData = {
            disId:parseInt($("#dis").val()),
            pageIndex:$scope.curr,
            pageSize:10
        }
        httpService.getList(qData,"/bbsManager/queryCommentInfoList").then(function successCallback(response) {
            $scope.interlocution = response.data.result;
            //如果没有回复隐藏模块
            if($scope.interlocution==null){
                $scope.listBool=false;
                return;
            }
            $scope.listBool=true;
            for(var i=0;i<$scope.interlocution.length;i++){
                if($scope.interlocution[i].CreateTime>0){
                    $scope.interlocution[i].CreateTime = timestampToTime($scope.interlocution[i].CreateTime);
                }
            }
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //设为答案
    $scope.setAnswer=function(AnsId,QueId){
        var data = {
            disId:parseInt($("#dis").val()),
            queId:QueId,
            ansId:AnsId
        }
        httpService.postList(data,"/bbsManager/addBestAnswer").then(function successCallback(response) {
            if(response.data.code == 1){
                layer.msg(response.data.msg);
                location.replace(location.href);
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
        //更新分页数据
        var qData = {
            disId:parseInt($("#dis").val()),
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(qData,"/bbsManager/queryCommentInfoList").then(function successCallback(response) {
            $scope.interlocution = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.curr=page;
    };
});

//相关推荐
app.controller('recommendController', function ($scope, $http,httpService) {
    var data = {
        pageSize:5,
        disId:$("#dis").val()
    }
    httpService.getList(data,"/bbsManager/queryBbsRecommendByDisId").then(function successCallback(response) {
        $scope.recommends = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.detail=function(disId,disType){
        if(disType==1){
            window.location.href=basePath + "/communifyDisDetail?disId="+disId
                +"&&disType="+disType;
        }else if (disType==2){
            window.location.href=basePath + "/communifyQusDetail?disId="+disId
                +"&&disType="+disType;
        }
    }


});
//社区热搜
app.controller('hotController', function ($scope, $http,httpService) {
    var data = {
        num:5
    }
    httpService.postList(data,"/knowledgeManage/updateTagInfoCounts").then(function successCallback(response) {
        $scope.hots=response.data.result;
        console.log($scope.hots);
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.hotSearch=function(name){
        window.location.href=basePath + "/commHotSearch?name="+name;
    }
});










