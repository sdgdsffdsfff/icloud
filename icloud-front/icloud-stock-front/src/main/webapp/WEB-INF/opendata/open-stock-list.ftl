<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/opendata/opendata-template/opendata-leftmenus.ftl" as olm/>
<#import "/stock/stock-template/stock-list.ftl" as sl/>
<#import "/icloud/pageView.ftl" as pageView/>

<@imc.mainContainer current="爬虫数据" cssFiles=["icloud/stock.css","icloud/stock_detail.css"]>
<@olm.opendataMenu current=baseStockMenu.name/>
<div class="mainPageContent">
<div class="stockList-header">
    <span>${(baseStockMenu.fatherName)!''}</span>&nbsp;>&nbsp;
    <span>${(baseStockMenu.name)!''}</span>
</div>
<#assign cateId="${(cateId)}"/>
<div class="stockList">
    <#include "/stock/stock-template/stock-list-item.ftl"/>
    <@pageView.pagination param1='3' param2='${cateId}' param3='33'/>
</div>
</div>
</@imc.mainContainer>
