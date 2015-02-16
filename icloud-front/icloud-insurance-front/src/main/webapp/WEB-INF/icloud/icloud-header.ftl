<#macro header current>
<header class="main-header">
    <div class="header-wrapper"/>
    <div class="main-menu">
       <a class="logo" href="${basepath}/index"><h1>必有网</h1></a>
       <nav class="main-nav">
         <ul class="clearfix">
         	<li <#if current=="行情">class="first active"</#if>><a href="${basepath}/stock/stockMenu">行情</a></li>
            </ul>
       </nav>
       <nav class="main-links">
          <ul class="clearfix">
          <#if !(currentUserInfo())?? >
            <li class="first"><a href="${basepath}/facade/icloudLogin">登录</a></li>
            <li class="first">游客,你好</li>
          <#else>
            <li class="first"><span class="hyper-link">
                <u>${(currentUserInfo().userName)}</u>
                <i class="arrow-down"></i>
                <div class="hyper-text">
                    <a href="${basepath}/usertb/tbList">我的空间</a>
                    <a href="${basepath}/user/icloudLogout">退出</a>
                </div>
            </span></li>
          </#if>
            </ul>
          </nav>
    </div>
</header>
</#macro>