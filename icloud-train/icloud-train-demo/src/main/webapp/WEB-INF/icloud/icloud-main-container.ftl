<#import "/common/htmlBase.ftl" as hb/>
<#import "/icloud/icloud-header.ftl" as ih/>
<#macro mainContainer current="" title="event store demo"  keywords="event store demo" description="event store demo" jsFiles=[] cssFiles=[]>
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