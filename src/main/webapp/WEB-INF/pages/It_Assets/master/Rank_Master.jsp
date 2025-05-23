
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
    action="RANKAction?${_csrf.parameterName}=${_csrf.token}"
    method="POST" modelAttribute="RANKCMD">
    <div class="container" align="center">
        <div class="card">
            <div class="card-header">
                <h5>RANK MASTER</h5>
            </div>

            <div class="card-body card-block">
                <div class="row">
                    <div class="col-md-6">
                        <div class="row form-group">
                            <div class="col-md-4">
                                <strong class="color_red">*</strong> <label for="text-input"
                                    class="form-control-label">RANK NAME</label>
                            </div>
                            <div class="col-md-8">
                                <form:input type="text" id="rank" name="rank" path="rank"
                                    class="form-control text_transform_upper" autocomplete='off'
                                    maxlength="100"></form:input>
                            </div>
                        </div>
                    </div>
                    <div class='col-md-6'>
                        <div class='row form-group'>
                            <div class='col-md-4'>
                                <strong class="color_red">*</strong> <label for="text-input"
                                    class="form-control-label">STATUS</label>
                            </div>
                            <div class="col-md-8">
                                <form:select id="status" name="status" path="status"
                                    class="form-control">
                                    <option value="0">--Select--</option>
                                    <option value="ACTIVE">ACTIVE</option>
                                    <option value="INACTIVE">INACTIVE</option>
                                </form:select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class='card-footer' align='center'>
                <a href=RANK_Url class="btn btn-success btn-sm">Clear</a> 
                <input type='submit' class='btn btn-primary btn-sm' value='Save' id="save_btn" /> 
                <i class="fa fa-search"></i>
                <input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
            </div>
        </div>
    </div>
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
					<table id="RANK_reporttbl" class="display">
						<thead>
							<tr>
								<th>RANK Name</th>
								<th>STATUS</th>
								<th>Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<c:url value="EditRANKUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteRANKUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="RANKmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="rank1" id="rank1" value="" />
	<input type="hidden" name="status1" id="status1" value="" />

</form:form>
<script nonce="${cspNonce}">

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('save_btn').onclick = function() {
			return isValidateClientSide();
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

		mockjax1('RANK_reporttbl');
		table = dataTable('RANK_reporttbl');
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
		var table = $('#RANK_reporttbl').DataTable();
		var info = table.page.info();

		var rank = $("#rank").val();
		var status = $("#status").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getRANKReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			rank : rank,
			status : status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].rank, j[i].status, j[i].id ]);
			}
		});
		$.post("getRANKTotalCount?" + key + "=" + value, {
			Search : Search,
			rank : rank,
			status : status
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("#rank").val().trim() == "") {
			alert("Please Enter Rank Name");
			$("#rank").focus();
			return false;
		}
		if ($("#status").val().trim() == "0") {
			alert("Please Select Status");
			$("#status").focus();
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

		var rank = $("#rank").val();
		$("#rank1").val(rank);
		
		var status = $("#status").val();
		$("#status1").val(status);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}
</script>
