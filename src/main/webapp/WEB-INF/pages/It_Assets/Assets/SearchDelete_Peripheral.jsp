<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/Calender/datePicketValidation.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
      <div class="" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>DELETE PERIPHERAL ASSETS</h5>
		      </div>
			<div class="card-body card-block">
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
											<c:if test="${item.id == section1}">selected</c:if>>${item.section}</option>
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
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Peripheral Type</label>
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
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Year Of Manufacturing</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year_of_manufacturing" name="year_of_manufacturing"  maxlength="4" class="form-control autocomplete" maxlength="4" autocomplete="off"></input>
								</div>

							</div>
						</div>
						
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Machine No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="machine_no" name="machine_no" class="form-control autocomplete" maxlength="50" autocomplete="off" />
								</div>

							</div>
						</div>
<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-3"> -->
<!-- 									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Model Number</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 										<input type="text" id="model_no" name="model_no" class="form-control autocomplete" maxlength="50" autocomplete="off"/> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
					</div>
					

			
					<div class="col-md-12 ">
						
						
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>From Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="from_date" id="from_date" maxlength="10"   class="form-control width93 display_inline rgb0" 
							aria-required="true" autocomplete="off">
								</div>

							</div>
						</div>
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"> </strong>To Date</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="to_date" id="to_date" maxlength="10"   class="form-control width93 display_inline rgb0" 
							aria-required="true" autocomplete="off">
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12">
						

<!-- 						<div class="col-md-6"> -->
<!-- 							<div class="row form-group"> -->
<!-- 								<div class="col-md-3"> -->
<!-- 									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Status</label> -->
<!-- 								</div> -->
<!-- 								<div class="col-md-8"> -->
<!-- 									<select name="status" id="status" class="form-control"> -->
<!-- 									<option value="0" >Pending</option> -->
<!-- 									<option value="1" >Approved</option> -->
<!-- 									<option value="3" >Rejected</option> -->
<!-- 									</select> -->
<!-- 								</div> -->

<!-- 							</div> -->
<!-- 						</div> -->
						
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-3">
										<label class=" form-control-label">Serviceable State</label>
									</div>
									<div class="col-md-8">
									  <select name="s_state" id="s_state" class="form-control">
									  	<option value="0">--Select--</option>
										 <c:forEach var="item" items="${getServiceable_StateList}" varStatus="num">
										    <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									     </c:forEach>
									  </select>							
	        						  </div>
									</div>
								</div>
					</div>
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_PeripheralUrl"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  value="Search" id="searchid"/>            
<!-- 		           <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Peripheral Data" onclick="getExcel();" />             -->
              </div>
          </div>
          
          <div class="" id="divPrint" >				  
          <i class="fa fa-file-excel-o fxlarge color_green text_align_right" id="btnExport"  aria-hidden="true" title="EXPORT TO EXCEL"></i>
            
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
				    <input type="hidden" id="CheckVal" name="CheckVal">
<%-- 				   <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
				   <b><input type=checkbox id='nSelAll' name='tregn'
						>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
							
							 <input type="button" class="btn btn-success btn-sm" id="deletebtn" value="Delete" 
							>

<%-- 							</c:if>	 --%>
		                 <table id="BloodReport" class="display">
		                      <thead >
		                          <tr>
                                     <th id="2">Peripheral Type</th>
                                     <th id="3">Type of HW</th>
                                     <th id="4">Year Of Proc</th>
                                     <th id="5">Year Of Manufacturing</th>
                                     <th id="8">Machine No</th>
<!--                                      <th id="9">Model Number</th> -->
                                      <th id="14">Section</th>
                                     <th id="15">Created By Username</th>
                                     
                                     <th id="10">Remarks</th>
<%--                                       <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<!-- 										<th>Select To Approve</th> -->
<%-- 										</c:if> --%>
                                     <th data-orderable="false" >Action</th>
		                          </tr>                                                        
		                      </thead> 
		                 </table>
<%-- 		                <c:if test="${roleType == 'APP'  && status1 == '0'}"> --%>
<!-- 		                 <input type="button" class="btn btn-success btn-sm" value="Approve" -->
<!-- 							onclick="return setApproveStatus();"> -->
<!-- 							<input type="button" class="btn btn-success btn-sm" value="Reject" -->
<!-- 							onclick="return setRejectStatus();"> -->
<%-- 							</c:if> --%>
		            </div>	          
	        </div> 
      </div>
  


<c:url value="SearchDelete_PeripheralUrl_1" var="Search_PeripheralUrl_1" />
<form:form action="${Search_PeripheralUrl_1}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
     <input type="hidden" name="machine_make1" id="machine_make1" value=""/>
     <input type="hidden" name="machine_no1" id="machine_no1" value=""/>
     <input type="hidden" name="model_no1" id="model_no1" value=""/>   
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="from_date1" id="from_date1" value=""/>
     <input type="hidden" name="to_date1" id="to_date1" value=""/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="1"/>
       <input type="hidden" name="s_state1" id="s_state1" value="0"/>
        <input type="hidden" name="section1" id="section1" value="0"/>
     
</form:form>


<c:url value="Excel_Peripheral_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	  <input type="hidden" name="year_of_manufacturing2" id="year_of_manufacturing2" value="0"/>
	  	<input type="hidden" name="machine_number2" id="machine_number2" value="" />
	<input type="hidden" name="model_number2" id="model_number2" value="" />
	<input type="hidden" name="status2" id="status2" value="0" />
	<input type="hidden" name="s_state2" id="s_state2" value="0" />
</form:form>

 <c:url value="Edit_Peripherals" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form> 
	
 <c:url value="Delete_PeripheralsAFMS" var="deleteUrl" />
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
<script type="text/javascript" nonce="${cspNonce}">


document.addEventListener('DOMContentLoaded', function () {
	
	document.getElementById('deletebtn').onclick = 
		function() {
		return deleteData();
  	};
  	document.getElementById('nSelAll').onclick = 
		function() {
		return setselectall();
  	};
  	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};
 	document.getElementById('searchid').onclick = 
		function() {
		return Search();
  	};
	document.getElementById('s_state').onclick = 
		function() {
		return serviceStChange();
  	};
  	
  	
	document.getElementById('from_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('from_date').onfocus = 
		function() {
		return this.style.color='#000000';
  	};
	document.getElementById('from_date').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
	document.getElementById('from_date').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('from_date').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
  	
	document.getElementById('to_date').onclick = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('to_date').onfocus = 
		function() {
		return this.style.color='#000000';
  	};
	document.getElementById('to_date').onblur = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
	document.getElementById('to_date').onkeyup = 
		function() {
		return clickclear(this, 'DD/MM/YYYY');
  	};
	document.getElementById('to_date').onchange = 
		function() {
		return clickrecall(this,'DD/MM/YYYY');
  	};
  	
  	
	document.getElementById('year_of_manufacturing').onkeypress = 
		function() {
		return  validateNumber(event);
  	};
	document.getElementById('machine_no').onkeypress = 
		function() {
		return onlyAlphaNumeric(event, this);
  	};
// 	document.getElementById('model_no').onkeypress = 
// 		function() {
// 		return onlyAlphaNumeric(event, this);
//   	};
  	
 	
	document.getElementById('year_of_manufacturing').onkeyup = 
		function() {
		return  validateYear(this);
  	};
 	
	document.getElementById('year_of_manufacturing').onblur = 
		function() {
		return  validateYearLn(this);
  	};
  	
  	
  
  	

    
    
    
    
    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('action_update')) {
                var encryptedPk = event.target.getAttribute('data-id');
                if (confirm('Are you sure you want to Update?')) {
                        editData(encryptedPk);
                }
        } else if (event.target.classList.contains('action_delete')) {
                var encryptedPk = event.target.getAttribute('data-id');
                if (confirm('Are you sure you want to Delete?')) {
                        deleteData(encryptedPk);
                }
        }
        else if (event.target.classList.contains('.fa-eye')) {
            var encryptedPk = event.target.getAttribute('data-id');
            if (confirm('Are You Sure You Want to View This Information?')) {
            	viewData(id);
            }
    }
});
  	
  	
  	
  	
  	
});


function getExcel() {	

	$("#computing_assets2").val($("#asset_type").val());
	$("#model_number2").val($("#model_number").val());
	$("#machine_number2").val($("#machine_number").val());
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val("1");
	$("#year_of_manufacturing2").val($("#year_of_manufacturing").val()); 
	$("#s_state2").val($("#s_state").val()); 
	
	document.getElementById('ExcelForm').submit();
} 

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
       // var model_no=$("#model_no").val() ;
         var from_date=$("#from_date").val() ;
         var to_date=$("#to_date").val() ;
         var status="1" ;
         var s_state=$("#s_state").val() ;
     	var section=$("#section").val() ;
     	
         
         
         
        $.post("getFilteredPeripheral1Delete?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
        	assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
                machine_make:machine_make,machine_no:machine_no,from_date:from_date,
                to_date:to_date,status:status,s_state:s_state,section:section},function(j) {
                        
                for (var i = 0; i < j.length; i++) {
                	var data =[j[i].assets_name,j[i].type_of_hw,j[i].year_of_proc,j[i].year_of_manufacturing,j[i].machine_no,
                        j[i].section,j[i].created_by,j[i].remarks ,j[i].chekboxaction
//                         <c:if test="${roleType == 'APP'  && status1 == '0'}">
                      	 
//                         </c:if>
//                         ,j[i].action 
                        ];
                        jsondata.push(data);
                }
                
                $("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
        });
        $.post("getTotalPeripheralCount1Delete?"+key+"="+value,{Search:Search,assets_type:assets_type,year_of_manufacturing:year_of_manufacturing,
            machine_make:machine_make,machine_no:machine_no,from_date:from_date,
            to_date:to_date,status:status,s_state:s_state,section:section},function(j) {
                FilteredRecords = j;
        });
}
</script>






<script nonce="${cspNonce}">
// function getExcel() {
	

// 	document.getElementById('typeReport1').value = 'excelL';
// 	document.getElementById('search2').submit();

// } 
function btn_clc(){
	location.reload(true);
}

 function deleteData(id){
	 findselected();
		
		var a = $("#CheckVal").val();

		if(a == ""){
			alert("Please Select the Data for Approval"); 
		}else{
	$("#id1").val(a);
	document.getElementById('deleteForm').submit();
		}
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
	//$("#model_no1").val($("#model_no").val());   
	$("#year_of_manufacturing1").val($("#year_of_manufacturing").val()); 
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());  
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val("1");
	$("#s_state1").val($("#s_state").val());
	$("#section1").val($("#section").val());
	$("#searchForm").submit();
}


</script>


<script nonce="${cspNonce}">
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
<script nonce="${cspNonce}"> 
$(document).ready(function() {

	mockjax1('BloodReport');
	table = dataTable('BloodReport');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
	
	datepicketDate('from_date');
	datepicketDate('to_date');

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
// 	if ("${model_no1}" !=""){
// 		$("#model_no").val("${model_no1}");
// 	} 
	if ("${from_date1}" !=""){
		$("#from_date").val("${from_date1}");
	} 
	if ("${to_date1}" !=""){
		$("#to_date").val("${to_date1}");
	} 
	if ("${s_state1}" !=""){
		$("#s_state").val("${s_state1}");
	} 

	if('${section1}' != "")
	{
	$("#section").val("${section1}");
	}

	

});	

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode  < 48||charCode> 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
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
</script>

 
 




