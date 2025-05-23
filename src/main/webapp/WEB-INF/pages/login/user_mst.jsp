<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/cue/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"
	nonce="${cspNonce}"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"
	nonce="${cspNonce}"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"
	nonce="${cspNonce}"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js" nonce="${cspNonce}"></script>
<style nonce="${cspNonce}">
.display_none {
	display: none;
}

/* .dataTables_scrollBody { */
/* 	overflow-x: hidden !important; */
/* 	overflow-y: scroll !important; */
/* 	scrollbar-width: thin; */
/* } */

/* .dataTables_scrollHead { */
/* 	overflow-y: hidden !important; */
/* } */
.ui-toolbar.ui-widget-header, .dataTables_scrollHead.ui-state-default {
	width: calc(100% - 8px) !important;
}

/* .dataTables_scrollHeadInner { */
/* 	padding-right: 0 !important; */
/* 	width: 100% !important; */
/* } */
.dataTable {
	width: 100% !important;
}

.watermarked::before {
	color: #3c3838;
	opacity: 1;
	width: calc(100% - 8px) !important;
}

.dataTables_wrapper {
	opacity: 0.9;
}

#home-tab, #profile-tab {
	font-weight: bold;
}

.mb {
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
	margin-top: 10px;
}

.col-md-12.pad {
	margin-bottom: -26px;
}

.black {
	color: white;
	/* background-color: white; */
}
</style>

<form:form name="user_mst" id="user_mst" action="user_mstAction"
	method='POST' modelAttribute="user_mstCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>User Master</h5>

				<!-- 				<div class="col-md-12" align="center"> -->
				<!-- 					<div class="col-md-4"></div> -->
				<!-- 					<div class="col-md-4 "> -->
				<!-- 						<div class="row form-group mb"> -->
				<!-- 							<ul class="nav nav-tabs" id="myTab" role="tablist"> -->
				<!-- 								<li class="nav-item" role="presentation"> -->
				<!-- 									<button class="nav-link active" id="home-tab" -->
				<!-- 										data-bs-toggle="tab" data-bs-target="#home" type="button" -->
				<!-- 										role="tab" aria-controls="home" aria-selected="true">Add User</button> -->
				<!-- 								</li> -->
				<!-- 								<li class="nav-item" role="presentation"> -->
				<!-- 									<button class="nav-link" id="profile-tab" data-bs-toggle="tab" -->
				<!-- 										data-bs-target="#profile" type="button" role="tab" -->
				<!-- 										aria-controls="profile" aria-selected="false">Search User</button> -->
				<!-- 								</li> -->
				<!-- 							</ul> -->
				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 					<div class="col-md-4"></div> -->
				<!-- 				</div> -->
				<div class="row" align="center">

					<div class="col-md-12 pad">
						<div class="row form-group mb">
							<ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item" role="presentation">
									<button class="nav-link active black" id="home-tab"
										data-bs-toggle="tab" data-bs-target="#home" type="button"
										role="tab" aria-controls="home" aria-selected="true">Add
										User</button>
								</li>
								<li class="nav-item" role="presentation">
									<button class="nav-link black" id="profile-tab"
										data-bs-toggle="tab" data-bs-target="#profile" type="button"
										role="tab" aria-controls="profile" aria-selected="false">Search
										User</button>
								</li>

							</ul>
						</div>
					</div>

				</div>



			</div>
			<div class="tab-content" id="myTabContent">
				<div class="card-body card-block show active" id="home"
					role="tabpanel" aria-labelledby="home-tab">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Name <strong class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="login_name" name="login_name"
										maxlength="70" class="form-control font_familiy_awsome"
										autocomplete="off" required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Login Name<strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="user_name" name="user_name"
										maxlength="30" class="form-control" autocomplete="off"
										required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Cell Number <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="cell_number" name="cell_number"
										maxlength="30" class="form-control" autocomplete="off"
										required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Service No <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="army_no" name="army_no" maxlength="9"
										placeholder="Ex. MR123456A"
										class="form-control text_transform_upper" autocomplete="off"
										required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Password <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-11 col-md-6">
									<input id="user_password" type="password" maxlength="28"
										name="user_password" class="form-control" autocomplete="off"
										required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Re-Password <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="user_re_password" type="password" maxlength="28"
										name="user_re_password" class="form-control"
										autocomplete="off" required
										pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12"></div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Role <strong class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="user_role_id" class="form-control"
										id="user_role_id">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getRoleNameList}"
											varStatus="num">
											<option value="${item.roleId}">${item.role}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="section_div"
							name="section_div">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Section Name <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="section" class="form-control" id="section">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}">${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Rank <strong class="color_red"></strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="rank" id="rank" class="form-control ">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getTypeofRankList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" align="left">
						<label for="passid"> <b>1) Password should be a mix of
								alphabets, numerals and special characters ($#^@&%_.~!*) without
								any space in between.</b><br> <b>2) Password must contain
								both upper and lowercase letters.</b><br> <b>3) Password
								length should be between 8 to 28 characters.</b><br><b>4) Please
								Add Rank From Rank Master.</b>
						</label>
					</div>
				</div>
				<div class="card-body card-block cue_text display_none" id="profile">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Search By <strong
										class="color_red">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<select name="access_lvl" class="form-control" id="access_lvl">
										<!--onchange="getaccess_lev(this.value);" -->
										<option value="">--Select--</option>
										<option value="All">All</option>
										<option value="Username">Login Name</option>
										<option value="Loginname">Name</option>
										<option value="section">Section</option>
										
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-3">
									<label class=" form-control-label">User State</label>
								</div>
								<div class="col-md-8">
									<select name="s_state" id="s_state" class="form-control">
										<option value="-1" name="All">All</option>
										<option value="1" name="Active">Active</option>
										<option value="0" name="In-Active">Inactive</option>
										<option value="7" name="Archived">Archived</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6 display_none" id="sub_lev">
									<label for="text-input">Please Select</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="formation_se" class="form-control display_none"
										id="formation_se">
										<option value="">--Select--</option>
										<option value="Command">Command</option>
										<option value="Corps">Corps</option>
										<option value="Division">Division</option>
										<option value="Brigade">Brigade</option>
									</select> <select name="line_dte_se" class="form-control display_none"
										id="line_dte_se">
										<!-- onchange="value_pass(this.value)" -->
										<option value="">--Select--</option>
										<option value="Arm">Arm</option>
										<option value="Staff">Staff</option>
									</select> <select name="deopot_se" class="form-control display_none"
										id="deopot_se">
										<option value="">--Select--</option>
										<option value="TMS">TMS</option>
										<option value="MMS">MMS</option>
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" id="sus_div">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Section <strong
										class="color_red">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<select name="sectionList" class="form-control"
										id="sectionList">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getSectionNameList}"
											varStatus="num">
											<option value="${item.id}">${item.section}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 " id="username_div">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Login Name<strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="login_name_srch"
										class="form-control font_familiy_awsome"
										name="login_name_srch" maxlength="70"
										placeholder="&#xF002; Search" autocomplete="off">
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12 " id="loginname_div">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Name<strong class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="name_srch" class="form-control font_familiy_awsome"
										name="name_srch" maxlength="70" placeholder="&#xF002; Search"
										autocomplete="off">
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12 " id="arm_div">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Arm </label>
								</div>
								<div class="col-12 col-md-6">
									<select name="user_arm_code" class="form-control"
										id="user_arm_code">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getUserarm_codeList}"
											varStatus="num">
											<option value="${item.arm_code}">${item.arm_desc}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 display_none" id="status_div">
						<div class="col-md-6 display_none">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Status<strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="status_val" class="form-control" id="status_val">
										<option value="-1">--Select--</option>
										<option value="0">Pending</option>
										<option value="1">Approved</option>

									</select>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="card-footer" align="center">
				<input type="reset" id="clearbtn" class="btn btn-success btn-sm"
					value="Clear"> <input type="submit"
					class="btn btn-primary btn-sm" value="Save" id="savebtn">
				<i class="fa fa-search display_none " id="icon_search"></i><input
					type="button" class="btn btn-primary btn-sm display_none "
					id="updatebtn" value="Search">
			</div>
			<input id="access_lve" name="access_lve1" type="hidden" /> <input
				id="sub_access_lve" name="sub_access_lve1" type="hidden" /> <input
				id="formation_code" name="user_formation_no" type="hidden" />
		</div>
	</div>
</form:form>
<div class="container" align="center">
	<div class="card">
		<div class="card-body">
			<div class="col-md-12 ">
				<table id="user_reporttbl"
					class=" table no-margin table-striped  table-hover  table-bordered  ">
					<thead>
						<tr>
							<th>Id</th>
							<th>Login Name</th>
							<th>Name</th>
							<th>Role</th>
							
							<th>Section</th>
							<th>Action</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>
</div>

<c:url value="search_user_by_role" var="searchUrl" />
<form:form action="${searchUrl}" method="get" id="searchForm"
	name="searchForm" modelAttribute="">
	<input type="hidden" name="access_lvl1" id="access_lvl1" value="0" />
	<input type="hidden" name="subaccess_lvl1" id="subaccess_lvl1"
		value="0" />
	<input type="hidden" name="user_sus_no1" id="user_sus_no1" value="0" />
	<input type="hidden" name="user_name1" id="user_name1" value="" />
	<input type="hidden" name="section1" id="section1" value="" />
	<input type="hidden" name="name_srch1" id="name_srch1" value="" />
	<input type="hidden" name="status_val1" id="status_val1" value="-1" />

</form:form>

<c:url value="update_user_mstUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid">
	<input type="hidden" name="updateid" id="updateid" value=0 />
</form:form>

<c:url value="search_user_mstUrl" var="mainFormUrl" />
<form:form action="${mainFormUrl}" method="GET" id="mainForm"
	name="mainForm"></form:form>



<c:url value="deleteUSERSUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm" modelAttribute="deleteid">
	<input type="hidden" name="deleteid" id="deleteid" />
</form:form>

<c:url value="archiveUSERSUrl" var="archiveUrl" />
<form:form action="${archiveUrl}" method="post" id="archiveForm"
	name="archiveForm" modelAttribute="archiveid">
	<input type="hidden" name="archiveid" id="archiveid" />
</form:form>
<c:url value="approveUSERSUrl" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm"
	name="approveForm" modelAttribute="approveid">
	<input type="hidden" name="approveid" id="approveid" />
</form:form>


<c:url value="ActiveDataURl1" var="ActiveUrl" />
<form:form action="${ActiveUrl}" method="post" id="ActiveForm"
	name="ActiveForm" modelAttribute="">
	<input type="hidden" name="acid1" id="acid1" value="0" />

</form:form>

<c:url value="DeactiveDataURl1" var="DeactiveURl" />
<form:form action="${DeactiveURl}" method="post" id="DeactiveForm"
	name="DeactiveForm" modelAttribute="">
	<input type="hidden" name="dcid1" id="dcid1" value="0" />

</form:form>

<script nonce="${cspNonce}">

	document.addEventListener('DOMContentLoaded', function() {
		

		document.getElementById('user_role_id').onchange = function() {
			return getaccess_lev(this.value);
		};
		document.getElementById('access_lvl').onchange = function() {
			access_lev(this.value);
			return getaccess_lev(this.value);
			
		};

		document.getElementById('formation_se').onchange = function() {
			return FormationChange();
		};
		document.getElementById('line_dte_se').onchange = function() {
			return ArmStaffHide();
		};
		document.getElementById('deopot_se').onchange = function() {
			return DepotChange();
		};

		document.getElementById('clearbtn').onclick = function() {
			return clearall();
		};

		document.getElementById('savebtn').onclick = function() {
			return isValid();
		};
		document.getElementById('updatebtn').onclick = function() {
			return Search();
		};
		
		
		
		document.addEventListener('click', function(event) {
            if (event.target.classList.contains('action_update')) {
            	debugger;
                    var encryptedPk = event.target.getAttribute('data-id');
                    var  encryptedPkid= event.target.getAttribute('data-data');
                    
                    if (confirm('Are you sure you want to Update?')) {
                            editData(encryptedPk,encryptedPkid);
                    }
            } else if (event.target.classList.contains('action_delete')) {
                    var encryptedPk = event.target.getAttribute('data-data');
                    if (confirm('Are you sure you want to Delete?')) {
                            deleteData(encryptedPk);
                    }
            } else if (event.target.classList.contains('action_archive')) {
                var encryptedPk = event.target.getAttribute('data-data');
                if (confirm('Are you sure you want to Archive?')) {
                        ArchiveData(encryptedPk);
                    }
            }
            else if (event.target.classList.contains('action_approve')) {
                var encryptedPk = event.target.getAttribute('data-data');
                if (confirm('Are you sure you want to Approve?')) {
                        ApproveData(encryptedPk);
                    }
            }
            else if (event.target.classList.contains('action_active')) {
                var encryptedPk = event.target.getAttribute('data-data');
                if (confirm('Are you sure you want to Active?')) {
                        ActiveData(encryptedPk);
                   }
            } 
            else if (event.target.classList.contains('action_deactive')) {
            var encryptedPk = event.target.getAttribute('data-data');
            if (confirm('Are you sure you want to Deactive?')) {
            	DeactiveData(encryptedPk);
            }
                  }
    });
		
		

	});

	function isValid() {

		if ($("#login_name").val() == "") {
			alert("Please Enter Name.");
			$("#login_name").focus();
			return false;
		}
		if ($("#user_name").val() == "") {
			alert("Please Enter Login Name.");
			$("#user_name").focus();
			return false;
		}

		if ($("#cell_number").val() == "") {
			alert("Please Enter Cell Number.");
			$("#cell_number").focus();
			return false;
		}
var check=false;
		if ($("#cell_number").val() != "") {
           
			$.post("CheckCellNumber?" + key + "=" + value, {
				cell_number : $("#cell_number").val()
			}).done(function(j) {
				
				if (j) {
					check=true;
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});

		}
		
		
		
		if(check)
			{
			alert("Cell Number Already Exist");
			$("#cell_number").focus();
			return false;
			}
		if ($("#army_no").val() == "") {
			alert("Please Enter Army No");
			$("#army_no").focus();
			return false;
		}
		if ($("#user_password").val() == "") {
			alert("Please Enter User Password");
			$("#user_password").focus();
			return false;
		}
		if ($("#user_password").val().length < 8
				| $("#user_password").val().length > 28) {
			alert("Please Enter Password at least 8 to 28 digit");
			$("#user_password").focus();
			return false;
		}
		if ($("#user_re_password").val() == "") {
			alert("Please Enter User Re-Password");
			$("#user_re_password").focus();
			return false;
		}
		if ($("#user_re_password").val().length < 8
				| $("#user_re_password").val().length > 28) {
			alert("Please Enter Re-Password at least 8 to 28 digit");
			$("#user_re_password").focus();
			return false;
		}
		if ($("select#user_role_id").val() == "0") {
			document.getElementById('sus_div').style.display = 'none';
			alert("Please Select Role Id");
			$("select#user_role_id").focus();
			return false;
		}

	

			if ($("select#section").val() == "0") {
				alert("Please Select Section");
				$("select#section").focus();
				return false;
			}
		

		
		return true;
	}

	$(document).ready(function() {
		$.ajaxSetup({
			 async : false
	});
		mockjax1('user_reporttbl');
		table = dataTable('user_reporttbl');

		if ('${access_lvl1}' != "") {
			$("#profile-tab").click();

			$("select#access_lvl").val('${access_lvl1}');

			if ('${access_lvl1}' == "Username") {
				$("div#username_div").show();
				$("#login_name_srch").show();
				$("#login_name_srch").val('${user_name}');

			} else if ('${access_lvl1}' == "section") {

				$("div#sus_div").show();
				$("#user_sus_no").show();
				$("select#sectionList").val('${section}');
				// 	 		 getSus_no('${access_lvl1}','');

			}

			$("div#divwatermark").val('').addClass('watermarked');
			watermarkreport();
			$("#divPrint").show();
			document.getElementById("printId").disabled = false;
		} else {

			$("input#login_name").val("");
			$("#user_name").val("");
			$("#army_no").val("");
			$("#user_password").val("");
			$("#user_re_password").val("");

			$("div#divwatermark").val('').addClass('watermarked');
			watermarkreport();
			$("#divPrint").show();
		}

	});

	function data(tableName) {
		jsondata = [];
		var table = $('#user_reporttbl').DataTable();
		var info = table.page.info();

		var access_lvl = $("#access_lvl").val();
		var login_name_srch = $("#login_name_srch").val();
		var sectionList = $("#sectionList").val();
		var s_state = $("#s_state").val();
		var name_srch = $("#name_srch").val();
		var status_val = $("#status_val").val();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		$.post("getUserReportList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			login_name_srch : login_name_srch,
			sectionList : sectionList,
			access_lvl : access_lvl,
			s_state : s_state,
			name_srch : name_srch,
			status_val:status_val
		}, function(j) {

			for (var k = 0; k < j.length; k++) {

				jsondata
						.push([ j[k].userid, j[k].username, j[k].login_name,
								j[k].role,  j[k].section,
								j[k].Action ]);
			}
		});
		$.post("getUserReportListCount?" + key + "=" + value, {
			Search : Search,
			login_name_srch : login_name_srch,
			sectionList : sectionList,
			access_lvl : access_lvl,
			s_state : s_state,
			name_srch : name_srch,
			status_val:status_val,

		}, function(j) {
			FilteredRecords = j;
		});
	}

	var access_lvl, sub_access_lvl, role_id;

	function getaccess_lev(val) {
		$("#user_sus_no").val("");
		document.getElementById('sub_access_lve').value = "";
		document.getElementById('formation_code').value = "";
		role_id = val;
		<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
		if ('${item.roleId}' == val) {
			$("#access_lve").val('${item.access_lvl}');
			$("#sub_access_lve").val('${item.sub_access_lvl}');
			access_lvl = '${item.access_lvl}';
			sub_access_lvl = '${item.sub_access_lvl}';
		}
		</c:forEach>
		abd();
	}

	function abd() {
		if ((access_lvl != "") || (access_lvl != '')) {
			if (access_lvl == 'All') {

				//document.getElementById('section_div').style.display = 'none';
				document.getElementById('sub_access_lve').value = "";
				document.getElementById('formation_code').value = "";
			} else {

				//document.getElementById('section_div').style.display = 'block';
				document.getElementById('sub_access_lve').value = "";
				document.getElementById('formation_code').value = "";
			}
		} else {
			//document.getElementById('section_div').style.display = 'none';
			//alert("Access level is not defined.");
		}
	}

	var newWin;
	function editData(a, userid) {
		debugger;
		document.getElementById('updateid').value = userid;
		document.getElementById('updateForm').submit();
	}
	



$("#profile-tab").click(function() {
    // Show profile tab and hide home tab
    $("#profile").show().removeClass('display_none').addClass('active');
    $("#home").hide().addClass('display_none').removeClass('active');

    // Update tab states
    $("#profile-tab").addClass('active');
    $("#home-tab").removeClass('active');

    // Manage buttons visibility
    $("#savebtn").hide();
    $("#updatebtn").removeClass('display_none');
    $("#icon_search").removeClass('display_none');

    // Reset form and reload table
    $("#user_mst")[0].reset();
    table.ajax.reload();
});

$("#home-tab").click(function() {
    // Show home tab and hide profile tab
    $("#home").show().removeClass('display_none').addClass('active');
    $("#profile").hide().addClass('display_none').removeClass('active');

    // Update tab states
    $("#home-tab").addClass('active');
    $("#profile-tab").removeClass('active');

    // Manage buttons visibility
    $("#savebtn").removeClass('display_none');
    $("#savebtn").show();
    $("#updatebtn").addClass('display_none');
    $("#icon_search").addClass('display_none');
	
    // Reset form and reload table
    $("#user_mst")[0].reset();
    table.ajax.reload();
});

	//**********************************************

	function Search() {
		if ($("#access_lvl").val() == "") {
			alert("Please Select Options");
			return false;
		} else {

			if ($("#access_lvl").val() == "Username") {

				if ($("#login_name_srch").val() != "") {
					$("#user_name1").val($("#login_name_srch").val());
				} else {
					alert("Please Enter Login Name");
					return false;
				}

			}

			if ($("#access_lvl").val() == "Loginname") {

				if ($("#name_srch").val() != "") {
					$("#name_srch1").val($("#name_srch").val());
				} else {
					alert("Please Enter Name");
					return false;
				}

			}

			if ($("#access_lvl").val() == "status") {

				if ($("#status_val").val() != "-1") {
					$("#status_val1").val($("#status_val").val());
				} else {
					alert("Please Select Status.");
					return false;
				}

			}
			
			
			
			$("#access_lvl1").val($("#access_lvl").val());
			$("#subaccess_lvl1").val("");
			$("#user_sus_no1").val($("#user_sus_no").val());
			$('select#user_arm_code').val();

			if ($("#access_lvl").val() == "section") {

				if ($("#sectionList").val() != "0") {
					$("#section1").val($("#sectionList").val());
				} else {

					alert("Please Select Section");
					return false;
				}

			}
			table.ajax.reload();
			// 	    document.getElementById('searchForm').submit();
		}
	}
var access_lvl, sub_access_lvl, role_id;
var previous_v = ""; // Variable to store the previous value of v

function access_lev(v) {
    debugger;
    $("#user_sus_no").val("");
    $("select#formation_se").val("");
    $("select#deopot_se").val("");
    $("select#line_dte_se").val("");
    $("#name_srch").val("");

    // Hide elements associated with the previous value of v
    if (previous_v !== "") {
        hideElements(previous_v);
    }

    if (v !== "") {
        if (v === "All") {
            $('#username_div div:first').addClass('display_none');
            $('#sub_lev').addClass('display_none');
            $('#formation_se').addClass('display_none');
            $('#arm_div').addClass('display_none');
            $('#line_dte_se').addClass('display_none');
            $('#deopot_se').addClass('display_none');
            $('#sus_div').addClass('display_none');
            $('#status_div').addClass('display_none');
            $('#loginname_div').addClass('display_none');
        } else if (v === "Username") {
            $('#username_div div:first ').removeClass('display_none');
            $('#sub_lev').addClass('display_none');
            $('#formation_se').addClass('display_none');
            $('#arm_div').addClass('display_none');
            $('#line_dte_se').addClass('display_none');
            $('#deopot_se').addClass('display_none');
            $('#sus_div').addClass('display_none');
            $('#status_div').addClass('display_none');
            $('#loginname_div').addClass('display_none');
            getUsername();
        } else if (v === "section") {
            $('#username_div').addClass('display_none');
            $('#sub_lev').addClass('display_none');
            $('#formation_se').addClass('display_none');
            $('#arm_div').addClass('display_none');
            $('#line_dte_se').addClass('display_none');
            $('#deopot_se').addClass('display_none');
            $('#sus_div div:first').removeClass('display_none');
            $('#loginname_div').addClass('display_none');
            $('#status_div').addClass('display_none');
            getSus_no("", "");
        } else if (v === "Loginname") {
            $('#username_div').addClass('display_none');
            $('#sub_lev').addClass('display_none');
            $('#formation_se').addClass('display_none');
            $('#arm_div').addClass('display_none');
            $('#line_dte_se').addClass('display_none');
            $('#deopot_se').addClass('display_none');
            $('#sus_div').addClass('display_none');
            $('#status_div').addClass('display_none');
            $('#loginname_div div:first').removeClass('display_none');
            getnamesrch();
        }
        
    
        
        
        
        
        else {
            $('#sub_lev').addClass('display_none');
            $('#arm_div').addClass('display_none');
            $('#line_dte_se').addClass('display_none');
            $('#formation_se').addClass('display_none');
            $('#deopot_se').addClass('display_none');
            $('#sus_div').removeClass('display_none');
            $("#user_sus_no").val("");
            $('#username_div').addClass('display_none');
            $('#login_name').val("");
            $('#login_name').addClass('display_none');
            $('#status_div').addClass('display_none');
            
        }
    } else {
        $('#sub_lev').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').addClass('display_none');
        $("#user_sus_no").val("");
        $('#username_div').addClass('display_none');
        $('#login_name').val("");
        $('#login_name').addClass('display_none');
        $('#status_div').addClass('display_none');
     
    }

    // Update the previous value of v
    previous_v = v;
}

// Function to hide elements based on the previous value of v
function hideElements(v) {
    if (v === "All") {
        $('#username_div div:first').addClass('display_none');
        $('#sub_lev').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').addClass('display _none');
        $('#loginname_div').addClass('display_none');
    } else if (v === "Username") {
        $('#username_div div:first').addClass('display_none');
        $('#sub_lev').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').addClass('display_none');
        $('#loginname_div').addClass('display_none');
    } else if (v === "section") {
        $('#username_div').addClass('display_none');
        $('#sub_lev').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div div:first').addClass('display_none');
        $('#loginname_div').addClass('display_none');
    } else if (v === "Loginname") {
        $('#username_div').addClass('display_none');
        $('#sub_lev').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').addClass('display_none');
        $('#loginname_div div:first').addClass('display_none');
    } 
    else if (v === "status") {
        $('#username_div').addClass('display_none');
        $('#sub_lev').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').addClass('display_none');
        $('#loginname_div').addClass('display_none');
        $('#status_div div:first').addClass('display_none');
    } 
    
    
    
    
    else {
        $('#sub_lev').addClass('display_none');
        $('#arm_div').addClass('display_none');
        $('#line_dte_se').addClass('display_none');
        $('#formation_se').addClass('display_none');
        $('#deopot_se').addClass('display_none');
        $('#sus_div').removeClass('display_none');
        $("#user_sus_no").val("");
        $('#username_div').addClass('display_none');
        $('#login_name').val("");
        $('#login_name').addClass('display_none');
        alert("Access level is not defined.");
    }
}

	function getSus_no(access_lvl, subaccess_lvl) {

		$("#user_sus_no").val("");


	}

	function clearall() {
		// 		document.getElementById('divPrint').style.display = 'none';
		// 		document.getElementById("printId").disabled = true;
		$("div#username_div").hide();
		$("#login_name").hide();
		$("div#arm_div").hide();
		$("#line_dte_se").hide();
		$("#deopot_se").hide();
		$("div#sub_lev").hide();
		$("#formation_se").hide();
		$("div#sus_div").hide();
		$("div#status_div").hide();
		
	}

	function printDiv() {

		var printLbl = [];
		var printVal = [];
		printDivOptimize('divPrint', 'Search User', printLbl, printVal);
	}

	function FormationChange() {
		$("#user_sus_no").val("");
	}

	function ArmStaffHide() {

		if ($("select#line_dte_se").val() == "Staff") {
			$("div#arm_div").hide();
			$("#user_arm_code").val("0");
		} else {
			$("div#arm_div").show();
		}
	}

	function DepotChange() {
		$("#user_sus_no").val("");
	}

	function getUsername() {
		var wepetext = $("#login_name_srch");
		wepetext.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getUsernameList?" + key + "=" + value,
					data : {
						userName : $("#login_name_srch").val()
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
		});
	}

	function getnamesrch() {
		var wepetext = $("#name_srch");
		wepetext.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getNameList?" + key + "=" + value,
					data : {
						userName : $("#name_srch").val()
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
		});
	}

	$(document).ready(function() {
		getUsername();
		getnamesrch();

		if ('${access_lvl1}' != "") {
			$("select#access_lvl").val('${access_lvl1}');

			if ('${access_lvl1}' == "Username") {
				$("div#username_div").show();
				$("#login_name").show();
				$("#login_name").val('${user_name}');

			} else if ('${access_lvl1}' == "section") {

				$("div#sus_div").show();
				$("#user_sus_no").show();
				$("select#section").val('${section}');
				getSus_no('${access_lvl1}', '');

			}

			else if ('${access_lvl1}' == "SUS_No") {

				$("div#sus_div").show();
				$("#user_sus_no").show();
				getSus_no('', '');
				$("#user_sus_no").val('${user_sus_no1}');
			}

			$("div#divwatermark").val('').addClass('watermarked');
			watermarkreport();
			$("#divPrint").show();
			document.getElementById("printId").disabled = false;
		}
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?")[0];
				window.location = url;
			}
		} catch (e) {
			// TODO: handle exception
		}
	});
	function deleteData(obj) {

		document.getElementById('deleteid').value = obj;
		document.getElementById('deleteForm').submit();
	}
	function ArchiveData(obj) {

		document.getElementById('archiveid').value = obj;
		document.getElementById('archiveForm').submit();
	}

	
	function ApproveData(obj) {

		document.getElementById('approveid').value = obj;
		document.getElementById('approveForm').submit();
	}
	function ActiveData(id) {
		$("#acid1").val(id);
		document.getElementById('ActiveForm').submit();
	}

	function DeactiveData(id) {
		$("#dcid1").val(id);
		document.getElementById('DeactiveForm').submit();
	}
</script>