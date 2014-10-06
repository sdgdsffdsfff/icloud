<#macro statusSelect name="status" id="status" key="0">
<select name=${name} id=${id}>
   <#list selectValues('1') as kv>
    <option value="${kv.key}" <#if key==kv.key>selected</#if>>&nbsp;${kv.value}&nbsp;</option>
   </#list>
</select>
</#macro>

<#macro typeSelect name="type" id="type" key="0">
<select name=${name} id=${id}>
   <#list selectValues('2') as kv>
    <option value="${kv.key}" <#if key==kv.key>selected</#if>>&nbsp;${kv.value}&nbsp;</option>
   </#list>
</select>
</#macro>

<#macro solidifySelect name="solidify" id="solidify" key="0">
<select name=${name} id=${id}>
   <#list selectValues('3') as kv>
    <option value="${kv.key}" <#if key==kv.key>selected</#if>>&nbsp;${kv.value}&nbsp;</option>
   </#list>
</select>
</#macro>
