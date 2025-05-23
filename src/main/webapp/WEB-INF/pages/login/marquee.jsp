<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/amin_module/webmaster/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/amin_module/webmaster/jquery-2.2.3.min.js"></script>

<!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css"> -->
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	
	$(document).ready(function() {
		
		
		$('#valid_upto').datepicker({
			showOn: 'both', 
			buttonImageOnly: true,
			buttonImage: 'js/Calender/cal_ico.png',
			dateFormat: 'dd/mm/yy',
			changeMonth: true,
			changeYear: true,
			yearRange: '1890:2099'
		});
    	$('img.ui-datepicker-trigger').css({'cursor' : 'pointer', "vertical-align" : 'middle'}); 
    	
		//$("textarea#test_msg").val('${msg12}');
		//$("#valid_upto").val('${valid_upto1}');
		try {
			if (window.location.href.includes("msg1=")) {
				var url = window.location.href.split("?msg1")[0];
			}
		} catch(e){ }
	});
	
	function validateDate(a,c) {
		var b =/(^(((((0?[1-9])|(1\d)|(2[0-8]))\/((0?[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0?[1,3-9])|(1[0-2])))))\/(((18|19|20)[0-9][0-9]))|(29\/0?2\/(18|19|20)(([02468][048])|([13579][26]))))$)/i; ////  for dd/mm/yyyy
		//var b = /^(0[1-9]|1\d|2\d|3[01])\-(0[1-9]|1[0-2])\-(19|20)\d{2}$/; //  for dd-MM-yyyy
		if (a == "DD/MM/YYYY") {} 
		else {
			if (b.test(a) == false) {
				alert("Invalid Date");
				c.value = "";
				c.focus()
			}
		}
	}
	function clickclear(a, b) {
		if (a.value == b) {
			a.value = "";
			a.style.color = "#000000"
		}else{
			a.addEventListener('keydown', function(event) {
	            const key = event.key; 
	            if (key === "Backspace" || key === "Delete") { }
	            else{
	            	var fvalue = a.value;
	    			if(fvalue.length == 2){
	    				a.value = a.value+"/"; 
	    			}
	    			if(fvalue.length == 5){
	    				a.value = a.value+"/"; 
	    			}
	            }
	        });
		} 
	}
	function clickrecall(a, b) {
		if (a.value == b || a.value == "") {
			a.value = b
		} else {
			a.style.color = "#000000"
		}
	}
</script>
<form:form name="marcuries" id="marcuries" action="marcuriesAction" method='POST' modelAttribute="marcuriescmd">
<div class="container" align="center">
	<div class="card">
		<div class="card-header"> <h5>MANAGE MARQUEE</h5></div>
		<div class="card-body card-block">
			<div class="col-md-12">				  
				<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Insert Marquee </label>
		                </div>
		                <div class="col-12 col-md-6">		              
		                  	<textarea rows="2" cols="250" id="test_msg" id="test_msg" name="test_msg" style="font-family: 'FontAwesome',Arial; width:300px;" maxlength="200" class="form-control char-counter" autocomplete="off"></textarea>
							<input type="hidden"  id="msg_old_name" name="msg_old_name"  class="form-control " autocomplete="off" > 
						</div>
	             	</div>
	            </div> 	                         	 	
			</div>  
			<div class="col-md-12">	
             	<div class="col-md-6">
	             	<div class="row form-group"> 
	             		<div class="col col-md-6">
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> To Date </label>
		                </div>		                
	             		<div class="col-12 col-md-6">	             		
               				<!-- <input type="date" id="valid_upto" name="valid_upto" class="form-control">    -->   			
               				<input type="hidden" id="valid_upto_old" name="valid_upto_old" class="form-control" > 
               				<input type="hidden" id="id_hid" name="id_hid" class="form-control" > 
               				<input type="text" name="valid_upto" id="valid_upto" maxlength="10" value="DD/MM/YYYY" onclick="clickclear(this, 'DD/MM/YYYY')"  
               				 	class="form-control" style="width: 85%;display: inline;"
								onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
								onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" > 
               			</div>
	             	</div>
             	</div>              	           	            
			</div>
		</div>
		<div class="card-footer" align="center">
    	    <input type="reset" class="btn btn-success btn-sm" value="Clear">    	
        	<input type="submit"  id="save_btn" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
        	<input type="submit" id="update_btn" class="btn btn-primary btn-sm" value="Update" style="display: none">
    	</div>
    </div>
</div>
</form:form>
<div class="container" align="center">
	<div class="card">
		<div class="card-body"  id="divPrint">
		 	<div id="divShow" style="display: block;"></div>
			<div  class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getMsgSearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
					<thead style="background-color: #9c27b0; color: white;">
						<tr style="font-size: 15px;">
							<th width="10%">Ser No</th>
							<th width="65%">Insert Area </th>
							<th width="15%">To Date </th>
							<th width="10%" style="text-align: center;">Action</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${list}" varStatus="num" >
						<tr style="font-size: 12px;">
							<td width="10%">${num.index+1}</td>
							<td width="65%">${item.msg}</td>
							<td width="15%">${item.valid_upto}</td>
							<td width="10%" id="thAction1" align="center" >${item.id}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>	
		</div> 
	</div>
</div>

<script>
function isValid()
{
	if($("textarea#test_msg").val() == ""){
		alert("Please Enter Marquee Text");
		$("textarea#test_msg").focus();
		return false;
   	}
	if($("#valid_upto").val()==""){
		alert("Please select valid upto date");
		$("#valid_upto").focus();
		return false;
	} 
	return true;
}
var dateControler = { currentDate : null }
$(document).on( "change", "#valid_upto",function( event, ui ) {
	var now = new Date();
	var selectedDate = new Date($(this).val());
	if(selectedDate < now) {
		$(this).val(dateControler.currentDate)
	} else {
		dateControler.currentDate = $(this).val();
	}
}); 
</script>
<script>
	function editData(id,name,date){	
		var dd = date.substring(0,2);
		var d = dd.includes("-");
		if(d == true){
			date = '0' + date;
		}
		//var newdate = date.split("-").reverse().join("-");
		
		date = date.replace(/-/g, "/");
		
		document.getElementById('id_hid').value=id;
		document.getElementById('test_msg').value=name;
		document.getElementById('msg_old_name').value=name;
		document.getElementById('valid_upto_old').value=date;
		document.getElementById('valid_upto').value=date;
		document.getElementById('update_btn').style.display='inline-block'; 
		document.getElementById('save_btn').style.display='none';  
	}
</script>
<script>
(function ($) {
    "use strict";
    $.fn.charCounter = function (options) {
        if (typeof String.prototype.format == "undefined") {
            String.prototype.format = function () {
                var content = this;
                for (var i = 0; i < arguments.length; i++) {
                    var replacement = '{' + i + '}';
                    content = content.replace(replacement, arguments[i]);
                }
                return content;
            };
        }
        var options = $.extend({
            backgroundColor: "#FFFFFF",
            position: {
                right: 10,
                top: 10
            },
            font:   {
                size: 10,
                color: "#a59c8c"
            },
            limit: 200
        }, options);
        return this.each(function () {
            var el = $(this),
                wrapper = $("<div/>").addClass('focus-textarea').css({
                    "position": "relative",
                        "display": "inline-block"
                }),
                label = $("<span/>").css({
                    "zIndex": 999,
                        "backgroundColor": options.backgroundColor,
                        "position": "absolute",
                        "font-size": options.font.size,
                        "color": options.font.color
                }).css(options.position);
            if(options.limit > 0){
                label.text("{0}/{1}".format(el.val().length, options.limit));
                el.prop("maxlength", options.limit);
            }else{
                label.text(el.val().length);
            }
            el.wrap(wrapper);
            el.before(label);
            el.on("keyup", updateLabel)
                .on("keypress", updateLabel)
                .on('keydown', updateLabel);
            function updateLabel(e) {
                if(options.limit > 0){
                    label.text("{0}/{1}".format($(this).val().length, options.limit));
                }else{
                    label.text($(this).val().length);
                }
            }
        });
    }
})(jQuery);
$(".char-counter").charCounter();
</script>