function updatepwd() {
	var validator = $("#basePasswordForm").validate({
		rules : {
			oldpwd : {
				required : true,
				rangelength : [ 4, 20 ]
			},
			newpwd : {
				required : true,
				rangelength : [ 4, 20 ]
			},
			confirmPwd : {
				required : true,
				equalTo : '#newpwd'
			}
		},
		messages : {
			oldpwd : {
				required : "请输入老的密码",
				minlength : jQuery.validator.format("密码不能小于{0}个字符"),
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符")
			},
			newpwd : {
				required : "请输入新的密码",
				minlength : jQuery.validator.format("密码不能小于{0}个字符"),
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符")
			},
			confirmPwd : {
				required : "请输入确认密码",
				minlength : "确认密码不能小于4个字符",
				equalTo : "两次输入密码不一致"
			},
		}
	});

	$("#modifypwd_button").click(function() {
		if ($("#basePasswordForm").valid()) {
			$("#basePasswordForm").submit();
		}
	});
}

$(document).ready(function() {
	updatepwd();
});