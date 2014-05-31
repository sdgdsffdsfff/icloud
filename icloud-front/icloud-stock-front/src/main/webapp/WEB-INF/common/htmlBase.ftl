<#macro htmlBase title keywords="股票行情,交易,研究报告" description="股票股价,行情,新闻,财报,数据" jsFiles=[] cssFiles=[] emedObjects=[]>
<#assign staticHost=host>
<#escape x as x?html>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" >
	<title>${title}</title>
	<meta charset="UTF-8">
	<meta name="keywords" content="${keywords}">
	<meta name="description" content="${description}">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/icloud_reset.css?v=${getStaticResourceVersion()}" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/icloud.css?v=${getStaticResourceVersion()}" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/jquery.bigautocomplete.css?v=${getStaticResourceVersion()}" />

	<#list cssFiles as css>
		<link rel="stylesheet" href="${basepath}/resources/css/${css}?v=${getStaticResourceVersion()}" />
	</#list>
	<#-- js -->
	<script type="text/javascript" src="${basepath}/resources/js/common/jquery.min.js?v=${getStaticResourceVersion()}"></script>
	<script type="text/javascript" src="${basepath}/resources/js/common/jquery.bigautocomplete.js?v=${getStaticResourceVersion()}"></script>
	<script type="text/javascript" src="${basepath}/resources/js/icloud/icloud.js?v=${getStaticResourceVersion()}"></script>
	<script type="text/javascript" src="${basepath}/resources/js/icloud/autocomplete.js?v=${getStaticResourceVersion()}"></script>
	<!--[if IE]>
	<script src="${staticHost}/resources/js/common/html5.js?v=${getStaticResourceVersion()}"></script>
	<![endif]-->

	<#list jsFiles as js>
		<script src="${basepath}/resources/js/${js}?v=${getStaticResourceVersion()}"></script>
	</#list>

	<#if emedObjects??>
	<#list emedObjects as emed>
		<#noescape>${emed}</#noescape>
	</#list>
	</#if>

	<#-- global js vars -->
	<script type="text/javascript">
		var basepath = '${basepath}';
		var host = '${staticHost}';
		var sessionId='${(session.id)!}';
	</script>

</head>
<body>
	<#nested/>
</body>
</html>
</#escape>
</#macro>