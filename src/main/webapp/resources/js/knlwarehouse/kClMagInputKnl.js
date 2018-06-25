/*
* 知识仓库管理-录入知识js
* */
//文件上传指令

//初始化选定左侧目录栏
$(function(){
    $(".warehouse-list-title>span:contains('知识仓库')").next().hide().next().show().css("color","rgb(122, 140, 227)").parent().next().show().children("a:contains('录入知识-文章')").css({"background":"#7a8ce3","color":"#fff"});
});
var E = window.wangEditor;

var editor = new E('#edit');
///communal/saveHeadImage
editor.customConfig.uploadImgServer = basePath +'/communal/richTextImageSave';
editor.customConfig.debug = 1;
// 或者 var editor = new E( document.getElementById('editor') )
console.log(editor);
editor.create();
//右边控制器
app.controller('knowledgeController', function($scope,$rootScope, $http,httpService,$timeout) {
    $scope.hasCat2 = true;
    $scope.list=[];
    $scope.warehouse={
        title:'',
        author:'',
        summary:'',
        articleBool:"1",
        article:'',
        key:'',
        articleType:'0',
        processType:'0',
        nodeType:'0'

    }
    //初始化知识库分类下拉框
    var catData={
        repCatParentId:0
    }
    httpService.getList(catData,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.articleTypes = response.data.result;
        }
    },function errorCallback(response) {
        console.log("err");
    });
    //获取二级知识库分类下拉框
    $scope.selectChange=function(){
        var data={
            repCatId:$scope.warehouse.articleType
        }
        httpService.getList(data,"/repositoryClassify/getRepository").then(function successCallback(response) {
            if (response.data.code==1){
                $scope.processTypes = response.data.result;
            }else if(response.data.code == 2){
                $scope.hasCat2 = false;
            }
        },function errorCallback(response) {
            console.log("err");
        });

    }
    //初始化节点下拉框
    httpService.getData("/repositoryCategoryFlow/getRepositoryCategoryFlowList").then(function successCallback(response) {
        if (response.data.code==1){
            $scope.nodeTypes = response.data.result;
        }
    },function errorCallback(response) {
        console.log("err");
    });

    $scope.fileAdd = function () {
        console.log($scope.filesArr);
        $scope.filesArr.push('');
        $scope.filesNameArr.push('');
    }
    $scope.fileRemove=function(index){
        $scope.filesArr.splice(index,1);
        $scope.filesNameArr.splice(index,1);
    }

    $scope.filesArr = [''];
    $scope.filesNameArr = [''];
    $scope.fileChange=function(el){
        //提交文件地址
        var files = el.files[0];

        var index = ($(el).attr('data-index'));
        // console.log(index);
        // var files = $scope.filesArr[index];
        console.log(files);
        var file = new FormData();
        file.append('file', files);
        $http({
            method: 'POST',
            url: basePath + '/communal/saveFile',
            data:file,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).then(function successCallback(response) {
            //返回值进行校验
            if (response.data.code==1){
                // $scope.url= response.data.result.FileUrl;
                $scope.list.push(response.data.result.FileUrl);
                $scope.filesArr[index] = response.data.result.FileUrl;
                $scope.filesNameArr[index] = files.name;
            }
            layer.msg(response.data.msg)
        }, function errorCallback(response) {

        });
    }
    //判断添加或编辑
    function judge() {
        if ($("#knlgitid").val() == "") {

            $scope.editBool = false;
            $scope.addBool = true;
        } else {
            $scope.editBool = true;
            $scope.addBool = false;
            var data = {
                viewOrUpd: 1,
                kId: parseInt($("#knlgitid").val(), 10)
            };
            httpService.getList(data, "/knowledgeManage/queryKnowledgeInfoDetails").then(function successCallback(response) {
                //待删除
                console.log(response.data)
                $scope.warehouse.title = response.data.result.Title;
                $scope.warehouse.author = response.data.result.Author;
                $scope.warehouse.summary = response.data.result.Profile;
                $scope.warehouse.articleType = response.data.result.RepCatId1 + '';

                for(var i=0;length=response.data.result.FileList.length,i<length;i++){
                    $scope.list.push(response.data.result.FileList[i].FileUrl);
                }
                var data = {
                    repCatId: $scope.warehouse.articleType
                }
                httpService.getList(data, "/repositoryClassify/getRepository").then(function successCallback(response) {
                    $scope.processTypes = response.data.result;
                    console.log($scope.processTypes);
                }, function errorCallback(response) {
                    console.log("err");
                });
                $scope.warehouse.processType = response.data.result.RepCatId2 + '';
                // console.log($scope.warehouse.processType);
                $scope.warehouse.nodeType = response.data.result.FlowId + '';
                $scope.warehouse.article = response.data.result.ArtContent;
                $scope.ArtId = response.data.result.ArtId;
                if (response.data.result.ArtContent !== null && response.data.result.ArtContent) {
                    $scope.warehouse.articleBool = "1";
                    editor.txt.html(response.data.result.ArtContent);
                } else {
                    $scope.warehouse.articleBool = "2";
                }

                $scope.warehouse.key = response.data.result.TagStrings;
                $scope.fileName = response.data.result.FileList[0].FileName;
                for(var i=0,length=$scope.list.length;i<length;i++){
                    $scope.filesNameArr[i] = $scope.list[i];
                    $scope.filesArr[i] = $scope.list[i];
                }

            }, function errorCallback(response) {
                console.log("err");
            });
        }
    }
    judge();


    //数据提交
    $scope.warehouseSubmit = function(){
        //获取数据
        $scope.list = clear_arr_trim($scope.filesArr);
        // console.log()
        clear_arr_trim($scope.filesNameArr);
        if(!$scope.filesArr[0]){
            $scope.filesArr[0] = '';
            $scope.filesNameArr[0] = '';
        }
        console.log($scope.list);
        $scope.warehouse.article = editor.txt.html();
        if ($("#knlgitid").val() == "") {
            var data={
                title : $scope.warehouse.title,
                author : $scope.warehouse.author,
                profile : $scope.warehouse.summary,
                knlgType :parseInt($scope.warehouse.articleBool,10),
                artContent : $scope.warehouse.article,
                tagStrings : $scope.warehouse.key,
                fileList:$scope.list,
                repCatId1:parseInt($scope.warehouse.articleType,10),
                repCatId2:parseInt($scope.warehouse.processType,10) || 0,
                flowId:parseInt($scope.warehouse.nodeType,10),
                bbsFlag:1
            }
        }else{
            var data={
                kId:parseInt($("#knlgitid").val(),10),
                title : $scope.warehouse.title,
                author : $scope.warehouse.author,
                profile : $scope.warehouse.summary,
                knlgType :parseInt($scope.warehouse.articleBool,10),
                artContent : $scope.warehouse.article || '',
                tagStrings : $scope.warehouse.key,
                fileList:$scope.list,
                repCatId1:parseInt($scope.warehouse.articleType,10),
                repCatId2:parseInt($scope.warehouse.processType,10) || 0,
                flowId:parseInt($scope.warehouse.nodeType,10)
            }

        }
        console.log("demodeddddddddd");
        console.log(data);
        if(!$scope.warehouse.article || $scope.warehouse.article == ''){
            console.log(2);
            data.knlgType = 2;
        }else{
            console.log(1);
            data.knlgType = 1;
        }
        console.log();
        if($scope.warehouse.title==""){
            layer.msg("请输入标题!");
            return;
        }
        if($scope.warehouse.summary==""){
            layer.msg("请输入简介!");
            return;
        }
        //&&$scope.warehouse.article.list==undefined
       /* if($scope.warehouse.article==""){
            layer.msg("文章内容或附件!");
            return;
        }*/

        //知识仓库一级分类
        if($scope.warehouse.articleType=="0"){
            layer.msg("请选择知识仓库一级分类!");
            return;
        }
        //知识仓库二级分类
        // if($scope.warehouse.processType=="0" && !$scope.hasCat2){
        //     layer.msg("请选择知识仓库二级分类!");
        //     return;
        // }
        //流程节点分类
        if($scope.warehouse.nodeType=="0"){
            layer.msg("请选择流程分类!");
            return;
        }
        //关键字判断提示
        if($scope.warehouse.key==""){
            layer.msg("请输入关键字!");
            return;
        }
       //&scope.warehouse.article==""
        if(!filterHTMLTag(data.artContent) && data.fileList.length<=1 && !data.fileList[0]){
           // console.log("")
                layer.msg("请输入知识内容或附件!");
            return;
        }



        //对选择框的输入进行判定,NaN判别
        /*if(isNaN(parseInt($scope.warehouse.processType,10))){
            layer.msg("请选择流程分类");
            return;
        }*/
        //测试,待删除
        if ($("#knlgitid").val() == "") {
            httpService.postList(data, "/knowledgeManage/addKnowledge").then(function successCallback(response) {
                layer.msg(response.data.msg);
                $timeout(function(){
                    window.location.href=basePath + "/knlgBank/1"
                },1000);
            }, function errorCallback(response) {
                console.log("err");
            });
        }else{
            console.log(data);
            httpService.postList(data, "/knowledgeManage/updateKnowledge").then(function successCallback(response) {
                layer.msg(response.data.msg);
                $timeout(function(){
                    window.location.href=basePath + "/knlgBank/1"
                },1000);
            }, function errorCallback(response) {
                console.log("err");
            });
        }
    }

});
function clear_arr_trim(array) {
    for(var i = 0 ;i<array.length;i++)
    {
        if(array[i] == "" || typeof(array[i]) == "undefined")
        {
            array.splice(i,1);
            i= i-1;
        }
    }
    return array;
}