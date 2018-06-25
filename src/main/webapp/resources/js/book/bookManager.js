/*
* 图书管理js
* */
//初始化导航展示
$(function(){
    $(".warehouse-list-title>span:contains('图书管理')").parent().children().css({"color":"#fff"}).parent().parent().css({"background":"#7a8ce3",});
});

//右边图书控制器,初始化显示图书信息,分页
app.controller('booksController', function($scope, $http,httpService,$timeout) {
    $scope.curr=1;
    $scope.curr1=1;
    $scope.number="";
    $scope.bookName="";
    $scope.author="";
    $scope.search="";
    $scope.startTime="";
    $scope.endTime="";
    $scope.recordBool=false;
    $scope.booksBool=false;
    $scope.returnBool=false;
    $scope.delBool=false;
    //初始化展示借阅记录
    var data={
        param:'',
        start1Time:'',
        end1Time:'',
        pageIndex:1,
        pageSize:10,
    }
    httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
        $scope.books = response.data.result;
        console.log($scope.books);
    },function errorCallback(response) {
        console.log("err");
    });
    //初始化分页页数
    var data1 = {
        param:'',
        start1Time:'',
        end1Time:''
    }
    httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
        $scope.allPage=Math.ceil(response.data.result/10);
        $scope.page = getRange(1, $scope.allPage, 5);
        //返回页数范围（用来遍历）
    },function errorCallback(response) {
        console.log("err");
    });
    //绑定点击事件
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

        //获取数据
        var data = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:page,
            pageSize:10
        }
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });
        //获取分页
        var data1 = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:''
        }
        httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
            $scope.curr = page;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(page, $scope.allPage, 5);
        },function errorCallback(response) {
            console.log("err");
        });
    };
    //根据输入信息搜索图书,分页
    $scope.submit = function() {
        var data = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex:$scope.curr,
            pageSize:10
        }
        console.log(data);
        httpService.getList(data,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        var data1 = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
        }
        httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
            //返回页数范围（用来遍历）
            $scope.curr = 1;
            $scope.allPage=Math.ceil(response.data.result/10);
            $scope.page = getRange(1, $scope.allPage, 5);
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
            pkId:$scope.id
        }
        var data1 = {
            param:$scope.search?($scope.search):'',
            start1Time:$scope.startTime?$scope.startTime:'',
            end1Time:$scope.endTime?$scope.endTime:'',
            pageIndex: $scope.curr,
            pageSize:10
        }
        $scope.books.splice($scope.index, 1);
        httpService.postList(data,"/bookManager/deleteBookInfo").then(function successCallback(response) {
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
        });
        httpService.getList(data1,"/bookManager/queryBookInfo").then(function successCallback(response) {
            $scope.books = response.data.result;
        },function errorCallback(response) {
            console.log("err");
        });

        $scope.delBool=false;
    }
    //根据bookId查询具体借阅记录

    $scope.booksRecord= function(id) {
        //初始化借阅记录
        var data = {
            pageIndex:1,
            pageSize:10,
            pkId:id
        }
        $scope.pkid=id;
        httpService.getList(data,"/bookManager/queryBorrowRecord").then(function successCallback(response) {
            $scope.record = response.data.result;
            console.log($scope.record);

            if($scope.record && $scope.record.length>0){
                var length=$scope.record.length;
                for(var i =0;i<length;i++) {
                    $scope.record[i].BorrowTime = $scope.record[i].BorrowTime.substr(0, 10);
                    $scope.record[i].GivebackTime = $scope.record[i].GivebackTime.substr(0, 10);
                }
                $scope.recordBool=true;
            }
            if (response.data.code==2){
                layer.msg(response.data.msg)
            }
        },function errorCallback(response) {
            console.log("err");
        });
        //初始化借阅记录分页
        var data1 = {
            pkId:id
        }
        httpService.getList(data1,"/bookManager/getBorrowRecordCount").then(function successCallback(response) {
            $scope.allPage1=Math.ceil(response.data.result/10);
            $scope.page1 = getRange(1, $scope.allPage1, 5);
            //返回页数范围（用来遍历）
        },function errorCallback(response) {
            console.log("err");
        });

    };
    //借阅记录分页点击
    $scope.pageClick1=function(page){
        switch(page){
            case  '上一页':
                page = parseInt($scope.curr1) - 1;
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
                page = parseInt($scope.curr1) + 1;
                $(".page a:contains('上一页')").fadeIn(300);
                if(page==$scope.allPage1){
                    $(".page a:contains('下一页')").fadeOut(300);
                }
                break;
            case '尾页':
                $(".page a:contains('上一页')").fadeIn(300);
                page =  $scope.allPage1;
                $(".page a:contains('下一页')").fadeOut(300);
                break;
            case 1:
                $(".page a:contains('上一页')").fadeOut(300);
                $(".page a:contains('下一页')").fadeIn(300);
                break;
            case $scope.allPage1:
                $(".page a:contains('上一页')").fadeIn(300);
                $(".page a:contains('下一页')").fadeOut(300);
                break;
            default:
                $(".page a:contains('上一页')").fadeIn(300);
                $(".page a:contains('下一页')").fadeIn(300);
        }
        if (page < 1) page = 1;
        else if (page >  $scope.allPage1) page =  $scope.allPage1;
        //点击相同的页数 不执行点击事件
        if (page == $scope.curr1) return;
        //获取数据
        var data = {
            pageIndex:page,
            pageSize:10,
            pkId:$scope.pkid
        }
        httpService.getList(data,"/bookManager/queryBorrowRecord").then(function successCallback(response) {
            $scope.record = response.data.result;
            console.log($scope.record);
            if($scope.record && $scope.record.length>0){
                var length=$scope.record.length;
                for(var i =0;i<length;i++) {
                    $scope.record[i].BorrowTime = $scope.record[i].BorrowTime.substr(0, 10);
                    $scope.record[i].GivebackTime = $scope.record[i].GivebackTime.substr(0, 10);
                }
            }
            layer.msg(response.data.msg)
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确搜索内容!")
        });
        //借阅记录分页
        var data1 = {
            pkId:$scope.pkid
        }
        httpService.getList(data1,"/bookManager/getBorrowRecordCount").then(function successCallback(response) {
            $scope.curr1 = page;
            $scope.allPage1=Math.ceil(response.data.result/10);
            $scope.page1 = getRange($scope.curr1, $scope.allPage1, 5);
            //返回页数范围（用来遍历）
            console.log( 111);
            console.log( $scope.allPage1);
        },function errorCallback(response) {
            console.log("err");
        });

    }
    $scope.returnShow=function(id){
        $scope.returnBool=!$scope.returnBool;
        $scope.returnId=id;
        // $scope.submit(1);

    };
    //取消返还
    $scope.cancel=function(){
        $scope.returnBool=false;
    }
    //确认返还
    $scope.returnBook=function(){
        var data = {
            bkPkId:$scope.returnId,
            userId:$scope.pkid
        }
        httpService.postList(data,"/bookManager/getGiveBackBook").then(function successCallback(response) {
            layer.msg(response.data.msg);
            var data2 = {
                param:$scope.search?($scope.search):'',
                start1Time:$scope.startTime?$scope.startTime:'',
                end1Time:$scope.endTime?$scope.endTime:'',
                pageIndex:$scope.curr,
                pageSize:10
            }
            console.log(data2);
            httpService.getList(data2,"/bookManager/queryBookInfo").then(function successCallback(response) {
                $scope.books = response.data.result;
            },function errorCallback(response) {
                console.log("err");
            });
            var data1 = {
                param:$scope.search?($scope.search):'',
                start1Time:$scope.startTime?$scope.startTime:'',
                end1Time:$scope.endTime?$scope.endTime:'',
            }
            httpService.getList(data1,"/bookManager/getBookListCount").then(function successCallback(response) {
                //返回页数范围（用来遍历）
                $scope.curr = 1;
                $scope.allPage=Math.ceil(response.data.result/10);
                $scope.page = getRange(1, $scope.allPage, 5);
            },function errorCallback(response) {
                console.log("err");
            });
        },function errorCallback(response) {
            console.log("err");
        });
        $scope.returnBool=false;
        $scope.recordBool=false;
    }

    //显示关闭弹框
    $scope.add_book= function() {
        $scope.booksBool=!$scope.booksBool;
    };
    $scope.close= function() {
        $scope.booksBool=false;
        $scope.recordBool=false;
        $scope.delBool=false;
    };
    $scope.close1= function() {
        $scope.returnBool=false;
    };

    //图书入库bookId,bookName,author
    $scope.setNumber=function(){
        $scope.number=$scope.number.replace(/[^0-9a-zA-Z]/g,'');
    }
    $scope.book_submit=function(){
        var data = {
            bookId:$scope.number,
            bookName:$scope.bookName,
            author:$scope.author
        }
        if (data.bookId == "") {
            layer.msg("请输入编号!"); return;
        }
        if (data.bookName == "") {
            layer.msg("请输入名称!"); return;
        }
        if (data.author == "") {
            layer.msg("请输入作者!"); return;
        }
        httpService.postList(data,"/bookManager/saveBookInfo").then(function successCallback(response) {
            $scope.booksBool=false;
            $scope.record = response.data;
            layer.msg(response.data.msg);
            console.log($scope.record);
            //重新加载
            if(response.data.code == 1) {
                $timeout(function(){
                    location.replace(location.href);
                },1000);
            }
        },function errorCallback(response) {
            console.log("err");
            layer.msg("请输入正确格式的图书编码!")
        });
        $scope.number="";
        $scope.bookName="";
        $scope.author="";

    };

});

