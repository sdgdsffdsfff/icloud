function onclickButton() {
	var validator = $("#juhuasuanUrlForm").validate({
		rules : {
			name : {
				required : true
			// remote : basepath + "/userManager/validateUserName"
			},
			taobaoUrl : {
				required : true
			// remote : basepath + "/userManager/validateEmail"
			}
		},
		messages : {
			name : {
				required : "请输入链接名称"
			// remote : "账号已存在，请重新输入"
			},
			taobaoUrl : {
				required : "请输入链接"
			// remote : "email地址已存在，请重新输入"
			}
		}
	});
	$("#addjuhuasuanButton").click(function() {
		if ($("#juhuasuanUrlForm").valid()) {
			$("#juhuasuanUrlForm").submit();
		}
	});

}

$(document).ready(function() {
	onclickButton();
});