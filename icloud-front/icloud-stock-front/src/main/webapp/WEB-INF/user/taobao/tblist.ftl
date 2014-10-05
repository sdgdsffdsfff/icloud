<#import "/icloud/icloud-main-container.ftl" as imc/>
<#import "/user/user-template/user-center-menus.ftl" as ucm/>
<@imc.mainContainer current="行情"  jsFiles=['layer/layer.min.js','icloud/juhuasuan.js'] cssFiles=['icloud/icloud_usercenter.css'] >
<@ucm.userCenterMenus current="所有链接"/>
<main class="us-content">
    <h1 class="us-title">聚划算</h1>
        <div class="us-body">
            <div class="tab_item">
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                    <table class="us-query-table">
                        <tr>
                            <th>询价单编号：</th>
                            <td><input id="" type="text" name="" style="width:115px;"></td>
                            <th>提交日期：</th>
                            <td>
                                <input id="" value="2013-08-08" type="text" name="" style="width:73px;">
                                <em> - </em>
                                <input id="" value="2013-08-08" type="text" name="" style="width:73px;">
                            </td>
                            <th>询价单状态：</th>
                            <td>
                                <select data-role="dropdownlist" style="width:120px" id="" name="">
                                    <option value="">全部</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>询价类型：</th>
                            <td>
                                <select data-role="dropdownlist" style="width:120px" id="" name="">
                                    <option value="">全部</option>
                                    <option value="">团队</option>
                                    <option value="">公务机</option>
                                </select>
                            </td>
                            <th>PNR码：</th>
                            <td><input id="" type="text" name="" style="width:170px;"></td>
                            <th>旅客姓名：</th>
                            <td><input id="" type="text" name="" style="width:115px;"></td>
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
                                <th>询价单编号</th>
                                <th>提交时间</th>
                                <th>询价类型</th>
                                <th>PNR</th>
                                <th>出发日期</th>
                                <th>询价单状态</th>
                                <th>关联订单编号</th>
                            </tr>
                        </thead>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr class="currentList">
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>公务机</td>
                            <td>JZ4W50</td>
                            <td>2013-08-14 </td>
                            <td>取消</td>
                            <td> </td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>公务机</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>完成</td>
                            <td><a class="text1" href="#">030312103112261</a></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                         <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><a class="text1" href="#">030312103112261</a></td>
                            <td>2013-08-14</td>
                            <td>团队</td>
                            <td> </td>
                            <td>2013-08-14</td>
                            <td>受理中</td>
                            <td></td>
                        </tr>
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