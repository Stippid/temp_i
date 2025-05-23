<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style nonce="${cspNonce}">

.faq_header{
    background-color: #3b588a;
}
.faq_header h5{
    font-size: 19px;
    color: #fff;
    padding: 9px;
    text-align: center;
    margin: 5px;
    margin-bottom: 15px;
    text-transform: uppercase;
    font-weight: bold;
}
.panel-heading {
    padding: 8px 16px;
    margin-bottom: 12px;
}
.panel-default{
    background-color: #3c709a;
}
/* .panel-default:nth-child(even){
    background-color: #7b884b;
} */
.panel-title a {
    font-size: 17px;
    color: #fff;
    /* font-family: Arial, Verdana; */
    text-transform: uppercase;
}
.panel-title a:hover,
.panel-title a:active{ 
   color: #15ddff;
   text-decoration: underline;
}

.panel-group a:after {
    font-family: 'FontAwesome';
    font-style: normal;
    font-size: 36px;
    content: "\f106";
    color: #3577ad;
    float: right;
    margin-top: -12px;
}
.panel-group a.collapsed:after {
    content: "\f107";
}
.panel-title a:after{
    color: #fff;
    font-size: 38px;
}
.panel-title-que a{
    font-size: 17px;
    color: #3577ad;
}
.panel-title-que a:hover,
.panel-title-que a:active{ 
   color: #1c4a9a;
   text-decoration: underline;
}
.panel-title-que a:focus{ 
   color: #1c4a9a;
   text-decoration: none;
}
.panel-default-que{
    background-color: #fff;
    margin-bottom: 12px;
    box-shadow: 0 1px 2px rgba(0,0,0,.08);
    transition: box-shadow .2s;
}
.panel-heading-que {
    padding: 10px 16px;
}
p{
    color: #6b7d8e;
    padding-bottom: 10px;
    font-size: 15px;
}
a{
    transition: all 0.3s ease-in-out;
}
.panel-body{
    padding-bottom: 10px;
}
</style>
</head>
<body>
<section class="accordion-section clearfix mt-3" aria-label="Question Accordions">
  <div class="container">
	 <div class="faq_header"> <h5>Frequently Asked Questions</h5></div> 
	  <div class="panel-group" id="accordion">
	  	${faq}
	 </div>
  </div>
</section>
</body>
</html>