
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
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script src="js/Calender/jquery-ui.js" nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>

<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">


<form:form name="MainAssets" id="MainAssets" action="updateAssetsAction"
	method="post" class="form-horizontal" modelAttribute="AppMainAssetsCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT IT CENSUS RETURN</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<input type="hidden" id="c_id" name="c_id"
					class="form-control autocomplete" autocomplete="off" />

				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Section
											No :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblsection">${AppMainAssetsCmd.section}</label> <select
										id="section" name="section" class="form-control display_none">
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
									<label class=" form-control-label"><strong>User
											Name: </strong></label>
								</div>
								<div class="col-md-8">
									<label id="username_label">${AppMainAssetsCmd.username}</label>

								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Computing
											Assets Type :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblAsset_type"></label>
									<form:select path="asset_type" id="asset_type"
										class="form-control display_none">

										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong>Model -->
<!-- 											Number :</strong></label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblmodel_number">${AppMainAssetsCmd.model_number}</label> --%>
<%-- 									<form:input type="text" id="model_number" path="model_number" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="col-md-12">	
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Make
											Name :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmake_name"></label>
									<form:select path="make_name" id="make_name" name="make_name"
										class="form-control display_none">
										<c:forEach var="item" items="${MakeList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Model
											Name :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_name"></label>
									<form:select path="model_name" id="model_name"
										class="form-control display_none">
										<c:forEach var="item" items="${ModelList}" varStatus="num">
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
									<label class=" form-control-label"><strong>Machine
											No. :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmachine_number">${AppMainAssetsCmd.machine_number}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Mac
											Address :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmac_address">${AppMainAssetsCmd.mac_address}</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>IP
											Address :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblip_address">${AppMainAssetsCmd.ip_address}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Processor
											Type :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblprocessor_type"></label>
									<form:select path="processor_type" id="processor_type"
										class="form-control display_none">

										<c:forEach var="item" items="${processor_typeList}"
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
									<label class=" form-control-label"><strong>RAM
											Capacity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblram_capi"></label>
									<form:select path="ram_capi" id="ram_capi"
										class="form-control display_none">

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
									<label class=" form-control-label"><strong>HDD
											Capacity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblhdd_capi"></label>
									<form:select path="hdd_capi" id="hdd_capi"
										class="form-control display_none">

										<c:forEach var="item" items="${hddList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										> SD Installed :</strong></label>
								</div>
								<div class="col-md-8" id="ssdcheckid">
								<label id="lblssd_check"></label>
								<div class="display_none">
								
									<input type="radio" id="ssdcheck11" class="display_none" name="ssdcheck" value="Yes" ></input>&nbsp
									<label for="ssdcheck11">Yes</label>&nbsp
									<input type="radio" id="ssdcheck21"  name="ssdcheck"value="No"></input>&nbsp
									<label for="ssdcheck22">No</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 " id="divssdcap">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										>SSD Capacity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblssd_capi"></label>
									<select name="ssd_capi1" id="ssd_capi1"
										class="form-control display_none">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${sddList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<label class=" form-control-label"><strong>Operating
											System :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbloperating_system"></label>
									<form:select path="operating_system" id="operating_system"
										class="form-control display_none">

										<c:forEach var="item" items="${osList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Office Productivity Suite
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbloffice"></label>
									<form:select path="office" id="office"
										class="form-control display_none">

										<c:forEach var="item" items="${officeList}" varStatus="num">
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
									<label class=" form-control-label"><strong>AntiVirus
											Installed :</strong> </label>
								</div>
								<div class="col-md-8">
									<label id="lblantiviruscheck"></label>
									<form:radiobutton id="anti_virus" value="Yes"
										path="antiviruscheck" class="display_none"></form:radiobutton>
									&nbsp <label for="yes" class="display_none">Yes</label>&nbsp
									<form:radiobutton id="anti_virus" path="antiviruscheck"
										value="No" checked="checked" class="display_none"></form:radiobutton>
									&nbsp <label for="no" class="display_none">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>AntiVirus
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblantivirus"></label>
									<form:select path="antivirus" id="antivirus"
										class="form-control display_none">

										<c:forEach var="item" items="${AntivirusList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<!-- 						<div class="col-md-6"> -->
						<!-- 							<div class="row form-group"> -->
						<!-- 								<div class="col-md-4"> -->
						<!-- 									<label class=" form-control-label"><strong>OS/Framware -->
						<!-- 											Activation and subsequent Patch Updation Mechanism :</strong></label> -->
						<!-- 								</div> -->
						<!-- 								<div class="col-md-8"> -->
						<!-- 									<label id="lblos_patch"></label> -->
						<%-- 									<form:select path="os_patch" id="os_patch" class="form-control display_none" --%>
						<%-- 										> --%>

						<%-- 										<c:forEach var="item" items="${osFirmwareList}" --%>
						<%-- 											varStatus="num"> --%>
						<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
						<%-- 										</c:forEach> --%>
						<%-- 									</form:select> --%>
						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Dply
											Envt as Applicable :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbldply_envt"></label>
									<form:select path="dply_envt" id="dply_envt"
										class="form-control display_none">

										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<!-- 						<div class="col-md-6"> -->
						<!-- 							<div class="row form-group"> -->
						<!-- 								<div class="col-md-4"> -->
						<!-- 									<label class=" form-control-label"><strong>Ram -->
						<!-- 											Slot Quantity :</strong></label> -->
						<!-- 								</div> -->
						<!-- 								<div class="col-md-8"> -->
						<%-- 									<label id="lblpart_rem_slot_qty">${AppMainAssetsCmd.ram_slot_qty}</label> --%>

						<!-- 								</div> -->
						<!-- 							</div> -->
						<!-- 						</div> -->
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Budget Head And Code :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head"></label>
									<form:select path="b_head" id="b_head"
										class="form-control display_none">

										<c:forEach var="item" items="${getBudgetHeadList}"
											varStatus="num">
											<form:option value="${item[0]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong>Budget Code :</strong></label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="lblb_code"></label> -->
<%-- 									<form:select path="b_code" id="b_code" name="b_code" --%>
<%-- 										class="form-control display_none"> --%>
<%-- 										<c:forEach var="item" items="${getBudgetCodeList}" --%>
<%-- 											varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Proc
											Cost :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost">${AppMainAssetsCmd.b_cost}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>CD
											Drive :</strong></label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
										<label id="lblscan"></label> <input type="radio" id="scan"
											value="Yes" name="cd_drive" class="display_none" />&nbsp <label
											for="yes" class="display_none">Yes</label>&nbsp <input
											type="radio" id="scan" name="cd_drive" value="No"
											checked="checked" class="display_none" />&nbsp <label
											for="no" class="display_none">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>CR
											No : </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblproc_date">${AppMainAssetsCmd.crv_no}</label>

								</div>
							</div>
						</div>
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Gem no :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbgem_no">${AppMainAssetsCmd.gem_no}</label>

								</div>
							</div>
						</div>
						</div>

				</div>


				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_computing"><b>COMPUTING
										DETAILS</b></a>

							</h4>
						</div>


						<div id="collapsecomputing" class="panel-collapse collapse">
							<div class="card-body card-block">

								<div class="row">

									<div class="col-md-12">
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
														title="IP Adddress should be in the mentioned format."
														type="text" id="ip_address1" name="ip_address1"
														maxlength="15" class="form-control autocomplete"
														autocomplete="off"></input>
												</div>
											</div>
										</div>

										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>RAM Capacity </label>
												</div>
												<div class="col-md-8">
													<select name="ram_capi1" id="ram_capi1"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${ramList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
													<label class=" form-control-label"><strong
														class="color_red">* </strong>HDD Capacity </label>
												</div>
												<div class="col-md-8">
													<select name="hdd_capi1" id="hdd_capi1"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${hddList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									
									
									
									
										<div class="col-md-12 " >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class= "color_red">* </strong>SSD Installed </label>
								</div>
								<div class="col-md-8" id="ssdcheckid1">
									<input type="radio" id="ssdcheck1" name="ssdcheck1" value="Yes" ></input>&nbsp
									<label for="ssdcheck1">Yes</label>&nbsp
									<input type="radio" id="ssdcheck2"  name="ssdcheck1"value="No"></input>&nbsp
									<label for="ssdcheck2">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="divssdcap1">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class= "color_red">* </strong>SSD Capacity</label>
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
									
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Operating System </label>
												</div>
												<div class="col-md-8">
													<select name="operating_system1" id="operating_system1"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${osList}" varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red"> </strong>Office Productivity Suite</label>
												</div>
												<div class="col-md-8">
													<select name="office1" id="office1" class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${officeList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
													<label class=" form-control-label"><strong
														class="color_red">* </strong>AntiVirus Installed </label>
												</div>
												<div class="col-md-8" id="anti_showid">
													<form:radiobutton id="antiviruscheck1" value="Yes"
														path="antiviruscheck"></form:radiobutton>
													&nbsp <label for="yes">Yes</label>&nbsp
													<form:radiobutton id="antiviruscheck2"
														path="antiviruscheck" value="No" checked="checked"></form:radiobutton>
													&nbsp <label for="no">No</label>
												</div>
											</div>
										</div>
										<div class="col-md-6 display_none" id="AntiVirusDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>AntiVirus</label>
												</div>
												<div class="col-md-8">
													<select name="antivirus1" id="antivirus1"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${AntivirusList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
<!-- 										<div class="col-md-6"> -->
<!-- 											<div class="row form-group"> -->
<!-- 												<div class="col-md-4"> -->
<!-- 													<label class=" form-control-label"><strong -->
<!-- 														class="color_red">* </strong>OS/Firmware Activation and -->
<!-- 														subsequent Patch Updation Mechanism </label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-8"> -->
<!-- 													<select name="os_patch1" id="os_patch1" -->
<!-- 														class="form-control"> -->
<!-- 														<option value="0">--Select--</option> -->
<%-- 														<c:forEach var="item" items="${osFirmwareList}" --%>
<%-- 															varStatus="num"> --%>
<%-- 															<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 														</c:forEach> --%>
<!-- 													</select> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->

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
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Dply Envt as Applicable</label>
												</div>
												<div class="col-md-8">
													<select name="dply_envt1" id="dply_envt1"
														class="form-control">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${dplyEnvList}"
															varStatus="num">
															<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>


									<div class="col-md-12">
<!-- 										<div class="col-md-6"> -->
<!-- 											<div class="row form-group"> -->
<!-- 												<div class="col-md-4"> -->
<!-- 													<label class=" form-control-label"><strong -->
<!-- 														class="color_red">* </strong>RAM Slot Quantity</label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-8"> -->
<%-- 													<form:input type="number" id="ram_slot_qty" --%>
<%-- 														path="ram_slot_qty" min="1" max="1000" --%>
<%-- 														class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>CD Drive</label>
												</div>
												<div class="col-md-8">

													<div class="col-md-8">
														<label>Yes</label> <input class=" " type="radio"
															id="scan1" value="Yes" name="cd_drive1"> <label>No</label>
														<input class=" " type="radio" id="scan1" value="No"
															name="cd_drive1" checked>
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
														class="color_red">* </strong>Budget Head</label>
												</div>
												<div class="col-md-8">
													<select name="b_head1" id="b_head1" class="form-control">
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
													<label class=" form-control-label"><strong
														class="color_red"> </strong>Proc Cost</label>
												</div>
												<div class="col-md-8">
													<form:input type="text" id="b_cost" path="b_cost"
														class="form-control autocomplete" autocomplete="off"></form:input>
												</div>
											</div>
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
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_warrenty"><b>WARRANTY
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
														class="color_red">* </strong>Warranty</label>
												</div>
												<div class="col-md-8" id="warrenty_showid">
													<form:radiobutton id="Warranty1" value="Yes"
														path="warrantycheck" checked="checked"></form:radiobutton>
													&nbsp <label for="yes" checked>Yes</label>&nbsp
													<form:radiobutton id="Warranty2" path="warrantycheck"
														value="No"></form:radiobutton>
													&nbsp <label for="no">No</label>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="WarrantyDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Warranty</label>
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
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_service"><b>SERVICEABLE
										DETAILS</b></a>
							</h4>
						</div>
						<div id="collapseservice" class="panel-collapse collapse">
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
<!-- 													<select name="s_state" id="s_state" class="form-control"> -->
<!-- 														<option value="0">--Select--</option> -->
<!-- 														<option value="1">Serviceable</option> -->
<!-- 														<option value="2">UN-Serviceable</option> -->
<!-- 													</select> -->
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

									<!-- 							** BISAG AHM ** -->

									<div class="col-md-12">
										<div class="col-md-6 display_none" id="un_show1">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>UN-Servicable From Date</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="unsv_from_dt1" id="unsv_from_dt1"
														maxlength="10"
														class="form-control  width88 display_inline rgb0"
														aria-required="true" autocomplete="off">

												</div>
											</div> 
										</div>
<!-- 										<div class="col-md-6 display_none" id="un_show2"> -->
<!-- 											<div class="row form-group"> -->
<!-- 												<div class="col-md-4"> -->
<!-- 													<label class=" form-control-label"><strong -->
<!-- 														class="color_red">* </strong>UN-Servicable To Date</label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-8"> -->
<!-- 													<input type="text" name="unsv_to_dt1" id="unsv_to_dt1" -->
<!-- 														maxlength="10" -->
<!-- 														class="form-control  width88 display_inline rgb0" -->
<!-- 														aria-required="true" autocomplete="off"> -->

<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="col-md-6 display_none" id="maintaince_div">
												<div class="col-md-4">
													<label class="form-control-label"><strong
														class= "color_red">* </strong>Maintenance
														Agency</label>
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
									<div class="col-md-12">
											
							</div>
									<!-- 							** END BISAG AHM ** -->

								</div>
							</div>
						</div>
					</div>

				</div>

			</div>

			<div class="card-footer" align="center" class="form-control">
				<a href="SearchApp_Computing_AssetsUrl"
					class="btn btn-success btn-sm">Back</a> <input type="submit"
					class="btn btn-primary btn-sm" id="updatebtn" value="Update">

			</div>
		</div>

	</div>


</form:form>
<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('updatebtn').onclick = function() {
			return Validate();
		};

		document.getElementById('a_service').onclick = function() {
			return divN(this);
		};
		document.getElementById('warrenty_showid').onclick = function() {
			return warrenty_show();
		};
		document.getElementById('a_warrenty').onclick = function() {
			return divN(this);
		};

		document.getElementById('b_cost').onkeypress = function() {
			return isNumber(event);
		};
		document.getElementById('b_head1').onchange = function() {
			return fn_B_Head();
		};
// 		document.getElementById('ram_slot_qty').onkeypress = function() {
// 			return isNumber(event);
// 		};
		document.getElementById('anti_showid').onclick = function() {
			return anti_show();
		};

		document.getElementById('ip_address1').onchange = function() {
			return ValidateIPaddress(this);
		};

		document.getElementById('a_computing').onclick = function() {
			return divN(this);
		};

		document.getElementById('make_name').onchange = function() {
			return fn_modelName();
		};

		document.getElementById('asset_type').onchange = function() {
			return fn_makeName();
		};
		document.getElementById('s_state').onchange = function() {
			fn_Servicable(this);
			 servicablechange();
		};
		//   	document.getElementById('s_state').onchange = 
		// 		function() {
		// 		return fn_Servicable(this);
		//   	};

// 		document.getElementById('unsv_to_dt1').onclick = function() {
// 			return clickclear(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_to_dt1').onfocus = function() {
// 			return this.style.color = '#000000';
// 		};
// 		document.getElementById('unsv_to_dt1').onblur = function() {
// 			return clickrecall(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_to_dt1').onkeyup = function() {
// 			return clickclear(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_to_dt1').onchange = function() {
// 			return clickrecall(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_to_dt1').onchange = function() {
// 			return fn_date_future(this);
// 		};

// 		document.getElementById('unsv_from_dt1').onclick = function() {
// 			return clickclear(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_from_dt1').onfocus = function() {
// 			return this.style.color = '#000000';
// 		};
// 		document.getElementById('unsv_from_dt1').onblur = function() {
// 			return clickrecall(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_from_dt1').onkeyup = function() {
// 			return clickclear(this, 'DD/MM/YYYY');
// 		};
// 		document.getElementById('unsv_from_dt1').onchange = function() {
// 			return clickrecall(this, 'DD/MM/YYYY');
// 		};

		document.getElementById('warranty').onclick = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('warranty').onfocus = function() {
			return this.style.color = '#000000';
		};
		document.getElementById('warranty').onblur = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('warranty').onkeyup = function() {
			return clickclear(this, 'DD/MM/YYYY');
		};
		document.getElementById('warranty').onchange = function() {
			return clickrecall(this, 'DD/MM/YYYY');
		};
		document.getElementById('ssdcheckid1').onchange = 
			function() {
			return ssd_show();
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
	  	
	});

	$(document).ready(function() {
		servicablechange()
		$("#id").val('${e_id}')
		

	});
</script>
<script nonce="${cspNonce}">
	function servicablechange() {
// 		var s_state = $("#s_state option:selected").text();

// 		if (s_state == "UN-Serviceable") {
// 			$("#un_show").show();
// 			$("#un_show1").show();
// 			$("#maintaince_div").show();
			
// 			$("#un_show2").hide();
// 		} else {
// 			$("#un_show").hide();
// 			$("#un_show1").hide();
// 			$("#maintaince_div").hide();
// 		}

	var a =$("select#s_state").val();
	if(a == '2') {
		$("#un_show").show();
		$("#un_show1").show();
		$("#maintaince_div").show();
		
	} else {
		$("#un_show").hide();
		$("#un_show1").hide();
		$("#file_show").hide();
		$("#maintaince_div").hide();
		$('#unserviceable_state').val('0');
	}

	}

	function fn_B_Head() {

		var b_head = $("select#b_head1").val();
		$
				.post("getBudgetCodeList?" + key + "=" + value, {
					b_head : b_head
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#b_code1").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</script>


<script nonce="${cspNonce}">
	//** BISAG AHM **//

	function Validate() {

		if ($("select#dply_envt1").val() == 7
				|| $("select#dply_envt1").val() == 9) {
			if ($("#ip_address1").val() == "") {

				alert("Please Select IP Address ")
				$("#ip_address1").focus();
				return false;

			}

		}

		if ($("#ram_capi1").val() == 0 || $("#ram_capi1").val() == "") {
			alert("Please Select RAM Capacity  ");
			$("#ram_capi1").focus();
			return false;
		}

		if ($("#hdd_capi1").val() == 0 || $("#hdd_capi1").val() == "") {
			alert("Please Select HDD Capacity ");
			$("#hdd_capi1").focus();
			return false;
		}

// 		if ($("#os_patch1").val() == 0 || $("#os_patch1").val() == "") {
// 			alert("Please Select OS/Firmware Activation and Subsequent Patch Updation Mechanism   ");
// 			$("#os_patch1").focus();
// 			return false;
// 		}

		if ($("#dply_envt1").val() == 0 || $("#dply_envt1").val() == "") {
			alert("Please Select Dply Envt as Applicable  ");
			$("#dply_envt1").focus();
			return false;
		}

		// 	if($("#s_state").val()==1){
		// 		if ($("input#unsv_to_dt1").val() == "" || $("#unsv_to_dt1").val() == "DD/MM/YYYY") {
		// 			alert("Please Select UN-Serviceable To Date");
		// 			$("#unsv_to_dt1").focus();
		// 			return false;
		// 		}

		// 	}

// 		if ($("#ram_slot_qty").val() == "" || $("#ram_slot_qty").val() == "0") {
// 			alert("Please Enter RAM Slot Quantity");
// 			$("#ram_slot_qty").focus();
// 			return false;
// 		}

		if (!$("input[name='cd_drive1']").is(':checked')) {
			alert('Please Check CD Drive!');
		}

		if ($("#b_head").val() == "0") {
			alert("Please Select Budget Head");
			$("#b_head").focus();
			return false;
		}

// 		if ($("#b_code").val() == "0") {
// 			alert("Please Select Budget Code");
// 			$("#b_code").focus();
// 			return false;
// 		}

		if ($("#s_state").val() == 2) {
			if ($("#unserviceable_state").val() == ""
					|| $("#unserviceable_state").val() == "0") {
				alert("Please Select UN-Serviceable State");
				$("#unserviceable_state").focus();
				return false;
			}
		}

		if ($("#s_state").val() == 2) {
			if ($("input#unsv_from_dt1").val() == ""
					|| $("#unsv_from_dt1").val() == "DD/MM/YYYY") {
				alert("Please Select UN-Serviceable From Date");
				$("#unsv_from_dt1").focus();
				return false;
			}
		}

	}

	function fn_Servicable(obj) {

		if (obj.value == '1') {
			var p_id = $("input#id").val();
			var s_state = $("select#s_state").val();
			var unservice_state = $("select#unserviceable_state").val();
			var unsv_from_dt1 = $("input#unsv_from_dt1").val();

			$.post("getServicable?" + key + "=" + value, {
				p_id : p_id
			}).done(function(j) {

				if (j != "" && j != null) {

					$("#un_show2").show();
				} else {

					$("#un_show2").hide();
				}

			});
		}
	}

	//** END BISAG AHM **//

	function divN(obj) {
		var id = obj.id;
		var sib_id = $("#" + id).parent().parent().next().attr('id');
		var hasC = $("#" + sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		$('.droparrow').each(function(i, obj) {
			$("#" + obj.id).attr("class", "droparrow collapsed");
		});

		if (hasC) {
			$("#" + id).addClass(" collapsed");
		} else {
			$("#" + sib_id).addClass("show");
			$("#" + id).removeClass("collapsed");
		}

	}

	function warrenty_show() {
		if ($("#Warranty1").prop("checked")) {
			$("#WarrantyDiv").show();

		} else {
			$('#WarrantyDiv').hide();
			$('#warranty').val('DD/MM/YYYY');
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
							var options = '';
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

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#model_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	$(document)
			.ready(
					function() {
						datepicketDate('warranty');
						datepicketDate('unsv_from_dt1');
						datepicketDate('unsv_to_dt1');
						datepicketDate('antivirus_expry1');
						
						
						
						
						$("#section").val("${AppMainAssetsCmd.section}");
						//$("#maintainAgency").val("${AppMainAssetsCmd.maintainagency}");
						
						
						debugger;
						$("#ssd_capi1").val("${AppMainAssetsCmd.ssd_capi}");
						if ($("#ssd_capi1").val()=="0" || $("#ssd_capi1").val()=='')
						{
						$("#lblssd_check").text("No");
						
						}
					if ($("#ssd_capi1").val()!="0" || $("#ssd_capi1").val()!='')
					{
						$("#lblssd_check").text("Yes");
					var a=	$("#ssd_capi1 option:selected").text()
						$("#lblssd_capi").text(a);
						
					}
						

						$("#warranty").datepicker("option", "maxDate", null);

						$("#asset_type").val("${AppMainAssetsCmd.asset_type}");
						var asset_type = $("#asset_type option:selected")
								.text();
						$.ajaxSetup({
							async : false
						});

						fn_makeName();
						var make_name = $("#make_name option:selected").text();
						fn_modelName();
						var model_name = $("#model_name option:selected")
								.text();
						var processor_type = $(
								"#processor_type option:selected").text();
						var ram_capi = $("#ram_capi option:selected").text();
						var hdd_capi = $("#hdd_capi option:selected").text();
						var operating_system = $(
								"#operating_system option:selected").text();
						var office = $("#office option:selected").text();

						$("#antivirus").val("${AppMainAssetsCmd.antivirus}");

						var antivirus = $("#antivirus option:selected").text();
						var os_patch = $("#os_patch option:selected").text();
						var dply_envt = $("#dply_envt option:selected").text();

						var connecting_tech = $(
								"#connecting_tech option:selected").text();
						var ethernet_port = $("#ethernet_port:selected").text();
						var s_state = $("#s_state option:selected").text();

						$("#unsv_from_dt1").val(
								"${AppMainAssetsCmd.unsv_from_dt}");
						$("#unsv_from_dt1").val(
								ParseDateColumncommission('${unsv_from_dt1}'))
						$("#lblsection").text( $("#section option:selected").text());
						$("#maintaince_agencylbl").text($("#maintainAgency option:selected").text());
						$("#lblAsset_type").text(asset_type);
						$("#lblmake_name").text(make_name);
						$("#lblmodel_name").text(model_name);
						$("#lblprocessor_type").text(processor_type);
						$("#lblram_capi").text(ram_capi);
						$("#lblhdd_capi").text(hdd_capi);
						$("#lbloperating_system").text(operating_system);
						$("#lbloffice").text(office);
						$("#lblantivirus").text(antivirus);
						$("#lblos_patch").text(os_patch);

						$("#lbldply_envt").text(dply_envt);
						$("#lbls_state").text(s_state);

						$("#lblconnecting_tech").text(connecting_tech);
						$("#lblethernet_port").text(ethernet_port);

						$(
								"input[name='cd_drive'][value='${AppMainAssetsCmd.cd_drive}']")
								.prop("checked", true);

						$("#scan").val("${AppMainAssetsCmd.cd_drive}");
						var scan = $('input[name=cd_drive]').val();
						$("#lblscan").text(scan);

						$("#lblb_head").text("${AppMainAssetsCmd.b_head}");

						$("select#b_code").val("${AppMainAssetsCmd.b_code}");
						var b_code = $("#b_code option:selected").text();
						$("#lblb_code").text(b_code);

						$(
								"input[name='antiviruscheck'][value='${AppMainAssetsCmd.antiviruscheck}']")
								.prop("checked", true);
						anti_show();
						$("#anti_virus").val(
								"${AppMainAssetsCmd.antiviruscheck}");
						var anti_virus = $('input[name=antiviruscheck]').val();
						var antiviruscheck='${AppMainAssetsCmd.antiviruscheck}';
						if(antiviruscheck=="Yes")
							{
							$("#antivirus_expry1").val((ParseDateColumncommission('${AppMainAssetsCmd.antivirus_expry}')));	
							}
						

						$("#lblantiviruscheck").text(anti_virus);

						$("select#operating_system").on("change", function() {
							if ($("select#operating_system").val() == 0) {
								$("#newLabel").hide();
							} else {
								$("#newLabel").show();
							}

						});

						$("#s_state").val('${service_state1}')
						servicablechange();
						$("#unserviceable_state").val('${unservice_state1}')
						$("#warranty").val(
								ParseDateColumncommission('${warranty1}'))
						$("#unsv_from_dt1").val(
								ParseDateColumncommission('${unsv_from_dt1}'))
						$("#unsv_to_dt1").val(
								ParseDateColumncommission('${unsv_to_dt1}'))

						$(
								"input[name='warrantycheck'][value='${AppMainAssetsCmd.warrantycheck}']")
								.prop("checked", true);

						$("#c_id").val('${ch_id1}');
						if (parseInt('${ch_list_size}') > 0) {

							if ('${ch_list[0].status}' == 1) {

								$("#c_id").val('0');
							}

							$("#s_state").val('${ch_list[0].service_state}');
							servicablechange();
							$("#unserviceable_state").val(
									'${ch_list[0].unservice_state}');
							$("#warranty")
									.val(
											ParseDateColumncommission('${ch_list[0].warranty}'));
							$("#unsv_from_dt1")
									.val(
											ParseDateColumncommission('${ch_list[0].unsv_from_dt}'));
							fn_Servicable(1);
							$("#unsv_to_dt1")
									.val(
											ParseDateColumncommission('${ch_list[0].unsv_to_dt}'));
							
							$("#maintainAgency")
							.val(
									'${ch_list[0].maintainagency}');
							

						} else {
							$("#c_id").val('0');

						}

						if ('${ch_list[0].service_state}' == 1
								&& ('${ch_list[0].unsv_to_dt}' != "" && '${ch_list[0].unsv_to_dt}' != null)) {
							$("#un_show2").show();
						}

						$("#ip_address1").tooltip();

						$("#ip_address1").css('cursor', 'pointer');

						$("#ip_address1").val('${AppMainAssetsCmd.ip_address}');

						$("select#ram_capi1").val(
								"${AppMainAssetsCmd.ram_capi}");

						$("select#hdd_capi1").val(
								"${AppMainAssetsCmd.hdd_capi}");

						$("select#operating_system1").val(
								"${AppMainAssetsCmd.operating_system}");

						$("select#office1").val("${AppMainAssetsCmd.office}");

						$("#antivirus1").val("${AppMainAssetsCmd.antivirus}");

						$("select#os_patch1").val(
								"${AppMainAssetsCmd.os_patch}");

						$("select#dply_envt1").val(
								"${AppMainAssetsCmd.dply_envt}");

						$("select#b_head1").val('${AppMainAssetsCmd.b_head}');
						fn_B_Head();

						$("select#b_code1").val('${AppMainAssetsCmd.b_code}');

						$(
								"input[name='cd_drive1'][value='${AppMainAssetsCmd.cd_drive}']")
								.prop("checked", true);
						var scan1 = $('input[name=cd_drive1]').val();
						$("#scan1").text(scan1);

						
						debugger;
						var checkssd='${AppMainAssetsCmd.ssd_capi}';
						if(checkssd!='' && checkssd!="0")
							{
							$("input[name='ssdcheck1'][value='Yes']").prop("checked",true);
							$("#divssdcap1").show();
							}
						else{
							$("input[name='ssdcheck1'][value='No']").prop("checked",true);
						}
						
						$("select#dply_envt1").on("change", function() {
							if ($("select#dply_envt1").val() == 7) {
								$("label#ipDisplay").show();
								$("label#nonIpDisplay").hide();

							} else if ($("select#dply_envt1").val() == 9) {
								$("label#ipDisplay").show();
								$("label#nonIpDisplay").hide();
							} else {
								$("label#ipDisplay").hide();
								$("label#nonIpDisplay").show();
							}

						});

					});

	function ValidateIPaddress(ipaddress) {
		var ip = new RegExp(
				/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)
		if (ip.test(ipaddress.value)) {
			return true;

		} else {
			alert("You have entered an invalid IP Address!")
			$("#ip_address1").focus();

			return false;
		}
	}
	function ssd_show() {
		if ($("#ssdcheck1").prop("checked")) {
			$("#divssdcap1").show();
		} else{
			$('#divssdcap1').hide();
			$('#ssd_capi').val('0');
		}
		
	}
	function anti_show() {

		if ($("#antiviruscheck1").prop("checked")) {
			$("#AntiVirusDiv").show();
			$("#antivirus_date").show();
			
		} else {
			$('#AntiVirusDiv').hide();
			$("#antivirus_date").hide();
			$('#antivirus1').val('0');	
			$("#antivirus_date").val();
		}

	}
</script>