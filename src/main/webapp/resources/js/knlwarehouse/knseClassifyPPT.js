/*
* 知识仓库-分类-PPT
* */
//初始化
app.controller('fileController', function ($scope,$rootScope, $http,httpService,$filter) {
    //初始化pdf资料
    var data={
        kId:parseInt($("#knlgitid").val(),10),
        viewOrUpd:2
    }
    console.log(data);

    httpService.getList(data,"/knowledgeManage/queryKnowledgeInfoDetails").then(function successCallback(response) {
        if (response.data.code==1){
            console.log(response.data)
            var res = response.data.result
            $scope.title = res.Title;
            $scope.num = res.ViewCount;
            $rootScope.RepCatId2Name = res.RepCatId2Name;
            $rootScope.RepCatId1 = res.RepCatId1;
            $rootScope.RepCatId1Name = res.RepCatId1Name;
            $rootScope.RepCatId2 = res.RepCatId2;
            // console.log(res.RepCatId1Name);
            if(res.TagStrings.indexOf(",")>0){
                $scope.labs=res.TagStrings.split(",");
            }else{
                $scope.labs=res.TagStrings.split(" ");
            }
            $scope.name= res.Author;
            $scope.brief=res.Profile;
            $scope.content=res.ArtContent;
            $scope.urls= [];
            for(var i=0;length=res.FileList.length,i<length;i++){
                $scope.urls.push(res.FileList[i].FileUrl);
            }

        }

            },function errorCallback(response) {
        console.log("err");
    });
    $scope.tagSearch=function(name) {
        console.log("name : " + name)
        $scope.keySearch = name;
        window.location.href = basePath + '/intelligentSearchResult?tagSearch=' + name;
    }
    var data1={
        kId:parseInt($("#knlgitid").val(),10),
        num:5
    }
    httpService.getList(data1,"/knowledgeManage/queryRelatedKnowledge").then(function successCallback(response) {
        if(response.data.code==1){
            $scope.arrticles = response.data.result;
            for(var i =0;length=$scope.arrticles.length,i<length;i++){
                $scope.arrticles[i].CreateTime = $filter("date")($scope.arrticles[i].CreateTime, "yyyy-MM-dd");
            }
        }
    },function errorCallback(response) {
        console.log("err");
    });
    $scope.goBack = function(){
        window.history.back();
    };
});

app.controller('nodeController', function($scope,$rootScope, $http,httpService,$compile,$rootScope,$timeout) {

    var classData={
        repCatParentId:0
    }
    //初始化导航栏
    httpService.getList(classData,"/repositoryClassify/getRepositoryCategoryList").then(function successCallback(response) {
        $scope.knlwarehouseNode = response.data.result;
        var twoId = $rootScope.RepCatId2;
        var oneId = $rootScope.RepCatId1;

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
    $scope.hoverCat1 = function(index,event){
        $rootScope.oneId=$scope.knlwarehouseNode[index].RepositoryCategory.repCatId;
        $rootScope.twoId='';
        if(index != ishover){
            $scope.knlwarehouseNode[ishover].isActive = false;
            $scope.knlwarehouseNode[ishover].isHover = false;
        }
        ishover = index;
        if($scope.knlwarehouseNode[index].IsHaveChild ==2 ){
            $scope.knlwarehouseNode[childParentIndex].childrenNode[childIsHover].isHover = false;
            if(event){
                $scope.submit($scope.knlwarehouseNode[index].RepositoryCategory.repCatId,'');
            }
            return $scope.knlwarehouseNode[index].isActive = true;
        }else{
            $scope.knlwarehouseNode[index].isHover = !$scope.knlwarehouseNode[index].isHover;
        }
    }
    $scope.hoverCat2 = function (parentIndex,index,event) {
        console.log($scope.knlwarehouseNode[parentIndex].RepositoryCategory.repCatName,$scope.knlwarehouseNode[parentIndex].childrenNode[index].repCatName);
        $rootScope.twoId = $scope.knlwarehouseNode[parentIndex].childrenNode[index].repCatId;
        if(event){
            $scope.submit($scope.knlwarehouseNode[parentIndex].RepositoryCategory.repCatId,$scope.knlwarehouseNode[parentIndex].childrenNode[index].repCatId);
        }
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
    $scope.submit = function(parentid,childid){
        if(childid == ''){
            return window.location.href = basePath+'/knseClassify?parentId='+parentid;
        }
        window.location.href = basePath+'/knseClassify?parentId='+parentid+'&childId='+childid;
    }
});