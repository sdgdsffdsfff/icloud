 <section class="usResult" id="juhuasuanItem">
                    <table>
                        <thead>
                            <tr>
                                <th>链接</th>
                                <th>Perfer</th>
                                <th>访问IP</th>
                                <th>用户会话</th>
                                <th>访问时间</th>
                            </tr>
                        </thead>
                        <#if pagination??>
                         <#list pagination.data as detail> 
                        <tr>
                            <td><a href="${taobaohosthref}${detail.perfer!""}" target="_blank">${detail.perfer!""}</a></td>
                            <td>${stringUtils('${detail.perferHost!""}','60','0')}</td>
                            <td>${detail.perferIp!""}</td>
                            <td>${detail.otherParam!""}</td>
                            <td>${detail.createTime?string('MM-dd HH:mm')}</td>
                        </tr>
                        </#list>
                        </#if>
                    </table>
                </section>