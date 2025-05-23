var timeOut = 0;
function getCheckPIKValidation()
{
	//sendSuccessFormationDashboard("Fr/IgD1RmAnFwl7X5WBpLg==");
	/*if(roleAccess=='Formation'){
		window.location.href = 'JnlpFileDownload';
		console.log("JNLP Downloded");
		button = jQuery('#b1');
		button.delay(30000).queue(function(){
			jQuery(this).click().dequeue();
		});
		jQuery("#TokenLoader").show();
	}*/
	/*if(roleAccess=='Formation'){*/
		window.location.href = 'JnlpFileDownload';
		console.log("JNLP Downloded");
		setInterval(function(){
	    	document.getElementById("b1").click();
	    }, 15000);
		jQuery("#TokenLoader").show();
		jQuery(".loader").fadeOut("slow");
	/*}*/
}
function run(){
	//alert("Run Jar");
	jQuery.ajax({
		type: 'GET',
		url: "http://localhost:8089",
		success: function( data ,textstatus ,jqXHR) {
			//alert("Client Side Response ==" + data);
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
	   				//alert("Server Side Response ==" + result);
	   				//sendSuccessFormationDashboard("Fr/IgD1RmAnFwl7X5WBpLg==");
	   				jQuery("#TokenLoader").hide();
	   				if(result == "Success"){
	   					document.getElementById("ValidationForm").submit();
	   				}else{
	   					alert(result);
	   				}
	   			},
	   			error: function (req, err) {
	   				alert("Server Side Connection Error ==" + err);
	   			}
			}); 
		},
	});
}