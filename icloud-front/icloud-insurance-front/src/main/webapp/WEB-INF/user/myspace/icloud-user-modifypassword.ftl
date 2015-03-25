<#import "/user/user-template/icloud-user-base-template.ftl" as iubt/>
<@iubt.baseHtml current="密码管理" jsFiles=['common/jquery.validate.js','common/messages_cn.js','user/updatepwd.js'] cssFiles=[] >
    <#if (successModifyPw)??>
    <h2 class="amendPwSucceed">
        <em></em>密码修改成功！</h2>
    </#if>
    <#if (fialModifyPw)??>
    <h2 class="amendPwDefeated">
        <em></em>密码修改失败！</h2>
    </#if>
    <form action="${basepath}/user/doModifyPassword" method="post" id="basePasswordForm">
    <div class="usRightContent" style="min-height:450px;">
        <table cellpadding="0" cellspacing="0" class="modifyPayPw_tab">
            <colgroup>
                <col width="140px" />
                <col width="300px" />
            </colgroup>
            <tr>
                <td>原密码：</td>
                <td>
                    <input type="password" id="oldpwd" name="oldpwd" />
                </td>
            </tr>
            <tr>
                <td>新密码：</td>
                <td>
                    <input type="password" id="newpwd" name="newpwd" />
                </td>
            </tr>
            <tr>
                <td>确认新密码：</td>
                <td>
                    <input type="password" id="confirmPwd" name="confirmPwd" />
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a class="adaptiveButton brightRed_btn" id="modifypwd_button">
                        <span class="left"></span>
                        <span class="center center_1">确认</span>
                        <span class="right"></span>
                    </a>
                </td>
                <td></td>
            </tr>
        </table>
    </div>
    </form>
</@iubt.baseHtml>