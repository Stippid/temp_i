<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>


	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
			<form>			
				<div class="card-header">
					<h5>SEARCH INTER UNIT TRANSFER</h5>
<!-- 					<h6 class="enter_by"> -->
<!-- 						<span style="font-size: 12px; color: red;">(To be entered by UNIT)</span> -->
<!-- 					</h6> -->
				</div>
				<div class="card-body card-block">	   
					<div class="col-md-12">
						 <div class="col-md-6" id=""  >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">  </strong><label class=" form-control-label">Category </label>
								</div>
								<div class="col-md-8">									
									<select name="type" id="type" class="form-control"   >
										<option value="0">--Select--</option>
										<option value="computing" >Computing</option>
										<option value="peripherals" >Peripherals</option>
									</select>										      
								</div>							
							</div>
						</div>					
						
					</div>
			 </div>						 
		</form>

	<!-- POST OUT FORM START  -->
			
	<div id="div_post_out" class="tabcontent" style="">
		<form id="post_out_form">
					<div class="card">
						<div class="card-header">
							<h5>TRANSFER FROM UNIT</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
					 <input type="hidden" id="p_id" name="p_id"	class="form-control autocomplete" autocomplete="off" value="0"> 
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">								
									 <input type="text" id="from_sus_no" name="from_sus_no" onkeyup="search_sus(this,'unit_name') ; specialcharecter(this)" tabindex="-1" onchange="To_sus_check()"
										class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)" >									 
								</div>
							</div>
						</div>
						 <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">From Unit Name </label>
								</div>
								<div class="col-md-8">
											
								 <input type="text" id="unit_name" name="unit_name" onkeyup="search_unit(this,'from_sus_no') ; specialcharecter(this)" tabindex="-1" 
										class="form-control autocomplete" maxlength="50" autocomplete="off" onchange="To_sus_check();">
									 
							 	
								</div>
							</div>
						</div> 
					</div>
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Machine No </label>
								</div>
								<div class="col-md-8">								
									<input type="text" id="machine_no" name="machine_no" class="form-control autocomplete" autocomplete="off"
										onchange="getDataMachine_no()" onkeyup="getMachine_no(this,'from_sus_no') ; specialcharecter(this)"
										maxlength="9" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Status </label>
								</div>
								<div class="col-md-8">								
									<select name="status" id="status" class="form-control"   >
										<option value="0">Pending</option>
										<option value="1" >Approved</option>
									</select>
								</div>
							</div>
						</div>
							
					
					</div>					
					
					
					
					
					
				 </div>
				 <div class="card-header">
					<h5>TRANSFER TO UNIT</h5>
				</div>
				<div class="card-body card-block">	   
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To SUS No </label>
								</div>
								<div class="col-md-8">
										<input type="text" id="to_sus_no" name="to_sus_no" onkeyup="search_sus(this,'to_unit_name') ; specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off" onchange="To_sus_check();" maxlength="8" onkeypress="return AvoidSpace(event)" >
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_unit_name" name="to_unit_name" onkeyup="search_unit(this,'to_sus_no') ; specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off" maxlength="50" onchange="To_sus_check();" >
									
									
								</div>
							</div>
						</div>
					</div>
			 </div>
						<div class="card-footer" align="center" class="form-control">
							<a href="" class="btn btn-success btn-sm">Clear</a> 
							<input type="button" class="btn btn-info btn-sm"
						onclick="getAssetTransfData();" value="Search">
						</div>
						<div class="container" id="divPrint" style="display:none;">
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="searchAssetTable"
			class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
			<thead>
				<tr>
					<th style="font-size: 15px; text-align: center">Ser No</th>
					<th style="font-size: 15px; text-align: center">Machine No</th>
					<th style="font-size: 15px; text-align: center">From Unit SUS No</th>
					<th style="font-size: 15px; text-align: center">To Unit SUS No</th>
					<th class="actionclass" style="font-size: 15px; text-align: center">ACTION</th>
				</tr>
			</thead>
			<tbody id="searchassettbody">				
			</tbody>
		</table>
	</div>
</div>
					</div>
				</form>
			</div>
		 		
			</div>
		</div>
	</div>
	
	<c:url value="Approve_AssetTransfer" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="idApp">
	 <input type="hidden" name="idApp" id="idApp" value="0"/> 
	 <input type="hidden" name="machine_idApp" id="machine_idApp" value="0"/> 
	 <input type="hidden" name="catApp" id="catApp" value="0"/> 
	  <input type="hidden" name="to_susApp" id="to_susApp" value="0"/> 
	 
</form:form> 

	<c:url value="Reject_AssetTransfer" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="idRej">
	 <input type="hidden" name="idRej" id="idRej" value="0"/> 
	 <input type="hidden" name="catRej" id="catRej" value="0"/> 
	 
</form:form> 

<script>
	$(document).ready(function() {
		
		$.ajaxSetup({
			async : false
		});
		
// 		$("div#category_hide").hide();
		
		
		var roleSusNo='${roleSusNo}';
	
		if(roleSusNo!='' && roleSusNo!=null){
			$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:roleSusNo}).done(function(data) {
    	  		var l=data.length-1;
    	  		var enc = data[l].substring(0,16);    	   	 
    	  	 	$("#unit_nameIn1").val(dec(enc,data[0]));
    	  	 	$("#unit_name").val(dec(enc,data[0]));
    	  			
    	  		}).fail(function(xhr, textStatus, errorThrown) {
    	  });

			$('#i_to_sus_no').val(roleSusNo);
			$('#i_to_sus_no').attr('readonly',true);
			$('#from_sus_no').val(roleSusNo);
			$('#from_sus_no').attr('readonly',true);
			$('#unit_name').attr('readonly',true);
			$('#unit_nameIn1').attr('readonly',true);
		}
	
		   $('#type').on('change', function() {
			      if ( $('#type').val() == 'postout') {
			    	  $("#div_post_out").show();
			    	  $("#div_post_in").hide();
			    	  
			    	   var sus_no = '${roleSusNo}';
			    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
			    	  		var l=data.length-1;
			    	  		var enc = data[l].substring(0,16);    	   	 
			    	  	 	$("#unit_name").val(dec(enc,data[0]));
			    	  			
			    	  		}).fail(function(xhr, textStatus, errorThrown) {
			    	  });
			    	  
			      }
			      if ( $('#type').val() == 'postin' && $('#service_category').val() != '0') {
			    	  $("#div_post_in").show();
			    	  $("#div_post_out").hide();
			    	  
			    	  			      }
			      if ( $('#type').val() == '' || $('#service_category').val() == '0') {
			    	  $("#div_post_in").hide();
			    	  $("#div_post_out").hide();
			      }
			      fn_service_category_change();
		 	});	
		   $('#service_category').on('change', function() {
			      if ( $('#type').val() == 'postout'  && $('#service_category').val() != '0') {
			    	  $("#div_post_out").show();
			    	  $("#div_post_in").hide();
			    	  
			    	   var sus_no = '${roleSusNo}';
			    	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
			    	  		var l=data.length-1;
			    	  		var enc = data[l].substring(0,16);    	   	 
			    	  	 	$("#unit_name").val(dec(enc,data[0]));
			    	  			
			    	  		}).fail(function(xhr, textStatus, errorThrown) {
			    	  });
			    	  
			      }
			      if ( $('#type').val() == 'postin' && $('#service_category').val() != '0') {
			    	  $("#div_post_in").show();
			    	  $("#div_post_out").hide();
			      }
			      if ( $('#type').val() == '' || $('#service_category').val() == '0') {
			    	  $("#div_post_in").hide();
			    	  $("#div_post_out").hide();
			      }
			      fn_service_category_change();
		 	});	
		   
		   jQuery(function($){ //on document.ready 
			  	 datepicketDate('out_auth_dt1');
			   	 datepicketDate('out_auth_dt');
				 datepicketDate('dt_of_sos');
				 datepicketDate('dt_of_tos');
				 datepicketDate('in_auth_dt');				 
				 datepicketDate('app_dt');				 
			});   
 
			try{
				if(window.location.href.includes("msg=")){
					var url = window.location.href.split("?")[0];
				    window.location = url;
			    } 
			}catch (e) {
				// TODO: handle exception
			}
			
			
		   
	});

</script>
<script>
  
function Search() {

	$("#personnel_no1").val($("#personnel_no").val());
	$("#rank1").val($("#rank").val());
	$("#to_sus_no1").val($("#to_sus_no").val());
	$("#from_sus_no1").val($("#from_sus_no_out").val());
	$("#type1").val($("#type").val());
	document.getElementById('searchForm').submit();
}

function getAssetTransfData(){
	
	var type=$("#type").val();
	
	
	var machine_no=$("#machine_no").val();
	var to_sus_no=$("#to_sus_no").val();
	var from_sus_no=$("#from_sus_no").val();
	var status=$("#status").val();
	if($('#type').val()=='0'){
		alert("Please Select Category");
		$("#type").focus();
		return false;
	}
	
	var cpurl;
	if(type!='0'){
		if(type=='computing' ){
			cpurl='Search_Computing_Transfer?';
		}
		else if(type=='peripherals'){
			cpurl='Search_Peripheral_Transfer?';
		}
		
		if(to_sus_no!='' &&  from_sus_no!=''){
			if(to_sus_no==from_sus_no){
				alert("From Sus and To Sus Can't Be Same");
				return false;
			}
		}
		
		$.post(cpurl + key + "=" + value,{ from_sus_no:from_sus_no,to_sus_no:to_sus_no,machine_no:machine_no, status:status},function(k) {console.log(k);
			$('#searchassettbody').empty();
			if(k.length > 0)
    		 {   
    			
    			if(k[0][0] == "error")
    			{
    				 alert(k[0][1]);
    				 return false;
    			}
    			if(k[0][0] != "error")
	    		{
    			  var x=0
	    			 for(i=0;i<k.length;i++){
	    				 x=x+1;
	    				 $("table#searchAssetTable").append('<tr>'
  	 						+'<td>'+x+'</td>'
  	 						+'<td > '+k[i].machine_no+' </td>'	
  	 						+'<td > '+k[i].from_sus+' </td>'
  	 						+'<td > '+k[i].to_sus+' </td>'
  	 						+'<td class="actionclass" align="center"> '+k[i].action+' </td>'
  	 						+'</tr>');
	 	    			 }
	 	    		
    			  
    			  
	 	    		 if(type=='postin' ){
	 	    			$("#th_tos").show();
						$("#th_sos").hide();
	 	    			 
	 	    			$('.postoutclass').hide();
		 	    			if($("#statusin").val() == 3){
		 	    				$('.remarks').show();
		 	    			}else{
		 	    				$('.remarks').hide();
		 	    			}
	 	    				if($("#statusin").val() == 1){
	 	    					$('.actionclass').hide();
	 	    				}else{
	 	    					$('.actionclass').show();
	 	    				}
	 	    			}
	 	    			else if(type=='postout'){
	 	    			  $('.postoutclass').show();
	 	    			 	if($("#statusin").val() == 3){
		 	    				$('.remarks').show();
		 	    			}else{
		 	    				$('.remarks').hide();
		 	    			}
	 	    				if($("#statusout").val() == 1){
	 	    					$('.actionclass').hide();
	 	    				}else{
	 	    					$('.actionclass').show();
	 	    				}
	 	    				
	 	    				$("#th_tos").hide();
							$("#th_sos").show(); 
	 	    			}	    		 
	    		 $("#divPrint").show();
	    		}
    		 }
    		 else{	    			 
    			 $("#divPrint").show();

				$("table#searchAssetTable").append('<tr><td colspan="5" align="center">Data Not Available</td>'
	 		+'</tr>');

    		 }
    		 colAdj("searchAssetTable");
// 	 		 colAdj("searchassettbody");
		});
		
		
		$("#personnel_no3").val($("#inpersonnel_no").val());
		$("#rank3").val($("#inrank").val());
		$("#to_sus_no3").val($("#to_sus_no").val());
		$("#from_sus_no3").val($("#from_sus_no_out").val());
		$("#type3").val($("#type").val());
		$("#service_category3").val($("#service_category").val());		
	}
	else{		
		 $("#divPrint").hide();
	}	
}

function Approved(id,machine_id,to_sus){
	
	$("#idApp").val(id);
	$("#machine_idApp").val(machine_id);
	$("#to_susApp").val(to_sus);
	$("#catApp").val($("#type").val());
	
// 	$("#unit_sus_noapp1").val(unit_sus_no);
// 	$("#typeapp1").val($("#type").val());
// 	$('#service_categoryAppout').val($('#service_category').val());	
// 	$("#personnel_noapp1").val($("#personnel_no").val());
// 	$("#rankappapp1").val($("#rank").val());
// 	$("#to_sus_noapp1").val($("#to_sus_no").val());
// 	$("#from_sus_noapp1").val($("#from_sus_no_out").val());
// 	$("#statusapp1").val($("#statusout").val());	
	document.getElementById('approveForm').submit();		
}

function Reject(id){
	
	$("#idRej").val(id);
	$("#catRej").val($("#type").val());
	
// 	$("#unit_sus_noapp1").val(unit_sus_no);
// 	$("#typeapp1").val($("#type").val());
// 	$('#service_categoryAppout').val($('#service_category').val());	
// 	$("#personnel_noapp1").val($("#personnel_no").val());
// 	$("#rankappapp1").val($("#rank").val());
// 	$("#to_sus_noapp1").val($("#to_sus_no").val());
// 	$("#from_sus_noapp1").val($("#from_sus_no_out").val());
// 	$("#statusapp1").val($("#statusout").val());	
	document.getElementById('rejectForm').submit();		
}
	//X SUS No

	$("#x_sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#x_sus_no");

		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList_postin?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
	                  var length = data.length-1;
	                  var enc = data[length].substring(0,16);
	                        for(var i = 0;i<data.length;i++){
	                                susval.push(dec(enc,data[i]));
	                        }
	                        var dataCountry1 = susval.join("|");
	                        var myResponse = []; 
					        var autoTextVal=susNoAuto.val();
					$.each(dataCountry1.toString().split("|"), function(i,e){
						var newE = e.substring(0, autoTextVal.length);
						if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
						  myResponse.push(e);
						}
					});      	          
					response( myResponse ); 
		          }
		        });
		      },
			  minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
				var sus_no = ui.item.value;			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
	         }, function(j) {
	                
	         }).done(function(j) {
	        	 var length = j.length-1;
	             var enc = j[length].substring(0,16);
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	 


 ///post in end
</script>
 <script>
	function appNamechng(){
		 
		var appname = $("#app_name").val();
		  
			if(appname == "1" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
					|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE"
					|| appname == "ON LEAVE PENDING RETIREMENT R/R" || appname == "ON STUDY LEAVE"){
				$("#xlist").show();
				}
			else{
				$("#xlist").hide();
				$("#x_sus_no").val("");
				$("#x_list_loc").val("");
			 
		}
	}
	function statusPER(){
		 
		var prefix_per = $("#in_personnel_no").val().substring(0,2);
		 
			if(prefix_per == "NT"){
				 $("#stats").show();
				}
			else{
				 $("#stats").hide();
				 $("#t_status").val("");
			}
	 	
	}
	function removeMinDate(){
		
		setMinDate('out_auth_dt', '01/01/1890');
		setMinDate('in_auth_dt', '01/01/1890');
		setMinDate('dt_of_sos', '01/01/1890');
		setMinDate('dt_of_tos', '01/01/1890');
		
	}
	function To_sus_check() {
		 var to_sus_no = $('#to_sus_no').val(); 
		 var from_sus_no = $('#from_sus_no').val(); 
		
		
		if(to_sus_no==from_sus_no && to_sus_no!='' && from_sus_no!=''){
	
			alert("From And To Sus Can't Be Same");
			$('#to_sus_no').val('');
			$('#to_unit_name').val('');
		}
		
		if(from_sus_no=='' ||  $('#unit_name').val()==''){
			 $("#machine_no").val('');
		}
		
		}
</script>
 
<script>
	//postout save
	function save_IUT() {
		
		var machine_no = $('#machine_no').val();
		var machine_id = $('#p_id').val();
		var to_sus = $('#to_sus_no').val();
		var from_sus = $('#from_sus_no').val();
		 
		if($('#type').val()=='0'){
			alert("Please Select Category");
			$("#type").focus();
			return false;
		}
 
// 		var out_auth_dt = $('#out_auth_dt').val();
// 		if ($("input#personnel_no").val() == "") {
// 			alert("Please Enter Personal No");
// 			$("input#personnel_no").focus();
// 			return false;
// 		}		
// 		if ($("input#out_auth").val().trim()  == "") {
// 			alert("Please Enter Auth No");
// 			$("input#out_auth").focus();
// 			return false;
// 		}
// 		 if($("#out_auth_dt").val().trim() == "DD/MM/YYYY"){
// 			 alert("Please Enter Auth Date");
// 			 $("input#out_auth_dt").focus();
// 			 return false;
// 		 }
// 		if ($("input#out_auth_dt").val().trim()  == "") {
// 			alert("Please Select Date of Auth");
// 			$("input#out_auth_dt").focus();
// 			return false;
// 		}
// 		if ($("input#o_to_sus_no").val() == "") {
// 			alert("Please Select Unit SUS No");
// 			$("input#o_to_sus_no").focus();
// 			return false;
// 		}			 
// 		if($("#dt_of_sos").val()== "DD/MM/YYYY"){
// 			alert("Please Select Date of SOS");
// 			$("input#dt_of_sos").focus();
// 			return false;
// 		 }
// 		if ($("input#dt_of_sos").val() == "") {
// 			alert("Please Select Date of SOS");
// 			$("input#dt_of_sos").focus();
// 			return false;
// 		}
		
// 	if(dt_tos!=null && dt_tos!=""){

		
// 			var newtos = dt_of_sos.split("/");
// 			var pretos=dt_tos.split("/");
			
// 			var newtosM=Number(newtos[1]);
// 			var newtosY=newtos[2];
			
// 			var pretosM=Number(pretos[1]);
// 			var pretosY=pretos[2];
			
// 			if(newtosY==pretosY){
// 				if(newtosM<=pretosM){
// 					alert("Invalid Date of SOS");
// 					$("input#dt_of_sos").focus();
// 					return false;
// 				}
// 			}
// 			else if(newtosY<pretosY){
// 				alert("Invalid Date of SOS");
// 				$("input#dt_of_sos").focus();
// 				return false;
// 			}
// 		}
		
		
		
// 		if ($("input#out_auth_dt").val() == "") {
// 			alert("Please Select Date of Auth");
// 			$("input#out_auth_dt").focus();
// 			return false;
// 		}
// 		if(comm_date!=''){
// 			if(getformatedDate(out_auth_dt) < getformatedDate(comm_date)) {
// 				alert("Date of Auth Should Be Greater Then Date of Commission");
// 				$("input#out_auth_dt").focus();
// 				return false;
// 			}
// 		}
// 		if(dt_tos!=''){
// 			if(getformatedDate(dt_of_sos) < getformatedDate(dt_tos)) {
// 				alert("SOS Should Be Greater Then TOS");
// 				$("input#dt_of_sos").focus();
// 				return false;
// 			}
// 		}
// 		if(comm_date!=''){
// 			if(getformatedDate(dt_of_sos) < getformatedDate(comm_date)) {
// 				alert("SOS Should Be Greater Then Date of Commission");
// 				$("input#dt_of_sos").focus();
// 				return false;
// 			}
// 		}
		
	 var saveUrl;
		if($('#type').val()=='computing'){
			saveUrl='computingTransfer_action?'
		}
		else if($('#type').val()=='peripherals'){
			saveUrl='periferalTransfer_action?'
			
		}
		
		$.post(saveUrl + key + "=" + value, {from_sus : from_sus,to_sus : to_sus,machine_no : machine_no,machine_id :machine_id	},
				function(data) {
         
			if (data == "update")
				alert("Data Updated Successfully");

			else if (parseInt(data) > 0) {
				alert("Transfer successfully.");
				fn_service_category_change();
			} else
				alert(data);
		}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});

		return true;
	}
////out Personal No

	function getMachine_no(obj,id) {
		removeMinDate();
		roleSus=$("#"+id).val();
		machine_no=$("#machine_no").val();
		if(roleSus!=''){
		var personel_no = obj.value;
		var susNoAuto = $("#"+obj.id);
		var perurl;
		if($('#type').val()=='computing'){
			perurl='getmachine_no_CompListApproved?';
		}
		else if($('#type').val()=='peripherals'){
			perurl='getmachine_no_perifListApproved?';
		}
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : perurl + key + "=" + value,
					data : {
						roleSus:roleSus,machine_no:machine_no
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}

						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Personal No");
					;
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
		}
		else{
			alert("please Enter From Sus");
			
			$("#"+id).focus();
			$("#"+id).val('');
			}
		}

	var census_id = null;
	function getDataMachine_no() {

		if($('#type').val()=='computing'){
			$('#compViewDiv').show();
			$('#perifViewDiv').hide();
			
			fn_getComputingData();
		}
		else if($('#type').val()=='peripherals'){
			$('#perifViewDiv').show();
			$('#compViewDiv').hide();
			fn_getPeripheralsData();
		}
			
		
		
	}
	
	
// 	function onCatChange(){
// 		if($('#type').val()=='0'){
// 			$('#type').hide()
// 		}
// 		else if($('#type').val()=='peripherals'){
// 			perurl='getArmy_noListApproved?';
// 		}
// 	}
	//////// post out end
	
	
	function fn_getComputingData(){
		
		var machine_no = $("input#machine_no").val();
		if (machine_no != "") {
			 
	
			var	perurl='GetMachine_noDataComp?';
		
			
		
			$.post(perurl + key + "=" + value, {
				machine_no : machine_no
			}, function(j) {
				$("#p_id").val(j[0].id);
				$("#assets_type").text(j[0].assets_name);
				$("#model_number").text(j[0].model_number);
				$("#mac_address").text(j[0].mac_address);
				$("#ip_address").text(j[0].ip_address);
				$("#processor_type").text(j[0].processor_type);
				$("#ram_capacity").text(j[0].ram);
				$("#hd_capacity").text(j[0].hdd);
				$("#operating_system").text(j[0].os);
				$("#os_firmware").text(j[0].os_firmware);
				$("#deply_envt").text(j[0].dply_env);
				console.log(j);
				
			});
		}
		
		
		$("input#machine_no").attr('readonly', false);
	}
	function fn_getPeripheralsData(){
		
		
		var machine_no = $("input#machine_no").val();
		if (machine_no != "") {
			 
	
			var	perurl='GetMachine_noDataPerif?';
		
			
		
			$.post(perurl + key + "=" + value, {
				machine_no : machine_no
			}, function(j) {
				$("#p_id").val(j[0].id);
				$("#assets_type").text(j[0].assets_name);
				$("#model_number").text(j[0].model_no);
				$("#year_of_proc").text(j[0].year_of_proc);
				$("#year_of_manufacturing").text(j[0].year_of_manufacturing);
				$("#type_of_hw").text(j[0].type_of_hw);
				console.log(j);
				
			});
		}
		
		
		$("input#machine_no").attr('readonly', false);
	}
	
	
	
	
function fn_service_category_change(){
	$("#p_id").val("0");
	
	$("#from_sus_no").val("");
	$("#unit_name").val("");
	$("#to_sus_no").val("");
	$("#to_unit_name").val("");
	$("#machine_no").val("");
	
	
	$("#assets_type").text("");
	$("#model_number").text("");
	$("#mac_address").text("");
	$("#ip_address").text("");
	$("#processor_type").text("");
	$("#ram_capacity").text("");
	$("#hd_capacity").text("");
	$("#operating_system").text("");
	$("#os_firmware").text("");
	$("#deply_envt").text("");
	
	
	$("#year_of_proc").text("");
	$("#year_of_manufacturing").text("");
	$("#type_of_hw").text("");
	$('#compViewDiv').hide();
	$('#perifViewDiv').hide();
	}


function  search_sus(sus_obj,unit_id){
// 	removeMinDate();
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSUSNoList_postin?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                var length = data.length-1;
                var enc = data[length].substring(0,16);
                      for(var i = 0;i<data.length;i++){
                              susval.push(dec(enc,data[i]));
                      }
                      var dataCountry1 = susval.join("|");
                      var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}

function search_unit(obj,id){
// 	removeMinDate();
			var unit_name = obj.value;
				 var susNoAuto=$("#"+obj.id);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList_postin?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	   	          
							response( susval ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Approved Unit Name.");
				        	  $("#"+id).val('');
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
					            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#"+id).val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		
}
	
</script>

