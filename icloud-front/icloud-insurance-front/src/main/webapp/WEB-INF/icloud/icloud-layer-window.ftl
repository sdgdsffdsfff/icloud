<#import "/common/htmlBase.ftl" as hb/>
<#macro layerwindow title="${defaultTitle}" keywords="${defaultKeywords}" description="${defaultDescription}" jsFiles=[] cssFiles=[]>
<@hb.htmlBase title=title keywords=keywords description=description jsFiles=jsFiles cssFiles=cssFiles  emedObjects=[]>
<#nested/>
</@hb.htmlBase>
</#macro>