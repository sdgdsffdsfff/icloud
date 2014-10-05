function onclickButton() {
	$("#addJuhuasuanUrl_id").click(function() {
		 $.layer({
	            type : 2,
	            title: '增加一个链接',
	            shadeClose: true,
	            maxmin: true,
	            fix : false,  
//	            area: ['1024px', 500],    
	            area : [ '1024px', ($(window).height() - 50) + 'px' ],
	            iframe: {
	                src : basepath+'/usertb/addJuhuasuanUrlView'
	            } 
	        });
			
	});
}

$(document).ready(function() {
	onclickButton();
});