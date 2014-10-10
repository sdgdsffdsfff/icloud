function onclickButton() {
	$("#addJuhuasuanUrl_id").click(function() {
		$.layer({
			type : 2,
			title : '链接操作',
			shadeClose : true,
			maxmin : true,
			fix : false,
			// area: ['1024px', 500],
			area : [ '1024px', ($(window).height() - 50) + 'px' ],
			close : function(index) {
				reload();
			},
			iframe : {
				src : basepath + '/usertb/addJuhuasuanUrlView'
			}
		});
	});

	$("#searchBeanButton").click(function() {
		$("#searchBeanForm").submit();
	});
}

$(document).ready(function() {
	onclickButton();
});

function reload(index) {
	// 当前页面重新刷新
	document.location.reload();
}
function juhuasuanSeachLoading(pageNo) {
	/**
	 * 设置值
	 */
	$("input#pageNo").val(pageNo);
	$("#searchBeanCopyForm").submit();
}

function reviewTheUrl(code) {
	$.layer({
		type : 2,
		title : '链接操作',
		shadeClose : true,
		maxmin : true,
		fix : false,
		area : [ '1024px', ($(window).height() - 50) + 'px' ],
		iframe : {
			src : basepath + '/usertb/juhuasuanUrlView?code=' + code
		}
	});
}
function modifyTheUrl(code) {
	$.layer({
		type : 2,
		title : '链接操作',
		shadeClose : true,
		maxmin : true,
		fix : false,
		area : [ '1024px', ($(window).height() - 50) + 'px' ],
		iframe : {
			src : basepath + '/usertb/modifyJuhusuanUrlView?code=' + code
		}
	});
}
