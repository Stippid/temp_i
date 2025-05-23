<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/assets/scss/style.css">

<form:form name="changePass" id="changePass"  action="changePassword_Action" method='POST'>
<div class="animated fadeIn">
	<div class="container" align="center">
        <div class="card">
            <div class="card-header"> <h5>Change Password</h5></div>
           	<div class="card-body">
           		<div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" >&emsp;Name</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<b style="font-size: 15px;">${userDetails.login_name}</b>
                       	</div>
                   	</div>
				</div>
                <div class="col-md-12" align="center">
                   	<div class="row form-group"> 
                       	<div class="col-md-3" align="left">                                       
                           	<label for="text-input" class=" form-control-label" >&emsp;User Name </label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<b style="font-size: 15px;">${userDetails.userName}</b>
                       	</div>
                   	</div>
				</div>
                <div class="col-md-12">
                	<div class="row form-group" > 
                       	
                       	<div class="col-md-3" align="left">                                     
                       		<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Old Password</label>
                      	</div>
                        <div class="col-md-3" >
                        	<input type="password" id="old_pass" name="old_pass" class="form-control" maxlength="28" autocomplete="off" required>  
                      	</div>
                   	</div>
             	</div>
				<div class="col-md-12">
					<div class="row form-group"> 
                    	<div class="col-md-3" align="left">                                       
                        	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> New Password</label>
                     	</div>
                      	<div class="col-md-3">
                        	<input  id="new_pass" type="password" maxlength="28"  name="new_pass" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$" class="form-control" autocomplete="off"  title="Must contain at least one number and one uppercase and lowercase letter and one special character and at least 8 or 28 characters" autocomplete="off" required><br>
                        </div>
                        <div class="col-md-6">
                        </div>
                    </div>
               	</div>
                    <div class="col-md-12">
                        <div class="row form-group"> 
							<div class="col-md-3" align="left">                                       
								<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Confirm New Password</label>
                           	</div>
                           	<div class="col-md-3">
								<input  id="c_password" type="password" maxlength="28" name="c_password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"  class="form-control" autocomplete="off" required> 
                       		</div>
                       		<div class="col-md-6"></div>
                   	</div>
                   </div>
                   <div class="col-md-12" align="left">
                   		
                   		<label for="passid">
                   			<b>1) Password should be a mix of alphabets, numerals and special characters ( $#^@\&%_.~!*) without any space in between.</b><br>
							<b>2) Password must contain both upper and lowercase letters.</b><br>
							<b>3) Password length should be between 8 to 28 characters.</b>
						</label>
                   </div>
                   <div class="col-md-12">
                 		<div class="card-footer" align="center">
                           <input type="submit" class="btn btn-success btn-sm" value="Submit" onclick="return isValid();">                                  
                        </div>
                  </div>  
          	</div>
       	</div>
    </div>
</div>
 
</form:form> 

<script>
function isValid()
{	
	if($("#old_pass").val()==""){
		alert("Please Enter Old Password");
		$("#old_pass").focus();
		return false;
	} 
	if($("#new_pass").val()==""){
		alert("Please Enter  New Password");
		$("#new_pass").focus();
		return false;
	} 
	if($("#c_password").val()==""){
		alert("Please Enter Confirm New Password");
		$("#c_password").focus();
		return false;
	} 
	if($("#new_pass").val() != $("#c_password").val()){
		alert("Passwords do not match.");
		$("#new_pass").focus();
		return false;
	} 
	return true;
}
</script>
	 