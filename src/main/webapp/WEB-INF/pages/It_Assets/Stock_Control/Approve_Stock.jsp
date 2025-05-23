<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">

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

.download-excel-btn {
	margin-bottom: 10px; /* Adjust as needed */
}

#divwatermark {
	margin-top: 20px; /* Create space above the checkbox */
}
</style>

<form:form name="user_mst" id="user_mst" action="user_mstAction"
	method='POST' modelAttribute="user_mstCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header" align="center">
				<h5>APPROVE STOCK</h5>
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
			<div class="tab-content" id="myTabContent">
				<div class="card-body card-block cue_text show active" id="home"
					role="tabpanel" aria-labelledby="home-tab">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class="form-control-label"> Section </label>
									</div>
									<div class="col-md-8">
										<select id="section" name="section" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSectionNameList}"
												varStatus="num">
												<option value="${item.id}"
													<c:if test="${item.id == user_mstCMD.section}">selected</c:if>>${item.section}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>Computing Assets Type</label>
									</div>
									<div class="col-md-8">
										<select name="assets_type" id="assets_type"
											class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${ComputingAssetList}"
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
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>Machine Number</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="machine_number" id="machine_number"
											class="form-control" maxlength="50" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Status</label>
									</div>
									<div class="col-md-8">
										<select name="status" id="status" class="form-control">
											<option value="0">Pending</option>
											<option value="1">Approved</option>
											<option value="3">Rejected</option>
											<option value="7">Archived</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>RAM Capacity</label>
									</div>
									<div class="col-md-8">
										<select name="ram_capi" id="ram_capi" class="form-control">
											<option value="0">--select--</option>
											<c:forEach var="item" items="${ramList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>HDD Capacity</label>
									</div>
									<div class="col-md-8">
										<select name="hdd_capi" id="hdd_capi" class="form-control">
											<option value="0">--select--</option>
											<c:forEach var="item" items="${hddList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Operating System</label>
									</div>
									<div class="col-md-8">
										<select name="operating_system" id="operating_system"
											class="form-control">
											<option value="0">--select--</option>
											<c:forEach var="item" items="${osList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 display_none ">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong></label>
									</div>
									<div class="col-md-8">
										<input type="text" id="unit_sus_no" name="unit_sus_no"
											class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong> </label>
									</div>
									<div class="col-md-8">
										<input type="text" id="unit_name" name="unit_name"
											class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 ">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>From Date</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="from_date" id="from_date"
											maxlength="10"
											class="form-control width93 display_inline rgb0"
											aria-required="true" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>To Date</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="to_date" id="to_date" maxlength="10"
											class="form-control width93 display_inline rgb0"
											aria-required="true" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class="form-control-label">Serviceable State</label>
									</div>
									<div class="col-md-8">
										<select name="s_state" id="s_state" class="form-control"
											onchange="serviceStChange();">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getServiceable_StateList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="col-md-12"></div>
				</div>
				<div class="card-body card-block cue_text display_none" id="profile">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class="form-control-label">Section </label>
									</div>
									<div class="col-md-8">
										<select id="section" name="section" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSectionNameList}"
												varStatus="num">
												<option value="${item.id}"
													<c:if test="${item.id == section}">selected</c:if>>${item.section}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Peripheral Type</label>
									</div>
									<div class="col-md-8">
										<select name="assets_type" id="assets_type"
											class="form-control">
											<option value="0">--select--</option>
											<c:forEach var="item" items="${getPeripheral}"
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
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Year Of Manufacturing</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="year_of_manufacturing"
											name="year_of_manufacturing" maxlength="4"
											class="form-control autocomplete" autocomplete="off"></input>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Machine No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="machine_no" name="machine_no"
											class="form-control autocomplete" maxlength="50"
											autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<!-- Additional fields can be added here if needed -->
						</div>
						<div class="col-md-12 display_none">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>Unit Name</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="unit_name" name="unit_name"
											class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Unit Sus No</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="unit_sus_no" name="unit_sus_no"
											class="form-control autocomplete" autocomplete="off" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>From Date</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="from_date" id="from_date"
											maxlength="10"
											class="form-control width93 display_inline rgb0"
											aria-required="true" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6 display_none">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"> </strong>To Date</label>
									</div>
									<div class="col-md-8">
										<input type="text" name="to_date" id="to_date" maxlength="10"
											class="form-control width93 display_inline rgb0"
											aria-required="true" autocomplete="off">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class="form-control-label"><strong
											class="color_red"></strong>Status</label>
									</div>
									<div class="col-md-8">
										<select name="status" id="status1" class="form-control">
											<option value="0">Pending</option>
											<option value="1">Approved</option>
											<option value="3">Rejected</option>
											<option value="7">Archived</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class="form-control-label">Serviceable State</label>
									</div>
									<div class="col-md-8">
										<select name="s_state" id="s_state" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getServiceable_StateList}"
												varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="card-body card-block cue_text display_none"
					id="consumable">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class=" form-control-label">Section </label>
									</div>
									<div class="col-md-8">
										<select id="section" name="section" class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getSectionNameList}"
												varStatus="num">
												<option value="${item.id}"
													<c:if test="${item.id == section}">selected</c:if>>${item.section}</option>
											</c:forEach>
										</select>

									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class=" form-control-label"><strong
											class="color_red"></strong>Consumable Type</label>
									</div>
									<div class="col-md-8">
										<select name="assets_type" id="assets_type"
											class="form-control">
											<option value="0">--Select--</option>
											<c:forEach var="item" items="${getConsumables}"
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
									<div class="col-md-3">
										<label for="text-input" class=" form-control-label"><strong
											class="color_red"></strong>Year Of Production</label>
									</div>
									<div class="col-md-8">
										<input type="text" id="year_of_manufacturing"
											name="year_of_manufacturing" maxlength="4"
											class="form-control autocomplete" maxlength="4"
											autocomplete="off"></input>
									</div>

								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label for="text-input" class=" form-control-label"><strong
											class="color_red"></strong>Status</label>
									</div>
									<div class="col-md-8">
										<select name="status" id="status2" class="form-control">
											<option value="0">Pending</option>
											<option value="1">Approved</option>
											<option value="3">Rejected</option>
											<option value="7">Archived</option>
										</select>
									</div>

								</div>
							</div>
						</div>
						<div class="col-md-12"></div>

					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<a href="approve_stock" class="btn btn-primary btn-sm" id="btn_clc">Clear</a>
				<i class="fa fa-search"></i><input type="button"
					class="btn btn-success btn-sm" id="searchbtn" value="Search" />
			</div>
		</div>

		<div align="center" class="card" id="COMPUTING">

			<!-- 			<i class="fa fa-file-excel-o fxlarge color_green text_align_right" -->
			<!-- 				id="btnExport" aria-hidden="true" title="EXPORT TO EXCEL"></i> -->

			<div class="card-body ">
				<div class="" id="divPrint">
					<br>
					<div align="center" class="container">
						<button class="download-excel-btn" id="btnExport"
							title="EXPORT TO EXCEL">
							<i class="fa fa-file-excel-o"></i> Download Excel
						</button>
					</div>
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span> <input type="hidden" id="CheckVal"
							name="CheckVal">
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<b><input type=checkbox id='nSelAll' name='tregn'>Select
								all [<span id="tregn">0</span><span id='nTotRow1'>/</span><span
								id="tregnn"> ${list.size()}</span>]</b>
						</c:if>
						<table id="computing_tbl" class="display">
							<thead>
								<tr>
									<th id="2">Computing Assets Type</th>
									<th id="4">Machine No</th>
									<th id="5">Mac Address</th>
									<th id="6">Ip Address</th>
									<th id="7">Processor Type</th>
									<th id="11">Office</th>
									<th id="13">Deply Envt as Applicable</th>
									<th id="14">Section</th>
									<th id="15">Created By Username</th>
									<c:if test="${roleType == 'APP' && status1 == '0'}">
										<th>Select To Approve/Reject</th>
									</c:if>

									<th class="action">Action</th>
								</tr>
							</thead>

						</table>
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<input type="button" class="btn btn-success btn-sm"
								id="approvebtn" name="approvebtn" value="Approve">
							<input type="button" class="btn btn-success btn-sm"
								id="rejectbtn" value="Reject">
						</c:if>
					</div>
				</div>
			</div>

		</div>

		<div align="center" class="card display_none" id="PERIPHERAL">


			<!-- 			<i class="fa fa-file-excel-o fxlarge color_green text_align_right" -->
			<!-- 				id="btnExport" aria-hidden="true" title="EXPORT TO EXCEL"></i> -->
			<div class="card-body ">
				<div class="" id="divPrint">
					<div align="center" class="container">
						<br>
						<button class="download-excel-btn" id="btnExport"
							title="EXPORT TO EXCEL">
							<i class="fa fa-file-excel-o"></i> Download Excel
						</button>
					</div>

					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span> <input type="hidden" id="CheckVal2"
							name="CheckVal2">
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<b><input type=checkbox id='nSelAll2' name='tregn'>Select
								all [<span id="tregn2">0</span><span id='nTotRow1'>/</span><span
								id="tregnn2"> ${list.size()}</span>]</b>
						</c:if>
						<table id="periphetal_tbl" class="display">
							<thead>
								<tr>
									<th id="2">Peripheral Type</th>
									<th id="3">Type of HW</th>
									<th id="4">Year Of Proc</th>
									<th id="5">Year Of Manufacturing</th>
									<th id="8">Machine No</th>
									<th id="14">Section</th>
									<th id="15">Created By Username</th>

									<th id="10">Remarks</th>
									<c:if test="${roleType == 'APP'  && status1 == '0'}">
										<th>Select To Approve</th>
									</c:if>
									<th data-orderable="false">Action</th>
								</tr>
							</thead>
						</table>
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<input type="button" class="btn btn-success btn-sm"
								id="approvebtn2" value="Approve">
							<input type="button" class="btn btn-success btn-sm"
								id="rejectbtn2" value="Reject">
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<div align="center" class="card display_none" id="CONSUMABLE">

			<!-- 			<i class="fa fa-file-excel-o fxlarge color_green text_align_right" -->
			<!-- 				id="btnExport" aria-hidden="true" title="EXPORT TO EXCEL"></i> -->
			<div class="card-body ">
				<div class="" id="divPrint">
					<div align="center" class="container">
						<br>
						<button class="download-excel-btn" id="btnExport"
							title="EXPORT TO EXCEL">
							<i class="fa fa-file-excel-o"></i> Download Excel
						</button>
					</div>

					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span> <input type="hidden" id="CheckVal3"
							name="CheckVal3">
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<b><input type=checkbox id='nSelAll3' name='tregn3'>Select
								all [<span id="tregn3">0</span><span id='nTotRow1'>/</span><span
								id="tregnn3"> ${list.size()}</span>]</b>
						</c:if>
						<table id="consumable_tbl" class="display text_align_center">
							<thead>
								<tr>
									<th id="2">Consumable Type</th>
									<th id="4">Year Of Proc</th>
									<th id="14">Section</th>
									<th id="15">Created By Username</th>
									<th id="10">Remarks</th>
									<c:if test="${roleType == 'APP'  && status1 == '0'}">
										<th>Select To Approve</th>
									</c:if>
									<th data-orderable="false">Action</th>
								</tr>
							</thead>
						</table>
						<c:if test="${roleType == 'APP'  && status1 == '0'}">
							<input type="button" class="btn btn-success btn-sm"
								id="approvebtn3" value="Approve">
							<input type="button" class="btn btn-success btn-sm"
								id="rejectbtn3" value="Reject">
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>
<%-- <c:url value="Search_Computing_AssetsUrl_1" --%>
<%-- 	var="Search_Computing_AssetsUrl_1" /> --%>
<%-- <form:form action="${Search_Computing_AssetsUrl_1}" method="post" --%>
<%-- 	id="searchForm" name="searchForm" modelAttribute="computing_assets1"> --%>
<!-- 	<input type="hidden" name="section1" id="section1" value="0" /> -->
<!-- 	<input type="hidden" name="computing_assets1" id="computing_assets1" -->
<!-- 		value="0" /> -->
<!-- 	<!--      <input type="hidden" name="model_number1" id="model_number1" value=""/> --> 
<!-- 	<input type="hidden" name="machine_number1" id="machine_number1" -->
<!-- 		value="" /> -->
<!-- 	<input type="hidden" name="ram_capi1" id="ram_capi1" value="0" /> -->
<!-- 	<input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0" /> -->
<!-- 	<input type="hidden" name="operating_system1" id="operating_system1" -->
<!-- 		value="0" /> -->
<!-- 	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="" /> -->
<!-- 	<input type="hidden" name="unit_name1" id="unit_name1" value="" /> -->
<!-- 	<input type="hidden" name="from_date1" id="from_date1" value="" /> -->
<!-- 	<input type="hidden" name="to_date1" id="to_date1" value="" /> -->
<!-- 	<input type="hidden" name="assets_type1" id="assets_type1" value="0" /> -->
<!-- 	<input type="hidden" name="status1" id="status1" value="0" /> -->
<!-- 	<input type="hidden" name="s_state1" id="s_state1" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="ComputingAssertsEdit" var="updateUrl" /> --%>
<%-- <form:form action="${updateUrl}" method="post" id="updateForm" --%>
<%-- 	name="updateForm" modelAttribute="id"> --%>
<!-- 	<input type="hidden" name="updateid" id="updateid" value="0" /> -->
<%-- </form:form> --%>

<%-- <c:url value="ComputingAssertsDelete" var="deleteUrl" /> --%>
<%-- <form:form action="${deleteUrl}" method="post" id="deleteForm" --%>
<%-- 	name="deleteForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id1" id="id1" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="ComputingAssertsArchive" var="archiveUrl" /> --%>
<%-- <form:form action="${archiveUrl}" method="post" id="archiveForm" --%>
<%-- 	name="archiveForm" modelAttribute="id1"> --%>
<!-- 	<input type="hidden" name="id2" id="id2" value="0" /> -->
<%-- </form:form> --%>

<%-- <c:url value="ComputingAssertsView" var="viewUrl" /> --%>
<%-- <form:form action="${viewUrl}" method="post" id="viewForm" --%>
<%-- 	name="viewForm" modelAttribute="id"> --%>
<!-- 	<input type="hidden" name="viewid" id="viewid" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="demo_report1" var="searchUrl" /> --%>
<%-- <form:form action="${searchUrl}" method="post" id="search1" --%>
<%-- 	name="search1" modelAttribute="comd2"> --%>
<!-- 	<input type="hidden" name="typeReport2" id="typeReport2" value="0" /> -->
<%-- </form:form> --%>
<%-- <c:url value="Excel_Computing_Assets_Search" var="excelUrl" /> --%>
<%-- <form:form action="${excelUrl}" method="post" id="ExcelForm" --%>
<%-- 	name="ExcelForm" modelAttribute="id3"> --%>
<!-- 	<input type="hidden" name="section2" id="section2" value="0" /> -->
<!-- 	<!--      <input type="hidden" name="model_number2" id="model_number2" value=""/> --> 
<!-- 	<input type="hidden" name="machine_number2" id="machine_number2" status -->
<!-- 		value="" /> -->
<!-- 	<input type="hidden" name="assets_type2" id="assets_type2" value="0" /> -->
<!-- 	<input type="hidden" name="status2" id="status2" value="0" /> -->
<!-- 	<input type="hidden" name="s_state2" id="s_state2" value="0" /> -->
<%-- </form:form> --%>
<!--       for home tab -->

<c:url value="Search_Computing_AssetsUrl_1"
	var="Search_Computing_AssetsUrl_1" />
<form:form action="${Search_Computing_AssetsUrl_1}" method="post"
	id="searchForm" name="searchForm" modelAttribute="computing_assets1">
	<input type="hidden" name="section1" id="section1" value="0" />
	<input type="hidden" name="computing_assets1" id="computing_assets1"
		value="0" />
	<input type="hidden" name="machine_number1" id="machine_number1"
		value="" />
	<input type="hidden" name="ram_capi1" id="ram_capi1" value="0" />
	<input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0" />
	<input type="hidden" name="operating_system1" id="operating_system1"
		value="0" />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value="" />
	<input type="hidden" name="unit_name1" id="unit_name1" value="" />
	<input type="hidden" name="from_date1" id="from_date1" value="" />
	<input type="hidden" name="to_date1" id="to_date1" value="" />
	<input type="hidden" name="assets_type1" id="assets_type1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="s_state1" id="s_state1" value="0" />
</form:form>
<c:url value="ComputingAssertsEdit" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id">
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="screenurl" id="screenurl" value="approve_stock" />
</form:form>

<c:url value="ComputingAssertsDelete" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
	<input type="hidden" name="screenurl2" id="screenurl2" value="approve_stock" />
</form:form>
<c:url value="ComputingAssertsArchive" var="archiveUrl" />
<form:form action="${archiveUrl}" method="post" id="archiveForm"
	name="archiveForm" modelAttribute="id1">
	<input type="hidden" name="id2" id="id2" value="0" />
	<input type="hidden" name="screenurl3" id="screenurl3" value="approve_stock" />
</form:form>

<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm"
	name="viewForm" modelAttribute="id">
	<input type="hidden" name="viewid" id="viewid" value="0" />
	<input type="hidden" name="screenurl4" id="screenurl4" value="approve_stock" />
</form:form>
<c:url value="demo_report1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1"
	name="search1" modelAttribute="comd2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
</form:form>
<c:url value="Excel_Computing_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	<!--      <input type="hidden" name="model_number2" id="model_number2" value=""/> -->
	<input type="hidden" name="machine_number2" id="machine_number2"
		value="" />
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="s_state2" id="s_state2" value="0" />
</form:form>


<!--       for profile tab -->
<c:url value="Search_PeripheralUrl_1" var="Search_PeripheralUrl_1" />
<form:form action="${Search_PeripheralUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
     <input type="hidden" name="machine_make1" id="machine_make1" value=""/>
     <input type="hidden" name="machine_no1" id="machine_no1" value=""/>
<!--      <input type="hidden" name="model_no1" id="model_no1" value=""/>    -->
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="section1" id="section1" value="0"/>
     <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
       <input type="hidden" name="s_state1" id="s_state1" value="0"/>
     
</form:form>
	<c:url value="Excel_Peripheral_Assets_Search" var="excelUrl" />
	<form:form action="${excelUrl}" method="post" id="ExcelForm2"
		name="ExcelForm2" modelAttribute="id3">
		<input type="hidden" name="section2" id="section2" value="0" />
		<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
		<input type="hidden" name="year_of_manufacturing2"
			id="year_of_manufacturing2" value="0" />
		<input type="hidden" name="machine_number2" id="machine_number2"
			value="" />
		<!-- 	<input type="hidden" name="model_number2" id="model_number2" value="" /> -->
		<input type="hidden" name="status2" id="status2" value="0" />
		<input type="hidden" name="s_state2" id="s_state2" value="0" />
	</form:form>


<c:url value="Edit_Peripherals" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm1" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid1" value="0" />
	<input type="hidden" name="screenurl5" id="screenurl5" value="approve_stock" />
</form:form> 
	
 <c:url value="Delete_Peripherals" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm1" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id5" value="0"/> 
		<input type="hidden" name="screenurl6" id="screenurl6" value="approve_stock" />
	</form:form>
	
	 <c:url value="View_Peripherals" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid1" value="0" />
	<input type="hidden" name="screenurl7" id="screenurl7" value="approve_stock" />
</form:form> 

<c:url value="PeripheralsArchive" var="archiveUrl" />
	<form:form action="${archiveUrl}" method="post" id="archiveForm1" name="archiveForm" modelAttribute="id1">
		<input type="hidden" name="arid2" id="arid2" value="0"/> 
		<input type="hidden" name="screenurl8" id="screenurl8" value="approve_stock" />
	</form:form>


<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 




<!-- for consumable tab -->
<c:url value="Search_consumable_1" var="Search_consumable_1" />
<form:form action="${Search_consumable_1}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
    
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="section1" id="section1" value="0"/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
     
</form:form>

<%-- <c:url value="Excel_Consumables_Assets_Search" var="excelUrl" /> --%>
<%-- <form:form action="${excelUrl}" method="post" id="ExcelForm3" --%>
<%-- 	name="ExcelForm" modelAttribute="id3"> --%>
<!-- <!-- 	<input type="hidden" name="section2" id="section2" value="0" /> --> 
<!-- <!-- 	<input type="hidden" name="assets_type2" id="assets_type2" value="0" /> -->
<!-- <!-- 	<input type="hidden" name="year_of_manufacturing2"id="year_of_manufacturing2" value="0" /> --> 
<!-- <!-- 	<input type="hidden" name="status2" id="status2" value="0" /> --> 
<%-- </form:form> --%>
<c:url value="Excel_Consumables_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm3"
	name="ExcelForm3" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	<input type="hidden" name="year_of_manufacturing2"id="year_of_manufacturing2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
</form:form>

<c:url value="Edit_Consumables" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm8" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid8" value="0" />
	<input type="hidden" name="screenurl9" id="screenurl9" value="approve_stock" />
</form:form> 
	
 <c:url value="Delete_consumables" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm8" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id8" value="0"/> 
		<input type="hidden" name="screenurl10" id="screenurl10" value="approve_stock" />
	</form:form>
	
	 <c:url value="View_consumables" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm8" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid8" value="0" />
	<input type="hidden" name="screenurl11" id="screenurl11" value="approve_stock" />
</form:form> 

<c:url value="ConsumablesArchive" var="archiveUrl" />
	<form:form action="${archiveUrl}" method="post" id="archiveForm8" name="archiveForm" modelAttribute="id1">
		<input type="hidden" name="arid2" id="arid8" value="0"/> 
		<input type="hidden" name="screenurl12" id="screenurl12" value="approve_stock" />
	</form:form>


<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<script nonce="${cspNonce}">
var activeTab="home-tab";
document.addEventListener('DOMContentLoaded', function () {
	//for computing
	
	   if ('${roleType}' == 'APP'  && '${status1}' == '0')
   	{
	document.getElementById('approvebtn').onclick = 
		function() {
		return setApproveStatus();
 	};
 	document.getElementById('rejectbtn').onclick = 
		function() {
		return setRejectStatus();
 	};document.getElementById('nSelAll').onclick = 
		function() {
		return setselectall();
 	};	
   	}
	
	
	// for peripheral
	   if ('${roleType}' == 'APP'  && '${status1}' == '0')
	   	{
		document.getElementById('approvebtn2').onclick = 
			function() {
			return setApproveStatus2();
	 	};
	 	document.getElementById('rejectbtn2').onclick = 
			function() {
			return setRejectStatus2();
	 	};document.getElementById('nSelAll2').onclick = 
			function() {
			return setselectall2();
	 	};	
	   	}
	
	//for consumabel
	  if ('${roleType}' == 'APP'  && '${status1}' == '0')
	   	{
		document.getElementById('approvebtn3').onclick = 
			function() {
			return setApproveStatus3();
	 	};
	 	document.getElementById('rejectbtn3').onclick = 
			function() {
			return setRejectStatus3();
	 	};document.getElementById('nSelAll3').onclick = 
			function() {
			return setselectall3();
	 	};	
	   	}
	
	
// 	//for peripheral
// 	document.getElementById('searchbtn').onclick = 
// 		function() {
// 		return Search();
//   	};
//   	document.getElementById('btnExport').onclick = 
// 		function() {
// 		return getExcel();
//   	};
  	
// 	document.addEventListener('click', function(event) {
//   	    if (event.target.classList.contains('action_update')) {
//   	        if (confirm('Are you sure you want to Update?')) {
//   	          var encryptedPk = event.target.getAttribute('data-id');
//   	            editData(encryptedPk); 
//   	        }
//   	    } 
//   	    else if (event.target.classList.contains('action_delete')) {
//   	        if (confirm('Are you sure you want to Delete?')) {
//   	          var encryptedPk = event.target.getAttribute('data-id');
//   	            deleteData(encryptedPk); 
//   	        }
//   	    } 
//   	    else if (event.target.classList.contains('action_archive')) {
//   	        if (confirm('Are you sure you want to Archive?')) {
//   	          var encryptedPk = event.target.getAttribute('data-id');
//   	        ArchiveData(encryptedPk); 
//   	        }
//   	    }
//   	    else if (event.target.classList.contains('action_view_1')) {
//   	      var encryptedPk = event.target.getAttribute('data-id');
//   	    viewData(encryptedPk); 
//   	    }
//   	});
	
	document.getElementById('searchbtn').onclick = 
		function() {
		return Search();
  	};
	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};
	document.addEventListener('click', function(event) {
		 if(activeTab=="home-tab"){ 
  	    if (event.target.classList.contains('action_update')) {
  	        if (confirm('Are you sure you want to Update?')) {
  	          var encryptedPk = event.target.getAttribute('data-id');
  	            editData(encryptedPk); 
  	        }
  	    } 
  	    else if (event.target.classList.contains('action_delete')) {
  	        if (confirm('Are you sure you want to Delete?')) {
  	          var encryptedPk = event.target.getAttribute('data-id');
  	            deleteData(encryptedPk); 
  	        }
  	    } 
  	    else if (event.target.classList.contains('action_archive')) {
  	        if (confirm('Are you sure you want to Archive?')) {
  	          var encryptedPk = event.target.getAttribute('data-id');
  	        ArchiveData(encryptedPk); 
  	        }
  	    }
  	    else if (event.target.classList.contains('action_view_1')) {
  	      var encryptedPk = event.target.getAttribute('data-id');
  	    viewData(encryptedPk); 
  	    }
		 }
		 //for peripheral
		 if(activeTab=="profile-tab")
			 {
			 debugger;
		      if (event.target.classList.contains('action_update')) {
	                 var encryptedPk = event.target.getAttribute('data-id');
	                 if (confirm('Are you sure you want to Update?')) {
	                         editData1(encryptedPk);
	                 }
	         } else if (event.target.classList.contains('action_delete')) {
	                 var encryptedPk = event.target.getAttribute('data-id');
	                 if (confirm('Are you sure you want to Delete?')) {
	                         deleteData1(encryptedPk);
	                 }
	         }
	         else if (event.target.classList.contains('fa-eye')) {
	             var encryptedPk = event.target.getAttribute('data-id');
	             if (confirm('Are you sure you want to view?')) {
	            	 viewData1(encryptedPk);
	             }
	     }
	         else if (event.target.classList.contains('action_archive')) {
	             var encryptedPk = event.target.getAttribute('data-id');
	             if (confirm('Are you sure you want to Archive?')) {
	            	 ArchiveData1(encryptedPk);
	             }
	     } 
			 }
		 
		 //for consumable tab
		 if(activeTab=="consumable-tab"){
			 
			 if (event.target.classList.contains('action_update')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 if (confirm('Are you sure you want to Update?')) {
                         editData8(encryptedPk);
                 }
         } else if (event.target.classList.contains('action_delete')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 if (confirm('Are you sure you want to Delete?')) {
                         deleteData8(encryptedPk);
                 }
         }
         else if (event.target.classList.contains('fa-eye')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to view?')) {
            	 viewData8(encryptedPk);
             }
     }
         else if (event.target.classList.contains('action_archive')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to Archive?')) {
            	 ArchiveData8(encryptedPk);
             }
     }
		 }
		 
		 
  	});
	
	
    document.querySelectorAll('.nrCheckBox').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
        	var encryptedPk = event.target.getAttribute('data-id');
            checkbox_count(this, encryptedPk); // Call your existing function
        });
    });
    
    
    
    document.querySelectorAll('.nrCheckBox2').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
        	var encryptedPk = event.target.getAttribute('data-id');
            checkbox_count2(this, encryptedPk); // Call your existing function
        });
    });
    
    document.querySelectorAll('.nrCheckBox3').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
        	var encryptedPk = event.target.getAttribute('data-id');
            checkbox_count(this, encryptedPk); // Call your existing function
        });
    });
    
    
});
// function getExcel1() {	
// debugger;
// console.log("Asset Type: ", $("#asset_type").val());
// console.log("Machine Number: ", $("#machine_number").val());
// console.log("Assets Type: ", $("#assets_type").val());
// console.log("Section: ", $("#section").val());
// console.log("Status: ", $("#status").val());

// 	$("#computing_assets2").val($("#asset_type").val());
// 	//$("#model_number2").val($("#model_number").val());
// 	$("#machine_number2").val($("#machine_number").val());
// 	$("#assets_type2").val($("#assets_type").val());
// 	$("#section2").val($("#section").val());
// 	$("#status2").val($("#status").val());
	
	
// 	document.getElementById('ExcelForm1').submit();
// } 

// function getExcel2() {	
// if(activeTab=="profile-tab"){
// 	$("#computing_assets2").val($("#asset_type").val());
// 	//$("#model_number2").val($("#model_number").val());
// 	$("#machine_number2").val($("#machine_number").val());
// 	$("#assets_type2").val($("#assets_type").val());
// 	$("#section2").val($("#section").val());
// 	$("#status2").val($("#status").val());
// 	$("#section2").val($("#section").val());
// 	$("#year_of_manufacturing2").val($("#year_of_manufacturing").val()); 
// 	document.getElementById('ExcelForm2').submit();
// }
// } 
function btn_clc(){
	location.reload(true);
}
//for home tab

if(activeTab=="home-tab"){ 
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
function ArchiveData(id){
		$("#id2").val(id);
		document.getElementById('archiveForm').submit();
	} 

function editData(id){
	 debugger;
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
}
function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}

}

//for profile tab
	  function deleteData1(id){
	 	$("#id5").val(id);
	 	document.getElementById('deleteForm1').submit();
	 } 
	  function editData1(id){
	 	 $("#updateid1").val(id);
	 	document.getElementById('updateForm1').submit();
	 } 
	  function viewData1(id){
	 	 $("#viewid1").val(id);
	 	document.getElementById('viewForm1').submit();
	 }
	  function ArchiveData1(id){
	 		$("#arid2").val(id);
	 		document.getElementById('archiveForm1').submit();
	 	} 
	  //for consumable
	  
	   function deleteData8(id){
	$("#id8").val(id);
	document.getElementById('deleteForm8').submit();
} 

function editData8(id){
	
	 $("#updateid8").val(id);
	document.getElementById('updateForm8').submit();
} 

function viewData8(id){
	 $("#viewid8").val(id);
	document.getElementById('viewForm8').submit();
}

function ArchiveData8(id){
		$("#arid8").val(id);
		document.getElementById('archiveForm8').submit();
	} 
	$(document).ready(
			function() {
				
				mockjax11('computing_tbl');
				table1 = dataTable11('computing_tbl');
				//search1 = Search('computing_tbl');
				mockjax11('periphetal_tbl');
				table2 = dataTable11('periphetal_tbl');
				
				mockjax11('consumable_tbl');
				table3 = dataTable11('consumable_tbl');

				
			});
	
	$("#profile-tab").click(
			function() {
				debugger;
				 activeTab = "profile-tab";
				// Show profile tab and hide home tab
				$("#profile").show().removeClass('display_none')
						.addClass('active');
				$("#home").hide().addClass('display_none')
						.removeClass('active');
				$("#consumable").hide().addClass('display_none')
						.removeClass('active');

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

				// Reset form and reload table
				//$("#user_mst")[0].reset();
			 table1.ajax.reload();
			});

	$("#home-tab").click(
			function() {
				 activeTab = "home-tab";
				// Show home tab and hide profile tab
				$("#home").show().removeClass('display_none')
						.addClass('active');
				$("#profile").hide().addClass('display_none')
						.removeClass('active');
				$("#consumable").hide().addClass('display_none')
						.removeClass('active');

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

				// Reset form and reload table
				$("#user_mst")[0].reset();
				 table2.ajax.reload();
			});

	$("#consumable-tab").click(
			function() {
				 activeTab = "consumable-tab";
				// Show consumable tab and hide other tabs
				$("#home").hide().addClass('display_none')
						.removeClass('active');
				$("#profile").hide().addClass('display_none')
						.removeClass('active');
				$("#consumable").show().removeClass('display_none')
						.addClass('active');

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

				// Reset form and reload table
				$("#user_mst")[0].reset();
				table3.ajax.reload();
			});
	
	function data(tableName) {
		if (tableName == "computing_tbl") {
			jsondata = [];

			var table = $('#'+tableName).DataTable();
			var info = table.page.info();
			var currentPage = info.page;
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
			var orderType = order[0][1];
			
			var unit_name=$("#unit_name").val() ;
			var unit_sus_no=$("#unit_sus_no").val() ;
			
			var assets_type=$("#assets_type").val() ;

		 //	var model_number=$("#model_number").val() ;
		 
		 	var machine_number=$("#machine_number").val();
		 
		 	var ram_capi=$("#ram_capi").val() ;

		 	var hdd_capi=$("#hdd_capi").val() ;

		 	var operating_system=$("#operating_system").val() ;

		 	var from_date=$("#from_date").val() ;

		 	var to_date=$("#to_date").val() ;
			
		 	var status=$("#status").val() ;
		 	
		 	var s_state=$("#s_state").val() ;
			
			var section =$("#section").val();
		 	
			$.post("getFilteredassetcomputing?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
				assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
				,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name,section:section},function(j) {
					//left here
					for (var i = 0; i < j.length; i++) {
		         	var data =[j[i].assets_name,j[i].machine_number,j[i].mac_address,j[i].ip_address,j[i].processor_type,j[i].office,j[i].dply_env,j[i].section,j[i].created_by
		                 <c:if test="${roleType == 'APP'  && status1 == '0'}">
		               	 ,j[i].chekboxaction
		                 </c:if>
		                 ,j[i].action ];
		                 jsondata.push(data);
		         }
					$("#nSelAll").prop('checked', false);
					$('#tregn').text("0");
					$('#tregnn').text(j.length);
					
				
			});
			$.post("getTotalAssetCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,machine_number:machine_number,ram_capi:ram_capi,hdd_capi:hdd_capi,operating_system:operating_system
				,from_date:from_date,to_date:to_date,status:status,s_state:s_state,unit_sus_no:unit_sus_no,unit_name:unit_name,section:section},function(j) {
				FilteredRecords = j;
			});

		}
		
		if (tableName == "periphetal_tbl") {
			
			  jsondata = [];

		        var table = $('#'+tableName).DataTable();
		        var info = table.page.info();
		        var currentPage = info.page;
		        var pageLength = info.length;
		        var startPage = info.start;
		        var endPage = info.end;
		        var Search = table.search();
		        var order = table.order();
		        var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		        var orderType = order[0][1];
		         var assets_type=$("#assets_type").val() ;
		         var year_of_manufacturing=$("#year_of_manufacturing").val() ;
		         var machine_make=$("#machine_make").val() ;
		         var machine_no=$("#machine_no").val() ;
		        // var model_no=$("#model_no").val() ;
		         var from_date=$("#from_date").val() ;
		         var to_date=$("#to_date").val() ;
		         var status=$("#status1").val() ;
		         var s_state=$("#s_state1").val() ;
		     	var unit_name=$("#unit_name").val() ;
		     	var unit_sus_no=$("#unit_sus_no").val() ;
		    	var section=$("#section").val() ;
		         
		         
		        $.post("getFilteredPeripheral1?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		        	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
		                machine_make:machine_make,machine_no:machine_no,from_date:from_date,

		        		
		        		
		        		
		        		
		        		              to_date:to_date,status:status,s_state:s_state,section:section},function(j) {
		                        
		                for (var i = 0; i < j.length; i++) {
		                	var data =[j[i].assets_name,j[i].type_of_hw,j[i].year_of_proc,j[i].year_of_manufacturing,j[i].machine_no,
		                      j[i].section,j[i].created_by,j[i].remarks
		                        <c:if test="${roleType == 'APP'  && status1 == '0'}">
		                      	 ,j[i].chekboxaction1
		                        </c:if>
		                        ,j[i].action ];
		                        jsondata.push(data);
		                }
		                
		                $("#nSelAll2").prop('checked', false);
						$('#tregn2').text("0");
						$('#tregnn2').text(j.length);
		        });
		        $.post("getTotalPeripheralCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
		            machine_make:machine_make,machine_no:machine_no,from_date:from_date,
		            to_date:to_date,status:status,s_state:s_state,section:section},function(j) {
		                FilteredRecords = j;
		        });
			
			
		}
		if (tableName == "consumable_tbl") {
			
			 jsondata = [];

		        var table = $('#'+tableName).DataTable();
		        var info = table.page.info();
		        var currentPage = info.page;
		        var pageLength = info.length;
		        var startPage = info.start;
		        var endPage = info.end;
		        var Search = table.search();
		        var order = table.order();
		        var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		        var orderType = order[0][1];
		         var assets_type=$("#assets_type").val() ;  
		         var status=$("#status2").val() ;
		    	var section=$("#section").val() ;
		    	var year_of_manufacturing=$("#year_of_manufacturing").val() ;
		         
		         
		        $.post("getFilteredconsumable1?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		        	assets_type:assets_type,
		                status:status,section:section,year_of_manufacturing:year_of_manufacturing},function(j) {
		                        
		                for (var i = 0; i < j.length; i++) {
		                	var data =[j[i].assets_name,j[i].year_of_proc
		                   ,j[i].section,j[i].created_by,j[i].remarks
		                        <c:if test="${roleType == 'APP'  && status1 == '0'}">
		                      	 ,j[i].chekboxaction 
		                        </c:if>
		                        ,j[i].action ];
		                        jsondata.push(data);
		                }
		                
		                $("#nSelAll3").prop('checked', false);
						$('#tregn3').text("0");
						$('#tregnn3').text(j.length);
		        });
		        $.post("getTotalconsumableCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,status:status,section:section,year_of_manufacturing:year_of_manufacturing},function(j) {
		                FilteredRecords = j;
		        });
			
			
		}
		
	}
	 function Search(){
		 debugger;
		 if(activeTab=="home-tab"){ 
			 table1.ajax.reload();
		 
		 }else if(activeTab=="profile-tab"){
			 table2.ajax.reload();
		 }else if(activeTab=="consumable-tab"){
			 table3.ajax.reload();
		 }
			
		}
	 
	 // for computing
	
	 function setApproveStatus(){
		
				//alert("hello");
			
			findselected();
			
			var a = $("#CheckVal").val();

			if(a == ""){
				alert("Please Select the Data for Approval"); 
			}else{

					$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
					alert(j);
					Search();
				}); 
			}
		
			
			
		}
	 function setRejectStatus(){
			
			findselected();
			
			var a = $("#CheckVal").val();

			if(a == ""){
				alert("Please Select the Data for Reject"); 
			}else{

					$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
					alert(j);
					Search();
				}); 
			}	
		}
	 function setselectall(){

			if ($("#nSelAll").prop("checked")) {
				$(".nrCheckBox").prop('checked', true);
			} else {
				$(".nrCheckBox").prop('checked', false);
			}
			
			var l = $('[name="cbox"]:checked').length;
			 $("#tregn").val(l);
			document.getElementById('tregn').innerHTML = l;
			
		}
	 function drawtregn(data) {
			var ii=0;
			$("#nrTable").empty();

			for (var i = 0; i <data.length; i++) {
				 var nkrow="<tr id='nrTable' padding='5px;'>";
				 nkrow=nkrow+"<td>&nbsp;&nbsp;";
				 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
				 $("#nrTable").append(nkrow);
				 ii=ii+1;
			}
			$("#tregn").text(ii);
		}
	 var check_count = 0;
	 function checkbox_count(obj,id)
	 {
	 	if(document.getElementById(obj.id).checked == true)
	 	{ 
	 		check_count++;
	 		
	 	}
	 	if(document.getElementById(obj.id).checked == false)
	 	{
	 		check_count--;
	 		
	 	}
	 	
	 	document.getElementById('tregn').innerHTML =check_count;
	 	
	 }
	 function findselected(){
			var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
				return $(this).attr('id');
			}).get();
				
			var b=nrSel.join(':');
			$("#CheckVal").val(b);
			$('#tregn').text(nrSel.length);
		}
	
	 
	 
	// for peripheral
	 
	 
	 
	
	
	
	
		
function findselected2(){
		debugger;
	var nrSel=$('.nrCheckBox2:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal2").val(b);
	$('#tregn2').text(nrSel.length);
}
	
	
	
	 
var check_count2 = 0;
function checkbox_count2(obj,id)
{
	if(document.getElementById(obj.id).checked == true)
	{ 
		check_count2++;
		
	}
	if(document.getElementById(obj.id).checked == false)
	{
		check_count2--;
		
	}
	
	document.getElementById('tregn2').innerHTML =check_count2;
	
}


function setApproveStatus2(){
	
	
	findselected2();
	
	var a = $("#CheckVal2").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

function setRejectStatus2(){
	
	findselected2();
	
	var a = $("#CheckVal2").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

function setselectall2(){

	if ($("#nSelAll2").prop("checked")) {
		$(".nrCheckBox2").prop('checked', true);
	} else {
		$(".nrCheckBox2").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn2").val(l);
	document.getElementById('tregn2').innerHTML = l;
	
}
function drawtregn2(data) {
	var ii=0;
	$("#nrTable").empty();

	for (var i = 0; i <data.length; i++) {
		 var nkrow="<tr id='nrTable' padding='5px;'>";
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
		 $("#nrTable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn2").text(ii);
}







//for consumabel


function findselected3(){
	var nrSel=$('.nrCheckBox3:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal3").val(b);
	$('#tregn3').text(nrSel.length);
}
function setselectall3(){

	if ($("#nSelAll3").prop("checked")) {
		$(".nrCheckBox3").prop('checked', true);
	} else {
		$(".nrCheckBox3").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn3').innerHTML = l;
	
}

function drawtregn3(data) {
	var ii=0;
	$("#nrTable").empty();

	for (var i = 0; i <data.length; i++) {
		 var nkrow="<tr id='nrTable' padding='5px;'>";
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
		 $("#nrTable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn3").text(ii);
}
function setApproveStatus3(){
	debugger;
	
	findselected3();
	
	var a = $("#CheckVal3").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_ConsumableAssetsData2?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

function setRejectStatus3(){
	
	findselected3();
	
	var a = $("#CheckVal3").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_ConsumableAssetsData2?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}

var check_count3 = 0;
function checkbox_count(obj,id)
{
	if(document.getElementById(obj.id).checked == true)
	{ 
		check_count3++;
		
	}
	if(document.getElementById(obj.id).checked == false)
	{
		check_count3--;
		
	}
	
	document.getElementById('tregn3').innerHTML =check_count3;
	
}

function getExcel() {	
	console.log("Form action URL:", "${excelUrl}");
	debugger;
	if(activeTab=="home-tab"){
		//console.log("Asset Type Element:", $("#asset_type"));
		console.log("Machine Number Element:", $("#machine_number"));
		console.log("Assets Type Element:", $("#assets_type"));
		console.log("Section Element:", $("#section"));
		console.log("Status Element:", $("#status"));
		// Log values before setting them
		//console.log("Asset Type:", $("#asset_type").val());
		console.log("Machine Number:", $("#machine_number").val());
		console.log("Assets Type:", $("#assets_type").val());
		console.log("Section:", $("#section").val());
		console.log("Status:", $("#status").val());
		
//	$("#computing_assets2").val($("#asset_type").val());
	//$("#model_number2").val($("#model_number").val());
	$("#machine_number2").val($("#machine_number").val());
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val($("#status").val());
	document.getElementById('ExcelForm').submit();
	}
	if(activeTab=="profile-tab"){
	$("#computing_assets2").val($("#asset_type").val());
	//$("#model_number2").val($("#model_number").val());
	$("#machine_number2").val($("#machine_number").val());
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val($("#status1").val());
	$("#section2").val($("#section").val());
	$("#year_of_manufacturing2").val($("#year_of_manufacturing").val()); 
	document.getElementById('ExcelForm1').submit();
	}
	if(activeTab=="consumable-tab"){
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val($("#status3").val());
	$("#year_of_manufacturing2").val($("#year_of_manufacturing").val()); 
	document.getElementById('ExcelForm3').submit();
	}
} 
</script>