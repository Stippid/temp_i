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
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript" nonce="${cspNonce}"></script>
<c:if test='${not empty msg}'>
<input type='hidden' name='msg' id='msg' value='${msg}'/>
</c:if>
<form:form name="EditFormname" id="EditFormid" action="EditSECTIONAction?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="EditSECTIONCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>UPDATE SECTION MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12 display_none" id="divLine"><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${EditSECTIONCMD1.id}" ></form:input>
 <div class='col-md-6'>
	  <div class='row form-group'>
	      <div class='col col-md-4'>
	           <strong class="color_red">*</strong> <label for="text-input" class="form-control-label">SECTION NAME</label>
          </div>
          <div class='col-12 col-md-6'>
	             <form:input type="text" id="section" path="section" value="${EditSECTIONCMD1.section}"
	            class="form-control text_transform_upper" autocomplete='off' maxlength="100" ></form:input>
        </div>
    </div>
</div>
    </div>
       <div class='card-footer' align='center'>
          <a href="SECTION_Url" class="btn btn-success btn-sm">Back</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Update' id="submitid"/>
       </div>
    </div>
  </div>
</form:form>



 <script nonce="${cspNonce}">

 document.addEventListener('DOMContentLoaded', function () {
 	
 	document.getElementById('submitid').onclick = 
 		function() {
 		return isValidateClientSide();
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
//alert("${EditSECTIONCMD1.id}");
});



function ParseDateColumncommission(data) {

	  var date="";
	  if(data!=null && data!=""){
		 var d = new Date(data);
		 var day=('0' + d.getDate()).slice(-2);
		 var month=('0' + (d.getMonth() + 1)).slice(-2);
		 var year=""+d.getFullYear();
		 date=day+"/"+month+"/"+year;
		   		}
		    return date;
	}
 function isValidateClientSide()
  {

  if($("input#section").val().trim() == "")
   {
	  alert("Please Enter Antivirus Name");
	  $("input#section").focus();
	  return false;
 
   } 
  return true; 
  }
 </script>
