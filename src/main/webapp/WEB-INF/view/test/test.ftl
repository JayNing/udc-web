
<#import "/default/module_01.ftl" as module_01/>

<@module_01.modelHead title="FreeMarker测试 ">
<link rel="stylesheet" href="${basePath}/resources/css/test/test.css">
</@module_01.modelHead>

<@module_01.modelBody>
        哈哈哈<br>
    <input id="test1" type="text" name="name1" value="我的祖国"><br>
    <a href="#" onclick="test()">测试jquery</a>


    <form method="post" enctype="multipart/form-data" action="/test/upload">

    上传文件: <input type="file" name="file">
    <br>
    应用类型:
    <label>
        <input type="text" name="appType" value="1">
    </label>

    <input type="submit" value="提交">
</form>

</@module_01.modelBody>

<@module_01.js_scripts>
<script type="text/javascript">
    function test(){
        var va = $("#test1").val();
        alert(va);
    }
</script>
</@module_01.js_scripts>
