function findpwd() {
	var validator = $("#findpwdForm").validate({
		rules : {
			email : {
				required : true,
				email : true,
				remote : basepath + "/userManager/validateEmail?requiredTrue=false"
			}
		},
		messages : {
			email : {
				required : "请输入Email地址",
				email : "请输入正确的email地址",
				remote : "您输入的email地址不存在"
			}
		}
	});

	$("#findpwd_button").click(function() {
		if ($("#findpwdForm").valid()) {
			$("#findpwdForm").submit();
		}
	});
}

$(document).ready(function() {
	findpwd();
});