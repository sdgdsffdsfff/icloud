<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="successful">
    <h3>
        <em></em>恭喜您，注册成功</h3>
    <div>欢迎使用本站。<a href="${basepath}/user/icloudLogin">点击此处进行登录</a></div>
</div>
</@imc.mainContainer>