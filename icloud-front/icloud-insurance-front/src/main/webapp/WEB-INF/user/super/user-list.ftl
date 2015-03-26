<#import "/user/user-template/icloud-user-base-template.ftl" as iubt/>
<#import "/icloud/pageView.ftl" as pView/>
<#import "/user/user-template/insurance-select-template.ftl" as ist/>
<@iubt.baseHtml current='用户列表' jsFiles=['layer/layer.min.js','insurance/userList.js'] cssFiles=[] >
<div class="us-body">
            <div class="tab_item">
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                <#if userBean??>
                <form action="${basepath}/super/userList" method="post" id="searchUserBeanCopyForm">
                  <input id="userName" type="hidden" name="userName" style="width:115px;" value="${userBean.userName!""}"/>
                  <input id="userId" type="hidden" name="userId" style="width:115px;" value="${userBean.userId!""}"/>
                  <input id="userType" type="hidden" name="userType" style="width:115px;" value="${userBean.userType!"-1"}"/>
                  <input id="pageNo" type="hidden" name="pageNo" style="width:115px;" value="0"/>
                </form>
                </#if>
                <form action="${basepath}/super/userList" method="post" id="searchUserBeanForm">
                    <table class="us-query-table">
                        <tr>
                            <th>用户名称：</th>
                            <td><input id="userName" type="text" name="userName" style="width:115px;" <#if userBean??>value="${userBean.userName!""}"</#if>></td>
                            <th>用户ID：</th>
                            <td><input id="userId" type="text" name="userId" style="width:115px;" <#if userBean??>value="${userBean.userId!""}"</#if>></td>
                            <th>用户类型：</th>
                            
                            <td><@ist.userTypeSelect all='-1' key='${userBean.userType!"-1"}'/></td>
                        </tr>
                    </table>
                    <a class="adaptiveButton brightRed_btn" id="searchBeanButton">
                        <span class="left"></span>
                        <span class="center center_1">搜&nbsp;索</span>
                        <span class="right"></span>
                    </a>
                </form>
                </section>
                <section class="usResult" id="userItem">
                    <table>
                        <thead>
                            <tr>
                                <th>用户ID</th>
                                <th>用户名</th>
                                <th>用户类型</th>
                                <th>用户邮箱</th>
                                <th>qq</th>
                                <th>用户性别</th>
                                <th>创建日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <#if pagination.data??>
                         <#list pagination.data as userInfo>
                         <tr>
                            <td>${userInfo.userId}</td>
                            <td>${userInfo.userName!""}</td>
                            <td>${userInfo.userType!""}</td>
                            <td>${userInfo.email!""}</td>
                            <td>${userInfo.qq!""}</td>
                            <td>${userInfo.sex!""}</td>
                            <td>${userInfo.createTime?string('yyyy-MM-dd HH:mm')}</td>
                            <td><a href="javascript:void(0)" onclick="changeUserType('${userInfo.userId}','${userInfo.upGradeId}','${userInfo.userName}','${userInfo.upGrade}');">${userInfo.upGrade}</a>&nbsp;&nbsp;
                            <a href="javascript:void(0)" onclick="changeUserType('${userInfo.userId}','${userInfo.downGradeId}','${userInfo.userName}','${userInfo.downGrade}');">${userInfo.downGrade}</a>
                            </td>
                        </tr> 
                         </#list>
                        
                         </#if>
                    </table>
                    <@pView.pagination param1='2' param2='3' param3='33'/>
                </section>
            </div>
        </div>
</@iubt.baseHtml>