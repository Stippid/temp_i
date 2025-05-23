<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
HttpSession sess = request.getSession(false);
if (sess.getAttribute("userId") == null) {
	response.sendRedirect("~/login");
	return;
}
%>
<script type="text/javascript" src="js/printWatermark/common.js"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<script src="js/printWatermark/printAllPages.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}">
	var username = "${username}";
</script>
<style nonce="${cspNonce}">
.textarea {
	maxlength: "1000"
}
</style>

<form:form name="viewticket" id="viewticket"
	action="viewticketAction?${_csrf.parameterName}=${_csrf.token}"
	method='POST' modelAttribute="viewticketCMD"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>View Ticket</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Ticket
										Id</label>
								</div>
								<div class="col-md-8">
									<input type="hidden" id="username" name="username"
										value="${username}" /> <input type="hidden" id="id" name="id"
										value="${id}" /> <input id="ticket" name="ticket"
										class="form-control" autocomplete="off" value="${ticket}"
										readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Created Date
									</label>
								</div>
								<div class="col-md-8">
									<input id="date1" name="date1" class="form-control"
										 readonly="readonly"></input> </input> <input
										type="hidden" id=assigned_dt name="assigned_dt"
										class="form-control" value="${assigned_dt}"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Section
										No </label>
								</div>
								<div class="col-md-8">
									<select id="section" name="section" class="form-control"
										readonly="readonly">

										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}" name="${item.id}">${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Ticket
										type </label>
								</div>
								<div class="col-md-8">
									<!-- 									<input id="help_topic" name ="help_topic" class="form-control"  -->
									<%-- 										value="${help_topic}"> --%>
									<select id="help_topic" name="help_topic" class="form-control"
										readonly="readonly">
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

									<label for="text-input" class=" form-control-label">Screenshot
									</label>
								</div>
								<div class="col-md-8">
									<!-- 									<input type="file" id="filedoc" name="filedoc" -->
									<!-- 										class="form-control" > -->
									<div class="col-md-2 mt_10" id="d1">

										<!-- 									<input -->
										<!-- 										type="hidden" id=hidFileName name="hidFileName" -->
										<!-- 										class="form-control"  -->
										<%-- 										value="${screen_shot}"> --%>
										<i id="download_id" class="fa fa-download display_none"
											readonly="readonly"> <span id="uploadedFileName"
											name="uploadedFileName" class="uploaded-file-name f_10">${screen_shot}</span>
											<input type="button"
											class="btn btn-success btn-sm display_none" id="downloadbtn"
											value="Download File" />
										</i>



									</div>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Issue
										summary </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="issue_summary" name="issue_summary"
										placeholder="Maximum 100 characters" value="${issue_summary}"
										class="form-control" autocomplete="off" maxlength="100"
										readonly="readonly"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Description
										(Max 1000 words)</label>
								</div>
								<div class="col-md-8">
									<textarea id="description" name="description"
										class="form-control autocomplete text_transform_upper"
										autocomplete="off" readonly="readonly">${description}</textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Status</label>
								</div>
								<div class="col-md-8">
										<select id="status" name="status" class="form-control"
										readonly="readonly">
										<option value="0">--Select--</option>
										<option value="1">Pending</option>
										<option value="2">Completed</option>
										
										
									</select>
								</div>
							</div>
						</div>

					</div>
						<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Completed Date</label>
								</div>
								<div class="col-md-8">
									<input
										 id=completed_dt1 name="completed_dt1"
										class="form-control"  readonly="readonly">
								</div>
							</div>
						</div>
				

					</div>

				</div>
				<div class='card-footer' align='center'>
					<a href="myticket" class="btn btn-success btn-sm">Back</a>
				</div>
			</div>
		</div>
	</div>

	<div id="printableArea" class="display_none">
		<div class="watermarked display_block" id="divShow"></div>

	</div>
</form:form>
<c:url value="getmodule_help" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post"
	id="getDownloadImageForm" name="getDownloadImageForm"
	modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="pageUrl" id="pageUrl" value="" />
	<input type="hidden" name="contraint" id="contraint" value="" />
</form:form>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
	
		document.getElementById('download_id').onclick = function() {
			return download_file();
		};
	})

	function getmodule_help(id) {
		document.getElementById("id1").value = id;
		document.getElementById("contraint").value = "Edit";
		document.getElementById("pageUrl").value = "viewticketTiles";
		document.getElementById("getDownloadImageForm").submit();
	}
	function download_file() {

		var id = $("#id").val();
		var pdfView = "ticketFileDownloadDemo?id=" + id;

		if (confirm("Are you sure you want to download the file?")) {

			fileName = "TEST_DOC";
			fileURL = pdfView;
			if (!window.ActiveXObject) {
				var save = document.createElement('a');
				save.href = fileURL;
				save.target = '_blank';
				var filename = fileURL.substring(fileURL.lastIndexOf('/') + 1);
				save.download = fileName || filename;
				if (navigator.userAgent.toLowerCase().match(
						/(ipad|iphone|safari)/)
						&& navigator.userAgent.search("Chrome") < 0) {
					document.location = save.href;
				} else {
					var evt = new MouseEvent('click', {
						'view' : window,
						'bubbles' : true,
						'cancelable' : false
					});
					save.dispatchEvent(evt);
					(window.URL || window.webkitURL).revokeObjectURL(save.href);
				}
			} else if (!!window.ActiveXObject && document.execCommand) {
				var _window = window.open(fileURL, '_blank');
				_window.document.close();
				_window.document.execCommand('SaveAs', true, fileName
						|| fileURL)
				_window.close();
			}

			location.reload();
		}
	}
</script>
<script nonce="${cspNonce}">
	function isvalidData() {
		var status = document.getElementById('ticket_status').value;
		var description = document.getElementById('description').value;
		//  if($("#agent_name").val() == "0"){
		// 		alert("Please Select Agent Name");
		// 		$("#agent_name").focus();
		// 		return false;
		// 	}
		if (status == "0") {
			alert("Please Select Status");
			$("#ticket_status").focus();
			return false;
		}

		// 		if (description.length() >1000) {
		// 			alert("Description Should Contain Maximum 1000 Characters");
		// 			$("#description").focus();
		// 			return false;
		// 		}
		return true;
	}
	// 	(function($) {
	// 		"use strict";
	// 		$.fn.charCounter = function(options) {
	// 			if (typeof String.prototype.format == "undefined") {
	// 				String.prototype.format = function() {
	// 					var content = this;
	// 					for (var i = 0; i < arguments.length; i++) {
	// 						var replacement = '{' + i + '}';
	// 						content = content.replace(replacement, arguments[i]);
	// 					}
	// 					return content;
	// 				};
	// 			}
	// 			var options = $.extend({
	// 				backgroundColor : "#FFFFFF",
	// 				position : {
	// 					right : 10,
	// 					top : 10
	// 				},
	// 				font : {
	// 					size : 10,
	// 					color : "#a59c8c"
	// 				},
	// 				limit : 1000
	// 			}, options);
	// 			return this.each(function() {
	// 				var el = $(this), wrapper = $("<div/>").addClass(
	// 						'focus-textarea').css({
	// 					"position" : "relative",

	// 				}), label = $("<span/>").css({
	// 					"zIndex" : 999,
	// 					"backgroundColor" : options.backgroundColor,
	// 					"position" : "absolute",
	// 					"font-size" : options.font.size,
	// 					"color" : options.font.color
	// 				}).css(options.position);
	// 				if (options.limit > 0) {
	// 					label
	// 							.text("{0}/{1}".format(el.val().length,
	// 									options.limit));
	// 					el.prop("maxlength", options.limit);
	// 				} else {
	// 					label.text(el.val().length);
	// 				}
	// 				el.wrap(wrapper);
	// 				el.before(label);
	// 				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
	// 						'keydown', updateLabel);
	// 				function updateLabel(e) {
	// 					if (options.limit > 0) {
	// 						label.text("{0}/{1}".format($(this).val().length,
	// 								options.limit));
	// 					} else {
	// 						label.text($(this).val().length);
	// 					}
	// 				}
	// 			});
	// 		}
	// 	})(jQuery);
	// 	$(".char-counter").charCounter();
</script>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" />
</c:if>

<script nonce="${cspNonce}">

	$(document).ready(function() {
		
			
		function ParseDateColumncommission(data) {
			  
			  var date="";
			  if(data!=null && data!=""){			 
				 var d = new Date(data);			 
				 var day=('0' + d.getDate()).slice(-2);
				 var month=('0' + (d.getMonth() + 1)).slice(-2);
				 var year=""+d.getFullYear();		    				 
				// date=year+"-"+month+"-"+day;
				 date=day+"/"+month+"/"+year;
				   		}
				    return date;
			}
		$("#date1").val(ParseDateColumncommission('${created_on}'));
	
		$("#completed_dt1").val(ParseDateColumncommission('${completed_dt}'));
		
		var status1 = '${getStatusNameList}';
		$("#status").val(status1);

		var filename = '${screen_shot}';
		debugger;
		if (filename && filename.trim() !== "") {
			var fileName2 = filename.substring(filename.lastIndexOf('/') + 1);
			$("#uploadedFileName").text(fileName2);
			$('#download_id').removeClass('display_none');
		} else {
			$("#uploadedFileName").text('No file uploaded');
			$('#download_id').removeClass('display_none');
		}

		if ('${msg1}' != "") {
			alert('${msg1}');
		}

		$("select#help_topic").val('${help_topic}');
		$("#section").val('${getSectionvalue}');
		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();

		if ('${ticket_status}' == '0') {
			var date = '${created_on}';
			$("input#date1").val(date);
		}
		if ('${ticket_status}' == '1') {
			$("#ticket_status").val('${ticket_status}');
			// 		$("#agent_name").val('${assigned_to}');
			var date = '${assigned_dt}';
			$("input#assigned_dt").val(date);
		}
		if ('${ticket_status}' == '2') {
			$("#ticket_status").val('${ticket_status}');
			$("#agent_name").val('${assigned_to}');
			var date = '${completed_dt}';
			$("input#completed_dt").val(date);
			$("input#update1").hide();
		}
		var remarksValue = $("#remarks").val();
		if (remarksValue && remarksValue.trim() !== "") { // Check if value exists and is not just whitespace
		}
		if ('${msg}' != "") {
			alert($("#msg").val());
		}
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
	});
	function printDiv() {
		debugger;
		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();
		var printLbl = [];
		var printVal = [];
		if ('${ticket_status}' == "0") {
			status = "New";
			printLbl = [ "Ticket Id :", "Created on :", "Created by :",
					"Help Topic :", "Issue summary :", "Status:",
					"Description :" ];
			printVal = [ document.getElementById('ticket').value,
					document.getElementById('date1').value,
					document.getElementById('username').value,
					document.getElementById('help_topic').value,
					document.getElementById('issue_summary').value, status,
					document.getElementById('description').value ];
		} else if ('${ticket_status}' == "1") {
			status = "In Progress";
			printLbl = [ "Ticket Id :", "Created on :", "Created by :",
					"Help Topic :", "Assign on :", "Issue summary :",
					"Status:", "Description :" ];
			printVal = [ document.getElementById('ticket').value,
					document.getElementById('date1').value,
					document.getElementById('username').value,
					document.getElementById('help_topic').value,
					document.getElementById('assigned_dt').value,
					document.getElementById('issue_summary').value, status,
					document.getElementById('description').value ];
		} else if ('${ticket_status}' == "2") {
			status = "Completed";
			printLbl = [ "Ticket Id :", "Created on :", "Created by :",
					"Help Topic :", "Assign on :", "completed on:",
					"Issue summary :", "Status:", "Description :" ];
			printVal = [ document.getElementById('ticket').value,
					document.getElementById('date1').value,
					document.getElementById('username').value,
					document.getElementById('help_topic').value,
					document.getElementById('assigned_dt').value,
					document.getElementById('completed_dt').value,
					document.getElementById('issue_summary').value, status,
					document.getElementById('description').value ];
		} else if ('${ticket_status}' == "3") {
			status = "Feedback";
		} else if ('${ticket_status}' == "4") {
			status = "Feature Request";
		}
		printDivOptimizehelp('printableArea', 'Ticket Details', printLbl,
				printVal, "select#status");
	}
</script>