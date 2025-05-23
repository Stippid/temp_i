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
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"
	nonce="${cspNonce}">
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
	action="PeripheralMasterAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="PeripheralMasterCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>PERIPHERAL MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
<!-- 				<div class="col-md-12 display_none" id="divLine"> -->
<!-- 					<span class="line"></span> -->
<!-- 				</div> -->
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">PERIPHERAL TYPE </label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="peripheral_type"
									path="peripheral_type"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="50"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<input type='reset' class='btn btn-success btn-sm' value='Clear'
					id="clear_btn"> <input type='submit'
					class='btn btn-primary btn-sm' value='Save' id="save_btn">
				<input type='button' id='printId' class='btn btn-info btn-sm'
					value='Print'>
			</div>
		</div>
	</div>
</form:form>



<c:url value="EditPeripheralMasterUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletePeripheralMasterUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>
<br>

<div class="container" align="center">
	<div class="card">
		<div class="card-body">
			<div class="" id="divPrint">
				<div class="container" align="center">
					<div class="col-md-12">
						<table id="PeripheralMaster_reporttbl"
							class="display">
							<thead>
								<tr>
									<th>Peripheral Type</th>
									<th>Action</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('save_btn').onclick = function() {
			return isValidateClientSide();
		};

		document.getElementById('clear_btn').onclick = function() {
			return clearall();
		};

		document.getElementById('printId').onclick = function() {
			return printDiv();
		};
		document.addEventListener('click', function(event) {
			if (event.target.classList.contains('action_update')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to Update?')) {
					editData(encryptedPk);
				}
			} else if (event.target.classList.contains('action_delete')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to Delete?')) {
					deleteData(encryptedPk);
				}
			}
		});

	});

	$(document).ready(function() {

		mockjax1('PeripheralMaster_reporttbl');
		table = dataTable('PeripheralMaster_reporttbl');
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		} catch (e) {

		}

	});
	function data(tableName) {
		jsondata = [];
		var table = $('#PeripheralMaster_reporttbl').DataTable();
		var info = table.page.info();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getPeripheralMasterReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].peripheral_type, j[i].id ]);
			}
		});
		$.post("getPeripheralMasterTotalCount?" + key + "=" + value, {
			Search : Search
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#peripheral_type").val().trim() == "") {
			alert("Please Enter Peripheral Type");
			$("input#peripheral_type").focus();
			return false;
		}
		return true;
	}
	function editData(obj) {

		document.getElementById('updateid').value = obj;
		document.getElementById('updateForm').submit();
	}

	function deleteData(obj) {

		document.getElementById('deleteid').value = obj;
		document.getElementById('deleteForm').submit();
	}
</script>
