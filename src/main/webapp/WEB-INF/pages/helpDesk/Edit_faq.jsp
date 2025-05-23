<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css" nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css" nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/scss/style.css" nonce="${cspNonce}">
<script src="js/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>

<form:form   name="Edit_faq" id="Edit_faq" action="Edit_faqAction" method='POST' modelAttribute="Edit_faqCMD">

<div class="container" align="center">
 <div class="card">
	<div class="card-header"> <h5> UPDATE FAQ</h5></div>
	  <div class="card-body card-block">
			  <div class="col-md-12 form-group">	
	             		<div class="col-2 col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Question</label>
		                </div>
		                <div class="col-10 col-md-10">
						  <input id="id" name="id" type="hidden" value="${Edit_faqCMD.id}">
						  <textarea rows="2" cols="250" id="question" name="question" class="form-control text-transform_none" maxlength="255" >${Edit_faqCMD.question}</textarea>
						</div>
               </div>
			    <div class="col-md-12 form-group">	
	             		<div class="col-2 col-md-2">	             		
		                	<label for="text-input" class=" form-control-label">Answer</label>
		                </div>
		                <div class="col-10 col-md-10">
						 <textarea id="answer" name="answer" class="form-control text-transform_none height150"  rows="2" cols="250" >${Edit_faqCMD.answer}</textarea>
						</div>
            	</div>
			</div>
	        <div class="card-footer" align="center">
	          <input type="submit" class="btn btn-primary btn-sm" value="Update" id="update_btn"> 
           </div> 
    </div>
</div>

</form:form> 
<script nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function() {

	document.getElementById('update_btn').onclick = function() {
		 return isvalidData();
  	};
  
  	
});


function isvalidData()

{
	if($("textarea#question").val() == "")
	{
		alert("Please Enter Question");
		$("textarea#question").focus();
		return false;
	}
	if($("textarea#answer").val() == "")
	{
		alert("Please Enter Answer");
		$("textarea#answer").focus();
		return false;
	}
	return true; 
}
</script>