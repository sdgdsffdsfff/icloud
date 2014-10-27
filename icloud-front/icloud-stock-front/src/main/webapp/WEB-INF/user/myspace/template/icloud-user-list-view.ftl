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
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as user> 
                        <tr>
                            <td>${user.userName!""}</td>
                            <td>${user.userEmail!""}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>