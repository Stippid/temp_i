<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html >
<head>
	<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js" nonce="${cspNonce}"></script>
	<link rel="stylesheet" href="js/cue/cueWatermark.css" nonce="${cspNonce}">
	<script src="js/cue/cueWatermark.js" type="text/javascript" nonce="${cspNonce}"></script>

	<script type="text/javascript" nonce="${cspNonce}">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		
		$(document).ready(function () {
		
		     $('#submodule_name').keyup(function() {
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
	    		
	    		
	    		$("#searchInput").on("keyup", function() {
		  			var value = $(this).val().toLowerCase();
		  			$("#SubModuleReport tbody tr").filter(function() { 
		  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		  			});
		  		});
	    		
			});
	</script>
</head>
<body>


  <form:form name="sub_module_mst" id="sub_module_mst" action="sub_module_mstAction" method='POST' modelAttribute="sub_module_mstCMD">

	<div class="container" align="center">
       	<div class="card">
       	
         		<div class="card-header"> <h5>Sub Module Master</h5></div>
         			<div class="card-body card-block cue_text">
         				<div class="col-md-12">
	         				<div class="col-md-6">
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input" >Sub Module Name <strong class ="color_red">*</strong></label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="submodule_name" name="submodule_name" class="form-control font_familiy_awsome text_transform_upper" autocomplete="off" maxlength="255">
									</div>
	 							</div>
	 						</div> 
	 						
	 						
 						</div>
 						<div class="col-md-12">
							  <div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">Module <strong class ="color_red">*</strong></label>
	                					</div>
	                					<div class="col-12 col-md-6">
               								
               								<select name="module.id" class="form-control" id ="module_id" >
		                 						<option value="0">--Select--</option>
		               							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
		               								<option value="${item.id}">${item.module_name}</option>
		               							</c:forEach>
		                 					</select>
               							</div>
	                				
							   		</div> 
							  </div>
						</div>
							
						 <div class="col-md-12">
 						           <div class="col-12 col-md-6 mt_17" >
	                					<div class="row form-group">
	                						<div class="col col-md-6"></div>
		                					<div class="col col-md-6">
		                						<div id="modulecheckboxes"></div>	                					
		                				    </div>
	                				    </div>
	                			  </div> 
	                			
	                    </div>
							 
							  <input type="hidden" name="submodule_id" id="submodule_id"/>
							  <input type="hidden" name="submodule_old" id="submodule_old"/>
							  <input type="hidden" name="module_old" id="module_old"/>
							  
				 </div>
         			
         			
         			<div class="card-footer" align="center">
         	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
	              		<input type="submit" id="save_btn" class="btn btn-primary btn-sm" value="Save" >
	              		<input type="submit" id="update_btn" class="btn btn-primary btn-sm display_none"  value="Update"   >
	              		
         	        </div>
         	 
		</div>
	</div>
	
	        
   </form:form>
	<div class="container" id="divSerachInput">
		<div class="card-body">
			<div class="col-md-6">
	 			<input id="searchInput" type="text" placeholder="&#xF002; Search Word"  size="35" width="20%" class="form-control font_familiy_awsome mb5">
	 		</div>
	 	</div>
	</div> 
	
   <div class="container"  id="divPrint" >
   	<div class="card-body">
			<div class="col-md-12">
     <div id="divShow"></div>
		 <div  class="watermarked" data-watermark="" id="divwatermark" >
				<span id="ip"></span>
				<table id="SubModuleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
					<thead>
						<tr class="f_15">
							<th class="width10 text_align_center">Ser No</th>	
							<th class="width40 text_align_center">Sub Module Name</th>	
							<th class="width40 text_align_center">Module Name</th>		
							<th   class='lastCol width10 text_align_center'>Action</th>
						</tr>
					</thead> 
					<tbody >
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr class="f_13">
									<td class="width10 text_align_center" >${num.index+1}</td>
									<td class="width40 text_align_center">${item.submodule_name}</td>
									<td class="width40 text_align_center">${item.module_name}</td>	
									<td class="width10 text_align_center" >${item.id}</td>					
								</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>	
		</div>
		</div>
	</div>

   <script nonce="${cspNonce}">
function isValid()
   {	
	   if($("input#submodule_name").val()==""){
			alert("Please Enter Sub Module Name");
			$("input#submodule_name").focus();
			return false;
		}
	   
   		 if($("select#module_id").val()== 0){
   			alert("Please Select Module");
   			$("select#modulelist").focus();
   			return false;
   		}  
   	
   	return true;
  
   }


function Update(id,name,mid){	
	document.getElementById('submodule_name').value=name;
	document.getElementById('submodule_id').value=id; 	
	$("select#module_id").val(mid);
	document.getElementById('update_btn').style.display='inline-block'; 
	document.getElementById('save_btn').style.display='none'; 
}

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('save_btn').onclick = function() {
		return isValid()	
  	};
	document.getElementById('update_btn').onclick = function() {
		 return isValid();
  	};

    document.querySelectorAll('.action_update').forEach(button => {
        button.addEventListener('click', function() {
            var id = this.getAttribute('data-id');
            var value = this.getAttribute('data-value');
            var extra = this.getAttribute('data-extra');

            if (confirm('Are you sure you want to update?')) {
                Update(id, value, extra); 
            }
        });
    });
});
</script>
</body>
</html>
