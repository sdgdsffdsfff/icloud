<#macro stockDetailCompanyInfo>
<div class="rightStockDetailContent">
    <div class="wrapper">
        <div class="stock-company">
            <strong class="title">简介:</strong>
			${(stockDetail.detailContent)!'此公司还没有简介'}
            <div class="wrapper"></div>
            <#if stockDetail.companyInfoWork ??>
               <strong class="title">业务:</strong>
               ${stockDetail.companyInfoWork}
               <div class="wrapper"></div>
            </#if>
        </div>
    </div>
</div>
</#macro>