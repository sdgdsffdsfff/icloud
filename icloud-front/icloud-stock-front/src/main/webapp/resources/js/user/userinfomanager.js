function userInfoManager() {
	var validator = $("#baseInfoForm").validate({
		rules : {
			qq : {
				required : false,
				number : true,
				rangelength : [ 5, 20 ]
			},
			chinaName : {
				required : false,
				rangelength : [ 1, 50 ],
			}
		}
	});
	
	$(".btn_edit").click(function() {
		$("#tbl_mainBusinessContactInfo :text").removeClass("disabled").prop("readonly", false);
		$(this).hide();
		$(".btn_save").show();
	});
	$(".btn_save").click(function(){
		if ($("#baseInfoForm").valid()) {
			$("#tbl_mainBusinessContactInfo :text").addClass("disabled").prop("readonly", true);
			$(this).hide();
			$(".btn_edit").show();
			$("#baseInfoForm").submit();
		}
	});
}

$(document).ready(function() {
	userInfoManager()  ;
});