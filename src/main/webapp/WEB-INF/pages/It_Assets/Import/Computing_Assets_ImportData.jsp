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
<form:form name="importdata1" id="importdata1"  action="ComputingImportData_Action?${_csrf.parameterName}=${_csrf.token}" method='POST' modelAttribute="ComputingImportData_cmd" enctype="multipart/form-data">


	<div class="container" align="center">
		<div class="card">
		<div class="card-header"><h5><span id="lbladd"></span>Step 1 : Create Utility </h5></div><br/>
			<div class="card-footer" align="center">
			<input type="button" id="button"  name ="btnsubmit" class="btn btn-primary btn-sm" onclick="downloadutility();" value="Zip utility data/Unzip utility data" >
		            <!-- <input type="file" id="btnimport" name="btnimport" class="custom-file-input">  -->
		            </div>
 <br/>
		</div>


<!--  -->



		<div class="scrollit" align="left" style="display: none;"
			id="importshow">
			<table id="Report" style="width: 100%; font-size: 12px;"
				class="table no-margin table-striped  table-hover  table-bordered">
			</table>
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
							<input type="file" id="excelfile" name="excelfile" /> 
						</div>
</div>
<!-- <input type="button"> -->
					</div>

				</div>   
		            
				<div class="card-footer" align="center">
				<input type="hidden"  id="countrow" name="countrow">

				<a href="Computingimportdata_Url" class="btn btn-success btn-sm">Clear</a>
				<input type="submit" id="btnsubmit"  name ="btnsubmit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()" >
				
				
	          	</div> 
		</div>


<!--  -->



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
						<th style="font-size: 15px">Computing Assets Type</th>
						<th style="font-size: 15px">Make/Brand Name</th>
						<th style="font-size: 15px">Model Name</th>
						<th style="font-size: 15px">Processor Type</th>
						<th style="font-size: 15px">RAM Capacity</th>
						<th style="font-size: 15px">HDD Capacity</th>
						<th style="font-size: 15px">Operating System</th>
						<th style="font-size: 15px">Office</th>
						<th style="font-size: 15px">AntiVirus Installed</th>
						<th style="font-size: 15px">AntiVirus</th>
						<th style="font-size: 15px">OS/Firmware Activation and subsequent Patch Updation Mechanism</th>
						<th style="font-size: 15px">Dply Envt as Applicable</th>
						<th style="font-size: 15px">RAM Slot Quantity</th>
						<th style="font-size: 15px">CD Drive</th>
						<th style="font-size: 15px">Warranty</th>
						<th style="font-size: 15px">Warranty Upto</th>
						<th style="font-size: 15px">Model Number</th>
						<th style="font-size: 15px">Machine No.</th>
						<th style="font-size: 15px">MAC Address</th>
						<th style="font-size: 15px">IP Address</th>
						<th style="font-size: 15px">Serviceable State</th>
						<th style="font-size: 15px">Un-serviceable State</th>
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
	} 
	
	if ('${list.size()}' == "") {
		$("div#getcategorySearch").hide();
	}
	
});


	function downloadutility() {

		
 		$.post("getDownloadUtility?" + key + "=" + value, {
 		}).done(function(j) {
 			
 			if(j==1)
 			{
 				alert("Successfully Created Utility(zip file) on this Location srv/IT/document.zip Please unzip file and double click on 'IT_Assets_Computing.html' .");
 			 	
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

	$(function() {
		$("input#excelfile").on("change",function() {
							$("#Report").find("tbody").empty();

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

													if (exceljson.length > 0 && cnt == 0) {
														BindTable(exceljson,'#Report');
														$("#countrow").val(exceljson.length);
														cnt++;
													}

												});

										$("div#importshow").hide();

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


			td += "</tbody>";
			$(tableid).append(td);

		}


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


</script>


























