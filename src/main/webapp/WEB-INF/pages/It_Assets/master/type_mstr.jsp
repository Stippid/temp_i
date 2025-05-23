
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Formname" id="Formid"
	action="type_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="type_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>MODEL TYPE MASTER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF PERIPHERAL</label>
							</div>
							<div class="col-md-8">
								<form:select id="peripheral_type" path="peripheral_type"
									name="peripheral_type" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${CategoryList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF HARDWARE</label>
							</div>
							<div class="col-md-8">
								<form:select id="type_of_hw" path="type_of_hw"
									class="form-control">
									<option value="0">--Select--</option>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">MODEL TYPE</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" id="type" path="type"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="50"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Type_mstr_Url class="btn btn-success btn-sm">Clear</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn" /> <i class="fa fa-search"></i><input type="button"
					class="btn btn-info btn-sm" id="btn-reload" value="Search">
			</div>
		</div>
	</div>
</form:form>



<c:url value="EditType_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletetype_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="TypeHwmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="peripheral_type1" id="peripheral_type1"
		value="0" />
	<input type="hidden" name="type_of_hw1" id="type_of_hw1" value="0" />
	<input type="hidden" name="type1" id="type1" value="0" />
</form:form>
<br>
<div align="center" class="container">
	<div class="card">
		<div class="card-body">
			<div class="" id="divPrint">


				<button class="download-excel-btn" id="btnExport"
					title="EXPORT TO EXCEL">
					<i class="fa fa-file-excel-o"></i> Download Excel
				</button>



				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> <input type="hidden" id="CheckVal"
						name="CheckVal">


					<table id="type_mstr_reporttbl" class="display">
						<thead>
							<tr>
								<th>Type Of Peripheral</th>
								<th>Type of Hardware</th>
								<th>Model Type</th>
								<th>Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<script nonce="${cspNonce}">
	$(document).ready(function() {

		mockjax1('type_mstr_reporttbl');
		table = dataTable('type_mstr_reporttbl');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
		$('#btnExport').on('click', function() {
			getExcel();
		});
		$('#peripheral_type').on('change', function() {
			fn_Peripheral();
		});

		document.addEventListener('click', function(event) {
			if (event.target.classList.contains('action_update')) {
				var encryptedPk = event.target.getAttribute('data-id');
				if (confirm('Are you sure you want to Update?')) {
					editData(encryptedPk);
				}
			} else if (event.target.classList.contains('action_delete')) {
				var encryptedPk = event.target.getAttribute('data-id');
				if (confirm('Are you sure you want to Delete?')) {
					deleteData(encryptedPk);
				}
			}
		});

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
		var table = $('#type_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var peripheral_type = $("#peripheral_type").val();
		var type_of_hw = $("#type_of_hw").val();
		var type = $("#type").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getType_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw,
			type : type
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].assets_name, j[i].type_of_hw, j[i].type,
						j[i].id ]);
			}
		});
		$.post("getType_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw,
			type : type
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {
		if ($("select#peripheral_type").val() == 0) {
			alert("Please Select Type Of Peripheral");
			$("select#peripheral_type").focus();
			return false;
		}

		if ($("select#type_of_hw").val() == 0) {
			alert("Please Enter Type of Hardaware");
			$("select#type_of_hw").focus();
			return false;
		}
		if ($("input#type").val().trim() == "") {
			alert("Please Enter Model Type");
			$("input#type").focus();
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

	function fn_Peripheral() {

		var peripheral_type = $("select#peripheral_type").val();
		$
				.post("getHWNameList?" + key + "=" + value, {
					peripheral_type : peripheral_type
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#type_of_hw").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function getExcel() {
		var peripheral_type = $("#peripheral_type").val();
		var type_of_hw = $("#type_of_hw").val();
		var type = $("#type").val();
		$("#peripheral_type1").val(peripheral_type);
		$("#type_of_hw1").val(type_of_hw);
		$("#type1").val(type);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}
</script>
