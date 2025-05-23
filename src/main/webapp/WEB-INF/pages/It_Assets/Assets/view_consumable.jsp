<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" nonce="${cspNonce}"></script>
<form:form name="ViewConsumablesAssets" id="ViewConsumablesAssets"
	action="ViewConsumablesAction" method="post" class="form-horizontal"
	modelAttribute="ViewConsumablesCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>VIEW Consumable ASSETS</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<div class="card-body card-block" id="printableArea">
					<div class="col-md-12">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Section</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblsection"></label> <select
										id="section" name="section" class="form-control display_none">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}"
												<c:if test="${item.id == ViewConsumablesCmd.section}">selected</c:if>>${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
							<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Consumable
											Type</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblAsset_type"></label>
									<form:select path="assets_type" id="assets_type"
										class="form-control display_none">

										<c:forEach var="item" items="${getConsumables}"
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
									<label class=" form-control-label"><strong>
											Year of Proc</strong> </label>
								</div>
								<div class="col-md-8">

									<label id="lblyear_of_proc">${ViewConsumablesCmd.year_of_proc}</label>


								</div>
							</div>
						</div>
							<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class="form-control-label"><strong>Total
										Quantity</strong></label>
							</div>
							<div class="col-md-8">
								<label id="lbl_assetcount">${ViewConsumablesCmd.assetcount}</label>

							</div>
						</div>
					</div>
						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong>
											Make Name</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmake_name"></label>
									<form:select path="make_name" id="make_name"
										class="form-control display_none">
										<c:forEach var="item" items="${getMakeName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Model Name</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_name"></label>
									<form:select path="model_name" id="model_name"
										class="form-control display_none">
										<c:forEach var="item" items="${getModelNameList}"
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
									<label class=" form-control-label"><strong>
											Remarks </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblremarks">${ViewConsumablesCmd.remarks}</label>
								</div>
							</div>
						</div>
					</div>


					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty
									</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarrantycheck">${ViewConsumablesCmd.warrantycheck}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"
										${ViewConsumablesCmd.warranty}><strong>Warranty
											Upto </strong></label>
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
									<label class=" form-control-label"><strong>
											Proc Cost</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost" class="wordbreak">${ViewConsumablesCmd.proc_cost}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Proc Date</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_proc_date"></label> <select
										name="ethernet_port" id="ethernet_port"
										class="form-control display_none">

										<c:forEach var="item" items="${hw_interfaceList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
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
									<label class=" form-control-label"><strong>
											Budget Head</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head">${ViewConsumablesCmd.b_head}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>
											Budget Code</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_code"></label>
									<form:select path="b_code" id="b_code"
										class="form-control display_none">
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
									<label class="form-control-label"><strong>CRV
											No.</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbl_crv_no">${ViewConsumablesCmd.crv_no}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong>GeM
											Contact No.</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbl_gem_no">${ViewConsumablesCmd.gem_no}</label>

								</div>
							</div>
						</div>
					</div>





				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="${screenurl}" class="btn btn-success btn-sm">Back</a>
					<i class="fa fa-download "></i><input type="button"
						class="btn btn-primary btn-sm" value="Print Page" id="btn_p">


				</div>
			</div>
		</div>
	</div>

</form:form>

<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('btn_p').onclick = function() {
			return printDiv();
		};
// 		document.getElementById('download_id').onclick = function() {
// 			return download_file();
// 		};
// 		document.getElementById('type_of_hw').onchange = function() {
// 			return fn_type();
// 		};

		document.getElementById('assets_type').onchange = function() {
			return fn_Peripheral(), fn_makeName();
		};
	});
	$(document)
			.ready(
					function() {
						$("#section").val("${ViewConsumablesCmd.section}");
						$("#maintainAgency").val(
								"${ViewConsumablesCmd.maintainagency}");
						debugger;
						$("#lblsection").text(
								$("#section option:selected").text());
						
						$("#assets_type").val(
								
								'${ViewConsumablesCmd.assets_type}');
						$("#make_name").val('${ViewConsumablesCmd.make_name}');
						
						$("#model_name")
								.val('${ViewConsumablesCmd.model_name}');
						$("#b_code").val('${ViewConsumablesCmd.b_code}');
						$("#type_of_hw")
								.val('${ViewConsumablesCmd.type_of_hw}');
						$("#type").val('${ViewConsumablesCmd.type}');
						
						var asset_type = $("select#assets_type option:selected")
								.text();
						$("#maintaince_agencylbl").text(
								$("#maintainAgency option:selected").text());
						if (asset_type == 'UPS') {

							$("#up").show();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
						} else if (asset_type == "PRINTERS") {

							$("#mono").show();
							$("#max").show();
							$("#con").show();

							$("#mf").hide();
							$("#up").hide();

						} else if (asset_type == 'MFD') {

							$("#mf").show();
							$("#up").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						}

						else if (asset_type == 'PROJECTION SYS') {

							$("#pro").show();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'VISUALIZER') {

							$("#vis").show();
							$("#res").show();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'NETWORK COMPONENTS') {
							$("#net").show();
							$("#no").show();
							$("#vis").hide();
							$("#res").hide();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'MONITOR') {
							$("#ty").show();
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

						}

						fn_brand_other();
						var make_name = $("#make_name option:selected").text();
						fn_make_other();
						var model_name = $("#model_name option:selected")
								.text();
						var b_code = $("#b_code option:selected").text();
						var type_of_hw = $("#type_of_hw option:selected")
								.text();
						fn_type_hw_other();

						var make_name = $("#make_name option:selected").text();
						var model_name = $("#model_name option:selected")
								.text();
						var type = $("#type option:selected").text();
						fn_model_type_other();

						var ups_capacity = $("#ups_capacity option:selected")
								.text();
						var paper_size = $("#paper_size option:selected")
								.text();
						var display_connector = $(
								"#display_connector option:selected").text();

						// 						var hardware_interface = $("#hw_interface option:selected").text();
						var ethernet_port = $("#ethernet_port option:selected")
								.text();
						var management_layer = $(
								"#management_layer option:selected").text();
						var network_features = $(
								"#network_features option:selected").text();
						var v_display_connector = $(
								"#v_display_connector option:selected").text();

						var s_state = $("#s_state option:selected").text();
						if (s_state == "Un-serviceable") {
							$("#uni").show();
							$("#unsv_div").show();
							$("#maintaince_agencyldiv").show();
						}

						selectBer();

						var unserviceable_state = $(
								"#unserviceable_state option:selected").text();



					
						var budget_code = $("#b_code option:selected").text();

						var lblb_code = $("#b_code option:selected").text();

						$("#lblb_code").text(lblb_code);
						$("#lblAsset_type").text(asset_type);
						$("#lbltype_of_hw").text(type_of_hw);
						$("#lblmake_name").text(make_name);
						$("#lblmodel_name").text(model_name);
						$("#lbltype").text(type);
						$("#lblups_capacity").text(ups_capacity);
						$("#lblpaper_size").text(paper_size);
						$("#lbldisplay_connector").text(display_connector);

						$("#lbls_state").text(s_state);

						$("#lblunserviceable_state").text(unserviceable_state);
						var a = '${hw_int_data[0][0]}'
						$("#lblhwinterface").text(a);
						var a = '${ntwrk_ftr_data[0][0]}'
						$("#lblb_network_features").text(a);
						$("#lblv_display_connector").text(v_display_connector);
			

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

						var lbl_warrenty = '${ViewConsumablesCmd.warranty}';
						wd = lbl_warrenty.substring(0, 10);
						$("#lblwarranty").text(ParseDateColumncommission(wd));

						var lblb_proc_dt = '${ViewConsumablesCmd.proc_date}';
						proc_dt = lblb_proc_dt.substring(0, 10);
						$("#lblb_proc_date").text(
								ParseDateColumncommission(proc_dt));

					});

	function printDiv() {

		// 		var cspNonce='${cspNonce}';
		// 		let innerContents = document.getElementById('printableArea').innerHTML;
		// 		popupWindow = window
		// 				.open(
		// 						'',
		// 						'_blank',
		// 						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		// 		popupWindow.document.open();
		// 		popupWindow.document
		// 				.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;} .col-md-12 {display: inline-flex;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
		// 						+ innerContents + '</html>');
		// 		popupWindow.document.close();
		var cspNonce = '${cspNonce}';
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window
				.open(
						'',
						'_blank',
						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();

		popupWindow.document
				.write('<html>'
						+ '<head>'
						+ '<link rel="stylesheet" href="js/assets/css/bootstrap.min.css" nonce="' + cspNonce + '">'
						+ '<link rel="stylesheet" href="js/cue/printWatermark.css" nonce="' + cspNonce + '">'
						+ '<style nonce="' + cspNonce + '">'
						+ 'table td { font-size: 50px; font-weight: bold !important; }'
						+ 'table th { font-size: 12px !important; }'
						+ 'tbody th { font-size: 10px; font-weight: normal !important; }'
						+ '.col-md-12 { display: inline-flex; }'
						+ '.f_22 { font-size: 22px; }'
						+ '.f_28 { font-size: 28px; }'
						+ '.text_align_center { text-align: center; }'
						+ '.text_decoration_underline { text-decoration: underline; }'
						+ '.margint_top_56 { margin-top: -56px; float: right; max-width: 155px; height: 50px; }'
						+ '.pd_right { padding-right: 50px; }'
						+ '.height50 { height: 50px; }'
						+ '.display_none { display: none; }'
						+ '</style>'
						+ '</head>'
						+ '<body id="id_body">'
						+ '<table>'
						+ '<tr>'
						+ '<td align="left">'
						+ '<img src="js/miso/images/indianarmy_smrm5aaa.jpg" class="height50">'
						+ '</td>'
						+ '<td align="center">'
						+ '<h4 class="f_22 text_align_center">AFMS IT ASSETS</h4>'
						+ '</td>'
						+ '<td align="right">'
						+ // Fixed typo from "algin" to "align"
						'<img src="js/miso/images/dgis-logo.png" class="margint_top_56">'
						+ '</td>'
						+ '</tr>'
						+ '</table>'
						+ '<h5 class="text_decoration_underline text_align_center"><b>RESTRICTED</b></h5>'
						+ '<h1 class="f_28 text_align_center">VIEW COMPUTING ASSETS DETAILS</h1><br>'
						+ innerContents + '</body>' + '</html>');

		popupWindow.document.close();
		popupWindow.onload = function() {
			var body = popupWindow.document.getElementById('id_body');

			body.oncopy = function() {
				return false;
			};
			body.oncut = function() {
				return false;
			};
			body.onpaste = function() {
				return false;
			};
			body.oncontextmenu = function() {
				return false;
			};
			window.focus();
			window.print();
			window.close();
		};
	}

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

	function fn_type_hw_other() {
		var text = $("#type_of_hw option:selected").text();

		if (text.toUpperCase() == "OTHERS") {
			$("#peri_hw_hid").show();
		} else {
			$("#peri_hw_hid").hide();
			$("#peri_hw_other").val('');
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

	/////For File Download
	function selectBer() {

		var a = $("select#unserviceable_state").val();
		if (a == '1') {

			$("#file_show").show();
		} else {
			$("#file_show").hide();
		}
	}

	</script>
	
	
	



