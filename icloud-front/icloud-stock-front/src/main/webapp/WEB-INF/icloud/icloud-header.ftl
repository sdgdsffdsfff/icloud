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
       <div class="search">
       	 <form>
          <input id="quick-search" class="quick-search" name="stockCode" type="text" autocomplete="off" placeholder="搜索 股票" class="typeahead">
         </form>
         <span class="icon">
            <i></i>
          </span>
       </div>
       <nav class="main-links">
          <ul class="clearfix">
            <li class="first"><a href="${basepath}/userManager/registerView">注册</a></li>
            <li><a href="#">登录</a></li>
            <li><span class="hyper-link">
                <u>我的用户</u>
                <i class="arrow-down"></i>
                <div class="hyper-text">
                    <a href="#">我的应用</a>
                    <a href="#">退出</a>
                </div>
            </span></li>
            </ul>
          </nav>
    </div>
</header>
</#macro>