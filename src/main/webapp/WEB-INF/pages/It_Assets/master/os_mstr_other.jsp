<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script nonce="${cspNonce}" src="js/miso/miso_js/jquery-2.2.3.min.js"
	nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script>

<script nonce="${cspNonce}" src="js/JS_CSS/jquery-1.10.2.js"
	type="text/javascript" nonce="${cspNonce}"></script>

<link nonce="${cspNonce}" href="js/Calender/jquery-ui.css"
	rel="Stylesheet" nonce="${cspNonce}"></link>
<script nonce="${cspNonce}" src="js/Calender/jquery-ui.js"
	type="text/javascript" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" src="js/miso/commonJS/commonmethod.js"
	type="text/javascript" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" src="js/miso/orbatJs/orbatAll.js"
	type="text/javascript" nonce="${cspNonce}"></script>
<link nonce="${cspNonce}" href="js/cue/cueWatermark.css"
	rel="Stylesheet" nonce="${cspNonce}"></link>
<link nonce="${cspNonce}" rel="stylesheet"
	href="js/InfiniteScroll/css/datatables.min.css" nonce="${cspNonce}">
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>


<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Formname" id="Formid"
	action="os_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="os_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>OPERATING SYSTEM : OTHERS</h5>
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
									class="form-control-label">OPERATING SYSTEM</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="os" path="os"
									class="form-control text_align_center" autocomplete='off'
									maxlength="100"></form:input>
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
				<a href=Os_mstr_Other_Url class="btn btn-success btn-sm">Clear</a>
				<!--            <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()'> -->
				<i class="fa fa-search"></i><input type="button"
					class="btn btn-info btn-sm" id="btn-reload" value="Search">
			</div>
		</div>
	</div>
</form:form>

<br>
<div class="container" align="center">
	<div class="card">
		<div class="card-body">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span> <input type="hidden" id="CheckVal"
					name="CheckVal"> <b><input type=checkbox id='nSelAll'
					name='tregn'>Select all [<span id="tregn">0</span><span
					id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b>
				<table id="os_mstr_reporttbl"
					class="display">
					<thead>
						<tr>
							<th>Operating System</th>
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
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('btn-reload').onclick = function() {
			table.ajax.reload();
		};
		document.getElementById('approve_btn').onclick = function() {
			return setApproveStatus();
		};
		document.getElementById('delete_btn').onclick = function() {
			return setDeleteStatus();
		};
		document.getElementById('nSelAll').onclick = function() {
			return setselectall();
		};
		document.addEventListener('change', function(event) {
			if (event.target.classList.contains('nrCheckBox')) {
				var encryptedPk = event.target.getAttribute('data-id');
				checkbox_count(event.target, encryptedPk);

			}
		});

	});
	$(document).ready(function() {

		mockjax1('os_mstr_reporttbl');
		table = dataTable('os_mstr_reporttbl');

	});
	function data(tableName) {
		jsondata = [];
		var table = $('#os_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var os = $("#os").val();
		var status = $("#status").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getOs_mstr_OtherReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			os : os,
			status : status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var data = [ j[i].os, j[i].chekboxaction ];
				jsondata.push(data);
			}
			$("#nSelAll").prop('checked', false);
			$('#tregn').text("0");
			$('#tregnn').text(j.length);
		});
		$.post("getOs_mstr_OtherTotalCount?" + key + "=" + value, {
			Search : Search,
			os : os,
			status : status
		}, function(j) {
			FilteredRecords = j;
		});
	}
	function isValidateClientSide() {

		if ($("input#os").val().trim() == "") {
			alert("Please Enter Operating System");
			$("input#os").focus();
			return false;
		}
		return true;
	}
	function findselected() {
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}

	function setselectall() {

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
		var ii = 0;
		$("#nrTable").empty();

		for (var i = 0; i < data.length; i++) {
			var nkrow = "<tr id='nrTable' padding='5px;'>";
			nkrow = nkrow + "<td>&nbsp;&nbsp;";
			nkrow = nkrow + data[i] + "(" + data[i] + ")</td>";
			$("#nrTable").append(nkrow);
			ii = ii + 1;
		}
		$("#tregn").text(ii);
	}

	function setApproveStatus() {

		findselected();

		var a = $("#CheckVal").val();

		if (a == "") {
			alert("Please Select the Data for Approval");
		} else {

			$.post("Approve_OsotherData?" + key + "=" + value, {
				a : a,
				status : "1"
			}).done(function(j) {
				alert(j);
				location.reload();
			});
		}
	}
	function setDeleteStatus() {

		findselected();

		var a = $("#CheckVal").val();

		if (a == "") {
			alert("Please Select the Data for Rejection.");
		} else {

			$.post("Delete_OsotherData?" + key + "=" + value, {
				a : a
			}).done(function(j) {
				alert(j);
				location.reload();
			});
		}
	}

	function setRejectStatus() {

		findselected();

		var a = $("#CheckVal").val();

		if (a == "") {
			alert("Please Select the Data for Reject");
		} else {

			$.post("Approve_ComputingAssetsData?" + key + "=" + value, {
				a : a,
				status : "3"
			}).done(function(j) {
				alert(j);
				window.location.reload();
				Search();
			});
		}
	}

	var check_count = 0;
	function checkbox_count(obj, id) {
		if (document.getElementById(obj.id).checked == true) {
			check_count++;

		}
		if (document.getElementById(obj.id).checked == false) {
			check_count--;

		}

		document.getElementById('tregn').innerHTML = check_count;

	}
</script>