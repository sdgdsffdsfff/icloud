function register() {
	$("#registerForm").validate({
		rules : {
			username : {
				required : true,
				rangelength : [ 4, 16 ],
				remote : basepath + "/userManager/validateUserName"
			},
			email : {
				required : true,
				email : true
			},
			password : {
				required : true,
				rangelength : [ 4, 20 ]
			},
			confirm_password : {
				required : true,
				equalTo : '#password'
			},
			qq : {
				required : false,
				number : true,
				rangelength : [ 5, 20 ]
			},
			telphone : {
				required : true,
				number : true,
				rangelength : [ 5, 20 ]
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符"),
				remote: "账号已存在，请重新输入"
			},
			email : {
				required : "请输入Email地址",
				email : "请输入正确的email地址"
			},
			telphone : {
				required : "请输入电话号码",
				minlength : jQuery.validator.format("密码不能小于{0}个字符"),
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符")
			},
			password : {
				required : "请输入密码",
				minlength : jQuery.validator.format("密码不能小于{0}个字符"),
				rangelength : jQuery.validator.format("请输入{0}~{1}个字符")
			},
			confirm_password : {
				required : "请输入确认密码",
				minlength : "确认密码不能小于5个字符",
				equalTo : "两次输入密码不一致"
			}
		}
	});
}

$(document).ready(function() {
	register();
});