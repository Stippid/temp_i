<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">

<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- <link rel="stylesheet" -->
<!-- 	href="js/assets/adjestable_Col/jquery.resizableColumns.css"> -->
<!-- <script src="js/assets/adjestable_Col/jquery.resizableColumns.js" -->
<!-- 	type="text/javascript"></script> -->
	
	
	<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<script src="js/Calender/datePicketValidation.js"></script>

<form:form name="Actual_str_of_user" id="Actual_str_of_user" action="assests_Serviceablity_detailsAction" method="post"	class="form-horizontal" modelAttribute="">
	<div class="animated fadeIn">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>LINE DIRECTORATE-WISE ASSETS REPORT</h5>
					<h6 class="enter_by">Reported On: ${date}</h6>
				</div>
				<div class="card-body card-block">
				<input type="hidden" id="username" name="username" value="${username}">
					          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control" >
								            	${selectcomd}
									            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
			                 					${selectcorps}
			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
			                 				</select>
			                			</div>
					              	</div>
								</div>
							</div>
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
						                <div class="col col-md-4">
						                  <label class=" form-control-label">Div</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control" >
						                 		${selectDiv}
						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
						                 	</select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
							<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="Select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Asset Category</label>
								</div>
								<div class="col-md-8">
									<select name="asset_type" id="asset_type" class="form-control" onchange="fn_makeName()"
										>
										<option value="0">--Select--</option>
										<option value="1">Computing</option>
										<option value="2">Peripheral</option>
										<option value="3">CONSUMABLES</option>	
									</select>
								</div>
							</div>
						</div>
								<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Assets Type </label>
								</div>
								<div class="col-md-8">
									<select id="a_type" class="form-control">
									<option value="0" >--Select--</option>
<%-- 										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
									</select>
								</div>
							</div>
						</div>

						
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Budget Head</label>
								</div>
								<div class="col-md-8">
								<select path="b_head" id="b_head" class="form-control" onchange="fn_B_Head()">
											<option value="0">--Select--</option>
											 <c:forEach var="item" items="${getBudgetHeadList}" varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										 </select>
<%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Budget Code</label>
								</div>
								<div class="col-md-8">
								<select path="b_code" id="b_code" class="form-control" >
											<option value="0">--Select--</option>

										 </select>
<%-- 									<form:input type="text" id="b_code" path="b_code" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" id="line_dte_hd">
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-4"> -->
<!-- 									<label class=" form-control-label">Line Dte</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 								<select name="line_dte" id="line_dte" class="form-control" > -->
<!-- 											<option value="0">--Select--</option> -->
<%-- 											  <c:forEach var="item" items="${getlinedteList}" varStatus="num"> --%>
<%-- 											<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 										 </select> -->
<%-- <%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%> 
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Arm</label>
								</div>
								<div class="col-md-8">
								<select name="line_dte" id="line_dte" class="form-control" >
											${selectLine_dte}
	                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
                  							</c:forEach>
										 </select>
<%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
						<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label">Serviceable State</label>
									</div>
									<div class="col-md-8">
										<select name="s_state" id="s_state"
											class="form-control" onchange="serviceStChange();">
											<option value="0">--select--</option>
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

				<div class="card-footer" align="center" class="form-control">
					<a href="Asset_All_indiaurl" class="btn btn-success btn-sm">Clear</a> 
					<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
					<i class="fa fa-download"></i><input type="button" class="btn btn-primary btn-sm" value="Print" onclick="downloaddata()">
					<i class="fa fa-file-excel-o" id="btnExportData" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcelFile();"></i>
				</div>
			</div>
		</div>
	</div>

	<div id="viewpage" class="col-md-12">
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getSearch_All_India_Holding"
					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
					<thead>
						<tr>
							<th id ="13">Arm</th>
							<th id ="13">Command</th>
							<th id ="14">Corps</th>
							<th id ="15">Div</th>
							<th id ="16">Bde</th>
							<th id ="17">Unit Name</th>
							<th id ="18">SUS No</th>
							<th id="1">Category</th>
							<th id="4">Computing Assets Type</th>
							<th id="5">Make/Brand Name</th>
							<th id="6">Model Name</th>
							<th id="7">Model Number</th>
							<th id="8">Machine No</th>
							<th id ="11">Budget Head</th>
							<th id ="12">Budget Code</th>
							<th id ="19">Servicable State</th>
							
							
							<c:if test="${roleType == 'APP'}">
								<th>Select To Approve</th>
							</c:if>

						</tr>
					</thead>
<!-- 					<tbody> -->
<%-- 						<c:forEach var="item" items="${list}" varStatus="num"> --%>
<!-- 							<tr> -->
<%-- 								<td>${num.index+1}</td> --%>
<%-- 								<td>${item[0]}</td> --%>
<%-- 								<td>${item[1]}</td> --%>
<%-- 								<td>${item[2]}</td> --%>
<%-- 								<td>${item[3]}</td> --%>
<%-- 								<td>${item[4]}</td> --%>
<%-- 								<td>${item[5]}</td> --%>
<%-- 								<td>${item[6]}</td> --%>
<%-- 								<td>${item[7]}</td> --%>
<%-- 								<td>${item[8]}</td> --%>
<%-- 								<td>${item[9]}</td> --%>
<%-- 								<td>${item[10]}</td> --%>
<%-- 								<td>${item[11]}</td> --%>
<%-- 								<c:if test="${roleType == 'APP'}"> --%>
<%-- 									<td id="thAction1">${item[13]}${item[14]}</td> --%>
<%-- 								</c:if> --%>
<%-- 								<td>${item[15]}${item[16]}${item[17]}</td> --%>
<!-- 							</tr> -->
<%-- 						</c:forEach> --%>
<!-- 					</tbody> -->
				</table>
			</div>
		</div>
	</div>

<!-- 	<div id="viewpage2" class="container"> -->
<!-- 		<div class="col-md-12" id="getSearch_Letter" style="display: block;"> -->
<!-- 			<div class="watermarked" data-watermark="" id="divwatermark"> -->
<!-- 				<span id="ip"></span> -->
<!-- 				<table id="getSearch_All_India_Holding" -->
<!-- 					class="table no-margin table-striped  table-hover  table-bordered report_print_scroll"> -->
<!-- 					<thead> -->
<!-- 						<tr> -->
<!-- 							<th style="font-size: 12px; text-align: center;">Total</th> -->
<!-- 							<td -->
<%-- 								style="background-color: #f0f3f5; color: black; text-align: center; width: 50%;"><span>${listsize}</span></td> --%>
<!-- 						</tr> -->
<!-- 					</thead> -->

<!-- 				</table> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
</form:form>

<%-- <c:url value="SearchLine_directed_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="command1">
	 <input type="hidden" name="asset_type1" id="asset_type1" value="0"/>
     <input type="hidden" name="a_type1" id="a_type1" value="0"/>
     <input type="hidden" name="b_head1" id="b_head1" value="0"/>
     <input type="hidden" name="b_code1" id="b_code1" value="0"/>
     <input type="hidden" name="line_dte1" id="line_dte1" value="0"/>
</form:form> --%>

<c:url value="Download_All_India_Asset_Holding_Details" var="dwonloadUrl" />
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="computing_assets_dn">
  <input type="hidden" name="cont_comd_dn" id="cont_comd_dn"  value="0">
	   <input type="hidden" name="cont_corps_dn" id="cont_corps_dn" value="0">
	   <input type="hidden" name="cont_div_dn" id="cont_div_dn" value="0">
	   <input type="hidden" name="cont_bde_dn" id="cont_bde_dn" value="0">
	   <input type="hidden" name="cont_comd_tx" id="cont_comd_tx" >
	   <input type="hidden" name="cont_corps_tx" id="cont_corps_tx">
	   <input type="hidden" name="cont_div_tx" id="cont_div_tx">
	   <input type="hidden" name="cont_bde_tx" id="cont_bde_tx">
	   <input type="hidden" name="unit_name_dn" id="unit_name_dn">
	   <input type="hidden" name="sus_no_dn" id="sus_no_dn">
	   
		 <input type="hidden" name="asset_type_dn" id="asset_type_dn" value="0">
		 <input type="hidden" name="a_type_dn" id="a_type_dn" value="0"/>
	     <input type="hidden" name="b_head_dn" id="b_head_dn" value="0"/>
	     <input type="hidden" name="b_code_dn" id="b_code_dn" value="0"/>
	     <input type="hidden" name="line_dte_dn" id="line_dte_dn" value="0"/>
	      
	     <input type="hidden" name="asset_type_tx" id="asset_type_tx">
		 <input type="hidden" name="a_type_tx" id="a_type_tx"/>
	     <input type="hidden" name="b_head_tx" id="b_head_tx"/>
	     <input type="hidden" name="b_code_tx" id="b_code_tx"/>
	     <input type="hidden" name="line_dte_tx" id="line_dte_tx"/>
	     <input type="hidden" name="s_state1" id="s_state1"/>
</form:form>

<c:url value="Excel_All_India_Assets_Details_Export" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="cont_comd_ex">
	 <input type="hidden" name="cont_comd_ex" id="cont_comd_ex"  value="0">
	   <input type="hidden" name="cont_corps_ex" id="cont_corps_ex" value="0">
	   <input type="hidden" name="cont_div_ex" id="cont_div_ex" value="0">
	   <input type="hidden" name="cont_bde_ex" id="cont_bde_ex" value="0">
	   <input type="hidden" name="cont_comd_txt" id="cont_comd_txt" >
	   <input type="hidden" name="cont_corps_txt" id="cont_corps_txt">
	   <input type="hidden" name="cont_div_txt" id="cont_div_txt">
	   <input type="hidden" name="cont_bde_txt" id="cont_bde_txt">
	   <input type="hidden" name="unit_name_ex" id="unit_name_ex">
	   <input type="hidden" name="sus_no_ex" id="sus_no_ex">
	    <input type="hidden" name="parent_arm_ex" id="parent_arm_ex">
	   <input type="hidden" name="cadre_ex" id="cadre_ex">
	   
	   <input type="hidden" name="asset_type_dn" id="asset_type_dn" value="0">
	 <input type="hidden" name="a_type_dn" id="a_type_dn" value="0"/>
     <input type="hidden" name="b_head_dn" id="b_head_dn" value="0"/>
     <input type="hidden" name="b_code_dn" id="b_code_dn" value="0"/>
     <input type="hidden" name="line_dte_dn" id="line_dte_dn" value="0"/>
      <input type="hidden" name="s_state1" id="s_state1" value="0">
     
     
	   <input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 



<script type="text/javascript">

function getExcelFile() {	
	var cont_comd_txt = $("#cont_comd option:selected").text();
 	var cont_corps_txt=$("#cont_corps option:selected").text();
 	var cont_div_txt=$("#cont_div option:selected").text();
 	var cont_bde_txt=$("#cont_bde option:selected").text();
 	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
 	var parent_arm=$("#parent_arm").val();
	
 	
 	if(cont_comd_txt == "--Select--") {
		cont_comd_txt = "";
	}
	if(cont_corps_txt == "--Select--") {
		cont_corps_txt = "";		
	}
	if(cont_div_txt == "--Select--") {
		cont_div_txt = "";
	}
	if(cont_bde_txt == "--Select--") {
		cont_bde_txt = "";
	}
 	
 	$("#cont_comd_txt").val(cont_comd_txt);
	$("#cont_corps_txt").val(cont_corps_txt);
	$("#cont_div_txt").val(cont_div_txt);
	$("#cont_bde_txt").val(cont_bde_txt);
	
	$("#cont_comd_ex").val(cont_comd);
	$("#cont_corps_ex").val(cont_corps);
	$("#cont_div_ex").val(cont_div);
	$("#cont_bde_ex").val(cont_bde);
	$("#unit_name_ex").val(unit_name);
	$("#sus_no_ex").val(sus_no);
	$("#parent_arm_ex").val(parent_arm);
	$("#cadre_ex").val(0);
	var asset_type_tx=$("#asset_type option:selected").text();
	var a_type_tx=$("#a_type option:selected").text();
	var b_head_tx=$("#b_head option:selected").text();
	var b_code_tx=$("#b_code option:selected").text();
	
	$("#asset_type_dn").val($("#asset_type").val());
	$("#a_type_dn").val($("#a_type").val());
	$("#b_head_dn").val($("#b_head").val());
	$("#b_code_dn").val($("#b_code").val());
	$("#line_dte_dn").val($("#line_dte").val());
	$("#s_state1").val($("#s_state").val());
	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('ExcelForm').submit();
} 



function data(getSearch_All_India_Holding){
	jsondata = [];

	var table = $('#'+getSearch_All_India_Holding).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
	var orderType = order[0][1];
	
	
	var cont_comd=$("#cont_comd").val();
 	var cont_corps=$("#cont_corps").val();
 	var cont_div=$("#cont_div").val();
 	var cont_bde=$("#cont_bde").val();
 	var unit_name=$("#unit_name").val();
 	var sus_no=$("#sus_no").val();
	var asset_type=$("#asset_type").val() ;
	var a_type=$("#a_type").val() ;
	var b_head=$("#b_head").val() ;
	var b_code=$("#b_code").val() ;
	var line_dte=$("#line_dte").val() ;
	var s_state=$("#s_state").val() ;
	

	var s_total = 0;

 	
	$.post("getassetindiaholding?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,asset_type:asset_type,
		b_head:b_head,b_code:b_code,a_type:a_type,line_dte:line_dte,s_state:s_state},function(j) {
			
		for (var i = 0; i < j.length; i++) {
			if (j[i].s_state=='1') {
				j[i].s_state='Serviceable'
			}
			else if (j[i].s_state=='2') {
				j[i].s_state='Un-serviceable'
			}
			jsondata.push([j[i].arm_desc,j[i].command,j[i].corps,j[i].div,j[i].bde,j[i].unit_name,j[i].sus_no,j[i].asset_cat,j[i].assets_name,j[i].make_name,j[i].model_name,j[i].model_number,j[i].machine_number,j[i].b_head,j[i].budget_code,j[i].s_state,
			]);
			 s_total += j[i].total;
		}
		jsondata.push([
			"","","","","","","","","","","","","","","<strong>TOTAL</strong>",
			"<strong>"+s_total+"</strong>"]);
	});
	$.post("getassetindiaholdingCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,asset_type:asset_type,b_head:b_head,b_code:b_code,a_type:a_type,line_dte:line_dte,s_state:s_state},function(j) {
		FilteredRecords = j;
	});
}
</script>



<Script>
	$(document).ready(function() {
		
		var username =  $("#username").val();
		if(username != "hq"){
// 			$("#line_dte_hd").hide();
		}
		/* if('${getArm_code}' != '')
		{
			$("#line_dte").val("${getArm_code}");	
		} */
// 		alert('${getArm_code}')
		
		if('${roleAccess}' == 'Unit')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
			$("#sus_no").attr("disabled", true); 
			$("#unit_name").attr("disabled", true);
			
			$("#sus_no").val('${sus_no}');
			$("#unit_name").val('${unit_name}');
		}
		if('${roleSubAccess}' == 'Brigade')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			$("#cont_bde").attr("disabled", true);
		}
		if('${roleSubAccess}' == 'Division')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true); 
			$("#cont_div").attr("disabled", true); 
			if('${cont_div1}' != ""){
	   	    	getCONTBde('${cont_div1}');
	   	    }
		}
		if('${roleSubAccess}' == 'Corps')
		{
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			if('${cont_corps1}' != ""){
	   		 	getCONTDiv('${cont_corps1}');
	       	}
	   	    if('${cont_div1}' != ""){
	   	    	getCONTBde('${cont_div1}');
	   	    }
		}
		if('${roleSubAccess}' == 'Command')
		{
			$("#cont_comd").attr("disabled", true);
			if('${cont_comd1}' != ""){
		    	$("#cont_comd").val('${cont_comd1}');
		    	getCONTCorps('${cont_comd1}');
	    	}
	    	if('${cont_corps1}' != ""){
	    		 getCONTDiv('${cont_corps1}');
	    	}
		    if('${cont_div1}' != ""){
		    	getCONTBde('${cont_div1}');
		    }
		}
		
		if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter' || '${roleAccess}' == 'Line_dte')
		{
			if('${cont_comd1}' != ""){
		    	$("#cont_comd").val('${cont_comd1}');
		    	getCONTCorps('${cont_comd1}');
	    	}
	    	if('${cont_corps1}' != ""){
	    		 getCONTDiv('${cont_corps1}');
	    	}
		    if('${cont_div1}' != ""){
		    	getCONTBde('${cont_div1}');
		    }
		}
		
		var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	   	$('select#cont_comd').change(function() {
		   		var fcode = this.value;
		   		if(fcode == "0"){
		   			$("select#cont_corps").html(select);
		   			$("select#cont_div").html(select);
		   			$("select#cont_bde").html(select);
	   		}else{	
	   			$("select#cont_corps").html(select);
		   			$("select#cont_div").html(select);
		   			$("select#cont_bde").html(select);
		   			
		   			getCONTCorps(fcode);
		    	
		       		fcode += "00";	
		   			getCONTDiv(fcode);
		       	
		       		fcode += "000";	
		   			getCONTBde(fcode);
	   		}
		   	});
		   	$('select#cont_corps').change(function() {
		   	   	var fcode = this.value;
	   	   	if(fcode == "0"){
	   	   		$("select#cont_div").html(select);
	   	   		$("select#cont_bde").html(select);
		   	}else{
		   		$("select#cont_div").html(select);
	   	   		$("select#cont_bde").html(select);
		   	   		getCONTDiv(fcode);
		       		fcode += "000";	
		   			getCONTBde(fcode);
		   	}
		   	});
		   	$('select#cont_div').change(function() {
		   		var fcode = this.value;    	   	
		   		if(fcode == "0"){
		 		$("select#cont_bde").html(select)
		   	}else{
		   		$("select#cont_bde").html(select)
			   		getCONTBde(fcode);
		   	}
			});
		
	
		
		
		mockjax1('getSearch_All_India_Holding');
		table = dataTable('getSearch_All_India_Holding');
		$('#btn-reload').on('click', function(){
	    	table.ajax.reload();
	    });
		
		
		
		if ('${asset_type1}' != "0" && '${asset_type1}' != "") {
			$("select#asset_type").val('${asset_type1}');
		}
		
		$.ajaxSetup({
			async: false
		});
		fn_makeName()
		if ('${a_type1}' != "0" && '${a_type1}' != "") {
			$("select#a_type").val('${a_type1}');
		}
		
		if ('${b_head1}' != "0" && '${b_head1}' != "") {
			$("select#b_head").val('${b_head1}');
		}
	
		fn_B_Head()
		
		if ('${b_code1}' != "0" && '${b_code1}' != "") {
			$("select#b_code").val('${b_code1}');
		}
		
		if ('${line_dte1}' != "0" && '${line_dte1}' != "") {
			$("select#line_dte").val('${line_dte1}');
		}
		
	});
	
	
	
	function getCONTCorps(fcode){
	 	var fcode1 = fcode[0];
			$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
				if(j != ""){
					var length = j.length-1;
					var enc = j[length][0].substring(0,16);
					var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
					
					for ( var i = 0; i < length; i++) {
						if('${cont_corps1}' ==  dec(enc,j[i][0])){
							options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
						}else{
							options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
						}
					}	
					$("select#cont_corps").html(options);
				}
			});
	 } 
	 function getCONTDiv(fcode){
	 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
		   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		   		if(j != ""){
	 		   	var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if('${cont_div1}' ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}
			   		$("select#cont_div").html(options);
		   		}
			});
	 } 
		function getCONTBde(fcode){
			var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
			$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
				if(j != ""){
					var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#cont_bde").html(options);
				for ( var i = 0; i < length; i++) {
					if('${cont_bde1}' ==  dec(enc,j[i][0])){
						options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
						$("#cont_bname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				$("select#cont_bde").html(options);
				}
		});
	}
		
		
	function Search() {
		$("#asset_type1").val($("#asset_type").val());
		$("#a_type1").val($("#a_type").val());
		$("#b_head1").val($("#b_head").val());
		$("#b_code1").val($("#b_code").val());
		$("#line_dte1").val($("#line_dte").val());
		document.getElementById('searchForm').submit();
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

	function fn_makeName() {
		
		 var options = '<option   value="0">' + "--Select--" + '</option>';
			var assets_name = $("select#asset_type").val();
			$.post("getCategoryList?" + key + "=" + value, {
				categogry_id: assets_name
			}).done(function(j) {
				
				for(var i = 0; i < j.length; i++) {
					options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
				}
				
			}).fail(function(xhr, textStatus, errorThrown) {});
			$("select#a_type").html(options);
		}
	
	function downloaddata() {
		
		var cont_comd_tx = $("#cont_comd option:selected").text();
	 	var cont_corps_tx=$("#cont_corps option:selected").text();
	 	var cont_div_tx=$("#cont_div option:selected").text();
	 	var cont_bde_tx=$("#cont_bde option:selected").text();
	 	
	 	var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();
	 	
		$("#cont_comd_tx").val(cont_comd_tx);
		$("#cont_corps_tx").val(cont_corps_tx);
		$("#cont_div_tx").val(cont_div_tx);
		$("#cont_bde_tx").val(cont_bde_tx);
		
		var asset_type_tx=$("#asset_type option:selected").text();
		var a_type_tx=$("#a_type option:selected").text();
		var b_head_tx=$("#b_head option:selected").text();
		var b_code_tx=$("#b_code option:selected").text();
		var line_dte_tx=$("#line_dte option:selected").text();
		$("#asset_type_tx").val(asset_type_tx);
		$("#a_type_tx").val(a_type_tx);
		$("#b_head_tx").val(b_head_tx);
		$("#b_code_tx").val(b_code_tx);
		$("#line_dte_tx").val(line_dte_tx);
		
		$("#cont_comd_dn").val(cont_comd);
		$("#cont_corps_dn").val(cont_corps);
		$("#cont_div_dn").val(cont_div);
		$("#cont_bde_dn").val(cont_bde);
		$("#unit_name_dn").val(unit_name);
		$("#sus_no_dn").val(sus_no);

		$("#asset_type_dn").val($("#asset_type").val());
		$("#a_type_dn").val($("#a_type").val());
		$("#b_head_dn").val($("#b_head").val());
		$("#b_code_dn").val($("#b_code").val());
		$("#line_dte_dn").val($("#line_dte").val());
		$("#s_state1").val($("#s_state").val());
		
		document.getElementById('downloadForm').submit();
	}
</script>

<script>
$(function() {
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
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
			        	  alert("Please Enter Approved Unit Name");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
				     
				      	var unit_name = ui.item.value;			      	
						 $.post("getActiveSusNoFromUnitName?"+key+"="+value, {unit_name:unit_name}, function(j) {
			                
			         }).done(function(j) {
			        	var length = j.length-1;
			         	var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
				      	
				     }
			      /* select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			        getOrbatDetailsFromUnitName($(this).val());
			  	} 	 */     
			});
		}); 
	
	 // Source Sus No auto
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
			        	}
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
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			   	});
		     }
		});
	});
});













</script>
