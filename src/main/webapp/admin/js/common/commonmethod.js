$(function() {
	$.fn.Autocomplete = function(a, b, c, d, e, x, z) {
		$.ajax({
			type : 'POST',
			url : b,
			data : d,
			success : function(response) {
				response.sort();
				var a = [];
				if (e != "") {
					for (var i = 0; i < response.length; i++) {
						a[response[i][e]] = response[i][e];
					}
				} else {
					for (var i = 0; i < response.length; i++) {
						a[response[i]] = response[i];
					}
				}
				$(c).autocomplete({
					data : a,
					limit : '10',
				});
			}
		});
	};


	$.fn.Autocomplete2 = function(a, b, c, d, x, z, e) {
		$(c).autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : b + '?' + key + "=" + value,
					data : d,
					success : function(data) {
						var a = [];
						var length = data.length - 1;
						if (data.length != 0) {
							var enc = data[length].substring(0, 16);
						}
						for (var i = 0; i < data.length; i++) {
							a[i] = dec(enc, data[i]);
						}
						response(a);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					if (e == "" || e == null) {
						alert("Please Input Valid Data...");
						$(c).val('');
						$(z).val('');
						$(c).focus();
						return false;
					}
				}
			},
			select : function(event, ui) {
				var w = ui.item.value;
				
				if(w != "")
				{	
					if (x != "" && x != null) {
						
						if(x == "getMNHDistinctICDList_ID")
						{	
							var a = w.split("-");
							w = a[0];
						}
						
						$.post(x + '?' + key + "=" + value, {
							y : w
						}, function(j) {
							var enc = j[j.length - 1].substring(0, 16);
							var n1 = dec(enc, j[0]);
						
							if (j == "") {
								alert("Please Input Valid Data...");
								$(c).val('');
								$(z).val('');
								$(c).focus();
								return false;
							} else {
								$(z).val(n1);
							}
						});
					}
				}
			}
		});
	};
	$.fn.onchange = function(x, y, z) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			$(z).val(j);
		});
	};
	$.fn.onchange2 = function(x, y, z) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			for (var i = 0; i < z.length; i++) {
				$('#' + z[i] + '').val(j[0][i]);
			}
		});
	};
	$.fn.onSelect = function(x, y, z, w) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			var options = '<option value="' + "-1" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < j.length; i++) {
				options += '<option value="' + j[i][w[0]] + '" name="'
						+ j[i][w[1]] + '" >' + j[i][w[1]] + '</option>';
			}
			$(z).html(options);
		});
	};
	$.fn.JsonGet = function(x, y) {
		$.post(x + '?' + key + "=" + value, {
			y : y
		}, function(j) {
			a = j;
		});
	};
	$.fn.getCurDt = function(a) {
		var d = new Date();
		var Fulldate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + d.getDate()).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getExDt = function(a) {
		var d = new Date();
		var Fulldate = (d.getFullYear() + 1) + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + d.getDate()).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getCurDt2 = function(a) {
		var d = new Date();
		var Fulldate = ("0" + d.getDate()).slice(-2) + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-" + d.getFullYear();
		$(a).val(Fulldate);
	};
	$.fn.getFirDt = function(a) {
		var d = new Date();
		var Uploaddate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2) + "-" + ("01").slice(-2);
		$(a).val(Uploaddate);
	};
	$.fn.getMthYr = function(a) {
		var d = new Date();
		var Fulldate = d.getFullYear() + "-"
				+ ("0" + (d.getMonth() + 1)).slice(-2);
		$(a).val(Fulldate);
	};
	$.fn.getPrintData = function(a) {
		var nra = $(a).html();
		var nrl = '<link rel="stylesheet" href="js/autoComplate/nrcss.css">';
		var nrw = window.open('', 200, 300);
		nrw.document.write(nrl + nra);
		nrw.window.print();
		nrw.document.close();
	};
	$.fn.getPrintDiv1 = function(a) {
		var nra = $(a).html();
		var nrw = window.open('', 200, 300);
		nrw.document.write(nra);
		nrw.window.print();
		nrw.document.close();
	};
	$.fn.getPrintDiv = function(a, b) {
		var pagesize = "L";
		var nrUrl = window.location.protocol + "//" + window.location.hostname + ":" + window.location.port;
		var nra = $(a).html();
		var nrPage = "<html><head>";
		nrPage = nrPage + "<link rel='stylesheet' href='../admin/js/common/nrcssp.css'>";
		nrPage = nrPage + "<style>table td{font-size:12px;} table th{font-size:13px !important;color:black;} </style> </head><body>";
		nrPage = nrPage + "<input type='button' onclick='window.print();' value='Print'>";
		nrPage = nrPage + "<div style='width: 276mm; height: 190mm;'>";
		nrPage = nrPage + "<div width='100%' class='nrTableHeading' style='text-align:center;font-size:18px;color:black;'><b><u>" + b + "</u></b></div>";
		nrPage = nrPage + nra + "</div></body></html>";
		if (pagesize == "L") {
			var nrw = window.open('', 'abc','left=100,top=50,width=1090,height=500');
		} else {
			var nrw = window.open('', 'abc','left=200,top=50,width=750,height=500');
		}
		nrw.document.write(nrPage);
		nrw.document.close();
	};
	$.fn.nrURLPopup = function(url, winName, w, h, scroll) {
		var popupWindow = null;
		LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
		TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
		settings = 'toolbar=no,height=' + h + ',width=' + w + ',top=' + TopPosition + ',left=' + LeftPosition + ',scrollbars=' + scroll + ',noresizable';
		popupWindow = window.open(url, winName, settings)
	};
	$.fn.tbtoExcel = function(b) {
		var a1 = $(b).html();
		var a2 = '<link rel="stylesheet" href="js/autoComplate/nrcss.css">';
		let file = new Blob([ a1 ], {
			type : "application/vnd.ms-excel"
		});
		let url = URL.createObjectURL(file);
		let a = $("<a />", { href : url, download : "MMSDOWN.xls" }).appendTo("body").get(0).click();
	};
});

//-------------------mnh---------------------

/*age calculation form dob */

function calculate_age(obj){                
    var from_d=$("#"+obj.id).val();
    var from_d1=from_d.substring(6,10);
    var from_d2=from_d.substring(3,5);
    var from_d3=from_d.substring(0,2);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    $("#age_year").val(year);
//    document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
    //document.getElementById('age_year1').value = year;
    
    if (month == undefined)
            {
            month = 0;
            }
    document.getElementById('age_month').value = month;
    
}



function calculate_age_edit(val){        
    
    if(val != "")
    {
    var from_d=val;
    
    var from_d1=from_d.substring(0,4);
    var from_d2=from_d.substring(7,5);
    var from_d3=from_d.substring(10,8);
    var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
    /* var to_d=$("#dt_of_joining").val();
    var to_d1=to_d.substring(0,4);
    var to_d2=to_d.substring(7,5);
    var to_d3=to_d.substring(10,8); */
    var today=new Date();
    var to_d3 = today.getDate();
    var to_d2 = today.getMonth() + 1;
    var to_d1 = today.getFullYear();        
    var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
    if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
    var year = to_d1 - from_d1        
    var month = to_d2 - from_d2
    }
    if(to_d2 > from_d2 && to_d3 < from_d3){
            var year = to_d1 - from_d1        
            var month = to_d2 - from_d2 - 1
            }
    if(from_d2 > to_d2){
            var year = to_d1 - from_d1 - 1
            var month1 = from_d2 - to_d2
            var month = 12 - month1
    }
    if(from_d2 == to_d2 && from_d3 > to_d3){
            var year = to_d1 - from_d1 - 1
            var days = from_d3 - to_d3
    }
    if(from_d2 == to_d2 && to_d3 > from_d3){
            var year = to_d1 - from_d1 
            var days =  to_d3 - from_d3 
            
    }
    if(from_d2 == to_d2 && to_d3 == from_d3){
            var year = to_d1 - from_d1 
            var days = 0
    }
    //days
    if(from_d2 > to_d2 && from_d3 > to_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(from_d2 > to_d2 && to_d3 > from_d3){
            var m = from_d2 - to_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1
            var days =  m2
    }
    if(to_d2 > from_d2   && to_d3 > from_d3){
            var m =  to_d2 - from_d2 
            var n = m * 30
            var m1 =  to_d3 - from_d3 
            var m2 = n+m1        
            var days =  m2 
    
    }
    if(to_d2 >  from_d2 && from_d3 > to_d3){
            var m = to_d2 - from_d2   
            var n = m * 30
            var m1 = from_d3 - to_d3 
            var m2 = n+m1
            var days =  m2
            
    }
    $("#age_year").val(year);

    document.getElementById('age_month').value = month;
    
    }
    else
    {
            $("#age_year").val("0");

            document.getElementById('age_month').value = "0";
    }
    
}

function quarter_validate(u_q){
	
	var d = new Date();
	var year = d.getFullYear();
	var Month = d.getMonth();
	//var Month = curDate.substring(4,5);
	
	 var quarter="";
		 
		
		 if(Month > 0 && Month <=3)
		 {
			
			 quarter="q1";
		 }
		 else if (Month > 3 && Month <=6)
		 {
			 
			 quarter="q2";
		 }
		 else if (Month > 6 && Month <=9)
		 {
			 
			 quarter="q3";
		 }
		 else
		 {
			 
			 quarter="q4";
		 }
		
		 var user_q = u_q.substr(1, 1); 
	     var current_q = quarter.substr(1, 1); 
	   
		if(parseInt(user_q) >  parseInt(current_q))
		{ 
			
			 return 0;
		}
		return 1;
		
} 
 


function DOB_from_Year_Month()
{
	
	
		
		var cntOfYears =  $("#age_year").val();
		var cntOfMonths =$("#age_month").val();
		if(cntOfMonths == "")
		{
			cntOfMonths =0;
		}
		var cntOfDays = 0;
		
		var birthDate = moment().subtract(cntOfYears, 'years').subtract(cntOfMonths, 'months').subtract(cntOfDays, 'days');

		
		document.getElementById("date_of_birth1").value = birthDate.format("YYYY-MM-DD");
	
	


}


function fire_validate(u_q){
	
	var d = new Date();
	var year = d.getFullYear();	
	var Month = d.getMonth() + 1;
	
	
	  var firing_event_qe="";
	  
		// 
		if(Month >= 1 || Month <= 3)
		{
			firing_event_qe ="3"
		}
		else if(Month >= 4 || Month <= 6)
		{
			firing_event_qe ="6"
		}
		else if(Month >= 7 || Month <= 9)
		{
			firing_event_qe ="9"
		}
		else if(Month >= 10 || Month <= 12)
		{
			firing_event_qe ="12"
		}
		if( firing_event_qe <  u_q ){
			return 0;
		}
		
		return 1;
} 