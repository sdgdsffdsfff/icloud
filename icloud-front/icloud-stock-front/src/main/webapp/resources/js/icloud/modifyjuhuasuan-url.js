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
	$("#modifyjuhuasuanButton").click(function() {
		if ($("#juhuasuanUrlForm").valid()) {
			updateSumbit();
		}
	});

}

function updateSumbit() {
	/**
	 * 获得所有值
	 */
	var infoTable = document.getElementById("infoTable");
	var moreUrlText = "";
	for (var i = 0; i < infoTable.rows.length; i++) {
		moreUrlText = moreUrlText + infoTable.rows[i].cells[0].outerText
				+ "#####";
	}
	$("#moreUrl").val(moreUrlText);
	$("#juhuasuanUrlForm").submit();
}

// $("#juhuasuanUrlForm").submit();
$(document).ready(function() {
	onclickButton();
});

function addMoreUrl() {
	/**
	 * 获得input的值
	 */
	var text = $("#tmpUrlText").val();
	if (text == "") {
		return;
	}
	urlId = urlId + 1;
	$("#tmpUrlText").val("");
	addReadyUrlInfo(urlId, text);
}

function deleteMoreUrl(urlid) {
	var infoTable = document.getElementById("infoTable");
	var row = document.getElementById(urlid);
	infoTable.deleteRow(row.rowIndex);
}

function addReadyUrlInfo(urlid, fileName) {
	// 用表格显示
	var infoTable = document.getElementById("infoTable");
	var row = infoTable.insertRow();
	row.id = urlid;
	var col1 = row.insertCell();
	var col2 = row.insertCell();
	col2.align = "right";
	col1.innerHTML = fileName;
	col2.innerHTML = "<a href='javascript:deleteMoreUrl(\"" + urlid
			+ "\")'>删除</a>";
	// col1.style.width = "250px";
	col1.style.cssText = "width:250px;word-break: break-all;word-wrap : break-word; overflow:hidden;"
	col2.style.width = "50px";
}