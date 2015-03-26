<#macro select name id key selectId all="0">
<select name='${name}' id='${id}'>
   <#if all=="-1">
   	<option value="-1" <#if key=='-1'>selected</#if>>&nbsp;不限&nbsp;</option>
   </#if>
   <#list selectValues('${selectId}') as kv>
    <option value="${kv.key}" <#if key==kv.key>selected</#if>>&nbsp;${kv.value}&nbsp;</option>
   </#list>
</select>
</#macro>
