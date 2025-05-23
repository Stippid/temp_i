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
	action="dply_env_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="dply_env_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>DEPLOYMENT ENVIRONMENT MASTER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-10">
						<div class="row form-group">
							<div class="col-md-3">
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">DEPLOYMENT ENVIRONMENT</label>
							</div>
							<div class="col-md-6">
								<form:input type="text" id="dply_env" path="dply_env"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="100"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>

				<a href=Dply_env_mstr_Url class="btn btn-success btn-sm">Clear</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn"> <i class="fa fa-search"></i><input
					type="button" class="btn btn-info btn-sm" id="btn-reload"
					value="Search">
			</div>
		</div>
	</div>


</form:form>



<c:url value="EditDply_env_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletedply_env_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="Dplymastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="dply_env1" id="dply_env1" value="0" />

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
					<table id="dply_env_mstr_reporttbl" class="display">
						<thead>
							<tr>
								<th>Deployment Environment</th>
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

		mockjax1('dply_env_mstr_reporttbl');
		table = dataTable('dply_env_mstr_reporttbl');
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
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
		var table = $('#dply_env_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var dply_env = $("#dply_env").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getDply_env_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			dply_env : dply_env
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].dply_env, j[i].id ]);
			}
		});
		$.post("getDply_env_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			dply_env : dply_env
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#dply_env").val().trim() == "") {
			alert("Please Enter Deployment Environment");
			$("input#dply_env").focus();
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

		var dply_env = $("#dply_env").val();
		$("#dply_env1").val(dply_env);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}
</script>
