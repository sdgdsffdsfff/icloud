<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current = "" jsFiles=[""] cssFiles=[""]>
 <h1 class="au-title">
   <span>用户列表-编码 &nbsp;&nbsp;&nbsp;<a href="${basepath}/customer/addCustomer">增加一个用户</a></span>
   </h1>
   <div class="au-block clearfix"/>
   <#if (customers)??> 
     <#list customers as c>
       <p>${(c.journalid)!''}，${(c.created)!''},<a href="${basepath}/customer/viewCustomer?aid=${(c.journalid)!''}">详情</a>
     </#list>
   </#if>
</@imc.mainContainer>