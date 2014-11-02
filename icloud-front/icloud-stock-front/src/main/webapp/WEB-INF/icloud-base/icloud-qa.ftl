<#import "/icloud/icloud-main-container.ftl" as imc/>
<@imc.mainContainer current = "短链接" jsFiles=[""] cssFiles=[""]>
 <h1 class="au-title">
   <span>疑问解答</span>
   </h1>
   <div class="au-block clearfix">
   <p>1. 什么是有效访问？什么是代理流量</p>
   <p>
   <center>
   	<img src="${basepath}/resources/images/taobao/qa1.jpg"/>
   </center>
   </p>
   <p>答: 1) 普通访问里含有用户在淘宝，天猫网站上专门点击链接买的，也包含一些电脑自动点击这些链接的。电脑自动点击这些链接的，不会构成购买的行为，因此这部分访问流量是垃圾流量。有效访问量是指排除了电脑自动点击的访问量后，用户专门点击的访问量。</p>
   <p>&nbsp;&nbsp;2)代理流量是指你的下属们的访问量。
   <p><span class="redFont">注意：但是需要注意的是，如何判断电脑自动生成的访问量是比较困难的，所以有效访问量的统计会有一些误差，因此仅提供参考。</span></p>
</@imc.mainContainer>