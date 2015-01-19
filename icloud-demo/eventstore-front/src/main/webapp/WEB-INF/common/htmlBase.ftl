<#macro htmlBase title keywords="event" description="event store demo" jsFiles=[] cssFiles=[] emedObjects=[]>
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
	<link rel="shortcut icon" href="${basepath}/resources/images/icloud/favicon.ico" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/icloud_reset.css?v=1" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/icloud.css?v=1" />
	<link rel="stylesheet" href="${basepath}/resources/css/icloud/jquery.bigautocomplete.css?v=1" />

	<#list cssFiles as css>
		<link rel="stylesheet" href="${basepath}/resources/css/${css}?v=1" />
	</#list>
	<#-- js -->
	<script type="text/javascript" src="${basepath}/resources/js/common/jquery.min.js?v=1"></script>
	<script type="text/javascript" src="${basepath}/resources/js/common/jquery.bigautocomplete.js?v=1"></script>
	<script type="text/javascript" src="${basepath}/resources/js/icloud/icloud.js?v=1"></script>
	<script type="text/javascript" src="${basepath}/resources/js/icloud/autocomplete.js?v=1"></script>
	<!--[if IE]>
	<script src="${basepath}/resources/js/common/html5.js?v=1"></script>
	<![endif]-->

	<#list jsFiles as js>
		<script src="${basepath}/resources/js/${js}?v=1"></script>
	</#list>

	<#if emedObjects??>
	<#list emedObjects as emed>
		<#noescape>${emed}</#noescape>
	</#list>
	</#if>

	<#-- global js vars -->
	<script type="text/javascript">
		var basepath = '${basepath}';
		var sessionId='${(session.id)!}';
	</script>

</head>
<body>
	<#nested/>
</body>
</html>
</#escape>
</#macro>