<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/>
<dandelion:asset jsExcludes="datatables"/>
<dandelion:asset jsExcludes="jquery"/>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script> 
<script src="js/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<link rel="stylesheet" href="js/amin_module/rbac/report/criteriareportdesign.css">

<style>

.dataTables_scrollBody{
	overflow-x: hidden !important;
	overflow-y: scroll !important;
	scrollbar-width: thin;
}
.dataTables_scrollHead{
	overflow-y: hidden !important;
}
.ui-toolbar.ui-widget-header,
.dataTables_scrollHead.ui-state-default{
   width: calc(100% - 8px) !important;
}
.dataTables_scrollHeadInner{
    padding-right: 0 !important;
    width: 100% !important;
}
.dataTable{
  width: 100% !important;

}  
.dataTables_wrapper{
	opacity: 0.9;
}
</style>
<div class="container" align="center">
      	<div class="card" >
		<div class="card-header"> <h5>Formation Dashboard Access Log</h5></div>
      		<div class="card-body card-block cue_text" >
      			<div class="col-md-12">
					<datatables:table id="applicanttbl1" url="dashboardAccessLogList" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_"  displayLength="10" lengthMenu="10,20,100,500,5000"  jqueryUI="true" 
					bDestroy="true" filterable="false" sortable="false" processing="false" border="1"  autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollCollapse="true" >  
				     <datatables:column title="Ser" property="id" searchable="false" data-halign="left" data-valign="left" />
				     <datatables:column title="User Id" property="username" searchable="true" data-halign="left" data-valign="left" />
<%-- 				     <datatables:column title="Unit Name" property="unit_name" searchable="true" data-halign="left" data-valign="left" /> --%>
				     <datatables:column title="Army No" property="army_no" searchable="true" data-halign="left" data-valign="left" />
				     <datatables:column title="Last Viewed On" property="created_on" renderFunction="ParseDateColumn" searchable="false" data-halign="left" data-valign="left" />
				     <datatables:column title="Level In Hierarchy" property="dashboard_role" searchable="true" data-halign="left" data-valign="left" />
				</datatables:table>
				</div>
			</div>
      	</div>
</div>
<script>
	function ParseDateColumn(data, type, row) {
		var d = new Date(data);
		var date=d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		if(date=="NaN-NaN-NaN")
			date="";
		return date;
	}
</script>