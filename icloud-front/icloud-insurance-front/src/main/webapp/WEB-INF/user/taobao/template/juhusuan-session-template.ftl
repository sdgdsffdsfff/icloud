 <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>链接ID</th>
                                <th>用户ID</th>
                                <th>访问总数</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as session> 
                        <tr>
                            <td><a href="${taobaohosthref}${session.icloudUrl!""}" target="_blank">${taobaohosthref}${session.icloudUrl!""}</a></td>
                            <td>${session.userId!""}</td>
                            <td>${session.count!""}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>