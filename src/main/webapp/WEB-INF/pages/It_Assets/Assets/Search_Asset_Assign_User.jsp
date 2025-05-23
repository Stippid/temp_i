
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

<style nonce="${cspNonce}">

i.fa.fa-pencil.action_unarchive {
    color: green;
}
</style>


<div class="" align="center">
	<div class="card">
		<div class="card-header">
			<h5>SEARCH APPROVED ASSETS ASSIGN USER</h5>
		</div>
		<div class="card-body card-block">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"> Section </label>
							</div>
							<div class="col-md-8">
								<select id="section" name="section" class="form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num">
										<option value="${item.id}"
											<c:if test="${item.id == MainAssetsCmd.section}">selected</c:if>>${item.section}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Computing Assets Type</label>
							</div>
							<div class="col-md-8">
								<select name="assets_type" id="assets_type" class="form-control">
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
								<label for="text-input" class=" form-control-label"><strong
									class="color_red"> </strong>Assets Unique Id </label>
							</div>
							<div class="col-md-8">
								<input type="text" name ="assets_uniq" id="assets_uniq" class="form-control" autocomplete="off">
									
							</div>
						</div>
					</div>
					</div>
			</div>
		</div>
		<div class="card-footer" align="center">
			<a href="${screenurl}" class="btn btn-primary btn-sm" id="btn_clc">Clear</a>
			<i class="fa fa-search"></i><input type="button"
				class="btn btn-success btn-sm" id="searchbtn" value="Search" />
		</div>
	</div>

	<div class="container-fluid" align="center">
		<div class="card">
			<div class="card-body">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<button class="download-excel-btn" id="btnExport"
						 title="EXPORT TO EXCEL"
						aria-hidden="true">
						<i class="fa fa-file-excel-o"></i>
					</button>
					<br> 
					
					<table id="approveassignReport" class="display">
						<thead>
							<tr>
						    	<th id="1">Assest Id</th>
								<th id="1">Computing Assets Type</th>
								<th id="4">Machine No</th>
							    <th id="13">Deply Envt as Applicable</th>
							    <th id="12">User</th>
							  	<th id="14">Section</th>
								<th class="action">Action</th>
							</tr>
						</thead>

					</table>
					
				</div>
			</div>
		</div>
	</div>
</div>


<c:url value="assigntouserurla" var="assignUrl" />
<form:form action="${assignUrl}" method="post" id="assigntouserForm"
	name="assigntouserForm"   modelAttribute="assetId">
  <input type="hidden" name="assetId" id="assetId" value="0" />
</form:form>	
<c:url value="ComputingAssertsView" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id">
	<input type="hidden" name="viewid" id="viewid" value="0" />
	<input type="hidden" name="screenurl4" id="screenurl4" value="search_asset_assignurl" />
</form:form>	

	


<c:url value="AssignAssertsArchive" var="archiveUrl" />
<form:form action="${archiveUrl}" method="post" id="archiveForm" name="archiveForm" modelAttribute="id1">
	<input type="hidden" name="id2" id="id2" value="0" />
	<input type="hidden" name="screenurl3" id="screenurl3" value="search_asset_assignurl" />
		<input type="hidden" name="status2" id="status2" value="0" />
</form:form>





<c:url value="assignTouserApprove" var="logoutUrl" />
	<form:form action="${logoutUrl}" method="post" id="ApproveassigntouserForm" name="ApproveassigntouserForm">
		<input type="hidden" name="assetId3" id="assetId3" value="" />
</form:form>

<script type="text/javascript" nonce="${cspNonce}">

function AssignData(a) {
	
    $("#assetId").val(a);
	document.getElementById('assigntouserForm').submit();
  
}
function ViewData(a)
{
	$('#viewid').val(a);
	$('#viewForm').submit();
}
function ArchievData(a,status)
{
	$('#status2').val(status);
	$('#id2').val(a);
	$('#archiveForm').submit();
}
function ApproveData(a)
{
	$('#assetId3').val(a);
	$('#ApproveassigntouserForm').submit();
}



var susNoAuto = $("#assets_uniq");
susNoAuto.autocomplete({
    source: function(request, response) {
        $.ajax({
            type: 'POST',
            url: "getassets_uniqidList?" + key + "=" + value,
            data: {
                loginname: request.term 
            },
            success: function(data) {
                var susval = [];
                var length = data.length - 1;
                var enc = data[length].substring(0, 16);
                for (var i = 0; i < length; i++) { 
                    var supplierData = data[i];
                    var supplierName = dec(enc, supplierData.assetid); 
                    susval.push({
                        label: supplierName, // This is what will be displayed in the dropdown
                        value: supplierData.id // This is the value that will be set in the input field
                    });
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
        $("#assets_uniq").val(ui.item.label); 
        return false;
    },
    change: function(event, ui) {
        if (!ui.item) {
            alert("Please Enter Assets unique No.");
            susNoAuto.val("");
            susNoAuto.focus();
        }
    }
});

function data(approveassignReport){
	
	jsondata = [];
	var table = $('#'+approveassignReport).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	var section=$("#section").val();
	var computing_asset_type=$("#assets_type").val();
	var assets_uniq=$("#assets_uniq").val();
	
	$.post("getApprovedassetcomputing?"+key+"="+value,{startPage:startPage,pageLength:pageLength,
		Search:Search,orderColunm:orderColunm,orderType:orderType
	,section:section,computing_asset_type:computing_asset_type,assets_uniq:assets_uniq
	},function(j) {

			for (var i = 0; i < j.length; i++) {
         	var data =[j[i].assetid,j[i].assets_name,j[i].machine_number,j[i].dply_envt,j[i].created_by,j[i].section,j[i].chekboxaction ];
                 jsondata.push(data);
         }
			
		
	});
	$.post("getApprovedassetcount?"+key+"="+value,{Search:Search,section:section,computing_asset_type:computing_asset_type,assets_uniq:assets_uniq
		},function(j) {
		FilteredRecords = j;
	});
}
$(document).ready(function() {
	
mockjax1('approveassignReport');
table = dataTable('approveassignReport');
});

$('#searchbtn').on('click', function(){
	table.ajax.reload();
});
	document.addEventListener('click', function(event) {
  	    if (event.target.classList.contains('action_view_1')) {
  	          var encryptedPk = event.target.getAttribute('data-id');
  	            ViewData(encryptedPk); 
  	   
  	    } 
  	    if (event.target.classList.contains('action_archive')) {
  	    	if(confirm("Ary You Sure You Want to Archieve ?"))
  	    		{
  	          var encryptedPk = event.target.getAttribute('data-id');
  	        var encryptedstatus = event.target.getAttribute('data-status');
  	        ArchievData(encryptedPk,encryptedstatus); 
  	    		}
	   
	    }   	   
  	    if (event.target.classList.contains('action_unarchive')) {
  	    	if(confirm("Ary You Sure You Want to Unarchieve ?"))
	    		{
	          var encryptedPk = event.target.getAttribute('data-id');
	        var encryptedstatus = event.target.getAttribute('data-status');
	        ArchievData(encryptedPk,encryptedstatus); 
	    		}

   
    } 
  	    
  	  
  	  if (event.target.classList.contains('action_approve')) {
	    	if(confirm("Ary You Sure You Want to Aprrove ?"))
	    		{
	          var encryptedPk = event.target.getAttribute('data-id');
	        ApproveData(encryptedPk); 
	    		}
	
	   
	    } 
  	  if (event.target.classList.contains('action_assigin_1')) {
	          var encryptedPk = event.target.getAttribute('data-id');
	          AssignData(encryptedPk); 
	    		
	
	   
	    } 
  	    
  	  
	});


</script>


