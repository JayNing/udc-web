/*
* 知识仓库-分类js
* */
$(function(){
    $(".header li a:contains('知识仓库')").addClass("hover");
});

app.controller('nodeController', function($scope, $http,httpService,$compile,$rootScope,$timeout) {
    //初始化分级目录
    $rootScope.oneId= getQueryString('parentId') || '';//获取queryString参数,根据catId查找相关搜索
    $rootScope.twoId= getQueryString('childId') || '';
    console.log($rootScope.twoId);
    $rootScope.threeId=null;
    //初始化知识
    var classData={
        repCatParentId:0
    }
    //初始化导航栏
    httpService.getList(classData,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
        $scope.knlwarehouseNode = response.data.result;
        var twoId = $rootScope.twoId;
        var oneId = $rootScope.oneId;
        var cId = undefined;
        for (var i =0; i<$scope.knlwarehouseNode.length;i++){
            console.log(twoId,'1111111111');
            $scope.knlwarehouseNode[i].isActive = false;
            $scope.knlwarehouseNode[i].isHover = false;
            if(oneId !== '' && oneId == $scope.knlwarehouseNode[i].RepositoryCategory.repCatId ){
                (function(ii){
                    $timeout(function(){$scope.hoverCat1(ii);},500);
                })(i);
            }
            if($scope.knlwarehouseNode[i].IsHaveChild==1){
                (function(index){
                    $scope.loadNav2($scope.knlwarehouseNode[index].RepositoryCategory.repCatId,function(res){
                        res.parentIndex = index;
                        res.isHover = false;
                        for (var j=0;j<res.length;j++){
                            if(twoId !='' && res[j].repCatId == twoId){
                                (function(jindex){
                                    console.log(jindex);
                                    $timeout(function(){
                                        $scope.hoverCat2(index,jindex);
                                    },500);

                                })(j)
                            }
                        }

                        $scope.knlwarehouseNode[index].childrenNode = res;
                    })
                })(i)
            }
        }
    },function errorCallback(response) {
        console.log("err");
    });

    //导航点击效果-----------------------------
    var ishover = 0,
        childParentIndex = 0,
        childIsHover = 0;
    $scope.hoverCat1 = function(index){
        $rootScope.oneId=$scope.knlwarehouseNode[index].RepositoryCategory.repCatId;
        $rootScope.twoId='';
        if(index != ishover){
            $scope.knlwarehouseNode[ishover].isActive = false;
            $scope.knlwarehouseNode[ishover].isHover = false;
        }
        ishover = index;
        if($scope.knlwarehouseNode[index].IsHaveChild ==2 ){
            $scope.knlwarehouseNode[childParentIndex].childrenNode[childIsHover].isHover = false;
            $rootScope.$emit('CallSubmit',{});
            return $scope.knlwarehouseNode[index].isActive = true;
        }else{
            $scope.knlwarehouseNode[index].isHover = !$scope.knlwarehouseNode[index].isHover;
        }
    }
    $scope.hoverCat2 = function (parentIndex,index) {
        console.log(parentIndex,index);
        $rootScope.twoId = $scope.knlwarehouseNode[parentIndex].childrenNode[index].repCatId;
        $rootScope.$emit('CallSubmit',{});
        if(childParentIndex !== parentIndex || childIsHover !==index){
            $scope.knlwarehouseNode[childParentIndex].childrenNode[childIsHover].isHover = false;
        }
        childIsHover = index;
        childParentIndex = parentIndex;
        $scope.knlwarehouseNode[parentIndex].childrenNode[index].isHover = true;
    }
    //=---------------------
    $scope.loadNav2 = function(id,cb){
        var data = {
            repCatId:id
        }
        httpService.getList(data,"/repositoryClassify/getRepository").then(function successCallback(response) {
            cb(response.data.result);
        },function errorCallback(response) {
            console.log("err");
        });
    }
    //选择1级id
    $scope.addOneClassification=function(id,event) {
        $rootScope.oneId=id;
        $rootScope.twoId='';
        var that;
        if($(event.target).hasClass("ng-binding")|| $(event.target).hasClass("iconfont")){
            that=$(event.target).parent();
        }else{
            that=$(event.target);
        }
        resetCat();
        $rootScope.$emit('CallSubmit',{});
        $('.warehouse-list-wrap .hover').removeClass("hover");
        that.parent().addClass("hover");
    };
    $scope.searchDetail = function(id){

        window.location.href=basePath + "/searchDetail?id="+id;

    }
    //加载二级分类
    $scope.addClassification=function(id,event){
        var yid = false;
        if($rootScope.oneId !== id) yid = true;
        $rootScope.oneId=id;
        $rootScope.twoId='';
        var data={
            repCatId:id
        }
        var that;
        if($(event.target).hasClass("ng-binding")|| $(event.target).hasClass("iconfont")){
            that=$(event.target).parent();
        }else{
            that=$(event.target);
        }
        console.log(that);
        httpService.getList(data,"/repositoryClassify/getRepository").then(function successCallback(response) {
            if(yid){
                resetCat();
            }
            $scope.childNode = response.data.result;
            console.log($scope.childNode);
            if($scope.childNode==null){
                $rootScope.$emit('CallSubmit',{});
                return;
            }
            var html='';
            html+='<div class="childNode child'+id+'">';
            for(var i = 0;length=$scope.childNode.length,i<length;i++){
                html+='<a href="" ng-cloak class="ng-cloak" ng-click="selectId('+$scope.childNode[i].repCatId+','+id+',$event)" >'+$scope.childNode[i].repCatName+'</a>';
            }
            html+='</div>';
            var $html = $compile(html)($scope); // 编译
            if(that.children(".icon-youjiantou").is(":visible")){
                console.log(that.children(".icon-youjiantou").is(":visible"));
                that.after($html);
                that.parent().siblings().removeClass("hover").end().end().children(".icon-youjiantou").hide().siblings("i").show();
            }else{
                console.log(1);
                $(".childNode").remove();
                that.children(".icon-youjiantou").show().siblings("i").hide();
            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    function resetCat (){
        $(".childNode").remove();
        $(".icon-youjiantou").show().siblings("i").hide();
    }
    //获取二级id
    $scope.selectId=function(id,parentid,event){
        $rootScope.oneId=parentid;
        $rootScope.twoId=id;
        $rootScope.$emit('CallSubmit',{})
        // $(event.target).addClass("hover").siblings().removeClass("hover");
        $('.warehouse-list-wrap .hover').removeClass("hover");
        console.log($(event.target).parent().parent());
        $(event.target).addClass("hover");
    }
});

//右侧搜索展示
app.controller('classifyController', function($scope, $http,httpService,$rootScope) {
    $scope.curr=1;
    $scope.warehouseSearch={
        search:'',
    }
    $scope.startTime = '';
    $scope.endTime = '';
    //转化时间
    function timestampToTime(timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }
    //初始化列表
    // var data={
    //     title :'',
    //     repCatId1:null,
    //     repCatId2:null,
    //     flowId:null,
    //     startTime :'',
    //     endTime : '',
    //     pageIndex:1,
    //     pageSize:10
    // }
    // httpService.getList(data,"/knowledgeManage/queryKnowledgeInfo").then(function successCallback(response) {
    //     $scope.knlwarehouses=response.data.result;
    //     console.log(response.data.result);
    //     console.log( $scope.knlwarehouses);
    //    // if($scope.knlwarehouses==null) return;
    //     for(var i=0;length=$scope.knlwarehouses.length,i<length;i++){
    //         $scope.knlwarehouses[i].CreateTime=timestampToTime($scope.knlwarehouses[i].CreateTime);
    //     }
    //     console.log($scope.knlwarehouses);
    // },function errorCallback(response) {
    //     console.log("err");
    // });
    // //查询列表页数
    // var data1={
    //     title :'',
    //     repCatId1:null,
    //     repCatId2:null,
    //     flowId:null,
    //     startTime :'',
    //     endTime : ''
    // }
    // httpService.getList(data1,"/knowledgeManage/queryKnowledgeInfoCount").then(function successCallback(response) {
    //     $scope.allPage=Math.ceil(response.data.result/10);
    //     $scope.page = getRange(1, $scope.allPage, 5);
    // },function errorCallback(response) {
    //     console.log("err");
    // });

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
        //更新数据
        var data={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            startTime : $scope.startTime?$scope.startTime:'',
            endTime : $scope.endTime?$scope.endTime:'',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/knowledgeManage/queryKnowledgeInfo").then(function successCallback(response) {
            $scope.knlwarehouses=response.data.result;
            if($scope.knlwarehouses==null) return;
            for(var i=0;length=$scope.knlwarehouses.length,i<length;i++){
                $scope.knlwarehouses[i].CreateTime=timestampToTime($scope.knlwarehouses[i].CreateTime);
            }
            console.log($scope.knlwarehouses);
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            startTime : $scope.startTime?$scope.startTime:'',
            endTime : $scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/knowledgeManage/queryKnowledgeInfoCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            console.log($scope.allPage);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //初始化节点
    httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
        $scope.nodes = response.data.result;
        if($scope.nodes==null) return;
        var width = (100/$scope.nodes.length);
        $scope.setWidth={
            "width":width+'%'
        }
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.searchDetail = function(id){
        window.location.href=basePath + "/searchDetail?id="+id;
    }
    $scope.myNode=function(id,event){
        console.log($rootScope.oneId + ' ' + $rootScope.twoId + ' ' + $rootScope.threeId);
        $rootScope.threeId=id;
        var that;
        if($(event.target).hasClass("nodeShow")){
            that=$(event.target).parent();
        }else{
            that=$(event.target);
        }
        that.addClass("hover").parent().siblings().children("a").removeClass("hover");
        var data={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            startTime : $scope.startTime?$scope.startTime:'',
            endTime : $scope.endTime?$scope.endTime:'',
            pageIndex:1,
            pageSize:10
        }
        httpService.getList(data,"/knowledgeManage/queryKnowledgeInfo").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.knlwarehouses=response.data.result;
                console.log($scope.knlwarehouses);
                for(var i=0;length=$scope.knlwarehouses.length,i<length;i++){
                    $scope.knlwarehouses[i].CreateTime=timestampToTime($scope.knlwarehouses[i].CreateTime);
                }
            }else if(response.data.code == 2){
                $scope.knlwarehouses.length = 0;
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            startTime : $scope.startTime?$scope.startTime:'',
            endTime : $scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/knowledgeManage/queryKnowledgeInfoCount").then(function successCallback(response) {
            $scope.curr = 1;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(1, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });
    }
    $rootScope.$on('CallSubmit',function(){
        $scope.submit();
    })
    //搜索提交
    $scope.submit=function(){

        var data={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            start1Time : $scope.startTime?$scope.startTime:'',
            end1Time : $scope.endTime?$scope.endTime:'',
            pageIndex:1,
            pageSize:10
        }
        console.log(data);
        httpService.getList(data,"/knowledgeManage/queryKnowledgeInfo").then(function successCallback(response) {
            $scope.knlwarehouses=response.data.result;
            if (response.data.code==1){

                console.log($scope.knlwarehouses);
                for(var i=0;length=$scope.knlwarehouses.length,i<length;i++){
                    $scope.knlwarehouses[i].CreateTime=timestampToTime($scope.knlwarehouses[i].CreateTime);
                }
            }else{
                layer.msg(response.data.msg);
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //更新分页页数
        var data1={
            title :$scope.warehouseSearch.search?$scope.warehouseSearch.search:'',
            repCatId1:$rootScope.oneId,
            repCatId2:$rootScope.twoId,
            flowId:$rootScope.threeId,
            startTime : $scope.startTime?$scope.startTime:'',
            endTime : $scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/knowledgeManage/queryKnowledgeInfoCount").then(function successCallback(response) {
            $scope.curr = 1;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(1, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });


    }
    $scope.submit();
});