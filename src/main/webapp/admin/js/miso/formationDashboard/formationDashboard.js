function downloadReport(filename){
	document.getElementById("filename").value=filename;
	document.getElementById("getDownloadDocFMNDashboardForm").submit();
}

function AllLifeStyleCount(list,chartName,divname,avg_color,cur,avg,total){
	am4core.useTheme(am4themes_animated);
	var chartMin = 0;
	var chartMax = total;

	var data = {
	  score: cur,
	  gradingData: [
	    {
	      title: "10 Year Avg",
	      color: avg_color,//"#6495ED",
	      lowScore: 0,
	      highScore: avg
	    },
	    {
	      title: "Max of 10 year",
	      color: "#bfc9ca",
	      lowScore: avg,
	      highScore: total
	    }
	  ]
	};
	  
	function lookUpGrade(lookupScore, grades) {
	  // Only change code below this line
	  for (var i = 0; i < grades.length; i++) {
	    if (
	      grades[i].lowScore < lookupScore &&
	      grades[i].highScore >= lookupScore
	    ) {
	      return grades[i];
	    }
	  }
	  return null;
	}

	// create chart
	var chart = am4core.create(divname, am4charts.GaugeChart);
	chart.hiddenState.properties.opacity = 0;
	chart.fontSize = 11;
	chart.innerRadius = am4core.percent(60);
	chart.resizable = true;
	chart.tooltipText = "Current Year -"+cur+" \n 10 Year Avg - "+avg+" \n 10 Year Max -"+total +" \n View More Details";
	
	/*chart.events.on("hit", function(ev) {
		alert("hiii");
	});*/
	
	var axis = chart.xAxes.push(new am4charts.ValueAxis());
	axis.min = chartMin;
	axis.max = chartMax;
	axis.strictMinMax = true;
	axis.renderer.radius = am4core.percent(120);
	axis.renderer.inside = true;
	axis.renderer.line.strokeOpacity = 0.1;
	axis.renderer.ticks.template.disabled = false;
	axis.renderer.ticks.template.strokeOpacity = 1;
	axis.renderer.ticks.template.strokeWidth = 1.5;
	axis.renderer.ticks.template.length = 4;
	axis.renderer.grid.template.disabled = true;
	axis.renderer.labels.template.radius = am4core.percent(15);
	axis.renderer.labels.template.fontSize = "0.9em";

	/**
	 * Axis for ranges
	 */

	var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
	axis2.min = chartMin;
	axis2.max = chartMax;
	axis2.strictMinMax = true;
	axis2.renderer.labels.template.disabled = true;
	axis2.renderer.ticks.template.disabled = true;
	axis2.renderer.grid.template.disabled = false;
	axis2.renderer.grid.template.opacity = 0.5;
	axis2.renderer.labels.template.bent = true;
	//axis2.renderer.labels.template.fill = am4core.color("#000");
	axis2.renderer.labels.template.fontWeight = "bold";
	axis2.renderer.labels.template.fillOpacity = 0.3;

	/**
	Ranges
	*/

	for (let grading of data.gradingData) {
	  var range = axis2.axisRanges.create();
	  range.axisFill.fill = am4core.color(grading.color);
	  range.axisFill.fillOpacity = 3.8;
	  range.axisFill.zIndex = -1;
	  range.value = grading.lowScore > chartMin ? grading.lowScore : chartMin;
	  range.endValue = grading.highScore < chartMax ? grading.highScore : chartMax;
	  range.grid.strokeOpacity = 0;
	  //range.stroke = am4core.color(grading.color).lighten(-0.1);
	 // range.label.inside = true;
	 // range.label.text = grading.title.toUpperCase();
	  range.label.inside = true;
	  range.label.location = 0.5;
	  range.label.inside = true;
	  range.label.radius = am4core.percent(10);
	  range.label.paddingBottom = -5; // ~half font size
	  range.label.fontSize = "0.9em";
	  
	}

	var matchingGrade = lookUpGrade(data.score, data.gradingData);

	/**
	 * Label 1
	 */

	var label = chart.radarContainer.createChild(am4core.Label);
	label.isMeasured = false;
	label.fontSize = "3em";
	label.x = am4core.percent(50);
	label.paddingBottom = 0;
	label.horizontalCenter = "middle";
	label.verticalCenter = "bottom";
	
	//label.dataItem = data;
	label.text = data.score;
	//label.tooltipText = "Current Year -"+cur+" \n 10 Year Avg - "+avg+" \n 10 Year Max -"+total;
	
	//label.text = "{score}";
	//label.fill = am4core.color(matchingGrade.color);

	/*
	* Label 2
	*/

	/*var label2 = chart.radarContainer.createChild(am4core.Label);
	label2.isMeasured = false;
	label2.fontSize = "2em";
	label2.horizontalCenter = "middle";
	label2.verticalCenter = "bottom";
	//label2.text = matchingGrade.title.toUpperCase();
	//label2.fill = am4core.color(matchingGrade.color);
	label2.text = chartName;*/
	
	/*
	* Hand
	*/

	var hand = chart.hands.push(new am4charts.ClockHand());
	hand.axis = axis2;
	hand.innerRadius = am4core.percent(55);
	hand.startWidth = 8;
	hand.pin.disabled = true;
	hand.value = data.score;
	//hand.fill = am4core.color("#444");
	//hand.stroke = am4core.color("#000");

	//hand.events.on("positionchanged", function(){
	//  label.text = axis2.positionToValue(hand.currentPosition).toFixed(1);
	//  alert(hand.currentPosition);
	//  var value2 = axis.positionToValue(hand.currentPosition);
	//  var matchingGrade = lookUpGrade(axis.positionToValue(hand.currentPosition), data.gradingData);
	//  label2.text = 'IHD';//matchingGrade.title.toUpperCase();
	 // label2.fill = am4core.color(matchingGrade.color);
	 /// label2.stroke = am4core.color(matchingGrade.color);  
	 // label.fill = am4core.color(matchingGrade.color);
	//})
}

function rankWiseHospitalAdmission(list){
	//Themes begin
	am4core.useTheme(am4themes_animated);
	//Themes end

	//Create chart instance
	var chart = am4core.create("rankWiseHospitalAdmission", am4charts.XYChart);
	chart.logo.disabled = true;

	chart.dateFormatter.dateFormat = "dd MMM YYYY";
	chart.numberFormatter.numberFormat = "#.#a";
	chart.numberFormatter.bigNumberPrefixes = [ 
		{ "number": 1e+3, "suffix": "K" },
		{ "number": 1e+6, "suffix": "M" },
		{ "number": 1e+9, "suffix": "B" }];

	//Chart title
	var title = chart.titles.create();
	//title.text = "DAILY CASES";
	title.fontSize = 20;
	title.paddingBottom = 5;

	//Add data
	chart.data = [{
		 "date1": new Date(2020, 0, 9),
		 "Col": 112,
		 "Brg": 207,
		 "MajGen": 312,
		 "LeftGen": 77,
		 "Others": 227,
		 "AMCADCMNS": 400,
		 "Unusual": 280
	 }, {
		 "date1": new Date(2020, 0, 16),
		 "Col": 200,
		 "Brg": 97,
		 "MajGen": 386,
		 "LeftGen": 105,
		 "Others": 330,
		 "AMCADCMNS": 350,
		 "Unusual": 170
	 }, {
		 "date1": new Date(2020, 1, 5),
		 "Col": 250,
		 "Brg": 126,
		 "MajGen": 402,
		 "LeftGen": 263,
		 "Others": 400,
		 "AMCADCMNS": 100,
		 "Unusual": 333
	 },
	 {
		 "date1": new Date(2020, 1, 12),
		 "Col": 290,
		 "Brg": 186,
		 "MajGen": 362,
		 "LeftGen": 293,
		 "Others": 346,
		 "AMCADCMNS": 180,
		 "Unusual": 383
	 }
	];

	 
	// Create axes
	 var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	 dateAxis.renderer.grid.template.location = 0;

	 var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

	 // Create series
	 function createSeries(field, name, color, dashed) {
	   var series = chart.series.push(new am4charts.LineSeries());
	   series.dataFields.valueY = field;
	   series.dataFields.dateX = "date1";
	   series.name = name;
	   //series.tooltipText = "{dateX}: [b]{valueY}[/]";
	   series.tooltipText = "[bold]{name}[/]\n{dateX}: [b]{valueY}[/]";
	   series.strokeWidth = 2;
	   series.stroke = color;
	   
	  /* var bullet = series.bullets.push(new am4charts.CircleBullet());
	   bullet.circle.stroke = am4core.color("#fff");
	   bullet.circle.strokeWidth = 2;*/
	   
	   if (dashed) {
		   series.strokeDasharray = "5 3";
	   }
	   
	   return series;
	 }

	 var series1 = createSeries("Col", "Col",am4core.color("#008080"));
	 var series2 = createSeries("Brg", "Brg", am4core.color("#0000FF"));
	 var series3 = createSeries("MajGen", "Maj Gen", am4core.color("#9ACD32"));
	 var series4 = createSeries("LeftGen", "Left Gen & Gen", am4core.color("#87CEFA"));
	 var series5 = createSeries("Others", "Others", am4core.color("#F4A460"));
	 var series6 = createSeries("AMCADCMNS", "AMC/ADC/MNS", am4core.color("#800000"), true);
	 var series7 = createSeries("Unusual", "Unusual Occ", am4core.color("#e60000"), true);
	 
	 chart.legend = new am4charts.Legend();
	 chart.cursor = new am4charts.XYCursor();
	 chart.scrollbarX = new am4core.Scrollbar();

}

function bedStateChart()
{			
	var colArray = new Array();				
	
	colArray.push({'colname': 'HQ WC CORPS 21','laidout': '120', 'occupied': '48', 'perbed': '40%'}); 
	colArray.push({'colname': 'HQ WC CORPS 10','laidout': '300', 'occupied': '181', 'perbed': '60.33%'}); 
	colArray.push({'colname': 'HQ WESTERN COMMAND','laidout': '550', 'occupied': '316', 'perbed': '57.45%'}); 		
	
	if(colArray.length <= 0){
		alert("Data not available");
	}
	
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("bedStateSummarydiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =colArray;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; //"Number of Counts";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 120;	
	
	var label1 = categoryAxis_cluster.title;
	label1.fontSize = 15;
	label1.fontWeight = "bold";
	label1.stroke=am4core.color("#0000ff");
	label1.strokeWidth = 0.6;
	label1.strokeOpacity = 0.2;
	label1.textDecoration = "underline";
	
	var label12 = valueAxis_cluster.title;
	label12.fontSize = 15;
	label12.fontWeight = "bold";
	label12.stroke=am4core.color("#0000ff");
	label12.strokeWidth = 0.6;
	label12.strokeOpacity = 0.2;
	label12.textDecoration = "underline";

	var colorSet = new am4core.ColorSet();
	colorSet.list = [  "#ff3377", "#1BA68D", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc","#FBC02D","#388E3C","#0288d1", "#F44336" ].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	function createSeries_cluster(field, name) {
		var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	if(field == "occupied")
	  	{
	  		series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY} ({perbed})[/]";
	  		//series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/] \n Avail: [bold]{perbed}[/]";
	  	}
	  	else
	  		series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	
	    series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	  	
	  	var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
	    bullet.label.text = "{colname}";
	    bullet.label.rotation = 90;
	    bullet.label.truncate = false;
	    bullet.label.hideOversized = false;
	    bullet.label.horizontalCenter = "left";
	    bullet.locationY = 1;
	    bullet.dy = 10;
	    
	} 
	createSeries_cluster("laidout", "Laid Out", true);
	createSeries_cluster("occupied", "Occupied", true);				
	clusterddiv.legend = new am4charts.Legend();
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc"); 
	
}

