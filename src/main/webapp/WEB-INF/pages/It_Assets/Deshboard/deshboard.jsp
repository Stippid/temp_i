<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="js/layout/css/style_db.css" rel="stylesheet">
<script src="js/assets/js/jquery-2.1.4.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/amchart4/index.js"></script>
<script src="js/amchart4/xy.js"></script>
<script src="js/amchart4/Animated5.js"></script>

<script src="js/amchart4/Chart.js"></script>
</head>

<body>
	<section class="main_section">
		<div class="container-fluid margin_dash">
	<jsp:include page="deshboard_menu.jsp" />
			<div class="chart_section">
			<div class="stacked_column_part">
			</div>
				<div class="stacked_column_part">
					<div class="row">
					
						<div class="col-md-2">
						<h6 class="align-heading"><b><u>Computing Holding/Servicable <br/>Un-Servicable</u></b></h6>
							<div class="select_menu">
								<!-- <h5>FMN</h5> -->
								<div class="col-md-2" ></div>
								<div class="col-md-2" >
								</div>
								<div class="col-md-8" >
								</div>
							</div>
							<div class="select_menu" style="margin-bottom: 0px;">
								<div class="col-md-12" >
								</div>
							</div>
						</div>
						<div class="col-md-9">
							<div id="chartcompdiv" class="chartsize"></div>
							<div class="chart_name">
							</div>
						</div>
					</div>
				</div>
	<div class="semicircle_pie_chart">
					<div class="row">
						<div class="col-md-9">
							<div id="chartperidiv" class="chartsize"></div>
							<div class="chart_name">
							</div>
						</div>
						<div class="col-md-2" >
						<h6 class="align-heading" ><b><u>Peripheral Holding/Servicable <br/> Un-Servicable</u></b></h6>
							<div class="select_menu">
								<div class="col-md-2" >
								</div>
								<div class="col-md-8" >
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
am5.ready(function() {

		
	var comproot = am5.Root.new("chartcompdiv");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	comproot.setThemes([
	  am5themes_Animated.new(comproot)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chartcom = comproot.container.children.push(am5xy.XYChart.new(comproot, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: comproot.verticalLayout
	}));
	console.log(chartcom);
	chartcom.set("scrollbarX", am5.Scrollbar.new(comproot, {
	  orientation: "horizontal"
	}));




	var data =${getcompData};

	// Create axes
	// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
	var xAxis = chartcom.xAxes.push(am5xy.CategoryAxis.new(comproot, {
	  categoryField: "data1",
	  renderer: am5xy.AxisRendererX.new(comproot, {}),
	  tooltip: am5.Tooltip.new(comproot, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chartcom.yAxes.push(am5xy.ValueAxis.new(comproot, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(comproot, {})
	}));


	// Add legend
	// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
	var legend = chartcom.children.push(am5.Legend.new(comproot, {
	  centerX: am5.p50,
	  x: am5.p50
	}));


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	function makeSeriescom(name, fieldName, total) {
	  var series = chartcom.series.push(am5xy.ColumnSeries.new(comproot, {
	    name: name,
	    stacked: true,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "data1"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}: {valueY}",
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  // https://www.amcharts.com/docs/v5/concepts/animations/
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(comproot, {
	      sprite: am5.Label.new(comproot, {
	        text: "{valueY}",
	        fill: comproot.interfaceColors.get("alternativeText"),
	        centerY: am5.p50,
	        centerX: am5.p50,
	        populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	  
	  
	  if (total) {
		    series.bullets.push(function () {
		      var totalLabel = am5.Label.new(comproot, {
		        text: "{valueY}",
		        fill: comproot.interfaceColors.get("text"),
		        centerY: am5.p100,
		        centerX: am5.p50,
		        populateText: true,
		        textAlign: "center"
		      });
		      
		      totalLabel.adapters.add("text", function(text, target) {
		        var dataContext = target.dataItem.dataContext;
		        var val = Math.abs(dataContext.data4 + dataContext.data3);
		        return "ON HOLDING: "+val;
		      });
		      
		      return am5.Bullet.new(comproot, {
		        locationX: 0.5,
		        locationY: 1,
		        sprite: totalLabel
		      });
		    });
		  }
	}

	makeSeriescom("SERVICABLE", "data3",false);
	makeSeriescom("UN-SERVICABLE", "data4",true);


	
	////////////////////////////
	var root = am5.Root.new("chartperidiv");


	// Set themes
	// https://www.amcharts.com/docs/v5/concepts/themes/
	root.setThemes([
	  am5themes_Animated.new(root)
	]);


	// Create chart
	// https://www.amcharts.com/docs/v5/charts/xy-chart/
	var chart = root.container.children.push(am5xy.XYChart.new(root, {
	  panX: false,
	  panY: false,
	  wheelX: "panX",
	  wheelY: "zoomX",
	  layout: root.verticalLayout
	}));
	console.log(chart);
	chart.set("scrollbarX", am5.Scrollbar.new(root, {
	  orientation: "horizontal"
	}));



	var data =${getperiData};

	var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
	  categoryField: "data1",
	  renderer: am5xy.AxisRendererX.new(root, {}),
	  tooltip: am5.Tooltip.new(root, {})
	}));

	xAxis.data.setAll(data);

	var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
	  min: 0,
	  renderer: am5xy.AxisRendererY.new(root, {})
	}));


	// Add legend
	// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
	var legend = chart.children.push(am5.Legend.new(root, {
	  centerX: am5.p50,
	  x: am5.p50
	}));


	// Add series
	// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
	function makeSeries(name, fieldName, total1) {
	  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
	    name: name,
	    stacked: true,
	    xAxis: xAxis,
	    yAxis: yAxis,
	    valueYField: fieldName,
	    categoryXField: "data1"
	  }));

	  series.columns.template.setAll({
	    tooltipText: "{name}, {categoryX}: {valueY}",
	    tooltipY: am5.percent(10)
	  });
	  series.data.setAll(data);

	  // Make stuff animate on load
	  // https://www.amcharts.com/docs/v5/concepts/animations/
	  series.appear();

	  series.bullets.push(function () {
	    return am5.Bullet.new(root, {
	      sprite: am5.Label.new(root, {
	        text: "{valueY}",
	        fill: root.interfaceColors.get("alternativeText"),
	        centerY: am5.p50,
	        centerX: am5.p50,
	        populateText: true
	      })
	    });
	  });

	  legend.data.push(series);
	  
	  
	  if (total1) {
		    series.bullets.push(function () {
		      var total1Label = am5.Label.new(root, {
		        text: "{valueY}",
		        fill: root.interfaceColors.get("text"),
		        centerY: am5.p100,
		        centerX: am5.p50,
		        populateText: true,
		        textAlign: "center"
		      });
		      
		      total1Label.adapters.add("text", function(text, target) {
		        var dataContext = target.dataItem.dataContext;
		        var val = Math.abs(dataContext.data4 + dataContext.data3);
		        return "ON HOLDING: "+val;
		      });
		      
		      return am5.Bullet.new(root, {
		        locationX: 0.5,
		        locationY: 1,
		        sprite: total1Label
		      });
		    });
		  }
	}

	makeSeries("SERVICABLE", "data3",false);
	makeSeries("UN-SERVICABLE", "data4",true);


	
	
}); // end am5.ready()




</script>

<!-- <div id="chartdiv"></div> -->