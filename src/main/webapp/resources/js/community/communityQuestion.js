/*
* 社区-提问js
* */
$(function(){
    $(".header li a:contains('社区')").addClass("hover");
});
app.controller('communityQuestionController', function($scope,$http,httpService,$timeout){
	$scope.share={
		question:'',
		arrticle:'',
		key:'',
		type:'0',
        knlwarehouseType:'0',
        gmaType:'0',
        businessType:'0'
	}
    //初始化文章类型下拉框
    httpService.getData("/essayType/getEssayTypeList").then(function successCallback(response) {
        $scope.cases = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    //初始化知识库分类下拉框
	var catData={
        repCatParentId:0
	}
    httpService.getList(catData,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
        $scope.classifications1 = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    //获取二级知识库分类下拉框
    $scope.selectChange=function(){
    	var data={
            repCatId:parseInt($scope.share.knlwarehouseType,10)>0?parseInt($scope.share.knlwarehouseType,10):''
		}
		console.log($scope.share.knlwarehouseType);
        httpService.getList(data,"/repositoryClassify/getRepository").then(function successCallback(response) {
            console.log(response.data);
           if(response.data.code==1){
               $scope.classifications2 = response.data.result;
           }else{
               $scope.classifications2=[];
               $scope.share.gmaType='0';
           }
        },function errorCallback(response) {
            console.log("err");
        });

	}
    //初始化业务流程下拉框
    httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
        $scope.business = response.data.result;
    },function errorCallback(response) {
        console.log("err");
    });
    //返回
    $scope.goBack=function(){
        window.history.back();
    }
    //提交
	$scope.submit=function(){
		var data={
            title:$scope.share.question,
            queDesc:$scope.share.arrticle,
            tagStrings:$scope.share.key,
            essayTypeId:$scope.share.type,
            repCatId1:$scope.share.knlwarehouseType,
            repCatId2:$scope.share.gmaType,
            flowId:$scope.share.businessType
		}
		if($scope.share.question==""){
			layer.msg("请输入你的问题");
			return;
		}
		if($scope.share.arrticle==""){
			layer.msg("请输入问题描述");
			return;
		}
		if($scope.share.key==""){
			layer.msg("请输入要提问的关键词");
			return;
		}
        if($scope.share.type==0){
            layer.msg("请选择问题类型");
            return;
        }
        if($scope.share.knlwarehouseType==0){
            layer.msg("请选择一级知识库分类");
            return;
        }

        // if($scope.share.gmaType==0){
        //     layer.msg("请选择二级知识库分类");
        //     return;
        // }
        if($scope.share.businessType==0){
            layer.msg("请选择业务流程分类");
            return;
        }

        httpService.postList(data,"/bbsManager/addQuestion").then(function successCallback(response) {
            layer.msg("提交成功!");
            $timeout(function(){
                window.location.href=basePath + "/topSkip/4"
            },1000);
        },function errorCallback(response) {
            console.log("err");
        });
	}
	
	
});
