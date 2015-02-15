<#import "/user/taobao/template/select-template.ftl" as st/>

<#macro statusSelect name="status" id="status" key="0" all="0">
<@st.select name='${name}' id='${id}' key='${key}' selectId='1' all='${all}'/>
</#macro>

<#macro typeSelect name="type" id="type" key="0" all="0">
<@st.select name='${name}' id='${id}' key='${key}' selectId='2' all='${all}'/>
</#macro>

<#macro solidifySelect name="solidify" id="solidify" key="0" all="0">
<@st.select name='${name}' id='${id}' key='${key}' selectId='3' all='${all}'/>
</#macro>

<#macro urlTypeSelect name="moreflag" id="moreflag" key="0" all="0">
<@st.select name='${name}' id='${id}' key='${key}' selectId='4' all='${all}'/>
</#macro>
