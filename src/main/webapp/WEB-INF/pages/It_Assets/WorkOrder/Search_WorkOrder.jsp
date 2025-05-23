<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<link rel="stylesheet" href="js/cue/cueWatermark.css">

<style nonce="${cspNonce}">
.mb {
	margin-bottom: 0;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}
.modal {
	position: fixed;
	top: 15%;
	left: 5%;
	height: 100%;
}

.modal-body {
	position: relative;
	overflow-y: auto;
	max-height: 400px;
	padding: 15px;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 50%;
}

.close {
	color: #dc3545;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.popupClose {
	color: #dc3545;
	/* float: right !important; */
	font-size: 28px;
	font-weight: bold;
}

.popupClose:hover, .popupClose:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>

<form:form name="user_mst" id="user_mst" action="user_mstAction"
	method='POST' modelAttribute="user_mstCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>SEARCH WORKORDER</h5>

				<div class="col-md-12" align="center">
					<div class="col-md-4"></div>
					<div class="col-md-4 ">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<button class="nav-link active font_weight_bold" id="home-tab"
										data-bs-toggle="tab" data-bs-target="#home" type="button"
										role="tab" aria-controls="home" aria-selected="true">COMPUTING</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link font_weight_bold" id="profile-tab" data-bs-toggle="tab"
										data-bs-target="#profile" type="button" role="tab"
										aria-controls="profile" aria-selected="false">PERIPHERAL</button>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>
			</div>
			<div class="tab-content" id="myTabContent">
				<div class="card-body card-block cue_text show active" id="home"
					role="tabpanel" aria-labelledby="home-tab">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Computing Assets Type</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="assets_type" id="assets_type"
										class="form-control">
										<option value="0">--select--</option>
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Work Order Number</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" name="wo_no" id="wo_no" class="form-control" />
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input"> W/O Dt </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="wo_dt" name="wo_dt" maxlength="30"
										class="form-control" autocomplete="off" required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Dt fwd to Wksp </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="dt_eqpt_reqd_fwd_wksp"
										name="dt_eqpt_reqd_fwd_wksp" maxlength="30"
										class="form-control" autocomplete="off" required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label">Work Shop </label>
								</div>
								<div class="col-12 col-md-6">
									<select id="maintainAgency" name="maintainAgency"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMaintainceAgencyList}">
											<option value="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Defect Objects</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" name="defects_obs" id="defects_obs"
										class="form-control" />
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="card-body card-block cue_text display_none" id="profile">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Peripheral Assets Type</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="assets_type_per" id="assets_type_per"
										class="form-control">
										<option value="0">--select--</option>
										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Work Order Number</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" name="wo_no_per" id="wo_no_per"
										class="form-control" />
								</div>

							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input"> W/O Dt </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="wo_dt_per" name="wo_dt_per"
										maxlength="30" class="form-control" autocomplete="off"
										required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Dt fwd to Wksp </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="dt_eqpt_reqd_fwd_wksp_per"
										name="dt_eqpt_reqd_fwd_wksp_per" maxlength="30"
										class="form-control" autocomplete="off" required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label">Work Shop </label>
								</div>
								<div class="col-12 col-md-6">
									<select id="maintainAgency1" name="maintainAgency1"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getMaintainceAgencyList}">
											<option value="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><strong>
									</strong>Defect Objects</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" name="defects_obs_per" id="defects_obs_per"
										class="form-control" />
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-footer" align="center">
				<input type="reset" id="clearbtn" class="btn btn-success btn-sm"
					value="Clear">
				<button type="button" class="btn btn-primary btn-sm" value="Search"
					id="savebtn" name="savebtn">Search</button>
				<button type="button" class="btn btn-primary btn-sm display_none"
					value="Search" id="updatebtn" name="updatebtn">Search</button>
			</div>

		</div>
	</div>
	<div class="modal" id="myHistoryModal">
		<div class="modal-content">
			<span class="popupClose" data-dismiss="modal">&times;</span>
			<div class="modal-body">
			 <h3 class="modal-title" align="center">Computing Work Order History</h3>
				<table
					class="table no-margin table-striped  table-hover  table-bordered table-primary"
					id="PopUp_Tb_Computing_History">
					<thead>
						<tr>

							<th>Work Order No</th>
							<th>Defects Observation</th>


						</tr>
					</thead>
					<tbody id="computing_PopTbbody">
						<tr id="tr_id_Computingpopup">
						</tr>
					</tbody>
				</table>
			</div>

		</div>
	</div>
<div class="modal" id="myHistoryModal_per">
    <div class="modal-content">
        <span class="popupClose" data-dismiss="modal">&times;</span>
        <div class="modal-body">
            <h3 class="modal-title" align="center">Peripheral Work Order History</h3>
            <table class="table no-margin table-striped table-hover table-bordered table-primary" id="PopUp_Tb_Computing_History_per">
                <thead>
                    <tr align="center">
                        <th>Work Order No</th>
                        <th>Defects Observation</th>
                    </tr>
                </thead>
                <tbody id="peripheral_PopTbbody">
                    <tr id="tr_id_Peripheralpopup">
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</form:form>
<div align="center" id="COMPUTING">
	<div class="col-md-12 ">
		<input type="hidden" id="p_id" name="p_id" value="0"> <input
			type="hidden" id="CheckVal" name="CheckVal">
		<table id="WO_Computing_Asset_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Work Order No</th>
					<th>Workshop Name</th>
					<th>Work Order Date</th>
					<th>Date Of Equipement</th>
					<th>Computing Assets Type</th>
					<th>Defect Observed</th>
					<th>No Of Work Order</th>
					<th>ACTION</th>
				</tr>
			</thead>

		</table>
	</div>
</div>
<div align="center" class="display_none" id="PERIPHERAL">
	<div class="col-md-12 ">
		<input type="hidden" id="p_id_per" name="p_id_per" value="0">
		<input type="hidden" id="CheckVal_per" name="CheckVal_per">
		<table id="WO_Peripheral_Asset_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Work Order No</th>
					<th>Workshop Name</th>
					<th>Work Order Date</th>
					<th>Date Of Equipement</th>
					<th>Computing Assets Type</th>
					<th>Defect Objects</th>
					<th>No Of Work Order</th>
					<th>ACTION</th>
				</tr>
			</thead>
		</table>
	</div>
</div>

<c:url value="Download_Peripheral_Asset_WO" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="Hid_id">
	<input type="hidden" name="Hid_id" id="Hid_id" value="0">
	<input type="hidden" name="typeReport" id="typeReport" value="0" />
	<input type="hidden" name="type" id="type" value="0" />
</form:form>




<script nonce="${cspNonce}">
	function Downloaddata(id) {
		debugger
		$("#Hid_id").val(id);
		document.getElementById('type').value = 'COMPUTING';
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('downloadForm').submit();
	}

	function Downloaddata1(id) {
		debugger
		$("#Hid_id").val(id);
		document.getElementById('type').value = 'PERIPHERAL';
		document.getElementById('typeReport').value = 'pdfL';
		document.getElementById('downloadForm').submit();
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
		check_count = l;

	}

	function setselectall_per() {

		if ($("#nSelAll_per").prop("checked")) {
			$(".nrCheckBox_per").prop('checked', true);
		} else {
			$(".nrCheckBox_per").prop('checked', false);
		}

		var l = $('[name="cbox_per"]:checked').length;
		$("#tregn1").val(l);
		document.getElementById('tregn1').innerHTML = l;
		check_count = l;

	}

	document.addEventListener("DOMContentLoaded", function() {
		var saveButton = document.getElementById("updatebtn");
		saveButton.addEventListener("click", function() {
			table1.ajax.reload();
		});

		var updatebtn = document.getElementById("savebtn");
		updatebtn.addEventListener("click", function() {
			table3.ajax.reload();
		});

		document.addEventListener('click', function(event) {
			if (event.target.classList.contains('PERIPHERAL')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to Download?')) {
					Downloaddata1(encryptedPk);
				}
			}
			if (event.target.classList.contains('COMPUTING')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to Download?')) {
					Downloaddata(encryptedPk);
				}
			}
			if (event.target.classList.contains('COMPUTING_history')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to see history?')) {
					historyData(encryptedPk);
				}
			}

			if (event.target.classList.contains('PERIPHERAL_history')) {
				var encryptedPk = event.target
						.getAttribute('data-encrypted-pk');
				if (confirm('Are you sure you want to see history?')) {
					historyData_per(encryptedPk);
				}
			}

		});
	});

	$(document).ready(function() {

		$("#nSelAll").change(setselectall);
		$("#nSelAll_per").change(setselectall_per);

		mockjax11('WO_Peripheral_Asset_reporttbl');
		table1 = dataTable11('WO_Peripheral_Asset_reporttbl');

		mockjax11('WO_Computing_Asset_reporttbl');
		table3 = dataTable11('WO_Computing_Asset_reporttbl');

	});

	function data(tableName) {

		if (tableName == "WO_Computing_Asset_reporttbl") {
			jsondata = [];
			var table = $('#' + tableName).DataTable();
			var info = table.page.info();

			var currentPage = info.page;
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];

			var assets_type = $("#assets_type").val();
			var wo_no = $("#wo_no").val();
			var wo_dt = $("#wo_dt").val();
			var dt_eqpt_reqd_fwd_wksp = $("#dt_eqpt_reqd_fwd_wksp").val();
			var wksp_unit_name = $("#maintainAgency").val();
			var defects_obs = $("#defects_obs").val();

			$.post("Pop_UP_Computing_Asset_History_ReportDataList?" + key + "="
					+ value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				assets_type : assets_type,
				wo_no : wo_no,
				wo_dt : wo_dt,
				dt_eqpt_reqd_fwd_wksp : dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name : wksp_unit_name,
				defects_obs : defects_obs
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					jsondata.push([ j[i].wo_no, j[i].maintenance, j[i].wo_dt,
							j[i].dt_eqpt_reqd_fwd_wksp, j[i].assets_name,
							j[i].defects_obs, j[i].history, j[i].action ]);
				}
			});
			$.post("Pop_UP_Computing_Asset_History_List_TotalCount?" + key
					+ "=" + value, {
				Search : Search,
				assets_type : assets_type,
				wo_no : wo_no,
				wo_dt : wo_dt,
				dt_eqpt_reqd_fwd_wksp : dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name : wksp_unit_name,
				defects_obs : defects_obs
			}, function(j) {
				FilteredRecords = j;
			});
		}

		if (tableName == "WO_Peripheral_Asset_reporttbl") {
			jsondata = [];
			var table = $('#' + tableName).DataTable();
			var info = table.page.info();

			var currentPage = info.page;
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];

			var assets_type = $("#assets_type_per").val();
			var wo_no = $("#wo_no_per").val();
			var wo_dt = $("#wo_dt_per").val();
			var dt_eqpt_reqd_fwd_wksp = $("#dt_eqpt_reqd_fwd_wksp_per").val();
			var wksp_unit_name = $("#maintainAgency1").val();
			var defects_obs = $("#defects_obs_per").val();

			$.post("Pop_UP_Peripheral_Asset_History_ReportDataList?" + key
					+ "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				assets_type : assets_type,
				wo_no : wo_no,
				wo_dt : wo_dt,
				dt_eqpt_reqd_fwd_wksp : dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name : wksp_unit_name,
				defects_obs : defects_obs
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					jsondata.push([ j[i].wo_no, j[i].maintenance, j[i].wo_dt,
							j[i].dt_eqpt_reqd_fwd_wksp, j[i].assets_name,
							j[i].defects_obs, j[i].history, j[i].action ]);
				}
			});
			$.post("Pop_UP_Peripheral_Asset_History_List_TotalCount?" + key
					+ "=" + value, {
				Search : Search,
				assets_type : assets_type,
				wo_no : wo_no,
				wo_dt : wo_dt,
				dt_eqpt_reqd_fwd_wksp : dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name : wksp_unit_name,
				defects_obs : defects_obs
			}, function(j) {
				FilteredRecords = j;
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

	function findselected() {
		debugger
		var nrSel = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}

	function findselected_per() {

		var nrSel = $('.nrCheckBox_per:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();

		var b = nrSel.join(':');
		$("#CheckVal_per").val(b);
		$('#tregn1').text(nrSel.length);
	}

	function SaveWorkOrder() {

		findselected();

		var a1 = $("#CheckVal").val();

		if (a1 == "") {
			alert("Please Select atleast 1 data to Generate Work Order");
		} else {

			var maintainAgency = $("#maintainAgency").val();
			// 			var maintainAgency = $("#maintainAgency option:selected").text();
			// 			var unit_sus_no = $("#unit_sus_no").val();
			if (maintainAgency == "" || maintainAgency == null
					|| maintainAgency == "0") {
				alert("Please Select WorkShop Agency.");
				return false;
			}

			var wo_no = $("#wo_no").val();
			var wo_dt = $("#wo_dt").val();

			if (wo_dt == "" || wo_dt == null) {
				alert("Please Enter W/O Dt.");
				return false;
			}
			var dt_eqpt_reqd_fwd_wksp = $("#dt_eqpt_reqd_fwd_wksp").val();

			$.post("GenerateWorkOrderAction_P?" + key + "=" + value, {
				maintainAgency : maintainAgency,
				wo_no : wo_no,
				wo_dt : wo_dt,
				dt_eqpt_reqd_fwd_wksp : dt_eqpt_reqd_fwd_wksp,

			}).done(function(j) {
				// 			alert(j);
				if (j > 0) {
					// 				alert("Data Saved Sucessfull");
					$("#p_id").val(j);
				}
				// 			Search();
				// 			isvalidData();
			});

			var p_id = a1.split(":");

			var p_id1 = $("#p_id").val();

			for (var i = 0; i < p_id.length; i++) {

				var Defects = $("#Defects" + p_id[i]).val();
				var remarks = $("#remarks" + p_id[i]).val();
				var proc_date = $("#proc_date" + p_id[i]).val();

				$.post("GenerateWorkOrderAction_Ch?" + key + "=" + value, {
					Defects : Defects,
					remarks : remarks,
					proc_date : proc_date,
					a1 : p_id[i],
					p_id1 : p_id1,
					wo_no : wo_no
				}).done(function(j) {
					// 				alert(j);
					if (j > 0) {

					}

					// 			isvalidData();
				});
			}
			alert("Data Saved Sucessfully");
			table3.ajax.reload();
			check_count = 0;
		}
	}

	function SaveWorkOrder1() {

		findselected_per();

		var a1 = $("#CheckVal_per").val();

		if (a1 == "") {
			alert("Please Select atleast 1 data to Generate Work Order");
		} else {

			var maintainAgency1 = $("#maintainAgency1").val();
			if (maintainAgency1 == "" || maintainAgency1 == null
					|| maintainAgency1 == "0") {
				alert("Please Select WorkShop Agency.");
				return false;
			}
			var wo_no = $("#wo_no").val();

			var wo_dt_per = $("#wo_dt_per").val();
			if (wo_dt_per == "" || wo_dt_per == null) {
				alert("Please Enter W/O Dt.");
				return false;
			}
			var dt_eqpt_reqd_fwd_wksp_per = $("#dt_eqpt_reqd_fwd_wksp_per")
					.val();

			$.post("GenerateWorkOrderActionPeripheral_P?" + key + "=" + value,
					{
						maintainAgency1 : maintainAgency1,
						wo_no : wo_no,
						wo_dt_per : wo_dt_per,
						dt_eqpt_reqd_fwd_wksp_per : dt_eqpt_reqd_fwd_wksp_per

					}).done(function(j) {
				//	 			alert(j);
				if (j > 0) {
					//	 				alert("Data Saved Sucessfull");
					$("#p_id_per").val(j);
				}
				//	 			Search();
				//	 			isvalidData();
			});

			var p_id = a1.split(":");

			var p_id1 = $("#p_id_per").val();

			for (var i = 0; i < p_id.length; i++) {

				var Defects = $("#Defects" + p_id[i]).val();
				var remarks = $("#remarks" + p_id[i]).val();
				var proc_date = $("#proc_date" + p_id[i]).val();

				$.post(
						"GenerateWorkOrderActionPeripheral_Ch?" + key + "="
								+ value, {
							Defects : Defects,
							remarks : remarks,
							proc_date : proc_date,
							a1 : p_id[i],
							p_id1 : p_id1,
							wo_no : wo_no
						}).done(function(j) {

					if (j > 0) {

					}

				});
			}
			alert("Data Saved Sucessfully");
			table1.ajax.reload();
		}
	}

	$("#profile-tab").click(function() {

		// Show profile tab and hide home tab
		$("#profile").show().removeClass('display_none').addClass('active');
		$("#home").hide().addClass('display_none').removeClass('active');

		// Update tab states
		$("#profile-tab").addClass('active');
		$("#home-tab").removeClass('active');

		// Manage buttons visibility
		$("#savebtn").hide();
		$("#updatebtn").removeClass('display_none');
		$("#COMPUTING").hide();
		$("#PERIPHERAL").show();

		// Reset form and reload table
		$("#user_mst")[0].reset();
		table1.ajax.reload();
	});

	$("#home-tab").click(function() {

		// Show home tab and hide profile tab
		$("#home").show().removeClass('display_none').addClass('active');
		$("#profile").hide().addClass('display_none').removeClass('active');

		// Update tab states
		$("#home-tab").addClass('active');
		$("#profile-tab").removeClass('active');

		$("#PERIPHERAL").hide();
		$("#COMPUTING").show();
		// Manage buttons visibility
		$("#savebtn").show();
		$("#savebtn").removeClass('active');
		$("#updatebtn").addClass('display_none');
		$("#icon_search").addClass('display_none');

		// Reset form and reload table
		$("#user_mst")[0].reset();
		table3.ajax.reload();
	});

	function fn_makeName() {

		var assets_name = $("select#asset_type").val();
		$
				.post("getMakeNameList?" + key + "=" + value, {
					assets_name : assets_name
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#make_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function fn_modelName() {
		var make_name = $("select#make_name").val();
		$
				.post("getModelNameList?" + key + "=" + value, {
					make_name : make_name
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#model_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function historyData(id) {
		$.post(
				"pop_up_detail_Computing_Asset_History_RecordDataList?" + key
						+ "=" + value,
				{
					id : id
				},
				function(j) {

					if (j.length > 0) {
						$('#computing_PopTbbody').empty();
						for (var i = 0; i < j.length; i++) {
							$("#computing_PopTbbody").append(
									' <tr id="tr_id_Computingpopup'+i+'">'
											+ '<td align="center">'
											+ j[i].wo_no + '</td>'
											+ '<td align="center">'
											+ j[i].defects_obs + '</td>'
											+ '</tr>');

						}
					} else {

					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}

	function historyData_per(id) {

		$.post(
				"pop_up_detail_Peripheral_Asset_History_RecordDataList?" + key
						+ "=" + value,
				{
					id : id
				},
				function(j) {

					if (j.length > 0) {
						$('#peripheral_PopTbbody').empty();
						for (var i = 0; i < j.length; i++) {
							$("#peripheral_PopTbbody").append(
									' <tr id="tr_id_Peripheralpopup'+i+'">'
											+ '<td align="center">'
											+ j[i].wo_no + '</td>'
											+ '<td align="center">'
											+ j[i].defects_obs + '</td>'
											+ '</tr>');

						}
					} else {

					}
				}).fail(function(xhr, textStatus, errorThrown) {
			alert("fail to fetch")
		});
	}
</script>





