<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("/login"); return; } 	
%>

<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css"nonce="${cspNonce}">
<link rel="stylesheet" href="js/assets/scss/style.css"nonce="${cspNonce}">
<script src="js/commonJS/commonmethod.js" type="text/javascript"nonce="${cspNonce}"></script>
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"nonce="${cspNonce}"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/amin_module/rbac/report/criteriareportdesign.css"nonce="${cspNonce}">
<style type="text/css"nonce="${cspNonce}" >
		.cue_text input, textarea {
		    text-transform: unset;
		}
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
		.dataTables_scrollHead{
			overflow-y: auto !important; 
	        overflow-x: auto !important; 
		}
</style>
</head>
<body>
 	<form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' modelAttribute="role_mstCMD">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header"> <h5>Users Created</h5></div>
				<div class="card-body" >
				<datatables:table id="applicanttbl" url="getUserReportList_dashbored" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000" jqueryUI="true" 
				bDestroy="true" filterable="false" sortable="true" processing="true" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollCollapse="true" >  
				     <datatables:column title="Ser No" property="sr" searchable="false" data-halign="left" data-valign="left" />
				     <datatables:column title="User Name" property="login_name" searchable="false" data-halign="left" data-valign="left" />
				     <datatables:column title="User Id" property="username" searchable="false" data-halign="left" data-valign="left" />
				     <datatables:column title="Role" property="role" searchable="false" data-halign="left" data-valign="left" />
				</datatables:table> 
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>