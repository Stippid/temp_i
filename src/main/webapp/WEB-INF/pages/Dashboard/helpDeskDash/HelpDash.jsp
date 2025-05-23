<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
HttpSession sess = request.getSession(false);
if (sess.getAttribute("userId") == null) {
	response.sendRedirect("~/login");
	return;
}
%>
<script type="text/javascript" src="js/printWatermark/common.js"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/assets/scss/style.css">
<script src="js/printWatermark/printAllPages.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/printWatermark/cueWatermark.css">
<script src="js/printWatermark/cueWatermark.js" type="text/javascript"
	nonce="${cspNonce}"></script>
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"
	nonce="${cspNonce}"></script>
<script type="text/javascript"
	src="js/amin_module/helpdesk/jquery-1.12.3.js" nonce="${cspNonce}"></script>

<style  nonce="${cspNonce}">
.custom-bg {
    background-color: #2066af; /* Dark blue color */
}
.custom-orange-bg {
    background-color: #df450c; /* Coral orange color */
}
.custom-dark-purple-bg {
    background-color: #4d375e; /* Dark purple color (Indigo) */
}
.custom-dark-yellow-bg {
    background-color: #dbaa17; /* Dark purple color (Indigo) */
}
h1 {
    margin-bottom: 30px; /* Adjust the value as needed */
}



</style>
<script nonce="${cspNonce}">
	var username = "${username}";
</script>






<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Helpdesk Dashboard</title>
  
</head>
<body>


<div class="container mt-8">
    <h1 align="center"> Helpdesk Dashboard</h1>
    <div class="row">
        <div class="col-md-4">
            <div class="card text-white bg-success mb-2">
                <div class="card-header">Created Tickets</div>
                <div class="card-body">
                    <h5 class="card-title"><b>${getcreateTicketCount[0].total}</b></h5>
                </div>
            </div>
        </div>
          <div class="col-md-4">
            <div class="card text-white custom-bg mb-4">
                <div class="card-header">Pending Tickets</div>
                <div class="card-body">
                      <h5 class="card-title"><b>${getpendingTicketCount[0].total}</b></h5>
                </div>
            </div>
        </div>
       <div class="col-md-4">
            <div class="card text-white custom-orange-bg mb-4">
                <div class="card-header">Assigned Tickets</div>
                <div class="card-body">
                    <h5 class="card-title"><b>${getassignTicketCount[0].total}</b></h5>
                </div>
            </div>
        </div>
         <div class="col-md-4">
            <div class="card text-white custom-dark-purple-bg mb-4">
                <div class="card-header">In-Progress Tickets</div>
                <div class="card-body">
                    <h5 class="card-title"><b>${getinprogressTicketCount[0].total}</b></h5>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card text-white custom-dark-yellow-bg mb-4">
                <div class="card-header">Resolved Tickets</div>
                <div class="card-body">
                    <h5 class="card-title fa fa-check-square-o"><b>${getCompletedTicketCount[0].total}</b></h5>
                </div>
            </div>
        </div>

      

     
    </div>
</div>


</body>
</html>