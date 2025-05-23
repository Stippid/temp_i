<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<script src="js/Calender/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script src="js/Calender/jquery-ui.js" nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/layout/css/animation.css"nonce="${cspNonce}">
<link href="js/cue/cueWatermark.css" rel="Stylesheet"nonce="${cspNonce}"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css" nonce="${cspNonce}">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"nonce="${cspNonce}"></link>
<link rel="stylesheet" href="js/assets/collapse/collapse.css"nonce="${cspNonce}">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css" nonce="${cspNonce}">


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
  right:1em;
  bottom:2em;
  color:#FAFAFA;
  text-decoration:none;
  background:#F44336;
  padding:5px;
  border-radius:5px;
  border:1px solid #e0e0e0;
  position:fixed;
  font-size: 180%;
  

}

label {
    word-break: break-word;
}


</style>


<form:form name="PeripheralAssetsApp" id="PeripheralAssetsApp"
	action="AppEditPeripheralAction" method="post" class="form-horizontal"
	modelAttribute="AppPeripheralCmd">

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT PERIPHERAL CENSUS RETURN</h5>
				</div>
				<input type="hidden" id="id" name="id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="c_id" name="c_id"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="c_id1" name="c_id1"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="c_id2" name="c_id2"
					class="form-control autocomplete" autocomplete="off"> <input
					type="hidden" id="c_id3" name="c_id3"
					class="form-control autocomplete" autocomplete="off">

				<div class="card-body card-block">
				
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Section
											No </label>
								</div>
								<div class="col-md-8">
									<label id="lblsection">${AppPeripheralCmd.section}</label> <select
										id="section" name="section" class="form-control display_none">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}"
												<c:if test="${item.id == AppPeripheralCmd.section}">selected</c:if>>${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">User
											Name</label>
								</div>
								<div class="col-md-8">
									<label id="username_label">${AppPeripheralCmd.username}</label>

								</div>
							</div>
						</div>
					</div>


					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Peripheral Type </label>
								</div>
								<div class="col-md-8">
									<label id="lblAsset_type"></label>
									<form:select path="assets_type" id="assets_type"
										class="form-control display_none">

										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Type of HW</label>
								</div>
								<div class="col-md-8">
									<label id="lbltype_of_hw"></label>
									<form:select path="type_of_hw" id="type_of_hw"
										 class="form-control display_none"
										>

										<c:forEach var="item" items="${hardwareListDDL}"
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
									<label class=" form-control-label"> Year of Proc </label>
								</div>
								<div class="col-md-8">
									<label id="lblyear_of_proc">${AppPeripheralCmd.year_of_proc}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Year of
										Manufacturing </label>
								</div>
								<div class="col-md-8">
									<label id="lblyear_of_manufacturing">${AppPeripheralCmd.year_of_manufacturing}</label>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Make Name</label>
								</div>
								<div class="col-md-8">
									<label id="lblmake_name"></label>
							<form:select path="make_name" id="make_name" class="form-control display_none">
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Model Name</label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_name"></label>
									
									
									<form:select path="model_name" id="model_name" class="form-control display_none">
								
										
									</form:select>

								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Proc Cost</label>
								</div>
								<div class="col-md-8">
									<label id="lblproc_coste">${AppPeripheralCmd.b_cost}</label>
									<%-- <form:input type="text" id="proc_cost" path="proc_cost" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Machine No.</label>
								</div>
								<div class="col-md-8">
									<label id="lblmachine_no">${AppPeripheralCmd.machine_no}</label>
									<%-- <form:input type="text" id="machine_no" path="machine_no" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Model Number</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblmodel_no">${AppPeripheralCmd.model_no}</label> --%>
<%-- 									<form:input type="text" id="model_no" path="model_no" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Remarks </label>
								</div>
								<div class="col-md-8">
									<label id="lblremarks">${AppPeripheralCmd.remarks}</label>
									<%-- <form:input type="text" id="remarks" path="remarks" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Type</label>
								</div>
								<div class="col-md-8">
									<label id="lbltype"></label>
									<form:select path="type" id="type" class="form-control display_none"
										>

										<c:forEach var="item" items="${Type1}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
<!-- 									<label class=" form-control-label"> UPS Capacity </label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="lblups_capacity"></label> -->
<%-- 									<form:select path="ups_capacity" id="ups_capacity" --%>
<%-- 										class="form-control" style="display:none;"> --%>

<%-- 										<c:forEach var="item" items="${UpsCapacity}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Monochrome/Color</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblmonochrome_color">${AppPeripheralCmd.monochrome_color}</label> --%>
<%-- 									<form:input type="text" id="monochrome_color" path="monochrome_color" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> IS Networked </label>
								</div>
								<div class="col-md-8">
									<label id="lblis_networked">${AppPeripheralCmd.is_networked}</label>
								
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> IP Address(For
										Networked Printers Only)</label>
								</div>
								<div class="col-md-8">
									<label id="lblip_address">${AppPeripheralCmd.ip_address}</label>
									
								</div>
							</div>
						</div>
<!-- 					</div> -->

<!-- 					<div class="col-md-12"> -->
						
<!-- 						<div class="col-md-6" style="display: none;" id="cap"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Capacity(Lumens)</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblcapacity">${AppPeripheralCmd.capacity}</label> --%>
									
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12 display_none" id="mf">
						<div class="col-md-2">
							<label class=" form-control-label"> Features Avlb with
								MFD</label>
						</div>
						<div class="1">
							<b><label>Print</label></b>
						</div>
						<div class="col-md-2">
							<label id="lblprint"></label> <input type="radio" id="print"
								value="Yes" name="print" class="display_none"/>&nbsp <label
								for="yes" class="display_none">Yes</label>&nbsp <input
								type="radio" id="print" name="print" value="No"
								checked="checked" class="display_none" />&nbsp <label
								for="no" class="display_none">No</label>
						</div>
						<div class="1">
							<b><label>Scan</label></b>
						</div>
						<div class="col-md-2">
							<label id="lblscan"></label> <input type="radio" id="scan"
								value="Yes" name="scan"class="display_none" />&nbsp <label
								for="yes" class="display_none">Yes</label>&nbsp <input
								type="radio" id="scan" name="scan" value="No" checked="checked"
								class="display_none"/>&nbsp <label for="no"
								class="display_none">No</label>
						</div>

						<div class="1">
							<b><label>Photography</label></b>
						</div>
						<div class="col-md-2">
							<label id="lblphotography"></label> <input type="radio"
								id="photography" value="Yes" name="photography"
								class="display_none" />&nbsp <label for="yes"
								class="display_none">Yes</label>&nbsp <input type="radio"
								id="photography" name="photography" value="No"
								class="display_none"checked="checked" />&nbsp <label
								for="no" class="display_none">No</label>
						</div>

						<div class="1">
							<b><label>Colour</label></b>
						</div>
						<div class="col-md-2">
							<label id="lblcolour"></label> <input type="radio" id="colour"
								value="Yes" name="colour" class="display_none">&nbsp
							<label for="yes" class="display_none">Yes</label>&nbsp <input
								type="radio" id="colour" name="colour" value="No"
								class="display_none"checked="checked" />&nbsp <label
								for="no" class="display_none">No</label>
						</div>
					</div>
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6" style="display: none;" id="res"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Resolution</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="lblresolution"></label> -->
									
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6" style="display: none;" id="no"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> No Of Ports</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lblno_of_ports">${AppPeripheralCmd.no_of_ports}</label> --%>
									
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"> Max Paper Size</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<label id="lblpaper_size"></label> -->
<%-- 									<form:select path="paper_size" id="paper_size" --%>
<%-- 										class="form-control" style="display:none;"> --%>

<%-- 										<c:forEach var="item" items="${getPaper_SizeList}" --%>
<%-- 											varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>

<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6" style="display: none;" id="display-type"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label">Display Type</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<label id="lbldisplay_type">${AppPeripheralCmd.display_type}</label> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->

<!-- 					</div> -->
					<div class="col-md-12 display_none" id="display">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Display Size</label>
								</div>
								<div class="col-md-8">
									<label id="lbldisplay_size">${AppPeripheralCmd.display_size}</label>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Display Connector</label>
								</div>
								<div class="col-md-8">
									<label id="lbldisplay_connector"></label> <select
										name="display_connector" id="display_connector"
										class="form-control display_none">

										<option value="VGA">VGA</option>
										<option value="HDMI">HDMI</option>
										<option value="VGA&HDMI BOTH">VGA&HDMI BOTH</option>
									</select>

								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Budget Head</label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head">${AppPeripheralCmd.b_head}</label>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Budget Code</label>
								</div>
								<div class="col-md-8">
									<label id="lblb_code"></label>
									<form:select path="b_code" id="b_code" class="form-control display_none"
										>
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
									<label class=" form-control-label"> Budget Cost</label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost">${AppPeripheralCmd.b_cost}</label>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Proc Date</label>
								</div>
								<div class="col-md-8">
									<label id="lblb_proc_date"></label>
									
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12 display_none"  id="net">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Ethernet Port</label>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Management Layer</label>
								</div>
								<div class="col-md-8">
									<label id="lblb_management_layer">${AppPeripheralCmd.management_layer}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12 display_none"  id="visual">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Visulizer Display
										Size</label>
								</div>
								<div class="col-md-8">
									<label id="v_display_size">${AppPeripheralCmd.v_display_size}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Visulizer Display
										Connector</label>
								</div>
								<div class="col-md-8">
									<label id="lblv_display_connector">${AppPeripheralCmd.v_display_connector}</label>
									<form:select path="v_display_connector"
										id="v_display_connector" class="form-control display_none"
										>

										<c:forEach var="item" items="${getDisplay_ConnectorList}"
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
												<label class="form-control-label">CRV No.</label>
											</div>
											<div class="col-md-8">
													<label id="lbl_crv_no">${AppPeripheralCmd.crv_no}</label>
												
											</div>
										</div>
					</div>
						<div class="col-md-6">
					<div class="row form-group">
											<div class="col-md-4">
												<label class="form-control-label">GeM Contact No.</label>
											</div>
											<div class="col-md-8">
													<label id="lbl_gem_no">${AppPeripheralCmd.gem_no}</label>
												
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
										data-parent="#accordion4" href="#" id="a_peripheral"
										><b>PERIPHERAL DETAILS</b></a>
								</h4>
							</div>
							<div id="collapseperipheral" class="panel-collapse collapse">
								<div class="card-body card-block">
									<br>
									<div class="row">
							
				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>Is Networked </label>
								</div>
								<div class="col-md-8" >
									<input type="radio"  id="is_networked1"  value="Yes" 
 										name="is_networked1"  ></input>&nbsp <label for="yes">Yes</label>&nbsp    
  									<input type="radio"  id="is_networked2" name="is_networked1"  value="No"  
   										 checked="checked"></input>&nbsp   
									<label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 display_none" id="ip_addressDiv1">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>IP Address(For Networked Device)</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="ip_address1" name="ip_address1" maxlength="15" class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>



									</div>
								</div>
							</div>
						</div>

					</div>
					
					
					<!-- PRINTER START -->

	<div class="panel-group" id="accordionprinter5" >
		<div class="panel panel-default" id="a_div2">
			<div class="panel-heading">
				<h4 class="panel-title main_title red" id="a_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
						data-parent="#accordion4" href="#" id="a_printer" 
						><b>PRINTER</b></a>
				</h4>
			</div>
			<div id="collapseprinter" class="panel-collapse collapse">
	            <div class="card-body card-block"><br>
				  <div class="row">
					<div class="col-md-12">				
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">*</strong>Monochrome/Color</label>
								</div>
								<div class="col-md-8">
								<input type="radio" id="monochrome_color_radio1" name="monochrome_color" value="monochrome">&nbsp <label for="monochrome">Monochrome</label>&nbsp <input type="radio"
											id="monochrome_color_radio2" name="monochrome_color" value="color"  checked="checked">&nbsp <label for="color">Color</label><br>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>Max Paper Size</label>
								</div>
								 <div class="col-md-8">
									  <form:select path="paper_size" id="paper_size" class="form-control" >
									  	<form:option value="0">--Select--</form:option>
										 <c:forEach var="item" items="${getPaper_SizeList}" varStatus="num">
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
								<label class=" form-control-label"><strong class="color_red">* </strong>Connectivity Type</label>
							</div>						
							 <div class="col-md-8">
								  <form:select path="connectivity_type" id="connectivity_type" class="form-control" >
								  	<form:option value="0">--Select--</form:option>
									 <c:forEach var="item" items="${getConnectivity_TypeList}" varStatus="num">
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

<!-- PRINTER END -->
					
		<!-- UPS START -->
	<div class="panel-group display_none" id="accordionups5">
		<div class="panel panel-default" id="a_div1">
			<div class="panel-heading">
				<h4 class="panel-title main_title red" id="a_div5">
					<a class="droparrow collapsed" data-toggle="collapse"
					data-parent="#accordion4" href="#" id="a_ups" 
					><b>UPS</b></a>
				</h4>
			</div>
			<div id="collapseups" class="panel-collapse collapse">
		      <div class="card-body card-block"><br>
				<div class="row">
				  <div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong class="color_red">* </strong>UPS Capacity </label>
							</div>
							<div class="col-md-8">
								<form:select path="ups_capacity" id="ups_capacity" class="form-control">
								<option value="0" >--Select--</option>
									<c:forEach var="item" items="${UpsCapacity}" varStatus="num">
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
					
<!-- PROJECTION SYS START -->

		<div class="panel-group" id="accordionprojectionsys5" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_projection" 
								><b>PROJECTION SYS</b></a>
						</h4>
					</div>
			<div id="collapseprojection" class="panel-collapse collapse">
			   <div class="card-body card-block"><br>
				  <div class="row">
				 <div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>Capacity(Lumens)</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="capacity" path="capacity" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>Hardware Interface</label>
								</div>
										<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" id= "checkboxid">
										<select name="hw_interface" id="hw_interface"
											class=" form-control">
											<option>Select Hardware Interface</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes checkboxcls"
										>
										<div >
											<input type="text" id="hw_interface_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Hardware">
										</div>
										<div>
											<c:forEach var="item" items="${hw_interfaceList}" varStatus="num" >
												<label for="one" class="hw_interfacelist" > <input 
													type="checkbox" name="multisub" id="hardwareid" value="${item[0]}"/>
													${item[1]}
												</label>
											</c:forEach>
											<input type="hidden" id="hd_hw_interface" name="hd_hw_interface" class="form-control autocomplete" autocomplete="off"></input>
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
					
					
					
<!-- network components start -->
		<div class="panel-group" id="accordionnetworkcomp5"  >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_network" 
								><b>Network Components</b></a>
						</h4>
					</div>
			<div id="collapsenetwork" class="panel-collapse collapse">
			   <div class="card-body card-block"><br>
				  <div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>No Of Ports</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="no_of_ports" path="no_of_ports" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red">* </strong>Network Features</label>
								</div>
									<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" id="NetworkshowCheckboxesid">
										<select name="network_features" id="network_features"
											class=" form-control">
											<option>Select Network Features</option>
										</select>
										<div class="overSelect" ></div>
									</div>
									<div id="checkboxes_net" class="checkboxes checkboxcls"
										>
										<div >
											<input type="text" id="network_features_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Networks">
										</div>
										<div>
											<c:forEach var="item" items="${getNetwork_featuresList}" varStatus="num" >
												<label for="one" class="network_featureslist"> <input 
													type="checkbox" name="multisub_net" id="multisub_net" value="${item[0]}"  />
													${item[1]}
												</label>
											</c:forEach>
											
											<input type="hidden" id="hd_network_features" name="hd_network_features" class="form-control autocomplete" autocomplete="off"></input>
											
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
									<label class=" form-control-label"><strong class="color_red">* </strong>Ethernet Port</label>
								</div>
								<div class="col-md-8">
									<form:select path="ethernet_port" id="ethernet_port" class="form-control" >
										<form:option value="0">--Select--</form:option>
											 <c:forEach var="item" items="${getEthernet_portList}" varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong class="color_red"></strong>Management Layer</label>
								</div>
								<div class="col-md-8">
                						<form:select path="management_layer" id="management_layer" class="form-control" >
											<form:option value="0">--Select--</form:option>
											  <c:forEach var="item" items="${getManagement_layerList}" varStatus="num">
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
					
					
					
					<div class="panel-group" id="accordion5">
						<div class="panel panel-default" id="a_div1">
							<div class="panel-heading">
								<h4 class="panel-title main_title red" id="a_div5">
									<a class="droparrow collapsed" data-toggle="collapse"
										data-parent="#accordion4" href="#" id="a_warrenty"
										><b>WARRANTY DETAILS</b></a>
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
													<div class="col-md-8" id ="warrentyshowid">
														<form:radiobutton id="Warranty1" name="warrantycheck"
															value="Yes" path="warrantycheck" checked="checked"></form:radiobutton>
														&nbsp <label for="yes" checked>Yes</label>&nbsp
														<form:radiobutton id="Warranty2" path="warrantycheck"
															name="warrantycheck" value="No"></form:radiobutton>
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
															aria-required="true" autocomplete="off"
															>

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
										data-parent="#accordion4" href="#" id="a_service"
										><b>SERVICEABLE DETAILS</b></a>
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
														<select name="s_state" id="s_state" class="form-control"
															>
															<option value="0">--Select--</option>
															<option value="1">Serviceable</option>
															<option value="2">UN-Serviceable</option>
														</select>

													</div>
												</div>
											</div>

											<div class="col-md-6" id="serviceable_state">
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
										
											 <!-- 							** AHM BISAG ** -->
							
							<div class="col-md-12">
							<div class="col-md-6 display_none"  id="un_show1">
								<div class="row form-group">
									<div class="col-md-4" >
										<label class=" form-control-label"><strong class="color_red">* </strong>UN-Servicable From Date</label>
									</div>
									<div class="col-md-8">
<%-- 								<label id="lblwarranty">${AppMainAssetsCmd.warranty}</label> --%>
								<input type="text" name="unsv_from_dt1" id="unsv_from_dt1" maxlength="10"   class="form-control width88 display_inline rgb0" 
							
							 aria-required="true" autocomplete="off" >
									
								</div>
									</div>
								</div>								
								<div class="col-md-6 display_none"  id="un_show2">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong class="color_red">* </strong>UN-Servicable To Date</label>
										</div>
											<div class="col-md-8">
<%--  								<label id="lblwarranty">${AppMainAssetsCmd.warranty}</label>  --%>
 								<input type="text" name="unsv_to_dt1" id="unsv_to_dt1" maxlength="10"   class="form-control width93 display_inline rgb0" 
 							 aria-required="true" autocomplete="off" >
									
 								</div> 
 									</div>
 								</div>
							</div>	
							
							<div class="col-md-12">
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
							
							
							
							
									
							<!-- 							** END AHM BISAG ** -->
										
									</div>




								</div>
							</div>
						</div>



					</div>

				

			</div>
			<div class="card-footer" align="center" class="form-control">
				<a href="Search_App_PeripheralUrl" class="btn btn-success btn-sm">Back</a>
				<input type="submit" class="btn btn-primary btn-sm" id="updateid" value="Update"
					>

			</div>
		</div>
	</div>
	

</form:form>
<script nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function () {
	document.getElementById('updateid').onclick = 
		function() {
		return Validate();
  	};
  	document.getElementById('assets_type').onchange = 
		function() {
		return fn_Peripheral(),fn_makeName(),fn_hide_show();
  	};
  	
  	document.getElementById('unsv_from_dt1').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('unsv_from_dt1').onfocus = 
		function() {
		return this.style.color='#000000';
  	};document.getElementById('unsv_from_dt1').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};document.getElementById('unsv_from_dt1').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('unsv_from_dt1').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
  	document.getElementById('unsv_to_dt1').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('unsv_to_dt1').onfocus = 
		function() {
		return this.style.color='#000000';
  	};document.getElementById('unsv_to_dt1').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};document.getElementById('unsv_to_dt1').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('unsv_to_dt1').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
  	
  	document.getElementById('warranty').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('warranty').onfocus = 
		function() {
		return this.style.color='#000000';
  	};document.getElementById('warranty').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};document.getElementById('warranty').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};document.getElementById('warranty').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
	document.getElementById('type_of_hw').onchange = 
		function() {
		return fn_type();
  	};
	document.getElementById('make_name').onchange = 
		function() {
		return fn_modelName();
  	};
  	document.getElementById('a_peripheral').onclick = 
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
  	document.getElementById('a_printer').onclick = 
		function() {
		return divN(this);
  	};
	document.getElementById('a_ups').onclick = 
		function() {
		return divN(this);
  	};
  	document.getElementById('a_projection').onclick = 
		function() {
		return divN(this);
  	};
 	document.getElementById('checkboxid').onclick = 
		function() {
		return showCheckboxes();
  	};
	document.getElementById('hardwareid').onclick = 
		function() {
		return hardware();
  	};
	document.getElementById('a_network').onclick = 
		function() {
		return divN(this);
  	};
  	document.getElementById('no_of_ports').onkeypress = 
		function() {
		return isNumber(event);
  	};
	document.getElementById('NetworkshowCheckboxesid').onclick = 
		function() {
		return NetworkshowCheckboxes();
  	};
  	document.getElementById('multisub_net').onclick = 
		function() {
		return network();
  	};
	document.getElementById('a_service').onclick = 
		function() {
		return divN(this);
  	};
  	
	document.getElementById('s_state').onchange = 
		function() {
		return servicablechange(this.value),fn_Servicable(this);
  	};
  	
	document.getElementById('warrentyshowid').onclick = 
		function() {
		return warrenty_show();
  	};
  	document.getElementById('a_warrenty').onclick = 
		function() {
		return divN(this);
  	};
  	

});




	$(document).ready(function() {
		

		// ** BISAG **//
		 datepicketDate('unsv_from_dt1');
		 datepicketDate('unsv_to_dt1');


		 // ** END BISAG **//
		
		 
		$("#id").val('${app_id}')
		 $("#assets_type").val('${AppPeripheralCmd.assets_type}')
		 var asset_type = $("#assets_type option:selected").text();
	 $.ajaxSetup({
		async: false
	});
	 fn_hide_show();
	 fn_makeName();
	
	 $("#make_name").val('${AppPeripheralCmd.make_name}')
		var make_name = $("#make_name option:selected").text();
		fn_modelName();
		 $("#model_name").val('${AppPeripheralCmd.model_name}')
		var model_name = $("#model_name option:selected").text();
		 

						$("#b_code").val('${AppPeripheralCmd.b_code}');
						$("#type_of_hw").val('${AppPeripheralCmd.type_of_hw}');
						$("#assets_type").val('${AppPeripheralCmd.assets_type}');
						var asset_type = $("select#assets_type option:selected")
								.text();
						if (asset_type == 'UPS') {

							$("#").show();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
							$("#mf").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#no").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#display-type").hide();
							$("#display").hide();
							$("#visual").hide();

						} else if (asset_type == "PRINTERS") {

							$("#accordionprinter5").show();
							$("#max").show();
							$("#con").hide();
							$("#mf").hide();
							$("#up").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#no").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#display-type").hide();
							$("#display").hide();
							$("#visual").hide();

						} else if (asset_type == 'MFD') {

							$("#mf").show();
							$("#up").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#no").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#display-type").hide();
							$("#display").hide();
							$("#visual").hide();

						}

						else if (asset_type == 'PROJECTION SYS') {

							$("#cap").show();
							$("#hard").show();
							$("#pro").show();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
							$("#no").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#display-type").hide();
							$("#display").hide();
							$("#visual").hide();
						} else if (asset_type == 'VISUALIZER') {
							$("#visual").show();
							$("#dis").show();
							$("#res").show();
							$("#con").show();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#max").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#no").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#display-type").hide();
							$("#display").hide();
						} else if (asset_type == 'NETWORK COMPONENTS') {
							$("#net").show();
							$("#ne").show();
							$("#no").show();
							$("#vis").hide();
							$("#res").hide();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#display-type").hide();
							$("#display").hide();
							$("#visual").hide();

						} else if (asset_type == 'MONITOR') {
							$("#display-type").show();
							$("#display").show();
							$("#net").hide();
							$("#no").hide();
							$("#vis").show();
							$("#res").hide();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
							$("#cap").hide();
							$("#hard").hide();
							$("#ne").hide();
							$("#net").hide();
							$("#visual").hide();

						}
						var make_name = $("#make_name option:selected").text();
						var model_name = $("#model_name option:selected").text();
						var b_code = $("#b_code option:selected").text();
						$("#id").val('${app_id}')
						var type_of_hw = $("#type_of_hw option:selected").text();
						var type = $("#type option:selected").text();
						var ups_capacity = $("#ups_capacity option:selected").text();
						var paper_size = $("#paper_size option:selected").text();
						var display_connector = $("#display_connector option:selected").text();
						var hardware_interface = $("#hw_interface option:selected").text();
						var ethernet_port = $("#ethernet_port option:selected").text();
						var management_layer = $("#management_layer option:selected").text();
						var v_display_connector = $("#v_display_connector option:selected").text();
						
						$("input[name='is_networked1'][value='${AppPeripheralCmd.is_networked}']").prop("checked",true);
						fn_isNetwork(1);
						$("input#ip_address1").val('${AppPeripheralCmd.ip_address}');
						$("select#ups_capacity").val('${AppPeripheralCmd.ups_capacity}');
						$("select#connectivity_type1").val('${AppPeripheralCmd.connectivity_type}');
						$("select#paper_size1").val('${AppPeripheralCmd.paper_size}');
						
						$("select#hw_interface2").val('${AppPeripheralCmd.hw_interface}');
						$("select#ethernet_port").val('${AppPeripheralCmd.ethernet_port}');
						$("select#management_layer").val('${AppPeripheralCmd.management_layer}');
						$("#lblsection").text( $("#section option:selected").text());
						//$("#maintaince_agencylbl").text($("#maintainAgency option:selected").text());
						
						datepicketDate('warranty');
						$( "#warranty" ).datepicker( "option", "maxDate", null);
						$("#lblAsset_type").text(asset_type);
						$("#lblb_code").text(b_code);
						$("#lbltype_of_hw").text(type_of_hw);
						$("#lblmake_name").text(make_name);
						$("#lblmodel_name").text(model_name);
						$("#lbltype").text(type);
						$("#lblups_capacity").text(ups_capacity);
						$("#lblpaper_size").text(paper_size);
						$("#lbldisplay_connector").text(display_connector);
						$("#lblhwinterface").text(hardware_interface);
						$("#lblb_ethernate_port").text(ethernet_port);
						$("#lblb_management_layer").text(management_layer);
						$("#lblv_display_connector").text(v_display_connector);
						$("#lbls_state").text(s_state);
						var scan = $('input[name=scan]').val();
						$("#lblscan").text(scan);
						var print = $('input[name=print]').val();
						$("#lblprint").text(print);
						var colour = $('input[name=colour]').val();
						$("#lblcolour").text(colour);
						var photography = $('input[name=photography]').val();
						$("#lblphotography").text(photography);
						var print = $('input[name=print]').val();
						$("#lblprint").text(print);
						var print = $('input[name=print]').val();
						$("#lblprint").text(print);
						$("#s_state").val('${service_state1}');
						$("#unserviceable_state").val('${unservice_state1}');
						servicablechange();
						$("#warranty").val(ParseDateColumncommission('${warranty1}'));
						// ** AHM BISAG **//
						$("#unsv_from_dt1").val(ParseDateColumncommission('${unsv_from_dt1}'))
				 		$("#unsv_to_dt1").val(ParseDateColumncommission('${unsv_to_dt1}'))
				 		
						// ** END AHM BISAG **//
						
						
						var lblb_proc_dt = '${AppPeripheralCmd.proc_date}';
						proc_dt = lblb_proc_dt.substring(0, 10);
						$("#lblb_proc_date").text(ParseDateColumncommission(proc_dt));
						
						
						$("input[name='warrantycheck'][value='${ch_list[0].warrantycheck}']").prop("checked", true);
						
						$("#c_id").val('${ch_id1}');
						
						var det = $("input[name='warrantycheck'][value='${ch_list[0].warrantycheck}']");
						if ('${ch_list[0].warranty}' != '') {
							$("#WarrantyDiv").show();
						} else {
							$('#WarrantyDiv').hide();
							$('#warranty').val('DD/MM/YYYY');
						}
						
						if (parseInt('${ch_list_size}') > 0) {
							
							
							if('${ch_list[0].status}'==1){
					 		 	
					 	 		$("#c_id").val('0');
					 	 		}
							

				
							var sss = $("#s_state").val('${ch_list[0].service_state}');
							servicablechange();
							$("#unserviceable_state").val('${ch_list[0].unservice_state}');
							$("#warranty").val(ParseDateColumncommission('${ch_list[0].warranty}'));
							// ** AHM BISAG **//
							$("#unsv_from_dt1").val(ParseDateColumncommission('${ch_list[0].unsv_from_dt}'));
							 fn_Servicable(1);
					 	  		$("#unsv_to_dt1").val(ParseDateColumncommission('${ch_list[0].unsv_to_dt}'));
					 	  		
					 	  		$("#maintainAgency").val('${ch_list[0].maintainagency}');
					 	  		
					 	  	// ** END AHM BISAG **//
							
						} else {
							$("#c_id").val('0');

						}
						
						var hardware = '${AppPeripheralCmd.hw_interface}';
						$("#hd_hw_interface").val(hardware);
						var subjectslist = hardware.split(',');
									for(k = 0; k < subjectslist.length; k++) {
										$("input[type=checkbox][name='multisub'][value='" + subjectslist[k] + "']").prop("checked", true);
									}
									
									
									var network = '${AppPeripheralCmd.network_features}';
									$("#hd_network_features").val(network);
									
									var networklist = network.split(',');
												for(kn = 0; kn < networklist.length; kn++) {
													$("input[type=checkbox][name='multisub_net'][value='" + networklist[kn] + "']").prop("checked", true);
												}

					});
	
	
	
	//** BISAG **//

	function Validate(){
		
// 		if($("#s_state").val()==1){
			
			
// 			if ($("input#unsv_to_dt1").val() == "" || $("#unsv_to_dt1").val() == "DD/MM/YYYY")
// 			{
// 				alert("Please Select UN-Serviceable To Date");
// 				$("#unsv_to_dt1").focus();
// 				return false;
// 			}
// 		}
		
		
		if($("#s_state").val()==2){
			if( $("#unserviceable_state").val()=="" || $("#unserviceable_state").val()=="0"){
				alert("Please Select UN-Serviceable State");
				$("#unserviceable_state").focus();
				return false;
			}
		}

		if($("#s_state").val()==2){
			if ($("input#unsv_from_dt1").val() == "" || $("#unsv_from_dt1").val() == "DD/MM/YYYY") {
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

	}

	//** END BISAG **//
  function fn_makeName() {
		
	
		var assets_name = $("select#assets_type").val();
		$.post("getMakeNameList?" + key + "=" + value, {
			assets_name: assets_name
		}).done(function(j) {
			var options = '';
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
		
			var options = '';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#model_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
	
	
	
	function divN(obj) {
		debugger
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
	//** AHM BISAG **//
	
	function servicablechange() {
		var s_state = $("#s_state option:selected").text();

		if (s_state == "UN-Serviceable") {
			$("#serviceable_state").show();
			$("#un_show1").show();
			$("#un_show2").hide();
			$("#maintaince_div").show();
			
		} else {
			$("#serviceable_state").hide();
			$("#un_show1").hide();
			$("#maintaince_div").hide();
		}
	}
	
	function fn_Servicable(obj) {


	if(obj.value == '1')
		{
		var p_id =$("input#id").val();
		var s_state =$("select#s_state").val();
		var unservice_state =$("select#unserviceable_state").val();
		var unsv_from_dt1 =$("input#unsv_from_dt1").val();
		
		$.post("getServicableForPeripheral?" + key + "=" + value, {
			p_id: p_id
		}).done(function(j) {
			
			if(j != "" && j != null){
			
				$("#un_show2").show();
			}
			else {
			
				$("#un_show2").hide();
			}
		
				
		});
		}
	}
	
	
	function fn_isNetwork(ind){
		
		var is_networked=$('input[name="is_networked'+ind+'"]:checked').val();
			
			if(is_networked.toUpperCase()=="YES"){
				$("#ip_addressDiv"+ind).show();
			}
			else{
				$("#ip_addressDiv"+ind).hide();
				$("#ip_address"+ind).val('');
				
			}
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
		 if(b=='19')
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
	
	
	//** END  AHM BISAG **//
</script>