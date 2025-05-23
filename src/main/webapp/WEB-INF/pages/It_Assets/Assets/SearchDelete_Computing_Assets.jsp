
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>

<div class="" align="center">
	<div class="card">
		<div class="card-header">
			<h5>DELETE COMPUTING ASSETS</h5>

		</div>
		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label">Section </label>
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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Computing Assets Type</label>
							</div>
							<div class="col-md-8">
								<select name="assets_type" id="assets_type" class="form-control">
									<option value="0">--select--</option>
									<c:forEach var="item" items="${ComputingAssetList}"
										varStatus="num">
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
					<!-- 									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>Model No.</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<!-- 									<input type="text" name="model_number" id="model_number" class="form-control" maxlength="50" onkeypress="return onlyAlphaNumeric(event, this)"/> -->
					<!-- 								</div> -->

					<!-- 							</div> -->
					<!-- 						</div> -->
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Machine Number</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="machine_number" id="machine_number"
									class="form-control" maxlength="50"
									onkeypress="return onlyAlphaNumeric(event, this)" />
							</div>

						</div>
					</div>

				</div>

				<div class="col-md-12">

					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>RAM Capacity</label>
							</div>
							<div class="col-md-8">
								<select name="ram_capi" id="ram_capi" class="form-control">
									<option value="0">--select--</option>
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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>HDD Capacity</label>
							</div>
							<div class="col-md-8">
								<select name="hdd_capi" id="hdd_capi" class="form-control">
									<option value="0">--select--</option>
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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"></strong>Operating System</label>
							</div>
							<div class="col-md-8">
								<select name="operating_system" id="operating_system"
									class="form-control">
									<option value="0">--select--</option>
									<c:forEach var="item" items="${osList}" varStatus="num">
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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"></strong>Unit Sus No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_sus_no" name="unit_sus_no"
									class="form-control autocomplete" autocomplete="off" />
							</div>

						</div>
					</div>
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Unit Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_name" name="unit_name"
									class="form-control autocomplete" autocomplete="off" />
							</div>

						</div>
					</div>

				</div>
				<div class="col-md-12 ">
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"></strong>From Date</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="from_date" id="from_date"
									maxlength="10" class="form-control width94 display_inline rgb0"
									aria-required="true" autocomplete="off">
							</div>

						</div>
					</div>
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>To Date</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="to_date" id="to_date" maxlength="10"
									class="form-control width94 display_inline rgb0"
									aria-required="true" autocomplete="off">
							</div>

						</div>
					</div>

				</div>

				<div class="col-md-12">

					<!-- 						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-3"> -->
					<!-- 									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Status</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<!-- 									<select name="status" id="status" class="form-control"> -->
					<!-- 									<option value="0" >Pending</option> -->
					<!-- 									<option value="1" >Approved</option> -->
					<!-- 									<option value="3" >Rejected</option> -->
					<!-- 									</select> -->
					<!-- 								</div> -->

					<!-- 							</div> -->
					<!-- 						</div> -->
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label">Serviceable State</label>
							</div>
							<div class="col-md-8">
								<select name="s_state" id="s_state" class="form-control"
									onchange="serviceStChange();">
									<option value="0">--select--</option>
									<c:forEach var="item" items="${getServiceable_StateList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>

							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
		<div class="card-footer" align="center">
			<a href="SearchDelete_Computing_AssetsUrl"
				class="btn btn-primary btn-sm" id="btn_clc">Clear</a> <i
				class="fa fa-search"></i><input type="button"
				class="btn btn-success btn-sm" id="searchbtn" value="Search" />
			<!-- 		          <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Computing Data" onclick="getExcel1();" />   
          -->
		</div>
	</div>

	<div class="" id="divPrint">
		<div align="center" class="container">
			<i class="fa fa-file-excel-o fxlarge color_green text_align_right"
				id="btnExport" aria-hidden="true" title="EXPORT TO EXCEL"></i>
		</div>
		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span> <input type="hidden" id="CheckVal"
				name="CheckVal"> <b><input type=checkbox id='nSelAll'
				name='tregn'>Select all [<span id="tregn">0</span><span
				id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> <input
				type="button" class="btn btn-success btn-sm" id="deletebtn"
				value="Delete">

			<table id="BloodReport" class="display">
				<thead>
					<tr>
						<th id="2">Computing Assets Type</th>
						<!--                                      <th id="3">Model Number</th> -->
						<th id="4">Machine No</th>
						<th id="5">Mac Address</th>
						<th id="6">Ip Address</th>
						<th id="7">Processor Type</th>
						<th id="11">Office</th>
						<th id="13">Deply Envt as Applicable</th>
						<th id="12">Section</th>
						<th id="14">Created By User</th>



						<%--                                      <c:if test="${roleType == 'APP' && status1 == '0'}"> --%>
						<!-- 										<th>Select To Approve/Reject</th> -->
						<%-- 										</c:if> --%>
						<th class="action">Action</th>
					</tr>
				</thead>

			</table>
			<%-- 		                  <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
			<!-- 		                 <input type="button" class="btn btn-success btn-sm" value="Approve" -->
			<!-- 							onclick="return setApproveStatus();"> -->
			<!-- 							<input type="button" class="btn btn-success btn-sm" value="Reject" -->
			<!-- 							onclick="return setRejectStatus();"> -->
			<%-- 							</c:if> --%>
		</div>
	</div>
</div>


<c:url value="SearchDelete_Computing_AssetsUrl_1"
	var="Search_Computing_AssetsUrl_1" />
<form:form action="${Search_Computing_AssetsUrl_1}" method="post"
	id="searchForm" name="searchForm" modelAttribute="computing_assets1">
	<input type="hidden" name="computing_assets1" id="computing_assets1"
		value="0" />
	<input type="hidden" name="section1" id="section1" value="0" />
	<input type="hidden" name="model_number1" id="model_number1" value="" />
	<input type="hidden" name="machine_number1" id="machine_number1"
		value="" />
	<input type="hidden" name="ram_capi1" id="ram_capi1" value="0" />
	<input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0" />
	<input type="hidden" name="operating_system1" id="operating_system1"
		value="0" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="" />
	<input type="hidden" name="unit_name1" id="unit_name1" value="" />
	<input type="hidden" name="from_date1" id="from_date1" value="" />
	<input type="hidden" name="to_date1" id="to_date1" value="" />
	<input type="hidden" name="assets_type1" id="assets_type1" value="0" />
	<input type="hidden" name="status1" id="status1" value="1" />
	<input type="hidden" name="s_state1" id="s_state1" value="0" />

</form:form>

<c:url value="ComputingAssertsEdit" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<c:url value="ComputingAssertsSearchDelete" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id">
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form>
<c:url value="demo_report1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1"
	name="search1" modelAttribute="comd2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
</form:form>

<c:url value="Excel_Computing_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	<input type="hidden" name="model_number2" id="model_number2" value="" />
	<input type="hidden" name="machine_number2" id="machine_number2"
		value="" />
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="s_state2" id="s_state2" value="0" />
</form:form>
<script type="text/javascript" nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};
  	document.getElementById('searchbtn').onclick = 
		function() {
		return Search();
  	};
  	
	document.getElementById('nSelAll').onclick = 
		function() {
		return setselectall();
  	};
	document.getElementById('deletebtn').onclick = 
		function() {
		return deleteData();
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
  	
// 	document.getElementById('model_number').onkeypress = 
// 		function() {
// 		return onlyAlphaNumeric(event, this);
//   	};
	document.getElementById('machine_number').onkeypress = 
		function() {
		return onlyAlphaNumeric(event, this);
  	};
  	
	
});

function getExcel() {	

	$("#computing_assets2").val($("#asset_type").val());
// 	$("#model_number2").val($("#model_number").val());
	$("#machine_number2").val($("#machine_number").val());
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val("1");
	
	
	document.getElementById('ExcelForm').submit();
} 
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
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	
	var unit_name=$("#unit_name").val() ;
	var unit_sus_no=$("#unit_sus_no").val() ;
	
	var assets_type=$("#assets_type").val() ;

//  	var model_number=$("#model_number").val() ;
 
 	var machine_number=$("#machine_number").val();
 
 	var ram_capi=$("#ram_capi").val() ;

 	var hdd_capi=$("#hdd_capi").val() ;

 	var operating_system=$("#operating_system").val() ;

 	var from_date=$("#from_date").val() ;

 	var to_date=$("#to_date").val() ;
	
 	var status=1 ;
 	
 	var s_state=$("#s_state").val() ;
 	var section =$("#section").val();

 	
	$.post("getFilteredassetcomputingDelete?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
		,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name,section:section},function(j) {
			//left here
			for (var i = 0; i < j.length; i++) {
         	var data =[j[i].assets_name,j[i].machine_number,j[i].mac_address,j[i].ip_address,j[i].processor_type,
				j[i].office,j[i].dply_env,j[i].section,j[i].created_by,j[i].chekboxaction
//                  <c:if test="${roleType == 'APP'  && status1 == '0'}">
//                	 ,j[i].chekboxaction
//                  </c:if>
//                  ,j[i].action 
                 ];
                 jsondata.push(data);
         }
			$("#nSelAll").prop('checked', false);
			$('#tregn').text("0");
			$('#tregnn').text(j.length);
			
		
	});
	$.post("getTotalAssetCount1Delete?"+key+"="+value,{Search:Search,assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
		,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name,section:section},function(j) {
		FilteredRecords = j;
	});
}
</script>

<!-- for Functions -->
<script nonce="${cspNonce}">
function getExcel1() {
	

	document.getElementById('typeReport2').value = 'excelL';
	document.getElementById('search1').submit();

} 
function btn_clc(){
	location.reload(true);
}

 function deleteData(){debugger;
	 findselected();
	 var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{
	$("#id1").val(a);
	document.getElementById('deleteForm').submit();
		}
} 
 
 function editData(id){
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
}
 
 function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}
 function Search(){
		

		$("#computing_assets1").val($("#asset_type").val());
// 		$("#model_number1").val($("#model_number").val());
		$("#machine_number1").val($("#machine_number").val());
		$("#ram_capi1").val($("#ram_capi").val());
		$("#hdd_capi1").val($("#hdd_capi").val());
		$("#operating_system1").val($("#operating_system").val());
		$("#unit_sus_no1").val($("#unit_sus_no").val());  
		$("#unit_name1").val($("#unit_name").val()); 
		$("#from_date1").val($("#from_date").val());
		$("#to_date1").val($("#to_date").val());  
		$("#assets_type1").val($("#assets_type").val());
		$("#status1").val("1");
		$("#s_state1").val($("#s_state").val());
		$("#section1").val($("#section").val());
		$("#searchForm").submit();
	}


</script>
<script nonce="${cspNonce}">
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

function setApproveStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
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
<script nonce="${cspNonce}"> 
$(document).ready(function() {
	
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	
	
	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
	datepicketDate('from_date');
	datepicketDate('to_date');
// 	if('${status1}' != "")
// 	{
// 	$("#status").val("${status1}");
// 	}
	if('${assets_type1}' != "")
	{
	$("#assets_type").val("${assets_type1}");
	}
// if('${model_number1}' != "")
// {
// $("input#model_number").val("${model_number1}");
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
if('${s_state1}' != "")
{
$("#s_state").val("${s_state1}");
}
if('${unit_name1}' != "")
{
$("#unit_name").val("${unit_name1}");
}

if('${unit_sus_no1}' != "")
{
$("#unit_sus_no").val("${unit_sus_no1}");
}
if('${section1}' != "")
{
$("#section").val("${section1}");
}
		
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});	

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode  < 48||charCode> 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
}
</script>


<script nonce="${cspNonce}">
	
		// Unit SUS No

		$("#unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var susNoAuto=$("#unit_sus_no");

			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
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
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
				var unit_name = this.value;
					 var susNoAuto=$("#unit_name");
					  susNoAuto.autocomplete({
					      source: function( request, response ) {
					        $.ajax({
					        	type: 'POST',
						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
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
					        	  document.getElementById("unit_name").value="";
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
							        	$("#unit_sus_no").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
					}); 			
			});

		</script>

