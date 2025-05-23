<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js" nonce="${cspNonce}"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript" nonce="${cspNonce}"></script>


<script type="text/javascript" nonce="${cspNonce}">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
		  $('html').bind('cut copy paste', function (e) {
	        e.preventDefault();
	    });
	   
	    $("html").on("contextmenu",function(e){
	        return false;
	    }); 
	    
	      $('#role_text').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });  
	    
	  	watermarkreport();
	    
	    try{
    		   if(window.location.href.includes("msg="))
    			{
    				var url = window.location.href.split("?msg")[0];
    				window.location = url;
    			} 	
    		}
    		catch (e) {
    			
    		} 
    
	});
	
	 
</script>

 <form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' modelAttribute="role_mstCMD">

	<div class="container" align="center">
       	<div class="card">
         		<div class="card-header"> <h5>Role Master</h5><!-- <strong>Schedule Details </strong> --></div>
         			<div class="card-body card-block cue_text">
         			<div class="col-md-12">
         				<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input">Role Name <strong class="color_red">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="role" name="role" maxlength="45" class=" font_familiy_awsome form-control" autocomplete="off" >
								</div>
 							</div>
 						</div> 
 					</div>
 				
 					<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input">Role Type <strong class="color_red">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               						<select name="role_type" class="form-control" id ="role_type" >
                 						<option value="0">--Select--</option>
               							<c:forEach var="item" items="${getRoleType}" varStatus="num" >
               								<option value="${item.role_type}">${item.role_type}</option>
               							</c:forEach>
                 					</select>
               					</div>
 							</div>
 						</div> 
 					</div>
 					
 					<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input">Access Level <strong class="color_red">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               						<select name="access_lvl" class="form-control" id ="access_lvl"> 
             							<option value="">--Select--</option>            				
             								<option value="Section">Section</option>
             									<option value="Enterprise">Enterprise</option>
             										<option value="All">All</option>
             									
             							
               						 </select>
               					</div>
               				</div>
 						</div> 
 					</div>
 					
 				 	<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6 display_none" id="sub_lev">
                 					<label for="text-input">Please Select <strong class="color_red">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               						<select name="formation_se" class="form-control display_none" id ="formation_se">  
               							<option value="">--Select--</option>
               							
               						</select>
               						 <select name="line_dte_se" class="form-control display_none" id ="line_dte_se">  
	               						<option value="">--Select--</option>
	               					             						
               						</select>               					
               						<select name="deopot_se" class="form-control display_none" id ="deopot_se">  
	               						<option value="">--Select--</option>
	               						
	               					</select>            
               						<select name="miso_se" class="form-control display_none" id ="miso_se" >  
	               						<option value="">--Select--</option>
	               						<option value="Medical">Medical</option>
	               					</select>   	
	               				</div>
 							</div>
 						</div> 
 					</div>
 					
 					<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6 display_none" id="sub_lev1">
                 					<label for="text-input">Please Select <strong class="color_red">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               					<select name="staff_se" class="form-control display_none" id ="staff_se">  
	               						<option value="">--Select--</option>
	               						<option value="MGO">MGO</option>
	               						<option value="SD">SD</option>	
	               						<option value="WE">WE</option>	               						
               						</select>
               						
               					
               						
               						</div>
 							</div>
 						</div> 
 					</div>
 					<input  id="sub_lvl_text" name="sub_access_lvl"  type="hidden" >
               		<input  id="staff_text" name="staff_lvl"  type="hidden" >
         		</div>
         	
         	<div class="card-footer" align="center">
         	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" id="save_btn">
	              		
         	</div>
               
         </div>
	</div>
   </form:form>
   
    			<div class="container" id="divSerachInput">
						<div class="col-md-6">
				 			<input id="searchInput" type="text"  placeholder="&#xF002; Search Word"  size="35" width="20%" class="mb5 font_familiy_awsome form-control">
				 		</div>
				</div>
				<div class="container"  id="divPrint" >
					<div class="col-md-14">
    				 <div id="divShow">
					</div>
					 <div  class="watermarked display_block" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
							<thead>
								<tr class="f_15">
									<th class="text_align_center" width="10">Ser No</th>	
									<th width="20">Role Name</th>	
									<th width="20">Role Type</th>	
									<th width="20">Access Level</th>	
									<th width="20">Sub Access Level</th>	
									<th width="20">Staff Level</th>
								</tr>
							</thead> 
							<tbody >
								<c:forEach var="item" items="${list}" varStatus="num" >
										<tr class="f_15">
											<td width="10" align="center">${num.index+1}</td>
											<td width="20">${item.role}</td>
											<td width="20">${item.role_type}</td>
											<td width="20">${item.access_lvl}</td>
											<td width="20">${item.sub_access_lvl}</td>	
											<td width="20" >${item.staff_lvl}</td>														
										</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>	
					</div>
					</div>
		

   <script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('access_lvl').onchange = function() {
			return access_lev(this.value);	
	  	};
		document.getElementById('formation_se').onchange = function() {
			return value_pass(this.value);	
	  	};
		document.getElementById('line_dte_se').onchange = function() {
			return value_pass(this.value);	
	  	};
		document.getElementById('deopot_se').onchange = function() {
			return value_pass(this.value);	
	  	};
		document.getElementById('miso_se').onchange = function() {
			return value_pass(this.value);	
	  	};
	  	document.getElementById('staff_se').onchange = function() {
			return value_pass1(this.value);	
	  	};
	  	document.getElementById('save_btn').onclick = function() {
			return isValid();	
	  	};
	  	
	  	
	  	

	  	
	  	
	  	
	  	
	
	    });
	
	
   $(document).ready(function () {
	$("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#RoleReport tbody tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
   });

</script> 
   
   <script nonce="${cspNonce}">
 
   
    function access_lev(v)
	{
    	
    	document.getElementById('sub_lvl_text').value="";
		if(v == "Formation"){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('formation_se').style.display='block';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('miso_se').style.display='none';
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('dg_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
			
		}
		else if(v == "Line_dte" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='block';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
		}
		
		else if(v == "Depot" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='block';	
			document.getElementById('miso_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
		else if(v == "Unit" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';			
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
		else if(v == "MISO" || v == "DG"){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('miso_se').style.display='block';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
		else if(v == "CTPartI" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';
			document.getElementById('miso_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
		else if(v == "CTPartII" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
			document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
		}
	} 
    
    function value_pass(v){
    	//alert(v)
    	document.getElementById('sub_lvl_text').value =v;
    	
    	if(v == "Staff"){
    		document.getElementById('sub_lev1').style.display='block';
    		document.getElementById('staff_se').style.display='block';
    	}
    	else{
    		document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
    	}
    	
    }
    
    function value_pass1(v){
    	document.getElementById('staff_text').value =v;
    }

   function isValid()
   {	
	   if($("input#role").val()==""){
  			alert("Please Enter Role");
  			$("input#role").focus();
  			return false;
  		}
	   
    	if($("select#role_type").val()==" "){
   			alert("Please Select Role Type");
   			$("select#role_type").focus();
   			return false;
   		}  
    	
    	if($("select#role_type").val()=="Other" && $("#role_text").val() == ""){
    		alert("please Enter Role type");
    		return false;
    	}
    	
    	if($("select#access_lvl").val()==""){
   			alert("Please Select Access Level");
   			$("select#role_type").focus();
   			return false;
   		}  
   
    	/*  if($("select#access_lvl").val() != "Unit" && $("select#access_lvl").val() != "MISO" && $("select#access_lvl").val() != "CTPartI" && $("select#access_lvl").val() != "CTPartII" && $("select#access_lvl").val() != "DG"){
	    	 if($("input#sub_lvl_text").val() == ""){
	    		 alert("Please Select Sub Access Level");   			
	   			return false;
	    	 }
    	} */
    	 /* if(document.getElementById('sub_lvl_text').value == "Staff"){
	    	 if($("staff_text").val()==""){
	    		 alert("Please select sub Access Level ");   			
	    			return false;
	     	 }
    	 } */
    	
   	
   		return true;
   	}
</script>