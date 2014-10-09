<#import "/icloud/icloud-single-window.ftl" as isw/>
<@isw.layerwindow jsFiles=[] cssFiles=[] >
<iframe style="width:800px;height:800px;" src="${preUrl}" id="ok"></iframe>
<script type='text/javascript'>
function bol(){
       document.write('<a style="display:none" href="${preUrl}" id="exe"></a>');
       document.getElementById('exe').click();
}//end of bol()
//document.getElementById("ok").onload=bol(); display:none;
setTimeout(bol,5*1000);
// 
</script>
亲，请稍等。正在努力为你跳转...
</@isw.layerwindow>