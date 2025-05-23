$( document ).ready(function() {
    $("#btnExport").click(function () {
        $("div#mctReport").btechco_excelexport({
            containerid: "mctReport"
           , datatype: $datatype.Table
        });
    });
});