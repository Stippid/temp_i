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
	<style nonce="${cspNonce}">

.f12bold
{
font-size: larger;
font-weight: bolder;
color: red;
}
</style>
<form:form name="peripheral_assests" id="peripheral_assests"
	action="peripheral_assests_Serviceablity_detailsAction" method="post" class="form-horizontal"
	modelAttribute="">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>PERIPHERAL CENSUS REPORT</h5>
					<h4 class="grey_head">(Reported On: ${date})</h4>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Serviceable State</label>
								</div>
								<div class="col-md-8">
									<select name="s_state" id="s_state" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getServiceable_StateList}"
											varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										<option value="4">Discard</option>
									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6 display_none" id="un_show">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">UN-Serviceable State</label>
								</div>
								<div class="col-md-8">
									<select name="unserviceable_state"
										id="unserviceable_state" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${UNServiceableList}"
											varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
			<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Peripheral Type </label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type"   class="form-control">
									<option value="0" >--Select--</option>
										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Section </label>
								</div>
								<div class="col-md-8">
									<select id="section" name="section" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}"
												>${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					
						
					</div>
					<div class="row">
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label ">User Name</label>
								</div>
								<div class="col-md-8">
								<input type="text" id ="user_name" name="user_name" class="form-control autocomplete"  autocomplete="off">
								</div>
							</div>
						</div>
						</div>
			
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href="Peripherals_Assests_details_url"
						class="btn btn-success btn-sm">Clear</a> <i class="fa fa-search"></i><input type="button"
						class="btn btn-info btn-sm" id="searchid"  value="Search">
						<i class="fa fa-download"></i><input type="button" class="btn btn-primary btn-sm" value="Print" id="downloadid" >
			 <i class="fa fa-file-excel-o export_excel" id="btnExport"  aria-hidden="true" title="EXPORT TO EXCEL"></i>
				</div>
			</div>
		</div>
	</div>


	<div id="viewpage" class="container">
<div class="card">
<div class="card-body">

		<div class="col-md-12 display_block" id="getSearch_Letter">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_Assets_Serviceablity_details"
					class="table no-margin table-striped  table-hover  table-bordered display">
					<thead class="table_thead">
						<tr>
							         <th>Ser No</th>
							         <th>Peripheral Type</th>
                                     <th>Type of HW</th>
                                     <th>Year Of Proc</th>
                                     <th>Year Of Manufacturing</th>
                                     <th>Proc Cost</th>
                                     <th>Machine Make</th>
                                     <th>Machine No</th>
                                     <th>Remarks</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
							
								        <td >${num.index+1}</td>
                                        <td >${item[0]}</td>
                                        <td >${item[1]}</td>
                                        <td >${item[2]}</td>
                                        <td >${item[3]}</td>
                                        <td >${item[4]}</td>
                                        <td >${item[5]}</td>
                                        <td >${item[6]}</td>
                                        <td >${item[8]}</td>
							</tr>
					
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	

	
	<div id="viewpage2" class="container">
		<div class="col-md-12 display_block" id="getSearch_Letter">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_Assets_Serviceablity_details2"
					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
					<thead>
						<tr>
							<th class="f_12 text_align_center f12bold">Total</th>
							<td class="bgf0f3f5 rgb0 text_align_center width50"><span>${listsize}</span></td>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	</div>
	
	</div>
	
</form:form>

<c:url value="GetSearch_Assests_Peripherals_details" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="s_state1">
	<input type="hidden" name="s_state1" id="s_state1" value="0">
	<input type="hidden" name="assets_type1" id="assets_type1" value="0">
	<input type="hidden" name="unserviceable_state1" id="unserviceable_state1" value="0">
</form:form>

<c:url value="Download_Peripherals_Assets_Details" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="computing_assets_dn">
	<input type="hidden" name="s_state2" id="s_state2" value="0">
	<input type="hidden" name="asset_type2" id="asset_type2" value="0">
	<input type="hidden" name="unserviceable_state2" id="unserviceable_state2" value="0">
</form:form>

<c:url value="Excel_Peripheral_Assets_Details" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="computing_assets_dn1">
	<input type="hidden" name="s_state3" id="s_state3" value="0">
	<input type="hidden" name="asset_type3" id="asset_type3" value="0">
	<input type="hidden" name="unserviceable_state3" id="unserviceable_state3" value="0">
</form:form>
<Script nonce="${cspNonce}">

document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('searchid').onclick = 
		function() {
		return Search();
  	};
  	document.getElementById('downloadid').onclick = 
		function() {
		return downloaddata();
  	};
	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};
	document.getElementById('s_state').onchange = 
		function() {
		return unservisable();
  	};
  	
  
	
});

$(document).ready(function() {
	
	if ('${s_state1}' !=""){
	
		$("select#s_state").val('${s_state1}');
	} 
	
	if ('${assets_type1}' !=""){
		
		$("select#assets_type").val('${assets_type1}');
	} 
	
	if ('${unserviceable_state1}' !=""){
	
		$("select#unserviceable_state").val('${unserviceable_state1}');
	} 
	unservisable();
});

function Search()
{	
	$("#s_state1").val($("#s_state").val()) ;	
	$("#assets_type1").val($("#assets_type").val()) ;	
	$("#unserviceable_state1").val($("#unserviceable_state").val()) ;	
	document.getElementById('searchForm').submit();
}
function unservisable() {
	var a = $("select#s_state option:selected").text();
	
	if (a == "Un-serviceable") {
		$("#un_show").show();

	} else {
		$("#un_show").hide();
		$("#unserviceable_state").val("0");
	}
}
function downloaddata(){
 	
	$("#s_state2").val($("#s_state").val());
	$("#unserviceable_state2").val($("#unserviceable_state").val());
	$("#asset_type2").val($("#assets_type").val());
	document.getElementById('downloadForm').submit();		 
}


function getExcel() {	
	$("#s_state3").val($("#s_state").val());
	
	$("#unserviceable_state3").val($("#unserviceable_state").val());
	$("#asset_type3").val($("#assets_type").val());
	
	document.getElementById('ExcelForm').submit();
} 
</script>
