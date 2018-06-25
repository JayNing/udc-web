
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<@defaultLayout.htmlHead title="权限管理">
<script type="text/javascript">
    function test(){
        var va = $("#test1").val();
        alert(va);
    }
</script>
</@defaultLayout.htmlHead>

<@defaultLayout.htmlBody>
        哈哈哈<br>
    <input id="test1" type="text" name="name1" value="我的祖国"><br>
    <a href="#" onclick="test()">测试jquery</a>
</@defaultLayout.htmlBody>