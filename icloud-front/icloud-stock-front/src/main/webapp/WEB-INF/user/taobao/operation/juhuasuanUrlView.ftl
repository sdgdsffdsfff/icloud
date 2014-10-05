<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<@ilw.layerwindow jsFiles=['layer/layer.min.js','icloud/juhuasuan.js','common/jquery.validate.js','common/messages_cn.js','icloud/addjuhuasuan-url.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<section class=" clearfix">
<form action="${basepath}/usertb/doAddJuhuasuanUrl" method="post" id="juhuasuanUrlForm">
            <div class="row gray-border" style="margin-top:0px;">
                <div class="row" style="height:500px;">
                    <ul class="find_password">
                        <li>
                            <label>
                                <div>链接名字:</div>
                                <input type="text" name="name" id="name" class="normal_txt" style="width:150px;" />
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>淘宝链接:</div>
                                <input type="text" name="taobaoUrl" id="taobaoUrl" class="normal_txt" style="width:150px;" />
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>转换链接:</div>
                                <input type="text" name="" class="normal_txt" style="width:150px;" placeholder="" />
                            </label>
                        </li>
                         <li>
                             <label>
                                 <div>链接状态:</div>
                                 <select name="status" id="status">
                                     <option value="0">正在运行</option>
                                     <option value="1">暂停</option>
                                     <option value="2">作废</option>
                                 </select>
                             </label>
                         </li>
                         <li>
                             <label>
                                 <div>链接介绍:</div>
                                 <textarea name="desText" id="desText" cols="30" rows="10" style="width:300px"></textarea>
                             </label>
                         </li>
                        <li>    
                            <div style="margin-left:153px;">
                                <a class="adaptiveButton brightRed_btn" id="addjuhuasuanButton">
                                    <span class="left"></span>
                                    <span class="center center_1">确&nbsp;&nbsp;定</span>
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