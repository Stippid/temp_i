<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<form:form  name="Search_Computing_Asset_WO" id="Search_Computing_Asset_WO"  method='POST'   >
	<div class="col-md-12" align="center">
	   <div class="card">
	  		<div class="card-header"><h5> GENERATE COMPUTING WORK ORDER </h5></div>
			<div class="card-body card-block">
	            <div class="col-md-12">
	            	        <div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4"><label> Computing Assets Type</label></div>
					                <div class="col-md-7">
								         	<select name="asset_type" id="asset_type" class="form-control" onchange="fn_makeName();">
									<option value="0" >--SELECT--</option>
										<c:forEach var="item" items="${ComputingAssetList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
				               		</div>
		               			</div>
	            			</div>
	            
	            		<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Make Name <span class="star_design">*</span></label></div>
				                <div class="col-md-7">
						         <select name="make_name" id="make_name" class="form-control" onchange="fn_modelName();">
									<option value="0" >--SELECT--</option>
									</select>
			               		</div>
		               		</div>
		               </div>
		            	
		              <div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Model Name <span class="star_design">*</span></label></div>
				                <div class="col-md-7">
						         	<select name="model_name" id="model_name" class="form-control">
									<option value="0" >--SELECT--</option>
									</select>
			               		</div>
		               		</div>
		               </div>
	            	</div>
	            	
	            	<div class="col-md-12">
	            	
						<!-- 	<div class="col-md-4">
							<div class="row form-group">
								<div class="col-md-4"><label> Dt From </label></div>
								<div class="col-md-7">
									   <input type="date" id="dt_from" name="dt_from" class="form-control" >
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="row form-group">
			               		<div class="col-md-4"><label> Dt To </label></div>
				                <div class="col-md-7">
						          	   <input type="date" id="dt_to" name="dt_to" class="form-control" >
			               		</div>
		               		</div>
		               </div> -->
		                     <div class="col-md-4">
								<div class="row form-group">
									<div class="col-md-4"><label> Status</label></div>
					                <div class="col-md-7">
							          <select name="status" id="status" class="form-control" >
							              <option value="" >--SELECT--</option>
									 		<option value="0" >PENDING WORK ORDER</option>
											<option value="1" >GENERATED WORK ORDER</option>
										</select>
				               		</div>
			               		</div>
		               		</div>
	            	</div>	
	            </div>
	            
	           	 <div class="card-footer" align="center">
					<a href="WorkOrder_Search_Computing_Asset_Url" class="btn btn-success btn-sm" >Clear</a>
	              	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
	          	</div>
	            				
		</div>
	  <div class="col-md-12">
			<table id="Computing_Asset_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>					
						<th>Computing Assets Name</th>
						<th>Make Name</th>
						<th>Model Name</th>
						<th>Model Number</th>
						<th>Machine Number</th>
						<th>MAC Address</th>
						<th>IP Address</th>
						<th>Action</th>
			    	</tr>
				</thead>
			</table>
		</div>
	</div>   
</form:form>



 <script>
 function fn_makeName() {
		
		
		var assets_name = $("select#asset_type").val();
		$.post("getMakeNameList?" + key + "=" + value, {
			assets_name: assets_name
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#make_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 function fn_modelName() {
		var make_name = $("select#make_name").val();
		$.post("getModelNameList?" + key + "=" + value, {
			make_name: make_name
		}).done(function(j) {
		
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#model_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 </script>
 


	<script type="text/javascript">
	
	$(document).ready(function() {  
		
		mockjax1('Computing_Asset_reporttbl');
		table = dataTable('Computing_Asset_reporttbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
		
	});
	
	function data(tableName){
		if(tableName == "Computing_Asset_reporttbl"){
			jsondata = [];
			var table = $('#'+tableName).DataTable();
			var info = table.page.info();
		
			var currentPage = info.page;
		    var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];
			
			var asset_type=$("#asset_type").val();
		 	var make_name=$("#make_name").val();
		 	var model_name=$("#model_name").val();
		 	var status=$("#status").val();
		 	
		
			$.post("Computing_Asset_List_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,
				orderType:orderType,asset_type:asset_type,make_name:make_name,model_name:model_name,status:status
				},function(j) {
					
				for (var i = 0; i < j.length; i++) {
					jsondata.push([j[i].assets_name, j[i].make_name,j[i].model_name,j[i].model_number,
						j[i].machine_number,j[i].mac_address,j[i].ip_address,j[i].action]);
				}
			});
			$.post("Computing_Asset_List_TotalCount?"+key+"="+value,{Search:Search,asset_type:asset_type,make_name:make_name,model_name:model_name,status:status},function(j) {
				FilteredRecords = j;
			});
		}
	}
	
	function EditData(id){        
	    $("#idV").val(id);                
	    document.getElementById('EditForm').submit();            
	}
	

	function Downloaddata(id,wo_id){
		$("#Hid_id").val(id);
		$("#H_wo_id").val(wo_id);
		document.getElementById('downloadForm').submit();		 
	}
</script>


<c:url value="Computing_Asset_WO_Url" var="EditUrl"/>
	<form:form action="${EditUrl}" method="post" id="EditForm" name="EditForm" modelAttribute="idV">
	  <input type="hidden" name="idV" id="idV" value="0">
</form:form> 

<c:url value="Download_Computing_Asset_WO" var="downloadUrl"/>
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="Hid_id">
   <input type="hidden" name="Hid_id" id="Hid_id" value="0">
    <input type="hidden" name="H_wo_id" id="H_wo_id" value="0">
</form:form>
