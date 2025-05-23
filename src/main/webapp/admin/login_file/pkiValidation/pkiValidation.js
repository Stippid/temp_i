var timeOut = 0;
function getCheckPIKValidation()
{
	//for Test
	jQuery.ajax({
	    type: "POST",
	    url: "checkTest?"+key+"="+value,
	    contentType: false,
		cache: false,
		processData : false,
		success: function (result) {
			if(result == "Success"){
				window.location.href = 'admin/commonDashboard';
			}else{
				alert(result);
			}
		}
	});
	// for test
	
	/*window.location.href = 'JnlpFileDownload';
	document.getElementById("b1").click();
	jQuery("#TokenLoader").show();
	jQuery("#jnlpButton").attr("disabled", true);*/
}
function run(){
	//alert("Run Jar");
	jQuery.ajax({
		type: 'GET',
		url: "http://localhost:8089",
		success: function( data ,textstatus ,jqXHR) {
			console.log(data);
			var fd = new FormData();
			var myblob = new Blob([data])
	   		fd.append("doc", myblob);
			jQuery.ajax({
	   		    type: "POST",
	   		    data: fd,
	   		    url: "uploadDoc?"+key+"="+value,
	   		    contentType: false,
	   			cache: false,
	   			processData : false,
	   			success: function (result) {
	   				jQuery("#TokenLoader").hide();
	   				jQuery("#jnlpButton").attr("disabled", false);
	   				if(result == "Success"){
	   					window.location.href = 'admin/commonDashboard';
	   				}else{
	   					alert(result);
	   				}
	   			},
	   			error: function (req, err) {
	   				alert("Server Side Connection Error ==" + err);
	   			}
			}); 
		},
		error: function( jqXHR, textStatus, errorThrown ){
			button = jQuery('#b1');
			button.delay(15000).queue(function(){
				jQuery(this).click().dequeue();
			});
		}
	});
}