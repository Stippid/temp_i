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
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"
	nonce="${cspNonce}">
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
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
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
	action="EditConnectivity_Action?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="EditConnectivity_CMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>UPDATE CONNECTIVITY TECHNOLOGY MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<!-- 				<div class="col-md-12" id="divLine" > -->
				<!-- 					<span class="line"></span> -->
				<!-- 				</div> -->
				<form:input type="hidden" id="id" path="id"
					value="${EditConnectivity_CMD1.id}"></form:input>
				<div class="row">
					<div class='col-md-6'>
						<div class='col col-md-4'>
							<strong class="color_red">*</strong> <label for="text-input"
								class="form-control-label">CONNECTIVITY TYPE</label>
						</div>
						<div class='col-12 col-md-8'>
							<form:input type="text" id="connectivity_type"
								path="connectivity_type"
								class="form-control text_transform_upper" autocomplete='off'
								maxlength="100"></form:input>
						</div>

					</div>
				</div>
			</div>
			<div class="card-footer" align="center">
				<a href="CONNECTIVITY_Url" class="btn btn-success btn-sm">Back</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Update'
					id="update_btn" />
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

	$(document).ready(
			function() {
				$.ajaxSetup({
					async : false
				});
				$("input#connectivity_type").val(
						"${EditConnectivity_CMD1.connectivity_type}");
				try {
					if (window.location.href.includes("msg=")) {
						var url = window.location.href.split("?msg")[0];
						window.location = url;
					}
				} catch (e) {

				}
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

		if ($("input#connectivity_type").val().trim() == "") {
			alert("Please Enter Connectivity Type");
			$("input#connectivity_type").focus();
			return false;
		}

		return true;
	}
</script>
