<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<#import "/user/taobao/template/juhuasuan-user-charts.ftl" as juc/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js','common/echarts-plain-map.js','icloud/juhuasuan-user-charts.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="成员流量"/>
<main class="us-content">
    <h1 class="us-title">流量:
    <#if parentsUsers??>
    	<#list parentsUsers as u>
    		<a href="${basepath}/usertb/trafficUserView?memberId=${u.id}">${u.userName!""}></a>
    	</#list>
    	>  ${tmpUser.userName!""}
    <#else>
        ${tmpUser.userName!""}
    </#if>
    </h1>
        <div class="us-body">
            <div class="tab_item">
            	  <#if pageView?? && pageView.currentPageNo==0>
            	  <@juc.juhuasuanUserChart />
            	  </#if>
                  <#include "/user/taobao/template/juhusuan-access-count-template.ftl"/>
            </div>
            <@pView.pagination param1='9' param2='${tmpUser.id}' param3='33'/>
        </div>
</main>
<#if pageView?? && pageView.currentPageNo==0>
<script type="text/javascript">
$(function() {
	fillJuhuanUserCharts(${tmpUser.id});
});
</script>
</#if>
</@imc.mainContainer>