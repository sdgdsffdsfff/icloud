<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="amendPwDefeated">
    <h3>
        <em></em>${(errorTip)!''}</h3>
    <div>欢迎使用本站。</div>
</div>
</@imc.mainContainer>