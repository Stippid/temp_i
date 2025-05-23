<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js" nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"nonce="${cspNonce}"></script>  
<script src="js/amin_module/helpdesk/jquery-2.2.3.min.js"nonce="${cspNonce}"></script>
<script type="text/javascript" src="js/amin_module/helpdesk/jquery-1.12.3.js"nonce="${cspNonce}"></script>
<link rel="stylesheet" href="js/autoComplate/autoComplate.css"nonce="${cspNonce}">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"nonce="${cspNonce}"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"nonce="${cspNonce}"></script>
<style nonce="${cspNonce}">
input[type="text"]{
  text-transform: capitalize;
}
</style>
<form:form   name="faq" id="faq" action="faqAction" method='POST' modelAttribute="faqCMD">
<div  id="div_faq">
<div class="col-md-12" align="center">
 <div class="card">
	<div class="card-header"> <h5>SEARCH FAQ</h5></div>
	  <div class="card-body card-block cue_text">
					<div class="col-md-12 form-group">
								<div class="col col-md-2">
									<label for="text-input" class=" form-control-label"><strong class="color_red">*</strong>Section</label>
								</div>
							<div class="col-12 col-md-10">
									<input type="text" id="section" name="section" class="form-control autocomplete font_familiy_awsome"  placeholder="&#xF002; Search" autocomplete="off" maxlength="20">
								</div>
					</div>
		       </div>
	        <div class="card-footer" align="center">
               <i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" id="search_btn" >
           </div> 
			<div class="card-body display_none" id="divPrint" s>
					<div id="divShow display_block"  ></div>
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="TicketReport"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead class="width8">
								<tr>
									<th class="width3 text_align_center" >Ser No</th>
									<th class="width20 text_align_center">Question</th>
									<th class="width72 text_align_center">Answer</th>
									<th class="width5 text_align_center" >Action</th>
								</tr>
							</thead>
							<tbody class="width8">
								<c:forEach var="item" items="${list}" varStatus="num">
									<tr>
										<th class="width3 text_align_center">${num.index+1}</th>
										<th class="width20 text_align_center">${item.question}</th>
										<th class="width72 text_align_center">${item.answer}</th>
										<th id="thAction1" class='lastCol width5 text_align_center'>${item.id}</th>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				</div>
				</div>

</div>
</form:form> 
<c:url value="TicketSearchList1" var="searchUrl" />
    			<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="question1">
    			<input type="hidden" name="section1" id="section1" value="0"/>
    		</form:form>
<c:url value="UpdateFAQ" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid" >
		<input type="hidden" name="updateid" id="updateid" value="0"/>
</form:form>  
  		
<script nonce="${cspNonce}">
document.addEventListener('DOMContentLoaded', function() {
	document.getElementById('div_faq').onclick = function() {
		return parent_disable1();	
  	};
	document.getElementById('div_faq').onfocus = function() {
		 return parent_disable1();
  	};
  	document.getElementById('search_btn').onclick = function() {
		 return Search();
  	};
  
  	
  	document.querySelectorAll('.action_update').forEach(icon => {
         icon.addEventListener('click', function() {
             var id = this.getAttribute('data-id');
             if (confirm('Are you sure you want to update?')) {
                 editData(id);
             } else {
                 return false;
             }
         });
     });

     // Handle delete button clicks
     document.querySelectorAll('.action_delete').forEach(icon => {
         icon.addEventListener('click', function() {
             var id = this.getAttribute('data-id');
             if (confirm('Are you sure you want to delete?')) {
                 deleteData(id);
             } else {
                 return false;
             }
         });
     });
  	
  	
  	
  	
  
});
  	
 $(function() {
var wepetext1=$("#section");
wepetext1.autocomplete({
     source: function( request, response ) {
      $.ajax({
      type: 'POST',
      url: "getsectionlist?"+key+"="+value,
      data: {section : document.getElementById('section').value},
        success: function( data ) {
      	  var susval = [];
      	  var length = data.length-1;
      	  var enc = data[length].substring(0,16);
	        	for(var i = 0;i<data.length;i++){
	        		susval.push(dec(enc,data[i]));
	        	} 
	        	  	          
			response( susval ); 
        }
      });
    },
    minLength: 1,
    autoFill: true,
    change: function(event, ui) {
  	 if (ui.item) {   	        	  
      	  return true;    	            
        }  
    }, 
  });
}); 

function parent_disable1() {
	if(newWin && !newWin.closed)
		newWin.focus();
}  

function getRefreshReport(page,msg)
{
	if(msg.includes("Updated+Successfully"))
	{
		if(page == "Edit_faq1")
		{
			Search();
		}
	}
} 
var newWin;
function editData(id){	
	/* var x = screen.width / 2 - 1100 /2;
    var y = screen.height/2 - 900/2;
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes'); */
	/* window.onfocus = function () { 
	} */
	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}

function deleteData(id){	
	$.post("deleteFAQdetailsUrlAdd?"+key+"="+value,{deleteid : id}, function(j) {
		alert(j)
		 Search();
	}); 
} 
function Search(){
	if($("#section").val() ==""){
		alert("Please enter section")
		$("#section").focus();
		return false;
	}
		$("#section1").val($("#section").val());
	    document.getElementById('searchForm').submit();
}
$(document).ready(function() {
	if('${section1}' != ""){
		 $("#section").val('${section1}');
		 $("#divPrint").show();
		 }
	if('${list.size()}' == 0 ){
	     $("table#TicketReport").append("<tr><td colspan='4' class='text_align_center'>Data Not Available</td></tr>");
		}
	 try{
		 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg=")[0];
			var m=  window.location.href.split("?msg=")[1];
			window.location = url;
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('Edit_faq1',m);
				window.close('','_parent','');
			}
		} 	
	}
	catch (e) {
	} 
	});
</script>