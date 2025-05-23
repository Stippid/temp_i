<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 
%>
<!doctype html>
<html class="no-js" lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>MISO</title>
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"nonce="${cspNonce}"></script> 
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css"nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/scss/style.css"nonce="${cspNonce}">
<script src="js/commonJS/commonmethod.js" type="text/javascript"nonce="${cspNonce}"></script>
<style type="text/css" nonce="${cspNonce}">
	.btn-group-sm > .btn, .btn-sm {
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
	.cue_text .col-md-12{
	  justify-content: center;
	}
</style>
</head>
<body>
  <form:form name="TicketStatusNew" id="TicketStatusNew" action="" method='POST' modelAttribute="TicketStatusNewCMD">
	<div class="container" align="center">
       	<div class="card">
         		<div class="card-header"> <h5>${ticket_status} Tickets</h5></div>
         	<div class="card-body" >
		 		<div  class="watermarked display_block" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
					<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print text_align_center" >
						<thead >
							<tr>
								<th class="f_15">Ser No</th>
								<th  class="f_15">Ticket Id</th>
								<th  class="f_15">Module</th>
								<th  class="f_15">Issue Summary</th>
								<th  class="f_15">Date</th>
								<th  class="f_15">Help Topic</th>
								
							</tr>
						</thead> 
						<tbody>
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<th class="f_15">${num.index+1}</th>
										<th class="f_15">${item.ticket}</th>
										<th class="f_15">${item.module_name}</th>
										<th class="f_15">${item.issue_summary}</th>
										<th class="f_15">${item.created_on}</th>
										<th class="f_15">${item.help_topic}</th>
										
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
         </div>
	</div>
  </form:form>
</body>
</html>