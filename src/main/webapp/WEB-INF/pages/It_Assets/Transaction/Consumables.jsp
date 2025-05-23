
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
/* .div_count{ */

/* border: solid #b7b6b0 5px;padding: 10px;margin: 5px; */
/* } */
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
 .mb {
	margin-bottom: 0;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}
</style>

<form:form name="ConsumableAssets" id="ConsumableAssets"
	action="ConsumableAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal" modelAttribute="ConsumablesCMD"
	enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 id="save">ADD CONSUMABLE ASSETS</h5>
					<h5 id="update">UPDATE/EDIT CONSUMABLE ASSETS</h5>
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
					value="${ConsumablesCMD.u_file}" class="form-control autocomplete"
					autocomplete="off">
					<input
					type="hidden" id="screenurl" name="screenurl" value="${screenurl}"
					class="form-control autocomplete" autocomplete="off">
					<input type="hidden" id="supplierId" name="supplierId">

				<form:hidden id="id" path="id" class="form-control autocomplete"
					action="ConsumableAction?${_csrf.parameterName}=${_csrf.token}"
					modelAttribute="ConsumableCmd" autocomplete="off"></form:hidden>
				<div class="card-body card-block">
					<div class="col-md-12">

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
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Consumable Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="assets_type" id="assets_type"
										class="form-control" name="assets_type">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${getConsumables}"
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
										<option value="0">--Select--</option>

									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
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
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="model_name" id="model_name"
										name="model_name" class="form-control">
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

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Warranty</label>
								</div>
								<div class="col-md-8" id="warrentyshowid">
									<form:radiobutton id="Warranty1" value="Yes"
										path="warrantycheck"  checked="checked"></form:radiobutton>
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
					<div class="col-md-12">
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
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"> </strong>Remarks </label>
								</div>
								<div class="col-md-8">
									<form:textarea id="remarks" name="remarks" path="remarks" maxlength="250"
										class="form-control autocomplete text_transform_upper"
										autocomplete="off"></form:textarea>
								</div>
							</div>
						</div>
						<div class="col-md-6 " id="countDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Total Quantity</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="assetcount2" name="assetcount2"
										class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
					</div>

					<div class="" id="multiAssetDiv"></div>
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
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red"> </strong>Proc Cost (&#8377)</label>
												</div>
												<div class="col-md-8">
													<form:input title="Approx. Procurement Cost" type="text"
														id="proc_cost" name="pro_cost" path="proc_cost"
														class="form-control autocomplete" autocomplete="off"></form:input>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">*</strong>Proc Date</label>
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
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Budget Head</label>
												</div>
												<div class="col-md-8">
													<form:select path="b_head" id="b_head" name="b_head"
														class="form-control">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${getBudgetHeadList}"
															varStatus="num">
															<form:option value="${item[1]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														class="color_red">* </strong>Budget Code</label>
												</div>
												<div class="col-md-8">
													<form:select path="b_code" id="b_code" name="b_code"
														class="form-control">
														<form:option value="0">--Select--</form:option>
													</form:select>
												</div>
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="col-md-4">
												<label class="form-control-label"><strong
													class="color_red">* </strong>CRV No.</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="crv_no" name="crv_no"
													class="form-control autocomplete" autocomplete="off">
											</div>
										</div>
										<div class="col-md-6">
											<div class="col-md-4">
												<label class="form-control-label">GeM Contact No.</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="gem_no1" name="gem_no1"
													maxlength="12" class="form-control" autocomplete="off"></input>
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
				<a href="${screenurl}" id="clear_1"
					class="btn btn-success btn-sm">Back</a> <input type="submit"
					class="btn btn-primary btn-sm" value="Save" id="save_btn">
				<input type="submit" class="btn btn-primary btn-sm" value="Update"
					id="update_btn">
			</div>
		</div>
	</div>
</form:form>
<script nonce="${cspNonce}">
var parentContainer = document.getElementById('multiAssetDiv'); 

//Event delegation for radio buttons
parentContainer.addEventListener('change', function(event) {
 if (event.target.matches('input[type="radio"][name^="is_networked"]')) {
     var index = event.target.name.replace('is_networked', ''); // Extract index from name
     fn_isNetwork(index); // Call your function with the index
 }
});

	document
			.addEventListener(
					'DOMContentLoaded',
					function() {

						document.getElementById('save_btn').onclick = function() {
						return warrenty_show();
						}; 
						
						document.getElementById('a_warrenty').onclick = function() {
							return divN(this);
						};
						document.getElementById('proc_cost').onkeypress = function() {
							return isNumber(event);
						};

						document.getElementById('a_budget').onclick = function() {
							return divN(this);
						};
						document.getElementById('model_name').onchange = function() {
							return fn_make_other();
						};
						
						document.getElementById('year_of_proc').onkeypress = function() {
							return isNumber(event);
						};
						document.getElementById('year_of_proc').onkeyup = function() {
							return validateYear(this);
						};
						document.getElementById('year_of_proc').onblur = function() {
							return validateYearLn(this);
						};

						document.getElementById('brand_other').oninput = function() {
							return this.value = this.value.toUpperCase();
						};
						document.getElementById('brand_other').onkeypress = function() {
							return onlyAlphabets(event);
						};
						document.getElementById('brand_other').onchange = function() {
							return searchMakeOther();
						};

						document.getElementById('make_name').onchange = function() {
							return fn_modelName(), fn_brand_other();
						};
						document.getElementById('assets_type').onchange = function() {

							return fn_Peripheral(), fn_makeName(),
									fn_hide_show();
						};
						document.getElementById('b_head').onchange = function() {
							return fn_B_Head();
						};

						document.getElementById('proc_date').onclick = function() {
							return clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('proc_date').onfocus = function() {
							return this.style.color = '#000000';
						};
						document.getElementById('proc_date').onblur = function() {
							return clickrecall(this, 'DD/MM/YYYY');
						};
						document.getElementById('proc_date').onkeyup = function() {
							return clickclear(this, 'DD/MM/YYYY');
						};
						document.getElementById('proc_date').onchange = function() {
							return clickrecall(this, 'DD/MM/YYYY');
						};
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
						document.getElementById('assetcount2').onkeypress = function() {
							return isNumber(event);
						};
						
						
						document.getElementById('warrentyshowid').onclick = 
							function() {
							return warrenty_show();
					  	};
						
					});

	$(document)
			.ready(
					function() {
					

						var id = $('#id').val();
						if (id == 0) {
							$('#save').show();
							$('#update').hide();
							$('#save_btn').show();
							$('#clear_1').hide();
							$('#update_btn').hide();
						} else {
							$('#save').hide();
							$('#update').show();
							$('#clear_1').show();
							$('#save_btn').hide();
							$('#update_btn').show();

						}

						$.ajaxSetup({
							async : false
						});

						$("#proc_cost").tooltip();
						$("#proc_cost").css('cursor', 'pointer');
						datepicketDate('warranty');
						datepicketDate('proc_date');

						$("#warranty").datepicker("option", "maxDate", null);

						// ** AHM BISAG **//
						datepicketDate('unsv_from_dt1')

						// ** END AHM BISAG **//
						//serviceStChange();
						if ('${ConsumablesCMD.id}' != "0") {
			
							$("#assetcount2").val('${ConsumablesCMD.assetcount}');
							$("#proc_cost").val('${ConsumablesCMD.proc_cost}');
							
							//$("#countDiv").hide();
							$("#model_no1").val('${ConsumablesCMD.model_no}');
							$("#machine_no1").val(
									'${ConsumablesCMD.machine_no}');
							$("#crv_no").val('${ConsumablesCMD.crv_no}');
							$("#gem_no1").val('${ConsumablesCMD.gem_no}');

							$("#warranty").val((ParseDateColumncommission('${ConsumablesCMD.warranty}')));

							
							$("input[name='warrantycheck'][value='${ConsumablesCMD.warrantycheck}']")
									.prop("checked", true);
							warrenty_show();
						
							
							
							$("#proc_date")
									.val(
											(ParseDateColumncommission('${ConsumablesCMD.proc_date}')));

					

							//** END BISG AHM **//

								//$("#proc_date").val(('${ConsumablesCMD.proc_date}').substring(0, 10));

							fn_Peripheral();
							fn_makeName();
							fn_hide_show();
							$("select#make_name").val(
									"${ConsumablesCMD.make_name}");

							fn_brand_other();
							$("#brand_other").val('${make_mstr_other}');

							fn_modelName();
							// 		fn_Peripheral();		
							$("select#model_name").val(
									'${ConsumablesCMD.model_name}');

							fn_make_other();
							$("#model_other").val('${model_mstr_other}');

							if ('${ConsumablesCMD.type}' == "") {
								$('select#type').val("0");
							} else {
								$('select#type').val("${ConsumablesCMD.type}");
							}


							$("#peri_hw_other").val('${type_hw_mstr}');

							fn_model_type_other();
							$("#model_type_other").val('${type_mstr}');

							fn_connectivity_type_other();
							$("#connectivity_other").val('${connect_mstr}');

							//selectBer();

							$("#make_mstr_id").val('${make_mstr_id}');
							$("#model_mstr_id").val('${model_mstr_id}');
							$("#type_hw_id").val('${type_hw_id}');
							$("#pro_type_id").val('${pro_type_id}');
							$("#pro_connect_id").val('${pro_connect_id}');
							if ('${ConsumablesCMD.section}' != "") {
								$("#section").val('${ConsumablesCMD.section}');

							}

							var sus_no = '${ConsumablesCMD.section}';

							
			
// 							fn_isNetwork(1);
							$("select#b_head").val('${ConsumablesCMD.b_head}');
							fn_B_Head();
							$("select#b_code").val('${ConsumablesCMD.b_code}');
						
// 									fn_makeName();
// 							 		$('select#make_name').val('${ConsumablesCMD.make_name}');
// 									fn_modelName();
// 									$('select#model_name').val('${ConsumablesCMD.model_name}');				
							// 		$('select#type').val('${ConsumablesCMD.type}');	


							$("select#maintainAgency").val(
									('${ConsumablesCMD.maintainagency}'));
						}

					});

	function Validate() {

		if ($("#section").val() == 0 || $("#section").val() == "") {
			alert("Please Select Section ");
			$("#section").focus();
			return false;
		}

		if ($("#assets_type").val() == 0 || $("#assets_type").val() == "") {
			alert("Please Select Consumables Type   ");
			$("#assets_type").focus();
			return false;
		}

		if ($("#make_name").val() == 0 || $("#make_name").val() == "") {
			alert("Please Select Make/Brand Name ");
			$("#make_name").focus();
			return false;
		}

		if ($("#model_name").val() == 0 || $("#model_name").val() == "") {
			alert("Please Select Model Name ");
			$("#model_name").focus();
			return false;
		}
// 		   if (!$('input[name="warrantycheck"]:checked').val()) {
//                alert("Please select a warranty option.");
//                $('#Warranty1').focus(); 
//                return false; 
//            }
// 			if ($("#warranty").val() == 0 || $("#warranty").val() == ""
// 				|| $("#warranty").val() == "DD/MM/YYYY") {
// 			alert("Please Select Warranty Upto ");
// 			$("#warranty").focus();
// 			return false;
// 		    }
			
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
			
			
			
		if ( $("#assetcount2").val() == "") {
			alert("Please Enter Total quantity ");
			$("#assetcount2").focus();
			return false;
		}
	
		if ($("#proc_date").val() == ""
			|| $("#proc_date").val() == "DD/MM/YYYY") {
		alert("Please Select Proc Date ");
		$("#proc_date").focus();
		return false;
	}

	if ($("#b_head").val() == "" || $("#b_head").val() == "0") {
		alert("Please Select Budget Head ");
		$("#b_head").focus();
		return false;
	}

	if ($("#b_code").val() == "" || $("#b_code").val() == "0") {
		alert("Please Select Budget Code ");
		$("#b_code").focus();
		return false;
	}
	if ( $("#crv_no").val() == "") {
		alert("Please Enter CRV No ");
		$("#crv_no").focus();
		return false;
	}
	
		var text = $("#type_of_hw option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			if ($("#peri_hw_other").val().trim() == "") {
				alert("Please Enter Type Of Peripheral HW Other. ");
				$("#peri_hw_other").focus();
				return false;
			}

		}


		var text = $("#make_name option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			if ($("#brand_other").val().trim() == "") {
				alert("Please Enter Make Name Other.  ");
				$("#brand_other").focus();
				return false;
			}

		}

		var text = $("#model_name option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			if ($("#model_other").val().trim() == "") {
				alert("Please Enter Model Name Other. ");
				$("#model_other").focus();
				return false;
			}

		}

	}

	function ValidateIPaddress(ipaddress) {
		if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
				.test(ipaddress)) {
			return (true)
		}

		return (false)
	}

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
	function fn_type() {
		var type_of_hw = $("select#type_of_hw").val();
		$
				.post("getTypeList?" + key + "=" + value, {
					type_of_hw : type_of_hw
				})
				.done(
						function(j) {

							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#type").html(options);
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



	///BISAG V2 240822///
	function fn_B_Head() {
		var b_head = $("select#b_head").val();

		$
				.post("getBudgetCodeList?" + key + "=" + value, {
					b_head : b_head
				})
				.done(
						function(j) {
							var options = '<option value="0">--Select--</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option value="' + j[i][0] + '" name="' + j[i][1] + '">'
										+ j[i][1] + '</option>';
							}
							$("select#b_code").html(options);
						}).fail(
						function(xhr, textStatus, errorThrown) {
							console.error("Error fetching budget codes:",
									textStatus, errorThrown);
						});

	}

	///BISAG V2 240822 END///

	function validateYear(e) {
		var year = $(e).val();

		if (year.length == 4
				&& (parseInt(year) <= 1890 || parseInt(year) >= 2099)) {
			alert("Please Enter Year In Range");
			$(e).val("");
		}
	}

	function validateYearLn(e) {
		var year = $(e).val();

		if (year.length >= 1 && year.length < 4) {
			alert("Please Enter Complete Year");
			$(e).val("");
			$(e).focus();
		}
	}

	function fn_hide_show() {
		var b = $("select#assets_type").val();
		if (b == '8') {
			$("#accordionups5").show();
		} else {
			$("#accordionups5").hide();
			$("select#ups_capacity").val('0');
			$("input#serial_no").val("");
		}
		if (b == '12') {
			$("#accordionprinter5").show();
		} else {
			$("#accordionprinter5").hide();
			$("input#monochrome_color").val("");
			$("select#paper_size").val("0");
		}
		if (b == '13') {
			$("#accordionmfd5").show();
		} else {
			$("#accordionmfd5").hide();
		}

		if (b == '14') {

			$("#accordionprojectionsys5").show();
		} else {
			$("#accordionprojectionsys5").hide();
			$("input#capacity").val("");
		}
		if (b == '15' || b == '19') {
			if (b == '19') {
				$("#visual_txt").text("Monitor");

			}
			if (b == '15') {
				$("#visual_txt").text("Visulizer");
			}
			$("#accordionvisulizer5").show();
		} else {
			$("#accordionvisulizer5").hide();
			$("input#resolution").val("");
		}
		if (b == '16') {
			$("#accordionnetworkcomp5").show();
		} else {
			$("#accordionnetworkcomp5").hide();
			$("input#no_of_ports").val("");
		}
		if (b == '2') {
			$("#accordionmoniter5").show();
		} else {
			$("#accordionmoniter5").hide();
			$("input#display_type").val("");
			$("input#display_size").val("");
			$("select#display_connector").val('0');
		}
	}

	function onlyAlphabets(e, t) {
		return (e.charCode > 64 && e.charCode < 91)
				|| (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;
	}

	function onlyAlphaNumeric(e, t) {
		return (e.charCode > 64 && e.charCode < 91)
				|| (e.charCode > 96 && e.charCode < 123)
				|| (e.charCode >= 48 && e.charCode <= 57) || e.charCode == 32;
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

	function fn_make_other() {
		var text = $("#model_name option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			$("#model_other_hid").show();
		} else {
			$("#model_other_hid").hide();
			$("#model_other").val('');
		}
	}

	function fn_model_type_other() {
		var text = $("#type option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			$("#model_type_other_hid").show();
		} else {
			$("#model_type_other_hid").hide();
			$("#model_type_other").val('');
		}
	}

	function fn_connectivity_type_other() {
		var text = $("#connectivity_type option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			$("#connect_other_hid").show();
		} else {
			$("#connect_other_hid").hide();
			$("#connectivity_other").val('');
		}
	}
	function searchTypeOfPeripheralOther() {

		var peri_hw_other = $("#peri_hw_other").val();
		$.post("getTypeOfPeripheralOtherList?" + key + "=" + value, {
			peri_hw_other : peri_hw_other
		}).done(function(j) {

			if (j.length > 0) {

				alert("This Type Of Peripheral HW Already Exists.");
				$("#peri_hw_other").val("");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function searchMakeOther() {

		var assets_type = $("#assets_type").val();
		var brand_other = $("#brand_other").val();
		$.post("getPeriBrandOtherList?" + key + "=" + value, {
			assets_type : assets_type,
			brand_other : brand_other
		}).done(function(j) {

			if (j.length > 0) {

				alert("This Make Name Already Exists.");
				$("#brand_other").val("");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function searchModelOther() {

		var assets_type = $("#assets_type").val();
		var model_other = $("#model_other").val();
		$.post("getPeriModelOtherList?" + key + "=" + value, {
			assets_type : assets_type,
			model_other : model_other
		}).done(function(j) {

			if (j.length > 0) {

				alert("This Model Name Already Exists.");
				$("#model_other").val("");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function searchTypeOfModelOther() {

		//		var asset_type = $("#assets_type").val();
		var model_type_other = $("#model_type_other").val();
		$.post("getTypeOfModelOtherList?" + key + "=" + value, {
			type_of_hw : type_of_hw,
			model_type_other : model_type_other
		}).done(function(j) {

			if (j.length > 0) {

				alert("This Model Name Already Exists.");
				$("#model_type_other").val("");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}

	function searchConnectivityTypeOther() {

		var connectivity_other = $("#connectivity_other").val();
		$.post("getTypeConnectivityOtherList?" + key + "=" + value, {
			connectivity_other : connectivity_other
		}).done(function(j) {

			if (j.length > 0) {

				alert("This Connectivity Type Already Exists.");
				$("#connectivity_other").val("");
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});

	}


	/////For File Download

	function download_file() {

		var id = $("#id").val();
		var pdfView = "BERFileDownloadDemoPeripheral?id=" + id;

		fileName = "TEST_DOC";
		fileURL = pdfView;
		if (!window.ActiveXObject) {
			var save = document.createElement('a');
			save.href = fileURL;
			save.target = '_blank';
			var filename = fileURL.substring(fileURL.lastIndexOf('/') + 1);
			save.download = fileName || filename;
			if (navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/)
					&& navigator.userAgent.search("Chrome") < 0) {
				document.location = save.href;
			} else {
				var evt = new MouseEvent('click', {
					'view' : window,
					'bubbles' : true,
					'cancelable' : false
				});
				save.dispatchEvent(evt);
				(window.URL || window.webkitURL).revokeObjectURL(save.href);
			}
		} else if (!!window.ActiveXObject && document.execCommand) {
			var _window = window.open(fileURL, '_blank');
			_window.document.close();
			_window.document.execCommand('SaveAs', true, fileName || fileURL)
			_window.close();
		}

		location.reload();
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
</script>



