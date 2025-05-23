$(document).ready(function() {
	var mvcrProgressCount = "";
	var transctionCount = "";
	var ro_rioCount = "";
});
function progressbar(mvcrProgressCount,transctionCount,ro_rioCount){
	if(mvcrProgressCount != 0){
		animateElements(mvcrProgressCount);
		$(window).scroll(animateElements);
	}
	if(transctionCount != 0){
		animateElementsmmstrans(transctionCount);
		$(window).scroll(animateElementsmmstrans);
	}
	if(ro_rioCount != 0){
		animateElementsmmsro_rio(ro_rioCount);
		$(window).scroll(animateElementsmmsro_rio);
	}
}
function animateElements(mvcrProgressCount) {
	$('#progressbar').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = mvcrProgressCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle1mms').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 160,
				thickness: 30,
				emptyFill: "rgba(0,0,0, .2)",
				fill: {
					color: '#14385f'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
			//	alert(stepValue);
				$("div#mmsmvcr").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}

function animateElementsmmstrans(transProgressCount) {
	$('#progressbarTransmms').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = transProgressCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle2mms').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 120,
				thickness: 10,
				emptyFill: "rgba(0,0,0, .2)",
				fill: {
					color: '#ffa500'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
			
				$("div#mmstrans").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}

function animateElementsmmsro_rio(ro_rioCount) {
	$('#progressbarRomms').each(function () {
		var elementPos = $(this).offset().top;
		var topOfWindow = $(window).scrollTop();
		var percent = ro_rioCount;
		var percentage = parseInt(percent, 10) / parseInt(100, 10);
		
		var animate = $(this).data('animate');
		if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
			$(this).data('animate', true);
			$(this).find('.circle3mms').circleProgress({
				startAngle: -Math.PI / 2,
				value: percent / 100,
				size: 120,
				thickness: 10,
				emptyFill: "rgba(0,0,0, .2)",
				fill: {
					color: '#008000'
				}
			}).on('circle-animation-progress', function (event, progress, stepValue) {
			//	alert(stepValue);
				$("div#mmsro_rio").text((stepValue*100).toFixed(2) + "%");
			}).stop();
		}
	});
}


