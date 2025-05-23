<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="js/layout/css/style_db.css" rel="stylesheet">
<script src="js/assets/js/jquery-2.1.4.min.js"></script>
<div class="tunnel_design">
	<div class="square tunnel_active dropdown" style = "width: 120px">
		<h5 class="tunnel_text dropbtn">IT Asset</h5>
		<div class="dropdown-content">
			<a href="onHoldingDashboard">Holding/Servicable-Un-Servicable</a>  
			<a href="WarrentyDashboard">Warranty </a> 
			<a href="AntivirusDashboard">Antivirus</a> 
			<a href="ADNDashboard">ADN Functionality</a> 
		</div>
	</div>
</div>