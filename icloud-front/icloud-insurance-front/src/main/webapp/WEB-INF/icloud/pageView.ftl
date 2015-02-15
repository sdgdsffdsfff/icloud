<#macro pagination param1 param2 param3>
    <#if (pageView)??>
    <div class="pageList">
        <div class="pager-wrapper">
            <ul class="pager">
            	<#if (pageView.prePage)??>
                  <li class="last">
                    <a ${getPageUrl('${param1}','${param2}','${param3}','${(pageView.prePage.pageNo)}')}>上一页</a>
                  </li>
                </#if>
                <#if (pageView.firstPage)??>
                	<li>
                        <a ${getPageUrl('${param1}','${param2}','${param3}','${(pageView.prePage.pageNo)}')})>${(pageView.firstPage.pageNo+1)}</a>
                    </li>
                    <li>...</li>
                </#if>
                <#if (pageView.pageList)??>
                	<#list pageView.pageList as pageItem>
                	    <#if pageItem.hasUrl=false>
                		 <li class="active">
                    		<a href="#">${(pageItem.pageNo+1)}</a>
                		 </li>
                		 <#else>
                		   <li>
                		      <a ${getPageUrl('${param1}','${param2}','${param3}','${(pageItem.pageNo)}')}>${(pageItem.pageNo+1)}</a>
                           </li>
                		 </#if>
                	</#list>
                </#if>
                <#if (pageView.lastPage)??>
                    <li>...</li>
                	<li>
                	    <a ${getPageUrl('${param1}','${param2}','${param3}','${(pageView.lastPage.pageNo)}')}>${(pageView.lastPage.pageNo+1)}</a>
                    </li>
                </#if>
                <#if (pageView.nextPage)??>
                   <li class="next">
                      <a ${getPageUrl('${param1}','${param2}','${param3}','${(pageView.nextPage.pageNo)}')}>下一页</a>
                   </li>
                </#if>
            </ul>
        </div>
    </div>
    </#if>
</#macro>