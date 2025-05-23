<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link rel="stylesheet"
	href="js/dataTableAlignment/adjust.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<style>

.modal{
 position: fixed;
    top: 15%;
    left: -20%;
     height: 100% ; 
    
}
.modal-body {
    position: relative;
    overflow-y: auto;
    max-height: 400px;
    padding: 15px;
}

 .modal-content {
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 50%;
} 
.popupClose {
  color: #dc3545;
 /* float: right !important; */
  font-size: 28px;
  font-weight: bold;
}
.popupClose:hover,
.popupClose:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
</style>
<form:form  name="Pop_Up_Peripharal_Asset_WO" id="Pop_Up_Peripharal_Asset_WO"  method='POST'   >
	<div class="col-md-12" align="center">
	   <div class="card">
	  		<div class="card-header"><h5> SEARCH PERIPHERAL WORK ORDER</h5></div>
			<div class="card-body card-block">
	           <div class="col-md-12">
	            	  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Assets Type</label>
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
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Work Order Number</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="wo_no" id="wo_no" class="form-control"/>
								</div>

							</div>
						</div>
						
	            	</div>
	            	<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>W/O Dt</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="wo_dt" id="wo_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong> Dt fwd to Wksp</label>
								</div>
								<div class="col-md-8">
								<input type="text" name="dt_eqpt_reqd_fwd_wksp" id="dt_eqpt_reqd_fwd_wksp" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>
							</div>
						</div>
						</div>
						<div class="col-md-12">
	            	  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Work Unit Name</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="wksp_unit_name" name="wksp_unit_name" class="form-control autocomplete" autocomplete="off"/>
								</div>

							</div>
						</div>  
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label for="text-input" class=" form-control-label"><strong style="color: red;"> </strong>Defect Objects</label>
								</div>
								<div class="col-md-8">
									<input type="text" name="defects_obs" id="defects_obs" class="form-control"/>
								</div>

							</div>
						</div>
						
	            	</div>
	            </div>
	           	 <div class="card-footer" align="center">
					<a href="" class="btn btn-success btn-sm" >Clear</a>
	              	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
	          	</div>
	            				
		</div>
	  <div class="col-md-12">
			<table id="WO_Peripheral_Asset_reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>					
						<th>Work Order No</th>
						<th>Work Unit Name</th>
						<th>Work Order Date</th>
						<th>Date Of Equipement</th>
						<th>Computing Assets Type</th>
						<th>Defect Objects</th>
						<th>No Of Work Order</th>
					 	<th>ACTION</th>
						
			    	</tr>
				</thead>
			</table>
		</div>
	</div> 
	<!--  Modal Start -->
	 <div class="modal" id="myPeripheralHistoryModal">
	 <div class="modal-content">
	 <span class="popupClose" data-dismiss="modal">&times;</span>
	  <div class="modal-body">
		<table class="table no-margin table-striped  table-hover  table-bordered table-primary" id="PopUp_Tb_Peripharal_History">
			<thead>
				<tr>
					
						<th>Work Order No</th>
						<th>Defects Observation</th>
				</tr>
			</thead>
			<tbody id="peripheral_PopTbbody">
			<tr id="tr_id_Peripheralpopup">
			</tr>
			</tbody>
		</table>
		</div>
	</div>
  </div>  
  
  <!-- Modal End -->
</form:form>

	<script type="text/javascript">
	
	$(document).ready(function() {  
		datepicketDate('wo_dt');
		datepicketDate('dt_eqpt_reqd_fwd_wksp');
		mockjax1('WO_Peripheral_Asset_reporttbl');
		table = dataTable('WO_Peripheral_Asset_reporttbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
		
	});
	
	function data(tableName){
		if(tableName == "WO_Peripheral_Asset_reporttbl"){
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
			
			var assets_type	= $("#assets_type").val();
			var wo_no = $("#wo_no").val();
			var wo_dt = $("#wo_dt").val();
			var dt_eqpt_reqd_fwd_wksp = $("#dt_eqpt_reqd_fwd_wksp").val();
			var wksp_unit_name = $("#wksp_unit_name").val();
			var defects_obs = $("#defects_obs").val();
			
			$.post("Pop_UP_Peripheral_Asset_History_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,
				orderType:orderType,assets_type:assets_type,
				wo_no:wo_no,
				wo_dt:wo_dt,
				dt_eqpt_reqd_fwd_wksp:dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name:wksp_unit_name,
				defects_obs:defects_obs},function(j) {
					
				for (var i = 0; i < j.length; i++) {
					jsondata.push([
						j[i].wo_no,
						j[i].wksp_unit_name,
						j[i].wo_dt,
						j[i].dt_eqpt_reqd_fwd_wksp,
						j[i].assets_name,
						j[i].defects_obs,
						j[i].history,
						j[i].action
						]);
				}
			});
			$.post("Pop_UP_Peripheral_Asset_History_List_TotalCount?"+key+"="+value,{Search:Search,assets_type:assets_type,
				wo_no:wo_no,
				wo_dt:wo_dt,
				dt_eqpt_reqd_fwd_wksp:dt_eqpt_reqd_fwd_wksp,
				wksp_unit_name:wksp_unit_name,
				defects_obs:defects_obs},function(j) {
				FilteredRecords = j;
			});
		}
	}
	function historyData(id){  
		
		$.post("pop_up_detail_Peripheral_Asset_History_RecordDataList?"+key+"="+value,{id:id},function(j) {
			
			 if(j.length>0){
				 $('#peripheral_PopTbbody').empty();
	 	        	for ( var i = 0; i < j.length; i++) {
	 	        		 $("#peripheral_PopTbbody").append(' <tr id="tr_id_Peripheralpopup'+i+'">'
	 	    					+'<td align="center">'+j[i].wo_no+'</td>'
	 	    					+'<td align="center">'+j[i].defects_obs+'</td>'
	 						    +'</tr>');
	 	        	
	 	        	 }
				}
				else{
					
			 	}
			 }).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			 }); 
		}
	
	function Downloaddata(id){

		$("#Hid_id").val(id);	
// 		$("#wksp_unit_name1").val(wksp_unit_name);
// 		$("#wo_dt1").val(wo_dt);
// 		$("#dt_eqpt_reqd_fwd_wksp1").val(dt_eqpt_reqd_fwd_wksp);
		document.getElementById('typeReport').value='pdfL';
		document.getElementById('downloadForm').submit();		 
	}
</script>
		<c:url value="Download_Peripheral_Asset_WO" var="downloadUrl"/>
		<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="Hid_id">
		   <input type="hidden" name="Hid_id" id="Hid_id" value="0">
		   <input type="hidden" name="typeReport" id="typeReport" value="0" />
<!-- 			<input type="hidden" name="wksp_unit_name1" id="wksp_unit_name1">  -->
<!-- 			<input type="hidden" name="wo_dt1" id="wo_dt1" value="0"> -->
<!-- 			<input type="hidden" name="dt_eqpt_reqd_fwd_wksp1" id="dt_eqpt_reqd_fwd_wksp1" > -->
		    
		</form:form>	
			

