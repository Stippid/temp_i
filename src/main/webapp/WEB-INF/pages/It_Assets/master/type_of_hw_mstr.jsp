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
	action="type_of_hw_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="type_of_hw_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>TYPE OF HARDWARE MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12 display_none" id="divLine">
					<span class="line"></span>
				</div>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF PERIPHERAL</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:select id="peripheral_type" path="peripheral_type"
									class="form-control">
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
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF HARDWARE</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="type_of_hw" path="type_of_hw"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="50"></form:input>
								<div id="toh" class='col-md-12 color_red f_11'></div>
							</div>
						</div>
					</div>
				</div>
				<div class='col-md-12'>
					<div class='col-md-6'></div>
				</div>


			</div>
			<div class='card-footer' align='center'>
				<a href=Asset_mstr_Url class="btn btn-success btn-sm">Clear</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn"> <i class="fa fa-search"></i><input
					type="button" class="btn btn-info btn-sm" id="btn-reload"
					value="Search">
			</div>
		</div>
	</div>
</form:form>



<c:url value="EditType_of_hw_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletetype_of_hw_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="HWmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="peripheral_type1" id="peripheral_type1"
		value="0" />
	<input type="hidden" name="type_of_hw1" id="type_of_hw1" value="0" />
</form:form>

<br>

<div class="container" align="center">
	<div class="card">
		<div class="card-body">
			<div class="" id="divPrint">
				<div class="container" align="center">
					<button class="download-excel-btn" id="btnExport"
						title="EXPORT TO EXCEL">
						<i class="fa fa-file-excel-o"></i> Download Excel
					</button>
					<div class="col-md-12">
						<table id="type_of_hw_mstr_reporttbl" class="display">
							<thead>
								<tr>
									<th id="3">Type Of Peripheral</th>
									<th id="2">Type Of Hardware</th>
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
		document.getElementById('btn-reload').onclick = function() {
			table.ajax.reload();
		};
		document.getElementById('btnExport').onclick = function() {
			return getExcel();
		};
		document.getElementById('peripheral_type').oncchange = function() {
			return checkUPS();
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

		mockjax1('type_of_hw_mstr_reporttbl');
		table = dataTable('type_of_hw_mstr_reporttbl');

		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		} catch (e) {

		}
	});

	function checkUPS() {
		var top = document.getElementById("peripheral_type");
		var value = top.options[top.selectedIndex].text;
		if (value == "UPS") {
			document.getElementById("toh").innerHTML = "(for e.g. Line Interrupted, Online, Offline)";
			$("#toh").show();
		} else
			$("#toh").hide();
	}
	function data(tableName) {
		jsondata = [];
		var table = $('#type_of_hw_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var type_of_hw = $("#type_of_hw").val();
		var peripheral_type = $("#peripheral_type").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];

		$.post("getType_of_hw_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].assets_name, j[i].type_of_hw, j[i].id ]);
			}
		});
		$.post("getType_of_hw_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw
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

		if ($("input#type_of_hw").val().trim() == "") {
			alert("Please Enter Type of Hardware");
			$("input#type_of_hw").focus();
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

	function getExcel() {
		var peripheral_type = $("#peripheral_type").val();
		var type_of_hw = $("#type_of_hw").val();

		$("#peripheral_type1").val(peripheral_type);
		$("#type_of_hw1").val(type_of_hw);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}
</script>
