function closereject() 	 
 {
	 var id=document.getElementById('rejectid_model').value;
		$('#rrr'+id).attr('data-target','#')
	 
 }

function printDivOptimize(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol,#thAction,#thAction1').hide();
	
	$("div#divShow").empty();
	$("div#divShow").show();
 	var row="";
 	var printLbl = allLbl; //["Type :", "WE/PE/FE/GSL No. :", "Table Title :"];
 	var printVal = allVal; //[$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title1').value];
	    row +='<div class="print_content"> ';
	 	row +='<div class="print_logo"> ';
		row +='<div class="row"> ';
		row +='<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
		row +='<div class="col-6 col-sm-6 col-md-6"><h3>'+header+'</h3> </div> ';
		row +='<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
		row +='</div> ';
		row +='</div> ';
		row +='	<div class="print_text"> ';
		row +='<div class="row"> ';
		
		var i;
		for (i = 0; i < printLbl.length; i++) {
			//alert("printLbl.length"+printLbl.length);
			row +='<div class="col-12 col-sm-6 col-md-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
			row +='<label class="label_right" id="uploaded_wepelbl">'+ printVal[i]+'</label> </div>';
		}
		row +='</div> ';
		row +='</div> ';
		row +='</div> ';
		
	 $("div#divShow").append(row); 
	
	 let popupWinindow
	    let innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		 }
	   //popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><style>table td{font-size:12px;} table th{font-size:13px !important;}</style></head><body onload="window.print()"  oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' + innerContents + '</html>');
       popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><link rel="stylesheet" href="js/miso/mmsDashboard/mmsWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   watermarkreport();
	  
	   popupWinindow.document.close();
	   //document.location.reload();
	   $("div#divShow").hide();
	 
	   if($(statusCol).val() != "all")
		    $('.lastCol,#thAction,#thAction1').show();
}




