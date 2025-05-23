

$(document).ready(function() {
		 $('body').bind('cut copy paste', function (e) {
        e.preventDefault();
    });
   
    //Disable mouse right click
    $("body").on("contextmenu",function(e){
        return false;
    });
	
	});


function printDivOptimize(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
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
	   // popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><style>table td{font-size:12px;} table th{font-size:13px !important;}</style></head><body onload="window.print()"  oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' + innerContents + '</html>');
       popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><link rel="stylesheet" href="js/printWatermark/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}
function pdfFileSizeValidation(val,myfile,id,size,doc_file_lbltik,lbl_id,x) {


if(myfile ==""){
		document.getElementById(doc_file_lbltik).innerHTML="";
}

var sizedoc = val.files[0].size;

document.getElementById(doc_file_lbltik).innerHTML="";
var txtlen=val.files[0].name.split('.');
	
	if(txtlen[0].length > 30)
	{
		$("#"+id).val("");
		document.getElementById(doc_file_lbltik).innerHTML= "<b class='errorClass' ><i class='fa fa-exclamation' ></i>File Name length must be less than or equal to 30</b>";
		return false;
	} 

$.post("getfilelist?"+key+"="+value, {mysize : sizedoc}).done(function(j) {

if(j == "true"){
document.getElementById(lbl_id).innerHTML="";
	document.getElementById(doc_file_lbltik).innerHTML="";
	
	var ext = myfile.split('.').pop();
	if(ext.toLowerCase() !="pdf"){
		$("#"+id+x).val("");
		document.getElementById(doc_file_lbltik).innerHTML="";
		document.getElementById(lbl_id+x).innerHTML="<i class='fa fa-exclamation'></i>Only Allowed *.pdf File Extension";
		
		return false;
		
	}
	
	else{
		document.getElementById(lbl_id).innerHTML="";
		document.getElementById(doc_file_lbltik).innerHTML="<i class='fa fa-exclamationstik'></i>File Upload";
		
		return false;
	}
}

else{
document.getElementById(doc_file_lbltik).innerHTML="";
document.getElementById(lbl_id).innerHTML="<i class='fa fa-exclamation'></i>File Size Upto "+j+" Kb!";
document.getElementById(""+id+"").value="";
return false;
}
}).fail(function(xhr, textStatus, errorThrown) { });

	return true;
}

function printDivOptimizehelp(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol').hide();
	$("div#divShow").empty();
	$("div#divShow").show();
	
 	var row="";
 	var printLbl = allLbl; 
 	var printVal = allVal; 
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
			/*row +='<div class="col-6 col-sm-3 col-md-3"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';*/
			row +='<div class=col-6 col-6"><label class=" label_left form-control-label" id="lbluc">'+ printLbl[i]+'</label> ';
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
	    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><link rel="stylesheet" href="js/printWatermark/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
		   watermarkreport();
	   
	   popupWinindow.document.close();
	   $("div#divShow").hide();
	   $('.lastCol').show();
}