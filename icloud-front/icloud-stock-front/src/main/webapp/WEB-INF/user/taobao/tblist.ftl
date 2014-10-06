<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>

<@imc.mainContainer current="行情"  jsFiles=['layer/layer.min.js','icloud/juhuasuan.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current="所有链接"/>
<main class="us-content">
    <h1 class="us-title">聚划算</h1>
        <div class="us-body">
            <div class="tab_item">
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                    <table class="us-query-table">
                        <tr>
                            <th>链接名称：</th>
                            <td><input id="name" type="text" name="name" style="width:115px;" <#if urlBean??>value="${urlBean.name!""}"</#if>></td>
                            <th>链接类型：</th>
                            <td>
                                <@jst.typeSelect all='-1' key='-1'/>
                            </td>
                            <th>加固方式：</th>
                            <td>
                                <@jst.solidifySelect all='-1' key='-1'/>
                            </td>
                            <th>链接状态：</th>
                            <td><@jst.statusSelect all='-1' key='-1'/></td>
                        </tr>
                    </table>
                    <a class="adaptiveButton brightRed_btn">
                        <span class="left"></span>
                        <span class="center center_1">搜&nbsp;索</span>
                        <span class="right"></span>
                    </a>
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
                            <td>${urlBean.taobaoUrl!""}</td>
                            <td>${taobaohost}${urlBean.icloudUrl!""}</td>
                            <td><@jst.typeSelect key='${urlBean.type!""}'/></td>
                            <td><@jst.solidifySelect key='${urlBean.solidify!""}'/></td>
                            <td><@jst.statusSelect key='${urlBean.status!""}' /></td>
                            <td>${urlBean.updateTime?string('yyyy-MM-dd HH:mm')}</td>
                            <td>查看</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>
            </div>
                <div class="pagination clearfix">
                    <ul>
                         <li><a href="#">上一页</a></li>
                         <li><a href="#">1</a></li>
                         <li><a href="#">2</a></li>
                         <li><a href="#" class="present">3</a></li>
                         <li><a href="#">4</a></li>
                         <li><a href="#">5</a></li>
                         <li> <span>...</span> </li>
                         <li><a href="#">60</a></li>
                         <li><a href="#">下一页</a></li>
                    </ul>
                    <div>
                       <span>共<em>132</em>条记录 &nbsp;&nbsp;&nbsp;共14页</span> 
                    </div>
                </div>
                <div style="clear:both;"></div>
        </div>
</main>
</@imc.mainContainer>