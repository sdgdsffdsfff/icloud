<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#macro baseHtml current jsFiles cssFiles>
<@imc.mainContainer current="保险" jsFiles=jsFiles cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current=current/>
<main class="us-content">
    <h1 class="us-title">${current}</h1>
	<#nested/>
</main>
</@imc.mainContainer>
</#macro>