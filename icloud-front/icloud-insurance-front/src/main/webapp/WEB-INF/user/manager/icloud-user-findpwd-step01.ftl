<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer jsFiles=['common/jquery.validate.js','common/messages_cn.js','icloud/findpwd.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="clearfix">
    <ul class="pull-right process_step">
        <li class="current">
            <div class="txt">发送验证邮件</div>
        </li>
        <li>
            <div class="txt">设置新密码</div>
        </li>
        <li>
            <div class="txt">完成</div>
        </li>
    </ul>
</div>
<div class="row gray-border" style="margin-top:20px;">
    <h3 class="title">
        <strong>发送邮件验证</strong>
    </h3>
    <div class="row" style="height:330px;">
    <form action="${basepath}/userManager/dofindPassWordStep2" method="post" id="findpwdForm">
        <ul class="find_password">
            <li>
                <label>
                    <div>电子邮箱:</div>
                    <input type="text" name="email" id="email" class="normal_txt" style="width:200px;" />
                </label>
                <span class="input_tips">请填写注册时的账号电子邮箱</span>
            </li>
            <li>
                <div style="margin-left:153px;">
                    <a class="adaptiveButton brightRed_btn" name="findpwd_button" id="findpwd_button">
                        <span class="left"></span>
                        <span class="center center_1">发送邮件</span>
                        <span class="right"></span>
                    </a>
                </div>
            </li>
        </ul>
     </form>
    </div>
</div>
</@imc.mainContainer>