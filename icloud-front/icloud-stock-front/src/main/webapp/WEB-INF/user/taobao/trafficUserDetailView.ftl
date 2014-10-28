<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="成员流量"/>
<main class="us-content">
    <h1 class="us-title">成员流量-${tmpUser.userName!""}</h1>
        <div class="us-body">
            <div class="tab_item">
                  <#include "/user/taobao/template/juhusuan-access-count-detail-template.ftl"/>
            </div>
            <@pView.pagination param1='10' param2='${tmpUser.id}' param3='33'/>
        </div>
</main>
</@imc.mainContainer>