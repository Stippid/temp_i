<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script nonce="${cspNonce}"src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script nonce="${cspNonce}"type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script nonce="${cspNonce}"type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script nonce="${cspNonce}"type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script nonce="${cspNonce}"src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link nonce="${cspNonce}"href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script nonce="${cspNonce}"src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script nonce="${cspNonce}"src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script nonce="${cspNonce}"src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link nonce="${cspNonce}"href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link nonce="${cspNonce}"rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script nonce="${cspNonce}"type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script nonce="${cspNonce}"type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script nonce="${cspNonce}"type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>


<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="Formname" id="Formid" action="ssd_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="ssd_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>SSD CAPACITY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12 display_none" id="divLine" ><span class="line"></span></div>
 <div class='col-md-12'>
	  
	      <div class='col-md-2'>
	           <strong class="color_red">*</strong> <label for="text-input" class="form-control-label">SSD CAPACITY</label>
          </div>
          <div class='col-md-3'>
	             <input type="text" id="ssd" name="ssd"  class="form-control text_transform_upper" autocomplete='off' maxlength="10" 
	       onkeypress="return digitKeyOnly(event)" onkeyup="return specialcharecterhdd(this);" />
	              <!--  onkeypress="return digitKeyOnly(event)"  -->
        </div>
        <div class='col-md-2'>
        <select name="size" id="size" class="form-control">
											<option value="0">--Select--</option>
											   <option value="MB">MB</option>
											    <option value="GB">GB</option>
											    <option value="TB">TB</option>
										 </select>
         </div>
   
</div>
    </div>
       <div class='card-footer' align='center'>
       
           <a href=Ssd_mstr_Url class="btn btn-success btn-sm">Clear</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Save' id="save_btn">
           <i class="fa fa-search"></i><input type="button" class="btn btn-info btn-sm" id="btn-reload" value="Search">
       </div>
    </div>
  </div>
</form:form>



<c:url value="EditSsd_mstrUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
<input type="hidden" name="updateid" id="updateid" />
</form:form>


<c:url value="deletessd_mstrUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteid">
<input type="hidden" name="deleteid" id="deleteid"/>
</form:form>


<c:url value="SSDmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="hdd1" id="hdd1" value="0" />
	<input type="hidden" name="size1" id="size1" value="0" />
</form:form> 

<div align="center" class="container">
		<i class="fa fa-file-excel-o export_excel" id="btnExport"
			
			aria-hidden="true" title="EXPORT TO EXCEL"></i>
	</div>
	<br>

<div class="container" align="center">
<div class="col-md-12">
<table id="hdd_mstr_reporttbl" class="display table no-margin table-striped  table-hover  table-bordered report_print text_align_center">
	<thead>
        <tr>
<th>SSD Capacity </th>
          <th>Action</th>
    </tr>
	</thead>
	</table>
</div>
</div>
 <script nonce="${cspNonce}">


     
    	 document.addEventListener('DOMContentLoaded', function() {
    		 document.getElementById('ssd').onkeypress = function() {
 				return digitKeyOnly(event);	
 		  	};
 		  	document.getElementById('ssd').onkeyup = function() {
				return specialcharecterhdd(this);	
		  	};
    			document.getElementById('save_btn').onclick = function() {
    				return isValidateClientSide();	
    		  	};
    		  	document.getElementById('btn-reload').onclick = function() {
    		  		table.ajax.reload();
    		  	};
    		  	document.getElementById('btnExport').onclick = function() {
    		  	return	getExcel();
    		  	};
    		  	
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

mockjax1('hdd_mstr_reporttbl');
table = dataTable('hdd_mstr_reporttbl');
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
function data(tableName){
	jsondata = [];
	var table = $('#hdd_mstr_reporttbl').DataTable();
	var info = table.page.info();
	
	var hdd =$("#ssd").val();
	var hdd_capacity = $("#size").val() ;

	var currentPage = info.page;
    var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;
	var orderType = order[0][1];

	$.post("getSsd_mstrReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,hdd:hdd,hdd_capacity:hdd_capacity},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].ssd,j[i].id]);
		}
	});
	$.post("getSsd_mstrTotalCount?"+key+"="+value,{Search:Search,hdd:hdd,hdd_capacity:hdd_capacity},function(j) {
		FilteredRecords = j;
	});
}
 function isValidateClientSide()
  {
	
  if($("input#ssd").val().trim() == "")
   {
	  alert("Please Enter SSD Capacity");
	  $("input#ssd").focus();
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
 
 function digitKeyOnly(e) {
	  var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
	  var value = Number(e.target.value + e.key) || 0;

	  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57 || keyCode == 46)) {
	    return true;
	  }
	  return false;
	}
 
 function getExcel() {
		
		var ram = $("#ssd").val() ;
		
		$("#hdd1").val(ram);
	 	
	 	document.getElementById('typeReport1').value = 'excelL';
	 	document.getElementById('searchForm').submit();
	} 
 
 function specialcharecterhdd(a)
 {
     var iChars = "@#^&*$,.:;%!+_-[]";   
     var data = a.value;
     for (var i = 0; i < data.length; i++)
     {      
         if (iChars.indexOf(data.charAt(i)) != -1)
         {    
         alert ("This " +data.charAt(i)+" special characters not allowed.");
         a.value = "";
         return false; 
         } 
     }
     return true;
 }
 </script>
