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
	action="MainAssetsAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal" modelAttribute="MainAssetsCmd"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 id="save">ADD COMPUTING ASSETS</h5>
					<h5 id="update">UPDATE/EDIT COMPUTING ASSETS</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<input type="hidden" id="make_mstr_id" name="make_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="model_mstr_id" name="model_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="pro_type_id" name="pro_type_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="office_mstr_id" name="office_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="os_mstr_id" name="os_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="screenUrl" name="screenUrl" value="${screen_url}"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="u_file_hid" name="u_file_hid"
					value="${MainAssetsCmd.u_file}" class="form-control autocomplete"
					autocomplete="off"> <input type="hidden"
					id="br_certificate1_id" name="br_certificate1_id"
					value="${MainAssetsCmd.br_certificate}"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="supplierId" name="supplierId">

				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong> Section </label>
								</div>
								<div class="col-md-8">
									<select id="section" name="section" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}"
												<c:if test="${item.id == MainAssetsCmd.section}">selected</c:if>>${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4"></div>
								<div class="col-md-8">
									<label class=" form-control-label admin"><strong
										class="color_red"></strong><b>${username}</b> </label>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Assets Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="asset_type" id="asset_type"
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

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name"
										class="form-control">
										<form:option value="0">--Select--</form:option>
									</form:select>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 display_none" id="brand_other_hid">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Make/Brand Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="brand_other" name="brand_other"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="model_name" id="model_name"
										class="form-control">
										<form:option value="0">--Select--</form:option>
									</form:select>
								</div>
							</div>
						</div>

						<div class="col-md-6 display_none" id="model_other_hid">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Model Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="model_other" name="model_other"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Processor Type</label>
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
						<div class="col-md-6 display_none" id="pro_type_other_hid">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Processor Type Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="pro_type_other" name="pro_type_other"
										class="form-control autocomplete" maxlength="50">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>RAM Capacity </label>
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>HDD Capacity </label>
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
					</div>
					<div class="row ">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>SSD Installed </label>
								</div>
								<div class="col-md-8" id="ssdcheckid">
									<input type="radio" id="ssdcheck1" name="ssdcheck" value="Yes"></input>&nbsp
									<label for="ssdcheck1">Yes</label>&nbsp <input type="radio"
										id="ssdcheck2" name="ssdcheck" value="No"></input>&nbsp <label
										for="ssdcheck2">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="divssdcap">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>SSD Capacity</label>
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
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Operating System </label>
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
						<div class="col-md-6 display_none" id="os_other_hid">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Operating System Other</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="os_other" name="os_other"
										class="form-control autocomplete" autocomplete="off"
										maxlength="50">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"> </strong>Office Productivity Suite</label>
								</div>
								<div class="col-md-8">
									<form:select path="office" id="office" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${officeList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="office_other_hid">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Office Other </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="office_other" name="office_other"
										class="form-control autocomplete" maxlength="50">
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"> </strong>Antivirus Installed </label>
								</div>
								<div class="col-md-8" id="antiviruscheckid">
									<form:radiobutton id="antiviruscheck1" value="Yes"
										path="antiviruscheck"></form:radiobutton>
									&nbsp <label for="antiviruscheck1">Yes</label>&nbsp
									<form:radiobutton id="antiviruscheck2" path="antiviruscheck"
										value="No" checked="checked"></form:radiobutton>
									&nbsp <label for="antiviruscheck2">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="AntiVirusDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Antivirus</label>
								</div>
								<div class="col-md-8">
									<form:select path="antivirus" id="antivirus"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${AntivirusList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 display_none" id="antivirus_date">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Antivirus expiry date </label>
								</div>
								<div class="col-md-8">
									<input type="text" name="antivirus_expry1"
										id="antivirus_expry1" maxlength="10"
										class="form-control width88 display_inline rgb0"
										aria-required="true" autocomplete="off" />
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Dply Envt as Applicable</label>
								</div>
								<div class="col-md-8">
									<form:select path="dply_envt" id="dply_envt"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"></strong>CD Drive</label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
										<form:radiobutton id="scan1" value="Yes" path="cd_drive"></form:radiobutton>
										&nbsp <label for="scan1">Yes</label>&nbsp
										<form:radiobutton id="scan2" path="cd_drive" value="No"
											checked="checked"></form:radiobutton>
										&nbsp <label for="scan2">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row"></div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Warranty</label>
								</div>
								<div class="col-md-8" id="warrantyid">
									<form:radiobutton id="Warranty1" value="Yes"
										path="warrantycheck" checked="checked"></form:radiobutton>
									&nbsp <label for="Warranty1">Yes</label>&nbsp
									<form:radiobutton id="Warranty2" path="warrantycheck"
										value="No"></form:radiobutton>
									&nbsp <label for="Warranty2">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="WarrantyDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Warranty Upto</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="warranty_dt" id="warranty"
										maxlength="10"
										class="form-control width88 display_inline rgb0"
										aria-required="true" autocomplete="off">
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6 display_none" id="countDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Total Count</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="assetcount" name="assetcount" value="1"
										class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="" id="multiAssetDiv">
						<div class="row modeldiv">
							<div class="row">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												class="color_red"></strong>MAC Address</label>
										</div>
										<div class="col-md-8">
											<input
												title="Enter MAC Address in given format  8C-EC-4B-48-01-00. Type getmac in command prompt and press Enter to get MAC Address."
												oninput="this.value = this.value.toUpperCase()" type="text"
												id="mac_address1" name="mac_address1"
												class="form-control autocomplete" maxlength="17"
												autocomplete="off"></input>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												class="color_red">* </strong>Machine No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="machine_number1"
												name="machine_number1"
												class="form-control autocomplete machineno_class"
												maxlength="30" autocomplete="off"
												class="form-control autocomplete">
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label id="nonIpDisplay" class=" form-control-label"><strong
												class="color_red"> </strong>IP Address </label> <label
												id="ipDisplay" class=" form-control-label display_none"><strong
												class="color_red">* </strong>IP Address </label>
										</div>
										<div class="col-md-8">
											<input
												title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address."
												type="text" id="ip_address1" name="ip_address1"
												maxlength="15" class="form-control autocomplete"
												autocomplete="off"></input>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title white" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_warrenty"><b>SERVICE
										DETAILS</b></a>
							</h4>
						</div>
						<div id="collapsewarrantey" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="row">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Serviceable State</label>
												</div>
												<div class="col-md-8">
													<form:select path="s_state" id="s_state"
														class="form-control">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${getServiceable_StateList}"
															varStatus="num">
															<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>

												</div>
											</div>
										</div>
										<div class="col-md-6 display_none" id="un_show">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Repair State</label>
												</div>
												<div class="col-md-8">
													<form:select path="unserviceable_state"
														id="unserviceable_state" class="form-control">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${UNServiceableList}"
															varStatus="num">
															<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>

									<div class="row display_none" id="file_show">
										<div class="col-md-6 ">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Upload BR Certificate</label>
												</div>
												<div class="col-md-8">
													<input type="file" id="br_certificate1"
														name="br_certificate1" /> <i id="download_i1"
														class="fa fa-download display_none"><span
														id="uploadedFileName1" class="uploaded-file-name f_10">${MainAssetsCmd.br_certificate}</span><input
														type="button" class="btn btn-success btn-sm display_none"
														id="downloadbtn" value="File" /></i>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="row">
										<div class="col-md-6 display_none" id="unsv_div">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Un-Servicable From Date</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="unsv_from_dt1" id="unsv_from_dt1"
														maxlength="10"
														class="form-control width88 display_inline rgb0"
														aria-required="true" autocomplete="off">
												</div>
											</div>
										</div>
										<div class="col-md-6 display_none" id="maintaince_div">
											<div class="col-md-4">
												<label class="form-control-label"><strong
													class="color_red">* </strong>Maintenance Agency</label>
											</div>
											<div class="col-md-8">
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
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="panel-group" id="accordion4">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title white" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_budget"><b>PROCUREMENT
										DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1budget" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">

									<div class="row">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red"> </strong>Proc Cost (&#8377) </label>
												</div>
												<div class="col-md-8">
													<input title="Approx. Procurement Cost" type="text"
														id="b_cost" name="b_cost"
														class="form-control autocomplete" autocomplete="off" />

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Budget Head And Code</label>
												</div>
												<div class="col-md-8">
													<form:select path="b_head" id="b_head" class="form-control">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${getBudgetHeadList}"
															varStatus="num">
															<form:option value="${item[1]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="col-md-4">
												<label class="form-control-label"><strong
													class="color_red">*</strong>CRV No.</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="crv_no" name="crv_no"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">*</strong>Crv Date</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="proc_dt" id="proc_date"
														maxlength="10"
														class="form-control width88 display_inline rgb0"
														aria-required="true" autocomplete="off">
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Upload Crv Document</label>
												</div>
												<div class="col-md-8">
													<input type="file" id="u_file1" name="u_file1" /> <i
														id="download_i" class="fa fa-download display_none"><span
														id="uploadedFileName" class="uploaded-file-name f_10">${MainAssetsCmd.u_file}</span><input
														type="button" class="btn btn-success btn-sm display_none"
														id="downloadbtn" value="File" /></i>

												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class="form-control-label">GeM Contract No.</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="gem_no1" name="gem_no1"
														maxlength="12" class="form-control" autocomplete="off"></input>
												</div>
											</div>
										</div>
									</div>

									<div class="row" id="supplier_field">
										<div class='col-md-6'>
											<div class='row form-group'>
												<div class="col-md-4">
													<strong class="color_red">*</strong> <label
														for="text-input" class="form-control-label">Supplier</label>
												</div>
												<div class='col-md-8'>
													<input type="text" id="supplier" name="supplier"
														class="form-control autocomplete" autocomplete='off'
														maxlength="100"></input>
												</div>
											</div>
										</div>

										<div class='col-md-6'>
											<div class='row form-group'>
												<div class='col-md-4'>
													<strong class="color_red">*</strong> <label
														for="text-input" class="form-control-label">Supplier
														Contact No:</label>
												</div>
												<div class='col-md-8'>
													<input type="text" id="contact_number"
														class="form-control text_transform_upper"
														autocomplete='off' maxlength="10" readonly></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="card-footer" align="center" class="form-control">
			<a href="${screen_url}" id="clear_1" class="btn btn-success btn-sm">Back</a>
			<a href="MainAssertsUrl" class="btn btn-primary btn-sm"
				id="clear_btn">Clear</a> <input type="submit"
				class="btn btn-success btn-sm" value="Save" id="save_btn"> <input
				type="submit" class="btn btn-primary btn-sm" value="Update"
				id="update_btn">
		</div>
	</div>
	</div>
</form:form>
<script nonce="${cspNonce}">

document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('save_btn').onclick = 
		function() {
		return Validate();
  	};
  	document.getElementById('update_btn').onclick = 
		function() {
		return Validate();
  	};
  	document.getElementById('antivirus_expry1').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY')
  	};
 	document.getElementById('antivirus_expry1').onfocus = 
		function() {
		return this.style.color='#000000'
  	};
 	document.getElementById('antivirus_expry1').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
 	document.getElementById('antivirus_expry1').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
 	document.getElementById('antivirus_expry1').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	////////
 	document.getElementById('proc_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY')
  	};
 	document.getElementById('proc_date').onfocus = 
		function() {
		return this.style.color='#000000'
  	};
 	document.getElementById('proc_date').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
 	document.getElementById('proc_date').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
 	document.getElementById('proc_date').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	document.getElementById('a_budget').onclick = 
		function() {
		return divN(this);
  	};
  	
  	
 	document.getElementById('unsv_from_dt1').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY')
  	};
 	document.getElementById('unsv_from_dt1').onfocus = 
		function() {
		return this.style.color='#000000'
  	};
 	document.getElementById('unsv_from_dt1').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
 	document.getElementById('unsv_from_dt1').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
 	document.getElementById('unsv_from_dt1').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
	document.getElementById('downloadbtn').onclick = 
		function() {
		return download_file();
  	};
  	
  	
  	document.getElementById('a_warrenty').onclick = 
		function() {
		return divN(this);
  	};
  	
	document.getElementById('ip_address1').onchange = 
		function() {
		return ValidateIPaddress(this);
  	};
  	
  	
	document.getElementById('machine_number1').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
  	document.getElementById('machine_number1').onkeypress = 
		function() {
  		return onlyAlphaNumeric(event, this);
  	};
	document.getElementById('machine_number1').onchange = 
		function() {
  		return searchMachineNumber();
  	};
  	
  	
  	
	
	document.getElementById('mac_address1').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
  	document.getElementById('mac_address1').onkeyup = 
		function() {
  		return makeMacAddress(this);
  	};
	document.getElementById('mac_address1').onchange = 
		function() {
		return validateCodeMac();
  	};
	document.getElementById('machine_number1').onkeypress = 
		function() {
  		return makeMacAddress(this);
  	};
  	document.getElementById('machine_number1').onkeypress = 
		function() {
  		return  /[0-9a-fA-F]/i.test(event.key);
  	};
	document.getElementById('machine_number1').onkeypress = 
		function() {
		return onlyAlphaNumeric(event, this);
  	};
  	
  	
  	document.getElementById('assetcount').onkeyup = 
		function() {
		return addMultiAsset();
  	};
	
  	document.getElementById('assetcount').onblur = 
		function() {
		return counthover();
  	};
  	document.getElementById('assetcount').onkeypress = 
		function() {
		return isNumber(event);
  	};
  	
/*   	
  	document.getElementById('model_number1').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	}; */
/*   	document.getElementById('model_number1').onkeypress = 
		function() {
  		return onlyAlphaNumeric(event, this);
  	}; */
/*   	document.getElementById('model_number1').onkeypress = 
		function() {
		return alphanumeric(this);
  	};
  	 */
  	
  	document.getElementById('warrantyid').onclick = 
		function() {
		return warrenty_show();
  	};
  	
  	
	
 	document.getElementById('warranty').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY')
  	};
 	document.getElementById('warranty').onfocus = 
		function() {
		return this.style.color='#000000'
  	};
 	document.getElementById('warranty').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
 	document.getElementById('warranty').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
 	document.getElementById('warranty').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
// 	document.getElementById('ram_slot_qty').onkeypress = 
// 		function() {
// 		return isNumber(event);
//   	};
  	
  	document.getElementById('office_other').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
 	document.getElementById('office_other').onkeypress = 
		function() {
		return onlyAlphaNumeric(event);
  	};
 	document.getElementById('office_other').onchange = 
		function() {
		return searchOfficeTypeOther();
  	};
  	
	document.getElementById('antiviruscheckid').onchange = 
		function() {
		return anti_show();
  	};
//   	document.getElementById('approveoisid').onchange = 
// 		function() {
// 		return supplier_show();
//   	};
	document.getElementById('ssdcheckid').onchange = 
		function() {
		return ssd_show();
  	};

  	document.getElementById('os_other').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
 	document.getElementById('os_other').onkeypress = 
		function() {
		return onlyAlphaNumeric(event);
  	};
 	document.getElementById('os_other').onchange = 
		function() {
		return searchOfficeTypeOther();
  	}; 
  	
	document.getElementById('office').onchange = 
		function() {
		return fn_office_other();
  	}; 
  	
  	document.getElementById('operating_system').onchange = 
		function() {
		return fn_os_other();
  	}; 

  	document.getElementById('model_other').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
 	document.getElementById('model_other').onkeypress = 
		function() {
		return onlyAlphaNumeric(event);
  	};
 	document.getElementById('model_other').onchange = 
		function() {
		return searchModelOther();
  	}; 
  	
	document.getElementById('processor_type').onchange = 
		function() {
		return fn_pro_type_other();
  	}; 
  	
 	document.getElementById('pro_type_other').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
 	document.getElementById('pro_type_other').onkeypress = 
		function() {
		return onlyAlphaNumeric(event);
  	};
 	document.getElementById('pro_type_other').onchange = 
		function() {
		return searchProcessorTypeOther();
  	}; 
  	
  	
	document.getElementById('brand_other').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
 	document.getElementById('brand_other').onkeypress = 
		function() {
 		return onlyAlphaNumeric(event);
  	};
 	document.getElementById('brand_other').onchange = 
		function() {
		return searchMakeOther();
  	}; 
  	
	document.getElementById('model_name').onchange = 
		function() {
		return fn_make_other();
  	}; 
  	
	document.getElementById('asset_type').onchange = 
		function() {
		return fn_makeName();
  	}; 
  	
	document.getElementById('make_name').onchange = 
		function() {
		return fn_modelName(),fn_brand_other();
  	}; 
  	

  	
  	
	document.getElementById('section').onkeypress = 
		function() {
		return onlyAlphaNumeric();
  	}; 
  	
	document.getElementById('s_state').onchange = 
		function() {
		return serviceStChange();
  	};
	document.getElementById('s_state').onchange = 
		function() {
		return serviceStChange();
  	};
  	
	document.getElementById('unserviceable_state').onchange = 
		
		function() {
	
		return selectBer();
  	}; 
  	
  	
  	
	document.getElementById('b_head').onchange = 
		function() {
		return fn_B_Head();
  	}; 
  	
  	document.getElementById('b_cost').onchange = 
		function() {
		return isNumber(event);
  	}; 
	
});




$(document).ready(function() {
	 var screenUrl = '${screen_url}';
	document.getElementById('screenUrl').value;
	
	$("#file_show").hide();
var susNoAuto = $("#supplier");
susNoAuto.autocomplete({
    source: function(request, response) {
        $.ajax({
            type: 'POST',
            url: "getSupplierNameList?" + key + "=" + value,
            data: {
                loginname: request.term 
            },
            success: function(data) {
                var susval = [];
                var length = data.length - 1;
                var enc = data[length].substring(0, 16);
                for (var i = 0; i < length; i++) { 
                    var supplierData = data[i];
                    var supplierName = dec(enc, supplierData.supplier); 
                    susval.push({
                        label: supplierName, // This is what will be displayed in the dropdown
                        value: supplierData.id // This is the value that will be set in the input field
                    });
                }
                
                response(susval); 
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error: ", status, error);
            }
        });
    },
    minLength: 1,
    select: function(event, ui) {
        $("#supplier").val(ui.item.label); 
        $("#supplierId").val(ui.item.value); 
        var id = ui.item.value;
        getSupplierData(id); 
       
        return false;
    },
    change: function(event, ui) {
        if (!ui.item) {
            alert("Please Enter Supplier Name.");
            susNoAuto.val("");
            susNoAuto.focus();
        }
    }
});
    function getSupplierData(id){
    console.log("Fetching supplier data for ID: ", id);
    $.ajax({
        type: 'POST',
        url: "getActiveSupplierDataList?"+ key + "=" + value, 
        data: {
        	id:id
        }, 
        success: function(data) {
            
            console.log(data);
      if (data.length > 0) {
                 $('#contact_number').val(data[0].contact_number);
                 $('#supplier').val(data[0].supplier);

            }
            
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error: ", status, error);
        }
    });
}
	var id=$('#id').val();
	if('${MainAssetsCmd.id}' == 0)
		{
		$('#save').show();
		$('#update').hide();
		$('#save_btn').show();
		$('#clear_1').hide();
		$('#clear_btn').show();
		
		$('#update_btn').hide();
		
	
		}
	else{
		
		$('#save').hide();
		$('#update').show();
		$('#clear_1').show();
		$('#save_btn').hide();
		$('#update_btn').show();
		$('#clear_btn').hide();
	}

	$.ajaxSetup({
		async : false
});
	$("#mac_address1").tooltip();
	$("#ip_address1").tooltip();
	$("#b_cost").tooltip();
	$("#mac_address1").css('cursor','pointer');
	$("#ip_address1").css('cursor','pointer');
	$("#b_cost").css('cursor','pointer');

	warrenty_show();
	datepicketDate('warranty');
	datepicketDate('proc_date');
	datepicketDate('unsv_from_dt1');
	datepicketDate('antivirus_expry1');
	
	$( "#warranty" ).datepicker( "option", "maxDate", null);
	
	if('${MainAssetsCmd.id}' != "0"){
		$("#countDiv").hide();
		$("#machine_number1").val('${MainAssetsCmd.machine_number}');
		$("#mac_address1").val('${MainAssetsCmd.mac_address}');
		$("#ip_address1").val('${MainAssetsCmd.ip_address}');
		$("#crv_no").val('${MainAssetsCmd.crv_no}');
		$("#gem_no1").val('${MainAssetsCmd.gem_no}');

		
		
		$("#supplierId").val('${MainAssetsCmd.supplier_name}');
		getSupplierData('${MainAssetsCmd.supplier_name}');
		 var filename = '${MainAssetsCmd.u_file}';
		    var index = filename.indexOf('/srv/IT/');

    if (filename) {
        var fileName2 = filename.substring(filename.lastIndexOf('/') + 1); 
        $("#uploadedFileName").text(fileName2); 
        $('#download_i').removeClass('display_none');
        $('#u_file1').addClass('display_none');
    }
		
    
    
	 var filename5 = '${MainAssetsCmd.br_certificate}';
	    var index = filename.indexOf('/srv/IT/');

if (filename5) {
 var fileName6 = filename.substring(filename.lastIndexOf('/') + 1); 
 $("#uploadedFileName1").text(fileName6); 
 $('#download_i1').removeClass('display_none');
 $('#br_certificate1').addClass('display_none');
}
	
	$("#warranty").val((ParseDateColumncommission('${MainAssetsCmd.warranty}')));	
	$("input[name='warrantycheck'][value='${MainAssetsCmd.warrantycheck}']").prop("checked",true);
	$("#proc_date").val((ParseDateColumncommission('${MainAssetsCmd.proc_date}')));	

	if ('${MainAssetsCmd.s_state}' == "" || '${MainAssetsCmd.s_state}' == null || '${MainAssetsCmd.s_state}' == "0"){
		$("select#s_state").val(0);
	} else {
		$("select#s_state").val('${MainAssetsCmd.s_state}');
	}
	
	$("select#unserviceable_state").val(('${MainAssetsCmd.unserviceable_state}'));
	$("#unsv_from_dt1").val((ParseDateColumncommission('${MainAssetsCmd.unsv_from_dt}')));
	$("input[name='antiviruscheck'][value='${MainAssetsCmd.antiviruscheck}']").prop("checked",true);

	var checkssd='${MainAssetsCmd.ssd_capi}';
	if(checkssd!='' && checkssd!="0")
		{
		$("input[name='ssdcheck'][value='Yes']").prop("checked",true);
		$("#divssdcap").show();
		}
	else{
		$("input[name='ssdcheck'][value='No']").prop("checked",true);
	}
debugger;
	
	$("select#maintainAgency").val(('${MainAssetsCmd.maintainagency}'));
	anti_show();
	
	var antiviruscheck='${MainAssetsCmd.antiviruscheck}';
	if(antiviruscheck=="Yes")
		{
		$("#antivirus_expry1").val((ParseDateColumncommission('${MainAssetsCmd.antivirus_expry}')));	
		}
	
	
	$("#b_cost").val('${MainAssetsCmd.b_cost}');	
	$("input[name='cd_drive'][value='${MainAssetsCmd.cd_drive}']").prop("checked",true);
	fn_makeName();	
	$("select#make_name").val('${MainAssetsCmd.make_name}');
	fn_brand_other();
	$("#brand_other").val('${make_mstr_other}');	
	fn_modelName();
	$("select#model_name").val('${MainAssetsCmd.model_name}');	
	fn_make_other();
	$("#model_other").val('${model_mstr_other}');	
	fn_pro_type_other();
	$("#pro_type_other").val('${pro_type_other}');	
	fn_os_other();
	$("#os_other").val('${os_mstr_other}');	
	fn_office_other();
	$("#office_other").val('${office_mstr_other}');	
	serviceStChange();
	$("select#operating_system").on("change", function() {
		if($("select#operating_system").val()==0){
			$("#newLabel").hide();
		}
		else{
		$("#newLabel").show();
		}
	});
	selectBer();
	$("#make_mstr_id").val('${make_mstr_id}');	
	$("#model_mstr_id").val('${model_mstr_id}');	
	$("#pro_type_id").val('${pro_type_id}');	
	$("#office_mstr_id").val('${office_mstr_id}');	
	$("#os_mstr_id").val('${os_mstr_id}');
	}
	$("select#dply_envt").on("change", function() {
		if($("select#dply_envt").val()==7){
			$("label#ipDisplay").show();
				$("label#nonIpDisplay").hide();

		}
		else if($("select#dply_envt").val()==9){
			$("label#ipDisplay").show();
				$("label#nonIpDisplay").hide();			
		} else{
			$("label#ipDisplay").hide();
			$("label#nonIpDisplay").show();
		}
	});
});

function ValidateIPaddress(ipaddress) { 
	var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)
	if (ip.test(ipaddress.value)) {  
		return true;
	}  
	else{
		alert("You have entered an invalid IP Address!")  
		$("#ip_address1").focus();
		$("#ip_address1").val("");
		return false;
	}
	
}

function makeMacAddress(obj){
	if(obj.value!=''){
	var val=obj.value.split('-').join('');
	var v = val.match(/.{1,2}/g).join("-");
	obj.value=v;
	}
}

function Validate(){

	if($("#section").val()==0 || $("#section").val()==""){
		alert("Please Select Section");
		$("#section").focus();
		return false;
	}
	if($("#asset_type").val()==0 || $("#asset_type").val()==""){
		alert("Please Select Computing Assets Type ");
		$("#asset_type").focus();
		return false;
	}
	
	if($("#make_name").val()==0 || $("#make_name").val()==""){
		alert("Please Select Make/Brand Name ");
		$("#make_name").focus();
		return false;
	}
	
	var text = $("#make_name option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
		if( $("#brand_other").val().trim() == ""){
			alert("Please Enter Make Name Other. ");
			$("#brand_other").focus();
			return false;
		}
	}

	if($("#model_name").val()==0 || $("#model_name").val()==""){
		alert("Please Select Model Name ");
		$("#model_name").focus();
		return false;
	}
	
	var text = $("#model_name option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
		if( $("#model_other").val().trim() == ""){
			alert("Please Enter Model Name Other. ");
			$("#model_other").focus();
			return false;
		}
	}
	
	if($("#processor_type").val()==0 || $("#processor_type").val()==""){
		alert("Please Select Processor Type ");
		$("#processor_type").focus();
		return false;
	}

	var text = $("#processor_type option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
		if( $("#pro_type_other").val().trim() == ""){
			alert("Please Enter Processor Type Other. ");
			$("#pro_type_other").focus();
			return false;
		}
	}
	
	if($("#ram_capi").val()==0 || $("#ram_capi").val()==""){
		alert("Please Select RAM Capacity  ");
		$("#ram_capi").focus();
		return false;
	}

	if($("#hdd_capi").val()==0 || $("#hdd_capi").val()==""){
		alert("Please Select HDD Capacity.");
		$("#hdd_capi").focus();
		return false;
	}
	
// 	if (!$("input[name='ssdcheck"+i+"']").is(':checked')) {
// 		   alert('Please Check SSD Installed  ');
// 		   return false;
// 		} 
 if (!$("input[name='ssdcheck']:checked").val()) {
            alert('Please Check SSD Installed');
            return false; // Prevent further actions
        }
	
	var ssdcheck=$('input[name="ssdcheck"]:checked').val();
	if(ssdcheck.toUpperCase()=="YES"){
		if($("#ssd_capi").val()==0 || $("#ssd_capi").val()==""){
			alert("Please Enter SSD Capacity.");
			$("#ssd_capi").focus();
			return false;
		}
	}
	else{
		$("#ssd_capi").val('0');
	}
	
	if($("#operating_system").val()==0 || $("#operating_system").val()==""){
		alert("Please Select Operating System.");
		$("#operating_system").focus();
		return false;
	}
	var text = $("#operating_system option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#os_other").val().trim() == ""){
			alert("Please Enter Operating System Other.");
			$("#os_other").focus();
			return false;
		}
	}
	
	
	var text = $("#office option:selected").text();
	if(text.toUpperCase() == "OTHERS"){
		if( $("#office_other").val().trim() == ""){
			alert("Please Enter Office Other.");
			$("#office_other").focus();
			return false;
		}
	}

	var antivirusChecked=$('input[name="antiviruscheck"]:checked').val();
	
	if(antivirusChecked.toUpperCase()=="YES"){
		
		if($("#antivirus").val()==0 || $("#antivirus").val()==""){
			alert("Please Select AntiVirus.");
			$("#antivirus").focus();
			return false;
		}
		
		if($("#antivirus_expry1").val()=="" || $("#antivirus_expry1").val()=="DD/MM/YYYY"){
			alert("Please Enter Antivirus Expiry Date.");
			$("#antivirus_expry1").focus();
			return false;
		}
	}
	else{
		$("#antivirus").val('0');
	}
	
	if($("#dply_envt").val()==0 || $("#dply_envt").val()==""){
		alert("Please Select Dply Envt as Applicable  ");
		$("#dply_envt").focus();
		return false;
	}

	var warrantycheckChecked=$('input[name="warrantycheck"]:checked').val();
		
	if(warrantycheckChecked.toUpperCase()=="YES"){
		if($("#warranty").val()==0 || $("#warranty").val()=="" || $("#warranty").val() == "DD/MM/YYYY"){
			alert("Please Select Warranty Upto ");
			$("#warranty").focus();
			return false;
		}
	}
	else{
		$("#warranty").val('');
	}
	
	if($("select#dply_envt").val()==7 ||$("select#dply_envt").val()==9 ){
		if( $("#ip_address1").val()==""){
		
			alert("Please Select IP Address ")  
			$("#ip_address1").focus();
			return false;
		
		}
		
	}
		
	
	if (!$("input[name='cd_drive']").is(':checked')) {
		alert('Please Check CD Drive!');
	}
	
	var len=$("#assetcount").val();
	var i=1;
	for(i;i<=len;i++) {
		if ($("#machine_number"+i).val().trim() == ""){
			alert("Please Enter Machine No. ");
			$("#machine_number"+i).focus();
			return false;
		}
		
		function validateCodeMac() {
			var  testString = $("input#mac_address"+i).val();
				if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
				}
				else{
					alert("Please enter letters and numbers only.");
				}
		}
		
		for(j=i+1;j<=len;j++){
			if( $("#machine_number"+i).val() == $("#machine_number"+j).val()){
				alert("You have Entered Duplicate Machine Number!")  
				$("#machine_number"+j).focus();
				return false;
			}
		}
	}
	
	if( $("#b_cost").val()=="0" ){
		alert("Proc Cost Must be Greater Than Zero");
		$("#b_cost").focus();
		return false;
	} else if ($("#b_cost").val() > 1000000000) {
		alert("Proc Cost cannot be greater Than 100 Crores");
		$("#b_cost").focus();
		return false;
	}

	if( $("#s_state").val()=="" || $("#s_state").val()=="0"){
		alert("Please Select Serviceable State");
		$("#s_state").focus();
		return false;
	}
	
	if($("#s_state").val()==2){
		if( $("#unserviceable_state").val()=="" || $("#unserviceable_state").val()=="0"){
			alert("Please Select Repair State");
			$("#unserviceable_state").focus();
			return false;
		}
		if($("#unsv_from_dt1").val()==0 || $("#unsv_from_dt1").val()=="" || $("#unsv_from_dt1").val() == "DD/MM/YYYY"){
			alert("Please Select UN-Serviceable From Date");
			$("#unsv_from_dt1").focus();
			return false;
		}
		if( $("#maintainAgency").val()=="" || $("#maintainAgency").val()=="0"){
			alert("Please Select Maintenance Agency");
			$("#maintainAgency").focus();
			return false;
		}
		
	}
	if($("#s_state").val()==2){
		if($("#unserviceable_state").val()==2){
			if($("#br_certificate1_id").val()==""){
			if($("#br_certificate1").val()=="")
			{
			alert("Please Upload BR Certificate.");
			$("#br_certificate1").focus();
			return false;
			}
			var fileInput = $("#br_certificate1");

			
			
			var fileName = fileInput.val();

			// Check if the file is a PDF
			if (!fileName.endsWith(".pdf")) {
			    alert("Please upload a valid BR Certificate In PDF document.");
			    fileInput.focus();
			    return false;
			}
		}
		}
		
	}
	
	if( $("#b_head").val()=="0"){
		alert("Please Select Budget Code");
		$("#b_head").focus();
		return false;
	}

// 	if( $("#b_head").val()=="0"){
// 		alert("Please Select Budget Head");
// 		$("#b_head").focus();
// 		return false;
// 	}
	if($("#crv_no").val()==0 || $("#crv_no").val()==""){
		alert("Please Enter CRV No.");
		$("#crv_no").focus();
		return false;
	}

	
	if($("#proc_date").val()==0 || $("#proc_date").val()=="" || $("#proc_date").val() == "DD/MM/YYYY"){
		alert("Please enter CRV date");
		$("#proc_date").focus();
		return false;
	}
	if( $("#proc_date").val() == "" || $("#proc_date").val() == "DD/MM/YYYY"){
		alert("Please Select CRV Date");
		$("#proc_date").focus();
		return false;
	}
	if('${MainAssetsCmd.id}' == 0)
	{
		var fileInput = $("#u_file1");

		if (fileInput.val() == "") {
		    alert("Please Upload CRV Document.");
		    fileInput.focus();
		    return false;
		}

		
		var fileName = fileInput.val();

		
		if (!fileName.endsWith(".pdf")) {
		    alert("Please upload a valid PDF document.");
		    fileInput.focus();
		    return false;
		}

	}
	    if ($("#supplier").val() === "") {
	        alert("Please Enter Supplier Name");
	        $("#supplier").focus();
	        return false;
	    }

}

function divN(obj){
	var id = obj.id;
	var sib_id = $("#"+id).parent().parent().next().attr('id');
	var hasC=$("#"+sib_id).hasClass("show");
	$(".panel-collapse").removeClass("show");
	$('.droparrow').each(function(i, obj) {
		$("#"+obj.id).attr("class", "droparrow collapsed");
		});
	
	if (hasC) {	
	$("#"+id).addClass( " collapsed");		 
	} else {				
		$("#"+sib_id).addClass("show");	
		$("#"+id).removeClass("collapsed");
	}
}

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

function serviceStChange() {
	var a =$("select#s_state").val();
	if(a == '2') {
		$("#un_show").show();
		$("#unsv_div").show();
		$("#maintaince_div").show();
		
	} else {
		$("#un_show").hide();
		$("#unsv_div").hide();
		$("#file_show").hide();
		$("#maintaince_div").hide();
		$('#unserviceable_state').val('0');
	}
}

function anti_show() {
	if ($("#antiviruscheck1").prop("checked")) {
		$("#AntiVirusDiv").show();
		$("#antivirus_date").show();
		
	} else{
		$('#AntiVirusDiv').hide();
		$("#antivirus_date").hide();
 		$("#antivirus_expry1").val("");
		//$("#antivirus_expry1").val("").prop("defaultValue", ""); 
		$('#antivirus').val('0');
	}
	
}
// function supplier_show() {


//     if ($("#approveois2").prop("checked")) {
//         $("#supplier_field").show(); // Show the element
//     } else {
//         $("#supplier_field").hide(); // Hide the element
//     }
// }
function ssd_show() {
	if ($("#ssdcheck1").prop("checked")) {
		$("#divssdcap").show();
	} else{
		$('#divssdcap').hide();
		$('#ssd_capi').val('0');
	}
	
}

function fn_B_Head() {
	var b_head = $("select#b_head").val();
	$.post("getBudgetCodeList?" + key + "=" + value, {
		b_head: b_head
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#b_code").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function counthover(){
	Assetcount = $("#assetcount").val();
	if (Assetcount=="" ) {
		$("#assetcount").val("1");
	}
}

var Assetcount = 1;
function addMultiAsset() {
		Assetcount = $("#assetcount").val();
		if (Assetcount=="0" ) {
			$("#assetcount").val("1");
			Assetcount = 1;
		}
		if (Assetcount=="" ) {
			Assetcount = 1;
		}
		
		var options="";
		for(var i = 1; i <= parseInt(Assetcount); i++) {
			options += '<div class="row" style="border: solid #b7b6b0 5px;padding: 10px;margin: 5px;">'+
			'<input type="text" id="ch_id'+i+'" name="ch_id'+i+'" class="form-control autocomplete" autocomplete="off" style="display: none;"></input>'+
			'<div class="row"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
				'<label class=" form-control-label"><strong style="color: red;"> </strong>Model Number</label>	</div>'+
			'<div class="col-md-8">'+
// 			'<input type="text" id="model_number'+i+'" name="model_number'+i+'" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphaNumeric(event, this)" maxlength="20"></input>'+
			'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
			'	<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>'+
			'</div><div class="col-md-8">'+
			'	<input type="text" class="form-control autocomplete" id="machine_number'+i+'" name="machine_number'+i+'" maxlength="50" oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphaNumeric(event, this)" maxlength="20"></input>'+
			'</div></div></div></div><div class="row"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
			'	<label class=" form-control-label"><strong style="color: red;"></strong>MAC Address</label>'+
			'</div><div class="col-md-8">'+
			'	<input title="Enter MAC Address in given format  8C-EC-4B-48-01-00. Type getmac in command prompt and press Enter to get MAC Address." type="text" id="mac_address'+i+'" name="mac_address'+i+'"  onchange="return validateCodeMac();" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" onkeyup="makeMacAddress(this);" onkeypress="makeMacAddress(this);return /[0-9a-fA-F]/i.test(event.key);return onlyAlphaNumeric(event, this);" maxlength="17" autocomplete="off"></input>'+
			'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
			'	<label class=" form-control-label"><strong style="color: red;"> </strong>IP Address </label>'+
			'</div><div class="col-md-8">'+
			'	<input title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address." type="text" id="ip_address'+i+'" name="ip_address'+i+'" maxlength="15" onkeypress="return /[.0-9]/i.test(event.key);" class="form-control autocomplete" autocomplete="off"></input>'+
			'</div>'+
			'<div class="col-md-8">'
			'<input type="text" id="gem_no'+i+'" name="gem_no'+i+'" maxlength="12" class="form-control" autocomplete="off"></input></div>'
			'</div></div></div></div>';

		}
		$("#multiAssetDiv").html(options);
	} 

	function warrenty_show() {

		if ($("#Warranty1").prop("checked")) {
			$("#WarrantyDiv").show();
		} else{
			$('#WarrantyDiv').hide();
			$('#warranty').val('DD/MM/YYYY');
		}
	}


function onlyAlphaNumeric(e, t) {
	return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || e.charCode == 32;   
}

function isNumber(evt) {
	evt = (evt) ? evt : window.event;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	}
	return true;
}
function searchMachineNumber(){
	var machine_number = $("#machine_number1").val();
	$.post("getAllMachineNumberComputing?" + key + "=" + value, {
		machine_number: machine_number
	}).done(function(j) {
		if(j.length > 0){
			
			alert("This Machine No already Exist.");
			$("#machine_number1").val("");
		} 		 
	}).fail(function(xhr, textStatus, errorThrown) {}); 
}
	
	function fn_brand_other() {
		var text = $("#make_name option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#brand_other_hid").show();
			}
			else{
				$("#brand_other_hid").hide();
				$("#brand_other").val('');
			}
		}
	
	function fn_make_other() {
		var text = $("#model_name option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#model_other_hid").show();
			}
			else{
				$("#model_other_hid").hide();
				$("#model_other").val('');
			}
		}
	function fn_pro_type_other() {
		var text = $("#processor_type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#pro_type_other_hid").show();
			}
			else{
				$("#pro_type_other_hid").hide();
				$("#pro_type_other").val('');
			}
		}
	function fn_os_other() {
		var text = $("#operating_system option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#os_other_hid").show();
			}
			else{
				$("#os_other_hid").hide();
				$("#os_other").val('');
			}
		}
	function fn_office_other() {
		var text = $("#office option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#office_other_hid").show();
			}
			else{
				$("#office_other_hid").hide();
				$("#office_other").val('');
			}
		}
	

	
	function validateCodeMac() {
		var  testString = $("input#mac_address1").val();
		if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
		} else{
			$("#mac_address1").val("");
			alert("Please Enter A Valid MAC Address");
		}
	}
	
	
		function searchMakeOther(){
		
			var asset_type = $("#asset_type").val();
			var brand_other = $("#brand_other").val();
			$.post("getBrandOtherList?" + key + "=" + value, {
				asset_type:asset_type,brand_other: brand_other
			}).done(function(j) {
							
				if(j.length > 0){
					
					alert("This Make Name Already Exists.");
					$("#brand_other").val("");
				} 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		
		function searchModelOther(){

			var asset_type = $("#asset_type").val();
			var model_other = $("#model_other").val();
			$.post("getModelOtherList?" + key + "=" + value, {
				asset_type:asset_type,model_other: model_other
			}).done(function(j) {
							
				if(j.length > 0){
					
					alert("This Model Name Already Exists.");
					$("#model_other").val("");
				} 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		function searchProcessorTypeOther(){
		
			var pro_type_other = $("#pro_type_other").val();
			$.post("getProcessorTypeOtherList?" + key + "=" + value, {
				pro_type_other:pro_type_other
			}).done(function(j) {
							
				if(j.length > 0){
					
					alert("This ProcessorType Already Exists.");
					$("#pro_type_other").val("");
				} 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
	
		
		function searchOperatingSystemTypeOther(){
			
			var os_other = $("#os_other").val();
			$.post("getOperatingSystemOtherList?" + key + "=" + value, {
				os_other:os_other
			}).done(function(j) {
							
				if(j.length > 0){
					
					alert("This OperatingSystem Already Exists.");
					$("#os_other").val("");
				} 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
	function searchOfficeTypeOther(){
	
			var office_other = $("#office_other").val();
			$.post("getOfficeOtherList?" + key + "=" + value, {
				office_other:office_other
			}).done(function(j) {
							
				if(j.length > 0){
					
					alert("This Office Already Exists.");
					$("#office_other").val("");
				} 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
	function selectBer()
	{
		
	
		var a =$("select#unserviceable_state").val();
		var b =$("select#s_state").val();
		//var c =$("select#s_state").val();
		if(a == '2' && b == '2')
			{
			
				$("#file_show").show();
			}
		else
			{
			$("#file_show").hide();
			}
	}
	
	function download_file() {
		
			var id = $("#id").val(); 
			
			var pdfView="BERFileDownloadDemo?id="+id;

				
				fileName="TEST_DOC";
				fileURL=pdfView;
				if (!window.ActiveXObject) {
					var save = document.createElement('a');
					save.href = fileURL;
					save.target = '_blank';
					var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
					save.download = fileName || filename;
					if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
							document.location = save.href; 
						}else{
							var evt = new MouseEvent('click', {
								'view': window,
								'bubbles': true,
								'cancelable': false
							});
							save.dispatchEvent(evt);
							(window.URL || window.webkitURL).revokeObjectURL(save.href);
						}	
				}
				else if ( !! window.ActiveXObject && document.execCommand)     {
					var _window = window.open(fileURL, '_blank');
					_window.document.close();
					_window.document.execCommand('SaveAs', true, fileName || fileURL)
					_window.close();
				}

			
			
			location.reload();
		}
	
	
	
	 $("input#user_name").keypress(function(){
			var loginname = this.value;
				 var susNoAuto=$("#user_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getActiveUsernameList?"+key+"="+value,
				        data: {loginname:loginname},
				          success: function( data ) {
				        	 var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
					        	for(var i = 0;i<data.length;i++){
					        		susval.push(dec(enc,data[i]));
					        	}
					        	   	          
							response( susval ); 
				          }
				        });
				      },
				      minLength: 1,
				      autoFill: true,
				      change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Enter Active UserName.");
				        	  document.getElementById("user_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				      } 	     
				}); 			
		});


 </script>

