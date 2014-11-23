function updateTaobaoUrl() {
	var validator = $("#baseForm").validate({
		rules : {
			taobaoUrl : {
				required : true
			}
		},
		messages : {
			taobaoUrl : {
				required : "请输入链接名称"
			}
		}
	});

	$("#modifyUrl_button").click(function() {
		if ($("#baseForm").valid()) {
			var taobaoUrl = $("#taobaoUrl").val();
			var resetTaobaoUrl = $("#resetTaobaoUrl").val();
			if (taobaoUrl == resetTaobaoUrl) {
				layer.msg("修改的域名跟原来一致，请确认修改是否正确", 1, 1);
			} else {
				modifyTaobaoUrl(taobaoUrl);
			}
		}
	});
	$("#ssetUrl_button").click(function() {
		$("#taobaoUrl").val($("#resetTaobaoUrl").val());
	});
}

function reload() {
	document.location.reload();
}

function modifyTaobaoUrl(taobaoUrl) {
	var tip = "确定要 修改主域名？"
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
					taobaoUrl : taobaoUrl
				};
				$.getJSON(basepath + "/user/doModifyTaobaoUrl", data, function(
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

$(document).ready(function() {
	updateTaobaoUrl();
});