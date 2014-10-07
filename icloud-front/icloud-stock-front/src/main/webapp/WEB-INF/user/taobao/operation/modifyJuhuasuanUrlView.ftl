<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<#import "/user/taobao/template/juhuasuan-select-template.ftl" as jst/>

<@ilw.layerwindow jsFiles=['layer/layer.min.js','icloud/juhuasuan.js','common/jquery.validate.js','common/messages_cn.js','icloud/addjuhuasuan-url.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<section class=" clearfix">
<form action="${basepath}/usertb/doModifyJuhusuanUrl" method="post" id="juhuasuanUrlForm">
 <input type="hidden" name="id" id="name" value='${urlBean.id!""}' />
            <div class="row gray-border" style="margin-top:0px;">
             <h3 class="title">
                    <strong>修改链接</strong>
                </h3>
                <div class="row" style="height:560px;">
                    <ul class="find_password">
                        <li>
                            <label>
                                <div>链接名字:</div>
                                ${urlBean.name!""}
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>链接类型:</div>
                               <@jst.typeSelect key='${urlBean.type!""}'/>
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
                         <li>
                             <label>
                                 <div>是否加固:</div>
                                 <@jst.solidifySelect key='${urlBean.solidify!""}'/>
                             </label>
                         </li>
                         <li>
                             <label>
                                 <div>链接状态:</div>
                                 <@jst.statusSelect key='${urlBean.status!""}' />
                             </label>
                         </li>
                         <li>
                             <label>
                                 <div>链接介绍:</div>
                                 <textarea name="desText" id="desText" cols="30" rows="10" style="width:300px">${urlBean.desText!""}</textarea>
                             </label>
                         </li>
                        <li>    
                            <div style="margin-left:153px;">
                                <a class="adaptiveButton brightRed_btn" id="addjuhuasuanButton">
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