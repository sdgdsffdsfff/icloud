<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>

<@imc.mainContainer current="行情"  jsFiles=['layer/layer.min.js','icloud/juhuasuan.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="所有链接"/>
<main class="us-content">
    <h1 class="us-title">聚划算</h1>
        <div class="us-body">
            <div class="tab_item">
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                <#if urlBean??>
                <form action="${basepath}/usertb/tbList" method="post" id="searchBeanCopyForm">
                  <input id="name" type="hidden" name="name" style="width:115px;" value="${urlBean.name!""}"/>
                  <input id="type" type="hidden" name="type" style="width:115px;" value="${urlBean.type!"-1"}"/>
                  <input id="pageNo" type="hidden" name="pageNo" style="width:115px;" value="0"/>
                  <input id="solidify" type="hidden" name="solidify" style="width:115px;" value="${urlBean.solidify!"-1"}"/>
                  <input id="status" type="hidden" name="status" style="width:115px;" value="${urlBean.status!"-1"}"/>
                </form>
                </#if>
                <form action="${basepath}/usertb/tbList" method="post" id="searchBeanForm">
                    <table class="us-query-table">
                        <tr>
                            <th>链接名称：</th>
                            <td><input id="name" type="text" name="name" style="width:115px;" <#if urlBean??>value="${urlBean.name!""}"</#if>></td>
                            <th>链接类型：</th>
                            <td>
                            <#if urlBean??>
                             <@jst.typeSelect all='-1' key="${urlBean.type!'-1'}"/>
                            <#else>
                             <@jst.typeSelect all='-1' key='-1'/>
                            </#if>
                            </td>
                            <th>加固方式：</th>
                            <td>
                             <#if urlBean??>
                             <@jst.solidifySelect all='-1' key="${urlBean.solidify!'-1'}"/>
                            <#else>
                             <@jst.solidifySelect all='-1' key='-1'/>
                            </#if>
                            </td>
                            <th>链接状态：</th>
                            <td>
                            <#if urlBean??>
                             <@jst.statusSelect all='-1' key="${urlBean.status!'-1'}"/>
                            <#else>
                             <@jst.statusSelect all='-1' key='-1'/>
                            </#if>
                            </td>
                        </tr>
                    </table>
                    <a class="adaptiveButton brightRed_btn" id="searchBeanButton">
                        <span class="left"></span>
                        <span class="center center_1">搜&nbsp;索</span>
                        <span class="right"></span>
                    </a>
                </form>
                </section>
                <section class="us-query clearfix" style="padding-top:0px; padding-bottom:5px;">
                <a class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id">
                        <span class="left"></span>
                        <span class="center center_1">增加链接</span>
                        <span class="right"></span>
                </a>
                </section>
                <section class="usResult">
                    <table>
                        <thead>
                            <tr>
                                <th>链接名称</th>
                                <th>淘宝链接</th>
                                <th>转换链接</th>
                                <th>链接类型</th>
                                <th>加固方式</th>
                                <th>链接状态</th>
                                <th>修改日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as urlBean> 
                        <tr>
                            <td><a class="text1" href="#"> ${urlBean.name!""}</a></td>
                            <#if urlBean.taobaoUrl?length gt 10>
                              <td>${urlBean.taobaoUrl?substring(0,10)}</td>
                            <#else>
                             <td>${urlBean.taobaoUrl}</td>
                            </#if>
                            <td>${taobaohost}${urlBean.icloudUrl!""}</td>
                            <td><@jst.typeSelect key='${urlBean.type!""}'/></td>
                            <td><@jst.solidifySelect key='${urlBean.solidify!""}'/></td>
                            <td><@jst.statusSelect key='${urlBean.status!""}' /></td>
                            <td>${urlBean.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                            <td><a href="#" onclick="reviewTheUrl('${urlBean.icloudUrl}');">查看</a></td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>
            </div>
            <@pView.pagination param1='4' param2='0' param3='33'/>
        </div>
</main>
</@imc.mainContainer>