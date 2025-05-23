<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form name="Peripheral_Asset_WO" id="Peripheral_Asset_WO" action="Peripheral_Asset_WO_Action" modelAttribute="Peripheral_Asset_WO_CMD" >
	<div class="col-md-12" align="center">
	   <div class="card">
	  		<div class="card-header"><h5> PERIPHERAL ASSET WORK ORDER </h5></div>
	  	
			<div class="card-body card-block">
				<div class="col-md-12">
					<!-- 	<div class="col-md-4">
							<div class="row form-group">
			            		<div class="col-md-4"><label> Wksp SUS No<span class="star_design">*</span></label></div>
					                <div class="col-md-7">
					               		<input type="text" id="wksp_sus_no" name="wksp_sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >		                	 
				               		</div>
		               		</div>
		               </div>	 -->	
		               	<div class="col-md-4">
		            		<div class="row form-group">
			               		<div class="col-md-4"><label> Wksp Unit Name<span class="star_design">*</span></label></div>
				                  <div class="col-md-7">
				                	 <input type="text" id="wksp_unit_name" name="wksp_unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control autocomplete"  autocomplete="off" >
			               		</div>
		               		</div>
		               </div>
		            <%--    <div class="col-md-4">
		               		<div class="row form-group">
			            		<div class="col-md-4"><label>Unit<span class="star_design">*</span></label></div>
					                <div class="col-md-7">
					                 	  <label><b>${unit_name}</b></label>
				                <input type="hidden" id="unit_name" name="unit_name" class="form-control " autocomplete="off" value="${unit_name}">				                
				               		</div>
		               		</div>
		               </div>   --%> 
						<div class="col-md-4">
		            		<div class="row form-group">
			               		<div class="col-md-4"><label> W/O No <span class="star_design">*</span></label></div>
				                  <div class="col-md-7">
				                	  <label><b>${WO_NO}</b></label>
				                	  <input type="hidden" id="wo_no" name="wo_no"  class="form-control" autocomplete="off" value="${WO_NO}" > 
				                	 <input type="hidden" id="Hid" name="Hid"  class="form-control" autocomplete="off"  value="${Hid}"> 
			               		</div>
		               		</div>
		               </div>
	            </div>	 	            	            
	          
	        	<div class="col-md-12">
	                
	            	
	            	<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> W/O Dt <span class="star_design">*</span></label></div>
				                <div class="col-md-7">
						         <input type="date" id="wo_dt" name="wo_dt" class="form-control" >
			               		</div>
		               		</div>
		               </div>
	            	     <div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4"><label> Peripheral Assets Type</label></div>
					                <div class="col-md-7">
								         <select name="asset_type" id="asset_type" class="form-control" onchange="fn_makeName();">
										</select>
				               		</div>
		               			</div>
	            			</div> 
	            		<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Make Name <span class="star_design">*</span></label></div>
				                <div class="col-md-7">
						           <select name="make_name" id="make_name" class="form-control" onchange="fn_modelName();">
									</select>
			               		</div>
		               		</div>
		               </div>
		         	</div>	       
	            	
	            <div class="col-md-12"> 
	            	   <div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Model Name <span class="star_design">*</span></label></div>
				                <div class="col-md-7">
						           <select name="model_name" id="model_name" class="form-control">
									</select>
			               		</div>
		               		</div>
		               </div>
		              <div class="col-md-4">
						<div class="row form-group">
			               	<div class="col-md-4"><label>Machine Number<span class="star_design">*</span></label></div>
				               <div class="col-md-7">
				                 <label><b>${Peripheral_Asset_WO_CMD.machine_no}</b></label>
				                <input type="hidden" id="machine_number" name="machine_number" maxlength="30"  class="form-control " autocomplete="off" value="${Peripheral_Asset_WO_CMD.machine_no}">
			               	</div>
		               		</div>
		             	</div>
		             	<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label>Dt of Intro into Service<span class="star_design">*</span></label></div>
				                <div class="col-md-7">
				                   <label id = "dt_of_in"><b></b></label>
				                	<input type="date" id="dt_of_intro" name="dt_of_intro" class="form-control" autocomplete="off" />				          	
			               		</div>
		               		</div>
		               	</div>
		            	
		          </div>	  
	              <div class="col-md-12">	               		          	
	            	
			              <div class="col-md-4">
							<div class="row form-group">
				               	<div class="col-md-4"><label> Last W/O No</label></div>
					               <div class="col-md-7">					              	 	
					              	 	 <label><b id = "last_wo_no">${l_wo_no}</b></label>  				                	
				            	   </div>
			               		</div>
	            			</div>
	            			<div class="col-md-4">
								<div class="row form-group">
								  <div class="col-md-4"><label> Last W/O Dt</label></div>
					                <div class="col-md-7">							          	
							          	 <label  id = "last_wo_dt" ><b>${l_wo_dt}</b></label> 				               			
				               		</div>
		               			</div>
	            			</div>
	            	</div>
	            	
	            	<div class="col-md-12">						
		               <div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Dt fwd to Wksp </label></div>
				                <div class="col-md-7">
						          	<input type="date" id="dt_eqpt_reqd_fwd_wksp" name="dt_eqpt_reqd_fwd_wksp" class="form-control"  >
			               		</div>
		               		</div>
		               </div>
		               <div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4"><label>Defects obs <span class="star_design">*</span></label></div>
								<div class="col-md-7">
									<textarea id="defect_obs" name="defect_obs" class="form-control autocomplete"  autocomplete="off" ></textarea>
								</div>
							</div>
						</div>
	            	</div>
	            </div> 
	            
	           	 <div class="card-footer" align="center">
	           	 	<a href="Peripheral_Asset_WO_Url" class="btn btn-success btn-sm" >Clear</a> 				   
	              	<input type="submit" class="btn btn-primary btn-sm" value="Generate WO" onclick="return Validation();" >  
	              	<a href="Work_Order_Search_Peripheral_Asset_Url" class="btn btn-danger btn-sm">Cancel</a>
	          	</div>	            				
		</div>
	</div>   
</form:form>
<script>
function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}
$(document).ready(function() {
	var authDt=formateDate('${Peripheral_Asset_WO_CMD.proc_date}');	
	$("#dt_of_intro").val(authDt);
	$("#dt_of_intro").attr("disabled", true);
	
	
	
	$.post("getPeripheral2?"+key+"="+value,function(j) {
					var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				var Asset = '${Peripheral_Asset_WO_CMD.assets_type}';
					for ( var i = 0; i < j.length; i++) {
						if(Asset ==  j[i][0]){
							options += '<option value="' + j[i][0] +'" name="'+j[i][1]+'" selected=selected >'+ j[i][1] + '</option>';
						}else{
							options += '<option value="' + j[i][0] +'" >'+ j[i][1] + '</option>';
						}
					}	
					$("select#asset_type").html(options);
					$("#asset_type").attr("disabled", true);
		}); 
	
	
	var assets_name = '${Peripheral_Asset_WO_CMD.assets_type}';
	$.post("getMakeNameList?" + key + "=" + value, {assets_name: assets_name}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		var make = '${Peripheral_Asset_WO_CMD.make_name}';
		for(var i = 0; i < j.length; i++) {
			if(make ==  j[i][0]){
				options += '<option value="' + j[i][0] +'" name="'+j[i][1]+'" selected=selected >'+ j[i][1] + '</option>';
			}else{
				options += '<option value="' + j[i][0] +'" >'+ j[i][1] + '</option>';
			}
		}
		$("select#make_name").html(options);
		$("#make_name").attr("disabled", true);
	}).fail(function(xhr, textStatus, errorThrown) {});
	});
		
	var make_name = '${Peripheral_Asset_WO_CMD.make_name}';
	$.post("getModelNameList?" + key + "=" + value, {make_name: make_name}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		var model = '${Peripheral_Asset_WO_CMD.model_name}';
		for(var i = 0; i < j.length; i++) {
			if(model ==  j[i][0]){
				options += '<option value="' + j[i][0] +'" name="'+j[i][1]+'" selected=selected >'+ j[i][1] + '</option>';
			}else{
				options += '<option value="' + j[i][0] +'" >'+ j[i][1] + '</option>';
			}
		}
		$("select#model_name").html(options);
		$("#model_name").attr("disabled", true);
	}).fail(function(xhr, textStatus, errorThrown) {});

</script>



<c:url value="Download_Computing_Asset_WO" var="downloadUrl"/>
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="Hid_id">
   <input type="hidden" name="Hid_id" id="Hid_id" value="0">
</form:form>

<script>

function Downloaddata(id){
	alert("---------"+id);
	$("#Hid_id").val(id);
	document.getElementById('downloadForm').submit();		 
}
</script>