<#import "/weixin/paper-main-container.ftl" as pmc/>
<@pmc.mainContainer current = "阅读" jsFiles=[""] cssFiles=[""]>
<div class="lbox_close wxapi_form">
  <h3 id="menu-basic">${paper.title}</h3>
  <span class="desc">${paper.content}</span>
</div>
</@pmc.mainContainer>