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
						<h6><b><u>ADN Network Document Graph</u></b></h6>
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
							<div id="chartdiv" class="chartsize"></div>
							<div class="chart_name">
							</div>
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

chart.set("scrollbarX", am5.Scrollbar.new(root, {
	  orientation: "horizontal"
	}));
// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);


var data =${getComputingADNData};
var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "data1",
  renderer: am5xy.AxisRendererX.new(root, {
    cellStartLocation: 0.1,
    cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(root, {})
}));

xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  renderer: am5xy.AxisRendererY.new(root, {})
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeries(name, fieldName) {
  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
    name: name,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "data1"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}:{valueY}",
    width: am5.percent(90),
    tooltipY: 0
  });

  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: 0,
        centerX: am5.p50,
        populateText: true
      })
    });
  });

  legend.data.push(series);
}

makeSeries("ADN", "data2");
makeSeries("NON-ADN", "data3");


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()
</script>

<!-- HTML -->
<!-- <div id="chartdiv"></div>  -->

<!-- <div id="chartdiv"></div> -->