function setfindpwd() {
	var validator = $("#findpwdForm").validate({
		rules : {
			password : {
				required : true,
				rangelength : [ 4, 20 ]
			},
			confirm_password : {
				required : true,
				equalTo : '#password'
			}
		},
		messages : {
			password : {
				required : "请输入密码",
				minlength : jQuery.validator.format("密码不能小于{0}个字符"),
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符")
			},
			confirm_password : {
				required : "请输入确认密码",
				minlength : "确认密码不能小于5个字符",
				equalTo : "两次输入密码不一致"
			},
		}
	});

	$("#findpwd_button").click(function() {
		if ($("#findpwdForm").valid()) {
			$("#findpwdForm").submit();
		}
	});
}

$(document).ready(function() {
	setfindpwd();
});