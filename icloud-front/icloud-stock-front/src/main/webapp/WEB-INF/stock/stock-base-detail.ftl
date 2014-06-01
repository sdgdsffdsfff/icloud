<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/stock/stock-template/stock-detail-menus.ftl" as sdm/>
<#import "/stock/stock-template/stock-detail-main.ftl" as sdmain/>
<#import "/stock/stock-template/stock-detail-companyinfo.ftl" as sdc/>

<@imc.mainContainer current="行情" title="${(stockPageTitle)!defaultTitle}" keywords="${(stockPageKeywords)!''}" description="${(stockPageDescription)!''}" jsFiles=["icloud/stockcharts.js","common/echarts-plain-map.js"] cssFiles=["icloud/stock.css","icloud/stock_detail.css"]>
<@sdm.stockDetailMenus current="基本信息"/>
<@sdmain.stockDetailMain />
<@sdc.stockDetailCompanyInfo />
<script type="text/javascript">
$(function() {
	fillDateAllCharts(${stock.stockCode});
});
</script>
</@imc.mainContainer>
