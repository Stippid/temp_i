<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"
	nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css"
	nonce="${cspNonce}">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>

<form:form
	action="Model_master_Action?${_csrf.parameterName}=${_csrf.token}"
	method='POST' modelAttribute="Model_master_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>MODEL MASTER</h5>
				</div>
				<div class="card-body card-block">
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label for="text-input"
										class=" form-control-label">CATEGORY</label>
								</div>
								<div class="col-md-8">
									<select name="category" id="category" class="form-control">
										<option value="0">--Select--</option>
										<option value="1">Computing</option>
										<option value="2">Peripheral</option>
										<option value="3">Consumable</option>
									</select> <input type="hidden" id="make_no" name="make_no"
										class="form-control" maxlength="3" value="0"
										autocomplete="off" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">ASSETS NAME</label>
								</div>
								<div class="col-md-8">
									<select name="assets_name" id="assets_name"
										class="form-control" onchange="fn_makeName();">
										<option value="0">--Select--</option>

									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">MAKE/BRAND NAME</label>
								</div>
								<div class="col-md-8">
									<select name="make_name" id="make_name" class="form-control">
										<option value="0">--Select--</option>

									</select>

								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<strong class="color_red">* </strong> <label
										class=" form-control-label">MODEL NAME</label>
								</div>
								<div class="col-md-8">
									<input id="model_name" name="model_name" maxlength="100"
										class="form-control text_transform_upper" autocomplete="off" />
								</div>
							</div>
						</div>




					</div>
				</div>
				<div class='card-footer' align='center'>
					<a href=model_masterUrl class="btn btn-success btn-sm">Clear</a> <input
						type='submit' class='btn btn-primary btn-sm' value='Save'
						id="save_btn" ' /> <i class="fa fa-search"></i><input
						type="button" class="btn btn-info btn-sm" id="btn-reload"
						value="Search">
				</div>
			</div>
		</div>
	</div>

	<br>
	<div class="container" align="center">
		<div class="card">
			<div class="card-body">
				<div class="" id="divPrint">

					<button class="download-excel-btn" id="btnExport"
						title="EXPORT TO EXCEL">
						<i class="fa fa-file-excel-o"></i> Download Excel
					</button>
					<div class="col-md-12">
						<table id="getModelSearch" class="display">
							<thead>
								<tr>
									<th id="5">Category</th>
									<th id="2">Assets Name</th>
									<th id="3">Make/Brand Name</th>
									<th id="4">Model Name</th>
									<th class="action">Action</th>

								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<%-- <c:url value="Modelireport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="veh_id1" id="veh_id1" value="0" />
	<input type="hidden" name="make_id1" id="make_id1" value="0" />
	<input type="hidden" name="Model_name1" id="Model_name1" value="0" />	
</form:form>  --%>

<c:url value="Edit_Model_Url" var="Edit_Model_Url" />
<form:form action="${Edit_Model_Url}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>

<c:url value="Delete_Model" var="delete_Url" />
<form:form action="${delete_Url}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2" value="0" />
</form:form>


<c:url value="Modelmastereport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
	<input type="hidden" name="assets_name1" id="assets_name1" value="0" />
	<input type="hidden" name="category1" id="category1" value="0" />
	<input type="hidden" name="make_name1" id="make_name1" value="0" />
	<input type="hidden" name="model_name1" id="model_name1" value="0" />

</form:form>

<script type="text/javascript" nonce="${cspNonce}">
	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('save_btn').onclick = function() {
			return isValidateClientSide();
		};
		document.getElementById('btn-reload').onclick = function() {
			table.ajax.reload();
		};
		document.getElementById('btnExport').onclick = function() {
			return getExcel();
		};

		document.getElementById('category').onchange = function() {
			return fn_Category();
		};

		document.getElementById('assets_name').onchange = function() {
			return fn_makeName();
		};

		document.addEventListener('click', function(event) {
			if (event.target.classList.contains('action_update')) {
				var encryptedPk = event.target.getAttribute('data-id');
				if (confirm('Are you sure you want to Update?')) {
					editData(encryptedPk);
				}
			} else if (event.target.classList.contains('action_delete')) {
				var encryptedPk = event.target.getAttribute('data-id');
				if (confirm('Are you sure you want to Delete?')) {
					deleteData(encryptedPk);
				}
			}
		});

	});

	function data(getModelSearch) {
		jsondata = [];

		var assets_name = $("#assets_name").val();
		var category = $("#category").val();
		var make_name = $("#make_name").val();
		var model_name = $("#model_name").val();
		var table = $('#' + getModelSearch).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id')
				.toLowerCase();

		var orderType = order[0][1];

		var category = $("#category").val();
		var assets_name = $("#assets_name").val();
		var make_name = $("#make_name").val();
		var model_name = $("#model_name").val();

		$.post("getFilteredModel?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			category : category,
			assets_name : assets_name,
			make_name : make_name,
			model_name : model_name
		}, function(j) {

			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].category, j[i].assets_name,
						j[i].make_name, j[i].model_name, j[i].action ]);
			}
		});
		$.post("getTotalModelCount?" + key + "=" + value, {
			Search : Search,
			category : category,
			assets_name : assets_name,
			make_name : make_name,
			model_name : model_name
		}, function(j) {
			FilteredRecords = j;
		});
	}

	function GetMake(obj) {
		var veh_id = obj.value;
		$.post("getMakelist?" + key + "=" + value, {
			veh_id : veh_id
		}, function(j) {
			var options = '<option value="' + "0" + '">' + "--Select--"
					+ '</option>';
			for (var i = 0; i < j.length; i++) {
				options += '<option value="' + j[i][0] + '">' + j[i][1]
						+ '</option>';
			}
			$("select#make_id").html(options);
		});
	}

	$(document).ready(function() {

		$("#model").hide();

		mockjax1('getModelSearch');
		table = dataTable('getModelSearch');
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		} catch (e) {

		}

	});

	function isValidateClientSide() {
		if ($("#category").val() == 0) {
			alert("Please Select Category");
			$("select#category").focus();
			return false;
		}

		if ($("#assets_name").val() == 0) {
			alert("Please Select Assets Name");
			$("#assets_name").focus();
			return false;
		}
		if ($("select#make_id").val() == 0) {
			alert("Please Select Make/Brand Name");
			$("select#make_id").focus();
			return false;
		}
		if ($("input#model_name").val().trim() == "") {
			alert("Please Enter Model Name");
			$("input#model_name").focus();
			return false;
		}
		return true;
	}

	function editData(id) {
		$("input#id1").val(id);
		document.getElementById('updateForm').submit();
	}

	function deleteData(id) {
		$("#id2").val(id);
		document.getElementById('deleteForm').submit();
	}

	// function getExcel() {

	// 	var veh_id=$("#veh_id").val() ;
	//  	var make_id=$("#make_id").val() ;
	//  	var Model_name=$("#model_name").val() ;

	// 	$("#veh_id1").val(veh_id);
	// 	$("#make_id1").val(make_id);
	// 	$("#Model_name1").val(Model_name);

	// 	document.getElementById('typeReport1').value = 'excelL';
	// 	document.getElementById('search2').submit();
	// } 
	function fn_Category() {

		var categogry_id = $("select#category").val();
		$
				.post("getCategoryList?" + key + "=" + value, {
					categogry_id : categogry_id
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#assets_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function fn_makeName() {

		var assets_name = $("select#assets_name").val();
		$
				.post("getMakeNameList?" + key + "=" + value, {
					assets_name : assets_name
				})
				.done(
						function(j) {
							var options = '<option   value="0">' + "--Select--"
									+ '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#make_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function getExcel() {
		var category = $("#category").val();
		var assets_name = $("#assets_name").val();
		var make_name = $("#make_name").val();
		var model_name = $("#model_name").val();

		$("#category1").val(category);
		$("#assets_name1").val(assets_name);
		$("#make_name1").val(make_name);
		$("#model_name1").val(model_name);

		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('searchForm').submit();
	}
</script>