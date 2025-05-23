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

<form:form  action="Edit_Country_masterAction" method='POST' modelAttribute="Edit_Country_masterCmd" >
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>UPDATE COUNTRY MASTER</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class=" form-control-label"> Country <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-md-8">
									<input type="text" id="country" name="country"
										class="form-control autocomplete"  maxlength="50"
										style="text-transform: uppercase" autocomplete="off" onkeypress="return onlyAlphabets(event, this)"  />
								</div>
								<input type="hidden" id="id" name="id" class="form-control"
									autocomplete="off" value="0">
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href=Country_Url class="btn btn-danger btn-sm">Cancel</a>
					<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();">

				</div>
			</div>
		</div>
	</div>
</form:form>

<Script>

$(document).ready(function() {
	
	$('input#country').val('${Edit_Country_masterCmd.country}');
	$("#id").val('${Edit_Country_masterCmd.id}');
});
function onlyAlphabets(e, t) {
    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
} 

function Validate() {
	
	if ($("#country").val().trim() == "") {
		alert("Please Enter Country");
		$("#country").focus();
		return false;
	}
	return true;
}
</Script>