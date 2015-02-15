<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<@imc.mainContainer current="行情"  jsFiles=['common/jquery.validate.js','common/messages_cn.js','user/userinfomanager.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current="信息维护"/>
<main class="us-content">
    <h1 class="us-title">信息维护</h1>
    <div class="us-body">
        <aside>
            <ul class="tab_change clearfix">
                <li class="currentState">
                    <em>
                        <a href="javascript:void(0);">我的账号</a>
                    </em>
                </li>
            </ul>
        </aside>
        <#if (successModifyUserInfo)??>
        <div class="usersuccessful">基本信息修改成功！</div>
        </#if>
        <div class="tabPanel">
            <h1 class="us-title">账号信息</h1>
            <table>
                <colgroup>
                    <col width="75">
                </colgroup>
                <tbody>
                    <tr>
                        <td class="td_head">用户名：</td>
                        <td>${(icloudUser.userName)!''}</td>
                    </tr>
                    <tr>
                        <td class="td_head">电子邮箱：</td>
                        <td>${(icloudUser.userEmail)!''}</td>
                    </tr>
                    <tr>
                        <td class="td_head">登录密码：</td>
                        <td>********
                            <a href="${basepath}/user/modifyPassword" class="btn_modifyPassword">修改密码</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_head">手机：</td>
                        <td>${(icloudUser.userTel)!''}</td>
                    </tr>
                     <tr>
                        <td class="td_head">运行状态：</td>
                        <td><#if icloudUser.open==0>暂停服务<#else>正常</#if></td>
                    </tr>
                    <tr>
                        <td class="td_head">代理资格：</td>
                        <td>${(icloudUser.level)!''}级代理</td>
                    </tr>
                </tbody>
            </table>
            <form action="${basepath}/user/modifyBaseInfo" method="post" id="baseInfoForm">
            <h1 class="us-title">
                                 用户资料
                <span style="float:right;margin-top:-8px">
                    <a class="adaptiveButton medium brightRed_btn btn_edit">
                        <span class="left"></span>
                        <span class="center center_1">编辑</span>
                        <span class="right"></span>
                    </a>
                    <a class="adaptiveButton medium brightRed_btn btn_save" style="display:none">
                        <span class="left"></span>
                        <span class="center center_1">保存</span>
                        <span class="right"></span>
                    </a>
                </span>
            </h1>
            <table id="tbl_mainBusinessContactInfo">
                <colgroup>
                    <col width="75">
                </colgroup>
                <tbody>
                    <tr>
                        <td class="td_head">
                            <i class="important">*</i>姓名：</td>
                        <td>
                            <input value="${(icloudUser.chinaName)!''}" type="text" name="chinaName" id="chinaName" class="editable disabled" readonly="readonly" />
                        </td>
                    </tr>
                    <tr>
                     <td class="td_head">
                            <i class="important">*</i>性别：</td>
                     <td>
                    	<select id="usersex" name="usersex" class="editable disabled" readonly="readonly" style="width:100px;" >
							<option value="0" <#if icloudUser.userSex=='男'>SELECTED</#if> >男</option>
							<option value="1" <#if icloudUser.userSex=='女'>SELECTED</#if> >女</option>
					    </select>
					</td>
                    </tr>
                    <tr>
                        <td class="td_head">
                            <i class="important">*</i>qq：</td>
                        <td>
                            <input value="${(icloudUser.qq)!''}" type="text" name="qq" id="qq" class="editable disabled" readonly="readonly" />
                        </td>
                    </tr>
                </tbody>
            </table>
            </form>
        </div>
    </div>
</main>
</@imc.mainContainer>