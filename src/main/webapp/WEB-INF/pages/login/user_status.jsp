<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script> 
<script src="js/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/amin_module/rbac/report/criteriareportdesign.css">

<style>

 .dataTables_scrollBody{
	overflow-x: hidden !important;
	overflow-y: scroll !important;
	scrollbar-width: thin;
}
.dataTables_scrollHead{
overflow-y: hidden !important;
}
.ui-toolbar.ui-widget-header,
.dataTables_scrollHead.ui-state-default{
   width: calc(100% - 8px) !important;
}
.dataTables_scrollHeadInner{
    padding-right: 0 !important;
    width: 100% !important;
}
.dataTable{
  width: 100% !important;

}  
.watermarked::before{
	color: #3c3838;
	opacity: 1;
	width: calc(100% - 8px) !important;
}
 .dataTables_wrapper{
	opacity: 0.9;
} 

</style>

	
	<script type="text/javascript">
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
		   
		    
		     
			}); 
	</script>
	




  <form:form name="" id="" action="" method='POST' modelAttribute="">
	<div class="container" align="center">
       	<div class="card" >
         		<div class="card-header"> <h5>User Activation</h5></div>
         			<div class="card-body card-block cue_text" >
         			<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input">User Status <strong style="color: red;">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               					
               					 	<select name="userid" class="form-control" id ="userid"  onchange="">  
             							<option value="">--Select--</option> 
             							<option value="1">Active User</option>
             							<option value="0">Deactive User</option>
             							
               						 </select>
               					</div>
               				</div>
 						</div> 
 						<div class="col-md-12"  id="username_div" >
	         				<div class="col-md-6" >
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input" >User Id<strong style="color: red;">*</strong> </label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="login_name" class="form-control" maxlength="70" name="login_name" style="font-family: 'FontAwesome',Arial;"  placeholder="&#xF002; Search"  autocomplete="off">
									</div>
	 							</div>
	 						</div> 
	 						
 						</div>
 					</div>
 					
	 						
         			</div>
         			<div class="card-footer" align="center">
         	           <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="reportScreen();" value="Search">
	              		 <i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" onclick="printDiv();"  value="Print"   disabled>  <!-- onclick="return printDiv('divPrint')" -->
         	        </div>
		</div>
	</div>
	
</form:form>
	 <div class="container"  id="divPrint" style="display: none">		
	 <div id="divShow">
			</div>	
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
			
				<datatables:table id="applicanttbl1" url="getUserReportactiveList1" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
				bDestroy="true" filterable="true" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollCollapse="true" >  
				     <datatables:column title="Id" property="sr" searchable="true" data-halign="left" data-valign="left" />
				     <datatables:column title="User Name" property="login_name" searchable="true" data-halign="left" data-valign="left" />
				     <datatables:column title="User Id" property="userName" searchable="true" data-halign="left" data-valign="left" />
				     <datatables:column title="Activation/Deactivation Date" property="ac_dc_date" searchable="true" data-halign="left" data-valign="left" />
				      <datatables:column title="Action" property="Action" searchable="true" sortable="false" id="thAction"  />   
				</datatables:table>  
				
			</div>		
	</div> 

		<c:url value="search_user_statusReport" var="searchUrl" />
 			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
	 			<input type="hidden" name="status1" id="status1" value="0"/> 	
	 			<input type="hidden" name="login_name1" id="login_name1" value="0"/> 				
 			</form:form> 
 		
 		<c:url value="ActiveDataURl" var="ActiveUrl" />
 			<form:form action="${ActiveUrl}" method="post" id="ActiveForm" name="ActiveForm" modelAttribute="">
	 			<input type="hidden" name="acid1" id="acid1" value="0"/>
	 			<input type="hidden" name="status1" id="status1" value="0"/>
	 			<input type="hidden" name="login_name1" id="login_name1" value="0"/> 
 			</form:form> 
 		
 		<c:url value="DeactiveDataURl" var="DeactiveURl" />
 			<form:form action="${DeactiveURl}" method="post" id="DeactiveForm" name="DeactiveForm" modelAttribute="">
 			<input type="hidden" name="dcid1" id="dcid1" value="0"/>
 			<input type="hidden" name="status1" id="status1" value="1"/>
 			<input type="hidden" name="login_name1" id="login_name1" value="0"/> 
 		</form:form>  
<script>

$(document).ready(function () {
	
	
	if('${status1}' != ""){
		$("Select#userid").val('${status1}');	
		$("#login_name").val('${login_name1}');	

   		$("div#divwatermark").val('').addClass('watermarked'); 
   		watermarkreport();
		$("#divPrint").show();
		document.getElementById("printId").disabled = false;	
	}
	
   	$("#searchInput").on("keyup", function() {
	  			var value = $(this).val().toLowerCase();
	  			$("#UserSearchReport tbody tr").filter(function() { 
	  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	  			});
	  		});
    	try{
		   if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("&msg")[0];
				window.location = url;
			} 	
		}
		catch (e) {
			
		}  
		
		
		 var wepetext=$("#login_name");
		  wepetext.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getUsernameList?"+key+"="+value,
		        data: {userName:$("#login_name").val()},
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
		    });
});


function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;	
	$("#searchInput").val("");
	$("#login_name").val("");
	$("div#divSerachInput").hide();  
	
}

/*  function printDiv() {	
	 alert("hiiii"+$('select#userid option:selected').text())
	 	var printLbl = ["User Status :"];
	 	var printVal = [$('select#userid option:selected').text()];
	 	printDivOptimize('divPrint','User Activation',printLbl,printVal,"");
	 }
 */

</script>

<script>
 function printDiv() 
{
	var printLbl = ["User Status :"];
 	var printVal = [$('select#userid option:selected').text()];
	printDivOptimize('divPrint','User Activation',printLbl,printVal,"");
 }
 
 
 
	

function reportScreen(){
	document.getElementById("printId").disabled = false;	
	
	if($("#userid").val() == "")
	{
		$("#divPrint").hide();
		alert("Please Select User Status");
		return false;
	}else{
		$("#divPrint").show();
	}
	
	
	$("#status1").val($("select#userid").val());	
	$("#login_name1").val($("#login_name").val());	
	document.getElementById('searchForm').submit();
}

function ActiveData(id){
	$("#acid1").val(id);	
	document.getElementById('ActiveForm').submit();
}

 function DeactiveData(id){
	$("#dcid1").val(id);	
	document.getElementById('DeactiveForm').submit();
} 
</script>

