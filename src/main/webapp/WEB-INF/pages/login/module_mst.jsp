<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js" nonce="${cspNonce}"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css"nonce="${cspNonce}">
<script src="js/cue/cueWatermark.js" type="text/javascript"nonce="${cspNonce}"></script>


	<script type="text/javascript"nonce="${cspNonce}">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
		
		$(document).ready(function (){
			
			 $('html').bind('cut copy paste', function (e) {
		        e.preventDefault();
		    });
		   
		    $("html").on("contextmenu",function(e){
		        return false;
		    }); 
		    
		    $('#module_name').keyup(function() {
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

  <form:form name="module_mst" id="module_mst" action="module_mstAction" method='POST' modelAttribute="module_mstCMD">
	<div class="container" align="center">
       	<div class="card">
       		<div class="card-header"> <h5>Module Master</h5><!-- <strong>Schedule Details </strong> --></div>
      			<div class="card-body cue_text">
      				<div class="col-md-12">
       				<div class="col-md-6">
						<div class="row form-group"> 
             					<div class="col col-md-6">
               					<label for="text-input" >Module Name <strong class ="color_red" >*</strong></label> 
             					</div>
             					<div class="col-12 col-md-6">
               				     <input  id="module_name" name="module_name"  class="form-control font_familiy_awsome" autocomplete="off" maxlength="20">
					        	<input  id="module_old_name" name="module_old_name" type="hidden" class="form-control" autocomplete="off" >
						</div>
						</div>
					</div> 
				</div>
			</div>
        		<div class="card-footer" align="center">
      	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
            		<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" >
            		<input type="submit" id="update_btn" class="btn btn-primary btn-sm display_none" value="Update" >
            	</div>
      		
				
        </div>
	</div>
</form:form>

<div class="container"  id="divPrint"  >
 		<div id="divShow"></div>
		 <div  class="watermarked display_block" data-watermark="" id="divwatermark"  >
			<span id="ip"></span>
			<table id="ModuleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
				<thead >
					<tr class="f_15">
						<th class="width10 text_align_center">Ser No</th>	
						<th class="width80 text_align_center">Module Name</th>			
						<th class='lastCol width10 text_align_center' >Action</th>
					</tr>
				</thead> 
				<tbody >
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr class="f_13">
							<td class="width10 text_align_center">${num.index+1}</td>
							<td class="width80 text_align_center">${item.module_name}</td>	
							<td class='lastCol width10 text_align_center'>${item.id}</td>														
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>	
</div>

<script nonce="${cspNonce}">
function isValid()
{
	if($("#module_name").val() == ""){
		alert("Please Enter Module Name");
		$("#module_name").focus();
		return false;
   	}
	return true;
}
	
	function Update(id,name){	
		document.getElementById('module_name').value=name;
		document.getElementById('module_old_name').value=id;
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

	            if (confirm('Are you sure you want to update?')) {
	                Update(id, value); 
	            }
	        });
	    });
	  
	  
	  
	  
	});

</script>