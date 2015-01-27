<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/member/template/MemberView.ftl" as mv/>
<@imc.mainContainer current = "" jsFiles=[""] cssFiles=[""]>
 <h1 class="au-title">
   <span>用户列表-编码 &nbsp;&nbsp;&nbsp;
   </h1>
   <div class="au-block clearfix"/>
   <#if (members)??> 
     <#list members as m>
       <@mv.memberView userName="${(m.userName)!''}" chineseName="${(m.chineseName)!''}" />
     </#list>
   </#if>
</@imc.mainContainer>