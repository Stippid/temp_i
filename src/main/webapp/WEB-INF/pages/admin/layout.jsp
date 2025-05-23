<%-- <%@page import="com.itextpdf.text.log.SysoLogger"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<dandelion:asset cssExcludes="datatables"/> 
<dandelion:asset jsExcludes="datatables"/> 
<dandelion:asset jsExcludes="jquery"/>  
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) {
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
	
	String user_agentWithIp = String.valueOf(sess.getAttribute("user_agentWithIp"));
	String userAgent = request.getHeader("User-Agent");
    String ip = "";

	if (request != null) {
        ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || "".equals(ip)) {
            ip = request.getRemoteAddr();
        }
    }
	String currentuser_agentWithIp = userAgent+"_"+ip;
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#40;","(");
	currentuser_agentWithIp = currentuser_agentWithIp.replace("&#41;",")");
	
	//out.print(currentuser_agentWithIp+"<=c = s=>"+user_agentWithIp);
	if(!user_agentWithIp.equals(currentuser_agentWithIp)){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><spring:message code="myapp.title" /></title>
  	<link rel="shortcut icon" href="../login_file/favicon.png"   nonce="${cspNonce}"  >
  	<link rel="stylesheet" href="js/assets/css/bootstrap.min.css"  nonce="${cspNonce}" >
  	<link rel="stylesheet" href="js/assets/css/font-awesome.min.css"  nonce="${cspNonce}" >
	<link rel="stylesheet" href="js/assets/scss/style.css"  nonce="${cspNonce}" >
	
	<script type="text/javascript" src="js/assets/js/vendor/jquery-2.1.4.min.js"  nonce="${cspNonce}" ></script> 
	<script src="js/assets/js/plugins.js"  nonce="${cspNonce}" ></script> 
	<script src="js/assets/js/main.js"  nonce="${cspNonce}" ></script> 

	<script type="text/javascript"  nonce="${cspNonce}" >
		var roleAccess = '${roleAccess}';
		var role = '${role}';
		var user_agent = '${user_agent}';
		var army_no = '${army_no}';
		var otpKey = '${otpKey}';
		
		var tbl, div;
     	function resetTimer() {
        	if (jQuery('#div_timeout').length) {  jQuery('#div_timeout').html(timeout());  }
     	}
     	function timeout() { return '600'; }
     	function getsubmodule(id){ localStorage.setItem("subModule", id); }
     	
     	var key = "${_csrf.parameterName}";
     	var value = "${_csrf.token}";
     	
     	jQuery(document).on('keypress', function(event) {
     		localStorage.setItem("army_no", army_no);
     		
     		var regex = new RegExp("${regScript}");
     	    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
     	    if (!regex.test(key)) {
     	       event.preventDefault();
     	       return false;
     	    } 
     	});
     	
   		jQuery(document).ready(function() {	
   			jQuery('body').bind('cut copy paste', function (e) {
	   	        e.preventDefault();
	   	    });
   			
   			// set current sub module
   			jQuery('ul#Dropdown_'+localStorage.getItem("subModule")).attr("class","dropdown-menu scrollbar show");
   			setInterval(function() {
				var today = new Date();
				var date =("0" + today.getDate()).slice(-2)+'-'+ ("0" + (today.getMonth()+1)).slice(-2)+'-'+today.getFullYear();
				var time = ("0" + today.getHours()).slice(-2) + ":" + ("0" + today.getMinutes()).slice(-2);// + ":" + ("0" + today.getSeconds()).slice(-2);
				var dateTime = date+' '+time;
				jQuery("#datetime").text(dateTime);
				
				if (jQuery('#div_timeout').length) {
	            	 var tt = jQuery('#div_timeout').html();
	                 if (tt === undefined) {
	                     tt = timeout();
	                 }
	                 var ct = parseInt(tt, 10) - 1;
	                 jQuery('#div_timeout').html(ct.toString().padStart(3, '0'));
	                 if (ct === 0) {
	                	 localStorage.clear();
	                	 formSubmit();
	                 }
	             } else {
	            	 event.preventDefault(); 
					 localStorage.clear();
	            	 formSubmit();
	             }
			}, 1000);
			try
			{
				var msg = document.getElementById("msg").value;
				if(msg != null )
				{
					alert(msg);
				}
			}
			catch (e) {
			}
		});
		function formSubmit() {
		
			document.getElementById("logoutForm").submit();
		}
		popupWindow = null;
		function parent_disable() {
			if(popupWindow && !popupWindow.closed)
				popupWindow.focus();
		}
	</script>
	<script type="text/javascript"  nonce="${cspNonce}" >
		document.onkeydown = function(e) {
			if(e.keyCode == 123) { return false; }
			if(e.keyCode == 44) {  return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; } 
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)){ return false; }
			if(e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)){ return false; }
		}
		document.addEventListener('DOMContentLoaded', function () {
		document.getElementById('layout_body').onchange = function() {
			parent_disable();resetTimer();
	  	};
		document.getElementById('layout_body').onfocus = function() {
			parent_disable();
	  	};
	  	 document.getElementById('layout_body').addEventListener('contextmenu', function(event) {
		        event.preventDefault(); 
		    });
		});
		
		document.addEventListener('DOMContentLoaded', function() {
			  
			document.getElementById('logout_btn').onclick = function() {
			
				 event.preventDefault(); 
				 localStorage.clear();
					formSubmit();
			  	};
			});
		
		
	</script>
	
	
	
</head>
<body id="layout_body" name="layout_body"  >
		<c:if test="${not empty msg}">
			<input type="hidden" name="msg" id="msg" value="${msg}"  disabled="disabled"/>
		</c:if>
		
		<div id="right-panel" class="right-panel"> 
			<!-- Header-->
		  	<header id="header" class="header">
		    	<div class="header-inner">
						<div class="header-logo"><img src="js/miso/images/header-logo.jpg"></div>
						
		      			<div class="header-title">
    <h1 class="header-title-main">
        <spring:message code="myapp.name"/>
    </h1>
    <h2 class="header-title-version">VERSION 1.0</h2>
</div>
		        		
		        		<div class="header-logo"><img src="js/miso/images/header-logo.jpg"></div>
		    	</div>
		  	</header>
		  	

		  	<a id="menuToggle" class="side-menu"><i class="fa fa fa-tasks"></i></a>
		  	
		  	
		  	<tiles:insertAttribute name="menu" />
		  	
		  		<!-- <div class="logout-button">
					<a href="javascript:formSubmit();" onclick="localStorage.clear();"><button class="btn-danger" type="submit" ><i class="fa fa-sign-in pr6"></i><b>Logout</b></button></a>
				</div> -->
				
		  	
		  	<div class="content mt-3" > 
		  		<div id="WaitLoader" class="display_none" align="center">
					<span id="">Processing Data.Please Wait ...<i  class="fa fa-hourglass fa-spin f_18"></i></span>
				</div>
				<tiles:insertAttribute name="body" />
		  	</div>
		</div>
	</body>
</html>
