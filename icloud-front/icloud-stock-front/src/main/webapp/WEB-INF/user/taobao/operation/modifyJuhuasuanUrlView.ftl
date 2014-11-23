<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>

<@ilw.layerwindow jsFiles=['layer/layer.min.js','icloud/juhuasuan.js','common/jquery.validate.js','common/messages_cn.js','icloud/modifyjuhuasuan-url.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<section class=" clearfix">
<form action="${basepath}/usertb/doModifyJuhusuanUrl" method="post" id="juhuasuanUrlForm">
 <input type="hidden" name="id" id="name" value='${urlBean.id!""}' />
            <div class="row gray-border" style="margin-top:0px;">
             <h3 class="title">
                    <strong>修改链接</strong>
                </h3>
                <div class="row" style="min-height:560px;">
                    <ul class="find_password">
                        <li>
                            <label>
                                <div>链接名字:</div>
                                <input type="text" name="name" id="name" class="normal_txt" style="width:150px;" value='${urlBean.name!""}'/>
                                
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>淘宝链接:</div>
                                <input type="text" name="taobaoUrl" id="taobaoUrl" class="normal_txt" style="width:150px;" value='${urlBean.taobaoUrl!""}'/>
                            </label>
                        </li>
                         <li>
                            <label>
                                <div>原始链接:</div>
                                <input type="text" name="originUrl" id="originUrl" class="normal_txt" style="width:150px;" value='${urlBean.originUrl!""}'/>
                            </label>
                        </li>
                        <!--
                        <li>
                            <label>
                                <div>转换链接:</div>
                                <input type="text" name="" class="normal_txt" style="width:150px;" placeholder="" />
                            </label>
                        </li>
                        -->
                        <input type="hidden" name="moreUrl" id="moreUrl" value=""/>
                         <li>
                             <label>
                                 <div>链接状态:</div>
                                 <@jst.statusSelect key='${urlBean.status!""}' />
                             </label>
                         </li>
                         
                         <li>
                        	<label>
                                 <div>链接类型:</div>
                                 <@jst.urlTypeSelect key='${urlBean.moreFlag!""}' />
                             </label>
                             <div style="margin-left:153px;">
                         	 <input type="text" name="tmpUrlText" id="tmpUrlText" class="normal_txt" style="width:150px;" value=''/>
                             <input type="button" name="addMoreUrlButton" id="addMoreUrlButton" onclick="addMoreUrl();" value="增加">
                         	<table id="infoTable" border="0" width="530" style="display: inline;  padding: 2px;margin-top:8px;">
                         	<#if urlBean_moreUrls??>
                         	 <#list urlBean_moreUrls as moreUrl>
                         	<tr>
                         		<td id='${moreUrl_index}' style="width:250px;word-break: break-all;word-wrap : break-word; overflow:hidden;">
                         		  ${moreUrl}
                         		</td>
                         		<td align="right"><a href='javascript:deleteMoreUrl("${moreUrl_index}")'>删除</a></td>
                         	</tr>
                         	</#list>
                         	</#if>
						    </table>
						 </div>
                         </li>
                        <li>
                             <label>
                                 <div>链接介绍:</div>
                                 <textarea name="desText" id="desText" cols="30" rows="10" style="width:300px">${urlBean.desText!""}</textarea>
                             </label>
                         </li>
                        <li>    
                            <div style="margin-left:153px;">
                                <a class="adaptiveButton brightRed_btn" id="modifyjuhuasuanButton">
                                    <span class="left"></span>
                                    <span class="center center_1">修&nbsp;&nbsp;改</span>
                                    <span class="right"></span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            </form>
        </section>
</@ilw.layerwindow>
	<script type="text/javascript">
	<#if urlBean_moreUrls??>
		urlId = ${urlBean_moreUrls?size};
	<#else>
	    urlId = 0;
	</#if>
	</script>