<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
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
        <div class="find_password" style="width:400px;margin:100px auto;">
            <p>您的重置密码邮件已发送到注册时的账号信息电子邮箱，请
                <a href="http://mail.163.com/" class="login_mail">登录邮箱</a>查收。</p>
            <p>(如果收件箱里没有，可以去垃圾箱找找看。)</p>
        </div>
    </div>
</div>
</@imc.mainContainer>