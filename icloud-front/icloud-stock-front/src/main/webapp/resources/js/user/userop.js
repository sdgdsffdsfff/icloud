function pauseUser(userId, operation) {
	var tip = "确定要 暂定该用户？"
	if (operation == 0) {
		tip = "确定要 启动该用户？";
	}
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
					operation : operation,
					operatorId : 1
				};
				$.getJSON(basepath + "/user/operateUser", data, function(data) {
					layer.msg(data.tip, 1, 1);
					reload();
				});
			},
			no : function() {
			}
		}
	});
}
function reload(){
	document.location.reload();
}
function promoteUser(userId, operation) {
	var tip = "确定要 取消该用户的代理资格？"
		if (operation == 0) {
			tip = "确定要 为该用户添加代理资格？";
		}
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
						operation : operation,
						operatorId : 2
					};
					$.getJSON(basepath + "/user/operateUser", data, function(data) {
						layer.msg(data.tip, 1, 1);
						reload();
					});
				},
				no : function() {
				}
			}
		});
}