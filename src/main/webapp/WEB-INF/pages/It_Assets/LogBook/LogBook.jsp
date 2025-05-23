<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"nonce="${cspNonce}"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"nonce="${cspNonce}">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"nonce="${cspNonce}"></script>

<form:form name="" id="" action="make_master_Action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" modelAttribute="make_master_CMD"> 
<spring:htmlEscape defaultHtmlEscape="true" /> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>Log Book</h5></div>
    				<div class="card-body card-block">
            			<div class="col-md-12">
            				<div class="col-md-6">
            					<div class="row form-group">
                					<div class="col-md-6">
                  						<strong class="color_red">* </strong> <label class=" form-control-label">CATEGORY</label>
                					</div>
                					<div class="col-md-6">
                						<select name="category" id="category" class="form-control" >
											<option value="0">--Select--</option>
											   <option value="1">Computing</option>
											    <option value="2">Peripheral</option>
											    <option value="3">Consumables</option>
										 </select>
										<input type="hidden" id="make_no" name="make_no" class="form-control" maxlength="3" value ="0"  onkeypress="return validateNumber(event, this)" autocomplete="off" readonly="readonly">
		        					</div>
              					</div>
            				</div>
            				<div class="col-md-6">
            					<div class="row form-group">
               						<div class="col-md-6">
                 							<strong class="color_red"> </strong> <label class=" form-control-label">ASSETS NAME </label>
               						</div>
               						<div class="col-md-6">
	               						<select name="assets_name" id="assets_name" class="form-control" >
											<option value="0">--Select--</option>
										
										</select>
									</div> 		
              				    </div>
            				</div>
            			</div>
            			
            				
            	
            			
            			<div class="col-md-12">
            				<div class="col-md-6">
            					<div class="row form-group">
               						<div class="col-md-6">
                 							<strong class="color_red"> </strong> <label class=" form-control-label">MAKE/BRAND NAME</label>
               						</div>
               						<div class="col-md-6">
	                					
	                					   <select name="make_name" id="make_name" class="form-control" >
											<option value="0">--Select--</option>
										
										</select>
									</div>
              				    </div>
            				</div>
            			</div>
            		</div>
					  <div class='card-footer' align='center'>
   						  <a href=Log_book_url class="btn btn-success btn-sm">Clear</a>
          				 
         				   <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       				</div>
	        	</div>
			</div>
    </div>
    <div align="center" class="container">
		<i class="fa fa-file-excel-o export_excel display_none" id="btnExport"
			
			aria-hidden="true" title="EXPORT TO EXCEL" ></i>
	</div>
	<br>

    
    <div class="container display_none" id="divTable" >
    <div class="card">
    <div class="card-body">
    
	<table id="LogBooktbl" class="display dataTable no-footer text_align_center  no-margin table-striped    table-bordered  text_align_center">
   		<thead>
       		<tr>
            	<th id="4">Category</th>
            	<th id="2">Assets Name</th>
            	<th id="3">Make/Brand Name</th>
            	<th id="5">Model Name</th>
            	<th data-orderable="false">Action</th>
            </tr>
    	</thead>
	</table>
</div>
</div>
</div>
</form:form>
<c:url value="downloadLogBookPDF" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="cont_comd_tx">
 <input type="hidden" id="LogbookId" name="LogbookId" value=""/>
  <input type="hidden" id="categoryId" name="categoryId" value=""/>
</form:form>

<script type="text/javascript" nonce="${cspNonce}">

document.addEventListener('DOMContentLoaded', function() {

  	document.getElementById('btnExport').onclick = function() {
		return getExcel();	
  	};
	document.getElementById('category').onchange = function() {
		return fn_Category(),fn_makeName();	
  	};
	document.getElementById('assets_name').onchange = function() {
		return fn_makeName();	
  	};

  	 document.addEventListener('click', function(event) {
         if (event.target.classList.contains('action_download')) {
             var encryptedPk = event.target.getAttribute('data-encrypted-pk');
             if (confirm('Are you sure you want to Download?')) {
            	 $("#LogbookId").val(encryptedPk);
            	 $("#categoryId").val( $("#category").val());
            	 $("#downloadForm").submit();
                
             }
         } else if (event.target.classList.contains('action_delete')) {
             var encryptedPk = event.target.getAttribute('data-encrypted-pk');
             if (confirm('Are you sure you want to View?')) {
                 deleteData(encryptedPk);
             }
         }
     });

});



$(document).ready(function () {
	mockjax1('LogBooktbl');
	table = dataTable('LogBooktbl');
	$('#btn-reload').on('click', function(){
		if(isvalid())
		{   $("#divTable").removeClass('display_none');
		 //$("#btnExport").removeClass('display_none');
			table.ajax.reload();	
		}
		
	});
	try{
		   if(window.location.href.includes("msg="))
			{
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			} 	
		}
		catch (e) {
		} 
	});
	
	function isvalid(){
		if($("#category").val()=="" ||$("#category").val()=="0" )
			{
			alert("Please Select Cateogry");
			$("#category").focus();
			return false;
			}
		
	return true;	
	}
	
function data(tableName){
	jsondata = [];
	var table = $('#LogBooktbl').DataTable();
	var info = table.page.info();

	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];
	var catgory =$("#category").val();
	var assets_name =$("#assets_name").val();
	var make_name =$("#make_name").val();
	

	$.post("getLogBookReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
		catgory:catgory,assets_name:assets_name,make_name:make_name},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].category,j[i].assets_name,j[i].make_name,j[i].model_name,j[i].history+j[i].action]);
		}
	});
	$.post("getLogBookTotalCount?"+key+"="+value,{Search:Search,catgory:catgory,assets_name:assets_name,make_name:make_name},function(j) {
		FilteredRecords = j;
	});
}
function fn_makeName() {
	debugger;
	var assets_name = $("select#assets_name").val();
	$
			.post("getMakeNameList?" + key + "=" + value, {
				assets_name : assets_name
			})
			.done(
					function(j) {
						var options = '<option   value="0">' + "--Select--"
								+ '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
									+ j[i][1] + '</option>';
						}
						$("select#make_name").html(options);
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}


function fn_Category() {
		
		
		var categogry_id = $("select#category").val();
		$.post("getCategoryList?" + key + "=" + value, {
			categogry_id: categogry_id
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#assets_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
</script>