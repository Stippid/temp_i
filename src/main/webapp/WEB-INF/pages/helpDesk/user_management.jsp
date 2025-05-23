<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>


<style>
textarea {
    width:690px;
    height:200px;
    text-transform: capitalize;
}
input[type="text"]{
  text-transform: capitalize;
}
</style>
	<script type="text/javascript">
		window.history.forward();
		function noBack() {
			window.history.forward();
		}
	
		</script>
<c:url value="deleteUsermngURL" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
		<input type="hidden" name="deleteid" id="deleteid" value="0"/>
	</form:form>
<form:form name="user_management" id="user_management" action="user_managementAction" method='POST' modelAttribute="user_managementCMD">

<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>User Management</h5></div>
		<div class="card-body card-block cue_text">
            <div class="col-md-12">	
             <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Module  </label>
		                </div>
		                <div class="col-12 col-md-8">
		             <input type="hidden" id="module_hid" name=module_hid  class="form-control" value="${module_name1}" >
						    <select  id="moduleid" name="moduleid" class="form-control" > 
               				<option value="0">--Select--</option>
       							<c:forEach var="item" items="${getModuleNameList}" varStatus="num" >
       								<option style="text-transform: uppercase;" value="${item.id}">${item.module_name}</option>
       							</c:forEach>
       							
						    </select>
						</div>
	             	</div>
                </div>
                <div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-4">	             		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Agent Name</label>
		                </div>
		                <div class="col-12 col-md-8">
               				<input type="text" id="agent_name" name="agent_name" class="form-control" maxlength="30" autocomplete="off">
               				<input type="hidden" id="agent_namehid" name="agent_namehid" class="form-control" value="${agent_name1}"  >
               				<input type="hidden" id="id_hid" name="id_hid" class="form-control" >
						</div>
	             	</div>
               </div>
           </div>
	        </div>
            <div class="card-footer" align="center">
      	            <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
            		<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isvalidData();">
            		  <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
            		<input type="submit" id="update_btn" class="btn btn-primary btn-sm" value="Update" style="display: none">
      			</div>
		
		</div>
       		</div>
	<div class="container"  id="divPrint" style="display: none; "  >
			<div id="divShow" style="display: block;"></div>
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
		        <table id="TicketReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                <thead style="background-color: #9c27b0; color: white;">
		                        <tr>
	                                <th style="font-size: 15px">Ser No</th>
	                                <th style="font-size: 15px">Module </th>
	                                <th style="font-size: 15px">Agent Name</th>
	                                <th style="font-size: 15px;">Action</th>
		                        </tr>                                                        
		                </thead> 
		                <tbody>
		                <c:forEach var="item" items="${list}" varStatus="num" >
							<tr>
									<td style="font-size: 15px;">${num.index+1}</td>
								<td style="font-size: 15px;">${item.module_name}</td>
								<td style="font-size: 15px;">${item.agent_name}</td>
								<td id="thAction1" style="font-size: 15px;">${item.id}</td>
							</tr>
							</c:forEach>
		                </tbody>
		        </table>
        	</div>	
		</div>	
</form:form> 
<c:url value="getHelpMngtList1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="module1">
		<input type="hidden" name="module_name1" id="module_name1" value="0"/>
		<input type="hidden" name="agent_name1" id="agent_name1" value="0"/>
	</form:form> 
<Script>

function isvalidData()
{	
	 if($("select#moduleid").val() == "0")
		{
			alert("Please Select module");
			$("select#moduleid").focus();
			return false;
		} 
	 if($("input#agent_name").val() == "")
		{
			alert("Please Enter Agent name");
			$("input#agent_name").focus();
			return false;
		} 
	 return true; 
}

var newWin;
function Search(){
	watermarkreport();
	 if($("select#moduleid").val() == "0")
	 {		 
		 alert("Please Select module");
		 $("select#moduleid").focus();
		return false;
	 }else{
		 $("#module_name1").val($("#moduleid").val());
	     $("#agent_name1").val($("#agent_name").val());
		document.getElementById('searchForm').submit();
		 $("#divPrint").show();
	 }
	return true;	
}
 $(document).ready(function () {
	 watermarkreport();
	 if('${module_name1}' != ""){
		 $("select#moduleid").val('${module_name1}');
		$("input#agent_name").val('${agent_name1}');
	 } 

	 if('${list.size()}' == '0' ){
	     $("table#TicketReport").append("<tr><td colspan='4'>Data Not Available</td></tr>");
	 }	
	 if('${module_name1}' != "" || '${agent_name1}' != ""){
		 $("#divPrint").show();
	 } 
	
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
<script>
var newWin;
	function editData(id,module,agent_name){
		document.getElementById('moduleid').value=module;
		document.getElementById('id_hid').value=id;
		document.getElementById('agent_name').value=agent_name;
		document.getElementById('agent_namehid').value=agent_name;
		document.getElementById('update_btn').style.display='inline-block'; 
		document.getElementById('save_btn').style.display='none';  
	}
	function closeWindow()
	{
		newWin.close();   
	}
	var id=$("#id_hid").val();
	 function deleteData(id){		
		 $.post("deleteUsermngURL?"+key+"="+value,{deleteid : id}, function(j) {
			alert(j);
			Search();
		}); 
	}
</script>