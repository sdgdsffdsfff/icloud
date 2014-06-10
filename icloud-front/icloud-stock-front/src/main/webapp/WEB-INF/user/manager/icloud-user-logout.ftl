<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="row gray-border" style="margin-top:20px;">
    <h3 class="title">
        <strong>退出登录</strong>
    </h3>
    <div class="row" style="height:270px;">
        <div class="successful">
            <h3>
                <em></em>退出登录成功</h3>
            <div>欢迎使用本站。</div>
            <ul class="clearfix">
                <li>
                    <a href="#">国内机票</a>
                </li>
                <li>
                    <a href="#">国际机票</a>
                </li>
                <li>
                    <a href="#">酒店</a>
                </li>
            </ul>
        </div>
    </div>
</div>
</@imc.mainContainer>