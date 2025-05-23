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
	action="hdd_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="hdd_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>HDD CAPACITY MASTER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<strong class="color_red">*</strong> <label for="text-input"
										class="form-control-label">HDD CAPACITY</label>
								</div>
								<div class="col-md-4">
									<input type="text" id="hdd" name="hdd"
										class="form-control text_align_center" autocomplete='off'
										maxlength="10" />
									<!--  onkeypress="return digitKeyOnly(event)"  -->
								</div>
								<div class="col-md-4">
									<select name="size" id="size" class="form-control">
										<option value="0">--Select--</option>
										<option value="MB">MB</option>
										<option value="GB">GB</option>
										<option value="TB">TB</option>
									</select>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>

				<a href=Hdd_mstr_Url class="btn btn-success btn-sm">Clear</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Save'
					id="save_btn"> <i class="fa fa-search"></i><input
					type="button" class="btn btn-info btn-sm" id="btn-reload"
					value="Search">
			</div>
		</div>
	</div>

</form:form>


<c:url value="EditHdd_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletehdd_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>


<c:url value="HDDmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="hdd1" id="hdd1" value="0" />
	<input type="hidden" name="size1" id="size1" value="0" />
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
					<div class="col-md-12">
						<table id="hdd_mstr_reporttbl" class="display">
							<thead>
								<tr>
									<th>HDD Capacity</th>
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
		document.getElementById('hdd').onkeypress = function() {
			return digitKeyOnly(event);
		};
		document.getElementById('hdd').onkeypress = function() {
			return specialcharecterhdd(this);
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

		mockjax1('hdd_mstr_reporttbl');
		table = dataTable('hdd_mstr_reporttbl');
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
		var table = $('#hdd_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var hdd = $("#hdd").val();
		var hdd_capacity = $("#size").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getHdd_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			hdd : hdd,
			hdd_capacity : hdd_capacity
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].hdd, j[i].id ]);
			}
		});
		$.post("getHdd_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			hdd : hdd,
			hdd_capacity : hdd_capacity
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#hdd").val().trim() == "") {
			alert("Please Enter HDD Capacity");
			$("input#hdd").focus();
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

	function digitKeyOnly(e) {
		var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
		var value = Number(e.target.value + e.key) || 0;

		if ((keyCode >= 37 && keyCode <= 40)
				|| (keyCode == 8 || keyCode == 9 || keyCode == 13)
				|| (keyCode >= 48 && keyCode <= 57 || keyCode == 46)) {
			return true;
		}
		return false;
	}

	function getExcel() {

		var ram = $("#hdd").val();

		$("#hdd1").val(ram);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}

	function specialcharecterhdd(a) {
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
