<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<!doctype html>
<html>
<head>
<script type="text/javascript" src="login_file/jquery-3.7.1.min.js" nonce="${cspNonce}"></script> 
	<link rel="shortcut icon" href="login_file/favicon.png" nonce="${cspNonce}" >
	<title><spring:message code="myapp.title" /></title>
	<link rel="stylesheet" href="login_file/style.css" nonce="${cspNonce}">
<%-- 	<link rel="stylesheet" href="admin/js/assets/css/font-awesome.min.css" nonce="${cspNonce}"> --%>
	<link rel="stylesheet" href="admin/js/assets/css/bootstrap.min.css"  nonce="${cspNonce}" >
	
	<script src="admin/js/assets/js/bootstrap.min.js" nonce="${cspNonce}"></script> 
	<script src="admin/js/assets/js/bootstrap.bundle.min.js" nonce="${cspNonce}"></script> 

	<style type="text/css"  nonce="${cspNonce}">
	.about_us_text {
	    max-width: 295px;
	    position: absolute;
	    /* right: 15px; */
	    text-align : center;
	    background-color: rgba(255,255,255,0.8);
	    background-color: #1fc8db;
	    background-image: linear-gradient(141deg,#9fb8ad 0,#1fc8db 51%,#2cb5e8 75%);
	    border: 1px solid #ccc;
	    border-radius: 10px;
	    top: 15px;
	    opacity: .9;
	}
	.float_left{
	float:left;}
	.display_none{
	display:none;
	}
	.margin_15{
	margin: 15px 0 0;
	}
	.color_red{
	color:red !important;
	}
	.position_fixed{
	position: fixed;
	}
	.bottom_20{
	bottom:20px;
	}
	.about_ul
	{
display: inline-block;list-style-type: square;color:#672a2a;font-weight: 600;font-size: 16px;
	}
	</style>

</head><body id="body_id" >
		<header id="header" class="header">
			<div class="header-menu">
	    		<div class="row">
			      	<div class="col-2 col-sm-2">
			        	<div class="header-left float_left">
			          		<div id="language-select"> <img src="login_file/logo2.png" class="img-fluid"> </div>
			        	</div>
					</div>
					<div class="col-8 col-sm-8">
						<div class="heading_content">
							<h1 class="main-header-name"><spring:message code="myapp.name" /></h1> <b class="display_none" id="div_timeout">60</b>
						</div>
					</div>
			      	<div class="col-2 col-sm-2">
			        	<div id="language-select"> <img src="login_file/logo2.png" class="img-fluid"> </div>
					</div>
				</div>
			</div>
	  	</header> 
	  	<div class="container-fluid flex_grow" >
	  	<div class="row m-0 p-0">
	  	
	  	<div class="col-12 col-xl-9 col-lg-7 col-md-7 col-sm-6 px-0">
	  	<div class="content banner">
			<div class="middle_content" id="login">


<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
<!--     <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
  </div>
  <div class="carousel-inner">
<!--     <div class="carousel-item active"> -->
<!--       <img src="login_file/slider_img4.jpg" class="d-block w-100" alt="..."> -->
<!--       <div class="carousel-caption d-none d-md-block"> -->
<!--         <h5>First slide</h5> -->
<!--         <p>first slide</p> -->
<!--       </div> -->
<!--     </div> -->
    <div class="carousel-item active">
      <img src="login_file/slider_img2.jpg" class="d-block w-100" alt="...">
<!--       <div class="carousel-caption d-none d-md-block"> -->
<!--         <h5>Second slide label</h5> -->
<!--         <p>Some representative placeholder content for the second slide.</p> -->
<!--       </div> -->
    </div>
    <div class="carousel-item">
      <img src="login_file/slider_img3.jpg" class="d-block w-100" alt="...">
<!--       <div class="carousel-caption d-none d-md-block"> -->
<!--         <h5>Third slide label</h5> -->
<!--         <p>Some representative placeholder content for the third slide.</p> -->
<!--       </div> -->
    </div>
  </div>
  <div class="control-btn">
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
  </div>
</div>
				
			</div>
		<div class="middle_content display_none" align="center" id="about_us"
			>
			<div class="row margin_15" >
				<div class="col-md-6">
					<h4 class="about_heading">About Us</h4>
					<p class="about_content">About Us Content</p>
				</div>
				<div class="col-md-6">
					<h4 class="about_heading">OUR AIM</h4>
					<p class="about_content">Our AIM Content</p>
				</div>
			</div>
		</div>
		<div class="middle_content display_none" align="center" id="contact_us"  >
			<div class="row margin_15" >
				<div class="col-md-12"><div class="col-md-4"></div><div class="col-md-4"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 1 : </b></p> <h5>1234</h5></div></div><div class="col-md-4"></div></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 2 : </b></p> <h5>1234</h5></div></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 3 :</b></p> <h5>1234</h5></div></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 4  :</b></p> <h5>1234, 1234</h5></div></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 5 :</b> </p> <h5>1234, 1234</h5></div></div>
			   	<div class="col-md-3"></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 6 :</b></p> <h5> 1234, 1234</h5></div></div>
			   	<div class="col-md-3"><div class="contact_info"><p><img alt="Refersh" src="login_file/telephone.ico"><b> Contact Us 7 :</b> </p> <h5>1234, 1234</h5></div></div>
			</div>
			<div class="margin_15">
				<p class="about_heading">Please Note :</p>
				<ul class="about_ul">
				    <li >Office timings are 0900h-1300h and 1400h-1730h.</li>
					<li>Offices are closed on Saturday and Sundays.</li>
				</ul>
			</div>	
		</div>
	</div> <!--end of banner-->
	  	</div>
	  	
	  	<div class="col-12 col-xl-3 col-lg-5 col-md-5 col-sm-6 px-0">
	  	<div class="login_box" >
	  
						<div class="left">
						<div class="contact">
							
							<div class="login-form">
							<img src="login_file/img.jpg" class="login-img" alt="...">
							<h2 class="navbar-brand">LOGIN</h2>
									<p class="login-text">Please enter your credentials to login.</p>
									<c:if test="${not empty error}"><p class="color_red">${error}</p></c:if>
									<c:if test="${not empty msg}"><p class="color_red">${msg}</p></c:if>
								<form role="form"  name='loginForm' action="#" method='POST' id="myFormId" class="login-form inputHeight">
									<div class="form-group">
										<input id="username" type='text' name='username' class="form-control disablecopypaste" maxlength="30" size="35" autocomplete="off" placeholder="Enter Username">					
									</div>
									<div class="row">
										<div class="col-md-12 ">
											<div class="form-group">

												<input id="password" type='password' name='password'
													class="form-control disablecopypaste" maxlength="28"
													size="35"
													pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%!^\\&_.~*]).{8,28}$"
													autocomplete="off" placeholder="Enter Password" />

												<div id="togglePasswordhosp" class=" eyes_button ">
													<i class="fa fa-eye" id="eye-icon2"></i>

												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-12 enter_captcha">
											<div class="form-group">
												<input type='text' class="form-control disablecopypaste" size="35" id="txtInput" name="txtInput"  placeholder="Enter Captcha"  autocomplete="off"/>	
											</div>
										</div>
										<div class="col-md-6 col-sm-6 col-xs-12 no-padding-left">
											<div class="form-group captcha">
												<div class="input-group">
													<canvas id="canvasCapcha" width="100%" height="50%"  class="form-control disablecopypaste" readonly></canvas> 
													<span class="input-group-btn">
														<button class="btn btn-primary btn-sm" id="btnrefresh" tabindex="-1" type="button">
															<img alt="Refersh" src="login_file/referesh.ico" >
														 </button>
													</span>
												</div>
											</div>	
										</div>
									</div>
									<div class="form-group login">
										<button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30" id="form_submit">LOGIN</button>
										<div class="tooltip">
											<a class="text-black">Reset Password</a>
											<span class="tooltiptext">Please click <a target="_blanck" href="/doc/Request_for_New_Password.pdf" >here</a> to download the reset password form and send it to Authorized Person.</span>
										</div>
									</div>
									<input type="hidden" id="csrfIdSet" name="" value="" />
								</form>
							</div>
							</div>
							</div>
							
						</div>
	  	</div>
	  	</div></div>
	  	
	<div class="bisag-logo  position_fixed bottom_20">
<!-- 		<img src="login_file/bisag.png" class="img-fluid bisag-img"> -->
	</div>
	<footer>
		<span class="content1 left">Website content owned by <a class="f-link">AFMS Office IT Inventory</a>. Designed, developed and maintained by <a href="https://bisag-n.gov.in/" class="f-link" target="_blank">BISAG-N</a>
						</span>
		<span class="content2 right">Connected to ${server} || Last Updated 25-04-2025 Visitors ${visiter_count}</span>
	</footer>
</body>
</html>

<script type="text/javascript" nonce="${cspNonce}">
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";
var csrfparname = "${_csrf.parameterName}";
var csrfvalue = "${_csrf.token}";
var yuji = "<c:url value='/auth/login_check?targetUrl=${targetUrl}' />";



var c = document.getElementById("canvasCapcha");
var ctx = c.getContext("2d");
var sets = [0,17,34,51,68];
var hei = [25,40,25,40,25];
var numb = [ 1, 2, 3, 4, 5 ];



	jQuery(document).ready(function() {

		clear();

		    var myArrayString = '${capchaCode}';
		    var myArray = myArrayString.slice(1, -1).split(',').map(item => item.trim());
		    
		    
		draw_captcha(myArray);
		
		var $togglePasswordhosp = $('#togglePasswordhosp');
		var $passwordhosp = $('#password');
		var $eyeIcon2 = $('#eye-icon2');

		$togglePasswordhosp.on('click', function() {

			if ($passwordhosp.attr('type') === 'password') {
				$passwordhosp.attr('type', 'text');
				$eyeIcon2.removeClass('fa-eye-slash').addClass('fa-eye');
			} else {
				$passwordhosp.attr('type', 'password');
				$eyeIcon2.removeClass('fa-eye').addClass('fa-eye-slash');
			}
		});

		$('body').bind('cut copy paste', function(e) {
			e.preventDefault();
		});
		$(".dropdown").hover(function() {
			var dropdownMenu = $(this).children(".dropdown-menu");
			if (dropdownMenu.is(":visible")) {
				dropdownMenu.parent().toggleClass("open");
			}
		});
		$(window).scroll(function() {
			if ($(document).scrollTop() > 50) {
				$(".header_bottom").addClass("head_nav");
			} else {
				$(".header_bottom").removeClass("head_nav");
			}
		});

		//DrawCaptcha();
		var msg = "";
		msg = jQuery('label#msg').text();
		if ('${error}' != "") {
			jQuery("div#errorDiv").show();
		}
		if ('${msg}' != "") {
			window.alert = function(al, $) {
				return function(msg) {
					al.call(window, msg);
					$(window).trigger("okbuttonclicked");
				};
			}(window.alert, window.jQuery);

			$(window).on("okbuttonclicked", function() {
				console.log("you clicked ok");
				window.location = window.location.href.split("?")[0];
			});
			alert('${msg}');
			jQuery("div#errorDiv").show();
		}

		$("#btnrefresh").click(function() {
			clear();
			captcha();
		})
		// End Canvas Capcha
	});

	document.addEventListener('DOMContentLoaded', function() {

		document.getElementById('form_submit').onclick = function() {
			validation();
		};
		document.getElementById('body_id').oncontextmenu = function() {
			return false;
		};

	});
	function clear() {
		ctx.clearRect(0, 0, canvasCapcha.width, canvasCapcha.height);
		$("#txtInput").val("");
	};

	function captcha() {
		jQuery.post("capchaCode?" + key + "=" + value, function(j) {
			draw_captcha(j)
		});
	};

	function draw_captcha(j) {
		
		
		var captcha = '';
		for (var i = 0; i < j.length; i++) {
				numb[i] = j[i];
				captcha += j[i];
		}

		$("#txtInput").val(captcha);
		ctx.font = " italic bold 42px arial";
		ctx.fillStyle = "#999696";
		ctx.fillText(numb[4], 0, 45);
		ctx.fillText(numb[3], 20, 30);
		ctx.fillText(numb[2], 40, 45);
		ctx.fillText(numb[1], 60, 30);
		ctx.fillText(numb[0], 80, 45);

		ctx.font = " italic bold 32px arial";
		ctx.fillStyle = "#0a0000";
		ctx.fillText(numb[0], sets[0], hei[0]);
		ctx.fillText(numb[1], sets[1], hei[1]);
		ctx.fillText(numb[2], sets[2], hei[2]);
		ctx.fillText(numb[3], sets[3], hei[3]);
		ctx.fillText(numb[4], sets[4], hei[4]);
	}

	function validation() {
		var ck_username = /^[A-Za-z0-9_]{1,20}$/;
		var ck_password = /^[A-Za-z0-9!@#$%^&*()_]{6,20}$/;
		var a = document.getElementById("username");
		if (a.value == "" || a.value == "'" || a.value == null
				|| a.value.toString().trim() == "" || a.value == "'''") {
			alert("Enter username");
			a.focus();
			return false;
		}
		var b = document.getElementById("password");
		if (b.value == "" || b.value == "'" || b.value == null
				|| b.value.toString().trim() == "") {
			alert("Enter password");
			b.focus();
			return false;
		}
		if (!ValidCaptcha()) {
			alert("Captcha Validation failed!");
			return false;
		}
	}
	// Validate the Entered input aganist the generated security code function   
	function ValidCaptcha() {

		var str1 = (numb[0]) + (numb[1]) + (numb[2]) + (numb[3]) + (numb[4]);
		var str2 = removeSpaces(document.getElementById('txtInput').value);
		if (str1 == "" || str2 == "") {
			return false;
		}
		if (str1 == str2) {
			debugger;
			jQuery('#csrfIdSet').attr('name', csrfparname);
			jQuery('#csrfIdSet').attr('value', csrfvalue);
			jQuery('#myFormId').attr('action', yuji);

			return true;
		} else {
			return false;
		}
	}
	// Remove the spaces from the entered and generated code
	function removeSpaces(string) {
		return string.split(' ').join('');
	}
	function aboutus() {
		$("#login").hide();
		$("#contact_us").hide();

		$("#about_us").show();
		$(".carousel-item img").css("height", "300px");
	}

	function contactus() {
		$("#login").hide();
		$("#about_us").hide();

		$("#contact_us").show();
		$(".carousel-item img").css("height", "300px");
	}

	document.onkeydown = function(e) {
		if (e.keyCode == 123) {
			return false;
		}
		if (e.keyCode == 44) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'S'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'H'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'A'.charCodeAt(0)) {
			return false;
		}
		if (e.ctrlKey && e.keyCode == 'E'.charCodeAt(0)) {
			return false;
		}
	}

	window.history.forward();
	function noBack() {
		window.history.forward();
	}

	var SuperPlaceholder = function(options) {
		this.options = options;
		this.element = options.element
		this.placeholderIdx = 0;
		this.charIdx = 0;

		this.setPlaceholder = function() {
			placeholder = options.placeholders[this.placeholderIdx];
			var placeholderChunk = placeholder.substring(0, this.charIdx + 1);
			document.querySelector(this.element).setAttribute("placeholder",
					this.options.preText + " " + placeholderChunk)
		};

		this.onTickReverse = function(afterReverse) {
			if (this.charIdx === 0) {
				afterReverse.bind(this)();
				clearInterval(this.intervalId);
				this.init();
			} else {
				this.setPlaceholder();
				this.charIdx--;
			}
		};

		this.goReverse = function() {
			clearInterval(this.intervalId);
			this.intervalId = setInterval(
					this.onTickReverse
							.bind(
									this,
									function() {
										this.charIdx = 0;
										this.placeholderIdx++;
										if (this.placeholderIdx === options.placeholders.length) {
											// end of all placeholders reached
											this.placeholderIdx = 0;
										}
									}), this.options.speed)
		};

		this.onTick = function() {
			var placeholder = options.placeholders[this.placeholderIdx];
			if (this.charIdx === placeholder.length) {
				// end of a placeholder sentence reached
				setTimeout(this.goReverse.bind(this), this.options.stay);
			}

			this.setPlaceholder();

			this.charIdx++;
		}

		this.init = function() {
			this.intervalId = setInterval(this.onTick.bind(this),
					this.options.speed);
		}

		this.kill = function() {
			clearInterval(this.intervalId);
		}
	}
</script>

