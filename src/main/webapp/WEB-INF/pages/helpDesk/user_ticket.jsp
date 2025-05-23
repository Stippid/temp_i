<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/printWatermark/common.js" nonce="${cspNonce}"></script>
<script src="js/printWatermark/printAllPages.js" type="text/javascript" nonce="${cspNonce}"></script> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<script src="js/commonJS/commonmethod.js" type="text/javascript" nonce="${cspNonce}"></script>

<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<script nonce="${cspNonce}">
var username="${username}";
</script>


<div class="container" align="center">





<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>View Ticket Details</h5>
			</div>
			<div class="card-body card-block cue_text">
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Ticket
									Id</label>
							</div>
							<div class="col-md-8">
								<input type="hidden" id="id" name="id" value="${id}" /> <input
									id="ticket" name="ticket" class="form-control"
									autocomplete="off" value="${ticket}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Date
								</label>
							</div>
							<div class="col-md-8">
								<input id="date1" name="date1" class="form-control"
									readonly="readonly" value="${created_on}"></input> <input
									type="hidden" id=completed_dt name="completed_dt"
									class="form-control" readonly="readonly"
									value="${completed_dt}"></input> <input type="hidden"
									id=assigned_dt name="assigned_dt" class="form-control"
									readonly="readonly" value="${assigned_dt}"></input>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Section
									No </label>
							</div>
							<div class="col-md-8">
								<select id="section" name="section" class="form-control"
									readonly="readonly">

									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getSectionNameList}"
										varStatus="num">
										<option value="${item.id}" name="${item.id}">${item.section}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Module
								</label>
							</div>
							<div class="col-md-8">
								<input id="module" class="form-control" readonly="readonly"
									value="${moduleName}"> <input type="hidden"
									id="moduleHid" name="module" value="${moduleId}">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Sub
									Module </label>
							</div>
							<div class="col-md-8">
								<input id="sub_module" class="form-control"
									value="${sub_module}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-6 display_none">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Screen
									Name </label>
							</div>
							<div class="col-md-8">
								<input id="screen_name" class="form-control"
									value="${screen_name}" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Ticket
									Type </label>
							</div>
							<div class="col-md-8">
								<input id="help_topic" class="form-control"
									value="${help_topic}" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Screenshot
								</label>
							</div>
							<div class="col-md-8">
								<div class="col-md-8" id="d1">
									<div class="col-md-10" id="d1">
										<input type="text" id="filedoc" name="filedoc"
											class="form-control" value="${screen_shot}">
									</div>
									<div class="col-md-2 mt_10" id="d1">
										<i id="download_id" class="fa fa-download"> </i>
									</div>

								</div>
								<div class="col-12 col-md-8" id="d2">
									<h4>No Screenshot Available</h4>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label for="text-input" class=" form-control-label">Issue
									summary </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="issue_summary" name="issue_summary"
									placeholder="Maximum 100 characters" value="${issue_summary}"
									class="form-control" autocomplete="off" maxlength="100"
									readonly="readonly"></input>
							</div>
						</div>
					</div>
						<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
							<label for="text-input" class=" form-control-label"><strong class="color_red">*</strong>Description (Max 1000 words)</label>
							</div>
							<div class="col-md-8">
								 <textarea rows="2" cols="250" id="description"  name="description" class="form-control h150" autocomplete="off" maxlength="1000" readonly="readonly">${description}</textarea>
							</div>
						</div>
					</div>

				</div>

			</div>
		
		</div>
	</div>
</div>



                             <div class="animated fadeIn" >
                <div class="row">
                        <div class="col-md-12" align="center">
                                <div class="col-md-12" align="center">
                                <button id="backId" name="backId" class="btn btn-success btn-sm btn_report">Back</button>
                                   <button id="printId" name="printId" class="btn btn-primary btn-sm btn_report"> <i class="fa fa-print"></i> Print</button>
                                </div>
                        </div>
                </div>
        </div>
	</div>

<div id="printableArea" class="display_none">
<div  class="watermarked display_block" data-watermark="" id="divShow">
	 	</div>
</div>
<%-- </form:form> --%>
 <c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>
<c:url value="getmodule_help" var="imageDownloadUrl" />
        <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id1">
        	<input type="hidden" name="id1" id="id1" value="0"/>
        	<input type="hidden" name="pageUrl" id="pageUrl" value=""/>
        	<input type="hidden" name="contraint" id="contraint" value=""/>
        </form:form>  
        
        
       <c:url value="myticket" var="backUrl" />
        <form:form action="${backUrl}" method="get" id="backUrlForm" name="backUrlForm" modelAttribute="id1">
        </form:form>  
        
        
        <script nonce="${cspNonce}">
      
        function getmodule_help(id)
        {  debugger
        	var file_doc = $("#filedoc").val();
        	
    	   document.getElementById("id1").value=id;
    	   document.getElementById("contraint").value="";
    	   document.getElementById("pageUrl").value=file_doc;
           document.getElementById("getDownloadImageForm").submit();
        }  
</script>
<script type="text/javascript" nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function() {
	
	document.getElementById('printId').onclick = function() {
		return printDiv('printableArea');	
  	};
  	document.getElementById('download_id').onclick = function() {
		return getmodule_help('${id}');	
  	};
  	
  	document.getElementById('backId').onclick = function() {
  		
  		document.getElementById('backUrlForm').submit();	
  	};
})



$(document).ready(function() {

	$("#section").val('${getSectionvalue}');
	if('${msg}'!=""){
		alert($("#msg").val());		
	}
	 if('${screen_shot}'!=""){
		 $("div#d1").show();
		 $("div#d2").hide();
		 $("#filedoc").hide();
	  } else{
		 $("div#d1").hide();
		 $("div#d2").show(); 
	 }
  	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	if('${ticket_status}' == '0')
	{
		var date = '${created_on}';
	 	$("input#date1").val(date);
	}
	if('${ticket_status}' == '1')
	{
		var date = '${assigned_dt}';
	 	$("input#assigned_dt").val(date);
	}
	if('${ticket_status}' == '2')
	{
		var date = '${completed_dt}';
	 	$("input#completed_dt").val(date);
	}
	
});
</script>  
<script nonce="${cspNonce}">
function printDiv() 
{
	$("div#divwatermark").val('').addClass('watermarked'); 
  	watermarkreport();
	var printLbl = [];
	var printVal = [];
	 if('${ticket_status}' == "0"){
		status = "New";
		printLbl = ["Ticket Id :","Created on :", "Ticket Typpe :","Status:","Section:","Issue summary :","Description :"];
		 printVal = [document.getElementById('ticket').value,document.getElementById('date1').value,document.getElementById('help_topic').value,status,document.getElementById('section').options[document.getElementById('section').selectedIndex].text,document.getElementById('issue_summary').value,document.getElementById('description').value];
	}else if('${ticket_status}' == "1"){
		status = "In Progress";
		printLbl = ["Ticket Id :","Created on :", "Ticket Typpe :","Assign on :","Status:","Section:","Issue summary :","Description :"];
		 printVal = [document.getElementById('ticket').value,document.getElementById('date1').value,document.getElementById('help_topic').value,document.getElementById('assigned_dt').value,status,document.getElementById('section').options[document.getElementById('section').selectedIndex].text,document.getElementById('issue_summary').value,document.getElementById('description').value];
	}else if('${ticket_status}' == "2"){
		status = "Completed";
		printLbl = ["Ticket Id :","Created on :",  "Ticket Typpe :","Assign on :","Status:","Section:" ,"Completed on:","Issue summary :","Description :"];
		 printVal = [document.getElementById('ticket').value,document.getElementById('date1').value,document.getElementById('help_topic').value,document.getElementById('assigned_dt').value,status,document.getElementById('section').options[document.getElementById('section').selectedIndex].text,document.getElementById('completed_dt').value,document.getElementById('issue_summary').value,document.getElementById('description').value];
	}else if('${ticket_status}' == "3"){
		status = "Feedback";
	}else if('${ticket_status}' == "4"){
		status = "Feature Request";
	}
	printDivOptimizehelp('printableArea','Ticket Details',printLbl,printVal,"");
	
 }
</script>