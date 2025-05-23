<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) {
		response.sendRedirect("/login");
		return;
	}
%>

<script nonce="${cspNonce}">
	var username="${username}";
	var curDate = "${curDate}";
	
	//var addiphostna ='${ip}';
</script>
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm" name="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">
			<div class="user-title">
		    	  <div class="login-profile" id="" aria-haspopup="true" aria-expanded="false">
  
  					<img src="js/img/nav-profile.png" class="img">

 				 <div class="user-name">
		      		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation"> <i class="fa fa-bars"></i> </button>
		      		<a class="navbar-brand role-name" href="#"><i class="menu-icon fa fa-user"></i>${roleloginName}</a> 
		      	</div>

 				 </div>
 				 

 				 
 				 
 				  				<span class=""> Session timeout in <i class="fa fa-hourglass fa-spin f_10"></i>  :  <b class="div_timeout" id="div_timeout">600</b></span>
 				<div class="search-container">
  <div class="search-icon">
    <i class="fa fa-search"></i>
  <input type="text" id="menu-search" name="menu-search" placeholder="Search Menu" autocomplete="off" >
  </div>
</div>
        	</div>
	    	

	    	<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					${menu}
				</ul> 
				
	    	</div> 
	    	<div class="logout-button">
					<a  id="href_logout"><button class="btn-danger" type="button" id="logout_btn" ><i class="fa fa-sign-in pr6"></i><b>Logout</b></button></a>
				</div>
				
	    	
	  	</nav>
	  		
	  	<script nonce="${cspNonce}">
	
	  	

	 var menuItems = document.querySelectorAll('.nav-item.dropdown');// bind all menu
	 var subItems = document.querySelectorAll('.dropdown-item'); // bind sub menu

	 var searchInput = document.getElementById('menu-search');

	 searchInput.addEventListener('input', function () {

	     var query = this.value.toLowerCase();

	     menuItems.forEach(item => {

	    		
	         var itemName = item.querySelector('.nav-link').innerText.toLowerCase();//main menu
	         var submoduleDropdown = item.querySelector('.dropdown-menu'); //bind submenu and screen

	         if (itemName.includes(query)) {
	             item.style.display = 'block';
	             if (submoduleDropdown) {
	                 submoduleDropdown.style.display = 'block';
	                 submoduleDropdown.classList.add('show');
	                 submoduleDropdown.querySelectorAll('.dropdown-item').forEach(subItem => {
	                     subItem.style.display = 'block';
	                 });
	                 submoduleDropdown.querySelectorAll('ul.dropdown-menu').forEach(subMenu => {
	                     subMenu.style.display = 'block'; // Show all ul.dropdown-menu elements
	                 });
	             }
	         } else {

	 			
	             let submoduleMatch = false;
	             let screenMatch = false;

	             if (submoduleDropdown) {
	                 submoduleDropdown.style.display = 'none';
	                 submoduleDropdown.classList.remove('show');

	                 submoduleDropdown.querySelectorAll('.dropdown-item').forEach(subItem => {
	                     var subItemName = subItem.querySelector('a').innerText.toLowerCase();
	                     var screenLinks = subItem.querySelectorAll('ul.dropdown-menu li');

	                     if (subItemName.includes(query)) {
	                         submoduleMatch = true;
	                         subItem.style.display = 'block';
	                         submoduleDropdown.style.display = 'block';
	                         submoduleDropdown.classList.add('show');
	                         subItem.querySelectorAll('ul.dropdown-menu').forEach(subMenu => {
	                             subMenu.style.display = 'block'; // Show all ul.dropdown-menu elements
	                         });
	                     } else {

	             			
	                         let screenItemMatch = false;

	                         screenLinks.forEach(screenItem => {
	                             var screenName = screenItem.querySelector('a').innerText.toLowerCase();

	                 			
	                             if (screenName.includes(query)) {
	                                 screenItem.style.display = 'block';
	                                 screenItemMatch = true;
	                                 submoduleDropdown.style.display = 'block';
	                                 submoduleDropdown.classList.add('show');
	                                 subItem.style.display = 'block';
	                                 item.style.display = 'block';
	                                 subItem.querySelector('ul.dropdown-menu').style.display = 'block'; // Show the ul element
	                                 var parentModules = item.querySelectorAll('.nav-item.dropdown');
	                                 parentModules.forEach(parentModule => {
	                                     parentModule.style.display = 'block';
	                                 });
	                             } else {
	                                 screenItem.style.display = 'none';
	                             }
	                         });

	                         if (screenItemMatch) {

	                 			
	                             submoduleMatch = true;
	                             subItem.style.display = 'block';
	                         } else {
	                             subItem.style.display = 'none';
	                         }
	                     }
	                 });

	     			
	                 if (submoduleMatch) {
	                     item.style.display = 'block';
	                 } else {
	                     item.style.display = 'none';
	                 }
	             } else {
	                 item.style.display = 'none';
	             }
	         }
	     });
	 });

	 document.addEventListener('DOMContentLoaded', function() {
		    // Add event listener for dropdown links
		   
		    var submoduleLinks = document.querySelectorAll('.submodule-link');
		    submoduleLinks.forEach(link => {
		    	
		        link.addEventListener('click', function(event) {
		        	
		            var submoduleId = this.id.split('_')[1]; // Extract the submodule ID
		            getsubmodule(submoduleId); // Call the function to handle the submodule
		        });
		    });
		   
		    // Add event listener for screen links
		    var screenLinks = document.querySelectorAll('.screen-link');
		    screenLinks.forEach(link => {
		    	
				
		        link.addEventListener('click', function() {
		            localStorage.Abandon(); // Call the function to handle localStorage
		        });
		    });
		});
	</script>
</aside>