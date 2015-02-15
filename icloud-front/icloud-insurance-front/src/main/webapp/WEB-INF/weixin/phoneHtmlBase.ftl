<#macro htmlBase title keywords description jsFiles=[] cssFiles=[] emedObjects=[]>
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
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
	<link rel="shortcut icon" href="${basepath}/resources/images/icloud/favicon.ico" />
	<link rel="stylesheet" href="${basepath}/resources/css/weixin/style.css?v=${getStaticResourceVersion()}" />
	
	<#if (cssFiles?size > 1)>
	<#list cssFiles as css>
		<link rel="stylesheet" href="${basepath}/resources/css/${css}?v=${getStaticResourceVersion()}" />
	</#list>
	</#if>
	<script src="${basepath}/resources/js/weixin/jweixin-1.0.0.js?v=${getStaticResourceVersion()}"></script>
	<#if (jsFiles?size > 1)>
	<#list jsFiles as js>
		<script src="${basepath}/resources/js/${js}?v=${getStaticResourceVersion()}"></script>
	</#list>
    </#if>
    
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
<body ontouchstart="">
	<#nested/>
</body>
</html>
</#escape>
</#macro>