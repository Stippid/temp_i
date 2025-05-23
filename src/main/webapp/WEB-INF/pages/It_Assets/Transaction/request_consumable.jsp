
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
</style>


<form:form name="RequestConsumableAssets" id="RequestConsumableAssets"
	action="RequestConsumableAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal"
	modelAttribute="RequestConsumableCmd" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 id="save">REQUEST CONSUMABLE</h5>
					<h5 id="update">UPDATE REQUEST CONSUMABLE</h5>
					<c:if test="${screenname == 'viewpage'}">
						<h5 id="">VIEW REQUEST CONSUMABLE</h5>
					</c:if>
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
					value="${MainAssetsCmd.u_file}" class="form-control autocomplete"
					autocomplete="off">

				<form:hidden id="id" path="id" class="form-control autocomplete"
					action="RequestConsumableAction?${_csrf.parameterName}=${_csrf.token}"
					modelAttribute="RequestConsumableCmd" autocomplete="off"></form:hidden>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>CRV No.</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="crv_no" name="crv_no"
									class="form-control autocomplete" autocomplete="off"
									value="${crv_no}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="col-md-4">
								<label class="form-control-label"><strong
									class="color_red">* </strong>ISSUE QUANTITY</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="issue_quantity2" name="issue_quantity2"
									class="form-control autocomplete" autocomplete="off"
									value="${issue_quantity}">
							</div>
						</div>
					</div>
				</div>
				<div class="card-body card-block display_none" id="blockdata">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong> Section </label>
								</div>
								<input type="hidden" id="p_id" name="p_id"
									class="form-control autocomplete" autocomplete="off"
									readonly="readonly">
								<div class="col-md-8">
									<label id="unit_sus_no_hid" class="display_none"><b>
											${roleSusNo} </b></label> <select id="section" name="section"
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
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Consumable Type </label>
								</div>
								<div class="col-md-8">
									<select id="assets_type" class="form-control"
										name="assets_type" readonly="readonly">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getConsumables}"
											varStatus="num">
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
										class="color_red">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<select id="make_name" class="form-control" readonly="readonly">
										<option value="0">--Select--</option>

									</select>
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
									<select id="model_name" name="model_name" class="form-control"
										readonly="readonly">
										<option value="0">--Select--</option>

									</select>
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
										path="warrantycheck"></form:radiobutton>
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
										aria-required="true" autocomplete="off" readonly="readonly">

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
									<input type="text" id="year_of_proc"
										class="form-control autocomplete" maxlength="4"
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
										class="color_red"> </strong>Remarks </label>
								</div>
								<div class="col-md-8">
									<textarea id="remarks" name="remarks" maxlength="250"
										class="form-control autocomplete text_transform_upper"
										autocomplete="off" readonly="readonly"></textarea>
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
										class="form-control autocomplete" autocomplete="off"
										readonly="readonly"></input>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										class="color_red"> </strong>Proc Cost (&#8377)</label>
								</div>
								<div class="col-md-8">
									<input title="Approx. Procurement Cost" type="text"
										id="proc_cost" name="pro_cost"
										class="form-control autocomplete" autocomplete="off"
										readonly="readonly"></input>
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
									<input type="text" name="proc_dt" id="proc_date" maxlength="10"
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
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Budget Head</label>
								</div>
								<div class="col-md-8">
									<select id="b_head" name="b_head" class="form-control"
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
									<label class=" form-control-label"><strong
										class="color_red">* </strong>Budget Code</label>
								</div>
								<div class="col-md-8">
									<select id="b_code" name="b_code" class="form-control"
										readonly="readonly">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="col-md-4">
								<label class="form-control-label">GeM Contact No.</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="gem_no1" name="gem_no1" maxlength="12"
									class="form-control" autocomplete="off" readonly="readonly"></input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="center" class="form-control">
				<a href="issue_consumables" id="clear_1"
					class="btn btn-success btn-sm">Back</a> <input type="submit"
					class="btn btn-primary btn-sm" value="Save" id="save_btn">
				<input type="submit" class="btn btn-primary btn-sm" value="Update"
					id="update_btn">
			</div>
		</div>
	</div>

</form:form>

<script nonce="${cspNonce}">
$(document).ready(function(){
	var initialCrvNo = $('#crv_no').val();

	 if (id != 0) {
		 fn_makeName();
		 $("#blockdata").show();
	        // If in edit mode, call getCrvData with the initial CRV No.
	        getCrvData(initialCrvNo);
	    }
	var id = $('#id').val();
	if (id == 0) {
		$("#blockdata").hide();
		$('#save').show();
		$('#update').hide();
		$('#save_btn').show();
		$('#clear_1').hide();
		$('#update_btn').hide();
		 $('#crv_no').prop('readonly', false);
	} else {
		$('#save').hide();
		$('#update').show();
		$('#clear_1').show();
		$('#save_btn').hide();
		$('#update_btn').show();
	    $('#crv_no').prop('readonly', true);
	}
	
	debugger;
	if (id != 0 && '${screenname}'=='viewpage') {
		$('#save').hide();
		$('#update').hide();
		$('#save_btn').hide();
		$('#update_btn').hide();
		 $('#issue_quantity2').prop('readonly', true);
	}
    var susNoAuto = $("#crv_no");
    susNoAuto.autocomplete({
        source: function(request, response) {
            $.ajax({
                type: 'POST',
                url: "getActiveCrvNoList?" + key + "=" + value, // Ensure key and value are defined
                data: {
                    loginname: request.term // Use request.term for the current input
                },
                success: function(data) {
                    var susval = [];
                    var length = data.length - 1;
                    var enc = data[length].substring(0, 16);
                    for (var i = 0; i < data.length; i++) {
                        susval.push(dec(enc, data[i]));
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
            $("#blockdata").show();
            var id = ui.item.value;
            getCrvData(id); // Fetch additional data based on selected CRV number
        },
        change: function(event, ui) {
            if (!ui.item) {
                alert("Please Enter Active CRV No.");
                susNoAuto.val("");
                susNoAuto.focus();
            }
        }
    });

    // Function to get CRV data
function getCrvData(id){
    	debugger;
    console.log("Fetching CRV data for ID: ", id);
    debugger;
    $.ajax({
        type: 'POST',
        url: "getActiveCrvDataList?" + key + "=" + value + "&id=" + encodeURIComponent(id), // Include id in the URL
        data: {}, // No need to send id in the data object if it's already in the URL
        success: function(data) {
            debugger;
            console.log(data);
      if (data.length > 0) {
                // Assuming data[0] is the object you want to work with
                $('#assetcount2').val(data[0].assetcount);
                $('#assets_type').val(data[0].assets_type);
                $.ajaxSetup({
            		async : false
            });
                fn_makeName();
                $('#make_name').val(data[0].make_name);
                
                fn_modelName() ;
                $('#model_name').val(data[0].model_name);
                
                
               
               // Set Budget Code
                $('#b_head').val(data[0].b_head); // Set Budget Head
                fn_B_Head();		
                
                $('#b_code').val(data[0].b_code); 
                $('#gem_no1').val(data[0].gem_no);
                $('#p_id').val(data[0].id);
                
               
                $('#proc_cost').val(data[0].proc_cost);
                $('#proc_date').val(new Date(data[0].proc_date).toLocaleDateString());
                $('#remarks').val(data[0].remarks);
                $('#section').val(data[0].section);
                $('#warranty').val(new Date(data[0].warranty).toLocaleDateString());
                $('#year_of_proc').val(data[0].year_of_proc);
                if (data[0].warrantycheck === "Yes") {
                    $('#Warranty1').prop('checked', true); // Check the "Yes" radio button
                } else {
                    $('#Warranty2').prop('checked', true); // Check the "No" radio button
                }
            }
            // Handle the response data as needed
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error: ", status, error);
        }
    });
}
});
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('save_btn').onclick = function() {
			return true;
			}; 
			document.getElementById('make_name').onchange = 
				function() {
				return fn_modelName(),fn_brand_other();
		  	};
		  	document.getElementById('assets_type').onchange = function() {

				return  fn_makeName();
		  	}
		  	
			document.getElementById('issue_quantity2').onkeypress = function() {
				return isNumber(event);
			};
	});
	
	function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
		}
		return true;
		}
	
	function Validate() {
		if ( $("#issue_quantity2").val() == "") {
			alert("Please Enter Issue quantity ");
			$("#issue_quantity2").focus();
			return false;
		}
	    var issueQuantity = parseFloat(document.getElementById('issue_quantity2').value);
        var totalQuantity = parseFloat(document.getElementById('assetcount2').value);
        if(issueQuantity > totalQuantity){
        	alert("Issue Quantity cannot be greater than Total Quantity.");
        	return false;
        }
		
		
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
	
	
	
	
	function fn_B_Head() {
		debugger;
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
</script>




