<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>


<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">


<style>
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


/* textarea {
    text-transform: none!important;
}
 */

</style>

<form:form name="PeripheralAssets" id="PeripheralAssets" action="PeripheralAction1" method="post" class="form-horizontal" modelAttribute="PeripheralCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>ADD PERIPHERAL(ASSETS) HARDWARE DETAILS</h5>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Peripheral Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="assets_type" id="assets_type" onchange="fn_Peripheral();fn_makeName();fn_hide_show();"  class="form-control" readonly="true">
									<form:option value="0" >--Select--</form:option>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name" class="form-control" onchange="fn_modelName();" readonly="true">
									<option value="0" >--Select--</option>
									
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
									<option value="0" >--Select--</option>
										
									</form:select>
								</div>
							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Warranty Upto</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:input type="date" id="warranty" path="warranty" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<%-- 									<form:input type="text" id="machine_no" path="machine_no" class="form-control autocomplete" autocomplete="off"></form:input> --%>
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
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
									<input type="text" name="warrantydt" id="warranty" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Type of Peripheral HW</label>
								</div>
								<div class="col-md-8">
									<form:select path="type_of_hw" id="type_of_hw"   onchange="fn_type();" class="form-control">
									<option value="0" >--Select--</option>
										<c:forEach var="item" items="${hardwareListDDL}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Model Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="type" id="type" class="form-control">
									<option value="0" >--Select--</option>
<%-- 										<c:forEach var="item" items="${Type1}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
									</form:select>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Year of Proc </label>
								</div>
								<div class="col-md-8">
<%-- 									<form:select path="year_of_proc" id="year_of_proc" class="form-control"> --%>
<%-- 									<form:option value="0" >--Select--</form:option> --%>
<%-- 										<c:forEach var="item" items="${YearOfProc}" varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select>  --%>
								<form:input type="text" id="year_of_proc" path="year_of_proc" class="form-control autocomplete" maxlength="4" autocomplete="off" onkeypress="return isNumber(event)" onkeyup="validateYear(this);" onblur="validateYearLn(this);" ></form:input>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Year of Manufacturing </label>
								</div>
								<div class="col-md-8">
<%-- 									<form:select path="year_of_manufacturing" id="year_of_manufacturing" class="form-control"> --%>
<%-- 									<form:option value="0" >--Select--</form:option> --%>
<%-- 										<c:forEach var="item" items="${YearOfManufacturing}" varStatus="num"> --%>
<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
								<form:input type="text" id="year_of_manufacturing" path="year_of_manufacturing" class="form-control autocomplete" maxlength="4" onkeypress="return isNumber(event)" onkeyup="validateYear(this);" onblur="validateYearLn(this);" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
					</div>
										
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Remarks </label>
								</div>
								<div class="col-md-8">
									<form:textarea  id="remarks" path="remarks" class="form-control autocomplete" autocomplete="off"></form:textarea>
								</div>
							</div>
						</div>
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
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Model Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="model_no1" name="model_no1" class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
 						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="machine_no1" name="machine_no1" class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>IS Networked </label>
								</div>
								<div class="col-md-8" >
									<input type="radio"  id="is_networked1"  value="Yes"  onclick="fn_isNetwork(1)"
 										name="is_networked1"  ></input>&nbsp <label for="yes">Yes</label>&nbsp    
  									<input type="radio"  id="is_networked2" name="is_networked1"  value="No"  onclick="fn_isNetwork(1)"
   										 checked="checked"></input>&nbsp   
									<label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="ip_addressDiv1" style="display: none">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>IP Address(For Networked Device)</label>
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
			
<!-- network components start -->
		<div class="panel-group" id="accordionnetworkcomp5" style="display:none;" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_network" onclick="divN(this)"
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>No Of Ports</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="no_of_ports" path="no_of_ports" class="form-control autocomplete" onkeypress="return isNumber(event)" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Network Features</label>
								</div>
									<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="NetworkshowCheckboxes()">
										<select name="network_features" id="network_features"
											class=" form-control">
											<option>Select Network Features</option>
										</select>
										<div class="overSelect" ></div>
									</div>
									<div id="checkboxes_net" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="network_features_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Networks">
										</div>
										<div>
											<c:forEach var="item" items="${getNetwork_featuresList}" varStatus="num" >
												<label for="one" class="network_featureslist"> <input onclick="network();"
													type="checkbox" name="multisub_net" value="${item[0]}"  />
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Ethernet Port</label>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Management Layer</label>
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

<!-- visulizer start -->
		<div class="panel-group" id="accordionvisulizer5" style="display:none;" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_visulizer" onclick="divN(this)"
								><b>Visulizer</b></a>
						</h4>
					</div>
			<div id="collapsevisulizer" class="panel-collapse collapse">
			   <div class="card-body card-block"><br>
				   <div class="row">
					  <div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Resolution</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="resolution" path="resolution" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Display Size</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="v_display_size" path="v_display_size" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Display Connector</label>
								</div>
								 <div class="col-md-8">
									  <form:select path="v_display_connector" id="v_display_connector" class="form-control" >
									  	<form:option value="0">--Select--</form:option>
										 <c:forEach var="item" items="${getDisplay_ConnectorList}" varStatus="num">
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

		<div class="panel-group" id="accordionprojectionsys5" style="display:none;" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
								data-parent="#accordion4" href="#" id="a_projection" onclick="divN(this)"
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Capacity(Lumens)</label>
								</div>
								<div class="col-md-8">
									<form:input type="text" id="capacity" path="capacity" class="form-control autocomplete" autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Hardware Interface</label>
								</div>
										<div class="col-md-8">
								<div class="multiselect">

									<div class="selectBox" onclick="showCheckboxes()">
										<select name="hw_interface" id="hw_interface"
											class=" form-control">
											<option>Select Hardware Interface</option>
										</select>
										<div class="overSelect"></div>
									</div>
									<div id="checkboxes" class="checkboxes"
										style="max-height: 200px; overflow: auto;">
										<div style="">
											<input type="text" id="hw_interface_search"
												class="form-control autocomplete" autocomplete="off"
												placeholder="Search Hardware">
										</div>
										<div>
											<c:forEach var="item" items="${hw_interfaceList}" varStatus="num" >
												<label for="one" class="hw_interfacelist" > <input onclick="hardware();"
													type="checkbox" name="multisub" value="${item[0]}"/>
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


<!-- MFD START -->
	<div class="panel-group" id="accordionmfd5" style="display:none;" >
				<div class="panel panel-default" id="a_div1">
					<div class="panel-heading">
						<h4 class="panel-title main_title red" id="a_div5">
							<a class="droparrow collapsed" data-toggle="collapse"
							data-parent="#accordion4" href="#" id="a_mfd" onclick="divN(this)"
							><b>MFD</b></a>
						</h4>
					</div>
				<div id="collapsemfd" class="panel-collapse collapse">
	                <div class="card-body card-block"><br>
					  <div class="row">
						<div class="col-md-12">
							<div class="col-md-2">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Features Avlb with MFD</label>
								</div>
							<div class="1"><b><label>Print</label></b></div>
								<div class="col-md-2">
								 <form:radiobutton  id="print"  value="Yes"
 										path="print"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="print" path="print"  value="No"
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
							</div>
							<div class="1"><b><label>Scan</label></b></div>
								<div class="col-md-2">
								 <form:radiobutton  id="scan"  value="Yes"
 										path="scan"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="scan" path="scan"  value="No"
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
							</div>

							<div class="1"><b><label>Photography</label></b></div>
								<div class="col-md-2">
								 <form:radiobutton  id="photography"  value="Yes"
 										path="photography"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="photography" path="photography"  value="No"
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
								</div>
								
							<div class="1"><b><label>Colour</label></b></div>
								<div class="col-md-2">
								 <form:radiobutton  id="colour"  value="Yes"
 										path="colour"  ></form:radiobutton>&nbsp <label for="yes">Yes</label>&nbsp   
  									<form:radiobutton  id="colour" path="colour"  value="No"
   										 checked="checked"></form:radiobutton>&nbsp  
									<label for="no">No</label>
								</div>
							</div>	              		 					  
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- END MFD -->

<!-- PRINTER START -->

	<div class="panel-group" id="accordionprinter5" style="display:none;" >
		<div class="panel panel-default" id="a_div1">
			<div class="panel-heading">
				<h4 class="panel-title main_title red" id="a_div5">
						<a class="droparrow collapsed" data-toggle="collapse"
						data-parent="#accordion4" href="#" id="a_printer" onclick="divN(this)"
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
									<label class=" form-control-label"><strong style="color: red;">*</strong>Monochrome/Color</label>
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
									<label class=" form-control-label"><strong style="color: red;">* </strong>Max Paper Size</label>
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
								<label class=" form-control-label"><strong style="color: red;">* </strong>Connectivity Type</label>
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
	<div class="panel-group" id="accordionups5" style="display:none;" >
		<div class="panel panel-default" id="a_div1">
			<div class="panel-heading">
				<h4 class="panel-title main_title red" id="a_div5">
					<a class="droparrow collapsed" data-toggle="collapse"
					data-parent="#accordion4" href="#" id="a_ups" onclick="divN(this)"
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
								<label class=" form-control-label"><strong style="color: red;">* </strong>UPS Capacity </label>
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

<!-- MONITER START -->

	<div class="panel-group" id="accordionmoniter5" style="display:none;" >
		<div class="panel panel-default" id="a_div1">
			<div class="panel-heading">
				<h4 class="panel-title main_title red" id="a_div5">
					<a class="droparrow collapsed" data-toggle="collapse"
					data-parent="#accordion4" href="#" id="a_moniter" onclick="divN(this)"
					><b>MONITER</b></a>
				</h4>
			</div>
				<div id="collapsemoniter" class="panel-collapse collapse">
		           <div class="card-body card-block"><br>
					<div class="row">
					  <div class="col-md-12">					
						<div class="col-md-6">
						  <div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Display Type</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" id="display_type" path="display_type" class="form-control autocomplete" autocomplete="off"></form:input>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">					
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Display Size</label>
							</div>
							<div class="col-md-8">
								<form:input type="text" id="display_size" path="display_size" class="form-control autocomplete" autocomplete="off"></form:input>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong style="color: red;">* </strong>Display Connector</label>
							</div>
							  <div class="col-md-8">
								  <form:select path="display_connector" id="display_connector" class="form-control" >
								  	<form:option value="0">--Select--</form:option>
									 <c:forEach var="item" items="${getDisplay_ConnectorList}" varStatus="num">
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
									<form:input type="text" id="b_cost" path="b_cost"  onkeypress="return isNumber(event)" class="form-control autocomplete" autocomplete="off" readonly="true"></form:input>
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
</div>
		<div class="card-footer" align="center" class="form-control">
			<a href="Search_PeripheralHardwareUrl" class="btn btn-success btn-sm">Back</a>
			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();"> 					
		</div>
	</div>
  </div>
	
</form:form>

<script> 
$(document).ready(function() {
	
	$.ajaxSetup({
		 async : false
});
	
	
	datepicketDate('warranty');
	datepicketDate('proc_date');
	
	if('${PeripheralCmd.id}' != "0"){
		$("#countDiv").hide();
		$("#model_no1").val('${PeripheralCmd.model_no}');
		$("#machine_no1").val('${PeripheralCmd.machine_no}');
	}
	
	$("#warranty").val((ParseDateColumncommission('${PeripheralCmd.warranty}')));	

// 	$("#warranty").val(('${PeripheralCmd.warranty}').substring(0, 10));	
	$("input[name='warrantycheck'][value='${PeripheralCmd.warrantycheck}']").prop("checked",true);
	$("#proc_date").val((ParseDateColumncommission('${PeripheralCmd.proc_date}')));	
// 	$("#proc_date").val(('${PeripheralCmd.proc_date}').substring(0, 10));
	fn_Peripheral();fn_makeName();fn_hide_show();
		$("select#make_name").val("${PeripheralCmd.make_name}");
		fn_modelName();
// 		fn_Peripheral();		
		$("select#model_name").val('${PeripheralCmd.model_name}');
		$("select#type_of_hw").val('${PeripheralCmd.type_of_hw}');
		fn_type();
		if ('${PeripheralCmd.type}' == "") {
			$('select#type').val("0");
		}else {
			$('select#type').val("${PeripheralCmd.type}");
		}
		
		
		$("input[name='is_networked1'][value='${PeripheralCmd.is_networked}']").prop("checked",true);
// 		anti_show();
		$("input[name='print'][value='${PeripheralCmd.print}']").prop("checked",true);
		$("input[name='scan'][value='${PeripheralCmd.scan}']").prop("checked",true);
		$("input[name='photography'][value='${PeripheralCmd.photography}']").prop("checked",true);
		$("input[name='colour'][value='${PeripheralCmd.colour}']").prop("checked",true);
		$("input[name='monochrome_color'][value='${PeripheralCmd.monochrome_color}']").prop("checked",true);
		
		serviceStChange();
		fn_isNetwork(1);
		$("#ip_address1").val('${PeripheralCmd.ip_address}');
		fn_B_Head();
		$("select#b_code").val('${PeripheralCmd.b_code}');	
// 		$('select#year_of_manufacturing').val('${PeripheralCmd.year_of_manufacturing}');
// 		fn_makeName();
// 		$('select#make_name').val('${PeripheralCmd.make_name}');
// 		fn_modelName();
// 		$('select#model_name').val('${PeripheralCmd.model_name}');				
// 		$('select#type').val('${PeripheralCmd.type}');	
// 		$('select#ups_capacity').val('${PeripheralCmd.ups_capacity}');
// 		$('select#paper_size').val('${PeripheralCmd.paper_size}');
// 		$('select#display_connector').val('${PeripheralCmd.display_connector}');
// 		$('select#v_display_connector').val('${PeripheralCmd.v_display_connector}');
// 		$('select#s_state').val('${PeripheralCmd.s_state}');
// 		serviceStChange();
// 		$('select#unserviceable_state').val('${PeripheralCmd.unserviceable_state}');	
// 		datepicketYear('year_of_proc1');
// 		datepicketYear('year_of_manufacturing1');

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
	
});	
</script>


<script>
function Validate(){
	
	if($("#assets_type").val()==0 || $("#assets_type").val()==""){
		alert("Please Select Peripheral Type  ");
		$("#assets_type").focus();
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
	
	
// 	if( $("#machine_no").val()==""){
// 		alert("Please Enter Machine No. ");
// 		$("#machine_no").focus();
// 		return false;
// 	}

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
	
	if($("#type_of_hw").val()==0 || $("#type_of_hw").val()==""){
		alert("Please Select Type of Peripheral HW ");
		$("#type_of_hw").focus();
		return false;
	}
	if($("#type").val()==0 || $("#type").val()==""){
		alert("Please Select Model Type ");
		$("#type").focus();
		return false;
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
		if( $("#model_no"+i).val()==""){
			alert("Please Enter Model Number ");
			$("#model_no"+i).focus();
			return false;
		}
		
		if( $("#machine_no"+i).val()==""){
			alert("Please Enter Machine No. ");
			$("#machine_no"+i).focus();
			return false;
		}
		

		
		if (!$("input[name='is_networked"+i+"']").is(':checked')) {
			   alert('Please Check IS Networked ');
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
		
	 else if(b=='15')
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
		 if( $("#network_features").val()=="" || $("#network_features").val()=="0" ){
			 	alert("Please Select Network Features")  
				$("#network_features").focus();
				return false;
		}
		 if( $("#ethernet_port").val()=="" || $("#ethernet_port").val()=="0" ){
			 	alert("Please Select Ethernet Port")  
				$("#ethernet_port").focus();
				return false;
		}
		 if( $("#management_layer").val()=="" || $("#management_layer").val()=="0" ){
			 	alert("Please Select Management Layer")  
				$("#management_layer").focus();
				return false;
		}
		 
		}
	 
	
	else if (b == '19') {
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
			if ($("#unserviceable_state").val() == ""
					|| $("#unserviceable_state").val() == "0") {
				alert("Please Select UN-Serviceable State");
				$("#unserviceable_state").focus();
				return false;
			}
		}
// 		if ($("#b_cost").val() == "") {
// 			alert("Please Enter Proc Cost");
// 			$("#b_cost").focus();
// 			return false;
// 		}

// 		if ($("#proc_date").val() == ""
// 				|| $("#proc_date").val().toUpperCase() == "DD/MM/YYYY") {
// 			alert("Please Enter Proc Date");
// 			$("#proc_date").focus();
// 			return false;
// 		}

		if ($("#b_head").val() == "" || $("#b_head").val() == "0") {
			alert("Please Select Budget Head");
			$("#b_head").focus();
			return false;
		}

		if ($("#b_code").val() == "" || $("#b_code").val() == "0") {
			alert("Please Select Budget Code");
			$("#b_code").focus();
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
		 }
	 else
		 {
		  $("#un_show").hide();
		  $("select#unserviceable_state").val('0');
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
				options += '<div class="row" style="border: solid #b7b6b0 5px; padding: 10px;    margin: 5px;">'+
						'<div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
							'<label class=" form-control-label"><strong style="color: red;">* </strong>Model Number</label>	</div>'+
						'<div class="col-md-8">'+
						'<input type="text" id="model_no'+i+'" name="model_no'+i+'" class="form-control autocomplete" autocomplete="off"></input>'+
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" id="machine_no'+i+'" name="machine_no'+i+'" class="form-control autocomplete" autocomplete="off"></input>'+
						'</div></div></div></div><div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'			<label class=" form-control-label"><strong style="color: red;">* </strong>IS Networked </label>'+
						'		</div><div class="col-md-8" >'+
						'			<input type="radio"  id="is_networked"  value="Yes"  onclick="fn_isNetwork('+i+')"'+
 						'				name="is_networked'+i+'"  ></input>&nbsp <label for="yes">Yes</label>&nbsp    '+
  						'			<input type="radio"  id="is_networked2" name="is_networked'+i+'"  value="No"  onclick="fn_isNetwork('+i+')"'+
   						'				 checked="checked"></input>&nbsp <label for="no">No</label>	</div></div></div>'+
						'<div class="col-md-6" id="ip_addressDiv'+i+'" style="display: none"><div class="row form-group"><div class="col-md-4">'+
						'			<label class=" form-control-label"><strong style="color: red;">* </strong>IP Address(For Networked Device)</label>'+
						'		</div><div class="col-md-8">'+
						'			<input type="text" id="ip_address'+i+'" name="ip_address'+i+'" maxlength="15" class="form-control autocomplete" autocomplete="off"></input>'+
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
	if(b=='15')
		{
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
	
	
 </script>
 
 
 
 