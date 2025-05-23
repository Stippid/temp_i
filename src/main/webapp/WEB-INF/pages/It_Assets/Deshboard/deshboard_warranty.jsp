<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<link href="js/layout/css/style_db.css" rel="stylesheet">
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/amchart4/index.js"></script>
<script src="js/amchart4/xy.js"></script>
<script src="js/amchart4/Animated5.js"></script>

<script src="js/amchart4/Chart.js"></script>
<script src="js/amchart4/canvasjs.min.js"></script>

</head>
<style>
#antidiv {
 
  height: 340px;
  /* width:10px !important; */
}

</style>
<body>
	<section class="main_section">
		<div class="container-fluid">
	<jsp:include page="deshboard_menu.jsp" />
			<div class="chart_section">
		
			<div class="stacked_column_part">
					<div class="row">
						<div class="col-md-3">
							<h6><b><u>Select Parameters For Computing Asset</u></b></h6>
							<div class="select_menu">
								<!-- <h5>FMN</h5> -->
								<div class="col-md-2" ></div>
								<div class="col-md-2" >
									<label>Select Year</label>
								</div>
								<div class="col-md-8">	               						
		               							<select id="ddlYears" class="form-control"  onchange="selectYear();"></select>
		               							
	               					    </div>
							</div>
							<div class="select_menu" style="margin-bottom: 0px;">
								<div class="col-md-12" >
<!-- 									<span style="float:right;"><img src="js/img/PdfIcon.svg" onclick="getPDF1();" title="Export PDF"></span> -->
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div id="chartdiv" class="chartsize"></div>
							<div class="chart_name">
<%-- 								<h4>Eqpt Health State of <span id="change_formation">${getCurrentFormationName[1]} </span> </h4> --%>
							</div>
						</div>
					</div>
				</div>
 <div class="semicircle_pie_chart">
					<div class="row">
						<div class="col-md-9">
						<div id="chartdiv_p" class="chartsize"></div>
							<div class="chart_name">
							</div>
						</div>
						<div class="col-md-3" >
							<h6><b><u>Select Parameters For Peripheral Asset</u></b></h6>
							<div class="select_menu">
								<div class="col-md-2" >
									<label>Select Year</label>
								</div>
								<div class="col-md-8">	               						
		               					<select id="ddlYearsp" class="form-control"  onchange="selectYearPeripheral();"></select>
		               				 	
	               					    </div>
								<div class="col-md-2" ></div>
							</div>
							<div class="select_menu">
								<div class="col-md-2" >
								</div>
								<div class="col-md-8" >

								</div>
								<div class="col-md-2" ></div>
							</div>
							<div class="row">
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4"></div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4"></div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4">
								</div>
								<div class="col-md-4"></div>
							</div>
							<div class="select_menu" style="margin-bottom: 0px;">
								<div class="col-md-12" >
								</div>
							</div>
						</div>
					</div>
				</div> 
			</div>
		</div>
	</section>


<script>
var root;
am5.ready(function() {
	 root = am5.Root.new("chartdiv");
	
	


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	  
	]);


	// Create chart
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: root.verticalLayout
	}));

	chart.set("scrollbarX", am5.Scrollbar.new(root, {
		  orientation: "horizontal"
		}));
	// Add legend
	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}));



var data =${getComputingWarrentyDueData};
	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
	  categoryField: "data1",
	  renderer: am5xy.AxisRendererX.new(root, {minGridDistance: 50,
	    cellStartLocation: 0.1,
	    cellEndLocation: 0.9
	  }),
	  tooltip: am5.Tooltip.new(root, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add series
	function makeSeries(name, fieldName, stacked) {
	  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	    stacked: stacked,
	    name: name,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "data1"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}:{valueY}",
	    width: am5.percent(90),
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(root, {
	      locationY: 0.5,
	      sprite: am5.Label.new(root, {
	        text: "{valueY}",
	        fill: root.interfaceColors.get("alternativeText"),
	        centerY: am5.percent(50),
	        centerX: am5.percent(50),
	        populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	}
	
	makeSeries("Computing", "data2", false);
// 	makeSeries("Peripheral", "data2", false);
	
// 	makeSeries("Peripheral", "asia", false);
	

	// Make stuff animate on load
	chart.appear(1000, 100);
	
	
	
	
	

}); // end am5.ready()





</script>



<script>
var rootp;
am5.ready(function() {
	rootp = am5.Root.new("chartdiv_p");
	
	


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	rootp.setThemes([
	  am5themes_Animated.new(rootp)
	  
	]);


	// Create chart
	var chart = rootp.container.children.push(am5xy.XYChart.new(rootp, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: rootp.verticalLayout
	}));

	chart.set("scrollbarX", am5.Scrollbar.new(rootp, {
		  orientation: "horizontal"
		}));
	// Add legend
	var legend = chart.children.push(am5.Legend.new(rootp, {
	  centerX: am5.p50,
	  x: am5.p50
	}));



var data =${getPeripheralWarrentyDueData};
	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(rootp, {
	  categoryField: "data1",
	  renderer: am5xy.AxisRendererX.new(rootp, {minGridDistance: 50,
	    cellStartLocation: 0.1,
	    cellEndLocation: 0.9
	  }),
	  tooltip: am5.Tooltip.new(rootp, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(rootp, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(rootp, {})
	}));


	// Add series
	function makeSeriesp(name, fieldName, stacked) {
	  var series = chart.series.push(am5xy.ColumnSeries.new(rootp, {
	    stacked: stacked,
	    name: name,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "data1"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}:{valueY}",
	    width: am5.percent(90),
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(rootp, {
	      locationY: 0.5,
	      sprite: am5.Label.new(rootp, {
	        text: "{valueY}",
	        fill: rootp.interfaceColors.get("alternativeText"),
	        centerY: am5.percent(50),
	        centerX: am5.percent(50),
	        populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	}
	
	makeSeriesp("Peripheral", "data2", false);
// 	makeSeries("Peripheral", "data2", false);
	
// 	makeSeries("Peripheral", "asia", false);
	

	// Make stuff animate on load
	chart.appear(1000, 100);
	
	
	
	
	

}); // end am5.ready()





</script>

<script type="text/javascript">
function selectYear(){
	
	
	
	var year = $("#ddlYears").val();
 
	$.post("getYearComputing?" + key + "=" + value, {
		year: year
	}).done(function(j) {
	
					
 		 if(j.length > 0){
 			 
 		
 			root.dispose();
 			root = am5.Root.new("chartdiv");
 		
 			root.setThemes([
 			  am5themes_Animated.new(root)
 			  
 			]);


 			// Create chart
 			var chart = root.container.children.push(am5xy.XYChart.new(root, {
 			  panX: false,
 			  panY: false,
 			  wheelX: "panX",
 			  wheelY: "zoomX",
 			  layout: root.verticalLayout
 			}));
 			chart.set("scrollbarX", am5.Scrollbar.new(root, {
 				  orientation: "horizontal"
 				}));
 			var data = j;
 			// Add legend
 			var legend = chart.children.push(am5.Legend.new(root, {
 			  centerX: am5.p50,
 			  x: am5.p50
 			
 			}));
 			data = JSON.parse(JSON.stringify(eval('(' + data + ')')));
console.log(data)
 		
 		
 			// Create axes
 			var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
 			  categoryField: "data1",
 			  renderer: am5xy.AxisRendererX.new(root, {minGridDistance: 50,
 			    cellStartLocation: 0.1,
 			    cellEndLocation: 0.9
 			  }),
 			  tooltip: am5.Tooltip.new(root, {})
 			}));

 			xAxis.data.setAll(data);

 			var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
 			  min: 0,
 			  renderer: am5xy.AxisRendererY.new(root, {})
 			}));


 			// Add series
 			function makeSeries(name, fieldName, stacked) {
 			  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
 			    stacked: stacked,
 			    name: name,
 			    xAxis: xAxis,
 			    yAxis: yAxis,
 			    valueYField: fieldName,
 			    categoryXField: "data1"
 			  }));

 			  series.columns.template.setAll({
 			    tooltipText: "{name}, {categoryX}:{valueY}",
 			    width: am5.percent(90),
 			    tooltipY: am5.percent(10)
 			  });
 			  series.data.setAll(data);

 			  // Make stuff animate on load
 			  series.appear();

 			  series.bullets.push(function () {
 			    return am5.Bullet.new(root, {
 			      locationY: 0.5,
 			      sprite: am5.Label.new(root, {
 			        text: "{valueY}",
 			        fill: root.interfaceColors.get("alternativeText"),
 			        centerY: am5.percent(50),
 			        centerX: am5.percent(50),
 			        populateText: true
 			      })
 			    });
 			  });

 			  legend.data.push(series);
 			}
 			
 			makeSeries("Computing", "data2", false);
 			

 			

 			// Make stuff animate on load
 			chart.appear(1000, 100); 
 			
 		 } 		 
	}).fail(function(xhr, textStatus, errorThrown)  
			
			{
		
		alert("fail to fetch")
	});
	
}


</script>


<script type="text/javascript">
function selectYearPeripheral(){

	
	var year = $("#ddlYearsp").val();
	$.post("getYearPeripheral?" + key + "=" + value, {
		year: year
	}).done(function(j) {
	
					
 		 if(j.length > 0){
 			 
 		
 			rootp.dispose();
 			rootp = am5.Root.new("chartdiv_p");
 		
 			rootp.setThemes([
 			  am5themes_Animated.new(rootp)
 			  
 			]);


 			// Create chart
 			var chart = rootp.container.children.push(am5xy.XYChart.new(rootp, {
 			  panX: false,
 			  panY: false,
 			  wheelX: "panX",
 			  wheelY: "zoomX",
 			  layout: rootp.verticalLayout
 			}));
 			chart.set("scrollbarX", am5.Scrollbar.new(rootp, {
 				  orientation: "horizontal"
 				}));
 			var data = j;
 			// Add legend
 			var legend = chart.children.push(am5.Legend.new(rootp, {
 			  centerX: am5.p50,
 			  x: am5.p50
 			
 			}));
 			data = JSON.parse(JSON.stringify(eval('(' + data + ')')));
console.log(data)
 		
 		
 			// Create axes
 			var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(rootp, {
 			  categoryField: "data1",
 			  renderer: am5xy.AxisRendererX.new(rootp, {minGridDistance: 50,
 			    cellStartLocation: 0.1,
 			    cellEndLocation: 0.9
 			  }),
 			  tooltip: am5.Tooltip.new(rootp, {})
 			}));

 			xAxis.data.setAll(data);

 			var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(rootp, {
 			  min: 0,
 			  renderer: am5xy.AxisRendererY.new(rootp, {})
 			}));


 			// Add series
 			function makeSeriesp(name, fieldName, stacked) {
 			  var series = chart.series.push(am5xy.ColumnSeries.new(rootp, {
 			    stacked: stacked,
 			    name: name,
 			    xAxis: xAxis,
 			    yAxis: yAxis,
 			    valueYField: fieldName,
 			    categoryXField: "data1"
 			  }));

 			  series.columns.template.setAll({
 			    tooltipText: "{name}, {categoryX}:{valueY}",
 			    width: am5.percent(90),
 			    tooltipY: am5.percent(10)
 			  });
 			  series.data.setAll(data);

 			  // Make stuff animate on load
 			  series.appear();

 			  series.bullets.push(function () {
 			    return am5.Bullet.new(rootp, {
 			      locationY: 0.5,
 			      sprite: am5.Label.new(rootp, {
 			        text: "{valueY}",
 			        fill: rootp.interfaceColors.get("alternativeText"),
 			        centerY: am5.percent(50),
 			        centerX: am5.percent(50),
 			        populateText: true
 			      })
 			    });
 			  });

 			  legend.data.push(series);
 			}
 			
 			makeSeriesp("Peripheral", "data2", false);
 			

 			

 			// Make stuff animate on load
 			chart.appear(1000, 100); 
 			
 		 } 		 
	}).fail(function(xhr, textStatus, errorThrown)  
			
			{
		
		alert("fail to fetch")
	});
	
}

</script>


<script type="text/javascript">
    window.onload = function () {
   
        //Reference the DropDownList.
        var ddlYears = document.getElementById("ddlYears");
        var ddlYearsp = document.getElementById("ddlYearsp");

   
     
 
        //Determine the Current Year.
        var currentYear = (new Date()).getFullYear();
 
        //Loop and add the Year values to DropDownList.
        for (var i = 1950; i <= currentYear; i++) {
            var option = document.createElement("OPTION");
            option.innerHTML = i;
            option.value = i;
            ddlYears.appendChild(option);
          
        }
        for (var i = 1950; i <= currentYear; i++) {
            var option = document.createElement("OPTION");
            option.innerHTML = i;
            option.value = i;
            ddlYearsp.appendChild(option);
          
        }
      $("#ddlYears").val("${currentyearList}");
      $("#ddlYearsp").val("${currentyearList}");
    };
    

</script>


