
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
			<h5>SEARCH Peripheral ASSETS ASSIGN USER</h5>
		</div>
		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"><strong
									class="color_red">* </strong> Section </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" class="display_none"><b>
										${roleSusNo} </b></label> <select id="section" name="section"
									class="form-control">

									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num"> 
									<option value="${item.id}">${item.section}</option> 
 									</c:forEach> 
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Peripheral Type </label>
							</div>
							<div class="col-md-8">
								<select path="" id="assets_type" class="form-control"
									name="assets_type"> 
									<option value="0">--Select--</option>  
									<c:forEach var="item" items="${getPeripheral}" varStatus="num">  
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>  
 									</c:forEach>  
 								</select> 
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Assets Unique Id </label>
							</div>
							<div class="col-md-8">
								<input type="text" name ="assets_uniq" id="assets_uniq" class="form-control" autocomplete="off">
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="card-footer" align="center">
			<a href="${screenurl}" class="btn btn-primary btn-sm" id="btn_clc">Clear</a>
			<i class="fa fa-search"></i><input type="button"
				class="btn btn-success btn-sm" id="btn-submit" value="Search" />
		</div>
	</div>

	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-body">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<button class="download-excel-btn" id="btnExport"
						style="margin-bottom: 10px;" title="EXPORT TO EXCEL"
						aria-hidden="true">
						<i class="fa fa-file-excel-o"></i>
					</button>
					<br> <span id="ip"></span> <input type="hidden" id="CheckVal"
						name="CheckVal"> <b><input type=checkbox id='nSelAll'
						name='tregn'>Select all [<span id="tregn">0</span><span
						id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b>

					<table id="BloodReport" class="display">
						<thead>
							<tr>
								<th id="2">Section</th>
								<th id="4">Peripheral Type</th>
								<th id="5">Type of Peripheral HW</th>
								<th id="6">Make Name</th>
								<th id="7">Model Name</th>
								<th id="7">Assignuser</th>
								<th id="7">Quantity</th>
								<th id="7">Date & Time</th>
								<th>Select To Approve/Reject</th>

							</tr>
						</thead>

					</table>

					<input type="button" class="btn btn-success btn-sm" id="approvebtn"
						name="approvebtn" value="Approve"> <input type="button"
						class="btn btn-success btn-sm" id="rejectbtn" value="Reject">
				</div>
			</div>
		</div>
	</div>
</div>
<c:url value="Excel_Peripheral_Assets_AssignSearch" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />

</form:form>
<script nonce="${cspNonce}"> 

document.addEventListener('DOMContentLoaded', function () {
    
	document.getElementById('approvebtn').onclick = 
		function() {
		return setApproveStatus();
  	};
  	document.getElementById('rejectbtn').onclick = 
		function() {
		return setRejectStatus();
  	};document.getElementById('nSelAll').onclick = 
		function() {
		return setselectall();
  	};	
	document.getElementById('searchbtn').onclick = 
		function() {
		return Search();
  	};
	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};

});

</script>

<script type="text/javascript" nonce="${cspNonce}">
function data(BloodReport){
	debugger;
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
	var assets_type=$("#assets_type").val() ;
	var section =$("#section").val();
	var status =$("#status").val();
 	
	$.post("getFilteredassetassignperipheral?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		assets_type:assets_type,section:section,status:status},function(j) {
			console.log("sdgg"+j);
			//left here
			for (var i = 0; i < j.length; i++) {
         	var data =[j[i].section,j[i].assets_name,j[i].type_of_hw,j[i].make_name,j[i].model_name,
         		j[i].username,j[i].assetcount,j[i].created_date,j[i].chekboxaction];
                 jsondata.push(data);
         }
			$("#nSelAll").prop('checked', false);
			$('#tregn').text("0");
			$('#tregnn').text(j.length);
			
		
	});
	$.post("getTotalAssetAssignperipheralCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,status:status,section:section},function(j) {
		FilteredRecords = j;
	});
}
</script>

<!-- for Functions -->
<script nonce="${cspNonce}">
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
			$.post("Approve_PeripheralAssetsAssignData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
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

			$.post("Approve_PeripheralAssetsAssignData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
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

function data(approveassignReport){
	
	jsondata = [];
	var table = $('#'+approveassignReport).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	var section=$("#section").val();
	var computing_asset_type=$("#assets_type").val();
	var assets_uniq=$("#assets_uniq").val();
	
	$.post("getFilteredassetassignperipheral?"+key+"="+value,{startPage:startPage,pageLength:pageLength,
		Search:Search,orderColunm:orderColunm,orderType:orderType
	,section:section,computing_asset_type:computing_asset_type,assets_uniq:assets_uniq
	},function(j) {

			for (var i = 0; i < j.length; i++) {
         	var data =[j[i].assetid,j[i].assets_name,j[i].machine_number,j[i].dply_envt,j[i].created_by,j[i].section,j[i].chekboxaction ];
                 jsondata.push(data);
         }
			
		
	});
	$.post("getTotalAssetAssignperipheralCount1?"+key+"="+value,{Search:Search,section:section,computing_asset_type:computing_asset_type,assets_uniq:assets_uniq
		},function(j) {
		FilteredRecords = j;
	});
}

$(document).ready(function() {

	
	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	$('#btn-submit').on('click', function(){
    	table.ajax.reload();
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




function Search(){
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val($("#status").val());
	$("#section1").val($("#section").val());
	//$("#searchForm").submit();
	table.ajax.reload();
}


function getExcel() {	

	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val($("#status").val());
	
	
	document.getElementById('ExcelForm').submit();
} 
</script>





