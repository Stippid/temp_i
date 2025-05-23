<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/lib/aes.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/AesUtil.js"></script>
<script nonce="${cspNonce}" src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link nonce="${cspNonce}" href="js/Calender/jquery-ui.css"
	rel="Stylesheet"></link>
<script nonce="${cspNonce}" src="js/Calender/jquery-ui.js"
	type="text/javascript"></script>
<script nonce="${cspNonce}" src="js/miso/commonJS/commonmethod.js"
	type="text/javascript"></script>
<link nonce="${cspNonce}" rel="stylesheet"
	href="js/InfiniteScroll/css/datatables.min.css">
<script nonce="${cspNonce}" src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<form:form name="" id=""
	action="make_master_Action?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal" modelAttribute="make_master_CMD">
	<spring:htmlEscape defaultHtmlEscape="true" />
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>MAKE/BRAND NAME:OTHERS</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">CATEGORY</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category" class="form-control">
										<option value="0">--Select--</option>
										<option value="1">Computing</option>
										<option value="2">Peripheral</option>
										<option value="3">Consumable</option>
									</select> <input type="hidden" id="make_no" name="make_no"
										class="form-control" maxlength="3" value="0"
										onkeypress="return 	Number(event, this)" autocomplete="off"
										readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">ASSETS NAME </label>
								</div>
								<div class="col-md-8">
									<select name="assets_name" id="assets_name"
										class="form-control">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>
						</div>
					</div>




					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">MAKE/BRAND NAME</label>
								</div>
								<div class="col-md-8">
									<input id="make_name" name="make_name" maxlength="100"
										class="form-control text_transform_upper" autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										class="color_red"></strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
										<option value="0">Pending</option>
										<option value="1">Approved</option>

									</select>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class='card-footer' align='center'>
					<a href="make_master_otherUrl" class="btn btn-success btn-sm">Clear</a>
					<!--           				  <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()' /> -->
					<i class="fa fa-search"></i><input type="button"
						class="btn btn-info btn-sm" id="btn-reload" value="Search">
				</div>
			</div>
		</div>
	</div>


	<!--     <div align="right" class="container"> -->
	<!-- 	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i> -->
	<!-- 	</div><br> -->


	<div class="container" id="divPrint" align="center">
		<div class="card">
			<div class="card-body">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> <input type="hidden" id="CheckVal"
						name="CheckVal"> <b><input type=checkbox id='nSelAll'
						name='tregn'>Select all [<span id="tregn">0</span><span
						id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b>

					<table id="getMakeSearch"
						class="display">
						<thead>
							<tr>
								<th id="4">Category</th>
								<th id="2">Assets Name</th>
								<th id="3">Make/Brand Name</th>

								<th>Select To Approve/Reject</th>


							</tr>
						</thead>
					</table>


					<input type="button" class="btn btn-success btn-sm" value="Approve"
						id="approve_btn"> <input type="button"
						class="btn btn-success btn-sm" value="Delete" id="delete_btn">


				</div>
			</div>
		</div>
	</div>
</form:form>






<script type="text/javascript" nonce="${cspNonce}">


document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('category').onchange = function() {
			return fn_Category();	
	  	};
	  	document.getElementById('btn-reload').onclick = function() {
	  		table.ajax.reload();
	  	};
	 	document.getElementById('nSelAll').onclick = function() {
	 		return setselectall();
	  	};  
	  	document.getElementById('approve_btn').onclick = function() {
	 		return setApproveStatus();
	  	}; 
	  	document.getElementById('delete_btn').onclick = function() {
	 		return setDeleteStatus();
	  	}; 
	  	document.addEventListener('change', function(event) {
	         if (event.target.classList.contains('nrCheckBox')) {
	             var encryptedPk = event.target.getAttribute('data-id');
	                 checkbox_count(event.target, encryptedPk);
	           
	         }
	     });
	  	
});


function data(getMakeSearch){
	jsondata = [];

	var assets_name =$("#assets_name").val();
	var category = $("#category").val();
	var make_name = $("#make_name").val();
	
	var table = $('#'+getMakeSearch).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
	
	var category=$("#category").val() ;
 	var assets_name=$("#assets_name").val() ;
 	var make_name=$("#make_name").val() ;
 	var status=$("#status").val();
 	
 	
	$.post("getFilteredMakeOther?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		category:category,assets_name:assets_name,make_name:make_name,status:status},function(j) {
			
		for (var i = 0; i < j.length; i++) {
			var data =[j[i].category,j[i].assets_name,j[i].make_name
			,j[i].chekboxaction
           ];
            jsondata.push(data);
		}
		$("#nSelAll").prop('checked', false);
		$('#tregn').text("0");
		$('#tregnn').text(j.length);
	});
	$.post("getTotalMakeCountOther?"+key+"="+value,{Search:Search,category:category,assets_name:assets_name,make_name:make_name,status:status},function(j) {
		FilteredRecords = j;
	});
}

$(document).ready(function() {
	
	$("#make_no").hide();
	var status1=$("#status").val();
	
	mockjax1('getMakeSearch');
	table = dataTable('getMakeSearch');
	$('#btn-reload').on('click', function(){
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
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
}

function Validate() {
	if ($("select#category").val() == 0) {
		alert("Please Select Category");
		$("select#category").focus();
		return false;
	}
	
	if ($("select#assets_name").val() == 0) {
		alert("Please Select Assets Name");
		$("select#assets_name").focus();
		return false;
	}
	
	if ($("input#make_name").val().trim() == "") {
		alert("Please Enter Make/Brand Name");
		$("input#make_name").focus();
		return false;
	}
	return true;
}


function fn_Category() {
		
		
		var categogry_id = $("select#category").val();
		$.post("getCategoryList?" + key + "=" + value, {
			categogry_id: categogry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#assets_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
	
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

			$.post("Approve_MakeotherData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			location.reload();
		}); 
	}	
}

function setDeleteStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Rejection."); 
	}else{

			$.post("Delete_MakeotherData?"+key+"="+value, {a:a}).done(function(j) {
			alert(j);
			location.reload();
		}); 
	}	
}

function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Data Rejected Successfully."); 
	}else{

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			location.reload();
	
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