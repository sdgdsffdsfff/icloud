<#import "/weixin/phoneHtmlBase.ftl" as phb/>
<#macro mainContainer current="" title="必有网"  keywords="必有网" description="必有网" jsFiles=[] cssFiles=[]>
<@phb.htmlBase title=title keywords=keywords description=description jsFiles=jsFiles cssFiles=cssFiles  emedObjects=[]>
<#nested/>
<#include "/weixin/weixin-footer.ftl"/>
<script>
  wx.config({
      debug: false,
      appId: 'wxf8b4f85f3a794e77',
      timestamp: 1420774989,
      nonceStr: '2nDgiWM7gCxhL8v0',
      signature: '1f8a6552c1c99991fc8bb4e2a818fe54b2ce7687',
      jsApiList: [
        'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'hideMenuItems',
        'showMenuItems',
        'hideAllNonBaseMenuItem',
        'showAllNonBaseMenuItem',
        'translateVoice',
        'startRecord',
        'stopRecord',
        'onRecordEnd',
        'playVoice',
        'pauseVoice',
        'stopVoice',
        'uploadVoice',
        'downloadVoice',
        'chooseImage',
        'previewImage',
        'uploadImage',
        'downloadImage',
        'getNetworkType',
        'openLocation',
        'getLocation',
        'hideOptionMenu',
        'showOptionMenu',
        'closeWindow',
        'scanQRCode',
        'chooseWXPay',
        'openProductSpecificView',
        'addCard',
        'chooseCard',
        'openCard'
      ]
  });
</script>
</@phb.htmlBase>
</#macro>