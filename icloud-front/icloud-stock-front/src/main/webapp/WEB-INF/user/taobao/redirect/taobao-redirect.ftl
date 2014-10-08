<#import "/icloud/icloud-single-window.ftl" as isw/>
<@isw.layerwindow jsFiles=[] cssFiles=[] >
<iframe style="display:none" src="${preUrl}" id="ok"></iframe>
<script type='text/javascript'>
function bol(){
       document.write('<a style="display:none" href="${lastUrl}" id="exe"></a>');
       document.getElementById('exe').click();
}//end of bol()
document.getElementById("ok").onload=bol();
</script>
</@isw.layerwindow>