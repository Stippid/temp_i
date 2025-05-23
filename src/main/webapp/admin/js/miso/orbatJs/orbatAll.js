jQuery(document).ready(function() {
	//Oprational
	jQuery('select#op_comd').change(function() {
   		var fcode = this.value;
   		getOPCorps(fcode);
   		
   		fcode += "00";
   		getOPDiv(fcode);
   		
   		fcode += "000";
   		getOPBde(fcode);
   	});
	jQuery('select#op_corps').change(function() {
       	var fcode = this.value;
       	getOPDiv(fcode);
       	fcode += "000";
       	getOPBde(fcode);
   	});
   	jQuery('select#op_div').change(function() {
       	var fcode = this.value;    	   	
	   	getOPBde(fcode);
   	});
   	
   	//Control
   	jQuery('select#cont_comd').change(function() {
   		var cont_cname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_cname").val(cont_cname);
     	jQuery("#cont_aname").val("");
  		jQuery("#cont_bname").val("");
		jQuery("#cont_dname").val("");
   		
   		var fcode = this.value;
       	getCONTCorps(fcode);
       	fcode += "00";	
   		getCONTDiv(fcode);
       	fcode += "000";	
       	getCONTBde(fcode);
   	});
   	jQuery('select#cont_corps').change(function() {
   		var cont_aname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_aname").val(cont_aname);
     	jQuery("#cont_bname").val("");
		jQuery("#cont_dname").val("");
	        
       	var fcode = this.value;
       	getCONTDiv(fcode);
       	
       	fcode += "000";	
       	getCONTBde(fcode);
   	});
   	jQuery('select#cont_div').change(function() {
   		var cont_dname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_dname").val(cont_dname);
        jQuery("#cont_bname").val("");
		var fcode = this.value;    	   	
	   	getCONTBde(fcode);
   	});
   	
   	jQuery('select#cont_bde').change(function() {
   		var cont_bname = jQuery(this).find('option:selected').attr("name");    	   	
        jQuery("#cont_bname").val(cont_bname);
   	});
   	
   	//Admin
	jQuery('select#adm_comd').change(function() {
   		var fcode = this.value;
       	getADMCorps(fcode);
       	fcode += "00";
       	getADMDiv(fcode);
       	fcode += "000";	
       	getADMBde(fcode);
   	});
   	jQuery('select#adm_corps').change(function() {
       	var fcode = this.value;
       	getADMDiv(fcode);
       	fcode += "000";	
       	getADMBde(fcode);
   	});
   	jQuery('select#adm_div').change(function() {
       	var fcode = this.value;    	   	
	   	getADMBde(fcode);
   	});
	
});



	function getOPCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_corps").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_corps").html(options);
			}
		});
	}
	function getOPDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_div").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_div").html(options);
			}
		});
	} 
	function getOPBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		//alert(fcode1)
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substr(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#op_bde").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#op_bde").html(options);
			}
			
		});
	}

// Control

	function getCONTCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_aname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_corps").html(options);
			}else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#cont_corps").html(options);
			}
		});
	} 
	function getCONTDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   $.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		   if(j != ""){
			   	var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_dname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_div").html(options);
		   }else{
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				jQuery("select#cont_div").html(options);
			}
		});
	} 
	function getCONTBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
	  $.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
		  if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode == dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) + '" name="'+dec(enc,j[i][1])+'" selected=selected>'+ dec(enc,j[i][1]) + '</option>';
						jQuery("#cont_bname").val(dec(enc,j[i][1]));
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#cont_bde").html(options);
		  }else{
			  var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			  jQuery("select#cont_bde").html(options);
		  }
	  });
	}

// ADM 

	function getADMCorps(fcode){
		var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_corps").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_corps").html(options);
			}
		});
	}
	function getADMDiv(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2];
		$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_div").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_div").html(options);
			}
		});
	} 
	function getADMBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if(fcode ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				jQuery("select#adm_bde").html(options);
			}else{
				 var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				 jQuery("select#adm_bde").html(options);
			}
		});
	}
	
	
	function checkFileExt(file) {
	  	var ext = file.value.match(/\.([^\.]+)$/)[1];
		switch (ext) {
		  	case 'pdf':
		  	//alert('Allowed');
		    break;
		  	default:
		    	alert('Only *.pdf file extension allowed');
		   	file.value = "";
		}
	}
	