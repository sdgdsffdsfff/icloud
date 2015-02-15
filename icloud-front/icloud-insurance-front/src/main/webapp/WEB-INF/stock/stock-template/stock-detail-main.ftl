<#import "/stock/stock-template/stock-charts.ftl" as sdc/>
<#macro stockDetailMain>
<div class="mainStockDetailContent">
    <div class="wrapper">
        <div class="stockTitle">
            <div class="topName">
                <span class="stockName">
                    <strong title="" class="stockName">${stock.stockName}(${stock.stockAllCode})</strong>
                </span>
                <span class="stockOp" style="display: inline;">
                    <a href="/performance" class="stockTrade">关注</a>
                </span>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="stockQuote">
            <div id="currentQuote">
                <div class="currentInfo">
                    <strong class="${getStockColor('${(stock.chg)}')}" data-current="${stock.currentPrice}">￥${stock.currentPrice}</strong>
                    <span class="${getStockColor('${(stock.chg)}')}">
                        <span>${stock.chg}</span>
                        <span class="quote-percentage">&nbsp;&nbsp;(${stock.percent})</span>
                    </span>
                </div>
                <div class="stockInfo">
                    <span id="timeInfo">${stock.updateTime}&nbsp;&nbsp;</span>
                    <span>实时行情</span>
                </div>
            </div>
            <table class="topTable">
                <tbody>
                    <tr>
                        <td>今开：
                            <span>${stock.open}</span>
                        </td>
                        <td>最高：
                            <span id="quote-high">${stock.high}</span>
                        </td>
                        <td>52周最高：
                            <span>${stock.high52w}</span>
                        </td>
                        <td title="当日成交量，单位：股">成交量：
                            <span id="quote-volume">${getDigital('${stock.volume}')}</span>
                        </td>
                    </tr>
                    <tr class="seperateTop">
                        <td>昨收：
                            <span>${stock.lastClose}</span>
                        </td>
                        <td>最低：
                            <span id="quote-low">${stock.low}</span>
                        </td>
                        <td>52周最低：
                            <span>${stock.low52w}</span>
                        </td>
                        <td>成交额：
                            <span>${getDigital('${stock.amount}')}</span>
                        </td>
                    </tr>
                    <tr class="seperateBottom">
                        <td>总市值：
                            <span>${getDigital('${stock.totalMoney}')}</span>
                        </td>
                        <td>总股本：
                            <span>${getDigital('${stock.totalStock}')}</span>
                        </td>
                        <td>流通股本：
                            <span>${getDigital('${stock.flowStock}')}</span>
                        </td>
                        <td>市销率：
                            <span>${stock.volume/stock.flowStock*100}</span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <@sdc.stockChart chartName="日曲线" chartID="date-charts" />
    </div>
</div>
</#macro>