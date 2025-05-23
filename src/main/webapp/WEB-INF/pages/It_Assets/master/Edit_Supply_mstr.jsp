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
<form:form name="Formname" id="Formid"
	action="EditVENDORAction?${_csrf.parameterName}=${_csrf.token}"
	method="POST" modelAttribute="EditVENDORCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Edit Vendor  MASTER</h5>
			</div>
			<form:input type="hidden" id="id" path="id" value="${EditVENDORCMD.id}" ></form:input>
			<div class="card-body card-block cue_text">
				<div class="col-md-12 display_none" id="divLine"">
					<span class="line"></span>
				</div>
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-6'>
							<strong class="color_red">*</strong> <label for="text-input"
								class="form-control-label">Supply Master</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="supplier" path="supplier" value="${EditVENDORCMD.supplier}"
								class="form-control text_transform_upper" autocomplete='off'
								maxlength="100"></form:input>
						</div>
					</div>
				</div>
				
				<div class='col-md-6'>
					<div class='row form-group'>
						<div class='col col-md-6'>
							<strong class="color_red">*</strong> <label for="text-input"
								class="form-control-label">Mobile Number</label>
						</div>
						<div class='col-12 col-md-6'>
							<form:input type="text" id="contact_number" path="contact_number" value="${EditVENDORCMD.contact_number}"
								class="form-control text_transform_upper" autocomplete='off'
								maxlength="10"></form:input>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<a href=Supply_Mstrurl class="btn btn-success btn-sm">Back</a>
				<input type='submit' class='btn btn-primary btn-sm' value='Update'
					id="update_btn" /> <i class="fa fa-search"></i>
			</div>
		</div>
	</div>
</form:form>
<c:url value="deleteSUPPLYUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>
<c:url value="EditSUPPLYUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" />
</form:form>
<c:url value="MAINTENANCEmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="maintenance1" id="maintenance1" value="0" />

</form:form>
