<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<#import "/icloud/pageView.ftl" as pView/>
<@imc.mainContainer current="短链接"  jsFiles=['layer/layer.min.js','icloud/juhuasuan-detail.js'] cssFiles=['icloud/icloud_usercenter.css',"icloud/stock.css"] >
<@ucm.userCenterMenus current="访问数据"/>
<main class="us-content">
    <h1 class="us-title">聚划算-${url_name!"当天访问量"}</h1>
        <div class="us-body">
            <div class="tab_item">
                <!-- -->
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                <#if urlBean??>
                <form action="${basepath}/usertb/trafficCurrentDay" method="post" id="searchBeanCopyForm">
                  <input id="pageNo" type="hidden" name="pageNo" style="width:115px;" value="0"/>
                </form>
                </#if>
                <span>
                    <as class="taobaoDesc">当日访问量:</as><strong title="" class="taobaoName">${currentDayCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <as class="taobaoDesc">昨日访问量:</as><strong title="" class="taobaoName">${lastDayCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <as class="taobaoDesc">总共访问量:</as><strong title="" class="taobaoName">${totalCount!'0'}</strong>
                </span>
                </section>
                <section class="us-query clearfix" style="padding-top:0px; padding-bottom:5px;">
                <a class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id">
                        <span class="left"></span>
                        <span class="center center_1">当日数据</span>
                        <span class="right"></span>
                </a>
                <a class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id">
                        <span class="left"></span>
                        <span class="center center_1">一个月内的访问量</span>
                        <span class="right"></span>
                </a>
                </section>
                <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>链接</th>
                                <th>Perfer</th>
                                <th>访问IP</th>
                                <th>用户会话</th>
                                <th>访问时间</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as detail> 
                        <tr>
                            <td><a href="${taobaohosthref}${detail.perfer!""}" target="_blank">${detail.perfer!""}</a></td>
                            <td>${stringUtils('${detail.perferHost!""}','60','0')}</td>
                            <td>${detail.perferIp!""}</td>
                            <td>${detail.otherParam!""}</td>
                            <td>${detail.createTime?string('MM-dd HH:mm')}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>
            </div>
            <@pView.pagination param1='5' param2='0' param3='33'/>
        </div>
</main>
</@imc.mainContainer>