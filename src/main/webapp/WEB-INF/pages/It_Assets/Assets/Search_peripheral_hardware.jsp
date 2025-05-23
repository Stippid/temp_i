<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> --%>
<%-- <spring:htmlEscape defaultHtmlEscape="true" /> --%>
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!-- <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> -->
<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<!-- <link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link> -->
<!-- <script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->
<!-- <script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script> -->
<!-- <link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"> -->
<!-- <script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script> -->
<!-- <script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script> -->
<!-- <script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script> -->
<!-- <script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script> -->
<!-- <link rel="stylesheet" href="js/cue/cueWatermark.css"> -->
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<!-- <script src="js/Calender/jquery-ui.js"></script> -->
<!-- <script src="js/Calender/datePicketValidation.js"></script> -->

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">


  
      <div class="" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>SEARCH/UPDATE PERIPHERAL ASSET HARDWARE DETAILS</h5>
		             
		      </div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Peripheral Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Year Of Manufacturing</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year_of_manufacturing" name="year_of_manufacturing" class="form-control autocomplete" maxlength="4" onkeypress="return isNumber(event)" onkeyup="validateYear(this);" onblur="validateYearLn(this);" autocomplete="off"></input>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-3"> -->
<!-- 									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Machine Make</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 										<input type="text" id="machine_make" name="machine_make" class="form-control autocomplete" autocomplete="off"/> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Machine No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="machine_no" name="machine_no" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Model Number</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="model_no" name="model_no" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
					</div>
					
					<div class="col-md-12" style="display: none">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Unit Name</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Unit Sus No</label>
								</div>
								<div class="col-md-8">
										<input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>
						
						
					</div>
					<div class="col-md-12">
						
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>From Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="from_date" id="from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
<!-- 										<input type="date" id="from_date" name="from_date" class="form-control autocomplete" autocomplete="off"/> -->
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>To Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="to_date" id="to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
<!-- 										<input type="date" id="to_date" name="to_date" class="form-control autocomplete" autocomplete="off"/> -->
								</div>

							</div>
						</div>
						
					</div>
					<div class="col-md-12">
						

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
									<option value="0" >Pending</option>
									<option value="1" >Approved</option>
									<option value="3" >Rejected</option>
									</select>
								</div>

							</div>
						</div>
					</div>
					
					 
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_PeripheralHardwareUrl"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" onclick="Search();" />            
<!-- 		           <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Whole Peripheral Data" onclick="getExcel();" />             -->
              </div>
          </div>
          
          <div class="" id="divPrint" >				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
				    <input type="hidden" id="CheckVal" name="CheckVal">
				   <c:if test="${roleType == 'APP'  && status1 == '0'}">
				   <b><input type=checkbox id='nSelAll' name='tregn'
						onclick='setselectall();'>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
							</c:if>	
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
<!-- 			                         <th>Ser No</th>                                                                               -->
                                     <th id="2">Peripheral Type</th>
                                     <th id="3">Type of HW</th>
                                     <th id="4">Year Of Proc</th>
                                     <th id="5">Year Of Manufacturing</th>
<!--                                      <th>Proc Cost</th> -->
<!--                                      <th>Machine Make</th> -->
                                     <th id="8">Machine No</th>
                                     <th id="9">Model Number</th>
                                     <th id="10">Remarks</th>
                                      <c:if test="${roleType == 'APP'  && status1 == '0'}">
										<th>Select To Approve</th>
										</c:if>
                                     <th data-orderable="false" >Action</th>
		                          </tr>                                                        
		                      </thead> 
<!-- 		                      <tbody> -->
<%-- 		                           <c:forEach var="item" items="${list}" varStatus="num" > --%>
<!-- 							          <tr> -->
<%-- 										<td >${num.index+1}</td> --%>
<%--                                         <td >${item[0]}</td> --%>
<%--                                         <td >${item[1]}</td> --%>
<%--                                         <td >${item[2]}</td> --%>
<%--                                         <td >${item[3]}</td> --%>
<%-- <%--                                         <td >${item[4]}</td> --%> 
<%-- <%--                                         <td >${item[5]}</td> --%> 
<%--                                         <td >${item[6]}</td> --%>
<%--                                         <td >${item[7]}</td> --%>
<%--                                         <td >${item[8]}</td> --%>
<%--                                         <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<%--                                         <td id="thAction1">${item[10]}${item[11]}</td> --%>
<%--                                         </c:if> --%>
<%-- <%--                                         <td >${item[9]}</td> --%> 
<%--                                         <td >${item[12]}${item[13]}${item[14]}</td> --%>
<!-- 							          </tr> -->
<%-- 							       </c:forEach> --%>
<!-- 		                     </tbody> -->
<%-- 		                     <c:if test="${list.size()==0}"> --%>
<!-- 						<tr> -->
<!-- 							<td style="font-size: 15px; text-align: center; color: red;" -->
<!-- 								colspan="2">Data Not Available</td> -->
<!-- 						</tr> -->
<%-- 					</c:if> --%>
		                 </table>
		                <c:if test="${roleType == 'APP'  && status1 == '0'}">
		                 <input type="button" class="btn btn-success btn-sm" value="Approve"
							onclick="return setApproveStatus();">
							<input type="button" class="btn btn-success btn-sm" value="Reject"
							onclick="return setRejectStatus();">
							</c:if>
		            </div>	          
	        </div> 
      </div>
  


<c:url value="Search_PeripheralUrl_12" var="Search_PeripheralUrl_12" />
<form:form action="${Search_PeripheralUrl_12}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
     <input type="hidden" name="machine_make1" id="machine_make1" value=""/>
     <input type="hidden" name="machine_no1" id="machine_no1" value=""/>
     <input type="hidden" name="model_no1" id="model_no1" value=""/>   
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value=""/>
     <input type="hidden" name="unit_name1" id="unit_name1" value=""/>
     <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
     
</form:form>

 <c:url value="peripheralhardwareEditUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="Delete_Peripherals" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	 <c:url value="View_Peripherals" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form> 
<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
 
<!-- for Functions -->
<script type="text/javascript">
function data(BloodReport){
        jsondata = [];

        var table = $('#'+BloodReport).DataTable();
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
         var model_no=$("#model_no").val() ;
         var from_date=$("#from_date").val() ;
         var to_date=$("#to_date").val() ;
         var status=$("#status").val() ;

         
        $.post("getFilteredPeripheral12?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
        	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
                machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
                to_date:to_date,status:status},function(j) {
                        
                for (var i = 0; i < j.length; i++) {
                	var data =[j[i].assets_name,j[i].type_of_hw,j[i].year_of_proc,j[i].year_of_manufacturing,j[i].machine_no,
                        j[i].model_no,j[i].remarks,j[i].action ];
                        jsondata.push(data);
//                         <c:if test="${roleType == 'APP'  && status1 == '0'}">
//                         jsondata.push(j[i].chekboxaction);
//                         </c:if>
//                         jsondata.push(j[i].action]);
                }
                
//                 $("#nSelAll").prop('checked', false);
// 				$('#tregn').text("0");
// 				$('#tregnn').text(j.length);
        });
        $.post("getTotalPeripheralCount12?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
            machine_make:machine_make,machine_no:machine_no,model_no:model_no,from_date:from_date,
            to_date:to_date,status:status},function(j) {
                FilteredRecords = j;
        });
}
</script>






<script>
function getExcel() {
	

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();

} 
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id){
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
} 
 
 function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}
 
function Search(){
	


	$("#machine_make1").val($("#machine_make").val()); 
	$("#machine_no1").val($("#machine_no").val());   
	$("#model_no1").val($("#model_no").val());   
	$("#year_of_manufacturing1").val($("#year_of_manufacturing").val()); 
	$("#unit_sus_no1").val($("#unit_sus_no").val());  
	$("#unit_name1").val($("#unit_name").val()); 
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());  
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val($("#status").val());
	$("#searchForm").submit();
}


</script>


<script>
function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
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



function setApproveStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
// 			isvalidData();
		}); 
	}	
}

function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_PeripheralAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
// 			isvalidData();
		}); 
	}	
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

</script>

<!-- for On Load Methods -->
<script> 
$(document).ready(function() {
	
	
	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
	
	datepicketDate('from_date');
	datepicketDate('to_date');
	if('${status1}' != "")
	{
	$("#status").val("${status1}");
	}
	if ("${assets_type1}" !=""){
		$("#assets_type").val("${assets_type1}");
	} 
	if ("${year_of_manufacturing1}" !=""){
		$("#year_of_manufacturing").val("${year_of_manufacturing1}");
	} 
	if ("${machine_make1}" !=""){
		$("input#machine_make").val("${machine_make1}");
	} 
	if ("${machine_no1}" !=""){
		$("#machine_no").val("${machine_no1}");
	} 
	if ("${model_no1}" !=""){
		$("#model_no").val("${model_no1}");
	} 
	if ("${from_date1}" !=""){
		$("#from_date").val("${from_date1}");
	} 
	if ("${to_date1}" !=""){
		$("#to_date").val("${to_date1}");
	} 
	

});	
</script>