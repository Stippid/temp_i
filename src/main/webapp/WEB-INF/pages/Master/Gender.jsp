<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
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

<form:form  action="gender_masterAction" method='POST' modelAttribute="gender_masterCmd" > 
<div class="animated fadeIn">
   		<div class="container" align="center">
 			<div class="card">
	   			<div class="card-header"><h5>Gender MASTER</h5></div>
	          		<div class="card-body card-block">
	            		<div class="col-md-12">	            					
	              			<div class="col-md-6">
	              				<div class="row form-group">	              			 	
               						<div class="col-md-4">
                 						<label for="text-input" class=" form-control-label">GENDER<strong style="color: red;">*</strong></label>
               						</div>
               						<div class="col-md-8">
	               						<input type="text" id="gender" name="gender" oninput="this.value = this.value.toUpperCase()" maxlength="50" class="form-control autocomplete" style="text-transform: uppercase" autocomplete="off" onkeypress="return onlyAlphabets(event, this)"  />
									</div> 							
	  							</div>
	  						</div>
	  						       					
	  					</div>	
	  				</div>
					<div class="card-footer" align="center" class="form-control">
						<a href=Gender_Url class="btn btn-success btn-sm" >Clear</a>     
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
	              		<i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search" onclick="Search();"> 
	              		
		            </div> 
	        	</div>
			</div>
	</div>
	<div align="right" class="container">
	<i class="fa fa-file-excel-o" id="btnExport" style="font-size:x-large; color:green;text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div><br>
	<div class="container">
		<table id="getgenderSearch" class="display">
			<thead>
				<tr>
					<th id="2">gender Name</th>
					<th class="action" >Action</th>
				</tr>
			</thead>
		</table>
	</div>
</form:form>

<c:url value="Edit_gender_Url" var="Edit_gender_Url" />
<form:form action="${Edit_gender_Url}" method="post" id="updateForm" name="updateForm" modelAttribute="id1" >
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<c:url value="Delete_gender" var="delete_Url" />
<form:form action="${delete_Url}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0"/> 
</form:form> 
<c:url value="Makegenderreport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="gender1" id="gender1" value="0" />

</form:form> 
<script type="text/javascript">

function data(getgenderSearch){
	jsondata = [];

	var table = $('#'+getgenderSearch).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = $(table.column(order[0][0]).header()).attr('id').toLowerCase();
	var orderType = order[0][1];
	
	var gender=$("#gender").val() ;
	
	$.post("getFiltergender?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
		,gender:gender},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].gender,j[i].action]);
		}
	});
	$.post("getTotalgenderCount?"+key+"="+value,{Search:Search,gender:gender},function(j) {
		FilteredRecords = j;
	});
}

</script>
<script type="text/javascript">
$(document).ready(function() {

	mockjax1('getgenderSearch');
	table = dataTable('getgenderSearch');
	$('#btn-reload').on('click', function(){
    	table.ajax.reload();
    });
});
</Script>

<Script>
function getExcel() {
	var gender=$("#gender").val() ;

	$("#gender1").val(gender);
 	
	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();
} 
function Validate() {
	
	
	if ($("#gender").val().trim() == "") {
		alert("Please Enter gender");
		$("#gender").focus();
		return false;
	}
	return true;
}
function onlyAlphabets(e, t) {
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
} 

function editData(id){	
	
	$("input#id1").val(id);
	document.getElementById('updateForm').submit();
}

function deleteData(id) {
	$("#id2").val(id);
	document.getElementById('deleteForm').submit();
}

</Script>