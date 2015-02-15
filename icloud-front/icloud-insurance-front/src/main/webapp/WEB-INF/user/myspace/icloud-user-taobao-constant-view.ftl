<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>

<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['common/jquery.validate.js','common/messages_cn.js','layer/layer.min.js','user/updateTaobaoUrl.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="链接前缀"/>
<main class="us-content">
    <h1 class="us-title">链接前缀</h1>
        <div class="us-body">
            <div class="tab_item">
            <form action="${basepath}/user/doModifyTaobaoUrl" method="post" id="baseForm">
               <table cellpadding="0" cellspacing="0" class="modifyPayPw_tab">
            <colgroup>
                <col width="140px" />
                <col width="500px" />
            </colgroup>
            <tr>
                <td>网站域名：</td>
                <td width="500px">
                    <input type="text" id="taobaoUrl" name="taobaoUrl" value='${taobaoUrl!''}' style="width:500px;"/>
                </td>
            </tr>
            <input type="hidden" id="resetTaobaoUrl" name="resetTaobaoUrl" value='${taobaoUrl!''}'/>
            <tr>
                <td>默认域名：</td>
                <td>
                    ${taobaoUrl!''}
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <a class="adaptiveButton brightRed_btn" id="modifyUrl_button">
                        <span class="left"></span>
                        <span class="center center_1">确认</span>
                        <span class="right"></span>
                    </a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="adaptiveButton brightRed_btn" id="ssetUrl_button">
                        <span class="left"></span>
                        <span class="center center_1">重置地址</span>
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