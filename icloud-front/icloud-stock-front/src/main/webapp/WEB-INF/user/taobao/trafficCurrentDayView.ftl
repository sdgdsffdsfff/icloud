<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<#import "/user/taobao/template/juhuasuan-user-charts.ftl" as juc/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js','icloud/juhuasuan-detail.js','common/echarts-plain-map.js','icloud/juhuasuan-user-charts.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="访问数据"/>
<main class="us-content">
    <h1 class="us-title">聚划算-${url_name!"当天访问量"}</h1>
        <div class="us-body">
            <div class="tab_item">
                  <#if pageView?? && pageView.currentPageNo==0>
            	  <@juc.juhuasuanUserChart />
            	  </#if>
                <#include "/user/taobao/template/juhusuan-detail-header-template.ftl"/>
                <#include "/user/taobao/template/juhusuan-detail-template.ftl"/>
            </div>
            <@pView.pagination param1='5' param2='0' param3='33'/>
        </div>
</main>
<#if pageView?? && pageView.currentPageNo==0>
<script type="text/javascript">
$(function() {
	fillJuhuanUserCharts('${currentUserInfo().userId}');
});
</script>
</#if>
</@imc.mainContainer>