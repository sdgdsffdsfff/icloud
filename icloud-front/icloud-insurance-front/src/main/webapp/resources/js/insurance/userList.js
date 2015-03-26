function onclickButton() {
	$("#searchBeanButton").click(function() {
		$("#searchUserBeanForm").submit();
	});
}

$(document).ready(function() {
	onclickButton();
});

function reload(index) {
	// 当前页面重新刷新
	document.location.reload();
}

function userListloading(pageNo) {
	/**
	 * 设置值
	 */
	$("input#pageNo").val(pageNo);
	$("#searchUserBeanCopyForm").submit();
}

function changeUserType(userId, opId, userName, tip) {
	var tip = "确定要 修改 用户： " + userName + " " + tip;
	$.layer({
		shade : [ 0 ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : tip,
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				var data = {
					userId : userId,
					opId : opId
				};
				$.getJSON(basepath + "/super/changeUserType", data, function(
						data) {
					layer.msg(data.tip, 1, 1);
					reload();
				});
			},
			no : function() {
			}
		}
	});
}
