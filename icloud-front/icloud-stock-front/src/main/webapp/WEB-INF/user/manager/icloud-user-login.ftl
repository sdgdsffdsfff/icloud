<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current="行情"  jsFiles=['common/jquery.validate.js','common/messages_cn.js','icloud/userlogin.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="regist">
    <h3 class="title">
        <strong>登&nbsp;录</strong>
    </h3>
    <div class="row" style="height:330px;">
    	<form action="${basepath}/user/doLoginUser" method="post" id="loginForm">
        <ul class="find_password">
            <li>
                <label>
                    <div>邮箱:</div>
                    <input type="text" name="email" class="normal_txt" style="width:150px;" <#if (user)??>value="${(user.email)!''}"</#if>/>
                </label>
            </li>
            <li>
                <label>
                    <div>密码:</div>
                    <input type="password" name="password" class="normal_txt" style="width:150px;" placeholder="" />
                </label>
            </li>
            <li>
                <label>
                    <div>&nbsp;</div>
                    <input type="checkbox" id="autoLogin" name="autoLogin" <#if (user.autoLogin)??>checked</#if> />下次自动登录</label>
                </label>
            </li>
            <#if (failTip)??>
            <li>
                <label>
                    <div>&nbsp;</div>
                    <span style="color:#c00;">${(failTip)!''}</span>
                </label>
            </li>
            </#if>
            <li>
                <div style="margin-left:153px;">
                    <a class="adaptiveButton brightRed_btn" id="login_button">
                        <span class="left"></span>
                        <span class="center center_1">登&nbsp;&nbsp;录</span>
                        <span class="right"></span>
                    </a>
                    &nbsp;&nbsp;&nbsp;&nbsp;<a href="${basepath}/userManager/dofindPassWordStep1">忘记密码</a>
                </div>
            </li>
        </ul>
        </form>
        
    </div>
</div>
</@imc.mainContainer>