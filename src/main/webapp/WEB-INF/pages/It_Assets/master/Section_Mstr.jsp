<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript" nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript" nonce="${cspNonce}"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>


<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="SECTIONAction?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="SectionMasterCmd">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SECTION MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12 display_none" id="divLine"><span class="line"></span></div>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong class="color_red">*</strong> <label for="text-input" class="form-control-label">SECTION </label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="section" path="section"  class="form-control text_transform_upper" autocomplete='off' maxlength="100" 
	              ></form:input>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
        <a href="Section_Mstr_Url" class="btn btn-success btn-sm">Clear</a>
        <input type='submit' class='btn btn-primary btn-sm' value='Save'  id="submitbtn"/>
        <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditSECTIONUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deleteSECTIONUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>

<c:url value="SECTIONmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="section1" id="section1" value="0" />
	
</form:form> 

<div align="right" class="container">
		<i class="fa fa-file-excel-o fxlarge color_green text_align_right" id="btnExport"
			aria-hidden="true" title="EXPORT TO EXCEL"></i>
	</div>
	<br>


<div class="container" align="center">
<div class="col-md-12">
<table id="Section_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print">
	<thead>
        <tr>
		  <th>Section Name</th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script nonce="${cspNonce}">

 document.addEventListener('DOMContentLoaded', function () {
 	
 	document.getElementById('btnExport').onclick = 
 		function() {
 		return getExcel();
   	};
 	document.getElementById('submitbtn').onclick = 
 		function() {
 		return isValidateClientSide();
   	};
//     document.querySelectorAll('.action_delete').forEach(function(button) {
//         button.addEventListener('click', function() {
//             var encryptedPk = this.getAttribute('data-encrypted-pk');
//             if (confirm('Are you sure you want to Delete?')) {
//                 deleteData(encryptedPk);
//             }
//         });
//     });

//     // Handle update button click
//     document.querySelectorAll('.action_update').forEach(function(button) {
//         button.addEventListener('click', function() {
//             var encryptedPk = this.getAttribute('data-encrypted-pk');
//             if (confirm('Are you sure you want to Update?')) {
//                 editData(encryptedPk);
//             }
//         });
//     });
   	document.addEventListener('click', function(event) {
        if (event.target.classList.contains('action_update')) {
                var encryptedPk = event.target.getAttribute('data-encrypted-pk');
                if (confirm('Are you sure you want to Update?')) {
                        editData(encryptedPk);
                }
        } else if (event.target.classList.contains('action_delete')) {
                var encryptedPk = event.target.getAttribute('data-encrypted-pk');
                if (confirm('Are you sure you want to Delete?')) {
                        deleteData(encryptedPk);
                }
        }
});
 });

$(document).ready(function () {

mockjax1('Section_reporttbl');
table = dataTable('Section_reporttbl');
$('#btn-reload').on('click', function(){
	table.ajax.reload();
});


});
function data(tableName){
	jsondata = [];
	var table = $('#Section_reporttbl').DataTable();
	var info = table.page.info();

	var section =$("#section").val();
	
	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getSECTIONReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,section:section},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].section,j[i].id]);
		}
	});
	$.post("getSECTIONTotalCount?"+key+"="+value,{Search:Search,section:section},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {

  if($("#section").val().trim() == "")
   {
	  alert("Please Enter Section Name");
	  $("#section").focus();
	  return false;
   } 
  return true; 
  } 
 function editData(obj){

	    document.getElementById('updateid').value=obj;
	    document.getElementById('updateForm').submit();
}


 function deleteData(obj){

	    document.getElementById('deleteid').value=obj;
	    document.getElementById('deleteForm').submit();
}
 
 function getExcel() {
		
		var antivirus=$("#section").val();
		$("#section1").val(antivirus);
	 	
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	} 
 </script>
