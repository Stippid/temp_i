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
<form:form name="EditFormname" id="EditFormid" action="Editssd_mstrAction?${_csrf.parameterName}=${_csrf.token}" method="POST" modelAttribute="Editssd_mstrCMD">
<div class="container" align="center">
	<div class="card">
    <div class="card-header"> <h5>UPDATE SSD CAPACITY MASTER</h5></div>
       <div class="card-body card-block cue_text">
<div class="col-md-12 display_none" id="divLine" ><span class="line"></span></div>
<form:input type="hidden" id="id" path="id" value="${Editssd_mstrCMD1.id}" ></form:input>
 <div class='col-md-12'>
 
  <div class='col-md-12'>
	  
	      <div class='col-md-2'>
	           <strong class="color_red">*</strong> <label for="text-input" class="form-control-label">SSD CAPACITY</label>
          </div>
          <div class='col-md-3'>
	             <input type="text" id="ssd" name="ssd"  class="form-control text_transform_upper" autocomplete='off' maxlength="10" value="${Editssd_mstrCMD1.ssd}"
	   />
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
    </div>
       <div class='card-footer' align='center'>
          <a href=ssdd_mstr_Url class="btn btn-success btn-sm">Back</a>
           <input type='submit' class='btn btn-primary btn-sm' value='Update' id="update_btn" onclick='return isValidateClientSide()'>
       </div>
    </div>
  </div>
</form:form>



 <script nonce="${cspNonce}">
 document.addEventListener('DOMContentLoaded', function() {
	 document.getElementById('ssd').onkeypress = function() {
			return digitKeyOnly(event);	
	  	};
	  	document.getElementById('ssd').onkeyup = function() {
		return specialcharecterhdd(this);	
  	};
  	document.getElementById('update_btn').onclick = function() {
		return isValidateClientSide();	
  	};
  	
 });
$(document).ready(function () {
	
	
	var str = '${Editssd_mstrCMD1.ssd}';
		str = str.substring(0, str.length - 2);
		var l = '${Editssd_mstrCMD1.ssd}'.length;
		//alert(l);
		  $("#ssd").val(str);
		  $("#size").val('${Editssd_mstrCMD1.ssd}'.substring(l, l-2));
	
	/*   $("#hdd").val('${Edithdd_mstrCMD1.hdd}'.substring(0, 3));
	  $("#size").val('${Edithdd_mstrCMD1.hdd}'.substring(3, 5)); */
	
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
	
  if($("input#ssd").val().trim() == "")
   {
	  alert("Please Enter SSD Capacity");
	  $("input#ssd").focus();
	  return false;
   } 
  if($("#size").val() == 0)
  {
	  alert("Please Select Size");
	  $("input#size").focus();
	  
	  return false;
	   }
  return true;
  
  } 
 
 function digitKeyOnly(e) {
	  var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
	  var value = Number(e.target.value + e.key) || 0;

	  if ((keyCode >= 37 && keyCode <= 40) || (keyCode == 8 || keyCode == 9 || keyCode == 13) || (keyCode >= 48 && keyCode <= 57 || keyCode == 46)) {
	    return isValidNumber(value);
	  }
	  return false;
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
