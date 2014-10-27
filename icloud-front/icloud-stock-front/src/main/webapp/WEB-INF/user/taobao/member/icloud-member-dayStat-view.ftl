<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js','icloud/juhuasuan-detail.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="成员名单"/>
<main class="us-content">
    <h1 class="us-title">成员流量-${url_name!"当天访问量"}</h1>
        <div class="us-body">
            <div class="tab_item">
                
            </div>
            
        </div>
</main>
</@imc.mainContainer>