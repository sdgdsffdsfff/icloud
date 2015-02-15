<#import "/common/htmlBase.ftl" as hb/>
<#macro layerwindow title="${defaultTitle}"  keywords="股票行情,交易,研究报告" description="股票股价,行情,新闻,财报,数据" jsFiles=[] cssFiles=[]>
<@hb.htmlBase title=title keywords=keywords description=description jsFiles=jsFiles cssFiles=cssFiles  emedObjects=[]>
<#nested/>
</@hb.htmlBase>
</#macro>