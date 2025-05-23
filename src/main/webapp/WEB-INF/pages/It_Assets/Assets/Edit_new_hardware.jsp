<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<form:form name="MainAssets" id="MainAssets" action="MainAssetAction" method="post" class="form-horizontal" modelAttribute="MainAssetsCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT COMPUTING HARDWARE DETAILS</h5>
<!-- 					<h6 class="enter_by"> -->
<!-- 						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span> -->
<!-- 					</h6> -->
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete" autocomplete="off"></form:hidden>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Computing Assets Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="asset_type" id="asset_type" class="form-control" onchange="fn_makeName();" readonly="true">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name" class="form-control" onchange="fn_modelName();" readonly="true">
									<form:option value="0" >--Select--</form:option>
										
									</form:select>
								</div>
							</div>
						</div>
					</div>
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="model_name" id="model_name" class="form-control">
									<form:option value="0" >--Select--</form:option>
										
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Processor Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="processor_type" id="processor_type" class="form-control">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${processor_typeList}" varStatus="num">
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>RAM Capacity </label>
								</div>
								<div class="col-md-8">
									<form:select path="ram_capi" id="ram_capi" class="form-control">
									<form:option value="0" >--Select--</form:option>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>HDD Capacity </label>
								</div>
								<div class="col-md-8">
									<form:select path="hdd_capi" id="hdd_capi" class="form-control">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Operating System </label>
								</div>
								<div class="col-md-8">
									<form:select path="operating_system" id="operating_system" class="form-control">
									<form:option value="0" >--Select--</form:option>
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
									<label class=" form-control-label"><strong style="color: red;"> </strong>Office</label>
								</div>
								<div class="col-md-8">
									<form:select path="office" id="office" class="form-control">
									<form:option value="0" >--Select--</form:option>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>AntiVirus Installed </label>
								</div>
								<div class="col-md-8" onclick="anti_show();">
									<form:radiobutton  id="antiviruscheck1"  value="Yes"
 										path="antiviruscheck"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="antiviruscheck2" path="antiviruscheck"  value="No" 
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="AntiVirusDiv" style="display:none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>AntiVirus</label>
								</div>
								<div class="col-md-8">
									<form:select path="antivirus" id="antivirus" class="form-control">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${AntivirusList}" varStatus="num">
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>OS/Firmware Activation and subsequent Patch Updation Mechanism </label>
								</div>
								<div class="col-md-8">
									<form:select path="os_patch" id="os_patch" class="form-control">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${osFirmwareList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Dply Envt as Applicable</label>
								</div>
								<div class="col-md-8">
									<form:select path="dply_envt" id="dply_envt" class="form-control">
									<form:option value="0" >--Select--</form:option>
										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;"> </strong>Connecting Technology</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:select path="connecting_tech" id="connecting_tech" class="form-control"> --%>
<%-- 									<form:option value="0" >--select--</form:option> --%>
<%-- 										<c:forEach var="item" items="${getConnectingTechList}" varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!--  						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;"> </strong>Dimension</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:input type="text" id="dimension" path="dimension" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Ethernet Port</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:select path="ethernet_port" id="ethernet_port" class="form-control"> --%>
<%-- 									<form:option value="0" >--select--</form:option> --%>
<%-- 										<c:forEach var="item" items="${getEthernetPortList}" varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!--  						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Part Number</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:input type="text" id="part_no" path="part_no" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12">
 						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>RAM Slot Quantity</label>
								</div>
								<div class="col-md-8">
									<form:input type="number" id="ram_slot_qty" path="ram_slot_qty" min="1" max="1000" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>CD Drive</label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
									<form:radiobutton  id="scan"  value="Yes"
 										path="cd_drive"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="scan" path="cd_drive"  value="No"
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
								</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Warranty</label>
								</div>
								<div class="col-md-8" onclick="warrenty_show();">
									<form:radiobutton  id="Warranty1"  value="Yes"
 										path="warrantycheck"   checked="checked"></form:radiobutton>&nbsp <label for="yes" >Yes</label>&nbsp   
  									<form:radiobutton  id="Warranty2" path="warrantycheck"  value="No" 
   										></form:radiobutton>&nbsp  
									<label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="WarrantyDiv">
							<div class="row form-group">
								<div class="col-md-4" >
									<label class=" form-control-label"><strong style="color: red;">* </strong>Warranty Upto</label>
								</div>
								<div class="col-md-8">
<!-- 									<input type="Date" id="warranty" name="warranty" class="form-control autocomplete" autocomplete="off"></input> -->
									<input type="text" name="warranty_dt" id="warranty" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								
								</div>
							</div>
						</div>
					</div>
					
					
					
					
					<div class="col-md-12">
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Warranty Upto</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- <!-- 									<input type="Date" id="warranty" name="warranty" class="form-control autocomplete" autocomplete="off"></input> --> 
<!-- 									<input type="text" name="warranty" id="warranty" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;" -->
<!-- 							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')"  -->
<!-- 							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" > -->
								
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-md-6" id="countDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Total Count</label>
								</div>
								<div class="col-md-8">
								<input type="text" id="assetcount" name="assetcount" onkeyup="addMultiAsset()" onblur="counthover()" value="1" onkeypress="return isNumber(event)" class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
 						
					</div>
					
					
					<div class="" id="multiAssetDiv">
					<div class="row" style="border: solid #b7b6b0 5px; padding: 10px;    margin: 5px;">
<!-- 					<input type="text" id="ch_id1" name="ch_id1" class="form-control autocomplete" autocomplete="off" style="display: none;"></input> -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Model Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="model_number1" name="model_number1" class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
 						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="machine_number1" name="machine_number1" class="form-control autocomplete machineno_class" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>MAC Address</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="mac_address1" name="mac_address1" class="form-control autocomplete" onkeyup="makeMacAddress(this);" onkeypress="makeMacAddress(this);return /[0-9a-fA-F]/i.test(event.key);" maxlength="17" autocomplete="off"></input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>IP Address </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ip_address1" name="ip_address1" maxlength="15" class="form-control autocomplete" onchange="ValidateIPaddress(document.MainAssets.ip_address1)" onkeypress="return /[.0-9]/i.test(event.key);" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>

					
					<div class="panel-group" id="accordion5" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_warrenty" onclick="divN(this)"
								><b>SERVICE DETAILS</b></a>
						</h4>
					</div>
					<div id="collapsewarrantey" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
							<div class="row">
							<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Serviceable State</label>
								</div>
									<div class="col-md-8">
                						<form:select path="s_state" id="s_state" class="form-control" onchange="serviceStChange();">
											<form:option value="0">--Select--</form:option>
											  <c:forEach var="item" items="${getServiceable_StateList}" varStatus="num">
										    <form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
									     </c:forEach>
										 </form:select>
							
		        					</div>
							</div>
						</div>
						<div class="col-md-6" id="un_show" style="display:none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>UN-Serviceable State</label>
								</div>
									<div class="col-md-8">
                						<form:select path="unserviceable_state" id="unserviceable_state" class="form-control" >
											<form:option value="0">--Select--</form:option>
											 <c:forEach var="item" items="${UNServiceableList}" varStatus="num">
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
					
					
					
					
					<div class="panel-group" id="accordion4">
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_budget" onclick="divN(this)"
								><b>PROCUREMENT DETAILS</b></a>
						</h4>
					</div>
					<div id="collapse1budget" class="panel-collapse collapse">
			                <div class="card-body card-block"><br>
							<div class="row">
							
							<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Proc Cost</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="b_cost" path="b_cost"   onkeypress="return isNumber(event)" class="form-control autocomplete" autocomplete="off" readonly="true"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Proc Date</label>
								</div>
								<div class="col-md-8">
<!-- 									<input type="Date" id=proc_date name="proc_date" class="form-control autocomplete" autocomplete="off"></input> -->
								<input type="text" name="proc_dt" id="proc_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>
							</div>
						</div>
					</div>
							<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Budget Head</label>
								</div>
								<div class="col-md-8">
								<form:select path="b_head" id="b_head" class="form-control" onchange="fn_B_Head()" readonly="true">
											<form:option value="0">--Select--</form:option>
											 <c:forEach var="item" items="${getBudgetHeadList}" varStatus="num">
											<form:option value="${item[1]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
										 </form:select>
<%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Budget Code</label>
								</div>
								<div class="col-md-8">
								<form:select path="b_code" id="b_code" class="form-control" readonly="true">
											<form:option value="0">--Select--</form:option>
<%-- 											 <c:forEach var="item" items="${UNServiceableList}" varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
										 </form:select>
<%-- 									<form:input type="text" id="b_code" path="b_code" class="form-control autocomplete" autocomplete="off"></form:input> --%>
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
					<a href="Search_HardwareUrl" class="btn btn-success btn-sm">Back</a>
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 
					
				</div>
			</div>
		</div>
	</div>
	
</form:form>

<script>
$(document).ready(function() {
	$.ajaxSetup({
		 async : false
});
	warrenty_show();
	datepicketDate('warranty');
	datepicketDate('proc_date');
	
	if('${MainAssetsCmd.id}' != "0"){
		$("#countDiv").hide();
		$("#model_number1").val('${MainAssetsCmd.model_number}');
		$("#machine_number1").val('${MainAssetsCmd.machine_number}');
		$("#mac_address1").val('${MainAssetsCmd.mac_address}');
		$("#ip_address1").val('${MainAssetsCmd.ip_address}');
	}
	
	$("#warranty").val((ParseDateColumncommission('${MainAssetsCmd.warranty}')));	
	$("input[name='warrantycheck'][value='${MainAssetsCmd.warrantycheck}']").prop("checked",true);
	$("#proc_date").val((ParseDateColumncommission('${MainAssetsCmd.proc_date}')));	
	
// 		$("#warranty").val(('${MainAssetsCmd.warranty}').substring(0, 10));	
// 		$("#proc_date").val(('${MainAssetsCmd.proc_date}').substring(0, 10));	
		$("input[name='antiviruscheck'][value='${MainAssetsCmd.antiviruscheck}']").prop("checked",true);
		anti_show();
		$("input[name='cd_drive'][value='${MainAssetsCmd.cd_drive}']").prop("checked",true);
		
		fn_makeName();		
		$("select#make_name").val('${MainAssetsCmd.make_name}');	
		fn_modelName();
		$("select#model_name").val('${MainAssetsCmd.model_name}');	
		
		
		serviceStChange();
		
		fn_B_Head();
		$("select#b_code").val('${MainAssetsCmd.b_code}');	
	
});
</script>

<script>
function Validate(){
// 	alert($("#MainAssets").serialize());
	
	
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
	if($("#model_name").val()==0 || $("#model_name").val()==""){
		alert("Please Select Model Name ");
		$("#model_name").focus();
		return false;
	}
	
// 	if(!ValidateMACAddress( $("#mac_address").val())){
// 	 	alert("You have entered an invalid MAC Address!")  
// 		$("#mac_address").focus();
// 		return false;
// 	 }
	
// 	if( $("#ip_address").val()!=""){
// 		 if(!ValidateIPaddress( $("#ip_address").val())){
// 		 	alert("You have entered an invalid IP Address!")  
// 			$("#ip_address").focus();
// 			return false;
// 		 }
// 	}
	if($("#processor_type").val()==0 || $("#processor_type").val()==""){
		alert("Please Select Processor Type ");
		$("#processor_type").focus();
		return false;
	}
	if($("#ram_capi").val()==0 || $("#ram_capi").val()==""){
		alert("Please Select RAM Capacity  ");
		$("#ram_capi").focus();
		return false;
	}
	if($("#hdd_capi").val()==0 || $("#hdd_capi").val()==""){
		alert("Please Select HDD Capacity ");
		$("#hdd_capi").focus();
		return false;
	}
	
	if($("#operating_system").val()==0 || $("#operating_system").val()==""){
		alert("Please Select Operating System  ");
		$("#operating_system").focus();
		return false;
	}
	
	
	
	var antivirusChecked=$('input[name="antiviruscheck"]:checked').val();
	
	if(antivirusChecked.toUpperCase()=="YES"){
		if($("#antivirus").val()==0 || $("#antivirus").val()==""){
			alert("Please Select AntiVirus  ");
			$("#antivirus").focus();
			return false;
		}
	}
	else{
		$("#antivirus").val('0');
	}
	
	
	if($("#os_patch").val()==0 || $("#os_patch").val()==""){
		alert("Please Select OS/Framware Activation and subsequent Patch Updation Mechanism   ");
		$("#os_patch").focus();
		return false;
	}
	
	if($("#dply_envt").val()==0 || $("#dply_envt").val()==""){
		alert("Please Select Dply Envt as Applicable  ");
		$("#dply_envt").focus();
		return false;
	}
	
// 	if($("#ethernet_port").val()==0 || $("#ethernet_port").val()==""){
// 		alert("Please Select Ethernet Port");
// 		$("#ethernet_port").focus();
// 		return false;
// 	}
// 	if($("#part_no").val()==0 || $("#part_no").val()==""){
// 		alert("Please Enter Part Number");
// 		$("#part_no").focus();
// 		return false;
// 	}
	
	if( $("#ram_slot_qty").val()=="" || $("#ram_slot_qty").val()=="0"){
		alert("Please Enter RAM Slot Quantity");
		$("#ram_slot_qty").focus();
		return false;
	}
	 
	 var warrantycheckChecked=$('input[name="warrantycheck"]:checked').val();
		
		if(warrantycheckChecked.toUpperCase()=="YES"){
			if($("#warranty").val()==0 || $("#warranty").val()==""){
				alert("Please Select Warranty  ");
				$("#warranty").focus();
				return false;
			}
		}
		else{
			$("#warranty").val('');
		}
		
	 
		
	
	if (!$("input[name='cd_drive']").is(':checked')) {
		   alert('Please Check CD Drive!');
		}
	
var len=$("#assetcount").val();
	
		var i=1;
	for(i;i<=len;i++)
		{
		if( $("#model_number"+i).val()==""){
			alert("Please Enter Model Number ");
			$("#model_number"+i).focus();
			return false;
		}
		
		if( $("#machine_number"+i).val()==""){
			alert("Please Enter Machine No. ");
			$("#machine_number"+i).focus();
			return false;
		}
		
		if( $("#mac_address"+i).val()==""){
			alert("Please Enter MAC Address");
			$("#mac_address"+i).focus();
			return false;
		}
		
	 	if(!ValidateMACAddress( $("#mac_address"+i).val())){
	 	alert("You have entered an invalid MAC Address!")  
		$("#mac_address"+i).focus();
		return false;
	 	}
	
		if( $("#ip_address"+i).val()!=""){
			 if(!ValidateIPaddress( $("#ip_address"+i).val())){
			 	alert("You have entered an invalid IP Address!")  
				$("#ip_address"+i).focus();
				return false;
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
	
// 	$('.machineno_class').each(function(i, obj) {
// 		$("#fromdate"+(i+1)).val(formateDate(obj.value));
// 		 });
	
	
	if( $("#s_state").val()=="" || $("#s_state").val()=="0"){
		alert("Please Select Serviceable State");
		$("#s_state").focus();
		return false;
	}
	if($("#s_state").val()==2){
		if( $("#unserviceable_state").val()=="" || $("#unserviceable_state").val()=="0"){
			alert("Please Select UN-Serviceable State");
			$("#unserviceable_state").focus();
			return false;
		}
	}
	if( $("#b_cost").val()=="0" ){
		alert("Proc Cost Must be Greater Than Zero");
		$("#b_cost").focus();
		return false;
	}
	
	
// 	if( $("#proc_date").val()=="" || $("#proc_date").val().toUpperCase()=="DD/MM/YYYY"){
// 		alert("Please Enter Proc Date");
// 		$("#proc_date").focus();
// 		return false;
// 	}
	
	if( $("#b_head").val()=="0"){
		alert("Please Select Budget Head");
		$("#b_head").focus();
		return false;
	}
	
	if( $("#b_code").val()=="0"){
		alert("Please Select Budget Code");
		$("#b_code").focus();
		return false;
	}
	
}

function ValidateIPaddress(ipaddress) {  
	  if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress)) {  
	    return (true)  
	  }  
	 
	  return (false)  
	} 

function ValidateMACAddress(value){
	
regex=/^([0-9a-f]{2}([:-]|$)){6}$|([0-9a-f]{4}([.]|$)){3}$/i;
	
	if (regex.test(value)){
		return true;
	}
	else{
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
</script>
 
 <script>

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
 
 function serviceStChange()
 {
	 var a =$("select#s_state").val();
	 if(a == '2')
		 {
		    $("#un_show").show();
		 }
	 else
		 {
		 $("#un_show").hide();
		 $('#unserviceable_state').val('0');
		 }
 }
 
 function anti_show()
 {
	 if ($("#antiviruscheck1").prop("checked")) {
		 $("#AntiVirusDiv").show();
			}
		else{
			$('#AntiVirusDiv').hide();
			$('#antivirus').val('0');
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
// 			return false;
		}
		if (Assetcount=="" ) {
			Assetcount = 1;
		}
		
			var options="";
			for(var i = 1; i <= parseInt(Assetcount); i++) {
				options += '<div class="row" style="border: solid #b7b6b0 5px; padding: 10px;    margin: 5px;">'+
						'<input type="text" id="ch_id'+i+'" name="ch_id'+i+'" class="form-control autocomplete" autocomplete="off" style="display: none;"></input>'+
						'<div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
							'<label class=" form-control-label"><strong style="color: red;">* </strong>Model Number</label>	</div>'+
						'<div class="col-md-8">'+
						'<input type="text" id="model_number'+i+'" name="model_number'+i+'" class="form-control autocomplete" autocomplete="off"></input>'+
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" id="machine_number'+i+'" name="machine_number'+i+'" class="form-control autocomplete machineno_class" autocomplete="off"></input>'+
						'</div></div></div></div><div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;">* </strong>MAC Address</label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" id="mac_address'+i+'" name="mac_address'+i+'" class="form-control autocomplete" onkeyup="makeMacAddress(this);" onkeypress="makeMacAddress(this);return /[0-9a-fA-F]/i.test(event.key);" maxlength="17" autocomplete="off"></input>'+
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;"> </strong>IP Address </label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" id="ip_address'+i+'" name="ip_address'+i+'" maxlength="15" onkeypress="return /[.0-9]/i.test(event.key);" class="form-control autocomplete" autocomplete="off"></input>'+
						'</div></div></div></div></div>';


			}
			$("#multiAssetDiv").html(options);
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
	
	
// 	function ValidateIPaddress(inputText)
// 	 {
// 	 var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
// 	 if(inputText.value.match(ipformat))
// 	 {
// 	 document.form1.text1.focus();
// 	 return true;
// 	 }
// 	 else
// 	 {
// 	 alert("You have entered an invalid IP address!");
// 	 document.form1.text1.focus();return false;
// 	 }
// 	 }
 </script>
 