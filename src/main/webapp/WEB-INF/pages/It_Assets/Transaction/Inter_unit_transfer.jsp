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

<style>
	#checkboxes {
/* 	  display: none; */
		overflow: auto;
		height: 150px;
	  border: 1px #dadada solid;
	}
	#checkboxes label {
	  display: block;
	  text-align:left;
	  padding-left: 30px;
	}
	#checkboxes label:hover {
	  background-color: #1e90ff;
	}
	#checkboxes label input[type="checkbox"]{
	  margin-right: 10px;
	}
	#checkboxes label,
	#submodulecheckboxes label,
	#screencheckboxes label{
	  margin-bottom:0;
	}
</style>
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
			<form>			
				<div class="card-header">
					<h5>INTER UNIT TRANSFER</h5>
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
					 <input type="hidden" id="p_id" name="p_id"	class="form-control autocomplete" autocomplete="off" value=""> 
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> From SUS No </label>
								</div>
								<div class="col-md-8">								
									 <input type="text" id="from_sus_no" name="from_sus_no" onkeyup="specialcharecter(this)" tabindex="-1" onchange="To_sus_check();getmachine_noList();"
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
											
								 <input type="text" id="unit_name" name="unit_name" onkeyup="specialcharecter(this)" tabindex="-1" 
										class="form-control autocomplete" maxlength="50" autocomplete="off" onchange="To_sus_check();getmachine_noList();">
									 
							 	
								</div>
							</div>
						</div> 
					</div>
					<div class="col-md-12" >
 				    <div class="col-md-6">
	            		<div class="row form-group">
	 				        <div  class="col-12 col-md-4" >
	 				        <label class=" form-control-label"> Machine No </label>
	 				        </div>
	         				<div  class="col-12 col-md-8" >
	         				<input type="hidden" id="machine_no" name="machine_no" >
	         					<div id="checkboxes"></div>
	         					
	         				</div> 
						</div>
	                </div>
				</div>
					
					
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Asset Type </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="assets_type" > </label> -->
									
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div>		 -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Model Number </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="model_number" > </label> -->
									
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div>						 -->
<!-- 					</div> -->
					<div  id="compViewDiv" style="display:none">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Mac Address </label>
								</div>
								<div class="col-md-8">
									<label id="mac_address" > </label>
									
								</div>
							</div>
						</div>		
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Ip Address </label>
								</div>
								<div class="col-md-8">
									<label id="ip_address" > </label>
									
								</div>
							</div>
						</div>						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Processor Type </label>
								</div>
								<div class="col-md-8">
									<label id="processor_type" > </label>
									
								</div>
							</div>
						</div>		
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> RAM Capacity </label>
								</div>
								<div class="col-md-8">
									<label id="ram_capacity" > </label>
									
								</div>
							</div>
						</div>						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> HDD Capacity </label>
								</div>
								<div class="col-md-8">
									<label id="hd_capacity" > </label>
									
								</div>
							</div>
						</div>		
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Operating System </label>
								</div>
								<div class="col-md-8">
									<label id="operating_system" > </label>
									
								</div>
							</div>
						</div>						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> OS/Firmware Activation and
										subsequent Patch Updation Mechanism </label>
								</div>
								<div class="col-md-8">
									<label id="os_firmware" > </label>
									
								</div>
							</div>
						</div>		
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Deply Envt as Applicable </label>
								</div>
								<div class="col-md-8">
									<label id="deply_envt" > </label>
									
								</div>
							</div>
						</div>						
					</div>
					</div>
					<div id="perifViewDiv" style="display:none">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Year Of Proc </label>
								</div>
								<div class="col-md-8">
									<label id="year_of_proc" > </label>
									
								</div>
							</div>
						</div>		
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Year Of Manufacturing </label>
								</div>
								<div class="col-md-8">
									<label id="year_of_manufacturing" > </label>
									
								</div>
							</div>
						</div>						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Type of HW </label>
								</div>
								<div class="col-md-8">
									<label id="type_of_hw" > </label>
									
								</div>
							</div>
						</div>	
						<div class="col-md-6">
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
										<input type="text" id="to_sus_no" name="to_sus_no" onkeyup="specialcharecter(this)"
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
									<input type="text" id="to_unit_name" name="to_unit_name" onkeyup="specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off" maxlength="50" onchange="To_sus_check();" >
									
									
								</div>
							</div>
						</div>
					</div>
			 </div>
						<div class="card-footer" align="center" class="form-control">
							<a href="" class="btn btn-success btn-sm">Clear</a> 
							<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="save_IUT();">
						</div>
					</div>
				</form>
			</div>
		<!-- POST OUT FORM END  -->
			
		<!-- POST IN FORM START  -->
			
<!-- 	<div id="div_post_in" class="tabcontent" style="display:none ;"> -->
<%-- 		<form id="post_in_form"> --%>
<!-- 					<div class="card"> -->
<!-- 						<div class="card-header"> -->
<!-- 							<h5>POST IN</h5> -->
<!-- 							<h6 class="enter_by"> -->
<!-- 								<span style="font-size: 12px; color: red;"></span> -->
<!-- 							</h6> -->
<!-- 						</div> -->
<!-- 					<div class="card-body card-block"><br> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> From SUS No </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="i_from_sus_no" name="from_sus_no" onkeyup="search_sus(this,'unit_nameIn') ; specialcharecter(this)" class="form-control autocomplete" autocomplete="off"  -->
<!-- 									maxlength="8" onchange="from_sus_check()" onkeypress="return AvoidSpace(event)">							 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						 <div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label">From Unit Name </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
											
<!-- 								 <input type="text" id="unit_nameIn" name="unit_nameIn" onkeyup="search_unit(this,'i_from_sus_no') ; specialcharecter(this)" -->
<!-- 										class="form-control autocomplete" maxlength="50" autocomplete="off" onchange="from_sus_check()">						 	 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div>  -->
<!-- 					</div> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Personal No </label> -->
<!-- 									<input type="hidden" id="i_id" name="i_id"	class="form-control autocomplete" autocomplete="off" value="0"> 					 -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="in_personnel_no" name="personnel_no" -->
<!-- 										class="form-control autocomplete" autocomplete="off" -->
<!-- 										onkeyup="getPersonnel_no(this,'i_from_sus_no') ; ; specialcharecter(this)" -->
<!-- 										onchange="Inpersonal_number() ,statusPER();" maxlength="9" onkeypress="return AvoidSpace(event)"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					 <div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Name </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="iname" name="name"> </label>								 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-4"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Rank </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="rank2" name="rank"> </label>									 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div>						 -->
<!-- 					</div> -->
<!-- 					<div class="col-md-12" style="display:none"> -->
<!-- 					 	<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label">Appointment</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8">								 -->
<!--  									<label id="app_name" name="app_name"> </label>									 -->
<!--  								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Date of Appointment </label> -->
<!--  								</div> -->
<!--  								<div class="col-md-8">  -->
<!-- 									<label id="app_dt" name="app_dt"> </label> -->
<!-- 									<input type="hidden" id="app_dt" name="app_dt" class="form-control autocomplete" autocomplete="off"> -->
<!--  								</div>  -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 						<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Auth No </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="in_auth" name="in_auth" class="form-control autocomplete"  -->
<!-- 									maxlength="50" onkeyup="return specialcharecter(this)" autocomplete="off"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Auth Date </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" name="in_auth_dt" id="in_auth_dt" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;" -->
<!-- 										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"  -->
<!-- 										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" />										 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> To SUS No </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="i_to_sus_no" name="to_sus_no"  onkeyup="search_sus(this,'unit_nameIn1') ; specialcharecter(this)" tabindex="-1" class="form-control autocomplete" autocomplete="off"  -->
<!-- 									maxlength="8" onchange="from_sus_check()" onkeypress="return AvoidSpace(event)">														 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label">To Unit Name </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" id="unit_nameIn1" name="unit_nameIn1" onkeyup="search_unit(this,'i_to_sus_no') ; specialcharecter(this)" tabindex="-1"  -->
<!-- 									class="form-control autocomplete" autocomplete="off" maxlength="50" onchange="from_sus_check()" >														 -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div>					  -->
<!-- 					  <div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Date of TOS </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 										<input type="text" name="dt_of_tos" id="dt_of_tos" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;" -->
<!-- 										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')"  -->
<!-- 										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" /> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					  <div class="col-md-6" id=""  > -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<strong style="color: red;"></strong><label class=" form-control-label">Remarks </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="unit_description_IN"> </label> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div>  -->
<!-- 				</div>  					 -->
<!-- 					<div class="col-md-12">						  -->
<!-- 						 <div class="col-md-6" id="stats" style="display: none"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<strong style="color: red;"> </strong><label class=" form-control-label">T Status </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<select name="t_status" id="t_status" class="form-control"   > -->
<!-- 										<option value= "0">--Select--</option> -->
<%-- 										<c:forEach var="item" items="${getstatusList}" varStatus="num"> --%>
<%-- 										<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
<!-- 					</div> -->
					 
<!-- 				 <div class="col-md-12" id="xlist" style="display: none" > -->
<!-- 					<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<strong style="color: red;"> -->
<!-- 						 </strong><label class=" form-control-label"> X List SUS No </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									 <input type="text"   id="x_sus_no" name="x_sus_no" value="" -->
<!-- 										class="form-control autocomplete" maxlength="8" autocomplete="off" -->
<!-- 										 onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">    -->
					  			 
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<strong style="color: red;">  </strong><label class=" form-control-label"> X List Loc </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									 <textarea    id="x_list_loc" name="x_list_loc" value="" class="form-control autocomplete"  -->
<!-- 									 maxlength="250" autocomplete="off" onkeyup="return specialcharecter(this)"></textarea> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div>				   -->
<!-- 				 </div> -->
<!-- 						<div class="card-footer" align="center" class="form-control"> -->
<!-- 							<a href="" class="btn btn-success btn-sm">Clear</a> <input -->
<!-- 								type="button" class="btn btn-primary btn-sm" value="Save" onclick="save_postin();"> -->
<!-- 						</div> -->
<!-- 					</div> -->
<%-- 					</form> --%>
<!-- 				</div> -->
		<!-- POST IN FORM END  -->	
		 		
			</div>
		</div>
	</div>

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
	function from_sus_check() {
		
		var from_sus_no = $('#i_from_sus_no').val(); 
		var to_sus_no = $('#i_to_sus_no').val(); 
		
	if(from_sus_no==to_sus_no && to_sus_no!='' && from_sus_no!='')	 {
		
		alert("From And To Sus Can't Be Same");
		if('${roleSusNo}'!='' && '${roleSusNo}'!=null){
			 $('#i_from_sus_no').val(''); 
			 $('#unit_nameIn').val(''); 
		}
		else{
		$('#i_to_sus_no').val('');
		$('#unit_nameIn1').val('');
		}
	}
	if(from_sus_no=='' ||  $('#unit_nameIn').val()==''){
		 $("#in_personnel_no").val('');
		 $("#personnel_no").val('');
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
		
		if ($("input#from_sus_no").val() == "") {
			alert("Please Enter From Unit SUS No");
			$("input#from_sus_no").focus();
			return false;
		}	
		
		if ($("#machine_no").val() == "") {
			alert("Please Select Machine No");
			$("#machine_no").focus();
			return false;
		}	
		
		if ($("input#to_sus_no").val() == "") {
			alert("Please Enter To Unit SUS No");
			$("input#to_sus_no").focus();
			return false;
		}	
 
			
		
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
		
		var machine_no = $("#machine_no").val();
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
		
		
		$("#machine_no").attr('readonly', false);
	}
	function fn_getPeripheralsData(){
		
		
		var machine_no = $("#machine_no").val();
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
		
		
		$("#machine_no").attr('readonly', false);
	}
	
	
	
	
function fn_service_category_change(){
	$("#p_id").val("");
	$("div#checkboxes").html("");
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
	
$("#to_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#to_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList_it?"+key+"="+value,
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
	        	  alert("Please Enter Approved Parent SUS No.");
	        	  document.getElementById("to_unit_name").value="";
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
             $("#to_unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});


// Parent unit name
 $("input#to_unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#to_unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getTargetUnitsNameActiveList_It?"+key+"="+value,
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
			        	  alert("Please Enter Approved Parent Unit.");
			        	  document.getElementById("to_unit_name").value="";
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
					        	$("#to_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});
	

		
	$("#from_sus_no").keyup(function(){
	
            var sus_no = this.value;
			var susNoAuto=$("#from_sus_no");
			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getSUSNoList_Active_or_Inactive_it?"+key+"="+value,
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
			        	  alert("Please Enter Approved Parent SUS No.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});





		$("#unit_name").keyup(function(){
			var unit_name = this.value;
			var susNoAuto=$("#unit_name");
//		 	removeMinDate();
					
						  susNoAuto.autocomplete({
						      source: function( request, response ) {
						        $.ajax({
						        	type: 'POST',
							    	url: "UnitsNameList_active_or_inactive?"+key+"="+value,
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
						        	  alert("Please Enter Approved Parent Unit.");
						        	  document.getElementById("unit_name").value="";
						        	  susNoAuto.val("");	        	  
						        	  susNoAuto.focus();
						        	  return false;	             
						          }   	         
						      }, 
						      select: function( event, ui ) {
						    	 var unit_name = ui.item.value;
						    
							            $.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
							            }).done(function(data) {
							            	var length = data.length-1;
								        	var enc = data[length].substring(0,16);
								        	$("#from_sus_no").val(dec(enc,data[0]));
							            }).fail(function(xhr, textStatus, errorThrown) {
							            });
						      } 	     
						}); 			
				});
	

function getmachine_noList(){
	debugger;
	sus_no=$('#from_sus_no').val();
	var options = "";
	var perurl;
	if($('#type').val()=='computing'){
		
		perurl='getmachine_no_CompListToTransf?';
	}
	else if($('#type').val()=='peripherals'){
		perurl='getmachine_no_perifListToTransf?';
	}
	//alert(sus_no);
		$.post(perurl+key+"="+value,{sus_no : sus_no}, function(j) {
			$("div#checkboxes").html("");
			for ( var i = 0; i < j.length; i++){
					$("div#checkboxes").append('<label for="'+j[i].id+'"  id="module_chk_lable"> <input type="checkbox"    onclick="chkString();"  name ="chk" id="'+j[i].id+'" value ="'+j[i].id+'" /><i id="'+j[i].id+'label">'+j[i].machine_number+'</i></label>');       						
			} 
		});			
}

function chkString()
	{
	var machine_id =new Array();	
	var machine_no =new Array();	
	
	$("input:checkbox[name=chk]:checked").each(function(){  	
		machine_no.push($("#"+$(this).attr('id')+"label").html());	   
		machine_id.push($(this).val());	     
 	});		 	
	document.getElementById('p_id').value=machine_id;
	document.getElementById('machine_no').value=machine_no;
	
	}
	
</script>

