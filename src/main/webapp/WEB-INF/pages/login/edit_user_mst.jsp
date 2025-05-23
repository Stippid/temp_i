<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js" nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/amin_module/rbac/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/cue/cue.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript" nonce="${cspNonce}"></script>
<script src="js/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>

<style type="text/css" nonce="${cspNonce}">
.cue_text input, textarea {
	text-transform: unset;
}

.btn-group-sm>.btn, .btn-sm {
	font-size: 12px;
	line-height: 1.5;
}

.glyphicon-refresh::before {
	content: "\e031";
}

.glyphicon {
	font-family: 'Glyphicons Halflings';
	font-style: normal;
	font-weight: 400;
	line-height: 1;
}
</style>
<script type="text/javascript" nonce="${cspNonce}">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function () {
	    $('body').bind('cut copy paste', function (e) {
    	    e.preventDefault();
    	});
   	$("body").on("contextmenu",function(e){
    	return false;
    });
});
</script>
<body>
	<form:form name="edit_user_mst" action="edit_User_Mst_Action"
		method='POST' modelAttribute="edit_User_MstCMD">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Edit User Master</h5>
				</div>
				<div class="card-body card-block cue_text">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">User Name <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="userId" name="userId"
										value="${edit_User_MstCMD.userId}"> <input
										id="login_name" name="login_name"
										value="${edit_User_MstCMD.login_name}" class="form-control font_familiy_awsome"
										autocomplete="off" maxlength="70">
								</div>
							</div>
						</div>
					<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">User ID <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="hidden" id="user_name" name="user_name"
										value="${edit_User_MstCMD.userName}"> <input id="user"
										name="user" 
										readonly="readonly" value="${edit_User_MstCMD.userName}"
										maxlength="30" class="form-control font_familiy_awsome" autocomplete="off"
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
									<input type="hidden" id="cell_number" name="cell_number"
										value="${edit_User_MstCMD.cell_number}"> <input id="cell"
										name="cell" 
										readonly="readonly" value="${edit_User_MstCMD.cell_number}"
										maxlength="30" class="form-control font_familiy_awsome" autocomplete="off"
										required>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Password <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="user_password" type="password" name="password"
										pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*](?!.*\s).{8,28}"
										class="form-control" value="${password}" autocomplete="off"
										title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
								</div>
							</div>
						</div>

					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Re-Password <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<input id="user_re_password" type="password" name="re_password"
										pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$#^@&%_.~!*](?!.*\s).{8,28}"
										class="form-control" value="${password}" autocomplete="off"
										required>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input">Role <strong
										class="color_red">*</strong>
									</label>
								</div>

								<div class="col-12 col-md-6">
									<input type="hidden" id="role1" name="role1"
										class="form-control" value="${getRole[0].role}"
										autocomplete="off" required> <select
										name="user_role_id" class="form-control" id="user_role_id"
										onchange="getaccess_lev(this.value);">
										<!-- onchange="getaccess_lev(this.value);" -->
										<!--  <option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getRoleNameList}"
											varStatus="num">
											<option value="${item.roleId}">${item.role}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
							<div class="col-md-6 display_none" id="sus_div">
							<div class="row form-group"> 
               					<div class="col col-md-6 ">
                 					<label for="text-input" >Section Name  <strong class="color_red">*</strong> </label> 
               					</div>
               					<div class="col-12 col-md-6">
<!--                  					<input type="text" id="army_no" name="army_no" maxlength="9"  onkeyup="this.value = this.value.toUpperCase();" placeholder="Ex. IC123456A" class="form-control" autocomplete="off" required> -->
								<select name="section" class="form-control" id ="section"  >
		                 						<option value="0">--Select--</option>
		               							<c:forEach var="item" items="${getSectionNameList}" varStatus="num" >
		               								<option value="${item.id}">${item.section}</option>
		               							</c:forEach>
		                 					</select>
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
										<!-- <option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getUserarm_codeList}"
											varStatus="num">
											<option value="${item.arm_code}">${item.arm_desc}</option>
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
									<label for="text-input">Rank <strong
										class="color_red">*</strong>
									</label>
								</div>
								<div class="col-12 col-md-6">
									<select name="rank" id="rank"
										class="form-control ">
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


				</div>
				<div class="col-md-12" align="left">
					<label for="passid"> <b>1) Password should be a mix of
							alphabets, numerals and special characters ( $#^@\&%_.~!*)
							without any space in between.</b><br> <b>2) Password must
							contain both upper and lowercase letters.</b><br> <b>3)
							Password length should be between 8 to 28 characters.</b>
					</label>
				</div>
				<div class="card-footer" align="center">
					<!--   <input type="reset" class="btn btn-success btn-sm" value="Clear">    	 -->
					<input type="submit" class="btn btn-primary btn-sm"  id ="submitid" value="Update"
						>
					<!--  onclick="return isValid();"-->
					<a href="user_mstUrl" class="btn btn-danger btn-sm"
						id="cancelid"> Cancel </a>
				</div>



				<input id="access_lve" name="access_lve1" type="hidden" /> <input
					id="sub_access_lve" name="sub_access_lve1" type="hidden" /> <input
					id="formation_code" name="user_formation_no" type="hidden" />




			</div>

		</div>


	</form:form>



	<script nonce="${cspNonce}">

	document.addEventListener('DOMContentLoaded', function () {
		
		document.getElementById('submitid').onclick = 
			function() {
			return isValid();
	  	};

	  	document.getElementById('cancelid').onclick = function() {
	  	  window.close();
	  	
	  	};
	});
	
$(document).ready(function () {
	var a=$("input#user_name").val();
   var role= $("input#role1").val();
 

    var sList = new Array();
    var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	<c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
		if('${item.role}' == role.trim()){
			options += '<option value="${item.roleId}" selected >${item.role}</option>';
			getaccess_lev('${item.roleId}');
			$("select#section").val('${edit_User_MstCMD.user_sus_no}');
			$("select#rank").val('${edit_User_MstCMD.rank}');

		}else{
			 options += '<option value="${item.roleId}" >${item.role }</option>';
		 }
	</c:forEach>

	//$("select#section").val('${edit_User_MstCMD.user_sus_no}');
	$("select#user_role_id").html(options); 
});
function isValid(){
	 
			
	  if($("#login_name").val()==""){
		alert("Please Enter User Name");
		$("#login_name").focus();
		return false;
	} 
	if($("#user_name").val()==""){
		alert("Please Enter User ID");
		$("#user_name").focus();
		return false;
	} 
	if($("#user_password").val()==""){
		alert("Please Enter User Password");
		$("#user_password").focus();
		return false;
	} 
	
	if($("#user_re_password").val()==""){
		alert("Please Enter  Re-Password");
		$("#user_re_password").focus();
		return false;
	} 
	if($("#user_password").val()!=$("#user_re_password").val()){
		alert("Password and Re-Password didn't match");
		$("#user_re_password").focus();
		return false;
	}
	if($("select#user_role_id").val()=="0"){
		document.getElementById('arm_div').style.display='none';
		document.getElementById('sus_div').style.display='none';
			alert("Please Select Role Id");
			$("select#user_role_id").focus();
			
			return false;
		} 
	
		if($("select#user_role_id").val()!="1"){
	
		if($("select#section").val()=="0"){
		alert("Please Select Section");
		$("select#section").focus();
		return false;
		}
	}
		
		if ($("select#rank").val() == "0") {
			alert("Please Select Rank");
			$("select#rank").focus();
			return false;
		
	}
		
	return true;
} 

</script>


	<script nonce="${cspNonce}">
   var access_lvl,sub_access_lvl,role_id;
   function getaccess_lev(val) {
	 
	  $("#user_sus_no").val("");
	  
   document.getElementById('sub_access_lve').value="";
		document.getElementById('formation_code').value=""; 
	   role_id = val;
		 <c:forEach var="item" items="${getRoleNameList}" varStatus="num" >
			if('${item.roleId}' == val){		
				$("#access_lve").val('${item.access_lvl}');
				$("#sub_access_lve").val('${item.sub_access_lvl}');
				 access_lvl = '${item.access_lvl}';
				 sub_access_lvl = '${item.sub_access_lvl}';
			}
		</c:forEach>
		
		abd();
	}
   


function abd() {
    if (access_lvl != "" || access_lvl != '') {
         if (sub_access_lvl == "Arm") {
            document.getElementById('arm_div').classList.remove('display_none');
            document.getElementById('sus_div').classList.add('display_none');
            // document.getElementById('sub_access_lve').value = "";
            document.getElementById('formation_code').value = "";
            getarm();
        } else {
            document.getElementById('arm_div').classList.add('display_none');
            document.getElementById('sus_div').classList.remove('display_none'); // Show sus_div
        }
    } else {
        document.getElementById('section_div').classList.add('display_none');
        alert("Access level is not defined.");
    }
}
	function getarm(){
		 var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
			<c:forEach var="item" items="${getUserarm_codeList}"  >
				if('${item.arm_code}' == '${edit_User_MstCMD.user_Arm_code}'){
			
					options += '<option value="${item.arm_code}" selected >${item.arm_desc}</option>';
				}else{
				
					 options += '<option value="${item.arm_code}" >${item.arm_desc }</option>';
				 }
			</c:forEach>
		
               							
			$("select#user_arm_code").html(options); 
	}   
   </script>
</body>
