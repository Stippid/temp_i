<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"
	nonce="${cspNonce}">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"
	nonce="${cspNonce}">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>

<form:form
	action="Edit_Model_master_Action?${_csrf.parameterName}=${_csrf.token}"
	method='POST' modelAttribute="Edit_Model_master_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE MODEL MASTER</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label for="text-input"
										class=" form-control-label">CATEGORY</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category" class="form-control"
										onchange="fn_Category();">
										<option value="0">--Select--</option>
										<option value="1">Computing</option>
										<option value="2">Peripheral</option>
										<option value="3">Consumable</option>
									</select> <input type="hidden" id="make_no" name="make_no"
										class="form-control" maxlength="3" value="0"
										onkeypress="return validateNumber(event, this)"
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">ASSETS NAME</label>
								</div>
								<div class="col-md-8">
									<select name="assets_name" id="assets_name"
										class="form-control">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong><label
										class=" form-control-label">MAKE/BRAND NAME</label>

								</div>
								<div class="col-md-8">
									<select name="make_name" id="make_name" class="form-control">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">MODEL NAME</label>
								</div>
								<div class="col-md-8">
									<input id="model_name" name="model_name" maxlength="100"
										class="form-control text_transform_upper" autocomplete="off" />
								</div>
							</div>
						</div>


						<input type="hidden" id="id" name="id" class="form-control"
							autocomplete="off" value="0">

					</div>
				</div>

				<div class="card-footer" align="center" class="form-control">
					<a href=model_masterUrl class="btn btn-success btn-sm">Back</a> <input
						type="submit" class="btn btn-primary btn-sm" value="Update"
						onclick="return Validate();" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<script nonce="${cspNonce}">
	$(document).ready(function() {
		$.ajaxSetup({
			async : false
		});

		$('select#category').val('${Edit_Model_master_CMD.category}');
		fn_Category();

		// 	$("#model").hide();
		$('select#assets_name').val('${Edit_Model_master_CMD.assets_name}');

		fn_makeName();
		$('select#make_name').val('${Edit_Model_master_CMD.make_name}');

		$('input#model_name').val('${Edit_Model_master_CMD.model_name}');
		$("#id").val('${Edit_Model_master_CMD.id}');

	});

	function Validate() {

		if ($("#category").val() == 0) {
			alert("Please Select Category");
			$("select#category").focus();
			return false;
		}

		if ($("#assets_name").val() == 0) {
			alert("Please Select Assets Name");
			$("#assets_name").focus();
			return false;
		}
		if ($("select#make_id").val() == 0) {
			alert("Please Select Make/Brand Name");
			$("select#make_id").focus();
			return false;
		}
		if ($("input#model_name").val().trim() == "") {
			alert("Please Enter Model Name");
			$("input#model_name").focus();
			return false;
		}
		return true;
	}

	function fn_Category() {

		var categogry_id = $("select#category").val();
		$
				.post("getCategoryList?" + key + "=" + value, {
					categogry_id : categogry_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#assets_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function fn_makeName() {

		var assets_name = $("select#assets_name").val();

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
</script>