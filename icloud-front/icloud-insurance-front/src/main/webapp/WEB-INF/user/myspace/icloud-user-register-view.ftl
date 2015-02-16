<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<@imc.mainContainer jsFiles=['common/jquery.validate.js','common/messages_cn.js','icloud/register.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current="增加成员"/>
<main class="us-content">
    <h1 class="us-title">会员注册</h1>
    <div class="us-body">
        <#if (successModifyUserInfo)??>
        <div class="usersuccessful">会员注册成功！</div>
        </#if>
        <div class="tabPanel">
            <form action="${basepath}/user/doRegisterUser" method="post" id="registerForm">
        <table cellpadding="0" cellspacing="0" class="mes_tab">
            <colgroup>
                <col width="150px" />
            </colgroup>
            <tr>
                <td>
                    <i>*</i>用户名</td>
                <td>
                    <input type="text" id="username" name="username"/>
                    <em>字母开头，由4～16个数字或字母组成。</em>
                </td>
            </tr>
            <tr>
                <td>
                    <i>*</i>电子邮箱</td>
                <td>
                    <input id="email" type="text"  name="email"/>
                    <em>此邮箱用户找回密码等服务，请确保地址正确。</em>
                </td>
            </tr>
            <tr>
                <td>
                    <i>*</i>手机号码</td>
                <td class="hint">
                    <input type="text" id="telphone" name="telphone"/>
                    <small>
                        <span></span>请填写手机号码</small>
                </td>
            </tr>
            <tr>
                <td>QQ</td>
                <td>
                    <input type="text" id="qq" name="qq"/>
                </td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <select id="usersex" name="usersex">
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
                </td>
            </tr>
            <tr>
                <td>
                    <i>*</i>设置密码</td>
                <td>
                    <input type="password" id="password" name="password"/>
                    <em>4~20位数字或者字母的组合，区分大小写。</em>
                </td>
            </tr>
            <tr>
                <td>
                    <i>*</i>确认密码</td>
                <td>
                    <input type="password" id="confirm_password" name="confirm_password"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a class="adaptiveButton brightRed_btn" id="register_button">
                        <span class="left"></span>
                        <span class="center">注 册</span>
                        <span class="right"></span>
                    </a>
                </td>
            </tr>
        </table>
        </form>
        </div>
    </div>
</main>
</@imc.mainContainer>