<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/cue/cueWatermark.css">

<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport"
			style="font-size: x-large; color: green; text-align: right;"
			aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel1();"></i>
	</div>
<c:url value="demo_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form> 
<c:url value="demo_report1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="comd2">
	<input type="hidden" name="typeReport2" id="typeReport2" value="0" />
</form:form> 
<Script>
function getExcel() {
	

	document.getElementById('typeReport1').value = 'excelL';
	document.getElementById('search2').submit();

} 
function getExcel1() {
	

	document.getElementById('typeReport2').value = 'excelL';
	document.getElementById('search1').submit();

} 
</Script>