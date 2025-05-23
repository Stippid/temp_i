<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"
	nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"
	nonce="${cspNonce}">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Formname" id="Formid"
	action="VENDORAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="VENDORCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Vendor  MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12 display_none" id="divLine"">
					<span class="line"></span>
				</div>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-6'>
							<strong class="color_red">*</strong> <label for="text-input"
								class="form-control-label">Supplier Name</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="supplier" path="supplier"
								class="form-control text_transform_upper" autocomplete='off'
								maxlength="100"></form:input>
						</div>
					</div>
				</div>
				
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-6'>
							<strong class="color_red">*</strong> <label for="text-input"
								class="form-control-label">Mobile Number</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="contact_number" path="contact_number"
								class="form-control text_transform_upper" autocomplete='off'
								maxlength="10"></form:input>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=maintenance_agency_Url class="btn btn-success btn-sm">Clear</a>
				<input type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn" /> <i class="fa fa-search"></i><input type="button"
					class="btn btn-info btn-sm" id="btn-reload" value="Search">
			</div>
		</div>
	</div>
</form:form>
<c:url value="deleteSUPPLYUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>
<c:url value="EditSUPPLYUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>
<c:url value="SUPPLYmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0"/>
	<input type="hidden" name="supplier1" id="supplier1" value="0"/>
	<input type="hidden" name="contact_number1" id="contact_number1" value="0"/>
</form:form>
<div class="container text_align_center">
	<i class="fa fa-file-excel-o export_excel" id="btnExport"
		aria-hidden="true" title="EXPORT TO EXCEL"></i>
</div>
<br>
<div class="container">
	<div class="col-md-12">
		<table id="VENDOR_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Supplier Name</th>
					<th>Contact number</th>
					<th>Action</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript" nonce="${cspNonce}">

document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('save_btn').onclick = function() {
		return isValidateClientSide();	
  	};
  	document.getElementById('btnExport').onclick = function() {
		return getExcel();	
  	};
  	 document.addEventListener('click', function(event) {
         if (event.target.classList.contains('action_update')) {
             var encryptedPk = event.target.getAttribute('data-encrypted-pk');
             if (confirm('Are you sure you want to Update?')) {
                 editData(encryptedPk);
             }
         } else if (event.target.classList.contains('action_delete')) {
        	 debugger;
             var encryptedPk = event.target.getAttribute('data-encrypted-pk');
             if (confirm('Are you sure you want to Delete?')) {
                 deleteData(encryptedPk);
             }
         }
     });
  
});
$(document).ready(function () {
	mockjax1('VENDOR_reporttbl');
	table = dataTable('VENDOR_reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
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
function data(tableName){
	jsondata = [];
	var table = $('#VENDOR_reporttbl').DataTable();
	var info = table.page.info();
	var supplier =$("#supplier").val();
	var contact_number =$("#contact_number").val();
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getVendorReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,supplier:supplier,contact_number:contact_number},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].supplier,j[i].contact_number,j[i].id]);
		}
	});
	$.post("getVendorTotalCount?"+key+"="+value,{Search:Search,supplier:supplier,contact_number:contact_number},function(j) {
		FilteredRecords = j;
	});
}
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('save_btn').onclick = function() {
		return isValidateClientSide();	
  	};
});
function isValidateClientSide()
{

if($("#supplier").val().trim() == "")
 {
	  alert("Please Enter Supply Name");
	  $("#supplier").focus();
	  return false;
 } 

if($("#contact_number").val().trim() == "")
 {
	  alert("Please Enter Contact Number");
	  $("#contact_number").focus();
	  return false;
 } 
return true; 
} 

function deleteData(obj){

	    document.getElementById('deleteid').value=obj;
	    document.getElementById('deleteForm').submit();
}
function editData(obj){

    document.getElementById('updateid').value=obj;
    document.getElementById('updateForm').submit();
}

function getExcel() {
	debugger
	
	var supplier=$("#supplier").val();
	$("#supplier1").val(supplier);
	
	var contact_number=$("#contact_number").val();
	$("#contact_number1").val(contact_number);
 	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('searchForm').submit();
} 
</script>
