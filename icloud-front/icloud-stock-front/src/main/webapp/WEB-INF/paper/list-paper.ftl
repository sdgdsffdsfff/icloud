<#import "/weixin/paper-main-container.ftl" as pmc/>
<#import "/icloud/pageView.ftl" as pView/>
<@pmc.mainContainer current = "阅读" jsFiles=[""] cssFiles=[""]>
<#if pagination??>
 <#if pagination.data??>
 <div class="wxapi_index_container">
      <ul class="label_box lbox_close wxapi_index_list">
     	<#list pagination.data as paper>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-basic">${paper.title}</a></li>
        </#list>
      </ul>
    </div>
  </#if>
</#if>
<@pView.pagination param1='-1' param2='1' param3='33'/>
</@pmc.mainContainer>