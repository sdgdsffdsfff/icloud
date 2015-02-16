<#import "/common/htmlBase.ftl" as hb/>
<#import "/icloud/icloud-header.ftl" as ih/>
<#macro mainContainer current="保险" title="${defaultTitle}" keywords="${defaultKeywords}" description="${defaultDescription}" jsFiles=[] cssFiles=[]>
<@hb.htmlBase title=title keywords=keywords description=description jsFiles=jsFiles cssFiles=cssFiles  emedObjects=[]>
<div class="main-wrapper">
<@ih.header current = current/>
<section class="main-body clearfix">
	<#nested/>
</section>
<#include "/icloud/icloud-footer.ftl"/>
</div>
</@hb.htmlBase>
</#macro>