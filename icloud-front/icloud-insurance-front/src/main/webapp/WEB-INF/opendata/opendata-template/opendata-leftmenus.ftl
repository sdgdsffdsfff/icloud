<#macro opendataMenu current>
<div class="leftMenu">
	<div class="nav-container unfold">
		<#if stockMenuBean??>
		<div class="first-nav nav1">
		<span class="name"><a href="${basepath}/stock/openMenus">返回分类</a></span>
		</div>
		<div class="first-nav nav1">
			<span class="name">${(stockMenuBean.name)!""}</span>
		</div>
		<ul class="second-nav">
			<#if (stockMenuBean.menus)??> 
			    <#list stockMenuBean.menus as menu>
			     <li>
			       <#if current==menu.name>
			         ${(menu.name)!''}
			       <#else>
			       	<a href="${basepath}/stock/openStockList?cateId=${(menu.code)!""}&pageNo=0">${(menu.name)!''}</a>
			       </#if>
			     </li> 
			     </#list>
			</#if>
		<#else>
		   <div class="first-nav nav1">
			<span class="name">股票分类</span>
		</div>
		<ul class="second-nav">
			<#if (mainMenus)??> 
			    <#list mainMenus as baseStockMenu>
			     <li>${(baseStockMenu.name)!''}
			     </li> 
			     </#list>
			</#if>
		</#if>
	</div>	
</div>
</#macro>