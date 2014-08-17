<#macro header current>
<header class="main-header">
    <div class="header-wrapper"/>
    <div class="main-menu">
       <a class="logo" href="${basepath}/index"><h1>必有网</h1></a>
       <nav class="main-nav">
         <ul class="clearfix">
         	<li <#if current=="行情">class="first active"</#if>><a href="${basepath}/stock/stockMenu">行情</a></li>
         	<li <#if current=="爬虫数据">class="first active"</#if>><a href="${basepath}/stock/openMenus">爬虫数据</a></li>
            </ul>
       </nav>
       <div class="search">
       	
         <input id="quick-search" class="quick-search" name="stockCode" type="text" autocomplete="off" placeholder="搜索 股票" class="typeahead">
         
         <span class="icon">
            <i></i>
          </span>
       </div>
       <nav class="main-links">
          <ul class="clearfix">
          <#if !(currentUserInfo())?? >
            <li class="first"><a href="${basepath}/userManager/registerView">注册</a></li>
            <li><a href="${basepath}/user/icloudLogin">登录</a></li>
            <li class="first">游客,你好</li>
          <#else>
            <li class="first"><span class="hyper-link">
                <u>${(currentUserInfo().userName)}</u>
                <i class="arrow-down"></i>
                <div class="hyper-text">
                    <a href="${basepath}/user/baseUserInfo">我的空间</a>
                    <a href="${basepath}/user/icloudLogout">退出</a>
                </div>
            </span></li>
          </#if>
            </ul>
          </nav>
    </div>
</header>
</#macro>