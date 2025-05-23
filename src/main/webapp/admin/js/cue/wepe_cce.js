function ccename(val)
{		 
	if(val != "" && val != null)
	{
		 $.post("getitemcode?"+key+"="+value, {val:val}).done(function(j) {
	    	 	document.getElementById("cescodehidden").value = j[0];	
				getCesNameByExistingData(j[0]);
	      }).fail(function(xhr, textStatus, errorThrown) { }); 		
	} 
}

function ccenameforseq(val)
{	
	if(val != "" && val != null)
	{
	  $.post("getitemcode?"+key+"="+value, {val:val}).done(function(j) {
    	 document.getElementById("item_seq_nohidden").value = j[0];		
			var subcesname =j[0];
			var n = $('input:radio[name=ces_cces]:checked').val();
			var no = document.getElementById("ces_cces_no").value
			var cesname =document.getElementById("cescodehidden").value;
			 if(cesname==j[0])
			 {
				 alert("SubItem name should not same as item name" );	
				 document.getElementById("item_seq_no1").focus(); 
			 }
      }).fail(function(xhr, textStatus, errorThrown) { }); 	
	} 
}



function getcceno(val)
{	
	var n = $('input:radio[name=ces_cces]:checked').val();
	document.getElementById("ces_ccestype").value=$('input:radio[name=ces_cces]:checked').val();
	
	 var wepetext1=$("#ces_cces_no");
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getexistingces_cces_no?"+key+"="+value,
	        data: {val : n,ces_cces_no:document.getElementById('ces_cces_no').value},
	          success: function( data ) {
	        	  if(data.length > 1){
		        	  var susval = [];
		        	  var length = data.length-1;
	  	        		 var enc = data[length].columnName1.substring(0,16);
	                      for(var i = 0;i<data.length-1;i++){
	                       susval.push(dec(enc,data[i].columnName));
		        	  	}
	                     	          
					response( susval ); 
	        	  }
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
}


function edititemname(val)
{
	if(val.includes(","))
	{
	var v =val.toString().split(",");
	}
	if(v[0]!=null)
		{
		
		 $.ajax({
	          type: 'POST',
	          url: "getitemnamefromcode?"+key+"="+value,
	          data: {
	        	  val:v[0]
	          },
	          success: function(j) {	    
	        	  document.getElementById("ces_cces_name1").value=j;   
	          }
	        });
		
		}
	if(v[1]!=null)
	{
		
	 $.ajax({
          type: 'POST',
          url: "getitemnamefromcode?"+key+"="+value,
          data: {
        	  val:v[1]
          },
          success: function(j) {       
        	  document.getElementById("item_seq_no1").value   =j;   
          }
        });
	
	}
	
	 var wepetext1=$("#item_seq_no1");
	  wepetext1.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	       	url: "getitemtype?"+key+"="+value,
	        data: {item_type:document.getElementById('item_seq_no1').value},
	          success: function( data ) {
	        	  if(data.length > 1){
	        	  var susval = [];
	        	  var length = data.length-1;
 	        		 var enc = data[length].columnName1.substring(0,16);
                     for(var i = 0;i<data.length-1;i++){
                      susval.push(dec(enc,data[i].columnName));
	        	  	}
		        	   	          
				response( susval ); 
	          }
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	alert("Please Enter Approved Nomenclature Stores");
	        	wepetext1.val("");
	        	wepetext1.focus();
	        	return false;	             
	          }   	         
	      }, 
	    });
}

function ccenopageload(val)
{
	 var n = $('input:radio[name=ces_cces]:checked').val(); 
	 
	 $.post("getdetailexistingces_cces_no?"+key+"="+value, {val:val,cestype:n}).done(function(j) {
		 if(j[0]!="" && j[0]!=null && j[0]!=undefined)
		{ 
			document.getElementById("efctiv_date").value =j[0].efctiv_date ;
			document.getElementById("expiry_date").value =j[0].expiry_date ; 
			document.getElementById("cescodehidden").value =j[0].ces_cces_name;						 
		}
		else
		{
			document.getElementById("ces_cces_name1").value="";
			document.getElementById("efctiv_date").value ="";
			document.getElementById("expiry_date").value ="";
		}				
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
	 
}

function getCesNameByExistingData(val)
{
	if(val != "" && val != null){
		
		     $.post("getdetailexistingces_cces_name?"+key+"="+value, {val:val}).done(function(j) {
	 	    	if(j[0]!="" && j[0]!=null && j[0]!=undefined)
				{		
					$("input[type=radio][name=ces_cces][value="+j[0].ces_cces+"]").prop('checked', true);
					document.getElementById("ces_cces_no").value =j[0].ces_cces_no;	
					document.getElementById("hidces_cces_name1Exist").value =j[0].ces_cces_no;
					
					var ed = new Date(j[0].efctiv_date);
					var edate = ed.getFullYear() +'-'+ ("0" + (ed.getMonth()+1)).slice(-2)+'-'+ ("0" + ed.getDate()).slice(-2);
					
					document.getElementById("efctiv_date").value = edate; 
					
					var exd = new Date(j[0].expiry_date);
					var exdate = exd.getFullYear() +'-'+ ("0" + (exd.getMonth()+1)).slice(-2)+'-'+ ("0" + exd.getDate()).slice(-2);
					document.getElementById("expiry_date").value = exdate ; 			
				}
				else
				{		
					if($("input[type=radio][name=ces_cces]").val() == undefined)
						$("input[type=radio][name=ces_cces]").prop('checked', false);
					
					if(document.getElementById("hidces_cces_name1Exist").value == "" && document.getElementById("hidces_cces_name1Exist").value == null)				
						document.getElementById("ces_cces_no").value ="";
					
					document.getElementById("efctiv_date").value ="";
					document.getElementById("expiry_date").value ="";
				}	
	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	}
	else
	{
		document.getElementById("ces_cces_name1").value="";
		document.getElementById("efctiv_date").value ="";
		document.getElementById("expiry_date").value ="";
	}
}


function cceno(val)
{
	var n = $('input:radio[name=ces_cces]:checked').val(); 
	 $.post("getdetailexistingces_cces_no?"+key+"="+value, {val:val,cestype:n}).done(function(j) {
		 if(j[0]!="" && j[0]!=null && j[0]!=undefined)
			{
				var itemname =j[0].ces_cces_name;
				
	   	 	    $.post("getitemnamefromcode?"+key+"="+value, {val:itemname}).done(function(j) {
	   	 	    	document.getElementById("ces_cces_name1").value =j;
	   	 	    }).fail(function(xhr, textStatus, errorThrown) { }); 
				
	   	 	     
	   	 	    var ed = new Date(j[0].efctiv_date);
	   	 	    var edate = ed.getFullYear() +'-'+ ("0" + (ed.getMonth()+1)).slice(-2)+'-'+ ("0" + ed.getDate()).slice(-2);
				document.getElementById("efctiv_date").value = edate; 
				
				var exd = new Date(j[0].expiry_date);
				var exdate = exd.getFullYear() +'-'+ ("0" + (exd.getMonth()+1)).slice(-2)+'-'+ ("0" + exd.getDate()).slice(-2);
				document.getElementById("expiry_date").value = exdate ; 			
	   	 	     
	   	 	    document.getElementById("cescodehidden").value =j[0].ces_cces_name;						 
	 			
			}
			else
			{
			document.getElementById("ces_cces_name1").value="";
			document.getElementById("efctiv_date").value ="";
			document.getElementById("expiry_date").value ="";
			document.getElementById("cescodehidden").value="";
			}				
	      }).fail(function(xhr, textStatus, errorThrown) { }); 
	 
	 
}

function existsave()
{ 		   	
		  var r =  $('input:radio[name=ces_cces]:checked').val();	
		  if(r == undefined)
		  {		 
			    alert("Please select Type");
			    $('input:radio[name=ces_cces]:checked').focus();
				return false;
		  } 
		  
	  	 if($("input#ces_cces_no").val() == "")
		 {
			 alert("Please enter CES/CCES No.");
			 $("input#ces_cces_no").focus();
			 return false;
		 }
	  	 
	  	 if($("input#ces_cces_name1").val() == "")
		 {
			 alert("Please enter CES/CCES Name");
			 $("input#ces_cces_name1").focus();
			 return false;
		 }
	  	 if($("input#efctiv_date").val() == "")
		 {
			 alert("Please enter Effective Date");
			 $("input#efctiv_date").focus();
			 return false;
		 }
	   
	  	 if($("input#expiry_date").val() == "")
		 {
			 alert("Please enter Expiry Date");
			 $("input#expiry_date").focus();
			 return false;
		 }
	  	 
		 if($("input#item_seq_no1").val() == "")
		 {
			 alert("Please enter Sub Item");
			 $("input#item_seq_no1").focus();
			 return false;
		 }
		
		 	 
		 if($("input#auth_proposed").val() == "" || $("input#auth_proposed").val() == "0.0" || $("input#auth_proposed").val() == "0")
		 {
			 alert("Please enter Proposed Auth");
			 $("input#auth_proposed").focus();
			 return false;
		 }
		
	 
		return true; 
	 
}


function clearall()
{
	 
	document.getElementById("efctiv_date").value ="" ;
	document.getElementById("expiry_date").value ="" ; 
	document.getElementById("cescodehidden").value ="";
 
	localStorage.clear();
	localStorage.Abandon();
}


////////////////////// 
function SerachIngetName(val)
{
	if(val != "" || val != null){
		var n = $('input:radio[name=ces_cces]:checked').val(); 
		     
		$.post("getdetailexistingces_cces_no?"+key+"="+value, {val:val,cestype:n}).done(function(j) {
			if(j[0]!="" && j[0]!=null && j[0]!=undefined)
			{
				var itemname =j[0].ces_cces_name;
				document.getElementById("item_seq_no").value =itemname;
				
				
	   	 	     $.post("getitemnamefromcode?"+key+"="+value, {val:itemname}).done(function(j) {
	   	 	    	document.getElementById("nomenClature").value =j;
	   	 	      }).fail(function(xhr, textStatus, errorThrown) { }); 
			}
		}).fail(function(xhr, textStatus, errorThrown) { }); 
		
	}
}

function isNumberKey(evt) {

	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;

}