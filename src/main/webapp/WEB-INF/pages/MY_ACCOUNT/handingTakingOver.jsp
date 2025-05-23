<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="handingTakingOver" id="handingTakingOver"  action="handingTakingOver_Action?${_csrf.parameterName}=${_csrf.token}" method='POST' enctype="multipart/form-data">
<div class="animated fadeIn">
	<div class="container" align="center">
        <div class="card">
            <div class="card-header"> <h5>Handing Taking Over</h5></div>
           	<div class="card-body">
           		<div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" ></div>
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" ><strong style="color: red;">*</strong> H/T Type</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<select id="ht_type" name="ht_type" class="form-control">
                        		<option value="0">--Select--</option>
                        		<option value="Permanent">Permanent</option>
                        		<option value="Temporary">Temporary</option>
                        	</select>
                       	</div>
                   	</div>
                   	<div class="col-md-3" ></div>
				</div>
				<div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" ></div>
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" ><strong style="color: red;">*</strong> Reason</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<select id="ht_reason" name="ht_reason" class="form-control">
                        		<option value="0">--Select--</option>
                        		<option value="PermanentPosting">Permanent Posting</option>
                        		<option value="TD">TD</option>
                        		<option value="Leave">Leave</option>
                        	</select>
                       	</div>
                       	<div class="col-md-3" ></div>
                   	</div>
				</div>
				<!-- <div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" ></div>
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" ><strong style="color: red;">*</strong> H/T To</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<input type="text" id="to_username" name="to_username" class="form-control" autocomplete="off" style="width:85%;display: inline-block;"/> <i class="fa fa-search" onclick="openUnitnameSelection();"></i>
                        	<input type="hidden" id="to_userid" name="to_userid" class="form-control" autocomplete="off"/>
                        </div>
                        <div class="col-md-3" ></div>
                   	</div>
				</div> -->
				<div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" ></div>
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" ><strong style="color: red;">*</strong> H/T to Army No</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<input type="text" id="to_army_no" name="to_army_no" maxlength="9" onkeyup="this.value = this.value.toUpperCase();" placeholder="Ex. IC123456A" class="form-control" autocomplete="off" />
                        </div>
                        <div class="col-md-3" ></div>
                   	</div>
				</div>
				<div class="col-md-12" align="center">
                   	<div class="row form-group">
                   		<div class="col-md-3" ></div>
                   		<div class="col-md-3" align="left">                                     
                           	<label for="text-input" class=" form-control-label" > Authority</label>
                       	</div>
                        <div class="col-md-3" align="left">
                        	<input type="file" id="auth_letter1" name="auth_letter1" class="form-control"/>
                        </div>
                        <div class="col-md-3" ></div>
                   	</div>
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
$(document).ready(function() {
	$("#upload_auth_letter").change(function(){	
		checkFileExt(this);
	});
});
 
function isValid()
{	
	if($("select#ht_type").val()=="0"){
		alert("Please Select H/T Type");
		$("#ht_type").focus();
		return false;
	} 
	if($("select#ht_reason").val()=="0"){
		alert("Please Select Reason");
		$("#ht_reason").focus();
		return false;
	} 
	if($("#to_userid").val()==""){
		alert("Please Select H/T To");
		$("#to_userid").focus();
		return false;
	} 
	return true;
}

/* var popupWindow=null;
function HandlePopupResultUnitName(unit_name,sus_no) 
{
	alert(unit_name +"=="+ sus_no);
	
	$("#source_unit_name").val(unit_name);
	$("#source_sus_no").val(sus_no);
}

function openUnitnameSelection() {
	popupWindow = window.open("LovUserDetails", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=200,left=500,width=900,height=600,fullscreen=no");
}
function parent_disable() {
	if(popupWindow && !popupWindow.closed)
		popupWindow.focus();
} */
</script>
	 