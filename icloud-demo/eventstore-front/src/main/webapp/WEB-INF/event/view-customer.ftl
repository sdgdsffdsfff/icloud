<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current = "" jsFiles=['layer/layer.min.js','event/viewcu.js'] cssFiles=[""]>
 <h1 class="au-title">
   <span><a href="${basepath}/customer/listCustomers">返回列表</a>&nbsp;&nbsp; 用户详情 &nbsp;&nbsp;&nbsp;<a href="#" onclick="jianliSn('${customer.aggreagetRootId}','${customer.version}');">建立快照</a></span>
   </h1>
   <div class="au-block clearfix"/>
   <p>
   用户信息:
   <p>
   	&nbsp;&nbsp;&nbsp;&nbsp;版本:${customer.version!""}&nbsp;&nbsp;&nbsp;&nbsp;姓名:${customer.customerName!""}&nbsp;&nbsp;&nbsp;&nbsp;年龄:${customer.customerYear!""}
   <p>
   <form action="${basepath}/customer/changeAttr" type="post">
   <input type="hidden" name="aid" value="${customer.aggreagetRootId!""}"/>
   <select name="changeKey">
   <option value="0">修改名字</option>
   <option value="1">修改值</option>
   </select>
   &nbsp;<input name="changeValue" value=""/>&nbsp;
   &nbsp;<input type="submit" value="确定"/>
   </from>
   <#if snap.version??>
     已有快照---->版本:${snap.version!""}&nbsp;&nbsp;&nbsp;&nbsp;姓名:${snap.customerName!""}&nbsp;&nbsp;&nbsp;&nbsp;年龄:${snap.customerYear!""}
   </#if>
   <p>
   事件流：
   <p>
   <#if events??>
   		<#list events as event>
   			<p>&nbsp;&nbsp;&nbsp;${event.journalid}&nbsp;-&nbsp;${event.changeKey!"null"}&nbsp;-&nbsp;${event.oldValue!"null"}&nbsp;-&nbsp;${event.newValue!"null_2"}
   		</#list>
   </#if>
</@imc.mainContainer>