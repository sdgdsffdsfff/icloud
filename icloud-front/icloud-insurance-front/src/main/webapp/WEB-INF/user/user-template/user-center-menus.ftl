<#macro userCenterMenus current>
<aside class="us-nav">
    <nav>
        <h3>用户中心</h3>
        <#if (currentUserInfo())?? && currentUserInfo().isProxy() == true>
        <dl>
            <dt>
                <i class="ucnav_26"></i>保险管理</dt>
            <dd>
                <a <#if current=="增加保险">class="current"</#if> href="${basepath}/proxy/addInsuranceView">增加保险</a>
            </dd>
            <dd>
                <a <#if current=="保险列表">class="current"</#if> href="${basepath}/proxy/insuranceList">保险列表</a>
            </dd>
        </dl>
        </#if>
        <#if (currentUserInfo())?? && currentUserInfo().isSuper() == true>
        <dl>
            <dt>
                <i class="ucnav_15"></i>网站管理</dt>
            <dd>
                <a <#if current=="用户列表">class="current"</#if> href="${basepath}/super/userList">用户列表</a>
            </dd>
            <dd>
                <a <#if current=="保险公司">class="current"</#if> href="${basepath}/super/insuranceCompanyList">保险公司</a>
            </dd>
            <dd>
                <a <#if current=="险种">class="current"</#if> href="${basepath}/super/insuranceCategoryList">险种</a>
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