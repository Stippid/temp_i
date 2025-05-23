<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"
	nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script nonce="${cspNonce}" src="js/miso/commonJS/commonmethod.js"
	type="text/javascript"></script>
<script nonce="${cspNonce}" src="js/miso/orbatJs/orbatAll.js"
	type="text/javascript"></script>
<link nonce="${cspNonce}" href="js/cue/cueWatermark.css"
	rel="Stylesheet"></link>
<link nonce="${cspNonce}" rel="stylesheet"
	href="js/InfiniteScroll/css/datatables.min.css">
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script nonce="${cspNonce}" type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="EditFormname" id="EditFormid"
	action="Editups_type_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="Editups_type_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>EDIT UPS TYPE MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12" id="divLine">
					<span class="line"></span>
				</div>
				<form:input type="hidden" id="id" path="id"
					value="${Editups_type_mstrCMD1.id}"></form:input>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF UPS</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="ups_type" path="ups_type"
									value="${Editups_type_mstrCMD1.ups_type}" class="form-control"
									autocomplete='off' maxlength="50"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Ups_type_mstr_Url class="btn btn-success btn-sm">Back</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Update'
					id="update_btn">
			</div>
		</div>
	</div>
</form:form>


<script nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('update_btn').onclick = function() {
			return isValidateClientSide();
		};

	});

	$(document).ready(function() {

	});

	function ParseDateColumncommission(data) {

		var date = "";
		if (data != null && data != "") {
			var d = new Date(data);
			var day = ('0' + d.getDate()).slice(-2);
			var month = ('0' + (d.getMonth() + 1)).slice(-2);
			var year = "" + d.getFullYear();
			date = day + "/" + month + "/" + year;
		}
		return date;
	}
	function isValidateClientSide() {

		if ($("input#ups_type").val().trim() == "") {
			alert("Please Enter Type Of UPS");
			$("input#ups_type").focus();
			return false;
		}
		return true;
	}
</script>
