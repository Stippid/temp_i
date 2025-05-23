<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="js/cue/cueWatermark.css" nonce="${cspNonce}">
<script src="js/cue/cueWatermark.js" type="text/javascript" nonce="${cspNonce}"></script>
<div class="container"  id="divPrint"  >
         		<div class="card-header" align="center"> <h5>Status of Inactive Users (Inactive since more than one month)</h5><!-- <strong>Schedule Details </strong> --></div>
         	
	
			<div id="divShow display_block" ></div>
			<div  class="watermarked display_block" data-watermark="" id="divwatermark">
				<span id="ip"></span>
		     <table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
						<thead >
							<tr>
								<th class="f_15 width200 text_align_center" >User Name</th>	
								<th class="f_15 width200 text_align_center" >Last Login date</th>	
							
							</tr>
						</thead> 
				<tbody>
						<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td class="f_15 text_align_center">${item.username}</td>	
											<td class="f_15 text_align_center">${item.date}</td>	
										
									</tr>
							</c:forEach>
		                </tbody>
		        </table>
        	</div>	
		</div>	 
<script nonce="${cspNonce}" >
	watermarkreport();
</script>