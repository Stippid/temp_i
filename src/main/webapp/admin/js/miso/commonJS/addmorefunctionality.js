
/*=========================================================== ADD MORE FUNCTIONALITY FOR SAVE/UPDATE WHOLE TABLE AT THE END ============================================================*/
var gc_add = []; //global count of add
var i="";
gc_add[i]=1;
function formopen_add(ser,tbl_name,tbl_no,tbody){
	
		i = tbl_no;
		var prev_ser = parseInt(ser) - 1 ;
		var s_t=tbl_name+prev_ser; // table name and serial 
		
		 $("#id_add_"+s_t).hide();
		 $("#id_remove_"+s_t).hide();
		 gc_add[tbl_no]=0;
		 gc_add[tbl_no]= parseInt(ser);
		 
		 if(gc_add[tbl_no] < 51){

			 $("input#count_hidden_"+tbl_name).val(ser);//current serial No
			 
			 $("table#table_"+tbl_name).append('<tr align="center" id="tr_id_'+tbl_name+gc_add[tbl_no]+'"><td>'+gc_add[tbl_no]+'</td>'
					 
 					+tbody+
		 			+'<td id="h_rp_td'+gc_add[tbl_no]+'" align="center" style="display:none;"><input type="hidden" name="h_rp_did'+gc_add[tbl_no]+'" id="h_rp_did'+gc_add[tbl_no]+'" value="0" class="form-control" autocomplete="off" /></td>'
		 			+'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_'+tbl_name+gc_add[tbl_no]+'" onclick="Getinput_tbody_'+tbl_name+'('+ gc_add[tbl_no]+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove_'+tbl_name+gc_add[tbl_no]+'" onclick="formopen_remove('+gc_add[tbl_no]+',\''+tbl_name+'\',\''+tbl_no+'\');"><i class="fa fa-trash"></a></td>' 
		   		    +'</tr>');
		 
			}else{
					alert("Please Enter max 50 Quantity");
					 if ( gc_add[tbl_no] == 51){
						 gc_add[tbl_no] = gc_add[tbl_no]-1; 
						 $("#id_remove_"+tbl_name+gc_add[tbl_no]).show();

					 }	   
			}
	
	}

function formopen_remove(ser,tbl_name,tbl_no){
	 $("tr#tr_id_"+tbl_name+ser).remove();
	 gc_add[tbl_no] = gc_add[tbl_no]-1;
	 $("input#count_hidden_"+tbl_name).val(gc_add[tbl_no]);
	 $("#id_add_"+tbl_name+gc_add[tbl_no]).show();
	 $("#id_remove_"+tbl_name+gc_add[tbl_no]).show();	
}

function formopen_edit(ser,tbl_name,tbl_no,tbody){	
		 
		 	var current_hd = $("input#current_count_hidden_"+tbl_name).val();
			if(parseInt(current_hd) == 0)
			{
				$("input#current_count_hidden_"+tbl_name).val(1);
				//current_hd = $("input#current_count_hidden_"+tbl_name).val();
			}	
			var prev_ser = parseInt(ser) - 1 ;
			var s_t=tbl_name+prev_ser; // table name and serial 
			
			 $("#id_add_"+s_t).hide();
			 $("#id_remove_"+s_t).hide(); 
			 
			current_hd = parseInt(current_hd) + 1;
			$("input#current_count_hidden_"+tbl_name).val(current_hd); //hd count-current count
			var newlastqunt = 0;
		
			
			
			var lastqty = $("#countold_"+tbl_name).val(); // list of size count previous data count
			additional_qty = parseInt(current_hd) - parseInt(lastqty);
			if(current_hd < 51)
			{
				var rp = parseInt(current_hd);					
				document.getElementById("Insertcount_"+tbl_name).value = additional_qty; // hidden variable insertcount
				$("input#current_count_hidden_"+tbl_name).val(rp);		
				 $("table#table_"+tbl_name).append('<tr align="center" id="tr_id_'+tbl_name+rp+'"><td>'+rp+'</td>'
					 
					+tbody+
		 			+'<td id="h_rp_td'+rp+'" align="center" style="display:none;"><input type="hidden" name="h_rp_did'+rp+'" id="h_rp_did'+rp+'" value="0" class="form-control" autocomplete="off" /></td>'
		 			+'<td><a class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_'+tbl_name+rp+'" onclick="Getinput_tbody_'+tbl_name+'('+rp+');" ><i class="fa fa-plus"></i></a> <a class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove_'+tbl_name+rp+'" onclick="formopen_remove_edit('+rp+',\''+tbl_name+'\',\''+tbl_no+'\');"><i class="fa fa-trash"></a></td>' 
		   		    +'</tr>');
		 
			}else{
				alert("Please Enter max 50 Quantity");
				$("#current_count_hidden_"+tbl_name).val(lastqty);
				
				if (current_hd == 51 ){
					rp = current_hd-1;
					 $("id_remove_"+tbl_name+rp).show();
					 $("#current_count_hidden_"+tbl_name).val(rp);
				  }	   
			}
			
		 
		  
	}

function formopen_remove_edit(ser,tbl_name,tbl_no){
	$("tr#tr_id_"+tbl_name+ser).remove();
 	var rp = $("input#current_count_hidden_"+tbl_name).val();	 	
 	rp= rp-1;
	$("input#current_count_hidden_"+tbl_name).val(rp);
	var i = $("input#Insertcount_"+tbl_name).val();
	i=i-1;
	$("input#Insertcount_"+tbl_name).val(i);
 	$("#id_add_"+tbl_name+rp).show();
 	$("#id_remove_"+tbl_name+rp).show();
		
}




/*=========================================================== ADD MORE FUNCTIONALITY FOR INDIVIDUAL ROW SAVE/UPDATE ============================================================*/


var gc_save = []; //global count of save
var j="";
gc_save[j]=1;

function formopen_save(ser,tbl_name,tbl_no,tbody){

		j = tbl_no;
		let prev_ser = parseInt(ser) - 1 ;
		let s_t=tbl_name+prev_ser; // table name and serial 
		
		 $("#id_add_"+s_t).hide();
	
	 gc_save[tbl_no]=0;
	 gc_save[tbl_no]= parseInt(ser);
	
	 if(gc_save[tbl_no] < 51){
		 $("table#table_"+tbl_name).append('<tr align="center" id="tr_id_'+tbl_name+gc_save[tbl_no]+'"><td>'+gc_save[tbl_no]+'</td>'
				+tbody+'<td style="display:none;"><input type="text" id="id_'+tbl_name+gc_save[tbl_no]+'" name="id_'+tbl_name+gc_save[tbl_no]+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
				+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "tools_save'+gc_save[tbl_no]+'" onclick="save_fn_'+tbl_name+'('+gc_save[tbl_no]+'); " ><i class="fa fa-save"></i></a>'
			 	+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "id_add_'+tbl_name+gc_save[tbl_no]+'" onclick="Getinput_tbody_'+tbl_name+'('+gc_save[tbl_no]+',\''+tbl_name+'\',\''+tbl_no+'\');" ><i class="fa fa-plus"></i></a>'
			 	+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "id_remove_'+tbl_name+gc_save[tbl_no]+'" onclick="delete_data_'+tbl_name+'('+gc_save[tbl_no]+');formopen_remove_save('+gc_save[tbl_no]+',\''+tbl_name+'\',\''+tbl_no+'\');"><i class="fa fa-trash"></i></a> </td>'
	   		    
	   		    +'</tr>');
		
		}
	 else
	{
			alert("Please Enter max 50 Quantity");
			if (gc_save[tbl_no] == 51) {
				gc_save[tbl_no] = gc_save[tbl_no] - 1;
				$("#id_remove_"+tbl_name+gc_save[tbl_no]).show();
			}
		}
	}

	function formopen_remove_save(ser,tbl_name,tbl_no) {

		$("tr#tr_id_"+tbl_name + ser).remove();
		gc_save[tbl_no] = gc_save[tbl_no] - 1;
		$("#id_add_"+tbl_name + gc_save[tbl_no]).show();
		$("#id_remove_"+tbl_name + gc_save[tbl_no]).show();
		//var id = $("#id_tools2").val();
		//delete_data_+tbl_name+(id);
		//window["delete_data_" + tbl_name](ser);
		
		if(ser==1){
			window["Getinput_tbody_" + tbl_name](0);
		}
		
	}
