<#import "/icloud/icloud-layer-window.ftl" as ilw/>
<@ilw.layerwindow jsFiles=['layer/layer.min.js','icloud/juhuasuan.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<section class=" clearfix">
            <div class="row gray-border" style="margin-top:0px;">
                <div class="row" style="height:500px;">
                    <ul class="find_password">
                        <li>
                            <label>
                                <div>链接名字:</div>
                                <input type="text" name="" class="normal_txt" style="width:150px;" />
                            </label>
                        </li>
                        <li>
                            <label>
                                <div>淘宝链接:</div>
                                <input type="text" name="" class="normal_txt" style="width:150px;" />
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
                                 <select name="" id="">
                                     <option selected="" value="请选择">请选择</option>
                                     <option value="二星级及以下/经济">二星级及以下/经济</option>
                                     <option value="三星级/舒适">三星级/舒适</option>
                                     <option value="四星级/高档">四星级/高档</option>
                                     <option value="五星级/豪华">五星级/豪华</option>
                                     <option value="度假酒店">度假酒店</option>
                                 </select>
                         </li>
                         <li>
                             <label>
                                 <div>链接介绍:</div>
                                 <textarea name="" id="" cols="30" rows="10" style="width:300px"></textarea>
                         </li>
                        <li>    
                            <div style="margin-left:153px;">
                                <a class="adaptiveButton brightRed_btn">
                                    <span class="left"></span>
                                    <span class="center center_1">确&nbsp;&nbsp;定</span>
                                    <span class="right"></span>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
</@ilw.layerwindow>