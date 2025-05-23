function closereject() 	 
 {
	 var id=document.getElementById('rejectid_model').value;
		$('#rrr'+id).attr('data-target','#')
	 
 }

function printDivOptimize1(divName,header,allLbl,allVal,statusCol) {
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	$('.lastCol,#thAction,#thAction1').hide();
	
	$("div#divShow").empty();
	$("div#divShow").show();
 	var row="";
 	var printLbl = allLbl; //["Type :", "WE/PE/FE/GSL No. :", "Table Title :"];
 	var printVal = allVal; //[$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title1').value];
	    
 	row += "<div style='background:#fff;'><h4 style='margin-top:20px;text-align:center;text-decoration: underline;'><b>RESTRICTED</b></h4></div>"+
				"<table style='width:100%;'>"+
					"<tr>"+
				   		"<td align='left' style='padding-right:50px;'>"+
				   			"<img src='js/miso/images/indianarmy_smrm5aaa.jpg' style='height: 50px;'>"+
				   		"</td>"+
				   		"<td align='center'>"+
				   			"<h4 style='text-decoration: underline;'><b>DTE GENERAL INFO SYS</b></h4>"+
				   			"<h5>"+header+"</h5>"+
				   		"</td>"+
				   		"<td align='right'>"+
				   			"<img src='js/miso/images/dgis-logo.png' style='max-width: 155px; height: 50px;'>"+ 
				   		"</td>"+
				   	"</tr>";
 		row += "</table>";
 	
 		row += "<table style='width:100%;'>";
 		for (i = 0; i < printLbl.length; i++) {
 			row += "<tr>";
 			row += 		"<td align='left'>";
 			row += 				"<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
 			row += 		"</td>";
 			row +=		"<td align='left'>";
 			row +=			"<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
 			row +=		"</td>";
			
 			if(printLbl[i++] != undefined && printLbl.length > i){
	 			row += 		"<td align='left'>";
	 			row += 				"<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
	 			row += 		"</td>";
	 			row +=		"<td align='left'>";
	 			row +=			"<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
	 			row +=		"</td>";
	 		}
 			row +=	"</tr>";
 		}
 		row += "</table>";
		
	 $("div#divShow").append(row); 
	 
	 $("div#divwatermark").val('').addClass('watermarked'); 
 	 watermarkreport();	
	
	 /*var divToPrint=document.getElementById("divPrint");
	 var newWin= window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	 //newWin.document.write(divToPrint.outerHTML);
	 newWin.document.write('<html ><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false"> ' +divToPrint.innerHTML+  ' </html>');
	 newWin.print();
	 newWin.document.close();*/
	
	 
		var popupWinindow
	 	var innerContents = document.getElementById(divName).innerHTML;
	    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	    popupWinindow.document.open();
	    popupWinindow.oncontextmenu = function () {
		 	 return false;
		}
	   	popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'+innerContents+'</html>');
	   	popupWinindow.document.close();
	   	$("div#divShow").hide();
	 
	   	if($(statusCol).val() != "all")
			$('.lastCol,#thAction,#thAction1').show();
}


function printDivOptimize(divName,header,allLbl,allVal,statusCol) {
	$('.lastCol,#thAction,#thAction1').hide();
	
	$("div#divShow").empty();
	$("div#divShow").show();
 	var row="";
 	var printLbl = allLbl; //["Type :", "WE/PE/FE/GSL No. :", "Table Title :"];
 	var printVal = allVal; //[$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title1').value];
 	row += "<div style='background:#fff;'><h4 style='margin-top:20px;text-align:center;text-decoration: underline;'><b>RESTRICTED</b></h4></div>"+
		"<table style='width:100%;'>"+
			"<tr>"+
		   		"<td align='left' style='padding-right:50px;'>"+
		   			"<img src='js/miso/images/indianarmy_smrm5aaa.jpg' style='height: 50px;'>"+
		   		"</td>"+
		   		"<td align='center'>"+
		   			"<h4 style='text-decoration: underline;'><b>DTE GENERAL INFO SYS</b></h4>"+
		   			"<h5 style='text-transform: uppercase;'>"+header+"</h5>"+
		   		"</td>"+
		   		"<td align='right'>"+
		   			"<img src='js/miso/images/dgis-logo.png' style='max-width: 155px; height: 50px;'>"+ 
		   		"</td>"+
		   	"</tr>";
	row += "</table>";
	
	row += "<table style='width:100%;'>";
	for (i = 0; i < printLbl.length; i++) {
		row += "<tr>";
		row += 		"<td align='left'>";
		row += 				"<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
		row += 		"</td>";
		row +=		"<td align='left'>";
		row +=			"<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
		row +=		"</td>";
	
		if(printLbl[i++] != undefined && printLbl.length > i){
			row += 		"<td align='left'>";
			row += 				"<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
			row += 		"</td>";
			row +=		"<td align='left'>";
			row +=			"<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
			row +=		"</td>";
		}
		row +=	"</tr>";
	}
	row += "</table>";
		
	$("div#divShow").append(row); 
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	let popupWinindow
	let innerContents = document.getElementById(divName).innerHTML;
	popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWinindow.document.open();
	popupWinindow.oncontextmenu = function () {
		return false;
	}
	popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	popupWinindow.document.close();
	$("div#divShow").hide();
	 
	if($(statusCol).val() != "all")
		$('.lastCol,#thAction,#thAction1').show();
}


function printDivOptimize12(divName,header,allLbl,allVal,statusCol) {
    $('.lastCol,#thAction,#thAction1').hide();
    $("div#divShow1").empty();
    $("div#divShow1").show();
     var row="";
     var printLbl = allLbl; //["Type :", "WE/PE/FE/GSL No. :", "Table Title :"];
     var printVal = allVal; //[$('input:radio[name=we_pe]:checked').val(),document.getElementById('we_pe_no').value,document.getElementById('table_title1').value];
                     row += "<div style='background:#fff;'><h4 style='text-align:center;text-decoration: underline; font-size: 13px; '><b>RESTRICTED</b></h4></div>"+
                            "<table style='width:100%;'>"+
                                    "<tr>"+
                                               "<td align='left' style='padding-right:50px;'>"+
                                               "<img src='js/miso/images/indianarmy_smrm5aaa.jpg'  class='img-fluid' alt='logo1' style='height:60px;width: 60px;'>"+
                                               "</td>"+
                                               "<td align='center'>"+
                                                       "<h4 style='text-decoration: underline; font-size: 15px;'><b>DTE GENERAL INFO SYS</b></h4>"+
                                                       "<h5 style='text-transform: uppercase;font-size: 13px; padding-top: 8px;'><b><u>"+header+"</u></b></h5>"+
                                               "</td>"+
                                               "<td align='right'>"+
                                               "<img src='js/miso/images/dgis-logo.png'  class='img-fluid' alt='logo' style='float: right; width: 95px;'>"+
                                               "</td>"+
                                       "</tr>";
                    row += "</table>";
                    
                    row += "<table style='width:100%;'>";
                    for (i = 0; i < printLbl.length; i++) {
                            row += "<tr>";
                            row +=                 "<td align='left'>";
                            row +=                                 "<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
                            row +=                 "</td>";
                            row +=                "<td align='left'>";
                            row +=                        "<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
                            row +=                "</td>";
                    
                            if(printLbl[i++] != undefined && printLbl.length > i){
                                    row +=                 "<td align='left'>";
                                    row +=                                 "<label style='font-size:14px;color:maroon;font-weight:700' class='label_left form-control-label' id='lbluc'>"+ printLbl[i]+"</label>";
                                    row +=                 "</td>";
                                    row +=                "<td align='left'>";
                                    row +=                        "<label style='font-size:14px;color:#3048CF;font-weight:700' id='uploaded_wepelbl'>"+ printVal[i]+"</label>";
                                    row +=                "</td>";
                            }
                            row +=        "</tr>";
                    }
                    row += "</table>";
            
     $("div#divShow1").append(row); 
    
     $("div#divwatermark").val('').addClass('watermarked'); 
     watermarkreport();
     let popupWinindow
        
     let innerContents = document.getElementById(divName).innerHTML;
    
        popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
        popupWinindow.document.open();
        popupWinindow.oncontextmenu = function () {
                      return false;
             }
       /*popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');*/
        popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/printWatermark/printWatermark.css"><style> table td{font-size:12px;} table th{font-size:12px !important;} </style></head><body onload="window.focus(); window.print();  window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
     
       popupWinindow.document.close();
       $("div#divShow1").hide();
       
     
       if($(statusCol).val() != "all")
       $('.lastCol,#thAction,#thAction1').show();
}

