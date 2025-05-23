
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
		             <h5>SEARCH/UPDATE COMPUTING ASSET HARDWARE DETAILS</h5>
		             
		      </div>
			<div class="card-body card-block">
				<div class="row">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label">Computing Assets Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--select--</option>
										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Budget Head</label>
								</div>
								<div class="col-md-8">
								<select name="b_head" id="b_head" class="form-control">
											<option value="0">--Select--</option>
											 <c:forEach var="item" items="${getBudgetHeadList}" varStatus="num">
											<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										 </select>
<%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%>
								</div>
							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;"> </strong>Proc Cost</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="b_cost" name="b_cost"   class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>
					
					</div>	
					
					
					 
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_HardwareUrl" class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm" id="btn-reload" value="Search"  />            
<!-- 		          <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Whole Computing Data" onclick="getExcel1();" />             -->
              </div>
          </div>
          
          <div class="" id="divPrint" >				  
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
				   <input type="hidden" id="CheckVal" name="CheckVal">
<%-- 				   <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<!-- 				   <b><input type=checkbox id='nSelAll' name='tregn' -->
<!-- 						onclick='setselectall();'>Select all [<span id="tregn"></span><span -->
<%-- 							id='nTotRow1'>/</span><span id="tregn"> ${list.size()}</span>]</b>  --%>
<%-- 							</c:if>	 --%>
							
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
			                         <th>Computing Assets Type</th>
                                     <th>Fund Head</th>
                                     <th>Cost</th>
                                     <th>Action</th>
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
<%--                                         <td >${item[4]}</td> --%>
<%--                                         <td >${item[5]}</td> --%>
<%--                                         <td >${item[6]}</td> --%>
<%--                                         <td >${item[7]}</td> --%>
<%--                                         <td >${item[8]}</td> --%>
<%--                                         <td >${item[9]}</td> --%>
<%--                                         <td >${item[10]}</td> --%>
<%-- 										<td >${item[11]}</td> --%>
										
<%--                                         <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<%--                                         <td id="thAction1">${item[13]}${item[14]}</td> --%>
<%--                                         </c:if> --%>
<%--                                         <td >${item[15]}${item[16]}${item[17]}</td> --%>
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
<%-- 		                  <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<!-- 		                 <input type="button" class="btn btn-success btn-sm" value="Approve" -->
<!-- 							onclick="return setApproveStatus();"> -->
<!-- 							<input type="button" class="btn btn-success btn-sm" value="Reject" -->
<!-- 							onclick="return setRejectStatus();"> -->
<%-- 							</c:if> --%>
		            </div>	          
	        </div> 
      </div>
  

<%-- <c:url value="Search_Computing_AssetsUrl_1" var="Search_Computing_AssetsUrl_1" /> --%>
<%-- <form:form action="${Search_Computing_AssetsUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="computing_assets1"> --%>
<!--      <input type="hidden" name="computing_assets1" id="computing_assets1" value="0"/> -->
<!--      <input type="hidden" name="model_number1" id="model_number1" value=""/> -->
<!--      <input type="hidden" name="machine_number1" id="machine_number1" value=""/> -->
<!--      <input type="hidden" name="ram_capi1" id="ram_capi1" value="0"/> -->
<!--      <input type="hidden" name="hdd_capi1" id="hdd_capi1" value="0"/> -->
<!--      <input type="hidden" name="operating_system1" id="operating_system1" value="0"/> -->
<!--      <input type="hidden" name="unit_sus_no1" id="unit_sus_no1" value=""/> -->
<!--      <input type="hidden" name="unit_name1" id="unit_name1" value=""/> -->
<!--       <input type="hidden" name="from_date1" id="from_date1" value=""/> -->
<!--      <input type="hidden" name="to_date1" id="to_date1" value=""/> -->
<!--      <input type="hidden" name="assets_type1" id="assets_type1" value="0"/> -->
<!--       <input type="hidden" name="status1" id="status1" value="0"/> -->
    
<%-- </form:form> --%>

 <c:url value="hardwareEditUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="HardwareDelete" var="HardwareDelete" />
	<form:form action="${HardwareDelete}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
	</form:form>
	
	<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
</form:form> 
<c:url value="demo_report1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="comd2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
</form:form>

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
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
// 	var orderColunm = $(table.column(order[0][0]).header()).attr('id');
// 	var orderType = order[0][1];
	

	
	var assets_type=$("#assets_type").val() ;

 	var b_head=$("#b_head").val() ;
 
 	//var ram_slot_qty=$("#ram_slot_qty").val();
 
 	var b_cost=$("#b_cost").val() ;


	$.post("getFilteredhardware?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,assets_type:assets_type,b_head:b_head,b_cost:b_cost},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].assets_name,j[i].b_head,j[i].b_cost,j[i].action]);
		}
	});
	$.post("gethardwareTotalCount?"+key+"="+value,{Search:Search,assets_type:assets_type,b_head:b_head,b_cost:b_cost},function(j) {
		FilteredRecords = j;
	});
	
}
</script> 
 
<!-- for Functions -->
<script>
function getExcel1() {
	

	document.getElementById('typeReport2').value = 'excelL';
	document.getElementById('search1').submit();

} 
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
 
 function editData(id){
	 //alert(id);
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
}
 
 function viewData(id){
	 $("#viewid").val(id);
	document.getElementById('viewForm').submit();
}
 function Search(){
		
//	 	if($("#asset_type").val() == "0") {
//	 		alert("Please Select Assets Type");
//	 		$("#asset_type").focus();
//	 		return false;
//	 	}
			

		$("#computing_assets1").val($("#asset_type").val());
		$("#model_number1").val($("#model_number").val());
		$("#machine_number1").val($("#machine_number").val());
		$("#ram_capi1").val($("#ram_capi").val());
		$("#hdd_capi1").val($("#hdd_capi").val());
		$("#operating_system1").val($("#operating_system").val());
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

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
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

			$.post("Approve_ComputingAssetsData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
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
// 	datepicketDate('from_date');
// 	datepicketDate('to_date');
// 	if('${status1}' != "")
// 	{
// 	$("#status").val("${status1}");
// 	}
// 	if('${assets_type1}' != "")
// 	{
// 	$("#assets_type").val("${assets_type1}");
// 	}
// if('${model_number1}' != "")
// {
// $("input#model_number").val("${model_number1}");
// }
// if('${machine_number1}' != "")
// {
// $("input#machine_number").val("${machine_number1}");
// }
// if('${ram_capi1}' != "")
// {
// $("select#ram_capi").val("${ram_capi1}");
// }
// if('${hdd_capi1}' != "")
// {
// $("select#hdd_capi").val("${hdd_capi1}");
// }
// if('${operating_system1}' != "")
// {
// $("select#operating_system").val("${operating_system1}");
// }
// if('${from_date1}' != "")
// {
// $("input#from_date").val("${from_date1}");
// }
// if('${to_date1}' != "")
// {
// $("input#to_date").val("${to_date1}");
// }
		
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});	
</script>