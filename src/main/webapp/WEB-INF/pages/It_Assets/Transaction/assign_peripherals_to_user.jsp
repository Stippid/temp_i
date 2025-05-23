<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/Calender/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script src="js/Calender/jquery-ui.js" nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>

<style nonce="${cspNonce}">
.f_10 {
	font-size: 12px;
}

.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}

.go-top {
	right: 1em;
	bottom: 2em;
	color: #FAFAFA;
	text-decoration: none;
	background: #F44336;
	padding: 5px;
	border-radius: 5px;
	border: 1px solid #e0e0e0;
	position: fixed;
	font-size: 180%;
}

label {
	word-break: break-word;
}

.ui-tooltip {
	position: absolute;
	top: 110px;
	left: 100px;
}

.card-body.card-block {
	margin: 20px;
	background: #fff;
	border: 2px solid #e4dede;
}

.card {
	background: #ffffff;
	border: 0;
}

.admin {
	font-size: 25px; /* Adjust the size as needed */
}
</style>
<form:form name="MainAssets" id="MainAssets"
	action="peripheralassetassignaction?${_csrf.parameterName}=${_csrf.token}" method="post"
	class="form-horizontal" modelAttribute="PeripheralMainAssetsassignCmd"
	enctype="multipart/form-data">
	<div class="" align="center">
		<div class="card">
			<div class="card-header">
				<h5>PERIPHERAL ASSIGN ASSET TO USER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<!-- Section -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
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
						<!-- Assets Type -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Peripheral Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="" id="assets_type" class="form-control"
										name="assets_type">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<!-- Make/Brand Name -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Type of Peripheral HW</label>
								</div>
								<div class="col-md-8">
									<form:select path="" id="type_of_hw" name="type_of_hw"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${hardwareListDDL}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<!-- Model Name -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="" id="make_name" name="make_name"
										class="form-control">
										<option value="0">--Select--</option>

									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<!-- Processor Type -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="" id="model_name" name="model_name"
										class="form-control">
										<option value="0">--Select--</option>
									</form:select>
								</div>
							</div>
						</div>
						<!-- RAM Capacity -->

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">Assign User</label>
								</div>
								<div class="col-md-8">
									<select name="assignuser" id="assignuser" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getTypeOfSecuserList}"
											varStatus="num">
											<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">AssetCount</label>
								</div>
								<div class="col-md-8">
									<input type="text" class="form-control" id="assetcount"
										name="assetcount" />
								</div>
							</div>
						</div>
						<!-- HDD Capacity -->

						<!-- SSD Installed -->

					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<a href="${screenurl}" class="btn btn-primary btn-sm" id="btn_clc">Clear</a>
				<input type="submit" class="btn btn-success btn-sm" id="searchbtn"
					value="Save" onclick="return validate();" />
				<!-- 		          <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Computing Data" onclick="getExcel1();" />             -->
			</div>
		</div>
	</div>
</form:form>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('assets_type').onchange = function() {

			return fn_Peripheral(), fn_makeName();
		};

		document.getElementById('make_name').onchange = function() {
			return fn_modelName(), fn_brand_other();
		};
	});

	function fn_Peripheral() {
		var peripheral_type = $("select#assets_type").val();
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

	function fn_makeName() {
		var assets_name = $("select#assets_type").val();
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
</script>


