<#import "/user/user-template/icloud-user-base-template.ftl" as iubt/>
<@iubt.baseHtml current='用户列表' jsFiles=[] cssFiles=[] >
<div class="us-body">
            <div class="tab_item">
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                <form action="${basepath}/super/userList" method="post" id="searchBeanForm">
                    <table class="us-query-table">
                        <tr>
                            <th>用户名称：</th>
                            <td><input id="userName" type="text" name="userName" style="width:115px;" <#if urlBean??>value="${urlBean.name!""}"</#if>></td>
                            <th>用户ID：</th>
                            <td><input id="userId" type="text" name="userId" style="width:115px;" <#if urlBean??>value="${urlBean.name!""}"</#if>></td>
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
                            <td><a href="javascript:void(0)" onclick="pauseUser('${userInfo.userId}','${userInfo.statusId}');">${userInfo.statusOp}</a>&nbsp;&nbsp;
                            <#if userInfo.promotionOp??>
                            <a href="javascript:void(0)" onclick="promoteUser('${userInfo.userId}','${userInfo.promotionId}');">${userInfo.promotionOp}</a>
                            </#if>
                            </td>
                        </tr> 
                         </#list>
                         </#if>
                    </table>
                    <span>没有数据</span>
                </section>
            </div>
        </div>
</@iubt.baseHtml>