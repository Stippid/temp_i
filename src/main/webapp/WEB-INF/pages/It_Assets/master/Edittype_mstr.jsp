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
	action="Edittype_mstrAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="Edittype_mstrCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>UPDATE MODEL TYPE MASTER</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="col-md-12 display_none" id="divLine">
					<span class="line"></span>
				</div>
				<form:input type="hidden" id="id" path="id"
					value="${Edittype_mstrCMD1.id}"></form:input>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF PERIPHERAL</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:select id="peripheral_type" path="peripheral_type"
									class="form-control">
									<option value="">--Select--</option>
									<c:forEach var="item" items="${CategoryList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
								</form:select>
							</div>
						</div>
					</div>

					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">TYPE OF HARDWARE</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:select id="type_of_hw" path="type_of_hw"
									class="form-control">
									<option value="">--Select--</option>
									<%--          <c:forEach var="item" items="${gettypeofhwListDDL}" varStatus="num"  > --%>
									<%--                <option value="${item.id}" name="${item.type_of_hw}" <c:if test="${item.id eq Edittype_mstrCMD1.type_of_hw}">selected="selected"</c:if>>${item.type_of_hw}</option> --%>
									<%--          </c:forEach> --%>
								</form:select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 display_none" id="divLine">
					<span class="line"></span>
				</div>
				<div class="row">
					<div class='col-md-6'>
						<div class='row form-group'>
							<div class='col col-md-4'>
								<strong class="color_red">*</strong> <label for="text-input"
									class="form-control-label">MODEL TYPE</label>
							</div>
							<div class='col-12 col-md-8'>
								<form:input type="text" id="type" path="type"
									value="${Edittype_mstrCMD1.type}" class="form-control"
									autocomplete='off' maxlength="50"
									oninput="this.value = this.value.toUpperCase()"></form:input>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Type_mstr_Url class="btn btn-success btn-sm">Back</a> <input
					type='submit' class='btn btn-primary btn-sm' value='Update'
					id="update_btn" />
			</div>
		</div>
	</div>
</form:form>



<script nonce="${cspNonce}">
	$(document).ready(
			function() {
				$('#peripheral_type').on('change', function() {
					fn_Peripheral();
				});

				document.getElementById('update_btn').onclick = function() {
					return isValidateClientSide();
				};

				$.ajaxSetup({
					async : false
				});

				$("select#peripheral_type").val(
						"${Edittype_mstrCMD1.peripheral_type}");
				fn_Peripheral();
				$("select#type_of_hw").val("${Edittype_mstrCMD1.type_of_hw}");

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

		if ($("select#peripheral_type").val() == 0) {
			alert("Please Select Type Of Peripheral");
			$("select#peripheral_type").focus();
			return false;
		}

		if ($("select#type_of_hw").val() == 0) {
			alert("Please Enter Type of Hardaware");
			$("select#type_of_hw").focus();
			return false;
		}
		if ($("input#type").val().trim() == "") {
			alert("Please Enter Model Type");
			$("input#type").focus();
			return false;
		}
		return true;
	}

	function fn_Peripheral() {

		var peripheral_type = $("select#peripheral_type").val();
		$
				.post("getHWNameList?" + key + "=" + value, {
					peripheral_type : peripheral_type
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#type_of_hw").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</script>
