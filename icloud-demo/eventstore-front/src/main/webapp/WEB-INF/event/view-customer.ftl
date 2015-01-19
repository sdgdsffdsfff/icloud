<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current = "" jsFiles=[""] cssFiles=[""]>
 <h1 class="au-title">
   <span><a href="${basepath}/customer/listCustomers">返回列表</a>&nbsp;&nbsp; 用户详情 &nbsp;&nbsp;&nbsp;<a href="${basepath}/customer/addCustomer">建立快照</a></span>
   </h1>
   <div class="au-block clearfix"/>
   <p>
   用户信息:
   	编号:${customer.aggreagetRootId!""},姓名:${customer.customerName!""},年龄:${customer.customerYear!""}
   <p>
   事件流：
   <p>
   
 
</@imc.mainContainer>