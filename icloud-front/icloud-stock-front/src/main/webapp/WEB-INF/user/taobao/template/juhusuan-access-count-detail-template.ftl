 <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>用户名</th>
                                <th>日期</th>
                                <th>个人流量</th>
                                <th>个人有效流量</th>
                                <th>代理流量</th>
                                <th>全部流量</th>
                                <th>全部有效流量</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as accessCount> 
                        <tr>
                            <td>${accessCount.userName!""}</td>
                            <td>${accessCount.createTime?string('MM-dd HH:mm')}</td>
                            <td>${accessCount.count!""}</td>
                            <td>${accessCount.validCount!""}</td>
                            <td>${(accessCount.allCount-accessCount.count)}</td>
                            <td>${accessCount.allCount!""}</td>
                            <td>${accessCount.allValidCount!""}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>