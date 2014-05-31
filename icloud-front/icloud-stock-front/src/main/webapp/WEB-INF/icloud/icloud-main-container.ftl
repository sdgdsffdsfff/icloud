<#import "/common/htmlBase.ftl" as hb/>
<#import "/icloud/icloud-header.ftl" as ih/>
<#macro mainContainer current="" title="${defaultTitle}"  keywords="股票行情,交易,研究报告" description="股票股价,行情,新闻,财报,数据" jsFiles=[] cssFiles=[]>
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