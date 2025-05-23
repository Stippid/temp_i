
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>
      <div class="col-md-12" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>EDIT IT CENSUS RETURN</h5>
		      </div>
			<div class="card-body card-block">
				<div class="row">
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"> Section </label>
							</div>
							<div class="col-md-8">
								<select id="section" name="section" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num">
										<option value="${item.id}"
											<c:if test="${item.id == MainAssetsCmd.section}">selected</c:if>>${item.section}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>Computing Assets Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
				</div>
					<div class="col-md-12">
						
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-3"> -->
<!-- 									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>Model Number</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<input type="text" name="model_number" id="model_number" class="form-control"/> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>Machine Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="machine_number" id="machine_number" class="form-control"/>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>Ram Capacity</label>
								</div>
								<div class="col-md-8">
									<select name="ram_capi" id="ram_capi" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${ramList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12 ">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>HDD Capacity</label>
								</div>
								<div class="col-md-8">
									<select name="hdd_capi" id="hdd_capi" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Operating System</label>
								</div>
								<div class="col-md-8">
									<select name="operating_system" id="operating_system" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${osList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6  display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>From Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="from_date" id="from_date" maxlength="10"   class="form-control width93 display_inline rgb0" 
							 aria-required="true" autocomplete="off">
								</div>

							</div>
						</div>
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>To Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="to_date" id="to_date" maxlength="10"   class="form-control width93 display_inline rgb0"
							 aria-required="true" autocomplete="off">
								</div>

							</div>
						</div>
						
					</div>
						<div class="col-md-12">
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
									<option value="0" >Pending for Approval</option>
									<option value="1" >Approved</option>
									<option value="3" >Rejected</option>
									</select>
								</div>
								

							</div>
						</div>
						
							</div>
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="SearchApp_Computing_AssetsUrl" class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" id="searchbtn"  value="Search"/>            
              </div>
          </div>
   <div class="" id="divPrint" >				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
					
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
			                                                                                                     
                                                                                                                
                                     <th id="2">Computing Assets Type</th>
<!--                                      <th id="3">Model Number</th> -->
                                     <th id="4">Machine No</th>
                                     <th id="5">Mac Address</th>
                                     <th id="6">Ip Address</th>
                                     <th id="7">Processor Type</th>
                                     <th id="8">RAM Capacity</th>
                                     <th id="9">HDD Capacity</th>
                                     <th id="10">Operating System</th>
                                     <th id="11">Office</th>
                                     <th id="12">Deply Envt as Applicable</th>
                                     <th id="13">Warranty</th>
                                     <th id="14">Servicable status</th>
                                     <th id="15">UnServicable status</th>
                                     <th id="16">Section</th>
                                     <th data-orderable="false" >Action</th>
		                          </tr>                                                        
		                      </thead> 
		                 </table>

		            </div>	          
	        </div> 
      </div>
  
  

<c:url value="SearchApp_Computing_AssetsUrl_1" var="SearchApp_Computing_AssetsUrl_1" />
<form:form action="${SearchApp_Computing_AssetsUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="id7">
<!--  <input type="hidden" name="id7" id="id7" value="0"/> -->
 <input type="hidden" name="section1" id="section1" value="0"/>
     <input type="hidden" name="computing_assets1" id="computing_assets1" value="0"/>
     <input type="hidden" name="model_number1" id="model_number1" value=""/>
     <input type="hidden" name="machine_number1" id="machine_number1" value=""/>
     <input type="hidden" name="ram_capi1" id="ram_capi1" value="0"/>
     <input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0"/>
     <input type="hidden" name="operating_system1" id="operating_system1" value="0"/>
     <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value=""/>
     <input type="hidden" name="unit_name1" id="unit_name1" value=""/>
      <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
    
</form:form>

 <c:url value="It_AppEditUrl" var="It_AppEditUrl" />
<form:form action="${It_AppEditUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id5" >
	<input type="hidden" name="id5" id="id5" value="0" />
	<input type="hidden" name="ch_id1" id="ch_id1" value="0" />
</form:form> 
	
 <c:url value="ComputingAssertsDelete" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id6" >
	<input type="hidden" name="id6" id="id6" value="0" />
</form:form> 

<c:url value="Approve_Update_Computing" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0"/> 
	<!-- <input type="hidden" name="status" id="status" value="0"/>
		<input type="hidden" name="modified_by" id="modified_by" />
		<input type="hidden" name="modified_date" id="modified_date" value="0"/> -->
</form:form>	
<c:url value="Reject_Update_Computing" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
</form:form>
 
 
<!-- for Functions -->
<script type="text/javascript" nonce="${cspNonce}" >
document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('searchbtn').onclick = 
		function() {
		return Search();
  	};
  	
  	document.getElementById('to_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
  	document.getElementById('to_date').onfocus = 
		function() {
		return this.style.color='#000000';
  	};
  	document.getElementById('to_date').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	document.getElementById('to_date').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
  	document.getElementById('to_date').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
  	
  	document.getElementById('from_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
  	document.getElementById('from_date').onfocus = 
		function() {
		return this.style.color='#000000';
  	};
  	document.getElementById('from_date').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	document.getElementById('from_date').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
  	document.getElementById('from_date').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
  	 document.addEventListener('click', function(event) {
         if (event.target.classList.contains('action_update')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 var encryptedchPk = event.target.getAttribute('data-chid');
                 if (confirm('Are you sure you want to Update?')) {
                         editData(encryptedPk,encryptedchPk);
                 }
         } else if (event.target.classList.contains('action_approve')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 if (confirm('Are you sure you want to Approve?')) {
                	 Approve(encryptedPk);
                 }
         }
         else if (event.target.classList.contains('action_reject')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to Reject?')) {
                     Reject(encryptedPk);
             }
     }
 });

  
 
  	
	
});

function data(BloodReport){
	jsondata = [];

	var table = $('#'+BloodReport).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
	
	var assets_type=$("#assets_type").val();
 	//var model_number=$("#model_number").val();
 	var machine_number=$("#machine_number").val();
 	var ram_capi=$("#ram_capi").val();
 	var hdd_capi=$("#hdd_capi").val();
 	var operating_system=$("#operating_system").val();
 	var unit_sus_no=$("#unit_sus_no").val();
 	var from_date=$("#from_date").val();
 	var to_date=$("#to_date").val();
 	var status=$("#status").val() ;
	var section =$("#section").val() ;

 	
	$.post("getFilteredasset?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
		,unit_sus_no:unit_sus_no,from_date:from_date,to_date:to_date,status:status ,section:section},function(j) {
			
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].assets_name,j[i].machine_number,j[i].mac_address,j[i].ip_address,j[i].processor_type,j[i].ram,
				j[i].hdd,j[i].os,j[i].office,j[i].dply_env,j[i].warranty,j[i].service_state
				,j[i].unservice_state,j[i].section,j[i].action]);
		}
	});
	$.post("getTotalAssetCount?"+key+"="+value,{Search:Search,assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
		,unit_sus_no:unit_sus_no,from_date:from_date,to_date:to_date,status:status,section:section},function(j) {
		FilteredRecords = j;
	});
}
</script>
<script nonce="${cspNonce}" >




function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id,ch_id){
	 $("#id5").val(id);
	 $("#ch_id1").val(ch_id);
	document.getElementById('updateForm').submit();
}
 function viewData(id){
	 $("#id6").val(id);
	document.getElementById('viewForm').submit();
}
 function Approve(id){
		$("#id3").val(id);
		document.getElementById('approveForm').submit();
		
	}

	function Reject(id){
		$("#id4").val(id);
		document.getElementById('rejectForm').submit();
	}  
	 function Search(){
			

			$("#computing_assets1").val($("#asset_type").val());
			//$("#model_number1").val($("#model_number").val());
			$("#machine_number1").val($("#machine_number").val());
			$("#ram_capi1").val($("#ram_capi").val());
			$("#hdd_capi1").val($("#hdd_capi").val());
			$("#operating_system1").val($("#operating_system").val());
			$("#unit_sus_no1").val($("#unit_sus_no").val());  
			$("#unit_name1").val($("#unit_name").val()); 
			$("#from_date1").val($("#from_date").val());
			$("#to_date1").val($("#to_date").val());  
			$("#assets_type1").val($("#assets_type").val());
			$("#status1").val($("#status").val());
			$("#section1").val($("#section").val());
			$("#searchForm").submit();
		}


</script>
<script nonce="${cspNonce}" >
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

function setUpdateStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("DSunitApproved?"+key+"="+value, {a:a,}).done(function(j) {
			alert(j);
			isvalidData();
		}); 
	}	
}



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

</script>

<!-- for On Load Methods -->
<script nonce="${cspNonce}" > 
$(document).ready(function() {
	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
	
	datepicketDate('from_date');
	datepicketDate('to_date');
	
	if('${status1}' != "")
	{
	$("#status").val("${status1}");
	}
	if('${assets_type1}' != "")
	{
	$("#assets_type").val("${assets_type1}");
	}
// if('${model_number1}' != "")
// {
//  $("input#model_number").val("${model_number1}");
// }
if('${machine_number1}' != "")
{
$("input#machine_number").val("${machine_number1}");
}
if('${ram_capi1}' != "")
{
$("select#ram_capi").val("${ram_capi1}");
}
if('${hdd_capi1}' != "")
{
$("select#hdd_capi").val("${hdd_capi1}");
}
if('${operating_system1}' != "")
{
$("select#operating_system").val("${operating_system1}");
}
if('${from_date1}' != "")
{
$("input#from_date").val("${from_date1}");
}
if('${to_date1}' != "")
{
$("input#to_date").val("${to_date1}");
}
if('${section1}' != "")
{
$("select#section").val("${section1}");
}
		
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
	}	
});	
</script>