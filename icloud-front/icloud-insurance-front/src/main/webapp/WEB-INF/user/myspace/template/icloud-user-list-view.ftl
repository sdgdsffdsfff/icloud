 <section class="usResult" id="userItem">
                    <table>
                        <thead>
                            <tr>
                                <th>用户名</th>
                                <th>邮箱</th>
                                <th>级别</th>
                                <th>状态</th>
                                <th>分销</th>
                                <th>当天流量</th>
                                <th>当天有效流量</th>
                                <th>推荐人</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <#if pagination.data??>
                         <#list pagination.data as user> 
                        <tr>
                            <td><a href="${basepath}/usertb/tbMemberList?memberId=${user.userId}">${user.userName!""}</a></td>
                            <td>${user.email!""}</td>
                            <td>${user.level!""}</td>
                            <td>${user.status!""}</td>
                            <td>${user.promotion!""}</td>
                            <td>${user.currentDayCount!""}</td>
                            <td>${user.currentDayValidCount!""}</td>
                            <td>${user.fatherName!""}</td>
                            <td><a href="javascript:void(0)" onclick="pauseUser('${user.userId}','${user.statusId}');">${user.statusOp}</a>&nbsp;&nbsp;
                            <#if user.promotionOp??>
                            <a href="javascript:void(0)" onclick="promoteUser('${user.userId}','${user.promotionId}');">${user.promotionOp}</a>
                            </#if>
                            &nbsp;
                            <a href="${basepath}/usertb/trafficUserView?memberId=${user.userId}">流量</>
                            </td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>