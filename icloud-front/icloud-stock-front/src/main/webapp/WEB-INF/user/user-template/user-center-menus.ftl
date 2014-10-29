<#macro userCenterMenus current>
<aside class="us-nav">
    <nav>
        <h3>用户中心</h3>
        <dl>
            <dt>
                <i class="ucnav_07"></i>我的淘宝</dt>
            <#if (currentUserInfo())??>
            	<#if !(currentUserInfo().open)>
            	   该帐号已经暂停，请联系您的代理人！
            	</#if>
            </#if>
            <dd>
                <a <#if current=="所有链接">class="current"</#if> href="${basepath}/usertb/tbList">所有链接</a>
            </dd>
            <dd>
                <a <#if current=="访问数据">class="current"</#if> href="${basepath}/usertb/trafficCurrentDay">访问数据</a>
            </dd>
            <dd>
                <a <#if current=="总体统计">class="current"</#if> href="${basepath}/usertb/allUrlStatistics">总体统计</a>
            </dd>
        </dl>
        <#if currentUserInfo().addUser==true>
        <dl>
            <dt>
                <i class="ucnav_22"></i>下属管理</dt>
            <dd>
                <a <#if current=="增加成员">class="current"</#if> href="${basepath}/user/registerView">增加成员</a>
            </dd>
            <dd>
                <a <#if current=="成员名单">class="current"</#if> href="${basepath}/user/myFollowerList">成员名单</a>
            </dd>
            <dd>
                <a <#if current=="成员流量">class="current"</#if> href="${basepath}/usertb/trafficUserView">成员流量</a>
            </dd>
        </dl>
        </#if>
        <dl>
            <dt>
                <i class="ucnav_22"></i>账户资料</dt>
            <dd>
                <a <#if current=="信息维护">class="current"</#if> href="${basepath}/user/baseUserInfo">信息维护</a>
            </dd>
            <dd>
                <a <#if current=="密码管理">class="current"</#if> href="${basepath}/user/modifyPassword">密码管理</a>
            </dd>
        </dl>
        
    </nav>
</aside>
</#macro>