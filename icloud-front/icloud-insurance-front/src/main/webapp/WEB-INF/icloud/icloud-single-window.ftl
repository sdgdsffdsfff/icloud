<#import "/common/singleHtmlBase.ftl" as hb/>
<#macro layerwindow title=""  keywords="" description="" jsFiles=[] cssFiles=[]>
<@hb.htmlBase title=title keywords=keywords description=description jsFiles=jsFiles cssFiles=cssFiles  emedObjects=[]>
<#nested/>
</@hb.htmlBase>
</#macro>