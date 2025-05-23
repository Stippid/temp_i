<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" nonce="${cspNonce}"></script>


<form:form name="ViewAssets" id="ViewAssets" action="ViewAssetsAction"
	method="post" class="form-horizontal" modelAttribute="ViewAssetsCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>VIEW COMPUTING ASSETS</h5>
						<input type="hidden" id="screenUrl" name ="screenUrl" value="${screenurl}" class="form-control autocomplete" autocomplete="off">
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<div class="card-body card-block" id="printableArea">
				
				<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Section
											No :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblsection">${ViewAssetsCmd.section}</label>
					<select id="section" name="section" class="form-control display_none">
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
									<label class=" form-control-label"><strong>User Name:
											</strong></label>
								</div>
								<div class="col-md-8">
								<label id="username_label">${ViewAssetsCmd.created_by}</label>
								
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
										class="form-control display_none" 
										>

										<c:forEach var="item" items="${ComputingAssetList}"
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
									<label class=" form-control-label"><strong>Make
											Name :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmake_name"></label>
									<form:select path="make_name" id="make_name" name="make_name"
										class="form-control display_none" 
										>
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
					<div class="col-md-6 display_none" id = "brand_other_hid">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										class="color_red"></strong><strong>Make/Brand Other </strong></label>
						                </div>
						                <div class="col-md-8">

					                	
					                	<label id="lblbrand_other">${make_mstr_other}</label>
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6 display_none" id = "model_other_hid">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										class="color_red"></strong><strong>Make/Brand Other </strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblmodel_other">${model_mstr_other}</label>
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
									<label id="lblmachine_number">${ViewAssetsCmd.machine_number}</label>
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
									<label id="lblmac_address">${ViewAssetsCmd.mac_address}</label>
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
									<label id="lblip_address">${ViewAssetsCmd.ip_address}</label>
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
					<div class="col-md-6 display_none" id = "pro_type_other_hid">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                   <strong>Processor
											Type Other:</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblprocessor_other">${pro_type_other}</label>
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
									<form:select path="ram_capi" id="ram_capi" class="form-control display_none"
										>

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
									<form:select path="hdd_capi" id="hdd_capi" class="form-control display_none"
										>

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
								
									<input type="radio" id="ssdcheck1" class="display_none" name="ssdcheck" value="Yes" ></input>&nbsp
									<label for="ssdcheck1">Yes</label>&nbsp
									<input type="radio" id="ssdcheck2"  name="ssdcheck"value="No"></input>&nbsp
									<label for="ssdcheck2">No</label>
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
										<form:select path="ssd_capi" id="ssd_capi" class="form-control display_none">
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
									<form:select path="office" id="office" class="form-control display_none"
										>

										<c:forEach var="item" items="${officeList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
				
					
					
					<div class="col-md-6 display_none" id = "os_other_hid">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong>Operating
											System :</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblos_other">${os_mstr_other}</label>
					                	 </div>
						            </div>
	              				</div>
	              					<div class="col-md-6 display_none" id = "office_other_hid">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong>Office
											Other :</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lbloffice_other">${office_mstr_other}</label>
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
									<form:radiobutton id="anti_virus" value="Yes" path="antiviruscheck"
										 class="display_none"></form:radiobutton>
									&nbsp <label for="yes" class="display_none">Yes</label>&nbsp
									<form:radiobutton id="anti_virus" path="antiviruscheck" value="No"
										checked="checked" class="display_none"></form:radiobutton>
									&nbsp <label for="no" class="display_none">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="AntiVirusDiv">
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
	<div class="col-md-6" id="antivirus_exp_div">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Antivirus expiry date:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="Antivirus_exp_date"></label>
								
								</div>
							</div>
						</div>
						
					
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarrantycheck">${ViewAssetsCmd.warrantycheck}</label>


								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarranty"></label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Serviceable
											State :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbls_state"></label>
									<form:select path="s_state" id="s_state" class="form-control display_none"
										>
										<c:forEach var="item" items="${ServiceableList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>

								</div>
							</div>
						</div>
						
						<div class="col-md-6 display_none"  id="uni">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong>UN-Serviceable State</strong></label>
													</div>
														<div class="col-md-8">
														<label id="lblunserviceable_state"></label>
					                						<form:select path="unserviceable_state" id="unserviceable_state" class="form-control display_none">

																 <c:forEach var="item" items="${UNServiceableList}" varStatus="num">
																<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
															</c:forEach>
															 </form:select>

							        					</div>
												</div>
											</div>
						
					</div>
							<div class="col-md-12 display_none" id="file_show1">
										<div class="col-md-6 ">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red"></strong><b>Upload BER Certificate</label>
												</div>
												<div class="col-md-8">
													<input type="file" id="br_certificate1" name="br_certificate1" class="display_none"/> <i id="download_i1"
													class="fa fa-download display_none"><span id="uploadedFileName1" class="uploaded-file-name f_10">${ViewAssetsCmd.br_certificate}</span><input type="button"
													class="btn btn-success btn-sm display_none" id="downloadbtn" 
													value="File" /></i>
												</div>
											</div>
										</div>
									</div>
<!-- 						<div class="col-md-12 display_none" id="file_show"> -->
<!-- 	<div class="col-md-6"> -->
<!--     <div class="row form-group"> -->
<!--         <div class="col-md-4"> -->
<!--             <label class="form-control-label"><strong class="color_red">* </strong>Upload Crv Document</label> -->
<!--         </div> -->
<!--         <div class="col-md-8"> -->
<!--             <input type="file" id="u_file1" name="u_file1" /> -->
<!--             <i id="download_i" class="fa fa-download display_none"> -->
<!--                 <span id="uploadedFileName" class="uploaded-file-name f_10"></span> -->
<!--                 <input type="button" class="btn btn-success btn-sm display_none" id="downloadbtn" value="Download File"/> -->
<!--             </i> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<!-- 									</div> -->
				
									<div class="col-md-12">
										<div class="col-md-6 display_none" id="unsv_div">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong>Un-servicable from :</strong></label>
												</div>
										<label id="lblunsv_from_dt1"></label>
											</div>
										</div>
										<div class="col-md-6 display_none" id="maintaince_agencyldiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong>Maintenance Agency :</strong></label>
												</div>
										<label id="maintaince_agencylbl">
										</label>
										<select id="maintainAgency" name="maintainAgency"
													class="form-control display_none">
													<option value="0">--Select--</option>
													<c:forEach var="item" items="${getMaintainceAgencyList}">
														<option value="${item[0]}">${item[1]}</option>
													</c:forEach>
												</select>	
											</div>
										</div>
										</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><b>Budget Head
											And Code</b> </label>
								</div>
								<label id="b_headlbl"> </label>
								<div class="col-md-8">
									<select path="b_head" id="b_head"
										class="form-control display_none">

										<c:forEach var="item" items="${getBudgetHeadList}">

											<option value="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong
										class="color_red"> </strong><b>Upload Crv Document</b></label>
								</div>
								<div class="col-md-8">
									<i id="download_i" class="fa fa-download display_none"> <span
										id="uploadedFileName" class="uploaded-file-name f_10">${ViewAssetsCmd.u_file}</span>
										<input type="button"
										class="btn btn-success btn-sm display_none" id="downloadbtn"
										value="Download File" />
									</i>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Proc
											Cost :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost" class="wordbreak">${ViewAssetsCmd.b_cost}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>CD
											Drive :</strong></label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
										<label id="lblscan"></label> <input type="radio" id="scan"
											value="Yes" name="cd_drive" class="display_none"/>&nbsp <label
											for="yes" class="display_none">Yes</label>&nbsp <input
											type="radio" id="scan" name="cd_drive" value="No"
											checked="checked" class="display_none"/>&nbsp <label
											for="no" class="display_none">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					

<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong>Ram -->
<!-- 											Slot Quantity :</strong></label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblpart_rem_slot_qty">${ViewAssetsCmd.ram_slot_qty}</label> --%>

<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->



				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>CRV
											Date : </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblproc_date">${ViewAssetsCmd.proc_date}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Gem Contract No :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblgem_no">${ViewAssetsCmd.gem_no}</label>

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
									<label id="lblcrv_date">${ViewAssetsCmd.crv_no}</label>

								</div>
							</div>
						</div>
					</div>
							
				
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="${screenurl}" class="btn btn-success btn-sm">Back</a>
					<i class="fa fa-download"></i><input type="button"
						class="btn btn-primary btn-sm" value="Print Page" id="btn_p">
					<button type="button" value="" class="btn btn-info btn-sm" id="btn_l">
    Log Book Coming Soon
</button>
				</div>
			</div>
		</div>
	</div>

</form:form>

<script nonce="${cspNonce}">


document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('btn_p').onclick = 
		function() {
		return printDiv();
  	};
	document.getElementById('b_head').onclick = 
		function() {
		return fn_bHead();
  	};
	document.getElementById('download_i').onclick = 
		function() {
		return download_file();
  	};
	document.getElementById('make_name').onchange = 
		function() {
		return fn_modelName();
  	};
  	
	document.getElementById('asset_type').onchange = 
		function() {
		return fn_makeName();
  	};
  	

});



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

// 	function fn_bHead() {

		
// 		var b_head = $("select#b_head").val();
// 		$
// 				.post("getBudgetHeadList?" + key + "=" + value, {
// 					b_head : b_head
// 				})
// 				.done(
// 						function(j) {

// 							var options = '';
// 							for (var i = 0; i < j.length; i++) {
// 								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
// 										+ j[i][1] + '</option>';
// 							}
// 							$("select#b_code").html(options);
// 						}).fail(function(xhr, textStatus, errorThrown) {
// 				});
// 	}

	function ParseDateColumncommission(data) {

		var date = "";
		if (data != null && data != "") {
			var d = new Date(data);
			var day = ('0' + d.getDate()).slice(-2);
			var month = ('0' + (d.getMonth() + 1)).slice(-2);
			var year = "" + d.getFullYear();
			date = day + "/" + month + "/" + year;
		}
		return date;
	}
	function printDiv() {
		var cspNonce='${cspNonce}';
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window
				.open(
						'',
						'_blank',
						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		
		popupWindow.document.write(
			    '<html>' +
			        '<head>' +
			            '<link rel="stylesheet" href="js/assets/css/bootstrap.min.css" nonce="' + cspNonce + '">' +
			            '<link rel="stylesheet" href="js/cue/printWatermark.css" nonce="' + cspNonce + '">' +
			            '<style nonce="' + cspNonce + '">' +
			                'table td { font-size: 50px; font-weight: bold !important; }' +
			                'table th { font-size: 12px !important; }' +
			                'tbody th { font-size: 10px; font-weight: normal !important; }' +
			                '.col-md-12 { display: inline-flex; }' +
			                '.f_22 { font-size: 22px; }' +
			                '.f_28 { font-size: 28px; }' +
			                '.text_align_center { text-align: center; }' +
			                '.text_decoration_underline { text-decoration: underline; }' +
			                '.margint_top_56 { margin-top: -56px; float: right; max-width: 155px; height: 50px; }' +
			                '.pd_right { padding-right: 50px; }' +
			                '.height50 { height: 50px; } .display_none{display:none;}' +
			            '</style>' +
			        '</head>' +
			        '<body id="id_body">' +
			            '<table>' +
			                '<tr>' +
			                    '<td align="left">' +
			                        '<img src="js/miso/images/indianarmy_smrm5aaa.jpg" class="height50">' +
			                    '</td>' +
			                    '<td align="center">' +
			                        '<h4 class="f_22 text_align_center"> AFMS IT ASSETS</h4>' +
			                    '</td>' +
			                    '<td algin="right">' +
			                        '<img src="js/miso/images/dgis-logo.png" class="margint_top_56">' +
			                    '</td>' +
			                '</tr>' +
			            '</table>' +
			            '<h5 class="text_decoration_underline text_align_center"><b>RESTRICTED</b></h5>' +
			            '<h1 class="f_28 text_align_center">VIEW COMPUTING ASSETS DETAILS</h1><br>' +
			            innerContents +
			        '</body>' +
			    '</html>'
			);

		    popupWindow.onload = function() {
		        var body = popupWindow.document.getElementById('id_body');

		        body.oncopy = function() { return false; };
		        body.oncut = function() { return false; };
		        body.onpaste = function() { return false; };
		        body.oncontextmenu = function() { return false; };
				window.focus();
		        popupWindow.print();
		        popupWindow.close();
		    };
		    popupWindow.document.close();

		
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
	 
	 
	 
	 
	$(document).ready(function() {
		
		debugger;
		var br_field = $("#unserviceable_state").val();
		if(br_field = 2){
			
			$("#file_show1").show();
			var filename1 = '${ViewAssetsCmd.br_certificate}'; 

			if (filename1 && filename1.trim() !== "") {
			    var fileName3 = filename1.substring(filename1.lastIndexOf('/') + 1); 
			    $("#uploadedFileName1").text(fileName3); 
			    $('#download_i1').removeClass('display_none'); 
			} else {
			    $("#uploadedFileName1").text('No file uploaded'); 
			    $('#download_i1').removeClass('display_none');
			}
			
		}
		
debugger;
var filename = '${ViewAssetsCmd.u_file}'; 

if (filename && filename.trim() !== "") {
    var fileName2 = filename.substring(filename.lastIndexOf('/') + 1); 
    $("#uploadedFileName").text(fileName2); 
    $('#download_i').removeClass('display_none'); 
} else {
    $("#uploadedFileName").text('No file uploaded'); 
    $('#download_i').removeClass('display_none');
}
		
		$("#section").val("${ViewAssetsCmd.section}");
		$("#maintainAgency").val("${ViewAssetsCmd.maintainagency}");
		var selectedValue = "${ViewAssetsCmd.b_head}".trim(); // Trim any spaces
        console.log("Selected Value: ", selectedValue); // Check the value
        $("#b_head").val(selectedValue); // Set the value
        console.log("Dropdown Value After Set: ", $("#b_head").val());
		debugger;
		$("#ssd_capi").val("${ViewAssetsCmd.ssd_capi}");
		if ($("#ssd_capi").val()=="0" || $("#ssd_capi").val()=='')
			{
			$("#lblssd_check").text("No");
			
			}
		if ($("#ssd_capi").val()!="0" && $("#ssd_capi").val()!='')
		{
			$("#lblssd_check").text("Yes");
			$("#lblssd_capi").text($("#ssd_capi option:selected").text());
			
		}
		
		
		
		$("#asset_type").val("${ViewAssetsCmd.asset_type}");
		var asset_type = $("#asset_type option:selected").text();
		$("#s_state").val('${ViewAssetsCmd.s_state}');
		var asset_type = $("#asset_type option:selected").text();
		
		fn_brand_other();
		var make_name = $("#make_name option:selected").text();
		fn_make_other();
		
		var model_name = $("#model_name option:selected").text();
		var processor_type = $("#processor_type option:selected").text();
		fn_pro_type_other();
		var ram_capi = $("#ram_capi option:selected").text();
		var hdd_capi = $("#hdd_capi option:selected").text();
		
		fn_os_other();
		var operating_system = $("#operating_system option:selected").text();
		
		
		
		$("#office").val("${ViewAssetsCmd.office}");
		
		
		
		var office = $("#office option:selected").text();
		fn_office_other();
		$("#antivirus").val("${ViewAssetsCmd.antivirus}");
		var antivirus = $("#antivirus option:selected").text();
		var os_patch = $("#os_patch option:selected").text();
		var dply_envt = $("#dply_envt option:selected").text();
	
		var s_state = $("#s_state option:selected").text();
		if(s_state=="Un-serviceable"){
			$("#uni").show();
			 $("#unsv_div").show();
			 $("#maintaince_agencyldiv").show();
			 
		}
		
		selectBer();
		var unserviceable_state = $("#unserviceable_state option:selected").text();
		
		var unsv_from_dt1 = '${ViewAssetsCmd.unsv_from_dt}';
		$("#lblunsv_from_dt1").text(ParseDateColumncommission(unsv_from_dt1));
		
		var connecting_tech = $("#connecting_tech option:selected").text();
		var ethernet_port = $("#ethernet_port option:selected").text();

		var warrenty_dt = '${ViewAssetsCmd.warranty}';

		warrenty_dt = warrenty_dt.substring(0, 10);
		$("#lblwarranty").text(ParseDateColumncommission(warrenty_dt));

        
// 		$("#b_head").val("${ViewAssetsCmd.b_code}");
// 		var b_head = $("#b_head option:selected").text();
// 		$("#lblb_head").text(b_head);

		var b_code = $("#b_code option:selected").text();
		$("#lblb_code").text(b_code);

		var proc_date = '${ViewAssetsCmd.proc_date}';
		proc_date = proc_date.substring(0, 10);
		$("#lblproc_date").text(ParseDateColumncommission(proc_date));
		$("#maintaince_agencylbl").text($("#maintainAgency option:selected").text());
		$("#b_headlbl").text($("#b_head option:selected").text());
		
		
		
		
		
		if(s_state=="Un-serviceable"){
			$("#uni").show();
		}
		
	
		
		$("#lblsection").text( $("#section option:selected").text());
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
		$("#lblunserviceable_state").text(unserviceable_state);
		$("#lblconnecting_tech").text(connecting_tech);
		$("#lblethernet_port").text(ethernet_port);

		$("#scan").val("${ViewAssetsCmd.cd_drive}");
		
		var scan = $('input[name=cd_drive]').val();
		
		
		$("#lblscan").text(scan);
		
	$("#anti_virus").val("${ViewAssetsCmd.antiviruscheck}");
	var anti_virus = $('input[name=antiviruscheck]').val();
		
				$("#lblantiviruscheck").text(anti_virus);
				if($("#anti_virus").val()=='Yes')
					{
					$("#Antivirus_exp_date").text(ParseDateColumncommission('${ViewAssetsCmd.antivirus_expry}'));
					$("#antivirus_exp_div").show();
					$("#lblantiviruscheck").show();
					
					}
	

				
	
				
				
				

				//$("#supplierId").val('${ViewAssetsCmd.supplier_name}');
				getSupplierData('${ViewAssetsCmd.supplier_name}');
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
					                 $('#contactNumber').text(data[0].contact_number);
					                 $('#supplier_name').text(data[0].supplier);

					            }
					            
					        },
					        error: function(xhr, status, error) {
					            console.error("AJAX Error: ", status, error);
					        }
					    });
					}

	});
	
	
	
	 function selectBer()
	 {
		 
		 
		 var a =$("select#unserviceable_state").val();
		 if(a == '1')
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
	
		    if (confirm("Are you sure you want to download the file?")) {
				
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
		}
</script>