jsondata = [];
var FilteredRecords = 0;
function dataTable(tableName){
	var table = $('#'+tableName).DataTable({
		"order": [[ 0, "asc","desc" ]],
		"lengthMenu": [[10, 25, 50,100, -1 ], [10, 25, 50,100, "All"]],
		"scrollY":        "400px",
	    "scrollCollapse": true,
	    "sPaginationType": "full",
	    "bLengthChange" : true,        
	    'language': {
            'loadingRecords': '&nbsp;',
            'processing': '<div class="spinner"></div>'
        },
		ajax: '/Data',
		'processing': true,
		"serverSide": true
	});
	return table;
}

var FilteredRecords = 0;
function dataTable11(tableName){
	var table = $('#'+tableName).DataTable({
		"order": [[ 0, "asc","desc" ]],
		"lengthMenu": [[-1 ], [ "All"]],
		"scrollY":        "400px",
	    "scrollCollapse": true,
	    "sPaginationType": "full",
	    "bLengthChange" : true,        
	    'language': {
            'loadingRecords': '&nbsp;',
            'processing': '<div class="spinner"></div>'
        },
		ajax: '/Data'+tableName,
		'processing': true,
		"serverSide": true,
		'dom': 'Rlfrtip',
    	'colReorder': {
        	'allowReorder': false
    	}
	});
	return table;
}
function mockjax1(tableName){
	$.mockjax({
	    url: '/Data',
	    responseTime: 1000,
	    response: function(settings){
	    	$.ajaxSetup({
				async : false
			});
			data(tableName);
			this.responseText = {
	    		draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
	            recordsFiltered: FilteredRecords
			};
	     }
	});
}
function mockjax11(tableName){
	$.mockjax({
	    url: '/Data'+tableName,
	    responseTime: 1000,
	    response: function(settings){
	    	$.ajaxSetup({
				async : false
			});
			data(tableName);
			this.responseText = {
	    		draw: settings.data.draw,
				data: jsondata,
				recordsTotal: jsondata.length,
	            recordsFiltered: FilteredRecords
			};
	     }
	});
}
