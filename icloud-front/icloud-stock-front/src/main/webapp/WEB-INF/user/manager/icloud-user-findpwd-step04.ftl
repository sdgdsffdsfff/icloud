<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="clearfix">
    <ul class="pull-right process_step">
        <li>
            <div class="txt">发送验证邮件</div>
        </li>
        <li>
            <div class="txt">设置新密码</div>
        </li>
        <li class="current">
            <div class="txt">完成</div>
        </li>
    </ul>
</div>
<div class="row gray-border" style="margin-top:20px;">
    <h3 class="title">
        <strong>设置新密码</strong>
    </h3>
    <div class="row" style="height:270px;">
        <div class="successful">
            <h3>
                <em></em>设置新密码成功</h3>
            <div>欢迎使用本站。<a href="${basepath}/user/icloudLogin">点击此处进行登录</a></div>
        </div>
    </div>
</div>
</@imc.mainContainer>