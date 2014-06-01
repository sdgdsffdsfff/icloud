<#macro stockChart chartName chartID>
<div class="stockChart">
	<div class="stockTitle">
		<div class="topName">
			<span class="stockName">
				<strong title="" class="stockName">${chartName}</strong>
				</span>
			<div class="clear"></div>
 		</div>
		<div class="clear"></div>
	</div>
	<!--<div id="${chartID}" style="height: 330px; border: 1px solid rgb(204, 204, 204);"></div>-->
	<div id="main" class="main" style="height: 230px; margin-bottom: 1px; padding-bottom: 0px; border-bottom-width: 0px; cursor: default;"></div>
	<div id="main2" class="main" style="height: 145px; margin-bottom: 1px; padding-bottom: 0px; border-bottom-width: 0px; cursor: default;"></div>
	<div id="main3" class="main" style="height: 105px; margin-bottom: 1px; padding-bottom: 0px; border-bottom-width: 0px; cursor: default;"></div>
</div>
</#macro>