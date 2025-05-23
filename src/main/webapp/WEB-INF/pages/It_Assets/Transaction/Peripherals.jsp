
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">



<style nonce="${cspNonce}">
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
/* textarea {
    text-transform: none!important;
}
 */
.card-body.card-block {
	/*     border: 20px solid #fff; */
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


<form:form name="PeripheralAssets" id="PeripheralAssets"
	action="PeripheralAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal" modelAttribute="PeripheralCmd"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 id="save">ADD PERIPHERAL ASSETS</h5>
					<h5 id="update">UPDATE/EDIT PERIPHERAL ASSETS</h5>
				</div>
				<input type="hidden" id="make_mstr_id" name="make_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="model_mstr_id" name="model_mstr_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="type_hw_id" name="type_hw_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="pro_type_id" name="pro_type_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="pro_connect_id" name="pro_connect_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="u_file_hid" name="u_file_hid"
					value="${PeripheralCmd.u_file}" class="form-control autocomplete"
					autocomplete="off"> <input type="hidden" id="supplierId"
					name="supplierId"> <input type="hidden" id="screenUrl"
					name="screenUrl" value="${screen_url}"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="br_certificate1_id" name="br_certificate1_id"
					value="${PeripheralCmd.br_certificate}"
					class="form-control autocomplete" autocomplete="off">
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
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
									<form:select path="assets_type" id="assets_type"
										class="form-control" name="assets_type">
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
									<form:select path="type_of_hw" id="type_of_hw"
										name="type_of_hw" class="form-control">
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
										class="color_red">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name"
										class="form-control">
										<option value="0">--Select--</option>

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
										<option value="0">--Select--</option>
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
										class="color_red">* </strong>Warranty</label>
								</div>
								<div class="col-md-8" id="warrentyshowid">
									<form:radiobutton id="Warranty1" value="Yes"
										path="warrantycheck" checked="checked"></form:radiobutton>
									&nbsp <label for="yes">Yes</label>&nbsp
									<form:radiobutton id="Warranty2" path="warrantycheck"
										value="No"></form:radiobutton>
									&nbsp <label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="WarrantyDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Warranty Upto</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="warrantydt" id="warranty"
										maxlength="10"
										class="form-control width88 display_inline rgb0"
										aria-required="true" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<!-- 	BISAG V1 220822 -->

					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"></strong>Model Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="type" id="type" class="form-control">
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
									<input type="text" id="model_type_other"
										name="model_type_other" class="form-control autocomplete"
										autocomplete="off" maxlength="50">
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
									<form:input type="text" id="year_of_proc" path="year_of_proc"
										class="form-control autocomplete" maxlength="4"
										autocomplete="off"></form:input>

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
									<form:input type="text" id="year_of_manufacturing"
										path="year_of_manufacturing" class="form-control autocomplete"
										maxlength="4" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"> </strong>Remarks </label>
								</div>
								<div class="col-md-8">
									<form:textarea id="remarks" path="remarks" maxlength="250"
										class="form-control autocomplete text_transform_upper"
										autocomplete="off"></form:textarea>
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
										class="form-control autocomplete" autocomplete="off"></input>
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
											<input type="text" id="machine_no1" name="machine_no1"
												class="form-control autocomplete" maxlength="30"
												autocomplete="off"></input>
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
							<div class="col-md-12">
								<div class="col-md-6 display_none" id="ip_addressDiv1">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												class="color_red">* </strong>IP Address(For Networked
												Device)</label>
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
				<!-- BISAG V1 220822 -->
				<!-- network components start -->
				<div class="panel-group display_none" id="accordionnetworkcomp5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_network"><b>Network
										Components</b></a>
							</h4>
						</div>
						<div id="collapsenetwork" class="panel-collapse collapse">
							<div class="card-body card-block">
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
													<form:input type="text" id="no_of_ports" path="no_of_ports"
														class="form-control autocomplete" autocomplete="off"
														maxlength="5"></form:input>
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
															<select name="network_features" id="network_features"
																class=" form-control">
																<option>Select Network Features</option>
															</select>
															<div class="overSelect"></div>
														</div>
														<div id="checkboxes_net" class="checkboxes checkboxcls">
															<div>
																<input type="text" id="network_features_search"
																	class="form-control autocomplete" autocomplete="off"
																	placeholder="Search Networks">
															</div>
															<div>
																<c:forEach var="item" items="${getNetwork_featuresList}"
																	varStatus="num">
																	<label for="one" class="network_featureslist">
																		<input type="checkbox" id="multisub_netid"
																		name="multisub_net" value="${item[0]}" /> ${item[1]}
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
													<form:select path="ethernet_port" id="ethernet_port"
														class="form-control">
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
													<form:select path="management_layer" id="management_layer"
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
						</div>
					</div>
				</div>
				<!-- network components end -->

				<!-- visulizer start -->
				<div class="panel-group display_none" id="accordionvisulizer5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title acc-heading" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_visulizer"><b
									id="visual_txt">Visulizer</b></a>
							</h4>
						</div>
						<div id="collapsevisulizer" class="panel-collapse collapse">
							<input type="hidden" id="visulizer" name="visulizer"
								value="visulizer"></input>
							<div class="card-body card-block">
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
													<form:input type="text" id="resolution" path="resolution"
														name="resolution" class="form-control autocomplete"
														autocomplete="off"></form:input>
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
													<form:input type="text" id="v_display_size"
														name="v_display_size" path="v_display_size"
														class="form-control autocomplete" autocomplete="off"></form:input>
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
													<form:select path="v_display_connector"
														name="v_display_connector" id="v_display_connector"
														class="form-control">
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
						</div>
					</div>
				</div>
				<!-- visulizer end -->

				<!-- PROJECTION SYS START -->

				<div class="panel-group display_none" id="accordionprojectionsys5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_projection"><b>PROJECTION
										SYS</b></a>
							</h4>
						</div>
						<div id="collapseprojection" class="panel-collapse collapse">
							<div class="card-body card-block">
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
													<form:input type="text" id="capacity" path="capacity"
														class="form-control autocomplete" autocomplete="off"></form:input>
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
													<div class="multiselect">

														<div class="selectBox" id="showCheckboxesid">
															<select name="hw_interface" id="hw_interface"
																class=" form-control">
																<option>Select Hardware Interface</option>
															</select>
															<div class="overSelect"></div>
														</div>
														<div id="checkboxes" class="checkboxes checkboxcls">
															<div>
																<input type="text" id="hw_interface_search"
																	class="form-control autocomplete" autocomplete="off"
																	placeholder="Search Hardware">
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
																	name="hd_hw_interface"
																	class="form-control autocomplete" autocomplete="off"></input>
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
				</div>

				<!-- END PROJECTION SYS -->


				<!-- MFD START -->
				<div class="panel-group display_none" id="accordionmfd5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title acc-heading" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_mfd"><b>MFD
										FETURES</b></a>
							</h4>
						</div>
						<div id="collapsemfd" class="panel-collapse collapse">
							<div class="card-body card-block">
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
											<form:radiobutton id="print" value="Yes" path="print"></form:radiobutton>
											&nbsp <label for="yes">Yes</label>&nbsp
											<form:radiobutton id="print" path="print" value="No"
												checked="checked"></form:radiobutton>
											&nbsp <label for="no">No</label>
										</div>
										<div class="1">
											<b><label>Scan</label></b>
										</div>
										<div class="col-md-2">
											<form:radiobutton id="scan" value="Yes" path="scan"></form:radiobutton>
											&nbsp <label for="yes">Yes</label>&nbsp
											<form:radiobutton id="scan" path="scan" value="No"
												checked="checked"></form:radiobutton>
											&nbsp <label for="no">No</label>
										</div>

										<div class="1">
											<b><label>Photography</label></b>
										</div>
										<div class="col-md-2">
											<form:radiobutton id="photography" value="Yes"
												path="photography"></form:radiobutton>
											&nbsp <label for="yes">Yes</label>&nbsp
											<form:radiobutton id="photography" path="photography"
												value="No" checked="checked"></form:radiobutton>
											&nbsp <label for="no">No</label>
										</div>

										<div class="1">
											<b><label>Colour</label></b>
										</div>
										<div class="col-md-2">
											<form:radiobutton id="colour" value="Yes" path="colour"></form:radiobutton>
											&nbsp <label for="yes">Yes</label>&nbsp
											<form:radiobutton id="colour" path="colour" value="No"
												checked="checked"></form:radiobutton>
											&nbsp <label for="no">No</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END MFD -->

				<!-- PRINTER START -->

				<div class="panel-group display_none" id="accordionprinter5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_printer"><b>PRINTER</b></a>
							</h4>
						</div>
						<div id="collapseprinter" class="panel-collapse collapse">
							<div class="card-body card-block">
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
														for="monochrome">Monochrome</label>&nbsp <input
														type="radio" id="monochrome_color_radio2"
														name="monochrome_color" value="color" checked="checked">&nbsp
													<label for="color">Color</label><br>
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
													<form:select path="paper_size" id="paper_size"
														class="form-control">
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
													<form:select path="connectivity_type"
														id="connectivity_type" class="form-control">
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
														name="connectivity_other"
														class="form-control autocomplete" autocomplete="off"
														maxlength="50">
												</div>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- PRINTER END -->

				<!-- UPS START -->
				<div class="panel-group display_none" id="accordionups5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_ups"><b>UPS</b></a>
							</h4>
						</div>
						<div id="collapseups" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>UPS Capacity </label>
												</div>
												<div class="col-md-8">
													<form:select path="ups_capacity" id="ups_capacity"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${UpsCapacity}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- UPS END -->

				<!-- MONITER START -->

				<div class="panel-group display_none" id="accordionmoniter5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title acc-heading" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_moniter"><b>MONITOR</b></a>
							</h4>
						</div>
						<div id="collapsemoniter" class="panel-collapse collapse">
							<div class="card-body card-block">
								<input type="hidden" id="monitor" name="monitor" value="monitor">
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
													<form:input type="text" id="display_type"
														name="display_type" path="display_type"
														class="form-control autocomplete" autocomplete="off"></form:input>
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
													<form:input type="text" id="display_size"
														name="display_size" path="display_size"
														class="form-control autocomplete" autocomplete="off"></form:input>
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
													<form:select path="display_connector"
														name="display_connector" id="display_connector"
														class="form-control">
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
						</div>
					</div>
				</div>

				<!-- MONITER END -->
				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title acc-heading" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_warrenty"><b>SERVICE
										DETAILS</b></a>
							</h4>
						</div>
						<div id="collapsewarrantey" class="panel-collapse collapse">
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
														class="color_red">* </strong>UN-Serviceable State</label>
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
														id="uploadedFileName1" class="uploaded-file-name f_10"></span><input
														type="button" class="btn btn-success btn-sm display_none"
														id="downloadbtn" value="File" /></i>
												</div>
											</div>
										</div>
									</div>

								</div>

								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6 display_none" id="unsv_div">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Un-servicable from</label>
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
							<h4 class="panel-title main_title acc-heading" id="a_div5">
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
														class="form-control autocomplete" />

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
													class="color_red"></strong>CRV No.</label>
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
														id="uploadedFileName" class="uploaded-file-name f_10"></span><input
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
									<!-- 									<div class="row"> -->
									<!-- 										<div class="col-md-6"> -->
									<!-- 											<div class="row form-group"> -->
									<!-- 												<div class="col-md-4"> -->
									<!-- 													<label class=" form-control-label"><strong -->
									<!-- 														class="color_red">*</strong>Approved By OIS </label> -->
									<!-- 												</div> -->
									<!-- 												<div class="col-md-8" id="approveoisid"> -->
									<%-- 													<form:radiobutton id="approveois1" value="Yes" --%>
									<%-- 														path="approveois"></form:radiobutton> --%>
									<!-- 													&nbsp <label for="approveois1">Yes</label>&nbsp -->
									<%-- 													<form:radiobutton id="approveois2" path="approveois" --%>
									<%-- 														value="No"></form:radiobutton> --%>
									<!-- 													&nbsp <label for="antiviruscheck2">No</label> -->
									<!-- 												</div> -->
									<!-- 											</div> -->
									<!-- 										</div> -->
									<!-- 									</div> -->

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
			<div class="card-footer" align="center" class="form-control">
				<a href="${screen_url}" id="clear_1" class="btn btn-success btn-sm">Back</a>
				<input type="submit" class="btn btn-primary btn-sm" value="Save"
					id="save_btn"> <input type="submit"
					class="btn btn-primary btn-sm" value="Update" id="update_btn">
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
	document.getElementById('a_ups').onclick = 
		function() {
		return divN(this);
  	};
	document.getElementById('a_moniter').onclick = 
		function() {
		return divN(this);
  	};
		document.getElementById('a_warrenty').onclick = 
			function() {
			return divN(this);
	  	};
	
	document.getElementById('s_state').onchange = 
		function() {
		return serviceStChange();
  	};
  	document.getElementById('unserviceable_state').onchange = 
		function() {
		return selectBer();
  	};
	document.getElementById('downloadbtn').onclick = 
		function() {
		return download_file();
  	};
	document.getElementById('b_cost').onkeypress = 
		function() {
		return isNumber(event);
  	};
	
		document.getElementById('a_budget').onclick = 
			function() {
			return divN(this);
	  	};
	  
		document.getElementById('connectivity_type').oninput = 
			function() {
			return this.value = this.value.toUpperCase();
	  	};
		document.getElementById('connectivity_type').onkeypress = 
			function() {
			return onlyAlphaNumeric(event);
	  	};
		document.getElementById('connectivity_type').onchange = 
			function() {
			return searchConnectivityTypeOther();
	  	};
    	
		document.getElementById('connectivity_type').onchange = 
			function() {
			return fn_connectivity_type_other();
	  	};
	document.getElementById('a_printer').onclick = 
		function() {
		return divN(this);
  	};
  	document.getElementById('a_mfd').onclick = 
		function() {
		return divN(this);
  	};
	document.getElementById('hardwareid').onclick = 
		function() {
		return hardware();
  	};
	document.getElementById('showCheckboxesid').onclick = 
		function() {
		return showCheckboxes();
  	};
	document.getElementById('a_visulizer').onclick = 
		function() {
		return divN(this);
  	};
	document.getElementById('a_projection').onclick = 
		function() {
		return divN(this);
  	};
	
	document.getElementById('networkshowid').onclick = 
		function() {
		return NetworkshowCheckboxes();
  	};
		
			document.getElementById('multisub_netid').onclick = 
				function() {
				return network();
		  	};
		
  	
			document.getElementById('no_of_ports').onkeypress = 
				function() {
				return isNumber(event);
		  	};
		  	document.getElementById('a_network').onclick = 
				function() {
				return divN(this);
		  	};
			document.getElementById('is_networked1').onclick = 
				function() {
				return fn_isNetwork(1);
		  	};
		  	document.getElementById('is_networked2').onclick = 
				function() {
				return fn_isNetwork(1);
		  	};
			
	document.getElementById('machine_no1').onkeypress = 
		function() {
		return onlyAlphaNumeric(event, this);
  	};
  	document.getElementById('machine_no1').onchange = 
		function() {
		return searchMachineNumber();
  	};
  	document.getElementById('machine_no1').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
  	};
	
	
	document.getElementById('model_no1').onkeypress = 
		function() {
		return onlyAlphaNumeric(event, this);
  	};
  	document.getElementById('model_no1').oninput = 
		function() {
		return this.value = this.value.toUpperCase();
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
  	
  	
	document.getElementById('model_name').onchange = 
		function() {
		return fn_make_other();
  	};
	document.getElementById('warrentyshowid').onclick = 
		function() {
		return warrenty_show();
  	};
  	document.getElementById('type').onchange = 
		function() {
		return fn_model_type_other();
  	};
  	
	
  	document.getElementById('year_of_proc').onkeypress = 
		function() {
		return isNumber(event);
  	};  	document.getElementById('year_of_proc').onkeyup = 
		function() {
		return validateYear(this);
  	};
	document.getElementById('year_of_proc').onblur = 
		function() {
		return validateYearLn(this);
  	};
  	
  	document.getElementById('year_of_manufacturing').onkeypress = 
		function() {
		return isNumber(event);
  	};  	document.getElementById('year_of_manufacturing').onkeyup = 
		function() {
		return validateYear(this);
  	};
	document.getElementById('year_of_manufacturing').onblur = 
		function() {
		return validateYearLn(this);
  	};
    	
    	
    	
  	
    	document.getElementById('model_type_other').oninput = 
    		function() {
    		return this.value = this.value.toUpperCase();
      	};
    	document.getElementById('model_type_other').onkeypress = 
    		function() {
    		return onlyAlphaNumeric(event);
      	};
    	document.getElementById('model_type_other').onchange = 
    		function() {
    		return searchTypeOfModelOther();
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
      	
      	
    	document.getElementById('brand_other').oninput = 
    		function() {
    		return this.value = this.value.toUpperCase();
      	};
    	document.getElementById('brand_other').onkeypress = 
    		function() {
    		return onlyAlphabets(event);
      	};
    	document.getElementById('brand_other').onchange = 
    		function() {
    		return searchMakeOther();
      	};
	
	
	document.getElementById('make_name').onchange = 
		function() {
		return fn_modelName(),fn_brand_other();
  	};
    	document.getElementById('peri_hw_other').oninput = 
    		function() {
    		return this.value = this.value.toUpperCase();
      	};
    	document.getElementById('peri_hw_other').onkeypress = 
    		function() {
    		return onlyAlphabets(event);
      	};
    	document.getElementById('peri_hw_other').onchange = 
    		function() {
    		return searchTypeOfPeripheralOther();
      	};
	document.getElementById('type_of_hw').onchange = 
		function() {
		return fn_type();
  	};
	document.getElementById('type_of_hw').onchange = 
		function() {
		return fn_type_hw_other();
  	};
	document.getElementById('assets_type').onchange = 
		function() {
		
		return fn_Peripheral(),fn_makeName(), fn_hide_show();
  	};
//   	document.getElementById('assets_type').onchange = 
// 		function() {
// 		return 
//   	};
//   	document.getElementById('assets_type').onchange = 
// 		function() {
// 		return fn_hide_show();
//   	};
	document.getElementById('b_head').onchange = 
		function() {
		return fn_B_Head();
  	};
  	
	document.getElementById('proc_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('proc_date').onfocus = 
		function() {
		return this.style.color='#000000';
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
  	
  	
  	
  	
	document.getElementById('unsv_from_dt1').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('unsv_from_dt1').onfocus = 
		function() {
		return this.style.color='#000000';
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
  	
  	
  	
 	
	document.getElementById('warranty').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('warranty').onfocus = 
		function() {
		return this.style.color='#000000';
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
  	
  	
  	
  	

});


$(document).ready(function() {
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
	

/* 	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  {
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	} */
	
   /* var sus_no1 = '${roleSusNo}';
  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no1:sus_no1}).done(function(data) {
  		var l=data.length-1;
  		var enc = data[l].substring(0,16);    	   	 
  	 	$("#unit_name").text(dec(enc,data[0]));
  		}).fail(function(xhr, textStatus, errorThrown) {
	  }); */ 
	var id=$('#id').val();
	if(id == 0)
	{
	$('#save').show();
	$('#update').hide();
	$('#save_btn').show();
	$('#clear_1').hide();
	$('#update_btn').hide();
// 	$('#supplier_field').hide();
	}
else{
	$('#save').hide();
	$('#update').show();
	$('#clear_1').show();
	$('#save_btn').hide();
	$('#update_btn').show();
	
}
	
	$.ajaxSetup({
		 async : false
});
	
	
	$("#ip_address1").tooltip();
	$("#b_cost").tooltip();
	$("#b_cost").css('cursor','pointer');
	datepicketDate('warranty');
	datepicketDate('proc_date');
	
	
	 $( "#warranty" ).datepicker( "option", "maxDate", null);
	
	// ** AHM BISAG **//
	datepicketDate('unsv_from_dt1')
	
	// ** END AHM BISAG **//
	
	if('${PeripheralCmd.id}' != "0"){
		selectBer();
		
		$("#countDiv").hide();
		$("#machine_no1").val('${PeripheralCmd.machine_no}');
		$("#crv_no").val('${PeripheralCmd.crv_no}');
		$("#gem_no1").val('${PeripheralCmd.gem_no}');
		$("#user_name").val('${PeripheralCmd.username}');
		$("#supplierId").val('${PeripheralCmd.supplier_name}');
		getSupplierData('${PeripheralCmd.supplier_name}'); 
		
		
		 var filename = '${PeripheralCmd.u_file}';
		    var index = filename.indexOf('/srv/IT/');

 if (filename) {
     var fileName2 = filename.substring(filename.lastIndexOf('/') + 1); 
     $("#uploadedFileName").text(fileName2); 
     $('#download_i').removeClass('display_none');
     $('#u_file1').addClass('display_none');
 }
		
 
 
	 var filename5 = '${PeripheralCmd.br_certificate}';
	
	    var index = filename.indexOf('/srv/IT/');

if (filename5) {
var fileName6 = filename5.substring(filename.lastIndexOf('/') + 1); 
$("#uploadedFileName1").text(fileName6); 
$('#download_i1').removeClass('display_none');
$('#br_certificate1').addClass('display_none');
}
	}
	
	$("#warranty").val((ParseDateColumncommission('${PeripheralCmd.warranty}')));	

// 	$("#warranty").val(('${PeripheralCmd.warranty}').substring(0, 10));	
	$("input[name='warrantycheck'][value='${PeripheralCmd.warrantycheck}']").prop("checked",true);
	$("#proc_date").val((ParseDateColumncommission('${PeripheralCmd.proc_date}')));	
	
//** BISG AHM **// 

	
	if ('${PeripheralCmd.s_state}' == "" || '${PeripheralCmd.s_state}' == null || '${PeripheralCmd.s_state}' == "0"){
		$("select#s_state").val(0);
	}else{
		$("select#s_state").val('${PeripheralCmd.s_state}');
	}
	
	$("select#unserviceable_state").val(('${PeripheralCmd.unserviceable_state}'));
	$("#unsv_from_dt1").val((ParseDateColumncommission('${PeripheralCmd.unsv_from_dt}')));	

	//** END BISG AHM **//
	
// 	$("#proc_date").val(('${PeripheralCmd.proc_date}').substring(0, 10));
	

	
	
	fn_Peripheral();fn_makeName();fn_hide_show();
		$("select#make_name").val("${PeripheralCmd.make_name}");
		
		
		fn_brand_other();
		$("#brand_other").val('${make_mstr_other}');	
		
		fn_modelName();
// 		fn_Peripheral();		
		$("select#model_name").val('${PeripheralCmd.model_name}');
		
		fn_make_other();
		$("#model_other").val('${model_mstr_other}');
		
		
		
		
		$("select#type_of_hw").val('${PeripheralCmd.type_of_hw}');
		fn_type();
		if ('${PeripheralCmd.type}' == "") {
			$('select#type').val("0");
		}else {
			$('select#type').val("${PeripheralCmd.type}");
		}
		
		fn_type_hw_other();
		
		$("#peri_hw_other").val('${type_hw_mstr}');
		
		
		fn_model_type_other();
		$("#model_type_other").val('${type_mstr}');
		
		
		fn_connectivity_type_other();
		$("#connectivity_other").val('${connect_mstr}');
		
		
		selectBer();
		
		
		$("#make_mstr_id").val('${make_mstr_id}');	
		$("#model_mstr_id").val('${model_mstr_id}');	
		$("#type_hw_id").val('${type_hw_id}');	
		$("#pro_type_id").val('${pro_type_id}');	
		$("#pro_connect_id").val('${pro_connect_id}');
		if('${PeripheralCmd.section}'!="")
			{
			$("#section").val('${PeripheralCmd.section}');
			
			}

		var sus_no ='${PeripheralCmd.section}';

		
		
		$("input[name='is_networked1'][value='${PeripheralCmd.is_networked}']").prop("checked",true);
		$("input#ip_address1").val('${PeripheralCmd.ip_address}');
// 		anti_show();
		$("input[name='print'][value='${PeripheralCmd.print}']").prop("checked",true);
		$("input[name='scan'][value='${PeripheralCmd.scan}']").prop("checked",true);
		$("input[name='photography'][value='${PeripheralCmd.photography}']").prop("checked",true);
		$("input[name='colour'][value='${PeripheralCmd.colour}']").prop("checked",true);
		$("input[name='monochrome_color'][value='${PeripheralCmd.monochrome_color}']").prop("checked",true);
		
		serviceStChange();
		fn_isNetwork(1);
		
		fn_B_Head();
		$("select#b_code").val('${PeripheralCmd.b_code}');	
// 		$('select#year_of_manufacturing').val('${PeripheralCmd.year_of_manufacturing}');
// 		fn_makeName();
// 		$('select#make_name').val('${PeripheralCmd.make_name}');
// 		fn_modelName();
// 		$('select#model_name').val('${PeripheralCmd.model_name}');				
// 		$('select#type').val('${PeripheralCmd.type}');	
		$('select#ups_capacity').val('${PeripheralCmd.ups_capacity}');
// 		$('select#paper_size').val('${PeripheralCmd.paper_size}');
// 		$('select#display_connector').val('${PeripheralCmd.display_connector}');
// 		$('select#v_display_connector').val('${PeripheralCmd.v_display_connector}');
// 		$('select#s_state').val('${PeripheralCmd.s_state}');
// 		serviceStChange();
// 		$('select#unserviceable_state').val('${PeripheralCmd.unserviceable_state}');	
// 		datepicketYear('year_of_proc1');
// 		datepicketYear('year_of_manufacturing1');

$("select#maintainAgency").val(('${PeripheralCmd.maintainagency}'));

		var hardware = '${PeripheralCmd.hw_interface}';
		var subjectslist = hardware.split(',');
					for(k = 0; k < subjectslist.length; k++) {
						$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
						/* $("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')'); */
					}
					
					
					var network = '${PeripheralCmd.network_features}';
					var networklist = network.split(',');
								for(kn = 0; kn < networklist.length; kn++) {
									$("input[type=checkbox][name='multisub_net'][value='" + networklist[kn] + "']").prop("checked", true);
									/* $("#sub_quali option:first").text('Subjects(' + subjectslist.length + ')'); */
								}
								 $("#hd_network_features").val(network);
	
});	
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

function Validate(){
	
	if($("#section").val()==0 || $("#section").val()==""){
		alert("Please Select Section");
		$("#section").focus();
		return false;
	}
	
// 	if($("#user_name").val()=="" || $("#user_name").val()==''){
// 		alert("Please Enter User Name");
// 		$("#user_name").focus();
// 		return false;
// 	}
	
	if($("#assets_type").val()==0 || $("#assets_type").val()==""){
		alert("Please Select Peripheral Type  ");
		$("#assets_type").focus();
		return false;
	}
	
	if($("#type_of_hw").val()==0 || $("#type_of_hw").val()=="" ){
		alert("Please Select Type of Peripheral HW ");
		$("#type_of_hw").focus();
		return false;
	}
	
	var text = $("#type_of_hw option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#peri_hw_other").val().trim() == ""){
			alert("Please Enter Type Of Peripheral HW Other. ");
			$("#peri_hw_other").focus();
			return false;
		}
	
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
	
// 	if( $("#machine_no").val()==""){
// 		alert("Please Enter Machine No. ");
// 		$("#machine_no").focus();
// 		return false;
// 	}

	var warrantycheckChecked=$('input[name="warrantycheck"]:checked').val();
	
	if(warrantycheckChecked.toUpperCase()=="YES"){
		if($("#warranty").val()==0 || $("#warranty").val()=="" || $("#warranty").val() == "DD/MM/YYYY"){
			alert("Please Select Warranty Upto");
			$("#warranty").focus();
			return false;
		}
	}
	else{
		$("#warranty").val('');
	}
	
	
	///BISAG V1 220822///
	
// 	if($("#type").val()==0 || $("#type").val()==""){
// 		alert("Please Select Model Type ");
// 		$("#type").focus();
// 		return false;
// 	}
	
	var text = $("#type option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			if( $("#model_type_other").val().trim() == ""){
				alert("Please Enter Model Type Other. ");
				$("#model_type_other").focus();
				return false;
			}
		
	}
	
// 	if( $("#model_no").val()==""){
// 		alert("Please Enter Model Number ");
// 		$("#model_no").focus();
// 		return false;
// 	}
	
// 	if (!$("input[name='is_networked']").is(':checked')) {
// 		   alert('Please Check IS Networked ');
// 		   return false;
// 		}
	
	
	
// 	var is_networked=$('input[name="is_networked"]:checked').val();
	
// 	if(is_networked.toUpperCase()=="YES"){
// 		if( $("#ip_address").val()==""){
// 			 	alert("Please Enter IP Address(For Networked Device)")  
// 				$("#ip_address").focus();
// 				return false;
// 		}
		
// 		if( $("#ip_address").val()!=""){
// 			 if(!ValidateIPaddress( $("#ip_address").val())){
// 			 	alert("You have entered an invalid IP Address!")  
// 				$("#ip_address").focus();
// 				return false;
// 			 }
// 		}
// 	}
// 	else{
// 		$("#ip_address").val('');
// 	}
	
	
// 	if( $("#warranty").val()=="" || $("#warranty").val().toUpperCase()=="DD/MM/YYYY"){
// 		alert("Please Enter Warranty");
// 		$("#warranty").focus();
// 		return false;
// 	}


var len=$("#assetcount").val();
	
		var i=1;
	for(i;i<=len;i++)
		{
// 		if( $("#model_no"+i).val()==""){
// 			alert("Please Enter Model Number ");
// 			$("#model_no"+i).focus();
// 			return false;
// 		}
		
		if( $("#machine_no"+i).val()==""){
			alert("Please Enter Machine No. ");
			$("#machine_no"+i).focus();
			return false;
		}
		
		/*  function validateCode() {
			   
			   
//			   var machine_number = $("#model_number1").val();
			   
			   var  testString = $("input#machine_no"+i).val();
				 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
				
				 }
				 else{
					 alert("Please enter letters and numbers only.");
					 $("#machine_no"+i).val("");
				 }
		   } */
		
		if (!$("input[name='is_networked"+i+"']").is(':checked')) {
			   alert('Please Check Is Networked ');
			   return false;
			} 
		
		var is_networked=$('input[name="is_networked'+i+'"]:checked').val();
		
		if(is_networked.toUpperCase()=="YES"){
			if( $("#ip_address"+i).val()==""){
				 	alert("Please Enter IP Address(For Networked Device)")  
					$("#ip_address"+i).focus();
					return false;
			}
			
			if( $("#ip_address").val()!=""){
				 if(!ValidateIPaddress( $("#ip_address"+i).val())){
				 	alert("You have entered an invalid IP Address!")  
					$("#ip_address"+i).focus();
					return false;
				 }
			}
		}
		else{
			$("#ip_address"+i).val('');
		}
		
		if( $("#ip_address"+i).val()!=""){
			 if(!ValidateIPaddress( $("#ip_address"+i).val())){
			 	alert("You have entered an invalid IP Address!")  
				$("#ip_address"+i).focus();
				return false;
			 }
			}
		
		
		for(j=i+1;j<=len;j++){
			if( $("#machine_no"+i).val() == $("#machine_no"+j).val()){
				alert("You have Entered Duplicate Machine Number!")  
				$("#machine_no"+j).focus();
				return false;
			}
		}

	}
	
	
	
	 var b =$("select#assets_type").val();
	 if(b=='8')
		 {
		 if( $("#ups_capacity").val()=="" || $("#ups_capacity").val()=="0"){
				alert("Please Select UPS Capacity ");
				$("#ups_capacity").focus();
				return false;
			}
		 
		 }	 
	 else if(b=='12')
		{
		 if (!$("input[name='monochrome_color']").is(':checked')) {
			   alert('Please Check Monochrome/Color ');
			   return false;
			}
		 if( $("#paper_size").val()=="" || $("#paper_size").val()=="0"){
				alert("Please Select Max Paper Size ");
				$("#paper_size").focus();
				return false;
			}
		 if( $("#connectivity_type").val()=="" || $("#connectivity_type").val()=="0"){
				alert("Please Select Connectivity Type ");
				$("#connectivity_type").focus();
				return false;
			}
		 
		 
		 
		}
	   
	 else if(b=='13')
		{
		 if (!$("input[name='print']").is(':checked')) {
			   alert('Please Check All Features Avlb with MFD ');
			   return false;
			}
		 if (!$("input[name='scan']").is(':checked')) {
			   alert('Please Check All Features Avlb with MFD ');
			   return false;
			}
		 if (!$("input[name='photography']").is(':checked')) {
			   alert('Please Check All Features Avlb with MFD ');
			   return false;
			}
		 if (!$("input[name='colour']").is(':checked')) {
			   alert('Please Check All Features Avlb with MFD ');
			   return false;
			}
		 
		 
		}
	   
		
	 else if(b=='14')
		{
		 
		
		 if( $("#capacity").val()==""){
			 	alert("Please Enter Capacity(Lumens)")  
				$("#capacity").focus();
				return false;
		}
		 if( $("#hw_interface").val()=="" || $("#hw_interface").val()=="0" ){
			 	alert("Please Select Hardware Interface")  
				$("#hw_interface").focus();
				return false;
		}
		 
		}
		
	 else if(b=='15' || b=='19')
		{
		 if( $("#resolution").val()==""){
			 	alert("Please Enter Resolution")  
				$("#resolution").focus();
				return false;
		}
		 if( $("#v_display_size").val()==""){
			 	alert("Please Enter Display Size")  
				$("#v_display_size").focus();
				return false;
		}
		 if( $("#v_display_connector").val()=="" || $("#v_display_connector").val()=="0" ){
			 	alert("Please Select Display Connector")  
				$("#v_display_connector").focus();
				return false;
		}
		}
	  	
	 else if(b=='16')
		{
		 if( $("#no_of_ports").val()==""){
			 	alert("Please Enter No Of Ports")  
				$("#no_of_ports").focus();
				return false;
		}
		 if( $("#hd_network_features").val()=="" || $("#hd_network_features").val()=="0" ){
			 	alert("Please Select Network Features")  
				$("#hd_network_features").focus();
				return false;
		}
		 if( $("#ethernet_port").val()=="" || $("#ethernet_port").val()=="0" ){
			 	alert("Please Select Ethernet Port")  
				$("#ethernet_port").focus();
				return false;
		}
// 		 if( $("#management_layer").val()=="" || $("#management_layer").val()=="0" ){
// 			 	alert("Please Select Management Layer")  
// 				$("#management_layer").focus();
// 				return false;
// 		}
		 
		}
	 
	
	else if (b == '2') {
			if ($("#display_type").val() == "") {
				alert("Please Enter Display Type")
				$("#display_type").focus();
				return false;
			}
			if ($("#display_size").val() == "") {
				alert("Please Enter Display Size")
				$("#display_size").focus();
				return false;
			}
			if ($("#display_connector").val() == ""
					|| $("#display_connector").val() == "0") {
				alert("Please Select Display Connector")
				$("#display_connector").focus();
				return false;
			}

		}

		if ($("#s_state").val() == "" || $("#s_state").val() == "0") {
			alert("Please Select Serviceable State");
			$("#s_state").focus();
			return false;
		}
		if ($("#s_state").val() == 2) {
			debugger;
			if ($("#unserviceable_state").val() == "" || $("#unserviceable_state").val() == "0") {
				alert("Please Select UN-Serviceable State");
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
			}
			}
			
		}
// 		if ($("#b_cost").val() == "") {
// 			alert("Please Enter Proc Cost");
// 			$("#b_cost").focus();
// 			return false;
// 		}

		
		if( $("#b_cost").val()=="0" ){
			alert("Proc Cost Must be Greater Than Zero");
			$("#b_cost").focus();
			return false;
		} else if ($("#b_cost").val() > 1000000000) {
			alert("Proc Cost cannot be greater Than 100 Crores");
			$("#b_cost").focus();
			return false;
		}
		if ($("#b_head").val() == "" || $("#b_head").val() == "0") {
			alert("Please Select Budget Head");
			$("#b_head").focus();
			return false;
		}
		if( $("#proc_date").val() == "" || $("#proc_date").val() == "DD/MM/YYYY"){
			alert("Please Select Crv Date");
			$("#proc_date").focus();
			return false;
		}
		
	

		if ($("#b_code").val() == "" || $("#b_code").val() == "0") {
			alert("Please Select Budget Code");
			$("#b_code").focus();
			return false;
		}
		if ($("#proc_date").val() == ""
				|| $("#proc_date").val().toUpperCase() == "DD/MM/YYYY") {
			alert("Please Enter Proc Date");
			$("#proc_date").focus();
			return false;
		} 
		if('${PeripheralCmd.id}' == 0)
		{
		if( $("#u_file1").val()=="")
		{
		alert("Please Upload CRV Document.");
		$("#u_file1").focus();
		return false;
		}
		}
// 		if (!$("input[name='approveois']").is(':checked')) {
// 			   alert('Please Check Approve By OIS ');
// 			   return false;
// 			} 

		//var approveChecked = $('input[name="approveois"]:checked').val();
		//if (approveChecked.toUpperCase() === "NO") {
			
		    if ($("#supplier").val() === "") {
		        alert("Please Enter Supplier Name");
		        $("#supplier").focus();
		        return false;
		    }
	//}
	var fileInput = $("#u_file1");

			
			
			var fileName = fileInput.val();

			// Check if the file is a PDF
			if (!fileName.endsWith(".pdf")) {
			    alert("Please upload a valid  Crv Document In PDF document.");
			    fileInput.focus();
			    return false;
			}

	}

		

	function ValidateIPaddress(ipaddress) {
		if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
				.test(ipaddress)) {
			return (true)
		}

		return (false)
	}

</script>

<script nonce="${cspNonce}">

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
 		}  else {				
 			$("#"+sib_id).addClass("show");	
 			$("#"+id).removeClass("collapsed");
 	    }				
 }
 
/* function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48||charCode> 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
} */

 function fn_Peripheral() {	 		

		var peripheral_type = $("select#assets_type").val();
		$.post("getHWNameList?" + key + "=" + value, {
			peripheral_type: peripheral_type
		}).done(function(j) {
			
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#type_of_hw").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 function fn_type() {	 		
		var type_of_hw = $("select#type_of_hw").val();
		$.post("getTypeList?" + key + "=" + value, {
			type_of_hw: type_of_hw
		}).done(function(j) {
			
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#type").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 
 function fn_makeName() {			
		var assets_name = $("select#assets_type").val();
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
	
	
function serviceStChange()
{
	 var a =$("select#s_state").val();
	 if(a == '2')
		 {
		  $("#un_show").show();
		  $("#unsv_div").show();
		  $("#maintaince_div").show();
		 }
	 else
		 {
		  $("#un_show").hide();
		  $("#maintaince_div").hide();
		  $("#unsv_div").hide();
			$("#file_show").hide();
		  //$("select#unserviceable_state").val('0');
		 }
}

function fn_isNetwork(ind){
	  var is_networked = $('input[name="is_networked' + ind + '"]:checked').val();

	  if (is_networked && typeof is_networked === 'string' && is_networked.toUpperCase() === "YES") {
		    $("#ip_addressDiv" + ind).show();
		} else {
		    $("#ip_addressDiv" + ind).hide();
		    $("#ip_address" + ind).val('');
		}
	}
///BISAG V2 240822///
function fn_B_Head() {
	
	
	var b_head = $("select#b_head").val();
	


	$.post("getBudgetCodeList?" + key + "=" + value, {
	    b_head: b_head
	}).done(function(j) {
	    var options = '<option value="0">--Select--</option>';
	    for (var i = 0; i < j.length; i++) {
	        options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">' + j[i][1] + '</option>';
	    }
	    $("select#b_code").html(options);
	}).fail(function(xhr, textStatus, errorThrown) {
	    console.error("Error fetching budget codes:", textStatus, errorThrown);
	});


}

///BISAG V2 240822 END///

function validateYear(e){
	var year = $(e).val();
	
	if (year.length == 4  && (parseInt(year) <= 1890 || parseInt(year) >=2099)) {
		alert("Please Enter Year In Range");
		$(e).val("");
	}
}


function validateYearLn(e){
	var year = $(e).val();
	
	if (year.length >= 1 && year.length < 4 ) {
		alert("Please Enter Complete Year");
		$(e).val("");
		$(e).focus();
	}
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
// 			return false;
		}
		if (Assetcount=="" ) {
			Assetcount = 1;
		}
		
			var options="";
			for(var i = 1; i <= parseInt(Assetcount); i++) {
				options += '<div class="row" style="border: solid #b7b6b0 5px;padding: 10px;margin: 5px;">'+
						'<div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
							'<label class=" form-control-label"><strong style="color: red;"></strong>Model Number</label>	</div>'+
						'<div class="col-md-8">'+
						/* '<input type="text" id="model_no'+i+'" name="model_no'+i+'" maxlength="20" onkeypress="return onlyAlphaNumeric(event, this)"  oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off"></input>'+ */
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" id="machine_no'+i+'" name="machine_no'+i+'" maxlength="20" onkeypress="return onlyAlphaNumeric(event, this)"  class="form-control autocomplete"  oninput="this.value = this.value.toUpperCase()" autocomplete="off"></input>'+
						'</div></div></div></div><div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'			<label class=" form-control-label"><strong style="color: red;">* </strong>Is Networked </label>'+
						'		</div><div class="col-md-8" >'+
						'			<input type="radio"  id="is_networked"  value="Yes"  onclick="fn_isNetwork('+i+')"'+
 						'				name="is_networked'+i+'"  ></input>&nbsp <label for="yes">Yes</label>&nbsp    '+
  						'			<input type="radio"  id="is_networked2" name="is_networked'+i+'"  value="No"  onclick="fn_isNetwork('+i+')"'+
   						'				 checked="checked"></input>&nbsp <label for="no">No</label>	</div></div></div>'+
						'<div class="col-md-6" id="ip_addressDiv'+i+'" style="display: none"><div class="row form-group"><div class="col-md-4">'+
						'			<label class=" form-control-label"><strong style="color: red;">* </strong>IP Address(For Networked Device)</label>'+
						'		</div><div class="col-md-8">'+
						'			<input title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address." type="text" id="ip_address'+i+'" name="ip_address'+i+'" maxlength="15" class="form-control autocomplete" autocomplete="off"></input>'+
						'		</div></div></div></div></div>';


			}
			$("#multiAssetDiv").html(options);
	} 

function fn_hide_show()
{
	 var b =$("select#assets_type").val();
	 if(b=='8')
		 {
		  $("#accordionups5").show();
		 }
	 else
		{
		 $("#accordionups5").hide();
		 $("select#ups_capacity").val('0');
		 $("input#serial_no").val("");  		 
		}
	if(b=='12')
		{
		 $("#accordionprinter5").show();
		}
	else
		{
		 $("#accordionprinter5").hide();
		 $("input#monochrome_color").val("");
		 $("select#paper_size").val("0");
		}   
	if(b=='13')
		{
		 $("#accordionmfd5").show();
		}
	else
		{
		 $("#accordionmfd5").hide();
		}    
		
	if(b=='14')
		{
		
		 $("#accordionprojectionsys5").show();
		}
	 else
		{
		 $("#accordionprojectionsys5").hide();
		 $("input#capacity").val("");
		}  	
	if(b=='15'|| b=='19')
		{
		if(b=='19')
			{
			$("#visual_txt").text("Monitor");
			
			}
		if(b=='15')
		{
			$("#visual_txt").text("Visulizer");
		}
		 $("#accordionvisulizer5").show();
		}
	else
		{
		 $("#accordionvisulizer5").hide();
		 $("input#resolution").val("");
		}  	
	if(b=='16')
		{
		 $("#accordionnetworkcomp5").show();
		}
	else
		{
		 $("#accordionnetworkcomp5").hide();
		 $("input#no_of_ports").val("");
		}  
	 if(b=='2')
		 {
		  $("#accordionmoniter5").show();
		 }
	 else
		 {
		  $("#accordionmoniter5").hide();
		  $("input#display_type").val("");  
		  $("input#display_size").val("");  
		  $("select#display_connector").val('0');
		 }	
  }






function onlyAlphabets(e, t) {
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
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
	
function warrenty_show()
{
	 if ($("#Warranty1").prop("checked")) {
		 $("#WarrantyDiv").show();
			}
		else{
			$('#WarrantyDiv').hide();
			$('#warranty').val('DD/MM/YYYY');
		}
	
}


function showCheckboxes() {
	  var checkboxes = document.getElementById("checkboxes");
	  $("#checkboxes").toggle();
	  $("#hw_interface_search").val(''); 
	  
	  $('.hw_interfacelist').each(function () {       
	  $(this).show()
	})
	}
	
	
$("#hw_interface_search").keyup(function () {
	  var re = new RegExp($(this).val(), "i")
	  $('.hw_interfacelist').each(function () {
	      var text = $(this).text(),
	          matches = !! text.match(re);
	      $(this).toggle(matches)
	  })
	});
	


function NetworkshowCheckboxes() {
	  var checkboxes = document.getElementById("checkboxes_net");
	  $("#checkboxes_net").toggle();
	  $("#network_features_search").val(''); 
	  
	  $('.network_featureslist').each(function () {       
	  $(this).show()
	})
	}
	
	
$("#network_features_search").keyup(function () {
	  var re = new RegExp($(this).val(), "i")
	  $('.network_featureslist').each(function () {
	      var text = $(this).text(),
	          matches = !! text.match(re);
	      $(this).toggle(matches)
	  })
	});
	
	
	
	function hardware() {
		var subjectvar = $('input[name="multisub"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar.join(",");
		 $("#hd_hw_interface").val(subject);
	}
	
	
	function network() {
		var subjectvar1 = $('input[name="multisub_net"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var subject = subjectvar1.join(",");
		 $("#hd_network_features").val(subject);
	}
	
	function searchMachineNumber(){
	
		var machine_no = $("#machine_no1").val();
		$.post("getAllMachineNumber?" + key + "=" + value, {
			machine_no: machine_no
		}).done(function(j) {
						
	 		 if(j.length > 0){
	 			 
	 			 alert("This Machine No already Exist.");
	 			$("#machine_no1").val("");
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
	 
	
	 function fn_type_hw_other() {
		 var text = $("#type_of_hw option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#peri_hw_hid").show();
			}
			else{
				$("#peri_hw_hid").hide();
				$("#peri_hw_other").val('');
			}
	 	}
	 function fn_model_type_other() {
		 var text = $("#type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#model_type_other_hid").show();
			}
			else{
				$("#model_type_other_hid").hide();
				$("#model_type_other").val('');
			}
	 	}
	 
	 
	 
	 function fn_connectivity_type_other() {
		 var text = $("#connectivity_type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#connect_other_hid").show();
			}
			else{
				$("#connect_other_hid").hide();
				$("#connectivity_other").val('');
			}
	 	}
	 
	/*  function validateCode() {
		   
		   
//		   var machine_number = $("#model_number1").val();
		   
		   var  testString = $("input#machine_no1").val();
			 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
			
			 }
			 else{
				 alert("Please enter letters and numbers only.");
				 $("#machine_no1").val("");
			 }
	   }
	  */
	 
	 function searchTypeOfPeripheralOther(){
			
		
			var peri_hw_other = $("#peri_hw_other").val();
			$.post("getTypeOfPeripheralOtherList?" + key + "=" + value, {
				peri_hw_other:peri_hw_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Type Of Peripheral HW Already Exists.");
		 			$("#peri_hw_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
	 
	 
	 function searchMakeOther(){
			
			var assets_type = $("#assets_type").val();
			var brand_other = $("#brand_other").val();
			$.post("getPeriBrandOtherList?" + key + "=" + value, {
				assets_type:assets_type,brand_other: brand_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Make Name Already Exists.");
		 			$("#brand_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		
		function searchModelOther(){

			var assets_type = $("#assets_type").val();
			var model_other = $("#model_other").val();
			$.post("getPeriModelOtherList?" + key + "=" + value, {
				assets_type:assets_type,model_other: model_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Model Name Already Exists.");
		 			$("#model_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		function searchTypeOfModelOther(){
	
// 			var asset_type = $("#assets_type").val();
			var type_of_hw = $("#type_of_hw").val();
			var model_type_other = $("#model_type_other").val();
			$.post("getTypeOfModelOtherList?" + key + "=" + value, {
				type_of_hw:type_of_hw,model_type_other:model_type_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Model Name Already Exists.");
		 			$("#model_type_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}	
		
		
		function searchConnectivityTypeOther(){
			
				var connectivity_other = $("#connectivity_other").val();
				$.post("getTypeConnectivityOtherList?" + key + "=" + value, {
					connectivity_other:connectivity_other
				}).done(function(j) {
								
			 		 if(j.length > 0){
			 			 
			 			 alert("This Connectivity Type Already Exists.");
			 			$("#connectivity_other").val("");
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
		
		
		
	/////For File Download
		 
		 
		 
		 function download_file() {
			 
				var id = $("#id").val(); 
				var pdfView="BERFileDownloadDemoPeripheral?id="+id;

					
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
		 
		
 </script>



