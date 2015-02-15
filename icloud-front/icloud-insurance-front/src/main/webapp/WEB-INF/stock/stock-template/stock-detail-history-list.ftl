<#import "/icloud/pageView.ftl" as pageView/>
<#macro sdList>
<div class="mainPageContent">
<div class="stockList-header">
    <span>${stock.stockName}</span>
    (
    <span>${(stock.stockAllCode)!''}</span>
    ) -
     <span>历史数据</span>
</div>
<div class="stockList">
    <div class="stockList-content new-portfolio" style="display: block;">
        <table cellspacing="0" cellpadding="0" class="portfolio">
            <thead>
                <tr>
                    <th class="sortable" data-key="symbol" style="min-width: 48px">
                        <s></s>股票代码
                    </th>
                     <th class="sortable" data-key="symbol" style="min-width: 48px">
                        <s></s>股票名称
                    </th>
                    <th class="sortable" data-key="name" style="min-width: 48px">
                        <s></s>日期
                    </th>
                    <th class="sortable" data-key="current" style="min-width: 36px">
                        <s></s>开盘价
                    </th>
                    <th class="sortable" data-key="change" style="min-width: 36px">
                        <s></s>收盘价
                    </th>
                    <th class="sortable desc" data-key="percent" style="min-width: 36px">
                        <s></s>最高价</th>
                    <th class="" data-key="today" style="min-width: 72px">
                        <s></s>最低价</th>
                    <th class="sortable" data-key="volume" style="min-width: 36px">
                        <s></s>成交量
                    </th>
                    <th class="sortable" data-key="amount" style="min-width: 36px">
                        <s></s>均价
                    </th>
                </tr>
            </thead>
            <tbody>
            <#if (pagination.data)??>
              <#list pagination.data as stockDateHistory>
              	<#assign a="stockDateHistory_index"/>
                <tr class="<#if stockDateHistory_index%2==0>even_stock_up<#else>odd_stock_up</#if>" code="${(stockDateHistory.stockCode)!''}">
                    <td class="nocolor">${(stockDateHistory.stockCode)!''}</td>
                    <td class="nocolor">${(stock.stockName)!''}</td>
                    <td class="nocolor">${(stockDateHistory.createTime)!''}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.openPrice}')}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.closePrice}')}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.highPrice}')}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.lowPrice}')}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.volume}')}</td>
                    <td class="nocolor">${getDigital('${stockDateHistory.adjPrice}')}</td>
                </tr>
			</#list>
			</#if>
            </tbody>
        </table>
    </div>
<@pageView.pagination param1='2' param2='${stock.stockCode}' param3='33'/>
</div>
</div>
</#macro>