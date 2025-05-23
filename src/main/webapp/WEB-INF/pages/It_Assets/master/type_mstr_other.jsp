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
	action="type_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="type_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>MODEL TYPE MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">

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
								<form:select id="type_of_hw" path="type_of_hw"
									class="form-control">
									<option value="0">--Select--</option>
									<%--           <c:forEach var="item" items="${gettypeofhwListDDL}" varStatus="num"  > --%>
									<%--                <option value="${item[0]}" name="${item[1]}" >${item[1]}</option> --%>
									<%--          </c:forEach> --%>
								</form:select>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">MODEL TYPE</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="type" path="type"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="50"></form:input>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class='col col-md-4'>
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"></strong>Status</label>
							</div>
							<div class='col-12 col-md-8'>
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
				<a href=Type_mstr_other_Url class="btn btn-success btn-sm">Clear</a>
				<!--            <input type='submit' class='btn btn-primary btn-sm' value='Save'  onclick='return isValidateClientSide()' /> -->
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
				<table id="type_mstr_reporttbl"
					class="display">
					<thead>
						<tr>
							<th>Type Of Peripheral</th>
							<th>Type of Hardware</th>
							<th>Model Type</th>
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
		document.getElementById('peripheral_type').onchange = function() {
			return fn_Peripheral();
		};

	});

	$(document).ready(function() {

		mockjax1('type_mstr_reporttbl');
		table = dataTable('type_mstr_reporttbl');

	});
	function data(tableName) {
		jsondata = [];
		var table = $('#type_mstr_reporttbl').DataTable();
		var info = table.page.info();

		var peripheral_type = $("#peripheral_type").val();
		var type_of_hw = $("#type_of_hw").val();
		var type = $("#type").val();
		var status = $("#status").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getType_other_mstrReportDataList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw,
			type : type,
			status : status
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				var data = [ j[i].assets_name, j[i].type_of_hw, j[i].type,
						j[i].chekboxaction ];
				jsondata.push(data);
			}
			$("#nSelAll").prop('checked', false);
			$('#tregn').text("0");
			$('#tregnn').text(j.length);
		});
		$.post("getType_other_mstrTotalCount?" + key + "=" + value, {
			Search : Search,
			peripheral_type : peripheral_type,
			type_of_hw : type_of_hw,
			type : type,
			status : status
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

			$.post("Approve_type_of_hw_model_type_Data?" + key + "=" + value, {
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
			alert("Please Select the Data for Deletion.");
		} else {

			$.post("Delete_type_of_hw_model_type_Data?" + key + "=" + value, {
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