<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/stock/stock-template/stock-detail-menus.ftl" as sdm/>
<#import "/stock/stock-template/stock-detail-history-list.ftl" as sdList/>

<@imc.mainContainer current="行情" title="${(stockPageTitle)!defaultTitle}" keywords="${(stockPageKeywords)!''}" description="${(stockPageDescription)!''}" jsFiles=["icloud/exampledata.js","common/echarts-plain-map.js"] cssFiles=["icloud/stock.css","icloud/stock_detail.css"]>
<@sdm.stockDetailMenus current="历史数据"/>
<@sdList.sdList />
</@imc.mainContainer>
