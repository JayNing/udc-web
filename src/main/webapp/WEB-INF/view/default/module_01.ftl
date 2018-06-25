
<#import "/default/defaultLayout.ftl" as defaultLayout/>

<#macro modelHead title charset="utf-8" lang="zh-CN">

<@defaultLayout.htmlHead title="${title}">
<#--<link rel="stylesheet" href="${basePath}/resources/css/test/test.css">-->
    <#nested>
</@defaultLayout.htmlHead>


</#macro>


<#macro modelBody>
<@defaultLayout.htmlBody>

<div style="width: 100px; height: 600px; float: left; background-color: aquamarine">

</div>

<div content="content">
    <#nested>
</div>

</@defaultLayout.htmlBody>
</#macro>


<#macro js_scripts>
    <@defaultLayout.js_scripts>
        <script>
            alert("module");
        </script>
    </@defaultLayout.js_scripts>
    <#nested>
</#macro>

