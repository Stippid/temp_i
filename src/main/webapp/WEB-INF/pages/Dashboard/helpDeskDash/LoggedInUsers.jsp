<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/printWatermark/common.js"nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css"nonce="${cspNonce}">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"nonce="${cspNonce}"></script> 
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css"nonce="${cspNonce}">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css"nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css"nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/scss/style.css"nonce="${cspNonce}">
<script src="js/commonJS/commonmethod.js" type="text/javascript"nonce="${cspNonce}"></script>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 

	
%>

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
<form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' modelAttribute="role_mstCMD">
	<div class="container" align="center">
       	<div class="card">
    		<div class="card-header"> <h5>Logged In Users</h5><!-- <strong>Schedule Details </strong> --></div>
         	<div class="card-body" >
		 		<div  class="watermarked display_block" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
					<table id="LoggedinReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead >
							<tr>
								<th class="width10">Ser No</th>	
								<th class="width25">Loggedin Users</th>	
								<th class="width50">Service No</th>	
								<th class="width15">Section</th>	
							</tr>
						</thead> 
						<tbody class="font_weight_bold">
							<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td class="width10">${num.index+1}</td>
										<td class="width25">${item.login_name}</td>
										<td class="width50">${item.army_no}</td>
										<td class="width15">${item.section}</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div> 
         </div>
	</div>
  </form:form>
   <c:url value="loggedin_report" var="loggedin_reportUrl" />
		<form:form action="${loggedin_reportUrl}" method="post" id="searchForm" name="searchForm" >
		</form:form> 
</body>
</html>