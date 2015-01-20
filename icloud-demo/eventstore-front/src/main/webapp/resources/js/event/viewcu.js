function jianliSn(aid, version) {
	$.layer({
		shade : [ 0 ],
		area : [ 'auto', 'auto' ],
		dialog : {
			msg : '为其建立最新快照',
			btns : 2,
			type : 4,
			btn : [ '确定', '取消' ],
			yes : function() {
				location.href = basepath + "/customer/takesn?aid=" + aid
						+ "&version=" + version;
			},
			no : function() {

			}
		}
	});
}