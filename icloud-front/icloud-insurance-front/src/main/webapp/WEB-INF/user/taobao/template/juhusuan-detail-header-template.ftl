<!-- -->
                <section class="us-query clearfix" style=" padding-top:15px; padding-bottom:5px;">
                <#if urlBean??>
                <form action="${basepath}/usertb/trafficCurrentDay" method="post" id="searchBeanCopyForm">
                  <input id="pageNo" type="hidden" name="pageNo" style="width:115px;" value="0"/>
                </form>
                </#if>
                <span>
                    <as class="taobaoDesc">当日访问量:</as><strong title="" class="taobaoName">${currentDayCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <as class="taobaoDesc">昨日访问量:</as><strong title="" class="taobaoName">${lastDayCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <as class="taobaoDesc">总共访问量:</as><strong title="" class="taobaoName">${totalCount!'0'}</strong>
                </span>
                <p>
                 <span>
                    <as class="taobaoDesc">当日有效访问:</as><strong title="" class="taobaoName">${currentDayValidCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <as class="taobaoDesc">昨日有效访问:</as><strong title="" class="taobaoName">${lasyDayValidCount!'0'}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     <as class="taobaoDesc">总共有效访问:</as><strong title="" class="taobaoName">${totalValidCount!'0'}</strong>
                </span>
                </section>
               
                <section class="us-query clearfix" style="padding-top:0px; padding-bottom:5px;">
                <#if url_name=="当月访问量">
                <a class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id" href="${basepath}/usertb/trafficCurrentDay">
                        <span class="left"></span>
                        <span class="center center_1">当日数据</span>
                        <span class="right"></span>
                </a>
                </#if>
                <#if url_name=="当天访问量">
                <a class="adaptiveButton brightRed_btn" id="addJuhuasuanUrl_id" href="${basepath}/usertb/traffic30Day">
                        <span class="left"></span>
                        <span class="center center_1">一个月内的访问量</span>
                        <span class="right"></span>
                </a>
                 </#if>
                </section>