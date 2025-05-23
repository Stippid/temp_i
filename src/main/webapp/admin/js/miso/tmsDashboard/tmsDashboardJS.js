$(document).ready(function() {
	
///////  Common Page Js/////////	
	
	$(".btn_dashboard").on('click', function() {
		$("#DASHBOARD_tabs").show();
		$("#CHART_tabs").hide();
	});

	$(".btn_charts").on('click', function() {
		$("#CHART_tabs, #DASHBOARD_tabs").toggle();
	});
	
	
	function getSelectedValue(id) {
	  return $("#" + id).find("dt a span.value").html();
	}
	
	$(document).bind('click', function(e) {
	  var $clicked = $(e.target);
	  if (!$clicked.parents().hasClass("dropdown")) $(".dropdown dd ul").hide();
	});
	
	$('.mutliSelect input[type="checkbox"]').on('click', function() {
	  	var title = $(this).closest('.mutliSelect').find('input[type="checkbox"]').val(),
	    title = $(this).val() + ",";
	
	  	if ($(this).is(':checked')) {
	    	var html = '<span title="' + title + '">' + title + '</span>';
	    	$('.multiSel').append(html);
	    	$(".hida").hide();
	  	} else {
	    	$('span[title="' + title + '"]').remove();
	    	var ret = $(".hida");
	    	$('.dropdown dt a').append(ret);
	
		}
	});
	

/////// CHART JS /////////
});
///// Chart Function ////////////////

var expanded = false;
function showCheckboxes() {
  var checkboxes = document.getElementById("checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}
function showCheckboxes1() {
  var checkboxes = document.getElementById("checkboxes1");
  if (!expanded) {
    checkboxes1.style.display = "block";
    expanded = true;
  } else {
    checkboxes1.style.display = "none";
    expanded = false;
  }
}
function showCheckboxes2() {
  var checkboxes = document.getElementById("checkboxes2");
  if (!expanded) {
    checkboxes2.style.display = "block";
    expanded = true;
  } else {
    checkboxes2.style.display = "none";
    expanded = false;
  }
}
function showCheckboxes3() {
  var checkboxes = document.getElementById("checkboxes3");
  if (!expanded) {
    checkboxes3.style.display = "block";
    expanded = true;
  } else {
    checkboxes3.style.display = "none";
    expanded = false;
  }
}
function showCheckboxes4() {
  var checkboxes = document.getElementById("checkboxes4");
  if (!expanded) {
    checkboxes4.style.display = "block";
    expanded = true;
  } else {
    checkboxes4.style.display = "none";
    expanded = false;
  }
}