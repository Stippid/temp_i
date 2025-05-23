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
	action="Processor_or_typeAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="Processor_or_typeCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>TYPE OF PROCESSOR MASTER</h5>
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
									class="form-control-label">TYPE OF PROCESSOR </label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="processor_type"
									path="processor_type" class="form-control text_transform_upper"
									autocomplete='off' maxlength="100"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Processor_or_type_Url class="btn btn-success btn-sm">Clear</a>
				<input type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn"> <i class="fa fa-search"></i><input
					type="button" class="btn btn-info btn-sm" id="btn-reload"
					value="Search">
			</div>
		</div>
	</div>
</form:form>



<c:url value="EditProcessor_or_typeUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteProcessor_or_typeUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="Processormastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="processor_type1" id="processor_type1"
		value="0" />

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
						<table id="Processor_or_type_reporttbl"
							class="display">
							<thead>
								<tr>
									<th>Type Of Processor</th>
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
		document.getElementById('processor_type').onclick = function() {
			return specialcharecterProcessor(this);
		};

		document.getElementById('btn-reload').onclick = function() {
			table.ajax.reload();
		};
		document.getElementById('btnExport').onclick = function() {
			return getExcel();
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

		mockjax1('Processor_or_type_reporttbl');
		table = dataTable('Processor_or_type_reporttbl');
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
		var table = $('#Processor_or_type_reporttbl').DataTable();
		var info = table.page.info();

		var processor_type = $("#processor_type").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getProcessor_or_typeReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			processor_type : processor_type
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].processor_type, j[i].id ]);
			}
		});
		$.post("getProcessor_or_typeTotalCount?" + key + "=" + value, {
			Search : Search,
			processor_type : processor_type
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#processor_type").val().trim() == "") {
			alert("Please Enter Type Of Processor");
			$("input#processor_type").focus();
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

		var processor_type = $("#processor_type").val();
		$("#processor_type1").val(processor_type);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}

	function specialcharecterProcessor(a) {
		var iChars = "@#^&*$,.:;%!+_-[]";
		var data = a.value;
		for (var i = 0; i < data.length; i++) {
			if (iChars.indexOf(data.charAt(i)) != -1) {
				alert("This " + data.charAt(i)
						+ " special characters not allowed.");
				a.value = "";
				return false;
			}
		}
		return true;
	}
</script>
