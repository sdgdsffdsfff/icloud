<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="row gray-border" style="margin-top:20px;">
    <h3 class="title">
        <strong>登录</strong>
    </h3>
    <div class="row" style="height:330px;">
        <ul class="find_password">
            <li>
                <label>
                    <div>邮箱:</div>
                    <input type="text" name="" class="normal_txt" style="width:150px;" />
                </label>
            </li>
            <li>
                <label>
                    <div>密码:</div>
                    <input type="text" name="" class="normal_txt" style="width:150px;" placeholder="" />
                </label>
            </li>
            <li>
                <div style="margin-left:153px;">
                    <a class="adaptiveButton brightRed_btn">
                        <span class="left"></span>
                        <span class="center center_1">登&nbsp;&nbsp;录</span>
                        <span class="right"></span>
                    </a>
                </div>

            </li>
        </ul>
    </div>
</div>
</@imc.mainContainer>