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

<Style nonce="${cspNonce}">
.mb {
	margin-bottom: 0;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}

</Style>

<form:form name="user_mst" id="user_mst" action="user_mstAction"
	method='POST' modelAttribute="user_mstCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header" align="center">
				<h5>CREATE WORKORDER</h5>

				<div class="col-md-12" align="center">
					<div class="col-md-4"></div>
					<div class="col-md-4 ">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<button class="nav-link active font_weight_bold"  id="home-tab"
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
									<label class="form-control-label">Work Shop <strong
										class="color_red">* </strong></label>
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
									<label for="text-input"> W/O Dt <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="wo_dt" name="wo_dt" maxlength="30"
										class="form-control" autocomplete="off" required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
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
				</div>
				<div class="card-body card-block cue_text display_none" id="profile">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label">Work Shop <strong
										class="color_red">* </strong></label>
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
									<label for="text-input"> W/O Dt <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="date" id="wo_dt_per" name="wo_dt_per"
										maxlength="30" class="form-control" autocomplete="off"
										required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
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
				</div>
			</div>

			<div class="card-footer" align="center">
				<input type="reset" id="clearbtn" class="btn btn-success btn-sm"
					value="Clear">
				<button type="button" class="btn btn-success btn-sm"
					value="W/O Generate" id="savebtn" name="savebtn">W/O
					Generate</button>
				<button type="button" class="btn btn-success btn-sm display_none"
					value="W/O Generate" id="updatebtn" name="updatebtn">W/O
					Generate</button>
			</div>

		</div>
	</div>
</form:form>
<div align="center" id="COMPUTING">
	<div class="col-md-12 ">
		<input type="hidden" id="p_id" name="p_id" value="0"> <input
			type="hidden" id="CheckVal" name="CheckVal"> <b><input
			type=checkbox id='nSelAll' name='tregn'>Select all [<span
			id="tregn">0</span><span id='nTotRow1'>/</span><span id="tregnn">
				</span>]</b>
		<table id="Computing_Asset_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Select To .</th>
					<th>Computing Assets Name</th>
					<th>Make Name</th>
					<th>Model Name</th>
<!-- 					<th>Model Number</th> -->
					<th>Machine Number</th>
					<th>MAC Address</th>
					<th>IP Address</th>
					<th>Defects Observed</th>
					<th>Remarks</th>
				</tr>
			</thead>

		</table>
	</div>
</div>
<div align="center" class="display_none" id="PERIPHERAL">
	<div class="col-md-12 ">
	<input type="hidden" id="p_id_per" name="p_id_per" value="0"> <input
			type="hidden" id="CheckVal_per" name="CheckVal_per">
		<b><input type=checkbox id='nSelAll_per' name='tregn1'
			>Select all [<span id="tregn1">0</span><span
			id='nTotRow11'>/</span><span id="tregnn1"></span>]</b>
		<table id="Peripheral_Asset_reporttbl"
			class="display table no-margin table-striped  table-hover  table-bordered report_print">
			<thead>
				<tr>
					<th>Select To Generate</th>
					<th>Peripheral Type</th>
					<th>Make Name</th>
					<th>Model Name</th>
<!-- 					<th>Model Number</th> -->
					<th>Machine Number</th>
					<th>Type of HW</th>
					<th>Year of Proc</th>
					<th>Defects Obs</th>
					<th>Remarks</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


<script nonce="${cspNonce}">
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
		var saveButton = document.getElementById("savebtn");
		saveButton.addEventListener("click", function() {
			return SaveWorkOrder();
		});

		var updatebtn = document.getElementById("updatebtn");
		updatebtn.addEventListener("click", function() {
			return SaveWorkOrder1();
		});

	});

	$(document).ready(function() {
		
		$("#nSelAll").change(setselectall);
		$("#nSelAll_per").change(setselectall_per);

		mockjax11('Peripheral_Asset_reporttbl');
		table1 = dataTable11('Peripheral_Asset_reporttbl');

		mockjax11('Computing_Asset_reporttbl');
		table3 = dataTable11('Computing_Asset_reporttbl');

	});

	function data(tableName) {

		if (tableName == "Computing_Asset_reporttbl") {
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

			var asset_type = $("#asset_type").val();
			var make_name = $("#make_name").val();
			var model_name = $("#model_name").val();
			var status = $("#status").val();

			$.post("Computing_Asset_List_ReportDataList?" + key + "=" + value,
					{
						startPage : startPage,
						pageLength : pageLength,
						Search : Search,
						orderColunm : orderColunm,
						orderType : orderType,
						asset_type : asset_type,
						make_name : make_name,
						model_name : model_name,
						status : status
					}, function(j) {

						for (var i = 0; i < j.length; i++) {
							jsondata
									.push([ j[i].chekboxaction,
											j[i].assets_name, j[i].make_name,
											j[i].model_name, 
											j[i].machine_number,
											j[i].mac_address, j[i].ip_address,
											j[i].Defects, j[i].remarks ]);
						}
						$("#nSelAll").prop('checked', false);
						$('#tregn').text("0");
						$('#tregnn').text(j.length);
						//				alert($('#tregnn').text(j.length));

					});
			$.post("Computing_Asset_List_TotalCount?" + key + "=" + value, {
				Search : Search,
				asset_type : asset_type,
				make_name : make_name,
				model_name : model_name,
				status : status
			}, function(j) {
				FilteredRecords = j;
			});

		}

		if (tableName == "Peripheral_Asset_reporttbl") {
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

			var asset_type = $("#asset_type").val();
			var make_name = $("#make_name").val();
			var model_name = $("#model_name").val();
			var status = $("#status").val();

			$.post("Peripheral_Asset_List_ReportDataList?" + key + "=" + value,
					{
						startPage : startPage,
						pageLength : pageLength,
						Search : Search,
						orderColunm : orderColunm,
						orderType : orderType,
						asset_type : asset_type,
						make_name : make_name,
						model_name : model_name,
						status : status
					}, function(j) {

						for (var i = 0; i < j.length; i++) {
							jsondata.push([ j[i].chekboxaction,
									j[i].assets_name, j[i].make_name,
									j[i].model_name, 
									j[i].machine_no, j[i].type_of_hw,
									j[i].year_of_proc, j[i].Defects,
									j[i].remarks ]);

						}
						$("#nSelAll1").prop('checked', false);
						$('#tregnn1').text("0");
						$('#tregnn1').text(j.length);
					});
			$.post("Peripheral_Asset_List_TotalCount?" + key + "=" + value, {
				Search : Search,
				asset_type : asset_type,
				make_name : make_name,
				model_name : model_name,
				status : status
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
		$.post("getMakeNameList?" + key + "=" + value, {
			assets_name: assets_name
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#make_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 function fn_modelName() {
		var make_name = $("select#make_name").val();
		$.post("getModelNameList?" + key + "=" + value, {
			make_name: make_name
		}).done(function(j) {
		
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#model_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 

	//**********************************************
</script>