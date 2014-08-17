<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/opendata/opendata-template/opendata-leftmenus.ftl" as olm/>

<@imc.mainContainer current="爬虫数据" cssFiles=["icloud/stock.css","icloud/stock_detail.css"]>
<@olm.opendataMenu current="默认分类哦"/>
<div class="mainPageContent">
<#if stockMenuBeans??>
<#list stockMenuBeans as bean>
	<div>
		<div class="stockTitle">
			<div class="topName">
				<span class="stockName"> <strong title="" class="stockName">${(bean.name)!""}</strong>
				</span>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="stockList-content new-portfolio" style="display: block;">
			<table cellspacing="0" cellpadding="0" class="portfolio">
				<tbody>
					<#list bean.menus as baseStockMenu>
					<#if baseStockMenu_index%5==0>
					<tr class="odd_stock_up"/>
					</#if>	
						<td><a href="${basepath}/stock/openStockList?cateId=${(baseStockMenu.code)!""}&pageNo=0">${(baseStockMenu.name)!""}</a></td>
					</#list>
				</tbody>
			</table>
		</div>
		<br />
	</div>
</#list>
</#if>   
</div>
</@imc.mainContainer>
