<#import "/icloud/pageView.ftl" as pageView/>
<#macro sList current>
<div class="stockList-header">
	<a href="${basepath}/stock/stockMenu">行情</a>&nbsp;>&nbsp;
    <span>${(baseStockMenu.fatherName)!''}</span>&nbsp;>&nbsp;
    <span>${(baseStockMenu.name)!''}</span>
</div>
<#assign cateId="${(cateId)}"/>
<div class="stockList">
    <div class="stockList-content new-portfolio" style="display: block;">
        <table cellspacing="0" cellpadding="0" class="portfolio">
            <thead>
                <tr>
                    <th class="sortable" data-key="symbol" style="min-width: 48px">
                        <s></s>股票代码
                    </th>
                    <th class="sortable" data-key="name" style="min-width: 48px">
                        <s></s>股票名称
                    </th>
                    <th class="sortable" data-key="current" style="min-width: 36px">
                        <s></s>当前价
                    </th>
                    <th class="sortable" data-key="change" style="min-width: 36px">
                        <s></s>涨跌额
                    </th>
                    <th class="sortable desc" data-key="percent" style="min-width: 36px">
                        <s></s>涨跌幅</th>
                    <th class="" data-key="today" style="min-width: 72px">
                        <s></s>当日股价幅度</th>
                    <th class="" data-key="week52" style="min-width: 72px">
                        <s></s>52周股价幅度</th>
                    <th class="sortable" data-key="marketcapital" style="min-width: 24px">
                        <s></s>市值</th>
                    <th class="sortable" data-key="volume" style="min-width: 36px">
                        <s></s>成交量
                    </th>
                    <th class="sortable" data-key="amount" style="min-width: 36px">
                        <s></s>成交额
                    </th>
                    <th class="" data-key="hasexist" style="min-width: 24px">
                        <s></s>操作
                    </th>
                </tr>
            </thead>
            <tbody>
            <#if (pagination.data)??>
              <#list pagination.data as stock>
              	<#assign a="stock_index"/>
                <tr class="<#if stock_index%2==0>even_stock_up<#else>odd_stock_up</#if>" code="${(stock.stockCode)!''}">
                    <td>
                        <a target="_blank" href="${basepath}/stock/stockBaseDetail?stockCode=${(stock.stockCode)!''}&type=${(baseStockMenu.code)!''}">${(stock.stockName)!''}</a>
                    </td>
                    <td>
                        <a target="_blank" href="${basepath}/stock/stockBaseDetail?stockCode=${(stock.stockCode)!''}&type=${(baseStockMenu.code)!''}">${(stock.stockAllCode)!''}</a>
                    </td>
                    <td class="stock-color">${(stock.currentPrice)!''}</td>
                    <td class="${getStockColor('${(stock.chg)}')}">${(stock.chg)!''}</td>
                    <td class="${getStockColor('${(stock.chg)}')}">${(stock.percent * 100)!''}%</td>
                    <td class="${getStockColor('${(stock.chg)}')}">${(stock.low)!''} - ${(stock.high)!''}</td>
                    <td class="${getStockColor('${(stock.chg)}')}">${(stock.low52w)!''} - ${(stock.high52w)!''}</td>
                    <td class="nocolor">${getDigital('${stock.totalMoney}')}</td>
                    <td class="nocolor">${getDigital('${stock.volume}')}</td>
                    <td class="nocolor">${getDigital('${stock.amount}')}</td>
                    <td>
                        <a href="#" class="followStock" target="_blank" data-stockid="${(stock.stockCode)!''}" data-stockname="${(stock.stockName)!''}" data-stockcurrent="11.5">
                            <span></span>关注
                        </a>
                    </td>
                </tr>
			</#list>
			</#if>
            </tbody>
        </table>
    </div>
    <@pageView.pagination param1='1' param2='${cateId}' param3='33'/>
</div>
</#macro>