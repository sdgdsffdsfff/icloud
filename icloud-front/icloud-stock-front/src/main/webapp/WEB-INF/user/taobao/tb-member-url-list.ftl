<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="成员名单"/>
<main class="us-content">
    <h1 class="us-title">链接详情:
    <#if parentsUsers??>
    	<#list parentsUsers as u>
    		<a href="${basepath}/usertb/tbMemberList?memberId=${u.id}">${u.userName!""}</a> >
    	</#list>
    	 ${tmpUser.userName!""}
    <#else>
        ${tmpUser.userName!""}
    </#if>
        <a href="${basepath}/usertb/trafficUserView?memberId=${tmpUser.id}">流量详情</a>
       </h1>
        <div class="us-body">
            <div class="tab_item">
                <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>链接名称</th>
                                <th>淘宝链接</th>
                                <th>转换链接</th>
                                <th>链接状态</th>
                                <th>修改日期</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as urlBean> 
                        <tr>
                            <td>${urlBean.name!""}</td>
                            <#if urlBean.taobaoUrl?length gt 30>
                              <td>${urlBean.taobaoUrl?substring(0,30)}</td>
                            <#else>
                             <td>${urlBean.taobaoUrl}</td>
                            </#if>
                            <td><a href="${taobaohosthref}${urlBean.icloudUrl!""}" target="_blank">${taobaohost}${urlBean.icloudUrl!""}</a></td>
                            <td>${staticValues('1','${urlBean.status!""}')}</td>
                            <td>${urlBean.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>
            </div>
            <@pView.pagination param1='10' param2='${tmpUser.id}' param3='33'/>
        </div>
</main>
</@imc.mainContainer>