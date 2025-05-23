<style nonce="${cspNonce}">
	#content{
		margin: 0 auto;
		width: 960px;
	}
	.clearfix:after {
		content: ".";
		display: block;
		clear: both;
		visibility: hidden;
		line-height: 0;
		height: 0;
	}
	 
	.clearfix {
		display: block;
	}
	#main-body{
		text-align: center;
	}
	.enormous-font{
	font-size: 10em;
	margin-bottom: 0em;
}
.bree-font{
	font-family: 'Bree Serif', serif;
}
.big-font{
	font-size: 2em;
}
hr{
	width: 25%;
	height: 1px;
	background: #1f3759;
	border: 0px;
}
div1{
	font-family: 'Source Sans Pro', sans-serif;
	background: #3f6eb3;
	color: #1f3759;
}
a:link{
	color: #1f3759;
	text-decoration: none;
}
a:active{
	color: #1f3759;
	text-decoration: none;
}
a:hover{
	color: #9fb7d9;
	text-decoration: none;
}
a:visited{
	color: #1f3759;
	text-decoration: none;
}

a.underline, .underline{
	text-decoration: underline;
}

p {
    color:white;
}

</style>

<div class="animated fadeIn" >
	<div class="">
		<div class="container" align="center">
			<div class="card bg3f6eb3" >
				<h3  class="color_red"><b>404</b></h3>
				<div class="card-header"><strong>Error Details</strong></div>
				<div class="card-body card-block bg3f6eb3" >
					<div class="col-md-12">
						<div id="content">
							<div class="clearfix"></div>
						    <div id="main-body" >
					        	<p class="big-font" > ...........Under..Process.........</p>
					            <hr>
					     
					        	 <p class="big-font"> Let's return to the <a href="helpdesk" class="underline">Home Page</a> in <span id="countdown">3</span> seconds</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
  <script nonce="${cspNonce}">
        // Set the countdown time in seconds
        var countdownTime = 3;

        // Update the countdown every second
        var countdownElement = document.getElementById('countdown');
        var interval = setInterval(() => {
            countdownTime--;
            countdownElement.textContent = countdownTime;

            // When countdown reaches 0, redirect to the specified URL
            if (countdownTime <= 0) {
                clearInterval(interval);
                window.location.href = 'helpdesk'; // Replace with your desired URL
            }
        }, 1000);
    </script>