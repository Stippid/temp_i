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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script src="js/excel/xlsx-0.7.7.core.min.js"></script>  
<script src="js/excel/xls-0.7.4.core.min.js"></script> 

<script src="js/exportExcelData/modernizr-2.8.3.js"></script>
<script src="js/exportExcelData/jquery-1.11.3.min.js"></script>
<script src="js/exportExcelData/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.lob.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.ext_ui.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_core.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.xml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.documents.core_openxml.js"></script>
<script type="text/javascript" src="js/exportExcelData/infragistics.excel_serialization_openxml.js"></script>

<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
#sampleContainer ol {
    padding: 0px 0px 0px 15px;
    margin: 0;
}

#sampleContainer input {
    margin: 10px 0;
}
#result {
    display: none;
    color: red;
}
        
#Report{
    width: 1000px;
    display: block;
    overflow: scroll;
}
#Report1{
    width: 1000px;
    display: block;
    overflow: scroll;
}
	.scrollit {
    
    width: 100%;
}
.scrollit table td input{
   border: 0;
   background: transparent;
   font-size: 12px;
   height: 20px;
}

table thead {
    font-size: 12px;
    background-color: #3f52b5;
    color: white;
}
.card-footer input[type="button"] {
     padding-left: 10px!important; 
}


</style>
<form:form name="importdata1" id="importdata1"  action="PeripheralImportData_Action?${_csrf.parameterName}=${_csrf.token}" method='POST' modelAttribute="PeripheralImportData_cmd" enctype="multipart/form-data">


	<div class="container" align="center">
		<div class="card">
		<div class="card-header"><h5><span id="lbladd"></span>Step 1 : Create Utility </h5></div><br/>
			<div class="card-footer" align="center">
			<input type="button" id="button"  name ="btnsubmit" class="btn btn-primary btn-sm" onclick="downloadutility();" value="Zip utility data/Unzip utility data" >
		            <!-- <input type="file" id="btnimport" name="btnimport" class="custom-file-input">  -->
		            </div>
 <br/>
		</div>



		</div>


<!-- <div class="container"> -->
	<div class="container" align="center">
		<div class="card">
		<div class="card-header"><h5><span id="lbladd"></span>Step 2 : Import Utility</h5></div>
			
			<div class="card-body card-block">
							               
					<div class="col-md-12">
					<div class="col-md-5">
					</div>
					 <div class="col-md-7">
						<div class="row form-group">
							<input type="file" id="excelfile" name="excelfile" /> <!-- <input
								type="button" id="viewfile" value="Show Table"
								/> -->
						</div>
</div>
<!-- <input type="button"> -->
					</div>

				</div>   
				
				<div class="scrollit" align="left" style="display: none;"
			id="importshow">
			<table id="Report" style="width: 100%; font-size: 12px;"
				class="table no-margin table-striped  table-hover  table-bordered">
			</table>
		</div>
		            <!-- <input type="file" id="btnimport" name="btnimport" class="custom-file-input">  -->
				<div class="card-footer" align="center">
				<input type="hidden"  id="countrow" name="countrow">
<!-- 				<input type="hidden"  id="countrow1" name="countrow1"> -->
				<a href="Peripheralimportdata_Url" class="btn btn-success btn-sm">Clear</a>
				<input type="submit" id="btnsubmit"  name ="btnsubmit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()" >
				<!-- <input type="button" id="Search" value="Search"
					class="btn btn-info btn-sm" onclick="f_Search()" >  -->
				
	          	</div> 
		</div>





		<div class="scrollit" align="left" style="display: none;"
			id="importshow">
			<table id="Report" style="width: 100%; font-size: 12px;"
				class="table no-margin table-striped  table-hover  table-bordered">
			</table>
		</div>


		</div>




<div class="container" id="getcategorySearch">

		<div id="divShow" style="display: block;"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="getcategorySearch"
				class="table no-margin table-striped  table-hover  table-bordered report_print" 
				width="100%">
				<thead>
				
				
			
					<tr>
						<th style="font-size: 15px; width: 10%;">Ser No</th>
						<th style="font-size: 15px">Peripheral Type</th>
						<th style="font-size: 15px">Make/Brand Name</th>
						<th style="font-size: 15px">Model Name</th>
						<th style="font-size: 15px">Warranty</th>
						<th style="font-size: 15px">Warranty Upto</th>
						<th style="font-size: 15px">Type of Peripheral HW</th>
						<th style="font-size: 15px">Model Type</th>
						<th style="font-size: 15px">Year of Proc</th>
						<th style="font-size: 15px">Year of Manufacturing</th>
						<th style="font-size: 15px">Remarks</th>
						<th style="font-size: 15px">Model Number</th>
						<th style="font-size: 15px">Machine No.</th>
						<th style="font-size: 15px">IS Networked</th>
						<th style="font-size: 15px">IP Address(For Networked Device)</th>
						<th style="font-size: 15px">No Of Ports</th>
						<th style="font-size: 15px">Network Features</th>
						<th style="font-size: 15px">Ethernet Port</th>
						<th style="font-size: 15px">Management Layer</th>
						<th style="font-size: 15px">Resolution</th>
						<th style="font-size: 15px">Display Size</th>
						<th style="font-size: 15px">Display Connector</th>
						<th style="font-size: 15px">Capacity(Lumens)</th>
						<th style="font-size: 15px">Hardware Interface</th>
						<th style="font-size: 15px">Print</th>
						<th style="font-size: 15px">Scan</th>
						<th style="font-size: 15px">Photography</th>
						<th style="font-size: 15px">Colour</th>
						<th style="font-size: 15px">Monochrome/Color</th>
						<th style="font-size: 15px">Max Paper Size</th>
						<th style="font-size: 15px">Connectivity Type</th>
						<th style="font-size: 15px">UPS Capacity</th>
						<th style="font-size: 15px">Display Type</th>
						<th style="font-size: 15px">Display Size</th>
						<th style="font-size: 15px">Display Connector</th>
						<th style="font-size: 15px">Serviceable State</th>
						<th style="font-size: 15px">UN-Serviceable State</th>
						<th style="font-size: 15px">Un-servicable from</th>
						<th style="font-size: 15px">Proc Cost</th>
						<th style="font-size: 15px">Proc Date</th>
						<th style="font-size: 15px">Budget Head</th>
						<th style="font-size: 15px">Budget Code</th>
						
						
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: center; width: 10%;">${num.index+1}</td>
							<td style="font-size: 15px;">${item[1]}</td>
							<td style="font-size: 15px;">${item[0]}</td>
							<td style="font-size: 15px;">${item[2]}</td>
							<td style="font-size: 15px;">${item[3]}</td>
							<td style="font-size: 15px;">${item[4]}</td>
							<td style="font-size: 15px;">${item[5]}</td>
							<td style="font-size: 15px;">${item[6]}</td>
							<td style="font-size: 15px;">${item[7]}</td>
							<td style="font-size: 15px;">${item[8]}</td>
							<td style="font-size: 15px;">${item[9]}</td>
							<td style="font-size: 15px;">${item[10]}</td>
							<td style="font-size: 15px;">${item[11]}</td>
							<td style="font-size: 15px;">${item[12]}</td>
							<td style="font-size: 15px;">${item[13]}</td>
							<td style="font-size: 15px;">${item[14]}</td>
							<td style="font-size: 15px;">${item[15]}</td>
							<td style="font-size: 15px;">${item[16]}</td>
							<td style="font-size: 15px;">${item[17]}</td>
							<td style="font-size: 15px;">${item[18]}</td>
							<td style="font-size: 15px;">${item[19]}</td>
							<td style="font-size: 15px;">${item[20]}</td>
							<td style="font-size: 15px;">${item[21]}</td>
							<td style="font-size: 15px;">${item[22]}</td>
							<td style="font-size: 15px;">${item[23]}</td>
							<td style="font-size: 15px;">${item[24]}</td>
							<td style="font-size: 15px;">${item[25]}</td>
							<td style="font-size: 15px;">${item[26]}</td>
							<td style="font-size: 15px;">${item[27]}</td>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
			
			
			
		</div>
	</div>

</form:form>

<script>


$(document).ready(function() {
	$("#marks").hide();
	if('${msg}' != "") {
// 		alert('${msg}')
	} 
	
	if ('${list.size()}' == "") {
		$("div#getcategorySearch").hide();
	}
	
});

// 	function f_Search() {

// 		month_validation1 = $("#month_validation").val();
// 		sus_no1 = $("#sus_no").val();

// 		$("#month_validation1").val(month_validation1);
// 		$("#sus_no1").val(sus_no1);

// 		document.getElementById('searchForm').submit();

// 	}
	function downloadutility() {

		
 		$.post("getDownloadUtility?" + key + "=" + value, {
 		}).done(function(j) {
 			
 			if(j==1)
 			{
 				alert("Successfully Created Utility(zip file) on this Location srv/IT/document.zip Please unzip file and double click on 'IT_Assets_Peripheral.html' .");
 			 	
 			}
 		});
		
	}
	
	
	function validate() {

		if ($("#excelfile").val() == "") {
			alert("Please Upload Excel File.");
			$("#excelfile").focus();
			return false;
		}
	}

	//function ExportToTable() {
	$(function() {
		$("input#excelfile").on("change",function() {
							$("#Report").find("tbody").empty();
// 							$("#Report1").find("tbody").empty();

							var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
							var regex_low = $("#excelfile").val().toLowerCase();
							if (regex.test(regex_low)) {
						
								var xlsxflag = false; /*Flag for checking whether excel is .xls format or .xlsx format*/
								if ($("#excelfile").val().toLowerCase()
										.indexOf(".xlsx|.xls") > 0) {
									xlsxflag = true;
								}

								/*Checks whether the browser supports HTML5*/
								if (typeof (FileReader) != "undefined") {

									var reader = new FileReader();

									reader.onload = function(e) {

										var data = e.target.result;
										
										/*Converts the excel data in to object*/
										if (xlsxflag) {
											var workbook = XLSX.read(data, 
											{
												type : 'binary'
											});
										}
										else {
											var workbook = XLS.read(data, {
												type : 'binary'
											});
										}
										/*Gets all the sheetnames of excel in to a variable*/
										var sheet_name_list = workbook.SheetNames;

										var cnt = 0; /*This is used for restricting the script to consider only first sheet of excel*/

										sheet_name_list
												.forEach(function(y) { /*Iterate through all sheets*/
													/*Convert the cell value to Json*/

													if (xlsxflag) {
														var exceljson = XLSX.utils.sheet_to_json(workbook.Sheets[y]);

													} 
													else {
														var exceljson = XLS.utils.sheet_to_row_object_array(workbook.Sheets[y]);

													}

// 													if (exceljson.length > 0
// 															&& cnt == 1) {
// 														BindTable(exceljson,
// 																'#Report1');
// 														$("#countrow1")
// 																.val(
// 																		exceljson.length);
// 														//	computeTableColumnTotal("#Report1", 23);
// 													}
													if (exceljson.length > 0 && cnt == 0) {
														BindTable(exceljson,'#Report');
														$("#countrow").val(exceljson.length);
// 														computeTableColumnTotal("#Report", 28);

														cnt++;
													}

												});

										$("div#importshow").hide();
// 										$("div#importshow1").show();

									}
									if (xlsxflag) {/*If excel file is .xlsx extension than creates a Array Buffer from excel*/
										reader
												.readAsArrayBuffer($("#excelfile")[0].files[0]);

									} else {
										reader
												.readAsBinaryString($("#excelfile")[0].files[0]);

									}
								} else {
									alert("Sorry! Your browser does not support HTML5!");
								}
							} else {
								alert("Please upload a valid Excel file!");
							}

						})

	});

	
	function BindTable(jsondata, tableid) {/*Function used to convert the JSON array to Html Table*/
		var columns = BindTableHeader(jsondata, tableid); /*Gets all the column headings of Excel*/

		for (var i = 0; i < jsondata.length; i++) {

			var td = "<tbody><tr>";
			for (var colIndex = 0; colIndex < columns.length; colIndex++) {
				var cellValue = jsondata[i][columns[colIndex]];

				var TH1 = columns[colIndex].replace(/ /g, "_");
				var TH = TH1.toLowerCase();

				td += "<td style='text-align:center;width: 10%'><input name='"+TH+"_"+i+"' id='"+TH+"_"+i+"' value='"+cellValue+"' style='text-align:center;' readonly='readonly' /></td>";

				if (cellValue == null)
					cellValue = "";

			}

// 			if (i == jsondata.length - 1) {

// 				td += "</tr>";
// 				if (tableid == "#Report") {
// 					td += '<tr><td colspan=6 align="center" >AVG </td> <td id="endurance_run_avg" style="text-align:center;width: 10%"></td> <td id="day_fg_avg" style="text-align:center;width: 10%"></td><td id="ni_fg_avg"  style="text-align:center;width: 10%">  </td><td id="tsoet_avg" style="text-align:center;width: 10%">  </td><td id="quiz_online_avg"  style="text-align:center;width: 10%">  </td><td id="comb_first_aid_and_cas_evac_avg"  style="text-align:center;width: 10%">  </td><td id="new_genr_wpn_and_eqpt_avg"  style="text-align:center;width: 10%">  </td> <td id="dem_avg"  style="text-align:center;width: 10%">  </td><td id="rt_procedure_avg"  style="text-align:center;width: 10%">  </td><td id="mor_shoot_avg"  style="text-align:center;width: 10%">  </td><td id="swimming_avg"  style="text-align:center;width: 10%">  </td><td id="navig_avg"  style="text-align:center;width: 10%">  </td><td id="driving_avg"  style="text-align:center;width: 10%">  </td><td id="any_spl_skills_avg"  style="text-align:center;width: 10%">  </td><td colspan=3 align="center" >----- </td> <td id="weighing_in_avg"  style="text-align:center;width: 10%">  </td>	</tr>'
// 				}

// 			}

			td += "</tbody>";
			$(tableid).append(td);

		}

		//alert( columns.length); length of column
		//alert(jsondata.length); length of total rows
// 		if (tableid == "#Report1") {
// 			avg_sum(jsondata, tableid);
// 		}

	}
	function BindTableHeader(jsondata, tableid) {/*Function used to get all column names from JSON and bind the html table header*/
		var columnSet = [];
		var headerTr$ = $('<tr/>');
		for (var i = 0; i < jsondata.length; i++) {
			var rowHash = jsondata[i];
			for ( var key in rowHash) {
				if (rowHash.hasOwnProperty(key)) {
					if ($.inArray(key, columnSet) == -1) {/*Adding each unique column names to a variable array*/
						columnSet.push(key);
						//  alert(key + "key");
						headerTr$.append($('<th/>').html(key));
					}
				}
			}
		}
		$(tableid).append(headerTr$);
		return columnSet;
	}

	var debugScript = true;

	function computeTableColumnTotal(tableId, colNumber) {
		// find the table with id attribute tableId
		// return the total of the numerical elements in column colNumber
		// skip the top row (headers) and bottom row (where the total will go)

		var result = 0;

		alert("hiii");
		
		alert(tableId)
		var tableElem = window.document.getElementById(tableId);
		alert(tableElem)
		var tableBody = tableElem.getElementsByTagName("tbody").item(0);
		var i;
		var howManyRows = tableBody.rows.length;
		for (i = 1; i < (howManyRows - 1); i++) // skip first and last row (hence i=1, and howManyRows-1)
		{
			var thisTrElem = tableBody.rows[i];
			var thisTdElem = thisTrElem.cells[colNumber];
			var thisTextNode = thisTdElem.childNodes.item(0);
			if (debugScript) {
				window.alert("text is " + thisTextNode.data);
			} // end if

			// try to convert text to numeric
			var thisNumber = parseFloat(thisTextNode.data);
			// if you didn't get back the value NaN (i.e. not a number), add into result
			if (!isNaN(thisNumber))
				result += thisNumber;
		} // end for

	}

// 	function avg_sum(jsondata, tableid) {

// 		$("#marks").show();
// 		var endurance_run_sum = 0;
// 		var day_fg_sum = 0;
// 		var ni_fg_sum = 0;
// 		var tsoet_sum = 0;
// 		var quiz_online_sum = 0;
// 		var comb_first_aid_and_cas_evac_sum = 0;
// 		var new_genr_wpn_and_eqpt_sum = 0;
// 		var dem_sum = 0;
// 		var rt_procedure_sum = 0;
// 		var mor_shoot_sum = 0;
// 		var swimming_sum = 0;
// 		var navig_sum = 0;
// 		var driving_sum = 0;
// 		var any_spl_skills_sum = 0;
// 		var weighing_in_sum = 0;
// 		var table2_avg_sum = 0;
// 		var table1_sum = 0;
// 		var table12_total = 0;

// 		var endurance_run_avg = 0;
// 		var day_fg_avg = 0;
// 		var ni_fg_avg = 0;
// 		var tsoet_avg = 0;
// 		var quiz_online_avg = 0;
// 		var comb_first_aid_and_cas_evac_avg = 0;
// 		var new_genr_wpn_and_eqpt_avg = 0;
// 		var dem_avg = 0;
// 		var rt_procedure_avg = 0;
// 		var mor_shoot_avg = 0;
// 		var swimming_avg = 0;
// 		var navig_avg = 0;
// 		var driving_avg = 0;
// 		var any_spl_skills_avg = 0;
// 		var weighing_in_avg = 0;

// 		for (var colIndex = 0; colIndex < jsondata.length; colIndex++) {

// 			endurance_run_sum += parseInt($("#endurance_run_" + colIndex).val());
// 			day_fg_sum += parseInt($("#day_fg_" + colIndex).val());
// 			ni_fg_sum += parseInt($("#ni_fg_" + colIndex).val());
// 			tsoet_sum += parseInt($("#tsoet_" + colIndex).val());
// 			quiz_online_sum += parseInt($("#quiz_online_" + colIndex).val());
// 			comb_first_aid_and_cas_evac_sum += parseInt($(
// 					"#comb_first_aid_and_cas_evac_" + colIndex).val());
// 			new_genr_wpn_and_eqpt_sum += parseInt($(
// 					"#new_genr_wpn_and_eqpt_" + colIndex).val());
// 			dem_sum += parseInt($("#dem_" + colIndex).val());
// 			rt_procedure_sum += parseInt($("#rt_procedure_" + colIndex).val());
// 			mor_shoot_sum += parseInt($("#mor_shoot_" + colIndex).val());
// 			swimming_sum += parseInt($("#swimming_" + colIndex).val());
// 			navig_sum += parseInt($("#navig_" + colIndex).val());
// 			driving_sum += parseInt($("#driving_" + colIndex).val());
// 			any_spl_skills_sum += parseInt($("#any_spl_skills_" + colIndex)
// 					.val());
// 			weighing_in_sum += parseInt($("#weighing_in_" + colIndex).val());

// 		}
// 		endurance_run_avg = endurance_run_sum / jsondata.length;
// 		day_fg_avg = day_fg_sum / jsondata.length;
// 		ni_fg_avg = ni_fg_sum / jsondata.length;
// 		tsoet_avg = tsoet_sum / jsondata.length;
// 		quiz_online_avg = quiz_online_sum / jsondata.length;
// 		comb_first_aid_and_cas_evac_avg = comb_first_aid_and_cas_evac_sum
// 				/ jsondata.length;
// 		new_genr_wpn_and_eqpt_avg = new_genr_wpn_and_eqpt_sum / jsondata.length;
// 		dem_avg = dem_sum / jsondata.length;
// 		rt_procedure_avg = rt_procedure_sum / jsondata.length;
// 		mor_shoot_avg = mor_shoot_sum / jsondata.length;
// 		swimming_avg = swimming_sum / jsondata.length;
// 		navig_avg = navig_sum / jsondata.length;
// 		driving_avg = driving_sum / jsondata.length;
// 		any_spl_skills_avg = any_spl_skills_sum / jsondata.length;
// 		weighing_in_avg = weighing_in_sum / jsondata.length;

// 		table1_sum = parseInt($("#camp_layout_0").val())
// 				+ parseInt($("#briefing_0").val())
// 				+ parseInt($("#snap_sit_0").val())
// 				+ parseInt($("#op_role_0").val())
// 				+ parseInt($("#debriefing_0").val())
// 				+ parseInt($("#tug_of_war_0").val())
// 				+ parseInt($("#social_aspects_0").val())
// 				+ parseInt($("#adm_task_0").val());

// 		table2_avg_sum = (endurance_run_avg + day_fg_avg + ni_fg_avg
// 				+ tsoet_avg + quiz_online_avg + comb_first_aid_and_cas_evac_avg
// 				+ new_genr_wpn_and_eqpt_avg + dem_avg + rt_procedure_avg
// 				+ mor_shoot_avg + swimming_avg + navig_avg + driving_avg
// 				+ any_spl_skills_avg + weighing_in_avg);

// 		table12_total = table1_sum + table2_avg_sum;

// 		document.getElementById("endurance_run_avg").innerHTML = endurance_run_avg;
// 		document.getElementById("day_fg_avg").innerHTML = day_fg_avg;
// 		document.getElementById("ni_fg_avg").innerHTML = ni_fg_avg;
// 		document.getElementById("tsoet_avg").innerHTML = tsoet_avg;
// 		document.getElementById("quiz_online_avg").innerHTML = quiz_online_avg;
// 		document.getElementById("comb_first_aid_and_cas_evac_avg").innerHTML = comb_first_aid_and_cas_evac_avg;
// 		document.getElementById("new_genr_wpn_and_eqpt_avg").innerHTML = new_genr_wpn_and_eqpt_avg;
// 		document.getElementById("dem_avg").innerHTML = dem_avg;
// 		document.getElementById("rt_procedure_avg").innerHTML = rt_procedure_avg;
// 		document.getElementById("mor_shoot_avg").innerHTML = mor_shoot_avg;
// 		document.getElementById("swimming_avg").innerHTML = swimming_avg;
// 		document.getElementById("navig_avg").innerHTML = navig_avg;
// 		document.getElementById("driving_avg").innerHTML = driving_avg;
// 		document.getElementById("any_spl_skills_avg").innerHTML = any_spl_skills_avg;
// 		document.getElementById("weighing_in_avg").innerHTML = weighing_in_avg;

// 		document.getElementById("table12_total").innerHTML = table12_total;

// 	}
</script>
</body>
</html>