function userlogin() {
	var validator = $("#loginForm").validate({
		rules : {
			email : {
				required : true,
			},
			password : {
				required : true,
			}
		},
		messages : {
			email : {
				required : "请输入Email地址"
			},
			password : {
				required : "请输入密码"
			}
		}
	});

	$("#login_button").click(function() {
		// validator.resetForm();
		if ($("#loginForm").valid()) {
			$("#loginForm").submit();
		}
	});
}

$(document).ready(function() {
	userlogin();
});