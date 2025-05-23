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
		    
	    		
	    		/* try{
	    		   	  if(window.location.href.includes("msg="))
	    			{
	    				var url = window.location.href.split("?msg")[0];
	    				window.location = url;
	    				newWin.close();
	    			} 	
	    		}
	    		catch (e) {
	    		}  */
		    try{
			   	 if(window.location.href.includes("updateid="))
				{
					var url = window.location.href.split("?updateid=")[0];
					var m = window.location.href.split("&msg")[1];
					window.location = url;
					
					if(m.includes("Updated+Successfully") || m.includes("Password+pattern+doesn't+match.") ){
						window.opener.getRefreshReport('user_mst1',m);
						window.close('','_parent','');
					} 
				} 
				else if(window.location.href.includes("msg="))
				{
					var m=  window.location.href.split("?msg=")[0];
					window.location = m;
					if( m.includes("Password+pattern+doesn't+match.") ){
						window.opener.getRefreshReport('user_mst1',m);
						window.close('','_parent','');
					}
				}
			}
			catch (e) {
			} 
			});
		function getRefreshReport(page,msg)
		{
			if(msg.includes("Updated+Successfully") || msg.includes("Password+pattern+doesn't+match.")  )
			{
				if(page == "user_mst1")
				{
					closeWindow();
					document.getElementById('mainForm').submit();
				}
				
			}
		} 
		function closeWindow()
		{
			newWin.close();
			
		}
	</script>
</head>
<body>

  <form:form name="search_user_mst" id="search_user_mst" action="search_user_mstAction" method='POST' modelAttribute="search_user_mstCMD">

	<div class="container" align="center">
       	<div class="card" >
         		<div class="card-header"> <h5>Search User Master</h5><!-- <strong>Schedule Details </strong> --></div>
         			<div class="card-body card-block cue_text" >
         			<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input">Search By <strong style="color: red;">*</strong></label> 
               					</div>
               					<div class="col-12 col-md-6">
               						<select name="access_lvl" class="form-control" id ="access_lvl"  onchange="access_lev(this.value)"> <!--onchange="getaccess_lev(this.value);" -->
             							<option value="">--Select--</option>
             							<option value="All">All</option>
             							<option value="Username">User Id</option>
             							
             							<option value="Formation">Formation</option>
             							<option value="Line_dte">Line Dte</option>               								
           								
           								<option value="Depot">DEPOT</option>
           								<option value="CTPartI">CT Part I</option>
           								<option value="CTPartII">CT Part II</option>
           								<option value="SUS_No">SUS No</option>
               						 </select>
               					</div>
               				</div>
 						</div> 
 					</div>
 					<div class="col-md-12">
 						<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6" id="sub_lev" style="display: none">
                 					<label for="text-input">Please Select</label> 
               					</div>
               					<div class="col-12 col-md-6">
               						<select name="formation_se" class="form-control" id ="formation_se" style="display: none" onchange="FormationChange()">  
               							<option value="">--Select--</option>
               							<option value="Command">Command</option>
               							<option value="Corps">Corps</option>
               							<option value="Division">Division</option>
               							<option value="Brigade">Brigade</option>
               						</select>
               						 <select name="line_dte_se" class="form-control" id ="line_dte_se"  style="display: none" onchange="ArmStaffHide()">  <!-- onchange="value_pass(this.value)" -->
	               						<option value="">--Select--</option>
	               						<option value="Arm">Arm</option>
	               						<option value="Staff">Staff</option>	               						
               						</select>               					
               						<select name="deopot_se" class="form-control" id ="deopot_se"  style="display: none" onchange="DepotChange()">  
	               						<option value="">--Select--</option>
	               						<option value="TMS">TMS</option>
	               						<option value="MMS">MMS</option>               						
               						</select>               				
               					</div>
 							</div>
 						</div> 
 					</div>
 				 
         			<div class="col-md-12" id="sus_div" style="display: none">
	 						 <div class="col-md-6">
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input" >SUS No</label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 				<input name="user_sus_no" class="form-control" id ="user_sus_no"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off" maxlength="8">					                
	 							</div>
	 						</div>  
 						</div>
	 						</div>
	 						<div class="col-md-12"  id="username_div" style="display: none">
	         				<div class="col-md-6" >
	 							<div class="row form-group"> 
	               					<div class="col col-md-6">
	                 					<label for="text-input" >User Id<strong style="color: red;">*</strong> </label> 
	               					</div>
	               					<div class="col-12 col-md-6">
	                 					<input  id="login_name" class="form-control" name="login_name"  maxlength="70" style="font-family: 'FontAwesome',Arial;"  placeholder="&#xF002; Search"  autocomplete="off">
									</div>
	 							</div>
	 						</div> 
	 						
 						</div>
	 						<div class="col-md-12" id="arm_div" style="display: none">
		 						 <div class="col-md-6">
		 							<div class="row form-group"> 
		               					<div class="col col-md-6">
		                 					<label for="text-input" >Arm </label> 
		               					</div>
		               					<div class="col-12 col-md-6">
		                 				<!-- <input name="arm_code" class="form-control" id ="arm_code"  style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search"  autocomplete="off" > -->
		                 				<select name="user_arm_code" class="form-control" id ="user_arm_code" >
                 					 <option value="0">--Select--</option>
               							<c:forEach var="item" items="${getUserarm_codeList}" varStatus="num" >
               								<option value="${item.arm_code}">${item.arm_desc}</option>
               							</c:forEach> 
                 					</select>					                
		 							</div>
		 						</div>  
	 							</div>
	 						</div>
	 						
         			</div>
         			<div class="card-footer" align="center">
         	           <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">   
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" onclick="Search();" value="Search">
	              		<i class="fa fa-print"></i><input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
         	        </div>
         	        <input  id="sub_lvl_text"  type="hidden" >
         	        <input id="access_lve" name="access_lve1" type="hidden" />
		 			<input id="sub_access_lve" name="sub_access_lve1"  type="hidden" /> 		 	
	 				<input id="formation_code" name="user_formation_no"  type="hidden"/>
		</div>
	</div>
	
</form:form>

	 <div class="container"  id="divPrint" style="display: none">	
	 <div id="divShow">
			</div>		
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<datatables:table  id="applicanttbl1" url="getUserReportList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
				bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%"  scrollCollapse="true" >  
					     <datatables:column title="Id" property="userid" searchable="false" data-halign="left" data-valign="left" />
					     <datatables:column title="User Name" property="login_name" searchable="false" data-halign="left" data-valign="left" />
					     <datatables:column title="User Id" property="username" searchable="false" data-halign="left" data-valign="left" />
					     <datatables:column title="Role" property="role" searchable="false" data-halign="left" data-valign="left" />
					     <datatables:column title="Access Level" property="access_lvl" searchable="false" data-halign="left" data-valign="left" />
					     <datatables:column title="Sub Access level" property="sub_access_lvl" searchable="false" data-halign="left" data-valign="left" />
<%-- 					     <datatables:column title="SUS No" property="user_sus_no" searchable="false" data-halign="left" data-valign="left" /> --%>
					     <datatables:column title="Action" property="Action" searchable="false" sortable="false" id='thAction' /> <!--  id='thAction'  -->
					</datatables:table>  
			</div>		
	</div>

		<c:url value="search_user_by_role" var="searchUrl" />
 			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="">
 			<input type="hidden" name="access_lvl1" id="access_lvl1" value="0"/>
 			<input type="hidden" name="subaccess_lvl1" id="subaccess_lvl1" value="0"/>
 			<input type="hidden" name="user_sus_no1" id="user_sus_no1" value="0"/>
 			<input type="hidden" name="user_name" id="user_name" value=""/>
 		</form:form> 
 		
	<c:url value="update_user_mstUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
 
	<c:url value="search_user_mstUrl" var="mainFormUrl" />
	<form:form action="${mainFormUrl}" method="GET" id="mainForm" name="mainForm" ></form:form>
<script>
function getUsername() {
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
}
$(document).ready(function () {
	getUsername();
	
	if('${access_lvl1}' != ""){
		$("select#access_lvl").val('${access_lvl1}');
		
		if('${access_lvl1}' == "Username"){
			$("div#username_div").show();
	   		$("#login_name").show();
	   		$("#login_name").val('${user_name}');
	   		
		} 
	
		
	   else if('${access_lvl1}' == "Formation"){
	   		 $("div#sub_lev").show();
	   		$("#formation_se").show();
	   		$("div#sus_div").show();
	   		$("#user_sus_no").show();
   			$("select#formation_se").val('${subaccess_lvl1}');
   		 getSus_no('${access_lvl1}','${subaccess_lvl1}');
   		$("#user_sus_no").val('${user_sus_no1}');
   		}
		
	 	else if('${access_lvl1}' == "Line_dte"){	 	
	 		$("div#sub_lev").show(); 
	 		$("#line_dte_se").show();
			$("div#arm_div").show();	
			$("#user_arm_code").show();
	 		$("select#line_dte_se").val('${subaccess_lvl1}');
	 		ArmStaffHide();
	 		getSus_no('${access_lvl1}','${subaccess_lvl1}');
	 		$("#user_arm_code").val('${getUserarm_codeList}');
			$("#user_arm_code").val('${user_sus_no1}');
  		} 		
		else if('${access_lvl1}' == "Depot"){
	   		 $("div#sub_lev").show();
	   		$("#deopot_se").show();
	   		$("div#sus_div").show();
	   		$("#user_sus_no").show();
 			$("select#deopot_se").val('${subaccess_lvl1}');
 			 getSus_no('${access_lvl1}','${subaccess_lvl1}');
 	   		$("#user_sus_no").val('${user_sus_no1}');
 		}
		else if('${access_lvl1}' == "SUS_No"){
	   		
	   		$("div#sus_div").show();
	   		$("#user_sus_no").show();			
			 getSus_no('','');
	   		$("#user_sus_no").val('${user_sus_no1}');
		}
		
   		$("div#divwatermark").val('').addClass('watermarked'); 
   		watermarkreport();
   		$("#divPrint").show();
   		document.getElementById("printId").disabled = false;	
   	}

   	
});

function Search(){
	if($("#access_lvl").val() == "")
	{
		alert("Please Select Options");
		return false;
	}
	else{
		
		if($("#access_lvl").val() == "Username"){
			
			if($("#login_name").val()!=""){
				$("#user_name").val($("#login_name").val());				
			}
			else{
				alert("Please Enter Username");
				return false;
			} 
			
		}
	
		$("#access_lvl1").val($("#access_lvl").val());
		
		if($("#access_lvl").val() == "Formation"){
			$("#subaccess_lvl1").val($("#formation_se").val());
			$("#user_sus_no1").val($("#user_sus_no").val());
		}
		else if($("#access_lvl").val() == "Line_dte"){
			$("#subaccess_lvl1").val($("#line_dte_se").val());
			$("#user_sus_no1").val($("#user_arm_code").val());
		}
		else if($("#access_lvl").val() == "Depot"){
			$("#subaccess_lvl1").val($("#deopot_se").val());
			$("#user_sus_no1").val($("#user_sus_no").val());
		
		}
		else if($("#access_lvl").val() == "SUS_No"){
			$("#subaccess_lvl1").val("");
			$("#user_sus_no1").val($("#user_sus_no").val());
		
		}
		else {
			
			$("#subaccess_lvl1").val("");
			$("#user_sus_no1").val($("#user_sus_no").val());
		}
		$('select#user_arm_code').val();
		
		$("#user_name").val($("#login_name").val());
		
	    document.getElementById('searchForm').submit();
	}
}
var access_lvl,sub_access_lvl,role_id;
function access_lev(v)
{ 	
	$("#user_sus_no").val(""); 	
	$("select#formation_se").val("");
	$("select#deopot_se").val("");
	$("select#line_dte_se").val("");
	
	var sub_lvl="";
		if((v != "" ) || (v != '' ) ){ 		
		 if(v == "All"){
			 document.getElementById('username_div').style.display='none';
			 document.getElementById('sub_lev').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('sus_div').style.display='none';
			}
		 else if(v == "Username"){
			 document.getElementById('username_div').style.display='block';
			 document.getElementById('login_name').style.display='block';
			 document.getElementById('sub_lev').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('sus_div').style.display='none';
			getUsername();
			}
		 else if(v == "SUS_No"){
			 document.getElementById('username_div').style.display='none';
			 document.getElementById('login_name').style.display='none';
			 document.getElementById('sub_lev').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('sus_div').style.display='block';
			 getSus_no("","");
		 }
		 else if(v == "Formation"){
			
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('formation_se').style.display='block';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('deopot_se').style.display='none';
			document.getElementById('username_div').style.display='none';
			$('select#formation_se').change(function() {
				sub_lvl = this.value;
				 getSus_no(v,sub_lvl);
			 });
			 document.getElementById('sus_div').style.display='block'; 
		}
		else if(v == "Line_dte" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='block';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('username_div').style.display='none';
			$('select#line_dte_se').change(function() {
				 sub_lvl = this.value;
				 if(sub_lvl == "Arm"){
					 document.getElementById('arm_div').style.display='block'; 
				 }
				 getSus_no(v,sub_lvl);
			 });
			 document.getElementById('sus_div').style.display='none'; 
		}
		else if(v == "Depot" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='block';	
			document.getElementById('username_div').style.display='none';
			$('select#deopot_se').change(function() {
				 sub_lvl = this.value;
				 getSus_no(v,sub_lvl);
			 });
			 document.getElementById('sus_div').style.display='block'; 
			
		}
	
		else if(v == 'CTPartI' ||  v == 'CTPartII' ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('arm_div').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('username_div').style.display='none';
			document.getElementById('sus_div').style.display='none';
		}
		
	 else{
	
		document.getElementById('sub_lev').style.display='none';
		document.getElementById('arm_div').style.display='none';
		document.getElementById('line_dte_se').style.display='none';
		document.getElementById('formation_se').style.display='none';		
		document.getElementById('deopot_se').style.display='none';	
		document.getElementById('arm_div').style.display='none';
		document.getElementById('sus_div').style.display='block';
		$("#user_sus_no").val("");
		document.getElementById('username_div').style.display='none';
		 document.getElementById('login_name').value="";
		 document.getElementById('login_name').style.display='none';
		
		 }	 
  
}
else{
	document.getElementById('sub_lev').style.display='none';
	document.getElementById('arm_div').style.display='none';
	document.getElementById('line_dte_se').style.display='none';
	document.getElementById('formation_se').style.display='none';		
	document.getElementById('deopot_se').style.display='none';	
	document.getElementById('arm_div').style.display='none';
	document.getElementById('user_arm_code').value="";
	document.getElementById('sus_div').style.display='none';
	$("#user_sus_no").val("");
	document.getElementById('username_div').style.display='none';
	 document.getElementById('login_name').value="";
	 document.getElementById('login_name').style.display='none';
	alert("Access level is not defined.");
} 

 
} 


function getSus_no(access_lvl,subaccess_lvl){

	
}

function clearall()
{		
	document.getElementById('divPrint').style.display='none';
	document.getElementById("printId").disabled = true;
	$("div#username_div").hide();
	$("#login_name").hide();
	$("div#arm_div").hide();
	$("#line_dte_se").hide();
	$("#deopot_se").hide();
	$("div#sub_lev").hide();
	$("#formation_se").hide();
	$("div#sus_div").hide();
}

 function printDiv() {
	 
	 
	 
		var printLbl = [];
		var printVal = [];
		printDivOptimize('divPrint','Search User',printLbl,printVal);
	 }

 function FormationChange()
 {
	  $("#user_sus_no").val(""); 
 }
 
 function ArmStaffHide()
 {
	 
	 if( $("select#line_dte_se").val() == "Staff")
	 {
		 $("div#arm_div").hide(); 
		 $("#user_arm_code").val("0");
	 }
	 else{
		 $("div#arm_div").show(); 
	 }
 }
 
 function DepotChange(){
		$("#user_sus_no").val(""); 
 }
</script>

 <script>
var newWin;
function editData(userid){	
	/* var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
  
    newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 
	 window.onfocus = function () { 
	
	 } */
	
	document.getElementById('updateid').value=userid;
	document.getElementById('updateForm').submit();
}
</script>


</body>
</html>
