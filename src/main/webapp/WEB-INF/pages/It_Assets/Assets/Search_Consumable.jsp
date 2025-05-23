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
		             <h5>SEARCH/EDIT CONSUMABLE ASSETS</h5>
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
											<c:if test="${item.id == section}">selected</c:if>>${item.section}</option>
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
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Consumable Type</label>
								</div>
								<div class="col-md-8">
									<select name="assets_type" id="assets_type" class="form-control">
									<option value="0" >--Select--</option>
										<c:forEach var="item" items="${getConsumables}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Year Of Production</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="year_of_manufacturing" name="year_of_manufacturing"  maxlength="4" class="form-control autocomplete" maxlength="4"  autocomplete="off"></input>
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12">
						

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong class="color_red"></strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
									<option value="0" >Pending</option>
									<option value="1" >Approved</option>
									<option value="3" >Rejected</option>
									<option value="7" >Archived</option>
									</select>
								</div>

							</div>
						</div>
						
					</div>
					
					 
			
				</div>
			</div>
	    	<div class="card-footer" align="center">
			     <a href="Search_ConsumableUrl"class="btn btn-primary btn-sm"  id="btn_clc">Clear</a>
		          <i class="fa fa-search"></i><input type="button" class="btn btn-success btn-sm"  id="searchbtn" value="Search" />            
<!-- 		           <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Peripheral Data" onclick="getExcel();" />             -->
              </div>
          </div>
 




	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-body">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<button class="download-excel-btn" id="btnExport"
						style="margin-bottom: 10px;" title="EXPORT TO EXCEL"
						aria-hidden="true">
						<i class="fa fa-file-excel-o"></i>
					</button>
					<br>
					<br> 
					<span id="ip"></span>
				    <input type="hidden" id="CheckVal" name="CheckVal">
				   <c:if test="${roleType == 'APP'  && status1 == '0'}">
				   <b><input type=checkbox id='nSelAll' name='tregn'
						>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
							</c:if>	
		     <table id="BloodReport" class="display text_align_center">
		                      <thead >
		                          <tr>
                                     <th id="2">Consumable Type</th>                                   
                                     <th id="4">Year Of Proc</th>                                    
                                      <th id="14">Section</th>
                                     <th id="15">Created By Username</th>                                    
                                     <th id="10">Remarks</th>
                                      <c:if test="${roleType == 'APP'  && status1 == '0'}">
										<th>Select To Approve</th>
										</c:if>
                                     <th data-orderable="false" >Action</th>
		                          </tr>                                                        
		                      </thead> 
		                 </table>
				        <c:if test="${roleType == 'APP'  && status1 == '0'}">
		                 <input type="button" class="btn btn-success btn-sm" id="approvebtn" value="Approve"
							>
							<input type="button" class="btn btn-success btn-sm" id="rejectbtn" value="Reject"
							>
							</c:if>
				</div>
			</div>
		</div>
	</div>





      </div>

<c:url value="Search_consumable_1" var="Search_consumable_1" />
<form:form action="${Search_consumable_1}" method="post" id="searchForm" name="searchForm" modelAttribute="machine_make1">
    
     <input type="hidden" name="year_of_manufacturing1" id="year_of_manufacturing1" value="0"/>
     <input type="hidden" name="section1" id="section1" value="0"/>
     <input type="hidden" name="assets_type1" id="assets_type1" value="0"/>
      <input type="hidden" name="status1" id="status1" value="0"/>
     
     
</form:form>

<c:url value="Excel_Consumables_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
	<input type="hidden" name="section2" id="section2" value="0" />
	<input type="hidden" name="assets_type2" id="assets_type2" value="0" />
	<input type="hidden" name="year_of_manufacturing2"id="year_of_manufacturing2" value="0" />
	<input type="hidden" name="status2" id="status2" value="0" />
</form:form>

<c:url value="Edit_Consumables" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id" >
	<input type="hidden" name="updateid" id="updateid" value="0" />
	<input type="hidden" name="screenurl9" id="screenurl9" value="Search_ConsumableUrl" />
</form:form> 
	
 <c:url value="Delete_consumables" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
		<input type="hidden" name="id1" id="id1" value="0"/> 
		<input type="hidden" name="screenurl10" id="screenurl10" value="Search_ConsumableUrl" />
	</form:form>
	
	 <c:url value="View_consumables" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id" >
	<input type="hidden" name="viewid" id="viewid" value="0" />
	<input type="hidden" name="screenurl11" id="screenurl11" value="Search_ConsumableUrl" />
</form:form> 

<c:url value="ConsumablesArchive" var="archiveUrl" />
	<form:form action="${archiveUrl}" method="post" id="archiveForm" name="archiveForm" modelAttribute="id1">
		<input type="hidden" name="arid2" id="arid2" value="0"/> 
			<input type="hidden" name="screenurl12" id="screenurl12" value="Search_ConsumableUrl" />
	</form:form>


<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
 
<!-- for Functions -->
<script type="text/javascript" nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function () {
	<c:if test="${roleType == 'APP'  && status1 == '0'}">
	document.getElementById('approvebtn').onclick = 
		function() {
		return setApproveStatus();
  	};
  	document.getElementById('rejectbtn').onclick = 
		function() {
		return setRejectStatus();
  	};
  	</c:if>
  	
	document.getElementById('btnExport').onclick = 
		function() {
		return getExcel();
  	};
	document.getElementById('searchbtn').onclick = 
		function() {
		return Search();
  	};
    <c:if test="${roleType == 'APP'  && status1 == '0'}">
	document.getElementById('nSelAll').onclick = 
		function() {
		return setselectall();
  	};
  	</c:if>
  	
  	
  
  	
  	
  	
 	document.getElementById('year_of_manufacturing').onkeyup = 
		function() {
		return validateYear(this);
  	};
 	document.getElementById('year_of_manufacturing').onkeypress = 
		function() {
		return validateNumber(event);
  	};
 	document.getElementById('year_of_manufacturing').onblur = 
		function() {
		return validateYearLn(this);
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
         else if (event.target.classList.contains('fa-eye')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to view?')) {
            	 viewData(encryptedPk);
             }
     }
         else if (event.target.classList.contains('action_archive')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to Archive?')) {
            	 ArchiveData(encryptedPk);
             }
     }
         
         
         
         
 });
  	 
//   	//document.querySelectorAll('[data-action]').forEach(function(element) {
//         element.addEventListener('click', function() {
//         	debugger;
// //             var action = this.getAttribute('data-action');
// //             var id = this.getAttribute('data-id');

//             if (action === 'edit') {
//             	var encryptedPk = event.target.getAttribute('data-id');
//                 if (confirm('Are You Sure You Want to Update Details ?')) {
//                     editData(encryptedPk);
//                 }
//             } else if (action === 'delete') {
//                 if (confirm('Are You Sure You Want to Delete This Information ?')) {
//                 	var encryptedPk = event.target.getAttribute('data-id');
//                     deleteData(encryptedPk);
//                 }
//             } else if (action === 'view') {
//                 if (confirm('Are You Sure You Want to View This Information ?')) {
//                 	var encryptedPk = event.target.getAttribute('data-id');
//                     viewData(encryptedPk);
//                 }
//             } else if (action === 'archive') {
//                 if (confirm('Are You Sure You Want to Archive This Information ?')) {
//                 	var encryptedPk = event.target.getAttribute('data-id');
//                     ArchiveData(encryptedPk);
//                 }
//             }
//         });
    //});
    document.querySelectorAll('.nrCheckBox3').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
        	var encryptedPk = event.target.getAttribute('data-id');
            checkbox_count(this, encryptedPk); // Call your existing function
        });
    });
  	
  	
});

function getExcel() {	
	$("#assets_type2").val($("#assets_type").val());
	$("#section2").val($("#section").val());
	$("#status2").val($("#status").val());
	$("#year_of_manufacturing2").val($("#year_of_manufacturing").val()); 
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
         var status=$("#status").val() ;
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
                
                $("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
        });
        $.post("getTotalconsumableCount1?"+key+"="+value,{Search:Search,assets_type:assets_type,status:status,section:section,year_of_manufacturing:year_of_manufacturing},function(j) {
                FilteredRecords = j;
        });
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
 
 function ArchiveData(id){
		$("#arid2").val(id);
		document.getElementById('archiveForm').submit();
	} 

 
 
function Search(){
	
	$("#machine_make1").val($("#machine_make").val()); 
	$("#model_no1").val($("#model_no").val());   
	$("#year_of_manufacturing1").val($("#year_of_manufacturing").val()); 
	$("#from_date1").val($("#from_date").val());
	$("#to_date1").val($("#to_date").val());  
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val($("#status").val());
	$("#s_state1").val($("#s_state").val());
	$("#section1").val($("#section").val());
	$("#searchForm").submit();
}

function findselected(){
	var nrSel=$('.nrCheckBox3:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
}


function setselectall(){

	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox3").prop('checked', true);
	} else {
		$(".nrCheckBox3").prop('checked', false);
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
	debugger;
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_ConsumableAssetsData2?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
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

			$.post("Approve_ConsumableAssetsData2?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
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
	
	var r =('${roleAccess}');

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
		$("input#year_of_manufacturing").val("${year_of_manufacturing1}");
	} 
	
	if ("${model_no1}" !=""){
		$("#model_no").val("${model_no1}");
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






function addRemarkModel(method,id)
{     
      if( $('#btnTrigger').length ){}
      else{
			var btn = document.createElement("button"); 
			btn.id="btnTrigger";
			document.body.appendChild(btn);
			$( "#btnTrigger" ).attr("data-toggle", "modal");
			$( "#btnTrigger" ).attr("data-target", "#remarkModel");
			$( "#btnTrigger" ).hide();
		  }

	  if( $('#remarkModel').length )        
		 {
		    $("#remarkModel").remove();
		 }	
		 	    	
       	var div = document.createElement("div");   
        div.id="remarkModel";
  		document.body.appendChild(div);
  		$( "#remarkModel" ).addClass( "modal fade" );
  		$( "#remarkModel" ).attr("role", "dialog");
  		$( "#remarkModel" ).attr("data-backdrop", "static");
  		
           div.innerHTML=''
   						 +'<div class="modal-dialog" style="width: 500px !important;">'
     					 +'  <div class="modal-content" style="width: 500px !important;">'
      					 +'  <div class="modal-header" >'
    				     +'  <h4 class="modal-title" align="left">Reject Remarks</h4>'
     				     +'    <button type="button" class="close" data-dismiss="modal">&times;</button> '     
      					 +'   </div>'
                         +'  <div class="modal-body">'
                         +' <textarea id="reject_remarks"'
						 +'name="reject_remarks" class="form-control autocomplete" '
						 +'autocomplete="off" maxlength="1000"></textarea>'
                         +'   </div>'
                         +'   <div class="modal-footer" align="center">'
                         +'      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
                         +'      <input type="button" id="remarkSubmit" data-dismiss="modal" value="Submit" onclick="'+method+'('+id+')"class="btn btn-success" >'
                         +'   </div>'
                         +' </div>'    
                         +'  </div>';
        
         $('#btnTrigger').click(); 	         
         if( $('.modal-backdrop').length ){
        	  $(".modal-backdrop").remove();    
         }
     
} 





</script>

 




