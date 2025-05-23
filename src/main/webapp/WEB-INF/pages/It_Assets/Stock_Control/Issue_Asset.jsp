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


<style nonce="${cspNonce}">
.mb {
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
	margin-top: 10px;
}

.col-md-12.pad {
	margin-bottom: -26px;
}

.black {
	color: white;
	/* background-color: white; */
}
</style>
<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="Formname" id="Formid"
	action="type_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="type_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>ISSUE STOCK</h5>
				<div class="row" align="center">
					<div class="col-md-12 pad">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<button class="nav-link active font_weight_bold black"
										id="home-tab" data-bs-toggle="tab" data-bs-target="#home"
										type="button" role="tab" aria-controls="home"
										aria-selected="true">COMPUTING</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link font_weight_bold black"
										id="profile-tab" data-bs-toggle="tab"
										data-bs-target="#profile" type="button" role="tab"
										aria-controls="profile" aria-selected="false">PERIPHERAL</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link font_weight_bold black"
										id="consumable-tab" data-bs-toggle="tab"
										data-bs-target="#consumable" type="button" role="tab"
										aria-controls="consumable" aria-selected="false">CONSUMABLE</button>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body card-block cue_text" id="COMPUTING">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Issue To Section </label>
							</div>
							<div class="col-md-8">
								<!-- 							<input type="text" id="Section_comp" name="Section_comp" -->
								<!-- 								class="form-control autocomplete" autocomplete="off"> -->
								<select id="Section_comp" name="Section_comp"
									class="form-control">
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
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>To Section Username</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="user_name" name="user_name"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>


				<div class="row">

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Redg. No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="redg_no" name="redg_no"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="card-body card-block cue_text display_none"
				id="COMPUTING_VIEW"></div>

			<div class="card-body card-block cue_text display_none"
				id="PERIPHERAL">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Issue To Section</label>
							</div>
							<div class="col-md-8">

								<select id="Section_peri" name="Section_peri"
									class="form-control">
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
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>To Section Username</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="user_name_peri" name="user_name_peri"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div class="row">

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Redg. No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="redg_no_section_peri"
									name="redg_no_section_peri" class="form-control autocomplete"
									autocomplete="off">
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="card-body card-block cue_text display_none"
				id="PERIPHERAL_VIEW"></div>


			<div class="card-body card-block cue_text display_none"
				id="CONSUMABLE">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Issue To Section</label>
							</div>
							<div class="col-md-8">

								<select id="Section_consum" name="Section_consum"
									class="form-control">
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
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>To Section Username</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="user_name_consum" name="user_name_consum"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>
				<div class="row">

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label "><strong
									class="color_red">* </strong>Redg. No</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="redg_no_section_consum"
									name="redg_no_section_consum" class="form-control autocomplete"
									autocomplete="off">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body card-block cue_text display_none"
				id="CONSUMABLE_VIEW"></div>

			<div class='card-footer' align='center'>
				<a href=Issue_Asset_Url class="btn btn-success btn-sm">Clear</a> <input
					type='submit' class='btn btn-primary btn-sm'
					value='ISSUE TO SECTION' id="save_btn" />

			</div>







			<!-- for computing -->


			<div class="card-body card-block display_none" id="blockdata">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong> Section </label>
							</div>
							<div class="col-md-8">
								<select id="section" name="section" class="form-control"
									readonly>
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
									class="color_red"></strong><b>Admin</b> </label>
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
								<form:select path="" id="asset_type" class="form-control"
									readonly="readonly">
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
								<form:select path="" id="make_name" name="make_name"
									class="form-control" readonly="readonly">
									<c:forEach var="item" items="${MakeList}" varStatus="num">
										<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
									</c:forEach>
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
									maxlength="50" readonly>
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
								<form:select path="" id="model_name" class="form-control"
									readonly="readonly">
									<form:option value="0">--Select--</form:option>
									<c:forEach var="item" items="${ModelList}" varStatus="num">
										<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
									</c:forEach>
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
									maxlength="50" readonly>
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
								<form:select path="" id="processor_type" class="form-control"
									readonly="readonly">
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
									class="form-control autocomplete" maxlength="50" readonly>
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
								<form:select path="" id="ram_capi" class="form-control"
									readonly="readonly">
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
								<form:select path="" id="hdd_capi" class="form-control"
									readonly="readonly">
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
								<input type="radio" id="ssdcheck1" name="ssdcheck" value="Yes"
									readonly></input>&nbsp <label for="ssdcheck1">Yes</label>&nbsp
								<input type="radio" id="ssdcheck2" name="ssdcheck" value="No"></input>&nbsp
								<label for="ssdcheck2" readonly>No</label>
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
								<form:select path="" id="ssd_capi" class="form-control"
									readonly="readonly">
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
								<form:select path="" id="operating_system" class="form-control"
									readonly="readonly">
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
									maxlength="50" readonly>
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
								<form:select path="" id="office" class="form-control"
									readonly="readonly">
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
									class="form-control autocomplete" maxlength="50" readonly>
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
								<form:radiobutton id="antiviruscheck1" value="Yes" path=""
									readonly="readonly"></form:radiobutton>
								&nbsp <label for="antiviruscheck1">Yes</label>&nbsp
								<form:radiobutton id="antiviruscheck2" path="" value="No"
									checked="" readonly="readonly"></form:radiobutton>
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
								<form:select path="" id="antivirus" class="form-control"
									readonly="readonly">
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
								<input type="text" name="antivirus_expry1" id="antivirus_expry1"
									maxlength="10" class="form-control width88 display_inline rgb0"
									aria-required="true" autocomplete="off" readonly />
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
								<form:select path="" id="dply_envt" class="form-control"
									readonly="readonly">
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
									<form:radiobutton id="scan1" value="Yes" path=""
										readonly="readonly"></form:radiobutton>
									&nbsp <label for="scan1">Yes</label>&nbsp
									<form:radiobutton id="scan2" path="" value="No" checked=""
										readonly="readonly"></form:radiobutton>
									&nbsp <label for="scan2">No</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Warranty</label>
							</div>
							<div class="col-md-8" id="warrantyid">
								<form:radiobutton id="Warranty1" value="Yes" path="" checked=""
									readonly="readonly"></form:radiobutton>
								&nbsp <label for="Warranty1">Yes</label>&nbsp
								<form:radiobutton id="Warranty2" path="" value="No"
									readonly="readonly"></form:radiobutton>
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
									maxlength="10" class="form-control width88 display_inline rgb0"
									aria-required="true" autocomplete="off" readonly>
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
									class="form-control autocomplete" autocomplete="off" readonly></input>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>MAC Address</label>
							</div>
							<div class="col-md-8">
								<input
									title="Enter MAC Address in given format  8C-EC-4B-48-01-00. Type getmac in command prompt and press Enter to get MAC Address."
									oninput="this.value = this.value.toUpperCase()" type="text"
									id="mac_address1" name="mac_address1"
									class="form-control autocomplete" maxlength="17"
									autocomplete="off" readonly></input>
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
								<input type="text" id="machine_number1" name="machine_number1"
									class="form-control autocomplete machineno_class"
									maxlength="30" autocomplete="off"
									class="form-control autocomplete" readonly>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label id="nonIpDisplay" class=" form-control-label"><strong
									class="color_red"> </strong>IP Address </label> <label id="ipDisplay"
									class=" form-control-label display_none"><strong
									class="color_red">* </strong>IP Address </label>
							</div>
							<div class="col-md-8">
								<input
									title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address."
									type="text" id="ip_address1" name="ip_address1" maxlength="15"
									class="form-control autocomplete" autocomplete="off" readonly></input>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Serviceable State</label>
								</div>
								<div class="col-md-8">
									<form:select path="" id="s_state" class="form-control"
										readonly="readonly">
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
									<form:select path="" id="unserviceable_state"
										class="form-control" readonly="readonly">
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
									<label class=" form-control-label" id="titleid"><strong
										class="color_red">* </strong>Upload BR Certificate</label>
								</div>
								<div class="col-md-8">
									<input type="file" id="br_certificate1" name="br_certificate1"
										class="display_none" /> <i id="download_i1"
										class="fa fa-download display_none" readonly><span
										id="uploadedFileName1" class="uploaded-file-name f_10"
										readonly></span><input type="button"
										class="btn btn-success btn-sm display_none" id="downloadbtn"
										value="File" readonly /></i>
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
										aria-required="true" autocomplete="off" readonly>
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
									class="form-control" readonly>
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getMaintainceAgencyList}">
										<option value="${item[0]}">${item[1]}</option>
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
								<label class=" form-control-label"><strong
									class="color_red"> </strong>Proc Cost (&#8377) </label>
							</div>
							<div class="col-md-8">
								<input title="Approx. Procurement Cost" type="text" id="b_cost"
									name="b_cost" class="form-control autocomplete"
									autocomplete="off" readonly />
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
								<form:select path="" id="b_head" class="form-control"
									readonly="readonly">
									<form:option value="0">--Select--</form:option>
									<c:forEach var="item" items="${getBudgetHeadList}"
										varStatus="num">
										<form:option value="${item[1]}" name="${item[1]}">${item[1]}</form:option>
									</c:forEach>
								</form:select>
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
									class="form-control autocomplete" autocomplete="off" readonly>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">*</strong>Crv Date</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="proc_dt" id="proc_date" maxlength="10"
										class="form-control width88 display_inline rgb0"
										aria-required="true" autocomplete="off" readonly>
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
									<i id="download_i" class="fa fa-download display_none"> <span
										id="uploadedFileName" class="uploaded-file-name f_10"></span>
										<input type="button"
										class="btn btn-success btn-sm display_none" id="downloadbtn"
										value="Download File" readonly />
									</i>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label">GeM Contact No.</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="gem_no1" name="gem_no1" maxlength="12"
										class="form-control" autocomplete="off" readonly></input>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">*</strong>Approved By OIS </label>
								</div>
								<div class="col-md-8" id="approveoisid">
									<form:radiobutton id="approveois1" value="Yes" path=""
										readonly="readonly"></form:radiobutton>
									&nbsp <label for="approveois1">Yes</label>&nbsp
									<form:radiobutton id="approveois2" path="" value="No"
										readonly="readonly"></form:radiobutton>
									&nbsp <label for="approveois2">No</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row display_none" id="supplier_field">
						<div class='col-md-6'>
							<div class='row form-group'>
								<div class="col-md-4">
									<strong class="color_red">*</strong> <label for="text-input"
										class="form-control-label">Supplier</label>
								</div>
								<div class='col-md-8'>
									<input type="text" id="supplier" name="supplier"
										class="form-control autocomplete" autocomplete='off'
										maxlength="100" readonly></input>
								</div>
							</div>
						</div>
						<div class='col-md-6'>
							<div class='row form-group'>
								<div class='col-md-4'>
									<strong class="color_red">*</strong> <label for="text-input"
										class="form-control-label">Supplier Contact No:</label>
								</div>
								<div class='col-md-8'>
									<input type="text" id="contact_number" name="contact_number"
										class="form-control autocomplete" autocomplete='off'
										maxlength="10" readonly></input>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 	for peripheral -->

			<div class="card-body card-block display_none"
				id="blockdata_peripheral">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong> Section </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" class="display_none"><b>
										${roleSusNo} </b></label> <select id="section_p" name="section"
									class="form-control" readonly="readonly">

									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num">
										<option value="${item.id}">${item.section}</option>
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
									class="color_red"></strong><b>Admin</b> </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Peripheral Type </label>
							</div>
							<div class="col-md-8">
								<form:select path="" id="assets_type_p" class="form-control"
									name="assets_type" readonly="readonly">
									<form:option value="0">--Select--</form:option>
									<c:forEach var="item" items="${getPeripheral}" varStatus="num">
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
									class="color_red">* </strong>Type of Peripheral HW</label>
							</div>
							<div class="col-md-8">
								<form:select path="" id="type_of_hw_p" name="type_of_hw"
									class="form-control" readonly="readonly">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${hardwareListDDL}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 display_none" id="peri_hw_hid">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Type of Peripheral HW Other </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="peri_hw_other" name="peri_hw_other"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Make/Brand Name</label>
							</div>
							<div class="col-md-8">
								<label id="lblmake_name"></label>
								<form:select path="" id="make_name_p" class="form-control"
									readonly="readonly">
									<c:forEach var="item" items="${MakeList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none" id="brand_other_hid">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Make/Brand Other </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="brand_other" name="brand_other"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" readonly="readonly">
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
								<label id="lblmodel_name"></label>
								<form:select path="" id="model_name_p" class="form-control"
									readonly="readonly">
									<c:forEach var="item" items="${getModelNameList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
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
									maxlength="50" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Warranty</label>
							</div>
							<div class="col-md-8" id="warrentyshowid">
								<form:radiobutton id="Warranty_p1" value="Yes" path=""></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="Warranty_p2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="Warranty_pDiv">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Warranty Upto</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="warrantydt" id="warranty_p"
									maxlength="10" class="form-control width88 display_inline rgb0"
									aria-required="true" autocomplete="off" readonly="readonly">

							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red"></strong>Model Type</label>
							</div>
							<div class="col-md-8">
								<form:select path="" id="type" class="form-control"
									readonly="readonly">
									<option value="0">--Select--</option>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none" id="model_type_other_hid">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Model Type Other </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="model_type_other" name="model_type_other"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" readonly="readonly">
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red"> </strong>Year of Proc </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="year_of_proc_p" path=""
									class="form-control autocomplete" maxlength="4"
									autocomplete="off" readonly="readonly"></input>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red"> </strong>Year of Manufacturing </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="year_of_manufacturing_p" path=""
									class="form-control autocomplete" maxlength="4"
									autocomplete="off" readonly="readonly"></input>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red" readonly="readonly"> </strong>Remarks </label>
							</div>
							<div class="col-md-8">
								<textarea id="remarks_p" path="" maxlength="250"
									class="form-control autocomplete text_transform_upper"
									autocomplete="off" readonly="readonly"></textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none" id="countDiv">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Total Count</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="assetcount" name="assetcount" value="1"
									class="form-control autocomplete" autocomplete="off"
									readonly="readonly"></input>
							</div>
						</div>
					</div>
				</div>
				<div class="" id="multiAssetDiv">
					<div class="row modeldiv">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red"></strong>Model Number</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="model_no1" name="model_no1"
											class="form-control autocomplete" maxlength="20"
											autocomplete="off" readonly="readonly"></input>
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
										<input type="text" id="machine_no_p" name="machine_no1"
											class="form-control autocomplete" maxlength="30"
											autocomplete="off" readonly="readonly"></input>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Is Networked </label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="is_networked1" value="Yes"
											name="is_networked1"></input>&nbsp <label for="yes">Yes</label>&nbsp
										<input type="radio" id="is_networked2" name="is_networked1"
											value="No"></input>&nbsp <label for="no">No</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 display_none" id="ip_addressDiv1">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>IP Address(For Networked Device)</label>
									</div>
									<div class="col-md-8">
										<input
											title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address."
											type="text" id="ip_address_p1" name="ip_address1"
											maxlength="15" class="form-control autocomplete"
											autocomplete="off" readonly="readonly"></input>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!--MFD START -->

				<div class="card-body card-block display_none" id="mfdid">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>MFD FEATURES</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2">
								<label class=" form-control-label"><strong
									class="color_red">* </strong>Features Avlb with MFD</label>
							</div>
							<div class="1">
								<b><label>Print</label></b>
							</div>
							<div class="col-md-2">
								<form:radiobutton id="print1" value="Yes" path=""></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="print2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>
							<div class="1">
								<b><label>Scan</label></b>
							</div>
							<div class="col-md-2">
								<form:radiobutton id="scan1" value="Yes" path=""></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="scan2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>

							<div class="1">
								<b><label>Photography</label></b>
							</div>
							<div class="col-md-2">
								<form:radiobutton id="photography1" value="Yes" path=""></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="photography2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>

							<div class="1">
								<b><label>Colour</label></b>
							</div>
							<div class="col-md-2">
								<form:radiobutton id="colour1" value="Yes" path=""></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="colour2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>
						</div>
					</div>
				</div>
				<!-- 						monitor start -->
				<div class="card-body card-block display_none" id="monitorid">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>MONITOR</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Display Type</label>
									</div>
									<div class="col-md-8">
										<form:input type="text" id="display_type" name="display_type"
											path="" class="form-control autocomplete" autocomplete="off"
											readonly="readonly"></form:input>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Display Size</label>
									</div>
									<div class="col-md-8">
										<form:input type="text" id="display_size" name="display_size"
											path="" class="form-control autocomplete" autocomplete="off"
											readonly="readonly"></form:input>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Display Connector</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="display_connector"
											name="display_connector" class="form-control"
											readonly="readonly">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getDisplay_ConnectorList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>



				<!-- 				multimedia speaker , scanner, web camere start -->
				<div class="panel-heading">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>SERVICE DETAILS</b>
						</div>
					</h4>
				</div>

				<div class="card-body card-block">
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Serviceable State</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="s_state_p" class="form-control"
											readonly="readonly">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getServiceable_StateList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none" id="un_show_p">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>UN-Serviceable State</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="unserviceable_state_p"
											class="form-control" readonly="readonly">
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
						<div class="col-md-12 display_none" id="file_showp1">
							<div class="col-md-6 ">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label" id="titleidp"><strong
											class="color_red"></strong><b>Upload BR Certificate</label>
									</div>
									<div class="col-md-8">
										<input type="file" id="br_certificatep1"
											name="br_certificate1" class="display_none" /> <i
											id="download_ip1" class="fa fa-download display_none"><span
											id="uploadedFileNamep1" class="uploaded-file-name f_10"></span><input
											type="button" class="btn btn-success btn-sm display_none"
											id="downloadbtnp" value="File" /></i>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="row display_none" id="unstatemain">
						<div class="col-md-12">
							<div class="col-md-6" id="unsv_div">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Un-servicable from</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="unsv_from_dt1" id="unsv_from_dtp1"
											maxlength="10"
											class="form-control width88 display_inline rgb0"
											aria-required="true" autocomplete="off" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="maintaince_div">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										class="color_red">* </strong>Maintenance Agency</label>
								</div>
								<div class="col-md-8">
									<select id="maintainAgencyp" name="maintainAgency"
										class="form-control" readonly="readonly">
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


				<!-- 				network component -->

				<div class="panel-heading"></div>
				<div class="card-body card-block display_none" id="networkcid">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>Network Components</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>No Of Ports</label>
									</div>
									<div class="col-md-8">
										<form:input type="text" id="no_of_ports" path=""
											class="form-control autocomplete" autocomplete="off"
											maxlength="5" readonly="readonly"></form:input>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Network Features</label>
									</div>
									<div class="col-md-8">
										<div class="multiselect">

											<div class="selectBox" id="networkshowid">
												<!-- 												<select name="network_features" id="network_features" -->
												<!-- 													class=" form-control"> -->
												<!-- <!-- 													<option>Select Network Features</option> -->
												<!-- 												</select> -->
												<div class="overSelect"></div>
											</div>
											<!-- 											<div id="checkboxes_net" class="checkboxes checkboxcls"> -->
											<!-- 												<div> -->
											<!-- 																										<input type="text" id="network_features_search" -->
											<!-- 																											class="form-control autocomplete" autocomplete="off" -->
											<!-- 																											placeholder="Search Networks"> -->
											<!-- 												</div> -->
											<!-- 												<div> -->
											<%-- 													<c:forEach var="item" items="${getNetwork_featuresList}" --%>
											<%-- 														varStatus="num"> --%>
											<!-- 														<label for="one" class="network_featureslist"> <input -->
											<!-- 															type="checkbox" id="multisub_netid" name="multisub_net" -->
											<%-- 															value="${item[0]}" /> ${item[1]} --%>
											<!-- 														</label> -->
											<%-- 													</c:forEach> --%>

											<!-- 													<input type="hidden" id="hd_network_features" -->
											<!-- 														name="hd_network_features" -->
											<!-- 														class="form-control autocomplete" autocomplete="off"></input> -->

											<!-- 												</div> -->
											<!-- 											</div> -->
											<div id="checkboxes_net" class="checkboxes checkboxcls">
												<div>
													<input type="text" id="network_features_search"
														class="form-control autocomplete" autocomplete="off"
														placeholder="Search Networks" readonly="readonly">
												</div>
												<div>
													<c:forEach var="item" items="${getNetwork_featuresList}"
														varStatus="num">
														<label for="one" class="network_featureslist"> <input
															type="checkbox" id="multisub_netid" name="multisub_net"
															value="${item[0]}" /> ${item[1]}
														</label>
													</c:forEach>

													<input type="hidden" id="hd_network_features"
														name="hd_network_features"
														class="form-control autocomplete" autocomplete="off"></input>

												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Ethernet Port</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="ethernet_port" class="form-control"
											readonly="readonly">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getEthernet_portList}"
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
											class="color_red"></strong>Management Layer</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="management_layer"
											class="form-control">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getManagement_layerList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 				printer -->

				<div class="panel-heading"></div>
				<div class="card-body card-block display_none" id="printid">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>Printer</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">*</strong>Monochrome/Color</label>
									</div>
									<div class="col-md-8">
										<input type="radio" id="monochrome_color_radio1"
											name="monochrome_color" value="monochrome">&nbsp <label
											for="monochrome">Monochrome</label>&nbsp <input type="radio"
											id="monochrome_color_radio2" name="monochrome_color"
											value="color" >&nbsp <label
											for="color">Color</label><br>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Max Paper Size</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="paper_size" class="form-control"
											readonly="readonly">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getPaper_SizeList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Connectivity Type</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="connectivity_type"
											class="form-control" readonly="readonly">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getConnectivity_TypeList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>

							<div class="col-md-6 display_none" id="connect_other_hid">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Connectivity Type Other </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="connectivity_other"
											name="connectivity_other" class="form-control autocomplete"
											autocomplete="off" maxlength="50" readonly="readonly">
									</div>
								</div>
							</div>

						</div>
					</div>
				</div>


				<!-- 				Projection Sys -->
				<div class="panel-heading"></div>
				<div class="card-body card-block display_none" id="projectionid">
					<h4 class="panel-title main_title acc-heading">
						<div>
							<b>PROJECTION SYS</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Capacity(Lumens)</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="capacity" path=""
											class="form-control autocomplete" autocomplete="off"
											readonly="readonly"></input>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Hardware Interface</label>
									</div>
									<div class="col-md-8">
										<!-- 											<div class="multiselect"> -->

										<!-- 														<div class="selectBox" id="networkshowid"> -->
										<!-- 															<select name="network_features" id="network_features" -->
										<!-- 																class=" form-control"> -->
										<!-- 																<option>Select Network Features</option> -->
										<!-- 															</select> -->
										<!-- 															<div class="overSelect"></div> -->
										<!-- 														</div> -->
										<!-- 														<div id="checkboxes_net" class="checkboxes checkboxcls"> -->
										<!-- 															<div> -->
										<!-- 																<input type="text" id="network_features_search" -->
										<!-- 																	class="form-control autocomplete" autocomplete="off" -->
										<!-- 																	placeholder="Search Networks"> -->
										<!-- 															</div> -->
										<!-- 															<div> -->
										<%-- 																<c:forEach var="item" items="${getNetwork_featuresList}" --%>
										<%-- 																	varStatus="num"> --%>
										<!-- 																	<label for="one" class="network_featureslist"> -->
										<!-- 																		<input type="checkbox" id="multisub_netpid" -->
										<%-- 																		name="multisub_netp" value="${item[0]}"/> ${item[1]} --%>
										<!-- 																	</label> -->
										<%-- 																</c:forEach> --%>

										<!-- 																<input type="hidden" id="hd_network_featuresp" -->
										<!-- 																	name="hd_network_featuresp" -->
										<!-- 																	class="form-control autocomplete" autocomplete="off"></input> -->

										<!-- 															</div> -->
										<!-- 														</div> -->

										<!-- 													</div> -->

										<div class="multiselect">

											<div class="selectBox" id="showCheckboxesid">
												<select name="hw_interface" id="hw_interface"
													class=" form-control" readonly="readonly">
													<option>Select Hardware Interface</option>
												</select>
												<div class="overSelect"></div>
											</div>
											<div id="checkboxes" class="checkboxes checkboxcls">
												<div>
													<input type="text" id="hw_interface_search"
														class="form-control autocomplete" autocomplete="off"
														placeholder="Search Hardware" readonly="readonly">
												</div>
												<div>
													<c:forEach var="item" items="${hw_interfaceList}"
														varStatus="num">
														<label for="one" class="hw_interfacelist"> <input
															id="hardwareid" type="checkbox" name="multisub"
															value="${item[0]}" /> ${item[1]}
														</label>
													</c:forEach>
													<input type="hidden" id="hd_hw_interface"
														name="hd_hw_interface" class="form-control autocomplete"
														autocomplete="off" readonly="readonly"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 				visulizer -->
				<div class="panel-heading"></div>

				<div class="card-body card-block display_none" id="visulizerid">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>Visulizer</b>
						</div>
					</h4>
					<br>
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Resolution</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="resolution" path=""
											readonly="readonly" class="form-control autocomplete"
											autocomplete="off"  readonly="readonly"></input>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Display Size</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="v_display_size" path=""
											readonly="readonly" class="form-control autocomplete"
											autocomplete="off"  readonly="readonly"></input>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">* </strong>Display Connector</label>
									</div>
									<div class="col-md-8">
										<form:select path="" id="v_display_connector"
											readonly="readonly" class="form-control">
											<form:option value="0">--Select--</form:option>
											<c:forEach var="item" items="${getDisplay_ConnectorList}"
												varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Procurement Details -->
				<div class="panel-heading">
					<h4 class="panel-title main_title acc-heading" id="">
						<div>
							<b>PROCUREMENT DETAILS</b>
						</div>
					</h4>
				</div>
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
											id="b_costp" name="b_cost" class="form-control autocomplete"
											readonly="readonly" />

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
										<form:select path="" id="b_headp" class="form-control"
											readonly="readonly">
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
									<input type="text" id="crv_nop" name="crv_no"
										class="form-control autocomplete" autocomplete="off"
										readonly="readonly">
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">*</strong>Crv Date</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="proc_dt" id="proc_datep"
											maxlength="10" readonly="readonly"
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
										<input type="file" id="u_file1" name="u_file1"
											class="display_none" /> <i id="pdownload_i"
											class="fa fa-download display_none"><span
											id="puploadedFileName" class="uploaded-file-name f_10"></span><input
											type="button" class="btn btn-success btn-sm display_none"
											id="downloadbtn" value="File" /></i>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class="form-control-label">GeM Contact No.</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="gem_nop1" name="gem_nop1"
											maxlength="12" class="form-control" autocomplete="off"
											readonly="readonly"></input>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong
											class="color_red">*</strong>Approved By OIS </label>
									</div>
									<div class="col-md-8" id="approveoisid">
										<form:radiobutton id="approveoisp1" value="Yes" path=""></form:radiobutton>
										&nbsp <label for="approveois1">Yes</label>&nbsp
										<form:radiobutton id="approveoisp2" path="" value="No"></form:radiobutton>
										&nbsp <label for="antiviruscheck2">No</label>
									</div>
								</div>
							</div>
						</div>

						<div class="row" id="supplier_field">
							<div class='col-md-6'>
								<div class='row form-group'>
									<div class="col-md-4">
										<strong class="color_red">*</strong> <label for="text-input"
											class="form-control-label">Supplier</label>
									</div>
									<div class='col-md-8'>
										<input type="text" id="supplierp" name="supplier"
											class="form-control autocomplete" autocomplete='off'
											maxlength="100" readonly="readonly"></input>
									</div>
								</div>
							</div>
							<div class='col-md-6'>
								<div class='row form-group'>
									<div class='col-md-4'>
										<strong class="color_red">*</strong> <label for="text-input"
											class="form-control-label">Supplier Contact No:</label>
									</div>
									<div class='col-md-8'>
										<input type="text" id="contact_numberp"
											class="form-control text_transform_upper" autocomplete='off'
											maxlength="10" readonly="readonly"></input>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body card-block display_none"
				id="blockdata_consumable">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong> Section </label>
							</div>
							<div class="col-md-8">
								<label id="unit_sus_no_hid" class="display_none"><b>${roleSusNo}</b></label>
								<select id="sectionc" name="section" class="form-control"
									readonly="readonly">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num">
										<option value="${item.id}">${item.section}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Consumable Type </label>
							</div>
							<div class="col-md-8">
								<form:select path="" id="assets_typec" class="form-control"
									name="assets_type" readonly="readonly">
									<form:option value="0">--Select--</form:option>
									<c:forEach var="item" items="${getConsumables}" varStatus="num">
										<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Make/Brand Name</label>
							</div>
							<div class="col-md-8">
								<label id="lblmake_name"></label>
								<form:select path="" id="make_namec" class="form-control"
									readonly="readonly">
									<c:forEach var="item" items="${getMakeName}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6 display_none" id="brand_other_hid">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Make/Brand Other </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="brand_other" name="brand_other"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Model Name</label>
							</div>
							<div class="col-md-8">
								<label id="lblmodel_name"></label>
								<form:select path="" id="model_namec" class="form-control"
									readonly="readonly">
									<c:forEach var="item" items="${getModelNameList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none" id="model_other_hid">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Model Other </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="model_other" name="model_other"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Warranty</label>
							</div>
							<div class="col-md-8" id="warrentyshowid">
								<form:radiobutton id="Warrantyc1" value="Yes" path=""
									checked="checked"></form:radiobutton>
								&nbsp <label for="yes">Yes</label>&nbsp
								<form:radiobutton id="Warrantyc2" path="" value="No"></form:radiobutton>
								&nbsp <label for="no">No</label>
							</div>
						</div>
					</div>
					<div class="col-md-6" id="WarrantycDiv">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Warranty Upto</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="warrantydt" id="warrantyc"
									maxlength="10" class="form-control width88 display_inline rgb0"
									aria-required="true" autocomplete="off" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label class="form-control-label"><strong
								class="color_red"> </strong>Remarks </label>
						</div>
						<div class="col-md-8">
							<textarea id="remarksc" name="remarks" path=""
								maxlength="250"
								class="form-control autocomplete text_transform_upper"
								autocomplete="off" readonly="readonly"></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label class=" form-control-label"><strong
								class="color_red"> </strong>Year of Proc </label>
						</div>
						<div class="col-md-8">
							<input type="text" id="year_of_procc" path=""
								class="form-control autocomplete" maxlength="4"
								autocomplete="off" readonly="readonly"></input>
						</div>
					</div>
				</div>
				<div class="col-md-6" id="countDiv">
					<div class="row form-group">
						<div class="col-md-4">
							<label class="form-control-label"><strong
								class="color_red">* </strong>Total Quantity</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="assetcountc2" name="assetcount2"
								class="form-control autocomplete" autocomplete="off"
								readonly="readonly"></input>
						</div>
					</div>
				</div>
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="" data-toggle="collapse" data-parent="#accordion4"
									href="#" id="a_warrenty"></a>
							</h4>
						</div>
						<div id="collapsewarrantey" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red"> </strong>Proc Cost (&#8377)</label>
							</div>
							<div class="col-md-8">
								<input title="Approx. Procurement Cost" type="text"
									id="proc_costc" name="pro_cost" path=""
									class="form-control autocomplete" autocomplete="off"
									readonly="readonly"></input>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">*</strong>Proc Date</label>
							</div>
							<div class="col-md-8">
								<input type="text" name="proc_dt" id="proc_datec" maxlength="10"
									class="form-control width88 display_inline rgb0"
									aria-required="true" autocomplete="off" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Budget Head</label>
							</div>
							<div class="col-md-8">
								<select id="b_headc" name="b_head" class="form-control"
									readonly="readonly">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getBudgetHeadList}"
										varStatus="num">
										<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>Budget Code</label>
							</div>
							<div class="col-md-8">
								<form:select path="" id="b_codec" class="form-control"
									readonly="readonly">
									<c:forEach var="item" items="${getBudgetCodeList}"
										varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>CRV No.</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="crv_noc" name="crv_no"
									class="form-control autocomplete" autocomplete="off"
									readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label">GeM Contact No.</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="gem_noc1" name="gem_no1" maxlength="12"
									class="form-control" autocomplete="off" readonly="readonly"></input>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<!-- 			for consumable -->

<script nonce="${cspNonce}">
var activeTab = "home-tab";

$(document).ready(function() {
 
	console.log("Initial Active Tab: ", activeTab);


	
	});
	document.addEventListener('DOMContentLoaded', function() {

		//if (activeTab = "profile-tab") {
			
			// 			document.getElementById('networkshowid').onclick = 
			// 				function() {
			// 				return NetworkshowCheckboxes();
			// 		  	};
		//}

		document.getElementById('save_btn').onclick = function() {
			return Validate();
		};

		setupAutocomplete("input#user_name", "#Section_comp");
		setupAutocomplete("input#user_name_peri", "#Section_peri");
		setupAutocomplete("input#user_name_consum", "#Section_consum");

		setupAutocompleteRegdNo("input#redg_no", "#Section_comp", "1");
		setupAutocompleteRegdNo("input#redg_no_section_peri", "#Section_peri",
				"2");
		setupAutocompleteRegdNo("input#redg_no_section_consum",
				"#Section_consum", "3");
	});
	function fn_Peripheral() {

		var peripheral_type = $("select#assets_type_p").val();
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
							$("select#type_of_hw_p").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	function fn_makeName() {
		var assets_name = $("select#assets_type_p").val();
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
							$("select#make_name_p").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	function fn_modelName() {
		var make_name = $("select#make_name_p").val();
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
	// 	function NetworkshowCheckboxes() {
	// 		  var checkboxes = document.getElementById("checkboxes_net");
	// 		  $("#checkboxes_net").toggle();
	// 		  $("#network_features_search").val(''); 

	// 		  $('.network_featureslist').each(function () {       
	// 		  $(this).show()
	// 		})
	// 		}
	// 	function fn_brand_other() {

	// 		var text = $("#make_name option:selected").text();

	// 		if (text.toUpperCase() == "OTHERS") {
	// 			$("#brand_other_hid").show();
	// 		} else {
	// 			$("#brand_other_hid").hide();
	// 			$("#brand_other").val('');
	// 		}
	// 	}

	function Validate() {
		if ($("#Section_comp").val() == 0 || $("#Section_comp").val() == "") {
			alert("Please Select Issue Section");
			$("#Section_comp").focus();
			return false;
		}

		if ($("#redg_no").val() == "" || $("#redg_no").val() == '') {
			alert("Please Enter Redg_No");
			$("#redg_no").focus();
			return false;
		}

	}
	function setupAutocomplete(inputSelector, sectionSelector) {
		$(inputSelector).keypress(
				function() {
					var Section = $(sectionSelector).val();
					var loginname = this.value;

					if (Section == "0" || Section == "") {
						alert("Please Select Issue to Section.");
						$(sectionSelector).focus();
						return false;
					}

					var susNoAuto = $(inputSelector);
					susNoAuto.autocomplete({
						source : function(request, response) {
							$.ajax({
								type : 'POST',
								url : "getActiveUsernameListFromSection?" + key
										+ "=" + value,
								data : {
									loginname : loginname,
									Section : Section
								},
								success : function(data) {
									var susval = [];
									var length = data.length - 1;
									var enc = data[length].substring(0, 16);
									for (var i = 0; i < data.length; i++) {
										susval.push(dec(enc, data[i]));
									}
									response(susval);
								}
							});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Active UserName.");
								$(inputSelector).val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							// You can handle the selection here if needed
						}
					});
				});
	}

	function setupAutocompleteRegdNo(inputSelector, sectionSelector, Type) {
		$(inputSelector).keypress(function() {
			var Section = $(sectionSelector).val();
			var loginname = this.value;

			if (Section == "0" || Section == "") {
				alert("Please Select Issue to Section.");
				$(sectionSelector).focus();
				return false;
			}

			var susNoAuto = $(inputSelector);
			susNoAuto.autocomplete({
				source : function(request, response) {
					$.ajax({
						type : 'POST',
						url : "getRedgListFromSection?" + key + "=" + value,
						data : {
							loginname : loginname,
							Section : Section,
							Type : Type
						},
						success : function(data) {
							var susval = [];
							var length = data.length - 1;
							var enc = data[length].substring(0, 16);
							for (var i = 0; i < data.length; i++) {
								susval.push(dec(enc, data[i]));
							}
							response(susval);
						}
					});
				},
				minLength : 1,
				autoFill : true,
				change : function(event, ui) {

					if (ui.item) {

						//                 	 register_number();
						//                 	 getcomputing();

						return true;
					} else {
						alert("Please Enter Redg No");
						$(inputSelector).val("");
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
// 				select : function(event, ui) {
// 					debugger;
// 					  console.log("Autocomplete item selected");
// 					    console.log("Active Tab: ", activeTab);
// 					if (activeTab === "home-tab") {
// 					register_number(ui.item.value)
// 						getcomputing();
// 					}

// 					 if (activeTab === "profile-tab") {
// 						register_number_p(ui.item.value)
// 						getcomputing_p();
// 					}
					
// 					 if (activeTab === "consumable-tab") {
// 						debugger;
// 						register_number_c(ui.item.value)
// 						getcomputing_c();
// 					}

// 				}
				
				select: function(event, ui) {
				    console.log("Autocomplete item selected");
				    console.log("Active Tab: ", activeTab);

				    setTimeout(() => {
				        if (activeTab === "home-tab") {
				            register_number(ui.item.value);
				            getcomputing();
				        } else if (activeTab === "profile-tab") {
				            register_number_p(ui.item.value);
				            getcomputing_p();
				        } else if (activeTab === "consumable-tab") {
				            register_number_c(ui.item.value);
				            getcomputing_c();
				        }
				    }, 500); // Adjust the delay as needed
				}
			});
		});
	}

	function getcomputing() {
		$("#blockdata").show();
		$("#blockdata_peripheral").hide();
		$("#blockdata_consumable").hide();
	}
	function getcomputing_p() {
		$("#blockdata_peripheral").show();
		$("#blockdata_consumable").hide();
		$("#blockdata").hide();

	}
	function getcomputing_c() {
		$("#blockdata_consumable").show();
		$("#blockdata").hide();
		$("#blockdata_peripheral").hide();

	}
	function register_number(selectedValue) {
		if ($("input#redg_no").val() == "") {
			alert("Please select Redg. No");
			$("input#redg_no").focus();
			return false;
		}
		var redg_no = selectedValue; //$("input#redg_no").val();

		if (activeTab = "home-tab") {
			$
					.post(
							'Getregister_numberData?' + key + "=" + value,
							{
								redg_no : redg_no
							},
							function(j) {
								if (j != "") {

									console.log(j);

									$("#section").val(j[0][0]);
									$("#asset_type").val(j[0][1]);
									$("#make_name").val(j[0][2]);
									$("#model_name").val(j[0][3]);
									$("#processor_type").val(j[0][4]);
									$("#ram_capi").val(j[0][5]);
									$("#hdd_capi").val(j[0][6]);
									var ssdvalue = j[0][7];
									if (ssdvalue == "0") {
										$("#ssdcheck2").prop("checked", true);
										$("#ssdcheck1").prop("disabled", true);
									} else if (ssdvalue != "0") {
										$("#divssdcap").show();
										$("#ssd_capi").val(j[0][7]);
										$("#ssdcheck1").prop("checked", true);
										$("#ssdcheck2").prop("disabled", true);
									}
									$("#operating_system").val(j[0][8]);
									$("#office").val(j[0][9]);
									$("#dply_envt").val(j[0][10]);
									$("#mac_address1").val(j[0][11]);
									$("#machine_number1").val(j[0][12]);
									$("#ip_address1").val(j[0][13]);
									$("#s_state").val(j[0][14]);
									$("#s_state").val(j[0][14]);
									var sstate = j[0][14];

									if (sstate == "2") {
										$("#un_show").show();
										$("#unserviceable_state").val(j[0][27]);
										var certificate = j[0][27];

										if (certificate == 2) {
											$("#file_show").show();
											var filename1 = j[0][28];

											if (filename1
													&& filename1.trim() !== "") {
												var fileName3 = filename1
														.substring(filename1
																.lastIndexOf('/') + 1);
												$("#uploadedFileName1").text(
														fileName3);
												$('#download_i1').removeClass(
														'display_none');
											
											} else {
												$("#uploadedFileName1").text(
														'No file uploaded');
												$('#download_i1').removeClass(
														'display_none');
											}
										}

										$("#unsv_div").show();
										$("#unsv_from_dt1").val(j[0][29]);
										$("#maintaince_div").show();
										$("#maintainAgency").val(j[0][30]);

									}
									$("#b_cost").val(j[0][15]);
									$("#b_head").val(j[0][16]);
									$("#crv_no").val(j[0][17]);
									let procDateValue = j[0][18];
									let procDate = new Date(procDateValue);
									let formattedProcDate = procDate
											.toISOString().split('T')[0];
									$("#proc_date").val(formattedProcDate);
									$("#gem_no1").val(j[0][19]);
									var approvalValue = j[0][20];
									if (approvalValue == "Yes") {

										$("#approveois1").prop("checked", true);
										$("#approveois2")
												.prop("disabled", true);

									} else if (approvalValue == "No") {
										$("#supplier_field").show();
										$("#approveois2").prop("checked", true);
										$("#approveois1")
												.prop("disabled", true);
										$("#supplier").val(j[0][21]);
									}

									var antivirus = j[0][22];
									if (antivirus == "Yes") {
										$("#AntiVirusDiv").show();
										$("#antivirus").val(j[0][31]);
										$("#antivirus_date").show();
										let antivirusExpryValue = j[0][32];
										let antivirusExpryDate = new Date(
												antivirusExpryValue);
										let formattedAntivirusExpryDate = antivirusExpryDate
												.toISOString().split('T')[0];
										$("#antivirus_expry1").val(
												formattedAntivirusExpryDate);

										$("#antiviruscheck1").prop("checked",
												true);
										$("#antiviruscheck2").prop("disabled",
												true);
									} else if (antivirus == "No") {
										$("#antiviruscheck2").prop("checked",
												true);
										$("#antiviruscheck1").prop("disabled",
												true);
									}
									var cddrive = j[0][23];
									if (cddrive == "Yes") {
										$("#scan1").prop("checked", true);
										$("#scan2").prop("disabled", true);
									} else if (cddrive == "No") {
										$("#scan2").prop("checked", true);
										$("#scan1").prop("disabled", true);

									}
									var warrentyr = j[0][24];
									if (warrentyr == "Yes") {
										$("#Warranty1").prop("checked", true);
										$("#Warranty2").prop("disabled", true);
										$("#WarrantyDiv").show();
										let warrantyValue = j[0][25];
										let warrantyDate = new Date(
												warrantyValue);
										let formattedDate = warrantyDate
												.toISOString().split('T')[0];
										$("#warranty").val(formattedDate);
									} else if (warrentyr == "No") {
										$("#Warranty2").prop("checked", true);
										$("#Warranty1").prop("disabled", true);
									}

									var filename = j[0][26]

									if (filename && filename.trim() !== "") {
										var fileName2 = filename
												.substring(filename
														.lastIndexOf('/') + 1);
										$("#uploadedFileName").text(fileName2);
										$('#download_i').removeClass(
												'display_none');
									} else {
										$("#uploadedFileName").text(
												'No file uploaded');
										$('#download_i').removeClass(
												'display_none');
									}

									$("#contact_number").val(j[0][33]);
								}

							});
		}
	}
	// for peripheral

	function register_number_p(selectedValue) {
		// 		if ($("input#redg_no").val() == "") {
		// 			alert("Please select Redg. No");
		// 			$("input#redg_no").focus();
		// 			return false;
		// 		}
		var redg_no = selectedValue; //$("input#redg_no").val();
		if (activeTab = "profile-tab") {
			$
					.post(
							'Getregister_number_peripheral_Data?' + key + "="
									+ value,
							{
								redg_no : redg_no
							},
							function(j) {
								if (j != "") {
									console.log(j);
									$("#section_p").val(j[0][0]);
									$("#assets_type_p").val(j[0][1]);
									$("#type_of_hw_p").val(j[0][2]);
									$("#make_name_p").val(j[0][3]);
									$("#model_name_p").val(j[0][4]);

									var warrentyr = j[0][5];

									if (warrentyr == "Yes") {
										$("#Warranty_p1").prop("checked", true);
										$("#Warranty_p2")
												.prop("disabled", true);
										$("#Warranty_pDiv").show();
										let warrantyValue = j[0][6];
										let warrantyDate = new Date(
												warrantyValue);
										let formattedDate = warrantyDate
												.toISOString().split('T')[0];
										$("#warranty_p").val(formattedDate);
									} else if (warrentyr == "No") {
										$("#Warranty_p2").prop("checked", true);
										$("#Warranty_p1")
												.prop("disabled", true);
									}
									$("#year_of_proc_p").val(j[0][7]);
									$("#year_of_manufacturing_p").val(j[0][8]);
									$("#remarks_p").val(j[0][9]);
									$("#machine_no_p").val(j[0][10]);
									var network = j[0][11];
									if (warrentyr == "Yes") {
										$("#is_networked1").prop("checked",
												true);
										$("#is_networked2").prop("disabled",
												true);
										$("#ip_addressDiv1").show();
										$("#ip_address_p1").val(j[0][12]);

									} else if (network == "No") {
										$("#is_networked2").prop("checked",
												true);
										$("#is_networked1").prop("disabled",
												true);
									}

									var assettypep = j[0][2];
									if (assettypep == '33') {
										$("#mfdid").show();
										var print = j[0][13];
										if (print == "Yes") {
											$("#print1").prop("checked", true);
											$("#print2").prop("disabled", true);
										} else if (print == "No") {
											$("#print2").prop("checked", true);
											$("#print1").prop("disabled", true);
										}

										var scan = j[0][14];
										if (print == "Yes") {
											$("#scan1").prop("checked", true);
											$("#scan2").prop("disabled", true);
										} else if (scan == "No") {
											$("#scan1").prop("checked", true);
											$("#scan2").prop("disabled", true);
										}

										var photography = j[0][15];
										if (photography == "Yes") {
											$("#photography1").prop("checked",
													true);
											$("#photography2").prop("disabled",
													true);
										} else if (photography == "No") {
											$("#photography2").prop("checked",
													true);
											$("#photography1").prop("disabled",
													true);
										}

										var colour = j[0][16];
										if (colour == "Yes") {
											$("#colour1").prop("checked", true);
											$("#colour2")
													.prop("disabled", true);
										} else if (colour == "No") {
											$("#colour2").prop("checked", true);
											$("#colour1")
													.prop("disabled", true);
										}
										//$("#scan").val(j[0][14]);
										//$("#photography").val(j[0][15]);
										//$("#colour").val(j[0][16]);
									} else if (assettypep == '57') {
										$("#monitorid").show();
										$("#display_type").val(j[0][17]);
										$("#display_size").val(j[0][18]);
										$("#display_connector").val(j[0][19]);
									} else if (assettypep == '28') {
										$("#networkcid").show();
										$("#no_of_ports").val(j[0][20]);
										var network = j[0][21];
										var networklist = network.split(',');
										for (kn = 0; kn < networklist.length; kn++) {
											$(
													"input[type=checkbox][name='multisub_net'][value='"
															+ networklist[kn]
															+ "']").prop(
													"checked", true);
										}
										$("#hd_network_features").val(network);
										$("#ethernet_port").val(j[0][22]);
										$("#management_layer").val(j[0][23]);
									} else if (assettypep == '20') {
										$("#printid").show();

										var monochrome = j[0][38];
										if (monochrome == "monochrome") {
											$("#monochrome_color_radio1").prop(
													"checked", true);
											$("#monochrome_color_radio2").prop(
													"disabled", true);
										} else if (monochrome == "color") {
											$("#monochrome_color_radio2").prop(
													"checked", true);
											$("#monochrome_color_radio1").prop(
													"disabled", true);
										}
										$("#paper_size").val(j[0][39]);
										$("#connectivity_type").val(j[0][40]);
									} else if (assettypep == '22') {
	
										$("#projectionid").show();

										var hardware = j[0][42];
										var subjectslist = hardware.split(',');
										for (k = 0; k < subjectslist.length; k++) {
											$(
													"input[type=checkbox][name='multisub'][value='"
															+ subjectslist[k]
															+ "']").prop(
													"checked", true);
											/* $("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')'); */
										}

										$("#capacity").val(j[0][41]);

									}else if(assettypep == '23'){
										$("#visulizerid").show();
										$("#resolution").val(j[0][43]);
										$("#v_display_connector").val(j[0][45]);
										$("#v_display_size").val(j[0][44]);
										
									}
									$("#s_state_p").val(j[0][24])
									var sstate = j[0][24];

									if (sstate == "2") {
										$("#un_show_p").show();
										$("#unserviceable_state_p").val(
												j[0][25]);
										var unserviceable_state_p = j[0][25];
										if (unserviceable_state_p == 2) {
											$("#file_showp1").show();
											var filename11 = j[0][26];

											if (filename11
													&& filename11.trim() !== "") {
												var fileName31 = filename11
														.substring(filename11
																.lastIndexOf('/') + 1);
												$("#uploadedFileNamep1").text(
														fileName31);
												$('#download_ip1').removeClass(
														'display_none');
// 												$('#titleidp').addClass(
// 												'display_none');
												
											} else {
												$("#uploadedFileNamep11").text(
														'No file uploaded');
												$('#download_ip1').removeClass(
														'display_none');
											}
										}
										$("#unstatemain").show();
										let unsv_from_dtp = j[0][27];
										let procDatep = new Date(unsv_from_dtp);
										let formattedProcDatep = procDatep
												.toISOString().split('T')[0];
										$("#unsv_from_dtp1").val(
												formattedProcDatep);
										$("#maintainAgencyp").val(j[0][28]);
									}
									$("#b_costp").val(j[0][29]);
									$("#b_headp").val(j[0][30]);
									$("#crv_nop").val(j[0][31]);

									let procDateValuep = j[0][32];
									let procDatep = new Date(procDateValuep);
									let formattedProcDatep = procDatep
											.toISOString().split('T')[0];
									$("#proc_datep").val(formattedProcDatep);

									var filenamep = j[0][33]

									if (filenamep && filenamep.trim() !== "") {
										var fileName2p = filenamep
												.substring(filenamep
														.lastIndexOf('/') + 1);
										$("#puploadedFileName")
												.text(fileName2p);
										$('#pdownload_i').removeClass(
												'display_none');
									} else {
										$("#puploadedFileName").text(
												'No file uploaded');
										$('#pdownload_i').removeClass(
												'display_none');
									}
									$("#gem_nop1").val(j[0][34]);

									var approvalValuep = j[0][35];
									if (approvalValuep == "Yes") {

										$("#approveoisp1")
												.prop("checked", true);
										$("#approveoisp2").prop("disabled",
												true);

									} else if (approvalValuep == "No") {
										$("#supplier_field").show();
										$("#approveoisp2")
												.prop("checked", true);
										$("#approveoisp1").prop("disabled",
												true);
										$("#contact_numberp").val(j[0][36]);
									}

									$("#supplierp").val(j[0][37]);
									

								}

								//$("#contact_numberp").val(j[0][37]);

							});
		}
	}
	//for consumable
	

	function register_number_c(selectedValue) {

		// 		if ($("input#redg_no").val() == "") {
		// 			alert("Please select Redg. No");
		// 			$("input#redg_no").focus();
		// 			return false;
		// 		}
		var redg_no = selectedValue; //$("input#redg_no").val();
		if (activeTab = "consumable-tab") {
			$.post('Getregister_number_consumable_Data?' + key + "=" + value, {
				redg_no : redg_no
			}, function(j) {
				console.log(j);
				$("#sectionc").val(j[0][0]);
				$("#assets_typec").val(j[0][1]);
				$("#make_namec").val(j[0][2]);
				$("#model_namec").val(j[0][3]);
				
				var warrentycr = j[0][4];

				if (warrentycr == "Yes") {
					$("#Warrantyc1").prop("checked", true);
					$("#Warrantyc2")
							.prop("disabled", true);
					$("#WarrantycDiv").show();
					let warrantycValue = j[0][5];
					let warrantycDate = new Date(
							warrantycValue);
					let formattedcDate = warrantycDate
							.toISOString().split('T')[0];
					$("#warrantyc").val(formattedcDate);
				} else if (warrentycr == "No") {
					$("#Warrantyc2").prop("checked", true);
					$("#Warrantyc1")
							.prop("disabled", true);
				}
				$("#remarksc").val(j[0][6]);
				$("#assetcountc2").val(j[0][7])
				$("#proc_costc").val(j[0][8])
				
				let warrantycValuep = j[0][9];
					let warrantycDatep = new Date(
							warrantycValuep);
					let formattedcDatep = warrantycDatep
							.toISOString().split('T')[0];
					$("#proc_datec").val(formattedcDatep);
				
					
					$("#b_headc").val(j[0][10])
					$("#b_costc").val(j[0][11])
					
					$("#crv_noc").val(j[0][12])
					$("#gem_noc1").val(j[0][13])
					$("#year_of_procc").val(j[0][14]);
			});
		}
	}
	$("#profile-tab").click(function() {
		activeTab = "profile-tab";
		 console.log("Active Tab changed to: ", activeTab);
		 
		 document.getElementById('assets_type_p').onchange = function() {
				return fn_Peripheral(), fn_makeName();
			};
			document.getElementById('make_name_p').onchange = function() {
				return fn_modelName();
			};
		// Show profile tab and hide home tab
		$("#profile").show().removeClass('display_none').addClass('active');
		$("#home").hide().addClass('display_none').removeClass('active');
		$("#consumable").hide().addClass('display_none').removeClass('active');

		// Update tab states
		$("#profile-tab").addClass('active');
		$("#home-tab").removeClass('active');
		$("#consumable-tab").removeClass('active');

		// Manage buttons visibility
		$("#savebtn").hide();
		$("#updatebtn").removeClass('display_none');
		$("#COMPUTING").hide();
		$("#CONSUMABLE").hide();
		$("#PERIPHERAL").show();
		
	});

	$("#home-tab").click(function() {
		
		activeTab = "home-tab";
		 console.log("Active Tab changed to: ", activeTab);
		// Show home tab and hide profile tab
		$("#home").show().removeClass('display_none').addClass('active');
		$("#profile").hide().addClass('display_none').removeClass('active');
		$("#consumable").hide().addClass('display_none').removeClass('active');

		// Update tab states
		$("#home-tab").addClass('active');
		$("#profile-tab").removeClass('active');
		$("#consumable-tab").removeClass('active');

		$("#PERIPHERAL").hide();
		$("#COMPUTING").show();
		$("#CONSUMABLE").hide();

		// Manage buttons visibility
		$("#savebtn").show();
		$("#updatebtn").addClass('display_none');
		$("#icon_search").addClass('display_none');
	});

	$("#consumable-tab").click(function() {
		
		activeTab = "consumable-tab";
		 console.log("Active Tab changed to: ", activeTab);
		// Show consumable tab and hide other tabs
		$("#home").hide().addClass('display_none').removeClass('active');
		$("#profile").hide().addClass('display_none').removeClass('active');
		$("#consumable").show().removeClass('display_none').addClass('active');

		// Update tab states
		$("#home-tab").removeClass('active');
		$("#profile-tab").removeClass('active');
		$("#consumable-tab").addClass('active');

		$("#PERIPHERAL").hide();
		$("#COMPUTING").hide();
		$("#CONSUMABLE").show();

		// Manage buttons visibility
		$("#savebtn").show();
		$("#updatebtn").addClass('display_none');
		$("#icon_search").addClass('display_none');
	});
</script>
