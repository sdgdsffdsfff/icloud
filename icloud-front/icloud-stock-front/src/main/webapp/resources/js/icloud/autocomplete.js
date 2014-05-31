/**股票名称自动补全*/
function stockNameAutoComplete() {
    $("#quick-search").bigAutocomplete({
        data: [{
                title: "book"
            },
            {
                title: "blue"
            },
            {
                title: "fool"
            },
            {
                title: "bus"
            }],
        callback: function (data) {
            alert(data);
        }
    });
//     var url_ = "ajax.php";
//    $("#nn").bigAutocomplete({url:url_});
}


$(document).ready(function () {
    stockNameAutoComplete();
});