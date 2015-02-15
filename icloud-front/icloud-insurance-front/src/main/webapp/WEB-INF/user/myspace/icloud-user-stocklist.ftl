<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<@imc.mainContainer current="行情"  jsFiles=['common/jquery.validate.js','common/messages_cn.js','user/userinfomanager.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current="关注的股票"/>
<main class="us-content">
    <h1 class="us-title">关注的股票</h1>
    <div class="us-body" style="min-height:450px;">
                   正在抓紧开发中，敬请期待。
    </div>
</main>
</@imc.mainContainer>