<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form  name="Search_Computing_Asset_WO" id="Search_Computing_Asset_WO"  method='POST'   >
	<div class="col-md-12" align="center">
	   <div class="card">
	  		<div class="card-header"><h5> GENERATE PERIPHERAL WORK ORDER </h5></div>
			<div class="card-body card-block">
	            <div class="col-md-12">
	            	 <!--   <div class="col-md-6">
		            		<div class="row form-group">
			               		<div class="col-md-4"><label> Wksp Unit Name<span class="star_design">*</span></label></div>
				                  <div class="col-md-8">
				                	 <input type="text" id="wksp_unit_name" name="wksp_unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control autocomplete"  autocomplete="off" >
			               		</div>
		               		</div>
		               </div> -->
		               
		                    <!--   BISAG V2 240822  -->
		                    
		               <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Wksp Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="wksp_unit_name" name="wksp_unit_name" class="form-control autocomplete"  autocomplete="off" maxlength="10"  >				
<%-- 										   <label  id="unit_name_l"><b>${unit_name}</b></label>   --%>
										</div>
						            </div>
	              		</div>
	              		
	               <!--   BISAG V2 240822 END -->
	            		<div class="col-md-6">
							<div class="row form-group">
			               		<div class="col-md-4"><label> W/O Dt <span class="star_design"></span></label></div>
				                <div class="col-md-8">
						         <input type="date" id="wo_dt" name="wo_dt" class="form-control" >
			               		</div>
		               		</div>
		               </div>
		            	
<!-- 		              <div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 			               		<div class="col-md-4"><label> Model Name <span class="star_design">*</span></label></div> -->
<!-- 				                <div class="col-md-7"> -->
<!-- 						         	<select name="model_name" id="model_name" class="form-control"> -->
<!-- 									<option value="0" >--SELECT--</option> -->
<!-- 									</select> -->
<!-- 			               		</div> -->
<!-- 		               		</div> -->
<!-- 		               </div> -->
	            	</div>
	            	
	            	<div class="col-md-12">
	      	
	            
	              <div class="col-md-6">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Dt fwd to Wksp </label></div>
				                <div class="col-md-8">
						          	<input type="date" id="dt_eqpt_reqd_fwd_wksp" name="dt_eqpt_reqd_fwd_wksp" class="form-control"  >
			               		</div>
		               		</div>
		               </div>
		               
		          <!--  BISAG V2 240822  -->
		           
		                 <div class="col-md-6" style="display:none">
							<div class="row form-group">
			               		<div class="col-md-4"><label>SUS No</label></div>
				                <div class="col-md-8">
						          <input type="hidden" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete"  autocomplete="off" maxlength="10"  >
			               		</div>
		               		</div>
		               </div>
		               
		            <!--   BISAG V2 240822 END -->
		               
<!-- 						<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4"><label> Dt From </label></div>
								<div class="col-md-7">
									   <input type="date" id="dt_from" name="dt_from" class="form-control" >
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Dt To </label></div>
				                <div class="col-md-7">
						          	   <input type="date" id="dt_to" name="dt_to" class="form-control" >
			               		</div>
		               		</div>
		               </div> -->
		               
<!-- 		                  <div class="col-md-4"> -->
<!-- 								<div class="row form-group"> -->
<!-- 									<div class="col-md-4"><label> Status</label></div> -->
<!-- 					                <div class="col-md-7"> -->
<!-- 							          <select name="status" id="status" class="form-control" > -->
<!-- 							              <option value="" >--SELECT--</option> -->
<!-- 											<option value="0" >PENDING WORK ORDER</option> -->
<!-- 											<option value="1" >GENERATED WORK ORDER</option> -->
<!-- 										</select> -->
<!-- 				               		</div> -->
<!-- 			               		</div> -->
<!-- 		               		</div> -->
	            	</div>	
	            </div>
	            
	           	 <div class="card-footer" align="center">
					<a href="Work_Order_Search_Peripheral_Asset_Url" class="btn btn-success btn-sm" >Clear</a>
<!-- 	              	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" /> -->
					<input type="button" class="btn btn-success btn-sm" value="W/O Generate"  id="btn-reload" onclick="return SaveWorkOrder();">
	          	</div>
	            				
		</div>
	  <div class="col-md-12">
	   <input type="hidden" id="p_id" name="p_id" value="0">
	  <input type="hidden" id="CheckVal" name="CheckVal">
				   <b><input type=checkbox id='nSelAll' name='tregn'
						onclick='setselectall();'>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b>
			<table id="Peripheral_Asset_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>		
			        	<th>Select To Generate</th>				
						<th>Peripheral Type</th>
						<th>Make Name</th>
						<th>Model Name</th>
						<th>Model Number</th>
						<th>Machine Number</th>
						<th>Type of HW</th>
						<th>Year of Proc</th>
						<th>Defects Obs</th>
						<th>Remarks</th>
			    	</tr>
				</thead>
			</table>
		</div>
	</div>   
</form:form>



 <script>
 function fn_makeName() {
		var assets_name = $("select#asset_type").val();
		$.post("getMakeNameList?" + key + "=" + value, {
			assets_name: assets_name
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#make_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 function fn_modelName() {
		
		
		var make_name = $("select#make_name").val();
		$.post("getModelNameList?" + key + "=" + value, {
			make_name: make_name
		}).done(function(j) {
		
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#model_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 </script>
 


	<script type="text/javascript">
	
	$(document).ready(function() {  
		
		mockjax1('Peripheral_Asset_reporttbl');
		table = dataTable('Peripheral_Asset_reporttbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
		
	});
	
	function data(tableName){
		if(tableName == "Peripheral_Asset_reporttbl"){
			jsondata = [];
			var table = $('#'+tableName).DataTable();
			var info = table.page.info();
		
			var currentPage = info.page;
		    var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];
			
			var asset_type=$("#asset_type").val();
		 	var make_name=$("#make_name").val();
		 	var model_name=$("#model_name").val();
		 	var status=$("#status").val();
		
		
			$.post("Peripheral_Asset_List_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,
				orderType:orderType,asset_type:asset_type,make_name:make_name,model_name:model_name,status:status
				},function(j) {
					
				for (var i = 0; i < j.length; i++) {
					jsondata.push([j[i].chekboxaction,j[i].assets_name,j[i].make_name,j[i].model_name,j[i].model_no,
						j[i].machine_no,j[i].type_of_hw,j[i].year_of_proc,j[i].Defects,j[i].remarks]);
				
				}
				$("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
			});
			$.post("Peripheral_Asset_List_TotalCount?"+key+"="+value,{Search:Search,asset_type:asset_type,make_name:make_name,model_name:model_name,status:status},function(j) {
				FilteredRecords = j;
			});
		}
	}
	
	function EditData(id){        
	    $("#idV").val(id);                
	    document.getElementById('EditForm').submit();            
	}

// 	function Downloaddata(id,wo_id){
// 		$("#Hid_id").val(id);
// 		$("#H_wo_id").val(wo_id);
// 		document.getElementById('downloadForm').submit();		 
// 	}
	
	function Downloaddata(){


// 		$("#Hid_id").val(id);	
		$("#wksp_unit_name1").val(wksp_unit_name);
		$("#wo_dt1").val(wo_dt);
		$("#dt_eqpt_reqd_fwd_wksp1").val(dt_eqpt_reqd_fwd_wksp);
		document.getElementById('typeReport').value='pdfL';
		document.getElementById('downloadForm').submit();		 
	}
	
	function findselected(){
		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();
			
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}
	
	
	function setselectall(){

		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}
		
		var l = $('[name="cbox"]:checked').length;
		 $("#tregn").val(l);
		document.getElementById('tregn').innerHTML = l;
		
	}
	
	function drawtregn(data) {
		var ii=0;
		$("#nrTable").empty();

		for (var i = 0; i <data.length; i++) {
			 var nkrow="<tr id='nrTable' padding='5px;'>";
			 nkrow=nkrow+"<td>&nbsp;&nbsp;";
			 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
			 $("#nrTable").append(nkrow);
			 ii=ii+1;
		}
		$("#tregn").text(ii);
	}
	
	///BISAG V2 240822///
	function SaveWorkOrder(){
		
		findselected();
		
		var a1 = $("#CheckVal").val();

		if(a1 == ""){
			alert("Please Select atleast 1 data to Generate Work Order"); 
		}else{
			
			
		
			var wksp_unit_name = $("#wksp_unit_name").val();
			var unit_sus_no = $("#unit_sus_no").val();
			if(wksp_unit_name=="" || wksp_unit_name==null) {
				alert("Workshop Unit Name is blank");
				return false;
			}
			var wo_no = $("#wo_no").val();
			
			var wo_dt = $("#wo_dt").val();
			var dt_eqpt_reqd_fwd_wksp = $("#dt_eqpt_reqd_fwd_wksp").val();
			

				$.post("GenerateWorkOrderActionPeripheral_P?"+key+"="+value, {wksp_unit_name:wksp_unit_name,wo_no:wo_no,wo_dt:wo_dt,dt_eqpt_reqd_fwd_wksp:dt_eqpt_reqd_fwd_wksp,unit_sus_no:unit_sus_no
					}).done(function(j) {
//	 			alert(j);
				if(j>0){
//	 				alert("Data Saved Sucessfull");
					$("#p_id").val(j);
				}
//	 			Search();
//	 			isvalidData();
			});
			
			var p_id = a1.split(":");

			var p_id1 = $("#p_id").val();

			for(var i=0; i<p_id.length; i++){

				var Defects = $("#Defects"+p_id[i]).val();
				var remarks = $("#remarks"+p_id[i]).val();
				var proc_date = $("#proc_date"+p_id[i]).val();
			

					$.post("GenerateWorkOrderActionPeripheral_Ch?"+key+"="+value, {Defects:Defects,remarks:remarks,proc_date:proc_date,a1:p_id[i],p_id1:p_id1,wo_no:wo_no
						}).done(function(j) {
//	 				alert(j);
					if(j>0){
						
		 				
		 				
		 				
					}
					
		// 			isvalidData();
				}); 
			}
			alert("Data Saved Sucessfully");
					table.ajax.reload();
		}	
	}


	///BISAG V2 240822 END///


	var check_count = 0;
	function checkbox_count(obj,id)
	{
		if(document.getElementById(obj.id).checked == true)
		{ 
			check_count++;
			
		}
		if(document.getElementById(obj.id).checked == false)
		{
			check_count--;
			
		}
		
		document.getElementById('tregn').innerHTML =check_count;
		
	}
	
	///BISAG V2 240822 ///
	
	
	 $("input#wksp_unit_name").keypress(function(){
		 
		 
			var unit_name = this.value;
				 var susNoAuto=$("#wksp_unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getTargetUnitsNameActiveList_IT?"+key+"="+value,
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
				        	  document.getElementById("wksp_unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      },
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
				    
					            $.post("getTargetSUSFromUNITNAME_IT?"+key+"="+value,{unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#unit_sus_no").val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		});
	
	///BISAG V2 240822 END///
	
</script>

<c:url value="Peripheral_Asset_WO_Url" var="EditUrl"/>
	<form:form action="${EditUrl}" method="post" id="EditForm" name="EditForm" modelAttribute="idV">
	  <input type="hidden" name="idV" id="idV" value="0">
</form:form> 

<%-- <c:url value="Download_Peripheral_Asset_WO" var="downloadUrl"/> --%>
<%-- <form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="Hid_id"> --%>
<!--       <input type="hidden" name="Hid_id" id="Hid_id" value="0"> -->
<!--       <input type="hidden" name="H_wo_id" id="H_wo_id" value="0"> -->
<%-- </form:form> --%>


<c:url value="Download_Peripheral_Asset_WO" var="downloadUrl"/>
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="Hid_id">
<!--    <input type="hidden" name="Hid_id" id="Hid_id" value="0"> -->
   <input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="wksp_unit_name1" id="wksp_unit_name1"> 
	<input type="hidden" name="wo_dt1" id="wo_dt1" value="0">
	<input type="hidden" name="dt_eqpt_reqd_fwd_wksp1" id="dt_eqpt_reqd_fwd_wksp1" >
    
</form:form>
 
