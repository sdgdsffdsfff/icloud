<#import "/user/user-template/select-template.ftl" as st/>

<#macro userTypeSelect name="userType" id="userType" key="0" all="0">
<@st.select name='${name}' id='${id}' key='${key}' selectId='1' all='${all}'/>
</#macro>
