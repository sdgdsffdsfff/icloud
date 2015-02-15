<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js','icloud/juhuasuan.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
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
     			&nbsp;
                <a href="${basepath}/usertb/downloadMyUrls" class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id2">
                        <span class="left"></span>
                        <span class="center center_1">下载链接</span>
                        <span class="right"></span>
                </a>
                &nbsp;
                <a class="adaptiveButton brightRed_btn" id="batchAddJuhuasuanUrl_id3">
                        <span class="left"></span>
                        <span class="center center_1">批量导入链接</span>
                        <span class="right"></span>
                </a>
                </section>
                <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>链接名称</th>
                                <th>淘宝链接</th>
                                <th>转换链接</th>
                                <th>链接类型</th>
                                <!--
                                <th>加固方式</th>
                                
                                -->
                                <th>链接状态</th>
                                <th>修改日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as urlBean> 
                        <tr>
                            <td><a class="text1" href="#"> ${urlBean.name!""}</a></td>
                            <#if urlBean.taobaoUrl?length gt 20>
                              <td>${urlBean.taobaoUrl?substring(0,20)}</td>
                            <#else>
                             <td>${urlBean.taobaoUrl}</td>
                            </#if>
                            <td><a href="${taobaohosthref}${urlBean.icloudUrl!""}" target="_blank">${taobaohost}${urlBean.icloudUrl!""}</a></td>
                            <td>${staticValues('4','${urlBean.moreFlag!""}')}</td>
                            <td>${staticValues('1','${urlBean.status!""}')}</td>
                            <td>${urlBean.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                            <td><a href="#" onclick="reviewTheUrl('${urlBean.icloudUrl}');">查看</a>&nbsp;&nbsp;
                            <a href="#" onclick="modifyTheUrl('${urlBean.icloudUrl}');">修改</a>&nbsp;&nbsp;
                            <!--
                            <a href="#" onclick="deleteTheUrl('${urlBean.icloudUrl}');">删除</a></td>
                            -->
                            <input name="juhuasuan-id" type="hidden" value="${(urlBean.icloudUrl)!''}"/>
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