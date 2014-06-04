/** 股票名称自动补全 */
function stockNameAutoComplete() {
	var url_ = basepath + '/stock/stockSearch';
	$("#quick-search").bigAutocomplete(
			{
				url : url_,
				callback : function(data) {
					var redirecturl = "stockBaseDetail?stockCode="
							+ data.result + "&type=-1;"
					window.open(redirecturl);
				}
			});
}

$(document).ready(function() {
	stockNameAutoComplete();
});