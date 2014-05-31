<#macro stockDetailMenus current>
<div class="leftMenu">
    <div class="nav-container unfold">
        <div class="first-nav nav1">
            <span class="name">股票详情</span>
        </div>
        <ul class="second-nav">
            <li>
            	<#if current=="基本信息">
            		<i class="list-style"></i>基本信息
                	<i class="expand"></i>
            	<#else>
            		<a href="${basepath}/stock/stockBaseDetail?stockCode=${stock.stockCode}&type=-1" title="基本信息">基本信息</a>
            	</#if>
            </li>
            <li>
            	<#if current=="历史数据">
            		<i class="list-style"></i>历史数据
                	<i class="expand"></i>
            	<#else>
            		<a href="${basepath}/stock/stockBaseHistory?stockCode=${stock.stockCode}" title="历史数据">历史数据</a>
            	</#if>
                <i class="list-style"></i>
                <i class="expand"></i>
            </li>`
    </div>
</div>
</#macro>