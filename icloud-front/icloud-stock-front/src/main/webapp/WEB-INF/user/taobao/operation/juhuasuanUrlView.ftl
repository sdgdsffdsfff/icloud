<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>
<@ilw.layerwindow jsFiles=['layer/layer.min.js','icloud/juhuasuan.js','common/jquery.validate.js','common/messages_cn.js','icloud/addjuhuasuan-url.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<#if urlBean??>
<section class=" clearfix">
            <div class="row gray-border" style="margin-top:0px;">
                <h3 class="title">
                    <strong>查看链接</strong>
                </h3>
                <div class="row" style="min-height:670px;">
                
                    <ul class="find_password">
                    <#if tip??>
                      温馨提示:${tip}
                    </#if>
                        <li>
                            <label>
                                <div>链接名字:</div>
                                ${urlBean.name!""}
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>淘宝链接:</div>
                                ${urlBean.taobaoUrl!""}
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>原始链接:</div>
                                 ${urlBean.originUrl!""}
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>转换链接:</div>
                                <a href="${taobaohosthref}${urlBean.icloudUrl!""}" target="_blank">
                                ${taobaohost}${urlBean.icloudUrl!""}
                                </a>
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>创建日期:</div>
                                ${urlBean.createTime?string('yyyy-MM-dd HH:mm:ss')}
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>更新时间:</div>
                                ${urlBean.updateTime?string('yyyy-MM-dd HH:mm:ss')}
                            </label>
                        </li>
                         <li>
                             <label>
                                 <div>链接状态:</div>
                                 ${staticValues('1','${urlBean.status!""}')}
                             </label>
                         </li>
                         <li>
                            <label>
                                 <div>链接类型:</div>
                                 <td>${staticValues('4','${urlBean.moreFlag!""}')}</td>
                             </label>
                             <#if urlBean_moreUrls??>
                             <div style="margin-left:153px;">
                         	<table id="infoTable" border="0" width="530" style="display: inline;  padding: 2px;margin-top:8px;">
                         	 <#list urlBean_moreUrls as moreUrl>
                         	<tr>
                         		<td id='0' style="width:250px;word-break: break-all;word-wrap : break-word; overflow:hidden;">
                         		  ${moreUrl}
                         		</td>
                         	</tr>
                         	</#list>
						    </table>
						    </div>
						    </#if>
                         </li>
                         <li>
                             <label>
                                 <div>链接介绍:</div>
                                 ${urlBean.desText!""}
                             </label>
                         </li>
                         <li>
                         	
                         </li>    
                            <div style="margin-left:153px;">
                                <a href="${basepath}/usertb/modifyJuhusuanUrlView?code=${urlBean.icloudUrl!""}" class="adaptiveButton brightRed_btn" id="addjuhuasuanButton">
                                    <span class="left"></span>
                                    <span class="center center_1">去修改该链接</span>
                                    <span class="right"></span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
</#if>
</@ilw.layerwindow>