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
	action="assetassignaction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	modelAttribute="MainAssetsassigneditCmd" enctype="multipart/form-data">
	<div class="" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Edit ASSIGN ASSET TO USER</h5>
			</div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<!-- Section -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">Section</label>
								</div>
								<div class="col-md-8">
									<select id="section" name="section" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}"
												<c:if test="${item.id == MainAssetsassigneditCmd.section}">selected</c:if>>${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<!-- Assets Type -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">Assets Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="asset_type" id="asset_type" name="asset_type"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
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
									<label class="form-control-label">Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name" name="make_name"
										class="form-control">
										<form:option value="0">--Select--</form:option>
									</form:select>
								</div>
							</div>
						</div>
						<!-- Model Name -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="model_name" id="model_name" name="model_name"
										class="form-control">
										<form:option value="0">--Select--</form:option>
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
									<label class="form-control-label">Processor Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="processor_type" id="processor_type"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${processor_typeList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<!-- RAM Capacity -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">RAM Capacity</label>
								</div>
								<div class="col-md-8">
									<form:select path="ram_capi" id="ram_capi" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${ramList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<!-- HDD Capacity -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">HDD Capacity</label>
								</div>
								<div class="col-md-8">
									<form:select path="hdd_capi" id="hdd_capi" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<!-- SSD Installed -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">SSD Installed</label>
								</div>
								<div class="col-md-8">
									<div id="ssdcheckid">
										<input type="radio" id="ssdcheck1" name="ssdcheck" value="Yes" />
										<label for="ssdcheck1">Yes</label> <input type="radio"
											id="ssdcheck2" name="ssdcheck" value="No" /> <label
											for="ssdcheck2">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<!-- SSD Capacity -->
						<div class="col-md-6 display_none" id="divssdcap">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">SSD Capacity</label>
								</div>
								<div class="col-md-8">
									<form:select path="ssd_capi" id="ssd_capi" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${sddList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<!-- Operating System -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class="form-control-label">Operating System</label>
								</div>
								<div class="col-md-8">
									<form:select path="operating_system" id="operating_system"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${osList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<!-- Assign User -->
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
								<input type="text" class="form-control" id="assetcount" name="assetcount"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<a href="${screenurl}" class="btn btn-primary btn-sm" id="btn_clc">Clear</a>
		<input type="submit"
					class="btn btn-success btn-sm" id="searchbtn" value="Save" onclick="return validate();"/>
				<!-- 		          <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Computing Data" onclick="getExcel1();" />             -->
			</div>
		</div>
	</div>
</form:form>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('make_name').onchange = function() {
			return fn_modelName(), fn_brand_other();
		};
		document.getElementById('asset_type').onchange = function() {
			return fn_makeName();
		};
		document.getElementById('ssdcheckid').onchange = function() {
			return ssd_show();
		};

	});
	function ssd_show() {
		if ($("#ssdcheck1").prop("checked")) {
			$("#divssdcap").show();
		} else {
			$('#divssdcap').hide();
			$('#ssd_capi').val('0');
		}

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
	function fn_brand_other() {
		var text = $("#make_name option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			$("#brand_other_hid").show();
		} else {
			$("#brand_other_hid").hide();
			$("#brand_other").val('');
		}
	}

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
	
	$(document).ready(function() {
		debugger;
	if('${MainAssetsassigneditCmd.id}' != "0"){
		$("#assignuser").val('${MainAssetsassigneditCmd.assignuser}');
		$("#assetcount").val('${MainAssetsassigneditCmd.assetcount}');
		fn_makeName();
// 		var makename = '${MainAssetsassigneditCmd.make_name}';
// 		$("select#make_name").val(makename);
// 		console.log('${MainAssetsassigneditCmd.make_name}')
  var makename = '${MainAssetsassigneditCmd.make_name}';

  if (makename) { // Check if makename has a value (is not empty or null)
    $("select#make_name").val(makename);
  } else {
    console.warn("make_name value from MainAssetsassigneditCmd is empty or null.");
  }
		
		fn_modelName();
		var modelname = '${MainAssetsassigneditCmd.model_name}'
		$("#model_name").val('${MainAssetsassigneditCmd.model_name}');	
		console.log('${MainAssetsassigneditCmd.model_name}')
		
		if('${MainAssetsassigneditCmd.ssd_capi}' != "0"){
			$("input[name='ssdcheck'][value='Yes']").prop("checked",true);
			$("#divssdcap").show();
			$("#ssd_capi").val('${MainAssetsassigneditCmd.ssd_capi}');
		}else{
			$("input[name='ssdcheck'][value='No']").prop("checked",true);
			$("#divssdcap").hide();
		}
	}
	
});
</script>


