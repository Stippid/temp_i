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
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"
	nonce="${cspNonce}">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form:form name="EditFormname" id="EditFormid"
	action="EditProcessor_or_typeAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="EditProcessor_or_typeCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>UPDATE TYPE OF PROCESSOR MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12 display_none" id="divLine">
					<span class="line"></span>
				</div>
				<form:input type="hidden" id="id" path="id"
					value="${EditProcessor_or_typeCMD1.id}"></form:input>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF PROCESSOR</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="processor_type"
									path="processor_type"
									value="${EditProcessor_or_typeCMD1.processor_type}"
									class="form-control text_transform_upper" autocomplete='off'
									maxlength="100"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Processor_or_type_Url class="btn btn-success btn-sm">Back</a>
				<input type='submit' class='btn btn-primary btn-sm' value='Update'
					id="update_btn">
			</div>
		</div>
	</div>
</form:form>



<script nonce="${cspNonce}">
	$(document).ready(function() {
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		} catch (e) {

		}

	});

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('update_btn').onclick = function() {
			return isValidateClientSide();
		};
		document.getElementById('processor_type').onclick = function() {
			return specialcharecterProcessor(this);
		};
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

		if ($("input#processor_type").val().trim() == "") {
			alert("Please Enter Type Of Processor");
			$("input#processor_type").focus();
			return false;
		}
		return true;
	}

	function specialcharecterProcessor(a) {
		var iChars = "@#^&*$,.:;%!+_-[]";
		var data = a.value;
		for (var i = 0; i < data.length; i++) {
			if (iChars.indexOf(data.charAt(i)) != -1) {
				alert("This " + data.charAt(i)
						+ " special characters not allowed.");
				a.value = "";
				return false;
			}
		}
		return true;
	}
</script>
