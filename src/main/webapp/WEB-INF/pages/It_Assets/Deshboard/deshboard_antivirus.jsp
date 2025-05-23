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

	<section class="main_section">
		<div class="container-fluid">
	<jsp:include page="deshboard_menu.jsp" />
			<div class="chart_section">
			<div class="stacked_column_part">
			</div>
				
				
				
								<div class="stacked_column_part">
					<div class="row">
						<div class="col-md-3">
<h6><b><u>Antivirus Document Graph</u></b></h6>
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
						<div id="chartdiv" class="chartsize " align="center"><canvas id="myChart" style="width:90%;max-width:572px"></canvas></div>

						</div>
					</div>
				</div>
			
			</div>
		</div>
	</section>

<!-- Chart code -->
<script>
am5.ready(function() {



	// Create root element
	// https://www.amcharts.com/docs/v5/getting-started/#Root_element
	var root = am5.Root.new("chartdiv");


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
	// Add scrollbar
	// https://www.amcharts.com/docs/v5/charts/xy-chart/scrollbars/



	// Make stuff animate on load
	// https://www.amcharts.com/docs/v5/concepts/animations/
	chart.appear(1000, 100);

	var data =${getcomputingData};
	var xValues = ["ANTIVIRUS PURCHASED", "ANTIVIRUS NOT PURCHASED"];
	var yValues = data;
	var barColors = [
	  "#fd7e14",
	  "#868e96"
	  
	 
	];

	new Chart("myChart", {
	  type: "doughnut",
	  data: {
	    labels: xValues,
	    datasets: [{
	      backgroundColor: barColors,
	      data: yValues
	    }]
	  },
	  options: {
	    title: {
	      display: true,
	      text: "Computing Document Graph"
	    }
	  }
	});
	
	
}); // end am5.ready()
</script>

<!-- <div id="chartdiv"></div> -->