/*
* 社区-分享js
* */
$(function(){
    $(".header li a:contains('社区')").addClass("hover");
});
// function myeditor(id){
//     //初始化编辑器
//     UE.getEditor(id, {
//         // toolbars: [['source','bold','italic','underline','fontsize','forecolor','backcolor','pasteplain',
//         //     'insertorderedlist','cleardoc','link','simpleupload','emotion','map','inserttable','insertimage','lineheight'
//         //     //'insertimage',//多图上传
//         // ]],
//     });
// }
// var ue = UE.getEditor('editor',{
//     scaleEnabled:true
// });
// myeditor('edit');
var E = window.wangEditor
var editor = new E('#edit')
///communal/saveHeadImage
editor.customConfig.uploadImgServer = basePath +'/communal/richTextImageSave';
// 或者 var editor = new E( document.getElementById('editor') )
editor.create()
app.controller('commumityShareController', function($scope,$http,httpService, $timeout){
    $scope.share={
        question:'',
        arrticle:'',
        key:'',
        type:'0',
        knlwarehouseType:'0',
        gmaType:'0',
        businessType:'0'
    }

    httpService.getData("/communal/initUserInfo").then(function successCallback(response) {
        $scope.departmentName = response.data.result[0].departmentName;
        $scope.userName = response.data.result[0].userName;
        console.log(response.data.result);
    },function errorCallback(response) {
        console.log("err");
    });
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
        $scope.share.arrticle = editor.txt.html();
        var data={
            title:$scope.share.question,
            artContent:$scope.share.arrticle,
            tagStrings:$scope.share.key,
            essayTypeId:$scope.share.type,
            repCatId1:$scope.share.knlwarehouseType,
            repCatId2:$scope.share.gmaType,
            flowId:$scope.share.businessType
        }
        if($scope.share.question==""){
            layer.msg("请输入你的贴文标题");
            return;
        }
        if($scope.share.arrticle==""){
            layer.msg("请输入贴文内容");
            return;
        }
        if($scope.share.key==""){
            layer.msg("请输入贴文的关键词");
            return;
        }

        if($scope.share.type==0){
            layer.msg("请选择贴文的类型");
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
            layer.msg("请选择知识库业务流程分类");
            return;
        }

        httpService.postList(data,"/bbsManager/addEssayInfo").then(function successCallback(response) {
            layer.msg("提交成功!");
            $timeout(function(){
                window.location.href=basePath + "/topSkip/4"
            },1000);
        },function errorCallback(response) {
            console.log("err");
        });
    }


});
