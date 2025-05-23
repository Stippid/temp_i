<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<style nonce="${cspNonce}">
textarea {
	width: 690px;
	height: 200px;
	text-transform: capitalize;
}

input[type="text"] {
	text-transform: capitalize;
}
</style>

	<form:form name="help" id="help"
		action="helpAction?${_csrf.parameterName}=${_csrf.token}" method='POST'
		modelAttribute="helpCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Open a New Ticket</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong> Section </label>
								</div>
								<div class="col-md-8">
									<label id="unit_sus_no_hid" class="display_none"><b>
											${roleSusNo} </b></label> <select id="section" name="section"
										class="form-control">

										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}">${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										class="color_red">*</strong> Module</label>
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
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										class="color_red">*</strong> Ticket Type </label>
								</div>
								<div class="col-md-8">
									<select id="help_topic" name="help_topic" class="form-control">
										<option value="0">--Select--</option>
										<option value="1">Hardware Issue</option>
										<option value="2">Software Issue</option>
										<option value="3">Feedback</option>
										<option value="4">Others</option>
										<!-- 										<option value="5">Feature Request</option> -->
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Screenshot<strong
										class="color_red"> (max size 5 Mb)</strong></label>
								</div>
								<div class="col-md-8">
									<input type="file" id="filedoc" name="filedoc"
										class="form-control">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										class="color_red"></strong> Issue summary </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="issue_summary" name="issue_summary"
										placeholder="Maximum 100 characters"
										class="width680 form-control char-counter" autocomplete="off"
										maxlength="100"></input>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"><strong
										class="color_red">*</strong> Description (Max 1000 words)</label>
								</div>
								<div class="col-md-8">
<!-- 									<textarea  id="description" -->
<!-- 										name="description" class="form-control" -->
<!-- 										autocomplete="off"></textarea> -->
                                 <textarea id="description"
							name="description" maxlength="1000"
							class="form-control autocomplete transform" autocomplete="off"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='card-footer' align='center'>
					<input type="submit" id="submit_btn" class="btn btn-primary btn-sm"
						value="Submit">
						<input type="reset" id="clearbtn" class="btn btn-success btn-sm"
					value="Clear">
				</div>
			</div>
		</div>
	</div>
</form:form>
<Script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('filedoc').onchange = function() {
			return ValidateSize(this);
		};
		document.getElementById('submit_btn').onclick = function() {
			return isvalidData();
		};

	});

	$(document).ready(function() {

		$('#module').val('36');

		// Trigger change event after setting the value
		$('#module').trigger('change');

		var file = document.getElementById('filedoc');
		file.onchange = function(e) {
			var ext = this.value.match(/\.([^\.]+)$/)[1];
			switch (ext) {
			case 'jpg':
			case 'jpeg':
			case 'pdf':
			case 'JPG':
			case 'JPEG':
			case 'PDF':
			case 'PNG':
				break;
			default:
				alert('Please Only Allowed *.jpeg/.pdf File Extension');
				this.value = '';
			}
			if (this.files[0].size >5242880) {
				alert("File size must be 5 Mb!");
				this.value = "";
			}
		};
	});

	$('select#module')
			.change(
					function() {

						var mid = this.value;
						var sList = new Array();
						if ($("select#module").val() == "-1") {
							document.getElementById("sub_module").disabled = true;
							document.getElementById("screen_name").disabled = true;
						}
						if ($("select#module").val() != "-1") {
							document.getElementById("sub_module").disabled = false;
							document.getElementById("screen_name").disabled = false;
						}

						var options = '<option value="'+"0"+'">' + "--Select--"
								+ '</option>';
						<c:forEach var="item" items="${getSubModuleNameList}" varStatus="num" >
						if ('${item.module.id}' == mid) {
							options += '<option class="text_transform_upper"  value="${item.id}">${item.submodule_name}</option>';
						}
						</c:forEach>
						$("select#sub_module").html(options);

					});
	$('select#sub_module')
			.change(
					function() {
						var mid = this.value;
						var sList = new Array();
						var options = '<option value="'+"0"+'">' + "--Select--"
								+ '</option>';
						<c:forEach var="item" items="${getScreenList}" varStatus="num" >
						if ('${item.sub_module.id}' == mid) {
							options += '<option class="text_transform_upper" value="${item.id}">${item.screen_name}</option>';
						}
						</c:forEach>
						$("select#screen_name").html(options);
					});
</Script>
<script nonce="${cspNonce}">
	function isvalidData() {
		if ($("select#section").val() == "0") {
			alert("Please Select Section");
			$("select#section").focus();
			return false;
		}

		// 	 if($("select#module").val() != "-1"){

		// 		       if($("select#sub_module").val() == "0")
		// 			{
		// 				alert("Please Select Sub Module");
		// 				$("select#sub_module").focus();
		// 				return false;
		// 			}
		// 			if($("select#screen_name").val() == "0")
		// 			{
		// 					alert("Please Select Screen Name");
		// 					$("select#screen_name").focus();
		// 					return false;
		// 			}
		// 		}

		if ($("select#help_topic").val() == "0") {
			alert("Please Select Ticket Type");
			$("select#help_topic").focus();
			return false;
		}
		// 	if($("input#issue_summary").val() == "")
		// 	{
		// 		alert("Please Enter issue summary");
		// 		$("input#issue_summary").focus();
		// 		return false;
		// 	}
		if ($("textarea#description").val() == "") {
			alert("Please Enter description");
			$("textarea#description").focus();
			return false;
		}
		return true;
	}
	(function($) {
		"use strict";
		$.fn.charCounter = function(options) {
			if (typeof String.prototype.format == "undefined") {
				String.prototype.format = function() {
					var content = this;
					for (var i = 0; i < arguments.length; i++) {
						var replacement = '{' + i + '}';
						content = content.replace(replacement, arguments[i]);
					}
					return content;
				};
			}
			var options = $.extend({
				backgroundColor : "#FFFFFF",
				position : {
					right : 10,
					top : 10
				},
				font : {
					size : 10,
					color : "#a59c8c"
				},
				limit : 100
			}, options);
			return this.each(function() {
				var el = $(this), wrapper = $("<div/>").addClass(
						'focus-textarea').css({
					"position" : "relative",

				}), label = $("<span/>").css({
					"zIndex" : 999,
					"backgroundColor" : options.backgroundColor,
					"position" : "absolute",
					"font-size" : options.font.size,
					"color" : options.font.color
				}).css(options.position);
				if (options.limit > 0) {
					label
							.text("{0}/{1}".format(el.val().length,
									options.limit));
					el.prop("maxlength", options.limit);
				} else {
					label.text(el.val().length);
				}
				el.wrap(wrapper);
				el.before(label);
				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
						'keydown', updateLabel);
				function updateLabel(e) {
					if (options.limit > 0) {
						label.text("{0}/{1}".format($(this).val().length,
								options.limit));
					} else {
						label.text($(this).val().length);
					}
				}
			});
		}
	})(jQuery);
	$(".char-counter").charCounter();
</script>
<Script nonce="${cspNonce}">
	(function($) {
		"use strict";
		$.fn.charCounter = function(options) {
			if (typeof String.prototype.format == "undefined") {
				String.prototype.format = function() {
					var content = this;
					for (var i = 0; i < arguments.length; i++) {
						var replacement = '{' + i + '}';
						content = content.replace(replacement, arguments[i]);
					}
					return content;
				};
			}
			var options = $.extend({
				backgroundColor : "#FFFFFF",
				position : {
					right : 10,
					top : 10
				},
				font : {
					size : 10,
					color : "#a59c8c"
				},
				limit : 1000
			}, options);
			return this.each(function() {
				var el = $(this), wrapper = $("<div/>").addClass(
						'focus-textarea').css({
					"position" : "relative",
					"display" : "inline-block"
				}), label = $("<span/>").css({
					"zIndex" : 999,
					"backgroundColor" : options.backgroundColor,
					"position" : "absolute",
					"font-size" : options.font.size,
					"color" : options.font.color
				}).css(options.position);
				if (options.limit > 0) {
					label
							.text("{0}/{1}".format(el.val().length,
									options.limit));
					el.prop("maxlength", options.limit);
				} else {
					label.text(el.val().length);
				}
				el.wrap(wrapper);
				el.before(label);
				el.on("keyup", updateLabel).on("keypress", updateLabel).on(
						'keydown', updateLabel);
				function updateLabel(e) {
					if (options.limit > 0) {
						label.text("{0}/{1}".format($(this).val().length,
								options.limit));
					} else {
						label.text($(this).val().length);
					}
				}
			});
		}
	})(jQuery);
	$(".char-counter1").charCounter();
</Script>