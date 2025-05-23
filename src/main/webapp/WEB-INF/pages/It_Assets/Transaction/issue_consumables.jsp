<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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



<div class="" align="center">
	<div class="card">
		<div class="card-header">
			<h5>ISSUE CONSUMABLES</h5>

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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"></strong>Consumable Type</label>
							</div>
							<div class="col-md-8">
								<select name="assets_type" id="assets_type" class="form-control">
									<option value="0">--select--</option>
									<c:forEach var="item" items="${getConsumables}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</select>
							</div>

						</div>
					</div>
					<div class="col-md-6">
						<div class="col-md-4">
							<label class="form-control-label"><strong
								class="color_red"></strong>CRV No.</label>
						</div>
						<div class="col-md-8">
							<input type="text" id="crv_no" name="crv_no"
								class="form-control autocomplete" autocomplete="off">
						</div>
					</div>

				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
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
			</div>
		</div>
		<div class="card-footer" align="center">
			<a href="issue_consumables" class="btn btn-primary btn-sm"
				id="btn_clc">Clear</a> <i class="fa fa-search"></i><input
				type="button" class="btn btn-success btn-sm" id="searchbtn"
				value="Search" />
			<!-- 		           <i class="fa fa-download"></i><input type="button" class="btn btn-success btn-sm"  value="Download Peripheral Data" onclick="getExcel();" />             -->
		</div>
	</div>

	<div class="" id="divPrint">
		<i class="fa fa-file-excel-o fxlarge color_green text_align_right"
			id="btnExport" aria-hidden="true" title="EXPORT TO EXCEL"></i>

		<div class="watermarked" data-watermark="" id="divwatermark">
			<span id="ip"></span> <input type="hidden" id="CheckVal"
				name="CheckVal">
			<table id="BloodReport" class="display">
				<thead>
					<tr>
						<th id="2">Section</th>
						<th id="4">Consumable Type</th>
						<th id="14">Make name</th>
						<th id="15">Model name</th>
						<th id="10">Total Quantity</th>
						<th>CRV No</th>
						<th data-orderable="false">Action</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<c:url value="Search_issue_consumable_1" var="Search_consumable_1" />
<form:form action="${Search_consumable_1}" method="post" id="searchForm"
	name="searchForm" modelAttribute="status1">
	<input type="hidden" name="section1" id="section1" value="0" />
	<input type="hidden" name="assets_type1" id="assets_type1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="crv_no1" id="crv_no1" value="" />
</form:form>

<c:url value="Excel_Issue_Consumables_Assets_Search" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm"
	name="ExcelForm" modelAttribute="id3">
		<input type="hidden" name="section3" id="section3" value="0" />
	<input type="hidden" name="assets_type3" id="assets_type3" value="0" />
	<input type="hidden" name="status3" id="status3" value="0" />
	<input type="hidden" name="crv_no3" id="crv_no3" value="" />
	
	
</form:form>

<c:url value="Edit_issue_Consumables" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>

<c:url value="Delete_issue_consumables" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<%-- <c:url value="Edit_issue_Consumables" var="viewUrl" /> --%>
<%-- <form:form action="${viewUrl}" method="post" id="viewForm" --%>
<%-- 	name="viewForm" modelAttribute="id"> --%>
<!-- 	<input type="hidden" name="viewid" id="viewid" value="0" /> -->
<%-- </form:form> --%>

<c:url value="ConsumablesArchive" var="archiveUrl" />
<form:form action="${archiveUrl}" method="post" id="archiveForm"
	name="archiveForm" modelAttribute="id1">
	<input type="hidden" name="arid2" id="arid2" value="0" />
</form:form>


<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2"
	name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>




<c:url value="Approve_Issue_Consumables" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0"/> 
</form:form>

<c:url value="Reject_Issue_Consumables" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
</form:form>



<!-- for Functions -->
<script type="text/javascript" nonce="${cspNonce}">
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
//         $("#blockdata").show();
//         var id = ui.item.value;
//         getCrvData(id); // Fetch additional data based on selected CRV number
    },
    change: function(event, ui) {
        if (!ui.item) {
            alert("Please Enter Active CRV No.");
            susNoAuto.val("");
            susNoAuto.focus();
        }
    }
});
document.addEventListener('DOMContentLoaded', function () {
	document.getElementById('searchbtn').onclick = 
		function() {
		 Search();
  	};
  	document.getElementById('btnExport').onclick = 
		function() {
  		getExcel();
  	};
  	
  	
  	 document.addEventListener('click', function(event) {
         if (event.target.classList.contains('action_update')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 if (confirm('Are you sure you want to Update?')) {
                         editData(encryptedPk);
                 }
         } else if (event.target.classList.contains('action_approve')) {
                 var encryptedPk = event.target.getAttribute('data-id');
                 if (confirm('Are you sure you want to Approve?')) {
                	 Approve(encryptedPk);
                 }
         }
         else if (event.target.classList.contains('action_reject')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to Reject?')) {
            	 Reject(encryptedPk);
             }
     }
         else if (event.target.classList.contains('action_view')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to view?')) {
            	 viewData(encryptedPk);
             }
             
             
     }
         else if (event.target.classList.contains('action_delete')) {
             var encryptedPk = event.target.getAttribute('data-id');
             if (confirm('Are you sure you want to delete?')) {
            	 deleteData(encryptedPk);
             }
             
             
     }
         
         
         
         
         
 });
    document.querySelectorAll('.nrCheckBox').forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
        	var encryptedPk = event.target.getAttribute('data-id');
            checkbox_count(this, encryptedPk); // Call your existing function
        });
    });
  	
  	
});

function getExcel() {	
	$("#section3").val($("#section").val());
	$("#assets_type3").val($("#assets_type").val());
	$("#status3").val($("#status").val());
	$("#crv_no3").val($("#crv_no").val());
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
        var section=$("#section").val() ;
         var assets_type=$("#assets_type").val() ; 
         var crv_no=$("#crv_no").val() ; 
         var status=$("#status").val() ;
    	
         
         
        $.post("getFilteredissueconsumable1?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
        	section:section,assets_type:assets_type,crv_no:crv_no,status:status},function(j) {
        		 console.log(j);
                for (var i = 0; i < j.length; i++) {
                	var data =[j[i].section,j[i].aname,j[i].mname,j[i].model_name,j[i].assetcount,j[i].crv_no,j[i].action ];
                        jsondata.push(data);
                }
                
                $("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
        });
        $.post("getTotalissueconsumableCount1?"+key+"="+value,{Search:Search,section:section,assets_type:assets_type,crv_no:crv_no,status:status},function(j) {
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
	 $("#updateid").val(id);
	document.getElementById('updateForm').submit();
}
 
 function ArchiveData(id){
		$("#arid2").val(id);
		document.getElementById('archiveForm').submit();
	} 

 
 
function Search(){
	$("#assets_type1").val($("#assets_type").val());
	$("#status1").val($("#status").val());
	$("#section1").val($("#section").val());
	$("#crv_no1").val($("#crv_no").val());
	$("#searchForm").submit();
}



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



function Approve(id){
	$("#id3").val(id);
	document.getElementById('approveForm').submit();
	
}
function Reject(id){
	$("#id4").val(id);
	document.getElementById('rejectForm').submit();
	
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
 
$(document).ready(function() {
	
	var r =('${roleAccess}');

	mockjax1('BloodReport');
	table = dataTable('BloodReport');
// 	$('#btn-reload').on('click', function(){
//     	table.ajax.reload();
//     });
	
	datepicketDate('from_date');
	datepicketDate('to_date');
	if('${status1}' != "")
	{
	$("#status").val("${status1}");
	}
	if ("${assets_type1}" !=""){
		$("#assets_type").val("${assets_type1}");
	} 
	if('${crv_no1}' != "")
	{
	$("#crv_no").val("${crv_no1}");
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






