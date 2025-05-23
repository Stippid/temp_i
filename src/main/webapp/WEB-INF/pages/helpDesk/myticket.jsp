<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/printWatermark/common.js"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">

<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<form:form name="myticket" id="myticket" action="myticketAction"
	method='POST' modelAttribute="myticketCMD">
<div id="upperdiv">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Manage Ticket</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										class="color_red">*</strong> Search by</label>
								</div>
								<div class="col-md-8">
									<label for="inline-radio1" class="form-check-label"> <input
										type="hidden" id="label1" name="label1" class="form-control"
										value="${label}">
										<input
										type="hidden" id="label2" name="label2" class="form-control"
										value="${label}">
										<input
										type="hidden" id="label1" name="label1" class="form-control"
										value="${label}"> <input type="radio"
										id="inline-radio1" value="TicketId" name="type"
										class="form-check-input">Ticket Id
									</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
										class="form-check-label"> <input type="radio"
										id="inline-radio2" value="Status" name="type"
										class="form-check-input">Status
									</label>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										class="color_red">*</strong> Ticket Id</label>
								</div>
								<div class="col-md-8">
									<input id="ticket" name="ticket" class="form-control"
										autocomplete="off"> <input type="hidden"
										id="sus_no_id" name="sus_no_id" class="form-control">
									<input type="hidden" id="unit_name" name="unit_name"
										value='${unit_name}' class="form-control font_familiy_awsome"
										readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										class="color_red">*</strong> Status </label>
								</div>
								<div class="col-md-8">
									<select name="ticket_status" id="ticket_status"
										class="form-control">
										<option value="">--Select--</option>
										<option value="0">New</option>
										<option value="1">In Progress</option>
										<option value="2">Completed</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Date
										(from)</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="from_date" name="from_date"
										class="form-control" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Date
										(to)</label>
								</div>
								<div class="col-md-8">
									<input type="date" id="to_date" name="to_date"
										class="form-control" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label"><strong
										class="color_red">*</strong> Ticket Type </label>
								</div>
								<div class="col-md-8">
									<select id="help_topic" name="help_topic" class="form-control">
										<option value="0">--Select--</option>
										<option value="1">Hardware Issue</option>
										<option value="2">Software Issue</option>
										<option value="3">Feedback</option>
										<option value="4">Others</option>
										<!-- <option value="5">Feature Request</option> -->
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Module</label>
								</div>
								<div class="col-md-8">
									<select id="module" name="module" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getModuleNameList}"
											varStatus="num">
											<option class="text_transform_upper" value="${item.id}">${item.module_name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='card-footer' align='center'>
					<input type="reset" class="btn btn-success btn-sm" value="Clear">
					<i class="fa fa-search"></i><input type="button" id="search_btn"
						class="btn btn-primary btn-sm" value="Search">
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="container display_none" id="divPrint">
		<div class="card">
			<div class="card-body">
				<div id="divShow" class="display_block"></div>
				<div class="watermarked display_block" data-watermark=""
					id="divwatermark">
					<span id="ip"></span>
					<table id="TicketReport"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead>
							<tr>
								<th class="width5">Ser No</th>
								<th class="width10">Ticket Id</th>
								<!-- 						<th class="width10">Module</th> -->
								<th class="width10">Status</th>
								<th class="width10">Date</th>
								<th class="width20">Ticket Type</th>
								<th class="width20">Issue Summary</th>
								<th class="lastCol  text_align_center width20">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num">
								<tr>
									<td class="width5">${num.index+1}</td>
									<td class="width10">${item.ticket}</td>
									<%-- 							<th class="width10">${item.module_name}</th> --%>
									<td class="width10">${item.ticket_status}</td>
									<td class="width10">${item.dt}</td>
									<td class="width20">${item.help_topic}</td>
									<td class="width20">${item.issue_summary}</td>
									<td id="thAction1" class='lastCol width20 text_align_center'>${item.id}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>
<c:url value="getAllCodeListJdbc1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="ticket1">
	<input type="hidden" name="ticket1" id="ticket1" value="0" />
	<input type="hidden" name="ticket_status1" id="ticket_status1"
		value="0" />
	<input type="hidden" name="from_date1" id="from_date1" value="0" />
	<input type="hidden" name="to_date1" id="to_date1" value="0" />
	<input type="hidden" name="help_topic1" id="help_topic1" value="" />
	<input type="hidden" name="type1" id="type1" value="" />
	<input type="hidden" name="module1" id="module1" value="" />
	<input type="hidden" name="label" id="label" value="${label}" />
</form:form>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('inline-radio1').onchange = function() {
			return clearTYPE();
		};
		document.getElementById('inline-radio2').onchange = function() {
			return clearTYPE1();
		};
		document.getElementById('to_date').onchange = function() {
			return checkDate();
		};
		document.getElementById('search_btn').onclick = function() {
			return Search();
		};
		document.getElementById('upperdiv').onFocus = function() {
			return parent_disable1();
		};
		document.getElementById('upperdiv').onclick = function() {
			return parent_disable1();
		};
		document.body.addEventListener('click', function(event) {
			if (event.target.matches('.fa-edit')) {
				var dataId = event.target.getAttribute('data-id');
				editData(dataId); // Call your editData function here
			}
			if (event.target.matches('.fa-eye')) {
				var dataId = event.target.getAttribute('data-id');
				viewData(dataId); // Call your editData function here
			}
			if (event.target.matches('.action_approve')) {
				var dataId = event.target.getAttribute('data-id');
				approveData(dataId); // Call your editData function here
			}
			if (event.target.matches('.approve')) {
				// if (confirm('Are you sure you want to Accept This Ticket?')) {
				var dataId = event.target.getAttribute('data-id');
				acceptData(dataId); // Call your editData function here
				// }
			}
			if (event.target.matches('.accept')) {
				 if (confirm('Are you sure you want to Accept This Ticket?')) {
				var dataId = event.target.getAttribute('data-id');
				acceptData(dataId); // Call your editData function here
				 }
			}
			
		});

	})

	function parent_disable1() {
		if (newWin && !newWin.closed)
			newWin.focus();
	}
	function getRefreshReport(page, msg) {
		if (msg.includes("Updated+Successfully")) {
			if (page == "myticket1") {
				Search();
			}
		}
	}
	function clearTYPE() {
		
		document.getElementById("ticket").disabled = false;
		document.getElementById("ticket_status").disabled = true;
		$("#ticket_status").val("");
		$("#divPrint").hide();
	}
	function clearTYPE1() {
	
		document.getElementById("ticket").disabled = true;
		document.getElementById("ticket_status").disabled = false;
		$("#ticket").val("");
		$("#divPrint").hide();
	}
	function Search() {

		watermarkreport();
		var r = $('input:radio[name=type]:checked').val();
// 		if (r == undefined) {
// 			alert("Please Select type");
// 			$("#type").focus();
// 			return false;
// 		}
// 		if (r == 'TicketId') {
// 			if ($("#ticket").val() == "") {
// 				alert("Please Enter Ticket Id.");
// 				$("#ticket").focus();
// 				return false;
// 			}
// 		}
// 		if (r == 'Status') {
// 			if ($("#ticket_status").val() == "") {
// 				alert("Please Select Ticket Status");
// 				$("#ticket_status").focus();
// 				return false;
// 			}
// 		}
		$("#ticket1").val($("#ticket").val());
		$("#ticket_status1").val($("#ticket_status").val());
		$("#to_date1").val($("#to_date").val());
		$("#from_date1").val($("#from_date").val());
		$("#help_topic1").val($("#help_topic").val());
		$("#type1").val(r);
		$("#module1").val($("#module").val());
		$("#label").val($("#label1").val());
		document.getElementById('searchForm').submit();

	}
	$(document)
			.ready(
					function() {
						
						watermarkreport();
						document.getElementById("ticket_status").disabled = true;
						document.getElementById("ticket").disabled = true;
						$("#sus_no_id").val('${list[0][0]}');
						$("#unit_name").val('${list[0][1]}');
						
						if ('${list.size()}' == 0) {
							$("table#TicketReport")
									.append(
											"<tr><td colspan='6' style='align:center;'>Data Not Available</td></tr>");
						}
						if ('${ticket1}' != "") {
							document.getElementById("ticket_status").disabled = true;
							document.getElementById("ticket").disabled = false;
						}
						if ('${ticket_status1}' != "") {
							document.getElementById("ticket_status").disabled = false;
							document.getElementById("ticket").disabled = true;
						}
						if ('${ticket1}' != "" || '${ticket_status1}' != "") {
							$("#label1").val('${label}');
							$("#ticket").val('${ticket1}');
							$("#ticket_status").val('${ticket_status1}');
							$("#from_date").val('${from_date1}');
							$("#to_date").val('${to_date1}');
							$("#help_topic").val('${help_topic1}');
							$("input[name=type][value=" + '${type1}' + "]")
									.prop('checked', true);
							$("#module").val('${module1}');
							
						}
						$("#divPrint").show();
						try {
							if (window.location.href.includes("msg=")) {
								var url = window.location.href.split("?msg=")[0];
								var m = window.location.href.split("?msg=")[1];
								window.location = url;

								if (m.includes("Updated+Successfully")) {
									window.opener.getRefreshReport('myticket1',
											m);
									window.close('', '_parent', '');
								}
							}
							if (document.getElementById("msg").value != "") {
								$("div#divwatermark").val('').removeClass(
										'watermarked');
								$("div#divSerachInput").hide();
								$("div#divPrint").hide();
							}
						} catch (e) {
						}
					});

	function checkDate() {
		var startDate = document.getElementById("from_date").value;
		var endDate = document.getElementById("to_date").value;

		if ((Date.parse(endDate) <= Date.parse(startDate))) {
			alert("Effective To date should be greater than Effective From date");
			document.getElementById("to_date").value = "";
		}
	}
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#TicketReport tbody tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	function printDiv() {
		var printLbl = [ "Status:", "Ticket Id:", "From Date:", "To Date:" ];
		var status = document.getElementById('ticket_status').value;
		var help_tp = document.getElementById('help_topic').value;
		if (status == "0") {
			status = "New";
		} else if (status == "1") {
			status = "In Progress";
		} else if (status == "2") {
			status = "Completed";
		} else if (status == "3") {
			status = "Feedback";
		} else if (status == "4") {
			status = "Feature Request";
		}
		if (help_tp == "1") {
			help_tp = "Feedback";
		} else if (help_tp == "2") {
			help_tp = "General Inquiry";
		} else if (help_tp == "3") {
			help_tp = "Report a problem";
		} else if (help_tp == "4") {
			help_tp = "Report a problem/Access Issues";
		} else if (help_tp == "5") {
			help_tp = "Feature Request";
		}
		var printVal = [ status, document.getElementById('ticket').value,
				help_tp ];
		printDivOptimize('divPrint', 'My Ticket', printLbl, printVal, "");
	}
</script>
<script nonce="${cspNonce}">
	var newWin;
	function editData(id) {

		document.getElementById('updateid').value = id;
		
		document.getElementById('updateForm').submit();
	}
	function viewData(id) {

		document.getElementById('viewid').value = id;
		
		document.getElementById('viewForm').submit();
	}
	function approveData(id) {
		debugger;
		document.getElementById('approveid').value = id;
		document.getElementById('approveForm').submit();
	}
	function approveData(id) {
		debugger;
		document.getElementById('approveid').value = id;
		document.getElementById('approveForm').submit();
	}
	function acceptData(id) {
		
		
		document.getElementById('acceptid').value = id;
		
		document.getElementById('acceptForm').submit();
	}
</script>

<c:url value="editticketdetails" var="updateUrl"/>
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
	
	<c:url value="finalviewdetails" var="updateUrl"/>
<form:form action="${updateUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="viewid">
	<input type="hidden" name="viewid" id="viewid" value="0"/>
	</form:form>
	
	
	
	
	<c:url value="approvedetails" var="updateUrl"/>
<form:form action="${updateUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="approveid">
	<input type="hidden" name="approveid" id="approveid" value="0"/>
	</form:form>
		<c:url value="acceptdetails" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="acceptForm"
	name="acceptForm" modelAttribute="approveid">
	<input type="hidden" name="acceptid" id="acceptid" value="0"/>
	</form:form>

