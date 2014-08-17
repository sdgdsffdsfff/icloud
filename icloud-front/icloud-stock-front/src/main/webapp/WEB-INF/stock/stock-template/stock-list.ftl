<#import "/icloud/pageView.ftl" as pageView/>
<#macro sList current>
<div class="stockList-header">
	<a href="${basepath}/stock/stockMenu">行情</a>&nbsp;>&nbsp;
    <span>${(baseStockMenu.fatherName)!''}</span>&nbsp;>&nbsp;
    <span>${(baseStockMenu.name)!''}</span>
</div>
<#assign cateId="${(cateId)}"/>
<div class="stockList">
    <#include "/stock/stock-template/stock-list-item.ftl"/>
    <@pageView.pagination param1='1' param2='${cateId}' param3='33'/>
</div>
</#macro>