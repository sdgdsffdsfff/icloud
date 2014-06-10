<#import "/icloud/icloud-main-container.ftl" as imc/>

<@imc.mainContainer current="行情"  jsFiles=[] cssFiles=['icloud/icloud_usercenter.css'] >
<div class="regist">
                <!--regist start-->
                <div class="chief">
                    <h3>会员注册</h3>
                </div>
                <div class="message">
                    <!--message start-->
                    <h3>账号信息</h3>
                    <table cellpadding="0" cellspacing="0" class="mes_tab">
                        <colgroup>
                            <col width="150px" />
                        </colgroup>
                        <tr>
                            <td>
                                <i>*</i>用户名</td>
                            <td>
                                <input type="text" />
                                <em>字母开头，由4～16个数字或字母组成。</em>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>电子邮箱</td>
                            <td>
                                <input type="text" />
                                <em>此邮箱用户找回密码等服务，请确保地址正确。</em>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>设置密码</td>
                            <td>
                                <input type="text" />
                                <em>4~20位数字或者字母的组合，区分大小写。</em>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>确认密码</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>客户类型</td>
                            <td>
                                <label>
                                    <input type="radio" checked="" name="type" />公司</label>
                                <label>
                                    <input type="radio" name="type" />个人</label>
                            </td>
                        </tr>
                    </table>
                </div>
                <!--message end-->

                <div class="company">
                    <!--company start-->
                    <div class="message">
                        <!--message start-->
                        <h3>公司信息</h3>
                        <table cellpadding="0" cellspacing="0" class="mes_tab">
                            <colgroup>
                                <col width="150px" />
                            </colgroup>
                            <tr>
                                <td>
                                    <i>*</i>公司类型</td>
                                <td>
                                    <label>
                                        <input type="radio" checked="" name="type1" />旅行社</label>
                                    <label>
                                        <input type="radio" name="type1" />企业</label>
                                    <label>
                                        <input type="radio" name="type1" />加盟店</label>
                                    <label>
                                        <input type="radio" name="type1" />会展公司</label>
                                    <label>
                                        <input type="radio" name="type1" />不夜城分部</label>
                                    <label>
                                        <input type="radio" name="type1" />其他</label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>公司名称</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>公司简称</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>公司地区</td>
                                <td>
                                    <select name="sheng" style="width:88px;" data-role="dropdownlist">
                                        <option>上海市</option>
                                        <option>浙江省</option>
                                        <option>安徽省</option>
                                    </select>
                                    <select name="city" style="width:88px;" data-role="dropdownlist">
                                        <option>上海市</option>
                                    </select>
                                    <select name="street" style="width:88px;" data-role="dropdownlist">
                                        <option>闸北区</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>详细地址</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <!--message end-->

                    <div class="empty"></div>

                    <div class="message">
                        <!--message start-->
                        <h3>公司负责人信息</h3>
                        <table cellpadding="0" cellspacing="0" class="mes_tab">
                            <colgroup>
                                <col width="150px" />
                            </colgroup>
                            <tr>
                                <td>
                                    <i>*</i>姓名</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>电子邮箱</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>手机号码</td>
                                <td class="hint">
                                    <input type="text" />
                                    <small>
                                        <span></span>请填写手机号码</small>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <i>*</i>办公电话</td>
                                <td>
                                    <input type="text" name="" id="" placeholder="区号" style="width:40px;" />-
                                    <input value="" style="width:100px;" type="text" name="" />
                                </td>
                            </tr>
                            <tr>
                                <td>QQ</td>
                                <td>
                                    <input type="text" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <!--message end-->
                </div>
                <!--company end-->

                <div class="message">
                    <!--message start-->
                    <h3>主要业务联系人信息</h3>
                    <table cellpadding="0" cellspacing="0" class="mes_tab">
                        <colgroup>
                            <col width="150px" />
                        </colgroup>
                        <tr>
                            <td>
                                <i>*</i>姓名</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>电子邮箱</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>手机号码</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <i>*</i>办公电话</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>QQ</td>
                            <td>
                                <input type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <textarea></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <label>
                                    <input type="checkbox" />我愿意接受某某某某协议</label>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <a class="adaptiveButton brightRed_btn">
                                    <span class="left"></span>
                                    <span class="center">注 册</span>
                                    <span class="right"></span>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
                <!--message end-->
            </div>
            <!--regist end-->
</@imc.mainContainer>