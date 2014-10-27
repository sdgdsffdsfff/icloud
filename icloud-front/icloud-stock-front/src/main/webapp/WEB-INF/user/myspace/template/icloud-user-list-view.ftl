 <section class="usResult" id="userItem">
                    <table>
                        <thead>
                            <tr>
                                <th>用户名</th>
                                <th>邮箱</th>
                                <th>级别</th>
                                <th>状态</th>
                                <th>分销</th>
                                <th>推荐人</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as user> 
                        <tr>
                            <td><a href="${basepath}/userpt/dayStat?memberId=${user.userId}">${user.userName!""}</a></td>
                            <td>${user.email!""}</td>
                            <td>${user.level!""}</td>
                            <td>${user.status!""}</td>
                            <td>${user.promotion!""}</td>
                            <td>${user.fatherName!""}</td>
                            <td><a href="#" onclick="reviewTheUrl('${user.userId}');">暂停</a>&nbsp;&nbsp;
                            <a href="#" onclick="modifyTheUrl('${user.userId}');">禁止拉人</a>
                            </td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>